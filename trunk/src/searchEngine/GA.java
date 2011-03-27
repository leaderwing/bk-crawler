package searchEngine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GA {
	public String extend_keyword(ArrayList<String> doc_content,
			ArrayList<Double> doc_weight) {
		int N = 20;
		String new_key = "";
		ArrayList<String> best_word = find_best_word(doc_content);
		System.out.println("best word =" + best_word);
		System.exit(0);
		// tinh tan suat cac bestword co trong doc j,tinh so doc co chua
		// bestword i
		ArrayList<Integer> num_doc_contain_bestword = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			num_doc_contain_bestword.add(0);
		}
		ArrayList<ArrayList<Integer>> list_doc_with_fre_bestword = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			ArrayList<Integer> doc = new ArrayList<Integer>();
			HashMap<String, Integer> count_keyword = new HashMap<String, Integer>();

			String[] arr_content = doc_content.get(i).split(" ");
			for (int j = 0; j < arr_content.length; j++) {
				if (count_keyword.get(arr_content[j]) == null) {
					count_keyword.put(arr_content[j], 1);

				} else {
					// int count=keyword.get(arr_content[i])+1;
					count_keyword.put(arr_content[j],
							count_keyword.get(arr_content[j]) + 1);
				}

			}
			for (int j = 0; j < 50; j++) {
				if (count_keyword.get(best_word.get(j)) == null) {
					doc.add(j, 0);
				} else {
					doc.add(j, count_keyword.get(best_word.get(j)));
					num_doc_contain_bestword.set(j,
							num_doc_contain_bestword.get(j) + 1);
				}
			}
			list_doc_with_fre_bestword.add(doc);
		}
		return new_key;
	}

	public ArrayList<String> find_best_word(ArrayList<String> doc_content) {
		ArrayList<String> best_word = new ArrayList<String>();
		// ArrayList<HashMap<String, Integer>> list_fre_term_in_docs = new
		// ArrayList<HashMap<String, Integer>>();
		int sum_length = 0;

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
			sum_length = sum_length + count_frequent_term.size();

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

			// list_fre_term_in_docs.add(count_frequent_term);

		}
		/*
		 * // string only contain a-z Pattern p =
		 * Pattern.compile("^[a-zA-Z]+$"); // find keyword has max weight in
		 * each document Double avg_length_doc = (double) (sum_length /
		 * doc_content.size()); for (int i = 0; i < doc_content.size(); i++) {
		 * HashMap<String, Integer> frequent_term_doc = list_fre_term_in_docs
		 * .get(i); Double max_weight = (double) 0; String best_term_of_doc =
		 * ""; // tu co trong so lon nhat trong document // duyet tung document
		 * 
		 * for (String key : frequent_term_doc.keySet()) { Matcher m =
		 * p.matcher(key); if (m.find()) { int num_doc_contain_key = 0; for (int
		 * j = 0; j < list_fre_term_in_docs.size(); j++) { HashMap<String,
		 * Integer> a = list_fre_term_in_docs .get(j); if (a.get(key) != null) {
		 * num_doc_contain_key++; } } // tinh trong so cho moi term trong
		 * document double ts = 0;
		 * 
		 * 
		 * Double a=Math.log(frequent_term_doc.get(key)) / Math.log(2); Double
		 * b=Math.log(doc_content.size() / num_doc_contain_key)/ Math.log(2); ts
		 * = ( a + 1)*b ;
		 * 
		 * double ms = (0.8 + 0.2 * frequent_term_doc.size()/ avg_length_doc);
		 * Double weight_term = ts / ms;
		 * 
		 * 
		 * 
		 * if (weight_term > max_weight) { max_weight = weight_term;
		 * best_term_of_doc = key; } } }
		 * 
		 * best_word.add(best_term_of_doc); }
		 */

		return best_word;
	}
}
