package tasks;

public class Task2 {

    public static int[] solve(int[][] data) {
        int maxStock = 0, maxBuy = 0, maxSell = 0, maxPrice = 0;
        int localPrice = -1, localBuy = -1, localSell = -1;
        for (int stock = 0; stock < data.length; ++stock)
        {
            int localMin = 0;
            int localMax = 0;
            boolean up = false;
            for (int date = 1; date < data[0].length; ++date)
            {
                // If we are in upward trend
                if (data[stock][date] > data[stock][date - 1])
                {
                    // And this is the first instance,
                    // And if stock price is lower than or equal to (first instance) the previous local Min,
                    // Update local min
                    if (!up)
                    {
                        if (data[stock][date-1] <= data[stock][localMin])
                            localMin = date - 1;
                        up = true;
                    }
                }
            
                // If we are in upward trend
                if (up)
                {
                    // And if there is price drop ahead OR we are at the end of the list
                    // If last two prices are the same and are local max, it returns the last index instead of first
                    if (data[stock][date - 1] > data[stock][date])
                    {
                        localMax = date - 1;
                        up = false;
                    }
                    else if (date == data[0].length - 1)
                    {
                        localMax = date;
                        up = false;
                    }

                    // If we are done with upward trend
                    if (!up)
                    {
                        int currentLocalPrice = data[stock][localMax] - data[stock][localMin];
                        // And if current local price is higher than stored one
                        if (currentLocalPrice > localPrice)
                        {
                            localPrice = currentLocalPrice;
                            localSell = localMax;

                            // If previous local min DOES make higher profit than current local min
                            // And is NOT first instance of local Buy
                            if (localBuy != -1 && currentLocalPrice < data[stock][localMax] - data[stock][localBuy])
                            {
                                // only update local Price and Sell
                                localPrice = data[stock][localMax] - data[stock][localBuy];
                                // Keep local Buy, don't update
                                localSell = localMax;
                            }
                            else
                                localBuy = localMin;
                        }
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
