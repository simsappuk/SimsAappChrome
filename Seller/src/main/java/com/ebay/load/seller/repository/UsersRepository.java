package com.ebay.load.seller.repository;


        import com.ebay.load.seller.model.Stock;
        import com.ebay.load.seller.model.Users;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,String>
{
    Users findByEmail(String email);
    Page<Users> findByAccountId(String accountId,Pageable p);

    @Query(value = "SELECT * FROM users where account_id=?1 and user_id=?2", nativeQuery = true)
    Users findByAccountIdAndUserId(String accountId, String id);

    @Query(value = "Delete FROM users where user_id=?1 and account_id=?2", nativeQuery = true)
    void deleteByUserIdAndAccountId(String id, String accountId);

    @Query(value = "SELECT * FROM users where user_id=?", nativeQuery = true)
    Users findByUserId(String id);
}