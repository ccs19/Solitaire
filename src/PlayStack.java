import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by christopher on 11/5/14.
 */
public class PlayStack extends CardStack implements Serializable{
    private float count = 0f;
    private int layer = 5;

    private JLayeredPane cardLayers = new JLayeredPane();

    public PlayStack(){
        super();
        count = 0f;
        setLayout(null);
    }

    public String toString(){
        return "PlayStack";
    }

    public void onContainerChange(){
    }

    public CardTexture removeCard(){
        CardTexture c = removeCard(getCardClickedIndex());
        return c;
    }



    /*
     * If user is allowed to drop cards here, return true, else false
     * This does not indicate whether the card addition is valid.
     */

    public boolean allowUserDrop(){
        return true;
    }



    public void addCard(CardTexture c){
        c.setBounds(0, 25 * getNumCards(), c.getWidth(), c.getHeight());
        this.add(c);
        this.setComponentZOrder(c, 0);
    }

    /*
     * Shows playable cards
     */
    public void showTopCard(){
        if(this.getComponentCount() == 0)
            return;
        Component[] c = this.getComponents();
        CardTexture card = (CardTexture)c[0];
        card.showCard();
    }


}
