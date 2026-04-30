package com.scoutmarket;

import org.springframework.stereotype.Service;

@Service
public class ValuationService {

    public void evaluatePlayer(Player player) {
        // 1. Calculate Estimated Market Value
        long baseValue = 10_000_000L; // Every pro player is worth at least 10M
        long goalValue = player.getGoals() * 5_000_000L;
        long assistValue = player.getAssists() * 3_000_000L;
        
        long calculatedValue = baseValue + goalValue + assistValue;

        // 2. Apply Age Premium/Penalty
        if (player.getAge() > 0 && player.getAge() <= 23) {
            calculatedValue = (long) (calculatedValue * 1.2); // 20% boost for young talent
        } else if (player.getAge() >= 30) {
            calculatedValue = (long) (calculatedValue * 0.8); // 20% drop for aging players
        }
        
        player.setEstimatedValue(calculatedValue);

        // 3. Calculate ROI Category
        // ROI = (Algorithm Value / Actual Market Value)
        
        if (player.getMarketValue() > 0) {
            double roiRatio = (double) calculatedValue / player.getMarketValue();
            
            if (roiRatio > 1.5) {
                player.setRoiCategory("Elite Bargain");
            } else if (roiRatio >= 0.9) {
                player.setRoiCategory("Fair Value");
            } else {
                player.setRoiCategory("High Risk / Overpriced");
            }
        } else {
            player.setRoiCategory("Unknown Value");
        }
    }
}