package com.example.atmconsultoria.ui.sobre;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atmconsultoria.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobreFragment extends Fragment {


    public SobreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String descricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer commodo lacus at nisl euismod molestie. Maecenas ultrices justo eu posuere iaculis. Fusce porttitor pellentesque erat non gravida. Donec sodales malesuada nibh in dictum. Cras ante dolor, porttitor a efficitur non, feugiat vel ante. Phasellus purus nisl, commodo vitae sodales a, mollis sit amet tellus. Etiam congue dapibus congue.";

        Element versao = new Element();
        versao.setTitle("Versão 1.0");

        return new AboutPage(getActivity())
                .setImage(R.drawable.logo)
                .setDescription(descricao)
                .addGroup("Entre em contato")
                .addEmail("atendimento@atmconsultoria.com.br", "Envie um e-mail")
                .addWebsite("https://www.google.com.br/", "Acesse nosso site")

                .addGroup("Redes sociais")
                .addFacebook("udemy", "Facebook")
                .addInstagram("Udemy", "Instagram")
                .addTwitter("udemy_brasil", "Twitter")
                .addYoutube("Udemy", "YouTube")
                .addPlayStore("com.udemy.android", "Download App")
                .addItem(versao)
                .create();
        //return inflater.inflate(R.layout.fragment_sobre, container, false);
    }

}
