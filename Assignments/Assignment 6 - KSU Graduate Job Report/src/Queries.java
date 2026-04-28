import java.util.ArrayList;

public class Queries {

    public static int higherEdAtDifferentUni(ArrayList<GraduateInfo> graduateInfos) {

        int higherEdAtDifferentUni = 0;

        for(GraduateInfo info : graduateInfos) {

            String firstDegreeAtUni = cleanData(info.target_university);
            String highestDegreeElsewhere = cleanData(info.highest_edu_university);

            if(firstDegreeAtUni.equalsIgnoreCase("kennesawStateUniversity") &&
            !highestDegreeElsewhere.equalsIgnoreCase("kennesawStateUniversity")) {

                higherEdAtDifferentUni++;
            }
        }

        return higherEdAtDifferentUni;
    }

    public static int[] highestEducationLevel(ArrayList<GraduateInfo> graduateInfos) {

        int[] highestEducationLevel = new int[5];

        for(GraduateInfo info : graduateInfos) {

            switch(cleanData(info.highest_edu_level)) {

                case "certificate" -> highestEducationLevel[0]++;
                case "associates" -> highestEducationLevel[1]++;
                case "bachelors" -> highestEducationLevel[2]++;
                case "masters" -> highestEducationLevel[3]++;
                case "doctorate" -> highestEducationLevel[4]++;
            }
        }

        return highestEducationLevel;
    }

    public static int[] lastYearAtKSU(ArrayList<GraduateInfo> graduateInfos) {

        int[] lastYearAtKSU = new int[5];

        for(GraduateInfo info : graduateInfos) {

            int year = info.highest_edu_year;

            if(!cleanData(info.highest_edu_university).equalsIgnoreCase("kennesawStateUniversity")) {
                year = info.target_edu_year;
            }

            if(year < 2005) lastYearAtKSU[0]++;
            else if(year <= 2009) lastYearAtKSU[1]++;
            else if(year <= 2014) lastYearAtKSU[2]++;
            else if(year <= 2019) lastYearAtKSU[3]++;
            else lastYearAtKSU[4]++;

        }

        return lastYearAtKSU;
    }

    // Use parallel ArrayLists; one holds the name, the other holds the number of instances it appears;
    // if-statement to check if instance is already present in ArrayList, if so: increment the parallel ArrayList<int> by the index of the search; if not: make another element
    public static ArrayList<String> topFiveIndustries(ArrayList<GraduateInfo> graduateInfos) {

        ArrayList<String> industries = new ArrayList<>();
        ArrayList<Integer> industryInstances = new ArrayList<>();

        updateListOfIndustries(graduateInfos, industries, industryInstances);

        descendingSortIndustries(industries, industryInstances);

        ArrayList<String> topFiveIndustries = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            topFiveIndustries.add(industries.get(i) +  ": " + industryInstances.get(i));
        }

        return topFiveIndustries;
    }

    private static void updateListOfIndustries(ArrayList<GraduateInfo> graduateInfos, ArrayList<String> industries, ArrayList<Integer> industryInstances) {
        for(GraduateInfo info : graduateInfos) {

            String industry = info.current_employer_industry;
            int industryIndex = getIndustryIndex(industry, industries);

            // Checks if topFiveIndustries already knows about an industry; if not, creates a new element for it
            if(industryIndex == -1) {

                industries.add(industry);
                industryInstances.add(1);
                continue;
            }

            industryInstances.set(industryIndex, industryInstances.get(industryIndex) + 1);
        }
    }

    private static int getIndustryIndex(String industry, ArrayList<String> topFiveIndustries) {

        for(int i = 0; i < topFiveIndustries.size(); i++) {

            String knownIndustry = cleanData(topFiveIndustries.get(i));

            if(knownIndustry.equals(cleanData(industry))) {

                return i;
            }
        }

        return -1;
    }

    private static void descendingSortIndustries(ArrayList<String> industries,
                                                 ArrayList<Integer> industryInstances) {

        for(int i = 0; i < industryInstances.size() - 1; i++) {

            for(int j = 0; j < industryInstances.size() - 1 - i; j++) {

                if(industryInstances.get(j) < industryInstances.get(j + 1)) {

                    int tempInstance = industryInstances.get(j);
                    industryInstances.set(j, industryInstances.get(j + 1));
                    industryInstances.set(j + 1, tempInstance);

                    String tempIndustry = industries.get(j);
                    industries.set(j, industries.get(j + 1));
                    industries.set(j + 1, tempIndustry);
                }
            }
        }
    }

    private static String cleanData(String data) {
        data = data.replaceAll(" ", "");
        return data.toLowerCase();
    }
}
