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
public class AddUserFragment extends Fragment {
    private EditText txtNome, txtEmail;
    private Button btnSave;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        txtEmail = view.findViewById(R.id.txtEmail);
        txtNome = view.findViewById(R.id.txtNome);
        btnSave = view.findViewById(R.id.btn_add_user);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String nome = txtNome.getText().toString();

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setNome(nome);

                MainActivity.appDatabase.usuarioDao().add(usuario);

                Toast.makeText(getActivity(), "Usuario salvo", Toast.LENGTH_LONG).show();

                usuario.setNome("");
                usuario.setEmail("");
            }
        });

        return view;
    }

}
