import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cube implements A1Cube {
	
	// record a cube's info in 6 arrays, each representing a side
	ArrayList<String> frontInfoList = new ArrayList<String>();
	ArrayList<String> backInfoList = new ArrayList<String>();
	ArrayList<String> leftInfoList = new ArrayList<String>();
	ArrayList<String> rightInfoList = new ArrayList<String>();
	ArrayList<String> upInfoList = new ArrayList<String>();
	ArrayList<String> downInfoList = new ArrayList<String>();
	
	// record last selected side and direction
	int lastSide = 0;
	int lastTurns = 0;
	
	// record random selected side and direction to rotate
	ArrayList<String> side_direction_records = new ArrayList<String>();
	
	boolean solved = false; // to check if the cube is solved
	
	public Cube() {
		
		fillList(frontInfoList, "red");
		fillList(backInfoList, "orange");
		fillList(leftInfoList, "green");
		fillList(rightInfoList, "blue");
		fillList(upInfoList, "white");
		fillList(downInfoList, "yellow");
		
		solved = true;
		
	}
	
	/* initialize an array with the same color
	 * position 0: top-left corner; position 1: top-right corner; 
	 * position 2: bottom-left corner; position 3: bottom-right corner */
	void fillList(ArrayList<String> list, String color) {
		
		for (int i = 0; i < 4; i++) {
			list.add(color);
		}	
		
	}
	
	/* rotate the front side clockwise or counterclockwise once
	 * k = 1: clockwise; k = 3: counterclockwise */
	public void front(int k) {
		
		/* clockwise: 
		 * front rotates and back stays the same
		 * left's 1&3 go up; up's 3&2 go right;
		 * right's 2&0 go down; down's 0&1 go left */
		
		int[] order = {1, 3, 3, 2, 2, 0, 0, 1}; // store what will change in each side
		// rotation begins from left eg: left's 1&3 become up's 3&2
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, leftInfoList, upInfoList, rightInfoList, downInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, frontInfoList); // input: order, orderList, currentFaceList		
		}
		
	}
	
	/* rotate the back side clockwise or counterclockwise once
	 * k = 1: clockwise; k = 3: counterclockwise */
	public void back(int k) {
		
		/* clockwise: 
		 * back rotates and front stays the same
		 * left's 0&2 go down; down's 2&3 go right; 
		 * right's 3&1 go up; up's 1&0 go left */
		
		int[] order = {0, 2, 2, 3, 3, 1, 1, 0}; // store what will change in each side
		// rotation begins from left eg: left's 0&2 become down's 2&3
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, leftInfoList, downInfoList, rightInfoList, upInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, backInfoList); // input: order, orderList, currentFaceList
		}
		
	}

	/* rotate the left side clockwise or counterclockwise once
	 * k = 1: clockwise; k = 3: counterclockwise */
	public void left(int k) {
		
		/* clockwise:  
		 * left rotates and right stays the same
		 * up's 0&2 go front; front's 0&2 go down; 
		 * down's 0&2 go back; back's 3&1 go up */
		
		int[] order = {0, 2, 0, 2, 0, 2, 3, 1}; // store what will change in each side
		// begins from up eg: up's 0&2 become front's 0&2
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, upInfoList, frontInfoList, downInfoList, backInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, leftInfoList); // input: order, orderList, currentFaceList
		}
		
	}
	
	/* rotate the right side clockwise or counterclockwise once
	 * k = 1: clockwise; k = 3: counterclockwise */
	public void right(int k) {
		
		/* clockwise: 
		 * right rotates and left stays the same
		 * up's 1&3 go back; back's 2&0 go down; 
		 * down's 1&3 go front; front's 1&3 go up */
		
		int[] order = {1, 3, 2, 0, 1, 3, 1, 3}; // store what will change in each side
		// begins from up eg: up's 1&3 become back's 2&0
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, upInfoList, backInfoList, downInfoList, frontInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, rightInfoList); // input: order, orderList, currentFaceList		
		}
		
	}
	
	/* rotate the up side clockwise or counterclockwise once
	 * k = 1: clockwise; k = 3: counterclockwise */
	public void up(int k) {
		
		/* clockwise: 
		 * up rotates and down stays the same
		 * left's 0&1 go back; back's 0&1 go right; 
		 * right's 0&1 go front; front's 0&1 go left */
		
		int[] order = {0, 1, 0, 1, 0, 1, 0, 1}; // store what will change in each side
		// begins from left eg: left's 0&1 become back's 0&1
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, leftInfoList, backInfoList, rightInfoList, frontInfoList);
		
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, upInfoList); // input: order, orderList, currentFaceList
		}
		
	}
	
	/* rotate the down side clockwise or counterclockwise once
	 * k = 1: clockwise; k = 3: counterclockwise */
	public void down(int k) {
		
		/* clockwise: 
		 * down rotates and up stays the same
		 * left's 2&3 go front; front's 2&3 go right; 
		 * right's 2&3 go back; back's 2&3 go left */
		
		int[] order = {2, 3, 2, 3, 2, 3, 2, 3}; // store what will change in each side
		// begins from left eg: left's 2&3 become front's 2&3
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, leftInfoList, frontInfoList, rightInfoList, backInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, downInfoList); // input: order, orderList, currentFaceList
		}
		
	}
	
	/* realize front(), back(), left(), right(), etc. */
	void rotateClockwise(int[] order, ArrayList<ArrayList<String>> list, ArrayList<String> currentList) {
		
		// record colors before changing
		String c1 = list.get(3).get(order[6]);
		String c2 = list.get(3).get(order[7]);
		String c3 = list.get(0).get(order[0]);
		String c4 = list.get(0).get(order[1]);
		String c5 = list.get(1).get(order[2]);
		String c6 = list.get(1).get(order[3]);
		String c7 = list.get(2).get(order[4]);
		String c8 = list.get(2).get(order[5]);		
		// change colors
		list.get(0).set(order[0], c1);
		list.get(0).set(order[1], c2);
		list.get(1).set(order[2], c3);
		list.get(1).set(order[3], c4);
		list.get(2).set(order[4], c5);
		list.get(2).set(order[5], c6);
		list.get(3).set(order[6], c7);
		list.get(3).set(order[7], c8);
		// rotate the current face
		String c9 = currentList.get(0);
		String c10 = currentList.get(1);
		String c11 = currentList.get(2);
		String c12 = currentList.get(3);
		currentList.set(0, c11);
		currentList.set(1, c9);
		currentList.set(2, c12);
		currentList.set(3, c10);
		
	}
	
	/* check if the cube is solved */
	public boolean isSolved() {	
		
		// assuming the cube is under control: no unreachable states
		// check colors in five sides
		if (isSameColor(frontInfoList) && isSameColor(backInfoList) && isSameColor(leftInfoList)
				&& isSameColor(rightInfoList) && isSameColor(upInfoList)) {
			return true;
		}
		
		return false;
		
	}
	
	/* check if a side has same colors */
	boolean isSameColor(ArrayList<String> list) {
		
		boolean sameColor = true;
		String first = list.get(0);
		for(int i = 1; i < 4 && sameColor; i++)
		{
		  if (list.get(i) != first) {
			  sameColor = false;
		  }
		}
		
		return sameColor;
		
	}
	
	/* choose a random side and direction that do not undo the last turn */
	public void randomize(int k) {
		
		String side_direction; // for printing purpose
		
		for (int i = 0; i < k; i++) {
			System.out.print("i: " + i + "; ");
			// if the cube is in solved status, it's okay to rotate any side in any direction
			if (solved) {
				// choose a random side
				Random rn = new Random();
				int whichSide = rn.nextInt(6) + 1;
				//System.out.print(whichSide)
				int x = whichSide;
				lastSide = x;
				
				// choose a random direction
				int clockwise = 1;
				int counterclockwise = 3;
				int turns = new Random().nextBoolean() ? clockwise : counterclockwise;			
				int y = turns;
				lastTurns = y;
				
				String direction;
				if (turns == 1) {
					direction = "Clockwise";
				}
				else {
					direction = "Counterclockwise";
				}
				
				if (whichSide == 1) {
					//System.out.print("Side: Front Turns: " + Integer.toString(turns) + " | ");
					front(turns);
					side_direction = "Front, " + direction;
				}
				else if (whichSide == 2) {
					//System.out.print("Side: Back Turns: " + Integer.toString(turns) + " | ");
					back(turns);
					side_direction = "Back, " + direction;
				}
				else if (whichSide == 3) {
					//System.out.print("Side: Left Turns: " + Integer.toString(turns) + " | ");
					left(turns);
					side_direction = "Left, " + direction;
				}
				else if (whichSide == 4) {
					//System.out.print("Side: Right Turns: " + Integer.toString(turns) + " | ");
					right(turns);
					side_direction = "Right, " + direction;
				}
				else if (whichSide == 5) {
					//System.out.print("Side: Up Turns: " + Integer.toString(turns) + " | ");
					up(turns);
					side_direction = "Up, " + direction;
				}
				else {
					//System.out.print("Side: Down Turns: " + Integer.toString(turns) + " | ");
					down(turns);
					side_direction = "Down, " + direction;
				}
			}
			// else need to check if the current action will undo the last turn
			else { 
				// choose a random side
				Random rn = new Random();
				int whichSide = rn.nextInt(6) + 1;
				//System.out.print(whichSide)
				
				// choose a random direction
				int clockwise = 1;
				int counterclockwise = 3;
				int turns = new Random().nextBoolean() ? clockwise : counterclockwise;
				
				String direction;
				if (turns == 1) {
					direction = "Clockwise";	
				}
				else {
					direction = "Counterclockwise";
				}
				
				System.out.print("Last side: " + lastSide + "; Last direction: " + lastTurns);
				System.out.print("; Current side: " + whichSide + "; Current direction: " + turns + " | ");
				
				
				boolean undo = ifUndo(lastTurns, turns);
				if (lastSide == 1 || lastSide == 2) {
					while (undo == true && (whichSide == 1 || whichSide == 2)) {
						// if undo, randomize again
						whichSide = rn.nextInt(6) + 1;
						undo = ifUndo(lastTurns, turns);
						System.out.print("Reradomized! ");
					}
				}
				else if (lastSide == 3 || lastSide == 4) {
					while (undo == true && (whichSide == 3 || whichSide == 4)) {
						// if undo, randomize again
						whichSide = rn.nextInt(6) + 1;
						undo = ifUndo(lastTurns, turns);
						System.out.print("Reradomized! ");
					}
				}
				else if (lastSide == 5 || lastSide == 6) {
					while (undo == true && (whichSide == 5 || whichSide == 6)) {
						// if undo, randomize again
						whichSide = rn.nextInt(6) + 1;
						undo = ifUndo(lastTurns, turns);
						System.out.print("Reradomized! ");
					}
				}
				
				int x = whichSide;
				lastSide = x;
				
				int y = turns;
				lastTurns = y;
				
				if (whichSide == 1) {
//					System.out.print("Side: Front Turns: " + Integer.toString(turns) + " | ");
					front(turns);
					side_direction = "Front, " + direction;
				}
				else if (whichSide == 2) {
//					System.out.print("Side: Back Turns: " + Integer.toString(turns) + " | ");
					back(turns);
					side_direction = "Back, " + direction;
				}
				else if (whichSide == 3) {
//					System.out.print("Side: Left Turns: " + Integer.toString(turns) + " | ");
					left(turns);
					side_direction = "Left, " + direction;
				}
				else if (whichSide == 4) {
//					System.out.print("Side: Right Turns: " + Integer.toString(turns) + " | ");
					right(turns);
					side_direction = "Right, " + direction;
				}
				else if (whichSide == 5) {
//					System.out.print("Side: Up Turns: " + Integer.toString(turns) + " | ");
					up(turns);
					side_direction = "Up, " + direction;
				}
				else {
//					System.out.print("Side: Down Turns: " + Integer.toString(turns) + " | ");
					down(turns);
					side_direction = "Down, " + direction;
				}
			}
			solved = isSolved();
			
			// add record into the array
			side_direction_records.add(side_direction);
		}
			
	}
	
	/* check if undo the last turn */
	boolean ifUndo(int lastTurns, int turns) {
		
		if ((lastTurns == 1 && turns == 3) || (lastTurns == 3 && turns == 1)) {
			return true;
		}	
		
		return false;
		
	}
	
	/* reset the cube to its solved status */
	public void reset() {	
		
		resetList(frontInfoList, "red");
		resetList(backInfoList, "orange");
		resetList(leftInfoList, "green");
		resetList(rightInfoList, "blue");
		resetList(upInfoList, "white");
		resetList(downInfoList, "yellow");
		
		// reset last side and direction to 0
		lastSide = 0;
		lastTurns = 0;
		
		// clear side_direction_records
		side_direction_records.clear();
		solved = true;
		
	}
	
	void resetList(ArrayList<String> list, String color) {
		
		for (int i = 0; i < 4; i++) {
			list.set(i, color);
		}	
		
	}
	
	/* return a copy of the cube */
	public A1Cube clone() {
		
		Cube cube_copy = new Cube();
		
		cube_copy.frontInfoList = this.frontInfoList;
		cube_copy.backInfoList = this.backInfoList;
		cube_copy.leftInfoList = this.leftInfoList;
		cube_copy.rightInfoList = this.rightInfoList;
		cube_copy.upInfoList = this.upInfoList;
		cube_copy.downInfoList = this.downInfoList;
		cube_copy.side_direction_records = this.side_direction_records;
		cube_copy.solved = this.solved;
		
		return cube_copy;
		
	}
	
}
