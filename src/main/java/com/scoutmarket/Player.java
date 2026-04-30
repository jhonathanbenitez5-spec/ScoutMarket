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
    private int age;
    private String nationality;
    private int gamesPlayed;
    private String position;
    private long estimatedValue;
    private String roiCategory;
    private String height;
    private int yellowCards;
    private int redCards;

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
        this.age = 0;
        this.nationality = "Unknown";
        this.gamesPlayed = 0;
        this.position = "Unknown";
        this.estimatedValue = 0L;
        this.roiCategory = "Pending";

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
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public int getGamesPlayed() { return gamesPlayed; }
    public void setGamesPlayed(int gamesPlayed) { this.gamesPlayed = gamesPlayed; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public long getEstimatedValue() { return estimatedValue; }
    public void setEstimatedValue(long estimatedValue) { this.estimatedValue = estimatedValue; }

    public String getRoiCategory() { return roiCategory; }
    public void setRoiCategory(String roiCategory) { this.roiCategory = roiCategory; }

    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height; }

    public int getYellowCards() { return yellowCards; }
    public void setYellowCards(int yellowCards) { this.yellowCards = yellowCards; }

    public int getRedCards() { return redCards; }
    public void setRedCards(int redCards) { this.redCards = redCards; }

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
