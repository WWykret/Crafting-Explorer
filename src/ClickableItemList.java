import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ClickableItemList extends JScrollPane {
    private int heldcount;
    private JPanel[] planes;
    private ClickableItem[] items;
    //private JLabel[] lables;
    private JPanel panel;
    private Window windwo;
    private Item mainItem;
    private JTextArea[] ar1;
    private JPanel[] dummy;


    ClickableItemList(ArrayList<Item> listIn, Window windwoIn, Item mainitemIn, Dimension preferedIn) {
        mainItem = mainitemIn;
        windwo = windwoIn;
        panel = new JPanel();
        add(panel);
        if (listIn == null) {
            listIn = new ArrayList<Item>();
        }
        heldcount = 0;

        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        setViewportView(panel);
        setPreferredSize(preferedIn);

        panel.setPreferredSize(new Dimension((int) preferedIn.getWidth(), 400));
        panel.setMinimumSize(preferedIn);

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Update(listIn, mainItem);
    }

    void Update(ArrayList<Item> listIn, Item mainItemIn) {
        mainItem = mainItemIn;
        for (int i = 0; i < heldcount; i++) {
            planes[i].remove(items[i]);
            planes[i].remove(ar1[i]);
            planes[i].remove(dummy[i]);
            panel.remove(planes[i]);
        }
        heldcount = listIn.size();
        planes = new JPanel[listIn.size()];
        items = new ClickableItem[listIn.size()];
        ar1=new JTextArea[listIn.size()];
        dummy=new JPanel[listIn.size()];

        for (int i = 0; i < listIn.size(); i++) {
            planes[i] = new JPanel();
            planes[i].setPreferredSize(new Dimension(getPreferredSize().width, 50));
            planes[i].setMaximumSize(new Dimension(getPreferredSize().width, 50));
            planes[i].setBackground(new Color(197, 197, 197));
            planes[i].setLayout(new BorderLayout(5, 5));
            panel.add(planes[i]);

            items[i] = new ClickableItem(listIn.get(i), windwo, windwo.Down_Arrow);
            planes[i].add(items[i], BorderLayout.LINE_START);

            ar1[i] = new JTextArea(listIn.get(i).GetName());
            ar1[i].setLineWrap(true);
            ar1[i].setWrapStyleWord(true);
            ar1[i].setBackground(new Color(197, 197, 197));
            ar1[i].setFont(windwo.customFont);
            planes[i].add(ar1[i], BorderLayout.CENTER);

            dummy[i]=new JPanel();
            dummy[i].setBackground(new Color(197, 197, 197));
            dummy[i].setPreferredSize(new Dimension(10,50));
            planes[i].add(dummy[i], BorderLayout.LINE_END);

        }
        panel.setPreferredSize(new Dimension(getPreferredSize().width, heldcount * 50));
        windwo.frame.repaint();
        windwo.frame.revalidate();
    }
}
