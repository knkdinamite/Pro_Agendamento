package com.projeto.activities.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.projeto.R;
import com.projeto.adapters.UsuariosAdapter;
import com.projeto.models.Aplicacao;
import com.projeto.models.Usuario;

public class ListarUsuariosActivity extends AppCompatActivity {

    private ListView usuarios_lista_listview;
    private UsuariosAdapter adaptador = null;
    private ImageView usuarios_lista_imageview_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuarios_lista);

        idenfificandoComponentes();
        inicializandoComponentes();
    }


    private void idenfificandoComponentes() {

        usuarios_lista_listview = (ListView) findViewById(R.id.usuarios_lista_listview);
        usuarios_lista_imageview_add = (ImageView) findViewById(R.id.usuarios_lista_imageview_add);


        usuarios_lista_imageview_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaRegisterActivity(ListarUsuariosActivity.this);
            }
        });
    }

    public void inicializandoComponentes() {


        Usuario usuario = Usuario.verificaUsuarioLogado();
        usuario.setContext(ListarUsuariosActivity.this);

        Usuario.listarUsuariosRemoto(usuario,usuarios_lista_listview);

    }

    @Override
    protected void onResume(){
        super.onResume();
        inicializandoComponentes();
    }

}