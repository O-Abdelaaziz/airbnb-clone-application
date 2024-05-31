package com.airbnb.clone.server.listing.application.dto.vo;

import jakarta.validation.constraints.NotNull;
/**
 * @Created 5/31/2024 - 4:18 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.application.dto.vo
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record BedsVO(@NotNull(message = "beds value must be present") int value) {
}
