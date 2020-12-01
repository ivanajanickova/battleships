import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelectionScreen {

    private JButton choosePlacement;
    private JButton chooseScoring;
    private JButton rules;
    private JButton highScores;
    private JButton exit;
    private JButton start;



    private ActionListener actionListener;

    public void setActionListener(ActionListener l) {
        this.actionListener = l;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public SelectionScreen(ActionListener l) {
        setActionListener(l);

        JFrame frame = new JFrame("Battleship Game: Selection Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Panel with Title
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.lightGray);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Welcome to Battleship");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel subtitle = new JLabel("Please slect your options and get started");
        subtitle.setFont(new Font("Serif", Font.PLAIN, 20));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(title);
        panel1.add(subtitle);

        //panel with option to choose board size
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JSpinner rowSpinner = new JSpinner();
        JSpinner colSpinner = new JSpinner();
        panel2.add(new JLabel("Choose the Board Size"));
        panel2.add(new JLabel("Rows"));
        panel2.add(rowSpinner);
        panel2.add(new JLabel("Columns"));
        panel2.add(colSpinner);

        //Panel with game options
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.lightGray);
        choosePlacement = new JButton("Choose Ship Placement");
        choosePlacement.addActionListener(getActionListener());
        chooseScoring = new JButton("Choose Scoring System");
        chooseScoring.addActionListener(getActionListener());
        panel3.add(choosePlacement);
        panel3.add(chooseScoring);
        panel3.add(panel2);

        //Panel with additional options (exit, rules, highScores)
        JPanel panel4 = new JPanel();
        panel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel4.setBackground(Color.lightGray);
        rules = new JButton("Rules");
        highScores = new JButton("High Scores");
        exit = new JButton("Exit");
        panel4.add(rules);
        panel4.add(highScores);
        panel4.add(exit);

        //Panel with Start Button
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        panel5.setBackground(Color.lightGray);
        start = new JButton("Start Game");
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel5.add(start);
        panel5.add(panel4);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel3, BorderLayout.CENTER);
        frame.add(panel5, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }


    public boolean isChoosePlacement(ActionEvent e){
        return e.getSource() == choosePlacement;
    }
    public boolean isChooseScoring(ActionEvent e){
        return e.getSource() == chooseScoring;
    }
    public boolean isRules(ActionEvent e){
        return e.getSource() == rules;
    }
    public boolean isHighScores(ActionEvent e){
        return e.getSource() == highScores;
    }
    public boolean isExit(ActionEvent e){
        return e.getSource() == exit;
    }
    public boolean isStart(ActionEvent e){
        return e.getSource() == start;
    }


    public static void main(String[] args) {
        SelectionsActionListener sa = new SelectionsActionListener();
        SelectionScreen s1 = new SelectionScreen(sa);
        sa.ListenTo(s1);
    }
}
