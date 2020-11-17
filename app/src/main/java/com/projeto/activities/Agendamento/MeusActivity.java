package com.projeto.activities.Agendamento;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.R;
import com.projeto.adapters.AgendAdapter;
import com.projeto.models.Agendamento;
import com.projeto.models.Aplicacao;
import com.projeto.models.Usuario;

public class MeusActivity extends AppCompatActivity {
    ListView agend_lista_user;
    private AgendAdapter adaptador = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meus_agendamentos);

        Bundle b = getIntent().getExtras();
        long idAgendameto = -1; // or other values
        if(b != null) {
            idAgendameto = b.getLong("id");
        }
        Log.d("activity","Valor do id recebido: "+ idAgendameto);
        Agendamento agendamento = Agendamento.findById(Agendamento.class,idAgendameto);

        agend_lista_user = (ListView) findViewById(R.id.agend_lista_user);

        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(MeusActivity.this);


            Agendamento.listarAgenduser(usuario,agend_lista_user);
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agend2, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.menu_item_voltar:
                voltar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void voltar() {

        Aplicacao.irParaAgendamentoActivity(MeusActivity.this);
    }
}