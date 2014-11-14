import java.awt.*;
import java.io.Serializable;

/**
 * Created by Christopher Schneider on 11/5/14.
 */
public class FinishStack extends DiscardStack implements Serializable {

    private String suit = null;
    private int ACE = 13;

    FinishStack(){
        super();
    }


    public boolean addCardStackFinish(MoveStack m){
        if(m.getNumCards() > 1)
            return false;
        CardTexture card = (CardTexture)m.getComponent(0);
        if(suit == null && card.getVal() == ACE){
            suit = card.getSuit();
            this.addCardStack(m);
            return true;
        }
        else if(suit.equals(card.getSuit()) && this.getNumCards() == card.getVal() ){
            this.addCard(m.removeCard(), 0);
            return true;
        }
        return false;
    }




    @Override
    public void onContainerChange(){
        if(this.getNumCards() == 0)
            suit = null;
    }
    @Override
    public String toString(){
        return "FinishStack";
    }


    /*
     * If user is allowed to drop cards here, return true, else false
     * This does not indicate whether the card addition is valid.
     */
    @Override
    public boolean allowUserDrop(){
        return true;
    }

    @Override
    public MoveStack getMoveStack() {
        MoveStack moveStack = new MoveStack();
        moveStack.addCard(this.removeCard(0));
        return moveStack;
    }


}
