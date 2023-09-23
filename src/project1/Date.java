package project1;

public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;


    public Date(int year, int month, int day){
        this.year = year;
        this. month = month;
        this. day = day;

    }

    public int compareTo(Date d){
        // Compare years
        if (this.year > d.year) {
            return 1;
        }
        if (this.year < d.year) {
            return -1;
        }

        // Compare months
        if (this.month > d.month) {
            return 1;
        }
        if (this.month < d.month) {
            return -1;
        }

        // Compare days
        if (this.day > d.day) {
            return 1;
        }
        if (this.day < d.day) {
            return -1;
        }

        return 0;  // Dates are the same
    }

    public boolean isValid(){
        return true;
    }


}
