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
public class DeleteUserFragment extends Fragment {

    private EditText txtUserCodigo;
    private Button btnDelete;

    public DeleteUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_user, container, false);

        btnDelete = view.findViewById(R.id.btn_delete_user);
        txtUserCodigo = view.findViewById(R.id.userId);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtUserCodigo.getText().toString());

                Usuario usuario = new Usuario();
                usuario.setId(id);

                MainActivity.appDatabase.usuarioDao().delete(usuario);

                Toast.makeText(getActivity(), "User deleted", Toast.LENGTH_LONG).show();
                txtUserCodigo.setText("");
            }
        });

        return view;
    }

}
