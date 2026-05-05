import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
* @param xxxx
* @author lamar alsalamah
* @return
**/
//
public class SnowProblem extends JFrame {
    /*The number of the comlumns in the board is 5 and the number of the rows in the board is 4. The size of the board squares is set to 90 pixels */
    static final int NoBoardColumns = 5, NoBoardRows = 4, boardSquareSize = 90;
    final java.util.ArrayList<Integer>[][] g = new java.util.ArrayList[NoBoardRows][NoBoardColumns];
    int treesPlaced = 0, ballsPlaced = 0, selectedRow = -1, selectedColumn = -1;
    int  gameStage = 0; 
    boolean snowManBuilt = false, endOfGame = false;
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
        for (String[] d : new String[][]{{"Up","-1","0"},{"Down","1","0"},{"Left","0","-1"},{"Right","0","1"}}) {
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
        public void keyPressed(KeyEvent KeySelected) {
            int keyboardButtonSelected = KeySelected.getKeyCode();
            if (keyboardButtonSelected == KeyEvent.VK_R ) { resetGame(); gameBoard.repaint(); return; }
            int drageOnTheSameRow = 0, drageOnTheSameColumn = 0;
            if (keyboardButtonSelected == KeyEvent.VK_UP ) drageOnTheSameRow = -1;
            else if (keyboardButtonSelected == KeyEvent.VK_DOWN ) drageOnTheSameRow = 1;
            else if (keyboardButtonSelected == KeyEvent.VK_LEFT ) drageOnTheSameColumn = -1;
            else if (keyboardButtonSelected == KeyEvent.VK_RIGHT ) drageOnTheSameColumn = 1;
            else return;
            slide(drageOnTheSameRow, drageOnTheSameColumn);
            gameBoard.repaint();
        }
    });
} 

void resetGame() {
    for (int r = 0; r < NoBoardRows; r++)
        for (int c = 0; c < NoBoardColumns; c++) g[r][c].clear();
    treesPlaced = 0; ballsPlaced = 0; selectedRow = selectedColumn = -1;
    gameStage = 0; snowManBuilt = false; endOfGame = false;
    updateStatus();
}

String ballName(int code) {
    return code == 2 ? "large snowball" : code == 3 ? "small snowball" : "snowman head";
}

void updateStatus() {
    if (endOfGame) status.setText("You win! Press Reset to play again.");
    else if (snowManBuilt) status.setText("Game over — piece fell off. Click on the reset button to reset.");
    else if (gameStage == 0) status.setText("Place tree " + (treesPlaced + 1) + " of 2 (click an empty square).");
    else if (gameStage == 1) {
        int code = 2 + ballsPlaced;
        status.setText("Place the " + ballName(code) + " (click an empty square).");
    }
    else status.setText("Click a ball/head, then use the direction buttons to slide.");
} 

int top(int r, int c) {
   java.util.ArrayList<Integer> s = g[r][c];
    return s.isEmpty() ? 0 : s.get(s.size() - 1);
}

boolean canStackOn(int mover, int r, int c) {
    int t = top(r,c);
    if (mover == 3 && t == 2) return true;
    if (mover == 4 && t == 3) return true;
    return false;
}

void slide(int drageOnTheSameRow, int drageOnTheSameColumn) {
    if (gameStage != 2 || snowManBuilt || endOfGame || selectedRow < 0) return;
    int piece = top(selectedRow, selectedColumn);
    if (piece < 2) return;
    g[selectedRow][selectedColumn].remove(g[selectedRow][selectedColumn].size() - 1);
    int r = selectedRow, co = selectedColumn;
    while (true) {
        int newRow = r + drageOnTheSameRow, newColumn = co + drageOnTheSameColumn;
        if (newRow < 0 || newRow >= NoBoardRows || newColumn < 0 || newColumn >= NoBoardColumns) {
            snowManBuilt = true; selectedRow = selectedColumn = -1; updateStatus(); return;
        }
        int t = top(newRow, newColumn);
        if (t == 0) {r = newRow; co = newColumn; continue; }
        if (canStackOn(piece, newRow, newColumn)) {
            g[newRow][newColumn].add(piece);
            selectedRow = newRow; selectedColumn = newColumn;
            checkWin();
            updateStatus();
            return;
        }
        g[r][co].add(piece);
        selectedRow = r; selectedColumn = co;
        updateStatus();
        return;
    }
}

