import java.net.*;
import java.io.*;
import java.util.*;
class UsersSrvrThread extends Thread
{
    Socket s;
    DataInputStream dis;
    FileInputStream fis;
    PrintStream ps;
    String uname,pwd,bata,reg;
    UsersSrvrThread(Socket x)
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
class UsersServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(777);
        UsersSrvrThread st;
        Socket skt;
        while(true)
        {
            skt = ss.accept( );
            st = new UsersSrvrThread(skt);
            st.start( );
        }
    }
}