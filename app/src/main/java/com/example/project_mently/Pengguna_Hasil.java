package com.example.project_mently;

import android.content.Intent;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kodeJava.Konsul;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pengguna_Hasil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pengguna_Hasil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference mdatabase;
    private RecyclerView recyclerView;
    private HasilKonsulAdapter adapter;
    private List<Konsul> hasilKonsulList;


    public Pengguna_Hasil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pengguna_Hasil.
     */
    // TODO: Rename and change types and number of parameters
    public static Pengguna_Hasil newInstance(String param1, String param2) {
        Pengguna_Hasil fragment = new Pengguna_Hasil();
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
        return inflater.inflate(R.layout.fragment_pengguna__hasil, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Intent intent =getActivity().getIntent();
        String username = intent.getStringExtra("nama");
        recyclerView = view.findViewById(R.id.recyclerViewHasil);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        hasilKonsulList = new ArrayList<>();
        adapter = new HasilKonsulAdapter(hasilKonsulList, this.getContext());
        recyclerView.setAdapter(adapter);

        mdatabase = FirebaseDatabase.getInstance().getReference("Konsul");  // Assuming "HasilKonsul" is the correct reference
        Query query = mdatabase.orderByChild("namaPasien").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hasilKonsulList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Konsul hasilKonsul = data.getValue(Konsul.class);
                    hasilKonsulList.add(hasilKonsul);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }
}