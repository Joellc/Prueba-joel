package co.com.sofka.Challengesofka.servicio;

import co.com.sofka.Challengesofka.model.Opcion;
import co.com.sofka.Challengesofka.repositorio.OpcionRepositorio;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OpcionServicio {
    @Resource
    private OpcionRepositorio opcionRepositorio;

    public Opcion createOpcion(Opcion opcion) {
        return opcionRepositorio.save(opcion);
    }

    public List<Opcion> findAll() {
        return (List<Opcion>) opcionRepositorio.findAll();
    }
}