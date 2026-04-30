import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * This game is called the snow problem. The board has 20 locations with size
 * of 5 by 4 board. There are two trees on the board. The location of the
 * trees can be changed by the gamer. On the board, there are two
 * snow balls: one small snowball and one large snowball. There is
 * also a head of the snowman. The balls can move horizontally or
 * vertically on the board. If the snawball does not hit a tree of another ball
 * or the snowman head, it falls off the board and the game is over. The snowman
 * head can move vertically or horizontally on the board one step
 * at a time. The trees are positioned at the beginning of the game and
 * should not be moved during the game. The goal of the game is to rebuild
 * the snowman by stacking the small snowball on the large snowball and stack
 * the head of the snowman head on the small snowball.
 *when one cell holds the full stack: large -> small -> head.
 *A "You won!" dialog appears on victory.
*Controls: click to place / select. Arrow keys (or buttons) to slide. R = reset.
@param xxxx
@author lamar alsalamah
@return
*/

//import javax.swing.*; // import GUI components in classes in swing
public class SnowProblem{
    public static void main(String[] args) {
        System.out.println("This will be printed");
        System.out.println("second line");
        System.out.println("third line");

    /*Create a clean, unpopulated top-level window that acts as the root container 
    for the problem’s GUI. Assign the instance name SnowProblemFrame*/
    JFrame SnowProblemFrame = new JFrame(); // Create a blank window for Snow problem
SnowProblemFrame.setVisible(true); // Make it visible
SnowProblemFrame.setTitle("Snow Problem!"); // set the title of the window GUI
SnowProblemFrame.setSize(1000, 1000); // set the size of the window GUI
SnowProblemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*Execute a clean termination 
of the ongoing operation, ensure all temporary resources are released, and subsequently 
close the graphical user interface to conclude the session*/
SnowProblemFrame.setLocationRelativeTo(null);// Locate the window GUI in the center of the screen

/*Create an instance of JPanel that functions as the main workspace for the interface.
 Attach this panel to the window SnowProblemFrame by making it the frame’s default content area, 
 which will give a clean canvas for adding buttons, labels, game objects, and custom 
 drawing routines in the next steps */
JPanel SnowProblemPanel = new JPanel(); // Create a panel
SnowProblemFrame.setContentPane(SnowProblemPanel); // Use panel on Window

/*Add JPanel "snowProblemDescription" on the top and center of the frame snowProblemFrame
 GUI to give the player a short description of the game*/
    JLabel SnowProblemDescription = new JLabel();
    SnowProblemDescription.setOpaque(true); //Back the the panel opaque.
    SnowProblemDescription.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));// Set the borders to 1.
    SnowProblemDescription.setBackground(Color.RED); // Set the background color of the label to green
    SnowProblemFrame.add(SnowProblemDescription,BorderLayout.NORTH);/*Position of the label is set to the top and center of the frame.
    Print the game goal on the label*/
    SnowProblemDescription.setText("Can you help the snowman? Build the snowman by guiding snowballs around obstacles until they are perfectly positioned to stack");

JButton b = new JButton("Press!"); 
SnowProblemPanel.add(b); 
SnowProblemFrame.setContentPane(SnowProblemPanel); 
    b.setPreferredSize(new Dimension(120, 40));
    SnowProblemPanel.add(b, BorderLayout.SOUTH);

   
    }
}
