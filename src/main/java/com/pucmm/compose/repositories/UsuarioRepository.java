package com.pucmm.compose.repositories;

import com.pucmm.compose.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
