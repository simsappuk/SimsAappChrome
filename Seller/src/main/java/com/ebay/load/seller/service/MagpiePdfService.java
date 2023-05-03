package com.ebay.load.seller.service;

import com.ebay.load.seller.model.Pdf;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class MagpiePdfService {

    public ByteArrayInputStream HTMlToPdfConversion(Pdf pdf) throws DocumentException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,out);
        try{
            Rectangle rect = new Rectangle(30, 30, 550, 800);
            writer.setBoxSize("art", rect);

            document.open();

            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
            com.itextpdf.text.Font font2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.BLACK);
            com.itextpdf.text.Font font1 = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);

            Paragraph headerParagraph = new Paragraph("Music Magpie Order detail's", font);
            headerParagraph.setTabSettings(new TabSettings(356f));
            headerParagraph.add(Chunk.TABBING);
            headerParagraph.add(new Chunk(pdf.getPaidDate(), font));

            Paragraph p1 = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Order information", font), rect.getLeft(), rect.getTop() - 50, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Delivery address", font), rect.getRight() - 250, rect.getTop() - 50, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Order total", font), rect.getRight() - 20, rect.getTop() - 50, 0);


            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Buyer           " + "Zaeem Farooqui", font1), rect.getLeft(), rect.getTop() - 70, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Seller          " + "Music Magpie", font1), rect.getLeft(), rect.getTop() - 90, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Payment date    " + pdf.getPaidDate(), font1), rect.getLeft(), rect.getTop() - 110, 0);



            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Zaeem Farooqui", font), rect.getRight() - 300, rect.getTop() - 70, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Pound Monkey Ltd Unit 7A", font1), rect.getRight() - 300, rect.getTop() - 85, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("1st Floor,Premier House", font1), rect.getRight() - 300, rect.getTop() - 100, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("43 Constance Road LE5 5DE,UK", font1), rect.getRight() - 300, rect.getTop() - 115, 0);


            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Promo applied  " + pdf.getPostageFee(), font1), rect.getRight() - 20, rect.getTop() - 100, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Total amount   " + pdf.getTotalAmount(), font1), rect.getRight() - 20, rect.getTop() -120, 0);

            Paragraph p3 = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");
            p3.setSpacingBefore(125);
            document.add(headerParagraph);
            document.add(p1);
            document.add(p3);
            Paragraph p2 = new Paragraph("Order number " + pdf.getOrderDetails().get(0).getExtendedOrderId(), font1);
            document.add(p2);

            for(int i=0;i<pdf.getOrderDetails().size();i++){
                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{10, 40, 30, 20});
                table.getDefaultCell().setBorder(0);
                table.addCell(new Paragraph("Qty", font));
                table.addCell(new Paragraph("Item Name", font));
                table.addCell(new Paragraph("Condition", font));
                table.addCell(new Paragraph("Item price", font));
                table.addCell(new Paragraph(pdf.getOrderDetails().get(i).getQuantity(), font1));
                table.addCell(new Paragraph(pdf.getOrderDetails().get(i).getTitle(), font1));
                table.addCell(new Paragraph("Used", font1));
                table.addCell(new Paragraph(pdf.getOrderDetails().get(i).getItemPrice(), font1));

                table.setSpacingBefore(20);
                document.add(table);
            }
            document.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
