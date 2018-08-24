package br.com.roomexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.roomexample.model.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    private EditText txtNome, txtEmail, txtId;
    private Button btnUpdate;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        txtEmail = view.findViewById(R.id.txt_user_email);
        txtId = view.findViewById(R.id.txt_user_id);
        txtNome = view.findViewById(R.id.txt_user_nome);

        btnUpdate = view.findViewById(R.id.btn_update_user);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtId.getText().toString());
                String nome = txtNome.getText().toString();
                String email = txtEmail.getText().toString();

                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setEmail(email);
                usuario.setNome(nome);

                MainActivity.appDatabase.usuarioDao().update(usuario);

                Toast.makeText(getContext(), "Usuario atualizado", Toast.LENGTH_LONG).show();

                txtEmail.setText("");
                txtId.setText("");
                txtNome.setText("");
            }
        });

        return view;
    }

}
