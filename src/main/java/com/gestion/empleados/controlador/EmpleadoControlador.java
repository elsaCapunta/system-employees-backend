package com.gestion.empleados.controlador;

import com.gestion.empleados.exepciones.ResourceNotFoundException;
import com.gestion.empleados.repositorio.EmpleadoRepositorio;
import com.gestion.empleados.modelo.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id){
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("no se encontro el empleado con el id" +id));
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado detalleEmpleado){
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("no se encontro el empleado con el id" +id));

        empleado.setNombre(detalleEmpleado.getNombre());
        empleado.setApellido(detalleEmpleado.getApellido());
        empleado.setEmail(detalleEmpleado.getEmail());

        Empleado empleadoActualizado = repositorio.save(empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));

        repositorio.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
