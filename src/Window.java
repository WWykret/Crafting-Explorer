import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    BufferedImage img4;
    BufferedImage img5;
    private JLabel nameLabel;
    Font customFont;

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



       // System.out.println(cp.getHeight()+" "+cp.getWidth());

        BufferedImage img1=null;
        BufferedImage img2 =null;
        BufferedImage img3 =null;
        img4 =null;
        img5=null;
        String filePath = new File("").getAbsolutePath();
        try {
            img1 = ImageIO.read(new File(filePath.concat("\\resources\\custom_images\\Left_Arrow_Active.png")));
            img2 = ImageIO.read(new File(filePath.concat("\\resources\\custom_images\\Right_Arrow_Active.png")));
            img3 = ImageIO.read(new File(filePath.concat("\\resources\\custom_images\\Right_Arrow.png")));
            img4 = ImageIO.read(new File(filePath.concat("\\resources\\custom_images\\Down_Arrow.png")));
            img5 = ImageIO.read(new File(filePath.concat("\\resources\\custom_images\\Furnace_Fire.png")));
        } catch (IOException e){
            System.out.println("lol");
        }

        try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(filePath.concat("\\resources\\custom_images\\Minecraft.ttf"))).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }

        ItemPanel = new JPanel();
        SearchPanel = new JPanel();

        cp.add(ItemPanel);
        cp.add(SearchPanel);

        ItemPanel.setBackground(new Color(197,197,197));
        SearchPanel.setBackground(Color.LIGHT_GRAY);

        ItemPanel.setLayout(null);
        SearchPanel.setLayout(new BoxLayout(SearchPanel, BoxLayout.Y_AXIS));

        ItemPanel.setPreferredSize(new Dimension(600, 600));
        SearchPanel.setPreferredSize(new Dimension(200, 600));
        System.out.println(ItemPanel.getWidth()+" "+ItemPanel.getHeight());

        t = new JTextField(40);
        t.setPreferredSize(new Dimension(SearchPanel.getPreferredSize().width, 25));
        t.addActionListener(this);
        t.setFont(customFont);
        SearchPanel.add(t);
        cil = new ClickableItemList(Main.GetItems(), this, null, new Dimension(SearchPanel.getPreferredSize().width, SearchPanel.getPreferredSize().height - t.getPreferredSize().height));
        SearchPanel.add(cil);

        //SearchPanel.add(cil);

