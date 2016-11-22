package model;
// Generated Nov 22, 2016 11:28:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Medicamento generated by hbm2java
 */
public class Medicamento  implements java.io.Serializable {


     private String codMedicamento;
     private String nombre;
     private String descripcion;
     private BigDecimal stock;
     private Set detalleRecetaMedicas = new HashSet(0);

    public Medicamento() {
    }

	
    public Medicamento(String codMedicamento, String nombre, String descripcion, BigDecimal stock) {
        this.codMedicamento = codMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
    }
    public Medicamento(String codMedicamento, String nombre, String descripcion, BigDecimal stock, Set detalleRecetaMedicas) {
       this.codMedicamento = codMedicamento;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.stock = stock;
       this.detalleRecetaMedicas = detalleRecetaMedicas;
    }
   
    public String getCodMedicamento() {
        return this.codMedicamento;
    }
    
    public void setCodMedicamento(String codMedicamento) {
        this.codMedicamento = codMedicamento;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public BigDecimal getStock() {
        return this.stock;
    }
    
    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }
    public Set getDetalleRecetaMedicas() {
        return this.detalleRecetaMedicas;
    }
    
    public void setDetalleRecetaMedicas(Set detalleRecetaMedicas) {
        this.detalleRecetaMedicas = detalleRecetaMedicas;
    }




}


