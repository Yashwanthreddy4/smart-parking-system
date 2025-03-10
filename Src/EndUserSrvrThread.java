import java.io.InputStream;
import java.io.PrintStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.36
// 

class EndUserSrvrThread extends Thread
{
    Socket s;
    DataInputStream din;
    DataInputStream dis;
    FileInputStream fis;
    File f;
    PrintStream ps;
    String fname;
    String key;
    
    EndUserSrvrThread(final Socket s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        try {
            this.din = new DataInputStream(this.s.getInputStream());
            this.ps = new PrintStream(this.s.getOutputStream());
            this.fname = this.din.readUTF();
            this.f = new File(this.fname + "Key");
            if (this.f.exists()) {
                this.fis = new FileInputStream(this.f);
                this.dis = new DataInputStream(this.fis);
                this.key = this.dis.readLine();
                this.ps.println(this.key);
            }
            else {
                this.ps.println("Invalid Key File Name");
            }
            this.din.close();
            this.dis.close();
            this.fis.close();
            this.s.close();
        }
        catch (Exception ex) {}
    }
}