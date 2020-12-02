import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Oscar Herrera
 *
 */
public class TownGraphManager implements TownGraphManagerInterface {
	
	Graph graph;
	
	/**
	 * constructor
	 */
	public TownGraphManager() {
		graph = new Graph();
	}

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town town_A = getTown(town1);
		Town town_B = getTown(town2);
		
		return (graph.addEdge(town_A, town_B , weight, roadName) != null);
		
	}

	/**
	 * returns the name of a road
	 * @parm town1
	 * @parm town2
	 */
	@Override
	public String getRoad(String town1, String town2) {
		
		return graph.getEdge(getTown(town1), getTown(town2)).getName();
	}
	
	/**
	 * adds a town to a graph
	 * @parm v
	 */
	@Override
	public boolean addTown(String v){
		Town town = new Town(v);
		return graph.addVertex(town);
	}

	/**
	 * returns a town from a map
	 * @parm name 
	 */
	@Override
	public Town getTown(String name) {
		
		for (Town t : graph.vertexSet())
		{
			if (t.getName().compareToIgnoreCase(name) == 0)
			{
				return t;
			}
		}
		
		return null;
	}

	/**
	 * returns true if graph contains a town
	 */
	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(getTown(v));
	}

	/**
	 * returns true if two towns are connected
	 * @parm town1
	 * @parm town2
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		
		return graph.containsEdge(getTown(town1), getTown(town2));
		
	}

	/**
	 * returns a list of all roads in alphabetical order
	 * 
	 */
	@Override
	public ArrayList<String> allRoads() {
		
		ArrayList<String> roadNames = new ArrayList<String>();
		
		for (Road r : graph.edgeSet())
		{
			roadNames.add(r.getName());
		}
		
		Collections.sort(roadNames);
		
		return roadNames;
	}

	/**
	 * deletes a road 
	 * @parm town1
	 * @parm town2
	 * @parm road
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		
		Road r = getRoad(road);
		
		return (graph.removeEdge(getTown(town1), getTown(town2), r.getWeight(), road) != null);
	}
	
	/**
	 * deletes a town from a road
	 * @parm v
	 */

	@Override
	public boolean deleteTown(String v) {
		
		return graph.removeVertex(getTown(v));
	}

	/**
	 * returns a list of all towns
	 */
	@Override
	public ArrayList<String> allTowns() {
		
		ArrayList<String> allTowns = new ArrayList<String>();
		
		for (Town t : graph.vertexSet())
		{
			allTowns.add(t.getName());
		}
		
		Collections.sort(allTowns);
		
		return allTowns;
	}


	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Road getRoad(String road) {
		for (Road r : graph.edgeSet()) 
		{
			if (road.compareToIgnoreCase(r.getName()) == 0)
			{
				return r;
			}
			
		}
		
		return null;
	}

}
