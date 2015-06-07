/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.alcreta.ahorcado;

import java.util.List;
import net.alcreta.ayuda.Aleatorio;
import net.alcreta.ayuda.ControlFicheros;
import net.alcreta.gui.Gui;
import net.alcreta.jugador.Jugador;

/**
 * Clase que lleva toda la logica del juego
 * @author Alvaro
 */
public class Ahorcado {

    private List<String> palabras = null;
    private String palabraActual = "";
    private String palabraOculta = "";
    private int fallos = 0;
    private final ControlFicheros oControlFicheros = new ControlFicheros("./", "palabras.txt","jugadores.bin");
    private Jugador oJugador;
    private List<Jugador> jugadores = null;

    public String getPalabraOculta() {
        return palabraOculta;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public Jugador getoJugador() {
        return oJugador;
    }

    /**
     * Resetea las variables y carga una nueva palabra
     */
    public void nuevoJuego() {
        palabraActual = "";
        palabraOculta = "";
        fallos = 0;
        palabraActual = palabras.get(Aleatorio.getNumeroAleatorio(palabras.size() - 1, 0));
        ocultarPalabra();

    }

    /**
     * Crea un String de '_' con la misma longitud que la palabra a ocultar
     */
    public void ocultarPalabra() {
        palabraOculta = "";
        for (int i = 0; i < palabraActual.length(); i++) {
            palabraOculta += "_";
        }
    }

    /**
     * Comprueba que la letra esta contenida en el String, no distinge entre
     * mayusculas o minusculas
     *
     * @param letra Char a buscar
     * @return Retorna si la letra estaba en el String
     */
    public boolean comprobacion(char letra) {
        boolean acierto = false;
        String aux = "";
        for (int i = 0; i < palabraActual.length(); i++) {
            if (Character.toLowerCase(letra) == Character.toLowerCase(palabraActual.charAt(i))) {
                aux += palabraActual.charAt(i);
                acierto = true;
            } else {
                aux += palabraOculta.charAt(i);
            }
        }
        if (acierto) {
            palabraOculta = aux;
            oJugador.setLetrasAcertadas(oJugador.getLetrasAcertadas()+1);
        } else {
            fallos++;
            oJugador.setLetrasFalladas(oJugador.getLetrasFalladas()+1);
        }
        return acierto;
    }

    /**
     * Carga los Strings contenidos en el ficheros en el array
     */
    public void cargarPalabras() {
        palabras = oControlFicheros.textToArray();
    }

    /**
     * Añade el String al fichero que almacena todos los Strings
     *
     * @param palabra String a añadir
     */
    public void añadirPalabra(String palabra) {
        if (buscarPalabra(palabra) == -1) {
            palabras.add(palabra);
            oControlFicheros.arrayToText(palabras);
            Gui.mensaje("Se ha añadido la palabra: " + palabra);
        } else {
            Gui.mensaje("Ya existe esta palabra: " + palabra);
        }
    }

    /**
     * Elimina el String en el fichero que almacena todos los Strings
     *
     * @param palabra String a eliminar
     */
    public void eliminarPalabra(String palabra) {
        int id = buscarPalabra(palabra);
        if (id != -1) {
            palabras.remove(id);
            oControlFicheros.arrayToText(palabras);
            Gui.mensaje("Se ha eliminado la palabra: " + palabra);
        } else {
            Gui.mensaje("La palabra '" + palabra + "' no existe");
        }
    }

    /**
     * Busca la palabra en el array que contiene todas los Strings del fichero
     *
     * @param buscarPalabra String a buscar
     * @return Retorna un numero entero con la id(key) del array o si no se ha
     * encontrado retornará -1
     */
    public int buscarPalabra(String buscarPalabra) {
        for (int i = 0; i < palabras.size(); i++) {
            if (palabras.get(i).equalsIgnoreCase(buscarPalabra)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Inicia sesion o crea un usuario en el caso de que no exista
     * @param usuario Nombre de Usuario
     * @param contrasenya La contraseña del usuario
     * @return Retrona 0 cuando el jugador no existe y se crea, 1 cuando se logea y -1 cuando la contraseña es erronea
     */
    public int iniciarJugador(String usuario, String contrasenya) {
        int id = buscarJugador(usuario);
        if (id == -1) {
            oJugador = new Jugador(usuario, contrasenya);
            jugadores.add(oJugador);
            oControlFicheros.arrayToBinario(jugadores);
            return 0;
        } else {
            oJugador = jugadores.get(id);
            if(oJugador.getContrasenya().equals(contrasenya)){
                return 1;
            } else{
                return -1;
            }
        }
    }
    
    /**
     * Busca a un Jugador en el Array list
     * @param buscarJugador Nombre del jugador a buscar
     * @return Retorna la posición del jugador a buscar
     */
    public int buscarJugador(String buscarJugador) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getUsuario().equalsIgnoreCase(buscarJugador)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Cargar los jugadores almacenados en el fichero y los ponemos en el array list
     */
    public void cargarJugadores(){
        jugadores = oControlFicheros.binarioToArray();
    }
    
    public void guardarJugador(){
        jugadores.add(buscarJugador(oJugador.getUsuario()), oJugador);
        oControlFicheros.arrayToBinario(jugadores);
    }
    
}
