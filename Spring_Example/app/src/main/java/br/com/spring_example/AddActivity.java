package br.com.spring_example;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.spring_example.clients.UsuarioRestClient;
import br.com.spring_example.model.Usuario;

public class AddActivity extends AppCompatActivity {

    private EditText txtNome, txtEmail;
    private Button btnSalva, btnCancela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);

            txtEmail = findViewById(R.id.editTextEmail);
            txtNome = findViewById(R.id.editTextNome);

            btnSalva = findViewById(R.id.btn_salvar_usuario);
            btnSalva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        Usuario usuario = new Usuario();
                        usuario.setEmail(txtEmail.getText().toString());
                        usuario.setNome(txtNome.getText().toString());

                        boolean result = new HttpAdd().execute(usuario).get();

                        if (result) {

                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {

                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                            builder.setMessage("add failed");
                            builder.create().show();

                        }

                    } catch (Exception e) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        builder.setMessage(e.getMessage());
                        builder.create().show();

                    }
                }
            });


            btnCancela = findViewById(R.id.btn_cancelar);
            btnCancela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage(e.getMessage());
            builder.create().show();

        }
    }

    private class HttpAdd extends AsyncTask<Usuario, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Usuario... usuarios) {
            UsuarioRestClient usuarioRestClient = new UsuarioRestClient();
            return usuarioRestClient.save(usuarios[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