void checkWin() {
    for (int r = 0; r < NoBoardRows; r++)
        for (int c = 0; c < NoBoardColumns; c++) {
    java.util.ArrayList<Integer> s = g[r][c];
if (s.size() == 3 && s.get(0) == 2 && s.get(1) == 3 && s.get(2) == 4) {
    endOfGame = true;
    SwingUtilities.invokeLater(() -> showWinDialog());
    return;
}
}
}

void showWinDialog() {
        Object[] options = { "Play again", "Close" };
        int choice = JOptionPane.showOptionDialog(
                this,
                "You won!  You rebuilt the snowman.",
                "The Snow Problem",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
        if (choice == 0) {
            resetGame();
            repaint();
        }
    }

    class Canvas extends JPanel {
        Canvas() {
            setPreferredSize(new Dimension(NoBoardColumns * boardSquareSize, NoBoardRows * boardSquareSize));
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent MouseClickCoordinates) {
                    int ColumnPosistionOnBoard = MouseClickCoordinates.getX()/boardSquareSize, RowPosistionOnBoard = MouseClickCoordinates.getY() / boardSquareSize;
                    if (RowPosistionOnBoard < 0 || RowPosistionOnBoard >= NoBoardRows || ColumnPosistionOnBoard < 0 || ColumnPosistionOnBoard >= NoBoardColumns) return;
                    if (gameStage == 0) {
                        if (g[RowPosistionOnBoard][ColumnPosistionOnBoard].isEmpty()) {
                            g[RowPosistionOnBoard][ColumnPosistionOnBoard].add(1);
                            treesPlaced++;
                            if (treesPlaced == 2) gameStage = 1;
                            updateStatus();
                            repaint();
                        }
                    } else if (gameStage == 1) {
                        if (g[RowPosistionOnBoard][ColumnPosistionOnBoard].isEmpty()) {
                            int code = 2 + ballsPlaced;
                            g[RowPosistionOnBoard][ColumnPosistionOnBoard].add(code);
                            ballsPlaced++;
                            if (ballsPlaced == 3) gameStage = 2;
                            updateStatus();
                            repaint();
                        }
                    } else if (!snowManBuilt && !endOfGame) {
                        int t = top(RowPosistionOnBoard, ColumnPosistionOnBoard);
                        if (t >= 2) {selectedRow = RowPosistionOnBoard; selectedColumn = ColumnPosistionOnBoard; repaint(); }
                    }
                    SnowProblem.this.requestFocusInWindow();
                }
        });
    }

    protected void paintComponent(Graphics setColorBoard) {
        super.paintComponent(setColorBoard);
        Graphics2D setColorBoardSquares = (Graphics2D) setColorBoard;
        setColorBoardSquares.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int r = 0; r < NoBoardRows; r++) for (int c = 0; c < NoBoardColumns; c++) {
            int x = c * boardSquareSize, y = r * boardSquareSize;
            setColorBoardSquares.setColor((r + c) % 2 == 0 ? new Color(0xffffff) : new Color(0xD6E8F7) );
            setColorBoardSquares.fillRect(x, y, boardSquareSize, boardSquareSize);
            if (r == selectedRow && c == selectedColumn) {
                setColorBoardSquares.setColor(new Color(0xFFC107));
                setColorBoardSquares.setStroke(new BasicStroke(3));
                setColorBoardSquares.drawRect(x + 2, y + 2, boardSquareSize - 4, boardSquareSize - 4);
            }
            drawStack(setColorBoardSquares, g[r][c], x, y);
        }
        setColorBoardSquares.setColor(Color.GRAY);
        for (int i = 0; i <= NoBoardColumns; i++) setColorBoardSquares.drawLine(i * boardSquareSize, 0, i * boardSquareSize, NoBoardRows * boardSquareSize);
        for (int i = 0; i <= NoBoardRows; i++) setColorBoardSquares.drawLine(0, i * boardSquareSize, NoBoardColumns * boardSquareSize, i * boardSquareSize);
    }

    void drawStack(Graphics2D drawCompleteSnowMan, java.util.ArrayList<Integer> stackSnowballsHead, int x, int y) {
        if (stackSnowballsHead.isEmpty()) return;
        if (stackSnowballsHead.get(0) == 1) { drawTreeSnowballsHead(drawCompleteSnowMan, 1, x, y, 0); return; }
        for (int i = 0; i < stackSnowballsHead.size(); i++) drawTreeSnowballsHead(drawCompleteSnowMan, stackSnowballsHead.get(i), x, y, -i * 18);
    }

    void drawTreeSnowballsHead(Graphics2D TreeSnowballsHead, int p, int x, int y, int verticalOffset) {
        int centerOfBoardSquareCorx = x + boardSquareSize / 2, centerOfBoardSquareCory = y + boardSquareSize / 2 + verticalOffset;
        switch (p) {
            case 1:
                TreeSnowballsHead.setColor(new Color(0x6B3E1F));
                TreeSnowballsHead.fillRect(centerOfBoardSquareCorx - 6, centerOfBoardSquareCory + 18, 12, 18);
                TreeSnowballsHead.setColor(new Color(0x2E9E3A));
                TreeSnowballsHead.fillPolygon(new int[]{centerOfBoardSquareCorx, centerOfBoardSquareCorx - 30, centerOfBoardSquareCorx + 30}, new int[]{ centerOfBoardSquareCory - 32, centerOfBoardSquareCory + 22, centerOfBoardSquareCory + 22},  3);
                break;
            case 2:
                TreeSnowballsHead.setColor(Color.WHITE);
                TreeSnowballsHead.fillOval(centerOfBoardSquareCorx - 36, centerOfBoardSquareCory - 36, 72, 72);
                TreeSnowballsHead.setColor(Color.GRAY);
                TreeSnowballsHead.drawOval(centerOfBoardSquareCorx - 36, centerOfBoardSquareCory - 36, 72, 72);
                break;
            case 3:
                TreeSnowballsHead.setColor(Color.WHITE);
                TreeSnowballsHead.fillOval(centerOfBoardSquareCorx - 24, centerOfBoardSquareCory - 24, 48, 48);
                TreeSnowballsHead.setColor(Color.GRAY);
                TreeSnowballsHead.drawOval(centerOfBoardSquareCorx - 24, centerOfBoardSquareCory - 24, 48, 48);
                break;
            case 4:
                TreeSnowballsHead.setColor(Color.WHITE);
                TreeSnowballsHead.fillOval(centerOfBoardSquareCorx - 16, centerOfBoardSquareCory - 16, 32, 32);
                TreeSnowballsHead.setColor(Color.GRAY);
                TreeSnowballsHead.drawOval(centerOfBoardSquareCorx - 16, centerOfBoardSquareCory - 16, 32, 32);
                TreeSnowballsHead.setColor(Color.BLACK);
                TreeSnowballsHead.fillOval(centerOfBoardSquareCorx - 6, centerOfBoardSquareCory - 4, 3, 3);
                TreeSnowballsHead.fillOval(centerOfBoardSquareCorx + 3, centerOfBoardSquareCory - 4, 3, 3);
                TreeSnowballsHead.setColor(Color.ORANGE);
                TreeSnowballsHead.fillOval(centerOfBoardSquareCorx - 1, centerOfBoardSquareCory + 2, 5, 5);
                break;
        }
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnowProblem().setVisible(true));
    }
}