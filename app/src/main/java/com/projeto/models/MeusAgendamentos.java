package com.projeto.models;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;
import com.projeto.activities.Agendamento.MeusActivity;
import com.projeto.adapters.AgendAdapter;
import com.projeto.adapters.MeusAdapter;
import com.projeto.api.retrofit.RetrofitConfig;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.projeto.models.Agendamento.confirmarAgendNaoEditado;

public class MeusAgendamentos extends SugarRecord {

    private long agendamento;
    private long usuario;
    private Context context;

    public MeusAgendamentos(){

    }

    public MeusAgendamentos(Context context){
        this.context = context;
    }

    public long getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(long agendamento) {
        this.agendamento = agendamento;
    }

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void editMeusAgendamento(String key,Context context){
        Call<MeusAgendamentos> call = new RetrofitConfig().setAgendService().addAgend("Token "+key);
        call.enqueue(new Callback<MeusAgendamentos>() {
                @Override
                public void onResponse(@NonNull Call<MeusAgendamentos> call, @NonNull Response<MeusAgendamentos> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            MeusAgendamentos agendamento = response.body();
                             agendamento.save();
                             ((MeusActivity)context).inicializandoComponentes();



                        }
                    }else {
                        confirmarAgendNaoEditado(context);
                    }

                }

                @Override
                public void onFailure(@NonNull Call<MeusAgendamentos> call, @NonNull Throwable t) {
                    Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());

                }
            });
}
    public static void listarAgenduser(@NotNull Usuario usuario, ListView agend_lista_user) {
        Call<List<MeusAgendamentos>> call = new RetrofitConfig().setAgendService().listarAgendporUser("Token " + usuario.getKey());
        call.enqueue(new Callback<List<MeusAgendamentos>>() {
            @Override
            public void onResponse(Call<List<MeusAgendamentos>> call, Response<List<MeusAgendamentos>> response) {
                if (response.isSuccessful()) {
                    List<MeusAgendamentos> meusAgendamentos = response.body();
                    Log.d("listarAgenduser", "listar");

                    MeusAdapter adaptador = new MeusAdapter(usuario.getContext(), meusAgendamentos);
                    agend_lista_user.setAdapter(adaptador);
                }
            }

            @Override
            public void onFailure(Call<List<MeusAgendamentos>> call, Throwable t) {
                Log.d("listarAgend", "listar");

            }
        });
    }

    public  static void adicionarAgend( String key,Context context){
        Call<MeusAgendamentos> call = new RetrofitConfig().setAgendService().addAgend("Token "+key);
        call.enqueue(new Callback<MeusAgendamentos>() {

            @Override
            public void onResponse(@NonNull Call<MeusAgendamentos> call, @NonNull Response<MeusAgendamentos> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                    }
                }else {
                     confirmarAgendNaoEditado(context);
                }

            }

            @Override
            public void onFailure(@NonNull Call<MeusAgendamentos> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());

            }
        });
    }
    }



