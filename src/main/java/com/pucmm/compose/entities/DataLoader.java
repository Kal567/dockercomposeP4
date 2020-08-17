package com.pucmm.compose.entities;

import com.pucmm.compose.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void run(ApplicationArguments args) {
        //ADMINS
        usuarioRepository.save(new Usuario(1, "admin", "juanmartinez@gmail.com", "admin", true));
        usuarioRepository.save(new Usuario(2, "admin2", "juanmartinez2@gmail.com", "admin2", true));

        //USUARIOS NORMALES
        usuarioRepository.save(new Usuario(3, "usuario", "lucypineda@gmail.com", "usuario", false));
        usuarioRepository.save(new Usuario(4, "usuario2", "lucypineda2@gmail.com", "usuario2", false));

    }
}
