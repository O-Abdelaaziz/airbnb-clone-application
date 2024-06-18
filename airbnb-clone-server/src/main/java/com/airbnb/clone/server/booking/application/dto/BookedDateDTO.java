package com.airbnb.clone.server.booking.application.dto;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

/**
 * @Created 6/17/2024 - 8:05 PM on (Monday)
 * @Package com.airbnb.clone.server.booking.application.dto
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record BookedDateDTO(
        @NotNull OffsetDateTime startDate,
        @NotNull OffsetDateTime endDate) {
}
