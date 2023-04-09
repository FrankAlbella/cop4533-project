package tasks;

public class Task2 {

    public static int[] solve(int[][] data) {
        int maxStock = 0, maxBuy = 0, maxSell = 0, maxPrice = 0;
        int localPrice = -1, localBuy = -1, localSell = -1;
        for (int stock = 0; stock < data.length; ++stock)
        {
            int localMin = -1;
            int localMax = -1;
            for (int date = 1; date < data[0].length; ++date)
            {
                // If we haven't had local minimum
                if (localMin == -1)
                {
                    // And if there is upward trend, update local minimum
                    if (data[stock][date] > data[stock][date - 1])
                        localMin = date - 1;
                }
                // If we are in upward trend
                // Check twice for local max at the end of the list
                if (localMin != -1)
                {
                    // And if there is price drop ahead OR we are at the end of the list
                    // If last two prices are the same and are local max, it returns the last index instead of first
                    if (data[stock][date - 1] > data[stock][date])
                        localMax = date - 1;
                    else if (date == data[0].length - 1)
                        localMax = date;
                    
                    // If we are done with upward trend
                    if (localMax != -1)
                    {
                        // And if current local price is higher than stored one
                        if (data[stock][localMax] - data[stock][localMin] > localPrice)
                        {
                            // Update local price, buy, sell
                            localPrice = data[stock][localMax] - data[stock][localMin];
                            localBuy = localMin;
                            localSell = localMax;
                        }
                        // Reset localMin and max to search for another upward trend
                        localMin = -1;
                        localMax = -1;
                    }
                }
            }

            // If local maximum is greater than previous one
            if (localPrice > maxPrice)
            {
                maxPrice = localPrice;
                maxStock = stock;
                maxBuy = localBuy;
                maxSell = localSell;
            }

            // Reset local Buy and local Min
            localBuy = -1;
            localSell = -1;
            localPrice = -1;
        }

        
        int[] answer = {maxStock, maxBuy, maxSell};
        return answer;
    }

}
