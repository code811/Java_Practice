import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("[KSU Graduate Job Report]");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();
        sc.close();

        File file = new File(fileName);
        try(Scanner fileReader = new Scanner(file)) {

            ArrayList<GraduateInfo> graduateInfos = new ArrayList<>();
            String reportFile = "report.txt";
            String errorFile = "error.txt";
            int lineNumber = 0;
            int validEntries = 0;
            int invalidEntries = 0;

            while(fileReader.hasNextLine()) {
                try {
                    lineNumber++;

                    String[] entry = fileReader.nextLine().split(",");

                    // Skips first line in final-data.csv
                    if(lineNumber == 1) continue;

                    validateEntry(entry, lineNumber);

                    GraduateInfo graduateInfo = new GraduateInfo(entry[0], entry[1], entry[2], entry[3], Integer.parseInt(entry[4]), entry[5], entry[6], entry[7], entry[8], Integer.parseInt(entry[9]), entry[10], entry[11], entry[12], Integer.parseInt(entry[13]), entry[14], Integer.parseInt(entry[15]));
                    graduateInfos.add(graduateInfo);
                    validEntries++;
                }
                catch(IncompleteEntryException e) {
                    writeFile(errorFile, e);
                    invalidEntries++;
                }
            }

            writeFile(reportFile, graduateInfos);
            System.out.printf("There was a total of %d valid entries and %d invalid entries.\n", validEntries, invalidEntries);
            System.out.printf("Report written to '%s'. Errors written to '%s'\n", reportFile, errorFile);
        }
        catch(FileNotFoundException e) {
            System.out.println("Could not find the specified file.");
        }

        System.out.println("Program complete.");
    }

    private static void validateEntry(String[] entry, int lineNumber) throws IncompleteEntryException {

        for(int i = 0; i < entry.length; i++) {

            if(entry[i].isEmpty()) {

                throw new IncompleteEntryException(
                        String.format("Missing field '%s'", getMissingField(i)),
                        lineNumber,
                        entry[i]
                );
            }

            int estimatedSalaryIndex = 15;
            if(i == estimatedSalaryIndex) {

                if(Integer.parseInt(entry[i]) <= 0) {

                    throw new IncompleteEntryException(
                            String.format("Missing field '%s'", getMissingField(i)),
                            lineNumber,
                            entry[i]
                    );
                }
            }
        }
    }

    private static String getMissingField(int lineNumber) {
        String[] fields = {"city", "state" ,"target_university" ,"target_edu_level", "target_edu_year", "target_edu_major", "target_edu_college", "highest_edu_university", "highest_edu_level", "highest_edu_year", "highest_edu_college", "current_job_category", "current_employer", "current_employer_start_year", "current_employer_industry", "estimated_salary"};
        return fields[lineNumber];
    }

    private static void writeFile(String fileName, ArrayList<GraduateInfo> graduateInfos) {
        try(FileWriter errorFile = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(errorFile)) {

            int[] highestEducationLevel = Queries.highestEducationLevel(graduateInfos);
            int[] lastYearAtKSU = Queries.lastYearAtKSU(graduateInfos);
            ArrayList<String> topFiveIndustries = Queries.topFiveIndustries(graduateInfos);

            bufferedWriter.write("Number of graduates who did not get their highest degree at KSU: " + Queries.higherEdAtDifferentUni(graduateInfos) + '\n');
            bufferedWriter.write('\n');
            bufferedWriter.write("Highest education level reached: \n");
            bufferedWriter.write("certificate: " + highestEducationLevel[0] + '\n');
            bufferedWriter.write("associates: " + highestEducationLevel[1] + '\n');
            bufferedWriter.write("bachelors: " + highestEducationLevel[2] + '\n');
            bufferedWriter.write("masters: " + highestEducationLevel[3] + '\n');
            bufferedWriter.write("doctorate: " + highestEducationLevel[4] + '\n');
            bufferedWriter.write('\n');
            bufferedWriter.write("Number of KSU graduates per period is: \n");
            bufferedWriter.write("Earlier than 2005: " + lastYearAtKSU[0] + '\n');
            bufferedWriter.write("2005-2009: " + lastYearAtKSU[1] + '\n');
            bufferedWriter.write("2010-2014: " + lastYearAtKSU[2] + '\n');
            bufferedWriter.write("2015-2019: " + lastYearAtKSU[3] + '\n');
            bufferedWriter.write("2020 or later: " + lastYearAtKSU[4] + '\n');
            bufferedWriter.write('\n');
            bufferedWriter.write("These are the 5 industries with the most graduates: \n");
            bufferedWriter.write(topFiveIndustries.get(0) + '\n');
            bufferedWriter.write(topFiveIndustries.get(1) + '\n');
            bufferedWriter.write(topFiveIndustries.get(2) + '\n');
            bufferedWriter.write(topFiveIndustries.get(3) + '\n');
            bufferedWriter.write(topFiveIndustries.get(4) + '\n');
        }
        catch(IOException e) {
            System.out.println("Something went wrong!");
        }
    }

    private static void writeFile(String fileName, IncompleteEntryException e) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            bufferedWriter.write(e.getMessage() + ", line " + e.getLineNumber() + '\n' +
                    e.getInfo() + '\n' +
                    "----\n");
        }
        catch(IOException ex) {
            System.out.println("Something went wrong!");
        }
    }
}