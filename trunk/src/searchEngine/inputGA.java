package searchEngine;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.hus.nlp.tokenizer.VietTokenizer;

import GA.Genome;

public class inputGA {
	private static ArrayList<String> best_word = new ArrayList<String>();

	public  ArrayList<String> getBestWord() {
		
		return best_word;
	}

	public  ArrayList<Genome> find_bestword_and_create_genome(
			ArrayList<String> doc_content, ArrayList<String> doc_link) {
		
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
			String[] arr_content = content.split(" ");
            
			

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
		 
		 Double avg_length_doc = (double) (sum_length /doc_content.size());
		 ArrayList<String> best_term = new ArrayList<String>();
		for (int i = 0; i < doc_content.size(); i++) {

			HashMap<String, Integer> frequent_term_doc = list_fre_term_in_docs.get(i);
			Double max_weight = (double) 0;
			// tu co trong so lon nhat trong document
			String best_term_of_doc = ""; 
			// duyet tung document

			for (String key : frequent_term_doc.keySet()) {
				Matcher m = p.matcher(key);
				if (m.find()) {
					int num_doc_contain_key = 0;
					for (int j = 0; j < list_fre_term_in_docs.size(); j++) {
						
						HashMap<String, Integer> a = list_fre_term_in_docs.get(j);
						if (a.get(key) != null) {
							num_doc_contain_key++;
						}
					} // tinh trong so cho moi term trong document
					double ts = 0;
					Double a = Math.log(frequent_term_doc.get(key))/ Math.log(2);
					Double b = Math.log((double) doc_content.size()/ num_doc_contain_key)/ Math.log(2);
					ts = (a + 1) * b;
					double c = (double) frequent_term_doc.size()/ avg_length_doc;
					double ms = (0.8 + 0.2 * c);
					Double weight_term = (double) ts / ms;
					/*
					 * System.out.println("fre term doc= "+frequent_term_doc.get(
					 * key)+"\n");
					 * 
					 * System.out.println("a= "+a+"\n");
					 * System.out.println("doc content size= "
					 * +doc_content.size()+"\n");
					 * System.out.println("num doc contain key = "
					 * +num_doc_contain_key+"\n");
					 * System.out.println("b= "+b+"\n");
					 * System.out.println("fre term doc size= "
					 * +frequent_term_doc.size()+"\n");
					 * System.out.println("avg length= "+avg_length_doc+"\n");
					 * System.out.println("ts= "+ts+"\n");
					 * System.out.println("ms= "+ms+"\n");
					 * System.out.println("weight_term= "+weight_term+"\n");
					 */
					if ((weight_term > max_weight)&& (!best_term.contains(key))) {
						max_weight = weight_term;
						best_term_of_doc = key;
					}
				}
			}
           
			best_term.add(best_term_of_doc);
		}
		 best_word=best_term;
		
		//end find bestword
		 ArrayList<ArrayList<Integer>> arr_doc_contain_frequent_bestword = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> num_doc_contain_key = new ArrayList<Integer>();
		for (int i = 0; i < doc_content.size(); i++) {
			num_doc_contain_key.add(i, 0);
		}
		
		// dem so lan xuat hien cua bestword trong moi document,so document chua bestword đó
		for (int i = 0; i < list_fre_term_in_docs.size(); i++) {
			ArrayList<Integer> doc = new ArrayList<Integer>();
			HashMap<String, Integer> frequent_term_in_doc = list_fre_term_in_docs
					.get(i);
           
			for (int j = 0; j < best_word.size(); j++) {
				if (frequent_term_in_doc.get(best_word.get(j)) == null) {
					doc.add(0);
					
				} else {
					if (frequent_term_in_doc.get(best_word.get(j)) > 0) {
						doc.add(frequent_term_in_doc.get(best_word.get(j)));
						
						int count=num_doc_contain_key.get(j) + 1;
						num_doc_contain_key.add(j,count);
					}

				}
			}
			arr_doc_contain_frequent_bestword.add(doc);
		}
		
		ArrayList<Genome> population = new ArrayList<Genome>();
		int N = doc_content.size();
		for (int i = 0; i < arr_doc_contain_frequent_bestword.size(); i++) {
			// chua trong so cua bestword trong tung document
			ArrayList<Double> weight_bestword_in_doc = new ArrayList<Double>();
			ArrayList<Integer> doc = new ArrayList<Integer>();
			doc = arr_doc_contain_frequent_bestword.get(i);
			double max_weight = 0;
			
			for (int j = 0; j < best_word.size(); j++) {
				double ts = 0;
				int frequent_term_j = doc.get(j);
				if (frequent_term_j == 0) {
					ts = 0;
				} else if ((frequent_term_j > 0)&& (num_doc_contain_key.get(j) > 0)) {
					double logf=Math.log(frequent_term_j) / Math.log(2);
					double logNn =Math.log((double)N / num_doc_contain_key.get(j))/Math.log(2);
					ts = ( logf+ 1)*logNn ;
				}
				double a=(double)arr_length_of_doc.get(j)/ avg_length_doc;
				double ms = (0.8 + 0.2 * a);
				if (ms != 0) {
					Double weight = (double)ts / ms;
					if (weight > max_weight) {
						max_weight = weight;

					}
					weight_bestword_in_doc.add(j, weight);
				} else {
					weight_bestword_in_doc.add(j, (double) 0);
				}
				
				 /* System.out.println("fre_key =" + frequent_term_j + "\n");
				  System.out.println("N =" + N + "\n");
				  System.out.println("num doc contain key ="+
				  num_doc_contain_key.get(j));
				  System.out.println("length doc =" + arr_length_of_doc.get(j)
				  + "\n"); System.out.println("avg length doc =" +
				  avg_length_doc + "\n"); System.out.println("tu so =" + ts +
				  "\n"); System.out.println("mau so =" + ms + "\n");
				  System.out.println("weight j =" + ts/ms+ "\n");*/
				 

			}
			// System.out.println("link document ="+ doc_link+"\n");
			//System.out.println("weight bestword in document = "+ weight_bestword_in_doc + "\n");
			// chuyen trong so ve khoang 0-1,tao quan the ban dau
			ArrayList<Double> arr_weight_of_doc = new ArrayList<Double>();
			for (int j = 0; j < weight_bestword_in_doc.size(); j++) {
				double weight = weight_bestword_in_doc.get(j);
				//System.out.println("weight=" + weight + "\n");
				//System.out.println("max_weight =" + max_weight + "\n");
				if (max_weight == 0) {
					weight = 0;
				} else {
					BigDecimal d = new BigDecimal(weight / max_weight);
					d = d.setScale(4, BigDecimal.ROUND_HALF_UP);
					weight = d.doubleValue();
				}
				//System.out.println("weight 2 = " + weight);
				arr_weight_of_doc.add(j, weight);
			}

		//System.out.println("weight bestword in document (0-1) ="+ arr_weight_of_doc + "\n");

			Genome gene = new Genome(arr_weight_of_doc);
		//System.out.println("gen ="+ gene.getChromosome());//System.exit(0);
			population.add(gene);
		}
		
		return population;
	}
}
