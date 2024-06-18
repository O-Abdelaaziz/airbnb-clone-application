package com.airbnb.clone.server.listing.presentation;

import com.airbnb.clone.server.listing.application.TenantService;
import com.airbnb.clone.server.listing.application.dto.DisplayCardListingDTO;
import com.airbnb.clone.server.listing.application.dto.DisplayListingDTO;
import com.airbnb.clone.server.listing.application.dto.SearchDTO;
import com.airbnb.clone.server.listing.domain.BookingCategory;
import com.airbnb.clone.server.sharedkernel.service.State;
import com.airbnb.clone.server.sharedkernel.service.StatusNotification;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Created 6/17/2024 - 11:54 AM on (Monday)
 * @Package com.airbnb.clone.server.listing.presentation
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@RestController
@RequestMapping("/api/tenant-listing")
public class TenantResource {

    private final TenantService tenantService;


    public TenantResource(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/get-all-by-category")
    public ResponseEntity<Page<DisplayCardListingDTO>> findAllByBookingCategory(Pageable pageable, @RequestParam BookingCategory category) {
        return ResponseEntity.ok(tenantService.getAllByCategory(pageable, category));
    }


    @GetMapping("/get-one")
    public ResponseEntity<DisplayListingDTO> getOne(@RequestParam UUID publicId) {
        State<DisplayListingDTO, String> displayListingState = tenantService.getOne(publicId);
        if (displayListingState.getStatus().equals(StatusNotification.OK)) {
            return ResponseEntity.ok(displayListingState.getValue());
        } else {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, displayListingState.getError());
            return ResponseEntity.of(problemDetail).build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<Page<DisplayCardListingDTO>> search(Pageable pageable,
                                                              @Valid @RequestBody SearchDTO searchDTO) {
        return ResponseEntity.ok(tenantService.search(pageable, searchDTO));
    }
}
