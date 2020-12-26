import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



//Displays 10 best scores and the corresponding Player Names (both winners and losers are included)
public class HighScoresScreen {

   // private final HashMap<String, Integer> playerScores = new HashMap<>();
    private List<Integer> scores = new ArrayList<>(Collections.nCopies(10, 0));
    private List<String> names = new ArrayList<>(Collections.nCopies(10, null));

    public HighScoresScreen() {
        this.readFile();

        JFrame frame = new JFrame("High Scores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.lightGray);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(50, 300));
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridLayout(10, 3));
        for (int i = 0; i < names.size(); i++) {
            panel.add(new JLabel(String.valueOf(i + 1)));
            panel.add(new JLabel(names.get(i)));
            panel.add(new JLabel(String.valueOf(scores.get(i))));
        }

        JLabel title = new JLabel("Ten Highest Scores");
        title.setFont(new Font("Serif", Font.PLAIN, 30));

        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == quit) {
                    frame.dispose();
                }
            }
        });
        JPanel panel3 = new JPanel();
        panel3.add(quit);

        frame.add(title, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);
        frame.add(new JPanel(), BorderLayout.EAST);
        frame.add(new JPanel(), BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);

    }

    public void readFile() {
        //reads the PlayerScores.txt file
        File file = new File("src/PlayerScores.txt");
        try (Scanner scan = new Scanner(new FileInputStream(file))) {
            try {
                while (scan.hasNext()) {
                    String line = scan.nextLine();
                    String[] splitLine = line.split(" ");
                    this.getBestPlayers(splitLine[0], Integer.parseInt(splitLine[1]));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getBestPlayers(String name, int score) {
        //makes an array of 10 best players + scores
        int count = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) < score && count == 0) {
                ArrayList<Integer> leftScores = new ArrayList<>(scores.subList(0, i));
                ArrayList<Integer> rightScores = new ArrayList<>(scores.subList(i, 9));
                ArrayList<String> leftNames = new ArrayList<>(names.subList(0, i));
                ArrayList<String> rightNames = new ArrayList<>(names.subList(i, 9));
                leftScores.add(score);
                leftNames.add(name);
                scores = Stream.of(leftScores, rightScores)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
                names = Stream.of(leftNames, rightNames)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
                count++;
            }

        }
    }
}
