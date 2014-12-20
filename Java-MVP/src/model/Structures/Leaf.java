package model.Structures;

/* 
 * Class Leaf :
 * holds the details of the heuristic score 
 * and the first move that executed and lead to this specific leaf 
*/

public class Leaf {
	
	private Integer value;
	private String path;
	
	//defualt C'tor
	public Leaf() {}
	
	//C'tor that gets value
	public Leaf(int value) {
		this.value = new Integer(value);
		path = new String();
	}
	
	//Copy C'tor
	public Leaf(Leaf original) {
		this.value = new Integer(original.getValue());
		this.path = new String(original.getPath());
	}
	
	// returns a new copy
	public Leaf clone() {
		return new Leaf(this);
	}
	
	public String getPath() {
		return path;
	}
	
	public void insertPath(String wayPoint) {
		path = new String(wayPoint);
	}
	
	public void printPath() {
		System.out.println(path);
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value= value;
	}
	
}



