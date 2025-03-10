/* Decompiler 13ms, total 1166ms, lines 47 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;

class RegSrvrThread extends Thread {
   Socket s;
   DataInputStream din;
   FileOutputStream fos;
   DataOutputStream dos;
   String uname;
   String pwd;
   String bata;
   String reg;
   String st = "\n";

   RegSrvrThread(Socket var1) {
      this.s = var1;
   }

   public void run() {
      try {
         this.din = new DataInputStream(this.s.getInputStream());
         this.fos = new FileOutputStream("Users.dat", true);
         this.dos = new DataOutputStream(this.fos);
         this.uname = this.din.readUTF();
         this.pwd = this.din.readUTF();
         this.bata = this.din.readUTF();
         this.reg = this.din.readUTF();
         this.dos.write(this.uname.getBytes());
         this.dos.write(this.st.getBytes());
         this.dos.write(this.pwd.getBytes());
         this.dos.write(this.st.getBytes());
         this.dos.write(this.bata.getBytes());
         this.dos.write(this.st.getBytes());
         this.dos.write(this.reg.getBytes());
         this.dos.write(this.st.getBytes());
         this.din.close();
         this.dos.close();
         this.fos.close();
         this.s.close();
      } catch (Exception var2) {
      }

   }
}
