package com.openclassrooms.PayMyBuddy.repository;

import com.openclassrooms.PayMyBuddy.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction,Integer> {

    //TODO revoir toute les transaction RECU
    @Query(value = "select * from transaction where id_giver = :id" , nativeQuery = true)
    List<Transaction> findYourTransactions(@Param("id")int id);

}
