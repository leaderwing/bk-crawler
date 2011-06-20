package viewCrawler;

import java.io.*;
import java.util.*;

public class ObservableStream extends ByteArrayOutputStream 
{
    Vector streamObservers = new Vector();
    
    void addStreamObserver(StreamObserver o) 
	{
	    streamObservers.addElement(o);
	}
    
    void removeStreamObserver(StreamObserver o) 
	{
	    streamObservers.removeElement(o);
	}
        
    void notifyObservers() 
	{
	    for(int i = 0; i < streamObservers.size(); i++)
		((StreamObserver) streamObservers.elementAt(i)).streamChanged();
	}
    
    public void write(byte[] b, int off, int len) 
	{
	    super.write(b, off, len);
	    notifyObservers();
	}

}

