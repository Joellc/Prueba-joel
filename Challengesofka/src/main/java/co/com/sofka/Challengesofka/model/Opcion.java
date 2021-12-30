package co.com.sofka.Challengesofka.model;

import javax.persistence.*;

@Entity
@Table(name = "opciones")
public class Opcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String texto;
    @Column(name = "opcion_correcta")
    private String opcionCorrecta;

    @OneToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getTexto() {return texto;}

    public void setTexto(String texto) {this.texto = texto;}

    public String getOpcionCorrecta() {return opcionCorrecta;}

    public void setOpcionCorrecta(String opcionCorrecta) {this.opcionCorrecta = opcionCorrecta;}

    public Pregunta getPregunta() {return pregunta;}

    public void setPregunta(Pregunta pregunta) {this.pregunta = pregunta;}

}