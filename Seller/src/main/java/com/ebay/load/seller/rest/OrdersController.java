package com.ebay.load.seller.rest;

import com.ebay.load.seller.config.SessionUserInfo;
import com.ebay.load.seller.model.Orders;
import com.ebay.load.seller.model.Spreadsheet;
import com.ebay.load.seller.repository.OrdersRepository;
import com.ebay.load.seller.seller.schema.beans.base.PdfEntity;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.*;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/orders/")
public class OrdersController {


    @Autowired
    private OrdersService ordersService;

    @Autowired
    private FoldersService foldersService;

    @Autowired
    EbayService ebayService;

    @Autowired
    ActivityLogService activityLogService;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    PdfService pdfService;



    @RequestMapping(value = "{channelId}/{spreadSheetId}/{accountId}/{sheetId}", method = RequestMethod.POST)
    public List<Orders> create(@PathVariable("accountId") String accountId,
                               @PathVariable("channelId") String id,
                               @PathVariable("spreadSheetId") String spreadsheetId,
                               @PathVariable("sheetId") String sheetNumber,
                               @RequestBody List<Orders> orders) {
        return ordersService.save(orders,spreadsheetId,id,accountId,sheetNumber);
    }

    @RequestMapping(value = "/saveCellData", method = RequestMethod.GET)
    public ResponseEntity<Orders> saveCellData(@RequestParam(value="name") String name,
                                                    @RequestParam("accountId") String accountId,
                                                    @RequestParam("channelId") String channelId,
                                                    @RequestParam("spreadSheetId") String spreadsheetId,
                                                    @RequestParam(value="value") String value,
                                                    @RequestParam(value="rowId") String rowId){
        return ordersService.getSavedData(name,value,accountId,channelId,spreadsheetId,SessionUserInfo.getLoggedInUser().getUser().getId(),rowId);
    }

    @RequestMapping(value = "/saveCopyPaste", method = RequestMethod.POST)
    public ResponseEntity<Orders> saveData(    @RequestParam("accountId") String accountId,
                                                    @RequestParam("channelId") String channelId,
                                                    @RequestParam("spreadSheetId") String spreadsheetId,
                                                    @RequestParam(value="rowId") String rowId,
                                                    @RequestBody Orders orders) {
        return ordersService.saveCopyPasteData(accountId,channelId,spreadsheetId,SessionUserInfo.getLoggedInUser().getUser().getId(),rowId,orders);
    }

