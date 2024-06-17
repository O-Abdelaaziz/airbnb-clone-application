package com.airbnb.clone.server.booking.mapper;

import com.airbnb.clone.server.booking.application.dto.BookedDateDTO;
import com.airbnb.clone.server.booking.application.dto.NewBookingDTO;
import com.airbnb.clone.server.booking.domain.Booking;
import org.mapstruct.Mapper;

/**
 * @Created 5/18/2024 - 12:37 PM on (Saturday)
 * @Package com.airbnb.clone.server.booking.mapper
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking newBookingToBooking(NewBookingDTO newBookingDTO);

    BookedDateDTO bookingToCheckAvailability(Booking booking);
}
