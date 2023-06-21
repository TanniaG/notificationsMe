package com.example.notificationsme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add (new SlideModel(R.drawable.officebro , ScaleTypes.FIT));
        slideModels.add (new SlideModel(R.drawable.officerafiki , ScaleTypes.FIT));
        slideModels.add (new SlideModel(R.drawable.officework , ScaleTypes.FIT));

        imageSlider.setImageList(slideModels ,ScaleTypes.FIT);

        Button button = findViewById(R.id.buttonGet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (MainActivity.this,SignUp.class);
                startActivity(intent);

            }
        });


    }




}