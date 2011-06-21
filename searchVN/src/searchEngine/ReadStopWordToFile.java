package searchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class ReadStopWordToFile {
	public static ArrayList<String>  readStopWordToFile()
    {
        //File file = new File("C:\\Users\\LONG\\workspace\\searchVN\\stopword_vietnamese.txt");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        ArrayList<String> list_stopword= new ArrayList<String>();
        try
        {
        	Reader in = new InputStreamReader(new FileInputStream("stopword_vietnamese.txt"), "Unicode");
            reader = new BufferedReader(in);
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
