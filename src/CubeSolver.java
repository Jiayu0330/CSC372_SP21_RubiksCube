import java.util.ArrayList;

public class CubeSolver {
	Node root;
	boolean found = false; // check if the goal state is found
	ArrayList<String> path = new ArrayList<String>();
	//ArrayList<String> whichSide = new ArrayList<String>();
	
	public CubeSolver(Cube cube) {
		root = new Node(cube, null, "Start"); // Node(Cube, parentNode, currentAction)
		IDAStar();
		
	}
	
	public static void main(String[] args) {
		Cube cube = new Cube();
		
//		cube.frontInfoList.set(0, "W");
//		cube.frontInfoList.set(1, "Y");
//		cube.frontInfoList.set(2, "O");
//		cube.frontInfoList.set(3, "B");
//		
//		cube.backInfoList.set(0, "W");
//		cube.backInfoList.set(1, "O");
//		cube.backInfoList.set(2, "O");
//		cube.backInfoList.set(3, "R");
//		
//		cube.leftInfoList.set(0, "B");
//		cube.leftInfoList.set(1, "R");
//		cube.leftInfoList.set(2, "B");
//		cube.leftInfoList.set(3, "Y");
//		
//		cube.rightInfoList.set(0, "R");
//		cube.rightInfoList.set(1, "R");
//		cube.rightInfoList.set(2, "W");
//		cube.rightInfoList.set(3, "G");
//		
//		cube.upInfoList.set(0, "Y");
//		cube.upInfoList.set(1, "G");
//		cube.upInfoList.set(2, "W");
//		cube.upInfoList.set(3, "G");
//		
//		cube.downInfoList.set(0, "G");
//		cube.downInfoList.set(1, "O");
//		cube.downInfoList.set(2, "Y");
//		cube.downInfoList.set(3, "W");
		
//		cube.left(1);
//		cube.right(3);
		
		int k = 10;
		cube.randomize(k);
		for (int i = 1; i < k + 1; i ++) {
        	System.out.println("Turn " + i + ": " + cube.side_direction_records.get(i - 1) + ". ");
        }
		
		@SuppressWarnings("unused")
		CubeSolver solver = new CubeSolver(cube);
	}
	
	/* IDA* search algorithm
	 * According to Dr. Korf’s article: 
	 * "At each iteration, perform a depth-first search, 
	 * cutting off a branch when its total cost (g+h) exceeds a given threshold. 
	 * This threshold starts at the estimate of the cost at the initial state, 
	 * and increases for each iteration of the algorithm. 
	 * At each iteration, the threshold used for the next iteration 
	 * is the minimum cost of all values that exceeded the current threshold."
	 */
	void IDAStar() {
		// if the initial state is the goal state
		if (getHeuristicScore(root) == 0) {
			found = true;
			System.out.println("It is already solved.");
		}
		else {
			//System.out.println(root.cube.toString());
			float threshold = getHeuristicScore(root); // threshold = h(n)
			int counter = 0; // the number of times that the threshold increases/loop from the root node
			while (true) {
				System.out.print("Counter: " + counter);
				System.out.println("; Threshold: " + threshold);
				float newT = search(root, 0, threshold); 
				// search(): returns the minimum value that exceeds current threshold as new threshold
				if (found == true) {
					return;
				}
				counter++;
				threshold = newT;
				// assume it always finds the goal state since it is a cube solver
			}
		}
	}
	
	/* Takes current visiting node, g(current node), and current threshold as parameters
	 * Returns the minimum value that exceeds current threshold as new threshold
	 * It is a recursion function used to check each branch that satisfies the condition.
	 * In other words, it cuts off branches that have greater fScore than the current threshold
	 */
	float search(Node node, int gScore, float threshold) {
		// add children to the node if it has no child
		if (node.children.isEmpty() == true) {
			addChildren(node);
		}

		float fScore = (float)(getHeuristicScore(node) + gScore);
		
		if (fScore > threshold) {
			
			return fScore; // will be returned if it is the minimal one
		}
		
		if (getHeuristicScore(node) == 0) {
			// h = 0 means that the current node has the goal state
			found = true; // set boolean found to true
			System.out.println("FOUND!");
			
			path.add("Turn " + node.action); // add path
			//whichSide.add(node.cube.toString() + " <-");
			
			int steps = 0;
			while (node.parent != null) {
				steps++;
				if (node.parent.action == "Start") {
					path.add(node.parent.action + "->");
				}
				else {
					path.add("Turn " + node.parent.action + "->");	
				}	
				//whichSide.add(node.parent.cube.toString() + " <-");
				node.parent = node.parent.parent;
//				if (node.parent == root) {
//					for (int i = 0; i < 4; i++) {
//						System.out.println(root.cube.frontInfoList.get(i));
//					}
//				}
			}
			
			System.out.println(steps + " steps.");
			for (int i = steps; i >= 0; i--) {
				System.out.print(path.get(i));
				//System.out.print(whichSide.get(i));
				//System.out.println();
			}
			
			return 0; // doesn't matter what to return because the goal state has been found
		}
		
		float min = Float.MAX_VALUE;
		for (Node n : node.children) {
			float newT = search(n, gScore + 1, threshold); // recursion: add gScore by 1
			if (found == true) {
				
				return 0; // doesn't matter what to return because the goal state has been found
			}
			else if (found == false) {
				float newMin = newT;
				if (newMin < min) {
					min = newMin; 
					// minimal value that exceeds the current threshold in the current loop
				}
			}
		}
			
		return min; // return min as the new threshold
	}
	

