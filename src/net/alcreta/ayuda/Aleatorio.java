/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.alcreta.ayuda;

/**
 * Clase que genera contiene un metodo que genera numeros aleatorios
 * @author Alvaro
 */
public class Aleatorio {
    
    /**
     * 
     * @param m Valor Maximo
     * @param n Valor Minimo
     * @return Numero Aleatorio
     */
    public static int getNumeroAleatorio(int m, int n){
       return (int) Math.floor(Math.random()*(n-m+1)+m);
    }
    
    
}
