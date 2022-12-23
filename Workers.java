import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Workers {
    private static final String IP = "nmalhotr@pc5.instageni.rutgers.edu";
    private static final int Port = 27410;
    public static boolean PasswordFound = false;

    public static void main (String[] args) throws IOException {
        Socket socket = new Socket(IP, Port);
        workerConnection serverConnection = new workerConnection(socket);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader keybooard = new BufferedReader(new InputStreamReader(System.in));
        new Thread(serverConnection).start();

        inner:
        while (true) {
//            System.out.println("-->");
//            String command = keybooard.readLine();
//            if(command.equals("quit")){
//                break;
//
//            }
//            out.println(command);
        }
    }

    public static String getHashValue(String password) {
        String HashGenerated = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger bigInt = new BigInteger(1, messageDigest);
            HashGenerated = bigInt.toString(16);
            if (HashGenerated.length() < 32) {
                HashGenerated = '0' + HashGenerated;
            }
            System.out.println("Hash : " +HashGenerated);
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        return HashGenerated;
    }

    public static String toString(char[] a){
        String string = new String(a);
        return string ;
    }
}
