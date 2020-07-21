package com.app.oooelePartner.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.oooelePartner.Fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.app.oooelePartner.Fragment.BookFragment;
import com.app.oooelePartner.Fragment.MenuFragment;
import com.app.oooelePartner.Fragment.ProfileFragment;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Utill.CommonUtils;
import com.tbuonomo.morphbottomnavigation.MorphBottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;
    RelativeLayout footerHome,footersearch,footerCart,footeraccount;
    public ImageView iv_home,ivsearch,iv_cart,iv_account;
    public TextView txthome,txtsearch,txtcart,txtaccount;
    MorphBottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            switch (menuItem.getItemId()) {
                case R.id.newfrag:
                    //toolbar.setTitle("My Gifts");
                    fragment = new HomeFragment();
                    loadFragmentmain(fragment);
                    return true;
                case R.id.booking:
                    fragment = new BookFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.profile:
                    //toolbar.setTitle("Cart");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.menu:
                    //toolbar.setTitle("Profile");
                    fragment = new MenuFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find();
        loadFragmentmain(new HomeFragment());
    }
    public void find() {
        iv_home = findViewById(R.id.iv_home);
        ivsearch = findViewById(R.id.ivsearch);
        iv_cart = findViewById(R.id.iv_cart);
        iv_account = findViewById(R.id.iv_account);
        txthome = findViewById(R.id.txthome);
        txtsearch = findViewById(R.id.txtsearch);
        txtcart = findViewById(R.id.txtcart);
        txtaccount = findViewById(R.id.txtaccount);
        footerHome = findViewById(R.id.footerHome);
        footersearch = findViewById(R.id.footersearch);
        footerCart = findViewById(R.id.footerCart);
        footeraccount = findViewById(R.id.footeraccount);
        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        footerHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof HomeFragment) {
                }else {
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    CommonUtils.tabChange(MainActivity.this, iv_home, ivsearch, iv_cart, iv_account, txthome, txtsearch, txtcart, txtaccount);
                }
            }
        });
        footersearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof BookFragment) {
                }else {
                    fragment = new BookFragment();
                    loadFragment(fragment);
                    CommonUtils.tabChange(MainActivity.this, ivsearch, iv_cart, iv_account, iv_home, txtsearch, txtcart, txtaccount, txthome);
                }
            }
        });
        footerCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragment instanceof ProfileFragment ) {}else {
                    fragment=new ProfileFragment();
                    loadFragment(fragment);
                    CommonUtils.tabChange(MainActivity.this, iv_cart, iv_account, iv_home, ivsearch, txtcart, txtaccount, txthome, txtsearch);
                }
            }
        });
        footeraccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragment instanceof MenuFragment ) {
                }else {
                    fragment=new MenuFragment();
                    loadFragment(fragment);
                    CommonUtils.tabChange(MainActivity.this, iv_account, iv_home, ivsearch, iv_cart, txtaccount, txthome, txtsearch, txtcart);
                }
            }
        });
        //  Utils.tabChange(MainActivity.this,iv_home,ivsearch,iv_cart,iv_account,txthome,txtsearch,txtcart,txtaccount);
    }

    private void loadFragmentmain(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.slide_right,R.anim.slideout_left);
        transaction.replace(R.id.container, fragment);
        //transaction.addToBackStack("EXIT");
        //transaction.addToBackStack(String.valueOf(MainActivity.class));
        transaction.commit();
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.slide_right,R.anim.slideout_left);
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack("EXIT");
        //transaction.addToBackStack(String.valueOf(MainActivity.class));
        transaction.commit();
    }
}