import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static final String testFilesDir = System.getProperty("user.dir") + "\\hw5\\src";
    private static final String filename = testFilesDir + "\\dijkstraData.txt";
    private static  HashMap<Integer, Vertex> G  = new HashMap<Integer, Vertex>();
    private static int[] sources = {7, 37, 59, 82, 99, 115, 133, 165, 188, 197};


    public static void main(String[]args){
        loadFile(filename, G);
        Dijkstra alg = new Dijkstra();

        for(int s :sources)
           System.out.print(alg.findShortestPass(G, 1, s) + ",");

    }


    private static void loadFile(String filename, HashMap<Integer, Vertex> data) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            while (true) {
                String line = br.readLine();

                if (line == null)
                    break;
                addEdges(line, data);
            }
            System.out.println(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addEdges(String line, HashMap<Integer, Vertex> data) {
        String[] newEntry = line.split("\\s");
        int fromVertex = Integer.parseInt(newEntry[0]);

        data.put(fromVertex, new Vertex(fromVertex));

        for(int i = 1; i < newEntry.length; i++)
            addEdge(newEntry[i], data.get(fromVertex));

    }

    private static void addEdge(String s, Vertex data) {
        String[] edge = s.split(",");
        int toVertex =  Integer.parseInt(edge[0]);
        int value =  Integer.parseInt(edge[1]);

        data.addEdge(toVertex, value);

    }

}
