package view;

import java.io.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

import searchEngine.Testcrawler;

//import COM.Subrahmanyam.utils.*;

public class FormCrawler extends Frame
{
    // Some GUI components.
    LabledTextField operand1, operand2;
    
    TextField result;
    Button showConsole;
    Button doIt;
    Button dumpOutput;
    Button dumpError;
    Checkbox enableOutput;
    Checkbox enableError;
    Label lbUrl= new Label("Seek Urls");
    TextField txt1= new TextField();
    TextArea seekUrl= new TextArea(10, 10);
    Checkbox setError;
    
    // The Java Console.
    Console console;
    
    public FormCrawler()
	{

	    super("Java Console: A Demo");
	    addWindowListener(new ErrorsListener(this));

	    // Create the GUI components.
	    operand1 = new LabledTextField("Operand 1", "");
	    
	    operand2 = new LabledTextField("Operand 2", "");
	    
	    doIt = new Button("Divide");
	    showConsole = new Button("Show Console");
	    dumpOutput = new Button("Dump Output");
	    dumpError = new Button("Dump Errors");
	    
	    enableOutput = new Checkbox("Enable Output", true);
	    enableError = new Checkbox("Enable Error", true);
	    
	    // Set a layout and add them.
	    setLayout(new GridLayout(0, 2, 10, 10));
	    //setLayout(new FlowLayout());
	    add(operand1);
	    add(operand2);
	    add(lbUrl);txt1.setColumns(30);add(txt1);
	    add(seekUrl);
	    add(enableOutput);
	    add(enableError);
	    
	    add(doIt);
	    add(showConsole);
	    add(dumpOutput);
	    add(dumpError);
	   
	    
	    // Setup listeners - the 1.1 stuff.
	    doIt.addActionListener(new DoIt());
	    showConsole.addActionListener(new ShowConsole());
	    
	    enableOutput.addItemListener(new EnableOutput());
	    enableError.addItemListener(new EnableError());

	    dumpOutput.addActionListener(new DumpOutput());
	    dumpError.addActionListener(new DumpError());

	    // Now create the console.
	    console = new Console();
	}

    public Dimension getMinimumSize() 
	{
	    return new Dimension(500, 400);
	}
    
    public Dimension getPreferredSize() 
	{
	    return getMinimumSize();
	}

    class DoIt implements ActionListener 
    {
	public void actionPerformed(ActionEvent e) 
	    {
		int result = (new Integer(operand1.getText())).intValue() / 
		    (new Integer(operand2.getText())).intValue();
		Testcrawler.divide(new Integer(operand1.getText()),new Integer(operand2.getText()) );
		/*System.out.println(operand1.getText() + " / " +
				   operand2.getText() + " = " + result);*/
	    }
    }
    
    class ShowConsole implements ActionListener 
    {
	public void actionPerformed(ActionEvent e) 
	    {
		console.setVisible(true);
	    }
    }

    class DumpOutput implements ActionListener 
    {
	public void actionPerformed(ActionEvent e) 
	    {
		console.resetOutput();
		try {
		    System.out.println(console.getOutputContent().toString());
		}
		catch(IOException ex) {
		}
		console.setOutput();
	    }
    }
    
    class DumpError implements ActionListener 
    {
	public void actionPerformed(ActionEvent e) 
	    {
		console.resetError();
		try {
		    System.err.println(console.getErrorContent().toString());
		}
		catch(IOException ex) {
		    ex.printStackTrace();
		}
		console.setError();
	    }
    }
    
    class EnableOutput implements ItemListener 
    {
	public void itemStateChanged(ItemEvent e) 
	    {
		if(e.getStateChange() == ItemEvent.SELECTED)
		    console.setOutput();
		else
		    console.resetOutput();
	    }
    }

    class EnableError implements ItemListener 
    {
	public void itemStateChanged(ItemEvent e) 
	    {
		if(e.getStateChange() == ItemEvent.SELECTED) 
		    console.setError();
		else
		    console.resetError();
	    }
    }
    

    public static void main_2(String s[]) 
	{
	    FormCrawler e = new FormCrawler();
	    e.pack();
	    e.setVisible(true);
	}
    
    class LabledTextField extends Panel 
    {
	Label l;
	TextField tf;
	
	public LabledTextField(String label, String field) 
	    {
		super();
		l = new Label(label, Label.LEFT);
		tf = new TextField(field, 3);
		setLayout(new GridLayout(1, 2));
		add(l);
		add(tf);
	    }
	
	String getText() 
	    {
		return tf.getText();
	    }
	
	void setText(String s) 
	    {
		tf.setText(s);
	    }
    }

    class ErrorsListener extends WindowAdapter 
    {
	Frame frame;
	
	ErrorsListener(Frame frame) 
	    {
		this.frame = frame;
	    }
	
	public void windowClosing(WindowEvent e) 
	    {
		frame.setVisible(false);
		System.exit(0);
	    }
    }
}

