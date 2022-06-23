import java.io.IOException;
import java.util.Scanner;

public class Main{
    //Konstanten
    private static Funktionen handler = new Funktionen();
    private static Scanner tastaturInput = new Scanner(System.in);
    private static String PFAD = "/home/laaren/Projects/Schule/Final/";
    
    public static void main(String[] args) {

        System.out.println("Bitte geben Sie den Hash vom Passwort ein: ");
        String originalHash = tastaturInput.next();

        double StartZeit = System.nanoTime();

        String bruteForce = handler.bruteForce(originalHash, 1);

        double endZeit   = System.nanoTime();
        double gesamtZeit = (endZeit - StartZeit)/1000000000d;

        String zaehler = String.format("%,d", handler.zaehler);
        String kombprosek = String.format("%,d", Math.round(handler.zaehler/gesamtZeit));
        
        System.out.println("\n\nFertig!\nDas Passwort lautet: "+handler.leser(PFAD+"passwort.txt")+"\n\nSTATISTIK:\n--------\n(SHA-256) Hash: "+originalHash
        +"\nAnzahl der veruschten Kombinationen: "+ zaehler +"\nKombinationen pro Sekunde: "+kombprosek+"\nLaufzeit: "+gesamtZeit+"s");
    }
}