/* Decompiler 6ms, total 1201ms, lines 43 */
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.Socket;

class ViewKeysSrvrThread extends Thread {
   Socket s;
   DataInputStream dis;
   FileInputStream fis;
   PrintStream ps;
   String uname;
   String key;

   ViewKeysSrvrThread(Socket var1) {
      this.s = var1;
   }

   public void run() {
      try {
         this.fis = new FileInputStream("Keys.dat");
         this.dis = new DataInputStream(this.fis);
         this.ps = new PrintStream(this.s.getOutputStream());

         while(true) {
            this.uname = this.dis.readLine();
            if (this.uname == null) {
               this.ps.println("end");
               this.dis.close();
               this.fis.close();
               this.s.close();
               break;
            }

            this.key = this.dis.readLine();
            this.ps.println(this.uname);
            this.ps.println(this.key);
         }
      } catch (Exception var2) {
      }

   }
}
