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
    private CategoriaServicio CategoriaServicio;

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
        System.out.println("Lo siento, pero la entrada1ión digitada no es válida ¡Suerte para la próxima!");
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
                play();
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
        System.out.println("**************************************************");
        System.out.println("¡Bienvenido al concurso de preguntas y respuestas!");
        System.out.println("**************************************************");
        System.out.println("1. Instrucciones");
        System.out.println("2. Jugar");
        System.out.println("3. Puntajes Históricos");
        System.out.println("4. Salir");
        System.out.println("**************************************************");
        System.out.println("Por favor digita una entrada1ión entre las anteriores: ");
        System.out.println("**************************************************");
    }

    public void play() {
        System.out.println("Por favor ingresa tu nombre para continuar: ");
        String nombre = in.next();
        Jugador jugador = jugadorServicio.findByName(nombre);
        if (jugador == null) {
            Jugador newJugador = new Jugador();
            newJugador.setNombre(nombre);
            jugador = jugadorServicio.createJugador(newJugador);
        }

        Juego juego = new Juego();
        juego.setJugador(jugador);
        Integer puntaje = 0;
        int CategoriaNumber = 1;
        boolean answer = false;
        do {
            answer = askQuestion(new Categoria(CategoriaNumber));
            if (answer) {
                puntaje+=200;
                if (puntaje < 1000) {
                    System.out.println("Felicidades, quieres seguir jugando (si) " +
                            "o te retiras con el acumulado de " + puntaje + ". (no)");
                    answer = in.next().equals("si");
                } else {
                    System.out.println("Felicidades, has ganado el juego! Tu score fue: " + puntaje);
                }
                CategoriaNumber++;
            } else {
                System.out.println("Respuesta Incorrecta. Vuelva a intentarlo." );
                puntaje = 0;
            }
        } while(answer && CategoriaNumber < 6);

        juego.setPuntaje(puntaje);
        juegoServicio.createJuego(juego);

    }

    private boolean askQuestion(Categoria Categoria) {
        List<Pregunta> preguntasCategoriaUno = preguntaServicio.findByCategoria(Categoria);
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
        System.out.println("INSTRUCCIONES: El concurso consiste en una serie de 5 preguntas de selección múltiple con una única respuesta, por cada ronda acertada acumularás 200 puntos, y antes de la siguiente pregunta podrás decidir si te retiras con el acumulado o continuas. Si decides continuar y tu respuesta es incorrecta el juego finaliza inmediatamente perdiendo la cantidad de puntos acumulados. Finalmente, ganarás el juego si acumulas 1000 puntos.");
    }
}
