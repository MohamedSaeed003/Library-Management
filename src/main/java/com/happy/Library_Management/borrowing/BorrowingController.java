package com.happy.Library_Management.borrowing;

import com.happy.Library_Management.user.User;
import com.happy.Library_Management.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
    public ResponseEntity<List<BorrowingTransaction>> getAll() {
        return ResponseEntity.ok(borrowingService.getAllTransactions());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
    public ResponseEntity<BorrowingTransaction> getById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingService.getById(id));
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingTransaction> borrowBook(@RequestParam Long bookId,
                                                           @RequestParam Integer userId) {
        return ResponseEntity.ok(borrowingService.borrowBook(bookId, userId));
    }

    @PostMapping("/return/{transactionId}")
    public ResponseEntity<BorrowingTransaction> returnBook(
            @PathVariable Long transactionId,
            Authentication authentication) {

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        BorrowingTransaction tx = borrowingService.returnBook(transactionId, user);
        return ResponseEntity.ok(tx);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
    public ResponseEntity<List<BorrowingTransaction>> getByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(borrowingService.getUserTransactions(userId));
    }
}
