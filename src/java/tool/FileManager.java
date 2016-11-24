package tool;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import model.CitaMedica;
import model.Especialidad;
import model.Medico;
import model.Persona;
import model.Turno;

/**
 *
 * @author davisonsp
 */
public class FileManager {
    
    public static String crearTicketCitaMedica(CitaMedica citaMedica) throws FileNotFoundException, DocumentException {
        Persona persona = citaMedica.getPaciente().getUsuario().getPersona();
        Turno turno = citaMedica.getTurno();
        Medico medico = turno.getMedico();
        Persona personaMedico = medico.getUsuario().getPersona();
        Especialidad especialidad = medico.getEspecialidad();
        String path = "C:\\Users\\davisonsp\\Documents\\NetBeansProjects\\sicmecal\\docs\\citas medicas\\" + persona.getNumeroDocumento() + "_" + citaMedica.getCodCitaMedica() + ".pdf";
        FileOutputStream archivo = new FileOutputStream(path);
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.add(new Paragraph("Hospital Dos de Mayo"));
        String saludo = "";
        if (persona.getSexo() == 'M') {
            saludo = "Sr. ";
        } else if (persona.getSexo() == 'F') {
            saludo = "Srta. ";
        }
        saludo += persona.getApellidop() + " " + persona.getApellidom() + " " + persona.getNombre();
        documento.add(new Paragraph(saludo));
        documento.add(new Paragraph("Detalle Cita Medica"));
        documento.add(new Paragraph("Especialidad: " + especialidad.getNombre()));
        documento.add(new Paragraph("Medico: " + personaMedico.getApellidop() + " " + personaMedico.getApellidom() + " " + personaMedico.getNombre()));
        documento.add(new Paragraph("Consultorio: " + turno.getConsultorio()));
        documento.add(new Paragraph("Fecha: " + turno.getHoraInicio()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        String horaInicio = simpleDateFormat.format(turno.getHoraInicio());
        String horaFin = simpleDateFormat.format(turno.getHoraFin());
        documento.add(new Paragraph("Horario: " + horaInicio + " - " + horaFin));
        documento.add(new Paragraph("Por favor de acercarse a cancelar la cita medica dentro de 2 dias, si no se procedera a cancelar su cita medica."));
        documento.close();
        return path;
    }
    
    public static String crearTicketPagoCita(CitaMedica citaMedica) throws FileNotFoundException, DocumentException {
        Persona persona = citaMedica.getPaciente().getUsuario().getPersona();
        Turno turno = citaMedica.getTurno();
        Medico medico = turno.getMedico();
        Persona personaMedico = medico.getUsuario().getPersona();
        Especialidad especialidad = medico.getEspecialidad();
        String path = "G:\\TP4_SIMECAL_2016-II\\Nueva_progra\\sicmecal\\docs\\citas medicas\\" + persona.getNumeroDocumento() + "_" + citaMedica.getCodCitaMedica() + ".pdf";
        FileOutputStream archivo = new FileOutputStream(path);
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.add(new Paragraph("Hospital Dos de Mayo"));
        String saludo = "";
        if (persona.getSexo() == 'M') {
            saludo = "Sr. ";
        } else if (persona.getSexo() == 'F') {
            saludo = "Srta. ";
        }
        saludo += persona.getApellidop() + " " + persona.getApellidom() + " " + persona.getNombre();
        documento.add(new Paragraph(saludo));
        documento.add(new Paragraph("Detalle Cita Medica"));
        documento.add(new Paragraph("Especialidad: " + especialidad.getNombre()));
        documento.add(new Paragraph("Medico: " + personaMedico.getApellidop() + " " + personaMedico.getApellidom() + " " + personaMedico.getNombre()));
        documento.add(new Paragraph("Consultorio: " + turno.getConsultorio()));
        documento.add(new Paragraph("Fecha: " + turno.getHoraInicio()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        String horaInicio = simpleDateFormat.format(turno.getHoraInicio());
        String horaFin = simpleDateFormat.format(turno.getHoraFin());
        documento.add(new Paragraph("Horario: " + horaInicio + " - " + horaFin));
        documento.add(new Paragraph("Por favor de acercarse a cancelar la cita medica dentro de 2 dias, si no se procedera a cancelar su cita medica."));
        documento.close();
        return path;
    }
}