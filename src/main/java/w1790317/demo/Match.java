package w1790317.demo;

import java.io.Serializable;

public class Match implements Serializable {

    private String name;
    private Date date;
    private int points;
    private static final long serialVersionUID = -9140733667711958936L;

    public Match() {

    }

    public Match(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Match(String name, Date date, int points) {
        this.name = name;
        this.date = date;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Match{" + "name='" + name + '\'' + ", date=" + date + '}';
    }
}
