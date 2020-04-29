package com.example.aprendaingles.fragment;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.aprendaingles.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumerosFragment extends Fragment implements View.OnClickListener {

    private ImageView imgUm, imgDois, imgTres, imgQuatro, imgCinco, imgSeis;
    private MediaPlayer mediaPlayer;


    public NumerosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_numeros, container, false);

        imgUm = view.findViewById(R.id.imgUm);
        imgDois = view.findViewById(R.id.imgDois);
        imgTres = view.findViewById(R.id.imgTres);
        imgQuatro = view.findViewById(R.id.imgQuatro);
        imgCinco = view.findViewById(R.id.imgCinco);
        imgSeis = view.findViewById(R.id.imgSeis);

        imgUm.setOnClickListener(this);
        imgDois.setOnClickListener(this);
        imgTres.setOnClickListener(this);
        imgQuatro.setOnClickListener(this);
        imgCinco.setOnClickListener(this);
        imgSeis.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == imgUm.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
            tocarSom();
        }
        if (v.getId() == imgDois.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.two);
            tocarSom();
        }
        if (v.getId() == imgTres.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.three);
            tocarSom();
        }
        if (v.getId() == imgQuatro.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.four);
            tocarSom();
        }
        if (v.getId() == imgCinco.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.five);
            tocarSom();
        }
        if (v.getId() == imgSeis.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.six);
            tocarSom();
        }

    }

    public void tocarSom() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();//libera recursos de midia da memoria
            mediaPlayer = null;
        }
    }
}
