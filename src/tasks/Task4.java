package tasks;

import java.util.ArrayList;
import java.util.Arrays;

public class Task4 {

    public static int[][] solve(int[][] data, int numTransactions) {

        int m = data.length;
        int n = data[0].length;

        // Array format is {profit, stock, buyDay, sellDay}
        ArrayList<int[]> possibleTrans = new ArrayList<>();

        // Generate all possible transactions and their profits

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    possibleTrans.add(new int[] { data[i][k] - data[i][j], i, j, k });
                }
            }
        }

        //printArrayList(possibleTrans);

        ArrayList<int[]> bestTrans = new ArrayList<>();
        int maxProfit = -1;
        for (int i = 0; i < possibleTrans.size(); i++) {

            ArrayList<int[]> trans = findTrans(possibleTrans, possibleTrans.get(i)[1], i, numTransactions);
            int localProfit = 0;
            for (int j = 0; j < trans.size(); j++) {
                localProfit += trans.get(j)[0];
            }

            if(localProfit > maxProfit) {
                bestTrans = trans;
                maxProfit = localProfit;
            }
                
        }

        int[][] answer = new int[bestTrans.size()][3];

        for(int i = 0; i < answer.length; i++) {
            int[] temp = {bestTrans.get(i)[1], bestTrans.get(i)[2], bestTrans.get(i)[3]};
            answer[i] = temp;
        }

        return answer;
    }

    private static ArrayList<int[]> findTrans(ArrayList<int[]> possTrans, int stock, int index, int transLeft) {
        ArrayList<int[]> solution = new ArrayList<>();

        int[] current = possTrans.get(index);
        solution.add(current);

        // check next possible transaction for next best calculation
        int[] nextTrans = maxProfitTrans(possTrans, current[1], index + 1, index, transLeft);

        if (current[0] + nextTrans[0] > current[0] && !doDaysOverlap(current, nextTrans))
            solution.add(nextTrans);

        return solution;
    }

    private static int[] maxProfitTrans(ArrayList<int[]> possTrans, int stock, int index, int prev, int transLeft) {
        int[] zero = { 0, 0, 0, 0 };
        if(index >= possTrans.size())
            return zero;

        if (doDaysOverlap(possTrans.get(index), possTrans.get(prev)))
            return zero;

        if (transLeft == 0)
            return zero;

        int[] current = possTrans.get(index);
        int[] nextTrans = maxProfitTrans(possTrans, stock, index + 1, index, transLeft);

        if (current[0] < nextTrans[0])
            return nextTrans;

        return current;
    }

    // Assumes trans1 is first
    // trans[2] is buy day
    // trans[3] is sell day
    private static Boolean doDaysOverlap(int[] trans1, int[] trans2) {
        if(trans1[0] != trans2[0])
            return false;
            
        if (trans1[2] > trans2[2] && trans1[3] < trans2[2])
            return true;
        if (trans2[2] < trans1[2] && trans2[2] > trans2[3])
            return true;

        return false;
    }

    private static void printArrayList(ArrayList<int[]> arrayList) {
        for(int i = 0; i < arrayList.size(); i++) {
            System.out.println(Arrays.toString(arrayList.get(i)));
        }
    }

}
