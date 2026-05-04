public class GraduateInfo {

    public String city,
            state,
            target_university,
            target_edu_level,
            target_edu_major,
            target_edu_college,
            highest_edu_university,
            highest_edu_level,
            highest_edu_college,
            current_job_category,
            current_employer,
            current_employer_industry;
    public int target_edu_year,
            highest_edu_year,
            current_employer_start_year,
            estimated_salary;

    public GraduateInfo(String city, String state, String target_university, String target_edu_level, int target_edu_year,
                        String target_edu_major, String target_edu_college, String highest_edu_university, String highest_edu_level,
                        int highest_edu_year, String highest_edu_college, String current_job_category, String current_employer,
                        int current_employer_start_year, String current_employer_industry, int estimated_salary) {
        this.city = city;
        this.state = state;
        this.target_university = target_university;
        this.target_edu_level = target_edu_level;
        this.target_edu_major = target_edu_major;
        this.target_edu_college = target_edu_college;
        this.highest_edu_university = highest_edu_university;
        this.highest_edu_level = highest_edu_level;
        this.highest_edu_college = highest_edu_college;
        this.current_job_category = current_job_category;
        this.current_employer = current_employer;
        this.current_employer_industry = current_employer_industry;
        this.target_edu_year = target_edu_year;
        this.highest_edu_year = highest_edu_year;
        this.current_employer_start_year = current_employer_start_year;
        this.estimated_salary = estimated_salary;
    }
}
