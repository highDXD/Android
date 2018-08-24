package br.com.roomexample.dao;

import android.arch.persistence.room.*;

import java.util.List;

import br.com.roomexample.model.Usuario;

@Dao
public interface UsuarioDao {

    @Insert
    void add(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Query("SELECT * FROM usuario ORDER BY id ASC")
    List<Usuario> getAll();
}
