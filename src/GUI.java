import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GUI {

    private static JFrame frame;
    private static Container c;
    private static CardLayout card;

    private static JPanel startPanel;
    private static JPanel searchPanel;
    private static JPanel itemPanel;

    private static JButton button1;
    private static JButton button2;
    private static JButton button3;
    private static JButton button4;
    private static JButton button5;
    private static JButton button6;

    private static JLabel label1;
    private static JLabel label2;
    private static JLabel label3;

    private static JTextField searchField;

    private static JList<Item> itemList;

    public static void main(String[] args) {
        card = new CardLayout();
        frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        c = frame.getContentPane();
        c.setLayout(card);

        startPanel = new JPanel();
        searchPanel = new JPanel();
        itemPanel = new JPanel();

        startPanel.setBackground(Color.BLUE);
        searchPanel.setBackground(Color.GREEN);
        itemPanel.setBackground(Color.ORANGE);

        c.add(startPanel, "startPanel");
        c.add(searchPanel, "searchPanel");
        c.add(itemPanel, "itemPanel");

        label1 = new JLabel("Start Panel");
        label2 = new JLabel("Search Panel");
        label3 = new JLabel("Item Panel");

        startPanel.add(label1);
        searchPanel.add(label2);
        itemPanel.add(label3);

        button1 = new JButton("2");
        button2 = new JButton("3");
        button3 = new JButton("1");
        button4 = new JButton("3");
        button5 = new JButton("1");
        button6 = new JButton("2");

        button1.addActionListener(new button2Listener());
        button2.addActionListener(new button3Listener());

        button3.addActionListener(new button1Listener());
        button4.addActionListener(new button3Listener());

        button5.addActionListener(new button1Listener());
        button6.addActionListener(new button2Listener());


        startPanel.add(button1);
        startPanel.add(button2);
        searchPanel.add(button3);
        searchPanel.add(button4);
        itemPanel.add(button5);
        itemPanel.add(button6);

        searchField = new JTextField(30);
        searchPanel.add(searchField);

        LinkedList<Item> h = new LinkedList<>();
        h.add((new Item(326, "Oak Wood", "C:/Users/Apka/O_Wood.jpg", null, "Oak dzwewo")));
        h.add(new Item(327, "Spruce Wood", "C:/Users/Apka/S_Wood.jpg", null, "Spruce dzwewo"));
        itemList = new JList<Item>(h);
        searchPanel.add(itemList);
    }

    private static class button1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(c,"startPanel");
        }
    }

    private static class button2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(c,"searchPanel");
        }
    }

    private static class button3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(c,"itemPanel");
        }
    }
}
