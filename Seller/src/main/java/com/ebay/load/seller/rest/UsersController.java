package com.ebay.load.seller.rest;

import com.ebay.load.seller.model.Users;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import com.ebay.load.seller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController
{


        @Autowired
        private UserService userService;

        @RequestMapping(value = "{accountId}", method = RequestMethod.POST)
        public
        @ResponseBody
        @PostMapping("")
        Users createUsers(@RequestBody Users users,
        @PathVariable(value="accountId") String accountId)
        {
             Users users1=userService.save(accountId,users);
             return users1;
        }


    @RequestMapping( method = RequestMethod.GET)
    ResponseEntity<List<Users>> findAll(@RequestParam(value = "accountId", defaultValue = "0", required = false) String accountId,
                                        @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                        @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return userService.findAll(accountId,new PageRequest(page.intValue(), pageSize.intValue()));
        }
    @ResponseBody
    @RequestMapping(value = "{id}/{accountId}", method = RequestMethod.GET)
    ResponseEntity<Users>  getSingleId(@PathVariable("id") String id,
                                       @PathVariable("accountId") String accountId)
    {
        return userService.findById(accountId,id);

    }

    @ResponseBody
    @RequestMapping(value = "delete/{id}/{accountId}", method = RequestMethod.POST)
    ResponseEntity<String>  deleteUser(@PathVariable("id") String id,
                                       @PathVariable("accountId") String accountId)
    {
        return userService.deleteUser(accountId,id);

    }

    @ResponseBody
    @RequestMapping(value = "update/{accountId}", method = RequestMethod.POST)
    void updateUser(@RequestBody Users users,
                    @PathVariable(value="accountId") String accountId)
    {
        userService.updateUser(accountId,users);

    }

    }


/*


  <div class="form-group">
                        <label class="col-md-3 col-xs-12 control-label">Select Channel</label>
                        <div class="col-md-6 col-xs-12">
                            <select ng-model="orders.channel" class="form-control select">
                                <option value="ebay">ebay</option>
                                <option value="CEX">CEX</option>
                                <option value="GAME">GAME</option>
                                <option value="MUSIC MAGPIE">MUSIC MAGPIE</option>
                                <option value="Grainger Games">Grainger Games</option>
                                <option value="Beatthebomb">Beatthebomb</option>
                                <option value="Tesco">Tesco</option>
                                <option value="cash generator">cash generator</option>
                                <option value="accessories">accessories</option>
                                <option value="Awesome Products">Awesome Products</option>
                                <option value="pounder_sale">pounder_sale</option>
                                <option value="Cool Shop">Cool Shop</option>
                                <option value="Tigerali">Tigerali</option>
                                <option value="Winningshades">Winningshades</option>
                                <option value="Gamesxtension">Gamesxtension</option>
                            </select>
                            <span class="help-block">Enter Channel</span>
                        </div>
                    </div>
 */

