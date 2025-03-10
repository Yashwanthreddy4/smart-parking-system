/* Decompiler 1ms, total 1334ms, lines 15 */
import java.net.ServerSocket;
import java.net.Socket;

class ReceiverServer {
   public static void main(String[] var0) throws Exception {
      ServerSocket var1 = new ServerSocket(222);

      while(true) {
         Socket var3 = var1.accept();
         ReceiverSrvrThread var2 = new ReceiverSrvrThread(var3);
         var2.start();
      }
   }
}
