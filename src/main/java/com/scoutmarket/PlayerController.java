package com.scoutmarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import java.util.List;


@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final AVLTree tree;
    private final MaxHeap heap;
    private final FootballApiService apiService;


    @Autowired
    public PlayerController(AVLTree tree, MaxHeap heap, FootballApiService apiService) {
        this.tree = tree;
        this.heap = heap;
        this.apiService = apiService;
    }

    @PostConstruct
    public void loadTestData() {
        // Adding 2023 for Messi
        Player p1 = new Player(10, "Lionel Messi", "Inter Miami", 253, 2023, 35000000);
        p1.updatePerfomance(12, 14); 
        
        // Adding 2023 for Vini Jr
        Player p2 = new Player(7, "Vinicius Jr", "Real Madrid", 140, 2023, 150000000);
        p2.updatePerfomance(15, 8);

        tree.insert(p1); heap.insert(p1);
        tree.insert(p2); heap.insert(p2);
    }

    @GetMapping("/leaderboard")
    public List<Player> getLeaderboard() {
        return heap.getTopPlayers(5);
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable int id) {
        return tree.search(id);
    }

    /**
     * Endpoint to add a new player. 
     * The user sends a JSON player object, we fetch their real stats, calculate ROI, and save them.
     * * @param newPlayer The player object mapped from the incoming JSON request.
     * @return The fully updated player object sent back to the user.
     */
    @PostMapping("/scout")
    public Player scoutNewPlayer(@RequestBody Player newPlayer) {
        
        // 1. Fetch live stats from the internet
        apiService.fetchAndApplyLiveStats(newPlayer);
        
        // 2. Save the fully updated player to your data structures
        tree.insert(newPlayer);
        heap.insert(newPlayer);
        
        // 3. Return the result to the browser/frontend
        return newPlayer;
    }
}