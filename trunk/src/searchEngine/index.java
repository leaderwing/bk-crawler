package searchEngine;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class index {

	public static void main(String args[]) {
		// array contain keyword
		ArrayList<String> arr_keywords = new ArrayList<String>();
		arr_keywords.add("Sports");

		arr_keywords.add("sports");
		arr_keywords.add("win");
		arr_keywords.add("score");
        // so nst trong quan the
		int num_nst=10;
		// seed url
		String url = "http://edition.cnn.com/";
		// array contain the number of occurrences of term j in document i
		int[] arr_frequency_term = new int[arr_keywords.size()];
		// tong so document da crawler
		int N = 0;
		// array chua tong so document co chu keyword
		int[] nj = new int[arr_keywords.size()];
		for (int n = 0; n < nj.length; n++) {
			nj[n] = 0;
		}
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
		// tao file xml
		try {
			OutputStream fout = new FileOutputStream("crawl.xml");
			OutputStream bout = new BufferedOutputStream(fout);
			OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");

			out.write("<?xml version=\"1.0\" ");
			out.write("encoding=\"ISO-8859-1\"?>\r\n");
			for (int i = 0; i < max_doc; i++) {
				if (arr_priority_link.size() > 0) {
					try {

						//System.out.println("before 1 " + i + "aaa"+ arr_priority_link.get(0));
						// code_html =
						// Jsoup.connect(arr_priority_link.get(0)).get();

						// System.out.println("code html =" + code_html);
						sourceLine = "";
						content = "";

						// The URL address of the page to open.
						// URL address = new
						// URL("http://vietnamnews.vnanet.vn/Politics-Laws/Index.html");
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
						//System.out.println("url_doc");

						arr_link_crawled.add(arr_priority_link.get(0));
						//System.out.println("arr link crawled");
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

							// code_html =
							// Jsoup.connect(arr_link_in_queue.get(0)).get();
							// System.out.println("code html =" + code_html);
							sourceLine = "";
							content = "";

							// The URL address of the page to open.
							// URL address = new
							// URL("http://vietnamnews.vnanet.vn/Politics-Laws/Index.html");
							URL address = new URL(arr_link_in_queue.get(0));

							// Open the address and create a BufferedReader with
							// the source code.
							InputStreamReader pageInput = new InputStreamReader(
									address.openStream());
							BufferedReader source = new BufferedReader(
									pageInput);

							// Append each new HTML line into one string. Add a
							// tab character.
							while ((sourceLine = source.readLine()) != null)
								content += sourceLine + "\t";

							//System.out.println("before 2 :" + i + " :"+ arr_link_in_queue.get(0));

							url_doc = arr_link_in_queue.get(0);

							arr_link_crawled.add(arr_link_in_queue.get(0));

							arr_link_in_queue.remove(0);

						} catch (Exception le) {
							// TODO Auto-generated catch block

							le.printStackTrace();
							//System.out.println("get error 2");
							arr_link_crawled.add(arr_link_in_queue.get(0));
							//System.out.println("link vua crawl :"	+ arr_link_in_queue.get(0));
							arr_link_in_queue.remove(0);
							//System.out.println("mang link vua bo phan tu 0 :"+ arr_link_in_queue);// System.exit(0);
							continue;

						}
					}
				}
				/*
				 * if (code_html == null) { continue; } else {
				 */
				// doc = Jsoup.parse(code_html.html());
				doc = Jsoup.parse(content);
				content_doc = doc.body().text();
				N++;
				helper helper = new helper();
				content_doc = helper.removeStopWord(content_doc);
				length_doc = content_doc.length();
				sum_length_doc = sum_length_doc + length_doc;
				float avg_length_doc = sum_length_doc / N;
				content_doc = content_doc.toLowerCase();
				String[] arr_content = content_doc.split(" ");
				weight_doc = 0;
				// int [] arr_fre_key= new int[arr_keywords.size()];
				for (int j = 0; j < arr_keywords.size(); j++) {
					int frequence_key = 0;
					for (int n = 0; n < arr_content.length; n++) {

						if (arr_content[n].equals(arr_keywords.get(j))) {
							frequence_key++;
						}
					}
					double ts = 0;
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
					weight_doc_j = ts / ms;
					weight_doc = weight_doc + weight_doc_j;
					// test
					// arr_fre_key[j]=frequence_key;
					/*System.out.println("fre_key =" + frequence_key + "\n");
					System.out.println("N =" + N + "\n");
					System.out.println("length doc =" + length_doc + "\n");
					System.out.println("avg length doc =" + avg_length_doc
							+ "\n");
					System.out.println("tu so =" + ts + "\n");
					System.out.println("mau so =" + ms + "\n");
					System.out.println("weight j =" + weight_doc_j + "\n");
*/
					//
				}
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
				if((doc_weight.size()==num_nst)&&(i%num_nst==0))
				{
					GA ga= new GA();
					String new_key_word=ga.extend_keyword(doc_content, doc_weight);
					System.out.println("new keyword ="+ new_key_word);System.exit(0);
				}
				System.out.println("weight " + weight_doc + "\n");

				// luu vao db hoac ghi ra file xml
				out.write("  <link index=\"" + i + "\">");
				// url tag
				out.write("<url>");
				out.write(url_doc);
				out.write("</url>\r\n");
				// content tag
				out.write("<content>");
				out.write(content_doc);
				out.write("</content>\r\n");
				// weight

				out.write("<weight>");
				out.write(Double.toString(weight_doc));
				out.write("</weight>\r\n");
				out.write("</link>\r\n");
				// cho link vao queue
				int position = 0;
				int count_while = 0;
				while (doc.select("a").first() != null) {
					Element link = doc.select("a").first();
					//System.out.println("link  : " + link);
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
						boolean is_topic = false;
						for (int n = 0; n < arr_keywords.size(); n++) {
							if (linkText.contains(arr_keywords.get(n))) {
								is_topic = true;
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
									for (int n = 0; n < arr_value_of_link
											.size(); n++) {
										if (weight_doc >= arr_value_of_link
												.get(n)) {
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
									for (int n = 0; n < arr_link_in_queue
											.size(); n++) {
										if (arr_link_in_queue.get(n).equals(
												linkHref)) {
											in_queue = true;
											break;
										}
									}
									if (!in_queue) {
										arr_value_of_link.add(position,
												weight_doc);
										arr_link_in_queue.add(position,
												linkHref);
									}
								} else {
									boolean in_queue = false;// check xem
									// link nay
									// da co
									// trong
									// queue
									// chua
									for (int n = 0; n < arr_link_in_queue
											.size(); n++) {
										if (arr_link_in_queue.get(n).equals(
												linkHref)) {
											in_queue = true;
											break;
										}
									}
									if (!in_queue) {
										arr_value_of_link.add(position,
												weight_doc);
										arr_link_in_queue.add(position,
												linkHref);
									}
								}
							}
						}
						link.remove();

					} else {
						link.remove();
					}
				}

				// }
				/*System.out.println("arr_priority_link =" + arr_priority_link
						+ "\n");
				System.out.println("arr_link_in_queue =" + arr_link_in_queue
						+ "\n");*/

			}
			out.flush(); // Don't forget to flush!
			out.close();
		} catch (UnsupportedEncodingException e) {
			System.out
					.println("This VM does not support the Latin-1 character set.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		/*
		 * String html =
		 * "<p>An <a href='http://example.com/'><b>example</b></a> link.</p><a href='http://dantri.com/'><b>Dantri</b></a>"
		 * ; doc = Jsoup.parse(html);
		 * 
		 * while(doc.select("a").first()!=null) { Element link =
		 * doc.select("a").first(); System.out.println("link  : " + link);
		 * link.remove();
		 * 
		 * }
		 */

	}
}
