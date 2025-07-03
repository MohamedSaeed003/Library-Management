package com.happy.Library_Management.borrowing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
    List<BorrowingTransaction> findByBorrowerId(Integer userId);
    boolean existsByBookIdAndStatus(Long bookId, BorrowingTransaction.BorrowStatus status);

}
