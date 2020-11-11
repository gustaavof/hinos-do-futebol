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
public class SelecoesFragment extends Fragment {

    private MainActivity activity;
    private ImageButton
            buttonBrasil, buttonAlemanha, buttonArgentina, buttonEspanha,
            buttonFranca, buttonHolanda, buttonInglaterra, buttonItalia,
            buttonMexico, buttonPortugal, buttonUruguai, buttonUsa;


    public SelecoesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selecoes, container, false);
        initializeMethods(view);
        return view;
    }


    private void initializeMethods(View v) {
        buttonBrasil = v.findViewById(R.id.buttonBrasil);
        buttonAlemanha = v.findViewById(R.id.buttonAlemanha);
        buttonArgentina = v.findViewById(R.id.buttonArgentina);
        buttonEspanha = v.findViewById(R.id.buttonEspanha);
        buttonFranca = v.findViewById(R.id.buttonFranca);
        buttonHolanda = v.findViewById(R.id.buttonHolanda);
        buttonInglaterra = v.findViewById(R.id.buttonInglaterra);
        buttonItalia = v.findViewById(R.id.buttonItalia);
        buttonMexico = v.findViewById(R.id.buttonMexico);
        buttonPortugal = v.findViewById(R.id.buttonPortugal);
        buttonUruguai = v.findViewById(R.id.buttonUruguai);
        buttonUsa = v.findViewById(R.id.buttonUsa);

        activity = (MainActivity) getContext();


        List<ImageButton> buttons = new ArrayList<>();

        buttons.addAll(Arrays.asList(buttonAlemanha, buttonUsa, buttonUruguai, buttonItalia,
                buttonInglaterra, buttonArgentina, buttonBrasil, buttonEspanha,
                buttonFranca, buttonHolanda, buttonMexico, buttonPortugal));

        buttons.forEach(activity::startButton);
    }
}