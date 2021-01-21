package w1790317.demo;

import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable {

    public static final long serialVersionUID = 12344567788655L;
    private int wins;
    private int draws;
    private int defeats;
    private int goalsScored;
    private int goalsReceived;
    private int numOfPoints;
    private int numOfMatches;
    private  Date date;

    public FootballClub() {

    }

    public FootballClub(String name, String location) {
        super(name,location);
    }

    public FootballClub(String name, String location, int wins, int draws, int defeats, int goalsScored, int goalsReceived, int numOfPoints, int numOfMatches, Date date) {
        super(name, location);
        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
        this.numOfPoints = numOfPoints;
        this.numOfMatches = numOfMatches;
        this.date = date;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    public int getNumOfMatches() {
        return numOfMatches;
    }

    public void setNumOfMatches(int numOfMatches) {
        this.numOfMatches = numOfMatches;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FootballClub [Wins:" + wins + "| Draws:"+ draws + "| Defeats:" + defeats + "| Goals Scored:" +
                goalsScored + "| Goals Received:" + goalsReceived + "| No of Matches:" + numOfMatches + "| No of Points:" + numOfPoints + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        FootballClub that = (FootballClub) obj;
        return goalsScored == that.goalsScored &&
                numOfPoints == that.numOfPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalsScored, numOfPoints);
    }

    @Override
    public int compareTo(FootballClub fc) {
        if (this.numOfPoints != fc.numOfPoints) {
            return fc.numOfPoints - this.numOfPoints;
        } else {
            return fc.goalsScored - this.goalsScored;
        }
    }
}

