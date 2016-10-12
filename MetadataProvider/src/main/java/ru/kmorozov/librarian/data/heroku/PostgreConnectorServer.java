package ru.kmorozov.librarian.data.heroku;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sbt-morozov-kv on 12.10.2016.
 */
public class PostgreConnectorServer {

    public static final int HEROKU_PORT = 2004;

    private ServerSocket providerSocket;
    private Socket connection = null;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message;

    void run() throws IOException {
        try {
            //1. creating a server socket
            providerSocket = new ServerSocket(HEROKU_PORT, 10);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            //3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            sendMessage("Connection successful");
            //4. The two parts communicate via the input and output streams
            do {
                try {
                    message = (String) in.readObject();
                    System.out.println("client>" + message);
                    if (message.equals("bye"))
                        sendMessage("bye");
                } catch (ClassNotFoundException classnot) {
                    System.err.println("Data received in unknown format");
                }
            } while (!message.equals("bye"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            //4: Closing connection
            try {
                in.close();
                out.close();
                providerSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("server>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
