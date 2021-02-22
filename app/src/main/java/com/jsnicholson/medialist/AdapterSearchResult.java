package com.jsnicholson.medialist;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jsnicholson.medialist.database.DatabaseConstants;
import com.jsnicholson.medialist.database.MediaSearchResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// a fairly standard recycler view adapter
public class AdapterSearchResult extends RecyclerView.Adapter<AdapterSearchResult.ViewHolderSearchResult>{

    private final Context m_context;
    private LayoutInflater inflater;
    private ClickListenerSearchResult clickListener;
    private List<MediaSearchResult> data;

    public AdapterSearchResult(Context context, ClickListenerSearchResult clickListener) {
        this.m_context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolderSearchResult onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_search_result, parent, false);
        return new ViewHolderSearchResult(view, clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolderSearchResult holder, int position) {
        holder.Bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // set the internal data based on a list of recipes, we deconstruct those later
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SetData(List<MediaSearchResult> newData) {
        if (data != null) {
            data.clear();
            data.addAll(newData);
            // sort by popularity
            Collections.sort(data, Collections.reverseOrder());
            notifyDataSetChanged();
        } else {
            data = newData;
        }
    }

    public void ClearData() {
        data.clear();
        notifyDataSetChanged();
    }

    // view holder, limited number of these are bound to data as it passes
    public class ViewHolderSearchResult extends RecyclerView.ViewHolder implements View.OnClickListener {
        ClickListenerSearchResult clickListener;
        MediaSearchResult m_media;
        TextView textTitle;
        Chip chipMediaType;
        ChipGroup chipGroup;

        ViewHolderSearchResult(View itemView, ClickListenerSearchResult clickListener) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            chipGroup = itemView.findViewById(R.id.chipGroup);
            chipMediaType = itemView.findViewById(R.id.chipMediaType);

            // set up a click listener
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.OnMediaSearchResultClicked(m_media);
        }

        void Bind(final MediaSearchResult result) {
            if (result != null) {
                m_media = result;

                String str = String.format("%s (%s)", result.title, (result.released != 0) ? result.released : "Unreleased");

                String strMediaType = DatabaseConstants.MAP_MEDIA_TYPE_TO_STRING.get(result.type);

                textTitle.setText(str);

                chipGroup.removeAllViews();

                Chip chipMedia = (Chip) inflater.inflate(R.layout.chip_media_type, null);
                chipMedia.setText(strMediaType);
                chipMedia.setId(View.generateViewId());
                chipGroup.addView(chipMedia);

                if(m_media.isAnime) {
                    Chip chipAnime = (Chip) inflater.inflate(R.layout.chip_media_type, null);
                    chipAnime.setText(R.string.media_type_anime);
                    chipAnime.setId(View.generateViewId());
                    chipGroup.addView(chipAnime);
                }
            }
        }
    }

    public interface ClickListenerSearchResult {
        void OnMediaSearchResultClicked(MediaSearchResult exercise);
    }
}