package model;
// Generated Nov 22, 2016 1:56:10 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * MuestraId generated by hbm2java
 */
public class MuestraId  implements java.io.Serializable {


     private BigDecimal codDiagnostico;
     private BigDecimal codIndicador;

    public MuestraId() {
    }

    public MuestraId(BigDecimal codDiagnostico, BigDecimal codIndicador) {
       this.codDiagnostico = codDiagnostico;
       this.codIndicador = codIndicador;
    }
   
    public BigDecimal getCodDiagnostico() {
        return this.codDiagnostico;
    }
    
    public void setCodDiagnostico(BigDecimal codDiagnostico) {
        this.codDiagnostico = codDiagnostico;
    }
    public BigDecimal getCodIndicador() {
        return this.codIndicador;
    }
    
    public void setCodIndicador(BigDecimal codIndicador) {
        this.codIndicador = codIndicador;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MuestraId) ) return false;
		 MuestraId castOther = ( MuestraId ) other; 
         
		 return ( (this.getCodDiagnostico()==castOther.getCodDiagnostico()) || ( this.getCodDiagnostico()!=null && castOther.getCodDiagnostico()!=null && this.getCodDiagnostico().equals(castOther.getCodDiagnostico()) ) )
 && ( (this.getCodIndicador()==castOther.getCodIndicador()) || ( this.getCodIndicador()!=null && castOther.getCodIndicador()!=null && this.getCodIndicador().equals(castOther.getCodIndicador()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCodDiagnostico() == null ? 0 : this.getCodDiagnostico().hashCode() );
         result = 37 * result + ( getCodIndicador() == null ? 0 : this.getCodIndicador().hashCode() );
         return result;
   }   


}


