package co.com.sofka.Challengesofka.vista;

import co.com.sofka.Challengesofka.model.Categoria;
import co.com.sofka.Challengesofka.model.Juego;
import co.com.sofka.Challengesofka.model.Jugador;
import co.com.sofka.Challengesofka.model.Pregunta;
import co.com.sofka.Challengesofka.servicio.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class Menu implements CommandLineRunner {
    @Resource
    private CategoriaServicio categoriaServicio;

    @Resource
    private JugadorServicio jugadorServicio;

    @Resource
    private PreguntaServicio preguntaServicio;

    @Resource
    private JuegoServicio juegoServicio;

    @Resource
    private OpcionServicio opcionServicio;

    private Scanner in = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        this.run();
    }

    public void exit() {
        System.out.println("Lo siento, pero la entrada digitada no es válida, Sigue intentando");
        System.exit(0);
    }

    public void run() {
        int entrada = 0;
        int entrada1 = 0;
        do {
            showMenu();
            try {
                entrada = in.nextInt();
            } catch (Exception e) {
                exit();
            }
            if (entrada == 1) {
                showInstructions();
                System.out.println("Presiona 1 para regresar al menú anterior o 2 para salir");
                try {
                    entrada1 = in.nextInt();
                } catch (Exception e) {
                    exit();
                }
                if (entrada1 == 1) {
                    continue;
                }
                if (entrada1 == 2) {
                    System.out.println("El programa ha finalizado satisfactoriamente");
                    System.exit(0);
                } else {
                    exit();
                }
            } else if (entrada == 2) {
                juego();
            } else if (entrada == 3) {
                imprimirPuntaje();
                System.out.println("Presiona 1 para regresar al menú anterior o 2 para salir");
                try {
                    entrada1 = in.nextInt();
                } catch (Exception e) {
                    exit();
                }
                if (entrada1 == 2) {
                    System.out.println("El programa ha finalizado satisfactoriamente");
                    System.exit(0);
                } else {
                    continue;
                }
            } else if (entrada == 4) {
                System.out.println("El programa ha finalizado satisfactoriamente");
                System.exit(0);
            } else {
                exit();
                continue;
            }
        } while (entrada != 4);
        in.close();
    }

    public void showMenu() {
        System.out.println("¡Bienvenido al concurso de preguntas y respuestas!");
        System.out.println("1. Instrucciones");
        System.out.println("2. Jugar");
        System.out.println("3. Puntajes Históricos");
        System.out.println("4. Salir");
        System.out.println("Por favor digita una entrada1ión entre las anteriores: ");
    }





    public void juego() {
        System.out.println("Nombre del Jugador: ");
        String nombre = in.next();
        Jugador jugador = jugadorServicio.findByNombre(nombre);
        if (jugador == null) {
            Jugador newJugador = new Jugador();
            newJugador.setNombre(nombre);
            jugador = jugadorServicio.createJugador(newJugador);
        }

        Juego juego = new Juego();
        juego.setJugador(jugador);
        Integer puntaje = 0;
        int categoriaNumber = 1;
        boolean answer = false;
        do {
            answer = askQuestion(new Categoria(categoriaNumber));
            if (answer) {
                puntaje+=200;
                if (puntaje < 1000) {
                    System.out.println("Felicidades, ¿deseas continuar? (si) " +
                            "ó finalizar " + puntaje + ". (no)");
                    answer = in.next().equals("si");
                } else {
                    System.out.println("Eres ganador! Puntaje: " + puntaje);
                }
                categoriaNumber++;
            } else {
                System.out.println("Incorrecta. Intenta de nuevo." );
                puntaje = 0;
            }
        } while(answer && categoriaNumber < 6);

        juego.setPuntaje(puntaje);
        juegoServicio.createJuego(juego);

    }

    private boolean askQuestion(Categoria categoria) {
        List<Pregunta> preguntasCategoriaUno = preguntaServicio.findByCategoria(categoria);
        Random rand = new Random();
        Pregunta pregunta = preguntasCategoriaUno.get(rand.nextInt(preguntasCategoriaUno.size()));
        System.out.println(pregunta.getTexto());
        System.out.println(pregunta.getOpcion().getTexto());
        String answer = in.next();
        return pregunta.getOpcion().getOpcionCorrecta().equalsIgnoreCase(answer);
    }


    public void imprimirPuntaje() {
        List<Juego> juegos = juegoServicio.findAll();
        for (Juego juego : juegos) {
            System.out.println("Jugador: " + juego.getJugador().getNombre() + " - Score: " + juego.getPuntaje());
        }
    }

    public void showInstructions() {
        System.out.println("INSTRUCCIONES: Son 5 preguntas de A,B,C y D; una sola de estas es correcta, Si acertas ganas 200mil pesos, Si responde correctamente puedes abandonar el juego con lo ganado .Si continuas y te equivocas en la respuesta perderas todo . Ganara el juegc cuando acomules 1 millon de pesos");
    }
}
