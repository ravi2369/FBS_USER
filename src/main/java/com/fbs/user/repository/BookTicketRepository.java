package com.fbs.user.repository;

import com.fbs.user.model.BookTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookTicketRepository extends JpaRepository<BookTicket, Long> {
    @Query("SELECT BT FROM BookTicket BT WHERE BT.email LIKE ?1%")
    List<BookTicket> searchByEmail(String email);

    @Query("SELECT BT FROM BookTicket BT WHERE BT.PNRNumber LIKE ?1%")
    List<BookTicket> searchByPnr(String PNRNumber);
}
