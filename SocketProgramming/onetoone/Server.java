import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args){
        int port = 5000;

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listenning on port "+port);

            Socket socket = serverSocket.accept();
            System.out.println("client connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Client: "+message);
                output.println("server received : "+message);
            }

            socket.close();
            System.out.println("Connection closed");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
