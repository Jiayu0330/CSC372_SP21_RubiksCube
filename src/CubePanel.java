import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class CubePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Cube cube;
	
	ArrayList<String> frontInfoList = new ArrayList<String>();
	ArrayList<String> backInfoList = new ArrayList<String>();
	ArrayList<String> leftInfoList = new ArrayList<String>();
	ArrayList<String> rightInfoList = new ArrayList<String>();
	ArrayList<String> upInfoList = new ArrayList<String>();
	ArrayList<String> downInfoList = new ArrayList<String>();
	
	int len = 70; // length of each small cube on the panel

	public CubePanel(Cube cube) {
		this.cube = cube;
		
		frontInfoList = cube.frontInfoList;
		backInfoList = cube.backInfoList;
		leftInfoList = cube.leftInfoList;
		rightInfoList = cube.rightInfoList;
		upInfoList = cube.upInfoList;
		downInfoList = cube.downInfoList;
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		fillFront(g2d);
		fillBack(g2d);
		fillLeft(g2d);
		fillRight(g2d);
		fillUp(g2d);
		fillDown(g2d);
		
		g2d.setColor(new Color(0, 0, 0)); // black border
		drawBorder(g2d, len*3, len*3); // front: red
		drawBorder(g2d, len*3, len*7); // back: orange
		drawBorder(g2d, len, len*3); // left: green
		drawBorder(g2d, len*5, len*3); // right: blue
		drawBorder(g2d, len*3, len); // up: white
		drawBorder(g2d, len*3, len*5); // down: yellow
		
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 22)); 
		g2d.drawString("FRONT", len*4, len*4);
		g2d.drawString("BACK", len*4, len*8);
		g2d.drawString("LEFT", len*2, len*4);
		g2d.drawString("RIGHT", len*6, len*4);
		g2d.drawString("UP", len*4, len*2);
		g2d.drawString("DOWN", len*4, len*6);
		
	}
	
	void drawBorder(Graphics g2d, int x, int y) {
		((Graphics2D) g2d).setStroke(new BasicStroke(3));
		g2d.drawRect(x, y, len, len);
		g2d.drawRect(x + len, y, len, len);
		g2d.drawRect(x, y + len, len, len);
		g2d.drawRect(x + len, y + len, len, len);
		
		((Graphics2D) g2d).setStroke(new BasicStroke(6));
		g2d.drawRect(x, y, len*2, len*2);
		
	}
	
	void fillFront(Graphics g2d) {
		ArrayList<String> colors = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			colors.add(frontInfoList.get(i));
		}
		fillColors(g2d, len*3, len*3, colors);
	}
	
	void fillBack(Graphics g2d) {
		ArrayList<String> colors = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			colors.add(backInfoList.get(i));
		}
		fillColors(g2d, len*3, len*7, colors);
	}
	
	void fillLeft(Graphics g2d) {
		ArrayList<String> colors = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			colors.add(leftInfoList.get(i));
		}
		fillColors(g2d, len, len*3, colors);
	}
	
	void fillRight(Graphics g2d) {
		ArrayList<String> colors = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			colors.add(rightInfoList.get(i));
		}
		fillColors(g2d, len*5, len*3, colors);
	}
	
	void fillUp(Graphics g2d) {
		ArrayList<String> colors = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			colors.add(upInfoList.get(i));
		}
		fillColors(g2d, len*3, len, colors);
	}
	
	void fillDown(Graphics g2d) {
		ArrayList<String> colors = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			colors.add(downInfoList.get(i));
		}
		fillColors(g2d, len*3, len*5, colors);
	}
	
	void fillColors(Graphics g2d, int x, int y, ArrayList<String> colors) {
		String currentColor = colors.get(0);
		setCurrentColor(g2d, currentColor);
		g2d.fillRect(x, y, len, len);
		
		currentColor = colors.get(1);
		setCurrentColor(g2d, currentColor);
		g2d.fillRect(x + len, y, len, len);
		
		currentColor = colors.get(2);
		setCurrentColor(g2d, currentColor);
		g2d.fillRect(x, y + len, len, len);
		
		currentColor = colors.get(3);
		setCurrentColor(g2d, currentColor);
		g2d.fillRect(x + len, y + len, len, len);
		
	}
	
	void setCurrentColor(Graphics g2d, String currentColor) {
		if (currentColor == "red") {
			g2d.setColor(new Color(255,0,0));
		}
		else if (currentColor == "orange") {
			g2d.setColor(new Color(255,165,0));
		}
		else if (currentColor == "green") {
			g2d.setColor(new Color(0,128,0));
		}
		else if (currentColor == "blue") {
			g2d.setColor(new Color(0,0,255));
		}
		else if (currentColor == "white") {
			g2d.setColor(new Color(255,255,255));
		}
		else {
			g2d.setColor(new Color(255,255,0));
		}
		
	}

}
