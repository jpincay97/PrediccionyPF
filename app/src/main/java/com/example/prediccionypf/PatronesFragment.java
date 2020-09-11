package com.example.prediccionypf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PatronesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatronesFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener, Asynchtask {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView txtPoblacion, txtIteracion;
    private EditText txt_soporte_min;
    private CheckBox cbNoSolucion;
    private Button btnEjecutar;
    private Spinner cmbOpciones;

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
        //final String[] datos = new String[]{"Apriori","FP Growth"};
        final String[] datos = new String[]{"apriori","fpgrowth"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,datos);
        cmbOpciones = view.findViewById(R.id.spAlgoritmo);
        txtPoblacion = view.findViewById(R.id.txtPoblacion);
        txtIteracion = view.findViewById(R.id.txtIteracion);
        cbNoSolucion = view.findViewById(R.id.cbNoSolucion);
        btnEjecutar = view.findViewById(R.id.btnEjecutar);
        txt_soporte_min = view.findViewById(R.id.txtSoporteMin);
        btnEjecutar.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnEjecutar){
            //Informacion Patrones
            //Primero traer los datos con la API REST de python (usar un "cargando....")

            if(!txt_soporte_min.getText().toString().equals("")){
                String min_soporte = txt_soporte_min.getText().toString();
                String algoritmo = cmbOpciones.getSelectedItem().toString();
                Map<String, String> datos = new HashMap<String, String>();
                WebService ws = new WebService("http://192.168.42.8:8000/obtener_patrones?url=datos_pepsi.csv&algoritmo="+algoritmo+"&min_soporte="+min_soporte+"",
                        datos, this.getContext(), this);
                ws.execute("GET");
            }else
                Toast.makeText(getActivity(), "Complete los parámetros",Toast.LENGTH_LONG).show();
        }
    }
    ArrayList<Patrones> patrones;
    Patrones patron;
    @Override
    public void processFinish(String result) throws JSONException {

        JSONObject datos = new JSONObject(result);

        patrones = new ArrayList<>();
        String algoritmo; Double soporte, tiempo_ejecucion, consumo_memoria;
        ArrayList<Double> soportes; ArrayList<String> patrones_frecuentes;

        algoritmo=datos.getString("algoritmo");
        soporte = datos.getDouble("soporte");
        tiempo_ejecucion = datos.getDouble("tiempo_ejec");
        consumo_memoria = datos.getDouble("consumo_memoria");
        JSONArray soportes_json = datos.getJSONArray("soportes");
        soportes= new ArrayList<>();
        for (int s = 0;s<soportes_json.length();s++){
            soportes.add(soportes_json.getDouble(s));
        }
        JSONArray patrones_json = datos.getJSONArray("patrones");
        patrones_frecuentes= new ArrayList<>();
        for (int p = 0;p<soportes_json.length();p++){
            patrones_frecuentes.add(patrones_json.getString(p));
        }
        patrones.add(new Patrones(algoritmo, soporte, tiempo_ejecucion, consumo_memoria,soportes, patrones_frecuentes));
        patron = new Patrones(algoritmo, soporte, tiempo_ejecucion, consumo_memoria,soportes, patrones_frecuentes);
        mostrar_informacion();
    }
    Bundle args = new Bundle();
    private void mostrar_informacion() {
        //Ya que se tienen los datos, mostrar la información
        //Toast.makeText(getActivity(), "Informacion Patrones ",Toast.LENGTH_LONG).show();
        Fragment fragment = new PatronesInformacion();
        args.putSerializable("patron",patron);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
