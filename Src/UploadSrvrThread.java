/* Decompiler 18ms, total 1170ms, lines 71 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.Socket;

class UploadSrvrThread extends Thread {
   Socket s;
   DataInputStream din;
   FileOutputStream fos;
   DataOutputStream dos;
   PrintStream ps;
   String fname;
   String sk;
   String content;
   String ka;
   String bata;
   String reg;
   String encData;
   String decData;
   String st = "";

   UploadSrvrThread(Socket var1) {
      this.s = var1;
   }

   public void run() {
      try {
         this.din = new DataInputStream(this.s.getInputStream());
         this.ps = new PrintStream(this.s.getOutputStream());
         this.fname = this.din.readUTF();
         this.sk = this.din.readUTF();
         this.ka = this.din.readUTF();
         this.bata = this.din.readUTF();
         this.reg = this.din.readUTF();
         this.fos = new FileOutputStream(this.fname + "Upload");
         this.dos = new DataOutputStream(this.fos);
         this.dos.write(this.fname.getBytes());
         this.dos.write(this.st.getBytes());
         this.dos.write(this.sk.getBytes());
         this.dos.write(this.st.getBytes());
         this.dos.write(this.ka.getBytes());
         this.dos.write(this.st.getBytes());
         this.dos.write(this.bata.getBytes());
         this.dos.write(this.st.getBytes());
         this.dos.write(this.reg.getBytes());
         this.dos.write(this.st.getBytes());
         this.dos.close();
         this.fos.close();
         this.fos = new FileOutputStream(this.fname + "Encrypt");
         this.dos = new DataOutputStream(this.fos);
         this.encData = this.din.readUTF();
         this.dos.write(this.encData.getBytes());
         this.dos.close();
         this.fos.close();
         this.fos = new FileOutputStream(this.fname + "Decrypt");
         this.dos = new DataOutputStream(this.fos);
         this.decData = this.din.readUTF();
         this.dos.write(this.decData.getBytes());
         this.dos.close();
         this.fos.close();
         this.din.close();
         this.dos.close();
         this.fos.close();
         this.s.close();
      } catch (Exception var2) {
      }

   }
}
