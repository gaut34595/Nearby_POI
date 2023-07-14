package com.example.nearby_poi.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby_poi.Model.GoogleModel;
import com.example.nearby_poi.R;
import com.example.nearby_poi.databinding.PlaceHolderItemLayoutBinding;

import java.util.Date;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<GoogleModel> googleModelList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlaceHolderItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.place_holder_item_layout,parent,false);
        return new ViewHolder(binding);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(googleModelList!=null){
            GoogleModel googleModel = googleModelList.get(position);
            holder.binding.setGoogleModel(googleModel);
        }
    }

    @Override
    public int getItemCount() {
        if(googleModelList!=null){
            return googleModelList.size();
        }else{
            return 0;
        }
    }

    public void setGoogleModelList(List<GoogleModel> googleModelList) {
        this.googleModelList = googleModelList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private PlaceHolderItemLayoutBinding binding;
        public ViewHolder(@NonNull PlaceHolderItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
