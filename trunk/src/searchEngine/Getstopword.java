package searchEngine;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.Doc;

import model.DocumentRelative;

public class Getstopword {
    public static void main_2(String args[]) {
    	//mảng chứa nội dung của các document
    	ArrayList<String> content= new ArrayList<String>();
    	ResultSet rs= DocumentRelative.getContent();
    	try {
			while(rs.next()){
				 
				 content.add(rs.getString("content"));
				 
			}
			DocumentRelative.closeStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//chứa tất cả content của các document vào một string
		String all_content="";
		for(int i=0;i<content.size();i++) {
			all_content=all_content+" "+content.get(i);
		}
		//tính F= số lần xuất hiện của mỗi term trong all_content
		HashMap<String, Integer> count_frequent_term = new HashMap<String, Integer>();
		
		all_content = all_content.replace("|", " ");
		all_content = all_content.replace(",", " ");
		all_content = all_content.replace(".", " ");
		String arr_all_content[];
		arr_all_content = all_content.split(" ");
		//tổng số term trong all_content
		int token=arr_all_content.length;
		for (int j = 0; j < arr_all_content.length; j++) {
			if (count_frequent_term.get(arr_all_content[j]) == null) {
				count_frequent_term.put(arr_all_content[j], 1);

			} else {
				int count = count_frequent_term.get(arr_all_content[j]) + 1;
				count_frequent_term.put(arr_all_content[j], count);
			}
		}
		//System.out.println("count frequent term="+count_frequent_term);
		
		//mảng của mảng : mỗi phần tử chứa list trọng số  term của document tg ứng
		ArrayList<HashMap<String, Double>> list_frequent_all_doc= new ArrayList<HashMap<String,Double>>();
		for(int i=0;i<content.size();i++) {
			HashMap<String, Double> weight_term_in_doc= new HashMap<String, Double>();
			String content_doc=content.get(i);
			content_doc = content_doc.replace("|", " ");
			content_doc = content_doc.replace(",", " ");
			content_doc = content_doc.replace(".", " ");
			String arr_content_doc[];
			arr_content_doc = content_doc.split(" ");
			//tổng số term trong mỗi doc
			int sum_term=arr_content_doc.length;
			//tần suat term trong mỗi doc
			HashMap<String, Integer> frequent_term_in_doc = new HashMap<String, Integer>();
			//tính tần số của term
			for (int j = 0; j < arr_content_doc.length; j++) {
				if (frequent_term_in_doc.get(arr_content_doc[j]) == null) {
					frequent_term_in_doc.put(arr_content_doc[j], 1);

				} else {
					int count = frequent_term_in_doc.get(arr_content_doc[j]) + 1;
					frequent_term_in_doc.put(arr_content_doc[j], count);
				}
			}
			
			 Pattern p = Pattern.compile("^[a-zA-Z]+$"); // string only contain a-z 
			//tính trọng số cho mỗi term
			for(String key:frequent_term_in_doc.keySet()) {
				Matcher m = p.matcher(key);
				if (m.find()) {
					double px =(double)frequent_term_in_doc.get(key)/sum_term;
					double pc=(double)count_frequent_term.get(key)/token;
					double weight_term=0;
					if((pc!=0)&&(px!=0)) {
					   weight_term = (double) px*Math.log((double)px/pc)/Math.log(2);
					}
					weight_term_in_doc.put(key, weight_term);
					
				}
			}
			list_frequent_all_doc.add(weight_term_in_doc);
			
		}
		
		//tính tổng trọng số của term trong all document
		HashMap<String, Double> arr_merge_weight= new HashMap<String, Double>();
		//đếm số lần term bị lặp lại trong danh sách mảng trọng số
		HashMap<String, Integer> count_frequent_term_in_list= new HashMap<String, Integer>();
		for(int i=0;i< list_frequent_all_doc.size();i++) {
			HashMap<String, Double> weight_term_in_doc= new HashMap<String, Double>();
			weight_term_in_doc=list_frequent_all_doc.get(i);
			for(String key : weight_term_in_doc.keySet()) {
				if (arr_merge_weight.get(key) == null) {
					arr_merge_weight.put(key, weight_term_in_doc.get(key));
					count_frequent_term_in_list.put(key, 1);

				} else {
					double weight = arr_merge_weight.get(key) + weight_term_in_doc.get(key);
					arr_merge_weight.put(key, weight);
					int count=count_frequent_term_in_list.get(key)+1;
					count_frequent_term_in_list.put(key, count);
				}
			}
		}
		
		//tính avg_weight của term trong toàn bộ document
		for(String key: arr_merge_weight.keySet()) {
			double weight=(double)arr_merge_weight.get(key)/count_frequent_term_in_list.get(key);
			arr_merge_weight.put(key, weight);
		}
		
		// lấy ra N=500 term có trọng số bé nhất
		ArrayList<String> term= new ArrayList<String>();
		ArrayList<Double> weight_term= new ArrayList<Double>();
		for(String key: arr_merge_weight.keySet()) {
			if(term.size()==0) {
				term.add(key);
				weight_term.add(arr_merge_weight.get(key));
			}
			else {
				int pos=-1;
				for(int i=0;i< weight_term.size();i++) {
					if(arr_merge_weight.get(key)<weight_term.get(i)) {
						pos=i;
					}
				}
				if(pos>=0) {
					term.add(pos, key);
					weight_term.add(pos, arr_merge_weight.get(key));
				}
				else {
					
						term.add(key);
						weight_term.add(arr_merge_weight.get(key));
					
				}
				if(term.size()==500) {
					term.remove(term.size()-1);
					weight_term.remove(weight_term.size()-1);
				}
			}
		}
		System.out.println("list stop word ="+ term);
		System.exit(0);
    }
}
