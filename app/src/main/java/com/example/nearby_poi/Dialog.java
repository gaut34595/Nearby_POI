package com.example.nearby_poi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.nearby_poi.Fragments.HomeFragment;
import com.example.nearby_poi.databinding.DialogLayoutBinding;

public class Dialog{
    private Activity activity;
    private AlertDialog alertDialog;

    public Dialog(Activity activity) {
        this.activity = activity;
    }

    public void startLoading() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        DialogLayoutBinding binding = DialogLayoutBinding.inflate(LayoutInflater.from(activity), null, false);
        builder.setView(binding.getRoot());
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void stopLoading() {
        alertDialog.dismiss();
    }

    }
    
