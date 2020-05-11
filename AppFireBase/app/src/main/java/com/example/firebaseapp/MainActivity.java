package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

   /* private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();*/

    private Button btnUpload;
    private ImageView imgFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUpload = findViewById(R.id.btnUpload);
        imgFoto = findViewById(R.id.imgFoto);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //configura para imagem ser salva em memória
                imgFoto.setDrawingCacheEnabled(true);
                imgFoto.buildDrawingCache();

                //recupera bitmap da imagem (imagem a ser carregada)
                Bitmap bitmap = imgFoto.getDrawingCache();

                //Compimindo bitmap para formato png/jpg

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                //converte o baos para pixel brutos em uma matriz de bytes
                //(dados da imagem)
                byte[] dadosImagem = baos.toByteArray();

                //define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                StorageReference imagemRef = imagens.child("celular.jpeg");

                

               /* imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Erro ao deletar o arquivo!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Sucesso ao deletar o arquivo!", Toast.LENGTH_SHORT).show();
                    }
                });*/


                //nome da imagem
              /*  String nomeArquivo = UUID.randomUUID().toString();
                StorageReference imagemRef = imagens.child(nomeArquivo + ".jpeg");

                //retorna objeto que irá controlar o upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Upload da imagem falhou: " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(MainActivity.this, "Sucesso ao fazer upload!", Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });


        // DatabaseReference usuarios = referencia.child("usuarios");

        //DatabaseReference usuarioPesquisa = usuarios.child("-M72rE4tthvlwmTTEA9E");
        //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Rodrigo");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);
        //  Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);

        //Maior igual
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(30);

        //menor igual
        // Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(25);



        /* Query usuarioPesquisa = usuarios.orderByChild("idade")
                 .startAt(18)
                 .endAt(30);*/
        /*filtrar palavras
        Query usuarioPesquisa = usuarios.orderByChild("nome")
                .startAt("J")
                .endAt("J"+"\uf8ff");*/

       /* usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Usuario dadosUsuario = dataSnapshot.getValue(Usuario.class);
                //   Log.i("Dados usuario:",  "Nome: "+dadosUsuario.getNome());
                Log.i("Dados usuario:", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


       /* usuarios.push().setValue(new Usuario("Ana","Ribeiro",29));
        usuarios.push().setValue(new Usuario("Marcelo","Farias",22));
        usuarios.push().setValue(new Usuario("Douglas","Souza",19));
        usuarios.push().setValue(new Usuario("Gilberto","Tavares",17));*/

        /*deslogar usuario
        usuario.signOut();*/

        /*logar usuario
        usuario.signInWithEmailAndPassword("jean@gmail.com", "12345jean")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("SIGNIN", "Sucesso ao logar usuario");
                        } else {
                            Log.i("SIGNIN", "Erro ao logar usuario");
                        }
                    }
                });*/


        /*verifica usuario logado
        if (usuario.getCurrentUser() != null) {
            Log.i("CREATEUSER", "usuario logado!");
        } else {
            Log.i("CREATEUSER", "usuario não logado!");
        }*/


        /*Cadastro usuario
        usuario.createUserWithEmailAndPassword("jean@gmail.com", "12345jean")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("CREATEUSER", "Sucesso ao cadastrar usuario");
                        } else {
                            Log.i("CREATEUSER", "Erro ao cadastrar usuario");
                        }
                    }
                });*/





        /*
        DatabaseReference usuarios = referencia.child("usuarios");
        DatabaseReference produtos = referencia.child("produtos");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Usuario usuario = new Usuario("Jean", "Ferreira", 20);

        Produto produto = new Produto("Notebook", "Dell", 1200.50f);

        usuarios.child("001").setValue(usuario);
        produtos.child("001").setValue(produto);*/

    }
}
