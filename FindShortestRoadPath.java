/*
 * Ailen Ogholoh
 * CSCI 2125
 * Homework 4
 * December 5, 2014
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class FindShortestRoadPath
{
    public static void main( String[] args ) throws Exception
    {
        //ensure correct argument count
        if( args.length != 4 )
        {
            System.out.println( "Incorrect argument count" );
            System.exit(1);
        }

        File inputFile = new File( args[0] );
        File outputFile = new File(args[3] );
        outputFile.createNewFile();
        Graph newGraph = new Graph();
        Scanner inputReader = new Scanner(inputFile);
        String line = "";
        String sourceID = args[1];
        String targetID = args[2];
        FileWriter writer = new FileWriter(outputFile);

        Graph g = new Graph();

        /*
         * read through input file, populate the graph
         * expects input in the format of
         * "a" "source" "destination" "cost"
         */
        while( inputReader.hasNextLine() )
        {
            line = inputReader.nextLine();
            String[] tokens = line.split(" ");

            if( tokens[0].equals("a") )
            {
                g.addEdge( tokens[1], tokens[2], Integer.parseInt(tokens[3]));
            }

        }

        /*
         * perform dijkstra's algorithm on source
         * find shortest path to target
         */
        g.dijkstraPath(sourceID);
        g.printPathToFile( targetID, writer );
        writer.flush();
        writer.close();

    }
}//end of class FindShortestRoadPath
