import java.io.InputStream;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.36
// 

class BetRegSrvrThread extends Thread
{
    Socket s;
    DataInputStream dis;
    DataInputStream dis1;
    FileInputStream fis;
    PrintStream ps;
    String uname;
    String pwd;
    String bata;
    String reg;
    String suname;
    
    BetRegSrvrThread(final Socket s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        try {
            this.ps = new PrintStream(this.s.getOutputStream());
            this.dis1 = new DataInputStream(this.s.getInputStream());
            this.suname = this.dis1.readUTF();
            this.fis = new FileInputStream("Users.dat");
            this.dis = new DataInputStream(this.fis);
            while (true) {
                do {
                    this.uname = this.dis.readLine();
                    if (this.uname == null) {
                        this.dis1.close();
                        this.dis.close();
                        this.fis.close();
                        this.s.close();
                        return;
                    }
                    this.pwd = this.dis.readLine();
                    this.bata = this.dis.readLine();
                    this.reg = this.dis.readLine();
                } while (!this.uname.equals(this.suname));
                this.ps.println(this.bata);
                this.ps.println(this.reg);
                continue;
            }
        }
        catch (Exception ex) {}
    }
}