import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class Test {
	
	// graphics elements
	JButton resetButton = new JButton("Reset");
	JTextField inputField = new JTextField(3);
    Document inputDoc = inputField.getDocument();
    JButton enterButton = new JButton("Enter");
    JButton checkButton = new JButton("Solved?");
    JTextArea report = new JTextArea(7, 70);
    
    Cube cube = new Cube();
    CubePanel cubePanel = new CubePanel(cube);
    JPanel sidePanel = new JPanel();
    JPanel botPanel = new JPanel();
    JFrame frame = new JFrame("RubiksCube");
    
    int totalTurns = 0; // keep track of turns before resetting the cube
	
    // make it a class for testing purpose/ adding other functions in the future
	public Test() {
		
		sidePanel.add(resetButton);
		sidePanel.add(new JLabel("How many turns:"));
        sidePanel.add(inputField);
        sidePanel.add(enterButton);
        sidePanel.add(checkButton);
        
        botPanel.add(report);
        botPanel.setBackground(Color.LIGHT_GRAY);
        report.setEditable(false);
        report.setBackground(Color.LIGHT_GRAY);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(cubePanel), BorderLayout.CENTER);
        frame.getContentPane().add(sidePanel, BorderLayout.EAST);
        frame.getContentPane().add(new JScrollPane(botPanel), BorderLayout.SOUTH);
        frame.setVisible(true);
        
        resetButton.addActionListener(new ActionListener() {	
            public void actionPerformed(ActionEvent e) {
            	cube.reset();
                cubePanel.repaint();
                report.append("Reseted! \n");
                totalTurns = 0;
            }
        });
        
        enterButton.addActionListener(new ActionListener() {     	
            public void actionPerformed(ActionEvent e) {
            	update();
            }
        });
        
        checkButton.addActionListener(new ActionListener() {	
            public void actionPerformed(ActionEvent e) {
            	boolean isSolved = cube.isSolved();
            	
            	if (isSolved) {
            		report.append("Solved. \n");
            	}
            	else {
            		report.append("Not Solved. \n");
            	}
            }
        });
        

//        inputDoc.addDocumentListener(new DocumentListener() {
//        	
//            public void changedUpdate(DocumentEvent e) {
//                update();
//            }
//            public void insertUpdate(DocumentEvent e) {
//                update();
//            }
//            public void removeUpdate(DocumentEvent e) {
//                update();
//            }
//        });
        
    }
	
	public static void main(String[] args) {
		
		Test test = new Test();
		test.frame.setSize(1000, 900);
		
		// test isSolved()
//		test.cube.front(1);
//		System.out.print(test.cube.isSolved() + " ");
//		test.cube.back(3);
//		System.out.print(test.cube.isSolved() + " ");
//		test.cube.right(1);
//		System.out.print(test.cube.isSolved() + " ");
//		test.cube.left(3);
//		System.out.print(test.cube.isSolved() + " ");
		
	}
	
	/* update the text area on the bottom panel */
	void update() {
		
		try {
            String turns = inputDoc.getText(0, inputDoc.getLength());
            totalTurns += Integer.parseInt(turns);
            report.append("You entered: " + turns + " | Total turns: " + totalTurns + "\n");
            cube.randomize(Integer.parseInt(turns));
            cubePanel.repaint();     
            for (int i = 1; i < totalTurns + 1; i ++) {
            	report.append("Turn " + i + ": " + cube.side_direction_records.get(i - 1) + ". ");
            	if (i%4 == 0) {
            		report.append("\n");
            	}
            }   
            report.append("\n");
        } catch (Exception e) {
            report.setText(e.toString());
        }
		
	}

}
