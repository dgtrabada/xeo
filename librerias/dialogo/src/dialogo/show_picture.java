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

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class show_picture extends javax.swing.JFrame {

    ImageIcon icon;

    public show_picture(String title) {
        initComponents();
        this.setTitle(title);  
        this.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        screen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        open = new javax.swing.JMenu();
        save = new javax.swing.JMenu();
        jMenumas = new javax.swing.JMenu();
        label = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setTitle("view");
        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        screen.setBackground(new java.awt.Color(255, 255, 255));
        screen.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        screen.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        screen.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jScrollPane1.setViewportView(screen);

        open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/open.gif"))); // NOI18N
        open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                openMousePressed(evt);
            }
        });
        jMenuBar1.add(open);

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save.gif"))); // NOI18N
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                saveMousePressed(evt);
            }
        });
        jMenuBar1.add(save);

        jMenumas.setText("+");
        jMenumas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenumas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenumasMousePressed(evt);
            }
        });
        jMenuBar1.add(jMenumas);

        label.setText("100");
        label.setEnabled(false);
        jMenuBar1.add(label);

        jMenu3.setText("-");
        jMenu3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu3MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        int a = (int) Double.valueOf(label.getText()).doubleValue();
        if (a > 0) {
            a -= 10;
            label.setText(a + "");
            int x = (1000 * icon.getIconWidth() - icon.getIconWidth() * 100) / 1000;
            int y = (1000 * icon.getIconHeight() - icon.getIconHeight() * 100) / 1000;
            BufferedImage imageBuffered = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            java.awt.Graphics imageGraphics = imageBuffered.createGraphics();
            imageGraphics.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
            plot(imageBuffered.getScaledInstance(x, y, Image.SCALE_SMOOTH));
        }
    }//GEN-LAST:event_jMenu3MousePressed

    private void jMenumasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenumasMousePressed
        int a = (int) Double.valueOf(label.getText()).doubleValue();
        a += 10;
        label.setText(a + "");
        int x = (1000 * icon.getIconWidth() + icon.getIconWidth() * 100) / 1000;
        int y = (1000 * icon.getIconHeight() + icon.getIconHeight() * 100) / 1000;
        BufferedImage imageBuffered = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics imageGraphics = imageBuffered.createGraphics();
        imageGraphics.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
        plot(imageBuffered.getScaledInstance(x,y, Image.SCALE_SMOOTH ));
    }//GEN-LAST:event_jMenumasMousePressed

    private void saveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMousePressed
        BufferedImage imageBuffered = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics imageGraphics = imageBuffered.createGraphics();
        imageGraphics.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
        new chooser().savePicture("Save file *.jpg", "save", new File(getTitle()).toString(), imageBuffered);
    }//GEN-LAST:event_saveMousePressed

    private void openMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openMousePressed
        String aux="~/.";
        if(new File(this.getTitle()).exists())aux=new File(this.getTitle()).getAbsolutePath();
        File newFile =  new chooser().fileChoose("Open file *.jpg","open",aux) ;
        if(newFile!=null)
        plot(newFile.getAbsolutePath());// TODO add your handling code here:
                                     
    }//GEN-LAST:event_openMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenumas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu label;
    private javax.swing.JMenu open;
    private javax.swing.JMenu save;
    private javax.swing.JLabel screen;
    // End of variables declaration//GEN-END:variables

    public void plot(BufferedImage aux) {
        icon = new ImageIcon(aux);
        screen.setIcon(icon);
        this.setVisible(true);
       
    }

    public void plot(ImageIcon aux) {
        icon = aux;
        screen.setIcon(icon);
        this.setVisible(true);
    }
    
    public void plot(Icon aux) {       
        screen.setIcon(aux);
        this.setVisible(true);
    }

    public void plot(Image aux) {
        icon = new javax.swing.ImageIcon(aux);
        screen.setIcon(icon);
        this.setVisible(true);
    }

    public void plot(String aux) {
        plot(new File(aux));
        this.setVisible(true);
    }

    public void plot(File file) {
        if (file.exists()) {
            this.setTitle(file.getAbsolutePath());
            icon = new javax.swing.ImageIcon(file.getAbsolutePath());
            screen.setIcon(icon);
        } else {
            screen.setText("error, reading : " + file.getAbsolutePath());
        }
        this.setVisible(true);
    }
}
