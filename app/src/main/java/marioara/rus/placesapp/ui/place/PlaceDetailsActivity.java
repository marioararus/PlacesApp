package marioara.rus.placesapp.ui.place;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import marioara.rus.placesapp.R;
import marioara.rus.placesapp.data.entity.PlaceDetails;
import marioara.rus.placesapp.data.entity.PlaceDetailsStatus;

public class PlaceDetailsActivity extends AppCompatActivity {
    private static final String PLACE_ID_KEY = "place.id.key";
    private ImageView ivPlace;
    private TextView tvPlaceName;
    private TextView tvPlaceAddress;
    private TextView tvError;
    private PlaceDetailsViewModel mPlaceDetailsViewModel;

    public static void start(String placeId, Context context) {
        Intent intent = new Intent(context, PlaceDetailsActivity.class);
        intent.putExtra(PLACE_ID_KEY, placeId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        ivPlace = findViewById(R.id.iv_place_details);
        tvPlaceName = findViewById(R.id.tv_place_name);
        tvPlaceAddress = findViewById(R.id.tv_place_address);
        tvError = findViewById(R.id.tv_error);

        mPlaceDetailsViewModel = ViewModelProviders.of(this).get(PlaceDetailsViewModel.class);
        if (getIntent() != null && getIntent().getExtras() != null) {
            mPlaceDetailsViewModel.getPlaceDetails(getIntent().getStringExtra(PLACE_ID_KEY)).observe(this, new Observer<PlaceDetailsStatus>() {
                @Override
                public void onChanged(PlaceDetailsStatus placeDetailsStatus) {
                    if (placeDetailsStatus.getPlaceDetails() != null) {
                        PlaceDetails placeDetails = placeDetailsStatus.getPlaceDetails();
                        tvPlaceName.setText(placeDetails.getName());
                        tvPlaceAddress.setText(placeDetails.getAddress());
                        if (placeDetails.getPhotos() != null && !placeDetails.getPhotos().isEmpty()) {
                            Glide.with(PlaceDetailsActivity.this).load(placeDetails.getPhotos().get(0)).into(ivPlace);
                        }
                    } else if (placeDetailsStatus.getErrorMessage() != null) {
                        tvError.setText(placeDetailsStatus.getErrorMessage());
                    }
                }
            });
        }

    }
}
