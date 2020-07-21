package com.app.oooelePartner.ProfileActivity;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.google.gson.JsonIOException;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.oooelePartner.Utill.CommonUtils.getDataColumn;
import static com.app.oooelePartner.Utill.CommonUtils.isDownloadsDocument;
import static com.app.oooelePartner.Utill.CommonUtils.isExternalStorageDocument;
import static com.app.oooelePartner.Utill.CommonUtils.isGooglePhotosUri;
import static com.app.oooelePartner.Utill.CommonUtils.isMediaDocument;

public class IdentityActivity extends AppCompatActivity implements View.OnClickListener {
    private int GALLERY = 1, CAMERA = 2;
    File filePath1 ;
    private static final String IMAGE_DIRECTORY = "/Oooele";
    String isImageEdit1 = "0";
    TextView relihide;
    ImageView img_back, img_aadhar;
    Button btn_save;
    String User_Id;
    EditText etPanCard;
    AVLoadingIndicatorView bar;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity);

        requestMultiplePermissions();

        find();
    }

    public void find() {
        btn_save = findViewById(R.id.btn_save);
        img_aadhar = findViewById(R.id.img_aadhar);
        bar = findViewById(R.id.bar);
        img_back = findViewById(R.id.img_back);
        relihide = findViewById(R.id.relihide);
        etPanCard = findViewById(R.id.edit_aadhar);
        img_back.setOnClickListener(this);
        img_aadhar.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        appPreferences = new AppPreferences(this);
        User_Id = appPreferences.getUserData(AppPreferences.KEY_ID);
        if(appPreferences.checkForValue(AppPreferences.PAN_CARD)){
            etPanCard.setText(appPreferences.getUserData(AppPreferences.PAN_CARD));
        }
    }

    @Override
    public void onClick(View v) {
        if (v == img_back) {
            onBackPressed();
        }
        if (v == img_aadhar) {
            //    isImageEdit1 = "1";
            showPictureDialog();
        }
        if (v == btn_save) {
            if (etPanCard.getText().toString().isEmpty()) {
            } else {
                UploadProductDocumnet();
            }
        }
    }

    MultipartBody.Part part1;

    private void UploadProductDocumnet() {
        bar.setVisibility(View.VISIBLE);
        Log.d("LOG_MESSAGE", "UploadProductDocumnet: " +filePath1.getAbsolutePath());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (isImageEdit1.equalsIgnoreCase("1")) {
            RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), filePath1);
            part1 = MultipartBody.Part.createFormData("pancard_img", filePath1.getName(), fileReqBody);
        }
        RequestBody expert_id = RequestBody.create(MediaType.parse("expert_id"), User_Id);
        RequestBody pancard_no = RequestBody.create(MediaType.parse("pancard_no"),
                etPanCard.getText().toString());

        Call<ResponseBody> call;

        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {

            if (isImageEdit1.equalsIgnoreCase("1")) {
                call = apiInterface.UploadIdentDoc(expert_id, pancard_no, part1);
            } else {
                call = apiInterface.UploadIdentDoc1(expert_id, pancard_no);
            }



            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    bar.setVisibility(View.GONE);

                    try {
                        if (response.isSuccessful()) {
                            appPreferences.
                                    setUserData(AppPreferences.PAN_CARD, etPanCard.getText().toString());
                            String resturentResult = response.body().string();

                            JSONObject jsonObject = new JSONObject(resturentResult);
                            String msg = jsonObject.optString("message");
                            if (msg.equals("KYC Detail Successfully Updated")) {
                                onBackPressed();

                            } else {

                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                            }
                        } else
                            Toast.makeText(getApplicationContext(), "Email Allreday Used", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });

        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                Uri selectedImage = data.getData();

                isImageEdit1 = "1";
                img_aadhar.setImageURI(selectedImage);
                filePath1 = new File(getPath(this, data.getData()));

            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            try {
                if (thumbnail != null) {
                    filePath1 = saveImage(thumbnail);

                }
                isImageEdit1 = "1";
                img_aadhar.setImageBitmap(thumbnail);
            } catch (JsonIOException e) {
                e.printStackTrace();

                DynamicToast.makeError(getApplicationContext(), "Failed !").show();
            }


        }
    }
    public static String getPath(final Context context, final Uri uri) {

// check here to KITKAT or new version
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

// DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {

// ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }
            }
// DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
// MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
// MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

// Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
// File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    public File saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            return f;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(IdentityActivity.this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY);



     /*   Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);*/
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(IdentityActivity.this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            DynamicToast.makeSuccess(getApplicationContext(), "All permissions are granted by user!").show();

                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        DynamicToast.makeError(getApplicationContext(), "Some Error!").show();

                    }
                })
                .onSameThread()
                .check();
    }
}
