package com.app.oooelePartner.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import com.app.oooelePartner.Activity.MainActivity;
import com.app.oooelePartner.Adapter.TabAdapter;
import com.app.oooelePartner.R;
import com.google.android.material.tabs.TabLayout;
import com.app.oooelePartner.Utill.CommonUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment implements View.OnClickListener {
Button btnopen,btnasssignn,btnpast;
MainActivity mainActivity;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    View view;
    public BookFragment() {
        // Required empty public constructor
    }

 /*   @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_book, container, false);



      //  tabLayout=(TabLayout)view.findViewById(R.id.mytablayout);
      //  viewPager=(ViewPager)view.findViewById(R.id.tab_viewpager);

        //setSupportActionBar(toolbar);
        setupViewPager(viewPager);

        loadFragment(new Assigied());

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_book, container, false);
        mainActivity= (MainActivity) getActivity();
        //    User_Id = String.valueOf(AppPreferences.getSavedUser(getActivity()).getId());
   find();
     //   getCurrentLead();
        return view;
    }



    public void find(){
        btnopen=view.findViewById(R.id.btnopen);
        btnasssignn=view.findViewById(R.id.btnasssignn);
        btnpast=view.findViewById(R.id.btnpast);
        btnopen.setOnClickListener(this);
        btnasssignn.setOnClickListener(this);
        btnpast.setOnClickListener(this);
        CommonUtils.goToFragment(getActivity(), new OpenFragment(), R.id.fragmentContainer, false);
    //    setupViewPager(viewPager);
    }
    @Override
    public void onResume() {
        super.onResume();
        // getImageBanner();
       // CommonUtils.tabChange(getActivity(), mainActivity.iv_home, mainActivity.ivsearch, mainActivity.iv_cart, mainActivity.iv_account, mainActivity.txthome, mainActivity.txtsearch, mainActivity.txtcart, mainActivity.txtaccount);
        CommonUtils.tabChange(getActivity(), mainActivity.ivsearch,  mainActivity.iv_cart,  mainActivity.iv_account,  mainActivity.iv_home,  mainActivity.txtsearch,  mainActivity.txtcart,  mainActivity.txtaccount,  mainActivity.txthome);

    }
    private void setupViewPager(ViewPager viewPager){
        TabAdapter tabAdapter=new TabAdapter(getActivity().getSupportFragmentManager());
        tabAdapter.addFragment(new OpenFragment(),"OPEN");
        tabAdapter.addFragment(new Assigied(),"ASSIGIED");
        tabAdapter.addFragment(new PastFragment(),"PAST");
        viewPager.setAdapter(tabAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v==btnopen){
          //  Log.e("tripissssss",StrTripID);
            btnopen.setBackgroundColor(btnopen.getContext().getResources().getColor(R.color.reddd));
            btnopen.setTextColor(btnopen.getContext().getResources().getColor(R.color.colorWhite));
            btnopen.setClickable(false);
            btnpast.setBackgroundColor(btnpast.getContext().getResources().getColor(R.color.colorWhite));
            btnpast.setTextColor(btnpast.getContext().getResources().getColor(R.color.reddd));
            btnpast.setClickable(true);
        //    Bundle bundle = new Bundle();
        //    bundle.putString("Trip_id", Trip_id);
            CommonUtils.goToFragment(getActivity(), new OpenFragment(), R.id.fragmentContainer, false);
        }  if (v==btnpast){
            //  Log.e("tripissssss",StrTripID);
          /*  btnopen.setBackgroundColor(btnopen.getContext().getResources().getColor(R.color.colorWhite));
            btnopen.setTextColor(btnopen.getContext().getResources().getColor(R.color.nav_black));
            btnopen.setClickable(true);*/
            btnopen.setBackgroundColor(btnpast.getContext().getResources().getColor(R.color.colorWhite));
            btnopen.setTextColor(btnpast.getContext().getResources().getColor(R.color.reddd));
            btnopen.setClickable(true);
            btnpast.setBackgroundColor(btnpast.getContext().getResources().getColor(R.color.reddd));
            btnpast.setTextColor(btnpast.getContext().getResources().getColor(R.color.colorWhite));
            btnpast.setClickable(true);
            //    Bundle bundle = new Bundle();
            //    bundle.putString("Trip_id", Trip_id);
            CommonUtils.goToFragment(getActivity(), new PastFragment(), R.id.fragmentContainer, false);
        }
    }
}
