package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        QueryBuilder query = new QueryBuilder();
        Matcher m = query.playsIn("NYR")
                .hasAtLeast(10, "goals")
                .hasFewerThan(25, "goals").build();
        
        int howManyPlayers = 0;
        for (Player player : stats.matches(m)) {
            System.out.println( player );
            howManyPlayers++;
        }
        
        System.out.println("\nThis many players found: " + howManyPlayers);
    }
}
