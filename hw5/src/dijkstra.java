import java.util.*;

public class Dijkstra {

   /*
   * This is probably not the best solution. I really don't know what are the best data structures to use in Java
   * for Dijkstra algorithm.
   *
   * */

    public int findShortestPass(HashMap<Integer, Vertex> G, int from, int to) {

        PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>();

        setNewMinDist(G, from, 0, from);
        Q.add(G.get(from));

        while(!Q.isEmpty()){

            Vertex u = Q.poll();

            if(u.Vertex == to) break; // found shortest path to needed vertex

            if(!G.get(u.Vertex).discovered){
                u.discovered = true;

                for(int v: G.get(u.Vertex).edges()){
                   relax(G, u, v);
                   Q.add(G.get(v));
                }
            }
        }

        return G.get(to).minDistance;
    }

    private void setNewMinDist(HashMap<Integer, Vertex> G, int v, int dist, int prevVertex){
        G.get(v).minDistance = dist;
        G.get(v).previous = prevVertex;
    }

    private void relax(HashMap<Integer, Vertex> G, Vertex u, int v) {
        int altDist = u.minDistance + u.distanceTo(v);

        if(altDist <  G.get(v).minDistance)
            setNewMinDist(G, v, altDist, u.Vertex);
    }
}
