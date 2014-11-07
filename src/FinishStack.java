import java.awt.*;
import java.io.Serializable;

/**
 * Created by christopher on 11/5/14.
 */
public class FinishStack extends DiscardStack implements Serializable {

    FinishStack(){
        super();

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

}
