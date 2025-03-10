import java.net.*;
import java.io.*;
class KeysSrvrThread extends Thread
{
    Socket s;
    DataInputStream din;
    FileOutputStream fos;
    DataOutputStream dos;
    String uname,key,st = "\n";
    KeysSrvrThread(Socket x)
    {
        s = x;
    }
    public void run( )
    {
        try
        {                                     
            din = new DataInputStream(s.getInputStream());
            fos = new FileOutputStream("Keys.dat",true);
            dos = new DataOutputStream(fos);
            uname = din.readUTF();
            key = din.readUTF();
            dos.write(uname.getBytes( ));
            dos.write(st.getBytes( ));
            dos.write(key.getBytes( ));
            dos.write(st.getBytes( ));
            din.close( );
            dos.close( );
            fos.close( );
            s.close( );
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }      
};

class KeysServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(700);
        KeysSrvrThread  st;
        Socket skt;
        while(true)
        {
            skt = ss.accept( );
            st = new KeysSrvrThread(skt);
            st.start( );
        }
    }
}