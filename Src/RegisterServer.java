import java.net.*;
import java.io.*;
class RegSrvrThread extends Thread
{
    Socket s;
    DataInputStream din;
    FileOutputStream fos;
    DataOutputStream dos;
    String uname,pwd,bata,reg,st = "\n";
    RegSrvrThread(Socket x)
    {
        s = x;
    }
    public void run( )
    {
        try
        {                                     
            din = new DataInputStream(s.getInputStream());
            fos = new FileOutputStream("Users.dat",true);
            dos = new DataOutputStream(fos);
            uname = din.readUTF();
            pwd = din.readUTF();
            bata = din.readUTF();
            reg = din.readUTF();
            dos.write(uname.getBytes( ));
            dos.write(st.getBytes( ));
            dos.write(pwd.getBytes( ));
            dos.write(st.getBytes( ));
            dos.write(bata.getBytes( ));
            dos.write(st.getBytes( ));
            dos.write(reg.getBytes( ));
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
class RegisterServer
{
    public static void main(String args[ ] ) throws Exception
    {
        ServerSocket ss = new ServerSocket(333);
        RegSrvrThread st;
        Socket skt;
        while(true)
        {
            skt = ss.accept( );
            st = new RegSrvrThread(skt);
            st.start( );
        }
    }
}