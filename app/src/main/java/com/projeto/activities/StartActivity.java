package com.projeto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.projeto.GifImageView;
import com.projeto.R;
import com.projeto.activities.autenticacao.LoginActivity;
import com.projeto.models.Aplicacao;
import com.projeto.models.Usuario;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        ImageView img=(ImageView)findViewById(R.id.img);

        Glide.with(StartActivity.this).load(R.drawable.loading_processmaker).asGif().into(img);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(Usuario.verificaUsuarioLogado()!=null){
                    Aplicacao.irParaAppActivity(StartActivity.this);
                }else {
                    Aplicacao.irParaLoginActivity(StartActivity.this);
                }
                finish();
            }
        }, 1500);
/*


 */
    }
}