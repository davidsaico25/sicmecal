package model;
// Generated Nov 22, 2016 11:28:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Turno generated by hbm2java
 */
public class Turno  implements java.io.Serializable {


     private BigDecimal codTurno;
     private Medico medico;
     private String consultorio;
     private Date horaInicio;
     private Date horaFin;
     private BigDecimal numCitasMax;
     private char estado;
     private Set citaMedicas = new HashSet(0);

    public Turno() {
    }

	
    public Turno(BigDecimal codTurno, Medico medico, String consultorio, Date horaInicio, Date horaFin, BigDecimal numCitasMax, char estado) {
        this.codTurno = codTurno;
        this.medico = medico;
        this.consultorio = consultorio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.numCitasMax = numCitasMax;
        this.estado = estado;
    }
    public Turno(BigDecimal codTurno, Medico medico, String consultorio, Date horaInicio, Date horaFin, BigDecimal numCitasMax, char estado, Set citaMedicas) {
       this.codTurno = codTurno;
       this.medico = medico;
       this.consultorio = consultorio;
       this.horaInicio = horaInicio;
       this.horaFin = horaFin;
       this.numCitasMax = numCitasMax;
       this.estado = estado;
       this.citaMedicas = citaMedicas;
    }
   
    public BigDecimal getCodTurno() {
        return this.codTurno;
    }
    
    public void setCodTurno(BigDecimal codTurno) {
        this.codTurno = codTurno;
    }
    public Medico getMedico() {
        return this.medico;
    }
    
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    public String getConsultorio() {
        return this.consultorio;
    }
    
    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }
    public Date getHoraInicio() {
        return this.horaInicio;
    }
    
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }
    public Date getHoraFin() {
        return this.horaFin;
    }
    
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
    public BigDecimal getNumCitasMax() {
        return this.numCitasMax;
    }
    
    public void setNumCitasMax(BigDecimal numCitasMax) {
        this.numCitasMax = numCitasMax;
    }
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }
    public Set getCitaMedicas() {
        return this.citaMedicas;
    }
    
    public void setCitaMedicas(Set citaMedicas) {
        this.citaMedicas = citaMedicas;
    }




}


