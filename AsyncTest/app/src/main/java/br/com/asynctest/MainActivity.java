package br.com.asynctest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView imagem;
    private Button baixar;
    private EditText endereco;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagem = (ImageView) findViewById(R.id.imageView);
        baixar = (Button) findViewById(R.id.button);
        endereco = (EditText) findViewById(R.id.editText);

        Log.i("AsyncTask", "Elementos de tela criados e atribuidos Thread: " + Thread.currentThread().getName());
        baixar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("AsyncTask", "Botão Clicado Thread: " + Thread.currentThread().getName());
                chamarAsyncTask(endereco.getText().toString());
            }
        });
    }

    private void chamarAsyncTask(String endereco) {
        TarefaDownload download = new TarefaDownload();
        Log.i("AsyncTask", "AsyncTask senado chamado Thread: " + Thread.currentThread().getName());
        download.execute(endereco);
    }

    private class TarefaDownload extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " + Thread.currentThread().getName());
            load = ProgressDialog.show(MainActivity.this, "Por favor Aguarde ...",
                    "Baixando Imagem ...");
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap imagemBitmap = null;
            try {
                Log.i("AsyncTask", "Baixando a imagem Thread: " + Thread.currentThread().getName());
                imagemBitmap = Auxiliar.baixarImagem(params[0]);
            } catch (IOException e) {
                Log.i("AsyncTask", e.getMessage());
            }

            return imagemBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imagem.setImageBitmap(bitmap);
                Log.i("AsyncTask", "Exibindo Bitmap Thread: " + Thread.currentThread().getName());
            } else {
                Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            load.dismiss();
        }
    }

}