package w1790317.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class FootballRestController {

    @Autowired
    PremierLeagueManager premierLeagueManager;

    @GetMapping("/clubs")
    public List<FootballClub> getAllClubs() {
        return premierLeagueManager.getClubList();
    }

    @GetMapping("/random")
    public List<FootballClub> randomMatch() {
        return premierLeagueManager.randomMatch();
    }

    @GetMapping("/sortByWins")
    public List<FootballClub> sortWins() {
        return premierLeagueManager.sortWins();
    }

    @GetMapping("/sortByDate")
    public List<FootballClub> sortDates() {
        return premierLeagueManager.sortDates();
    }

    @GetMapping("/sortByGoals")
    public List<FootballClub> sortGoals() {
        return premierLeagueManager.sortGoals();
    }

    @GetMapping("/sortByPoints")
    public List<FootballClub> sortpoints() {
        return premierLeagueManager.sortPoints();
    }

    @GetMapping("/randomMatchDetails")
    public List<Match> randomMatchDetails() {
        return premierLeagueManager.randomMatchDetails();
    }

    @GetMapping("/matchDates")
    public List<Match> matchDates() {
        return premierLeagueManager.matchDates();
    }

    @GetMapping("/sortMatchDates")
    public List<Match> sortMatchDates() {
        return premierLeagueManager.sortMatchDates();
    }
}
