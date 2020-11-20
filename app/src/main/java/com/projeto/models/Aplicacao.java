package com.projeto.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.projeto.activities.Agendamento.AgendamentoActivity;
import com.projeto.activities.Agendamento.MeusActivity;
import com.projeto.activities.AppActivity;
import com.projeto.activities.autenticacao.LoginActivity;
import com.projeto.activities.autenticacao.RegisterActivity;
import com.projeto.activities.tarefa.TarefaActivity;
import com.projeto.activities.usuario.ListarUsuariosActivity;
import com.projeto.activities.usuario.UsuarioDetalheActivity;

import static com.projeto.statics.ConstantesGlobais.ADICIONAR;
import static com.projeto.statics.ConstantesGlobais.REMOVER;


public class Aplicacao {


    private Context context;
    private Class<?> activityDestino;

    public Aplicacao(Context context, Class<?> activityDestino) {
        this.context = context;
        this.activityDestino = activityDestino;
    }

    public Aplicacao(Context context) {
        this.context = context;
    }

    public static void irParaListarUsuariosActivity(Context context) {
        Intent intent = new Intent(context, ListarUsuariosActivity.class);
        context.startActivity(intent);
    }


    public static void irParaListarLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void irParaAppActivity(Context context) {
        Intent intent = new Intent(context, AppActivity.class);
        context.startActivity(intent);
    }

    public static void irParaRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public static void irParaLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void irParaTarefaActivity(Context context) {
        Intent intent = new Intent(context, TarefaActivity.class);
        context.startActivity(intent);
    }

    public static void irParaUsuarioDetalheActivity(Context context, Long id) {
        Intent intent = new Intent(context, UsuarioDetalheActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", id);
        intent.putExtras(b);
        context.startActivity(intent);
    }

    public static void irParaAgendamentoActivity(Context context) {
        Intent intent = new Intent(context, AgendamentoActivity.class);
        context.startActivity(intent);


        //public static void fecharApp (Context context){
           // Intent homeIntent = new Intent(Intent.ACTION_MAIN);
           // homeIntent.addCategory(Intent.CATEGORY_HOME);
           // homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           // context.startActivity(homeIntent);
        //}
    }


    public static void irParaMeusActivity(Context context, Long id) {
        Intent intent = new Intent(context, MeusActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", id);
        intent.putExtras(b);
        context.startActivity(intent);
    }

    public static void irParaMeusAgends(Context context) {
        Intent intent = new Intent(context, MeusActivity.class);
        context.startActivity(intent);
    }
    public static void AlertarpraAdicionar(Context context, Agendamento agendamento){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(ADICIONAR);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Usuario usuario = Usuario.verificaUsuarioLogado();
                        agendamento.setUsuario(usuario.getId());
                        agendamento.editarAgendamento(usuario.getKey(),context);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static void aguardar(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void AlertarpraRemover(Context context, Agendamento agendamento) {AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
        builder2.setMessage(REMOVER);
        builder2.setCancelable(true);

        builder2.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Usuario usuario = Usuario.verificaUsuarioLogado();

                        agendamento.setUsuario(null);
                        agendamento.editMeusAgendamento(usuario.getKey(),context);

                    }
                }).setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert12 = builder2.create();
        alert12.show();
    }
}
