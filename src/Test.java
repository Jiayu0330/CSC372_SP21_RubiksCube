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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class Test {
	JButton resetButton = new JButton("Reset");
	JTextField inputField = new JTextField(2);
    Document inputDoc = inputField.getDocument();
    JTextArea report = new JTextArea(7, 0);
    
    Cube cube = new Cube();
    JPanel cubePanel = new CubePanel(cube);
    JPanel sidePanel = new JPanel();
    JPanel botPanel = new JPanel();
    JFrame frame = new JFrame("RubiksCube");
    
    // keep track of turns before resetting the cube
    int totalTurns = 0;
	
    // make it a class for testing purpose/ adding other functions in the future
	public Test() {
		sidePanel.add(resetButton);
		sidePanel.add(new JLabel("How many turns:"));
        sidePanel.add(inputField);
        
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

        inputDoc.addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                update();
            }
            public void insertUpdate(DocumentEvent e) {
                update();
            }
            public void removeUpdate(DocumentEvent e) {
                //updateReport();
            }
        });
        
    }
	
	public static void main(String[] args) throws InterruptedException {
		Test test = new Test();
		test.frame.setSize(820, 900);
		
	}
	
	void update() {
		try {
            String turns = inputDoc.getText(0, inputDoc.getLength());
            totalTurns += Integer.parseInt(turns);
            report.append("You entered: " + turns + " | Total turns: " + totalTurns + "\n");
            cube.randomize(Integer.parseInt(turns));
            cubePanel.repaint();     
            for (int i = 1; i < totalTurns + 1; i ++) {
            	report.append("Turn " + i + ": " + cube.side_direction_records.get(i - 1) + ". ");
            }   
            report.append("\n");
        } catch (Exception e) {
            report.setText(e.toString());
        }
		
	}
	
	void printResult() {
		System.out.print(cube.frontInfoList.toString());
		System.out.print(cube.backInfoList.toString());
		System.out.print(cube.leftInfoList.toString());
		System.out.print(cube.rightInfoList.toString());
		System.out.print(cube.upInfoList.toString());
		System.out.print(cube.downInfoList.toString());
		System.out.printf("%n");
		
	}

}
