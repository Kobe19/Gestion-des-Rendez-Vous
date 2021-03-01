/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionRdvPsyPatient.calendar;

import com.toedter.calendar.IDateEvaluator;
import java.awt.Color;
import java.util.Date;

/**
 *
 * @author Daniel FOGUE et Yvan Doal 

*/
//combodate.getJCalendar().getDayChooser().addDateEvaluator(new DateEvaluator());
 
public class DateEvaluator implements IDateEvaluator {

    @Override
    public boolean isSpecial(Date date) {
        return false;
    }

    @Override
    public Color getSpecialForegroundColor() {
        return null;
    }

    @Override
    public Color getSpecialBackroundColor() {
        return null;   
    }

    @Override
    public String getSpecialTooltip() {
        return null;   
    }

    @Override
    public boolean isInvalid(Date date) {
        return date.getDay() == 0;
    }

    @Override
    public Color getInvalidForegroundColor() {
        return null;
    }

    @Override
    public Color getInvalidBackroundColor() {
        return null;
    }

    @Override
    public String getInvalidTooltip() {
        return "desole nous sommes fermé le dimanche";
    }
    
}
