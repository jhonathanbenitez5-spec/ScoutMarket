package com.scoutmarket;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValuationServiceTest {

    @Test
    public void testRoiMath() {
        Player testPlayer = new Player();
        testPlayer.setEstimatedValue(12000000); 
        
        double userOffer = 10000000;
        // 2. Calculate the ROI
        double expectedRoi = 1.20;
        double actualRoi = testPlayer.getEstimatedValue() / userOffer;
        // 3. Assert:MUST be true to pass
        assertEquals(expectedRoi, actualRoi, 0.01, "ROI Math is broken!");
    }
    @Test
    public void testYoungPlayerPremium() {
        ValuationService service = new ValuationService();
        Player youngPlayer = new Player();
        youngPlayer.setGoals(2); // 10M base + 10M goals = 20M
        youngPlayer.setAssists(0);
        youngPlayer.setAge(21); // Should get 20% premium (20M * 1.2 = 24M)
        
        service.evaluatePlayer(youngPlayer);
        assertEquals(24000000L, youngPlayer.getEstimatedValue(), "Young player premium failed!");
    }
    @Test
    public void testAgingPlayerPenalty() {
        ValuationService service = new ValuationService();
        Player oldPlayer = new Player();
        oldPlayer.setGoals(2); // 10M base + 10M goals = 20M
        oldPlayer.setAssists(0);
        oldPlayer.setAge(32); // Should get 20% penalty (20M * 0.8 = 16M)
        
        service.evaluatePlayer(oldPlayer);
        assertEquals(16000000L, oldPlayer.getEstimatedValue(), "Aging player penalty failed!");
    }

    @Test
    public void testRoiCategoryEliteBargain() {
        ValuationService service = new ValuationService();
        Player bargainPlayer = new Player();
        bargainPlayer.setGoals(10); // 10M base + 50M goals = 60M
        bargainPlayer.setAge(25); // No age modifier. Value = 60M
        bargainPlayer.setMarketValue(30000000L); // User offers 30M
        
        service.evaluatePlayer(bargainPlayer);
        // ROI = 60M / 30M = 2.0 (Greater than 1.5, so Elite Bargain)
        assertEquals("Elite Bargain", bargainPlayer.getRoiCategory(), "ROI Category assignment failed!");
    }
}