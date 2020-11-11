package com.example.abas.fragment;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.abas.R;
import com.example.abas.activity.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class ClubesEuropeusFragment extends Fragment {

    private MainActivity activity;

    private ImageButton
            buttonAjax, buttonArsenal, buttonAtleticoMadrid, buttonBarcelona,
            buttonBayern, buttonBenfica, buttonBVB, buttonChelsea,
            buttonCity, buttonInter, buttonJuventus, buttonLiverpool,
            buttonMilan, buttonPorto, buttonPSG, buttonReal,
            buttonTottenham, buttonUnited;

    public ClubesEuropeusFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubes_europeus, container, false);
        initializeMethods(view);
        return view;
    }

    private void initializeMethods(View v) {
        buttonAjax = v.findViewById(R.id.buttonAjax);
        buttonArsenal = v.findViewById(R.id.buttonArsenal);
        buttonAtleticoMadrid = v.findViewById(R.id.buttonAtleticoMadrid);
        buttonBarcelona = v.findViewById(R.id.buttonBarcelona);
        buttonBayern = v.findViewById(R.id.buttonBayern);
        buttonBenfica = v.findViewById(R.id.buttonBenfica);
        buttonBVB = v.findViewById(R.id.buttonBVB);
        buttonChelsea = v.findViewById(R.id.buttonChelsea);
        buttonCity = v.findViewById(R.id.buttonCity);
        buttonInter = v.findViewById(R.id.buttonInter);
        buttonJuventus = v.findViewById(R.id.buttonJuventus);
        buttonLiverpool = v.findViewById(R.id.buttonLiverpool);
        buttonMilan = v.findViewById(R.id.buttonMilan);
        buttonPorto = v.findViewById(R.id.buttonPorto);
        buttonPSG = v.findViewById(R.id.buttonPSG);
        buttonReal = v.findViewById(R.id.buttonReal);
        buttonTottenham = v.findViewById(R.id.buttonTottenham);
        buttonUnited = v.findViewById(R.id.buttonUnited);

        activity = (MainActivity) getContext();

        List<ImageButton> buttons = new ArrayList<>();

        buttons.addAll(Arrays.asList(buttonBarcelona, buttonUnited, buttonTottenham, buttonPorto,
                buttonAjax, buttonArsenal, buttonAtleticoMadrid, buttonBayern, buttonBenfica,
                buttonBVB, buttonChelsea, buttonCity, buttonInter, buttonJuventus, buttonLiverpool,
                buttonMilan, buttonPSG, buttonReal));

        buttons.forEach(activity::startButton);
    }
}