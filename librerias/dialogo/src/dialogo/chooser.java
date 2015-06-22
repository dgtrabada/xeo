/*
 
 xeo is a free (GPLv3) open project management for nanostructures using Java
 Copyright (C) 2009 by Daniel González Trabada
 
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 
 */
package dialogo;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class chooser extends javax.swing.JFrame {

    public chooser() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTitle("choseer");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void savePicture(String title, String boton, String file, java.awt.image.BufferedImage Buff) {
        JFileChooser fc = new JFileChooser();
        fc.setName(title);
        fc.setSelectedFile(new File(file.substring(0, file.length() - 4) + ".jpg"));
        fc.setSelectedFile(new File(file.substring(0, file.length() - 4) + ".jpg"));
        int returnVal = fc.showDialog(this, boton);
        File auxfile = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            auxfile = fc.getSelectedFile();
        }
        if (auxfile != null) {
            try {            
                int d = auxfile.getAbsolutePath().lastIndexOf(".");
                String extension=auxfile.getAbsolutePath().substring(d + 1);
                javax.imageio.ImageIO.write(Buff, extension, auxfile);
                System.out.println(auxfile.getAbsolutePath()+" save");
            } catch (java.io.IOException e) {
                System.out.println("errors save image");
            }
        }
    }

    public File fileChoose(String title, String boton, String file_ini) {
        JFileChooser fc = new JFileChooser();
        fc.setName(title);
      //  fc.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "gif", "png"));        
        if (new File(file_ini).exists()) {
            fc.setSelectedFile(new File(file_ini));
        }
        //fc.setSelectedFile(aux);
        int returnVal = fc.showDialog(this, boton);
        File auxfile = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            auxfile = fc.getSelectedFile();
        }
        return auxfile;
    }

    public File ProjectChoose(String title, String boton, File aux, String path) {
        JFileChooser fc = new JFileChooser();
        fc.setName(title);
        if (new File(path).exists()) {
            fc.setSelectedFile(new File(path));
        }
        int returnVal = fc.showDialog(this, boton);
        File auxfile = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            auxfile = fc.getSelectedFile();
        }
        return auxfile;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
