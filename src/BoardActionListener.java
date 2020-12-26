import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class BoardActionListener implements ActionListener {

    private int roundCount = 1;
    private BoardScreen boardScreen;
    private final HashMap<Character, Integer> SCORING_MAP = new HashMap<>() {{
        put('C', 2);
        put('B', 4);
        put('S', 6);
        put('D', 8);
    }};

    public void listenTo(BoardScreen boardScreen){
        this.boardScreen = boardScreen;
        JButton[][] buttons = boardScreen.getButtons();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.changePlayerLabel();
        Coordinate c = boardScreen.getPushedButton(e);
        switch (boardScreen.getButtonIdentity()) {
            case 'S' -> {
                boardScreen.changeButton(c, 'S');
                if (roundCount % 2 == 1) {
                    boardScreen.getPlayer1().addScore(boardScreen.getShipsDestroyed(), 'S');
                } else {
                    boardScreen.getPlayer2().addScore(boardScreen.getShipsDestroyed(), 'S');
                }
                roundCount++;
            }
            case 'B' -> {
                boardScreen.changeButton(c, 'B');
                if (roundCount % 2 == 1) {
                    boardScreen.getPlayer1().addScore(boardScreen.getShipsDestroyed(), 'B');
                } else {
                    boardScreen.getPlayer2().addScore(boardScreen.getShipsDestroyed(), 'B');
                }
                roundCount++;
            }
            case 'D' -> {
                boardScreen.changeButton(c, 'D');
                if (roundCount % 2 == 1) {
                    boardScreen.getPlayer1().addScore(boardScreen.getShipsDestroyed(), 'D');
                } else {
                    boardScreen.getPlayer2().addScore(boardScreen.getShipsDestroyed(), 'D');
                }
                roundCount++;
            }
            case 'C' -> {
                boardScreen.changeButton(c, 'C');
                if (roundCount % 2 == 1) {
                    boardScreen.getPlayer1().addScore(boardScreen.getShipsDestroyed(), 'C');
                } else {
                    boardScreen.getPlayer2().addScore(boardScreen.getShipsDestroyed(), 'C');
                }
                roundCount++;
            }
            case 'N' -> {
                boardScreen.changeButton(c, 'N');
                roundCount++;
            }
        }
        this.changeScore();
        if (boardScreen.countShipsDestroyed() == 4){
            new EndScreen(boardScreen.getPlayer1(), boardScreen.getPlayer2());
            boardScreen.getFrame().dispose();
        }
    }


    public  void changePlayerLabel(){
        JLabel nextPlayer = new JLabel();
        boardScreen.getPlayerPanel().remove(boardScreen.getPlayerLabel());
        boardScreen.setPlayerLabel(nextPlayer);
        boardScreen.getPlayerLabel().setFont(new Font("Serif", Font.PLAIN, 30));
        boardScreen.getPlayerLabel().setAlignmentX(Component.CENTER_ALIGNMENT);
        if(roundCount % 2 == 0){
            nextPlayer.setText(boardScreen.getPlayer1().getName());
            boardScreen.getPlayerPanel().add(boardScreen.getPlayerLabel());
            boardScreen.getPlayerPanel().revalidate();
        }
        else {
            nextPlayer.setText(boardScreen.getPlayer2().getName());
            boardScreen.getPlayerPanel().add(boardScreen.getPlayerLabel());
            boardScreen.getPlayerPanel().revalidate();
        }
    }

    public  void changeScore(){
        if(roundCount % 2 == 0){
            boardScreen.getPlayer1ScorePanel().remove(boardScreen.getPlayer1ScoreLabel());
            JLabel nextScore = new JLabel(String.valueOf(boardScreen.getPlayer1().getScore()));
            boardScreen.setPlayer1ScoreLabel(nextScore);
            boardScreen.getPlayer1ScoreLabel().setAlignmentX(Component.CENTER_ALIGNMENT);
            boardScreen.getPlayer1ScorePanel().add(boardScreen.getPlayer1ScoreLabel());
            boardScreen.getPlayer1ScorePanel().revalidate();
        }
        else{
            boardScreen.getPlayer2ScorePanel().remove(boardScreen.getPlayer2ScoreLabel());
            JLabel nextScore = new JLabel(String.valueOf(boardScreen.getPlayer2().getScore()));
            boardScreen.setPlayer2ScoreLabel(nextScore);
            boardScreen.getPlayer2ScoreLabel().setAlignmentX(Component.CENTER_ALIGNMENT);
            boardScreen.getPlayer2ScorePanel().add(boardScreen.getPlayer2ScoreLabel());
            boardScreen.getPlayer2ScorePanel().revalidate();
        }
    }
}
