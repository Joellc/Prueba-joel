package co.com.sofka.Challengesofka.model;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nivel;

    public Categoria() {

    }

    public Categoria(Integer nivel) { this.nivel = nivel; }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}
