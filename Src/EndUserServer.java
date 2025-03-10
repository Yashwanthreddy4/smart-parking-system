import java.net.*;
import java.io.*;
class EndUserSrvrThread extends Thread
{
Socket s;
DataInputStream din,dis;
FileInputStream fis;
File f;
PrintStream ps;
String fname,key;
EndUserSrvrThread(Socket x)
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
f = new File(fname + "Key");
if(f.exists( ))
{
fis = new FileInputStream(f);
dis = new DataInputStream(fis);
key = dis.readLine( );
ps.println(key);
}
else
{
ps.println("Invalid Key File Name");
}
din.close( );
dis.close( );
fis.close( );
s.close( );
}
catch(Exception e)
{
}
}      
};
class EndUserServer
{
public static void main(String args[]) throws Exception
{
ServerSocket ss = new ServerSocket(111);
EndUserSrvrThread st;
Socket skt;
while(true)
{
skt = ss.accept( );
st = new EndUserSrvrThread(skt);
st.start( );
}
}
}