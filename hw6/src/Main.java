import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Main {
    private static final String testFilesDir = System.getProperty("user.dir") + "\\hw6\\src";
    private static final String filename1 = testFilesDir + "\\algo1-programming_prob-2sum.txt";
    private static final String filename2 = testFilesDir + "\\Median.txt";


    public static void main(String[]args){

        PriorityQueue<Integer> lowestH = new PriorityQueue<Integer>(11, Collections.reverseOrder());
        PriorityQueue<Integer> highestH = new PriorityQueue<Integer>();

        System.out.println("Problem 2 Solution: " + solveMedianMaintenance(filename2, lowestH, highestH));


        HashMap<Long, Long> positiveH = new HashMap<Long, Long>();
        HashMap<Long, Long> negativeH = new HashMap<Long, Long>();

        loadFile(filename1, positiveH, negativeH);

        System.out.println("Problem 1 Solution: " + solve2Sum(positiveH, negativeH));


    }


    /*
    *
    *
    * QUESTION 1 SOLUTION
    * Very slow. will try to fix it someday, but not now
    * Still gives the correct answer
    *
    * */

    private static void loadFile(String filename, HashMap<Long, Long> positiveH, HashMap<Long, Long> negativeH) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            while (true) {
                String line = br.readLine();

                if (line == null)
                    break;

                addValue(line, positiveH, negativeH);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addValue(String line, HashMap<Long, Long> positiveH, HashMap<Long, Long> negativeH) {
        long val = Long.parseLong(line);

        if (val >= 0) positiveH.put(val, val);
        else          negativeH.put(val, val);
    }

    private static int solve2Sum(HashMap<Long, Long> positiveH, HashMap<Long, Long> negativeH) {
        int solution = 0;
        HashMap<Integer,Long> found = new HashMap<Integer, Long>();


        for(long key : positiveH.keySet()){
            for (int i = -10000; i <= 10000; i++){
                if(!found.containsKey(i)){
                    long x = i-key;
                    if(negativeH.containsKey(x)){
                        //System.out.println(x +" + "+key +" = "+ i +" found: ["+found.size()+"}");
                        found.put(i, key);
                        solution++;
                    }
                }
            }
        }
        return solution;
    }

    /*
     *
     *
     * QUESTION 2 SOLUTION
     *
     *
     * */
    private static int solveMedianMaintenance(String filename, PriorityQueue<Integer> lowestH, PriorityQueue<Integer> highestH) {

        int sum = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            while (true) {
                String line = br.readLine();

                if (line == null)
                    break;

                sum += addValue(line, lowestH, highestH);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum % 10000;

    }

    private static int addValue(String line, PriorityQueue<Integer> lowestH, PriorityQueue<Integer> highestH) {
        int val = Integer.parseInt(line);
        long mid = highestH.isEmpty() ? Long.MIN_VALUE : highestH.peek();

        // add
        if( val > mid) highestH.add(val);
        else           lowestH.add(val);

        // balance
        if(highestH.size() > lowestH.size() + 1) lowestH.add(highestH.poll());
        else if(highestH.size() < lowestH.size()) highestH.add(lowestH.poll());

        // return mid
        if(highestH.size() > lowestH.size()) return highestH.peek();
        else                                 return lowestH.peek();


    }
}
