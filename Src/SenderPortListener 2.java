
/* Decompiler 8ms, total 1258ms, lines 55 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class SenderPortListener implements Runnable {
   int port;
   // $FF: synthetic field
   final Sender this$0;

   public SenderPortListener(Sender var1, int var2) {
      this.this$0 = var1;
      this.port = var2;
   }

   public void run() {
      if (this.port == 666) {
         try {
            ServerSocket var1 = new ServerSocket(666);

            while(true) {
               Socket var2 = var1.accept();
               DataInputStream var3 = new DataInputStream(var2.getInputStream());
               String var4 = var3.readUTF();
               String var5 = var3.readUTF();
               String var6 = var3.readUTF();
               String var7 = var3.readUTF();
               String var8 = var3.readUTF();
               FileOutputStream var9 = new FileOutputStream("Senders.dat", true);
               DataOutputStream var10 = new DataOutputStream(var9);
               String var11 = "\n";
               var10.write(var4.getBytes());
               var10.write(var11.getBytes());
               var10.write(var5.getBytes());
               var10.write(var11.getBytes());
               var10.write(var6.getBytes());
               var10.write(var11.getBytes());
               var10.write(var7.getBytes());
               var10.write(var11.getBytes());
               var10.write(var8.getBytes());
               var10.write(var11.getBytes());
               var10.close();
               var9.close();
               DataOutputStream var12 = new DataOutputStream(var2.getOutputStream());
               var12.writeUTF("Success");
            }
         } catch (Exception var13) {
            System.out.println(var13);
         }
      }

   }
}
