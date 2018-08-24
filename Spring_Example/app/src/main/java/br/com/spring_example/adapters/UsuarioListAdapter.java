package br.com.spring_example.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.spring_example.R;
import br.com.spring_example.model.Usuario;

public class UsuarioListAdapter extends ArrayAdapter<Usuario>{

    private List<Usuario> usuarios;
    private Context context;

    public UsuarioListAdapter(@NonNull Context context, List<Usuario> usuarios) {
        super(context, R.layout.usuario_layout, usuarios);
        this.usuarios = usuarios;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.usuario_layout, parent, false);

        Usuario usuario = this.usuarios.get(position);

        TextView textViewNome = view.findViewById(R.id.textViewName);
        textViewNome.setText(usuario.getNome());

        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        textViewEmail.setText(usuario.getEmail());

        return view;

    }
}
