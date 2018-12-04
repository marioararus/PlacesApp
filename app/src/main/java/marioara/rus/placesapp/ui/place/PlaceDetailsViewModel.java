package marioara.rus.placesapp.ui.place;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import marioara.rus.placesapp.R;
import marioara.rus.placesapp.data.entity.PlaceDetailsStatus;
import marioara.rus.placesapp.domain.PlacesRepository;

public class PlaceDetailsViewModel extends AndroidViewModel {
    private PlacesRepository mPlacesRepository;

    public PlaceDetailsViewModel(Application application) {
        super(application);
        mPlacesRepository = new PlacesRepository(application.getString(R.string.google_maps_key));
    }

    public LiveData<PlaceDetailsStatus> getPlaceDetails(String placeId) {
        return mPlacesRepository.getPlaceDetails(placeId);
    }

}
