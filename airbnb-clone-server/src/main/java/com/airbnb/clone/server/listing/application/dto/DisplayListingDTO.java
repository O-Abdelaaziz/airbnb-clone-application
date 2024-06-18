package com.airbnb.clone.server.listing.application.dto;

import com.airbnb.clone.server.listing.application.dto.sub.DescriptionDTO;
import com.airbnb.clone.server.listing.application.dto.sub.LandlordListingDTO;
import com.airbnb.clone.server.listing.application.dto.sub.ListingInfoDTO;
import com.airbnb.clone.server.listing.application.dto.sub.PictureDTO;
import com.airbnb.clone.server.listing.application.dto.vo.PriceVO;
import com.airbnb.clone.server.listing.domain.BookingCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Created 6/17/2024 - 2:37 PM on (Monday)
 * @Package com.airbnb.clone.server.listing.application.dto
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Getter
@Setter
public class DisplayListingDTO {
    private DescriptionDTO description;
    private List<PictureDTO> pictures;
    private ListingInfoDTO infos;
    private PriceVO price;
    private BookingCategory category;
    private String location;
    private LandlordListingDTO landlord;
}
