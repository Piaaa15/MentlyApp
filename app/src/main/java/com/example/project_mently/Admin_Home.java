package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kodeJava.Pasien;

public class Admin_Home extends Fragment {

    private RecyclerView mRecyclerView;
    private PasienAdapter mAdapter;
    private List<Pasien> mPasienList;
    private DatabaseReference mDatabaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin__home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        mRecyclerView = view.findViewById(R.id.rcyViewAdminHome);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize list and adapter
        mPasienList = new ArrayList<>();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Pasien");
        mAdapter = new PasienAdapter(getContext(), mPasienList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PasienAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pasien pasien) {
                Intent intent = new Intent(getActivity(), DetailPasien.class);
                intent.putExtra("nama", pasien.getNama());
                intent.putExtra("jenkel", pasien.getJenkel());
                intent.putExtra("umur", pasien.getUmur());
                intent.putExtra("tanggalDibuat", pasien.getTanggalDibuat());
                startActivity(intent);
            }
        });

        // Fetch data from Firebase
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPasienList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Pasien pasien = postSnapshot.getValue(Pasien.class);
                    mPasienList.add(pasien);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }
}
