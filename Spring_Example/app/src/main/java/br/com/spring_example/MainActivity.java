package br.com.spring_example;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.spring_example.adapters.UsuarioListAdapter;
import br.com.spring_example.clients.UsuarioRestClient;
import br.com.spring_example.model.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            final List<Usuario> usuarios = new HttpRequestUsuario().execute().get();
            ListView listViewUsuarios = findViewById(R.id.listViewUsuario);
            listViewUsuarios.setAdapter(new UsuarioListAdapter(getApplicationContext(), usuarios));

            listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("usuario", usuarios.get(position));
                    startActivity(intent);
                }
            });


            Button btnAdd = findViewById(R.id.buttonAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddActivity.class);
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage(e.getMessage());
            builder.create().show();
        }
    }

    private class HttpRequestUsuario extends AsyncTask<Void, Void, List<Usuario>> {

        @Override
        protected List<Usuario> doInBackground(Void... voids) {
            UsuarioRestClient usuarioRestClient = new UsuarioRestClient();
            return usuarioRestClient.findAll();
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);
        }
    }
}
