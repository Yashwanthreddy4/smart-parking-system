import java.net.*;
import java.io.*;
import java.util.*;
class ViewUsersSrvrThread extends Thread
{
    Socket s;
    DataInputStream dis;
    FileInputStream fis;
    PrintStream ps;
    String uname,pwd,bata,reg;
    ViewUsersSrvrThread(Socket x)
    {
        s = x;
    }
    public void run( )
    {
        try
        { 
        fis = new FileInputStream("Users.dat");
        dis = new DataInputStream(fis);
        ps = new PrintStream(s.getOutputStream( ));
        while(true)
        {
            uname = dis.readLine( );
            if(uname == null)
            {
                break;
            }
            pwd = dis.readLine( );
            bata = dis.readLine( );
            reg = dis.readLine( );
            ps.println(uname);
            ps.println(pwd);
            ps.println(bata);
            ps.println(reg);
        }
        ps.println("end");
        dis.close( );
        fis.close( );
        s.close( );
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }      
};
class ViewUsersServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(400);
        ViewUsersSrvrThread st;
        Socket skt;
        while(true)
        {
            skt = ss.accept( );
            st = new ViewUsersSrvrThread(skt);
            st.start( );
        }
    }
}