    @RequestMapping(value = "getThirdPartyData/response/{accountId}/{tabName}", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> thirdPartyData(@PathVariable("tabName") String channel,
                                                      @PathVariable("accountId") String accountId) {
        return ordersService.getChannelData(channel,accountId);
    }

    @RequestMapping(value = "newSheet/{spreadSheetId}/{channelId}/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<Spreadsheet> newSheetNumber(@PathVariable("spreadSheetId") String spreadsheetId,
                                                      @PathVariable("channelId") String channelId,
                                                      @PathVariable("accountId") String accountId) {
        return ordersService.newSheetNumber(SessionUserInfo.getLoggedInUser().getUser().getId(),spreadsheetId,channelId,accountId);
    }

    @RequestMapping(value = "loadSheet/{spreadSheetId}/{channelId}/{sheetNumber}/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> loadSheetNumber(@PathVariable("spreadSheetId") String spreadsheetId,
                                                        @PathVariable("channelId") String channelId,
                                                        @PathVariable("sheetNumber") String sheetNumber,
                                                        @PathVariable("accountId") String accountId) {
        return ordersService.loadSheet(SessionUserInfo.getLoggedInUser().getUser().getId(),spreadsheetId,channelId,sheetNumber,accountId);
    }

    @RequestMapping(value = "getPurchaseData/{selection}/{channelId}/{accountId}/{spreadSheetId}/{data}", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> getPurchaseData(@PathVariable("spreadSheetId") String spreadsheetId,
                                                        @PathVariable("channelId") String channelId,
                                                        @PathVariable("selection") String selection,
                                                        @PathVariable("accountId") String accountId,
                                                        @PathVariable("data") String data) {
        return ordersService.getPurchasedSearchData(SessionUserInfo.getLoggedInUser().getUser().getId(),spreadsheetId,channelId,selection,accountId,data);
    }

    @RequestMapping(value = "getSheet/{spreadSheetId}/{channelId}/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> findAllSheets(@PathVariable("spreadSheetId") String spreadsheetId,
                                                      @PathVariable("channelId") String channelId,
                                                      @PathVariable("accountId") String accountId) {
        return ordersService.findAllSheetNumbers(SessionUserInfo.getLoggedInUser().getUser().getId(),spreadsheetId,channelId,accountId);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> getAllId(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                               @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                               @RequestParam(value = "accountId", defaultValue = "", required = false) String accountId,
                                               @RequestParam(value = "channelId", defaultValue = "1", required = false) String channelId) {
        return ordersService.findAllWithOwnerId(SessionUserInfo.getLoggedInUser().getUser().getId(), channelId, accountId, new Sort("createdDate"));
    }
    @RequestMapping(value = "load/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> getAll(@RequestParam(value = "channelId", defaultValue = "1", required = false) String channelId,
                                               @RequestParam(value = "accountId", defaultValue = "1", required = false) String accountId,
                                               @RequestParam(value = "sheetNumber", defaultValue = "", required = false) String sheetNumber,
                                               @PathVariable("id") String spreadsheetId) {
        return ordersService.findAllWithSpreadsheetId(SessionUserInfo.getLoggedInUser().getUser().getId(), spreadsheetId,channelId, sheetNumber,accountId);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Orders> getSingleOrderDelete(@PathVariable("id") String id) {
        return ordersService.findSingleIdforEdit(SessionUserInfo.getLoggedInUser().getUser().getId(), id);
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    ResponseEntity<String> getSingleAccounts(@PathVariable("id") String id) {
        return ordersService.findSingleIdForDelete(id, SessionUserInfo.getLoggedInUser().getUser().getId());
    }

    @RequestMapping(value = "/refreshPurchaseOrders", method = RequestMethod.POST)
    public ResponseEntity<String> refreshPurchaseOrders(@RequestParam(value = "accountId") String accountId) throws ParseException {
        return ordersService.refreshPurchaseOrders(new PageRequest(0,200),accountId, SessionUserInfo.getLoggedInUser().getUser().getId());
    }


    @RequestMapping(value = "/getTitle", method = RequestMethod.GET)
    public ResponseEntity<String> getTitle(@RequestParam(value="recordNumber") String recordNum){
        return ebayService.getTitle(recordNum);
    }


    @ResponseBody
    @CrossOrigin
    @RequestMapping(value="/get/postJsonData",method=RequestMethod.POST)
    public void getJson(HttpServletRequest request,
                        @RequestParam(value="site") String site) throws IOException, JSONException, ParseException {
        InputStream is=request.getInputStream();
        if(site.equals("Game"))
            ebayService.getGameData(IOUtils.toString(is, "UTF-8"),"2");
        else if(site.equals("MusicMagpie"))
            ebayService.getMagpieJsonData(IOUtils.toString(is, "UTF-8"),"3");
        else if(site.equals("Cex"))
            ebayService.getCexData(IOUtils.toString(is, "UTF-8"),"4");
        else if (site.equals("log"))
            activityLogService.saveSheets(IOUtils.toString(is, "UTF-8"));
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value="/setTitles/notSentItem",method=RequestMethod.POST)
    public String setTitle(HttpServletRequest request,
                          @RequestParam(value="site") String site) throws IOException, JSONException, ParseException {
        InputStream is=request.getInputStream();
        return activityLogService.setTitles(IOUtils.toString(is, "UTF-8"));
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/gmail/viewOrderDetails/data", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
    public PdfEntity viewOrderDetails(@RequestParam(value="json") String data) throws IOException, DocumentException, JSONException {
        return ebayService.saveOrderDetailsFromGmail(data);
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/magpie/viewOrderDetails/data", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
    public PdfEntity downloadMagpieReceipt(@RequestParam(value="json") String data) throws IOException, DocumentException, JSONException {
        return ebayService.generateMagpiePdf(data);
    }


    @RequestMapping(value = "popup/generatePdf/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
    public PdfEntity generatePdf(@PathVariable("id") String id) throws DocumentException {
        Orders order = ordersRepository.findOneById(id);
        ByteArrayInputStream bis = null;
        try{
            bis =pdfService.savePdf(order);
        }catch(DocumentException e){
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","inline;filename="+order.getExtendedOrderId()+".pdf");
        return PdfEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @RequestMapping(value = "getInvoiceData/{channelId}/{accountId}/{spreadSheetId}/{data}", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> getInvoiceData(@PathVariable("channelId") String channelId,
                                                       @PathVariable("spreadSheetId") String spreadsheetId,
                                                       @PathVariable("accountId") String accountId,
                                                       @PathVariable("data") String data) {
        return ordersService.getInvoicePdfByDate(SessionUserInfo.getLoggedInUser().getUser().getId(), channelId, accountId, spreadsheetId, data);
    }

    @RequestMapping(value = "/getVintedStatus/update", method = RequestMethod.GET)
    public String vintedStatusUpdate(@RequestParam(value="trackingId") String trackingNumber){
        return HttpClient.sendGet(trackingNumber);
    }


}
