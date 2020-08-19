package com.app.oooelePartner.Utill;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.app.oooelePartner.R;


public class CommonUtils {


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    /**
     * Show toast message
     *
     * @param mContext Context of activity or fragment
     * @param message  Message that show into the Toast
     */
    public static void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }


    /**
     * Goto any Fragment
     *
     * @param mContext  Context of the Activity of Fragment.
     * @param fragment  Fragment that want to open
     * @param container Container in which want to infulate Fragment
     */
    public static void goToFragment(Context mContext, Fragment fragment, int container, boolean addtoBackstack) {
        FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        if (addtoBackstack)
            transaction.addToBackStack(null);
        try {
            transaction.commit();
        } catch (Exception e) {

        }
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    public static void tabChange(Context context, ImageView home, ImageView search, ImageView cart, ImageView account, TextView txthome, TextView txtsearch, TextView txtcart, TextView txtaccount) {
        home.setColorFilter(context.getResources().getColor(R.color.red_pitch));
        search.setColorFilter(context.getResources().getColor(R.color.nav_black));
        cart.setColorFilter(context.getResources().getColor(R.color.nav_black));
        account.setColorFilter(context.getResources().getColor(R.color.nav_black));
        // account.

        txthome.setTextColor(context.getResources().getColor(R.color.red_pitch));
        txtsearch.setTextColor(ContextCompat.getColor(context, R.color.nav_black));
        txtcart.setTextColor(ContextCompat.getColor(context, R.color.nav_black));
        txtaccount.setTextColor(ContextCompat.getColor(context, R.color.nav_black));

    }
}
