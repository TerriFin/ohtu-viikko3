/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.List;

/**
 *
 * @author samisaukkonen
 */
public class CourseStats {
    public int returnsTotal;
    public int hourTotal;
    public int exerciseTotal;
    
    @Override
    public String toString() {
        return returnsTotal + ", " + hourTotal + ", " + exerciseTotal;
    }
}
