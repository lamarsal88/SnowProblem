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

public class SnowProblem extends JFrame {
    /*The number of the comlumns in the board is 5 and the number of the rows in the board is 4. The size of the board squares is set to 90 pixels */
    static final int NoBoardColumns = 5, NoBoardRows = 4, boardSquareSize = 90;
    final java.util.ArrayList<Integer>[][] g = new java.util.ArrayList[NoBoardRows][NoBoardColumns];
    int treesPlaced = 0, ballPlaced = 0, selectedRow = -1, selectedColumn = -1;
    int phase = 0; //
    boolean over = false, won = false;
    JLabel status = new JLabel();

    public SnowProblem() {
        super("Snow Problem");
        //
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        for (int r = 0; r < NoBoardRows; r++)
            for (int c = 0; c < NoBoardColumns; c++) g[r][c] = new java.util.ArrayList<>();
        Canvas gameBoard = new Canvas();
        add(gameBoard, BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        status.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        south.add(status, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        for (String[] d : new String[][]{{"Up","-1","0"},{"Down","1","0"},{"Left","0","-1"},{"Righ","0","1"}}) {
            JButton b = new JButton(d[0]);
            int drageOnTheSameRow = Integer.parseInt(d[1]), drageOnTheSameColumn =  Integer.parseInt(d[2]);
            b.addActionListener(e -> { slide(drageOnTheSameRow, drageOnTheSameColumn); gameBoard.repaint(); });
            buttonPanel.add(b); 
    }
    JButton reset = new JButton("Reset");
    reset.addActionListener(e -> { resetGame(); gameBoard.repaint(); });
    buttonPanel.add(reset);
    south.add(buttonPanel,BorderLayout.SOUTH);
    add(south, BorderLayout.SOUTH);

    resetGame();
    pack();
    setLocationRelativeTo(null);
    setFocusable(true);
    addKeyListener(new KeyAdapter() {

    })


} 
}