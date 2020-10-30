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
        Agendamento agendamento = new Agendamento(AgendamentoActivity.this,"tst","22/02/2022","10:00:00","13:00:00");
        agendamento.setId(4L);
        agendamento.editarAgendamento(usuario.getKey());
        //Agendamento agendamento = new Agendamento(AgendamentoActivity.this,"cha","21/08/2021","12:00:00","14:00:00");
        //agendamento.setId(3L);
        //agendamento.deletarAgendamento(usuario.getKey());
    }
}