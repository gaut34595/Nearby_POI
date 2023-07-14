package com.example.nearby_poi.Adapters;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby_poi.Model.StepDirectionModel;
import com.example.nearby_poi.databinding.StepLayoutBinding;
import com.google.maps.model.DirectionsStep;

import java.util.List;

public class AdapterV extends RecyclerView.Adapter<AdapterV.ViewHolder> {

    private List<StepDirectionModel> directionModels;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StepLayoutBinding binding = StepLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(directionModels!=null){
            StepDirectionModel stepDirectionModel = directionModels.get(position);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
                holder.binding.textstp.setText(Html.fromHtml(stepDirectionModel.getHtmlInstructions(), Html.FROM_HTML_MODE_LEGACY));
            }else{
                holder.binding.textstp.setText(Html.fromHtml(stepDirectionModel.getHtmlInstructions()));
            }

            holder.binding.texttime.setText(stepDirectionModel.getDuration().getText());
            holder.binding.textdist.setText(stepDirectionModel.getDistance().getText());
        }
    }

    @Override
    public int getItemCount() {
        if(directionModels!=null){
            return directionModels.size();
        }else{
            return 0;
        }
    }

    public void setDirectionStepModels(List<StepDirectionModel> directionStepModels) {
        this.directionModels= directionModels;
        notifyDataSetChanged();

    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private StepLayoutBinding binding;

        public ViewHolder(@NonNull StepLayoutBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
    }
}
