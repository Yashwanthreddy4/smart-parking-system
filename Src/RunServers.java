import java.io.IOException;
class RunServers
{
    public static void main(String args[ ]) throws IOException
    {
        Runtime r = Runtime.getRuntime( );
        Process p1 = r.exec("java RegisterServer");
        Process p2 = r.exec("java SenderServer");
        Process p3 = r.exec("java KeysServer");
        Process p4 = r.exec("java UsersServer");
        Process p5 = r.exec("java BetRegServer");
        Process p6 = r.exec("java PrivilagesServer");
        Process p7 = r.exec("java UploadServer");
        Process p8 = r.exec("java LoginServer");
        Process p9 = r.exec("java EndUserServer");
        Process p10 = r.exec("java ViewUsersServer");
        Process p11 = r.exec("java ViewPrivilagesServer");
        Process p12 = r.exec("java ViewKeysServer");
    }
}