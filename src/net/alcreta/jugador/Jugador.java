/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.alcreta.jugador;

import java.io.Serializable;

/**
 * Clase jugador que contiene todos sus datos
 * @author alcreta
 */
public class Jugador implements Serializable{
    String usuario, contrasenya;
    int palabrasAcertadas = 0, palabrasFalladas = 0, letrasAcertadas = 0, letrasFalladas = 0;

    public Jugador(String usuario, String contrasenya) {
        this.usuario = usuario;
        this.contrasenya = contrasenya;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public int getPalabrasAcertadas() {
        return palabrasAcertadas;
    }

    public void setPalabrasAcertadas(int palabrasAcertadas) {
        this.palabrasAcertadas = palabrasAcertadas;
    }

    public int getPalabrasFalladas() {
        return palabrasFalladas;
    }

    public void setPalabrasFalladas(int palabrasFalladas) {
        this.palabrasFalladas = palabrasFalladas;
    }

    public int getLetrasAcertadas() {
        return letrasAcertadas;
    }

    public void setLetrasAcertadas(int letrasAcertadas) {
        this.letrasAcertadas = letrasAcertadas;
    }

    public int getLetrasFalladas() {
        return letrasFalladas;
    }

    public void setLetrasFalladas(int letrasFalladas) {
        this.letrasFalladas = letrasFalladas;
    }
    
    
}
