package w1790317.demo;

import java.io.IOException;
import java.util.List;

public interface LeagueManager {

    void addNewClub(FootballClub footballClub);

    void deleteClub(String clubName);

    void displayClub();

    void displayClub(String name);

    void leagueStatistics();

    void updateMatch(String clubOne, String clubTwo, int pointsClubOne, int pointsClubTwo, int goalsClubOne, int goalsClubTwo, Date date);

    void saveData(String fileName) throws IOException;

    void loadData(String fileName) throws IOException, ClassNotFoundException;

    List<FootballClub> getClubList();

    List<FootballClub> sortWins();

    List<FootballClub> sortGoals();

    List<FootballClub> sortDates();

    List<FootballClub> sortPoints();

    List<Match> matchDates();

    List<Match> randomMatchDetails();

    List<Match> sortMatchDates();

    void saveMatch(String fileName) throws IOException;

    void loadMatch(String fileName) throws IOException, ClassNotFoundException;

    void loadName();

    void loadDate();

    List<FootballClub> randomMatch();
}
