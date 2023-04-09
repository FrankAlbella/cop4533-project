package tasks;

public class Task1 {

    public static int[] solve(int[][] data) {
        int maxStock = 0, maxBuy = 0, maxSell = 0, maxPrice = 0;

        for (int stock = 0; stock < data.length; ++stock)
        {
            for (int buy = 0; buy < data[0].length - 1; ++buy)
            {
                // Start from the same day you bought in case where prices only goes down
                for (int sell = buy; sell < data[0].length; ++sell)
                {
                    int price = data[stock][sell] - data[stock][buy];
                    if (price > maxPrice)
                    {
                        maxPrice = price;
                        maxStock = stock;
                        maxBuy = buy;
                        maxSell = sell;
                    }
                }
            }
        }

        int[] answer = {maxStock, maxBuy, maxSell};
        return answer;


        //throw new UnsupportedOperationException("Unimplemented method 'solve'");
    }

}
