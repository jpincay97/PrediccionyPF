package com.example.prediccionypf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PatronesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatronesFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView txtPoblacion, txtIteracion;
    CheckBox cbNoSolucion;

    public PatronesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatronesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatronesFragment newInstance(String param1, String param2) {
        PatronesFragment fragment = new PatronesFragment();
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
        //return inflater.inflate(R.layout.fragment_patrones, container, false);
        View view = inflater.inflate(R.layout.fragment_patrones,container,false);
        final String[] datos = new String[]{"Genético","Apriori","FP Growth"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,datos);
        Spinner cmbOpciones = view.findViewById(R.id.spAlgoritmo);
        txtPoblacion = view.findViewById(R.id.txtPoblacion);
        txtIteracion = view.findViewById(R.id.txtIteracion);
        cbNoSolucion = view.findViewById(R.id.cbNoSolucion);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(this);
        //ImageView img = view.findViewById(R.id.imgInicio);
        //img.setImageResource(-1);
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //txtItem.setText("Seleccionado: " + parent.getItemAtPosition(position));
        if(!parent.getItemAtPosition(position).equals("Genético")){
            txtPoblacion.setEnabled(false);
            txtIteracion.setEnabled(false);
            cbNoSolucion.setEnabled(false);
            cbNoSolucion.setEnabled(false);
        }
        else{
            txtPoblacion.setEnabled(true);
            txtIteracion.setEnabled(true);
            cbNoSolucion.setEnabled(true);
            cbNoSolucion.setEnabled(true);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        //txtItem.setText("No ha seleccionado nada");
    }
}
