import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    //Konstanten
    private static Funktionen handler = new Funktionen();
    private static Scanner tastaturInput = new Scanner(System.in);
    private static int SLEEP_MAX = 25;
    
    public static void main(String[] args) {
        ArrayList<Thread> threadListe = new ArrayList<Thread>();

        System.out.println("Bitte geben Sie den Hash vom Passwort ein: ");
        String originalHash = tastaturInput.next();

        System.out.println("\n\nStarte Thread-Ring:");

        double StartZeit = System.nanoTime();

        for(int i = 0; i < 6; i++){
            final int s = i;
            Thread t = new Thread(() -> handler.bruteForce(originalHash, s+1));
            t.start();
            threadListe.add(t);
            System.out.println("Starte:\t\t\t\tThread-"+i);
        }
        System.out.println("");
        while(!threadListe.isEmpty()){
            for (int i = 0; i < threadListe.size(); i++){
             try{
               threadListe.get(i).sleep(SLEEP_MAX);  
             }
              catch(Exception e){
                  e.printStackTrace();
              }
                
            }
            for (int x = 0; x < threadListe.size(); x++){
                if (threadListe.get(x).getState() == Thread.State.TERMINATED){
                    System.out.println("Thread fertig:\t\t\t" + threadListe.get(x).getName());
                    threadListe.remove(x);
                }
                if (!handler.passwort.isEmpty()){
                    for (int i = 0; i < threadListe.size(); i++){
                        threadListe.get(i).interrupt();
                        System.out.println("Thread unterbrochen:\t\t"+threadListe.get(i).getName());
                    }
                    threadListe.removeAll(threadListe);
                    break;
                }
            }
        }
        double endZeit   = System.nanoTime();
        double gesamtZeit = (endZeit - StartZeit)/1000000000d;

        String zaehler = String.format("%,d", handler.zaehler);
        String kombprosek = String.format("%,d", Math.round(handler.zaehler/gesamtZeit));
        
        System.out.println("\n\nFertig!\nDas Passwort lautet: "+handler.passwort+"\n(SHA-256) Hash: "+originalHash+"\n\nSTATISTIK:\n-----------"
        +"\nAnzahl der veruschten Kombinationen: "+ zaehler +"\nKombinationen pro Sekunde: "+kombprosek+"\nLaufzeit: "+gesamtZeit+"s");
        System.exit(0);
    }
}
