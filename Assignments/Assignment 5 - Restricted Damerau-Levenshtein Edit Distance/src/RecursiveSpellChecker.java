import java.util.ArrayList;
import java.util.Scanner;

public class RecursiveSpellChecker {

    private static int countSpaces(String s){
        if(s.isEmpty()) return 0;
        int isSpace = ((s.charAt(0) == ' ') ? 1 : 0);
        return isSpace + countSpaces(s.substring(1));
    }

    private static String getWord(String s){
        if(s.charAt(0) == ' ') return "";
        if(s.length() == 1) return s;
        return s.charAt(0) + getWord(s.substring(1));
    }

    private static int calculateEditDistance(String a, String b){
        if(a.equals(b)) return 0;
        if(a.isEmpty()) return b.length();
        if(b.isEmpty()) return a.length();

        int min = a.length() + b.length();

        min = Math.min(min, 1 + calculateEditDistance(a.substring(1), b));
        min = Math.min(min, 1 + calculateEditDistance(a, b.substring(1)));

        if(a.charAt(0) == b.charAt(0)) {
            min = Math.min(min, calculateEditDistance(a.substring(1), b.substring(1)));
        }
        else {
            min = Math.min(min, 1 + calculateEditDistance(a.substring(1), b.substring(1)));
        }

        if(a.length() >= 2 && b.length() >= 2 &&
                a.charAt(0) == b.charAt(1) &&
                a.charAt(1) == b.charAt(0)) {
                min = Math.min(min, 1 + calculateEditDistance(a.substring(2), b.substring(2)));
        }

        return min;
    }

    private static String[] stripPunctuation(String s){
        if(s.substring(s.length() - 1).matches("^[.,!?:;]$")) {
            return new String[]{s.substring(0, s.length() - 1), s.substring(s.length() - 1)};
        }
        else {
            return new String[]{s, ""};
        }
    }

    private static void updateCandidates(ArrayList<Word> candidates,
                                         Word candidate,
                                         int index) {
        // The checks below are not necessary for private methods. Since the
        // outside world cannot call this method, we should be able to guarantee
        // that we never call this method with any set of arguments which would
        // resolve either of the IFs below to true. Nonetheless, said IFs have
        // been added here for completion's sake.
        if(candidates == null || candidate == null) return;
        if(index > candidates.size() || index < 0) return;

        if(index == candidates.size()) candidates.addLast(candidate);
        else if(candidates.get(index) == null) candidates.add(index, candidate);
        else if(candidates.get(index).editDistance < candidate.editDistance){
            updateCandidates(candidates, candidate, index + 1);
        }
        else candidates.add(index, candidate);

        if(candidates.size() > 5) candidates.removeLast();
    }

    private static void splitOnSpaces(String[] tokens,
                                      String sentence,
                                      int index){
        // ditto
        if(tokens == null || sentence == null) return;
        if(index >= tokens.length || index < 0) return;

        tokens[index] = getWord(sentence);
        sentence = sentence.substring(tokens[index].length());

        if(!sentence.isEmpty()){
            sentence = sentence.substring(1); // First character must be a space. Remove it.
            splitOnSpaces(tokens, sentence, index + 1);
        }
    }

    public static void spellCheckSentence(ArrayList<String> dict,
                                          String sentence,
                                          Scanner sc){
        // Since this is a public method, it is wise to guard ourselves from
        // callers passing arguments which would cause our code to crash.
        if(dict == null || sentence == null || sc == null) return;

        sentence = sentence.strip(); // Don't care about spaces before or after the sentence starts
        String[] words = new String[countSpaces(sentence) + 1]; // The number of words in a sentence is always one greater than its number of spaces
        splitOnSpaces(words, sentence, 0);

        ArrayList<Word> candidates;

        // Sadly, it is unlikely that Java will allow us to create a call stack
        // deep enough to traverse the entire dictionary.
        for(int i = 0; i < words.length; i++){
            String[] tokenComponents = stripPunctuation(words[i]); // Separate word and its punctuation, if any
            String token = tokenComponents[0].toLowerCase(); // Make it lower case. All dictionary words are lower case
            candidates = new ArrayList<>();
            int distance = 0;

            for(String candidate : dict){
                distance = calculateEditDistance(token, candidate);

                if(distance == 0) break;

                Word w = new Word(token, candidate, distance);
                updateCandidates(candidates, w, 0); // If a word isn't a match with a dictionary word, save it to present to the user later
            }

            if(distance != 0){
                System.out.printf("'%s' not in dictionary. Pick an option: \n", token);

                for(int j = 0; j < candidates.size(); j++){
                    System.out.printf("%d. Replace with '%s'\n", j, candidates.get(j).candidateWord);
                }
                System.out.printf("5. Add '%s' to dictionary\n", token);

                String option = sc.nextLine();

                switch (option){
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                        String newWord = candidates.get(Integer.parseInt(option)).candidateWord;
                        words[i] = newWord + tokenComponents[1]; // Re-adding punctuation, if it had any
                        System.out.printf("Replaced '%s' with '%s'\n", token, newWord);
                        break;
                    default:
                        dict.add(token);
                        System.out.printf("'%s' added to dictionary\n", token);
                }
                System.out.println();
            }
        }

        StringBuilder sb = new StringBuilder();
        for(String s :  words) sb.append(s).append(" "); // Building the original sentence back
        System.out.printf("The final sentence is: '%s'\n", sb.subSequence(0, sb.length() - 1));
    }
}
