/* Decompiler 12ms, total 1189ms, lines 46 */
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.Socket;

class UsersSrvrThread extends Thread {
   Socket s;
   DataInputStream dis;
   FileInputStream fis;
   PrintStream ps;
   String uname;
   String pwd;
   String bata;
   String reg;

   UsersSrvrThread(Socket var1) {
      this.s = var1;
   }

   public void run() {
      try {
         this.fis = new FileInputStream("Users.dat");
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

            this.pwd = this.dis.readLine();
            this.bata = this.dis.readLine();
            this.reg = this.dis.readLine();
            this.ps.println(this.uname);
         }
      } catch (Exception var2) {
      }

   }
}
