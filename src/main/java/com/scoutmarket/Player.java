package com.scoutmarket;



public class Player implements Comparable<Player>{

    private int playerID;
    private String name;
    private String team;
    private int leagueId;
    private int season;
    private int goals;
    private int assists;
    private long marketValue;
    private double performanceScore;

    public Player() {
    }

    //constructor
    public Player (int playerID, String name, String team, int leagueId,int season,long marketValue){
        this.playerID = playerID;
        this.name= name;
        this.team = team;
        this.leagueId = leagueId;
        this.season = season;
        this.marketValue = marketValue;
        this.goals = 0;
        this.assists = 0;
        this.performanceScore = 0.0;

    }
    public int getLeagueId() { return leagueId; }
    public int getSeason() { return season; }

    public void updatePerfomance(int newGoals, int newAssists){
        this.goals = newGoals;
        this.assists = newAssists;

        double quality = (this.goals * 10.0) + (this.assists * 5.0);    
    
        // This calculates the ROI 
        this.performanceScore = (quality / this.marketValue) * 1_000_000;
    
    }

    // Getter methods: These allow other classes to readthe data safely.
    public int getPlayerID() { return playerID; }
    public String getName() { return name; }
    public double getPerformanceScore() { return performanceScore; }

    @Override
    public int compareTo(Player other) {
        // Returns 1 if this player is better, -1 if worse, 0 if equal.
        return Double.compare(this.performanceScore, other.performanceScore);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) | ID: %d | Score: %.2f | Value: €%,d", 
                name, team, playerID, performanceScore, marketValue);
    }

    //  JSON converter to show the data
    public String getTeam() { return team; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
    public long getMarketValue() { return marketValue; }
    
}
