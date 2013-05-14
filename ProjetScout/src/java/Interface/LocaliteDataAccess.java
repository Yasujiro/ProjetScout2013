
package Interface;

import Exception.SearchDataException;
import java.util.ArrayList;
import model.Localite;

public interface LocaliteDataAccess {
    
    public ArrayList<Localite> getLocalite(Integer pCode) throws SearchDataException;
    
}
