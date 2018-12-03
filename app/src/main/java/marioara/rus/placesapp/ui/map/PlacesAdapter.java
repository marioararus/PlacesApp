package marioara.rus.placesapp.ui.map;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import marioara.rus.placesapp.R;
import marioara.rus.placesapp.data.entity.Place;

/**
 * Created by Bogdan Roatis on 2018-11-30.
 */
public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {
    private List<Place> places = new ArrayList<>();
    private OnPlaceClickListener mOnPlaceClickListener;


    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        final Place place = places.get(position);

        if (place.getPhotos() != null && !place.getPhotos().isEmpty()) {
            holder.setImage(place.getPhotos().get(0));
        }

        holder.setText(place.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnPlaceClickListener != null) {
                    mOnPlaceClickListener.onPlaceClick(place.getPlaceId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public interface OnPlaceClickListener {
        public void onPlaceClick(String id);
    }

    public void setOnPlaceClickListener(OnPlaceClickListener onPlaceClickListener) {
        mOnPlaceClickListener = onPlaceClickListener;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
        notifyDataSetChanged();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPlaceImage;
        private TextView tvPlaceName;
        private ProgressBar progressBar;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPlaceImage = itemView.findViewById(R.id.iv_place_image);
            tvPlaceName = itemView.findViewById(R.id.tv_place_name);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        public void setText(String text) {
            tvPlaceName.setText(text);
        }

        public void setImage(String imageUrl) {

            Glide.with(itemView.getContext()).load(imageUrl).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(ivPlaceImage);

        }
    }
}
