import java.net.*;
import java.io.*;
import java.util.*;
class LoginSrvrThread extends Thread
{
    Socket s;
    BufferedReader reader;
    DataInputStream dis;
    DataOutputStream dout;
    FileInputStream fis;
    PrintStream ps;
    String uname,pwd,bata,reg;
    LoginSrvrThread(Socket x)
    {
        s = x;
    }
    @Override
    public void run( )
    {
        try
        { 
            fis = new FileInputStream("Users.dat");
            dis = new DataInputStream(fis);
            reader = new BufferedReader(new InputStreamReader(dis));
            dout = new DataOutputStream(s.getOutputStream());
            
            ps = new PrintStream(s.getOutputStream( ));
            while(true)
            {
                uname = reader.readLine( );
                if(uname == null)
                {
                    break;
                }
                pwd = reader.readLine( );
                bata = reader.readLine( );
                reg = reader.readLine( );
                ps.println(uname);
                ps.println(pwd);
            }
            ps.println("end");
            dis.close( );
            fis.close( );
            s.close( );
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }      
};
class LoginServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(300);
        LoginSrvrThread st;
        Socket skt;
        while(true)
        {
            skt = ss.accept( );
            st = new LoginSrvrThread(skt);
            st.start( );
        }
    }
}