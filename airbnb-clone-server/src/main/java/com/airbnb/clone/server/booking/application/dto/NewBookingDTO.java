package com.airbnb.clone.server.booking.application.dto;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @Created 6/17/2024 - 8:03 PM on (Monday)
 * @Package com.airbnb.clone.server.booking.application.dto
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record NewBookingDTO(
        @NotNull OffsetDateTime startDate,
        @NotNull OffsetDateTime endDate,
        @NotNull UUID listingPublicId) {
}
