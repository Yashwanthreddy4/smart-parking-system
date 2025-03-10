import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
public class EndUser  extends JFrame implements ActionListener {
        JPanel p1;
	JTextArea ta;
	JScrollPane pane;
	JButton b1,b2,b3,b4,b5;
        	JLabel l1,l2;
	JLabel t1;
	JLabel t2;
	String strLine,AES;
	String path1,fname;
	int num;
        public Font f2=new Font("times new roman",Font.BOLD,15);
        EndUser()
	{
		p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(170,186,204));
		setTitle("EndUser");
		setTitle("End User::Secure Data Retrieval for Decentralized Disruption-Tolerant Military Networks");                

                ImageIcon banner=new ImageIcon(this.getClass().getResource("Sender.jpg"));
		JLabel title=new JLabel();
		title.setIcon(banner);
		title.setBounds(0,-10,900,90);

		l1=new JLabel("File Name : ");
		l1.setForeground(Color.WHITE);
		l1.setBounds(100,80,150,25);
		l1.setFont(f2);
		p1.add(l1);	

		t1=new JLabel("");
		t1.setForeground(Color.WHITE);
		t1.setBounds(180,80,100,25);
		t1.setBackground(new Color(182,182,192));
		t1.setFont(f2);
		p1.add(t1);

		l2=new JLabel("Secret Key : ");
		l2.setForeground(Color.WHITE);
		l2.setBounds(350,80,150,25);
		l2.setFont(f2);
		p1.add(l2);

		t2=new JLabel("");
		t2.setForeground(Color.BLACK);
		t2.setBounds(435,82,200,25);
		t2.setBackground(new Color(182,182,192));
		t2.setFont(f2);
		p1.add(t2);


	                   ta=new JTextArea();
		ta.setFont(f2);
		ta.setForeground(Color.BLACK);
		pane=new JScrollPane();
		pane.setViewportView(ta);
		ta.setRows(20);
		ta.setColumns(20);
		pane.setBounds(100,120,500,300);

		b1=new JButton("Receive");
		b1.setForeground(Color.RED);
		b1.setBounds(620,120,150,25);
		b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b1.setFont(f2);
		b1.addActionListener(this);
		p1.add(b1);

		b2=new JButton("Get Key from KA");
		b2.setForeground(Color.RED);
		b2.setBounds(620,190,150,25);
		b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b2.setFont(f2);
		b2.addActionListener(this);
		p1.add(b2);
                
		b3=new JButton("Decrypt");
		b3.setForeground(Color.RED);
		b3.setBounds(620,260,150,25);
		b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b3.setFont(f2);
		b3.setEnabled(false);
		b3.addActionListener(this);		
		p1.add(b3);

		b4=new JButton("Save");
		b4.setForeground(Color.RED);
		b4.setBounds(620,330,150,25);
		b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b4.setFont(f2);
		b4.addActionListener(this);
		p1.add(b4);

		b5=new JButton("Close");
		b5.setForeground(Color.RED);
		b5.setBounds(620,400,150,25);
		b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b5.setFont(f2);
		b5.addActionListener(this);
		p1.add(b5);

 	                   p1.add(pane);
		p1.add(title);
		add(p1);
		setSize(900,550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        public void actionPerformed(ActionEvent ae)
	{
		b3.setEnabled(false);
		if(ae.getSource()==b1)
		{ 
			String strLINE=null;
			String newline="\n";
		String ip=JOptionPane.showInputDialog(null,"Enter Receiver Node IP Address");
			try
			{
			Socket st1=new Socket(ip,111);	
			}
			catch(UnknownHostException ex)
			{
			ta.setText("");	
			JOptionPane.showMessageDialog(null,"Invalid IP Address");		
			return;
			}
			catch(IOException ex)
			{
			}

			JFileChooser chooser=new JFileChooser();
			try
			{
		     File f=new File(new File("filename").getCanonicalPath());
			     chooser.setSelectedFile(f);
			}
			catch(IOException e1)
			{
			}
				int retval=chooser.showOpenDialog(b1);
				if(retval==JFileChooser.APPROVE_OPTION){
					File field=chooser.getSelectedFile();
					path1=field.getAbsolutePath();
				}

				File curFile=chooser.getSelectedFile();

				try{
				FileInputStream fstream=new FileInputStream(curFile);
				DataInputStream ins=new DataInputStream(fstream);
			BufferedReader br= new BufferedReader(new InputStreamReader(ins));
				StringBuffer buffer=new StringBuffer();
				while((strLine=br.readLine())!=null)
				{
				buffer.append(strLine+"\n");
				}
				ta.setText(buffer.toString());
				}
				catch(Exception e1)
				{
				System.err.println("Error:"+e1.getMessage());
				}
			}

		if(ae.getSource()==b2)
		{

			fname=JOptionPane.showInputDialog(null,"Enter The File Name");
		try
		{
			Socket st1=new Socket("localhost",111);
			DataOutputStream dos1=new DataOutputStream(st1.getOutputStream());
			DataInputStream in1=new DataInputStream(st1.getInputStream());
 			dos1.writeUTF(fname);
				String key=in1.readLine( );
				t1.setText(fname);
				t2.setText(key);
				if(t2.getText( ).equals("Invalid Key File Name"))
				{
				ta.setText("");
				t2.setText("Invalid Key");
				b3.setEnabled(false);
				}			      
				else
				{
				b3.setEnabled(true);
				}
			}
			catch(Exception es)
			{
			System.out.println(es);
			}
		}

		if(ae.getSource()==b3)
		{
		     String Data=ta.getText();
		     try
		     {
			AES a1=new AES();
			String decdata=a1.decrypt(fname);
			ta.setText(decdata);
		     }
		catch(Exception es)
		{
		System.out.println(es);
		}
		}

		if(ae.getSource()==b4)
		{
			String fname=t1.getText();
			String sk=t2.getText();
			String content=ta.getText();
			FileOutputStream fos;
			DataOutputStream dos;			
			String st = "\n";
			try
			{			
			fos =new FileOutputStream(fname + "Download");
		 	dos =new DataOutputStream(fos);
			dos.writeBytes(content);
			dos.write(st.getBytes( ));
			dos.close( );
			fos.close( );
			JOptionPane.showMessageDialog(null,"File Received Sucessfully");
			}
			catch(Exception es)
			{
			System.out.println(es);
			}	
		}
			if(ae.getSource( ) == b5)
			{
			this.dispose( );
			}
		}
        
         public static void main (String[] args) 
{

		new EndUser();
		try
		{
	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception es)
		{
		System.out.println(es);
		}
	
    }
}