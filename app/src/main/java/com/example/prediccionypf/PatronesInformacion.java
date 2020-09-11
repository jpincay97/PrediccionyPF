package com.example.prediccionypf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;


public class PatronesInformacion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView lbl_soporte, lbl_tiempo_ejecicion, lbl_consumo_memoria, lbl_titulo;
    private ListView lv_patrones;


    public PatronesInformacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatronesInformacion.
     */
    // TODO: Rename and change types and number of parameters
    public static PatronesInformacion newInstance(String param1, String param2) {
        PatronesInformacion fragment = new PatronesInformacion();
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
    Patrones patrones;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_patrones_informacion, container, false);
        lbl_soporte=vista.findViewById(R.id.txtSoporte);
        lbl_tiempo_ejecicion=vista.findViewById(R.id.txtTiempo);
        lbl_consumo_memoria=vista.findViewById(R.id.TxtMemoria);
        lbl_titulo=vista.findViewById(R.id.lbl_titulo);
        lv_patrones = vista.findViewById(R.id.lv_patrones);

        patrones = (Patrones) getArguments().getSerializable("patron");

        lbl_titulo.setText(lbl_titulo.getText()+patrones.getAlgoritmo());
        lbl_soporte.setText(String.valueOf(patrones.getSoporte()));
        lbl_tiempo_ejecicion.setText(String.valueOf(patrones.getTiempo_ejecucion()));
        lbl_consumo_memoria.setText(String.valueOf(patrones.getConsumo_memoria()));

        ArrayList<PatronesSoportes> patronessoporte = new ArrayList<>();
        for (int i=0;i<patrones.getPatrones_frecuentes().size();i++){
            patronessoporte.add(new PatronesSoportes(patrones.getPatrones_frecuentes().get(i),patrones.getSoportes().get(i)));
        }
        AdaptadorPatrones adaptadorPatrones = new AdaptadorPatrones(getContext(), patronessoporte);
        lv_patrones.setAdapter(adaptadorPatrones);
        return vista;
    }
}
