import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.*;
import java.security.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class Funktionen{
    public static int zaehler = 1;
    public static ArrayList<String> passwoerter = new ArrayList<String>();
    public static String txtZuHash(String text){
        try{
            MessageDigest msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(text.getBytes(StandardCharsets.UTF_8));

            StringBuilder builder = new StringBuilder();
            for (byte b : hash) {
                builder.append(String.format("%02x", b));
            }
            String txtHash = builder.toString();
            return txtHash;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static String vergleiche(String pfad, String originalHash){
        String temp;
        String ergebnis = "";
        try{
            BufferedReader leser = new BufferedReader(new FileReader(pfad));
            while ((temp = leser.readLine()) != null){
                if (originalHash.equals(txtZuHash(temp))){
                    ergebnis = temp;
                    break;
                }
                zaehler++;
            }
            leser.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return ergebnis;
    }

}
