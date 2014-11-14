import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

/**
 * @author Christopher Schneider
 *	Description: Project 1, OO Programming.  Card class.
 */


public class Card implements Serializable{


    private static final String[] defaultCardTypes = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final String[] defaultSuits = {"Clubs", "Diamonds", "Spades", "Hearts"};
    //Note: Currently only works and tested with even number of suits and colors.
    private static final String[] defaultColors = {"Black", "Red"};

    private int cardVal;
    private String color;
    private String suit;
    private String cardName;
    //Order of suits in list determines value of suit (Low to high)
    private static final List<String> suitTypes = Arrays.asList(defaultSuits);
    //Colors!
    private static final List<String> colorTypes = Arrays.asList(defaultColors);
    //Order of types in list determines value of type (Low to high)
    private static final List<String> cardTypes = Arrays.asList(defaultCardTypes);

    //Constants.
    //Card attributes are derived from the number of items in the lists above.
    private static final int NUM_SUITS = suitTypes.size(); //Number of suits 
    private static final int NUM_CARDS_PER_SUIT = cardTypes.size(); //Number of cards
    private static final int NUM_COLORS = colorTypes.size(); //Number of possible colors.
    private static final int NUM_CARDS = NUM_SUITS*NUM_CARDS_PER_SUIT; //Total number of cards.





    /** Accepts a value and returns a card.  e.g. value 0 returns 2 of clubs, value 51 returns Ace of Hearts.
     * @param val Card's value
     */
    public Card(int val){
        if(val > ( NUM_CARDS - 1) || val < 0 ){
            System.out.println("Invalid card requested. Card value -1");
            cardVal = -1;
            color = "Clown";
            cardName = "Joe Pesci";
            suit = "Good Fellas";
        }
        else{
            cardVal = val;
            setColor();
            setSuit();
            setCardName();
        }
    }


    /** Returns the card's value.
     * @return The numeric "strength" of the card.
     */
    public int getNumber(){
        return cardVal/4 + 1;
    }

    /** Returns true if red, false if black.
     * @return Returns true if red, false if black.
     */
    public String getColor(){
        return color;
    }

    /** Returns the suit of the card.
     * @return Suit of card
     */
    public String getSuit(){
        return suit;
    }

    /** Returns the face value of the card. King, queen, jack, etc.
     * @return Card's value
     */
    public String getValue(){
        return cardName;
    }

    /** Returns int: negative if weaker card, equal if the same, and greater if stronger.
     * @param c Card to be compared against.
     * @return Returns negative if weaker card, equal if the same, and greater if stronger.
     */
    public int compareTo(Card c){
        return( this.cardVal - c.cardVal );
    }

    /** Returns the value of the card in a readable string.
     * @return Returns card - Value of Suit , color
     */
    public String toString(){
        return getValue() + " of " + getSuit() + ", " + color;
    }

    /** Set the suit of the card.
     *
     */
    private void setSuit(){
        suit = suitTypes.get( cardVal % NUM_SUITS );
    }

    /** Set the color of the card.
     *
     */
    private void setColor(){
        color = colorTypes.get( (cardVal) % NUM_COLORS);
    }

    /** Set the face value of the card (King, queen, jack, etc.)
     *
     */
    private void setCardName(){
        cardName = cardTypes.get( cardVal / NUM_SUITS );
    }




}