	/* A heuristic function that estimates the cost from the current node to the goal state
	 * Returns total misplaced number of cubelets divided by 8 since each turn changes 8 cubelets
	 * Uses the minimal one of numbers based on different sides of view
	 * For example, we may consider the side "Red, Red, Green, White" as red side instead of orange side
	 */
	float getHeuristicScore(Node node) {
		Cube cube = node.cube;
		if (cube.isSolved()) {
			return 0;
		}
		else {
			/* Red and Orange are interchangeable; 
			Green and Blue are interchangeable; 
			White and Yellow are interchangeable */
			int totalMisplaced1 = getMisplacedNum(cube.frontInfoList, cube.backInfoList, "R", "O") + 
					getMisplacedNum(cube.leftInfoList, cube.rightInfoList, "G", "B") + 
					getMisplacedNum(cube.upInfoList, cube.downInfoList, "W", "Y");
			
			int totalMisplaced2 = getMisplacedNum(cube.frontInfoList, cube.backInfoList, "R", "O") + 
					getMisplacedNum(cube.leftInfoList, cube.rightInfoList, "W", "Y") + 
					getMisplacedNum(cube.upInfoList, cube.downInfoList, "G", "B");
			
			int totalMisplaced3 = getMisplacedNum(cube.frontInfoList, cube.backInfoList, "W", "Y") + 
					getMisplacedNum(cube.leftInfoList, cube.rightInfoList, "G", "B") + 
					getMisplacedNum(cube.upInfoList, cube.downInfoList, "R", "O");
			
			int totalMisplaced4 = getMisplacedNum(cube.frontInfoList, cube.backInfoList, "W", "Y") + 
					getMisplacedNum(cube.leftInfoList, cube.rightInfoList, "R", "O") + 
					getMisplacedNum(cube.upInfoList, cube.downInfoList, "G", "B");
			
			int totalMisplaced5 = getMisplacedNum(cube.frontInfoList, cube.backInfoList, "G", "B") + 
					getMisplacedNum(cube.leftInfoList, cube.rightInfoList, "R", "O") + 
					getMisplacedNum(cube.upInfoList, cube.downInfoList, "W", "Y");
			
			int totalMisplaced6 = getMisplacedNum(cube.frontInfoList, cube.backInfoList, "G", "B") + 
					getMisplacedNum(cube.leftInfoList, cube.rightInfoList, "W", "Y") + 
					getMisplacedNum(cube.upInfoList, cube.downInfoList, "R", "O");
			
			int min = Math.min(Math.min(totalMisplaced1, totalMisplaced2), Math.min(totalMisplaced3, totalMisplaced4));
			min = Math.min(min, Math.min(totalMisplaced5, totalMisplaced6));
			float score = (float)min/8;
			
			return score;
		}
	}
	
	int getMisplacedNum(ArrayList<String> side1, ArrayList<String> side2, String color1, String color2) {
		int side1color1 = 0;
		int side1color2 = 0;
		int side2color1 = 0;
		int side2color2 = 0;
		int misplacedNum = 0;
		
		for (int i = 0; i < 4; i++) {
			if (side1.get(i) == color1) {
				side1color1++;
			}
			if (side1.get(i) == color2) {
				side1color2++;
			}
			if (side2.get(i) == color1) {
				side2color1++;
			}
			if (side2.get(i) == color2) {
				side2color2++;
			}
		}
		
		int max = Math.max(Math.max(side1color1, side1color2), Math.max(side2color1, side2color2));
		
		if ((side1color1 == max) || (side2color2 == max)) {
			for (int i = 0; i < 4; i++) {
				if (side1.get(i) != color1) {
					misplacedNum++;
				}
				if (side2.get(i) != color2) {
					misplacedNum++;
				}
			}
		}
		else if ((side1color2 == max) || (side2color1 == max)) {
			for (int i = 0; i < 4; i++) {
				if (side1.get(i) != color2) {
					misplacedNum++;
				}
				if (side2.get(i) != color1) {
					misplacedNum++;
				}
			}
		}
		
		return misplacedNum;
	}
	
