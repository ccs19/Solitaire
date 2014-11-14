import javax.swing.*;
import java.awt.*;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by christopher on 10/27/14.
 */
public class CardGrid extends JLayeredPane implements Serializable {


    private static int WIDTH = 1300;
    private static int HEIGHT = 1000;


    private static final int ROWS = 2;
    private static final int COLUMNS = 7;
    private static final int SPACE = 10;
    private static final Dimension CARDGRIDSIZE = new Dimension(WIDTH,HEIGHT);
    private static GridLayout gridLayout = new GridLayout(ROWS, COLUMNS, SPACE, SPACE);
    private static JPanel backPanel = null;
    private static JPanel[][] cardGrid = new JPanel[ROWS][COLUMNS];

    private static int NUMSUITS = 4;


    /**
     * Creates the game board with all card stacks
     * @param s Reference to game window
     */
    public CardGrid(SolitaireWindow s) {
        backPanel = new JPanel(gridLayout);
        backPanel.setSize(CARDGRIDSIZE);
        backPanel.setLocation(2 * SPACE, 2 * SPACE);
        backPanel.setBackground(Color.GREEN);
        this.setBackground(Color.GREEN);
        this.setPreferredSize(CARDGRIDSIZE);

        //Create special card stacks
        cardGrid[0][1] = new DiscardStack();
        cardGrid[0][2] = new DiscardStack();
        cardGrid[0][2].setBackground(Color.green);
        cardGrid[0][0] = new DrawStack((DiscardStack)cardGrid[0][1]);

        //Create finish stacks (Where we drop cards to win)
        for(int i = 0; i < NUMSUITS; i++)
            cardGrid[0][i+3] = new FinishStack();

        //Create regular card stacks
        for(int c = 0; c < COLUMNS; c++)
            cardGrid[1][c] = new PlayStack();

        //Add all stacks to JPanel
        for(int r = 0; r < ROWS; r++)
            for(int c = 0; c < COLUMNS; c++) {
                    backPanel.add(cardGrid[r][c]);
            }

        //Fill stacks with cards
        addAllCards();
        backPanel.setBorder(BorderFactory.createEmptyBorder(SPACE, SPACE, SPACE, SPACE));
        setPreferredSize(CARDGRIDSIZE);
        add(backPanel, JLayeredPane.DEFAULT_LAYER);
        CardGridListener mouseAdapter = new CardGridListener(this);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    /**
     * Create a new deck and add all cards in the proper format
     *
     */
    private void addAllCards(){
        int p = 1;
        int i = 0;
        int done = 0;

        DrawStack d = (DrawStack)cardGrid[0][0];
        Deck deck = new Deck();
        while(i < 52)
        {
            PlayStack ps;
            if(done == 0)
            for(int j = 0; j < COLUMNS; j++){
                ps = (PlayStack)cardGrid[1][j];
                for(int k = 0; k != p; k++) {
                    ps.addCard( deck.drawCard() );
                    i++;
                }
                ps.showTopCard();
                p++;
            }
            done = 1;
            d.addCard( deck.drawCard() );
            i++;
        }
    }

    /**
     * Get the back panel which holds the card stacks
     * @return Game back panel
     */
    public JPanel getBackPanel(){
        return backPanel;
    }

    /**
     * Remove the back panel and dereference it.
     */
    public void resetGame(){
        this.remove(backPanel);
        backPanel = null;
    }


}

