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
	
	// record random selected side and direction to rotate
	ArrayList<String> side_direction_records = new ArrayList<String>();
	
	// to check if the cube is solved
	boolean solved = false;
	
	public Cube() {
		frontInfoList = fillList(frontInfoList, "red");
		backInfoList = fillList(backInfoList, "orange");
		leftInfoList = fillList(leftInfoList, "green");
		rightInfoList = fillList(rightInfoList, "blue");
		upInfoList = fillList(upInfoList, "white");
		downInfoList = fillList(downInfoList, "yellow");
		
		solved = true;
		
	}
	
	ArrayList<String> fillList(ArrayList<String> list, String color) {
		for (int i = 0; i < 4; i++) {
			list.add(color);
		}	
		return list;
		
	}
	
	public void front(int k) {
		/* clockwise: 
		 * front rotate and back stay the same
		 * left's 1&3 go up; up's 3&2 go right;
		 * right's 2&0 go down; down's 0&1 go left */
		
		int[] order = {1, 3, 3, 2, 2, 0, 0, 1}; 
		// rotation begins from left eg: left's 1&3 become up's 3&2
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, leftInfoList, upInfoList, rightInfoList, downInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, frontInfoList);
			
			// keep the old code here in case something goes wrong; similar code in back(), left(), etc.
			
//			// record colors before changing
//			String c1 = downInfoList.get(order[6]);
//			String c2 = downInfoList.get(order[7]);
//			String c3 = leftInfoList.get(order[0]);
//			String c4 = leftInfoList.get(order[1]);
//			String c5 = upInfoList.get(order[2]);
//			String c6 = upInfoList.get(order[3]);
//			String c7 = rightInfoList.get(order[4]);
//			String c8 = rightInfoList.get(order[5]);		
//			// change colors
//			leftInfoList.get(order[0]).setColor(c1);
//			leftInfoList.get(order[1]).setColor(c2);
//			upInfoList.get(order[2]).setColor(c3);
//			upInfoList.get(order[3]).setColor(c4);
//			rightInfoList.get(order[4]).setColor(c5);
//			rightInfoList.get(order[5]).setColor(c6);
//			downInfoList.get(order[6]).setColor(c7);
//			downInfoList.get(order[7]).setColor(c8);
//			// rotate the current face
//			String c9 = frontInfoList.get(0);
//			String c10 = frontInfoList.get(1);
//			String c11 = frontInfoList.get(2);
//			String c12 = frontInfoList.get(3);
//			frontInfoList.get(0).setColor(c11);
//			frontInfoList.get(1).setColor(c9);
//			frontInfoList.get(2).setColor(c12);
//			frontInfoList.get(3).setColor(c10);
			
		}
	}
	
	public void back(int k) {
		/* clockwise: 
		 * back rotate and front stay the same
		 * left's 0&2 go down; down's 2&3 go right; 
		 * right's 3&1 go up; up's 1&0 go left */
		
		int[] order = {0, 2, 2, 3, 3, 1, 1, 0};
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, leftInfoList, downInfoList, rightInfoList, upInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, backInfoList);
			
//			// record colors before changing
//			String c1 = upInfoList.get(order[6]);
//			String c2 = upInfoList.get(order[7]);
//			String c3 = leftInfoList.get(order[0]);
//			String c4 = leftInfoList.get(order[1]);
//			String c5 = downInfoList.get(order[2]);
//			String c6 = downInfoList.get(order[3]);
//			String c7 = rightInfoList.get(order[4]);
//			String c8 = rightInfoList.get(order[5]);		
//			// change colors
//			leftInfoList.get(order[0]).setColor(c1);
//			leftInfoList.get(order[1]).setColor(c2);
//			downInfoList.get(order[2]).setColor(c3);
//			downInfoList.get(order[3]).setColor(c4);
//			rightInfoList.get(order[4]).setColor(c5);
//			rightInfoList.get(order[5]).setColor(c6);
//			upInfoList.get(order[6]).setColor(c7);
//			upInfoList.get(order[7]).setColor(c8);
//			// rotate the current face
//			String c9 = backInfoList.get(0);
//			String c10 = backInfoList.get(1);
//			String c11 = backInfoList.get(2);
//			String c12 = backInfoList.get(3);
//			backInfoList.get(0).setColor(c11);
//			backInfoList.get(1).setColor(c9);
//			backInfoList.get(2).setColor(c12);
//			backInfoList.get(3).setColor(c10);
			
		}
	}
	
	public void left(int k) {
		/* clockwise:  
		 * left rotate and right stay the same
		 * up's 0&2 go front; front's 0&2 go down; 
		 * down's 0&2 go back; back's 3&1 go up */
		
		int[] order = {0, 2, 0, 2, 0, 2, 3, 1}; 
		// begins from up eg: up's 0&2 become front's 0&2
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, upInfoList, frontInfoList, downInfoList, backInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, leftInfoList);
			
