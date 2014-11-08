import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Created by christopher on 10/26/14.
 */
public class SolitaireWindow implements Serializable{


    private static JFrame gameWindow;
    private static JMenuBar windowMenu;
    private static JMenu mainMenu, save;
    private static JMenuItem menuItem;
    private static JRadioButtonMenuItem buttonMenuItem;
    private static JCheckBoxMenuItem checkBoxMenuItem;
    private JMenuItem b;
    private static int[] monitorResolution = new int[2];
    private CardGrid c;

    SolitaireWindow(){
        gameWindow = new JFrame("Ultimate Solitaire for Winners");

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO: Add autosave on close
        createMenu();

        //Calculate window size
        Dimension d = CardConstants.getScreenResolution();
        gameWindow.setSize((int) ((double) d.getWidth() * (0.9)), (int) ((double) d.getHeight() * (0.9)));

        c = new CardGrid(this);
        gameWindow.getContentPane().add(c);
        gameWindow.setBackground(Color.GREEN);

        //redrawPage rp = new redrawPage();
       // gameWindow.addMouseListener(rp);
       // gameWindow.addMouseMotionListener(rp);
        gameWindow.setVisible(true);
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




    //Create exit button in menu and add listener
    private void initExitButton() {
        JMenuItem exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mainMenu.add(exitButton);
    }

    private void initNewGameButton(){
        JMenuItem newGameButton = new JMenuItem("New");
        //exitButton.addActionListener(new ActionListener(){
            //public void actionPerformed(ActionEvent e){
               // System.exit(0);
           // }
       // });
        mainMenu.add(newGameButton);
    }


    private void initSaveGameButton(){
        JMenuItem saveButton = new JMenuItem("Save");
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                saveGame();
            }
        });
        mainMenu.add(saveButton);
    }
    private void initLoadGameButton(){
        JMenuItem loadButton = new JMenuItem("Load");
        loadButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                loadGame();
            }
        });
        mainMenu.add(loadButton);
    }

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
                System.err.println("Error reading file" + e.getMessage());

            }
        }

    }


    private class redrawPage extends MouseAdapter {


        @Override
        public void mousePressed(MouseEvent m){
            SwingUtilities.updateComponentTreeUI(gameWindow);
            gameWindow.getContentPane().invalidate();
            gameWindow.getContentPane().validate();
            gameWindow.getContentPane().repaint();
        }

        @Override
        public void mouseDragged(MouseEvent m){
            mousePressed(m);

        }

    }

}
