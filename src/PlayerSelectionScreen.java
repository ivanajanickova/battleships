import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerSelectionScreen {

    private Player player1;
    private Player player2;


    private final ChooseScoring scoring;


    public PlayerSelectionScreen(Board board, ChooseScoring scoring){
        this.scoring = scoring;

        JFrame frame  = new JFrame("Player Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 150));

        //Panel for selecting fist player
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.lightGray);
        panel1.setLayout(new FlowLayout());
        JTextField name1 = new JTextField();
        name1.setPreferredSize(new Dimension(100, 30));
        name1.setMaximumSize(name1.getPreferredSize());
        name1.setMinimumSize(name1.getPreferredSize());
        panel1.add(new JLabel("Name of the 1st player:"));
        panel1.add(name1);
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Panel for selecting second player
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new FlowLayout());
        JTextField name2 = new JTextField();
        name2.setPreferredSize(new Dimension(100, 30));
        name2.setMaximumSize(name2.getPreferredSize());
        name2.setMinimumSize(name2.getPreferredSize());
        panel2.add(new JLabel("Name of the 2nd player:"));
        panel2.add(name2);
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.setAlignmentY(Component.CENTER_ALIGNMENT);

        //Panel for players selection
        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.add(panel1);
        panel.add(panel2);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        //Panel with 'Play' button
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.lightGray);
        JButton button = new JButton("Play");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button && !name1.getText().equals("") && !name2.getText().equals("")){
                    player1 =  new Player(name1.getText());
                    favorPlayer1();
                    player2 = new Player(name2.getText());
                    BoardActionListener b = new BoardActionListener();
                    BoardScreen screen = new BoardScreen(board, b, player1, player2);
                    b.listenTo(screen);
                    frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please enter names for both players.");
                }
            }
        });
        panel3.add(button);
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.setAlignmentY(Component.CENTER_ALIGNMENT);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

    }

    public void favorPlayer1(){
        if(this.scoring != null && this.scoring.isFavored()) {
            this.player1.changeScoring();
        }
    }
}
