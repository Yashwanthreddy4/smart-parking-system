/* Decompiler 3ms, total 2413ms, lines 34 */
import java.io.File;
import java.io.PrintStream;
import java.net.Socket;

class ReceiverSrvrThread extends Thread {
   Socket s;
   PrintStream ps;
   String[] fl;

   ReceiverSrvrThread(Socket var1) {
      this.s = var1;
   }

   public void run() {
      try {
         this.ps = new PrintStream(this.s.getOutputStream());
         File var1 = new File("C:\\C# Lab");
         String[] var2 = var1.list();
         String[] var3 = var2;
         int var4 = var2.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            this.ps.println(var6);
         }

         this.ps.println("end");
         this.s.close();
      } catch (Exception var7) {
      }

   }
}
