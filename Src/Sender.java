import java.io.*;
import javax.swing.*;
import java.net.Socket;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;


 


public class Sender extends JFrame implements ActionListener
{
    JPanel p1;
    JTextArea ta;
    JScrollPane pane;
    JButton b1,b2,b3,b4,b5;
    MenuBar mbr;
    Menu file;
    MenuItem i1,i2;
    JLabel l1,l2,t1,t2;
    String strLine,AES,path1,fname;
    public Font f2;
    int num;
    
    Sender() {
        f2 = new Font("times new roman", 1, 15);
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(170,186,204));
        setTitle("Sender::Secure Data Retrieval for Decentralized Disruption-Tolerant Military Networks");
        mbr = new MenuBar();
        setMenuBar(mbr);
        file = new Menu("File");
        i1 = new MenuItem("Register");
        i2 = new MenuItem("Give Privilages");
        file.add(i1);
        file.add(i2);
        mbr.add(file);
        final ImageIcon icon = new ImageIcon(this.getClass().getResource("Sender.jpg"));
        final JLabel comp = new JLabel();
        comp.setIcon(icon);
        comp.setBounds(0, -10, 900, 90);
        i1.addActionListener(this);
        i2.addActionListener(this);
        l1 = new JLabel("File Name : ");
        l1.setForeground(Color.WHITE);
        l1.setBounds(100, 80, 150, 25);
        l1.setFont(f2);
        p1.add(l1);
        t1 = new JLabel("");
        t1.setForeground(Color.WHITE);
        t1.setBounds(180, 80, 100, 25);
        t1.setBackground(new Color(182, 182, 192));
        t1.setFont(f2);
        p1.add(t1);
        l2 = new JLabel("Secret Key : ");
        l2.setForeground(Color.WHITE);
        l2.setBounds(350, 80, 150, 25);
        l2.setFont(f2);
        p1.add(l2);
        t2 = new JLabel("");
        t2.setForeground(Color.BLACK);
        t2.setBounds(435, 82, 100, 25);
        t2.setBackground(new Color(182, 182, 192));
        t2.setFont(f2);
        p1.add(t2);
        ta = new JTextArea();
        ta.setFont(this.f2);
        ta.setForeground(Color.BLACK);
        pane = new JScrollPane();
        pane.setViewportView(this.ta);
        ta.setRows(20);
        ta.setColumns(20);
        pane.setBounds(100, 120, 500, 300);
        b1 = new JButton("Browse");
        b1.setForeground(Color.RED);
        b1.setBounds(620, 120, 150, 25);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setFont(f2);
        b1.addActionListener(this);
        p1.add(b1);
        b2 = new JButton("Get Key from KA");
        b2.setForeground(Color.RED);
        b2.setBounds(620, 190, 150, 25);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setFont(f2);
        b2.addActionListener(this);
        p1.add(b2);
        b3 = new JButton("Encrypt");
        b3.setForeground(Color.RED);
        b3.setBounds(620, 260, 150, 25);
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setFont(f2);
        b3.addActionListener(this);
        p1.add(b3);
        b4 = new JButton("Upload");
        b4.setForeground(Color.RED);
        b4.setBounds(620, 330, 150, 25);
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b4.setFont(f2);
        b4.addActionListener(this);
        p1.add(b4);
        b5 = new JButton("Close");
        b5.setForeground(Color.RED);
        b5.setBounds(620, 400, 150, 25);
        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b5.setFont(f2);
        b5.addActionListener(this);
        p1.add(b5);
        p1.add(pane);
        p1.add(comp);
        add(p1);
        setSize(900, 500);
        setVisible(true);
        setDefaultCloseOperation(3);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == i1) {
            new Register();
        }
        if (actionEvent.getSource() == i2) {
            new GivePrivilages();
        }
        if (actionEvent.getSource() == b1) {
            final JFileChooser fileChooser = new JFileChooser();
            try {
                
                fileChooser.setSelectedFile(new File(new File("filename").getCanonicalPath()));
            }
            catch (IOException ex2) {}
            if (fileChooser.showOpenDialog(this.b1) == 0) {
                this.path1 = fileChooser.getSelectedFile().getAbsolutePath();
            }
            final File selectedFile = fileChooser.getSelectedFile();
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(selectedFile))));
                final StringBuffer sb = new StringBuffer();
                while (true) {
                    final String line = bufferedReader.readLine();
                    this.strLine = line;
                    if (line == null) {
                        break;
                    }
                    sb.append(this.strLine + "\n");
                }
                this.ta.setText(sb.toString());
            }
            catch (Exception ex) {
                System.err.println("Error:" + ex.getMessage());
            }
        }
        if (actionEvent.getSource() == this.b2) {
            this.fname = JOptionPane.showInputDialog(null, "Enter The File Name");
            final Random random = new Random();
            try {
                this.num = random.nextInt(2);
                if (this.num == 0) {
                    final Socket socket = new Socket("localhost", 666);
                    final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    dataOutputStream.writeUTF(this.fname);
                    final String line2 = dataInputStream.readLine();
                    this.t1.setText(this.fname);
                    this.t2.setText(line2);
                    final Socket socket2 = new Socket("localhost", 666);
                    final DataOutputStream dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
                    final DataInputStream dataInputStream2 = new DataInputStream(socket2.getInputStream());
                    dataOutputStream2.writeUTF("KA-1");
                    dataOutputStream2.writeUTF(this.fname);
                    dataOutputStream2.writeUTF(line2);
                    final DataOutputStream dataOutputStream3 = new DataOutputStream(new Socket("localhost", 700).getOutputStream());
                    dataOutputStream3.writeUTF("KA-1");
                    dataOutputStream3.writeUTF(line2);
                }
                if (this.num == 1) {
                    final Socket socket3 = new Socket("localhost", 666);
                    final DataOutputStream dataOutputStream4 = new DataOutputStream(socket3.getOutputStream());
                    final DataInputStream dataInputStream3 = new DataInputStream(socket3.getInputStream());
                    dataOutputStream4.writeUTF(this.fname);
                    final String line3 = dataInputStream3.readLine();
                    this.t1.setText(this.fname);
                    this.t2.setText(line3);
                    final Socket socket4 = new Socket("localhost", 666);
                    final DataOutputStream dataOutputStream5 = new DataOutputStream(socket4.getOutputStream());
                    final DataInputStream dataInputStream4 = new DataInputStream(socket4.getInputStream());
                    dataOutputStream5.writeUTF("KA-1");
                    dataOutputStream5.writeUTF(this.fname);
                    dataOutputStream5.writeUTF(line3);
                    final DataOutputStream dataOutputStream6 = new DataOutputStream(new Socket("localhost", 700).getOutputStream());
                    dataOutputStream6.writeUTF("KA-2");
                    dataOutputStream6.writeUTF(line3);
                }
                if (this.num == 2) {
                    final Socket socket5 = new Socket("localhost", 666);
                    final DataOutputStream dataOutputStream7 = new DataOutputStream(socket5.getOutputStream());
                    final DataInputStream dataInputStream5 = new DataInputStream(socket5.getInputStream());
                    dataOutputStream7.writeUTF(this.fname);
                    final String line4 = dataInputStream5.readLine();
                    this.t1.setText(this.fname);
                    this.t2.setText(line4);
                    final Socket socket6 = new Socket("localhost", 666);
                    final DataOutputStream dataOutputStream8 = new DataOutputStream(socket6.getOutputStream());
                    final DataInputStream dataInputStream6 = new DataInputStream(socket6.getInputStream());
                    dataOutputStream8.writeUTF("KA-3");
                    dataOutputStream8.writeUTF(this.fname);
                    dataOutputStream8.writeUTF(line4);
                    final DataOutputStream dataOutputStream9 = new DataOutputStream(new Socket("localhost", 700).getOutputStream());
                    dataOutputStream9.writeUTF("KA-3");
                    dataOutputStream9.writeUTF(line4);
                }
            }
            catch (Exception x) {
                System.out.println(x);
            }
        }
        if (actionEvent.getSource() == this.b3) {
            final String text = this.ta.getText();
            try {
                this.ta.setText(new AES().encrypt(text, this.fname));
                this.b3.setEnabled(false);
            }
            catch (Exception x2) {
                System.out.println(x2);
            }
        }
        if (actionEvent.getSource() == this.b4) {
            final String text2 = this.t1.getText();
            final String text3 = this.t2.getText();
            this.ta.getText();
            final String showInputDialog = JOptionPane.showInputDialog(null, "Enter Storage Node IP Address");
            final String[] items = { "B1", "B2", "B3", "B4" };
            final String[] items2 = { "R1", "R2", "R3", "R4" };
            final JComboBox message = new JComboBox(items);
            final JComboBox message2 = new JComboBox(items2);
            JOptionPane.showMessageDialog(null, message, "Select Batalion", 3);
            JOptionPane.showMessageDialog(null, message2, "Select Region", 3);
            final AES aes = new AES();
            try {
                String str = null;
                if (this.num == 0) {
                    str = "KA-1";
                }
                if (this.num == 1) {
                    str = "KA-2";
                }
                if (this.num == 2) {
                    str = "KA-3";
                }
                final String string = message.getSelectedItem().toString();
                final String string2 = message2.getSelectedItem().toString();
                final Socket socket7 = new Socket(showInputDialog, 999);
                final DataOutputStream dataOutputStream10 = new DataOutputStream(socket7.getOutputStream());
                final DataInputStream dataInputStream7 = new DataInputStream(socket7.getInputStream());
                dataOutputStream10.writeUTF(text2);
                dataOutputStream10.writeUTF(text3);
                dataOutputStream10.writeUTF(str);
                dataOutputStream10.writeUTF(string);
                dataOutputStream10.writeUTF(string2);
                final BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(this.fname + "Encrypt"))));
                final StringBuffer buffer = new StringBuffer();
                while (true) {
                    final String line5 = bufferedReader2.readLine();
                    this.strLine = line5;
                    if (line5 == null) {
                        break;
                    }
                    buffer.append(this.strLine + "\n");
                }
                dataOutputStream10.writeUTF(new String(buffer));
                final BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(this.fname + "Decrypt"))));
                final StringBuffer buffer2 = new StringBuffer();
                while (true) {
                    final String line6 = bufferedReader3.readLine();
                    this.strLine = line6;
                    if (line6 == null) {
                        break;
                    }
                    buffer2.append(this.strLine + "\n");
                }
                dataOutputStream10.writeUTF(new String(buffer2));
                JOptionPane.showMessageDialog(null, "File Uploaded Sucessfully");
            }
            catch (Exception x3) {
                System.out.println(x3);
            }
            this.b3.setEnabled(true);
        }
        if (actionEvent.getSource() == this.b5) {
            this.dispose();
        }
    }
    
    public static void main(final String[] array) {
        new Sender();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception x) {
            System.out.println(x);
        }
    }
}