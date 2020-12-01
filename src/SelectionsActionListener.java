import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionsActionListener implements ActionListener {

    SelectionScreen selectionScreen;

    public void ListenTo(SelectionScreen selectionScreen){
        this.selectionScreen = selectionScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(selectionScreen.isChoosePlacement(e)){
            ChoosePlacement c = new ChoosePlacement();
        }
        else if (selectionScreen.isChooseScoring(e)){
            ChooseScoring c = new ChooseScoring();
        }
        else  if(selectionScreen.isRules(e)){
            //TODO
        }
        else if(selectionScreen.isHighScores(e)){
            //TODO
        }
        else if(selectionScreen.isExit(e)){
            //TODO
        }
        else if(selectionScreen.isStart(e)){
            //TODO
        }
    }
}
