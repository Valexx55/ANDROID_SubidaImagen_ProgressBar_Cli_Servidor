package com.example.vale.subidaimagenprogressbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bitmap bitmap1 = null;
        UpLoadImage upLoadImage = null;

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.sundance);

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            TextView textView = (TextView) findViewById(R.id.texto);

            Log.d(getClass().getCanonicalName(), "Lanzando UpLoadImage");

            upLoadImage = new UpLoadImage(this, progressBar, textView);
            upLoadImage.execute(bitmap1);

    }

}