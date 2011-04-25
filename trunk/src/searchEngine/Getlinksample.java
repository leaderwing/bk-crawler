package searchEngine;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.StringContent;

import model.DocumentRelative;
import model.Bestword;
import model.DocumentSample;
import model.Newkeyword;

//import org.jsoup.Connection;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//import vn.hus.nlp.tokenizer.VietTokenizer;

import GA.Algorithm;
import GA.Genome;
/**
 * create sample collection to test new key
 * @author HaNguyen
 *
 */
public class Getlinksample {
	

	public static void main_2(String args[]) {
		// link mà cso dạng http://hostname.com/abc.png là loại
		String[] ext_file= {"png","exe","jpg","doc","docx"};
		// array contain keyword
		ArrayList<String> arr_keywords = new ArrayList<String>();
		//arr_keywords.add("sport");
		//arr_keywords.add("sports");
		arr_keywords.add("win");
		arr_keywords.add("football");
		arr_keywords.add("championship");
		arr_keywords.add("cup");
		arr_keywords.add("goal");
		
		arr_keywords.add("kick");
		arr_keywords.add("soccer");
		
		arr_keywords.add("score");
		arr_keywords.add("scores");
		ArrayList<String> keyword_2=new ArrayList<String>();
		for(int i=0;i<arr_keywords.size();i++) {
			keyword_2.add(arr_keywords.get(i));
		}
		
		// so nst trong quan the
		int num_nst = 30;
		// seed url
		
		String url="http://www.nytimes.com/pages/sports/soccer/index.html";
		//String url="http://sports.yahoo.com/";
		// array contain the number of occurrences of term j in document i
		int[] arr_frequency_term = new int[arr_keywords.size()];
		// tong so document da crawler
		int N = 0;
		
		
		// sum length of documents
		int sum_length_doc = 0;
		
	
		// max document crawler
		int max_doc = 3000;
		
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
		

		// khoi tao
		arr_priority_link.add(url);
		// array chua tong so document co chu keyword
		int[] nj = new int[arr_keywords.size()];
		for (int n = 0; n < nj.length; n++) {
			nj[n] = 0;
		}
		
		
		String url_crawling = "";
		String sourceLine = "";
		String content = "";
		for (int i = 0; i < max_doc; i++) {
			// length of document
			int length_doc=0;
			// sum weight of the document
			double weight_doc=0.0;
			Document code_html = null;
			// content document
			String content_doc="";
			
			if (arr_priority_link.size() > 0) {
				try {
					System.out.println("before 1 " + i + "aaa"+ arr_priority_link.get(0));

					
					code_html = Jsoup.connect(arr_priority_link.get(0)).get();
					
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

						
						code_html =Jsoup.connect(arr_link_in_queue.get(0)).get();
						
						System.out.println("before 2 :" + i + " :"+ arr_link_in_queue.get(0));
						url_doc = arr_link_in_queue.get(0);
						arr_link_crawled.add(arr_link_in_queue.get(0));
						arr_link_in_queue.remove(0);
						arr_value_of_link.remove(0);
					} catch (Exception le) {
						le.printStackTrace();
						arr_link_crawled.add(arr_link_in_queue.get(0));
						arr_link_in_queue.remove(0);
						arr_value_of_link.remove(0);
						continue;
					}
				}
			}

			
			if (code_html == null) { continue; }
			Document doc = Jsoup.parse(code_html.html());
			content_doc = doc.body().text();
			N++;
			helper helper = new helper();
            
			content_doc= content_doc.toLowerCase();

			
			content_doc = helper.removeStopWord(content_doc);
			
			
			String[] arr_content = content_doc.split(" ");
			length_doc = arr_content.length;
			sum_length_doc = sum_length_doc + length_doc;
			float avg_length_doc = sum_length_doc / N;
			
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
			boolean is_true_topic=false;//site đang duyệt có nói về topic của mình hay không?
			for (int j = 0; j < arr_keywords.size(); j++) {
				// the weight of the term j in document i
				double weight_doc_j=0;
				if (count_frequent_term.get(arr_keywords.get(j)) == null) {
					count_frequent_term.put(arr_keywords.get(j), 0);
				}
				int frequence_key = count_frequent_term
						.get(arr_keywords.get(j));

						double ts = 0;
						System.out.println("j= "+ j+"\n");
						if (frequence_key > 0) {
							nj[j] = nj[j] + 1;
						}
						
//						double px =(double)frequence_key/length_doc;
//						double pc=(double)nj[j]/sum_length_doc;
//						if((pc!=0)&&(px!=0)) {
//						double weight_term = (double) px*Math.log((double)px/pc)/Math.log(2);
//						weight_doc = weight_doc + weight_term;
//						
//						}
						
						if (frequence_key == 0) {
							ts = 0;
						} else if ((frequence_key > 0) && (nj[j] > 0)) {
							ts = (Math.log(frequence_key) / Math.log(2) + 1)
									* Math.log((double)N / nj[j]) / Math.log(2);
						} else if ((frequence_key > 0) && (nj[j] == 0)) {
							ts = (Math.log(frequence_key) / Math.log(2) + 1)
									* Math.log(N) / Math.log(2);
						}
						double ms = (0.8 + 0.2 * length_doc / avg_length_doc);
		                ms=ms*Math.log(i)/Math.log(2);
						if (ms != 0) {
							weight_doc_j = ts / ms;
							weight_doc = weight_doc + weight_doc_j;

						System.out.println("weight doc j =" + weight_doc_j + "\n");
						}
		
						//
                
				
			}

	
			
			
			// save into db
			DocumentSample.saveDocument(url_doc, content_doc, weight_doc);

			// cho link vao queue
			int position = -1;//vị trí chèn link
			//position chỉ cần tìm ở vòng lặp đầu,các vòng sau thì chèn tại position đó luôn
			int count_while=0;
			
			while (doc.select("a").first() != null) {
				Element link = doc.select("a").first();
				//System.out.println("link  : " + link);
				String linkHref = link.attr("href"); // "http://example.com/"
				// loại các link có dạng http://hostname.xxx/abc.png(jpg,exe....)
				boolean is_get_link= true;
				int pos_dot = linkHref.lastIndexOf(".");
				String extension_file= linkHref.substring(pos_dot+1);
			    extension_file=extension_file.toLowerCase();
			    for(int n=0;n<ext_file.length;n++) {
			    	if(ext_file[n].equals(extension_file)) {
			    		is_get_link=false;
			    		break;
			    	}
			    }
			    //link không hợp lệ
			    if(is_get_link==false) {
			    	link.remove();
			    	continue;
			    }
			    
				//chỉ lấy link từ đầu link tới vị trí kí tự "#"
				int pos_special_char= linkHref.indexOf("#");
				if(pos_special_char>=0) {
				linkHref=linkHref.substring(0, pos_special_char);
				}
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
					
					//kiểm tra xem link text có chứa keyword không
				    boolean is_topic = false;
					linkText=linkText.toLowerCase();
					linkText = linkText.replaceAll("[^a-z 0-9]+","");
					String[] arr_link_text= linkText.split(" "); 
					for (int n = 0; n < arr_keywords.size(); n++) {
						for (int m = 0; m < arr_link_text.length; m++) {
							if (arr_link_text[m].equals(arr_keywords.get(n))) {
								
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
						boolean in_queue = false;
						// check xem link nay da co trong mang uu tien chua
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
							// check xem link nay da co trong queue chua?
							boolean in_queue = false;
							for (int n = 0; n < arr_link_in_queue.size(); n++) {
								if (arr_link_in_queue.get(n).equals(
										linkHref)) {
									in_queue = true;
									break;
								}
							}
							//link này chưa có trong queue
							if (in_queue == false) {
								// tìm vị trí thích hợp để nhồi link vào queue theo
								// trọng số giảm dần
								if(count_while==0) {
									for (int n = 0; n < arr_value_of_link.size(); n++) {
										
										if (weight_doc >= arr_value_of_link.get(n)) {
											position = n;
											
											break;
										} else {
											continue;
										}
									}
								}
								count_while++;
								if (position == -1) {
									arr_value_of_link.add(weight_doc);
									arr_link_in_queue.add(linkHref);
								} else {
									arr_value_of_link.add(position, weight_doc);
									arr_link_in_queue.add(position, linkHref);
								}
	
							}
							   
								
								
							
						}
					}
					link.remove();

				}
				//link này đã được crawl,loại luôn
				else {
					link.remove();
				}
			}
            
			

		}

	}
}
