import java.util.Scanner;

public class Main{
    //Konstanten
    private static String BuchstabenKonstante= "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Funktionen handler = new Funktionen();
    private static Scanner tastaturInput = new Scanner(System.in);
    
    public static void main(String[] args) {

        System.out.println("Bitte geben Sie die Länge des Passworts ein: ");
        int passwortLaenge = tastaturInput.nextInt();

        System.out.println("Bitte geben Sie den Hash vom Passwort ein: ");
        String originakHash = tastaturInput.next();

        double StartZeit = System.nanoTime();

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
            //---------------------------------
            if(handler.vergleichen(originakHash, builder.toString()) == true){
                double endZeit   = System.nanoTime();
                double gesamtZeit = (endZeit - StartZeit)/1000000000d;
                System.out.println("\n\nFertig!\nDas Passwort lautet: "+builder.toString()+"\nHash: "+originakHash+"\nLaufzeit: "+gesamtZeit+"s");
            }
        }


    }
}