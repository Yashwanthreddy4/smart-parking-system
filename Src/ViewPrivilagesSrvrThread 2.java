/* Decompiler 3ms, total 1165ms, lines 49 */
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.Socket;

class ViewPrivilagesSrvrThread extends Thread {
   Socket s;
   DataInputStream dis;
   FileInputStream fis;
   PrintStream ps;
   String uname;
   String bata;
   String reg;
   String dow;

   ViewPrivilagesSrvrThread(Socket var1) {
      this.s = var1;
   }

   public void run() {
      try {
         this.fis = new FileInputStream("Privilages.dat");
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

            this.bata = this.dis.readLine();
            this.reg = this.dis.readLine();
            this.dow = this.dis.readLine();
            this.ps.println(this.uname);
            this.ps.println(this.bata);
            this.ps.println(this.reg);
            this.ps.println(this.dow);
         }
      } catch (Exception var2) {
      }

   }
}
