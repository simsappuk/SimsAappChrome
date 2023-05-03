package com.ebay.load.seller.rest;

import com.ebay.load.seller.model.Scheduler;
import com.ebay.load.seller.model.SchedulerItem;
import com.ebay.load.seller.service.SchedulerService;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/scheduler")
public class SchedulerController {

    @Autowired
    SchedulerService schedulerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Scheduler>> loadSchedulerContent(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                                @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return schedulerService.findAll(new PageRequest(page.intValue(), pageSize.intValue()));
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    ResponseEntity<List<SchedulerItem>> getSingleStock(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                       @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                                                       @PathVariable("id") String id) {
        return schedulerService.findAllChanges(new PageRequest(page.intValue(), pageSize.intValue()),id);
    }
}