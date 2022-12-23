import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.Scanner;

public class apiClient {
    private static final String IP = "127.0.0.1";
    private static final int Port = 9090;



    public static boolean PasswordFound = false;

    public static void main (String[] args) throws IOException{
      Socket socket = new Socket(IP,Port);
        NewClientHandler serverConnection = new NewClientHandler(socket);
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        new Thread(serverConnection).start();

                System.out.println("Choose if you want to connect this user for ");
//            String Mode = in.readLine();
                String data = "Making First Connection";
                String SelectMode;
//                System.out.println("Sending to server:\n" + data);
//                out.println(data);
//
                String line;
////first phase
//                while ((line = in.readLine()) != null) { //Making tcp Connection
//                    System.out.println("TCP Connection Made : Client received: " + line);
//                    break;
//                }
//                System.out.println("TCP Connection Successful now waiting For Checking Password."); // When the server replies the first time we move to First Phase for sending the message
////HASH REQUEST
                //second phase
                System.out.println("Transferring");
                out.println("How many Clients");
                out.flush();
                System.out.println("Transferring");
                String NumberClient;
                int numberOfClients = 1;
                while((line = in.readLine())!=null){
                    numberOfClients =  Integer.parseInt(line);
                    break;
                }
                System.out.println("Total clients : "+numberOfClients);
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Choosen Clients : ");
                int choosenNumberofClients = sc.nextInt();
                 numberOfClients = choosenNumberofClients;
                String password = "BATSY";

                String Hash = getHashValue(password);
                String newHash = Hash +" "+ numberOfClients;
                out.println(newHash);
                out.flush();
                line = in.readLine();
                while(line == null){

                }

                while(line != null){
                while(!line.equals(password) ){

                }
                if(line.equals(password)){
                    break ;
                }

                }

                System.out.println("Password for the Following Hash is : " + line);


            out.println(1);
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
