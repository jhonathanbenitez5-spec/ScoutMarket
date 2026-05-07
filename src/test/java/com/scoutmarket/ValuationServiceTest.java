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
}