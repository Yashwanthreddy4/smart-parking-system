import java.net.*;
import java.io.*;
import java.util.*;
class ViewPrivilagesSrvrThread extends Thread
{
    Socket s;
    DataInputStream dis;
    FileInputStream fis;
    PrintStream ps;
    String uname,bata,reg,dow;
    ViewPrivilagesSrvrThread(Socket x)
    {
        s = x;
    }
    public void run( )
    {
        try
        { 
            fis = new FileInputStream("Privilages.dat");
            dis = new DataInputStream(fis);
            ps = new PrintStream(s.getOutputStream( ));
            while(true)
            {
                uname = dis.readLine( );
                if(uname == null)
                {
                    break;
                }
                bata = dis.readLine( );
                reg = dis.readLine( );
                dow = dis.readLine( );
                ps.println(uname);
                ps.println(bata);
                ps.println(reg);
                ps.println(dow);
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
class ViewPrivilagesServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(600);
        ViewPrivilagesSrvrThread st;
        Socket skt;
        while(true)
        {
            skt = ss.accept( );
            st = new ViewPrivilagesSrvrThread(skt);
            st.start( );
        }
    }
}