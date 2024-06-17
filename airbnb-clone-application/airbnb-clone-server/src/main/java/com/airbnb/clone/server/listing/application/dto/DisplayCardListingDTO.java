package com.airbnb.clone.server.listing.application.dto;

import com.airbnb.clone.server.listing.application.dto.sub.PictureDTO;
import com.airbnb.clone.server.listing.application.dto.vo.PriceVO;
import com.airbnb.clone.server.listing.domain.BookingCategory;

import java.util.UUID;

/**
 * @Created 6/15/2024 - 10:09 AM on (Saturday)
 * @Package com.airbnb.clone.server.listing.application.dto
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record DisplayCardListingDTO(PriceVO price,
                                    String location,
                                    PictureDTO cover,
                                    BookingCategory bookingCategory,
                                    UUID publicId) {}