import java.util.ArrayList;

public class SpellCheckerUI {

    public static void printHeader() {
        System.out.println("[Spell Checker]");
        System.out.println();
    }

    public static void printForSentence() {
        System.out.print("Enter a sentence to spell-check, or nothing to quit: ");
    }

    public static void printInitialSentence(String sentence) {
        System.out.println("You've entered '" + sentence + "'");
        System.out.println();
    }

    public static void printSpellCheck(String original, ArrayList<Word> candidates) {
        System.out.println("'" + original + "' not in dictionary. Pick an option:");
        int index;
        for(index = 0; index < candidates.size(); index++) {
            String candidateWord = candidates.get(index).candidateWord;
            System.out.println(index + ". Replace with '" + candidateWord + "'");
        }
        System.out.println(index + ". Add '" + original + "' to dictionary");
    }

    public static void printAddToDictionary(String original) {
        System.out.println("'" + original + "' added to dictionary");
        System.out.println();
    }

    public static void printCorrection(Word candidate) {
        String original = candidate.originalWord;
        System.out.println("Replaced '" + original + "' with '" + candidate.candidateWord + "'");
        System.out.println();
    }

    public static void printFinalSentence(String sentence) {
        System.out.println("The final sentence is: '" + sentence + "'");
        System.out.println();
    }

    public static void printExitProgram() {
        System.out.println();
        System.out.println("Shutting off...");
    }
}
