package com.app.oooelePartner;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.fabric.sdk.android.Fabric;

// Declare for Google Analytics for Firebase

public class Application extends android.app.Application {
    // [END declare_analytics]

    @Override
    public void onCreate() {
        super.onCreate();

        // Obtain the FirebaseAnalytics instance.
        // [START declare_analytics]
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        // [END FirebaseAnalytics instance]

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);

    }
}
