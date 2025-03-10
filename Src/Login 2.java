import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;





public class Login extends JFrame implements ActionListener
{
	Container c;
	Font f1=new Font("times new roman",Font.BOLD,16);
	Font f2=new Font("times new roman",Font.BOLD,16);
        JLabel j1,j2,j3;
        JTextField t5;
        JComboBox cb1;
        JButton jb1,jb2,jb3,jb4;
        JPasswordField t2;
        String uname,pwd[ ] = new String[100];
        boolean flag = false;
	Login()
	{
            setTitle("Registration::Secure Data Retrieval for Decentralized DisruptionTolerant Military Networks");
		c=getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(170,186,204));

        jb1=new JButton("Login");
        jb1.setFont(f1);
        jb1.setBounds(90,250,120,25);
        //jb1.setForeground(new Color(104,131,188));
        //jb1.setBackground(new Color(255,193,204));
        //jb1.setBorder(BorderFactory.createLineBorder(Color.red));
        //jb1.setBorderPainted(false);
        //jb1.setFocusPainted(false);
        //jb1.setContentAreaFilled(false);
        jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //jb1.setOpaque(true);
        //jb2.setBounds(160,400,150,30);
        jb1.addActionListener(this);
        c.add(jb1);

		//jb1=new JButton("Login");
		//jb1.setBounds(0,350,150,30);
		//jb1.addActionListener(this);
		//c.add(jb1);

		jb2=new JButton("Reset");
        jb2.setFont(f1);
		jb2.setBounds(250,250,120,25);
		jb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jb2.addActionListener(this);
		c.add(jb2);

        jb4=new JButton("Close");
        jb4.setFont(f1);
		jb4.setBounds(250,332,120,25);
        jb4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jb4.addActionListener(this);
		c.add(jb4);
		
		j3=new JLabel("NewUser :");
		j3.setFont(f1);
		j3.setBounds(10,320,80,50);
		c.add(j3);
		
		jb3=new JButton("Register");
        jb3.setFont(f1);
		jb3.setBounds(90,332,120,25);
        jb3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jb3.addActionListener(this);
		c.add(jb3);

		j1=new JLabel("Name :");
		j1.setFont(f1);
		j1.setBounds(20,100,100,50);
		c.add(j1);

		cb1=new JComboBox( );
		cb1.setFont(f1);
		cb1.setBounds(120,110,150,30);
		c.add(cb1);
		cb1.addItem("  --- Select ---");
		cb1.setSelectedItem("--- Select ---");
		try
		{
			Socket skt=new Socket("localhost",300);
			DataInputStream dis = new DataInputStream(skt.getInputStream( ));
			int idx = 1;
			while(true)
			{
			uname = dis.readLine( );
			pwd[idx] = dis.readLine( );
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
			
		j2=new JLabel("Password :");
		j2.setFont(f2);
		j2.setBounds(20,150,100,50);
		c.add(j2);
                
        t2=new JPasswordField(20);
		t2.setFont(f2);
		t2.setBounds(120,160,150,30);
		c.add(t2);
                
        setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
        public void actionPerformed(ActionEvent ae) 
{
          if(ae.getSource()==jb1)
	     {
		try
		{
			String name = (String) cb1.getSelectedItem();
			String pass1 = t2.getText();
			if(name.equals("") || pass1.equals(""))
			{
			     JOptionPane.showMessageDialog(null,"Enter All Details");
			}
			else
			{
			String pwd1 = t2.getText( );
			String pwd2 = pwd[cb1.getSelectedIndex( )];
			if(pwd1.equals(pwd2))
			{
			   JOptionPane.showMessageDialog(null,"Login Succesfull");
			   new MainFrame( );
			}
			else
			{
			   JOptionPane.showMessageDialog(null,"Invalid Password");
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
			cb1.setSelectedItem("  --- Select ---");
			t2.setText("");
		}
	if(ae.getSource()==jb3)
		{
			new Register();
		}
        if(ae.getSource()==jb4)
		{
			this.dispose( );
		}
	}
        public static void main(String[] args)
	{
		new Login();
		try
		{
	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception es){System.out.println(es);}
	}

}