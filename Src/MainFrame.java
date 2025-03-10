import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;


public class MainFrame extends JFrame implements ActionListener 
{
    Container p1;
    JButton b1,b2,b3,b4,b5,b6;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public Font f2=new Font("times new roman",Font.BOLD,15);

    MainFrame()
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
        b2=new JButton("Switch User");
        b2.setForeground(Color.RED);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setFont(f2);
        b2.addActionListener(this);
        p1.add(b2);
        b1=new JButton("Sender");
        b1.setForeground(Color.RED);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setFont(f2);
        b1.addActionListener(this);
        p1.add(b1);
        b4=new JButton("EndUser");
        b4.setForeground(Color.RED);
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b4.setFont(f2);
        b4.addActionListener(this);
        p1.add(b4);
        b3=new JButton("Admin");
        b3.setForeground(Color.RED);
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setFont(f2);
        b3.addActionListener(this);
        p1.add(b3);
        /*b3=new JButton("Key Authority");
        b3.setForeground(Color.RED);
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setFont(f2);
        b3.addActionListener(this);
        p1.add(b3);
        b6=new JButton("GivePrivilages");
        b6.setForeground(Color.RED);
        b6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b6.setFont(f2);
        b6.addActionListener(this);
        p1.add(b6);
        */
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
            new Sender( );
        }
        if(ae.getSource( ) == b2)
        {
            new Login( );
        }
        if(ae.getSource( ) == b3)
        {
            new AdminLogin( );
        }
        if(ae.getSource( ) == b4)
        {
            
                new EndUser();
           
            
        }
        if(ae.getSource( ) == b5)
        {
            this.dispose( );
            System.exit(0);
        }
        //if(ae.getSource( ) == b6)
        //{
          //  new GivePrivilages();
        //}
    }

    //Main method

    public static void main (String[] args) 
    {
        new MainFrame();
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