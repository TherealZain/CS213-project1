package project1;

public class Contact {
    private Department department;
    private String email;

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
        Department[] allDepartments = Department.values();
        boolean departmentCheck = false;
        boolean emailCheck = false;

        for (Department dep : allDepartments) {
            if (department.equals(dep)) {
                departmentCheck = true;
                break;
            }
        }

        if (email.toLowerCase().endsWith("@rutgers.edu")) {
            String prefix = email.substring(0, email.indexOf("@"));
            if (prefix.equalsIgnoreCase(department.name())) {
                emailCheck = true;
            }
        }

        return departmentCheck && emailCheck;

    }
}
