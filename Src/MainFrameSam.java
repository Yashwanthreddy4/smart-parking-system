import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;


public class MainFrameSam extends JFrame implements ActionListener 
{
    Container p1;
    JButton b1,b2,b3,b4,b5,b6;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public Font f2=new Font("times new roman",Font.BOLD,15);

    MainFrameSam()
    {
        setLayout(null);
        p1=getContentPane();
        p1=new JPanel();
        p1.setBackground(new Color(170,186,200));
        setTitle("Sender::Secure Data Retrieval for Decentralized Disruption-Tolerant Military Networks");
        ImageIcon banner=new ImageIcon(this.getClass().getResource("Sender.jpg"));
        JLabel title=new JLabel();
        title.setIcon(banner);
        title.setBounds(0,-10,900,90);

        b2=new JButton("Login");
        b2.setForeground(Color.RED);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setFont(f2);
        b2.addActionListener(this);
        p1.add(b2);
        b1=new JButton("NewUser");
        b1.setForeground(Color.RED);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setFont(f2);
        b1.addActionListener(this);
        p1.add(b1);
        
        b5=new JButton("Exit");
        b5.setForeground(Color.RED);
        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b5.setFont(f2);
        b5.addActionListener(this);
        p1.add(b5);
        p1.add(title);
        p1.setBounds(0,0,900,500);
        add(p1);
        setSize(900,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //ActionEvent method

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource( ) == b1)
        {
            new Register( );
        }
        if(ae.getSource( ) == b2)
        {
            new Login( );
        }
       
        if(ae.getSource( ) == b5)
        {
            System.exit(0);
        }
      
    }

    //Main method

    public static void main (String[] args) 
    {
        new MainFrameSam();
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