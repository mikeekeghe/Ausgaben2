/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Bernhard
 */
@Entity
@Table
public class DocumentPage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] page;
    @Column(nullable = false)
    private int pageIndex;
    @ManyToOne
    private Document document;

    public DocumentPage() {
    }

    public DocumentPage(BufferedImage page, int pageIndex) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(page, "JPG", out);
            this.page = out.toByteArray();
            this.pageIndex = pageIndex;
        } catch (IOException ex) {
            Logger.getLogger(DocumentPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DocumentPage(File file, int pageIndex) {
        try {
            BufferedImage page = ImageIO.read(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(page, "JPG", out);
            this.page = out.toByteArray();
            this.pageIndex = pageIndex;
        } catch (IOException ex) {
            Logger.getLogger(DocumentPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BufferedImage getPage() {
        try {
            InputStream in = new ByteArrayInputStream(page);
            return ImageIO.read(in);
        } catch (IOException ex) {
            Logger.getLogger(DocumentPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setPage(BufferedImage page) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(page, "JPG", out);
            this.page = out.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(DocumentPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
