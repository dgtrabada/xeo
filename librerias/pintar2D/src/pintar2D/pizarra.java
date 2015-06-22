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

package pintar2D;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class pizarra extends javax.swing.JFrame {
    int dIc=5;
    boolean verX=false;
    calculadora.calculadora calc = new calculadora.calculadora();
    reader.reader cadena = new reader.reader();
    ArrayList<String> g2d = new ArrayList();
    pintar2D pintar2D_out = new pintar2D();
    String temp;
    String SEP;
    public pizarra() {
        temp = System.getProperty("java.io.tmpdir");
        SEP = System.getProperty("file.separator");
        System.out.println(temp);
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        pantalla = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        plot = new javax.swing.JTextField();
        x_ini = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        x_fin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        n_Step = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("pintar2D");
        pantalla.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pantallaMouseDragged(evt);
            }
        });
        pantalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pantallaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pantallaMouseReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setText("plot");

        plot.setText("x*x");
        plot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                plotKeyPressed(evt);
            }
        });

        x_ini.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        x_ini.setText("0");

        jLabel2.setText("X");

        jLabel3.setText(":");

        x_fin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        x_fin.setText("10");

        jLabel6.setText(";   Step");

        n_Step.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        n_Step.setText("300");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(plot, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(x_ini, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(x_fin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(n_Step, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(plot, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(x_ini, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(x_fin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6)
                    .add(n_Step, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Open");
        jMenuItem1.setText("data NY");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });

        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("data XNY");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });

        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Options");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });

        jMenuBar1.add(jMenu2);

        jMenu3.setText("reload");
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
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, pantalla, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(pantalla, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        pintar2D_out.inputfile2D=  new dialogo.chooser().fileChoose("Open file","open",".") ;
        verX=true;
        load_file();
    }//GEN-LAST:event_jMenuItem2MousePressed
    
    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        pintar2D_out.inputfile2D=  new dialogo.chooser().fileChoose("Open file","open",".") ;
        verX=false;
        load_file();
    }//GEN-LAST:event_jMenuItem1MousePressed
    
    private void plotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_plotKeyPressed
        if(evt.VK_ENTER == evt.getKeyCode()) {
            verX=true;
            funcion(true);
        }
    }//GEN-LAST:event_plotKeyPressed
    
    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        pintar(true);
    }//GEN-LAST:event_jMenu3MousePressed
    
    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        pintar2D_out.opt.setVisible(true);
        pintar2D_out.opt.ini();
    }//GEN-LAST:event_jMenu2MousePressed
    
    private void pantallaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pantallaMouseDragged
        if(pantalla.isEnabled()&& pintar2D_out.mouse){
            pintar2D_out.mouseFin(evt.getX(),evt.getY(),true);
            pantalla.setIcon(new javax.swing.ImageIcon(pintar2D_out.Selec()));
        }
    }//GEN-LAST:event_pantallaMouseDragged
    
    private void pantallaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pantallaMouseReleased
        if(evt.getButton()==evt.BUTTON1 && pantalla.isEnabled()&& pintar2D_out.mouse){
            pintar2D_out.lupa2D();
            pintar2D_out.opt.ajustarMaximos=false;
            if(pintar2D_out.opt.isVisible()) pintar2D_out.opt.ini();
            pintar2D_out.mouse=false;
            pintar(false);
        }
    }//GEN-LAST:event_pantallaMouseReleased
    
    private void pantallaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pantallaMousePressed
        if(evt.getButton()==evt.BUTTON1 && pantalla.isEnabled())   pintar2D_out.mouseIni(evt.getX(),evt.getY(),true);
    }//GEN-LAST:event_pantallaMousePressed
    
    
    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField n_Step;
    private javax.swing.JLabel pantalla;
    private javax.swing.JTextField plot;
    private javax.swing.JTextField x_fin;
    private javax.swing.JTextField x_ini;
    // Fin de declaración de variables//GEN-END:variables
    
    
    void load_file(){
        File inputFile=pintar2D_out.inputfile2D;
        if(inputFile!=null) {
            if(inputFile.getName().substring(inputFile.getName().length()-4,inputFile.getName().length()).equals(".jpg")||
                    inputFile.getName().substring(inputFile.getName().length()-4,inputFile.getName().length()).equals("jpeg")||
                    inputFile.getName().substring(inputFile.getName().length()-4,inputFile.getName().length()).equals(".gif")||
                    inputFile.getName().substring(inputFile.getName().length()-3,inputFile.getName().length()).equals(".ps")||
                    inputFile.getName().substring(inputFile.getName().length()-4,inputFile.getName().length()).equals(".eps")){
                new dialogo.show_picture(inputFile.getName()).plot(inputFile);
                
            }else{
                pintar(true);
            }
        }
    }
    
    
    void pintar(boolean ajustar){
        if(verX) pintarXNY(ajustar);
        else pintarNY(ajustar);
    }
    
    void pintarNY(boolean ajustar){
        //  new pintar2D().plotXNY(inputFile);
        g2d.clear();
        int NCol=cadena.numeroCol(pintar2D_out.inputfile2D);
        g2d.add("protocolo");
        for(int k=1;k<=NCol;k++) g2d.add(k+" "+(k+1)+" "+pintar2D_out.inputfile2D.getName());
        pintar2D_out.g2d=g2d;
        if(ajustar) pintar2D_out.Max_onefile_Y(1.0 /*step*/); ///ajustamos maximos
        pintar2D_out.imageBuffered = new BufferedImage(pantalla.getWidth()-dIc,pantalla.getHeight()-dIc, BufferedImage.TYPE_INT_RGB);
        pintar2D_out.show_onefile_Y(1.0);
        pantalla.setIcon(new javax.swing.ImageIcon( pintar2D_out.imageBuffered));
        if(pintar2D_out.opt.isVisible())
            if(pintar2D_out.opt.ajustarMaximos)
                pintar2D_out.opt.ini();
    }
    void pintarXNY(boolean ajustar){
        g2d.clear();
        g2d.add("1 "+1+" "+"X"  );
        int NCol=cadena.numeroCol(pintar2D_out.inputfile2D);
        for(int i = 2; i<=NCol; i++)  g2d.add(i+" "+i+" "+plot.getText() );
        pintar2D_out.g2d=g2d;
        if(pintar2D_out.opt.ajustarMaximos) pintar2D_out.Max_onefile_XNY();
        pintar2D_out.imageBuffered = new BufferedImage(pantalla.getWidth()-dIc,pantalla.getHeight()-dIc, BufferedImage.TYPE_INT_RGB);
        if(pintar2D_out.firstTime) {
            pintar2D_out.opt.indexRight=true;
            pintar2D_out.firstTime=false;
            pintar2D_out.opt.nfy=5;
        }
        pintar2D_out.show_onefile_XNY();
        pantalla.setIcon(new javax.swing.ImageIcon(pintar2D_out.imageBuffered));
        if(pintar2D_out.opt.isVisible())
            if(pintar2D_out.opt.ajustarMaximos)
                pintar2D_out.opt.ini();
        
        
    }
    
    void funcion(boolean ajustar){
        if(ajustar){
            int n=(int) Double.valueOf(n_Step.getText()).doubleValue();
            double x=0,y=0;
            double X_ini=0,X_fin=0;
            X_ini=(int) Double.valueOf(x_ini.getText()).doubleValue();
            X_fin=(int) Double.valueOf(x_fin.getText()).doubleValue();
            String cadena=plot.getText();
            String ret="";
            try {
                java.io.FileOutputStream archivo = new java.io.FileOutputStream(new File(temp+SEP+"pintar2D.tmp"));
                java.io.DataOutputStream in = new java.io.DataOutputStream(archivo);
                
                for(int i=0;i<=n;i++){
                    x=X_ini+(X_fin-X_ini)*i/n;
                    ret="";
                    for(int j=0;j<cadena.length();j++){
                        if(cadena.substring(j,j+1).equals("x"))ret+=x;
                        else ret+=cadena.substring(j,j+1);
                    }
                    in.writeBytes(x+" "+calc.calcular(ret)+"\n");
                }
                in.close();
                archivo.close();
            }catch (IOException oe) {System.out.println("error: write " +pintar2D_out.inputfile2D.getAbsolutePath());}
            //    System.out.println("temporal en : "+temp+SEP+"pintar2D.tmp");
            pintar2D_out.inputfile2D=new File(temp+SEP+"pintar2D.tmp");
            
        }
        pintar(true);
    }
}
