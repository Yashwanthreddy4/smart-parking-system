/* Decompiler 3ms, total 1249ms, lines 49 */
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.Socket;

class ViewUsersSrvrThread extends Thread {
   Socket s;
   DataInputStream dis;
   FileInputStream fis;
   PrintStream ps;
   String uname;
   String pwd;
   String bata;
   String reg;

   ViewUsersSrvrThread(Socket var1) {
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
            this.ps.println(this.pwd);
            this.ps.println(this.bata);
            this.ps.println(this.reg);
         }
      } catch (Exception var2) {
      }

   }
}
