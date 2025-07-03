package com.happy.Library_Management.borrowing;

import com.happy.Library_Management.book.Book;
import com.happy.Library_Management.book.BookRepository;
import com.happy.Library_Management.role.Role;
import com.happy.Library_Management.user.User;
import com.happy.Library_Management.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingTransactionRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public List<BorrowingTransaction> getAllTransactions() {
        return borrowingRepository.findAll();
    }

    public BorrowingTransaction getById(Long id) {
        return borrowingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public BorrowingTransaction borrowBook(Long bookId, Integer userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isBorrowed = borrowingRepository.existsByBookIdAndStatus(
                bookId, BorrowingTransaction.BorrowStatus.BORROWED
        );

        if (isBorrowed) {
            throw new RuntimeException("This book is currently borrowed by another user.");
        }

        BorrowingTransaction transaction = BorrowingTransaction.builder()
                .book(book)
                .borrower(user)
                .borrowDate(LocalDate.now())
                .dueDate(LocalDate.now().plusWeeks(2))
                .status(BorrowingTransaction.BorrowStatus.BORROWED)
                .build();

        return borrowingRepository.save(transaction);
    }

    public BorrowingTransaction returnBook(Long transactionId, User currentUser) {
        BorrowingTransaction transaction = getById(transactionId);

        boolean isBorrower = transaction.getBorrower().getId().equals(currentUser.getId());
        boolean isPrivileged = currentUser.getRoles().stream()
                .map(Role::getRoleName)
                .anyMatch(roleName ->
                        roleName.equals("ADMIN") ||
                                roleName.equals("LIBRARIAN") ||
                                roleName.equals("STAFF")
                );

        if (!isBorrower && !isPrivileged) {
            throw new AccessDeniedException("You are not allowed to return this book.");
        }

        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus(BorrowingTransaction.BorrowStatus.RETURNED);
        return borrowingRepository.save(transaction);
    }


    public List<BorrowingTransaction> getUserTransactions(Integer userId) {
        return borrowingRepository.findByBorrowerId(userId);
    }
}
