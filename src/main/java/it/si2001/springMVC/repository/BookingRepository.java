package it.si2001.springMVC.repository;

import it.si2001.springMVC.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "from Booking where user.email = :email")
    List<Booking> findBookingByUserEquals(@Param("email") String email);

    @Query(value = "from Booking where user.id = :id")
    List<Booking> findBookingByUserId(@Param("id") int id);

}
