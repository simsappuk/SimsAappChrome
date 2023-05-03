package com.ebay.load.seller.service;


import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.repository.OrdersRepository;
import com.itextpdf.text.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PdfService {

    @Autowired
    OrdersRepository ordersRepository;

    public ByteArrayInputStream savePdf(Orders orders) throws DocumentException {

                //String path = System.getProperty("user.home") + "/Desktop/" + orders.getExtendedOrderId() + ".pdf";
                String currency;
                if(orders.getTotalAmountCurrencyId().equals("GBP"))
                    currency="£";
                else
                    currency="€";
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

                Paragraph headerParagraph = new Paragraph(new Chunk(String.valueOf(orders.getPaidDate()), font));
                headerParagraph.setTabSettings(new TabSettings(356f));
                headerParagraph.add(Chunk.TABBING);
                headerParagraph.add(new Chunk("eBay: Order detail's", font));

                Paragraph p1 = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");

                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Order information", font), rect.getLeft(), rect.getTop() - 50, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Delivery address", font), rect.getRight() - 250, rect.getTop() - 50, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Order total", font), rect.getRight() - 20, rect.getTop() - 50, 0);


                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Buyer            " + orders.getBuyerUserId(), font1), rect.getLeft(), rect.getTop() - 70, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Seller           " + orders.getSellerUserId(), font1), rect.getLeft(), rect.getTop() - 90, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Order placed on  " + ConvertDateToString(orders.getPaidDate()), font1), rect.getLeft(), rect.getTop() - 110, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Payment method   " + orders.getPaymentMethod(), font1), rect.getLeft(), rect.getTop() - 130, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Payment date     " + ConvertDateToString(orders.getPaidDate()), font1), rect.getLeft(), rect.getTop() - 150, 0);


                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(orders.getBuyerName(), font), rect.getRight() - 300, rect.getTop() - 70, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(orders.getBuyerStreet1(), font1), rect.getRight() - 300, rect.getTop() - 85, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(orders.getBuyerStreet2(), font1), rect.getRight() - 300, rect.getTop() - 100, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(orders.getBuyerPostalCode(), font1), rect.getRight() - 300, rect.getTop() - 115, 0);
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(orders.getBuyerCountry(), font1), rect.getRight() - 300, rect.getTop() - 130, 0);


                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Subtotal        "+currency+ orders.getSubTotal(), font1), rect.getRight() - 20, rect.getTop() - 80, 0);
                if (orders.getShippingServiceCost() != null && !orders.getShippingServiceCost().equals("0.0"))
                    ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Postage        "+currency+ orders.getShippingServiceCost(), font1), rect.getRight() - 20, rect.getTop() - 100, 0);
                else
                    ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Postage        Free", font1), rect.getRight() - 20, rect.getTop() - 100, 0);

                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Total        " + currency+ orders.getTotalAmount(), font1), rect.getRight() - 20, rect.getTop() - 120, 0);

                Paragraph p3 = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");
                p3.setSpacingBefore(125);

                Paragraph p2 = new Paragraph("Item(s) bought from "+orders.getSellerUserId(), font2);
                Paragraph p4 = new Paragraph("Order number " + orders.getExtendedOrderId(), font1);
                p4.setSpacingBefore(2);

                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{10, 40, 30, 20});
                table.getDefaultCell().setBorder(0);
                table.addCell(new Paragraph("Qty", font));
                table.addCell(new Paragraph("Item Name", font));
                table.addCell(new Paragraph("Delivery service", font));
                table.addCell(new Paragraph("Item price", font));
                table.addCell(new Paragraph(orders.getQuantityPurchased(), font1));
                table.addCell(new Paragraph(orders.getTitle() + "(" + orders.getItemId() + ")", font1));
                table.addCell(new Paragraph(orders.getShippingServiceSelected(), font1));
                table.addCell(new Paragraph(currency + orders.getTotalAmount(), font1));

                table.setSpacingBefore(20);

                document.add(headerParagraph);
                document.add(p1);
                document.add(p3);
                document.add(p2);
                document.add(p4);
                document.add(table);
                document.close();
               /* byte[] pdf = baos.toByteArray();
                orders.setPdfFile(pdf);
                ordersRepository.save(orders);*/
            } catch (Exception e) {
                System.err.println(e);
            }
            return new ByteArrayInputStream(out.toByteArray());
    }

    private String ConvertDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
        return formatter.format(date);
    }
}
