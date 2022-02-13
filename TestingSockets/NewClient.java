import java.net.*;
import java.io.*;
import java.util.Scanner;

public class NewClient{
    functions handler = new functions();

    private void startClient(String ip, int port){
        try{
            //connect to socket with ip and port
            handler.socket = new Socket(ip, port);
            System.out.println("Connected to "+ handler.socket.getInetAddress());

            handler.sendMessage("Message from client");
            System.out.println(handler.reciveMessage());
            handler.sendFile("/home/laaren/Pictures/hi.txt");
            
        }
        catch(IOException startCLientException){
            System.out.println(startCLientException);
        }

        try
		{
			handler.out.close();
			handler.socket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}

    }

    public static void main(String[] args) {
        NewClient client = new NewClient();

        client.startClient("127.0.0.1", 9900);
    }

}