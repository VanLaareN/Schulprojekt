import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.*;
import java.security.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Funktionen{
    //Konstanten
    private static String BuchstabenKonstante= "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static int zaehler;
    public static String passwort;

    public static String txtZuHash(String text){
        try{
            MessageDigest msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(text.getBytes(StandardCharsets.UTF_8));

            StringBuilder builder = new StringBuilder();
            for (byte b : hash) {
                builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            String txtHash = builder.toString();
            return txtHash;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static boolean vergleichen(String originalHash, String zuVergleichenderHash){
        if (originalHash.equals(txtZuHash(zuVergleichenderHash)))
            return true;
        return false;
    }

    public static String bruteForce(String originalHash, int passwortLaenge){
        char[] buchstabenArray = BuchstabenKonstante.toCharArray();
        String builderLaenge = " ".repeat(passwortLaenge);
        StringBuilder builder = new StringBuilder(builderLaenge);

        int maxLaenge = (int) Math.pow(BuchstabenKonstante.length(), passwortLaenge);
        int[] position = new int[passwortLaenge];

        for (int alleDurchgaenge = 0; alleDurchgaenge < maxLaenge; alleDurchgaenge++){
            for(int x = 0; x < passwortLaenge; x++){
                //Reset
                if(position[x] == buchstabenArray.length){
                    position[x] = 0;
                    position[x+1]++;
                }
                builder.setCharAt(x, buchstabenArray[position[x]]);
            }
            position[0]++;
            zaehler++;
            //System.out.println(builder.toString());
            if(vergleichen(originalHash, builder.toString()) == true){
                passwort = builder.toString();
                return null;
            }
        }
        bruteForce(originalHash, passwortLaenge+1);
        return null;
    }

    
}