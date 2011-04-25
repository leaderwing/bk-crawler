package searchEngine;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Bestword;
import model.DocumentRelative;
import model.Newkeyword;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import vn.hus.nlp.tokenizer.VietTokenizer;

import GA.Algorithm;
import GA.Genome;

public class indexVN {
	private static double mutation_rate; // phan tram dot bien
	private static double crossover_rate; // phan tram lai ghep
	private static int number_generation; // phan tram lai ghep

	public static void main_2(String args[]) {
		// array contain keyword
		ArrayList<String> arr_keywords = new ArrayList<String>();
		arr_keywords.add("kinh_tế");
		arr_keywords.add("thị_trường");
		arr_keywords.add("doanh_nghiệp");
		//arr_keywords.add("trận");
		//arr_keywords.add("cđv");
		//arr_keywords.add("vô_địch");
		//arr_keywords.add("tỷ_số");
		//arr_keywords.add("thắng");
		//arr_keywords.add("clb");
		//arr_keywords.add("cup");
		indexVN.mutation_rate = 0.01;
		indexVN.crossover_rate = 0.8;
		indexVN.number_generation = 100;
		// so nst trong quan the
		int num_nst = 20;
		// seed url
		String url = "http://dantri.com.vn/c76/kinhdoanh.htm";
		// array contain the number of occurrences of term j in document i
		int[] arr_frequency_term = new int[arr_keywords.size()];
		// tong so document da crawler
		int N = 0;
		
		// length of document
		int length_doc;
		// sum length of documents
		int sum_length_doc = 0;
		// the weight of the term j in document i
		double weight_doc_j;
		// sum weight of the document
		double weight_doc;
		// max document crawler
		int max_doc = 500;
		// content document
		String content_doc;
		// url of document
		String url_doc = "";
		// array chua cac link da crawler
		ArrayList<String> arr_link_crawled = new ArrayList<String>();
		// array chua cac link duoc uu tien cao nhat
		ArrayList<String> arr_priority_link = new ArrayList<String>();
		// array link duoc xep theo weight of document
		ArrayList<Double> arr_value_of_link = new ArrayList<Double>();
		ArrayList<String> arr_link_in_queue = new ArrayList<String>();
		// khoi tao cac mang chua thong tin ve 50 document tot nhat
		ArrayList<String> doc_link = new ArrayList<String>();
		ArrayList<String> doc_content = new ArrayList<String>();
		ArrayList<Double> doc_weight = new ArrayList<Double>();

		// khoi tao
		arr_priority_link.add(url);
		Document code_html = null;
		Document doc;
		String url_crawling = "";
		String sourceLine = "";
		String content = "";
		for (int i = 0; i < max_doc; i++) {
			// array chua tong so document co chu keyword
			int[] nj = new int[arr_keywords.size()];
			for (int n = 0; n < nj.length; n++) {
				nj[n] = 0;
			}
			if (arr_priority_link.size() > 0) {
				try {

					System.out.println("before 1 " + i + "aaa"
							+ arr_priority_link.get(0));

					sourceLine = "";
					content = "";

					// The URL address of the page to open.
					// URL address = new
					// URL("http://vietnamnews.vnanet.vn/Politics-Laws/indexCopy.html");

					URL address = new URL(arr_priority_link.get(0));

					// Open the address and create a BufferedReader with the
					// source code.
					InputStreamReader pageInput = new InputStreamReader(
							address.openStream());
					BufferedReader source = new BufferedReader(pageInput);

					// Append each new HTML line into one string. Add a tab
					// character.
					while ((sourceLine = source.readLine()) != null)
						content += sourceLine + "\t";
					url_doc = arr_priority_link.get(0);

					arr_link_crawled.add(arr_priority_link.get(0));
					// System.out.println("arr link crawled");
					arr_priority_link.remove(0);

				} catch (Exception le) {
					// TODO Auto-generated catch block

					System.out.println("get error 1");
					le.printStackTrace();
					arr_link_crawled.add(arr_priority_link.get(0));
					arr_priority_link.remove(0);
					continue;

				}

			} else {
				if (arr_link_in_queue.size() > 0) {
					try {

						sourceLine = "";
						content = "";

						// The URL address of the page to open.
						// URL address = new
						// URL("http://vietnamnews.vnanet.vn/Politics-Laws/indexCopy.html");

						URL address = new URL(arr_link_in_queue.get(0));

						// Open the address and create a BufferedReader with
						// the source code.
						InputStreamReader pageInput = new InputStreamReader(
								address.openStream());
						BufferedReader source = new BufferedReader(pageInput);

						// Append each new HTML line into one string. Add a
						// tab character.
						while ((sourceLine = source.readLine()) != null)
							content += sourceLine + "\t";

						System.out.println("before 2 :" + i + " :"
								+ arr_link_in_queue.get(0));

						url_doc = arr_link_in_queue.get(0);

						arr_link_crawled.add(arr_link_in_queue.get(0));

						arr_link_in_queue.remove(0);

					} catch (Exception le) {

						le.printStackTrace();

						arr_link_crawled.add(arr_link_in_queue.get(0));

						arr_link_in_queue.remove(0);

						continue;

					}
				}
			}

			doc = Jsoup.parse(content);
			content_doc = doc.body().text();

			N++;
			
			content_doc= content_doc.toLowerCase();
            //System.out.println("content_ doc ="+ content_doc+"\n");
			VietTokenizer vietTokenizer = new VietTokenizer();
			// mỗi phần tử mảng có dạng chuỗi như sau : bóng_đá là một môn thể_thao..........
			String[] sentences = vietTokenizer.tokenize(content_doc);
			// mảng word trong document.VD : thể_thao,hát,bóng_đá....
			ArrayList<String> arr_term_in_doc= new ArrayList<String>();
			for (int n = 0; n < sentences.length; n++) {
				if (!sentences[n].equals("\n\n")) {
					//System.out.println("sentence " + n + "=" + sentences[n] + "\n");
					String[] arr_split_doc= sentences[n].split(" ");
					for(int m=0;m< arr_split_doc.length;m++) {
						arr_term_in_doc.add(arr_split_doc[m]);
					}
				}
			}
			
			length_doc = arr_term_in_doc.size();
			sum_length_doc = sum_length_doc + length_doc;
			float avg_length_doc = sum_length_doc / N;
			content_doc = content_doc.toLowerCase();
			
			weight_doc = 0;
			HashMap<String, Integer> count_frequent_term = new HashMap<String, Integer>();
			for (int j = 0; j < arr_term_in_doc.size(); j++) {
				if (count_frequent_term.get(arr_term_in_doc.get(j)) == null) {
					count_frequent_term.put(arr_term_in_doc.get(j), 1);

				} else {
					int count = count_frequent_term.get(arr_term_in_doc.get(j)) + 1;
					count_frequent_term.put(arr_term_in_doc.get(j), count);
				}
			}
            System.out.println("count frequent term ="+ count_frequent_term+"\n");
			// int [] arr_fre_key= new int[arr_keywords.size()];
			for (int j = 0; j < arr_keywords.size(); j++) {
				if (count_frequent_term.get(arr_keywords.get(j)) == null) {
					count_frequent_term.put(arr_keywords.get(j), 0);
				}
				int frequence_key = count_frequent_term
						.get(arr_keywords.get(j));

				double ts = 0;
				System.out.println("vong lap thu "+j);
				if (frequence_key > 0) {
					nj[j] = nj[j] + 1;
				}
				if (frequence_key == 0) {
					ts = 0;
				} else if ((frequence_key > 0) && (nj[j] > 0)) {
					ts = (Math.log(frequence_key) / Math.log(2) + 1)
							* Math.log(N / nj[j]) / Math.log(2);
				} else if ((frequence_key > 0) && (nj[j] == 0)) {
					ts = (Math.log(frequence_key) / Math.log(2) + 1)
							* Math.log(N) / Math.log(2);
				}
				double ms = (0.8 + 0.2 * length_doc / avg_length_doc);
				ms=ms*Math.log(i)/Math.log(2);
				if (ms != 0) {
					weight_doc_j = ts / ms;
					weight_doc = weight_doc + weight_doc_j;
				} else {
					weight_doc_j = 0;
				}
				// test
				// arr_fre_key[j]=frequence_key;
				System.out.println("fre_key =" + frequence_key + "\n");
				System.out.println("N =" + N + "\n");
				System.out.println("length doc =" + length_doc + "\n");
				System.out.println("avg length doc =" + avg_length_doc + "\n");
				System.out.println("tu so =" + ts + "\n");
				System.out.println("mau so =" + ms + "\n");
				System.out.println("weight j =" + weight_doc_j + "\n");

				//
			}
			//System.exit(0);
			// luu lai num_nst doc co weight lon nhat
			if (weight_doc > 0) {
				if (doc_weight.size() == 0) {
					doc_weight.add(weight_doc);
					doc_link.add(url_doc);
					doc_content.add(content_doc);
				} else {
					Boolean bool = false;
					for (int j = 0; j < doc_weight.size(); j++) {
						if (weight_doc >= doc_weight.get(j)) {
							doc_weight.add(j, weight_doc);
							doc_link.add(j, url_doc);
							doc_content.add(j, content_doc);
							bool = true;

							break;
						}
					}
					if (bool == false) {
						doc_weight.add(weight_doc);
						doc_link.add(url_doc);
						doc_content.add(content_doc);
					}
				}
				if (doc_weight.size() > num_nst) {
					doc_weight.remove(doc_weight.size() - 1);
					doc_link.remove(doc_weight.size() - 1);
					doc_content.remove(doc_weight.size() - 1);
				}
			}
			if ((doc_weight.size() == num_nst)) {
				System.out.println("link best doc =" + doc_link + "\n");// System.exit(0);
				InputGA_new_approach input_GA= new InputGA_new_approach();
				ArrayList<Genome>initial_population = input_GA.find_bestword_and_create_genome(doc_content, doc_link,arr_keywords);
				ArrayList<String> best_word = input_GA.getListBestWord();
				System.out.println("best word =" + best_word + "\n");System.exit(0);
				Bestword.saveDocument(best_word);
				Algorithm alg = new Algorithm(doc_link,initial_population,arr_keywords ,best_word, num_nst,
						indexVN.mutation_rate, indexVN.crossover_rate,
						indexVN.number_generation);
				alg.Go();
				String new_key = alg.newKey();
				if (!new_key.equals("")) {
					int pos_new_key = alg.getPositionNewKey();
					arr_keywords.add(new_key);
					double best_fitness = alg.getBestFitness();
					System.out.println("best word =" + best_word + "\n");
					System.out.println("doc link =" + doc_link + "\n");
					System.out.println("new key=" + new_key);
					Newkeyword.saveDocument(new_key, doc_link.get(pos_new_key),
							best_fitness);
				}
				doc_content.clear();
				doc_link.clear();
				doc_weight.clear();
			}
			System.out.println("weight " + weight_doc + "\n");
			// save into db
			DocumentRelative.saveDocument(url_doc, content_doc, weight_doc);

			// cho link vao queue
			int position = 0;
			int count_while = 0;
			while (doc.select("a").first() != null) {
				Element link = doc.select("a").first();
				// System.out.println("link  : " + link);
				String linkHref = link.attr("href"); // "http://example.com/"
				String linkText = link.text(); // "example""
				
				
				boolean is_crawl = false;
				// neu url khong co http o dau thi phai them cua link
				// parent vao
				Pattern pat = Pattern.compile("http");
				Matcher matcher = pat.matcher(linkHref);
				boolean flag = matcher.find(); // true
				// System.out.println(n+" "+flag+union_link.get(n));
				Pattern p = Pattern.compile("/");
				if (flag == false) {

					// add them http cua trang chu vao cac link con

					// String s="http://dantri.com/oto.htm";
					String[] http_split = p.split(url_doc);
					// List<String> wordList =
					// Arrays.asList(http_split);

					String http = http_split[0] + "//" + http_split[2];

					linkHref = http + linkHref;

				}

				for (int n = 0; n < arr_link_crawled.size(); n++) {
					if (arr_link_crawled.get(n).equals(linkHref)) {
						is_crawl = true;
						break;
					}
				}
				// neu link nay chua duoc crawl
				if (!is_crawl) {
					//kiểm tra xem link có chứa keyword không
					//tách từ VN trong link text
					linkText=linkText.toLowerCase();
					linkText = linkText.replace(".", " ");
					linkText = linkText.replace(",", " ");
					linkText = linkText.replace("-", " ");
					linkText = linkText.replace("|", " ");
					linkText = linkText.replace("#", " ");
					String[] arr_linktext = vietTokenizer.tokenize(linkText);
					ArrayList<String> arraylist_linktext= new ArrayList<String>();
					for (int n = 0; n < arr_linktext.length; n++) {
						if (!arr_linktext[n].equals("\n\n")) {
							//System.out.println("sentence " + n + "=" + sentences[n] + "\n");
							String[] arr_split_linktext= arr_linktext[n].split(" ");
							for(int m=0;m< arr_split_linktext.length;m++) {
								arraylist_linktext.add(arr_split_linktext[m]);
							}
						}
					}
					
					boolean is_topic = false;
					for (int n = 0; n < arr_keywords.size(); n++) {
						for (int m = 0; m < arraylist_linktext.size(); m++) {
							if (arraylist_linktext.get(m).equals(arr_keywords.get(n))) {
								System.out.println("keyword ="+ arr_keywords.get(n) + "\n");
								System.out.println("link text =" + linkText);
								is_topic = true;
								break;
							}
						}
						if(is_topic) {
							break;
						}

					}
					// linkText co chua keyword
					if (is_topic) {
						boolean in_queue = false;// check xem link nay
						// da co trong mang
						// uu tien chua
						for (int n = 0; n < arr_priority_link.size(); n++) {
							if (arr_priority_link.get(n).equals(linkHref)) {
								in_queue = true;
								break;
							}
						}
						if (!in_queue) {
							arr_priority_link.add(linkHref);
						}
					} else {
						if (arr_value_of_link.size() == 0) {
							arr_value_of_link.add(weight_doc);
							arr_link_in_queue.add(linkHref);
						} else {
							if (count_while == 0) {
								for (int n = 0; n < arr_value_of_link.size(); n++) {
									if (weight_doc >= arr_value_of_link.get(n)) {
										position = n;
										count_while = 1;
										break;
									} else {
										continue;
									}
								}
								boolean in_queue = false;// check xem
								// link nay
								// da co
								// trong
								// queue
								// chua
								for (int n = 0; n < arr_link_in_queue.size(); n++) {
									if (arr_link_in_queue.get(n).equals(
											linkHref)) {
										in_queue = true;
										break;
									}
								}
								if (!in_queue) {
									arr_value_of_link.add(position, weight_doc);
									arr_link_in_queue.add(position, linkHref);
								}
							} else {
								boolean in_queue = false;// check xem
								// link nay
								// da co
								// trong
								// queue
								// chua
								for (int n = 0; n < arr_link_in_queue.size(); n++) {
									if (arr_link_in_queue.get(n).equals(
											linkHref)) {
										in_queue = true;
										break;
									}
								}
								if (!in_queue) {
									arr_value_of_link.add(position, weight_doc);
									arr_link_in_queue.add(position, linkHref);
								}
							}
						}
					}
					link.remove();

				} else {
					link.remove();
				}
			}
           // System.out.println("stop");System.exit(0);
			
			
			

		}

	}
}
