import java.util.ArrayList;
import java.util.Scanner;

public class SpellCheckerController {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<String> dictionary = Dictionary.getDictionary();

    private ArrayList<Word> candidates = new ArrayList<>();

    public void start() {
        String initialSentence = "";
        SpellCheckerUI.printHeader();
        do {
            SpellCheckerUI.printForSentence();
            initialSentence = sc.nextLine();

            if(initialSentence.isEmpty()) {
                break;
            }

            SpellCheckerUI.printInitialSentence(initialSentence);
            String[] finalSentence = initialSentence.toLowerCase().split(" ");
            spellCheckSentence(finalSentence);

            SpellCheckerUI.printFinalSentence(getFinalSentence(finalSentence));
        } while(!initialSentence.isEmpty());

        SpellCheckerUI.printExitProgram();
        sc.close();
    }

    // Creates a string from the String[]
    private String getFinalSentence(String[] finalSentence) {
        String sentence = "";
        for(String word : finalSentence) {
            sentence = sentence.concat(word + " ");
        }
        sentence = sentence.stripTrailing();
        return sentence;
    }

    // Checks string for any word NOT in the dictionary
    private void spellCheckSentence(String[] finalSentence) {

        for(int i = 0; i < finalSentence.length; i++) {
            String word = finalSentence[i];

            if(spellCheckWord(word)) {
                continue;
            }

            SpellCheckerUI.printSpellCheck(word, candidates);
            int option = Integer.parseInt(sc.nextLine());
            if(option < candidates.size()) {
                finalSentence[i] = candidates.get(option).candidateWord;
                SpellCheckerUI.printCorrection(candidates.get(option));
            }
            else if(option == candidates.size()) {
                dictionary.add(word);
                SpellCheckerUI.printAddToDictionary(word);
            }
        }
    }

    private boolean spellCheckWord(String word) {
        if(dictionary.contains(word)) {
            return true;
        }

        createPotentialCandidate(word);
        return false;
    }

    private void createPotentialCandidate(String word) {
        candidates.clear();

        for(String dictionaryWord : dictionary) {
            Word potentialCandidate = new Word();
            potentialCandidate.originalWord = word;
            potentialCandidate.candidateWord = dictionaryWord;
            potentialCandidate.editDistance = calculateEditDistance(word, dictionaryWord);
            updateCandidates(candidates, potentialCandidate);
        }
    }

    // Creates the 2D array of integers and return bottom-right cell
    private int calculateEditDistance(String original, String candidate) {
        String row = " ".concat(original);
        String column = " ".concat(candidate);
        int[][] levenshteinAlgorithm = new int[row.length()][column.length()];
        for(int i = 0; i < row.length(); i++) {
            for(int j = 0; j < column.length(); j++) {

                // For the first row
                if(i == 0) {
                    levenshteinAlgorithm[0][j] = j;
                    continue;
                }
                // For the first column
                if(j == 0) {
                    levenshteinAlgorithm[i][0] = i;
                    continue;
                }

                int cost = (row.charAt(i) == column.charAt(j)) ? 0 : 1;

                levenshteinAlgorithm[i][j] = Math.min(
                        Math.min(
                                levenshteinAlgorithm[i - 1][j] + 1,      // deletion
                                levenshteinAlgorithm[i][j - 1] + 1       // insertion
                        ),
                        levenshteinAlgorithm[i - 1][j - 1] + cost    // substitution
                );
            }
        }
        return levenshteinAlgorithm[original.length()][candidate.length()];
    }

    // Updates a list for the 5 smallest edit distances from the given word
    private void updateCandidates(ArrayList<Word> candidates, Word potentialCandidate) {

        candidates.add(potentialCandidate);

        candidates.sort((a, b) -> Integer.compare(a.editDistance, b.editDistance));

        if (candidates.size() > 5) {
            candidates.removeLast();
        }
    }
}