//			// record colors before changing
//			String c1 = backInfoList.get(order[6]);
//			String c2 = backInfoList.get(order[7]);
//			String c3 = upInfoList.get(order[0]);
//			String c4 = upInfoList.get(order[1]);
//			String c5 = frontInfoList.get(order[2]);
//			String c6 = frontInfoList.get(order[3]);
//			String c7 = downInfoList.get(order[4]);
//			String c8 = downInfoList.get(order[5]);		
//			// change colors
//			upInfoList.get(order[0]).setColor(c1);
//			upInfoList.get(order[1]).setColor(c2);
//			frontInfoList.get(order[2]).setColor(c3);
//			frontInfoList.get(order[3]).setColor(c4);
//			downInfoList.get(order[4]).setColor(c5);
//			downInfoList.get(order[5]).setColor(c6);
//			backInfoList.get(order[6]).setColor(c7);
//			backInfoList.get(order[7]).setColor(c8);
//			// rotate the current face
//			String c9 = leftInfoList.get(0);
//			String c10 = leftInfoList.get(1);
//			String c11 = leftInfoList.get(2);
//			String c12 = leftInfoList.get(3);
//			leftInfoList.get(0).setColor(c11);
//			leftInfoList.get(1).setColor(c9);
//			leftInfoList.get(2).setColor(c12);
//			leftInfoList.get(3).setColor(c10);
			
		}
	}
	
	public void right(int k) {
		/* clockwise: 
		 * right rotate and left stay the same
		 * up's 1&3 go back; back's 2&0 go down; 
		 * down's 1&3 go front; front's 1&3 go up */
		
		int[] order = {1, 3, 2, 0, 1, 3, 1, 3}; 
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, upInfoList, backInfoList, downInfoList, frontInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, rightInfoList);
			
//			// record colors before changing
//			String c1 = frontInfoList.get(order[6]);
//			String c2 = frontInfoList.get(order[7]);
//			String c3 = upInfoList.get(order[0]);
//			String c4 = upInfoList.get(order[1]);
//			String c5 = backInfoList.get(order[2]);
//			String c6 = backInfoList.get(order[3]);
//			String c7 = downInfoList.get(order[4]);
//			String c8 = downInfoList.get(order[5]);		
//			// change colors
//			upInfoList.get(order[0]).setColor(c1);
//			upInfoList.get(order[1]).setColor(c2);
//			backInfoList.get(order[2]).setColor(c3);
//			backInfoList.get(order[3]).setColor(c4);
//			downInfoList.get(order[4]).setColor(c5);
//			downInfoList.get(order[5]).setColor(c6);
//			frontInfoList.get(order[6]).setColor(c7);
//			frontInfoList.get(order[7]).setColor(c8);
//			// rotate the current face
//			String c9 = rightInfoList.get(0);
//			String c10 = rightInfoList.get(1);
//			String c11 = rightInfoList.get(2);
//			String c12 = rightInfoList.get(3);
//			rightInfoList.get(0).setColor(c11);
//			rightInfoList.get(1).setColor(c9);
//			rightInfoList.get(2).setColor(c12);
//			rightInfoList.get(3).setColor(c10);
			
		}
	}
	
	public void up(int k) {
		/* clockwise: 
		 * up rotate and down stay the same
		 * left's 0&1 go back; back's 0&1 go right; 
		 * right's 0&1 go front; front's 0&1 go left */
		
		int[] order = {0, 1, 0, 1, 0, 1, 0, 1}; 
		// begins from left eg: left's 0&1 become back's 0&1
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, leftInfoList, backInfoList, rightInfoList, frontInfoList);
		
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, upInfoList);
			
