import {NewListingInfo} from "../../landlord/model/listing.model";
import {BookedDatesDTOFromServer} from "../model/booking.model";

export interface Search {
  location: string,
  dates: BookedDatesDTOFromServer,
  infos: NewListingInfo
}
