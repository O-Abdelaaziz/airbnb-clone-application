package com.airbnb.clone.server.listing.application.dto.sub;

import com.airbnb.clone.server.listing.application.dto.vo.DescriptionVO;
import com.airbnb.clone.server.listing.application.dto.vo.TitleVO;
import jakarta.validation.constraints.NotNull;

/**
 * @Created 5/31/2024 - 4:22 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.application.dto.sub
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record DescriptionDTO(
        @NotNull TitleVO title,
        @NotNull DescriptionVO description
        ) {
}
