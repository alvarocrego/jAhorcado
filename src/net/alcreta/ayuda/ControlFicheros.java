/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.alcreta.ayuda;

import java.io.EOFException;
import net.alcreta.gui.Gui;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.alcreta.jugador.Jugador;

/**
 * Clase que controla los fichero de la aplicación
 * @author Alvaro
 */
public class ControlFicheros {

    private final String ruta;
    private final String ficheroPalabras;
    private final String ficheroJugadores;

    /**
     * Construye la clase añadiendo la ruta, y el nombre de los ficheros a controlar
     * @param ruta Ruta donde estarán los ficheros
     * @param ficheroPalabras Nombre del fichero que almacena las palabras del ahorcado
     * @param ficheroJugadores Nombre del fichero que almacena los jugadores
     */
    public ControlFicheros(String ruta, String ficheroPalabras, String ficheroJugadores) {
        this.ruta = ruta;
        this.ficheroPalabras = ficheroPalabras;
        this.ficheroJugadores = ficheroJugadores;
    }

    /**
     * Lee el fichero de texto y lo combierte en array
     * @return Retorna un array list con las palabras contenidas en el fichero
     */
    public List<String> textToArray() {
        List<String> array = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(ficheroPalabras))) {
            while (sc.hasNext()) {
                array.add(sc.next());
            }
        } catch (FileNotFoundException e) {
            Gui.mensaje("No se encontro el fichero");
            System.exit(0);
        }
        return array;
    }
    
    /**
     * Convierte un array list de strings en un fichero de texto
     * @param palabras Array list ha almazenar
     */
    public void arrayToText(List<String> palabras) {
        FileWriter ficheroEscribir = null;
        try {
            ficheroEscribir = new FileWriter(ruta + ficheroPalabras);
            PrintWriter pw = new PrintWriter(ficheroEscribir);
            palabras.stream().forEach((palabra) -> {
                pw.println(palabra);
            });
        } catch (IOException ex) {
            Gui.mensaje("Error de escritura");
        } finally {
            if (ficheroEscribir != null) {
                try {
                    ficheroEscribir.close();
                } catch (IOException ex) {
                    Logger.getLogger(ControlFicheros.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    
    /**
     * Convierte un array list de objetos Jugador en un fichero binario
     * @param jugadores Array list de Objetos jugador a convertir
     */
    public void arrayToBinario(List<Jugador> jugadores) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ruta+ficheroJugadores));
            for (Jugador jugador : jugadores) {
                oos.writeObject(jugador);
            }
        } catch (FileNotFoundException e) {
            Gui.mensaje("arrayToBinario Error abriendo el fichero");
        } catch (IOException e) {
            Gui.mensaje("arrayToBinario Error escribiendo en el fichero");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(ControlFicheros.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Lee el fichero binario y almacena los objetos Jugador en el fichero
     * @return Retorna el array list con los objetos Jugador
     */
    public List<Jugador> binarioToArray() {
        ObjectInputStream ois = null;
        List<Jugador> jugadores = null;
        try {
            jugadores = new ArrayList<>();
            ois = new ObjectInputStream(new FileInputStream(ruta+ficheroJugadores));
            try {
                while (true) {
                    jugadores.add((Jugador) ois.readObject());
                }
            } catch (EOFException e) {
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControlFicheros.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException e) {
            return jugadores;
        } catch (IOException e) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ControlFicheros.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return jugadores;
    }
}
