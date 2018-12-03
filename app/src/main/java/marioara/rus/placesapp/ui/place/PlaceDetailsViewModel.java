package marioara.rus.placesapp.ui.place;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import marioara.rus.placesapp.data.entity.PlaceDetailsStatus;
import marioara.rus.placesapp.domain.PlacesRepository;

public class PlaceDetailsViewModel extends ViewModel {
    private PlacesRepository mPlacesRepository;

    public PlaceDetailsViewModel() {
        mPlacesRepository = new PlacesRepository();
    }

    public LiveData<PlaceDetailsStatus> getPlaceDetails(String placeId) {
        return mPlacesRepository.getPlaceDetails(placeId);
    }

}
