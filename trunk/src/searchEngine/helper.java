package searchEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class helper {
	public String removeStopWord(String content) {
		String[] stopword = { "a", "about", "above", "after", "again",
				"against", "all", "am", "an", "and", "any", "are", "aren't",
				"as", "at", "be", "because", "been", "before", "being",
				"below", "between", "both", "but", "by", "can't", "cannot",
				"could", "couldn't", "did", "didn't", "do", "does", "doesn't",
				"doing", "don't", "down", "during", "each", "few", "for",
				"from", "further", "had", "hadn't", "has", "hasn't", "have",
				"haven't", "having", "he", "he'd", "he'll", "he's", "her",
				"here", "here's", "hers", "herself", "him", "himself", "his",
				"how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in",
				"into", "is", "isn't", "it", "it's", "its", "itself", "let's",
				"me", "more", "most", "mustn't", "my", "myself", "no", "nor",
				"not", "of", "off", "on", "once", "only", "or", "other",
				"ought", "our", "ours", "ourselves", "out", "over", "own",
				"same", "shan't", "she", "she'd", "she'll", "she's", "should",
				"shouldn't", "so", "some", "such", "than", "that", "that's",
				"the", "their", "theirs", "them", "themselves", "then",
				"there", "there's", "these", "they", "they'd", "they'll",
				"they're", "they've", "this", "those", "through", "to", "too",
				"under", "until", "up", "very", "was", "wasn't", "we", "we'd",
				"we'll", "we're", "we've", "were", "weren't", "what", "what's",
				"when", "when's", "where", "where's", "which", "while", "who",
				"who's", "whom", "why", "why's", "with", "won't", "would",
				"wouldn't", "will", "you", "you'd", "you'll", "you're",
				"you've", "your", "yours", "yourself", "yourselves", "comment",
				"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December","si" };
		//content = content.toLowerCase();
		
		
		String[] arr_content = content.split(" ");
		/*List<String> list_term_in_content = new ArrayList<String>();
		//convert array content to array list
		Collections.addAll(list_term_in_content, arr_content);
		for (int i = 0; i < stopword.length; i++) {
                  for(int j=0;j<list_term_in_content.size();j++)
                  {
                	  
                	  if(stopword[i].equals(list_term_in_content.get(j))){
                		  list_term_in_content.remove(j);
                		  j--;
                	  }
                  }
		}
		content="";
		for(int j=0;j<list_term_in_content.size();j++)
		{
		    content+=list_term_in_content.get(j)+" ";	
			
		}*/
		String new_content="";
		for (int i = 0; i < stopword.length; i++) {
			for(int j=0;j<arr_content.length;j++)
			{
				if(stopword[i].equals(arr_content[j]))
				{
					arr_content[j]="";
				}
			}
		}
		for(int i=0;i<arr_content.length;i++)
		{
			new_content+=arr_content[i];
		}
		return new_content;

	}
}
