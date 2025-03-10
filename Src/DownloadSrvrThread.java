import java.io.InputStream;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.36
// 

class DownloadSrvrThread extends Thread
{
    Socket s;
    DataInputStream dis;
    FileInputStream fis;
    PrintStream ps;
    String uname;
    String pwd;
    String bata;
    String reg;
    
    DownloadSrvrThread(final Socket s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        try {
            this.fis = new FileInputStream("Users.dat");
            this.dis = new DataInputStream(this.fis);
            this.ps = new PrintStream(this.s.getOutputStream());
            while (true) {
                this.uname = this.dis.readLine();
                if (this.uname == null) {
                    break;
                }
                this.ps.println(this.uname);
                this.pwd = this.dis.readLine();
                this.bata = this.dis.readLine();
                this.reg = this.dis.readLine();
            }
            this.ps.println("end");
            this.dis.close();
            this.fis.close();
            this.s.close();
        }
        catch (Exception ex) {}
    }
}