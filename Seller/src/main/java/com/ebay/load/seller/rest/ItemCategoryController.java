package com.ebay.load.seller.rest;

import com.ebay.load.seller.model.ItemCategory;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.EbayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/category")
public class ItemCategoryController {
    @Autowired
    EbayService ebayService;

    @RequestMapping(value = "/content/save", method = RequestMethod.GET)
    ResponseEntity<ItemCategory> getSingleStock(
            @RequestParam(value="selection",defaultValue = "",required = false) String selectedHref,
            @RequestParam(value="category",defaultValue = "",required = false) String category,
            @RequestParam(value="console",defaultValue = "",required = false) String console
    ) {
        return ebayService.getItemCategoryContent(selectedHref,category,console);
    }

    @RequestMapping(value = "/content",method = RequestMethod.GET)
    ResponseEntity<ItemCategory> displayContent(@RequestParam(value="selection",defaultValue = "",required = false) String selectedHref,
                                                @RequestParam(value="console",defaultValue = "",required = false) String console){
        return ebayService.displayItemCategoryContent(selectedHref,console);
    }

    @RequestMapping(value = "/content/delete",method = RequestMethod.GET)
    ResponseEntity<ItemCategory> deleteContent(@RequestParam(value="modelName",defaultValue = "",required = false) String modelName,
                                               @RequestParam(value="console",defaultValue = "",required = false) String console){
        return ebayService.deleteContent(modelName,console);
    }
}