	void addChildren(Node node) {
		// add possible actions to the current node
		Cube cube1 = (Cube) node.cube.clone();
		Cube cube2 = (Cube) node.cube.clone();
//		Cube cube3 = (Cube) node.cube.clone();
//		Cube cube4 = (Cube) node.cube.clone();
		Cube cube5 = (Cube) node.cube.clone();
		Cube cube6 = (Cube) node.cube.clone();
//		Cube cube7 = (Cube) node.cube.clone();
//		Cube cube8 = (Cube) node.cube.clone();
		Cube cube9 = (Cube) node.cube.clone();
		Cube cube10 = (Cube) node.cube.clone();
//		Cube cube11 = (Cube) node.cube.clone();
//		Cube cube12 = (Cube) node.cube.clone();
		
		cube1.front(1);
		Node F1 = new Node(cube1, node, "Front Clockwise"); // F1 = front clockwise
		cube2.front(3);
		Node F3 = new Node(cube2, node, "Front Counterclockwise"); // F3 = front counterclockwise
//		cube3.back(1);
//		Node B1 = new Node(cube3, node, "Back Clockwise"); // B1 = back clockwise
//		cube4.back(3);
//		Node B3 = new Node(cube4, node, "Back Counterclockwise"); // B3 = back counterclockwise
		cube5.left(1);
		Node L1 = new Node(cube5, node, "Left Clockwise"); // L1 = left clockwise
		cube6.left(3);
		Node L3 = new Node(cube6, node, "Left Counterclockwise"); // L3 = left counterclockwise
//		cube7.right(1);
//		Node R1 = new Node(cube7, node, "Right Clockwise"); // R1 = right clockwise
//		cube8.right(3);
//		Node R3 = new Node(cube8, node, "Right Counterclockwise"); // R3 = right counterclockwise
		cube9.up(1);
		Node U1 = new Node(cube9, node, "Up Clockwise"); // U1 = up clockwise
		cube10.up(3);
		Node U3 = new Node(cube10, node, "Up Counterclockwise"); // U3 = up counterclockwise
//		cube11.down(1);
//		Node D1 = new Node(cube11, node, "Down Clockwise"); // D1 = down clockwise
//		cube12.down(3);
//		Node D3 = new Node(cube12, node, "Down Counterclockwise"); // D3 = down counterclockwise
		
		// there are 6 possible actions for the initial state
		if (node.action == "Start") {
			node.children.add(F1);
			node.children.add(F3);
			node.children.add(L1);
			node.children.add(L3);
			node.children.add(U1);
			node.children.add(U3);
		}
		
		// but 5 for the rest of states because we don't want to undo the last action
		else if (node.action == "Front Clockwise") {
			node.children.add(F1);
			node.children.add(L1);
			node.children.add(L3);
			node.children.add(U1);
			node.children.add(U3);
		}
		
		else if (node.action == "Front Counterclockwise") {
			node.children.add(F3);
			node.children.add(L1);
			node.children.add(L3);
			node.children.add(U1);
			node.children.add(U3);
		}
		
		else if (node.action == "Left Clockwise") {
			node.children.add(F1);
			node.children.add(F3);
			node.children.add(L1);
			node.children.add(U1);
			node.children.add(U3);
		}
		
		else if (node.action == "Left Counterclockwise") {
			node.children.add(F1);
			node.children.add(F3);
			node.children.add(L3);
			node.children.add(U1);
			node.children.add(U3);
		}
		
		else if (node.action == "Up Clockwise") {
			node.children.add(F1);
			node.children.add(F3);
			node.children.add(L1);
			node.children.add(L3);
			node.children.add(U1);
		}
		
		else if (node.action == "Up Counterclockwise") {
			node.children.add(F1);
			node.children.add(F3);
			node.children.add(L1);
			node.children.add(L3);
			node.children.add(U3);
		}
		
		else {
			System.out.println("Error!");
		}
	}

}
