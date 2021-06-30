import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

//klasa odpowiedzialna za rysowanie głównego okna aplikacji
public class Window implements ActionListener {

    JFrame frame;
    private Container frameContainer;
    private JMenuBar menubar;
    private JMenu FilesMenuItem;
    private JMenuItem ImporFilesButton;
    private JTextField searchField;
    private ClickableItemList searchResultList;
    private ClickableItemList craftingResultList;
    private JPanel ItemPanel;
    private JPanel SearchPanel;
    private JPanel craftingscreen;
    private JPanel mainitempanel;
    private JLabel amountlabl;
    private JButton nextRecepieButton;
    private JButton prevRecepieButton;
    private ItemWindow itemWindow;
    BufferedImage Left_Arrow_Active;
    BufferedImage Right_Arrow_Active;
    BufferedImage Right_Arrow;
    BufferedImage Down_Arrow;
    BufferedImage Furnace_Fire;
    BufferedImage Crafting_Table_Icon;
    private JLabel nameLabel;
    Font customFont;
    BufferedImage No_Image_Icon;

    void displayWindow() {
        //wstępna inicjalizacja okna
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Crafting Explorer");
        //próba zmiany look and fell na systemowy
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //inicjalizacja podstawowych komponentów okna
        frameContainer = frame.getContentPane();
        frameContainer.setLayout(new BoxLayout(frameContainer, BoxLayout.X_AXIS));

        menubar = new JMenuBar();
        frameContainer.add(menubar);
        frame.setJMenuBar(menubar);

        FilesMenuItem = new JMenu("Files");
        menubar.add(FilesMenuItem);

        ImporFilesButton = new JMenuItem("Import Minecraft Files");
        ImporFilesButton.addActionListener(this);
        FilesMenuItem.add(ImporFilesButton);

        downloadFiles();

        frame.setIconImage(Crafting_Table_Icon);

        //kolejna inicjalizacja wszyskiech elemętów IU właściwej aplikacji
        ItemPanel = new JPanel();
        ItemPanel.setBackground(new Color(197, 197, 197));
        ItemPanel.setLayout(null);
        ItemPanel.setPreferredSize(new Dimension(600, 600));
        frameContainer.add(ItemPanel);

        SearchPanel = new JPanel();
        SearchPanel.setBackground(new Color(197, 197, 197));
        SearchPanel.setLayout(new BoxLayout(SearchPanel, BoxLayout.Y_AXIS));
        SearchPanel.setPreferredSize(new Dimension(200, 600));
        frameContainer.add(SearchPanel);

        searchField = new JTextField(40);
        searchField.setPreferredSize(new Dimension(SearchPanel.getPreferredSize().width, 25));
        searchField.addActionListener(this);
        searchField.setFont(customFont);
        SearchPanel.add(searchField);

        searchResultList = new ClickableItemList(Main.GetItems(), this, null, new Dimension(SearchPanel.getPreferredSize().width, SearchPanel.getPreferredSize().height - searchField.getPreferredSize().height));
        SearchPanel.add(searchResultList);

        craftingscreen = new JPanel();
        craftingscreen.setLayout(new GridLayout(3, 3, 2, 2));
        craftingscreen.setBackground(new Color(197, 197, 197));
        craftingscreen.setBounds(30, 223 - 20, 154, 154);
        ItemPanel.add(craftingscreen);

        for (int i = 0; i < 9; i++) {
            JPanel filler = new JPanel();
            filler.setPreferredSize(new Dimension(50, 50));
            filler.setBackground(new Color(140, 140, 140));
            craftingscreen.add(filler);
        }

        prevRecepieButton = new JButton(new ImageIcon(Left_Arrow_Active));
        prevRecepieButton.addActionListener(this);
        prevRecepieButton.setBorderPainted(false);
        prevRecepieButton.setVisible(false);
        prevRecepieButton.setBounds(30, 175 - 20, 24, 24);
        ItemPanel.add(prevRecepieButton);

        nextRecepieButton = new JButton(new ImageIcon(Right_Arrow_Active));
        nextRecepieButton.addActionListener(this);
        nextRecepieButton.setBorderPainted(false);
        nextRecepieButton.setVisible(false);
        nextRecepieButton.setBounds(179 - 20, 175 - 20, 24, 24);
        ItemPanel.add(nextRecepieButton);


        JLabel Right_ArrowLabel1 = new JLabel(new ImageIcon(Right_Arrow));
        JLabel Right_ArrowLabel2 = new JLabel(new ImageIcon(Right_Arrow));

        ItemPanel.add(Right_ArrowLabel1);
        Right_ArrowLabel1.setBounds(50 + 154 + 7 - 15, 223 + 154 / 2 - 25 / 2 - 20, 25, 25);
        ItemPanel.add(Right_ArrowLabel2);
        Right_ArrowLabel2.setBounds(50 + 154 + 7 + 25 + 50 + 18 + 7, 223 + 154 / 2 - 25 / 2 - 20, 25, 25);

        mainitempanel = new JPanel();
        mainitempanel.setLayout(new GridLayout(1, 1));
        mainitempanel.setBackground(new Color(140, 140, 140));
        mainitempanel.setBounds(250 - 15, 223 + 154 / 2 - 50 / 2 - 20, 50, 50);
        ItemPanel.add(mainitempanel);

        nameLabel = new JLabel();
        nameLabel.setFont(customFont);
        nameLabel.setBounds(mainitempanel.getX() - 20, mainitempanel.getY() + mainitempanel.getHeight(), 100, 20);
        ItemPanel.add(nameLabel);

        amountlabl = new JLabel();
        amountlabl.setFont(customFont);
        amountlabl.setBounds(nameLabel.getX(), nameLabel.getY() + nameLabel.getHeight(), 25, 20);
        ItemPanel.add(amountlabl);

        craftingResultList = new ClickableItemList(new ArrayList<Item>(), this, null, new Dimension(200, 400));
        craftingResultList.setBounds(350, 20, 200, 500);
        ItemPanel.add(craftingResultList);

        //odśiwrzenie głównego okna
        frame.repaint();
        frame.revalidate();
        frame.setVisible(true);
    }

