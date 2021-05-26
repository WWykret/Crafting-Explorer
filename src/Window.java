import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class Window implements ActionListener {

    private JFrame frame;
    private Container cp;
    private JMenuBar menu;
    private JMenu i1;
    private JMenuItem i2;
    private JTextField t;
    private ClickableItemList cil;


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
        i1 = new JMenu("Dupa");
        menu.add(i1);
        i2 = new JMenuItem("Kupa");
        i1.add(i2);
        i2.addActionListener(this);

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
        t.addActionListener(this);

        SearchPanel.add(t);

        ClickableItemList cil = new ClickableItemList(new ArrayList<Item>(),this);
        SearchPanel.add(cil);

        //SearchPanel.add(cil);

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

        JButton b20 = new JButton("X");
        ItemPanel.add(b20);

        ClickableItem ci1 = new ClickableItem(Main.GetItems().get(1),this);
        ItemPanel.add(ci1);

    }


    void displayItemWindow(Item itemIn){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(i2)){
            JFileChooser jfc = new JFileChooser();
            int returnValue = jfc.showOpenDialog(null);

            //int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                FileLoader f = new FileLoader(selectedFile.getAbsolutePath());
                Main.Update(selectedFile.getAbsolutePath());
            }
        }else if(e.getSource().equals(t)){
            cil = new ClickableItemList(SearchEngine.FilterItems(Main.GetItems(),t.getText()),this);
        }

    }
}

