/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Exception.AddDataException;
import Exception.ModDataException;
import Exception.SearchDataException;
import java.util.ArrayList;
import model.Registration;

public interface RegistrationDataAccess {
    public void addRegistration(Registration reg) throws AddDataException;
    public void modRegistration(Registration reg) throws ModDataException;
    public ArrayList<Registration> getReg(Registration r) throws SearchDataException;
}
