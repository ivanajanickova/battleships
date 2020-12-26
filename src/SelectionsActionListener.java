import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SelectionsActionListener implements ActionListener {

    SelectionScreen selectionScreen;
    ChooseScoring scoring;
    ChoosePlacement placement;
    Board board = null;

    public void listenTo(SelectionScreen selectionScreen){
        this.selectionScreen = selectionScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(selectionScreen.isChoosePlacement(e)){
            placement = new ChoosePlacement();
        }
        else if (selectionScreen.isChooseScoring(e)){
            scoring = new ChooseScoring();
        }
        else  if(selectionScreen.isRules(e)){
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new File("src/Rules.pdf"));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        else if(selectionScreen.isHighScores(e)){
            new HighScoresScreen();
        }
        else if(selectionScreen.isExit(e)){
            selectionScreen.getFrame().dispose();
        }
        else if(selectionScreen.isStart(e)){
            if(this.placement == null || !placement.boardExists()){
                board = new Board(selectionScreen.getHeight(), selectionScreen.getWidth());
            }
            else{
                board = placement.getBoard();
            }
            if(board.getBoard() != null){
                PlayerSelectionScreen p =  new PlayerSelectionScreen(board, scoring);
            }
        }
    }
}
