import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClickableItemList extends JScrollPane {
    private int heldcount;
    private JPanel[] planes;
    private ClickableItem[] items;
    private JLabel[] lables;
    private JPanel panel;
    private Window windwo;

    ClickableItemList(ArrayList<Item> listIn, Window windwoIn, Item mainitem) {
        windwo = windwoIn;

        panel = new JPanel();
        add(panel);

        if (listIn == null) {
            listIn = new ArrayList<Item>();
        }
        heldcount = 0;


        panel.setPreferredSize(new Dimension(200, listIn.size() * 50));

        panel.setBackground(Color.GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //SearchPanel.add(scrolspace);

        setViewportView(panel);
        setPreferredSize(new Dimension(200, 100));
        //scrolpane.setPreferredSize(new Dimension(100,1000));

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Update(listIn, mainitem);
/*
        planes = new JPanel[listIn.size()];
        items = new ClickableItem[listIn.size()];
        lables = new JLabel[listIn.size()];

        for (int i = 0; i < listIn.size(); i++) {
            planes[i] = new JPanel();
            panel.add(planes[i]);
            planes[i].setBackground(Color.CYAN);
            planes[i].setPreferredSize(new Dimension(200, 50));
            planes[i].setLayout(new GridLayout(1, 2));

            items[i] = new ClickableItem(listIn.get(i), windwoIn);
            planes[i].add(items[i]);

            lables[i] = new JLabel(listIn.get(i).GetName());
            planes[i].add(lables[i]);

        }
        */
    }

    void Update(ArrayList<Item> listIn, Item mainItem) {
        for (int i = 0; i < heldcount; i++) {
            planes[i].remove(items[i]);
            planes[i].remove(lables[i]);
            panel.remove(planes[i]);
        }
        heldcount = listIn.size();
        planes = new JPanel[listIn.size()];
        items = new ClickableItem[listIn.size()];
        lables = new JLabel[listIn.size()];

        if (mainItem != null) {
            JPanel infopan = new JPanel();
            infopan.setBackground(Color.WHITE);
            JLabel namelabel = new JLabel(mainItem.GetName());
            panel.add(infopan);
            infopan.add(namelabel);
        }

        for (int i = 0; i < listIn.size(); i++) {
            planes[i] = new JPanel();
            panel.add(planes[i]);
            planes[i].setBackground(Color.DARK_GRAY);
            planes[i].setPreferredSize(new Dimension(200, 50));
            planes[i].setLayout(new GridLayout(1, 2));

            items[i] = new ClickableItem(listIn.get(i), windwo);
            planes[i].add(items[i]);

            lables[i] = new JLabel(listIn.get(i).GetName());
            planes[i].add(lables[i]);

        }
        windwo.frame.repaint();
        windwo.frame.revalidate();
    }
}
