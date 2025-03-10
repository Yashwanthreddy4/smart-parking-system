import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


public class Register extends JFrame implements ActionListener
{
	Container c;
	Font f1=new Font("times new roman",Font.BOLD,16);
	Font f2=new Font("times new roman",Font.BOLD,16);
	JLabel j1,j2,j3,j4,j5,j6,j7,j8,jcp;
	JTextField t1,t5,t8,tcp;
	JComboBox t3,t4;
	JButton jb1,jb2,jb3;
	JPasswordField t2;
	Register()
	{
        setTitle("Registration::Secure Data Retrieval for Decentralized DisruptionTolerant Military Networks");
		c=getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(170,186,204));

		jb1=new JButton("Submit");
		jb1.setBounds(20,400,90,30);
        jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb1.setFont(f1);
		jb1.addActionListener(this);
		c.add(jb1);

		jb2=new JButton("Reset");
		jb2.setBounds(120,400,90,30);
        jb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb2.setFont(f1);
		jb2.addActionListener(this);
		c.add(jb2);

		jb3=new JButton("Close");
		jb3.setBounds(220,400,90,30);
        jb3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb3.setFont(f1);
		jb3.addActionListener(this);
		c.add(jb3);


		j1=new JLabel("Name :");
		j1.setFont(f1);
		j1.setBounds(20,100,100,50);
		c.add(j1);

		t1=new JTextField(20);
		t1.setFont(f1);
		t1.setBounds(120,110,150,30);
		c.add(t1);

		j2=new JLabel("Password :");
		j2.setFont(f2);
		j2.setBounds(20,150,100,50);
		c.add(j2);

		t2=new JPasswordField(20);
		t2.setFont(f2);
		t2.setBounds(120,160,150,30);
		c.add(t2);

		jcp=new JLabel("Confirm PW:");
		jcp.setFont(f1);
		jcp.setBounds(20,200,100,50);
		c.add(jcp);

		tcp=new JPasswordField(20);
		tcp.setFont(f2);
		tcp.setBounds(120,210,150,30);
		c.add(tcp);

		
		j3=new JLabel("Battalion:");
		j3.setFont(f1);
		j3.setBounds(20,250,100,50);
		c.add(j3);

		String list1[]={"B1","B2","B3","B4"};
		String list2[]={"R1","R2","R3","R4"};
		t3=new JComboBox(list1);
		t3.setFont(f2);
		t3.setBounds(120,260,150,30);
        t3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c.add(t3);

		j4=new JLabel("Region:");
		j4.setFont(f1);
		j4.setBounds(20,300,100,50);
		c.add(j4);

		t4=new JComboBox(list2);
		t4.setFont(f2);
		t4.setBounds(120,310,150,30);
        t4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		c.add(t4);


		setSize(350,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae)
	{
	     if(ae.getSource()==jb1)
	     {
		
		try
		{
			String name=t1.getText();
			String pass1=t2.getText();
			String pass2=tcp.getText();
			String Bat=t3.getSelectedItem().toString();
			String Region=t4.getSelectedItem().toString();
	        if(name.equals("")||pass1.equals("")||pass2.equals("")||Bat.equals("")||Region.equals(""))
			{
			     JOptionPane.showMessageDialog(null,"Enter All details");
			}
			else
			{
               
				if(pass1.equals(pass2))
				{
					String ip=JOptionPane.showInputDialog(null,"Enter Storage Node IP Address");
				     Socket sc=new Socket(ip,333);
				     DataOutputStream dout=new
			         DataOutputStream(sc.getOutputStream());
				     dout.writeUTF(name);
				     dout.writeUTF(pass1);
				     dout.writeUTF(Bat);
				     dout.writeUTF(Region);
			        JOptionPane.showMessageDialog(null,"Registration Successfull");
					new MainFrame();
				this.dispose( );
				}
				else
				{
			        JOptionPane.showMessageDialog(null,"Password Confirmation Failed!!!");
				}
			    }
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}
		if(ae.getSource()==jb2)
		{
			t1.setText("");
			t2.setText("");
			tcp.setText("");
		}
		if(ae.getSource()==jb3)
		{
			this.dispose( );
		}
	}
	public static void main(String[] args)
	{
		new Register();
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