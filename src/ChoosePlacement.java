import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChoosePlacement {

    private Board board;

    ChoosePlacement(){
        JFrame frame = new JFrame("Choose Placement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //panel 1
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.lightGray);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setPreferredSize(new Dimension(500, 70));
        JLabel l1 = new JLabel("The boat placement will be randomly generated for you.");
        l1.setFont(new Font("Serif",Font.BOLD, 15));
        l1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton chooseRandom = new JButton("Random");
        chooseRandom.setAlignmentX(Component.LEFT_ALIGNMENT);
        chooseRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Please select the board dimensions and START the game");
                frame.dispose();
            }
        });
        panel1.add(l1);
        panel1.add(chooseRandom);

        //panel 2
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setPreferredSize(new Dimension(500, 70));
        JLabel l2 = new JLabel("Choose valid file with boat placement.");
        l2.setFont(new Font("Serif",Font.BOLD, 15));
        l2.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel l3 = new JLabel("To see rules for a valid file plese see the 'Rules' section");
        l3.setFont(new Font("Serif",Font.ITALIC, 10));
        l3.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton chooseFile = new JButton("Choose File");
        chooseFile.setAlignmentX(Component.LEFT_ALIGNMENT);
        chooseFile.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if (e.getSource() == chooseFile) {
                     File file;
                     int response;
                     JFileChooser chooser = new JFileChooser(".");

                     chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                     response = chooser.showOpenDialog(null);
                     if (response == JFileChooser.APPROVE_OPTION) {
                         file = chooser.getSelectedFile();
                         Board b2 = new Board(file);
                         Character[][] ch = b2.getBoard();
                         for (Character[] i : ch) {
                             for (Character j : i) {
                                 System.out.print(j);
                             }
                             System.out.println();
                         }
                     }
                 }
                 frame.dispose();
             }
         });
        panel2.add(l2);
        panel2.add(l3);
        panel2.add(chooseFile);

        //central panel (if the window is resized)
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.lightGray);


        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.add(panel3, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new ChoosePlacement();
    }


}
