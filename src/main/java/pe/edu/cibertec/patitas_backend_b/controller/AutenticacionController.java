package pe.edu.cibertec.patitas_backend_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.patitas_backend_b.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_b.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backend_b.service.AutenticacionService;

import java.time.Duration;
import java.util.Arrays;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest){

        try {

            Thread.sleep(Duration.ofSeconds(5));
            String[] datosusuario = autenticacionService.validarUsuario(loginRequest);
            System.out.println("Respuesta backend: " + Arrays.toString(datosusuario));
            if (datosusuario == null){
                return new LoginResponseDTO("01", "Error: Usuario no encontrado", "", "");
            }
            return new LoginResponseDTO("00", "", datosusuario[0], datosusuario[1]);

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return new LoginResponseDTO("99", "Error: Ocurrio un problema", "", "");
        }

    };

}
