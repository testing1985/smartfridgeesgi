import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SFXmlFilter extends FileFilter{
	
   private String m_sDescription;
   private String m_sExtension;
   public SFXmlFilter(String description, String extension) {

      this.m_sDescription = description;
      this.m_sExtension   = extension;
   }

   public boolean accept(File file) {
	   
      if(file.isDirectory()) { 
         return true; 
      }
      
      String nomFichier = file.getName().toLowerCase();
      return nomFichier.endsWith( m_sExtension );
   }
   
   public String getDescription() {
	   return m_sDescription;
   }
}