    //próba załadowana customowych grafik
    void downloadFiles() {
        Left_Arrow_Active = null;
        Right_Arrow_Active = null;
        Right_Arrow = null;
        Down_Arrow = null;
        Furnace_Fire = null;
        Crafting_Table_Icon = null;
        No_Image_Icon = null;
        try {
            Left_Arrow_Active = ImageIO.read(Utils.GetFileFromResourceFile("custom_images/Left_Arrow_Active.png"));
            Right_Arrow_Active = ImageIO.read(Utils.GetFileFromResourceFile("custom_images/Right_Arrow_Active.png"));
            Right_Arrow = ImageIO.read(Utils.GetFileFromResourceFile("custom_images/Right_Arrow.png"));
            Down_Arrow = ImageIO.read(Utils.GetFileFromResourceFile("custom_images/Down_Arrow.png"));
            Furnace_Fire = ImageIO.read(Utils.GetFileFromResourceFile("custom_images/Furnace_Fire.png"));
            Crafting_Table_Icon = ImageIO.read(Utils.GetFileFromResourceFile("custom_images/Crafting_Table_Icon.jpg"));
            No_Image_Icon = ImageIO.read(Utils.GetFileFromResourceFile("custom_images/No_Image.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, Utils.GetFileFromResourceFile("custom_images/Minecraft.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    void displayItemWindow(Item itemIn, boolean newitem) {
        //funkcja odświeża przedmioty trzymane w głównym oknie
        if (newitem) {
            itemWindow = new ItemWindow(itemIn);
        }
        craftingscreen.removeAll();
        mainitempanel.removeAll();
        if (itemWindow.GetCurrentRecepture() == null) {
            for (int i = 0; i < 9; i++) {
                JPanel filler = new JPanel();
                filler.setPreferredSize(new Dimension(50, 50));
                filler.setBackground(new Color(140, 140, 140));
                craftingscreen.add(filler);
            }
        } else if (itemWindow.GetCurrentRecepture().GetMethod().equals(Utils.CraftingType.smelt)) {
            for (int i = 0; i < 9; i++) {
                if (i == 4) {
                    craftingscreen.add(new ClickableItem(itemWindow.GetCurrentRecepture().ingredients.get(0), this, Down_Arrow));
                } else if (i == 7) {
                    JLabel imglabel5 = new JLabel(new ImageIcon(Furnace_Fire));
                    craftingscreen.add(imglabel5);
                } else {
                    JPanel filler = new JPanel();
                    filler.setPreferredSize(new Dimension(50, 50));
                    filler.setBackground(new Color(197, 197, 197));
                    craftingscreen.add(filler);
                }
            }

        } else {
            for (int i = 0; i < 9; i++) {
                if (i >= itemWindow.GetCurrentRecepture().ingredients.size() || itemWindow.GetCurrentRecepture().ingredients.get(i) == null) {
                    JPanel filler = new JPanel();
                    filler.setPreferredSize(new Dimension(50, 50));
                    filler.setBackground(new Color(140, 140, 140));
                    craftingscreen.add(filler);
                } else {
                    craftingscreen.add(new ClickableItem(itemWindow.GetCurrentRecepture().ingredients.get(i), this, Down_Arrow));
                }
            }
        }
        mainitempanel.add(new ClickableItem(itemWindow.GetMainItem(), this, Down_Arrow));
        if(itemIn==null)
            nameLabel.setText("");
        else
            nameLabel.setText(itemIn.GetName());
        if (itemWindow.GetCurrentRecepture() != null && itemWindow.GetCurrentRecepture().resultQuantity > 1) {
            Integer x = itemWindow.GetCurrentRecepture().resultQuantity;
            amountlabl.setText("X " + x.toString());
        } else {
            amountlabl.setText("");
        }
        if (itemWindow.GetReceptures().size() > 1) {
            nextRecepieButton.setVisible(true);
            prevRecepieButton.setVisible(true);
        } else {
            nextRecepieButton.setVisible(false);
            prevRecepieButton.setVisible(false);
        }

        craftingResultList.Update(itemWindow.GetNextItems(), null);
        frame.repaint();
        frame.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ImporFilesButton)) {
            //wywołanie pobrania nowych plików minecrafa
            JFileChooser jfc = new JFileChooser();
            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                Main.Update(selectedFile.getAbsolutePath());
            }
            searchResultList.Update(SearchEngine.FilterItems(Main.GetItems(), ""), null);
        } else if (e.getSource().equals(searchField)) {
            //wywowałanie wyszuania na podstawie wpisanych wartości
            searchResultList.Update(SearchEngine.FilterItems(Main.GetItems(), searchField.getText()), null);
        } else if (e.getSource().equals(nextRecepieButton)) {
            //wywołanie wyświetlenie kolejnej receptury przedmiotu
            itemWindow.NextRecepture();
            displayItemWindow(itemWindow.GetMainItem(), false);
        } else if (e.getSource().equals(prevRecepieButton)) {
            //wywołanie wyświetlenie poprzedniej receptury przedmiotu
            itemWindow.PrevRecepture();
            displayItemWindow(itemWindow.GetMainItem(), false);
        }
    }

    public void FixAfterLoadingFiles() {
        frame.repaint();
        frame.revalidate();
        displayItemWindow(null, true);
    }
}

