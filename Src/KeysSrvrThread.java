import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.36
// 

class KeysSrvrThread extends Thread
{
    Socket s;
    DataInputStream din;
    FileOutputStream fos;
    DataOutputStream dos;
    String uname;
    String key;
    String st;
    
    KeysSrvrThread(final Socket s) {
        this.st = "\n";
        this.s = s;
    }
    
    @Override
    public void run() {
        try {
            this.din = new DataInputStream(this.s.getInputStream());
            this.fos = new FileOutputStream("Keys.dat", true);
            this.dos = new DataOutputStream(this.fos);
            this.uname = this.din.readUTF();
            this.key = this.din.readUTF();
            this.dos.write(this.uname.getBytes());
            this.dos.write(this.st.getBytes());
            this.dos.write(this.key.getBytes());
            this.dos.write(this.st.getBytes());
            this.din.close();
            this.dos.close();
            this.fos.close();
            this.s.close();
        }
        catch (Exception ex) {}
    }
}