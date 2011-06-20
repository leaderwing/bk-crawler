package searchEngine;

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

import model.DocumentRelative;
import model.Bestword;
import model.Linkcrawled;
import model.Linkqueue;
import model.Newkeyword;
import model.VariableState;

//import org.jsoup.Connection;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//import vn.hus.nlp.tokenizer.VietTokenizer;

import GA.Algorithm;
import GA.Genome;

public class index {
	// array chua tong so document co chu keyword
	private static ArrayList<Integer> nj=new ArrayList<Integer>();
	// array contain keyword
	private static ArrayList<String> arr_keywords = new ArrayList<String>();
	private static int num_doc_crawled;
	private static double weight_doc;
	private static String content_doc;//content of document
	private static String html;// mã html của document
	// sum length of documents
	private static int sum_length_doc;
	private static Document doc;
	private static double mutation_rate; // phan tram dot bien
	private static double crossover_rate; // phan tram lai ghep
	private static int number_generation; // phan tram lai ghep
	
	public static void main(String args[]) {
		
		sum_length_doc=0;
		int max_crawl=0;
		// link mà cso dạng http://hostname.com/abc.png là loại
		String[] ext_file= {"png","exe","jpg","doc","docx","ashx","pdf","mp3"};
		
		//arr_keywords.add("sport");
		//arr_keywords.add("sports");
		//arr_keywords.add("win");
		//arr_keywords.add("champion");
		//arr_keywords.add("championship");
		//arr_keywords.add("cup");
		//arr_keywords.add("goal");
		
		//arr_keywords.add("kick");
		//arr_keywords.add("soccer");
		
		//arr_keywords.add("score");
		//arr_keywords.add("scores");
		ArrayList<String> keyword_2=new ArrayList<String>();
		for(int i=0;i<arr_keywords.size();i++) {
			keyword_2.add(arr_keywords.get(i));
		}
		index.mutation_rate = 0.01;
		index.crossover_rate = 0.8;
		index.number_generation = 200;
		// so nst trong quan the
		int num_nst = 30;
		
		
		
		
		
		
		//ngưỡng trọng số
		double threshold_weight=0;
		
		
		
		// khoi tao cac mang chua thong tin ve 50 document tot nhat
		ArrayList<String> doc_link = new ArrayList<String>();
		ArrayList<String> doc_content = new ArrayList<String>();
		ArrayList<Double> doc_weight = new ArrayList<Double>();
		
        //lấy giá trị biến được lưu trong db
		ResultSet result= VariableState.getContent();
		try {
			
			while(result.next()){
				nj.add(result.getInt("num_doc_contain_key"));
				sum_length_doc=result.getInt("sum_length");
				num_doc_crawled=result.getInt("num_doc_crawled");
				arr_keywords.add(result.getString("keyword"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VariableState.closeConnect();
		System.out.println("nj ="+ nj+"\n");
		System.out.println("sum length ="+sum_length_doc+"\n");
		System.out.println("num doc crawled="+ num_doc_crawled+"\n");
		
		/*for (int n = 0; n < arr_keywords.size(); n++) {
			nj.add(0);
		}*/
		 /*double avg_weight = CalculateWeightByKey.calculateWeightByKey(arr_keywords);
	     System.out.println("avg weight =" + avg_weight);*/ //System.exit(0);
	     
		while(max_crawl<50000) {
			max_crawl++;
			ResultSet rs= Linkqueue.getLink();
			int id=0;
			String link_crawl="";
			try {
				while(rs.next()){
					 id=rs.getInt("id");
					 link_crawl=rs.getString("link");
					 weight_doc=rs.getDouble("weight");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Linkqueue.delLink(id);
			Linkqueue.closeConnect();
			System.out.println("link crawler ="+link_crawl+"\n");
			Linkcrawled.saveDocument(link_crawl);
			if(link_crawl=="") {
				System.out.println("link rỗng");
				break;
			}
			Document code_html = null;
			// content document
			content_doc="";
			double weight_doc_current=weight_doc;
			String sourceLine = "";
		    String content = "";
			try {
				
				URL address = new URL(link_crawl);
				// Open the address and create a BufferedReader with the source code.
				InputStreamReader pageInput = new InputStreamReader(address.openStream());
				BufferedReader source = new BufferedReader(pageInput);
				// Append each new HTML line into one string. Add a tab character.
				while ((sourceLine = source.readLine()) != null)
					content += sourceLine + "\t";
				//code_html = Jsoup.connect(link_crawl).get();
			} catch (Exception le) {
				le.printStackTrace();
				continue;
				
			}	
			//System.out.println("content ="+content+"\n");
			 content=content.toLowerCase();
				doc = Jsoup.parse(content);
				content_doc = doc.body().text();
			/*if (code_html == null) {continue; }
			doc = Jsoup.parse(code_html.html());
			content_doc = doc.body().text();*/
			//System.out.println("doc ="+ doc.select("a"));System.exit(0);
			helper helper = new helper();
			content_doc = helper.removeStopWord(content_doc);
			// save into db
			DocumentRelative.saveDocument(link_crawl, content_doc, weight_doc);
			
			// luu lai num_nst doc co weight lon nhat
			
			if ((weight_doc > 3  ) && (max_crawl>200)) {
				
				if (doc_weight.size() == 0) {
					doc_weight.add(weight_doc);
					doc_link.add(link_crawl);
					doc_content.add(content_doc);
				} else {
					Boolean bool = false;
					//chèn trọng số của doc vào doc_weight theo chiều giảm dần
					for (int j = 0; j < doc_weight.size(); j++) {
						if (weight_doc >= doc_weight.get(j)) {
							doc_weight.add(j, weight_doc);
							doc_link.add(j, link_crawl);
							doc_content.add(j, content_doc);
							bool = true;

							break;
						}
					}
					// chèn trọng số của doc vào cuối mảng
					if (bool == false) {
						doc_weight.add(weight_doc);
						doc_link.add(link_crawl);
						doc_content.add(content_doc);
					}
				}
				//các mảng chỉ lưu với kích thước <= num_nst
				if (doc_weight.size() > num_nst) {
					doc_weight.remove(doc_weight.size() - 1);
					doc_link.remove(doc_weight.size() - 1);
					doc_content.remove(doc_weight.size() - 1);
				}
			}
			
			if ((doc_weight.size() == num_nst)) {
				
				InputGA_new_approach input_GA = new InputGA_new_approach();
				ArrayList<Genome> initial_population = input_GA
						.find_bestword_and_create_genome(doc_content, doc_link,
								arr_keywords);
				ArrayList<String> best_word = input_GA.getListBestWord();
				Bestword.saveDocument(best_word);
				Algorithm alg = new Algorithm(doc_link, initial_population,
						keyword_2, best_word, num_nst, index.mutation_rate,
						index.crossover_rate, index.number_generation);
				//alg.Go(6);
				alg.Go(2);
				ArrayList<String> new_key = alg.newKey();
				ArrayList<String> linkofkey = alg.getLinkNewKey();
				ArrayList<Double> weight_of_key=alg.getWeightNewKey();
				double best_fitness= alg.getBestFitness();
				for(int j=0;j<new_key.size();j++) {
					if (!new_key.get(j).equals("")) {

						boolean get = true;
						// key mới không trùng với tập key đã có
						for (int n = 0; n < keyword_2.size(); n++) {
							String key_intial = keyword_2.get(n);
							if (key_intial.equals(new_key.get(j))) {
								get = false;
								break;
							} else {
								continue;
							}
						}
						if (get) {
							// kiểm tra keyword mới có avg_weight > initial_key
							// hay ko?
							ArrayList<String> key = new ArrayList<String>();
							key.add(new_key.get(j));
							double weight_experiment = CalculateWeightByKey.calculateWeightByTerm(key);
							double avg_fitness_experiment= CalculateWeightByKey.calculateWeightByTerm(arr_keywords);
							keyword_2.add(new_key.get(j));
							double avg=avg_fitness_experiment/arr_keywords.size();
							Newkeyword.saveDocument(new_key.get(j),
									linkofkey.get(j), weight_experiment,avg_fitness_experiment,weight_of_key.get(j),best_fitness);
							Newkeyword.closeConnect();
							if((weight_experiment>=avg)&&(weight_experiment>0)){
								arr_keywords.add(new_key.get(j));
								nj.add(0);
								VariableState.saveDocument(new_key.get(j), 0, num_doc_crawled, sum_length_doc);
								VariableState.closeConnect();
							}
							
						}
					}
				}
				doc_content.clear();
				doc_link.clear();
				doc_weight.clear();
			}
			
			
           // ArrayList<String> list_link_extract=new ArrayList<String>();
			HashMap<String, Integer> list_link_extract=new HashMap<String, Integer>();
            while (doc.select("a").first() != null) {
            	Element link = doc.select("a").first();
            	if(list_link_extract.get(link.attr("href"))==null) {
            		list_link_extract.put(link.attr("href"), 0);
            	}
            	link.remove();
            }
		//System.out.println("list link extract ="+list_link_extract);
		 int count_link=0;//đếm số link giới hạn dc down
			for(String key: list_link_extract.keySet()) {
				//Element link = doc.select("a").first();
				String linkHref = key; // "http://example.com/"
				
				//link rỗng
				if(linkHref=="") {
					//link.remove();
					continue;
				}
				
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
					String[] http_split = p.split(link_crawl);
					// List<String> wordList =
					// Arrays.asList(http_split);

					String http = http_split[0] + "//" + http_split[2];

					linkHref = http + linkHref;

				}
				
				if(linkHref.contains("video")||linkHref.contains("mailto")||linkHref.contains("adx_click")||linkHref.contains("facebook")||linkHref.contains("twitter")||linkHref.contains("blog")){
					continue;
				}
				// loại các link có dạng http://hostname.xxx/abc.png(jpg,exe....)
				boolean is_get_link= true;
				int pos_dot = linkHref.lastIndexOf(".");
				if (pos_dot > 0) {
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
						System.out.println("link ko hop le \n");
						//link.remove();
						continue;
					}
				}
				//chỉ lấy link từ đầu link tới vị trí kí tự "#"
				int pos_special_char= linkHref.indexOf("#");
				if(pos_special_char>=0) {
				linkHref=linkHref.substring(0, pos_special_char);
				}
				//String linkText = link.text(); // "example""
				//check link  trong queue
				 boolean is_queue= Linkqueue.checkLink(linkHref);
				 Linkqueue.closeConnect();
				 System.out.println("is queue="+is_queue+"\n");
				 if(is_queue) {
					// link.remove();
					 continue;
				 }
				System.out.println("extract link crawl="+linkHref);
				
				 boolean is_crawled= Linkcrawled.checkLink(linkHref);
				 Linkcrawled.closeConnect();
				 System.out.println("is crawled="+is_crawled+"\n");
  				// neu link nay chua duoc crawl
  				if(!is_crawled) {
  					boolean is_success;
  					if(weight_doc_current>3){
  						count_link++;
  						System.out.println("link bị gioi hạn thứ "+count_link+"\n");
  						
  						System.out.println("số cá thể uu tu ="+ doc_weight.size()+"\n");
  						
  						if(count_link==10){
  							break;
  						}
  					}
  		            is_success=calculateWeight(linkHref);
  		            if(is_success) {
  		            	System.out.println("success link ="+ linkHref+"\n");
  					    Linkqueue.saveDocument(linkHref,weight_doc);
  					    Linkqueue.closeConnect();
  		            }
  					
  				}
  				//link này đã được crawl,loại luôn
  				else {
  					//link.remove();
  					continue;
  					
  				}
			}
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
	public static boolean calculateWeight(String link_crawl) {
		if(link_crawl=="")return false;
		num_doc_crawled++;
		boolean is_success=true;
		// length of document
		int length_doc=0;
		// sum weight of the document
		weight_doc=0;
		
		Document code_html = null;
		// content document
		content_doc="";
		
		

		String sourceLine = "";
	    html = "";
		try {
			
			URL address = new URL(link_crawl);
			// Open the address and create a BufferedReader with the source code.
			InputStreamReader pageInput = new InputStreamReader(address.openStream());
			BufferedReader source = new BufferedReader(pageInput);
			// Append each new HTML line into one string. Add a tab character.
			while ((sourceLine = source.readLine()) != null)
				html += sourceLine + "\t";
			//code_html = Jsoup.connect(link_crawl).get();
		} catch (Exception le) {
			le.printStackTrace();
			is_success=false;
			return is_success;
			
		}	
		
		html = html.toLowerCase();
		doc = Jsoup.parse(html);
		content_doc = doc.body().text();
		/*if (code_html == null) { is_success=false;return is_success; }
		doc = Jsoup.parse(code_html.html());
		content_doc = doc.body().text();*/
		
		helper helper = new helper();
		content_doc = helper.removeStopWord(content_doc);
		String[] arr_content = content_doc.split(" ");
		length_doc = arr_content.length;
		sum_length_doc = sum_length_doc + length_doc;
		float avg_length_doc = sum_length_doc / num_doc_crawled;
		
		HashMap<String, Integer> count_frequent_term = new HashMap<String, Integer>();
		for (int j = 0; j < arr_content.length; j++) {
			if (count_frequent_term.get(arr_content[j]) == null) {
				count_frequent_term.put(arr_content[j], 1);

			} else {
				int count = count_frequent_term.get(arr_content[j]) + 1;
				count_frequent_term.put(arr_content[j], count);
			}
		}
		
		
		for (int j = 0; j < arr_keywords.size(); j++) {
			// the weight of keyword in document i
			double weight_doc_j=0;
			if (count_frequent_term.get(arr_keywords.get(j)) == null) {
				count_frequent_term.put(arr_keywords.get(j), 0);
			}
			int frequence_key = count_frequent_term.get(arr_keywords.get(j));
			double ts = 0;
			System.out.println("j= "+ j+"\n");
			if (frequence_key > 0) {
				int num=nj.get(j)+1;
				nj.set(j, num);
			}
			if (frequence_key == 0) {
				ts = 0;
			} else if ((frequence_key > 0) && (nj.get(j) > 0)) {
				ts = (Math.log(frequence_key) / Math.log(2) + 1)
						* Math.log((double)num_doc_crawled / nj.get(j)) / Math.log(2);
			} else if ((frequence_key > 0) && (nj.get(j) == 0)) {
				ts = (Math.log(frequence_key) / Math.log(2) + 1)
						* Math.log(num_doc_crawled) / Math.log(2);
			}
			double ms = (0.8 + 0.2 * length_doc / avg_length_doc);
            //ms=ms*Math.log(num_doc_crawled)/Math.log(2);
			if (ms != 0) {
				weight_doc_j = ts / ms;
				weight_doc = weight_doc + weight_doc_j;
			} 
			// test
			System.out.println("keyword ="+ arr_keywords.get(j)+"\n");
			System.out.println("fre_key =" + frequence_key + "\n");
			System.out.println("N =" + num_doc_crawled + "\n");
			System.out.println("nj ="+nj.get(j)+"\n");
			System.out.println("length doc =" + length_doc + "\n");
			System.out.println("sum length ="+ sum_length_doc+"\n");
			System.out.println("avg length doc =" + avg_length_doc + "\n");
			System.out.println("tu so =" + ts + "\n");
			System.out.println("mau so =" + ms + "\n");
			System.out.println("weight j =" + weight_doc_j + "\n");
 
		}
		VariableState.delete();
		for(int i=0;i<arr_keywords.size();i++){
			VariableState.saveDocument(arr_keywords.get(i), nj.get(i), num_doc_crawled, sum_length_doc);
		}
		VariableState.closeConnect();
		
		return is_success;
	}
	
}
