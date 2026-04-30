package com.scoutmarket;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FootballApiService {

    private static final String API_KEY = "de13b1508ffcbe2feda588f2ec5b8188"; 
    private static final String API_HOST = "v3.football.api-sports.io";
    private static final String BASE_URL_PLAYERS = "https://v3.football.api-sports.io/players?";

    private HttpEntity<String> createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", API_KEY);
        headers.set("x-rapidapi-host", API_HOST);
        return new HttpEntity<>(headers);
    }
    
    public JsonNode getTeamsByLeague(int leagueId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://v3.football.api-sports.io/teams?league=" + leagueId + "&season=2023";
        return restTemplate.exchange(url, HttpMethod.GET, createAuthHeaders(), JsonNode.class).getBody();
    }

    public JsonNode getSquadByTeam(int teamId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://v3.football.api-sports.io/players/squads?team=" + teamId;
        return restTemplate.exchange(url, HttpMethod.GET, createAuthHeaders(), JsonNode.class).getBody();
    }

    public boolean fetchAndApplyLiveStats(Player player) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String safeUrl = "https://v3.football.api-sports.io/players?" 
            + "id=" + player.getPlayerID() 
            + "&season=" + player.getSeason();
            
ResponseEntity<JsonNode> response = restTemplate.exchange(
     safeUrl, 
     HttpMethod.GET, 
     createAuthHeaders(), 
     JsonNode.class
);

            JsonNode rootTree = response.getBody();

            if (rootTree != null && rootTree.path("results").asInt(0) > 0) {
                
                JsonNode firstResult = rootTree.path("response").get(0);
                JsonNode playerNode = firstResult.path("player");
                JsonNode statsNode = firstResult.path("statistics").get(0);
                
                player.setAge(playerNode.path("age").asInt(0));
                player.setNationality(playerNode.path("nationality").asText("Unknown"));
                
                String rawHeight = playerNode.path("height").asText("");
                if (rawHeight.length() == 3) {
                    player.setHeight(rawHeight.charAt(0) + "." + rawHeight.substring(1) + " m");
                } else {
                    player.setHeight(rawHeight.isEmpty() ? "N/A" : rawHeight + " cm");
                }

                player.setGamesPlayed(statsNode.path("games").path("appearences").asInt(0));
                player.setPosition(statsNode.path("games").path("position").asText("Unknown"));
                
                int liveGoals = statsNode.path("goals").path("total").asInt(0);
                int liveAssists = statsNode.path("goals").path("assists").asInt(0); 
                
                player.updatePerfomance(liveGoals, liveAssists);
                
                player.setYellowCards(statsNode.path("cards").path("yellow").asInt(0));
                player.setRedCards(statsNode.path("cards").path("red").asInt(0));
                
                System.out.println(" Successfully scouted " + player.getName() + " with deep profile stats!");
                return true;
            } else {
                System.out.println(" API found no data for: " + player.getName());
                return false;
            }

        } catch (Exception e) {
            System.err.println(" API Connection Failed: " + e.getMessage());
            return false;
        }
    }
}