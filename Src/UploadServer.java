import java.net.*;
import java.io.*;
import java.util.*;
class UploadSrvrThread extends Thread
{
    Socket s;
    DataInputStream din;
    FileOutputStream fos;
    DataOutputStream dos;
    PrintStream ps;
    String fname,sk,content,ka,bata,reg,encData,decData,st = "";
    UploadSrvrThread(Socket x)
    {
        s = x;
    }
    public void run( )
    {
        try
        { 
            din = new DataInputStream(s.getInputStream());
            ps = new PrintStream(s.getOutputStream());
            fname = din.readUTF();
            sk = din.readUTF();
            ka = din.readUTF();
            bata = din.readUTF();
            reg = din.readUTF();
            fos = new FileOutputStream(fname + "Upload");
            dos = new DataOutputStream(fos);
            dos.write(fname.getBytes( ));
            dos.write(st.getBytes( ));
            dos.write(sk.getBytes( ));
            dos.write(st.getBytes( ));
            dos.write(ka.getBytes( ));
            dos.write(st.getBytes( ));
            dos.write(bata.getBytes( ));
            dos.write(st.getBytes( ));
            dos.write(reg.getBytes( ));
            dos.write(st.getBytes( ));
            dos.close( );
            fos.close( );
            fos = new FileOutputStream(fname + "Encrypt");
            dos = new DataOutputStream(fos);
            encData = din.readUTF();
            dos.write(encData.getBytes( ));
            dos.close( );
            fos.close( );
            fos = new FileOutputStream(fname + "Decrypt");
            dos = new DataOutputStream(fos);
            decData = din.readUTF();
            dos.write(decData.getBytes( ));
            dos.close( );
            fos.close( );
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


class UploadServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(999);
        UploadSrvrThread st;
        Socket skt;
        while(true)
        {
            skt = ss.accept( );
            st = new UploadSrvrThread(skt);
            st.start( );
        }
    }
}