package searchEngine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.DocumentSample;

public class CalculateWeightByKey {
    
	public static double calculateWeightByKey(ArrayList<String> key) {
	
		ResultSet rs=DocumentSample.getAllContent();
		ArrayList<HashMap<String, Integer>> list_frequent_key_of_doc = new ArrayList<HashMap<String,Integer>>();
		HashMap<String, Integer> num_doc_contain_key = new HashMap<String, Integer>();
		for(int i=0; i< key.size();i++) {
			num_doc_contain_key.put(key.get(i), 0);
		}
		ArrayList<Integer> arr_length_doc= new ArrayList<Integer>();
		ArrayList<Integer> arr_id= new ArrayList<Integer>();
		int sum_length= 0;
		try {
			while(rs.next()) {
				HashMap<String, Integer> count_frequent_term= new HashMap<String, Integer>();
				String content =rs.getString("content");
				String[] arr_content= content.split(" ");
				arr_id.add(rs.getInt("id"));
				arr_length_doc.add(arr_content.length);
				sum_length=sum_length + arr_content.length;
				for(int i=0; i<arr_content.length;i++) {
					if (count_frequent_term.get(arr_content[i]) == null) {
						count_frequent_term.put(arr_content[i], 1);

					} else {
						int count = count_frequent_term.get(arr_content[i]) + 1;
						count_frequent_term.put(arr_content[i], count);
					}
				}
				for(int i=0;i< key.size();i++) {
					if(count_frequent_term.get(key.get(i))!=null) {
						int count=num_doc_contain_key.get(key.get(i)) +1;
						num_doc_contain_key.put(key.get(i), count);
					}
				}
				list_frequent_key_of_doc.add(count_frequent_term);
				
			}
			
			DocumentSample.closeStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//tinh trong so cac document theo keyword
		int N= list_frequent_key_of_doc.size();
		double avg_length_doc= (double)sum_length/N;
		double sum_weight=0;
		for(int i=0;i<list_frequent_key_of_doc.size();i++) {
			HashMap<String, Integer> count_frequent_term= list_frequent_key_of_doc.get(i);
			double weight= 0;
			for(int j=0;j<key.size();j++) {
				// the weight of keyword in document i
				double weight_doc_j=0;
				if (count_frequent_term.get(key.get(j)) == null) {
					count_frequent_term.put(key.get(j), 0);
				}
				int frequence_key = count_frequent_term.get(key.get(j));
				double ts = 0;
				
				
				if (frequence_key == 0) {
					ts = 0;
				} else if (frequence_key > 0) {
					ts = (Math.log(frequence_key) / Math.log(2) + 1)
							* Math.log((double)N / num_doc_contain_key.get(key.get(j))) / Math.log(2);
				}
				double ms = (0.8 + 0.2 * arr_length_doc.get(i) / avg_length_doc);
	           // ms=ms*Math.log(num_doc_crawled)/Math.log(2);
				if (ms != 0) {
					weight_doc_j = ts / ms;
					weight = weight + weight_doc_j;
				}
				
			}
			
			sum_weight=sum_weight+ weight;
			DocumentSample.updateWeightbyId(arr_id.get(i), weight);
			DocumentSample.closeStatement();
			
		}
		double avg_weight= (double) sum_weight/N;
		
		return avg_weight;
	}
	public static double calculateWeightByTerm(ArrayList<String> key) {
		
		ResultSet rs=DocumentSample.getAllContent();
		ArrayList<HashMap<String, Integer>> list_frequent_key_of_doc = new ArrayList<HashMap<String,Integer>>();
		HashMap<String, Integer> num_doc_contain_key = new HashMap<String, Integer>();
		for(int i=0; i< key.size();i++) {
			num_doc_contain_key.put(key.get(i), 0);
		}
		ArrayList<Integer> arr_length_doc= new ArrayList<Integer>();
		ArrayList<Integer> arr_id= new ArrayList<Integer>();
		int sum_length= 0;
		try {
			while(rs.next()) {
				HashMap<String, Integer> count_frequent_term= new HashMap<String, Integer>();
				String content =rs.getString("content");
				String[] arr_content= content.split(" ");
				arr_id.add(rs.getInt("id"));
				arr_length_doc.add(arr_content.length);
				sum_length=sum_length + arr_content.length;
				for(int i=0; i<arr_content.length;i++) {
					if (count_frequent_term.get(arr_content[i]) == null) {
						count_frequent_term.put(arr_content[i], 1);

					} else {
						int count = count_frequent_term.get(arr_content[i]) + 1;
						count_frequent_term.put(arr_content[i], count);
					}
				}
				for(int i=0;i< key.size();i++) {
					if(count_frequent_term.get(key.get(i))!=null) {
						int count=num_doc_contain_key.get(key.get(i)) +1;
						num_doc_contain_key.put(key.get(i), count);
					}
				}
				list_frequent_key_of_doc.add(count_frequent_term);
				
			}
			
			DocumentSample.closeStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//tinh trong so cac document theo keyword
		int N= list_frequent_key_of_doc.size();
		double avg_length_doc= (double)sum_length/N;
		double sum_weight=0;
		for(int i=0;i<list_frequent_key_of_doc.size();i++) {
			HashMap<String, Integer> count_frequent_term= list_frequent_key_of_doc.get(i);
			double weight= 0;
			for(int j=0;j<key.size();j++) {
				// the weight of keyword in document i
				double weight_doc_j=0;
				if (count_frequent_term.get(key.get(j)) == null) {
					count_frequent_term.put(key.get(j), 0);
				}
				int frequence_key = count_frequent_term.get(key.get(j));
				double ts = 0;
				
				
				if (frequence_key == 0) {
					ts = 0;
				} else if (frequence_key > 0) {
					ts = (Math.log(frequence_key) / Math.log(2) + 1)
							* Math.log((double)N / num_doc_contain_key.get(key.get(j))) / Math.log(2);
				}
				double ms = (0.8 + 0.2 * arr_length_doc.get(i) / avg_length_doc);
	           // ms=ms*Math.log(num_doc_crawled)/Math.log(2);
				if (ms != 0) {
					weight_doc_j = ts / ms;
					weight = weight + weight_doc_j;
				}
				
			}
			
			sum_weight=sum_weight+ weight;
			
			
		}
		double avg_weight= (double) sum_weight/N;
		return avg_weight;
	}
	
}

