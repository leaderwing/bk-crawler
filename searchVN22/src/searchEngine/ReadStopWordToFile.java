package searchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadStopWordToFile {
	public static ArrayList<String>  readStopWordToFile()
    {
        File file = new File("C:\\Users\\LONG\\workspace\\searchVN22\\stopword_vietnamese.txt");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        ArrayList<String> list_stopword= new ArrayList<String>();
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null)
            {
                /*contents.append(text)
                    .append(System.getProperty(
                        "line.separator"));*/
                list_stopword.add(text);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        // show file contents here
       
		return list_stopword;
    }
}
