import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Christopher Schneider on 11/13/14.
 */
public class Deck {


    ArrayList<CardTexture> cards = new ArrayList<CardTexture>();

    /**
     * Creates a deck of cards and shuffles the deck
     */
    public Deck(){
        initDeck();
        Collections.shuffle(cards);
    }

    /**
     * Create all cards and add them to the deck
     */
    private void initDeck(){
        int x = CardConstants.getNumCards();
        for(int i = 0; i < x; i++)
            cards.add(new CardTexture(i));
    }

    /**
     * Draw one card from deck
     * @return Card
     */
    public CardTexture drawCard(){
        CardTexture c = cards.get(0);
        cards.remove(0);
        return c;
    }

}
