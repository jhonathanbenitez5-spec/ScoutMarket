package com.scoutmarket;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/* Football API Connection */
@Service
public class FootballApiService {

    
    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String API_HOST = "v3.football.api-sports.io";
    private static final String BASE_URL = "https://v3.football.api-sports.io/players?";

    /**
     @param player The player we want to scout.
     */
    public void fetchAndApplyLiveStats(Player player) {
        
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", API_KEY);
        headers.set("x-rapidapi-host", API_HOST);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

       try {
            String safeUrl = BASE_URL 
                           + "season=" + player.getSeason()
                           + "&league=" + player.getLeagueId() 
                           + "&search=" + player.getName().replace(" ", "%20");
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    safeUrl, 
                    HttpMethod.GET, 
                    requestEntity, 
                    JsonNode.class
            );

            JsonNode rootTree = response.getBody();

            // 🔥 TEMPORARY DEBUG LOG: Print the exact text the API sends us
            System.out.println("\n--- RAW API RESPONSE ---");
            System.out.println(rootTree.toPrettyString());
            System.out.println("------------------------\n");

            if (rootTree != null && rootTree.path("results").asInt(0) > 0) {
                
                JsonNode stats = rootTree.path("response").get(0).path("statistics").get(0);
                
                int liveGoals = stats.path("goals").path("total").asInt(0);
                int liveAssists = stats.path("assists").asInt(0);

                // 6. Here I update the  ROI math in Player.java)
                player.updatePerfomance(liveGoals, liveAssists);
                
                System.out.println("✅ Successfully scouted " + player.getName() + ": " + liveGoals + "G, " + liveAssists + "A");
            } else {
                System.out.println("⚠️ API found no data for: " + player.getName());
            }

        } catch (Exception e) {
            System.err.println(" API Connection Failed: " + e.getMessage());
        }
    }
}