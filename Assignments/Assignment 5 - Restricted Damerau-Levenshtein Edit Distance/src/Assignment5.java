import java.util.ArrayList;
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> dict = Dictionary.getDictionary();
        String sentence;

        System.out.println("[Spell Checker]");

        do{
            System.out.println();
            System.out.print("Enter a sentence to spell-check, or nothing to quit: ");
            sentence = sc.nextLine();

            System.out.printf("You've entered '%s'\n\n", sentence);

            if(!sentence.isEmpty()) {
                RecursiveSpellChecker.spellCheckSentence(dict, sentence, sc);
            }

        }while(!sentence.isEmpty());

        System.out.println("Shutting off...");
    }
}