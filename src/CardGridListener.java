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
    private CardStack clickedCardPanel = null;

    private CardGrid cardGrid = null;
    private JPanel backPanel = null;
    private MoveStack dragStack = null;

    private int dx = 0;
    private int dy = 0;

    /**
     * Initializes card listener with game rules
     * @param cg Main game grid
     */
    public CardGridListener(CardGrid cg){
        cardGrid = cg;
        backPanel = cardGrid.getBackPanel();
    }

    /**
     * Listener for mouse press
     * @param m Mouse event
     */
    @Override
    public void mousePressed(MouseEvent m) {
        setStackClicked(m);

        MoveStack(dragStack, m.getLocationOnScreen());
        cardGrid.repaint();
        //System.out.println("X: " + dragStack.getY() + " Y: " + dragStack.getX());
    }

    /**
     * Listener for mouse drag
     * @param m Mouse event
     */
    @Override
    public void mouseDragged(MouseEvent m){
        try {
            MoveStack(dragStack, m.getLocationOnScreen());
        }
        catch(Exception e){
            return;
        }
    }

    /**
     * Listener for mouse release.
     * Verifies the card is placed in the right spot
     * @param m Mouse event
     */
    @Override
    public void mouseReleased(MouseEvent m){
        CardStack dropLocation;
        boolean valid = true;
        //If no valid click, do nothing, return
        if(dragStack == null) {
            return;
        }
        //Remove CardTexture from JPanel layer
        cardGrid.remove(dragStack);
        //If invalid card panel, put back in original panel
        try {
            dropLocation = (CardStack) backPanel.getComponentAt(m.getPoint());
        }
        catch(Exception e){
            putStackBack();
            return;
        }

        if (dropLocation == null || dropLocation.allowUserDrop() == false || dropLocation == clickedCardPanel )
            putStackBack();
        else if(dropLocation.toString().equals("FinishStack")){
            FinishStack f = (FinishStack)dropLocation;
            valid = f.addCardStackFinish(dragStack);
        }
        else if(dropLocation.toString().equals("PlayStack")){
            PlayStack p = (PlayStack)dropLocation;
            valid = p.addCardStackFinish(dragStack);
            p.repaint();
        }

        if(!valid)
            putStackBack();
        clickedCardPanel.showTopCard();
        clickedCardPanel.repaint();
        cardGrid.revalidate();
        cardGrid.repaint();
        dragStack = null;
    }

    /**
     * Get the stack we've clicked
     * @param m Mouse event
     */
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
            dragStack.setLocation(m.getLocationOnScreen().x - dx, m.getLocationOnScreen().y - dy);
            cardGrid.add(dragStack);
            cardGrid.setComponentZOrder(dragStack,0);
            dragStack.repaint();
            //mouseDragged(m);
        }
    }

    /**
     * Allows fluid movement of one card
     * @param c Card moved
     * @param x Card x location
     * @param y Card y location
     */
    private void MoveCard(CardTexture c, int x, int y){
        c.setLocation(x, y);
        c.repaint();
        dragCard.repaint();
    }

    /**
     * Supposed to allow fluid movement of stack of cards
     * @param ms Stack moved
     * @param point Mouse location
     */
    private void MoveStack(MoveStack ms, Point point){
        ms.setLocation(point);
        int count = ms.getNumCards();
        for(int i = 0; i < count; i++) {
            ms.getComponent(i).setLocation(point.x + (i * 25), point.y);
            ms.getComponent(i).repaint();
        }
        ms.repaint();
    }

    /**
     * Puts a single card back to its original stack.
     */
    private void putCardBack(){
        clickedCardPanel.addCard(dragCard, clickedCardPanel.getCardClickedIndex());
    }

    /**
     * Puts a stack back in its original location
     */
    private void putStackBack(){
        if(clickedCardPanel.toString().equals("FinishStack")){
            FinishStack f = (FinishStack)clickedCardPanel;
            f.addCardStackFinish(dragStack);
        }
        else
            clickedCardPanel.addCardStack(dragStack);
    }


}