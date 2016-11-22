package model;
// Generated Nov 22, 2016 1:56:10 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Triaje generated by hbm2java
 */
public class Triaje  implements java.io.Serializable {


     private BigDecimal codDiagnostico;
     private Diagnostico diagnostico;
     private String observacion;
     private BigDecimal peso;
     private BigDecimal presion;
     private String enfermera;

    public Triaje() {
    }

    public Triaje(Diagnostico diagnostico, String observacion, BigDecimal peso, BigDecimal presion, String enfermera) {
       this.diagnostico = diagnostico;
       this.observacion = observacion;
       this.peso = peso;
       this.presion = presion;
       this.enfermera = enfermera;
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
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
    public String getEnfermera() {
        return this.enfermera;
    }
    
    public void setEnfermera(String enfermera) {
        this.enfermera = enfermera;
    }




}


