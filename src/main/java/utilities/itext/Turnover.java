/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.itext;

import beans.Category;
import beans.PaymentPosition;
import bl.AusgabenBl;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import db.Database;
import enums.StatisticType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author bwaidach
 */
public class Turnover {

    private static final Database db = Database.getInstance();
    private static final AusgabenBl bl = AusgabenBl.getInstance();
    private static final NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public static void createTurnOver(String title, StatisticType type, Date from, Date to) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("text.pdf"));

            document.open();

            Paragraph headline = new Paragraph("Umsatzstatistik von: " + title, Fonts.DOCUMENTHEADLINE);
            headline.setAlignment(Element.ALIGN_CENTER);
            document.add(headline);

            HashMap<String, BigDecimal> expenseData = null;
            if (type.equals(StatisticType.CATEGORY)) {
                expenseData = db.getTotalExpensesByCategory(from, to);
            }

            Paragraph tables = createTablesWithoutDetails(expenseData);
            tables.setSpacingBefore(30f);
            document.add(tables);

//            document.add(createPieChart(expenseData, "Ausgaben"));
            Category cat = bl.getBillCategoryByName("Elektrogeräte");

            document.add(createTablesWithDetails(bl.getPaymentPositionsByCategory(cat, from, to)));

            document.close();
            System.exit(0);
        } catch (Exception ex) {
            Logger.getLogger(Turnover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Paragraph createTablesWithoutDetails(HashMap<String, BigDecimal> expenseData) throws DocumentException {
        Paragraph paragraph = new Paragraph();

        PdfPTable expense = new PdfPTable(2);
        float[] colWidths = {1f, 3f};
        expense.setWidths(colWidths);

        for (String cat : expenseData.keySet()) {
            Paragraph contentCell1 = new Paragraph(cat);
            PdfPCell cell1 = new PdfPCell(contentCell1);

            Paragraph contentCell2 = new Paragraph(formatter.format(expenseData.get(cat)));
            PdfPCell cell2 = new PdfPCell(contentCell2);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            cell1.setPaddingBottom(4f);
            cell2.setPaddingBottom(4f);

            expense.addCell(cell1);
            expense.addCell(cell2);
        }

        paragraph.add(expense);
        return paragraph;
    }

    private static Paragraph createTablesWithDetails(ArrayList<PaymentPosition> data) throws DocumentException {
        Paragraph paragraph = new Paragraph();

        PdfPTable expense = new PdfPTable(2);
        float[] colWidths = {5f, 1f};
        expense.setWidths(colWidths);

        int lastPaymentID = 0;
        for (PaymentPosition pp : data) {
            if (lastPaymentID != pp.getPayment().getId()) {
                //new Payment
                Paragraph p1 = new Paragraph(pp.getPayment().getPayee().getName());
                PdfPCell c1 = new PdfPCell(p1);

                //add next cell with price
                //calc price
                BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
                for(PaymentPosition payPos : data){
                    if(payPos.getPayment().getId() == pp.getPayment().getId()){
                        totalPrice = totalPrice.add(payPos.getTotalPrice());
                    }
                }
                Paragraph p2 = new Paragraph(totalPrice.toPlainString());
                PdfPCell c2 = new PdfPCell(p2);
                
                c1.setBorder(Rectangle.NO_BORDER);
                c1.setBorderWidthTop(0.5f);
                c2.setBorder(Rectangle.NO_BORDER);
                c2.setBorderWidthTop(0.5f);

                expense.addCell(c1);
                expense.addCell(c2);
            }

            //payment position
            Paragraph p1 = new Paragraph("            " + pp.getQuantity() + "x: " + pp.getArticle().getName());
            PdfPCell c1 = new PdfPCell(p1);

            //add next cell with price
            Paragraph p2 = new Paragraph(pp.getTotalPriceAsString());
            PdfPCell c2 = new PdfPCell(p2);
            
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);

            expense.addCell(c1);
            expense.addCell(c2);

            lastPaymentID = pp.getPayment().getId();
        }

        paragraph.add(expense);

        paragraph.setSpacingBefore(10f);

        return paragraph;
    }

    private static Image createPieChart(HashMap<String, BigDecimal> map, String title) throws Exception {
        DefaultPieDataset data = new DefaultPieDataset();

        for (String key : map.keySet()) {
            data.setValue(key, map.get(key).doubleValue());
        }

        JFreeChart pieChart = ChartFactory.createPieChart(title, data, true, false, Locale.GERMAN);
//        PiePlot plot = (PiePlot) pieChart.getPlot();
//        plot.setLabelGenerator(null);

        BufferedImage pie = pieChart.createBufferedImage(500, 500);
        File tempPie = File.createTempFile("png", null);
        ImageIO.write(pie, "png", tempPie);
        Image img = Image.getInstance(tempPie.getPath());

        return img;
    }

    public static void main(String[] args) {
        Date begin = new Date(2014 - 1900, 9, 1);
        Date end = new Date(2014 - 1900, 10, 30);
        createTurnOver("November 2014", StatisticType.CATEGORY, begin, end);
    }

}
