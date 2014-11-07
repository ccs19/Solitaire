import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by christopher on 10/29/14.
 */



public class FileChooser {

    private JFileChooser chooser;

    public void FileChooserSave(CardGrid gridToBeSaved){
        JFileChooser chooser;

    }

    public CardGrid FileChooserLoad(){
        chooser = new JFileChooser();
        File openedFile = chooser.getSelectedFile();
        FileInputStream fileInputStream = null;
        ObjectInputStream cardGridInput = null;
        CardGrid cardGrid = null;
        Object o = null;
      //  int valid = chooser.showOpenDialog(this);

        try {
            fileInputStream = new FileInputStream(openedFile);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        if(fileInputStream != null) {


            try {
                cardGridInput = new ObjectInputStream(fileInputStream);
                //} catch (ClassNotFoundException e) {
                //    return null;
                //}
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else return null;

        try {
            o = cardGridInput.readObject();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e ){
            e.printStackTrace();
        }


        if(o instanceof CardGrid)
            return (CardGrid)o;
        else
            return null;
    }

}
