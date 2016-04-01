/*
 * Ailen Ogholoh
 * CSCI 2125
 * Homework 4
 * December 5, 2014
 */
import java.util.PriorityQueue;
import java.util.NoSuchElementException;
public class Dijkstra
{
    PriorityQueue queue = new PriorityQueue();
    Vertex start = (Vertex) vertexMap.get( start.getName());
    if( start == null )
    {
        throw new SoSuchElementException("Starting vertex was not found");
        clearAll();
        queue.insert( new Path( start, 0 ));
        start.setDistance(0);

        int nodesVisited = 0;
        while( nodesVisited < vertexMap.size() && !queue.isEmpty())
        {
            Path vrec = (Path) pq.remove( pk.peek());
            Vertex v = vrec.getDestination();
            if( v.getScratch != 0 )
                continue;
            v.getScratch = 1;
            nodesVisited++;

            for( Iterator iterator = v.adjacencies.iterator(); iterator.hasNext();)
            {
                Edge e = (Edge) iterator.next();
                Vertex w = e.getDestination();
                double cvw = edge.getCost();

                if( cvw < 0 )
                    throw new Exception( "Graph has negative edges" );
                if( w.getDistance() > v.getDistance() + cvw )
                {
                    w.setDistance( v.getDistance + cvw );
                    w.setPrevious( v );
                    pq.insert( new Path( w, w.getDistance()));
                }
            }
        }
    }
}//end of class Dijkstra
