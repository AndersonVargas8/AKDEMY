package com.app.akdemy.config;
import com.app.akdemy.entity.Role;
import com.app.akdemy.entity.TipoDocumento;
import com.app.akdemy.entity.User;
import com.app.akdemy.repository.RoleRepository;
import com.app.akdemy.repository.TipoDocumentoRepository;
import com.app.akdemy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;

// Solo se ejecuta una vez que se inicia la aplicacion
/*
@Configuration
public class Database {
    @Bean
    CommandLineRunner addRoles(RoleRepository roleRepository) {
        return args -> {
            Role estudiante = new Role("ESTUDIANTE","ROLE_ESTUDIANTE");
            Role profesor = new Role("PROFESOR", "ROLE_PROFESOR");
            Role admin = new Role("ADMIN", "ROLE_ADMIN");
            Role acudiente = new Role("ACUDIENTE", "ROLE_ACUDIENTE");
            Role coordinador = new Role("COORDINADOR", "ROLE_COORDINADOR");
            ArrayList<Role> roles = new ArrayList<>();


            roles.add(admin);
            roles.add(coordinador);
            roles.add(profesor);
            roles.add(acudiente);
            roles.add(estudiante);

            roleRepository.saveAll(roles);

        };
    }
}
*/