///////////////////////////////////////////////////////////////////



        System.out.println(ItemPanel.getWidth()+" "+ItemPanel.getHeight());
        craftingscreen = new JPanel();
        craftingscreen.setLayout(new GridLayout(3, 3,2,2));
        craftingscreen.setBackground(new Color(197,197,197));
        ItemPanel.add(craftingscreen);
        craftingscreen.setBounds(30, 223, 154, 154);

        for (int i = 0; i < 9; i++){
            JPanel jp = new JPanel();
            jp.setPreferredSize(new Dimension(50, 50));
            jp.setBackground(new Color(140,140,140));
            craftingscreen.add(jp);
        }

        next = new JButton(new ImageIcon(img2));
        prev = new JButton(new ImageIcon(img1));
        next.addActionListener(this);
        prev.addActionListener(this);
        next.setBorderPainted(false);
        prev.setBorderPainted(false);
        next.setVisible(false);
        prev.setVisible(false);
        ItemPanel.add(next);
        ItemPanel.add(prev);
        prev.setBounds(30, 175, 24, 24);
        //prev.setBounds(50, 100, 25, 25);
        next.setBounds(179-20, 175, 24, 24);
        //next.setBounds(150, 100, 25, 25);

        JLabel imglabel1 = new JLabel(new ImageIcon(img3));
        JLabel imglabel2 = new JLabel(new ImageIcon(img3));

        ItemPanel.add(imglabel1);
        imglabel1.setBounds(50+154+7-15, 223+154/2-25/2, 25, 25);
        ItemPanel.add(imglabel2);
        imglabel2.setBounds(50+154+7+25+50+18+7, 223+154/2-25/2, 25, 25);

        mainitempanel = new JPanel();
        mainitempanel.setLayout(new GridLayout(1, 1));
        mainitempanel.setBackground(new Color(140,140,140));
        ItemPanel.add(mainitempanel);
        mainitempanel.setBounds(250-15, 223+154/2-50/2, 50, 50);

        nameLabel = new JLabel();
        nameLabel.setFont(customFont);
        ItemPanel.add(nameLabel);
        nameLabel.setBounds(mainitempanel.getX()-20, mainitempanel.getY()+mainitempanel.getHeight(), 100, 20);

        amountlabl = new JLabel();
        amountlabl.setFont(customFont);
        ItemPanel.add(amountlabl);
        amountlabl.setBounds(nameLabel.getX(), nameLabel.getY()+nameLabel.getHeight(), 25, 20);

        cil2 = new ClickableItemList(Main.GetItems(), this, null, new Dimension(200, 400));
        ItemPanel.add(cil2);
        cil2.setBounds(350, 25, 200, 500);
        frame.repaint();
        frame.revalidate();

    }


    void displayItemWindow(Item itemIn) {
        iw = new ItemWindow(itemIn);
        craftingscreen.removeAll();
        mainitempanel.removeAll();
        if(iw.GetCurrentRecepture() == null){
            for (int i = 0; i < 9; i++){
                JPanel jp = new JPanel();
                jp.setPreferredSize(new Dimension(50, 50));
                jp.setBackground(new Color(140,140,140));
                craftingscreen.add(jp);
            }
        }else if(iw.GetCurrentRecepture().GetMethod()=="minecraft:smelting"){
            for (int i = 0; i < 9; i++) {
                if(i==4){
                    craftingscreen.add(new ClickableItem(iw.GetCurrentRecepture().ingredients.get(0), this,img4));
                } else if (i == 7) {
                    JLabel imglabel5 = new JLabel(new ImageIcon(img5));
                    craftingscreen.add(imglabel5);
                }else{
                    JPanel jp = new JPanel();
                    jp.setPreferredSize(new Dimension(50, 50));
                    jp.setBackground(new Color(140,140,140));
                    craftingscreen.add(jp);
                }
            }

        }else{
            for (int i = 0; i < 9; i++) {
                if (i >= iw.GetCurrentRecepture().ingredients.size() || iw.GetCurrentRecepture().ingredients.get(i) == null) {
                    JPanel jp = new JPanel();
                    jp.setPreferredSize(new Dimension(50, 50));
                    jp.setBackground(new Color(140,140,140));
                    craftingscreen.add(jp);
                } else {
                    craftingscreen.add(new ClickableItem(iw.GetCurrentRecepture().ingredients.get(i), this,img4));
                }
            }
        }
        mainitempanel.add(new ClickableItem(iw.GetMainItem(), this,img4));
        nameLabel.setText(itemIn.GetName());
        if (iw.GetCurrentRecepture() != null) {
            Integer x = iw.GetCurrentRecepture().resultQuantity;
            amountlabl.setText("X "+x.toString());
        } else {
            amountlabl.setText("");
        }
        if (iw.GetReceptures().size()>1) {
            next.setVisible(true);
            prev.setVisible(true);
        } else {
            next.setVisible(false);
            prev.setVisible(false);
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

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                Main.Update(selectedFile.getAbsolutePath());
            }
            cil.Update(SearchEngine.FilterItems(Main.GetItems(), ""), null);
        } else if (e.getSource().equals(t)) {
            cil.Update(SearchEngine.FilterItems(Main.GetItems(), t.getText()), null);
        } else if (e.getSource().equals(next)) {
            iw.NextRecepture();
            craftingscreen.removeAll();
            for (int i = 0; i < 9; i++) {
                if (iw.GetCurrentRecepture() == null || i >= iw.GetCurrentRecepture().ingredients.size() || iw.GetCurrentRecepture().ingredients.get(i) == null) {
                    JPanel jp = new JPanel();
                    jp.setPreferredSize(new Dimension(50, 50));
                    jp.setBackground(new Color(140,140,140));
                    craftingscreen.add(jp);
                } else {
                    craftingscreen.add(new ClickableItem(iw.GetCurrentRecepture().ingredients.get(i), this,img4));
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
                    craftingscreen.add(new ClickableItem(iw.GetCurrentRecepture().ingredients.get(i), this,img4));
                }
            }
            frame.repaint();
            frame.revalidate();
        }

    }
}

