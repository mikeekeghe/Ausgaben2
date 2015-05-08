/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import eu.gnome.morena.Configuration;
import eu.gnome.morena.Device;
import eu.gnome.morena.Manager;
import eu.gnome.morena.Scanner;
import eu.gnome.morena.wia.WIACamera;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Bernhard
 */
public class ScannerUtilities {

    public static BufferedImage getImage() throws Exception {
        Configuration.addDeviceType(".*jet.*", true);
        Manager manager = Manager.getInstance();

        List<Device> devices = manager.listDevices();
        Device device = (Device) devices.get(0);
        if (device instanceof WIACamera) {
            WIACamera camera = (WIACamera) device;

        } else if (device instanceof Scanner) {
            Scanner scanner = (Scanner) device;
            scanner.setMode(Scanner.RGB_8);
            scanner.setResolution(100);

        }
        System.out.println(device.toString());

        return SynchronousHelper.scanImage(device);
    }

    public static ArrayList<BufferedImage> getMultipleImages() throws Exception {
        ArrayList<BufferedImage> images = new ArrayList<>();
        Manager manager = Manager.getInstance();
        List<Device> devices = manager.listDevices();
        Device device = (Device) devices.get(0);
        Scanner scanner = (Scanner) device;
        scanner.setMode(Scanner.RGB_8);
        scanner.setResolution(100);

        ScanSession session = new ScanSession();
        session.startSession(device, 1);
        File file = null;
        while ((file = session.getImageFile()) != null) {
            BufferedImage bi = ImageIO.read(file);
            images.add(bi);
        }

        return images;
    }

}
