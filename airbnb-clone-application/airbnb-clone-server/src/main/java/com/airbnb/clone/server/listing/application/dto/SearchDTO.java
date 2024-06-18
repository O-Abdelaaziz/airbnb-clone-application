package com.airbnb.clone.server.listing.application.dto;

import com.airbnb.clone.server.booking.application.dto.BookedDateDTO;
import com.airbnb.clone.server.listing.application.dto.sub.ListingInfoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Created 6/18/2024 - 11:13 AM on (Tuesday)
 * @Package com.airbnb.clone.server.listing.application.dto
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record SearchDTO(@Valid BookedDateDTO dates,
                        @Valid ListingInfoDTO infos,
                        @NotEmpty String location) {
}
