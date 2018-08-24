package br.com.roomexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.roomexample.model.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {

    private TextView txtInfo;

    public ReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_user, container, false);

        txtInfo = view.findViewById(R.id.txt_display_info);

        List<Usuario> usuarios = MainActivity.appDatabase.usuarioDao().getAll();

        String info = "";

        for (Usuario usuario : usuarios) {
            int id = usuario.getId();
            String nome = usuario.getNome();
            String email = usuario.getEmail();

            info = info +"\n\n" + usuario.toString();
        }

        txtInfo.setText(info);
        return view;
    }

}
