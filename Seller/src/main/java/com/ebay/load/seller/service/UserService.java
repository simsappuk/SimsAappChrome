

package com.ebay.load.seller.service;

        import com.ebay.load.seller.model.Users;

        import com.ebay.load.seller.seller.schema.beans.base.ResponseEntity;
        import org.springframework.data.domain.Pageable;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public interface UserService
{
    //  com.ebay.load.seller.model.Users findUserByEmail(String email);
    // @Secured({ "ADMIN" })
    public ResponseEntity<List<Users>> findAll(String q, Pageable p);
    //  public void saveUser(Users user);
    //  public ResponseEntity<Users>  enableUser(Users user);
    //public ResponseEntity<Users>  editUser(Users user);
    //public ResponseEntity<Users>  currentUser();
    public Users checkExistingEmail(String email);
    public Users save(String accountId,Users  users);
    public ResponseEntity<Users> findById(String accountId,String id);

    ResponseEntity<String>  deleteUser(String accountId, String id);

    void updateUser(String accountId, Users users);
}




