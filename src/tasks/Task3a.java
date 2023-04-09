package tasks;

public class Task3a {
    static class Transaction
    {   
        public int stock = -1;
        public int buyDay = -1;
        public int sellDay = -1;
        public int profit = -1;
    };

    public static int[] solve(int[][] data) {
        Transaction bestTrans = solveTail(data, 0);
        return new int[] {bestTrans.stock, bestTrans.buyDay, bestTrans.sellDay};
    }

    static Transaction solveTail(int[][] data, int stock) {
        // If out of range than return default values of -1
        if(stock >= data.length) 
            return new Transaction();
        
        Transaction localTrans = bestTransactionOnDay(data[stock]);
        localTrans.stock = stock;

        Transaction nextDayTrans = solveTail(data, stock+1);

        if (localTrans.profit < nextDayTrans.profit)
            return nextDayTrans;

        return localTrans;
    }

    static Transaction bestTransactionOnDay(int[] prices) {
        Transaction trans = new Transaction();
        int localMin = -1;
        int localMax = -1;
        for (int date = 1; date < prices.length; ++date)
        {
            // If we haven't had local minimum
            if (localMin == -1)
            {
                // And if there is upward trend, update local minimum
                if (prices[date] > prices[date - 1])
                    localMin = date - 1;
            }
            // If we are in upward trend
            // Check twice for local max at the end of the list
            if (localMin != -1)
            {
                // And if there is price drop ahead OR we are at the end of the list
                // If last two prices are the same and are local max, it returns the last index instead of first
                if (prices[date - 1] > prices[date])
                    localMax = date - 1;
                else if (date == prices.length - 1)
                    localMax = date;
                
                // If we are done with upward trend
                if (localMax != -1)
                {
                    // And if current local price is higher than stored one
                    if (prices[localMax] - prices[localMin] > trans.profit)
                    {
                        // Update local price, buy, sell
                        trans.profit = prices[localMax] - prices[localMin];
                        trans.buyDay = localMin;
                        trans.sellDay = localMax;
                    }
                    // Reset localMin and max to search for another upward trend
                    localMin = -1;
                    localMax = -1;
                }
            }
        }

        return trans;
    }

}
