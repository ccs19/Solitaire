import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by christopher on 11/7/14.
 */
public class CardStackListener extends MouseAdapter implements Serializable {

    private CardTexture dragCard = null;
    private int wDiv;
    private int hDiv;
    private CardStack clickedCardPanel = null;
    private CardGrid cardGrid = null;
    private JPanel backPanel = null;

    CardStackListener(CardGrid c) {
        super();
        cardGrid = c;
        backPanel = cardGrid.getBackPanel();
    }

    @Override
    public void mousePressed(MouseEvent m) {
        setCardClicked(m);
        mouseDragged(m);
    }

    @Override
    public void mouseDragged(MouseEvent m){
        //Click offset so mouse pointer is in middle of card
        wDiv = dragCard.getWidth() / 2;
        hDiv = dragCard.getHeight() / 2;
        MoveCard(dragCard, m.getPoint().x - wDiv, m.getPoint().y - hDiv);
        //Repaint on drag to avoid card clipping
        cardGrid.repaint();
    }





    /*
     * Find card we're clicking in the JPanel array
     */
    private void setCardClicked(MouseEvent m){
        clickedCardPanel = (CardStack) backPanel.getComponentAt(m.getPoint());
        Component[] c = clickedCardPanel.getComponents();
        if (c.length == 0)
            return;
        if (c[0] instanceof CardTexture) {
            clickedCardPanel.setValues(SwingUtilities.convertMouseEvent(cardGrid, m, clickedCardPanel));
            dragCard = clickedCardPanel.removeCard();
            cardGrid.add(dragCard, JLayeredPane.DRAG_LAYER);
            dragCard.repaint();
        }
    }


    //Allows fluid movement of card
    private void MoveCard(CardTexture c, int x, int y){
        c.setLocation(x, y);
        c.repaint();
        dragCard.repaint();
    }


    //Puts card(s) back in original panel
    private void putCardBack(){
        clickedCardPanel.addCard(dragCard);
    }

}