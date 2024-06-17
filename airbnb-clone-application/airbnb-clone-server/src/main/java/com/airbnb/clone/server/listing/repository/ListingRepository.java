package com.airbnb.clone.server.listing.repository;

import com.airbnb.clone.server.listing.domain.BookingCategory;
import com.airbnb.clone.server.listing.domain.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

/**
 * @Created 5/17/2024 - 4:14 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.repository
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    @Query("SELECT listing FROM Listing listing LEFT JOIN FETCH listing.pictures picture" +
            " WHERE listing.landlordPublicId = :landlordPublicId AND picture.isCover = true")
    List<Listing> findAllByLandlordPublicIdFetchCoverPicture(UUID landlordPublicId);

    long deleteByPublicIdAndLandlordPublicId(UUID publicId, UUID landlordPublicId);

    @Query("SELECT listing from Listing listing LEFT JOIN FETCH listing.pictures picture" +
            " WHERE picture.isCover = true AND listing.bookingCategory = :bookingCategory")
    Page<Listing> findAllByBookingCategoryWithCoverOnly(Pageable pageable, BookingCategory bookingCategory);

    @Query("SELECT listing from Listing listing LEFT JOIN FETCH listing.pictures picture" +
            " WHERE picture.isCover = true")
    Page<Listing> findAllWithCoverOnly(Pageable pageable);

    Optional<Listing> findByPublicId(UUID publicId);
}
