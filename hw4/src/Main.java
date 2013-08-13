import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * This Main Class is for tests and loading data in HashMaps.
 * Real implementation of Kosaraju's algorithm is in StronglyConnectedComponents file
 */

public class Main {

    /* https://class.coursera.org/algo-004/quiz/
    The file contains the edges of a directed graph. Vertices are labeled as positive integers from 1 to 875714.

    Every row indicates an edge, the vertex label in first column is the tail
    and the vertex label in second column is the head
    (recall the graph is directed, and the edges are directed from the first column vertex to the second column vertex).

    So for example, the 11th row looks liks : "2 47646".

    This just means that the vertex with label 2 has an outgoing edge to the vertex with label 47646

    Your task is to code up the algorithm from the video lectures for computing strongly connected components (SCCs),
    and to run this algorithm on the given graph.

    Output Format:

    You should output the sizes of the 5 largest SCCs in the given graph, in decreasing order of sizes,
    separated by commas (avoid any spaces).

    So if your algorithm computes the sizes of the five largest SCCs to be 500, 400, 300, 200 and 100,
    then your answer should be "500,400,300,200,100".

    If your algorithm finds less than 5 SCCs, then write 0 for the remaining terms.

    Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300, and 100,
    then your answer should be "400,300,100,0,0".*/

    private static final String testFilesDir = System.getProperty("user.dir") + "\\hw4\\src\\TestCases";


    private static final String filename = testFilesDir + "\\SCC.txt";  // Can be downloaded from http://spark-public.s3.amazonaws.com/algo1/programming_prob/SCC.txt
    private static final String filename1 = testFilesDir + "\\testCase.txt";  // 3,3,2,0,0
    private static final String filename2 = testFilesDir + "\\testCase2.txt"; // 3,3,3,0,0
    private static final String filename3 = testFilesDir + "\\testCase3.txt"; // 3,3,1,1,0
    private static final String filename4 = testFilesDir + "\\testCase4.txt"; // 7,1,0,0,0
    private static final String filename5 = testFilesDir + "\\testCase5.txt"; // 3,2,2,2,1
    private static final String filename6 = testFilesDir + "\\testCase6.txt"; // 6,3,2,1,0
    private static final String filename7 = testFilesDir + "\\testCase7.txt"; // 6,1,1,0,0
    private static final String filename8 = testFilesDir + "\\testCase8.txt"; // 4,3,2,0,0

    private static final String SOLVED = "lol, calculate yourself";

    public static void main(String[] args) {

//        run(filename1, "3,3,2,0,0");
//        run(filename2, "3,3,3,0,0");
//        run(filename3, "3,3,1,1,0");
//        run(filename4, "7,1,0,0,0");
//        run(filename5, "3,2,2,2,1");
//        run(filename6, "6,3,2,1,0");
//        run(filename7, "6,1,1,0,0");
//        run(filename8, "4,3,2,0,0");
        run(filename, SOLVED);
    }

    private static void run(String filename, String correctSolution) {
        HashMap<Integer, ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> rData = new HashMap<Integer, ArrayList<Integer>>();


        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT-0"));
        long start = System.currentTimeMillis();

        System.out.println("Loading File SCC: ");

        int maxVertex = loadFile(filename, rData, data);
        System.out.println("Max Vertex: " + maxVertex);
        System.out.println("Loaded in: " + df.format(new Date(System.currentTimeMillis() - start)));

        System.out.println("Calculating SCC: ");

        StronglyConnectedComponents scc = new StronglyConnectedComponents();
        ArrayList<Integer> result = scc.runAlgorithm(data, rData, maxVertex);
        printResults(result, correctSolution);

        System.out.println("Algorithm runtime: " + df.format(new Date(System.currentTimeMillis() - start)));

        System.out.println("Total memory (bytes): " + Runtime.getRuntime().totalMemory() / 1024 / 1024);
    }

    private static int loadFile(String filename, HashMap<Integer, ArrayList<Integer>> rData, HashMap<Integer, ArrayList<Integer>> data) {
        int maxVertex = 0;
        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            while (true) {
                String line = br.readLine();

                if (line == null)
                    break;

                int curMaxValue = addEdge(line, rData, data);

                maxVertex = Math.max(maxVertex, curMaxValue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return maxVertex;
    }

    private static int addEdge(String line, HashMap<Integer, ArrayList<Integer>> rData, HashMap<Integer, ArrayList<Integer>> data) {
        String[] newEntry = line.split("\\s");
        int key = Integer.parseInt(newEntry[0]);
        int edge = Integer.parseInt(newEntry[1]);

        addTo(key, edge, data);
        addTo(edge, key, rData);
        return Math.max(key, edge);
    }

    private static void addTo(int key, int edge, HashMap<Integer, ArrayList<Integer>> data) {
        if (!data.containsKey(key))
            data.put(key, new ArrayList<Integer>());

        data.get(key).add(edge);

    }

    private static void printResults(ArrayList<Integer> result, String correctSolution) {
        Collections.sort(result, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        System.out.println("-----------------------------\nSolution: (Should be: " + correctSolution + ")");
        String solution = "";
        for (int i = 0; i < 5; i++) {
            if (i < result.size())
                solution += result.get(i);
            else
                solution += "0";
            if (i != 4)
                solution += ",";
        }

        System.out.println(solution);

        if (!solution.equals(correctSolution) && !correctSolution.equals(SOLVED))
            System.out.println("!!!!!!!!!!!!!!!!!Incorrect " + solution + " should be " + correctSolution + "!!!!!!!!!!!!!!!!");
        System.out.println("-----------------------------");
    }

}
