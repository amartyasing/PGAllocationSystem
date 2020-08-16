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

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Home extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v,view,viewadd,viewupdate,viewdelete;
        v=inflater.inflate(R.layout.home,container,false);
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getActivity());
        view=getLayoutInflater().inflate(R.layout.candidatemanage,null);
        bottomSheetDialog.setContentView(view);
        Button btnpgalloc,btncandidatedetails;
        viewadd=view.findViewById(R.id.linadd);
        viewupdate=view.findViewById(R.id.linupdate);
        viewdelete=view.findViewById(R.id.lindelete);
        btncandidatedetails=v.findViewById(R.id.candidatedetails);
        btnpgalloc=v.findViewById(R.id.pgalloc);
        btnpgalloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),RoomAllotment.class);
                startActivity(intent);

            }
        });
        btncandidatedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });
        viewadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddCandidate.class);
                startActivity(intent);
            }
        });
        viewupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),UpdateCandidate.class);
                startActivity(intent);
            }
        });
        viewdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DeleteCandidate.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
