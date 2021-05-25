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
        frame.setSize(600, 600);
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
        t.setPreferredSize(new Dimension(100,30));
        SearchPanel.add(t);

        JPanel scrolspace = new JPanel();

        scrolspace.setPreferredSize(new Dimension(100,400));

        scrolspace.setBackground(Color.ORANGE);
        scrolspace.setLayout(new BoxLayout(scrolspace, BoxLayout.Y_AXIS));

        //SearchPanel.add(scrolspace);

        JScrollPane scrolpane = new JScrollPane(scrolspace);
        scrolpane.setPreferredSize(new Dimension(100,100));
        //scrolpane.setPreferredSize(new Dimension(100,1000));

        scrolpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrolpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        SearchPanel.add(scrolpane);

        JButton b1 = new JButton("A");
        JButton b2 = new JButton("B");
        JButton b3 = new JButton("CCCC");
        JButton b4 = new JButton("DD");

        scrolspace.add(b1);
        scrolspace.add(b2);
        scrolspace.add(b3);
        scrolspace.add(b4);

        JPanel craftingscreen = new JPanel();
        craftingscreen.setLayout(new GridLayout(3,3));
        craftingscreen.setBackground(Color.GRAY);

        ItemPanel.add(craftingscreen);

        JButton b11 = new JButton("1");
        JButton b12 = new JButton("2");
        JButton b13 = new JButton("3");
        JButton b14 = new JButton("4");
        JButton b15 = new JButton("5");
        JButton b16 = new JButton("6");
        JButton b17 = new JButton("7");
        JButton b18 = new JButton("8");
        JButton b19 = new JButton("9");

        craftingscreen.add(b11);
        craftingscreen.add(b12);
        craftingscreen.add(b13);
        craftingscreen.add(b14);
        craftingscreen.add(b15);
        craftingscreen.add(b16);
        craftingscreen.add(b17);
        craftingscreen.add(b18);
        craftingscreen.add(b19);



    }

    void displayItemWindow(Item itemIn){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

