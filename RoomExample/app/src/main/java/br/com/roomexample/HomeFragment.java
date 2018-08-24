package br.com.roomexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnAdduser, btnReadUser, btnDelete, btnUpdate;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnAdduser = view.findViewById(R.id.btn_add);
        btnAdduser.setOnClickListener(this);

        btnReadUser = view.findViewById(R.id.btn_view);
        btnReadUser.setOnClickListener(this);

        btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);

        btnUpdate = view.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new AddUserFragment()).addToBackStack(null)
                        .commit();
                break;

            case R.id.btn_view:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new ReadUserFragment()).addToBackStack(null)
                        .commit();
                break;

            case R.id.btn_delete:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new DeleteUserFragment()).addToBackStack(null)
                        .commit();
                break;

            case R.id.btn_update:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new UpdateFragment()).addToBackStack(null)
                        .commit();
                break;
        }
    }

}




