package w1790317.demo;

import java.io.Serializable;
import java.util.Objects;

public class SportsClub implements Serializable {

    private String name;
    private String location;

    public SportsClub() {

    }

    public SportsClub(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SportsClub [Name:" + name + "| Location:" + location + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SportsClub sc = (SportsClub) obj;
        return name.equals(sc.name) &&
                location.equals(sc.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

