package com.projeto.activities.Agendamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.projeto.R;
import com.projeto.models.Agendamento;
import com.projeto.models.Usuario;

public class AgendamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);
        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(AgendamentoActivity.this);
            Agendamento.listarAgendRemoto(usuario);
        }


    }
}