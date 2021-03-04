import java.util.ArrayList;

public class Node {
	Cube cube;
	Node parent;
	ArrayList<Node> children = new ArrayList<Node>();
	String action; // used to track the path
	
	public Node(Cube cube, Node parent, String action) {
		this.cube = cube;
		this.parent = parent;
		this.action = action;
	}
	
}
