import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BoardScreen {

    JFrame frame;
    JPanel gridPanel;
    private final int width;
    private final int length;
    private ArrayList<Character> shipsDestroyed = new ArrayList(Collections.nCopies(4, null));
    private JButton[][] buttons; //all buttons of the grid
    private JLabel playerLabel;
    private final JPanel playerPanel;
    private final JPanel player1ScorePanel;
    private JLabel player1ScoreLabel;
    private final JPanel player2ScorePanel;
    private JLabel player2ScoreLabel;
    private Coordinate buttonCoordinates;
    private final HashMap<Character, Color> COLORS = new HashMap<>() {{
        put('S', Color.CYAN);
        put('D', Color.MAGENTA);
        put('B', Color.YELLOW);
        put('C', Color.RED);
        put('N', Color.BLUE);
    }};

    private Player player1;
    private Player player2;


    private final HashMap<Character, CoordinateArray> boatPlacement; //boatPlacement from Board class

    private ActionListener actionListener;

    public void setActionListener(ActionListener l) {
        this.actionListener = l;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public JLabel getPlayerLabel() {
        return playerLabel;
    }

    public void setPlayerLabel(JLabel playerLabel) {
        this.playerLabel = playerLabel;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public JPanel getPlayerPanel() {
        return playerPanel;
    }

    public JPanel getPlayer1ScorePanel(){
        return player1ScorePanel;
    }

    public JLabel getPlayer2ScoreLabel() {
        return player2ScoreLabel;
    }

    public JLabel getPlayer1ScoreLabel() {
        return player1ScoreLabel;
    }

    public void setPlayer1ScoreLabel(JLabel player1ScoreLabel) {
        this.player1ScoreLabel = player1ScoreLabel;
    }

    public JPanel getPlayer2ScorePanel() {
        return player2ScorePanel;
    }

    public void setPlayer2ScoreLabel(JLabel player2ScoreLabel) {
        this.player2ScoreLabel = player2ScoreLabel;
    }
    public ArrayList<Character> getShipsDestroyed(){
        return shipsDestroyed;
    }

    public JFrame getFrame() {
        return frame;
    }

    public BoardScreen(Board board, ActionListener l, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        width = board.getWidth();
        length = board.getLength();
        boatPlacement = board.getBOAT_PLACEMENT();
        this.setActionListener(l);
        frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 800));

        //Panel with game board (grid)
        gridPanel = new JPanel();
        gridPanel.setBackground(Color.lightGray);
        gridPanel.setPreferredSize(new Dimension(500, 500));
        GridLayout grid = new GridLayout(length, width);
        gridPanel.setLayout(grid);
        grid.setHgap(5);
        grid.setVgap(5);
        this.makeButtonGrid();

        //panel with player1 score
        player1ScoreLabel = new JLabel(String.valueOf(player1.getScore()));
        player1ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label1 = new JLabel(player1.getName() + " score:");
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        label1.setAlignmentX(Component.LEFT_ALIGNMENT);
        player1ScorePanel = new JPanel();
        player1ScorePanel.setBackground(Color.lightGray);
        player1ScorePanel.setLayout(new BoxLayout(player1ScorePanel, BoxLayout.Y_AXIS));
        player1ScorePanel.add(label1);
        player1ScorePanel.add(player1ScoreLabel);
        player1ScorePanel.setPreferredSize(new Dimension(200, 100));

        //panel with player2 score
        player2ScoreLabel = new JLabel(String.valueOf(player2.getScore()));
        player2ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label2 = new JLabel(player2.getName() + " score:");
        label2.setFont(new Font("Serif", Font.PLAIN, 20));
        label2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        player2ScorePanel = new JPanel();
        player2ScorePanel.setBackground(Color.lightGray);
        player2ScorePanel.setLayout(new BoxLayout(player2ScorePanel, BoxLayout.Y_AXIS));
        player2ScorePanel.add(label2);
        player2ScorePanel.add(player2ScoreLabel);
        player2ScorePanel.setPreferredSize(new Dimension(200, 100));

        //panel indicating whose turn it is
        JLabel label3 = new JLabel("TURN");
        label3.setFont(new Font("Serif", Font.PLAIN, 20));
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerLabel = new JLabel(player1.getName());
        playerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerPanel = new JPanel();
        playerPanel.setBackground(Color.lightGray);
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.add(label3);
        playerPanel.add(playerLabel);
        //playerPanel.setPreferredSize(new Dimension(300, 100));

        //Upper panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        JButton highScore = new JButton("High Scores");
        highScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == highScore){
                    new HighScoresScreen();
                }
            }
        });
        JButton quit = new JButton("Quit Game");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == quit){
                    frame.dispose();
                }
            }
        });

        panel.add(highScore, FlowLayout.LEFT);
        panel.add(player1ScorePanel);
        panel.add(playerPanel);
        panel.add(player2ScorePanel);
        panel.add(quit, FlowLayout.TRAILING);


        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);


    }

    public void makeButtonGrid() {
        //makes grid of buttons based on the input dimensions
        this.buttons = new JButton[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                JButton b = new JButton();
                b.setBackground(Color.darkGray);
                b.addActionListener(this.getActionListener());
                this.gridPanel.add(b);
                buttons[i][j] = b;
            }
        }
    }

    public Coordinate getPushedButton(ActionEvent e) {
        //returns Coordinates of a button that was pushed
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (e.getSource() == buttons[i][j]) {
                    buttonCoordinates = new Coordinate(i, j);
                    return buttonCoordinates;
                }
            }
        }
        return buttonCoordinates;
    }

    public Character getButtonIdentity() {
        //returns a char corresponding to ship, in case no ship was hit returns 'N'
        for (Character key : boatPlacement.keySet()) {
            for (int c = 0; c < boatPlacement.get(key).length(); c++) {
                if (boatPlacement.get(key).getCoordinateAtIndex(c).isEqual(this.buttonCoordinates)) {
                    boatPlacement.get(key).removeAtIndex(c);
                    if (boatPlacement.get(key).size() == 0){
                        shipsDestroyed.add(key);
                    }
                    return key;
                }
            }
        }
        return 'N';
    }


    public void changeButton(Coordinate c, Character ch) {
        //changes color of a button and disables it
        JButton b = buttons[c.getRow()][c.getCol()];
        b.setBackground(COLORS.get(ch));
        b.setEnabled(false);
    }

    public int countShipsDestroyed(){
        int count = 0;
        for(Character c : this.getShipsDestroyed()){
            if(c != null){
                count++;
            }
        }
        return count;
    }

}
