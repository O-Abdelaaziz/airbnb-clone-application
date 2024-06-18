package com.airbnb.clone.server.listing.application.dto;

import com.airbnb.clone.server.listing.application.dto.sub.DescriptionDTO;
import com.airbnb.clone.server.listing.application.dto.sub.ListingInfoDTO;
import com.airbnb.clone.server.listing.application.dto.sub.PictureDTO;
import com.airbnb.clone.server.listing.application.dto.vo.PriceVO;
import com.airbnb.clone.server.listing.domain.BookingCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Created 5/31/2024 - 4:13 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.application.dto
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Getter
@Setter
public class SaveListingDTO {

    @NotNull
    BookingCategory category;

    @NotNull String location;

    @NotNull @Valid
    ListingInfoDTO infos;

    @NotNull @Valid
    DescriptionDTO description;

    @NotNull @Valid
    PriceVO price;

    @NotNull
    List<PictureDTO> pictures;
}
