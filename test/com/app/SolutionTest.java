package com.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    public void aliceGreaterThanBob() {
        Solution s = new Solution();
        int res = s.solution(new int[]{6, 1, 4, 6, 3, 2, 7, 4}, 3, 2);
        assertEquals(24, res);
    }


    @Test
    public void bobGreaterThanAlice() {
        Solution s = new Solution();
        int res = s.solution(new int[]{1, 2000, 3000, 5000, 7, 1, 9}, 2, 3);
        assertEquals(10009, res);
    }

    @Test
    public void noChance() {
        Solution s = new Solution();
        int res = s.solution(new int[]{10, 19, 15}, 2, 2);
        assertEquals(-1, res);
    }

}