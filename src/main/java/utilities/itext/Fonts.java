/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.itext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 *
 * @author bwaidach
 */
public interface Fonts {
    public static final Font body = new Font(Font.FontFamily.HELVETICA, 18);
    public static final Font DOCUMENTHEADLINE = new Font(Font.FontFamily.HELVETICA, 
                                                22, Font.BOLD | Font.UNDERLINE, BaseColor.RED);
}  
