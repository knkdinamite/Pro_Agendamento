package com.projeto.api.retrofit;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projeto.api.servicos.AgendService;
import com.projeto.api.servicos.EventoService;
import com.projeto.api.servicos.TarefaService;
import com.projeto.api.servicos.UserService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.projeto.statics.ConstantesGlobais.ENDERECO_API;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    //.addInterceptor(new ConnectivityInterceptor(context))
                    .build();

            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .create();

            this.retrofit = new Retrofit.Builder()
                    .baseUrl(ENDERECO_API)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }



    public UserService setUserService() {
        return this.retrofit.create(UserService.class);
    }

    public TarefaService setTarefaService() {
        return this.retrofit.create(TarefaService.class);
    }

    public EventoService setEventoService() {
        return this.retrofit.create(EventoService.class);
    }

    public AgendService setAgendService() {
        return (AgendService) this.retrofit.create(AgendService.class);
    }
}
