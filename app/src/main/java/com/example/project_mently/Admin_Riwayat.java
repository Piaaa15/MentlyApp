package com.example.project_mently;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import kodeJava.Konsul;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_Riwayat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_Riwayat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private RiwayatAdapter adapter;
    private List<Konsul> konsulList;

    public Admin_Riwayat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_Riwayat.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_Riwayat newInstance(String param1, String param2) {
        Admin_Riwayat fragment = new Admin_Riwayat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin__riwayat, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewRiwayat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        konsulList = new ArrayList<>();
        adapter = new RiwayatAdapter(konsulList, getContext());
        recyclerView.setAdapter(adapter);
        fetchKonsulData();
    }
    private void fetchKonsulData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Konsul");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                konsulList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Konsul konsul = snapshot.getValue(Konsul.class);
                    konsulList.add(konsul);
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Collections.sort(konsulList, new Comparator<Konsul>() {
                    @Override
                    public int compare(Konsul k1, Konsul k2) {
                        try {
                            Date date1 = dateFormat.parse(k1.getTanggal());
                            Date date2 = dateFormat.parse(k2.getTanggal());
                            return date2.compareTo(date1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }
}