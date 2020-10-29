package com.projeto.api.servicos;

import com.projeto.models.Tarefa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TarefaService {

    @GET("tarefas/")
    Call<List<Tarefa>> listarTarefas(@Header("Authorization") String key);
}
