package com.airbnb.clone.server.listing.application;

import com.airbnb.clone.server.listing.application.dto.CreatedListingDTO;
import com.airbnb.clone.server.listing.application.dto.SaveListingDTO;
import com.airbnb.clone.server.listing.domain.Listing;
import com.airbnb.clone.server.listing.mapper.ListingMapper;
import com.airbnb.clone.server.listing.repository.ListingRepository;
import com.airbnb.clone.server.user.application.Auth0Service;
import com.airbnb.clone.server.user.application.UserService;
import com.airbnb.clone.server.user.application.dto.ReadUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Created 5/31/2024 - 3:49 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.application
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Service
public class LandlordService {
    private final ListingRepository listingRepository;
    private final PictureService pictureService;
    private final ListingMapper listingMapper;
    private final UserService userService;
    private final Auth0Service auth0Service;

    @Autowired
    public LandlordService(ListingRepository listingRepository, PictureService pictureService, ListingMapper listingMapper, UserService userService, Auth0Service auth0Service) {
        this.listingRepository = listingRepository;
        this.pictureService = pictureService;
        this.listingMapper = listingMapper;
        this.userService = userService;
        this.auth0Service = auth0Service;
    }

    public CreatedListingDTO create(SaveListingDTO saveListingDTO) {
        Listing newListing = listingMapper.saveListingDTOToListing(saveListingDTO);

        ReadUserDTO userConnected = userService.getAuthenticatedUserFromSecurityContext();
        newListing.setLandlordPublicId(userConnected.publicId());

        Listing savedListing = listingRepository.saveAndFlush(newListing);

        pictureService.saveAll(saveListingDTO.getPictures(), savedListing);

        auth0Service.addLandlordRoleToUser(userConnected);

        return listingMapper.listingToCreatedListingDTO(savedListing);
    }
}
