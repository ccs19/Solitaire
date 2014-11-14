import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Created by Christopher Schneider on 10/26/14.
 */
public class SolitaireWindow implements Serializable{


    private static JFrame gameWindow;
    private static JMenuBar windowMenu;
    private static JMenu mainMenu, save;
    private CardGrid c;

    /**
     * Creates solitaire game window
     */
    SolitaireWindow(){
        gameWindow = new JFrame("Solitaire");

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO: Add autosave on close
        createMenu();

        //Calculate window size
        Dimension d = CardConstants.getScreenResolution();
        gameWindow.setSize((int) ((double) d.getWidth() * (0.9)), (int) ((double) d.getHeight() * (0.9)));
        initGameWindow();

    }

    /**
     * Creates game menubar
     */
    private void createMenu(){
        //Menubar on top of screen
        windowMenu = new JMenuBar();
        //Game JMenu
        mainMenu = new JMenu("Game");
        //Add items to menu
        windowMenu.add(mainMenu);
        //Add menu to JFrame
        gameWindow.setJMenuBar(windowMenu);

        //Create and add menu buttons
        initNewGameButton();
        initSaveGameButton();
        initLoadGameButton();
        initExitButton();
    }

    /**
     * Initialize game window
     */
    private void initGameWindow(){
        c = new CardGrid(this);
        gameWindow.getContentPane().add(c);
        gameWindow.setBackground(Color.GREEN);
        gameWindow.setVisible(true);
    }


    /**
     * Create exit button
     */
    private void initExitButton() {
        JMenuItem exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mainMenu.add(exitButton);
    }

    /**
     * Create new game button
     */
    private void initNewGameButton(){
        JMenuItem newGameButton = new JMenuItem("New");
        newGameButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                gameWindow.getContentPane().remove(c);
                c.resetGame();
                c = null;
                initGameWindow();
            }
        });
        mainMenu.add(newGameButton);
    }

    /**
     * Create save game button
     */
    private void initSaveGameButton(){
        JMenuItem saveButton = new JMenuItem("Save");
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                saveGame();
            }
        });
        mainMenu.add(saveButton);
    }

    /**
     * Create load game button
     */
    private void initLoadGameButton(){
        JMenuItem loadButton = new JMenuItem("Load");
        loadButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                loadGame();
            }
        });
        mainMenu.add(loadButton);
    }

    /**
     * Open save dialog
     */
    private void saveGame(){
        JFileChooser jFileChooser = new JFileChooser();
        FileOutputStream f = null;

        int r = jFileChooser.showSaveDialog(gameWindow);
        if(r == JFileChooser.APPROVE_OPTION){
            try{
                f = new FileOutputStream(jFileChooser.getSelectedFile().getAbsolutePath());
                ObjectOutputStream os = new ObjectOutputStream(f);
                os.writeObject(c);
                f.close();
            }
            catch (Exception e){
                System.err.println("Error: " + e);
            }

        }
    }

    /**
     * Opens JFileChooser for loading game.
     */
    private void loadGame(){
        JFileChooser jFileChooser = new JFileChooser();
        FileInputStream f = null;
        int r = jFileChooser.showOpenDialog(gameWindow);
        if(r == JFileChooser.APPROVE_OPTION){
            try{
                f = new FileInputStream(jFileChooser.getSelectedFile().getAbsolutePath());
                ObjectInputStream is = new ObjectInputStream(f);
                gameWindow.remove(c);
                c = (CardGrid)is.readObject();
                f.close();
                gameWindow.add(c);
                SwingUtilities.updateComponentTreeUI(gameWindow);
                gameWindow.getContentPane().revalidate();
                gameWindow.getContentPane().repaint();
            }
            catch(Exception e){
                System.err.println("Error reading file " + e.getMessage());

            }
        }

    }




}
