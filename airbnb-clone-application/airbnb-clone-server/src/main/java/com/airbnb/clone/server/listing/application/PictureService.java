package com.airbnb.clone.server.listing.application;

import com.airbnb.clone.server.listing.application.dto.sub.PictureDTO;
import com.airbnb.clone.server.listing.domain.Listing;
import com.airbnb.clone.server.listing.domain.ListingPicture;
import com.airbnb.clone.server.listing.mapper.ListingPictureMapper;
import com.airbnb.clone.server.listing.repository.ListingPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Created 5/31/2024 - 3:52 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.application
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Service
public class PictureService {
    private final ListingPictureRepository listingPictureRepository;

    private final ListingPictureMapper listingPictureMapper;

    public PictureService(ListingPictureRepository listingPictureRepository, ListingPictureMapper listingPictureMapper) {
        this.listingPictureRepository = listingPictureRepository;
        this.listingPictureMapper = listingPictureMapper;
    }

    public List<PictureDTO> saveAll(List<PictureDTO> pictures, Listing listing) {
        Set<ListingPicture> listingPictures = listingPictureMapper.pictureDTOsToListingPictures(pictures);

        boolean isFirst = true;

        for (ListingPicture listingPicture : listingPictures) {
            listingPicture.setCover(isFirst);
            listingPicture.setListing(listing);
            isFirst = false;
        }

        listingPictureRepository.saveAll(listingPictures);
        return listingPictureMapper.listingPictureToPictureDTO(listingPictures.stream().toList());
    }
}
