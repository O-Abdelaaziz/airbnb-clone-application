package com.airbnb.clone.server.listing.application;

import com.airbnb.clone.server.booking.application.BookingService;
import com.airbnb.clone.server.listing.application.dto.DisplayListingDTO;
import com.airbnb.clone.server.listing.application.dto.SearchDTO;
import com.airbnb.clone.server.listing.application.dto.sub.LandlordListingDTO;
import com.airbnb.clone.server.user.application.dto.ReadUserDTO;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.airbnb.clone.server.listing.application.dto.DisplayCardListingDTO;
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

    private final BookingService bookingService;

    public TenantService(ListingRepository listingRepository, ListingMapper listingMapper, UserService userService, BookingService bookingService) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
        this.userService = userService;
        this.bookingService = bookingService;
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

    @Transactional(readOnly = true)
    public State<DisplayListingDTO, String> getOne(UUID publicId) {
        Optional<Listing> listingByPublicIdOpt = listingRepository.findByPublicId(publicId);

        if (listingByPublicIdOpt.isEmpty()) {
            return State.<DisplayListingDTO, String>builder()
                    .forError(String.format("Listing doesn't exist for publicId: %s", publicId));
        }

        DisplayListingDTO displayListingDTO = listingMapper.listingToDisplayListingDTO(listingByPublicIdOpt.get());

        ReadUserDTO readUserDTO = userService.getByPublicId(listingByPublicIdOpt.get().getLandlordPublicId()).orElseThrow();
        LandlordListingDTO landlordListingDTO = new LandlordListingDTO(readUserDTO.firstName(), readUserDTO.imageUrl());
        displayListingDTO.setLandlord(landlordListingDTO);

        return State.<DisplayListingDTO, String>builder().forSuccess(displayListingDTO);
    }

    @Transactional(readOnly = true)
    public Page<DisplayCardListingDTO> search(Pageable pageable, SearchDTO newSearch) {

        Page<Listing> allMatchedListings = listingRepository.findAllByLocationAndBathroomsAndBedroomsAndGuestsAndBeds(pageable, newSearch.location(),
                newSearch.infos().baths().value(),
                newSearch.infos().bedrooms().value(),
                newSearch.infos().guests().value(),
                newSearch.infos().beds().value());

        List<UUID> listingUUIDs = allMatchedListings.stream().map(Listing::getPublicId).toList();

        List<UUID> bookingUUIDs = bookingService.getBookingMatchByListingIdsAndBookedDate(listingUUIDs, newSearch.dates());

        List<DisplayCardListingDTO> listingsNotBooked = allMatchedListings.stream().filter(listing -> !bookingUUIDs.contains(listing.getPublicId()))
                .map(listingMapper::listingToDisplayCardListingDTO)
                .toList();

        return new PageImpl<>(listingsNotBooked, pageable, listingsNotBooked.size());
    }

}
