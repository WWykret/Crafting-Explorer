import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

//klasa implemętuje przycik który przyjmuje wygląd podanego podanego przedmiotu
public class ClickableItem extends JButton implements ActionListener {
    private Item heldItem;
    private Window parentWindow;
    private JButton subtypesButton;

    //pomocznica funkcja przeskalowująca bezstratnie ikonke przedmiotu
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    ClickableItem(Item itemIn, Window windowIn, BufferedImage arrow) {
        setBorderPainted(false);
        setFocusPainted(false);

        if (itemIn == null) {
            setBackground(new Color(197, 197, 197));
            setEnabled(false);
            return;
        }

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

        bi = resizeImage(bi, 50, 50);
        ImageIcon ii = new ImageIcon(bi);
        setIcon(ii);
        setPreferredSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));
        addActionListener(this);

        //jezli przedmiot posiada poddtypy dodajemy dotakowyprzyciks który wyświetla okienko zawierające je
        if (heldItem.GetTypes() != null) {
            subtypesButton = new JButton(new ImageIcon(arrow));
            add(subtypesButton);
            subtypesButton.setBorderPainted(false);
            subtypesButton.setPreferredSize(new Dimension(10, 10));
            subtypesButton.addActionListener(this);
            subtypesButton.setBounds(0, getPreferredSize().width - 10, 10, 10);
        }
    }

    //funkcja odpowiedzialna ze tworzenie okienka zawierającego listę podtypów
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
            //akcja wywoływana pry kliknięcu na przycik, ustawia przechowywany item w głównym polu aplikacji
            displayItemWindow();
        } else if (e.getSource().equals(subtypesButton)) {
            //akcja wywoływana pry kliknięcu na na przycik wyświetlający podtypy przedmiotu, otwiera onienko podtypów
            displaySubtype();
        }
    }
}

