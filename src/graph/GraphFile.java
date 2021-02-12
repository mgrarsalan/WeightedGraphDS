/**
 * 
 */
package graph;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import graph.WeightedGraph.Vertex;

/**
 * 
 * For processing the XML files for loading and creating a graph
 * NOTE: THIS CLASS USES THE JDOM XML PARSER FROM:
 * https://github.com/hunterhacker/jdom
 * All rights of the refrenced open source library belongs to hunterhacker
 * @author Arsalan
 * @author https://github.com/hunterhacker/
 *
 */
public class GraphFile {
	
	public static WeightedGraph loadGraph(String address) throws JDOMException
	{
		WeightedGraph graph = null;
		try {
			File graphFile = new File(address);
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(graphFile);
			Element graphElement = document.getRootElement();
			LinkedList<LinkedList<Vertex>> adjacencyList = 
					new LinkedList<LinkedList<Vertex>>();
			for(Element vertex: graphElement.getChildren())
			{
				//int ID = Integer.parseInt(vertex.getAttributeValue("ID"));
				LinkedList<Vertex> Edges = new LinkedList<WeightedGraph.Vertex>();
				for(Element connection: vertex.getChildren())
				{
					int destinationID
					= Integer.parseInt(connection.getAttributeValue("destination"));
					int weight =
							(Integer.parseInt(connection.getAttributeValue("weight")));
					WeightedGraph.Vertex connectedVertex =
							(new WeightedGraph()).new Vertex(destinationID,weight);
					Edges.add(connectedVertex);
				}
				
				adjacencyList.add(Edges);
			}
			
			graph = new WeightedGraph(graphElement.getAttributeValue("name"), adjacencyList);
			//graph.printGraph();
			//graph.DFS(0, true);
			//System.out.println(graph.getEdgeCount());
			//return graph;
		} catch (IOException e) {
			//System.out.println("FATAL ERROR: I/O. FILE IN USE OR NOT FOUND");
		}
		return graph;
		
	}

}
