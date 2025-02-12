package modelos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ArchPdf {

    private File ruta_destino;

    public ArchPdf() {
        ruta_destino = null;
    }

    public void crear_PDF(Reporte objP) {
        Colocar_Destino();
        if (this.ruta_destino != null) {
            try {
                Document mipdf = new Document();
                File archivoPDF = new File(this.ruta_destino + ".pdf");
                PdfWriter.getInstance(mipdf, new FileOutputStream(archivoPDF));
                mipdf.open();
                mipdf.addTitle("Ejercicio PDF producto");
                mipdf.add(new Paragraph("DATOS DEL PRODUCTO\n"));
                mipdf.add(new Paragraph(objP.toString()));
                mipdf.close();
                JOptionPane.showMessageDialog(null, "Documento PDF creado");
                
                // Abre el PDF automáticamente
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(archivoPDF);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo automáticamente");
                }
            } catch (DocumentException | FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error en creación del documento");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al intentar abrir el archivo");
            }
        }
    }

    public void Colocar_Destino() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf", "PDF");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
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
