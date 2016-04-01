/* 
 * Ailen Ogholoh
 * CSCI 2125
 * Homework 4
 * December 5, 2014
 */

import java.util.LinkedList;

/*
 * models a vertex in a graph capable of performing
 * Dijkstra's shortest graph path algorithm
 */
public class Vertex
{
    private String name;
    private LinkedList adjacencies;
    private int distance;
    private Vertex previous;
    public boolean known;

    public Vertex( String name )
    {
        this.name = name;
        adjacencies = new LinkedList();
        resetVertex();
    }

    //reset pathing values relevant to shortest path algorithms
    public void resetVertex()
    {
        distance = Integer.MAX_VALUE;
        previous = null;
        known = false;
    }
    public String getName()
    {
        return name;
    }
    public LinkedList getAdjacencies()
    {
        return adjacencies;
    }
    public Vertex getPrevious()
    {
        return previous;
    }
    public int getDistance()
    {
        return distance;
    }
    public void setDistance( int distance )
    {
        this.distance = distance;
    }
    public void setPrevious( Vertex v )
    {
        previous = v;
    }
}//end of class Vertex
