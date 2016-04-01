/* 
 * Ailen Ogholoh
 * CSCI 2125
 * Homework 4
 * December 5, 2014
 */

import java.util.Collection;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.io.IOException;

/*
 * models a graph, performs dijkstra's algorithm on a source vertex
 * prints out the shortest path to a destination vertex
 */

public class Graph
{
    private HashMap vertexMap;
    private static final int INFINITY = Integer.MAX_VALUE;

    public Graph()
    {
        vertexMap = new HashMap();
    }

    //adds an edge to the graph
    public void addEdge( String sourceName, String destName, int cost )
    {
        Vertex source = getVertex(sourceName);
        Vertex destination = getVertex( destName );
        source.getAdjacencies().add( new Edge( destination, cost ));
    }

    //obtains vertex from the graph, creates one if it doesn't exist
    private Vertex getVertex( String vertName )
    {
        Vertex v = (Vertex)vertexMap.get(vertName);
        if( v == null )
        {
            v = new Vertex( vertName );
            vertexMap.put( vertName, v );
        }
        return v;
    }

    /*
     * Handles edge cases, e.g. unknown and unreachable
     * verticies. Prints the total cost of the path.
     * Calls the recursive routine.
     */
    public void printPath( String destName )
    {
        Vertex w = (Vertex) vertexMap.get( destName );
        if( w == null)
        {
            throw new NoSuchElementException( "Destination not found");
        }
        else if( w.getDistance() == INFINITY )
        {
            System.out.println( "Cannot reach " + destName + ".");
        }
        else
        {
            System.out.println( "Cost: " + w.getDistance());
            printPath( w );  
            System.out.println();
        }    
    }

    /*
     * recursive routine to print the shortest path to destination
     * after running Dijkstra, path is known to exist
     */
    private void printPath( Vertex dest )
    {
        if( dest.getPrevious() != null )
        {
            printPath( dest.getPrevious());
            System.out.print( "to " );
        }
        System.out.println( dest.getName() );
    }

    /*
     * the same as printPath method except takes a
     * FileWriter parameter and writes to a file
     */
    public void printPathToFile( String destName, FileWriter writer ) throws IOException
    {
        Vertex w = (Vertex) vertexMap.get( destName );
        if( w == null)
        {
            throw new NoSuchElementException( "Destination not found");
        }
        else if( w.getDistance() == INFINITY )
        {
            writer.write( "Cannot reach " + destName + ".");
        }
        else
        {
            writer.write( "Cost: " + w.getDistance() + "\n");
            printPathToFile( w, writer );
            writer.write("\n");
        }
    }

    /*
     * the same as recursive printPath method except takes a
     * FileWriter parameter and writes to a file
     */ 
    private void printPathToFile( Vertex dest, FileWriter writer ) throws IOException
    {
        if( dest.getPrevious() != null )
        {
            printPathToFile( dest.getPrevious(), writer );
            writer.write( "to " );
        }
        writer.write( dest.getName() + "\n" );
    }

    /*
     * Resets the vertex output information to what it was
     * prior to using Dijkstra
     */
    private void clearAll()
    {
        for( Iterator iterator = vertexMap.values().iterator(); iterator.hasNext();)
        {
            ((Vertex) iterator.next()).resetVertex();
        }
    }

    //Dijkstra's single source weighted shortest-path algorithm
    public void dijkstraPath( String startName ) throws Exception
    {
        PriorityQueue queue = new PriorityQueue();
        Vertex start = (Vertex) vertexMap.get( startName );
        if( start == null )
        {
            throw new NoSuchElementException("Starting vertex was not found");
        }

        clearAll();  //clear all pathing information

        queue.add( new Path( start, 0 ));
        start.setDistance(0);

        int nodesVisited = 0;
        while( nodesVisited < vertexMap.size() && !queue.isEmpty())
        {
            Path vrec = (Path)queue.peek();
            queue.remove( vrec);
            Vertex v = vrec.getDestination();

            if( v.known )    //if v has already been analyzed
            {
                continue;    //move on to the next vertex
            }
            v.known = true;
            nodesVisited++;

            /*
             * use the list iterator to iterate through
             * each vertex adjacent to v
             */
            for( Iterator iterator = v.getAdjacencies().iterator(); iterator.hasNext();)
            {
                Edge e = (Edge) iterator.next();
                Vertex w = e.getDestination();
                int cvw = e.getCost();

                //avoid a negative edge cost graph
                if( cvw < 0 )
                {
                    throw new Exception( "Graph has negative edges" );
                }

                //decrease distance to that of new path
                if( w.getDistance() > v.getDistance() + cvw )
                {
                    w.setDistance( v.getDistance() + cvw );
                    w.setPrevious( v );
                    Path newPath = new Path( w, w.getDistance());
                    queue.add( newPath );
                }
            }
        }
    }
}//end of class Graph
