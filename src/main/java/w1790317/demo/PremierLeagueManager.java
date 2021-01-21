package w1790317.demo;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class PremierLeagueManager implements LeagueManager {

    /*Uses of following ArrayLists are as follows
     * clubs -> to store a football club with its details
     * names -> to store club names
     * HashMap datelist -> to store club name with its date
     * matches -> to store details about a match*/

    public static ArrayList<FootballClub> clubs = new ArrayList<FootballClub>();
    public static ArrayList<String> names = new ArrayList<>();
    public static HashMap<String, Date> datelist = new HashMap<String, Date>();
    public static ArrayList<Match> matches = new ArrayList<>();
    public static ArrayList<Match> randomMatchDetails = new ArrayList<>();
    public static Date matchDate;

    private static int indexOne;
    private static int indexTwo;
    public static int randomOne;
    public static int randomTwo;
    public static int day = 10;

    /* Assumption - The PremierLeagueManager class maintains
    a number of football clubs (10)
    which play in the premier league.*/
    @Override
    public void addNewClub(FootballClub footballClub) {
        if(clubs.size() >= 10) {
            System.out.println("League Table Full");
        }
        else {
            clubs.add(footballClub);
            System.out.println("---------------------------------------");
            System.out.println("Remaining Slots: " + (10 - clubs.size()));
            System.out.println("---------------------------------------");
        }
    }
    /*
     * Remove a club rom the list using club name*/
    @Override
    public void deleteClub(String clubName) {
        boolean clubExist = false;
        for(FootballClub footballClub : clubs) {
            if (footballClub.getName().equalsIgnoreCase(clubName)) {
                clubExist = true;
                Match match = new Match(footballClub.getName(), footballClub.getDate());
                matches.remove(match);
                names.remove(footballClub.getName());
                datelist.remove(footballClub.getName(),footballClub.getDate());
                clubs.remove(footballClub);
                System.out.println("---------------------------------------");
                System.out.println("Club Removed");
                System.out.println("Remaining Slots: " + (10 - clubs.size()));
                System.out.println("---------------------------------------");
                break;
            }
        }
        if (!clubExist) {
            System.out.println(clubName + " not found");
            System.out.println("---------------------------------------");
        }
    }

    /*This method will show the list of
     * available clubs to the user */
    @Override
    public void displayClub() {
        System.out.println("---------------------------------------");
        System.out.print("Available Clubs in the League: ");
        for (FootballClub footballClub : clubs) {
            System.out.print(footballClub.getName() + "| ");
        }
        System.out.println();
    }

    /*Over-loading above method where the
     * user can view a particular club by entering its name */
    @Override
    public void displayClub(String name) {
        for (FootballClub footballClub : clubs) {
            if(footballClub.getName().equalsIgnoreCase(name)) {
                System.out.println("+----------------------------------------------------------------------------------------------+");
                System.out.println("| Wins | Draws | Defeats | Goals Received | Goals Scored | Points | Matches Played | Last Date |");
                System.out.println("+----------------------------------------------------------------------------------------------+");
                System.out.println("| "+footballClub.getWins()+"    | "+footballClub.getDraws()+"     | "+footballClub.getDefeats()+"  "
                        + "     | "+footballClub.getGoalsReceived()+"              | "+footballClub.getGoalsScored()+""
                        + "            | "+footballClub.getNumOfPoints()+"      | "+footballClub.getNumOfMatches()+"             |"+footballClub.getDate().toString() + " |");
                System.out.println("+------+-------+---------+----------------+--------------+--------+----------------------------+");
            }
        }
    }

    /*This method will show the details of the
     * clubs sorted according to the number of
     * points scored */
    @Override
    public void leagueStatistics() {
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
    }

    /*This method is to update details about
     * a played match between two teams */
    @Override
    public void updateMatch(String clubOne, String clubTwo, int pointsClubOne, int pointsClubTwo, int goalsClubOne, int goalsClubTwo, Date date) {
        for(FootballClub footballClub : clubs) {
            if(footballClub.getName().equalsIgnoreCase(clubOne)) {
                indexOne = clubs.indexOf(footballClub);
                footballClub.setNumOfMatches(footballClub.getNumOfMatches() + 1);
                footballClub.setNumOfPoints(footballClub.getNumOfPoints() + pointsClubOne);
                footballClub.setGoalsScored(footballClub.getGoalsScored() + goalsClubOne);
                footballClub.setDate(date);
            }
            if(footballClub.getName().equalsIgnoreCase(clubTwo)) {
                indexTwo = clubs.indexOf(footballClub);
                footballClub.setNumOfMatches(footballClub.getNumOfMatches() + 1);
                footballClub.setNumOfPoints(footballClub.getNumOfPoints() + pointsClubTwo);
                footballClub.setGoalsScored(footballClub.getGoalsScored() + goalsClubTwo);
                footballClub.setDate(date);
            }
            clubs.get(indexOne).setGoalsReceived(clubs.get(indexTwo).getGoalsScored());
            clubs.get(indexTwo).setGoalsReceived(clubs.get(indexOne).getGoalsScored());
        }
        /*A new match object is created using details
         * of football club represented using indexOne
         * and added to matches, names, dateList*/
        Match matchOne = new Match(clubs.get(indexOne).getName(),clubs.get(indexOne).getDate());
        matches.add(matchOne);
        names.add(clubs.get(indexOne).getName());
        datelist.put(clubs.get(indexOne).getName(), clubs.get(indexOne).getDate());

        /*A new match object is created using details
         * of football club represented using indexTwo
         * and added to matches, names, dateList*/
        Match matchTwo = new Match(clubs.get(indexTwo).getName(),clubs.get(indexTwo).getDate());
        matches.add(matchTwo);
        names.add(clubs.get(indexTwo).getName());
        datelist.put(clubs.get(indexTwo).getName(), clubs.get(indexTwo).getDate());

        FootballClub clubA = clubs.get(indexOne);
        FootballClub clubB = clubs.get(indexTwo);

        if (pointsClubOne > pointsClubTwo) {
            clubA.setWins(clubA.getWins() + 1);
            clubB.setDefeats(clubB.getDefeats() + 1);
        }
        if (pointsClubOne < pointsClubTwo) {
            clubB.setWins(clubB.getWins() + 1);
            clubA.setDefeats(clubA.getDefeats() + 1);

        }
        else if (pointsClubOne == pointsClubTwo) {
            clubA.setDraws(clubA.getDraws() + 1);
            clubB.setDraws(clubB.getDraws() + 1);
        }
    }

    /*This method will save the objects to a
     * text file. */
    @Override
    public void saveData(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for(FootballClub footballClub : clubs) {
            oos.writeObject(footballClub);
        }
        oos.flush();
        fos.close();
        oos.close();
    }

    /* Objects wrote to the file are added
     * to the ArrayList*/
    @Override
    public void loadData(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (true) {
            try {
                FootballClub fc = (FootballClub) ois.readObject();
                clubs.add(fc);
            } catch (EOFException e) {
                break;
            }
        }
        fis.close();
        ois.close();
    }

    @Override
    public List<FootballClub> getClubList() {
        return clubs;
    }

    @Override
    public List<Match> matchDates() {
        return matches;
    }

    @Override
    public List<Match> randomMatchDetails() {
        return randomMatchDetails;
    }

    /* Sort according to wins*/
    @Override
    public List<FootballClub> sortWins() {
        List<FootballClub> footballClubList = new ArrayList<FootballClub>();
        for(FootballClub footballClub : getClubList()) {
            footballClubList.add(footballClub);
        }
        Collections.sort(footballClubList, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return o2.getWins() - o1.getWins();
            }
        });
        return footballClubList;
    }
    /* Sort according to goals*/
    @Override
    public List<FootballClub> sortGoals() {
        List<FootballClub> footballClubList = new ArrayList<FootballClub>();
        for(FootballClub footballClub : getClubList()) {
            footballClubList.add(footballClub);
        }
        Collections.sort(footballClubList, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return o2.getGoalsScored() - o1.getGoalsScored();
            }
        });
        return footballClubList;
    }

    /* Sort according to date played*/
    @Override
    public List<FootballClub> sortDates() {
        List<FootballClub> footballClubList = new ArrayList<FootballClub>();
        for(FootballClub footballClub : getClubList()) {
            footballClubList.add(footballClub);
        }
        Collections.sort(footballClubList, new Comparator<FootballClub>() {
            @Override
            public int compare(FootballClub o1, FootballClub o2) {
                return o1.getDate().toString().compareTo(o2.getDate().toString());
            }
        });
        return footballClubList;
    }

    /* Sort according to points*/
    @Override
    public List<FootballClub> sortPoints() {
        Collections.sort(clubs);
        return clubs;
    }

    /* Sort according to dates in Match class*/
    @Override
    public List<Match> sortMatchDates() {
        List<Match> matcheDates = new ArrayList<Match>();
        for(Match match : matches) {
            matcheDates.add(match);
        }
        Collections.sort(matcheDates, new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                return o1.getDate().toString().compareTo(o2.getDate().toString());
            }
        });
        return matcheDates;
    }

    /*Saving to a file*/
    @Override
    public void saveMatch(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for(Match match : matches) {
            oos.writeObject(match);
        }
        oos.flush();
        fos.close();
        oos.close();
    }

    /*Loading from a file*/
    @Override
    public void loadMatch(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        while (true) {
            try {
                Match match = (Match) ois.readObject();
                matches.add(match);
            } catch (EOFException e) {
                break;
            }
        }
        fis.close();
        ois.close();
    }

    @Override
    public void loadName() {
        for(FootballClub footballClub : clubs) {
            names.add(footballClub.getName());
        }
    }

    @Override
    public void loadDate() {
        for(FootballClub footballClub : clubs) {
            datelist.put(footballClub.getName(), footballClub.getDate());
        }
    }

    @Override
    public List<FootballClub> randomMatch() {

        /*Built in random() is not used because there is a chance
         * to generate same random at once for two variables*/

        /*It is assumed that maximum of 6 goals can only scored in a match
         * by one time*/

        int goalsMin = 0;
        int goalsMax = 6;

        /*An ArrayList type of integer is created
         * to store the number(amount) of clubs */
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i< clubs.size(); i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        randomOne = list.get(0);
        randomTwo = list.get(1);

        int randomOneGoals = (int) (Math.random() * (goalsMax - goalsMin + 1) + goalsMin);
        int randomTwoGoals = (int) (Math.random() * (goalsMax - goalsMin + 1) + goalsMin);

        FootballClub A = clubs.get(randomOne);
        FootballClub B = clubs.get(randomTwo);
        day++;

        matchDate = new Date(day,12,2020);

        if(randomOneGoals > randomTwoGoals) {
            A.setWins(A.getWins() + 1);
            A.setNumOfMatches(A.getNumOfMatches() + 1);
            A.setGoalsScored(A.getGoalsScored() + randomOneGoals);
            A.setNumOfPoints(A.getWins() *2);
            A.setDate(matchDate);

            B.setDefeats(B.getDefeats() + 1);
            B.setNumOfMatches(B.getNumOfMatches() + 1);
            B.setGoalsScored(B.getGoalsScored() + randomTwoGoals);
            B.setNumOfPoints(B.getWins() *2);
            B.setDate(A.getDate());
        }
        else if(randomOneGoals < randomTwoGoals) {
            B.setWins(B.getWins() + 1);
            B.setNumOfMatches(B.getNumOfMatches() + 1);
            B.setGoalsScored(B.getGoalsScored() + randomOneGoals);
            B.setNumOfPoints(B.getWins() * 2);
            B.setDate(matchDate);

            A.setNumOfMatches(A.getNumOfMatches() + 1);
            A.setDefeats(A.getDefeats() + 1);
            A.setGoalsScored(A.getGoalsScored() + randomTwoGoals);
            A.setNumOfPoints(A.getWins() * 2);
            A.setDate(B.getDate());
        }
        else {
            A.setNumOfMatches(A.getNumOfMatches() + 1);
            B.setNumOfMatches(B.getNumOfMatches() + 1);
            A.setGoalsScored(A.getGoalsScored() + randomOneGoals);
            B.setGoalsScored(B.getGoalsScored() + randomTwoGoals);
            A.setDraws(A.getDraws() + 1);
            B.setDraws(B.getDraws() + 1);
            A.setDate(matchDate);
            B.setDate(A.getDate());
        }
        String clubOne = clubs.get(randomOne).getName();
        Date dateClubOne = clubs.get(randomOne).getDate();
        int clubOnePoints = clubs.get(randomOne).getNumOfPoints();
        Match matchOne = new Match(clubOne,dateClubOne);
        matches.add(matchOne);

        String clubTwo = clubs.get(randomTwo).getName();
        Date dateClubTwo = clubs.get(randomTwo).getDate();
        int clubTwoPoints = clubs.get(randomTwo).getNumOfPoints();
        Match matchTwo = new Match(clubTwo,dateClubTwo);
        matches.add(matchTwo);

        Match randomOne = new Match(clubOne,dateClubOne,clubOnePoints);
        Match randomTwo = new Match(clubTwo, dateClubTwo, clubTwoPoints);

        randomMatchDetails.clear();
        randomMatchDetails.add(randomOne);
        randomMatchDetails.add(randomTwo);

        return clubs;
    }
}

