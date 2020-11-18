package com.example.atualizandoaview_comerro;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.IOException;



public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://i.pinimg.com/originals/25/d6/7e/25d67e1ab8761a9fce706554245b2bdb.png";


    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);


        findViewById(R.id.bt1).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                //Faz o download da imagem em uma nova thread

                    new Thread(){

                        @SuppressLint("LongLogTag")
                        @Override
                        public void run(){
                            try {
                                //faz o download da imagem
                                final Bitmap imagem= Download.downloadBitmap(URL);
                                //atualiza a tela
                                atualizaImagem(imagem);

                            } catch (IOException e) {
                                //Uma aplicação real deveria tratar este erro
                                Log.e("Erro ao fazer o download: ", e.getMessage(), e);
                            }
                        }
                    }.start();

            }
        });
    }



    /*Vai dar erro neste método pois somente a UI Thread pode atualizar a view
    private void atualizaImagem(final Bitmap imagem) {
        //atualiza
       ImageView imageView= (ImageView) findViewById(R.id.img);
       ImageView.setImageBitmap(imagem);
    }*/

    private void atualizaImagem(final Bitmap imagem){
        runOnUiThread(new Runnable() { //atualiza a view na ui thread
            @Override
            public void run() {
                //atualiza a imagem
                ImageView imgView= (ImageView) findViewById(R.id.img);
                imgView.setImageBitmap(imagem);
            }
        });
    }
    }
