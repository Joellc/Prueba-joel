package co.com.sofka.Challengesofka.servicio;

import co.com.sofka.Challengesofka.model.Categoria;
import co.com.sofka.Challengesofka.model.Pregunta;
import co.com.sofka.Challengesofka.repositorio.PreguntaRepositorio;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PreguntaServicio {
    @Resource
    private PreguntaRepositorio preguntaRepositorio;

    public Pregunta createPregunta(Pregunta pregunta) { return preguntaRepositorio.save(pregunta); }

    public List<Pregunta> findAll() { return (List<Pregunta>) preguntaRepositorio.findAll(); }

    public List<Pregunta> findByCategoria(Categoria categoria) { return preguntaRepositorio.findByCategoria(categoria); }

}