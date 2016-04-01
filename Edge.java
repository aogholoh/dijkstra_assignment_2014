/* 
 * Ailen Ogholoh
 * CSCI 2125
 * Homework 4
 * December 5, 2014
 */

//models a weighted edge between two vertcies
public class Edge
{
    private Vertex destination;
    private int cost;

    public Edge( Vertex destination, int cost )
    {
        this.destination = destination;
        this.cost = cost;
    }
    public Vertex getDestination()
    {
        return destination;
    }
    public int getCost()
    {
        return cost;
    }
}//end of class Edge
