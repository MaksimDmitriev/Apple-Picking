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

        ApplePickingInfo bobWhenFirst = getApplePickingInfo(A, L, -1, 0);
        ApplePickingInfo aliceWhenSecond = getApplePickingInfo(A, K, bobWhenFirst.start, L);

        return Math.max(aliceWhenFirst.appleCount + bobWhenSecond.appleCount, aliceWhenSecond.appleCount + bobWhenFirst.appleCount);
    }

    private ApplePickingInfo getApplePickingInfo(int[] a, int numberToPick, int ignoreRangeStart, int ignoreRangeLen) {
        int i = 0;
        int sum = 0;
        int start = 0;
        if (numberToPick > ignoreRangeStart) {
            i = ignoreRangeStart + ignoreRangeLen;
            start = i;
        }
        // calculate the first sum
        int j = 0;
        while (j < numberToPick) {
            sum += a[i];
            i++;
            j++;
        }
        int max = sum;

        // calculate other sums
        for (; i <= a.length - numberToPick; i++) {
            if (ignoreRangeStart != -1 && ignoreRangeStart - i < numberToPick) {
                i = ignoreRangeStart + ignoreRangeLen;
                // calculate the first sum after skipping the range of the other person
                if (i > a.length - numberToPick) {
                    // the number of trees left is less than numberToPick.
                    break;
                } else {
                    sum = 0;
                    j = 0;
                    while (j < numberToPick) {
                        sum += a[i];
                        i++;
                        j++;
                    }
                    continue;
                }
            }

            sum -= a[i - numberToPick];
            sum += a[i];
            if (sum > max) {
                max = sum;
                start = i - numberToPick + 1;
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