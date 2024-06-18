package com.airbnb.clone.server.listing.domain;

import com.airbnb.clone.server.sharedkernel.domain.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Created 5/17/2024 - 4:08 PM on (Friday)
 * @Package com.airbnb.clone.server.listing.domain
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Getter
@Setter
@Entity
@Table(name = "listing_picture")
public class ListingPicture extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listingPictureSequenceGenerator")
    @SequenceGenerator(name = "listingPictureSequenceGenerator", sequenceName = "listing_picture_generator", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    @Column(name = "file_content_type")
    private String fileContentType;

    @Column(name = "is_cover")
    private boolean isCover;

    @ManyToOne
    @JoinColumn(name = "listing_fk", referencedColumnName = "id")
    private Listing listing;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListingPicture that = (ListingPicture) o;
        return isCover == that.isCover && Objects.deepEquals(file, that.file) && Objects.equals(fileContentType, that.fileContentType) && Objects.equals(listing, that.listing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(file), fileContentType, isCover, listing);
    }
}
