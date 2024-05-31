package com.airbnb.clone.server.listing.application.dto.sub;

import jakarta.validation.constraints.NotNull;

/**
 * @Created 5/31/2024 - 4:23 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.application.dto.sub
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record LandlordListingDTO(@NotNull String firstname,
                                 @NotNull String imageUrl) {
}
