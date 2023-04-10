package tasks;

import java.util.Arrays;

public class Task3b {

    public static int[] solve(int[][] data) {
        int[] result = new int[3];
        int[][] memo = new int[data.length][data[0].length];

        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], 0);
        }

        int maxProfit = -1;

        for (int stock = 0; stock < data.length; stock++) {
            int minPrice = data[stock][0];
            int minPriceDay = 0;

            for (int day = 1; day < data[stock].length; day++) {
                if (data[stock][day] < minPrice) {
                    minPrice = data[stock][day];
                    minPriceDay = day;
                }

                memo[stock][day] = Math.max(memo[stock][day - 1], data[stock][day] - minPrice);

                if (memo[stock][day] > maxProfit) {
                    maxProfit = memo[stock][day];
                    result[0] = stock;
                    result[1] = minPriceDay;
                    result[2] = day;
                }
            }
        }

        return result;
    }
}
