package co.com.sofka.Challengesofka.repositorio;

import co.com.sofka.Challengesofka.model.Jugador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface JugadorRepositorio extends CrudRepository <Jugador, Integer>{
    List<Jugador> findByNombre(String nombre);
}
