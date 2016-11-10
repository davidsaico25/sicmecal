package model;
// Generated Nov 10, 2016 6:17:23 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Triaje generated by hbm2java
 */
public class Triaje  implements java.io.Serializable {


     private BigDecimal codDiagnostico;
     private Diagnostico diagnostico;
     private BigDecimal peso;
     private BigDecimal presion;

    public Triaje() {
    }

    public Triaje(Diagnostico diagnostico, BigDecimal peso, BigDecimal presion) {
       this.diagnostico = diagnostico;
       this.peso = peso;
       this.presion = presion;
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
    public BigDecimal getPeso() {
        return this.peso;
    }
    
    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }
    public BigDecimal getPresion() {
        return this.presion;
    }
    
    public void setPresion(BigDecimal presion) {
        this.presion = presion;
    }




}


