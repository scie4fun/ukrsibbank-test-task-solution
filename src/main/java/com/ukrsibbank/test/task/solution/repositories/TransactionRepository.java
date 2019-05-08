package com.ukrsibbank.test.task.solution.repositories;

import com.ukrsibbank.test.task.solution.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
