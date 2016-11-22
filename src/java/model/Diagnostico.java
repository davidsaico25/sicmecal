package model;
// Generated Nov 22, 2016 1:56:10 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * Diagnostico generated by hbm2java
 */
public class Diagnostico  implements java.io.Serializable {


     private BigDecimal codDiagnostico;
     private HistorialClinico historialClinico;
     private String descripcion;
     private String medico;
     private Date fecha;
     private RecetaMedica recetaMedica;
     private Triaje triaje;
     private OrdenMedico ordenMedico;

    public Diagnostico() {
    }

	
    public Diagnostico(BigDecimal codDiagnostico, HistorialClinico historialClinico, String descripcion, String medico, Date fecha) {
        this.codDiagnostico = codDiagnostico;
        this.historialClinico = historialClinico;
        this.descripcion = descripcion;
        this.medico = medico;
        this.fecha = fecha;
    }
    public Diagnostico(BigDecimal codDiagnostico, HistorialClinico historialClinico, String descripcion, String medico, Date fecha, RecetaMedica recetaMedica, Triaje triaje, OrdenMedico ordenMedico) {
       this.codDiagnostico = codDiagnostico;
       this.historialClinico = historialClinico;
       this.descripcion = descripcion;
       this.medico = medico;
       this.fecha = fecha;
       this.recetaMedica = recetaMedica;
       this.triaje = triaje;
       this.ordenMedico = ordenMedico;
    }
   
    public BigDecimal getCodDiagnostico() {
        return this.codDiagnostico;
    }
    
    public void setCodDiagnostico(BigDecimal codDiagnostico) {
        this.codDiagnostico = codDiagnostico;
    }
    public HistorialClinico getHistorialClinico() {
        return this.historialClinico;
    }
    
    public void setHistorialClinico(HistorialClinico historialClinico) {
        this.historialClinico = historialClinico;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getMedico() {
        return this.medico;
    }
    
    public void setMedico(String medico) {
        this.medico = medico;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public RecetaMedica getRecetaMedica() {
        return this.recetaMedica;
    }
    
    public void setRecetaMedica(RecetaMedica recetaMedica) {
        this.recetaMedica = recetaMedica;
    }
    public Triaje getTriaje() {
        return this.triaje;
    }
    
    public void setTriaje(Triaje triaje) {
        this.triaje = triaje;
    }
    public OrdenMedico getOrdenMedico() {
        return this.ordenMedico;
    }
    
    public void setOrdenMedico(OrdenMedico ordenMedico) {
        this.ordenMedico = ordenMedico;
    }




}


