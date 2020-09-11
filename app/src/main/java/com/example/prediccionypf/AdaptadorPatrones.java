package com.example.prediccionypf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorPatrones extends ArrayAdapter<PatronesSoportes> {
    public AdaptadorPatrones(Context context, ArrayList<PatronesSoportes> patrones){
        super(context, R.layout.ly_patrones,patrones);
    }

    public View getView(int posicion, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_patrones, null);
        TextView lbl_patron = item.findViewById(R.id.lbl_patron);
        TextView lbl_soporte = item.findViewById(R.id.lbl_soporte);

        lbl_patron.setText(String.valueOf(getItem(posicion).getPatron()));
        lbl_soporte.setText(String.valueOf(getItem(posicion).getSoporte()));

        return item;

    }
}
