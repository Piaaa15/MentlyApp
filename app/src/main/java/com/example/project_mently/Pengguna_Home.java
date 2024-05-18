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
import android.widget.TextView;

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
 * Use the {@link Pengguna_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pengguna_Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DatabaseReference mdatabase;
    private RecyclerView recyclerView;
    private KonsulAdapter adapter;
    private List<Konsul> konsulList;


    public Pengguna_Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pengguna_Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Pengguna_Home newInstance(String param1, String param2) {
        Pengguna_Home fragment = new Pengguna_Home();
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
        return inflater.inflate(R.layout.fragment_pengguna__home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set the text for the TextView
        TextView txtUsername = (TextView) getView().findViewById(R.id.namauser);

        Intent intent =getActivity().getIntent();
        String username = intent.getStringExtra("nama");
        txtUsername.setText(username);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        konsulList = new ArrayList<>();
        adapter = new KonsulAdapter(konsulList);
        recyclerView.setAdapter(adapter);

        mdatabase = FirebaseDatabase.getInstance().getReference("Konsul");
        Query query = mdatabase.orderByChild("namaPasien").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                konsulList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Konsul konsul = data.getValue(Konsul.class);
                    konsulList.add(konsul);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
