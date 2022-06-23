import java.io.IOException;
import java.nio.charset.*;
import java.security.*;

public class Funktionen{
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

    public static boolean vergleichen(String originakHash, String zuVergleichenderHash){
        if (originakHash.equals(txtZuHash(zuVergleichenderHash)))
            return true;
        return false;
    }
}