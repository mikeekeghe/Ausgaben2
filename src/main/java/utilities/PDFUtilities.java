/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author Bernhard
 */
public class PDFUtilities {
    public static ArrayList<BufferedImage> extractPagesAsImage(File file) throws Exception {
        ArrayList<BufferedImage> images = new ArrayList<>();
        
        if (file != null) {
            PDDocument doc = PDDocument.load(file);
            List<PDPage> pages = doc.getDocumentCatalog().getAllPages();
            for (PDPage page : pages) {
                
                BufferedImage bImage = page.convertToImage(BufferedImage.TYPE_INT_RGB, 160);
                images.add(bImage);
            }
        }
        return images;
    }
    
    public static BufferedImage extractSingleImage(File file, int pageIndex) throws Exception{
        ArrayList<BufferedImage> images = extractPagesAsImage(file);
        if(! (images.size()+1 < images.size()) ) //enough pictures in list
        {
            return images.get(pageIndex-1);
        }else{
            throw new Exception("PageIndex to high!");
        }
        
    }
}
