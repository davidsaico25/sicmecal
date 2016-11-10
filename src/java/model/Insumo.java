package model;
// Generated Nov 10, 2016 6:17:23 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Insumo generated by hbm2java
 */
public class Insumo  implements java.io.Serializable {


     private BigDecimal codinsumo;
     private String nombre;
     private BigDecimal cantidad;
     private BigDecimal cantMinima;

    public Insumo() {
    }

    public Insumo(BigDecimal codinsumo, String nombre, BigDecimal cantidad, BigDecimal cantMinima) {
       this.codinsumo = codinsumo;
       this.nombre = nombre;
       this.cantidad = cantidad;
       this.cantMinima = cantMinima;
    }
   
    public BigDecimal getCodinsumo() {
        return this.codinsumo;
    }
    
    public void setCodinsumo(BigDecimal codinsumo) {
        this.codinsumo = codinsumo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public BigDecimal getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
    public BigDecimal getCantMinima() {
        return this.cantMinima;
    }
    
    public void setCantMinima(BigDecimal cantMinima) {
        this.cantMinima = cantMinima;
    }




}


