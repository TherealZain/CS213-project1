package project1;

import java.util.Locale;

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


    public boolean isValid(){

    }
}
