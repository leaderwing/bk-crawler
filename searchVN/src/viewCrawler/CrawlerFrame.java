package viewCrawler;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import searchEngine.index;
//import view.Console;


 class CrawlerFrame extends JFrame{
	 // statis object of CrawlerFrame	
		private static CrawlerFrame crawlerFrame = new CrawlerFrame();
	 // Some GUI components.
	  	private JLabel lbUrl = new JLabel("Seek Url");
	  	private JLabel lbKey = new JLabel("Seek Key");
	  	private JLabel lbPopu = new JLabel("Population Size");
	  	private JLabel lbMuta = new JLabel("Mutation Size");
	  	private JLabel lbCross = new JLabel("Crossover");
	  	private JLabel lbGener = new JLabel("Generation Number");
	  	private JLabel lbNum = new JLabel("Parent Crossover Number");
	  	private JLabel lbMaxlink = new JLabel("Number Max Link");
	  	
	  	private JTextField tfUrl =new JTextField(20);
	  	private JTextField tfKey =new JTextField(20);
	  	private JTextField tfPopu =new JTextField(5);
	  	private JTextField tfMuta =new JTextField(5);
	  	private JTextField tfCross =new JTextField(5);
	  	private JTextField tfGener =new JTextField(5);
	  	private JTextField tfNum =new JTextField(5);
	  	private JTextField tfMaxlink =new JTextField(5);
	  	
	  	private JButton btShowConsole = new JButton("Show Console");
	  	private JButton btStart = new JButton("Start");
	    
	 //Panel of Seek Url and Key
	  	private JPanel panelSeek = new JPanel();
	  	  	
	 //Panel of Genetic Algorithm
		private JPanel panelGA = new JPanel();
	    
	 // The Java Console.
		private Console console;

	    private CrawlerFrame() {

		super("GA Crawler");
		
		panelSeek.add(lbUrl);
		panelSeek.add(tfUrl);
		panelSeek.add(lbKey);
		panelSeek.add(tfKey);
		
		GridLayout layout1=new GridLayout(2,2,10,10);
		panelSeek.setLayout(layout1);
		
		
		TitledBorder title2 = BorderFactory.createTitledBorder("Genetic Algorithm");
		panelGA.setBorder(title2);
		panelGA.add(lbPopu);
		panelGA.add(tfPopu);
		panelGA.add(lbMuta);
		panelGA.add(tfMuta);
		panelGA.add(lbCross);
		panelGA.add(tfCross);
		panelGA.add(lbGener);
		panelGA.add(tfGener);
		panelGA.add(lbNum);
		panelGA.add(tfNum);
		panelGA.add(lbMaxlink);
		panelGA.add(tfMaxlink);
		panelGA.add(btShowConsole);
		panelGA.add(btStart);
		
	// create the layout into panelGA
		GridLayout layout2=new GridLayout(7,2,10,10);
		panelGA.setLayout(layout2);
		
		
		//this.add(panelSeek);
		//this.add(panelGA);
		
		//GridLayout layout=new GridLayout(2,1,10,10);
		//this.setLayout(layout);
		// tạo đối tượng layout
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// tạo vị trí và kiểu cho panelSeek
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 0.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.PAGE_START;
		this.add(panelSeek, c);

		// tạo ví trí và kiểu cho panelGA
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.PAGE_START;
		this.add(panelGA, c);
		
		//bắt sự kiện ở các button
		 btShowConsole.addActionListener(new ShowConsole());
		 btStart.addActionListener(new StartCrawler());
		 
		// Now create the console.
		console = new Console();

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}
	    
	public static CrawlerFrame getInstance(){
		return crawlerFrame;
	}
	
	 class ShowConsole implements ActionListener 
	 {
		 public void actionPerformed(ActionEvent e) 
		 {
			console.setVisible(true);
		 }
	  }
	
	 class StartCrawler implements ActionListener 
	    {
		public void actionPerformed(ActionEvent e) 
		    {
		
			String seek_url= tfUrl.getText();
			String seek_key=tfKey.getText();
			int population_size= new Integer(tfPopu.getText());
			double mutation= new Double(tfMuta.getText());
			double crossover = new Double(tfCross.getText());
			int generation_number= new Integer(tfGener.getText());
			int parent_crossover_number= new Integer(tfNum.getText());
			int num_max_link = new Integer(tfMaxlink.getText());
			//index.crawler( seek_url, seek_key, population_size, mutation, crossover, generation_number, parent_crossover_number,num_max_link);
		    }
	    }
	public static void main(String arg[]) {
		
		CrawlerFrame crawlerFrame=(CrawlerFrame)CrawlerFrame.getInstance();
				
	}
}
