package br.com.spring_example;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.spring_example.clients.UsuarioRestClient;
import br.com.spring_example.model.Usuario;

public class DetailActivity extends AppCompatActivity {


    private TextView txtNome, txtEmail;
    private Button btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);

            txtNome = findViewById(R.id.textViewNome);
            txtEmail = findViewById(R.id.textViewEmail);


            Intent intent = getIntent();
            final Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");

            txtNome.setText(usuario.getNome());
            txtEmail.setText(usuario.getEmail());


            btnEdit = findViewById(R.id.buttonEdit);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(DetailActivity.this, EditActivity.class);
                    intent2.putExtra("usuario", usuario);
                    startActivity(intent2);
                }
            });


            btnDelete = findViewById(R.id.buttonDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setCancelable(false);
                        builder.setTitle("Confirm");
                        builder.setMessage("are u sure?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {

                                    boolean result = new HttpRequestDelete().execute(usuario.getId()).get();
                                    if (result) {

                                        Intent intent1 = new Intent(DetailActivity.this, MainActivity.class);
                                        startActivity(intent1);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                        builder.setMessage("delete failed");
                                        builder.create().show();
                                    }
                                } catch (Exception e) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                    builder.setMessage(e.getMessage());
                                    builder.create().show();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.create().show();


                    } catch (Exception e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        builder.setMessage(e.getMessage());
                        builder.create().show();
                    }

                }
            });
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage(e.getMessage());
            builder.create().show();
        }
    }

    private class HttpRequestDelete extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... integers) {
            UsuarioRestClient usuarioRestClient = new UsuarioRestClient();
            return usuarioRestClient.delete(integers[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

}
