public class Player implements Comparable<Player>{

    private int playerID;
    private String name;
    private String team;
    private int goals;
    private int assists;
    private long marketValue;
    private double performanceScore;

    //constructor
    public Player (int playerID, String name, String team, long marketValue){
        this.playerID = playerID;
        this.name= name;
        this.team = team;
        this.marketValue = marketValue;

        // set the value to zero
        this.goals = 0;
        this.assists = 0;
        this.performanceScore = 0.0;

    }

    public void updatePerfomance(int newGoals, int newAssists){
        this.goals = newGoals;
        this.assists = newAssists;

        // we use "10.0" and "5.0" to tell Java we want a Decimal (double), not a whole number.
        double quality = (this.goals * 10.0) + (this.assists * 5.0);    
    
        // This calculates the ROI 
        this.performanceScore = (quality / this.marketValue) * 1_000_000;
    
    }

    // Getter methods: These allow other classes to "Read" the data safely.
    public int getPlayerID() { return playerID; }
    public String getName() { return name; }
    public double getPerformanceScore() { return performanceScore; }

    @Override
    public int compareTo(Player other) {
        // Returns 1 if this player is better, -1 if worse, 0 if equal.
        return Double.compare(this.performanceScore, other.performanceScore);
    }

    // toString: This tells Java how to "Print" the player to the console.
    @Override
    public String toString() {
        return String.format("%s (%s) | ID: %d | Score: %.2f | Value: €%,d", 
                name, team, playerID, performanceScore, marketValue);
    }

        
    
}
