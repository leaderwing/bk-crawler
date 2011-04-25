package searchEngine;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Newkeyword;



public class Input_new_approach {
	private static ArrayList<String> arr_best_word = new ArrayList<String>();
    private static ArrayList<Double> weight_best_word= new ArrayList<Double>();
    private static ArrayList<Integer> arr_frequent_bestword= new ArrayList<Integer>();
    private static ArrayList<String> new_key=new ArrayList<String>();
    private static ArrayList<Double> weight_new_key=new ArrayList<Double>();
    private static ArrayList<String> link_new_key=new ArrayList<String>();
	public  ArrayList<String> getListBestWord() {
		
		return arr_best_word;
	}

	public ArrayList<Double> getListWeightNewKey() {

		return weight_new_key;
	}

	public ArrayList<String> getLinkNewKey() {

		return link_new_key;
	}

	public  ArrayList<String> find_bestword_and_create_genome(
			ArrayList<String> doc_content, ArrayList<String> doc_link,ArrayList<String>key_extension) {
		
		// mảng thứ i là mảng lưu lại số lần xuất hiện của từng term trong
		// document thứ i : key-> value ("sport"=>33....)
		ArrayList<HashMap<String, Integer>> list_fre_term_in_docs = new ArrayList<HashMap<String, Integer>>();
		ArrayList<Integer> arr_length_of_doc = new ArrayList<Integer>();
		int sum_length = 0;
		
		// find list best word
		for (int i = 0; i < doc_content.size(); i++) {
			HashMap<String, Integer> count_frequent_term = new HashMap<String, Integer>();
			String content = doc_content.get(i);
			content = content.replace("|", " ");
			content = content.replace(",", " ");
			content = content.replace(".", " ");
			String arr_content[];
			arr_content = content.split(" ");
            
			

			for (int j = 0; j < arr_content.length; j++) {
				if (count_frequent_term.get(arr_content[j]) == null) {
					count_frequent_term.put(arr_content[j], 1);

				} else {
					int count = count_frequent_term.get(arr_content[j]) + 1;
					count_frequent_term.put(arr_content[j], count);
				}
			}
			//length of each document to caculate weight of each bestword
			arr_length_of_doc.add(i, arr_content.length);
			sum_length = sum_length + arr_content.length;

			
           
			list_fre_term_in_docs.add(count_frequent_term);

		}
		
		
		
		//find keyword has max weight in each document by LNU-LTU
		
		 Pattern p = Pattern.compile("^[a-zA-Z]+$"); // string only contain a-z 
		 
		
		 ArrayList<String> best_term = new ArrayList<String>();
		 ArrayList<Double> weight_best_term=new ArrayList<Double>();
		for (int i = 0; i < doc_content.size(); i++) {
            
			HashMap<String, Integer> frequent_term_doc = list_fre_term_in_docs.get(i);
			Double max_weight = (double) 0;
			// tu co trong so lon nhat trong document
			String best_term_of_doc = ""; 
			// duyet tung document
            int F=0;
			for (String key : frequent_term_doc.keySet()) {
				Matcher m = p.matcher(key);
				if (m.find()) {
					
					
				    int f=0;
					for (int j = 0; j < list_fre_term_in_docs.size(); j++) {
						
						HashMap<String, Integer> a = list_fre_term_in_docs.get(j);
						if (a.get(key) != null) {
							f=f+a.get(key);
						}
					} // tinh trong so cho moi term trong document
					double px =(double)frequent_term_doc.get(key)/arr_length_of_doc.get(i);
					double pc=(double)f/sum_length;
					double weight_term=0;
					if((pc!=0)&&(px!=0)) {
					   weight_term = (double) px*Math.log((double)px/pc)/Math.log(2);
					}
					
					 /*System.out.println("Tfx= "+frequent_term_doc.get(key)+"\n");
					 System.out.println("lfx= "+frequent_term_doc.size()+"\n");
					  System.out.println("px= "+px+"\n");
					  System.out.println("F= "+F+"\n");
					  System.out.println("sum lenght= "+sum_length+"\n");
					  System.out.println("pc = "+pc+"\n");
					 
					  System.out.println("weight_term= "+weight_term+"\n");System.exit(0);*/
					if ((weight_term > max_weight)&& (!best_term.contains(key))) {
						F=f;
						max_weight = weight_term;
						best_term_of_doc = key;
					}
				}
			}
			arr_frequent_bestword.add(F);
            weight_best_term.add(max_weight);
			best_term.add(best_term_of_doc);
		}
		arr_best_word=best_term;
		 weight_best_word=weight_best_term;
		 
		//end find bestword
		 //tính lại trọng số của các best wors trong các doc,update trọng số nếu nó lớn hơn cái cũ
		 for(int i=0;i<doc_content.size();i++) {
			 HashMap<String, Integer> frequent_term_doc = list_fre_term_in_docs.get(i);
			 
			 for(int j=0;j<arr_best_word.size();j++) {
				 if(j!=i) {
					String key= arr_best_word.get(j);
					
					if (frequent_term_doc.get(key) != null) {
						double px = (double) frequent_term_doc.get(key)/ arr_length_of_doc.get(i);
						double pc = (double) arr_frequent_bestword.get(j)/ sum_length;
						double weight_term=0;
						if((pc!=0)&&(px!=0)) {
						   weight_term = (double) px*Math.log((double)px/pc)/Math.log(2);
						}
						if (weight_term > weight_best_word.get(j)) {
							weight_best_word.set(j, weight_term);
						}
					}
					else {
						//neu keyword khong co trong document thi giu nguyen weight cua key o document truoc
					}
				 }
				 
			 }
		 }
		 
		//lấy ra vài key có trọng số lớn nhất mà không trùng keyword khởi tạo
		 int  counter, index;
	       int length= weight_best_word.size();
	       for(counter=0; counter<length-1; counter++) { //Loop once for each element in the array.
	           for(index=0; index<length-1-counter; index++) { //Once for each element, minus the counter.
	               if(weight_best_word.get(index) > weight_best_word.get(index+1)) { //Test if need a swap or not.
	                   double temp = weight_best_word.get(index); //These three lines just swap the two elements:
	                   weight_best_word.set(index, weight_best_word.get(index+1)) ;
	                   weight_best_word.set(index+1, temp);
	               }
	           }
	       }

		// System.out.println("weight best word ="+weight_best_word);
		 for(int n=0;n<3;n++) {
			
			 String key=arr_best_word.get(arr_best_word.size()-1);
			 double weight_key=weight_best_word.get(arr_best_word.size()-1);
			weight_best_word.remove(arr_best_word.size()-1);
			arr_best_word.remove(arr_best_word.size()-1);
			if (weight_key > 0) {
				/*boolean get = true;
				// key mới không trùng với tập key đã có
				for (int i = 0; i < key_extension.size(); i++) {
					String key_intial = key_extension.get(i);
					if (key_intial.equals(key)) {
						get = false;
						break;
					} else {
						continue;
					}
				}*/
				//if (get) {
					new_key.add(key);
					weight_new_key.add(weight_key);
					link_new_key.add(doc_link.get(arr_best_word.size() - 1));

				//}
			}
			
		}
		//System.out.println("array new key ="+ new_key);//System.exit(0);
		return Input_new_approach.new_key;
	}
}
