package com.app.oooelePartner.WorkingArea;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Adapter.AdapterGetWorkingArea;
import com.app.oooelePartner.Bean.BeanGetWorkingArea;
import com.app.oooelePartner.Bean.BeanWorkingRadius;
import com.app.oooelePartner.GeoFence.GeofenceTrasitionService;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseProfileUpload;
import com.app.oooelePartner.Response.ResponseWorkingRadiusList;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static final int REQUEST_CHECK_SETTINGS = 2;
    TextView txt_km;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 2;
    private final String TAG = MapsActivity.class.getSimpleName();
    PendingIntent mGeofencePendingIntent;
    String Str_radius = "1";
    String StrGlat = "";
    LocationRequest mLocationRequest;
    String StrGlng = "";
    Circle circle;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    String regex = "^(-?\\d+(\\.\\d+)?),\\s*(-?\\d+(\\.\\d+)?)$";
    Pattern latLongPattern = Pattern.compile(regex);
    ImageView imgCurrentloc;
    TextView searchview;
    SeekBar seekBar2;
    ImageView back;
    String st_Latitude, st_Longituude;
    LocationManager locationManager;
    //Our Map
    FragmentManager fragmentManager;
    EditText completaddress;
    Location mlocation;
    Marker mCurrLocationMarker;
    TextView tv_set_change;
    Button savelocation;
    TextView tv_set_location;
    String EditAddress;
    AVLoadingIndicatorView avi;
    ArrayList<BeanGetWorkingArea> banVisits;
    ArrayList<BeanWorkingRadius> beanWorkingRadii;
    BeanWorkingRadius beanWorkingRadius;
    AdapterGetWorkingArea adapterGetWorkingArea;
    Button btn_SetWorkingArea;
    RecyclerView recyclersetworking;
    RecyclerView.LayoutManager layoutManager;
    String User_Id;
    private GoogleApiClient mGoogleApiClient;
    private String userAddress = "";
    private String usercity = "";
    private double mLatitude;
    private double mLongitude;
    private String place_id = "";
    private String place_url = " ";
    private GoogleMap mMap;
    private boolean mLocationPermissionGranted;
    //Declaration of FusedLocationProviderClient
    private FusedLocationProviderClient fusedLocationProviderClient;
    private List<AsyncTask> filterTaskList = new ArrayList<>();
    private int doAfterPermissionProvided, doAfterLocationSwitchedOn = 1;
    private double currentLatitude = Math.floor(2.7);
    private double currentLongitude = Math.floor(2.7);
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private List<Geofence> mGeofenceList;
    //To store longitude and latitude from map
    private double longitude;
    private double latitude;

    String[] permissions = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION};
    private GoogleApiClient.ConnectionCallbacks connectionAddListener =
            new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(Bundle bundle) {

            if (checkAndRequestPermissions()) {
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                if (location == null) {
                    LocationServices.FusedLocationApi.
                            requestLocationUpdates(mGoogleApiClient, mLocationRequest, (LocationListener) MapsActivity.this);

                } else {
                    currentLatitude = location.getLatitude();
                    currentLongitude = location.getLongitude();
                    double val = Math.floor(2.11);

                }
                try {
                    LocationServices.GeofencingApi.addGeofences(
                            mGoogleApiClient,
                            getGeofencingRequest(),
                            getGeofencePendingIntent()
                    ).setResultCallback(new ResultCallback<Status>() {

                        @Override
                        public void onResult(Status status) {
                            if (status.isSuccess()) {
                                Log.i(TAG, "Saving Geofence");

                            } else {
                                Log.e(TAG, "Registering geofence failed: " + status.getStatusMessage() +
                                        " : " + status.getStatusCode());
                            }
                        }
                    });
                } catch (SecurityException securityException) {
                    // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
                    Log.e(TAG, "Error");
                }
            }

        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.e(TAG, "onConnectionSuspended");
        }
    };
    private GoogleApiClient.OnConnectionFailedListener connectionFailedListener
            = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            Log.e(TAG, "onConnectionFailed");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapsfragment);
        if (savedInstanceState == null) {
            mGeofenceList = new ArrayList<>();
            int resp = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
            if (resp == ConnectionResult.SUCCESS) {
                initGoogleAPIClient();
                createGeofences(currentLatitude, currentLongitude);
            } else {
                Log.e(TAG, "Your Device doesn't support Google Play Services.");
            }

            // Create the LocationRequest object
            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(1 * 1000)        // 10 seconds, in milliseconds
                    .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        }

        User_Id = String.valueOf(AppPreferences.getSavedUser(getApplication()).getId());
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        searchview = findViewById(R.id.searchview);
        txt_km = findViewById(R.id.txt_km);
        tv_set_location = findViewById(R.id.tv_set_location);
        completaddress = findViewById(R.id.completaddress);
        savelocation = findViewById(R.id.savelocation);
        tv_set_change = findViewById(R.id.tv_set_change);
        imgCurrentloc = findViewById(R.id.imgCurrentloc);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Prepare for Request for current location
        getLocationRequest();
        //define callback of location request
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                Log.d(TAG, "onLocationAvailability: isLocationAvailable =  " + locationAvailability.isLocationAvailable());
            }

            @Override
            public void onLocationResult(LocationResult locationResult) {
                Log.d(TAG, "onLocationResult: " + locationResult);
                if (locationResult == null) {
                    return;
                }
                //show location on map
                switch (doAfterLocationSwitchedOn) {
                    case 1:
                        startParsingAddressToShow();
                        break;
                    case 2:
                        //on click of imgCurrent
                        showCurrentLocationOnMap(false);
                        break;
                    case 3:
                        //on Click of Direction Tool
                        showCurrentLocationOnMap(true);
                        break;
                }
                //Location fetched, update listener can be removed
                fusedLocationProviderClient.removeLocationUpdates(locationCallback);
            }
        };

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        savelocation.setOnClickListener(this);
        //if you want to open the location on the LocationPickerActivity through intent
        Intent i = getIntent();
        if (i != null) {
            Bundle extras = i.getExtras();
            if (extras != null) {
                userAddress = extras.getString(MapUtility.ADDRESS);
                //temp -> get lat , log from db
                mLatitude = getIntent().getDoubleExtra(MapUtility.LATITUDE, 0);
                mLongitude = getIntent().getDoubleExtra(MapUtility.LONGITUDE, 0);
            }
        }
        if (savedInstanceState != null) {
            mLatitude = savedInstanceState.getDouble("latitude");
            mLongitude = savedInstanceState.getDouble("longitude");
            userAddress = savedInstanceState.getString("userAddress");
            currentLatitude = savedInstanceState.getDouble("currentLatitude");
            currentLongitude = savedInstanceState.getDouble("currentLongitude");
        }
        if (!MapUtility.isNetworkAvailable(this)) {
            MapUtility.showToast(this, "Please Connect to Internet");
        }
        imgCurrentloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapsActivity.this.showCurrentLocationOnMap(false);
                doAfterPermissionProvided = 2;
                doAfterLocationSwitchedOn = 2;
            }
        });
        searchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Places.isInitialized()) {
                    Places.initialize(MapsActivity.this.getApplicationContext(), MapUtility.apiKey);
                }
                // Set the fields to specify which types of place data to return.
                List<com.google.android.libraries.places.api.model.Place.Field> fields = Arrays.asList(com.google.android.libraries.places.api.model.Place.Field.ID, com.google.android.libraries.places.api.model.Place.Field.NAME, com.google.android.libraries.places.api.model.Place.Field.ADDRESS, com.google.android.libraries.places.api.model.Place.Field.LAT_LNG);
                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(MapsActivity.this);
                MapsActivity.this.startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
            }
        });


     /*   googleMapTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Default google map
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "http://maps.google.com/maps?q=loc:" + mLatitude + ", " + mLongitude + ""));
                MapsActivity.this.startActivity(intent);
            }
        });
*/
        find();
        getWorkingArea();
    }

    public void find() {
        avi = findViewById(R.id.bar);
        seekBar2 = findViewById(R.id.seekBar2);
        btn_SetWorkingArea = findViewById(R.id.btn_SetWorkingArea);
        recyclersetworking = findViewById(R.id.setworkingrecycler);
        recyclersetworking.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclersetworking.setLayoutManager(layoutManager);
        recyclersetworking.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclersetworking.setAdapter(adapterGetWorkingArea);
        seekBar2.setOnSeekBarChangeListener(this);
        btn_SetWorkingArea.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    public void initGoogleAPIClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(connectionAddListener)
                .addOnConnectionFailedListener(connectionFailedListener)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //after a place is searched
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = (Place) Autocomplete.getPlaceFromIntent(data);
                userAddress = String.valueOf(place.getAddress());
                tv_set_location.setText("" + userAddress);
                mLatitude = place.getLatLng().latitude;
                mLongitude = place.getLatLng().longitude;
                place_id = place.getId();
                place_url = String.valueOf(place.getWebsiteUri());
                addMarker();
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        } else if (requestCode == REQUEST_CHECK_SETTINGS) {
            //after location switch on dialog shown
            if (resultCode != RESULT_OK) {
                //Location not switched ON
                Toast.makeText(MapsActivity.this, "Location Not Available..", Toast.LENGTH_SHORT).show();
            } else {
                // Start location request listener.
                //Location will be received onLocationResult()
                //Once loc recvd, updateListener will be turned OFF.
                Toast.makeText(this, "Fetching Location...", Toast.LENGTH_LONG).show();
                startLocationUpdates();

            }
        }
    }

    private boolean checkAndRequestPermissions() {
        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int coarsePermision = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (coarsePermision != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }

        //getSettingsLocation();
        return true;

    }

    private void showCurrentLocationOnMap(final boolean isDirectionClicked) {

        if (checkAndRequestPermissions()) {

            @SuppressLint("MissingPermission")
            Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
            lastLocation.addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mMap.clear();
                        if (isDirectionClicked) {
                            currentLatitude = location.getLatitude();
                            currentLongitude = location.getLongitude();
                            //Go to Map for Directions
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                                    "http://maps.google.com/maps?saddr=" + currentLatitude + ", " + currentLongitude + "&daddr=" + mLatitude + ", " + mLongitude + ""));
                            MapsActivity.this.startActivity(intent);
                        } else {
                            //Go to Current Location
                            mLatitude = location.getLatitude();
                            mLongitude = location.getLongitude();
                            MapsActivity.this.getAddressByGeoCodingLatLng();
                        }

                    } else {
                        //Gps not enabled if loc is null
                        MapsActivity.this.getSettingsLocation();
                        Toast.makeText(MapsActivity.this, "Location not Available", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            lastLocation.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //If perm provided then gps not enabled
//                getSettingsLocation();
                    Toast.makeText(MapsActivity.this, "Location Not Availabe", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    private void addMarker() {

        LatLng coordinate = new LatLng(mLatitude, mLongitude);
        if (mMap != null) {
            MarkerOptions markerOptions;
            try {
                mMap.clear();
                tv_set_location.setText("" + userAddress);
                //markerOptions = new MarkerOptions().position(coordinate).title(userAddress).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_red_800_24dp));

                markerOptions = new MarkerOptions().position(coordinate).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_red_800_24dp));
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
                mMap.animateCamera(cameraUpdate);
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                Marker marker = mMap.addMarker(markerOptions);
                marker.showInfoWindow();


                circle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(mLatitude, mLongitude))
                        .radius(1000)
                        .strokeColor(Color.RED)
                        .strokeWidth(4f));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        if (mMap.isIndoorEnabled()) {
            mMap.setIndoorEnabled(false);
        }


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {
                View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);

                // Getting the position from the marker
                LatLng latLng = arg0.getPosition();
                mLatitude = latLng.latitude;
                mLongitude = latLng.longitude;

                TextView tvLat = v.findViewById(R.id.address);
                tvLat.setText(userAddress);
                return v;

            }
        });
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Setting a click event handler for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mLatitude = latLng.latitude;
                mLongitude = latLng.longitude;
                StrGlat = String.valueOf(mLatitude);
                StrGlng = String.valueOf(mLongitude);

                Log.e("latlng", StrGlng + "");
                MapsActivity.this.addMarker();
                if (!MapUtility.isNetworkAvailable(MapsActivity.this)) {
                    MapUtility.showToast(MapsActivity.this, "Please Connect to Internet");
                }

                MapsActivity.this.getAddressByGeoCodingLatLng();

            }
        });

        if (checkAndRequestPermissions()) {
            startParsingAddressToShow();
        } else {
            doAfterPermissionProvided = 1;
        }

    }

    private void getSettingsLocation() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    // All location settings are satisfied. The client can initialize location
                    // requests here.
                    //...
                    if (response != null) {
                        LocationSettingsStates locationSettingsStates = response.getLocationSettingsStates();
                        Log.d(TAG, "getSettingsLocation: " + locationSettingsStates);
                        MapsActivity.this.startLocationUpdates();

                    }
                } catch (ApiException exception) {
                    Log.d(TAG, "getSettingsLocation: " + exception);
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the
                            // user a dialog.
                            try {
                                // Cast to a resolvable exception.
                                ResolvableApiException resolvable = (ResolvableApiException) exception;
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                resolvable.startResolutionForResult(
                                        MapsActivity.this,
                                        REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            } catch (ClassCastException e) {
                                // Ignore, should be an impossible error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            //...
                            break;
                    }
                }
            }
        });
    }

    /**
     * Show location from intent
     */
    private void startParsingAddressToShow() {
        //get address from intent to show on map
        if (userAddress == null || userAddress.isEmpty()) {

            //if intent does not have address,
            //cell is blank
            showCurrentLocationOnMap(false);

        } else

            //check if address contains lat long, then extract
            //format will be lat,lng i.e 19.23234,72.65465
            if (latLongPattern.matcher(userAddress).matches()) {

                Pattern p = Pattern.compile("(-?\\d+(\\.\\d+)?)");   // the pattern to search for
                Matcher m = p.matcher(userAddress);

                // if we find a match, get the group
                int i = 0;
                while (m.find()) {
                    // we're only looking for 2s group, so get it
                    if (i == 0)
                        mLatitude = Double.parseDouble(m.group());
                    if (i == 1)
                        mLongitude = Double.parseDouble(m.group());

                    i++;

                }
                //show on map
                getAddressByGeoCodingLatLng();
                addMarker();

            } else {
                //get  latlong from String address via reverse geo coding
                //Since lat long not present in db
                if (mLatitude == 0 && mLongitude == 0) {
                    getLatLngByRevGeoCodeFromAdd();
                } else {
                    // Latlong is more accurate to get exact point on map ,
                    // String address might not be sufficient (i.e Mumbai, Mah..etc)
                    addMarker();
                }
            }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("latitude", mLatitude);
        outState.putDouble("longitude", mLongitude);
        outState.putString("userAddress", userAddress);
        outState.putDouble("currentLatitude", currentLatitude);
        outState.putDouble("currentLongitude", currentLongitude);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void getAddressByGeoCodingLatLng() {

        //Get string address by geo coding from lat long
        if (mLatitude != 0 && mLongitude != 0) {

            if (MapUtility.popupWindow != null && MapUtility.popupWindow.isShowing()) {
                MapUtility.hideProgress();
            }

            Log.d(TAG, "getAddressByGeoCodingLatLng: START");
            //Cancel previous tasks and launch this one
            for (AsyncTask prevTask : filterTaskList) {
                prevTask.cancel(true);
            }

            filterTaskList.clear();
            GetAddressFromLatLng asyncTask = new GetAddressFromLatLng();
            filterTaskList.add(asyncTask);
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mLatitude, mLongitude);
        }
    }

    private void getLatLngByRevGeoCodeFromAdd() {

        //Get string address by geo coding from lat long
        if (mLatitude == 0 && mLongitude == 0) {

            if (MapUtility.popupWindow != null && MapUtility.popupWindow.isShowing()) {
                MapUtility.hideProgress();
            }

            Log.d(TAG, "getLatLngByRevGeoCodeFromAdd: START");
            //Cancel previous tasks and launch this one
            for (AsyncTask prevTask : filterTaskList) {
                prevTask.cancel(true);
            }

            filterTaskList.clear();
            GetLatLngFromAddress asyncTask = new GetLatLngFromAddress();
            filterTaskList.add(asyncTask);
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userAddress);
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //Toast.makeText(MapsActivity.this, "Seekbar vale " + progress, Toast.LENGTH_SHORT).show();
        Str_radius = String.valueOf(progress);
        txt_km.setText(Str_radius + " KM");
        //   SStr_Raddius=progress
        //     Log.d("SeekBardata", "" + Str_radius);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //  Toast.makeText(MapsActivity.this, "Seekbar touch started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //  Toast.makeText(MapsActivity.this, "Seekbar touch stopped", Toast.LENGTH_SHORT).show();
    }

