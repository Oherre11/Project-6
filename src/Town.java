import java.util.LinkedList;

public class Town implements Comparable<Town> {
	
	private String townName;
	private LinkedList<Town> adjacentTowns;
	private LinkedList<Town> shortestPath;
	private int distance;
	
	Town(String name){
		adjacentTowns = new LinkedList<Town>();
		shortestPath = new LinkedList<Town>();
		townName = name;
		distance = Integer.MAX_VALUE;
	}
	
	Town(Town templateTown){
		adjacentTowns = new LinkedList<Town>();
		shortestPath = new LinkedList<Town>();
		townName = templateTown.getName();
		distance = Integer.MAX_VALUE;
	}
	
	public String toString() {
		return townName;
	}
	
	public String getName() {
		return townName;
	}
	
	public int hashCode() {
		return townName.hashCode();
	}
	
	public boolean equals(Object obj) {
		
		if (obj == this) { 
            return true; 
        } 
		
		 if (!(obj instanceof Town)) { 
	            return false; 
	        } 
		 
		 Town t = (Town) obj;
		 
		 return t.getName().compareTo(this.townName) == 0;
	                
		
	}

	@Override
	public int compareTo(Town o) {
		if(o.getName().compareTo(this.townName) == 0)
			return 0;
		
		else 
			return -1;
	}
	
	public void setAdjecentTowns(Town t) {
		adjacentTowns.add(t);
	}
	
	public LinkedList<Town> getAdjacentTowns(){
		return adjacentTowns;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distanceFromSource) {
		this.distance = distanceFromSource;
	}
	
	public void setShortestPath(LinkedList<Town> shortestPath){
		
		this.shortestPath = shortestPath;
		
	}
	
	public LinkedList<Town> getShortestPath() {
		
		return shortestPath;
	}
}
