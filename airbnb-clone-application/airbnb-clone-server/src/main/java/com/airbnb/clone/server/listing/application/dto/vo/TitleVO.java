package com.airbnb.clone.server.listing.application.dto.vo;

import jakarta.validation.constraints.NotNull;

/**
 * @Created 5/31/2024 - 4:15 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.application.dto.vo
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record TitleVO(@NotNull(message = "Title value must be present") String value) {
}
