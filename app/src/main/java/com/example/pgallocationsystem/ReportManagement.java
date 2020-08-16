package com.example.pgallocationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReportManagement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.reportmanagement,container,false);
        Button btnviewdetails,btnviewfreerooms;
        btnviewdetails=v.findViewById(R.id.viewcandidatedeail);
        btnviewfreerooms=v.findViewById(R.id.viewfreeroom);
        btnviewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ViewCandidate.class);
                startActivity(intent);

            }
        });
        btnviewfreerooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ViewRooms.class);
                startActivity(intent);

            }
        });

        return v;

    }
}
