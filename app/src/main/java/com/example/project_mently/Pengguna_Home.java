package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
 * Use the {@link Pengguna_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pengguna_Home extends Fragment implements KonsulAdapter.OnHasilClickListener {

    // Define parameter arguments
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Define parameters
    private String mParam1;
    private String mParam2;

    private DatabaseReference mdatabase;
    private RecyclerView recyclerView;
    private KonsulAdapter adapter;
    private List<Konsul> konsulList;

    public Pengguna_Home() {
        // Required empty public constructor
    }

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

        TextView txtUsername = view.findViewById(R.id.namauser);
        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("nama");
        txtUsername.setText(username);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        konsulList = new ArrayList<>();
        adapter = new KonsulAdapter(konsulList, getContext(), this);
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
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }

    @Override
    public void onHasilClick(Konsul konsul) {
        Pengguna_Hasil hasilFragment = Pengguna_Hasil.newInstance(konsul);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, hasilFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
