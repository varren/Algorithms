import java.util.HashMap;
import java.util.Set;


public class Vertex implements Comparable<Vertex> {

    public static int NO_CONNECTIONS = Integer.MAX_VALUE;

    public int Vertex;
    public int minDistance;
    public int previous;
    public boolean discovered = false;

    public HashMap<Integer,Integer> edges = new HashMap<Integer, Integer>();

    public Vertex(int id){
        Vertex = id;
        this.minDistance = NO_CONNECTIONS;
    }

    public void addEdge(int toVertex, int value) {
        edges.put(toVertex,value);
    }

    public Set<Integer> edges(){
        return edges.keySet();
    }

    public int distanceTo(int edge){
        if(edges.containsKey(edge))
            return edges.get(edge);
        else
            return NO_CONNECTIONS;
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(minDistance, o.minDistance);

    }

    @Override
    public String toString(){
        return "" + edges;
    }

}
