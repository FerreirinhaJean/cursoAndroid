package com.example.organizzeclone.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFireBase {

    private static FirebaseAuth autenticacao;
    private static DatabaseReference reference;

    public static FirebaseAuth getFireBaseAutenticacao() {
        if (autenticacao == null)
            autenticacao = FirebaseAuth.getInstance();
        return autenticacao;
    }

    public static DatabaseReference getFireBase() {
        if (reference == null)
            reference = FirebaseDatabase.getInstance().getReference();
        return reference;
    }

}
