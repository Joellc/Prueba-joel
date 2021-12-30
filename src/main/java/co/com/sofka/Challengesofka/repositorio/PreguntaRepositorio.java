package co.com.sofka.Challengesofka.repositorio;

import co.com.sofka.Challengesofka.model.Categoria;
import co.com.sofka.Challengesofka.model.Pregunta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PreguntaRepositorio extends CrudRepository <Pregunta, Integer> {
    List<Pregunta> findByCategoria(Categoria categoria);
}