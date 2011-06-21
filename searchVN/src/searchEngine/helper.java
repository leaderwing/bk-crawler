package searchEngine;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.DocumentSample;

public class helper {
	private static ArrayList<String> stopword = ReadStopWordToFile.readStopWordToFile();
	public static String removeStopWord(String content) {
		
		String edit_content = "";
		edit_content = content.toLowerCase();
		edit_content = content.replaceAll("[0-9]", "");
		edit_content = edit_content.replace("'", "\'");
		edit_content = edit_content.replace('"', '\"');
		String[] arr_content = edit_content.split(" ");

		String new_content = "";

		for (int i = 0; i < stopword.size(); i++) {
			for (int j = 0; j < arr_content.length; j++) {

				arr_content[j] = arr_content[j].trim();

				if ((stopword.get(i).equals(arr_content[j]))
						|| (arr_content[j] == " ")) {

					arr_content[j] = "";

				}
			}
		}
		for (int i = 0; i < arr_content.length; i++) {
			if (stopword.contains(arr_content[i])) {
				arr_content[i] = "";
			}
		}
		for (int i = 0; i < arr_content.length; i++) {

			if (arr_content[i].length() > 0) {
				// System.out.println("arr content i =_"+arr_content[i]);
				new_content += " " + arr_content[i];
			}
		}

		return new_content;

	}

	public static String[] removeStopWordVN(String[] sentences) {
		String[] temp = sentences;
		for (int i = 0; i<temp.length; i++){		
			temp[i] = removeStopWord(temp[i]);
		}
		return temp;
	}
	/*
	 * public static double calculateWeightByKey(String key) { double
	 * weight_key=0; ResultSet rs= DocumentSample.getContent();
	 * 
	 * return weight_key;
	 * 
	 * }
	 */
}
