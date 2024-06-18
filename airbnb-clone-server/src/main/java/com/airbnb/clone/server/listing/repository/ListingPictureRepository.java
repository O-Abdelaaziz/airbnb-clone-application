package com.airbnb.clone.server.listing.repository;

import com.airbnb.clone.server.listing.domain.ListingPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created 5/17/2024 - 4:16 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.repository
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Repository
public interface ListingPictureRepository extends JpaRepository<ListingPicture, Long> {
}
