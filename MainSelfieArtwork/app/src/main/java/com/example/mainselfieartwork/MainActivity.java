package com.example.mainselfieartwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    Button BackButton;
    ImageView imgView;
    TextView debugText;

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackButton = findViewById(R.id.LoadPicture);
        imgView = findViewById(R.id.imageView);
        debugText = findViewById(R.id.textView);

        //String imagePath = getIntent().getStringExtra("imagePath");
        //Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        //imgView.setImageDrawable(bitmap);





        // get Uri
        //Uri uri = getIntent().getData();

        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
           //Uri myUri = Uri.parse(extras.getString("imageUri"));


            String test = extras.getString("imageUri");
            Uri myUri = Uri.parse(test);

            imgView.setImageURI(myUri);
            debugText.setText(test);
        }else{
            debugText.setText("no intent");
        }*/
        // decode bitmap from Uri

       Bundle extras = getIntent().getExtras();
        if (extras != null)
        {

           /* Uri imageUri = (Uri) getIntent().getParcelableExtra(Intent.EXTRA_STREAM);
            if (imageUri != null) {
                // Update UI to reflect image being shared
                imgView.setImageURI(imageUri);
            }*/


            Uri uri = getIntent().getParcelableExtra("imagePath");
            //String image_path= getIntent().getStringExtra("imagePath");
            //Uri fileUri = Uri.parse(image_path);


            //Uri uri = Uri.parse(extras.getString("imagePath"));

            if (uri != null) {

                debugText.setText(uri.toString());

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                intent.putExtra("imageUri", uri.toString());
                Bundle bundle = intent.getExtras();

                //startActivityForResult(intent, RESULT_LOAD_IMAGE);
                Uri uriConverted = Uri.parse(bundle.getString("imageUri"));

                imgView.setImageURI(uriConverted);
                debugText.setText(uriConverted.toString());



            }
        }else{
            debugText.setText("no intent");
        }
    }

    public void UseURI()
    {
        Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        gallery.setType("image/*");
        startActivityForResult(gallery, RESULT_LOAD_IMAGE);
    }

    public void InitApp() {
        BackButton = findViewById(R.id.LoadPicture);
        imgView = findViewById(R.id.imageView);
        debugText = findViewById(R.id.textView);
    }

    public void OpenImagePicker(View v){
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.pictureprovider");
        if (launchIntent != null)
        {
            launchIntent.putExtra("FileURI", "hallo");
            startActivity(launchIntent); //null pointer check in case package name was not found
        }
    }
}
