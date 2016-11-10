package model;
// Generated Nov 10, 2016 6:17:23 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * RecetaMedica generated by hbm2java
 */
public class RecetaMedica  implements java.io.Serializable {


     private BigDecimal codDiagnostico;
     private Diagnostico diagnostico;
     private Set detalleRecetaMedicas = new HashSet(0);

    public RecetaMedica() {
    }

	
    public RecetaMedica(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
    public RecetaMedica(Diagnostico diagnostico, Set detalleRecetaMedicas) {
       this.diagnostico = diagnostico;
       this.detalleRecetaMedicas = detalleRecetaMedicas;
    }
   
    public BigDecimal getCodDiagnostico() {
        return this.codDiagnostico;
    }
    
    public void setCodDiagnostico(BigDecimal codDiagnostico) {
        this.codDiagnostico = codDiagnostico;
    }
    public Diagnostico getDiagnostico() {
        return this.diagnostico;
    }
    
    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
    public Set getDetalleRecetaMedicas() {
        return this.detalleRecetaMedicas;
    }
    
    public void setDetalleRecetaMedicas(Set detalleRecetaMedicas) {
        this.detalleRecetaMedicas = detalleRecetaMedicas;
    }




}


