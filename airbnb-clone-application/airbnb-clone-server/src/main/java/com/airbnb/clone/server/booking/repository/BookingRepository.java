package com.airbnb.clone.server.booking.repository;

import com.airbnb.clone.server.booking.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created 5/18/2024 - 12:36 PM on (Saturday)
 * @Package com.airbnb.clone.server.booking.repository
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
