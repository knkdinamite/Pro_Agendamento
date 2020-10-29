package com.projeto.activities.tarefa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projeto.R;
import com.projeto.models.Tarefa;
import com.projeto.models.Usuario;

public class TarefaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_tarefa);

        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(TarefaActivity.this);
        }

        Tarefa tarefa = new Tarefa(TarefaActivity.this);
        tarefa.receberListaDeTarefas(usuario.getKey());

    }
}