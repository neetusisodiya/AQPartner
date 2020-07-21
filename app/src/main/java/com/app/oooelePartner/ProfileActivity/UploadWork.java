package com.app.oooelePartner.ProfileActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.oooelePartner.Activity.MainActivity;
import com.app.oooelePartner.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UploadWork extends AppCompatActivity implements View.OnClickListener {


    public static final String UPLOAD_URL = "";
    public static final String UPLOAD_KEY = "image";
ImageView img_back;
    private int PICK_IMAGE_REQUEST = 999;
    private TextView buttonChoose;
    private Button buttonUpload;
    private ImageView imageView;
    private Bitmap bitmap;
    private Uri filePath;
    String encodeImage="";

    String uploadeUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_work);

        init();

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(UploadWork.this,new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE},
                        PICK_IMAGE_REQUEST
                );
            }
        });
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StringRequest stringRequest=new StringRequest(Request.Method.POST, uploadeUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(UploadWork.this, response, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(UploadWork.this, "Error" +error, Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> map=new HashMap<>();

                        String imagData=imageToString(bitmap);

                        map.put("image",imagData);

                        return map;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(UploadWork.this);
                requestQueue.add(stringRequest);

            }
        });
    }


    void init() {
        buttonChoose = findViewById(R.id.select);
        buttonUpload = findViewById(R.id.submit_img);
        imageView = findViewById(R.id.image);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==PICK_IMAGE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            } else {
                Toast.makeText(this, "Dont have Permission", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data !=null){

            Uri filepath=data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] ImageByte=outputStream.toByteArray();
        encodeImage = Base64.encodeToString(ImageByte,Base64.DEFAULT);
        return encodeImage;
    }

    @Override
    public void onClick(View v) {
        if (v==img_back){
            onBackPressed();
        }
    }
}