//			// record colors before changing
//			String c1 = frontInfoList.get(order[6]);
//			String c2 = frontInfoList.get(order[7]);
//			String c3 = leftInfoList.get(order[0]);
//			String c4 = leftInfoList.get(order[1]);
//			String c5 = backInfoList.get(order[2]);
//			String c6 = backInfoList.get(order[3]);
//			String c7 = rightInfoList.get(order[4]);
//			String c8 = rightInfoList.get(order[5]);		
//			// change colors
//			leftInfoList.get(order[0]).setColor(c1);
//			leftInfoList.get(order[1]).setColor(c2);
//			backInfoList.get(order[2]).setColor(c3);
//			backInfoList.get(order[3]).setColor(c4);
//			rightInfoList.get(order[4]).setColor(c5);
//			rightInfoList.get(order[5]).setColor(c6);
//			frontInfoList.get(order[6]).setColor(c7);
//			frontInfoList.get(order[7]).setColor(c8);
//			// rotate the current face
//			String c9 = upInfoList.get(0);
//			String c10 = upInfoList.get(1);
//			String c11 = upInfoList.get(2);
//			String c12 = upInfoList.get(3);
//			upInfoList.get(0).setColor(c11);
//			upInfoList.get(1).setColor(c9);
//			upInfoList.get(2).setColor(c12);
//			upInfoList.get(3).setColor(c10);
			
		}
	}
	
	public void down(int k) {
		/* clockwise: 
		 * down rotate and up stay the same
		 * left's 2&3 go front; front's 2&3 go right; 
		 * right's 2&3 go back; back's 2&3 go left */
		
		int[] order = {2, 3, 2, 3, 2, 3, 2, 3};
		ArrayList<ArrayList<String>> orderList = new ArrayList<ArrayList<String>>();
		Collections.addAll(orderList, leftInfoList, frontInfoList, rightInfoList, backInfoList);
		
		for (int i = 0; i < k; i++) {
			rotateClockwise(order, orderList, downInfoList);
			
//			// record colors before changing
//			String c1 = backInfoList.get(order[6]);
//			String c2 = backInfoList.get(order[7]);
//			String c3 = leftInfoList.get(order[0]);
//			String c4 = leftInfoList.get(order[1]);
//			String c5 = frontInfoList.get(order[2]);
//			String c6 = frontInfoList.get(order[3]);
//			String c7 = rightInfoList.get(order[4]);
//			String c8 = rightInfoList.get(order[5]);		
//			
//			// change colors
//			leftInfoList.get(order[0]).setColor(c1);
//			leftInfoList.get(order[1]).setColor(c2);
//			frontInfoList.get(order[2]).setColor(c3);
//			frontInfoList.get(order[3]).setColor(c4);
//			rightInfoList.get(order[4]).setColor(c5);
//			rightInfoList.get(order[5]).setColor(c6);
//			backInfoList.get(order[6]).setColor(c7);
//			backInfoList.get(order[7]).setColor(c8);
//			
//			// rotate the current face
//			String c9 = downInfoList.get(0);
//			String c10 = downInfoList.get(1);
//			String c11 = downInfoList.get(2);
//			String c12 = downInfoList.get(3);
//			
//			downInfoList.get(0).setColor(c11);
//			downInfoList.get(1).setColor(c9);
//			downInfoList.get(2).setColor(c12);
//			downInfoList.get(3).setColor(c10);
		}
	}
	
	// realize front(), back(), etc.
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
	
	// check if the cube is solved
	public boolean isSolved() {	
		// assuming the cube is under control - no unreachable states
		// check five sides
		if (isSameColor(frontInfoList) && isSameColor(backInfoList) && isSameColor(leftInfoList)
				&& isSameColor(rightInfoList) && isSameColor(upInfoList)) {
			return true;
		}
		return false;
		
	}
	
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
	
	public void randomize(int k) {
		int lastSide = 0;
		int lastTurns = 0;
		
		String side_direction;
		
		for (int i = 0; i < k; i++) {
			// if the cube is in solved status, it's okay to rotate any side in any direction
			if (solved) {
				//System.out.print("k = " + Integer.toString(i) + " ");
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
			else {
				//System.out.print("k = " + Integer.toString(i) + " ");
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
				
				boolean undo = true;
				boolean isSame = isSame(lastTurns, turns);
				while (undo == false) {
					if ((whichSide == 1 || whichSide == 2) && isSame && (lastSide == 1 || lastSide == 2)) {
						whichSide = rn.nextInt(6) + 1;
					}
					else if ((whichSide == 3 || whichSide == 4) && isSame && (lastSide == 3 || lastSide == 4)) {
						whichSide = rn.nextInt(6) + 1;
					}
					else if ((whichSide == 5 || whichSide == 6) && isSame && (lastSide == 5 || lastSide == 6)) {
						whichSide = rn.nextInt(6) + 1;
					}
				}
				
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
			solved = false;
			
			// add record into the array
			side_direction_records.add(side_direction);
		}
			
	}
	
	boolean isSame(int lastTurns, int turns) {
		if ((lastTurns == 1 && turns == 3) || (lastTurns == 3 && turns == 1)) {
			return true;
		}	
		return false;
		
	}
	
	// reset the cube to its solved status
	public void reset() {	
		frontInfoList = resetList(frontInfoList, "red");
		backInfoList = resetList(backInfoList, "orange");
		leftInfoList = resetList(leftInfoList, "green");
		rightInfoList = resetList(rightInfoList, "blue");
		upInfoList = resetList(upInfoList, "white");
		downInfoList = resetList(downInfoList, "yellow");
		
		// clear side_direction_records
		side_direction_records.clear();
		solved = true;
		
	}
	
	ArrayList<String> resetList(ArrayList<String> list, String color) {
		for (int i = 0; i < 4; i++) {
			list.set(i, color);
		}	
		return list;
		
	}
	
	public A1Cube clone() {
		
		return this;
		
	}
	
}
