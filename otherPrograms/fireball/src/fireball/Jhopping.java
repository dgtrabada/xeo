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


package fireball;


import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class Jhopping extends javax.swing.JFrame {
    reader.reader cadena = new reader.reader();
    hopping hopping = new hopping();
    String SEP = System.getProperty("file.separator");
    String path;
    public Font fuente ;
    public Jhopping(String p) {
        setXeoFont("/dejavu/DejaVuSans.ttf");
        path=p;
        initComponents();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        stm_paint = new javax.swing.JButton();
        CheckFormula = new javax.swing.JCheckBox();
        CheckFireball = new javax.swing.JCheckBox();
        Checkmix = new javax.swing.JCheckBox();
        logScale = new javax.swing.JCheckBox();
        stm_all = new javax.swing.JCheckBox();
        Ahop = new javax.swing.JTextField();
        Whop = new javax.swing.JTextField();
        cuthop = new javax.swing.JTextField();
        alfahop = new javax.swing.JTextField();
        stm_run = new javax.swing.JButton();
        corte = new javax.swing.JSpinner();
        stm_change = new javax.swing.JButton();
        stm_load = new javax.swing.JButton();
        NTOT_Edit = new javax.swing.JSpinner();
        stm_save = new javax.swing.JButton();
        open_hopping = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        expan_dos1 = new javax.swing.JButton();
        screen_hop = new javax.swing.JLabel();

        setTitle("hopping");
        setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        stm_paint.setFont(fuente);
        stm_paint.setText("Paint");
        stm_paint.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        stm_paint.setEnabled(false);
        stm_paint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stm_paintMousePressed(evt);
            }
        });

        stm_paint.setBounds(10, 80, 120, 20);
        jDesktopPane1.add(stm_paint, javax.swing.JLayeredPane.DEFAULT_LAYER);

        CheckFormula.setBackground(new java.awt.Color(255, 255, 255));
        CheckFormula.setFont(fuente);
        CheckFormula.setForeground(new java.awt.Color(255, 51, 51));
        CheckFormula.setSelected(true);
        CheckFormula.setText("hopping formula");
        CheckFormula.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        CheckFormula.setMargin(new java.awt.Insets(0, 0, 0, 0));
        CheckFormula.setBounds(10, 110, 130, 15);
        jDesktopPane1.add(CheckFormula, javax.swing.JLayeredPane.DEFAULT_LAYER);

        CheckFireball.setBackground(new java.awt.Color(255, 255, 255));
        CheckFireball.setFont(fuente);
        CheckFireball.setForeground(new java.awt.Color(0, 102, 255));
        CheckFireball.setSelected(true);
        CheckFireball.setText("hopping fireball");
        CheckFireball.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        CheckFireball.setMargin(new java.awt.Insets(0, 0, 0, 0));
        CheckFireball.setBounds(10, 130, 120, 15);
        jDesktopPane1.add(CheckFireball, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Checkmix.setBackground(new java.awt.Color(255, 255, 255));
        Checkmix.setFont(fuente);
        Checkmix.setForeground(new java.awt.Color(51, 204, 0));
        Checkmix.setText("mix ");
        Checkmix.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Checkmix.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Checkmix.setBounds(10, 150, 50, 15);
        jDesktopPane1.add(Checkmix, javax.swing.JLayeredPane.DEFAULT_LAYER);

        logScale.setBackground(new java.awt.Color(255, 255, 255));
        logScale.setFont(fuente);
        logScale.setSelected(true);
        logScale.setText("log (scale)");
        logScale.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        logScale.setMargin(new java.awt.Insets(0, 0, 0, 0));
        logScale.setBounds(10, 170, 90, 15);
        jDesktopPane1.add(logScale, javax.swing.JLayeredPane.DEFAULT_LAYER);

        stm_all.setBackground(new java.awt.Color(255, 255, 255));
        stm_all.setFont(fuente);
        stm_all.setText("see all");
        stm_all.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        stm_all.setMargin(new java.awt.Insets(0, 0, 0, 0));
        stm_all.setBounds(10, 190, 60, 15);
        jDesktopPane1.add(stm_all, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Ahop.setFont(fuente);
        Ahop.setText("0.00000");
        Ahop.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Ahop.setBounds(420, 12, 80, 17);
        jDesktopPane1.add(Ahop, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Whop.setFont(fuente);
        Whop.setText("1.00");
        Whop.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Whop.setBounds(420, 38, 80, 17);
        jDesktopPane1.add(Whop, javax.swing.JLayeredPane.DEFAULT_LAYER);

        cuthop.setFont(fuente);
        cuthop.setText("0.00");
        cuthop.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        cuthop.setBounds(420, 64, 80, 17);
        jDesktopPane1.add(cuthop, javax.swing.JLayeredPane.DEFAULT_LAYER);

        alfahop.setFont(fuente);
        alfahop.setText("0");
        alfahop.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        alfahop.setBounds(420, 90, 80, 17);
        jDesktopPane1.add(alfahop, javax.swing.JLayeredPane.DEFAULT_LAYER);

        stm_run.setFont(fuente);
        stm_run.setText("set A ");
        stm_run.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        stm_run.setEnabled(false);
        stm_run.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stm_runMousePressed(evt);
            }
        });

        stm_run.setBounds(340, 120, 38, 20);
        jDesktopPane1.add(stm_run, javax.swing.JLayeredPane.DEFAULT_LAYER);

        corte.setFont(fuente);
        corte.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        corte.setBounds(430, 120, 70, 20);
        jDesktopPane1.add(corte, javax.swing.JLayeredPane.DEFAULT_LAYER);

        stm_change.setFont(fuente);
        stm_change.setText("change ");
        stm_change.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        stm_change.setEnabled(false);
        stm_change.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stm_changeMousePressed(evt);
            }
        });

        stm_change.setBounds(340, 150, 160, 20);
        jDesktopPane1.add(stm_change, javax.swing.JLayeredPane.DEFAULT_LAYER);

        stm_load.setFont(fuente);
        stm_load.setText("Load");
        stm_load.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        stm_load.setEnabled(false);
        stm_load.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stm_loadMousePressed(evt);
            }
        });

        stm_load.setBounds(10, 50, 40, 20);
        jDesktopPane1.add(stm_load, javax.swing.JLayeredPane.DEFAULT_LAYER);

        NTOT_Edit.setBackground(new java.awt.Color(255, 255, 255));
        NTOT_Edit.setFont(fuente);
        NTOT_Edit.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        NTOT_Edit.setBounds(80, 50, 53, 20);
        jDesktopPane1.add(NTOT_Edit, javax.swing.JLayeredPane.DEFAULT_LAYER);

        stm_save.setFont(fuente);
        stm_save.setText("save the changes ");
        stm_save.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        stm_save.setEnabled(false);
        stm_save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stm_saveMousePressed(evt);
            }
        });

        stm_save.setBounds(340, 180, 160, 20);
        jDesktopPane1.add(stm_save, javax.swing.JLayeredPane.DEFAULT_LAYER);

        open_hopping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/open.gif")));
        open_hopping.setAlignmentY(0.0F);
        open_hopping.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        open_hopping.setDisabledIcon(new javax.swing.ImageIcon(""));
        open_hopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                open_hoppingMousePressed(evt);
            }
        });

        open_hopping.setBounds(10, 10, 40, 35);
        jDesktopPane1.add(open_hopping, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/formula.gif")));
        jLabel1.setBounds(210, 10, 200, 100);
        jDesktopPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        expan_dos1.setFont(fuente);
        expan_dos1.setText("help");
        expan_dos1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        expan_dos1.setEnabled(false);
        expan_dos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                expan_dos1MousePressed(evt);
            }
        });

        expan_dos1.setBounds(289, 180, 40, 20);
        jDesktopPane1.add(expan_dos1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        screen_hop.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        screen_hop.setEnabled(false);
        screen_hop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                screen_hopMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                screen_hopMouseReleased(evt);
            }
        });
        screen_hop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                screen_hopMouseDragged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(screen_hop, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 508, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(screen_hop, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 214, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void expan_dos1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expan_dos1MousePressed
        new dialogo.helpURL(getClass().getResource("/help/begin.html")).setVisible(true);
    }//GEN-LAST:event_expan_dos1MousePressed
    
    private void stm_paintMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stm_paintMousePressed
        if(stm_paint.isEnabled()){
            Ahop.setText(cadena.pasarString(hopping.A));
            alfahop.setText(hopping.alfa+"");
            Whop.setText(hopping.W+"");
            cuthop.setText(cadena.pasarString(hopping.corte));
            DrawHopping(true);
        }
    }//GEN-LAST:event_stm_paintMousePressed
    
    private void stm_runMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stm_runMousePressed
        if(stm_run.isEnabled()){
            hopping.A= Double.valueOf(Ahop.getText()).doubleValue();
            hopping.W= Double.valueOf(Whop.getText()).doubleValue();
            hopping.alfa = Double.valueOf(alfahop.getText()).doubleValue();
            if(corte.getValue().hashCode()<0)corte.setValue(hopping.NEnergias-1);
            if(corte.getValue().hashCode()>=hopping.NEnergias)corte.setValue(0);
            hopping.LoadA(corte.getValue().hashCode());
            Ahop.setText(cadena.pasarString(hopping.A));
            cuthop.setText(cadena.pasarString(hopping.corte));
            DrawHopping(true);
        }
    }//GEN-LAST:event_stm_runMousePressed
    
    private void stm_changeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stm_changeMousePressed
        if(stm_change.isEnabled()){
            hopping.A= Double.valueOf(Ahop.getText()).doubleValue();
            hopping.W= Double.valueOf(Whop.getText()).doubleValue();
            hopping.alfa = Double.valueOf(alfahop.getText()).doubleValue();
            hopping.corte = Double.valueOf(cuthop.getText()).doubleValue();
            DrawHopping(true);
        }
        
    }//GEN-LAST:event_stm_changeMousePressed
    
    private void stm_loadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stm_loadMousePressed
        if(stm_load.isEnabled()){
            hopping.nhop=NTOT_Edit.getValue().hashCode();
            hopping.nhop=(hopping.nhop<0)?0:hopping.nhop;
            hopping.nhop=(hopping.nhop>=hopping.NTOT)?(hopping.NTOT-1):hopping.nhop;
            NTOT_Edit.setValue(hopping.nhop);
            hopping.LoadParametrosAjuste(hopping.nhop);
            Ahop.setText(cadena.pasarString(hopping.A));
            alfahop.setText(hopping.alfa+"");
            Whop.setText(hopping.W+"");
            cuthop.setText(cadena.pasarString(hopping.corte));
            DrawHopping(true);
        }
    }//GEN-LAST:event_stm_loadMousePressed
    
    private void stm_saveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stm_saveMousePressed
        hopping.SAVE(  Ahop.getText(), alfahop.getText(), Whop.getText(), cuthop.getText() );
        hopping.LoadHopping();//recargamos!!
        hopping.LoadParametrosAjuste(hopping.nhop);
        Ahop.setText(cadena.pasarString(hopping.A));
        alfahop.setText(hopping.alfa+"");
        Whop.setText(hopping.W+"");
        cuthop.setText(cadena.pasarString(hopping.corte));
        DrawHopping(true);
    }//GEN-LAST:event_stm_saveMousePressed
    
    private void screen_hopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_hopMouseDragged
        if(screen_hop.isEnabled()) {
            hopping.X_mouse_fin=evt.getX();
            hopping.Y_mouse_fin=evt.getY();
            hopping.Selected=true;
            DrawHopping(false);
        }
    }//GEN-LAST:event_screen_hopMouseDragged
    
    private void screen_hopMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_hopMouseReleased
        if(screen_hop.isEnabled()){
            hopping.lupa2D();
            hopping.Selected=false;
            DrawHopping(false);
        }
    }//GEN-LAST:event_screen_hopMouseReleased
    
    private void screen_hopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_hopMousePressed
        if(evt.getButton()==evt.BUTTON1 && screen_hop.isEnabled()) {hopping.X_mouse_ini=evt.getX();hopping.Y_mouse_ini=evt.getY();}
    }//GEN-LAST:event_screen_hopMousePressed
    
    private void open_hoppingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_open_hoppingMousePressed
        NTOT_Edit.setValue(0);
        if(hopping.inputfile != null) hopping.inputfile =  new dialogo.chooser().fileChoose("Open file dens.out","open",hopping.inputfile.getAbsolutePath()) ;
        else hopping.inputfile =  new dialogo.chooser().fileChoose("Open file","open",path) ;
        if(hopping.inputfile !=null) {
            hopping.LoadHopping();
            stm_save.setEnabled(true);
            stm_load.setEnabled(true);
            stm_run.setEnabled(true);
            stm_change.setEnabled(true);
            stm_paint.setEnabled(true);
            screen_hop.setEnabled(true);
            hopping.LoadParametrosAjuste(0);
            Ahop.setText(cadena.pasarString(hopping.A));
            alfahop.setText(hopping.alfa+"");
            Whop.setText(hopping.W+"");
            cuthop.setText(cadena.pasarString(hopping.corte));
            DrawHopping(true);
        }
        corte.setValue(1);
    }//GEN-LAST:event_open_hoppingMousePressed
    
    
    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JTextField Ahop;
    private javax.swing.JCheckBox CheckFireball;
    private javax.swing.JCheckBox CheckFormula;
    private javax.swing.JCheckBox Checkmix;
    private javax.swing.JSpinner NTOT_Edit;
    private javax.swing.JTextField Whop;
    private javax.swing.JTextField alfahop;
    private javax.swing.JSpinner corte;
    private javax.swing.JTextField cuthop;
    private javax.swing.JButton expan_dos1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox logScale;
    private javax.swing.JButton open_hopping;
    private javax.swing.JLabel screen_hop;
    private javax.swing.JCheckBox stm_all;
    private javax.swing.JButton stm_change;
    private javax.swing.JButton stm_load;
    private javax.swing.JButton stm_paint;
    private javax.swing.JButton stm_run;
    private javax.swing.JButton stm_save;
    // Fin de declaración de variables//GEN-END:variables
    
    void DrawHopping(boolean ajustar_maximos){
        hopping.logScale=logScale.isSelected();
        hopping.sX=screen_hop.getWidth()-5;
        hopping.sY=screen_hop.getHeight()-5;
        hopping.CheckFireball=CheckFireball.isSelected();
        hopping.CheckFormula = CheckFormula.isSelected();
        hopping.CheckMix = Checkmix.isSelected();
        hopping.PintarTODOS=stm_all.isSelected();
        if(ajustar_maximos) hopping.ajustarMaximos();
        hopping.Planos_paralelos();
        screen_hop.setIcon(hopping.icon);
    }
    
    void setXeoFont(String fontDir ){
        boolean ERFont=false;
        try{
            java.io.InputStream is = this.getClass().getResourceAsStream(fontDir);
            try{
                fuente = Font.createFont(Font.TRUETYPE_FONT,is);
                fuente = fuente.deriveFont(11.0f);
            }catch(IOException e){ERFont=true;}
        }catch(java.awt.FontFormatException e){ERFont=true;}
        if(ERFont){
            fuente = new Font("SansSerif",Font.PLAIN,11);
            System.out.println("ERROR : fuente no encontrada usamos : "+fuente.toString());
        }
    }
    
}
