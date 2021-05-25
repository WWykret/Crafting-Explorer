import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickableItem extends JButton implements ActionListener{
    Item heldItem;
    Window parentWindow;
    ClickableItem(Item itemIn, Window windowIn){
        heldItem=itemIn;
        parentWindow=windowIn;
        setIcon(new ImageIcon(heldItem.GetGraphics()));
        addActionListener(this);
    }
    void displaySubtype(){

    }

    void displayItemWindow(){
        parentWindow.displayItemWindow(heldItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayItemWindow();
    }
}

