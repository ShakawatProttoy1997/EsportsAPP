package com.example.signupandloginwithfirebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
//import com.denzcoskun.imageslider.constants.ScaleTypes;


public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home,container,false);
        //ImageSlider imageSlider = view.findViewById(R.id.image_slider);
       // List<SlideModel>slideModels = new ArrayList<>();
        //for offer
       // slideModels.add(new SlideModel(R.mipmap.gamehub1), "offer on T-shirts", ImageView.ScaleType.CENTER_CROP);
      //  imageSlider.setImageList(slideModels);
        return view;
    }
}