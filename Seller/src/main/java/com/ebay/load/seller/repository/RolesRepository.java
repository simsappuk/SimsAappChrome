package com.ebay.load.seller.repository;

import com.ebay.load.seller.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rolesRepository")
public interface RolesRepository extends JpaRepository<Roles,Integer>
{
    Roles findByRole(String role);

    @Query(value = "SELECT a.* FROM public.role a join user_role b on a.id=b.role_id where user_id = ?1", nativeQuery = true)
    List<Roles> findRoleByUserId(String id);

}
