package marioara.rus.placesapp.ui;

import android.app.Application;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import marioara.rus.placesapp.data.model.LocationModel;

/**
 * Created by Marioara Rus on 2018-11-28.
 */
public class MapsViewModel extends AndroidViewModel implements OnCompleteListener<Location> {
    private MutableLiveData<Location> mMutableLiveData;

    public MapsViewModel(@NonNull Application application) {
        super(application);
        mMutableLiveData = new MutableLiveData<>();
    }

    /**
     * Request user's location
     *
     * @return a LiveData on wich to observe the result
     */
    public LiveData<Location> getCurrentLocation() {
        LocationModel.INSTANCE.getCurrentLocation(getApplication(), this);
        return mMutableLiveData;
    }


    @Override
    public void onComplete(@NonNull Task<Location> task) {
        if (task.isSuccessful()) {
            mMutableLiveData.setValue(task.getResult());
        } else {
            Log.d("onComplete", task.toString());
        }
    }
}
