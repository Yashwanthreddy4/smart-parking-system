import java.net.*;
import java.io.*;
import java.util.*;
class ViewKeysSrvrThread extends Thread
{
    Socket s;
    DataInputStream dis;
    FileInputStream fis;
    PrintStream ps;
    String uname,key;
    ViewKeysSrvrThread(Socket x)
    {
        s = x;
    }
    public void run( )
    {
        try
        { 
            fis = new FileInputStream("Keys.dat");
            dis = new DataInputStream(fis);
            ps = new PrintStream(s.getOutputStream( ));
            while(true)
            {
                uname = dis.readLine( );
                if(uname == null)
                {
                    break;
                }
                key = dis.readLine( );
                ps.println(uname);
                ps.println(key);
            }
            ps.println("end");
            dis.close( );
            fis.close( );
            s.close( );
        }
        catch(Exception e)
        {
        }
    }      
};
class ViewKeysServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(500);
        ViewKeysSrvrThread st;
        Socket skt;
        while(true)
        {
            skt = ss.accept( );
            st = new ViewKeysSrvrThread(skt);
            st.start( );
        }
    }
}