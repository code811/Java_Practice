import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Diff Detector]");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of file 1: ");
        String file1Name = sc.nextLine();
        System.out.print("Enter the name of file 2: ");
        String file2Name = sc.nextLine();
        System.out.println();

        File file1 = new File(file1Name);
        File file2 = new File(file2Name);

        System.out.println(diff(file1, file2));
        System.out.println("Program complete");
    }

    public static String diff(File file1, File file2) {
        String diff = "";
        try(Scanner sc1 = new Scanner(file1);
            Scanner sc2 = new Scanner(file2)) {

            int line = 0;
            while(sc1.hasNextLine() && sc2.hasNextLine()) {
                line++;
                String file1Line = sc1.nextLine();
                String file2Line = sc2.nextLine();

                if(!file1Line.equals(file2Line)) {
                    diff += ("Line " + line + ":\n");
                    diff += ("< " + file1Line + '\n');
                    diff += ("> " + file2Line + '\n');
                }
            }

            if(sc1.hasNextLine() || sc2.hasNextLine()) {
                diff += ("Files have different number of lines\n");
            }
        }
        catch(IOException e) {
            String file;
            if(!file1.canExecute()) file = file1.getName();
            else file = file2.getName();
            return (file + " (The system cannot find the file specified)\n");
        }

        if(diff.isEmpty()) {
            diff += ("Files are identical.\n");
        }

        return diff;
    }
}