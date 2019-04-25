package com.project.interestingplaces.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.interestingplaces.R;
import com.project.interestingplaces.model.Country;

import java.util.ArrayList;
import java.util.List;

public class InterestingPlacesAdapter extends RecyclerView.Adapter<InterestingPlacesAdapter.ViewHolder> {

    private List<Country> list = new ArrayList<>();
    private OnClickListener<Country> onClickListener = null;

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


            if (onClickListener != null) {
                onClickListener.onClick(country);
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

    public void setOnClickListener(OnClickListener<Country> onClickListener) {
        this.onClickListener = onClickListener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