/*    @Override
    public void onClick(View v) {
        if (v==imgCurrentloc){
            MapsActivity.this.showCurrentLocationOnMap(false);
            doAfterPermissionProvided = 2;
            doAfterLocationSwitchedOn = 2;
        }
    }*/

    double roundAvoid(double value) {
        double scale = Math.pow(10, 6);
        return Math.round(value * scale) / scale;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (AsyncTask task : filterTaskList) {
            task.cancel(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Do tasks for which permission was granted by user in onRequestPermission()
        if (!isFinishing() && mLocationPermissionGranted) {
            // perform action required b4 asking permission
            mLocationPermissionGranted = false;
            switch (doAfterPermissionProvided) {
                case 1:
                    startParsingAddressToShow();
                    break;
                case 2:
                    showCurrentLocationOnMap(false);
                    break;
                case 3:
                    showCurrentLocationOnMap(true);
                    break;
            }

        }

    }

    private void startLocationUpdates() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MapsActivity.this, "Location not Available", Toast.LENGTH_SHORT).show();
            return;
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                locationCallback,
                null /* Looper */)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "startLocationUpdates: onSuccess: ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            Log.d(TAG, "startLocationUpdates: " + e.getMessage());
                        } else {
                            Log.d(TAG, "startLocationUpdates: " + e.getMessage());
                        }
                    }
                });

    }

    private void getLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void createGeofences(double latitude, double longitude) {
        String id = UUID.randomUUID().toString();
        Geofence fence = new Geofence.Builder()
                .setRequestId(id)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .setCircularRegion(latitude, longitude, 200)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
        mGeofenceList.add(fence);
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(this, GeofenceTrasitionService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        return PendingIntent.getService(this, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
    }

    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        Log.i(TAG, "onLocationChanged");
    }

    @Override
    public void onClick(View v) {
        if (v == btn_SetWorkingArea) {
            ApiSetWorkignArea();
        }
        if (v == back) {
            onBackPressed();
        }
    }

    private void ApiSetWorkignArea() {
        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id", "address", "lat", "lng", "working_radius"},
                new String[]{User_Id, userAddress, String.valueOf(currentLatitude), String.valueOf(currentLongitude), Str_radius});
        Call<ResponseProfileUpload> call = apiInterface.ApiPartnerSetWorkignArea(builder.build());
        avi.setVisibility(View.GONE);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (response.isSuccessful()) {
                    ResponseProfileUpload resObj = (ResponseProfileUpload) response.body();
                    if (resObj.getMessage().equals("Successfully Saved Working Area")) {
                        DynamicToast.makeSuccess(getApplicationContext(), "Successfully Saved Working Area").show();
                        getWorkingArea();
                    } else {
                        avi.setVisibility(View.GONE);
                        DynamicToast.makeError(getApplicationContext(), "Date Is Not Uploaded").show();
                        // Toast.makeText(LoginNewActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    avi.setVisibility(View.GONE);
                    DynamicToast.makeError(getApplicationContext(), "Error! Please try again! ").show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getWorkingArea() {
        //    CommonUtils.showProDialog1(getApplicationContext());
///
        // progressDialog.show();

        //bar.setVisibility(View.VISIBLE);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<ResponseWorkingRadiusList> call = service.ApiGetdAllWorkingLeads(builder.build());


            call.enqueue(new Callback<ResponseWorkingRadiusList>() {
                @Override
                public void onResponse(Call<ResponseWorkingRadiusList> call, Response<ResponseWorkingRadiusList> response) {

                    try {

                        if (response.body().getStatus().equals("true")) {
                            //       bar.setVisibility(View.GONE);
                            // relihidedata.setVisibility(View.GONE);

                            //   String Path = response.body().getPath();
                            //   Path2 = response.body().getPath2();
                            beanWorkingRadii = new ArrayList<>();
                            //     banVisits.clear();
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                beanWorkingRadius = new BeanWorkingRadius();


                                beanWorkingRadius.setId(response.body().getData().get(i).getId());
                                beanWorkingRadius.setExpert_id(response.body().getData().get(i).getExpert_id());
                                beanWorkingRadius.setLat(response.body().getData().get(i).getLat());
                                beanWorkingRadius.setLng(response.body().getData().get(i).getLng());
                                beanWorkingRadius.setAddress(response.body().getData().get(i).getAddress());
                                beanWorkingRadius.setWorking_radius(response.body().getData().get(i).getWorking_radius());


                                beanWorkingRadii.add(beanWorkingRadius);
                            }
                            //  Adaptercartsing adaptercartsing;
                           /* adaptercartsing = new Adaptercartsing(getActivity(), cartListBeans);
                            recycletop_cart.setAdapter(adaptercartsing);*/
                            Log.e("allBidBeanList", "" + beanWorkingRadii.size());
                            adapterGetWorkingArea = new AdapterGetWorkingArea(getApplicationContext(), beanWorkingRadii);
                            recyclersetworking.setAdapter(adapterGetWorkingArea);

                            //  adapterNewLeads = new AdapterNewLeads(getActivity(), banVisits);
                            //    newRecycle.setAdapter(adapterNewLeads);
                        } else {
                            //      bar.setVisibility(View.GONE);
                            //     relihidedata.setVisibility(View.VISIBLE);
                            //     btn_placeorder.setVisibility(View.GONE);
                        }


                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<ResponseWorkingRadiusList> call, Throwable t) {

                    // bar.setVisibility(View.GONE);
                    // Toast.makeText(getApplicationContext(),"mobile Or Password Wrong..", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //progressDialog.dismiss();
            //  scrolls.setVisibility(View.VISIBLE);
            // reli.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }


    @SuppressLint("StaticFieldLeak")
    private class GetAddressFromLatLng extends AsyncTask<Double, Void, String> {
        Double latitude, longitude;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MapUtility.showProgress(MapsActivity.this);
        }

        @Override
        protected String doInBackground(Double... doubles) {
            try {

                latitude = doubles[0];
                longitude = doubles[1];

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                StringBuilder sb = new StringBuilder();

                //get location from lat long if address string is null
                addresses = geocoder.getFromLocation(latitude, longitude, 1);

                if (addresses != null && addresses.size() > 0) {

                    String address = addresses.get(0).getAddressLine(0);
                    if (address != null)
                        sb.append(address).append(" ");
                    String city = addresses.get(0).getLocality();
                    if (city != null)
                        sb.append(city).append(" ");

                    String state = addresses.get(0).getAdminArea();
                    if (state != null)
                        sb.append(state).append(" ");
                    String country = addresses.get(0).getCountryName();
                    if (country != null)
                        sb.append(country).append(" ");

                    String postalCode = addresses.get(0).getPostalCode();
                    if (postalCode != null)
                        sb.append(postalCode).append(" ");
                    return sb.toString();

                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return roundAvoid(latitude) + "," + roundAvoid(longitude);

            }
        }


        @Override
        protected void onPostExecute(String userAddress) {
            super.onPostExecute(userAddress);
            MapsActivity.this.userAddress = userAddress;
            MapUtility.hideProgress();
            addMarker();
        }
    }

    private class GetLatLngFromAddress extends AsyncTask<String, Void, LatLng> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MapUtility.showProgress(MapsActivity.this);
        }

        @Override
        protected LatLng doInBackground(String... userAddress) {
            LatLng latLng = new LatLng(0, 0);

            try {

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());

                //get location from lat long if address string is null
                addresses = geocoder.getFromLocationName(userAddress[0], 1);

                if (addresses != null && addresses.size() > 0) {
                    latLng = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                }
            } catch (Exception ignored) {
            }
            return latLng;
        }


        @Override
        protected void onPostExecute(LatLng latLng) {
            super.onPostExecute(latLng);
            MapsActivity.this.mLatitude = latLng.latitude;
            MapsActivity.this.mLongitude = latLng.longitude;
            MapUtility.hideProgress();
            addMarker();
        }
    }


}
