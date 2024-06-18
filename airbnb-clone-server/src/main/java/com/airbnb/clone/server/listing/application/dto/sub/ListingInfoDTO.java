package com.airbnb.clone.server.listing.application.dto.sub;


import com.airbnb.clone.server.listing.application.dto.vo.BathsVO;
import com.airbnb.clone.server.listing.application.dto.vo.BedroomsVO;
import com.airbnb.clone.server.listing.application.dto.vo.BedsVO;
import com.airbnb.clone.server.listing.application.dto.vo.GuestsVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * @Created 5/31/2024 - 4:24 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.application.dto.sub
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record ListingInfoDTO(
        @NotNull @Valid GuestsVO guests,
        @NotNull @Valid BedroomsVO bedrooms,
        @NotNull @Valid BedsVO beds,
        @NotNull @Valid BathsVO baths) {
}
