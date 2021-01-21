package w1790317.demo;

import java.util.Objects;

public class UniversityFootballClub extends FootballClub {

    private String universityName;

    public UniversityFootballClub() {

    }

    public UniversityFootballClub(String universityName) {
        this.universityName = universityName;
    }

    public UniversityFootballClub(String name, String location, int wins, int draws, int defeats, int goalsScored, int goalsReceived, int numOfPoints, int numOfMatches, Date date, String universityName) {
        super(name, location, wins, draws, defeats, goalsScored, goalsReceived, numOfPoints, numOfMatches, date);
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "University FootballClub [University Name:" + universityName +"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UniversityFootballClub ufc = (UniversityFootballClub) obj;
        return universityName.equals(ufc.universityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityName);
    }
}
