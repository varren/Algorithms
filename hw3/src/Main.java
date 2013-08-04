import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    private static HashMap<Integer,ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>();

    public static void main (String[] args){
        loadFile();
        System.out.println(data.toString());

    }
    private static final String filename = System.getProperty("user.dir")+ "\\hw3\\src" + "\\kargerMinCut.txt";

    private static void loadFile(){
        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            while(true){
                String line = br.readLine();
                if(line == null)
                    break;
                String [] newEntry = line.split("\\s");
                int key =Integer.parseInt(newEntry[0]);
                ArrayList<Integer> currentNodeConnections = new ArrayList<Integer>();


                for(int i = 1;i < newEntry.length; i++){
                    currentNodeConnections.add(Integer.parseInt(newEntry[i]));
                }

                data.put(key,currentNodeConnections);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
