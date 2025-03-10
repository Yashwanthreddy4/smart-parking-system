import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*; 
import javax.crypto.*;
import java.net.*;


class PortListener implements Runnable
{

/*int[] ports=new int[] {8889};
for(int i=0;i<1;i++)
{
    Thread t=new Thread(new PortListener(ports[i]));
    t.setName("Listener-"+ports[i]);
    t.start();
}	
 */


int port;
public PortListener(int port)
{
this.port=port;
}
public void run()
{

if(this.port == 666)
{
    try
    {

    ServerSocket sst=new ServerSocket(port);
    Socket cn;
        while(true)
        {
        cn=sst.accept();
DataInputStream in3=new DataInputStream(cn.getInputStream());
        String aname=in3.readUTF();
        String fname=in3.readUTF();
        String miss=in3.readUTF();
        String tym=in3.readUTF();
        String partition=in3.readUTF();

FileOutputStream fos = new FileOutputStream("Senders.dat",true);
DataOutputStream  dos = new DataOutputStream(fos);
String st = "\n";
dos.write(aname.getBytes( ));
dos.write(st.getBytes( ));
dos.write(fname.getBytes( ));
dos.write(st.getBytes( ));
dos.write(miss.getBytes( ));
dos.write(st.getBytes( ));
dos.write(tym.getBytes( ));
dos.write(st.getBytes( ));
dos.write(partition.getBytes( ));
dos.write(st.getBytes( ));
dos.close( );
fos.close( );
DataOutputStream dos9=new DataOutputStream(cn.getOutputStream());
dos9.writeUTF("Success");
        }
    }
catch(Exception es)
{
System.out.println(es);
}
}
}
}

class ConnectAnyPortNumber
{
    public static void main(String[] args) throws Exception
    {
        new PortListener(666);
    }
}