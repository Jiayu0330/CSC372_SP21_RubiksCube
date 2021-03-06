public class Node {
	Cube cube;
	Node parent;
	String action; // used to track the path
	
	public Node(Cube cube, Node parent, String action) {
		this.cube = cube;
		this.parent = parent;
		this.action = action;
	}
}
