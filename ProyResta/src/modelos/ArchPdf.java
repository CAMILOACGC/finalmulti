/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
//archivos
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
//librerias ajenas a itext
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
//import sun.tools.jar.Main;
/**
 * @web http://jc-mouse.blogspot.com/
 * @author Mouse
 */
public class ArchPdf {

    private File ruta_destino;

    public ArchPdf(){
        ruta_destino=null;
    }

    /* metodo que hace uso de la clase itext para manipular archivos PDF*/
    public void crear_PDF(Producto objP){
        //abre ventana de dialogo "guardar"
        Colocar_Destino();
        //si destino es diferente de null
        if(this.ruta_destino!=null){
            try {
                // se crea instancia del documento
                Document mipdf = new Document();
                // se establece una instancia a un documento pdf
                PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf"));
                mipdf.open();// se abre el documento
                mipdf.addTitle("Ejercicio PDF producto"); // se añade el titulo
                /*mipdf.addAuthor(a); // se añade el autor del documento
                mipdf.addSubject(s); //se añade el asunto del documento
                mipdf.addKeywords(k); //Se agregan palabras claves*/
                mipdf.add(new Paragraph("DATOS DEL PRODUCTO\n"));
                mipdf.add(new Paragraph(objP.toString()));
                // se añade el contendio del PDF
                mipdf.close(); //se cierra el PDF&
                JOptionPane.showMessageDialog(null,"Documento PDF creado");
            } catch (DocumentException ex) {
 JOptionPane.showMessageDialog(null,"Error en creacion de documento");            
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Error: no existe el archivo..");
            }
        }
    }
    /* abre la ventana de dialogo GUARDAR*/
    public void Colocar_Destino(){
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf","PDF");
       JFileChooser fileChooser = new JFileChooser();
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showSaveDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){
           this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
    }

    public File getRuta_destino() {
        return ruta_destino;
    }

    public void setRuta_destino(File ruta_destino) {
        this.ruta_destino = ruta_destino;
    }
    
}