package br.com.roomexample.database;

import android.arch.persistence.room.*;

import br.com.roomexample.dao.UsuarioDao;
import br.com.roomexample.model.Usuario;

@Database(entities = {Usuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract UsuarioDao usuarioDao();

}
