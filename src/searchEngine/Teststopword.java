package searchEngine;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



//import org.jsoup.Connection;

import model.DocumentRelative;
import model.Linkcrawled;
import model.Linkqueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Teststopword{
	

	public static void main_2(String args[]) {
		// link mà cso dạng http://hostname.com/abc.png là loại
		String[] ext_file= {"png","exe","jpg","doc","docx"};
		// seed url
		//String url="http://www.nytimes.com";
		
		
		HashMap<String, Integer> count_frequent_term = new HashMap<String, Integer>();
		boolean is_crawl=true;
		String link_crawl="";
		while(is_crawl){
			ResultSet rs= Linkqueue.getLink();
			try {
				while(rs.next()) {
					try {
						link_crawl=rs.getString("link");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Linkqueue.closeConnect();
			System.out.println("link crawler ="+link_crawl+"\n");
			Linkcrawled.saveDocument(link_crawl);
			if(link_crawl=="") {
				break;
			}else {
				Document code_html = null;
				// content document
				String content_doc = "";

				try {
					code_html = Jsoup.connect(link_crawl).get();
				} catch (Exception le) {
					le.printStackTrace();
					continue;
				}
				if (code_html == null) {
					continue;
				}
				Document doc = Jsoup.parse(code_html.html());
				content_doc = doc.body().text();
				helper helper = new helper();
				content_doc = helper.removeStopWord(content_doc);
				DocumentRelative.saveDocument(link_crawl, content_doc, 0);
				while (doc.select("a").first() != null) {
					Element link = doc.select("a").first();
					
					String linkHref = link.attr("href"); // "http://example.com/"
					System.out.println("link href ="+linkHref+"\n");
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
                    boolean is_crawled= Linkcrawled.checkLink(linkHref);
					// neu link nay chua duoc crawl
					if(!is_crawled) {
						Linkqueue.saveDocument(linkHref,0);
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
}
