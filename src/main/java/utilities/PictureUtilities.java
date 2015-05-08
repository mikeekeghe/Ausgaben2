/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Bernhard
 */
public class PictureUtilities {

    public static BufferedImage convertToBufferedImage(File file) {
        try {
            BufferedImage bi = null;
            bi = ImageIO.read(file);
            return bi;
        } catch (IOException ex) {
            Logger.getLogger(PictureUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static BufferedImage getImageFromClipboard() {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            try {
                return (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                // handle this as desired
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Image convertBufferedImageToImage(BufferedImage bi) {
        return Toolkit.getDefaultToolkit().createImage(bi.getSource());
    }

}
