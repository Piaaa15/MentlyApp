package com.example.project_mently;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kodeJava.Konsul;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pengguna_Konsul#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pengguna_Konsul extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DatabaseReference mdatabase;



    public Pengguna_Konsul() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pengguna_Konsul.
     */
    // TODO: Rename and change types and number of parameters
    public static Pengguna_Konsul newInstance(String param1, String param2) {
        Pengguna_Konsul fragment = new Pengguna_Konsul();
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
        return inflater.inflate(R.layout.fragment_pengguna__konsul, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mdatabase = FirebaseDatabase.getInstance().getReference("Konsul");
        Button btnTambah = getView().findViewById(R.id.btnTambahKOnsul);


        // jawab ya penyakit depresi
        RadioButton rdYa1 = view.findViewById(R.id.jwbya1);
        RadioButton rdYa2 = view.findViewById(R.id.jwbya2);
        RadioButton rdYa3 = view.findViewById(R.id.jwbya3);

        // jawab ya penyakit anxiety
        RadioButton rdYa4 = view.findViewById(R.id.jwbya4);
        RadioButton rdYa5 = view.findViewById(R.id.jwbya5);
        RadioButton rdYa6 = view.findViewById(R.id.jwbya6);

        // jawab ya penyakit aspd
        RadioButton rdYa7 = view.findViewById(R.id.jwbya7);
        RadioButton rdYa8 = view.findViewById(R.id.jwbya8);
        RadioButton rdYa9 = view.findViewById(R.id.jwbya9);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String penyakit1 = "";
                String penyakit2 = "";
                String penyakit3 = "";
                String tingkatStress = "";
                String solusi = "";
                String resepObat = "";
                String Gejala = "";

                int countPenyakit1 = 0;
                int countPenyakit2 = 0;
                int countPenyakit3 = 0;

                // Periksa kondisi untuk depresi
                if (rdYa1.isChecked()) {
                    countPenyakit1++;
                    Gejala += "Sering Menangis  ";
                }
                if (rdYa2.isChecked()) {countPenyakit1++;
                    Gejala += "Kecewa  ";
                }
                if (rdYa3.isChecked()) {
                    countPenyakit1++;
                    Gejala += "Merasa Gagal  ";}
                if (countPenyakit1 > 0) {
                    penyakit1 = "Depresi  ";
                    resepObat = "Obat depresi  ";
                    solusi = "Solusi depresi  ";
                }

                // Periksa kondisi untuk anxiety
                if (rdYa4.isChecked()) {
                    countPenyakit2++;
                    Gejala += "Kurang Istirahat  ";
                }
                if (rdYa5.isChecked())  {
                    countPenyakit2++;
                    Gejala += "Gugup  ";
                }
                if (rdYa6.isChecked()) {
                    countPenyakit2++;
                    Gejala += "Khawatir  ";
                }
                if (countPenyakit2 > 0) {
                    penyakit2 = "Anxiety  ";
                    resepObat += "Obat anxiety  ";
                    solusi += "Solusi anxiety  ";
                }

                // Periksa kondisi untuk ASPD
                if (rdYa7.isChecked()) {
                    countPenyakit3++;
                    Gejala += "Sering buru2  ";
                }
                if (rdYa8.isChecked()) {
                    countPenyakit3++;
                    Gejala += "Sering Menyalahkan orang lain  ";
                }
                if (rdYa9.isChecked()) {
                    countPenyakit3++;
                    Gejala += "Sering Kasar  ";
                }
                if (countPenyakit3 > 0){
                    penyakit3 = "ASPD";
                    resepObat += "Obat ASPD";
                    solusi += "Solusi ASPD";


                }

                // Determine stress level
                if (countPenyakit1 > 1 && countPenyakit2 > 1 && countPenyakit3 > 1) {
                    tingkatStress = "Tinggi";
                } else if ((countPenyakit1 > 1 && countPenyakit2 > 1) ||
                        (countPenyakit1 > 1 && countPenyakit3 > 1) ||
                        (countPenyakit2 > 1 && countPenyakit3 > 1)) {
                    tingkatStress = "Sedang";
                }else{
                    tingkatStress = "Rendah";
                }

                String penyakit_saya = penyakit1 + penyakit2 + penyakit3;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String tanggalDibuat = sdf.format(new Date());
                Intent intent =getActivity().getIntent();
                String username = intent.getStringExtra("nama");
                String id = mdatabase.push().getKey();

                Konsul konsul = new Konsul(id, penyakit_saya, Gejala, solusi, resepObat, tingkatStress, tanggalDibuat, username);


                mdatabase.child(id).setValue(konsul);

                Toast.makeText(getActivity(), "Konsultasi Berhasil", Toast.LENGTH_SHORT).show();

            }


        });





    }
}