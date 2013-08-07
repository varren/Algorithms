import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String filename = System.getProperty("user.dir")+ "\\hw3\\src" + "\\kargerMinCut.txt";
    private static HashMap<Integer,ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>();


    public static void main (String[] args){
        loadFile();
        System.out.println(data.toString());

        int solution = findMinCut(data);
        System.out.println(solution);
    }

    public static int findMinCut(HashMap<Integer,ArrayList<Integer>> data) {

        int numOfItter = 100;
        RandomizedContractionAlgorithm find = new RandomizedContractionAlgorithm();
        int minCut = data.size();

        for (int i = 0; i < numOfItter; i++){
            HashMap<Integer,ArrayList<Integer>> newData = clone(data);
            int currentVal = find.contractionAlgorithm(newData);
            if(currentVal<minCut)
                minCut=currentVal;
        }
        return minCut;

    }

    public static HashMap<Integer, ArrayList<Integer>> clone( HashMap<Integer, ArrayList<Integer>> m){
        final HashMap<Integer, ArrayList<Integer>> result = new HashMap<Integer, ArrayList<Integer>>();

        for (final Map.Entry<Integer,ArrayList<Integer>> e : m.entrySet())
        {
            int key = e.getKey();
            ArrayList<Integer> value = new ArrayList<Integer>();

            for(int i : e.getValue())
                value.add(i);

            result.put(key, value);
        }

        return result;
    }

    private static void loadFile(){
        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            while(true){
                String line = br.readLine();

                if(line == null)
                    break;

                addEntry(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addEntry(String line) {
        String [] newEntry = line.split("\\s");
        int key =Integer.parseInt(newEntry[0]);
        ArrayList<Integer> currentNodeConnections = new ArrayList<Integer>();


        for(int i = 1;i < newEntry.length; i++){
            currentNodeConnections.add(Integer.parseInt(newEntry[i]));
        }

        data.put(key, currentNodeConnections);
    }
}
