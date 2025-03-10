import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.InflaterInputStream;

import javax.swing.*;
import javax. imageio.*;


public class GivePrivilages extends JFrame implements ActionListener
{
	int temporary;
	Container p1;
	JLabel l1,l2,l3,l4;
	JLabel ltitle;
	JComboBox cb1,cb2,cb3;
	JButton b1,b2;
	JCheckBox c1;
	public Font f1=new Font("times new roman",Font.BOLD,15);
	public Font f2=new Font("times new roman",Font.BOLD,20);
	String name,uname,bata,reg;
	String download="false";
	boolean flag = false;
	GivePrivilages()
	{	
		
		p1=new JPanel();
		p1.setLayout(null);
		
		setTitle("GivePrivilages::Secure Data Retrieval for Decentralized Disruption-Tolerant Military Networks");
		ltitle=new JLabel("PRIVILAGES");
		ltitle.setFont(f2);
		ltitle.setBounds(100,20,150,25);
		p1.add(ltitle);
		cb1=new JComboBox();
		cb1.setBounds(150,100,150,25);
		cb1.setFont(f2);
		p1.add(cb1);
		try
		{
			Socket skt=new Socket("localhost",300);
			DataInputStream dis = new DataInputStream(skt.getInputStream( ));
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			int idx = 0;
			while(true)
			{
			uname = br.readLine( );
			
			if(uname.equals("end"))
			{
			break;
			}
			cb1.addItem(uname);			
			idx++;			
			}
			flag = true;
			}
			catch(Exception es)
			{
			System.out.println(es);
			}
		String list1[]={"B1","B2","B3","B4"};
		String list2[]={"R1","R2","R3","R4"};
		cb2=new JComboBox( list1);
		cb2.setBounds(150,150,150,25);
		cb2.setFont(f2);
		p1.add(cb2);

		cb3=new JComboBox(list2 );
		cb3.setBounds(150,200,150,25);
		cb3.setFont(f2);
		p1.add(cb3);

		l1=new JLabel("Select Betalion:");
		l1.setBounds(0,150,150,25);
		l1.setFont(f2);
		p1.add(l1);

		
		l2=new JLabel("Select Region:");
		l2.setBounds(0,200,150,25);
		l2.setFont(f2);
		p1.add(l2);

		
		l3=new JLabel("Permission:");
		l3.setBounds(0,250,150,25);
		l3.setFont(f2);
		p1.add(l3);

		
		l4=new JLabel("Select EndUser:");
		l4.setBounds(0,100,150,25);
		l4.setFont(f2);
		p1.add(l4);
         

		c1=new JCheckBox("Download");
		c1.setBounds(150,250,150,25);
		c1.setFont(f2);
		p1.add(c1);

		
		b1=new JButton("Permit");
		b1.setBounds(140,350,100,25);
		b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b1.setFont(f2);
		b1.addActionListener(this);
		p1.add(b1);
		b2=new JButton("Close");
		b2.setBounds(20,350,100,25);
		b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b2.setFont(f2);
		b2.addActionListener(this);
		p1.add(b2);

		
		c1.addActionListener(this);		
		cb1.addActionListener(this);		
		p1.setBackground(new Color(170,186,200));

		add(p1);
		setSize(350,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==c1)
		{
		     download="true";
		}
		if(ae.getSource()==b1)
		{
		     String uname=cb1.getSelectedItem().toString();
		     String bat=cb2.getSelectedItem().toString();
		     String reg=cb3.getSelectedItem().toString();
		     String dow=download;
		     try
		     {
		                   Socket sun=new Socket("localhost",444);
			DataOutputStream dos3=new DataOutputStream(sun.getOutputStream());
			DataInputStream in3=new DataInputStream(sun.getInputStream());
				     dos3.writeUTF(uname);
				     dos3.writeUTF(bat);
				     dos3.writeUTF(reg);
				     dos3.writeUTF(dow);
			JOptionPane.showMessageDialog(null,"Privilages Given to "+uname);
		     }catch(Exception es){System.out.println(es);}		
			    
			this.dispose( );
		}
		if(ae.getSource() == b2)
		{
			this.dispose( );
		}
		if(ae.getSource( ) == cb1 && flag == true)
		{
			try
			{
				Socket skt=new Socket("localhost",200);
				DataOutputStream dos = new DataOutputStream(skt.getOutputStream( ));
				DataInputStream dis = new DataInputStream(skt.getInputStream( ));
				BufferedInputStream br = new BufferedInputStream(new InflaterInputStream(dis));
				String it = cb1.getSelectedItem().toString();
				dos.writeUTF(it);	
				cb2.removeAllItems( );
				cb3.removeAllItems( );
				bata = dis.readLine( );
				cb2.addItem(bata);
				reg = dis.readLine( );
				cb3.addItem(reg);
			}
			catch(Exception es)
			{
				System.out.println(es);
			}
		}
	}
	public static void main(String[] args)
	{
		
		new GivePrivilages();
	}
}		
