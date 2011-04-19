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
    private static ArrayList<String> best_word=new ArrayList<String>();
	public  ArrayList<String> getListBestWord() {
		
		return arr_best_word;
	}

	public  ArrayList<String> find_bestword_and_create_genome(
			ArrayList<String> doc_content, ArrayList<String> doc_link,ArrayList<String>initial_key) {
		
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
					double px =(double)frequent_term_doc.get(key)/frequent_term_doc.size();
					double pc=(double)f/sum_length;
					Double weight_term = (double) px*Math.log((double)px/pc)/Math.log(2);
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
		 System.out.println("before array best word ="+ arr_best_word+"\n");
		 System.out.println("before weight best word ="+ weight_best_word+"\n");
		//end find bestword
		 //tính lại trọng số của các best wors trong các doc,update trọng số nếu nó lớn hơn cái cũ
		 for(int i=0;i<doc_content.size();i++) {
			 HashMap<String, Integer> frequent_term_doc = list_fre_term_in_docs.get(i);
			 
			 for(int j=0;j<arr_best_word.size();j++) {
				 if(j!=i) {
					String key= arr_best_word.get(j);
					
					if (frequent_term_doc.get(key) != null) {
						double px = (double) frequent_term_doc.get(key)/ frequent_term_doc.size();
						double pc = (double) arr_frequent_bestword.get(j)/ sum_length;
						Double weight_term = (double) px* Math.log((double) px / pc) / Math.log(2);
						if (weight_term > weight_best_word.get(j)) {
							weight_best_word.set(j, weight_term);
						}
					}
				 }
				 
			 }
		 }
		 System.out.println("after array best word ="+ arr_best_word+"\n");
		 System.out.println("after weight best word ="+ weight_best_word+"\n");
		//lấy ra vài key có trọng số lớn nhất mà không trùng keyword khởi tạo
		 String new_key="";
		for(int i=0;i<3;i++) {
			double weight=0;
			int pos= 0;
			for(int j=0;j<weight_best_word.size();j++) {
				if(weight<weight_best_word.get(j)) {
					weight=weight_best_word.get(j);
					pos=j;
				}
			}
			
			 new_key=arr_best_word.get(pos);
			 System.out.println("new key="+new_key+"\n");
			weight_best_word.remove(pos);
			arr_best_word.remove(pos);
			if(initial_key.contains(new_key)) {
				i--;
			}
			else {
				best_word.add(new_key);
			}
		}
		System.out.println("array new key ="+ best_word);//System.exit(0);
		return best_word;
	}
}
