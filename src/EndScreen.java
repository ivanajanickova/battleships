import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class EndScreen {

    Player player1;
    Player player2;

    public EndScreen(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("GAME OVER!");
        label1.setFont(new Font("Serif", Font.PLAIN, 70));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        String winner;
        if(player1.getScore() == player2.getScore()){
            winner = "It's a tie";
        }
        else {
            winner = player1.getScore() > player2.getScore() ? player1.getName() : player2.getName();
            int score = Math.max(player1.getScore(), player2.getScore());
            winner = "The winner is " + winner + " with score: " + score;
        }
        this.savePlayerScore();
        JLabel label2 = new JLabel(winner);
        label2.setFont(new Font("Serif", Font.PLAIN, 50));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label1);
        panel.add(label2);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        JButton quit = new JButton("Return to Selections");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        JButton highScores = new JButton("High Scores");
        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == highScores){
                    new HighScoresScreen();
                }
            }
        });


        panel2.add(quit);
        panel2.add(highScores);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

    }

    public void savePlayerScore(){
        FileOutputStream output = null;
        PrintWriter fileOut = null;
        try {
            output = new FileOutputStream("src/PlayerScores.txt", true);
            fileOut = new PrintWriter(output, true);
            String record1 = player1.getName() + " " + player1.getScore();
            String record2 = player2.getName() + " " + player2.getScore();
            fileOut.println(record1);
            fileOut.println(record2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert fileOut != null;
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
