import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseScoring implements ActionListener {

    private JButton yes;
    private JButton no;
    private JFrame frame;

    private  boolean favored = false;

    public boolean isFavored() {
        return favored;
    }

    public ChooseScoring() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 100));

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.lightGray);
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Would you like to favor 1st played by adding 2 points to his score?");
        label.setFont(new Font("Serif", Font.PLAIN, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel1.add(label);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);

        yes = new JButton("Yes");
        yes.addActionListener(this);
        no = new JButton("No");
        no.addActionListener(this);

        panel2.add(yes);
        panel2.add(no);

        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel1, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == yes){
            favored = true;
            frame.dispose();
        }
        else if(e.getSource() == no){
            favored = false;
            frame.dispose();
        }
    }
}
