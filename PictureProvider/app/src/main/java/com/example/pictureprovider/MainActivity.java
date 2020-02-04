package com.example.pictureprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;






import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button BackButton;
    TextView DebugText;
    ImageView imgView;
    private static int RESULT_LOAD_IMAGE = 1;
    String FileURI = "undefined";
    Uri selectedImageUri;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //InitApp();
        BackButton = findViewById(R.id.BackButton);
        DebugText = findViewById(R.id.DebugText);
        imgView = findViewById(R.id.imageView);

        DebugText.setText("Choose file to send");

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String stringFromOtherApp = extras.getString("FileURI");
            DebugText.setText(stringFromOtherApp);
        }
   }


   void InitApp(){
       BackButton = findViewById(R.id.BackButton);
       DebugText = findViewById(R.id.DebugText);
   }


    public void BackToMainActivity(View v){

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.mainselfieartwork");

        if (launchIntent != null)
        {
            //launchIntent.putExtra("imageUri", selectedImageUri);
            //launchIntent.putExtra("imageUri", selectedImageUri.toString());
            //launchIntent.putExtra("imageUri", selectedImageUri.toString());
            launchIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            launchIntent.putExtra("imagePath", selectedImageUri);
            launchIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            launchIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            launchIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            //launchIntent.setData(selectedImageUri);
            startActivity(launchIntent);

            //this.finish();
        }
}

    public void OpenGallery(View view) {
        Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        gallery.setType("image/*");
        startActivityForResult(gallery, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult called");
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK){

            selectedImageUri = data.getData( );



           // File imageFile = new File(getRealPathFromURI(selectedImageURI));
            //path = imageFile.getAbsolutePath();

            //String realURI = RealPathUtil.getRealPath(getApplicationContext(), imageUri) ;  // getRealPathFromURI2(imageUri);
            imgView.setImageURI(selectedImageUri);



            DebugText.setText("selectedImageUri: " + selectedImageUri);

        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}









































