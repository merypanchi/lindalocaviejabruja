package com.example.lindalocaviejabruja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    float X,Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Toca el mapa para agregar tu imagen", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton feliz = findViewById(R.id.floatingActionButton);
        feliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        FloatingActionButton neutral = findViewById(R.id.floatingActionButton2);
        neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        FloatingActionButton triste = findViewById(R.id.floatingActionButton3);
        triste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        ImageView mapa=findViewById(R.id.imageView);
        mapa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent camera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                X=event.getX();
                Y=event.getY();
                startActivityForResult(camera,0);
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==0 && resultCode== RESULT_OK){
            Log.e("appCool", "coordenate:" + X+";"+Y);
            Bitmap image = (Bitmap)data.getExtras().get("data");
            ConstraintLayout layout= findViewById(R.id.myLayout);
            ImageView imagev = new ImageView(MainActivity.this);
            imagev.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            imagev.setImageBitmap(image);
            imagev.setY(Y);
            imagev.setX(X);
            layout.addView(imagev);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBackPressed() {
        FloatingActionButton feliz = findViewById(R.id.floatingActionButton);
        feliz.setVisibility(View.VISIBLE);
        FloatingActionButton neutral = findViewById(R.id.floatingActionButton2);
        neutral.setVisibility(View.VISIBLE);
        FloatingActionButton triste = findViewById(R.id.floatingActionButton3);
        triste.setVisibility(View.VISIBLE);
    }
}
