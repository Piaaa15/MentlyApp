package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pengguna_Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pengguna_Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Pengguna_Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pengguna_Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Pengguna_Profile newInstance(String param1, String param2) {
        Pengguna_Profile fragment = new Pengguna_Profile();
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
        return inflater.inflate(R.layout.fragment_pengguna__profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtUsername = view.findViewById(R.id.userProfilePasien);

        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("nama");
        String email = intent.getStringExtra("email");
        String umur = intent.getStringExtra("umur");
        String jenkel = intent.getStringExtra("jenkel");

        // Ensure the values are not null before using them
        if (username != null) {
            txtUsername.setText(username);
        }

        ImageView btnInfo = view.findViewById(R.id.btnInformasiPasien);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent(getActivity(), Informasi_Pribadi.class);
                if (username != null) infoIntent.putExtra("nama", username);
                if (email != null) infoIntent.putExtra("email", email);
                if (umur != null) infoIntent.putExtra("umur", umur);
                if (jenkel != null) infoIntent.putExtra("jenkel", jenkel);
                startActivity(infoIntent);
            }
        });


        View btnKeluar = view.findViewById(R.id.button);
        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Toast.makeText(getActivity(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
