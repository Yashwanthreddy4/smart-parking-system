import java.net.*;
import java.io.*;
class PrivilagesSrvrThread extends Thread
{
    Socket s;
    DataInputStream din;
    FileOutputStream fos;
    DataOutputStream dos;
    String uname,bata,reg,down,st = "\n";
    PrivilagesSrvrThread(Socket x)
    {
        s = x;
    }
    public void run( )
    {
    try
    {                                     
        din = new DataInputStream(s.getInputStream());
        fos = new FileOutputStream("Privilages.dat",true);
        dos = new DataOutputStream(fos);
        uname = din.readUTF();
        bata = din.readUTF();
        reg = din.readUTF();
        down = din.readUTF();
        dos.write(uname.getBytes( ));
        dos.write(st.getBytes( ));
        dos.write(bata.getBytes( ));
        dos.write(st.getBytes( ));
        dos.write(reg.getBytes( ));
        dos.write(st.getBytes( ));
        dos.write(down.getBytes( ));
        dos.write(st.getBytes( ));
        din.close( );
        dos.close( );
        fos.close( );
        s.close( );
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
    }      
};


class PrivilagesServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(444);
        PrivilagesSrvrThread st;
        Socket skt;
        while(true)
        {
            skt = ss.accept( );
            st = new PrivilagesSrvrThread(skt);
            st.start( );
        }
    }
}