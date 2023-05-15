package com.example.myapplication;



import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {

        private ImageView imageView;
        private Button zoomInButton;
        private Button zoomOutButton;
        private Button adjustColorsButton;

        private float scaleFactor = 1.0f;
        private float[] colorMatrixArray = {
                2, 0, 0, 0, -25,
                0, 2, 0, 0, -25,
                0, 0, 2, 0, -25,
                0, 0, 0, 1, 0
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            imageView = findViewById(R.id.imageView);
            zoomInButton = findViewById(R.id.zoomInButton);
            zoomOutButton = findViewById(R.id.zoomOutButton);
            adjustColorsButton = findViewById(R.id.adjustColorsButton);
            // 获取恢复颜色按钮的引用
            Button resetColorsButton = findViewById(R.id.resetColorsButton);
            resetColorsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetImageColors();
                }
            });


            zoomInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scaleImage(1.2f);
                }
            });

            zoomOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scaleImage(0.8f);
                }
            });

            adjustColorsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adjustImageColors();
                }
            });
        }

        private void scaleImage(float scale) {
            scaleFactor *= scale;
            imageView.setScaleX(scaleFactor);
            imageView.setScaleY(scaleFactor);
        }

        private void adjustImageColors() {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            Bitmap adjustedBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

            ColorMatrix colorMatrix = new ColorMatrix(colorMatrixArray);
            ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);

            imageView.setColorFilter(colorFilter);
        }
        private void resetImageColors() {
            imageView.clearColorFilter();
        }

    }
