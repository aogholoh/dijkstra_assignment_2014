/* 
 * Ailen Ogholoh
 * CSCI 2125
 * Homework 4
 * December 5, 2014
 */

//models a path from vertex to another, implements Comparable interface
public class Path implements Comparable
{
    private Vertex destination;
    private int cost;

    public Path( Vertex destination, int cost )
    {
        this.destination = destination;
        this.cost = cost;
    }
    public int compareTo( Object newCost )
    {
        int otherCost = ((Path)newCost).getCost();
        if( cost < otherCost )
            return -1;
        else if( cost > otherCost )
            return 1;
        else
            return 0;
        //return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }
    public Vertex getDestination()
    {
        return destination;
    }
    public int getCost()
    {
        return cost;
    }
}//end of class Path
