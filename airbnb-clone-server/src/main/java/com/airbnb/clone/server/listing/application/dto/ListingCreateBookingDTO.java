package com.airbnb.clone.server.listing.application.dto;

import com.airbnb.clone.server.listing.application.dto.vo.PriceVO;

import java.util.UUID;

/**
 * @Created 6/17/2024 - 8:13 PM on (Monday)
 * @Package com.airbnb.clone.server.listing.application.dto
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record ListingCreateBookingDTO(
        UUID listingPublicId, PriceVO price) {
}
