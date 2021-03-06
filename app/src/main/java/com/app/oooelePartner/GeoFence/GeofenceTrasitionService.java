package com.app.oooelePartner.GeoFence;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.oooelePartner.Utill.Constants;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceTrasitionService extends IntentService {
    private static final String TAG = "GeoIntentService";

    public GeofenceTrasitionService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.d(TAG, "GeofencingEvent error " + geofencingEvent.getErrorCode());
        } else {
            int transaction = geofencingEvent.getGeofenceTransition();
            List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            if (transaction == Geofence.GEOFENCE_TRANSITION_ENTER && geofence.getRequestId().equals(Constants.GEOFENCE_ID_STAN_UNI)) {
                Log.d(TAG, "You are inside Stanford University");
            } else {
                Log.d(TAG, "You are outside Stanford University");
            }
        }
    }

}
