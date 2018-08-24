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

public class EditActivity extends AppCompatActivity {


    private EditText txtNome, txtEmail;
    private Button btnSalva, btnCancela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit);

            txtEmail = findViewById(R.id.editTextEmail);
            txtNome = findViewById(R.id.editTextNome);

            Intent intent = getIntent();
            final Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
            txtNome.setText(usuario.getNome());
            txtEmail.setText(usuario.getEmail());


            btnSalva = findViewById(R.id.btn_salvar_usuario);
            btnSalva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        usuario.setNome(txtNome.getText().toString());
                        usuario.setEmail(txtEmail.getText().toString());

                        boolean result = new HttpRequestUpdate().execute(usuario).get();
                        if (result) {
                            Intent intent1 = new Intent(EditActivity.this, MainActivity.class);
                            startActivity(intent1);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                            builder.setMessage("update failed");
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
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage(e.getMessage());
            builder.create().show();
        }
    }

    private class HttpRequestUpdate extends AsyncTask<Usuario, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Usuario... usuarios) {
            UsuarioRestClient usuarioRestClient = new UsuarioRestClient();
            return usuarioRestClient.update(usuarios[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
