package tasks;

import java.util.Arrays;

public class Task3a {

    public static int[] solve(int[][] data) {
        int[] result = new int[3];
        int[][] memo = new int[data.length][data[0].length];

        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
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

                memo[stock][day] = solveTail(data, memo, stock, day, minPrice);

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

    private static int solveTail(int[][] data, int[][] memo, int stock, int day, int minPrice) {
        if (day == 0 || stock >= data.length)
            return 0;

        if (memo[stock][day] != -1)
            return memo[stock][day];

        int profitBuying = data[stock][day] - minPrice + solveTail(data, memo, stock, day - 1, minPrice);
        int profitNotBuying = solveTail(data, memo, stock, day - 1, minPrice);

        memo[stock][day] = Math.max(profitBuying, profitNotBuying);

        return memo[stock][day];
    }
}
