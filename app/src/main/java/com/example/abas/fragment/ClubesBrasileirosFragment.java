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
public class ClubesBrasileirosFragment extends Fragment {

    private MainActivity activity;

    private ImageButton
            buttonAtletico, buttonBotafogo, buttonCorinthians, buttonCruzeiro,
            buttonFlamengo, buttonFluminense, buttonGremio, buttonInternacional,
            buttonPalmeiras, buttonSantos, buttonSaoPaulo, buttonVasco;

    public ClubesBrasileirosFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubes_brasileiros, container, false);
        initializeMethods(view);
        return view;
    }

    private void initializeMethods(View v) {
        buttonAtletico = v.findViewById(R.id.buttonAtletico);
        buttonBotafogo = v.findViewById(R.id.buttonBotafogo);
        buttonCorinthians = v.findViewById(R.id.buttonCorinthians);
        buttonCruzeiro = v.findViewById(R.id.buttonCruzeiro);
        buttonFlamengo = v.findViewById(R.id.buttonFlamengo);
        buttonFluminense = v.findViewById(R.id.buttonFluminense);
        buttonGremio = v.findViewById(R.id.buttonGremio);
        buttonInternacional = v.findViewById(R.id.buttonInternacional);
        buttonPalmeiras = v.findViewById(R.id.buttonPalmeiras);
        buttonSantos = v.findViewById(R.id.buttonSantos);
        buttonSaoPaulo = v.findViewById(R.id.buttonSaoPaulo);
        buttonVasco = v.findViewById(R.id.buttonVasco);

        activity = (MainActivity) getContext();

        List<ImageButton> buttons = new ArrayList<>();

        // adicionando os botões na lista
        buttons.addAll(Arrays.asList(buttonAtletico, buttonBotafogo, buttonCorinthians, buttonCruzeiro,
                buttonFlamengo, buttonFluminense, buttonGremio, buttonInternacional,
                buttonPalmeiras, buttonSantos, buttonSaoPaulo, buttonVasco));

        // inicializando os botões
        buttons.forEach(activity::startButton);
    }
}