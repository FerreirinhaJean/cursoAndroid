package com.example.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_principal, R.id.nav_servicos,
                R.id.nav_clientes, R.id.nav_contato, R.id.nav_sobre)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void enviarEmail() {
        //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:51921498755")); //abre para ligação com numero informado
        // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.guiadasemana.com.br/contentFiles/image/2018/10/FEA/galeria/39210_w840h525_1540306449praia-de-itaguare-bertioga-shutterstockk2.jpg")); //abre link da imagem no navegador
        // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Bar+do+gordo/@-29.6156226,-52.194969,17z/data=!3m1!4b1!4m5!3m4!1s0x951c90023f1b939d:0x12d1edb13bc6c9e4!8m2!3d-29.6156273!4d-52.1927803")); //abre google maps com link da localização

        //Configura envio de email
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"atendimento@atmconsultoria.com.br"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "contato pelo App");
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem automática");

        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent, "Compartilhar"));
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
