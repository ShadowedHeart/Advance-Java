import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server. Type messages to send:");

            String usermessage;

            while ((usermessage = consoleInput.readLine()) != null) {
                output.println(usermessage);
                System.out.println("server" + input.readLine());
            }
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
