/*package searchEngine;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.StringContent;

import model.DocumentRelative;
import model.Bestword;
import model.DocumentSample;
import model.Linkcrawled;
import model.Linkqueue;
import model.Newkeyword;

//import org.jsoup.Connection;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

//import test.GC1;

//import vn.hus.nlp.tokenizer.VietTokenizer;

import GA.Algorithm;
import GA.Genome;

public class Getlinksample {
	private static double mutation_rate; // phan tram dot bien
	private static double crossover_rate; // phan tram lai ghep
	private static int number_generation; // phan tram lai ghep

	public static void main(String args[]) {
		// link mà cso dạng http://hostname.com/abc.png là loại
		String[] ext_file = { "png", "exe", "jpg", "doc", "docx" };
		// array contain keyword
		ArrayList<String> arr_keywords = new ArrayList<String>();
		// arr_keywords.add("sport");
		// arr_keywords.add("sports");
		arr_keywords.add("win");
		arr_keywords.add("football");
		// arr_keywords.add("championship");
		arr_keywords.add("cup");
		// arr_keywords.add("goal");

		// arr_keywords.add("kick");
		arr_keywords.add("soccer");

		// arr_keywords.add("score");
		// arr_keywords.add("scores");
		// tinh trong so trung binh cua keyword trong linksmaple
		//double avg_weight = CalculateWeightByKey.calculateWeightByKey(arr_keywords);
		//System.out.println("avg weight =" + avg_weight);// System.exit(0);
		ArrayList<String> keyword_2 = new ArrayList<String>();
		for (int i = 0; i < arr_keywords.size(); i++) {
			keyword_2.add(arr_keywords.get(i));
		}
		Getlinksample.mutation_rate = 0.01;
		Getlinksample.crossover_rate = 0.8;
		Getlinksample.number_generation = 200;
		// so nst trong quan the
		int num_nst = 30;
		// seed url

		String url = "http://news.bbc.co.uk/sport2/hi/football/default.stm";
		// String url="http://news.bbc.co.uk/sport";
		// array contain the number of occurrences of term j in document i
		int[] arr_frequency_term = new int[arr_keywords.size()];
		// tong so document da crawler
		int N = 0;

		// sum length of documents
		int sum_length_doc = 0;

		// max document crawler
		int max_doc = 30000;

		// url of document
		String url_doc = "";
		// array chua cac link da crawler
		ArrayList<String> arr_link_crawled = new ArrayList<String>();
		// array chua cac link duoc uu tien cao nhat
		ArrayList<String> arr_priority_link = new ArrayList<String>();
		// mảng trọng số của link
		ArrayList<Double> arr_value_of_link = new ArrayList<Double>();
		// array link duoc xep theo weight of document
		ArrayList<String> arr_link_in_queue = new ArrayList<String>();
		// khoi tao cac mang chua thong tin ve 50 document tot nhat
		ArrayList<String> doc_link = new ArrayList<String>();
		ArrayList<String> doc_content = new ArrayList<String>();
		ArrayList<Double> doc_weight = new ArrayList<Double>();

		// khoi tao
		arr_priority_link.add(url);
		arr_priority_link
				.add("http://www.nytimes.com/pages/sports/soccer/index.html");
		arr_priority_link.add("http://www.foxsports.com.au/football");

		// array chua tong so document co chu keyword
		ArrayList<Integer> nj = new ArrayList<Integer>();
		for (int n = 0; n < arr_keywords.size(); n++) {
			nj.add(n, 0);
		}
		String url_crawling = "";

		// content document

		// mảng chứa các term trong doc

		for (int i = 0; i < max_doc; i++) {
			System.out.println("vòng lặp thứ " + i + "\n");

			// length of document
			int length_doc = 0;
			// sum weight of the document
			double weight_doc = 0;
			Document code_html = null;
			Document doc = null;
			String content = "";
			// content document
			String content_doc = "";
			double avg_length_doc = 0;
			ResultSet rs = Linkqueue.getLink();
			int id = 0;
			String link_crawl = "";
			try {
				while (rs.next()) {
					id = rs.getInt("id");
					link_crawl = rs.getString("link");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("link crawl ="+link_crawl+"\n");
			Linkqueue.delLink(id);
			Linkqueue.closeConnect();
			try {
				// System.out.println("before 1 " + i + "aaa"+
				// arr_priority_link.get(0));
				String sourceLine = "";
				content = "";

				URL address = new URL(link_crawl);
				// Open the address and create a BufferedReader with the source
				// code.
				InputStreamReader pageInput = new InputStreamReader(
						address.openStream());

				BufferedReader source = new BufferedReader(pageInput);
				// Append each new HTML line into one string. Add a tab
				// character.
				while ((sourceLine = source.readLine()) != null)
					content += sourceLine + "\t";
				pageInput.close();
				source.close();
				//code_html = Jsoup.connect(arr_priority_link.get(0)).timeout(5000).get();

				url_doc = link_crawl;
				Linkcrawled.saveDocument(link_crawl);
				Linkcrawled.closeConnect();

			} catch (Exception le) {
				// TODO Auto-generated catch block
				System.out.println("get error 1");
				le.printStackTrace();
				Linkcrawled.saveDocument(link_crawl);
				Linkcrawled.closeConnect();
				continue;
			}

			content = content.toLowerCase();
			doc = Jsoup.parse(content);
			content_doc = doc.body().text();
            System.out.println("content ="+ content+"\n");
            if (code_html == null) { continue; }
            doc = Jsoup.parse(code_html.html()); 
            content_doc = doc.body().text();
			N++;

			content_doc = content_doc.toLowerCase();
			
			helper helper = new helper();
			content_doc = helper.removeStopWord(content_doc);
			// System.out.println(content_doc);
			// /System.exit(0);
			String[] arr_content = content_doc.split(" ");
			length_doc = arr_content.length;
			sum_length_doc = sum_length_doc + length_doc;
			avg_length_doc = sum_length_doc / N;

			// đếm số lần xuấ hiện của từng term trong document
			HashMap<String, Integer> count_frequent_term = new HashMap<String, Integer>();
			for (int j = 0; j < arr_content.length; j++) {
				if (count_frequent_term.get(arr_content[j]) == null) {
					count_frequent_term.put(arr_content[j], 1);

				} else {
					int count = count_frequent_term.get(arr_content[j]) + 1;
					count_frequent_term.put(arr_content[j], count);
				}
			}

			// int [] arr_fre_key= new int[arr_keywords.size()];
			boolean is_true_topic = false;// site đang duyệt có nói về topic của
											// mình hay không?
			for (int j = 0; j < arr_keywords.size(); j++) {
				// the weight of the term j in document i
				double weight_doc_j = 0;
				if (count_frequent_term.get(arr_keywords.get(j)) == null) {
					count_frequent_term.put(arr_keywords.get(j), 0);
				}
				int frequence_key = count_frequent_term
						.get(arr_keywords.get(j));

				double ts = 0;
				System.out.println("j= " + j + "\n");
				if (frequence_key > 0) {
					int count = nj.get(j) + 1;
					nj.set(j, count);
				}
				if (frequence_key == 0) {
					ts = 0;
				} else if ((frequence_key > 0) && (nj.get(j) > 0)) {
					ts = (Math.log(frequence_key) / Math.log(2) + 1)
							* Math.log((double) N / nj.get(j)) / Math.log(2);
				} else if ((frequence_key > 0) && (nj.get(j) == 0)) {
					ts = (Math.log(frequence_key) / Math.log(2) + 1)
							* Math.log(N) / Math.log(2);
				}
				double ms = (0.8 + 0.2 * length_doc / avg_length_doc);
				 ms=ms*Math.log(i+1)/Math.log(2);
				if (ms != 0) {
					weight_doc_j = ts / ms;
					weight_doc = weight_doc + weight_doc_j;
				}
				

			}

			

			// save into db
	
			DocumentSample.saveDocument(url_doc, content_doc, weight_doc);
			DocumentSample.closeConnect();
			// cho link vao queue
			int position = -1;// vị trí chèn link
			// position chỉ cần tìm ở vòng lặp đầu,các vòng sau thì chèn tại
			// position đó luôn
			int count_while = 0;
			HashMap<String, Integer> list_link_extract = new HashMap<String, Integer>();
			HashMap<String, String> list_link_text = new HashMap<String, String>();
			while (doc.select("a").first() != null) {
				Element link = doc.select("a").first();
				if (list_link_extract.get(link.attr("href")) == null) {
					list_link_extract.put(link.attr("href"), 0);
					list_link_text.put(link.attr("href"), link.text());
				}
				link.remove();
			}
			for (String key : list_link_extract.keySet()) {
				// Element link = doc.select("a").first();

				// String linkHref = link.attr("href"); // "http://example.com/"
				String linkHref = key;
				// loại các link có dạng
				// http://hostname.xxx/abc.png(jpg,exe....)
				boolean is_get_link = true;
				int pos_dot = linkHref.lastIndexOf(".");
				String extension_file = linkHref.substring(pos_dot + 1);
				extension_file = extension_file.toLowerCase();
				for (int n = 0; n < ext_file.length; n++) {
					if (ext_file[n].equals(extension_file)) {
						is_get_link = false;
						break;
					}
				}
				// link không hợp lệ
				if (is_get_link == false) {
					// link.remove();
					continue;
				}

				// chỉ lấy link từ đầu link tới vị trí kí tự "#"
				int pos_special_char = linkHref.indexOf("#");
				if (pos_special_char >= 0) {
					linkHref = linkHref.substring(0, pos_special_char);
				}
				// String linkText = link.text(); // "example""
				String linkText = list_link_text.get(key);

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

				boolean is_queue = Linkqueue.checkLink(linkHref);
				Linkqueue.closeConnect();
				boolean is_crawled = Linkcrawled.checkLink(linkHref);
				Linkcrawled.closeConnect();
				// check xem link này đã crawl hay nằm tong queue hay chưa?
				if (is_queue == true || is_crawled == true) {
					continue;
				}
				// neu link nay chua duoc crawl
				// kiểm tra xem link text có chứa keyword không
				boolean is_topic = false;
				linkText = linkText.toLowerCase();
				linkText = linkText.replaceAll("[^a-z 0-9]+", "");
				String[] arr_link_text = linkText.split(" ");
				for (int n = 0; n < arr_keywords.size(); n++) {
					for (int m = 0; m < arr_link_text.length; m++) {
						if (arr_link_text[m].equals(arr_keywords.get(n))) {
							
							is_topic = true;
							break;
						}
					}
					if (is_topic) {
						break;
					}

				}
				// linkText co chua keyword
				if (is_topic) {

					Linkqueue.saveDocument(linkHref, weight_doc + 100);
					Linkqueue.closeConnect();
				} else {
					Linkqueue.saveDocument(linkHref, weight_doc);
					Linkqueue.closeConnect();
				}
				// link.remove();

			}
			// content=null;

			content_doc = null;
			arr_content = null;
			url_doc = null;
			Runtime rt = Runtime.getRuntime();

			System.out.println("Available Free Memory: " + rt.freeMemory());

			System.out.println("Free Memory before call to gc():  "
					+ rt.freeMemory());
			System.runFinalization();
			System.gc();
			System.out.println(" Free Memory after call to gc():  "
					+ rt.freeMemory());

		}

	}
}
*/