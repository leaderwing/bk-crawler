package searchEngine;


import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



//import org.jsoup.Connection;

import model.DocumentRelative;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Teststopword{
	private static double mutation_rate; // phan tram dot bien
	private static double crossover_rate; // phan tram lai ghep
	private static int number_generation; // phan tram lai ghep

/*	public static void main(String args[]) {
		// link mà cso dạng http://hostname.com/abc.png là loại
		String[] ext_file= {"png","exe","jpg","doc","docx"};
		// array contain keyword
		ArrayList<String> arr_keywords = new ArrayList<String>();
		arr_keywords.add("sport");
		arr_keywords.add("sports");
		arr_keywords.add("win");
		arr_keywords.add("champion");
		//arr_keywords.add("championship");
		arr_keywords.add("cup");
		//arr_keywords.add("goal");
		
		//arr_keywords.add("kick");
		//arr_keywords.add("soccer");
		
		//arr_keywords.add("score");
		//arr_keywords.add("scores");
		ArrayList<String> keyword_2=new ArrayList<String>();
		for(int i=0;i<arr_keywords.size();i++) {
			keyword_2.add(arr_keywords.get(i));
		}
		Teststopword.mutation_rate = 0.01;
		Teststopword.crossover_rate = 0.8;
		Teststopword.number_generation = 200;
		// so nst trong quan the
		int num_nst = 50;
		// seed url
		
		String url="http://www.nytimes.com";
		//String url="http://sports.yahoo.com/";
		// array contain the number of occurrences of term j in document i
		int[] arr_frequency_term = new int[arr_keywords.size()];
		// tong so document da crawler
		int N = 0;
		
		// length of document
		int length_doc;
		// sum length of documents
		int sum_length_doc = 0;
		
		// sum weight of the document
		double weight_doc;
		// max document crawler
		int max_doc = 3000;
		
		// url of document
		String url_doc = "";
		// array chua cac link da crawler
		ArrayList<String> arr_link_crawled = new ArrayList<String>();
		// array chua cac link duoc uu tien cao nhat
		ArrayList<String> arr_priority_link = new ArrayList<String>();
		
		// array link duoc xep theo weight of document
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
		
		HashMap<String, Integer> count_frequent_term = new HashMap<String, Integer>();
		for (int i = 0; i < max_doc; i++) {
			// content document
			String content_doc="";
			// array chua tong so document co chu keyword
			int[] nj = new int[arr_keywords.size()];
			for (int n = 0; n < nj.length; n++) {
				nj[n] = 0;
			}
			if (arr_priority_link.size() > 0) {
				try {
					System.out.println("before 1 " + i + "aaa"+ arr_priority_link.get(0));
					sourceLine = "";
					content = "";
					URL address = new URL(arr_priority_link.get(0));
					// Open the address and create a BufferedReader with the source code.
					InputStreamReader pageInput = new InputStreamReader(address.openStream());
					BufferedReader source = new BufferedReader(pageInput);
					// Append each new HTML line into one string. Add a tab character.
					while ((sourceLine = source.readLine()) != null)
						content += sourceLine + "\t";
					
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
						
					} catch (Exception le) {
						le.printStackTrace();
						arr_link_crawled.add(arr_link_in_queue.get(0));
						arr_link_in_queue.remove(0);
						
						continue;
					}
				}
			}
            
			if (code_html == null) { continue; }
			doc = Jsoup.parse(code_html.html());
			content_doc = doc.body().text();
			N++;
			helper helper = new helper();
         
			
			content_doc = helper.removeStopWord(content_doc);
			
			
			String[] arr_content = content_doc.split(" ");
			for(int j=0;j<arr_content.length;j++) {
				
					if (count_frequent_term.get(arr_content[j]) == null) {
						count_frequent_term.put(arr_content[j], 1);

					} else {
						int count = count_frequent_term.get(arr_content[j]) + 1;
						count_frequent_term.put(arr_content[j], count);
					}
				
				
			}
			if (i == 3000) {
				
				//System.out.println("size of content ="+ arr_content.length+"\n");
				for (int j = 0; j < list_content.size(); j++) {
					//System.out.println("thứ j="+j+"\n");
					if (count_frequent_term.get(list_content.get(j)) == null) {
						count_frequent_term.put(list_content.get(j), 1);

					} else {
						int count = count_frequent_term.get(list_content.get(j)) + 1;
						count_frequent_term.put(list_content.get(j), count);
					}
				}
				System.out.println("tổng số term ="+count_frequent_term.size()+"\n");
				System.out.println("count frequent term ="+ count_frequent_term);
				System.exit(0);
			}
			
			
			
			
			
			
			// save into db
			int weight=0;
			DocumentRelative.saveDocument(url_doc, content_doc, weight);
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
						
							arr_link_in_queue.add(linkHref);
						
					}
					link.remove();

				}
				//link này đã được crawl,loại luôn
				else {
					link.remove();
				}
			}
            
			

		}

	}*/
}
