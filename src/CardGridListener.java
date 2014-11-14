import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by christopher on 11/13/14.
 */
public class CardGridListener extends MouseAdapter implements Serializable {

    private CardTexture dragCard = null;
    private int wDiv = 0;
    private int hDiv = 0;
    private CardStack clickedCardPanel = null;

    private CardGrid cardGrid = null;
    private JPanel backPanel = null;
    private MoveStack dragStack = null;

    private int dx = 0;
    private int dy = 0;


    public CardGridListener(CardGrid cg){
        cardGrid = cg;
        backPanel = cardGrid.getBackPanel();
    }

    @Override
    public void mousePressed(MouseEvent m) {


        setStackClicked(m);
        MoveStack(dragStack, m.getLocationOnScreen());
        cardGrid.repaint();
        //System.out.println("X: " + dragStack.getY() + " Y: " + dragStack.getX());


//        try {
//            clickedCardPanel = (CardStack) backPanel.getComponentAt(m.getPoint());
//        }
//        catch(Exception E){
//            return;
//        }
//
//        Component[] c = clickedCardPanel.getComponents();
//        if(c.length == 0 )
//            return;
//        else if(clickedCardPanel instanceof CardStack) {
//            clickedCardPanel.setValues(SwingUtilities.convertMouseEvent(cardGrid, m, clickedCardPanel));
//            dragCard = clickedCardPanel.removeCard();
//            cardGrid.add(dragCard, JLayeredPane.DRAG_LAYER);
//            dragCard.repaint();
//            mouseDragged(m);
//        }

    }



    //Animation for moving card.
    @Override
    public void mouseDragged(MouseEvent m){
        try {
            //Click offset so mouse pointer is in middle of card
            //wDiv = dragCard.getWidth() / 2;
            //hDiv = dragCard.getHeight() / 2;

            MoveStack(dragStack, m.getPoint());
            System.out.println("X: " + dragStack.getY() + " Y: " + dragStack.getX());
            //Repaint on drag to avoid card clipping

            int x = dragStack.getNumCards();
            //debug



            dragStack.printCards();
        }
        catch(Exception e){
            return;
        }
    }


    @Override
    public void mouseReleased(MouseEvent m){
        CardStack dropLocation;
        //If no valid click, do nothing, return
        if(dragStack == null) {
            return;
        }
        //Remove CardTexture from JPanel layer
        cardGrid.remove(dragStack);
        //If invalid card panel, put back in original panel
        try {
            dropLocation = (CardStack) backPanel.getComponentAt(m.getPoint());
            if (dropLocation.allowUserDrop() == false)
                putStackBack();
                //else add to new destination
            else if (dropLocation == clickedCardPanel)
                putStackBack();
            else {
                dropLocation.addCardStack(dragStack);
                clickedCardPanel.repaint();
            }
        }
        catch(Exception e){
            putStackBack();
            return;
        }
        cardGrid.revalidate();
        cardGrid.repaint();
        dragStack = null;
    }


    private void setStackClicked(MouseEvent m){

        try {
            clickedCardPanel = (CardStack) backPanel.getComponentAt(m.getPoint());
        }
        catch(Exception E){
            return;
        }



        Component[] c = clickedCardPanel.getComponents();
        if(c.length == 0 )
            return;
        else if(clickedCardPanel instanceof CardStack) {
            clickedCardPanel.setValues(SwingUtilities.convertMouseEvent(cardGrid, m, clickedCardPanel));
            dragStack = clickedCardPanel.getMoveStack();
            dx = m.getLocationOnScreen().x - dragStack.getX();
            dy = m.getLocationOnScreen().y - dragStack.getY();
            dragStack.setLocation(m.getLocationOnScreen().x - dx, m.getLocationOnScreen().y - dy);
            cardGrid.add(dragStack);
            cardGrid.setComponentZOrder(dragStack,0);
            dragStack.repaint();
            //mouseDragged(m);
        }
    }


    //Allows fluid movement of one card
    private void MoveCard(CardTexture c, int x, int y){



        c.setLocation(x, y);
        c.repaint();
        dragCard.repaint();
    }



    private void MoveStack(MoveStack ms, Point point){
        //cardGrid.moveToFront(m);

        //debug
        //System.out.println("")


        ms.setLocation(point);
        ms.repaint();
    }


    private void putCardBack(){
        clickedCardPanel.addCard(dragCard, clickedCardPanel.getCardClickedIndex());
    }

    private void putStackBack(){
        clickedCardPanel.addCardStack(dragStack);
    }


}