import java.util.Scanner;
public class Main{
    private static Scanner tastaturInput = new Scanner(System.in);
    private static Funktionen handler = new Funktionen();
    private static String PFAD = "/home/laaren/Projects/Schule/Final/passwort.txt";
    public static void main(String[] args) {
        System.out.println("Bitte geben Sie den Hash vom Passwort ein: ");
        String originalHash = tastaturInput.next();

        double StartZeit = System.nanoTime();

        String ergebnis = handler.vergleiche(PFAD, originalHash);

        double endZeit   = System.nanoTime();
        double gesamtZeit = (endZeit - StartZeit)/1000000000d;

        if(ergebnis.isEmpty() == true){
            System.out.println("Alle Einträge in der Datei: "+ PFAD+ " wurden gehasht und mit dem ursprünglichen Hash verglichen, es gab keinen positiven Match...");
        }
        else{
            System.out.println("\nFertig!\nDas Passwort lautet: "+ergebnis+"\n(SHA-256) Hash: "+originalHash);
        }

        String zaehler = String.format("%,d", handler.zaehler);
        String kombprosek = String.format("%,d", Math.round(handler.zaehler/gesamtZeit));
        
        System.out.println("\nSTATISTIK:\n--------\nAnzahl der veruschten Kombinationen: "+ handler.zaehler +"\nLaufzeit: "+gesamtZeit+"s");
    }
}