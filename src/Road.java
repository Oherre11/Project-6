
public class Road implements Comparable<Road> {
	
	Town source;
	Town destination;
	private int degrees;
	private String name; 
	
	public Road(Town source,Town destination,int degrees, String name) {
		
		this.source = source;
		this.destination = destination;
		this.degrees = degrees;
		this.name = name;
		
	}
	
	public Road (Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
		degrees = 1;
	}
	
	public boolean contains(Town town) {
		if (this.source.equals(town) || this.destination.equals(town))
		
			return true;
		
		else
			
			return false;
	}

	@Override
	public int compareTo(Road o) {

		if ((o.source.getName().compareTo(this.source.getName()) == 0) 
				|| (o.destination.getName().compareTo(this.destination.getName()) == 0))
		
			return 0;
		
		else
			
			return -1;
	} 

	public String toString() {
		return name  + degrees;
	}
	
	public String getName() {
		return name;
	}
	
	public Town getDestination() {
		return destination;
	}
	
	public Town getSource() {
		return source;
	}
	
	public int getWeight() {
		return degrees;
	}
	
	public boolean equals(Object r) {
		
		if (r == this) { 
            return true; 
        } 
		
		 if (!(r instanceof Road)) { 
	            return false; 
	        } 
		 
		 Road ro = (Road) r;
		 
		 return ro.compareTo(this) == 0;
	                
		
		
	}
}
