package com.ebay.load.seller.service;

//import com.ebay.load.seller.model.Roles;
import com.ebay.load.seller.model.Users;
import com.ebay.load.seller.repository.RolesRepository;
import com.ebay.load.seller.repository.UsersRepository;
import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
import org.apache.xpath.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    public Users checkExistingEmail(String email) {
        Users users = usersRepository.findByEmail(email);
        return users;
    }

    @Override
    public Users save(String accountId, Users users) {
        if(checkExistingEmail(users.getEmail()) == null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
            users.setAccountId(accountId);
            users.setInactive(false);
            Users users1 = usersRepository.save(users);
            return users1;
        }
        return null;
    }

    @Override
    public void updateUser(String accountId, Users users) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
            usersRepository.saveAndFlush(users);
    }

    @Override
    public ResponseEntity<List<Users>> findAll(String accountId, Pageable p) {
        Page<Users> a = usersRepository.findByAccountId(accountId,p);
        return new ResponseEntity<List<Users>>().withResults(a.getContent()).withTotalElements(a.getTotalElements()).withTotalPages(a.getTotalPages());
    }

    public ResponseEntity<String> deleteUser(String accountId,String id){
        Users admin=usersRepository.findByUserId(id);
        if(!admin.getId().equals("1")) {
            usersRepository.deleteByUserIdAndAccountId(id, accountId);
            return new ResponseEntity<String>().withErrors(false);
        }
        else
            return new ResponseEntity<String>().withErrors(true);
    }



    @Override
    public ResponseEntity<Users> findById(String accountId,String id)
    {
        Users aa= usersRepository.findByAccountIdAndUserId(accountId,id);
        return new ResponseEntity<Users>().withResults(aa);

    }

}