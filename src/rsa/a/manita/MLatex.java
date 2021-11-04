/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rsa.a.manita;

import org.scilab.forge.jlatexmath.ParseException;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 *
 * @author sofo9
 */
public class MLatex {
    //Representa una fórmula matemática lógica que se mostrará en un TexIcon
    TeXFormula formula;
    //Una implementación de Icon que pintará la formula que la creó.
    TeXIcon icon;
    String math;
    
    public MLatex(){
        // ...
    }

    public MLatex(String math){    
        this.math = math;
    }
    
    public void MLatex(String math){    
        this.math = math;
    }
    
    public TeXIcon getIconLaTex(float size){        
        try {
            formula = new TeXFormula(this.math);
            this.icon = this.formula.createTeXIcon(TeXConstants.ALIGN_CENTER, size);
            return this.icon;
        } catch (ParseException e) {
            System.err.println("Error: "+ e.getMessage());
            return this.icon = null;
        }                         
    }
}
