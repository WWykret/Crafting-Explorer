import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class ClickableItem extends JButton implements ActionListener{
    private  Item heldItem;
    private  Window parentWindow;

    private  JButton b1;

    private  ClickableItemList cl;
    private  boolean subtypedisplayed;
    private  Popup po;
    private  PopupFactory factory;

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

    ClickableItem(Item itemIn, Window windowIn){
        subtypedisplayed=false;
        setLayout(null);
        heldItem=itemIn;
        parentWindow=windowIn;

        BufferedImage bi=heldItem.GetGraphics();

        //bi=(BufferedImage) getScaledImage(heldItem.GetGraphics(),100,100);
        //bi = new MultiStepRescaleOp(100, 100, RenderingHints.VALUE_INTERPOLATION_BILINEAR).filter(bi, null);
/*
        bi = new BufferedImage(100, 100,);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
        graphics2D.dispose();
*/



        bi=resizeImage(bi,50,50);
        ImageIcon ii=new ImageIcon(bi);
        setIcon(ii);
        setPreferredSize(new Dimension(50,50));
        addActionListener(this);



        b1 = new JButton("dupa");
        add(b1);
        b1.setSize(10,10);
        b1.addActionListener(this);
        b1.setBounds(0,getPreferredSize().height-10,10,10);



    }
    void displaySubtype() {
        if (subtypedisplayed) {
            remove(cl);
            po.hide();
            subtypedisplayed = false;
        } else {
            cl = new ClickableItemList(heldItem.GetTypes(), parentWindow);
            add(cl);
            factory = PopupFactory.getSharedInstance();
            po = factory.getPopup(this, cl, getLocationOnScreen().x, getLocationOnScreen().y+getHeight());

            po.show();
            subtypedisplayed = true;
        }
        //cl.setBounds(0,getPreferredSize().height,cl.getWidth(),cl.getHeight());
    }

    void displayItemWindow(){
        parentWindow.displayItemWindow(heldItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("lol1");
        if(e.getSource().equals(this)){
            parentWindow.displayItemWindow(heldItem);
        }else {
            System.out.println("lol2");
            displaySubtype();

        }

    }
}

