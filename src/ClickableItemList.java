import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ClickableItemList extends JScrollPane {
    private int heldcount;
    private JPanel[] planes;
    private ClickableItem[] items;
    private JLabel[] lables;
    private JPanel panel;
    private Window windwo;
    private Dimension preferd;
    private Item  mainItem;
    private JPanel infopan;
    private JLabel namelabel;


    ClickableItemList(ArrayList<Item> listIn, Window windwoIn, Item mainitemIn,Dimension preferedIn) {
        mainItem=mainitemIn;
        windwo = windwoIn;
        preferd=preferedIn;
        panel = new JPanel();
        add(panel);

        if (listIn == null) {
            listIn = new ArrayList<Item>();
        }
        heldcount = 0;

        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        //SearchPanel.add(scrolspace);

        setViewportView(panel);
        setPreferredSize(preferd);

        panel.setPreferredSize(new Dimension((int) preferd.getWidth(), 400));
        panel.setMinimumSize(preferd);
        //scrolpane.setPreferredSize(new Dimension(100,1000));

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Update(listIn, mainItem);
    }

    void Update(ArrayList<Item> listIn, Item mainItemIn) {
        mainItem=mainItemIn;
        for (int i = 0; i < heldcount; i++) {
            planes[i].remove(items[i]);
            planes[i].remove(lables[i]);
            panel.remove(planes[i]);
        }
        heldcount = listIn.size();
        planes = new JPanel[listIn.size()];
        items = new ClickableItem[listIn.size()];
        lables = new JLabel[listIn.size()];


        for (int i = 0; i < listIn.size(); i++) {
            planes[i] = new JPanel();
            planes[i].setPreferredSize(new Dimension(getPreferredSize().width,50));
            planes[i].setMaximumSize(new Dimension(getPreferredSize().width,50));
            panel.add(planes[i]);
            planes[i].setBackground(Color.LIGHT_GRAY);
            planes[i].setLayout(new BorderLayout(5,5));

            items[i] = new ClickableItem(listIn.get(i), windwo,windwo.img4);
            planes[i].add(items[i],BorderLayout.LINE_START);

            lables[i] = new JLabel(listIn.get(i).GetName());
            lables[i].setFont(windwo.customFont);
            planes[i].add(lables[i],BorderLayout.CENTER);

        }
        panel.setPreferredSize(new Dimension(getPreferredSize().width, heldcount*50));
        windwo.frame.repaint();
        windwo.frame.revalidate();
    }

}
