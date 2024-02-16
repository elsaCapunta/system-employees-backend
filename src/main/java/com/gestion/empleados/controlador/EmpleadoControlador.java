package com.gestion.empleados.controlador;

import com.gestion.empleados.repositorio.EmpleadoRepositorio;
import com.gestion.empleados.modelo.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoRepositorio repositorio;

    @GetMapping("/empleados")
    public List<Empleado> ListarTodosLosEmpleados(){
        return repositorio.findAll();
    }

    @PostMapping("/empleados")
    public Empleado guardaEmpleado(@RequestBody Empleado empleado){
        return repositorio.save(empleado);
    }

}
