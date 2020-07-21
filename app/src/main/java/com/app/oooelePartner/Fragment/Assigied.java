package com.app.oooelePartner.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.oooelePartner.Adapter.Assigied_Adapter;
import com.app.oooelePartner.R;
import com.app.oooelePartner.model.assmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Assigied extends Fragment {
     private RecyclerView recyclerAss;

    public Assigied() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_assigied, container, false);

        List<assmodel> list = new ArrayList<>();
        list = getData();


        recyclerAss=view.findViewById(R.id.assirecycle);

        Assigied_Adapter adapter = new Assigied_Adapter(getContext(),list);
        recyclerAss.setHasFixedSize(true);
        recyclerAss.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAss.setAdapter(adapter);


        return view;
    }


    private List<assmodel> getData()
    {
        List<assmodel> list = new ArrayList<>();
        list.add(new assmodel("Neelam Sharma",
                "15 Dec|10:00 am",
                "Khatipura","100s","Partner"));

        list.add(new assmodel("Neelam Sharma",
                "15 Dec|10:00 am",
                "Khatipura","100s","Partner"));

        list.add(new assmodel("Neelam Sharma",
                "15 Dec|10:00 am",
                "Khatipura","100s","Partner"));

        list.add(new assmodel("Neelam Sharma",
                "15 Dec|10:00 am",
                "Khatipura","100s","Partner"));

        list.add(new assmodel("Neelam Sharma",
                "15 Dec|10:00 am",
                "Khatipura","100","Partner"));


        return list;
    }


}
