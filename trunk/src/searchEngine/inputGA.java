package searchEngine;

import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GA.Chromosome;
import GA.Genome;

public class inputGA {
	private static ArrayList<String> best_word = new ArrayList<String>();
	

	public static ArrayList<ArrayList<Double>> find_bestword_and_create_genome(ArrayList<String> doc_content) {
		ArrayList<String> best_word = new ArrayList<String>();
		//mảng thứ i là mảng lưu lại số lần xuất hiện của từng term trong document thứ i : key-> value ("sport"=>33....) 
		ArrayList<HashMap<String, Integer>> list_fre_term_in_docs = new ArrayList<HashMap<String, Integer>>();
		ArrayList<Integer> arr_length_of_doc= new ArrayList<Integer>();
		int sum_length = 0;
        //find list best word
		for (int i = 0; i < doc_content.size(); i++) {
			HashMap<String, Integer> count_frequent_term = new HashMap<String, Integer>();
			String content = doc_content.get(i);
			content = content.replace("|", " ");
			content = content.replace(",", " ");
			content = content.replace(".", " ");
			String[] arr_content = content.split(" ");

			for (int j = 0; j < arr_content.length; j++) {
				if (count_frequent_term.get(arr_content[j]) == null) {
					count_frequent_term.put(arr_content[j], 1);

				} else {
					int count = count_frequent_term.get(arr_content[j]) + 1;
					count_frequent_term.put(arr_content[j], count);
				}
			}
			arr_length_of_doc.add(i, arr_content.length);
			sum_length = sum_length + arr_content.length;

			int max = 0;
			String word = "";
			Pattern p = Pattern.compile("^[a-zA-Z]+$");
			for (String key : count_frequent_term.keySet()) {
				System.out.println("key/value: " + key + "/"
						+ count_frequent_term.get(key));
				Matcher m = p.matcher(key);

				if (m.find()) {
					if (count_frequent_term.get(key) > max) {
						max = count_frequent_term.get(key);
						word = key;
					}
				}
			}

			 best_word.add(word);

			list_fre_term_in_docs.add(count_frequent_term);

		}
		System.out.println("bestword ="+ best_word+"\n");
		
	
		Double avg_length_doc = (double) (sum_length/doc_content.size());
		ArrayList<ArrayList<Integer>> arr_doc_contain_frequent_bestword= new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> num_doc_contain_key = new ArrayList<Integer>();
		for(int i=0 ;i<doc_content.size();i++) {
			num_doc_contain_key.add(i, 0);
		}
		//dem so lan xuat hien cua bestword trong moi document,so document chua bestword đó
		for(int i=0;i<list_fre_term_in_docs.size();i++) {
			ArrayList<Integer> doc= new ArrayList<Integer>();
			HashMap<String, Integer> frequent_term_in_doc= list_fre_term_in_docs.get(i);
			
			for(int j=0; j<best_word.size();j++) {
				if(frequent_term_in_doc.get(best_word.get(j))==null) {
					doc.add(0);
				}
				else {
					if(frequent_term_in_doc.get(best_word.get(j))>0) {
					doc.add(frequent_term_in_doc.get(best_word.get(j)));
					num_doc_contain_key.add(j,num_doc_contain_key.get(j)+1);
					}
					
				}
			}
			arr_doc_contain_frequent_bestword.add(doc);
		}
		System.out.println("array document contain frequent bestword="+ arr_doc_contain_frequent_bestword+"\n");
		ArrayList<ArrayList<Double>> test_qt= new ArrayList<ArrayList<Double>>();
		Genome qt =new Genome();//qt=quan the
		int N= doc_content.size();
		for(int i=0;i< arr_doc_contain_frequent_bestword.size();i++) {
			//chua trong so cua bestword trong tung document
			ArrayList<Double>weight_bestword_in_doc= new ArrayList<Double>();
			ArrayList<Integer> doc= new ArrayList<Integer>();
			doc=arr_doc_contain_frequent_bestword.get(i);
			Double max_weight=0.0;
			for(int j=0;j<best_word.size();j++) {
				double ts = 0;
				int frequent_term_j=doc.get(j);
				if (frequent_term_j == 0) {
					ts = 0;
				} else if ((frequent_term_j > 0) && (num_doc_contain_key.get(j) > 0)) {
					ts = (Math.log(frequent_term_j) / Math.log(2) + 1)
							* Math.log(N / num_doc_contain_key.get(j));
				} else if ((frequent_term_j > 0) && (num_doc_contain_key.get(j) == 0)) {
					ts = (Math.log(frequent_term_j) / Math.log(2) + 1)
							* Math.log(N) / Math.log(2);
				}
				double ms = (0.8 + 0.2 * arr_length_of_doc.get(j) / avg_length_doc);
			    if(ms!=0) {
			    	Double weight= ts/ms;
			    	if(weight>max_weight) {
			    		max_weight=weight;
			    		
			    	}
			       weight_bestword_in_doc.add(j, weight);
			    }
			    else {
			    	weight_bestword_in_doc.add(j, (double) 0);
			    }
			    System.out.println("fre_key =" + frequent_term_j + "\n");
				System.out.println("N =" + N + "\n");
				System.out.println("num doc contain key ="+ num_doc_contain_key.get(j));
				System.out.println("length doc =" + arr_length_of_doc.get(j) + "\n");
				System.out.println("avg length doc =" + avg_length_doc
						+ "\n");
				System.out.println("tu so =" + ts + "\n");
				System.out.println("mau so =" + ms + "\n");
				System.out.println("weight j =" + ts/ms+ "\n");

				
			}
			System.out.println("weight bestword in document = "+ weight_bestword_in_doc+"\n");System.exit(0);
			//chuyen trong so ve khoang 0-1,tao quan the ban dau
			ArrayList<Double> arr_weight_of_doc= new ArrayList<Double>();
			for(int j=0;j<weight_bestword_in_doc.size();j++) {
				Double weight = weight_bestword_in_doc.get(j);
				System.out.println("weight="+ weight+"\n");
				System.out.println("max_weight ="+ max_weight+"\n");
				weight=(double) (Math.round((weight/max_weight)*1000)/1000);//1205.6318->1205.63
				System.out.println("weight 2 = "+ weight);
				arr_weight_of_doc.add(j,weight);
			}
			System.out.println("weight bestword in document (0-1) ="+ arr_weight_of_doc+"\n");
			test_qt.add(arr_weight_of_doc);
			Chromosome nst= new Chromosome(arr_weight_of_doc);//nst= nhiem sac the
			qt.AddChromosome(nst);
		}
        
		return test_qt;
	}
}
