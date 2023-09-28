package project1;

/**
 * @author Zain Zulfiqar
 */
public enum Department {
    CS("Computer Science"),
    EE ("Electrical Engineering"),
    ITI ("Information Technology and Informatics"),
    MATH ("Mathematics"),
    BAIT ("Business Analytics and Information Technology");

    private final String fullName;

    /**
     * Constructs Department enum based on fullName
     * @param fullName of department as String
     */
    Department(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets full name of the department from its acronym
     * @return fullName as String
     */
    public String getFullName() {
        return fullName;
    }
}
