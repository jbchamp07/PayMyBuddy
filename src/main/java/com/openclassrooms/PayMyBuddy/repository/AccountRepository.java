package com.openclassrooms.PayMyBuddy.repository;

import com.openclassrooms.PayMyBuddy.model.Account;
import com.openclassrooms.PayMyBuddy.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    //@Query(value = "select id,user,balance from account a ,friendlist f where a.id = f.id_receiver and f.id_giver = :id_giver" , nativeQuery = true)
    @Query(value = "select DISTINCT account_id from user u ,friendlist f where u.account_id = f.id_receiver and f.id_giver = :id_giver" , nativeQuery = true)
    int[] friendsId(@Param("id_giver")int id);
    @Query(value = "select * from account where account.id IN (:list)" , nativeQuery = true)
    Iterable<Account> findAllFriend(@Param("list")int[] list);
}
