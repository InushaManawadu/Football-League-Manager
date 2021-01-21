package w1790317.demo;

import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable {

    private static final long serialVersionUID = -9140733667711958936L;
    private int day;
    private int month;
    private int year;

    public Date() {

    }

    public Date(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day>=1 && day<=31) {
            this.day = day;
        }
        else {
            throw new IllegalArgumentException("Day should be an integer between 1 and 31.");
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            throw new IllegalArgumentException("Month should be an integer between 1 and 12.");
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return year + "-" +month + "-" + day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

}
