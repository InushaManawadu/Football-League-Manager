package w1790317.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PremierLeagueManagerTest {

    private List<FootballClub> clubs = new ArrayList<FootballClub>();
    private List<Match> matches = new ArrayList<Match>();

    @Test
    void addNewClub() {
        clubs.add(new FootballClub("Colombo","colombo"));
        clubs.add(new FootballClub("Kandy","kandy"));
        clubs.add(new FootballClub("Galle","galle"));
        assertEquals(3,clubs.size());
    }

    @Test
    void deleteClub() {
        clubs.add(new FootballClub("Colombo","colombo"));
        clubs.add(new FootballClub("Kandy","kandy"));
        clubs.add(new FootballClub("Galle","galle"));
        clubs.remove(2);
        assertEquals(2, clubs.size());
    }

    @Test
    void displayClub() {
        clubs.add(new FootballClub("Colombo","colombo"));
        clubs.add(new FootballClub("Kandy","kandy"));
        clubs.add(new FootballClub("Galle","galle"));
        for (FootballClub footballClub : clubs) {
            System.out.println(footballClub.getName());
        }
        assertEquals(3, clubs.size());
    }

    @Test
    void leagueStatistics() {
        Date date = new Date(10,12,2020);
        clubs.add(new FootballClub("Colombo","colombo",1,0,0,2,1,2,1,date));
        clubs.add(new FootballClub("kandy","kandy",0,0,1,1,2,2,1,date));
        Collections.sort(clubs);
        String format = "| %-13s | %-4d | %-4d | %-4d | %-4d | %-4d  | %-4d  |%n";
        System.out.printf("+---------------+------+------+------+------+-------+-------+%n");
        System.out.printf("| Club Name     |Points|Goals |Wins  |Defeats|Draws |Matches|%n");
        System.out.printf("+---------------+------+------+-------------+-------+-------+%n");
        for(FootballClub footballClub : clubs) {
            System.out.printf(format,  footballClub.getName(), footballClub.getNumOfPoints(), footballClub.getGoalsScored(),footballClub.getWins(),footballClub.getDefeats(),
                    footballClub.getDraws(),footballClub.getNumOfMatches());
        }
        System.out.printf("+---------------+------+------+------+------+-------+-------+%n");
        assertEquals(2, clubs.size());
    }

    @Test
    void updateMatch() {
        Date date = new Date(10,12,2020);
        clubs.add(new FootballClub("Colombo","colombo"));
        clubs.add(new FootballClub("Kandy","kandy"));
        String format = "| %-13s | %-4d | %-4d | %-4d | %-4d | %-4d  | %-4d  |%n";
        System.out.printf("+---------------+------+------+------+------+-------+-------+%n");
        System.out.printf("| Club Name     |Points|Goals |Wins  |Defeats|Draws |Matches|%n");
        System.out.printf("+---------------+------+------+-------------+-------+-------+%n");
        for(FootballClub footballClub : clubs) {
            System.out.printf(format,  footballClub.getName(), footballClub.getNumOfPoints(), footballClub.getGoalsScored(),footballClub.getWins(),footballClub.getDefeats(),
                    footballClub.getDraws(),footballClub.getNumOfMatches());
        }
        System.out.printf("+---------------+------+------+------+------+-------+-------+%n");
        assertEquals(2, clubs.size());

        System.out.printf("+---------------+------+------+------+------+-------+-------+%n");
        System.out.printf("| Club Name     |Points|Goals |Wins  |Defeats|Draws |Matches|%n");
        System.out.printf("+---------------+------+------+-------------+-------+-------+%n");
        for(FootballClub footballClub : clubs) {
            if(footballClub.getName().equalsIgnoreCase("Kandy")) {
                footballClub.setNumOfMatches(1);
                footballClub.setDefeats(1);
                footballClub.setNumOfPoints(0);
                footballClub.setGoalsScored(2);
                footballClub.setGoalsReceived(3);
                footballClub.setDate(date);
                System.out.printf(format,  footballClub.getName(), footballClub.getNumOfPoints(), footballClub.getGoalsScored(),footballClub.getWins(),footballClub.getDefeats(),
                        footballClub.getDraws(),footballClub.getNumOfMatches());
            }
            if(footballClub.getName().equalsIgnoreCase("Colombo")) {
                footballClub.setNumOfMatches(1);
                footballClub.setWins(1);
                footballClub.setNumOfPoints(2);
                footballClub.setGoalsScored(3);
                footballClub.setGoalsReceived(2);
                footballClub.setDate(date);
                System.out.printf(format,  footballClub.getName(), footballClub.getNumOfPoints(), footballClub.getGoalsScored(),footballClub.getWins(),footballClub.getDefeats(),
                        footballClub.getDraws(),footballClub.getNumOfMatches());
            }
        }
        System.out.printf("+---------------+------+------+------+------+-------+-------+%n");
        assertEquals(2, clubs.size());
    }

    @Test
    void getClubList() {
        clubs.add(new FootballClub("Colombo","colombo"));
        clubs.add(new FootballClub("Kandy","kandy"));
        assertEquals(2, clubs.size());
    }

    @Test
    void matchDates() {
        Date date = new Date(10,12,2020);
        clubs.add(new FootballClub("Colombo","colombo",1,0,0,2,1,2,1,date));
        Match matchOne = new Match(clubs.get(0).getName(),clubs.get(0).getDate());
        matches.add(matchOne);
        assertEquals(1, matches.size());
    }

    @Test
    void sortWins() {
        Date date = new Date(10,12,2020);
        clubs.add(new FootballClub("kandy","kandy",0,0,1,1,2,2,1,date));
        clubs.add(new FootballClub("Colombo","colombo",1,0,0,2,1,2,1,date));
        System.out.println("Before Sorting");
        System.out.println(clubs.get(0).getName() + " " + clubs.get(0).getWins());
        System.out.println(clubs.get(1).getName() + " " + clubs.get(1).getWins());
        List<FootballClub> footballClubList = new ArrayList<FootballClub>();
        for(FootballClub footballClub : clubs) {
            footballClubList.add(footballClub);
        }
        Collections.sort(footballClubList, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return o2.getWins() - o1.getWins();
            }
        });
        System.out.println("After Sorting");
        System.out.println(footballClubList.get(0).getName() + " " + footballClubList.get(0).getWins());
        System.out.println(footballClubList.get(1).getName() + " " + footballClubList.get(1).getWins());
        assertEquals(1, footballClubList.get(0).getWins());
        assertEquals(0, footballClubList.get(1).getWins());
    }

    @Test
    void sortGoals() {
        Date date = new Date(10,12,2020);
        clubs.add(new FootballClub("kandy","kandy",0,0,1,1,2,2,1,date));
        clubs.add(new FootballClub("Colombo","colombo",1,0,0,2,1,2,1,date));
        System.out.println("Before Sorting");
        System.out.println(clubs.get(0).getName() + " " + clubs.get(0).getGoalsScored());
        System.out.println(clubs.get(1).getName() + " " + clubs.get(1).getGoalsScored());
        List<FootballClub> footballClubList = new ArrayList<FootballClub>();
        for(FootballClub footballClub : clubs) {
            footballClubList.add(footballClub);
        }
        Collections.sort(footballClubList, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return o2.getGoalsScored() - o1.getGoalsScored();
            }
        });
        System.out.println("After Sorting");
        System.out.println(footballClubList.get(0).getName() + " " + footballClubList.get(0).getGoalsScored());
        System.out.println(footballClubList.get(1).getName() + " " + footballClubList.get(1).getGoalsScored());
        assertEquals(2, footballClubList.get(0).getGoalsScored());
        assertEquals(1, footballClubList.get(1).getGoalsScored());
    }

    @Test
    void sortDates() {
        Date date1 = new Date(10,12,2020);
        Date date2 = new Date(11,12,2020);
        clubs.add(new FootballClub("kandy","kandy",0,0,1,1,2,2,1,date2));
        clubs.add(new FootballClub("Colombo","colombo",1,0,0,2,1,2,1,date1));
        System.out.println("-------Before Sorting-------");
        System.out.println(clubs.get(0).getName() + " " + clubs.get(0).getDate().toString());
        System.out.println(clubs.get(1).getName() + " " + clubs.get(1).getDate().toString());
        List<FootballClub> footballClubList = new ArrayList<FootballClub>();
        for(FootballClub footballClub : clubs) {
            footballClubList.add(footballClub);
        }
        Collections.sort(footballClubList, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return o1.getDate().toString().compareTo(o2.getDate().toString());
            }
        });
        System.out.println("-------After Sorting--------");
        System.out.println(footballClubList.get(0).getName() + " " + footballClubList.get(0).getDate().toString());
        System.out.println(footballClubList.get(1).getName() + " " + footballClubList.get(1).getDate().toString());
        assertEquals("2020-12-10", footballClubList.get(0).getDate().toString());
        assertEquals("2020-12-11", footballClubList.get(1).getDate().toString());
    }

    @Test
    void sortPoints() {
    }

    @Test
    void sortMatchDates() {
    }

}