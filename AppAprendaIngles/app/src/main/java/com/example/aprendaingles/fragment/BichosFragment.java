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
public class BichosFragment extends Fragment implements View.OnClickListener {

    private ImageView imgGato, imgCachorro, imgLeao, imgVaca, imgOvelha, imgMacaco;
    private MediaPlayer mediaPlayer;

    public BichosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bichos, container, false);

        imgCachorro = view.findViewById(R.id.imgCachorro);
        imgGato = view.findViewById(R.id.imgGato);
        imgLeao = view.findViewById(R.id.imgLeao);
        imgVaca = view.findViewById(R.id.imgVaca);
        imgOvelha = view.findViewById(R.id.imgOvelha);
        imgMacaco = view.findViewById(R.id.imgMacaco);

        imgCachorro.setOnClickListener(this);
        imgGato.setOnClickListener(this);
        imgLeao.setOnClickListener(this);
        imgVaca.setOnClickListener(this);
        imgOvelha.setOnClickListener(this);
        imgMacaco.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == imgCachorro.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dog);
            tocarSom();
        }
        if (v.getId() == imgGato.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cat);
            tocarSom();
        }
        if (v.getId() == imgLeao.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.lion);
            tocarSom();
        }
        if (v.getId() == imgVaca.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cow);
            tocarSom();
        }
        if (v.getId() == imgOvelha.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sheep);
            tocarSom();
        }
        if (v.getId() == imgMacaco.getId()) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.monkey);
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
