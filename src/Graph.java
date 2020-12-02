import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph implements GraphInterface<Town ,Road> {
	
	private HashMap <Town, Set<Road>> graph;
	private Set<Road> edgeSet;
	
	public Graph() {
		
		graph =  new HashMap <Town, Set<Road>>();
		edgeSet = new HashSet<>();
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		

		if (!(graph.containsKey(sourceVertex) || !(graph.containsKey(destinationVertex))))
		{
			return null;
		}
		
		if (!sourceVertex.getAdjacentTowns().contains(destinationVertex) 
			&& !destinationVertex.getAdjacentTowns().contains(destinationVertex))
		{
			return null;
		}
		
		else {
			
			for (Road r : graph.get(sourceVertex))
			{
				if ((r.getDestination().equals(sourceVertex) || r.getDestination().equals(destinationVertex))
					 && r.getSource().equals(sourceVertex) || r.getSource().equals(destinationVertex))
				{
					return r;
				}
			}
			
			return null;
		}
		
		
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		if (!graph.containsKey(sourceVertex) || !graph.containsKey(destinationVertex))
		{
			throw new IllegalArgumentException();
		}
		
		else {
			
			Road road = new Road(sourceVertex, destinationVertex, weight, description);
			edgeSet.add(road);
			graph.get(sourceVertex).add(road);
			graph.get(destinationVertex).add(road);
			sourceVertex.setAdjecentTowns(destinationVertex);
			destinationVertex.setAdjecentTowns(sourceVertex);
			return road;
		}
		
	}

	@Override
	public boolean addVertex(Town v) {
		
		if (this.graph.containsKey(v)) {
            return false;
        }
		
		else {
			graph.put(v, new HashSet<>());
			return true;
		}
		
		
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		if (!graph.containsKey(sourceVertex) || !graph.containsKey(destinationVertex))
		{
			return false;
		}
		
		if(sourceVertex.getAdjacentTowns().contains(destinationVertex) 
				|| destinationVertex.getAdjacentTowns().contains(sourceVertex))
		{
			return true;
		}
		
		else {
			
			return false;
		}
		
		
	}

	@Override
	public boolean containsVertex(Town v) {
		return graph.containsKey(v);
	}

	@Override
	public Set<Road> edgeSet() {
		return edgeSet;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		
		return graph.get(vertex);
		
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		if (!graph.containsKey(sourceVertex) || !graph.containsKey(destinationVertex))
		{
			return null;
		}
		
		if (!sourceVertex.getAdjacentTowns().contains(destinationVertex) 
				&& !destinationVertex.getAdjacentTowns().contains(destinationVertex))
		{
			return null;
		}
		
		else {
			
				for (Road r : graph.get(sourceVertex))
				{
					if ((r.getDestination().equals(sourceVertex) || r.getDestination().equals(destinationVertex))
						 && r.getSource().equals(sourceVertex) || r.getSource().equals(destinationVertex))
					{
						Road removedRoad = r;
						graph.get(sourceVertex).remove(r);
						sourceVertex.getAdjacentTowns().remove(destinationVertex);
						destinationVertex.getAdjacentTowns().remove(sourceVertex);
						edgeSet.remove(r);
						return removedRoad;
					}
				}
				
			return null;
		}
		
	}
		
	

	@Override
	public boolean removeVertex(Town v) {
		
		if (!this.graph.containsKey(v)) {
            return false;
        }
		
		else {
			
			graph.remove(v, graph.get(v));
			
			return true;
		}
		
		
	}

	@Override
	public Set<Town> vertexSet() {
		return graph.keySet();
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		
		ArrayList<String> shortestp = new ArrayList<String>();
		LinkedList <Town> list = destinationVertex.getShortestPath();
		
		for (Town t : list)
		{
			shortestp.add(t.toString());
		}
		
		shortestp.add(destinationVertex.toString());
		
		return shortestp;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		Set<Town> closedSet = new HashSet<Town>();
		Set<Town> openSet = new HashSet<Town>();
		openSet = vertexSet();
		
		openSet.add(sourceVertex);
		sourceVertex.setDistance(0);
		
		while (openSet.size() != 0)
		{
			int minWeight = Integer.MAX_VALUE;
			Town minAdjTown = null;
			
			for (Town t: closedSet) 
			{
				for (Town adjT : t.getAdjacentTowns())
				{
					
					int weight = adjT.getDistance() + getEdge(t, adjT).getWeight();
					
					if (weight < minWeight)
					{
						minWeight = weight;
						minAdjTown = adjT;
						LinkedList<Town> list = new LinkedList<>(t.getShortestPath());
						list.add(t);
						minAdjTown.setShortestPath(list);
					}			
				}
				
				if (minAdjTown != null)
				{
					minAdjTown.setDistance(minWeight);
					openSet.remove(minAdjTown);
					closedSet.add(minAdjTown);
					
				}
				
			}
		}
	}
	
}


