import java.net.*;
import java.io.*;
import java.util.Scanner;

public class NewServer
{
    functions handler = new functions();
    private void startServer(int port){
        
        try{
        //Start server socket
        handler.server = new ServerSocket(port);
        System.out.println("Starting Server...");
        System.out.println("Waiting for Clients...");
        
        //Accept connections
        handler.socket = handler.server.accept();
        System.out.println("Client accepted from IP: "+ handler.socket.getInetAddress());
        
        System.out.println(handler.reciveMessage());
        handler.sendMessage("First message");
        handler.reciveFile();
        
        //Close objects
        handler.socket.close();
		handler.in.close();
        }
        catch (IOException serverStartException){
            System.out.println(serverStartException);
        }

    }

    public static void main(String[] args) {
        NewServer server = new NewServer();
        server.startServer(9900);
    }
}