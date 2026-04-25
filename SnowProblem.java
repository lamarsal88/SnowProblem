/**
this java code is the snow problem
a frame by the name of SnowProblemFrame is created for the GUI window
@param xxxx
@author lamar alsalamah
@return
*/

import javax.swing.*;// import GUI components in classes in swing
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
SnowProblemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*  Execute a clean termination 
of the ongoing operation, ensure all temporary resources are released, and subsequently 
close the graphical user interface to conclude the session*/
SnowProblemFrame.setLocationRelativeTo(null);// Locate the window GUI in the center of the screen

/*Create an instance of JPanel that functions as the main workspace for the interface.
 Attach this panel to the window SnowProblemFrame by making it the frame’s default content area, 
 which will give a clean canvas for adding buttons, labels, game objects, and custom 
 drawing routines in the next steps */
JPanel SnowProblemPanel = new JPanel(); // Create a panel
SnowProblemFrame.setContentPane(SnowProblemPanel); // Use panel on Window
    }
}