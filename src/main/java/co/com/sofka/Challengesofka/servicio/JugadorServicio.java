package co.com.sofka.Challengesofka.servicio;

import co.com.sofka.Challengesofka.model.Jugador;
import co.com.sofka.Challengesofka.repositorio.JugadorRepositorio;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JugadorServicio {
    @Resource
    private JugadorRepositorio jugadorRepositorio;

    public Jugador createJugador(Jugador jugador) {
        return jugadorRepositorio.save(jugador);
    }

    public List<Jugador> findAll() {
        return (List<Jugador>) jugadorRepositorio.findAll();
    }

    public Jugador findByNombre(String nombre) {
        List<Jugador> jugadores = jugadorRepositorio.findByNombre(nombre);
        return !jugadores.isEmpty() ? jugadores.get(0) : null;
    }

}