package project1;

public class Contact {
    private Department department;
    private String email;

    public Contact( Department department, String email){
        this.department = department;
        this.email = email;
    }

    public String getDepartment(){
        return department.getFullName();
    }

    public String getEmail() {
        String departmentAcronyms = department.name().toLowerCase();
        return departmentAcronyms + "@rutgers.edu";
    }

    /**
     * Checks if department name and email are valid
     * @return true if both department name and email are in format,
     * false otherwise
     * NEEDS TESTING
     */
    public boolean isValid(){
        return isValidDepartment() && isValidEmail();

    }

    public boolean isValidEmail(){
        if (email.toLowerCase().endsWith("@rutgers.edu")) {
            String prefix = email.substring(0, email.indexOf("@"));
            if (prefix.equalsIgnoreCase(department.name())) {
                return true;
            }
        }
        return false;
    }

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
