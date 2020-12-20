import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionsActionListener implements ActionListener {

    SelectionScreen selectionScreen;
    ChooseScoring scoring;

    public void listenTo(SelectionScreen selectionScreen){
        this.selectionScreen = selectionScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(selectionScreen.isChoosePlacement(e)){
            ChoosePlacement placement = new ChoosePlacement();
        }
        else if (selectionScreen.isChooseScoring(e)){
            scoring = new ChooseScoring();
        }
        else  if(selectionScreen.isRules(e)){
            //TODO
        }
        else if(selectionScreen.isHighScores(e)){
            //TODO
        }
        else if(selectionScreen.isExit(e)){
            selectionScreen.getFrame().dispose();
        }
        else if(selectionScreen.isStart(e)){
            PlayerSelectionScreen p =  new PlayerSelectionScreen(selectionScreen, scoring);
        }
    }
}
