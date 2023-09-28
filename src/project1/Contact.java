package project1;

/**
 * @author Zain Zulfiqar
 */
public class Contact {
    private Department department;
    private String email;

    /**
     * Constructs Contact based on department and email
     * @param department of Contact as Department
     * @param email of Contact as String
     */
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    /**
     * Gets department's full name from the department enum
     * @return fullName of department as String
     */
    public String getDepartment(){
        return department.getFullName();
    }

    /**
     * Gets department's email in proper formatting
     * @return email as String
     */
    public String getEmail() {
        String departmentAcronyms = department.name().toLowerCase();
        return departmentAcronyms + "@rutgers.edu";
    }

    /**
     * Checks if department name and email are valid
     * @return true if both department name and email are valid, false if invalid
     */
    public boolean isValid(){
        return isValidDepartment() && isValidEmail();
    }

    /**
     * Checks if email input is in right format
     * @return true if email is in right format, false if invalid
     */
    public boolean isValidEmail(){
        if (email.toLowerCase().endsWith("@rutgers.edu")) {
            String prefix = email.substring(0, email.indexOf("@"));
            if (prefix.equalsIgnoreCase(department.name())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if department input is valid based on department enum
     * @return true if department is valid, false if invalid
     */
    public boolean isValidDepartment(){
        Department[] allDepartments = Department.values();

        for (Department dep : allDepartments) {
            if (department.equals(dep)) {
                return true;
            }
        }
        return false;
    }

}
