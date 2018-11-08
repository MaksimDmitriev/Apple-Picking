package com.app;


// https://www.glassdoor.com/Interview/Alice-and-Bob-work-in-a-beautiful-orchard-There-are-N-apple-trees-in-the-orchard-The-apple-trees-are-arranged-in-a-row-an-QTN_2639746.htm

class Solution {
    public int solution(int[] A, int K, int L) {
        // write your code in Java SE 8
        if (A == null || K + L > A.length) {
            return -1;
        }

        if (K + L == A.length) {
            int result = 0;
            for (int elem : A) {
                result += elem;
            }
            return result;
        }

        ApplePickingInfo aliceWhenFirst = getApplePickingInfo(A, K, -1, 0);
        ApplePickingInfo bobWhenSecond = getApplePickingInfo(A, L, aliceWhenFirst.start, K);
        if (bobWhenSecond.appleCount == -1) {
            return -1;
        }

        ApplePickingInfo bobWhenFirst = getApplePickingInfo(A, L, -1, 0);
        ApplePickingInfo aliceWhenSecond = getApplePickingInfo(A, K, bobWhenFirst.start, L);

        return Math.max(aliceWhenFirst.appleCount + bobWhenSecond.appleCount, aliceWhenSecond.appleCount + bobWhenFirst.appleCount);
    }

    private ApplePickingInfo getApplePickingInfo(int[] a, int numberToPick, int ignoreRangeStart, int ignoreRangeLen) {
        int max = 0;
        int start = 0;

        int i = 0;
        if (numberToPick >= ignoreRangeStart + 1) {
            i = ignoreRangeStart + ignoreRangeLen;
            if (i + numberToPick > a.length) {
                return new ApplePickingInfo(-1, -1);
            }
        }

        for (; i <= a.length - numberToPick; i++) {
            if (i == ignoreRangeStart) {
                i = ignoreRangeStart + ignoreRangeLen;
            }
            int sum = 0;
            int j = i;
            while (j < i + numberToPick) {
                sum += a[j];
                j++;
            }
            if (sum > max) {
                max = sum;
                start = i;
            }
        }

        return new ApplePickingInfo(start, max);
    }

    private static class ApplePickingInfo {

        int start;
        int appleCount;

        ApplePickingInfo(int start, int appleCount) {
            this.start = start;
            this.appleCount = appleCount;
        }
    }
}