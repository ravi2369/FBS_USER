package com.fbs.user.repository;

import com.fbs.user.model.BookTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTicketRepository extends JpaRepository<BookTicket, Long> {
}
