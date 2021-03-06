
package Interface;

import Exception.*;
import java.util.ArrayList;
import model.Registration;

public interface RegistrationDataAccess {
    public void addRegistration(Registration reg) throws AddDataException;
    public void modRegistration(Registration reg) throws ModDataException;
    public ArrayList<Registration> getReg(Registration r) throws SearchDataException;
    public void DelReg(Registration reg)throws DeleteException;
}
