package searchEngine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GA.Genome;

import model.DocumentRelative;
import model.DocumentSample;

public class CalculateWeightByKey {
    
	public static double calculateWeightByKey(ArrayList<String> key) {
	
		ResultSet rs=DocumentSample.getAllContent();
		//ResultSet rs=DocumentRelative.getContent(500);
		ArrayList<HashMap<String, Integer>> list_frequent_key_of_doc = new ArrayList<HashMap<String,Integer>>();
		HashMap<String, Integer> num_doc_contain_key = new HashMap<String, Integer>();
		for(int i=0; i< key.size();i++) {
			num_doc_contain_key.put(key.get(i), 0);
		}
		ArrayList<Integer> arr_length_doc= new ArrayList<Integer>();
		ArrayList<Integer> arr_id= new ArrayList<Integer>();
		int sum_length= 0;
		try {
			String[] arr_content;
			String content="";
			HashMap<String, Integer> count_frequent_term= new HashMap<String, Integer>();
			int var_num=0;
			while(rs.next()) {
				 count_frequent_term.clear();
				 content =rs.getString("content");
				 arr_content= content.split(" ");
				arr_id.add(rs.getInt("id"));
				arr_length_doc.add(arr_content.length);
				sum_length=sum_length + arr_content.length;
				for(int i=0; i<arr_content.length;i++) {
					if (count_frequent_term.get(arr_content[i]) == null) {
						count_frequent_term.put(arr_content[i], 1);

					} else {
						var_num = count_frequent_term.get(arr_content[i]) + 1;
						count_frequent_term.put(arr_content[i], var_num);
					}
				}
				for(int i=0;i< key.size();i++) {
					if(count_frequent_term.get(key.get(i))!=null) {
						var_num=num_doc_contain_key.get(key.get(i)) +1;
						num_doc_contain_key.put(key.get(i), var_num);
					}
				}
				list_frequent_key_of_doc.add(count_frequent_term);
				
			}
			
			DocumentSample.closeConnect();
			//DocumentRelative.closeConnect();
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
	          //  ms=ms*Math.log(N)/Math.log(2);
				if (ms != 0) {
					weight_doc_j = ts / ms;
					weight = weight + weight_doc_j;
				}
				
			}
			
			sum_weight=sum_weight+ weight;
			//DocumentSample.updateWeightbyId(arr_id.get(i), weight);
			//DocumentSample.closeStatement();
			
		}
		double avg_weight= (double) sum_weight/N;
		
		return avg_weight;
	}
	public static double calculateWeightByTerm(ArrayList<String> key){
		//ResultSet rs=DocumentSample.getAllContent();
		ResultSet rs=DocumentRelative.getContent(200);
		ArrayList<HashMap<String, Integer>> list_frequent_key_of_doc = new ArrayList<HashMap<String,Integer>>();
		HashMap<String, Integer> num_doc_contain_key = new HashMap<String, Integer>();
		for(int i=0; i< key.size();i++) {
			num_doc_contain_key.put(key.get(i), 0);
		}
		ArrayList<Integer> arr_length_doc= new ArrayList<Integer>();
		ArrayList<Integer> arr_id= new ArrayList<Integer>();
		int sum_length= 0;
		try {
			String[] arr_content;
			String content="";
			HashMap<String, Integer> count_frequent_term= new HashMap<String, Integer>();
			int var_num=0;
			while(rs.next()) {
				 count_frequent_term.clear();
				 content =rs.getString("content");
				 arr_content= content.split(" ");
				arr_id.add(rs.getInt("id"));
				arr_length_doc.add(arr_content.length);
				sum_length=sum_length + arr_content.length;
				for(int i=0; i<arr_content.length;i++) {
					if (count_frequent_term.get(arr_content[i]) == null) {
						count_frequent_term.put(arr_content[i], 1);

					} else {
						var_num = count_frequent_term.get(arr_content[i]) + 1;
						count_frequent_term.put(arr_content[i], var_num);
					}
				}
				for(int i=0;i< key.size();i++) {
					if(count_frequent_term.get(key.get(i))!=null) {
						var_num=num_doc_contain_key.get(key.get(i)) +1;
						num_doc_contain_key.put(key.get(i), var_num);
					}
				}
				list_frequent_key_of_doc.add(count_frequent_term);
				
			}
			
			//DocumentSample.closeConnect();
			DocumentRelative.closeConnect();
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
	          //  ms=ms*Math.log(N)/Math.log(2);
				if (ms != 0) {
					weight_doc_j = ts / ms;
					weight = weight + weight_doc_j;
				}
				
			}
			
			sum_weight=sum_weight+ weight;
			//DocumentSample.updateWeightbyId(arr_id.get(i), weight);
			//DocumentSample.closeStatement();
			
		}
		double avg_weight= (double) sum_weight/N;
		
		return avg_weight;
		 

	}
	/*public static double calculateWeightByTerm(ArrayList<String> key) {
		

		ResultSet rs=DocumentSample.getAllContent();
		
		HashMap<String, Integer> num_doc_contain_key = new HashMap<String, Integer>();
		for(int i=0; i< key.size();i++) {
			num_doc_contain_key.put(key.get(i), 0);
		}
		
		ArrayList<Integer> arr_id= new ArrayList<Integer>();
		int sum_length= 0;
		int N= 0;
		double avg_length_doc= 0;
		double sum_weight=0;
		try {
			String[] arr_content;
			String content="";
			HashMap<String, Integer> count_frequent_term= new HashMap<String, Integer>();
			int var_num=0;
			while(rs.next()) {
				N++;
				 count_frequent_term.clear();
				 content =rs.getString("content");
				 arr_content= content.split(" ");
				arr_id.add(rs.getInt("id"));
				
				sum_length=sum_length + arr_content.length;
				avg_length_doc=(double)sum_length/N;
				for(int i=0; i<arr_content.length;i++) {
					if (count_frequent_term.get(arr_content[i]) == null) {
						count_frequent_term.put(arr_content[i], 1);

					} else {
						var_num = count_frequent_term.get(arr_content[i]) + 1;
						count_frequent_term.put(arr_content[i], var_num);
					}
				}
				for(int i=0;i< key.size();i++) {
					if(count_frequent_term.get(key.get(i))!=null) {
						var_num=num_doc_contain_key.get(key.get(i)) +1;
						num_doc_contain_key.put(key.get(i), var_num);
					}
				}
				//tinh trong so
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
					double ms = (0.8 + 0.2 * arr_content.length / avg_length_doc);
		           // ms=ms*Math.log(N)/Math.log(2);
					if (ms != 0) {
						weight_doc_j = ts / ms;
						weight = weight + weight_doc_j;
					}
					
					
				}
				//System.out.println("weight ="+ weight+"\n");
				sum_weight=sum_weight+ weight;
				
			}
			
			DocumentSample.closeConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("N="+ N+"\n");
		System.out.println("num doc contain key ="+ num_doc_contain_key+"\n");
		System.out.println("sum_weight ="+sum_weight+"\n");
		double avg_weight= (double) sum_weight/N;
		
		return avg_weight;
	}
*/	
	
}

