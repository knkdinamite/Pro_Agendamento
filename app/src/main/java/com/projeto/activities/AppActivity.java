package com.projeto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.projeto.R;
import com.projeto.models.Aplicacao;
import com.projeto.models.Usuario;
import com.projeto.observers.ActivityObserver;

public class AppActivity extends AppCompatActivity {

    private Usuario usuario;
    private ListView listaDeGrupos;
    private View aplicacao_view_tarefas,aplicacao_view_usuarios,view5 ;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aplicacao);

        usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(AppActivity.this);
        }

        identificandoComponentes();
        inicializandoComponentes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_logoff:
                fazerLogoff();
                return true;
            case R.id.menu_item_deletar_conta:
                usuario.deletarUsuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fazerLogoff() {
        usuario.setLogado(false);
        usuario.save();
        Aplicacao.irParaListarLoginActivity(AppActivity.this);
    }

    private void identificandoComponentes() {
        aplicacao_view_tarefas = (View) findViewById(R.id.aplicacao_view_tarefas);
        aplicacao_view_usuarios = (View) findViewById(R.id.aplicacao_view_usuarios);
        view5 = (View) findViewById(R.id.view5);

    }
    private void inicializandoComponentes() {
        aplicacao_view_tarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaTarefaActivity(AppActivity.this);
            }
        });
        aplicacao_view_usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaListarUsuariosActivity(AppActivity.this);
            }
        });
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aplicacao.irParaAgendamentoActivity(AppActivity.this);
            }
        });
    }



    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Clique voltar novamente, se realmente deseja sair", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}