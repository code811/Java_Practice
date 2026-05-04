import java.util.ArrayList;
import java.util.Scanner;

public class SpellCheckerController2 {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<String> dictionary = Dictionary.getDictionary();

    private ArrayList<Word> candidates = new ArrayList<>();

    public void start() {
        String initialSentence = "";
        SpellCheckerUI.printHeader();
        do {
            SpellCheckerUI.printForSentence();
            initialSentence = sc.nextLine();
            SpellCheckerUI.printInitialSentence(initialSentence);
            String[] finalSentence = initialSentence.toLowerCase().split("\\s+");
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

                int sub = levenshteinAlgorithm[i - 1][j - 1];
                int insert = levenshteinAlgorithm[i][j - 1];
                int delete = levenshteinAlgorithm[i - 1][j];

                char originalLetter = row.charAt(i);
                char candidateLetter = column.charAt(j);

                if(originalLetter == candidateLetter) {
                    levenshteinAlgorithm[i][j] = sub;
                }

                int leastEditDistance = Math.min(sub, insert);
                leastEditDistance = Math.min(leastEditDistance, delete);
                if(leastEditDistance == sub) {
                    levenshteinAlgorithm[i][j] = sub + 1;
                }
                else if(leastEditDistance == insert) {
                    levenshteinAlgorithm[i][j] = insert + 1;
                }
                else {
                    levenshteinAlgorithm[i][j] = delete + 1;
                }

            }
        }
        return levenshteinAlgorithm[original.length()][candidate.length()];
    }

    // Updates a list for the 5 smallest edit distances from the given word
    private void updateCandidates(ArrayList<Word> candidates, Word potentialCandidate) {
        for (Word candidate : candidates) {
            int currentSmall = candidate.editDistance;
            int potentialSmallest = potentialCandidate.editDistance;
            if (currentSmall > potentialSmallest) {
                swap(candidate, potentialCandidate);
            }
        }
        if(candidates.size() > 5) {
            candidates.removeLast();
        }
    }

    // if this doesn't work use .set()
    private void swap(Word candidate, Word potentialCandidate) {
        Word temp = candidate;
        candidate = potentialCandidate;
        potentialCandidate = temp;
    }
}
