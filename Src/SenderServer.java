import java.net.*;
import java.io.*;
import java.util.*;
class SenderSrvrThread extends Thread
{
    Socket s;
    DataInputStream din;
    FileOutputStream fos,fos1;
    DataOutputStream dos,dos1,dos2;
    PrintStream ps;
    static String fname;
    String fname1,ka,key,st = "";
    Random r1,r2;
    int m,rn1,rn2;
    SenderSrvrThread(Socket x,int y)
    {
        s = x;
        m = y;
    }
    public void run( )
    {
        try
        { 
            din = new DataInputStream(s.getInputStream());
            dos1 = new DataOutputStream(s.getOutputStream());
            ps = new PrintStream(s.getOutputStream());
            if(m % 2 == 1)
            {
                fname = din.readUTF();
                r1= new Random();
                r2= new Random();
                rn1 = r1.nextInt(100);
                rn2 = r2.nextInt(100);
                fos1 = new FileOutputStream(fname + "Key");
                dos2 = new DataOutputStream(fos1);
                String k = rn1 + fname + rn2;
                dos2.write(k.getBytes( ));
                dos2.write(st.getBytes( ));
                dos2.close( );
                fos1.close( );
                ps.println(k);
            }
            if(m % 2 == 0)
            {
                fos = new FileOutputStream(fname + "KA");
                dos = new DataOutputStream(fos);
                ka = din.readUTF();
                fname1 = din.readUTF();
                key = din.readUTF( );
                dos.write(ka.getBytes( ));
                dos.write(st.getBytes( ));
                dos.write(fname1.getBytes( ));
                dos.write(st.getBytes( ));
                dos.write(key.getBytes( ));
                dos.write(st.getBytes( ));
                ps.println(ka + "\t" + fname1 + "\t" + key);
                dos.close( );
                fos.close( );
            }
            din.close( );
            dos1.close( );
            s.close( );
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }      
};
class SenderServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(666);
        SenderSrvrThread st;
        Socket skt;
        int n = 0;
        while(true)
        {
            skt = ss.accept( );
            n++;
            st = new SenderSrvrThread(skt,n);
            st.start( );
        }
    }
}