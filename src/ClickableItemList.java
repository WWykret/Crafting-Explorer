import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClickableItemList extends JScrollPane {
    ClickableItemList(ArrayList<Item> listIn,Window windwoIn){

        JPanel panel=new JPanel();
        add(panel);

        if(listIn==null){
            listIn=new ArrayList<Item>();
        }


        panel.setPreferredSize(new Dimension(200,listIn.size()*50));

        panel.setBackground(Color.ORANGE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //SearchPanel.add(scrolspace);

        setViewportView(panel);
        setPreferredSize(new Dimension(200,100));
        //scrolpane.setPreferredSize(new Dimension(100,1000));

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        JPanel[] planes=new JPanel[listIn.size()];
        ClickableItem[] items=new ClickableItem[listIn.size()];
        JLabel[] lables = new JLabel[listIn.size()];

        for(int i=0;i<listIn.size();i++){
            planes[i]=new JPanel();
            panel.add(planes[i]);
            planes[i].setBackground(Color.CYAN);
            planes[i].setPreferredSize(new Dimension(200,50));
            planes[i].setLayout(new GridLayout(1,2));

            items[i]=new ClickableItem(listIn.get(i),windwoIn);
            planes[i].add(items[i]);

            lables[i]=new JLabel(listIn.get(i).GetName());
            planes[i].add(lables[i]);

        }
    }
}
