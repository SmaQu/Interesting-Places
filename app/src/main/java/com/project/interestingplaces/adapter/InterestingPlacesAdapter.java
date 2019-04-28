package com.project.interestingplaces.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.interestingplaces.R;
import com.project.interestingplaces.model.Country;
import com.project.interestingplaces.util.DateParser;

import java.util.ArrayList;
import java.util.List;

public class InterestingPlacesAdapter extends RecyclerView.Adapter<InterestingPlacesAdapter.ViewHolder> {

    private List<Country> list = new ArrayList<>();
    private OnClickListener<Integer> onClickListener = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_country, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (list.size() > 0) {
            final Country country = list.get(i);

            Glide.with(viewHolder.itemView)
                    .load(country.getPictureUrl())
                    .placeholder(R.drawable.drawable_progress_drawable)
                    .into(viewHolder.countryPictureIv);

            viewHolder.countryNameTv.setText(country.getName());
            viewHolder.dateOfVisit.setText(DateParser.formatDate(country.getDate()));

            if (onClickListener != null) {
                viewHolder.itemView.setOnClickListener(view -> {
                    onClickListener.onClick(country.getId());
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNewAdapterItems(List<Country> newValues) {
        list = newValues;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener<Integer> onClickListener) {
        this.onClickListener = onClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView countryPictureIv;
        private TextView countryNameTv;
        private TextView dateOfVisit;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryPictureIv = itemView.findViewById(R.id.image_country_picture);
            countryNameTv = itemView.findViewById(R.id.text_country_name);
            dateOfVisit = itemView.findViewById(R.id.text_visit_date);
        }
    }
}
