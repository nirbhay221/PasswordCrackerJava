import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;

public class workerConnection implements Runnable{
    private Socket server ;
    private BufferedReader in;
    private PrintWriter out;
    int ChoosenClients;

    int NumberOfClientsActive;
    String Password ;
    int counter = 0 ;
    public ArrayList<Character> WordList;
    boolean PasswordFound = false;
    public workerConnection(Socket server) throws IOException {
        this.server = server ;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out =  new PrintWriter(server.getOutputStream(),true);
        WordList = new ArrayList<Character>();

    }

    public void run(){
        String serverResponse = null ;

        try{
        inner:    while(true) {
                System.out.println("Waiting For Hashing Process to start :");
                serverResponse = in.readLine();
             outer:   if(serverResponse.contains("quit")){
                    continue inner;

                }
                else{


                }
                if(serverResponse.contains("Hash")){
                    int first = serverResponse.indexOf(' ',31);
                    String Hash = serverResponse.substring(0,first);
                    System.out.println("Hash "+Hash);

                    int ClientNumber = Integer.parseInt(serverResponse.substring(first+1,first+2));
                    int NumberOfClients = Integer.parseInt(serverResponse.substring(first+3,first+4));
                    System.out.println("Hash : "+Hash+" Client Number : "+ClientNumber+" , Number of Clients : "+NumberOfClients);
                    System.out.println("Generating the Password from the Hash :" );
                    System.out.println("************BEEP**********BOOP******************Beep***********************Boop******************");

                    char[] AllCharactersNeeded = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                    for(int f = 0 ; f<AllCharactersNeeded.length ; f++){
                            WordList.add(AllCharactersNeeded[f]);
                    }
                    char [] PasswordFromHash ={'-', '-','-', '-','-'} ;
                    OUTER:for(int i = 0 ; i < WordList.size() ; i+=NumberOfClients  ){

                        PasswordFromHash[0] = WordList.get(ClientNumber-1+i);
                        String Status = "BUSY";
                        for(int j = 0 ; j < WordList.size();j++){
                            PasswordFromHash[1] = WordList.get(j);

                            for(int k = 0 ; k < WordList.size();k++){
                                PasswordFromHash[2] = WordList.get(k);

                                for(int l = 0 ; l < WordList.size();l++){
                                    PasswordFromHash[3]= WordList.get(l);

                                    for(int m = 0 ; m < WordList.size();m++){
                                        PasswordFromHash[4] =WordList.get(m);

                                        String PasswordForHash = toString(PasswordFromHash);
                                        System.out.println(PasswordForHash);
                                        String HashGeneratednew =  getHashValue(PasswordForHash);

                                        if((Hash).equals(HashGeneratednew)){

                                            System.out.println("We found the password you were looking for : ");
                                            System.out.println("Password Generated from Hash is "+PasswordForHash);
                                            out.println(PasswordForHash+" Password ");
                                            out.flush();
                                            PasswordFound = true;
                                            for(int n = 0; n < WordList.size(); n++){
                                                WordList.remove(n);
                                            }
                                            continue inner;

                                        }




                                    }
                                }
//                                if(true) {
//                                    continue inner;
//
//                                }

                            }
                        }
                    }


                }




                if(serverResponse == null){
                    break;
                }

                counter++;
                System.out.println("Server says :" + serverResponse);

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    static boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
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
