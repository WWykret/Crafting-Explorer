import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Window implements ActionListener {

    private static JFrame frame;
    private static Container cp;
    private static JMenuBar menu;

    void displayWindow() {
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        cp = frame.getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));

        menu = new JMenuBar();
        cp.add(menu);
        frame.setJMenuBar(menu);
        JMenuItem i1 = new JMenu("lol");
        menu.add(i1);
        i1.addActionListener(this);

        JPanel ItemPanel = new JPanel();
        JPanel SearchPanel = new JPanel();

        cp.add(ItemPanel);
        cp.add(SearchPanel);

        ItemPanel.setBackground(Color.BLUE);
        SearchPanel.setBackground(Color.RED);

        ItemPanel.setLayout(new FlowLayout());
        SearchPanel.setLayout(new FlowLayout());

        JTextField t = new JTextField(20);
        SearchPanel.add(t);

        JPanel scrolspace = new JPanel();
        scrolspace.setBackground(Color.ORANGE);
        scrolspace.setLayout(new BoxLayout(scrolspace, BoxLayout.Y_AXIS));

        SearchPanel.add(scrolspace);

        JButton b1 = new JButton("A");
        JButton b2 = new JButton("B");
        JButton b3 = new JButton("CCCC");
        JButton b4 = new JButton("DD");

        scrolspace.add(b1);
        scrolspace.add(b2);
        scrolspace.add(b3);
        scrolspace.add(b4);
    }



    void displayItemWindow(Item itemIn){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

