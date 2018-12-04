package marioara.rus.placesapp.ui.map;

import android.app.Application;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import marioara.rus.placesapp.R;
import marioara.rus.placesapp.data.entity.PlacesStatus;
import marioara.rus.placesapp.data.model.remote.RemoteLocationModel;
import marioara.rus.placesapp.domain.PlacesRepository;

/**
 * Created by Marioara Rus on 2018-11-28.
 */
public class MapsViewModel extends AndroidViewModel {
    private MutableLiveData<Location> mMutableLiveData;
    private PlacesRepository mPlacesRepository;


    public MapsViewModel(@NonNull Application application) {
        super(application);
        mMutableLiveData = new MutableLiveData<>();
        mPlacesRepository = new PlacesRepository(application.getString(R.string.google_maps_key));
    }

    /**
     * Request user's location
     *
     * @return a LiveData on wich to observe the result
     */
    public LiveData<Location> getCurrentLocation() {
        RemoteLocationModel.INSTANCE.getCurrentLocation(getApplication(), new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    mMutableLiveData.setValue(task.getResult());
                } else {
                    Log.d("onComplete", task.toString());
                }
            }
        });
        return mMutableLiveData;
    }

    public LiveData<PlacesStatus> getPlaces(double lat, double lng) {
        return mPlacesRepository.getPlaces(lat, lng);
    }
}
