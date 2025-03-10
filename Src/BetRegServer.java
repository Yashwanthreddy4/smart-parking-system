import java.net.*;
import java.io.*;
import java.util.*;
class BetRegSrvrThread extends Thread
{
Socket s;
DataInputStream dis,dis1;
FileInputStream fis;
PrintStream ps;
String uname,pwd,bata,reg,suname;
BetRegSrvrThread(Socket x)
{
s = x;
}
public void run( )
{
try
{
ps = new PrintStream(s.getOutputStream( ));
dis1 = new DataInputStream(s.getInputStream( ));
suname = dis1.readUTF( );
fis = new FileInputStream("Users.dat");
dis = new DataInputStream(fis);
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
	if(uname.equals(suname))
	{
	ps.println(bata);
	ps.println(reg);
	break;
	}
}
dis1.close( );
dis.close( );
fis.close( );
s.close( );
}
catch(Exception e)
{
}
}      
};
class BetRegServer
{
public static void main(String args[]) throws Exception
{
ServerSocket ss = new ServerSocket(200);
BetRegSrvrThread st;
Socket skt;
while(true)
{
skt = ss.accept( );
st = new BetRegSrvrThread(skt);
st.start( );
}
}
}