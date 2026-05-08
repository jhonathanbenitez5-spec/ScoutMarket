package com.scoutmarket;

import org.springframework.stereotype.Service;

@Service
public class ScoutingFacade {

    private final FootballApiService apiService;
    private final ValuationService valuationService;

    public ScoutingFacade(FootballApiService apiService, ValuationService valuationService) {
        this.apiService = apiService;
        this.valuationService = valuationService;
    }

    public Player scoutAndEvaluatePlayer(Player player) {
        boolean apiSuccess = apiService.fetchAndApplyLiveStats(player);
        
        if (apiSuccess) {
            valuationService.evaluatePlayer(player);
        } else {
            player.setRoiCategory("API Fetch Failed");
        }
        
        return player;
    }
}