package com.airbnb.clone.server.listing.application.dto.vo;

import jakarta.validation.constraints.NotNull;

/**
 * @Created 5/31/2024 - 4:16 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.application.dto.vo
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record BathsVO(@NotNull(message = "Bath value must be present") int value) {
}
