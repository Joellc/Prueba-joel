package co.com.sofka.Challengesofka.servicio;

import co.com.sofka.Challengesofka.model.Juego;
import co.com.sofka.Challengesofka.repositorio.JuegoRepositorio;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JuegoServicio {
    @Resource
    private JuegoRepositorio juegoRepositorio;

    public Juego createJuego(Juego juego) {
        return juegoRepositorio.save(juego);
    }

    public List<Juego> findAll() {
        return (List<Juego>) juegoRepositorio.findAll();
    }

}