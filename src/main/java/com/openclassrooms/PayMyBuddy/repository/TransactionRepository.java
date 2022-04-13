package com.openclassrooms.PayMyBuddy.repository;

import com.openclassrooms.PayMyBuddy.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    //TODO revoir toute les transaction RECU
    @Query(value = "select * from transaction where id_giver = :id OR id_receiver = :id", nativeQuery = true)
    Page<Transaction> findYourTransactions(@Param("id")int id, Pageable pageable);

}
