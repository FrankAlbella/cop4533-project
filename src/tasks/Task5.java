package tasks;

import java.util.*;

public class Task5 {

    static class Transaction {
        public int stock = -1;
        public int buy = -1;
        public int sell = -1;
        public int profit = 0;
    }
    static public ArrayList<Transaction> temp = new ArrayList<Transaction>();
    static ArrayList<Transaction> max = new ArrayList<Transaction>();

    static void maxProfitWindow(int start, int k, int[][] data, Transaction M[][])
    {
        if (k == 0)
            return;
        for (int y = 0; y < data[0].length; ++y)
        {
            max.add(M[start][y]);
            maxProfitWindow(y, k-1, data, M);

        }
        temp.add(M[start][data[0].length-1]);
    }

    public static int[][] solve(int[][] data, int numTransactions) {
        // TODO Auto-generated method stub

        Transaction M[][] = new Transaction[data[0].length][data[0].length];

        // Create array of profits made by buying and selling consecutively
        // Ex: array[0] = profit(buy = 0, sell = 1), array[1] = profit(buy = 1, sell = 2)
        int profitOneDay[][] = new int[data.length][data[0].length-1]; //data[0].length?
        for (int stock = 0; stock < data.length; ++stock)
        {
            for (int buy = 0; buy < profitOneDay[0].length; ++buy)
            {
                int sell = buy + 1;
                profitOneDay[stock][buy] = data[stock][sell] - data[stock][buy];
            }
        }

        // Create List of transactions that is profitable
        ArrayList<Transaction>[] profitPositive = new ArrayList[data.length];
        for (int i = 0; i < data.length; ++i)
            profitPositive[i] = new ArrayList<Transaction>();
        
        for (int stock = 0; stock < data.length; ++stock)
        {
            Transaction t = new Transaction();
            t.stock = stock;
            for (int buy = 0; buy < data[0].length; ++buy)
            {
                if (buy < profitOneDay[0].length && profitOneDay[stock][buy] < 0)
                {
                    t = new Transaction();
                    t.stock = stock;
                }
                if (buy < profitOneDay[0].length && profitOneDay[stock][buy] > 0)
                {
                    // If this is the first instance of positive profit
                    if (t.buy == -1)
                        t.buy = buy;
                    t.profit += profitOneDay[stock][buy];
                }
                // If we are in up trend
                if (t.buy != -1)
                {
                    Transaction t_new = new Transaction();
                    t_new.stock = stock;
                    t_new.buy = t.buy;
                    t_new.sell = buy+1;
                    t_new.profit = t.profit;
                    if (profitPositive[stock].size() != 0)
                    {
                        int prevBuy = profitPositive[stock].get(profitPositive[stock].size()-1).buy;
                        if (data[stock][buy] - data[stock][prevBuy] > data[stock][buy] - data[stock][t.buy])
                        {
                            t_new.buy = prevBuy;
                            t_new.profit = data[stock][buy] - data[stock][prevBuy];
                        }
                    }
                    //t.sell = buy; // Is it buy?
                    if (!(buy == data[0].length - 1 && t.buy == t_new.buy && t.profit == t_new.profit))
                        profitPositive[stock].add(t_new);
                }
            }
        }

        // Update matrix M
        for (int x = 0; x < M.length; ++x)
        {
            for (int y = x; y < M[0].length; ++y)
            {
                Transaction max = new Transaction();
                for (int stock = 0; stock < data.length; ++stock)
                {
                    for (int i = 0; i < profitPositive[stock].size(); ++i)
                    {
                        Transaction t = profitPositive[stock].get(i);
                        if (t.buy >= x && t.sell <= y && max.profit < t.profit)
                            max = t;
                    }
                }
                if (max.buy == -1)
                    max.buy = 0;
                if (max.sell == -1)
                    max.sell = 0;
                if (max.stock == -1)
                    max.stock = 0;
                M[x][y] = max;
            }
        }

        ArrayList<Transaction>[] collection = new ArrayList[data[0].length];
        for (int i = 0; i < data[0].length; ++i)
        {
            maxProfitWindow(i, numTransactions, data, M);
            collection[i] = temp;
            temp = new ArrayList<Transaction>();
        }


        int[][] answer = {{-1, -1, -1}, {-1, -1, -1}};
        return answer;
    }
    
}
