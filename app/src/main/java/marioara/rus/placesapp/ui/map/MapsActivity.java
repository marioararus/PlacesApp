package marioara.rus.placesapp.ui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import marioara.rus.placesapp.R;
import marioara.rus.placesapp.data.entity.PlacesStatus;
import marioara.rus.placesapp.ui.place.PlaceDetailsActivity;

/**
 * Created by Bogdan Roatis on 2018-11-30.
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, PlacesAdapter.OnPlaceClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap mMap;
    private MapsViewModel mMapsViewModel;
    private GeoDataClient geoDataClient;
    private PlaceDetectionClient placeDetectionClient;

    private TextView tvError;
    private RecyclerView rvPlaces;
    private PlacesAdapter mPlacesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mMapsViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tvError = findViewById(R.id.tv_error);
        rvPlaces = findViewById(R.id.rv_places);
        mPlacesAdapter = new PlacesAdapter();
        rvPlaces.setAdapter(mPlacesAdapter);
        mPlacesAdapter.setOnPlaceClickListener(this);
        rvPlaces.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvPlaces.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

                outRect.right = getResources().getDimensionPixelOffset(R.dimen.place_divider);

                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = getResources().getDimensionPixelOffset(R.dimen.place_divider);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Check if the permissions are granted
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // if they are not granted, request them
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // get the current location
            getCurrentLocation();
        }

    }

    /**
     * Get the user's longitude and latitude
     * Focus the camera on the user's location on the map
     */
    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        mMap.setMyLocationEnabled(true);
        mMapsViewModel.getCurrentLocation().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                LatLng currentUserLocationLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera((CameraUpdateFactory.newLatLngZoom(currentUserLocationLatLng, 10f)));
                mMapsViewModel.getPlaces(currentUserLocationLatLng.latitude, currentUserLocationLatLng.longitude)
                        .observe(MapsActivity.this, new Observer<PlacesStatus>() {
                            @Override
                            public void onChanged(PlacesStatus placesStatus) {
                                if (placesStatus.getPlaces() != null && !placesStatus.getPlaces().isEmpty()) {
                                    tvError.setVisibility(View.GONE);
                                    rvPlaces.setVisibility(View.VISIBLE);
                                    mPlacesAdapter.setPlaces(placesStatus.getPlaces());
                                } else if (placesStatus.getErrorMessage() != null) {
                                    tvError.setText(placesStatus.getErrorMessage());
                                    tvError.setVisibility(View.VISIBLE);
                                    rvPlaces.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });
    }


    @Override
    public void onPlaceClick(String id) {
        PlaceDetailsActivity.start(id, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "We cannot show your location", Toast.LENGTH_LONG).show();
            }
        }
    }
}
