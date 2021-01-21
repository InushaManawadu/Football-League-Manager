package w1790317.demo;

import java.util.Objects;

public class SchoolFootballClub extends FootballClub  {

    private String schoolName;

    public SchoolFootballClub() {

    }

    public SchoolFootballClub(String schoolName) {
        this.schoolName = schoolName;
    }

    public SchoolFootballClub(String name, String location, int wins, int draws, int defeats, int goalsScored, int goalsReceived, int numOfPoints, int numOfMatches, Date date, String schoolName) {
        super(name, location, wins, draws, defeats, goalsScored, goalsReceived, numOfPoints, numOfMatches, date);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "School FootballClub [School Name:" + schoolName +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return schoolName.equals(that.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolName);
    }
}
