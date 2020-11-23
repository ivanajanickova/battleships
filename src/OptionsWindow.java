import javax.swing.*;
import java.io.File;

public class OptionsWindow {

    public static void main(String[] args) {
        File file;
        int response;
        JFileChooser chooser = new JFileChooser(".");

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        response = chooser.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
            Board b2 = new Board(file);
            Character[][] ch = b2.getBoard();
            for(Character[] i : ch){
                for(Character j : i){
                    System.out.print(j);
                }
                System.out.println();
            }
        }



    }
}
