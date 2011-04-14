package searchEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class helper {
	public String removeStopWord(String content) {
		ArrayList<String> stopword= ReadStopWordToFile.readStopWordToFile();
		
		content=content.toLowerCase();
		content = content.replaceAll("[^a-z 0-9]+"," ");
		String[] arr_content = content.split(" ");
		
		String new_content="";
		for (int i = 0; i < stopword.size(); i++) {
			for(int j=0;j<arr_content.length;j++)
			{ 
				
				
				if(stopword.get(i).equals(arr_content[j]))
				{
					
					arr_content[j]="";
					
				}
			}
		}
		for(int i=0;i<arr_content.length;i++) {
			if(stopword.contains(arr_content[i])) {
				arr_content[i]="";
			}
		}
		for(int i=0;i<arr_content.length;i++)
		{
			new_content+=" "+arr_content[i];
		}
		
		return new_content;

	}
}
