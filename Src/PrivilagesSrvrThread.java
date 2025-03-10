import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.36
// 

class PrivilagesSrvrThread extends Thread
{
    Socket s;
    DataInputStream din;
    FileOutputStream fos;
    DataOutputStream dos;
    String uname;
    String bata;
    String reg;
    String down;
    String st;
    
    PrivilagesSrvrThread(final Socket s) {
        this.st = "\n";
        this.s = s;
    }
    
    @Override
    public void run() {
        try {
            this.din = new DataInputStream(this.s.getInputStream());
            this.fos = new FileOutputStream("Privilages.dat", true);
            this.dos = new DataOutputStream(this.fos);
            this.uname = this.din.readUTF();
            this.bata = this.din.readUTF();
            this.reg = this.din.readUTF();
            this.down = this.din.readUTF();
            this.dos.write(this.uname.getBytes());
            this.dos.write(this.st.getBytes());
            this.dos.write(this.bata.getBytes());
            this.dos.write(this.st.getBytes());
            this.dos.write(this.reg.getBytes());
            this.dos.write(this.st.getBytes());
            this.dos.write(this.down.getBytes());
            this.dos.write(this.st.getBytes());
            this.din.close();
            this.dos.close();
            this.fos.close();
            this.s.close();
        }
        catch (Exception ex) {}
    }
}