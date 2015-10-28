/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author pc
 */
public class Mago {
    private String nombre;
    private Poder poder;
    private Monster monstuoAmigo;
    static private int TotalMagos;
    
    public Mago(String n,Poder p, Monster m ){
       nombre=n;
       poder=p;
       monstuoAmigo=m;
    
    }
     @Override
     public String toString(){
        return "Nombre: "+this.nombre+", "+ 
               "poder: "+poder.toString()+", "+
               "monstruo amigo: "+"{"+this.monstuoAmigo.toString()+"}"+", "+
               "Mal Rollo: "+"{"+Integer.toString(TotalMagos)+"}";
    }
}
