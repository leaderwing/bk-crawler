package searchEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class helper {
	public String removeStopWord(String content) {
		ArrayList<String> stopword= ReadStopWordToFile.readStopWordToFile();
		String edit_content="";
		content=content.toLowerCase();
		edit_content= content.replaceAll("[^a-z 0-9]+","");
		String[] arr_content = edit_content.split(" ");
		
		String new_content="";
		for (int i = 0; i < stopword.size(); i++) {
			for(int j=0;j<arr_content.length;j++)
			{ 
				
				arr_content[j]=arr_content[j].trim();
				
				if((stopword.get(i).equals(arr_content[j]))||(arr_content[j]==" "))
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
			/*if((arr_content[i]=="")||(arr_content[i]==" ")||(arr_content[i]==null)) {
			   continue;
			}
			else {
				System.out.println("arr content i =_"+arr_content[i]+"_ \n");
				new_content+=" "+arr_content[i];
			}*/
			if(arr_content[i].length()>0) {
				//System.out.println("arr content i =_"+arr_content[i]+"_ \n");
				new_content+=" "+arr_content[i];
			}
		}
		
		return new_content;

	}
}
