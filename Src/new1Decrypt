import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64.Encoder;
import java.util.Base64;
import java.util.Base64.*;

public class AES
{
  public String encrypt(String paramString1, String paramString2)
    throws IOException
  {
    String str1 = new String();
    String str2 = new String();
    String str3 = new String();
    try
    {
      KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
      localKeyGenerator.init(128);
      SecretKey localSecretKey = localKeyGenerator.generateKey();
      
      byte[] arrayOfByte1 = new byte[16];
      SecureRandom localSecureRandom = new SecureRandom();
      localSecureRandom.nextBytes(arrayOfByte1);
      Cipher localCipher1 = Cipher.getInstance("AES/CTR/NoPadding");
      localCipher1.init(1, localSecretKey, new IvParameterSpec(arrayOfByte1));
      str1 = paramString1;
      byte[] arrayOfByte2 = str1.getBytes();
      byte[] arrayOfByte3 = localCipher1.doFinal(arrayOfByte2);
      str2 = Base64.getEncoder().encodeToString(arrayOfByte3);
      //str2 = new BASE64Encoder().encode(arrayOfByte3);
      FileOutputStream localFileOutputStream = new FileOutputStream(paramString2 + "Encrypt");
      DataOutputStream localDataOutputStream = new DataOutputStream(localFileOutputStream);
      localDataOutputStream.write(str2.getBytes());
      localDataOutputStream.close();
      localFileOutputStream.close();
      Cipher localCipher2 = Cipher.getInstance("AES/CTR/NoPadding");
      localCipher2.init(2, localSecretKey, new IvParameterSpec(arrayOfByte1));
      byte[] arrayOfByte4 = localCipher2.doFinal(arrayOfByte3);
      str3 = new String(arrayOfByte4);
      localFileOutputStream = new FileOutputStream(paramString2 + "Decrypt");
      localDataOutputStream = new DataOutputStream(localFileOutputStream);
      localDataOutputStream.write(str3.getBytes());
      localDataOutputStream.close();
      localFileOutputStream.close();
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      System.out.println("No Such Algorithm exists " + localNoSuchAlgorithmException);
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      System.out.println("No Such Padding exists " + localNoSuchPaddingException);
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      System.out.println("Invalid Key " + localInvalidKeyException);
    }
    catch (BadPaddingException localBadPaddingException)
    {
      System.out.println("Bad Padding " + localBadPaddingException);
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      System.out.println("Illegal Block Size " + localIllegalBlockSizeException);
    }
    catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException)
    {
      System.out.println("Invalid Parameter " + localInvalidAlgorithmParameterException);
    }
    return str2;
  }
  
  public String decrypt(String paramString)
  {
    String str1 = "";String str2 = "";
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramString + "Decrypt");
      DataInputStream localDataInputStream = new DataInputStream(localFileInputStream);
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localDataInputStream));
      StringBuffer localStringBuffer = new StringBuffer();
      while ((str2 = localBufferedReader.readLine()) != null) {
        localStringBuffer.append(str2 + "\n");
      }
      str1 = new String(localStringBuffer);
    }
    catch (Exception localException) {}
    return str1;
  }
}
