package searchEngine;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GA.Genome;

//import model.Newkeyword;

public class InputGA_new_approach {
	private static ArrayList<String> arr_best_word = new ArrayList<String>();
	private static ArrayList<Double> weight_best_word = new ArrayList<Double>();
	private static ArrayList<Integer> arr_sum_frequent_bestword = new ArrayList<Integer>();

	public ArrayList<String> getListBestWord() {

		return arr_best_word;
	}

	public ArrayList<Genome> find_bestword_and_create_genome(
			ArrayList<String> doc_content, ArrayList<String> doc_link,
			ArrayList<String> initial_key) {

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
			// length of each document to caculate weight of each bestword
			arr_length_of_doc.add(i, arr_content.length);
			sum_length = sum_length + arr_content.length;

			list_fre_term_in_docs.add(count_frequent_term);

		}

		// find keyword has max weight in each document

		// Pattern p = Pattern.compile("^[a-zA-Z]+$"); // string only contain
		// a-z
		// lọc tiếng việt
		Pattern p = Pattern
				.compile("^[_a-zA-ZâăáàảạãấầẩậẫắằẳẵặèéẻẹẽêểềếệễỏọòóõôồốổộỗơởờớợỡỷỳýỵỹỉĩịìíùúủụũÂĂÁÀẢẠÃẤẦẨẬẪẮẰẲẴẶÈÉẺẸẼÊỂỀẾỆỄỎỌÒÓÕÔỒỐỔỘỖƠỞỜỚỢỠỶỲÝỴỸỈĨỊÌÍÙÚỦỤŨ]+$");
		Double avg_length_doc = (double) (sum_length / doc_content.size());

		ArrayList<String> best_term = new ArrayList<String>();
		ArrayList<Double> weight_best_term = new ArrayList<Double>();
		for (int i = 0; i < doc_content.size(); i++) {

			HashMap<String, Integer> frequent_term_doc = list_fre_term_in_docs
					.get(i);
			Double max_weight = (double) 0;
			// tu co trong so lon nhat trong document
			String best_term_of_doc = "";
			// duyet tung document
			int F = 0;
			for (String key : frequent_term_doc.keySet()) {
				Matcher m = p.matcher(key);
				if (m.find()) {

					int f = 0;
					for (int j = 0; j < list_fre_term_in_docs.size(); j++) {

						HashMap<String, Integer> a = list_fre_term_in_docs
								.get(j);
						if (a.get(key) != null) {
							f = f + a.get(key);
						}
					} // tinh trong so cho moi term trong document
					double px = (double) frequent_term_doc.get(key)
							/ frequent_term_doc.size();
					double pc = (double) f / sum_length;
					double weight_term = 0;
					if ((pc != 0) && (px != 0)) {
						weight_term = (double) px * Math.log((double) px / pc)
								/ Math.log(2);
					}

					if ((weight_term > max_weight)
							&& (!best_term.contains(key))) {
						F = f;
						max_weight = weight_term;
						best_term_of_doc = key;
					}
				}
			}
			arr_sum_frequent_bestword.add(F);
			weight_best_term.add(max_weight);
			best_term.add(best_term_of_doc);
		}
		arr_best_word = best_term;
		weight_best_word = weight_best_term;

		// end find bestword
		// tính trọng số của các best wors trong các doc
		// ArrayList<Genome> population = new ArrayList<Genome>();
		// for(int i=0;i<doc_content.size();i++) {
		// HashMap<String, Integer> frequent_term_doc =
		// list_fre_term_in_docs.get(i);
		// ArrayList<Double> arr_weight_of_doc = new ArrayList<Double>();
		// for(int j=0;j<arr_best_word.size();j++) {
		//
		// String key= arr_best_word.get(j);
		// //neu tai lieu nay co chua key cua tai lieu khac
		// if (frequent_term_doc.get(key) != null) {
		// double px = (double) frequent_term_doc.get(key)/
		// frequent_term_doc.size();
		// double pc = (double) arr_sum_frequent_bestword.get(j)/ sum_length;
		// Double weight_term = (double) px* Math.log((double) px / pc) /
		// Math.log(2);
		// arr_weight_of_doc.add(weight_term);
		// }
		// else {
		// arr_weight_of_doc.add(0.0);
		// }
		//
		//
		// }
		//
		// Genome gene = new Genome(arr_weight_of_doc);
		// population.add(gene);
		// }
		ArrayList<ArrayList<Integer>> arr_doc_contain_frequent_bestword = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> num_doc_contain_key = new ArrayList<Integer>();
		for (int i = 0; i < doc_content.size(); i++) {
			num_doc_contain_key.add(i, 0);
		}
		// System.out.println("list best word ="+ best_term+"\n");
		// dem so lan xuat hien cua bestword trong moi document,so document chua
		// bestword đó
		for (int i = 0; i < list_fre_term_in_docs.size(); i++) {
			ArrayList<Integer> doc = new ArrayList<Integer>();
			HashMap<String, Integer> frequent_term_in_doc = list_fre_term_in_docs
					.get(i);

			for (int j = 0; j < arr_best_word.size(); j++) {
				if (frequent_term_in_doc.get(arr_best_word.get(j)) == null) {
					doc.add(0);

				} else {
					if (frequent_term_in_doc.get(arr_best_word.get(j)) > 0) {
						doc.add(frequent_term_in_doc.get(arr_best_word.get(j)));

						int count = num_doc_contain_key.get(j) + 1;
						num_doc_contain_key.add(j, count);
					}

				}

			}
			// System.out.println("i="+i+": "+ doc+"\n");
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

			for (int j = 0; j < arr_best_word.size(); j++) {
				double ts = 0;
				int frequent_term_j = doc.get(j);
				if (frequent_term_j == 0) {
					ts = 0;
				} else if ((frequent_term_j > 0)
						&& (num_doc_contain_key.get(j) > 0)) {
					double logf = Math.log(frequent_term_j) / Math.log(2);
					double logNn = Math.log((double) N
							/ num_doc_contain_key.get(j))
							/ Math.log(2);
					ts = (logf + 1) * logNn;
				}
				double a = (double) arr_length_of_doc.get(j) / avg_length_doc;
				double ms = (0.8 + 0.2 * a);
				if (ms != 0) {
					Double weight = (double) ts / ms;
					if (weight > max_weight) {
						max_weight = weight;

					}
					weight_bestword_in_doc.add(j, weight);
				} else {
					weight_bestword_in_doc.add(j, 0.0);
				}
			}
			// System.out.println("array weight of doc "+i+":"+weight_bestword_in_doc+"\n");
			// chuyen trong so ve khoang 0-1,tao quan the ban dau
			ArrayList<Double> arr_weight_of_doc = new ArrayList<Double>();
			for (int j = 0; j < weight_bestword_in_doc.size(); j++) {
				double weight = weight_bestword_in_doc.get(j);

				if (max_weight == 0) {
					weight = 0;
				} else {

					weight = (double) weight / max_weight;
				}

				arr_weight_of_doc.add(j, weight);
			}

			Genome gene = new Genome(arr_weight_of_doc);
		// System.out.println("array weight of doc 0-1 "+i+":"+arr_weight_of_doc+"\n");System.exit(0);
			population.add(gene);
		}

		return population;
	}
}
