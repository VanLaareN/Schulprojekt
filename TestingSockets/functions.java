import java.net.*;
import java.io.*;
import java.util.Scanner;

public class functions{
    public Socket		       socket =    null;
    public ServerSocket        server =    null;
    public DataInputStream     in =        null;
    public DataOutputStream    out =       null;
    public Scanner             scanner =   null;
    public FileOutputStream    fileOut=    null;
    
    //Make input and output streams (output sends to socket / input recives from socket)
    public void sendMessage(String msg){
        try{
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(msg);
        }
        catch (Exception failedToSendMessage){
            System.out.println("Failed to send message");
        }
    }

    public String reciveMessage(){
        String message = "";

        try{
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        message = in.readUTF();
        }
        catch(Exception failedToReciveMessage){
            System.out.println("Failed to recive message");
        }
        return message;
    }

    public void sendFile(String path){
        //send file:
        String[] splited = path.split("/");
        String fileName = splited[splited.length-1];
        sendMessage(fileName);

        //prepare file and variables
        try{
        File file = new File(path);
        long length = file.length();
        byte[] bytes = new byte[4096];

        //read in file and send them to socket
        out = new DataOutputStream(socket.getOutputStream());
        InputStream inFile = new FileInputStream(file);

        int count;
        while ((count = inFile.read(bytes)) > 0){
            out.write(bytes, 0, count);
        }
        }
        catch (IOException failed){
            System.out.println("Failed to send file");
        }
    }

    public void reciveFile(){
        try{
        //Recive name of the file
        String fileName = reciveMessage();

        //Recive file from client
        fileOut = new FileOutputStream("/home/laaren/Videos/"+fileName);
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        int count = 0;
        byte[] bytes = new byte[4089];
        while((count = in.read(bytes)) > 0){
            fileOut.write(bytes, 0, count);
        }

        }
        catch (IOException failed){
            System.out.println("FAILED TO RECIVE FILE");
        }
    }
}