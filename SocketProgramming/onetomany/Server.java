import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final Map<Integer, ClientHandler> clients = Collections.synchronizedMap(new HashMap<>());
    private static int clientIdCounter = 1;

    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Server is listening on port " + port);

            Thread serverInputThread = new Thread(() -> {
                while (true) {
                    System.out.println("Enter Client ID and Message (1 message): ");
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ", 2);
                    if (parts.length < 2) continue;

                    try {
                        int clientId = Integer.parseInt(parts[0]);
                        String message = parts[1];
                        sendMessageToClient(clientId, "Server: " + message);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid client ID format.");
                    }
                }
            });
            serverInputThread.start();

            while (true) {
                Socket socket = serverSocket.accept();
                int clientId = clientIdCounter++;
                System.out.println("Client " + clientId + " connected");
                ClientHandler clientHandler = new ClientHandler(socket, clientId);
                clients.put(clientId, clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessageToClient(int clientId, String message) {
        synchronized (clients) {
            ClientHandler client = clients.get(clientId);
            if (client != null) {
                client.sendMessage(message);
            } else {
                System.out.println("Client " + clientId + " not found.");
            }
        }
    }

    public static void broadcastMessage(String message, int senderId) {
        synchronized (clients) {
            for (Map.Entry<Integer, ClientHandler> entry : clients.entrySet()) {
                if (entry.getKey() != senderId) {
                    entry.getValue().sendMessage(message);
                }
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private int clientId;

    public ClientHandler(Socket socket, int clientId) {
        this.socket = socket;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Connected as Client " + clientId);

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Client " + clientId + ": " + message);
                Server.broadcastMessage("Client " + clientId + ": " + message, clientId);
            }
        } catch (IOException e) {
            System.out.println("Client " + clientId + " disconnected");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        if (output != null) {
            output.println(message);
        }
    }
}

