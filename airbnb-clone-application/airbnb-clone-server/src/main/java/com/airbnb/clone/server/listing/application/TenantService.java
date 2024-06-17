package com.airbnb.clone.server.listing.application;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.airbnb.clone.server.listing.application.dto.DisplayCardListingDTO;
import com.airbnb.clone.server.listing.application.dto.SaveListingDTO;
import com.airbnb.clone.server.listing.domain.BookingCategory;
import com.airbnb.clone.server.listing.domain.Listing;
import com.airbnb.clone.server.listing.mapper.ListingMapper;
import com.airbnb.clone.server.listing.repository.ListingRepository;
import com.airbnb.clone.server.sharedkernel.service.State;
import com.airbnb.clone.server.user.application.UserService;

/**
 * @Created 6/17/2024 - 11:51 AM on (Monday)
 * @Package com.airbnb.clone.server.listing.application
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Service
public class TenantService {
    private final ListingRepository listingRepository;

    private final ListingMapper listingMapper;

    private final UserService userService;


    public TenantService(ListingRepository listingRepository, ListingMapper listingMapper, UserService userService) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
        this.userService = userService;
    }

    public Page<DisplayCardListingDTO> getAllByCategory(Pageable pageable, BookingCategory category) {
        Page<Listing> allOrBookingCategory;
        if (category == BookingCategory.ALL) {
            allOrBookingCategory = listingRepository.findAllWithCoverOnly(pageable);
        } else {
            allOrBookingCategory = listingRepository.findAllByBookingCategoryWithCoverOnly(pageable, category);
        }

        return allOrBookingCategory.map(listingMapper::listingToDisplayCardListingDTO);
    }
}
