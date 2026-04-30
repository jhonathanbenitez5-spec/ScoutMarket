package com.scoutmarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "http://localhost:5173") 
public class PlayerController {

    @Autowired
    private FootballApiService apiService;

    @Autowired
    private ValuationService valuationService;

    private AVLTree tree = new AVLTree();
    private MaxHeap heap = new MaxHeap(100);

    @PostConstruct
    public void loadTestData() {
        // --- LIONEL MESSI ---
        Player p1 = new Player(10, "Lionel Messi", "Inter Miami CF", 253, 2023, 35000000L);
        p1.updatePerfomance(12, 14); 
        p1.setAge(36);
        p1.setNationality("Argentina");
        p1.setPosition("Right Winger");
        p1.setGamesPlayed(35);
        p1.setHeight("1.70 m");
        p1.setYellowCards(2);
        p1.setRedCards(0);
        
        valuationService.evaluatePlayer(p1); 
        
        // --- VINICIUS JR ---
        Player p2 = new Player(7, "Vinicius Jr", "Real Madrid", 140, 2023, 150000000L);
        p2.updatePerfomance(15, 8);
        p2.setAge(23);
        p2.setNationality("Brazil");
        p2.setPosition("Left Winger");
        p2.setGamesPlayed(42);
        p2.setHeight("1.76 m");
        p2.setRedCards(0);
        
        valuationService.evaluatePlayer(p2); 

        // Save to data structures
        tree.insert(p1); 
        heap.insert(p1);
        
        tree.insert(p2); 
        heap.insert(p2);
    }

    @PostMapping("/scout")
    public ResponseEntity<?> scoutNewPlayer(@RequestBody Player requestPlayer) {
        
        boolean isFound = apiService.fetchAndApplyLiveStats(requestPlayer);
        
        if (!isFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Player '" + requestPlayer.getName() + "' was not found. Please check the spelling.")
            );
        }
        
        valuationService.evaluatePlayer(requestPlayer);
        tree.insert(requestPlayer);
        heap.insert(requestPlayer);
        
        return ResponseEntity.ok(requestPlayer);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<Player>> getLeaderboard() {
        List<Player> allPlayers = new ArrayList<>();
        MaxHeap tempHeap = new MaxHeap(100);
        
        for (int i = 0; i < heap.getSize(); i++) {
            tempHeap.insert(heap.getHeap()[i]);
        }
        
        int totalScouted = tempHeap.getSize();
        for (int i = 0; i < totalScouted; i++) {
            allPlayers.add(tempHeap.extractMax());
        }
        
        return ResponseEntity.ok(allPlayers);
    }
    @GetMapping("/teams/{leagueId}")
    public ResponseEntity<JsonNode> getTeams(@PathVariable int leagueId) {
        return ResponseEntity.ok(apiService.getTeamsByLeague(leagueId));
    }

    @GetMapping("/squad/{teamId}")
    public ResponseEntity<JsonNode> getSquad(@PathVariable int teamId) {
        return ResponseEntity.ok(apiService.getSquadByTeam(teamId));
    }
}