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
    private JButton subtypesButton;

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    ClickableItem(Item itemIn, Window windowIn, BufferedImage arrow) {
        setBackground(new Color(140, 140, 140));
        setLayout(null);
        heldItem = itemIn;
        parentWindow = windowIn;
        BufferedImage bi;
        if (heldItem.GetGraphics() != null) {
            bi = heldItem.GetGraphics();
        } else {
            bi = windowIn.No_Image_Icon;
        }

        setBorderPainted(false);
        setFocusPainted(false);

        bi = resizeImage(bi, 50, 50);
        ImageIcon ii = new ImageIcon(bi);
        setIcon(ii);
        setPreferredSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        addActionListener(this);
        if (heldItem.GetTypes() != null) {
            subtypesButton = new JButton(new ImageIcon(arrow));
            add(subtypesButton);
            subtypesButton.setBorderPainted(false);
            subtypesButton.setPreferredSize(new Dimension(10, 10));
            subtypesButton.addActionListener(this);
            subtypesButton.setBounds(0, getPreferredSize().width - 10, 10, 10);
        }
    }

    void displaySubtype() {
        JDialog subtypeDialog = new JDialog(parentWindow.frame);
        subtypeDialog.setTitle(heldItem.GetName());
        subtypeDialog.setIconImage(heldItem.GetGraphics());
        subtypeDialog.setSize(200, 200);
        subtypeDialog.setResizable(false);
        subtypeDialog.setLocation(getLocationOnScreen().x, getLocationOnScreen().y + getHeight());

        ClickableItemList cl = new ClickableItemList(heldItem.GetTypes(), parentWindow, heldItem, new Dimension(200, 200));
        subtypeDialog.add(cl);

        subtypeDialog.setVisible(true);
    }

    void displayItemWindow() {
        parentWindow.displayItemWindow(heldItem, true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            displayItemWindow();
        } else if (e.getSource().equals(subtypesButton)) {
            displaySubtype();
        }
    }
}

