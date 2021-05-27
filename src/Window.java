import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Window implements ActionListener {

    JFrame frame;
    private Container cp;
    private JMenuBar menu;
    private JMenu i1;
    private JMenuItem i2;
    private JTextField t;
    private ClickableItemList cil;
    private ClickableItemList cil2;
    private JPanel ItemPanel;
    private JPanel SearchPanel;
    private JPanel craftingscreen;
    private JPanel mainitempanel;
    private JLabel amountlabl;
    private JButton next;
    private JButton prev;
    private ItemWindow iw;

    void displayWindow() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        cp = frame.getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));

        menu = new JMenuBar();
        cp.add(menu);
        frame.setJMenuBar(menu);
        i1 = new JMenu("Dupa");
        menu.add(i1);
        i2 = new JMenuItem("Kupa");
        i1.add(i2);
        i2.addActionListener(this);

        JPanel ItemPanel = new JPanel();
        JPanel SearchPanel = new JPanel();

        cp.add(ItemPanel);
        cp.add(SearchPanel);

        ItemPanel.setBackground(Color.DARK_GRAY);
        SearchPanel.setBackground(Color.LIGHT_GRAY);

        ItemPanel.setLayout(new FlowLayout());
        SearchPanel.setLayout(new FlowLayout());

        t = new JTextField(20);
        t.setPreferredSize(new Dimension(100, 30));
        t.addActionListener(this);

        SearchPanel.add(t);

        cil = new ClickableItemList(Main.GetItems(), this, null);
        SearchPanel.add(cil);

        //SearchPanel.add(cil);

///////////////////////////////////////////////////////////////////

        craftingscreen = new JPanel();
        craftingscreen.setLayout(new GridLayout(3, 3));
        craftingscreen.setBackground(Color.GRAY);
        ItemPanel.add(craftingscreen);

        next = new JButton("Next");
        prev = new JButton("Prev");
        next.addActionListener(this);
        prev.addActionListener(this);
        ItemPanel.add(next);
        ItemPanel.add(prev);

        mainitempanel = new JPanel();
        ItemPanel.add(mainitempanel);

        amountlabl = new JLabel();
        ItemPanel.add(amountlabl);

        cil2 = new ClickableItemList(Main.GetItems(), this, null);
        ItemPanel.add(cil2);
        frame.repaint();
        frame.revalidate();
    }


    void displayItemWindow(Item itemIn) {
        iw = new ItemWindow(itemIn);
        craftingscreen.removeAll();
        mainitempanel.removeAll();

        for (int i = 0; i < 9; i++) {
            if (iw.GetCurrentRecepture() == null || i >= iw.GetCurrentRecepture().ingredients.size() || iw.GetCurrentRecepture().ingredients.get(i) == null) {
                JPanel jp = new JPanel();
                jp.setPreferredSize(new Dimension(50, 50));
                craftingscreen.add(jp);
            } else {
                craftingscreen.add(new ClickableItem(iw.GetCurrentRecepture().ingredients.get(i), this));
            }
        }
        mainitempanel.add(new ClickableItem(iw.GetMainItem(), this));

        if (iw.GetCurrentRecepture() != null) {
            Integer x = iw.GetCurrentRecepture().resultQuantity;
            amountlabl.setText(x.toString());
        } else {
            amountlabl.setText("");
        }
        cil2.Update(iw.GetNextItems(), null);
        frame.repaint();
        frame.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(i2)) {
            JFileChooser jfc = new JFileChooser();
            int returnValue = jfc.showOpenDialog(null);

            //int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                Main.Update(selectedFile.getAbsolutePath());
            }
        } else if (e.getSource().equals(t)) {
            cil.Update(SearchEngine.FilterItems(Main.GetItems(), t.getText()), null);
        } else if (e.getSource().equals(next)) {
            iw.NextRecepture();
            craftingscreen.removeAll();
            for (int i = 0; i < 9; i++) {
                if (iw.GetCurrentRecepture() == null || i >= iw.GetCurrentRecepture().ingredients.size() || iw.GetCurrentRecepture().ingredients.get(i) == null) {
                    JPanel jp = new JPanel();
                    jp.setPreferredSize(new Dimension(50, 50));
                    craftingscreen.add(jp);
                } else {
                    craftingscreen.add(new ClickableItem(iw.GetCurrentRecepture().ingredients.get(i), this));
                }
            }
            frame.repaint();
            frame.revalidate();
        } else if (e.getSource().equals(prev)) {
            iw.PrevRecepture();
            craftingscreen.removeAll();
            for (int i = 0; i < 9; i++) {
                if (iw.GetCurrentRecepture() == null || i >= iw.GetCurrentRecepture().ingredients.size() || iw.GetCurrentRecepture().ingredients.get(i) == null) {
                    JPanel jp = new JPanel();
                    jp.setPreferredSize(new Dimension(50, 50));
                    craftingscreen.add(jp);
                } else {
                    craftingscreen.add(new ClickableItem(iw.GetCurrentRecepture().ingredients.get(i), this));
                }
            }
            frame.repaint();
            frame.revalidate();
        }

    }
}

