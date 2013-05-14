
package Interface;

import Exception.SearchDataException;
import java.util.ArrayList;
import model.Unit;

public interface UnitDataAccess {
    
    public ArrayList<Unit> getUnits(String name, Integer postalCode, String libLoc) throws SearchDataException;
    public ArrayList<Unit> getUnits() throws SearchDataException;
}
