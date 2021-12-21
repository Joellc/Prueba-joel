package co.com.sofka.Challengesofka.servicio;

import co.com.sofka.Challengesofka.model.Categoria;
import co.com.sofka.Challengesofka.repositorio.CategoriaRepositorio;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoriaServicio {
    @Resource
    private CategoriaRepositorio categoriaRepositorio;

    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    public List<Categoria> findAll() {
        return (List<Categoria>) categoriaRepositorio.findAll();

    }
}
