package com.app.oooelePartner.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.oooelePartner.Activity.CreditHistory;
import com.app.oooelePartner.Activity.MainActivity;
import com.app.oooelePartner.Activity.Static_web;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Utill.CommonUtils;
/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
MainActivity mainActivity;
    private String ac_page="http://www.oooele.com/static-pages/creat-ac.php";
    private String refund_page="http://www.oooele.com/static-pages/credit-ref.php";
    private String customer_page="http://www.oooele.com/static-pages/connect-cu.php";
    private String Ref_page="http://www.oooele.com/static-pages/credit-ref.php";
    private String Review_page="http://www.oooele.com/static-pages/review-pro.php";
    private String Down_page="http://www.oooele.com/static-pages/app-down.php";
    private String Tips_page="http://www.oooele.com/static-pages/tips-hir.php";
    private TextView credit;
    private TextView acc,refund,setting,customer,tips,Ref,review,down;
    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_menu, container, false);
        mainActivity= (MainActivity) getActivity();
        credit=(TextView)view.findViewById(R.id.credit_id);
        acc=view.findViewById(R.id.ac_page);
        refund=view.findViewById(R.id.refund);
        setting=view.findViewById(R.id.account_setting);
        customer=view.findViewById(R.id.customer);
        tips=view.findViewById(R.id.tips);
        Ref=view.findViewById(R.id.ref);
        review=view.findViewById(R.id.Review);
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CreditHistory.class));
            }
        });
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Static_web.class);
                intent.putExtra("web",ac_page);
                startActivity(intent);
            }
        });
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Static_web.class);
                intent.putExtra("web",refund_page);
                startActivity(intent);
            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Static_web.class);
                intent.putExtra("web",customer_page);
                startActivity(intent);
            }
        });
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Static_web.class);
                intent.putExtra("web",Tips_page);
                startActivity(intent);
            }
        });

        Ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Static_web.class);
                intent.putExtra("web",Ref_page);
                startActivity(intent);
            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Static_web.class);
                intent.putExtra("web",Review_page);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Static_web.class);
                intent.putExtra("web",Down_page);
                startActivity(intent);
            }
        });

        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
        // getImageBanner();
        CommonUtils.tabChange(getActivity(), mainActivity.iv_account, mainActivity.iv_home, mainActivity.ivsearch, mainActivity.iv_cart, mainActivity.txtaccount, mainActivity.txthome, mainActivity.txtsearch, mainActivity.txtcart);

       // CommonUtils.tabChange(getActivity(), mainActivity.iv_home, mainActivity.ivsearch, mainActivity.iv_cart, mainActivity.iv_account, mainActivity.txthome, mainActivity.txtsearch, mainActivity.txtcart, mainActivity.txtaccount);
    }
}
