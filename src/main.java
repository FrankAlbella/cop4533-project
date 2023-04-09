import tasks.Task1;
import tasks.Task2;
import tasks.Task3a;
import tasks.Task3b;
import tasks.Task4;
import tasks.Task5;
import tasks.Task6;

class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Unexpected number of args. Expected task.");
            System.out.println("usage: Stocks task");
	    return;
        }

        // only check the first char because task 3 has a and b
        int task = Character.getNumericValue(args[0].charAt(0));
        
        String[] temp; // String array used to store numbers before converting to int
        int[][] data; // used to store i stocks prices on j days
        int numStocks; // m
        int numDays; // n
        int numTransactions = 1; // k
        int[][] result = new int[1][3]; // used to store the result of each task 

        // Tasks 4 through 6 expect numTransactions (k) as the first input 
        if(task >= 4 && task <= 6) {
            temp = System.console().readLine().split(" ");

            if (temp.length != 1) {
                System.out.println("Expected 1 int representing number of transactions!");
                return;
            }

            numTransactions = Integer.parseInt(temp[0]);
        }

        // Read line containing "stocks [space] days"
        temp = System.console().readLine().split(" ");

        numStocks = Integer.parseInt(temp[0]);
        numDays = Integer.parseInt(temp[1]);
        
        data = new int[numStocks][numDays];

        // Read numStocks lines, getting numDays worth of values on each line
        for(int i = 0; i < numStocks; i++) {
            temp = System.console().readLine().split(" ");

            if (temp.length != numDays) {
                System.out.println("Expected " + numDays + " ints representing stock prices!");
                return;
            }
            
            for(int j = 0; j < numDays; j++) {
                data[i][j] = Integer.parseInt(temp[j]);
            }
        }

        switch(task) {
            case 1: 
                result[0] = Task1.solve(data);
                break;
            case 2: 
                result[0] = Task2.solve(data);
                break;  
            case 3: 
                if(args[0].charAt(1) == 'a')
                    result[0] = Task3a.solve(data);
                else // assume if the 2nd char isn't 'a' then it's 'b'
                    result[0] = Task3b.solve(data);
                break;
            case 4: 
                result = Task4.solve(data, numTransactions);
                break;
            case 5: 
                result = Task5.solve(data, numTransactions);
                break;
            case 6: 
                result = Task6.solve(data, numTransactions);
                break;
            default:
                System.out.println("Task undefined!");
                return;
        }

        // Print out solution set, each line is "Stock BuyDay SellDay"
        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + " " + result[i][1] + " " + result[i][2]);
        }
    }
}