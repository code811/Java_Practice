import java.util.List;
import java.util.Scanner;

public class View {

    private MainComponent mainComponent;
    private final Scanner sc = new Scanner(System.in);

    public View (MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    public void start() {
        int option = 1;
        do {
            printHeader();
            option = promptMenu();
            doOption(option);
            System.out.println();
        } while (option != 0);
    }

    private void doOption(int option) {
        switch(option) {
            case 1 -> printEncryptMessage();
            case 2 -> printDecryptMessage();
            case 3 -> printAddMendec();
            case 4 -> printShowMendecs();
            case 5 -> printDescribeMendec();
            case 6 -> printRemoveMendec();
            case 0 -> printQuit();
        }
    }

    private void printHeader() {
        System.out.println("[Message Encryptor/Decryptor]");
    }

    private int promptMenu() {
        System.out.println("1. Encrypt message");
        System.out.println("2. Decrypt message");
        System.out.println("3. Add mendec");
        System.out.println("4. Show mendecs");
        System.out.println("5. Describe mendec");
        System.out.println("6. Remove mendec");
        System.out.println("0. Quit");
        System.out.print("Enter option: ");
        int option = Integer.parseInt(sc.nextLine());
        System.out.println();
        return option;
    }

    // Case 1
    private void printEncryptMessage() {
        System.out.print("Enter mendec ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println();
        System.out.print("Enter plaintext: ");
        String cipherText = mainComponent.encryptMessage(sc.nextLine(), id);
        System.out.println();
        System.out.println("Your ciphertext is '" + cipherText + "'");
    }

    // Case 2
    private void printDecryptMessage() {
        System.out.print("Enter mendec ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println();
        System.out.print("Enter ciphertext: ");
        String plainText = mainComponent.decryptMessage(sc.nextLine(), id);
        System.out.println();
        System.out.println("Your plaintext is '" + plainText + "'");
    }

    // Case 3
    private void printAddMendec() {
        int option = promptAddMendec();

        switch(option) {
            case 1 -> mainComponent.addMendecList(promptAddCaesar());
            case 2 -> mainComponent.addMendecList(promptAddVigenere());
            case 3 -> mainComponent.addMendecList(promptAddTransposition());
        }

        System.out.println("Mendec added.");
    }

    private int promptAddMendec() {
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Vigenere Cipher");
        System.out.println("3. Transposition Cipher");
        System.out.print("Use which cipher? ");
        return Integer.parseInt(sc.nextLine());
    }

    private CaesarCipher promptAddCaesar() {
        System.out.print("Enter mendec name: ");
        String name = sc.nextLine();
        System.out.print("Enter offset: ");
        int offset = Integer.parseInt(sc.nextLine());
        return new CaesarCipher(name, offset);
    }

    private VigenereCipher promptAddVigenere() {
        System.out.print("Enter mendec name: ");
        String name = sc.nextLine();
        System.out.print("Enter key: ");
        String key = sc.nextLine();
        return new VigenereCipher(name, key);
    }

    private TransposerCipher promptAddTransposition() {
        System.out.print("Enter mendec name: ");
        String name = sc.nextLine();
        System.out.print("Enter width: ");
        int width = Integer.parseInt(sc.nextLine());
        return new TransposerCipher(name, width);
    }

    // Case 4
    private void printShowMendecs() {
        String option = promptShowMendecs();
        printMendec(option, mainComponent.getList());
    }

    private String promptShowMendecs() {
        System.out.println("Show which mendecs? Options are: ");
        System.out.println("ALL");
        System.out.println("CAE for Caesar Cipher");
        System.out.println("VIG for Viginere Cipher");
        System.out.println("TRA for Transposer Cipher");
        System.out.print("Enter option: ");
        return sc.nextLine();
    }

    private void printMendec(String option, List<Mendec> Mendecs) {
        switch(option) {
            case "ALL" -> {
                for(Mendec mendec : Mendecs) {
                    System.out.println(mendec);
                }
            }
            case "CAE" -> {
                for(Mendec mendec : Mendecs) {
                    if(mendec instanceof CaesarCipher) {
                        System.out.println(mendec);
                    }
                }
            }
            case "VIG" -> {
                for(Mendec mendec : Mendecs) {
                    if(mendec instanceof VigenereCipher) {
                        System.out.println(mendec);
                    }
                }
            }
            case "TRA" -> {
                for(Mendec mendec : Mendecs) {
                    if(mendec instanceof TransposerCipher) {
                        System.out.println(mendec);
                    }
                }
            }
        }
    }

    // Case 5
    private void printDescribeMendec() {
        System.out.print("Enter mendec ID: ");
        Mendec cipher = mainComponent.searchList(Integer.parseInt(sc.nextLine()));
        for(int i = 0; i < mainComponent.getList().size(); i++) {
            System.out.println(cipher);
            System.out.println(cipher.getDescription());;
        }
    }

    // Case 6
    private void printRemoveMendec() {
        System.out.print("Enter mendec ID: ");
        int id = Integer.parseInt(sc.nextLine());
        if(mainComponent.searchList(id) == null) {
            return;
        }
        mainComponent.removeMendec(id);
        System.out.println("Mendec removed.");
    }

    // Case 0
    private void printQuit() {
        System.out.println("Shutting off...");
    }
}
