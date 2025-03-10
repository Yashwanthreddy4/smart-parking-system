import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
public class KeyAuthority  extends JFrame implements ActionListener {
                   JPanel p1,p2;
	JTextArea ta;
	JScrollPane pane;
	JButton b1,b2,b3,b4;
	JTable jTbl;
	JTableHeader jTblHdr;
	public Font f2=new Font("times new roman",Font.BOLD,15);
	String uname,pwd,bata,reg,dow,key;
	KeyAuthority()
	{
	setLayout(null);
	p1=new JPanel();
	p2 = new JPanel();
	p1.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
	p2.setLayout(new BorderLayout( ));

	p1.setBackground(new Color(170,186,200));
	p2.setBackground(new Color(170,186,200));
setTitle("Sender::Secure Data Retrieval for Decentralized Disruption-Tolerant Military Networks");

		ImageIcon banner=new ImageIcon(this.getClass().getResource("image-4.jpeg"));
		JLabel title=new JLabel();
		title.setIcon(banner);
		title.setBounds(0,-10,900,90);

		b1=new JButton("View Users");
		b1.setForeground(Color.RED);
		b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b1.setFont(f2);
		b1.addActionListener(this);
		p1.add(b1);

		b2=new JButton("View Privilages");
		b2.setForeground(Color.RED);
		b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b2.setFont(f2);
		b2.addActionListener(this);
		p1.add(b2);

		b3=new JButton("View Keys");
		b3.setForeground(Color.RED);
		b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b3.setFont(f2);
		b3.addActionListener(this);
		p1.add(b3);

		b4=new JButton("Close");
		b4.setForeground(Color.RED);
		b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b4.setFont(f2);
		b4.addActionListener(this);
		p1.add(b4);

		ta=new JTextArea();
		ta.setFont(f2);
		ta.setForeground(Color.BLACK);
		pane=new JScrollPane();
		pane.setViewportView(ta);
		ta.setRows(20);
		ta.setColumns(20);
		ta.setEnabled(false);
		pane.setBounds(100,120,500,300);
	
		p2.add(pane);
		p1.add(title);
//		p2.add(title);
	
		p1.setBounds(375,50,500,400);
		p2.setBounds(10,50,350,400);
		add(p1);
		add(p2);

		setSize(900,500);
		setVisible(true);
		setBackground(new Color(170,186,200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{		
		String hds[ ] = {"Name","Password","Betalion","Region"};
		Object data[ ][ ] = new Object[100][100];	
		int idx = 0;
		try
		{		
		Socket skt=new Socket("localhost",400);
		DataInputStream dis = new DataInputStream(skt.getInputStream( ));
		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			while(true)
			{
			uname = br.readLine( );
			if(uname.equals("end"))
			{
			break;
			}
			pwd = br.readLine( );
			bata = br.readLine( );
			reg = br.readLine( );
			data[idx][0] = uname;
			data[idx][1] = pwd;
			data[idx][2] = bata;
			data[idx][3] = reg;
			jTbl = new JTable(data,hds);
			jTblHdr = jTbl.getTableHeader( );
			p2.add(jTblHdr,"North");
			p2.add(jTbl,"Center");
			p2.revalidate( );
			idx++;
			}
		}
		catch(Exception es)
		{
		System.out.println(es);
		}
		}

		if(ae.getSource()==b2)
		{		
		String hds[ ] = {"Name","Betalion","Region","Download"};
		Object data[ ][ ] = new Object[100][100];	
		int idx = 0;
		try
		{		
		Socket skt=new Socket("localhost",600);
		DataInputStream dis = new DataInputStream(skt.getInputStream( ));
		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			while(true)
			{
			uname = br.readLine( );
			if(uname.equals("end"))
			{
			break;
			}
			bata = br.readLine( );
			reg = br.readLine( );
			dow = br.readLine( );
			data[idx][0] = uname;
			data[idx][1] = bata;
			data[idx][2] = reg;
			data[idx][3] = dow;
			jTbl = new JTable(data,hds);
			jTblHdr = jTbl.getTableHeader( );
			p2.add(jTblHdr,"North");
			p2.add(jTbl,"Center");
			p2.revalidate( );
			idx++;
			}
		}
		catch(Exception es)
		{
		System.out.println(es);
		}
		}

		if(ae.getSource()==b3)
		{		
		String hds[ ] = {"User Name","Key"};
		Object data[ ][ ] = new Object[100][100];	
		int idx = 0;
		try
		{		
		Socket skt=new Socket("localhost",500);
		DataInputStream dis = new DataInputStream(skt.getInputStream( ));
		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			while(true)
			{
			uname = br.readLine( );
			if(uname.equals("end"))
			{
			break;
			}
			key = br.readLine( );
			data[idx][0] = uname;
			data[idx][1] = key;
			jTbl = new JTable(data,hds);
			jTblHdr = jTbl.getTableHeader( );
			p2.add(jTblHdr,"North");
			p2.add(jTbl,"Center");
			p2.revalidate( );
			idx++;
			}
		}
		catch(Exception es)
		{
		System.out.println(es);
		}
		}
		if(ae.getSource()==b4)
		{		
		     try
		     {
				this.dispose( );
		     }
            catch(Exception es)
            {
            	System.out.println(es);
            }
		}
	}

   public static void main (String[] args) 
   {

		new KeyAuthority();

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