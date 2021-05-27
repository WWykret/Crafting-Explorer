import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class ClickableItem extends JButton implements ActionListener {
    private Item heldItem;
    private Window parentWindow;

    private JButton b1;

    private ClickableItemList cl;
    private boolean subtypedisplayed;
    private Popup po;
    private PopupFactory factory;
    private JDialog poop;

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
/*
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    */

    ClickableItem(Item itemIn, Window windowIn,BufferedImage arrow) {
        setBackground(new Color(140,140,140));
        /*
        Border b = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(b);
        */
        subtypedisplayed = false;
        setLayout(null);
        heldItem = itemIn;
        parentWindow = windowIn;

        BufferedImage bi = heldItem.GetGraphics();
        setBorderPainted(false);

        bi = resizeImage(bi, 50, 50);
        ImageIcon ii = new ImageIcon(bi);
        setIcon(ii);
        setPreferredSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        addActionListener(this);
        if (heldItem.GetTypes()!=null) {
            b1 = new JButton(new ImageIcon(arrow));
            //b1.setIcon( new ImageIcon(arrow));
            add(b1);
            b1.setBorderPainted(false);
            b1.setPreferredSize(new Dimension(10, 10));
            b1.addActionListener(this);
            b1.setBounds(0, getPreferredSize().width - 10, 10, 10);
        }


    }

    void displaySubtype() {
        poop = new JDialog(parentWindow.frame);
        poop.setTitle(heldItem.GetName());
        poop.setSize(200, 200);
        poop.setResizable(false);
        //poop.setLocationRelativeTo(parentWindow.frame);
        poop.setLocation(getLocationOnScreen().x, getLocationOnScreen().y+getHeight());
        cl = new ClickableItemList(heldItem.GetTypes(), parentWindow, heldItem, new Dimension(200, 200));
        poop.add(cl);
        poop.setVisible(true);
    }

    void displayItemWindow() {
        parentWindow.displayItemWindow(heldItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            parentWindow.displayItemWindow(heldItem);
        } else {
            displaySubtype();
        }
    }
}

