import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NewClientHandler implements Runnable{
    private Socket server ;
    private BufferedReader in;
    private PrintWriter out;

    boolean PasswordFound = false;
    public NewClientHandler(Socket server) throws IOException {
        this.server = server ;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out =  new PrintWriter(server.getOutputStream(),true);


    }

    public void run(){
//        String serverResponse = null ;
//
//        try{
//            int x = 5;
////            while(true) {
////                serverResponse = in.readLine();
////                if(serverResponse == null){
////                    break;
////                }
////
////
////                System.out.println("Server says :" + serverResponse);
////
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        finally{
//            try {
//                in.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

    }
}
