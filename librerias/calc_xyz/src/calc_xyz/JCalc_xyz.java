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


package calc_xyz;

import java.io.File;
import java.io.IOException;

public class JCalc_xyz extends javax.swing.JFrame {
    java.util.ArrayList<String> codigo;
    java.util.ArrayList<String> g2d;
    boolean Media=false;
    public File xyzfile = null;
    public File outfile = null;
    public double timeStep;
    String home,SEP,path;
    int orderTmp;
    pintar2D.pintar2D pintar2D_dinamic = new pintar2D.pintar2D();
    java.awt.Font fuente,fuenteMono;
    reader.reader cadena = new reader.reader();
    int dIc=5;
    public JCalc_xyz(){
        boolean ERFont=false;
        try{
            java.io.InputStream is = this.getClass().getResourceAsStream("/dejavu/DejaVuSans.ttf");
            try{
                fuente = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,is);
                fuente = fuente.deriveFont(11.0f);
                is.close();
            }catch(java.io.IOException e){ERFont=true;}
        }catch(java.awt.FontFormatException e){ERFont=true;}
        if(ERFont){
            fuente = new java.awt.Font("SansSerif",java.awt.Font.PLAIN,11);
            System.out.println("ERROR jCalc_xyz : fuente no encontrada usamos : "+fuente.toString());
        }
        ERFont=false;
        try{
            java.io.InputStream is = this.getClass().getResourceAsStream("/dejavu/DejaVuSansMono.ttf");
            try{
                fuenteMono = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,is);
                fuenteMono = fuenteMono.deriveFont(11.0f);
                is.close();
            }catch(java.io.IOException e){ERFont=true;}
        }catch(java.awt.FontFormatException e){ERFont=true;}
        if(ERFont){
            fuenteMono = new java.awt.Font("SansSerifMono",java.awt.Font.PLAIN,11);
            System.out.println("ERROR jCalc_xyz : fuente no encontrada usamos : "+fuenteMono.toString());
        }
        
        this.setVisible(false);
        g2d = new java.util.ArrayList();
        codigo = new java.util.ArrayList();
        orderTmp=0;
    }
    
    public void iniciar(String p, int o) {
        orderTmp=o;
        SEP = System.getProperty("file.separator");
        home=System.getProperty("user.home")+SEP;
        if(!new File(home+SEP+".xeorc"+SEP+"xeo.ini").exists()) home=System.getProperty("user.dir")+SEP;
        path=p;
        initComponents();
        codeText.requestFocusInWindow();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jDialogTimeStep = new javax.swing.JDialog();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel14 = new javax.swing.JLabel();
        timeStep_Text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSplitPane3 = new javax.swing.JSplitPane();
        screen_xyz = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        codeText = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuOpen = new javax.swing.JMenu();
        jMenuSave = new javax.swing.JMenu();
        jMenuReload = new javax.swing.JMenu();
        jMenuExpand = new javax.swing.JMenu();
        jMenuOptions = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuTimeStep = new javax.swing.JMenu();
        jMenuOptions1 = new javax.swing.JMenu();
        jMenuOptions2 = new javax.swing.JMenu();

        jDialogTimeStep.setTitle("time step");
        jLabel14.setFont(fuente);
        jLabel14.setText(" time step =");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel14.setPreferredSize(new java.awt.Dimension(59, 10));
        jLabel14.setBounds(10, 10, 100, 20);
        jDesktopPane1.add(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        timeStep_Text.setFont(fuente);
        timeStep_Text.setText("1");
        timeStep_Text.setToolTipText("to reescale the steps");
        timeStep_Text.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        timeStep_Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                timeStep_TextKeyPressed(evt);
            }
        });

        timeStep_Text.setBounds(120, 10, 60, 20);
        jDesktopPane1.add(timeStep_Text, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(fuente);
        jButton1.setText("set new time step");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton1.setBounds(10, 38, 170, 20);
        jDesktopPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout jDialogTimeStepLayout = new org.jdesktop.layout.GroupLayout(jDialogTimeStep.getContentPane());
        jDialogTimeStep.getContentPane().setLayout(jDialogTimeStepLayout);
        jDialogTimeStepLayout.setHorizontalGroup(
            jDialogTimeStepLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        );
        jDialogTimeStepLayout.setVerticalGroup(
            jDialogTimeStepLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JCalc_xyz");
        setFocusTraversalPolicy(getFocusTraversalPolicy());
        setFocusTraversalPolicyProvider(true);
        jSplitPane3.setDividerLocation(380);
        jSplitPane3.setDividerSize(5);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        screen_xyz.setBackground(new java.awt.Color(255, 255, 255));
        screen_xyz.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        screen_xyz.setEnabled(false);
        screen_xyz.setFocusable(false);
        screen_xyz.setOpaque(true);
        screen_xyz.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                screen_xyzMouseDragged(evt);
            }
        });
        screen_xyz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                screen_xyzMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                screen_xyzMouseReleased(evt);
            }
        });
        screen_xyz.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                screen_xyzMouseWheelMoved(evt);
            }
        });

        jSplitPane3.setTopComponent(screen_xyz);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        codeText.setColumns(20);
        codeText.setFont(fuenteMono);
        codeText.setRows(5);
        codeText.setText("z[1]\nz[2]");
        codeText.setBorder(null);
        codeText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                codeTextMouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(codeText);

        jSplitPane3.setRightComponent(jScrollPane1);

        jMenuBar1.setFocusable(false);
        jMenuOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/open.gif")));
        jMenuOpen.setFocusable(false);
        jMenuOpen.setFont(fuente);
        jMenuOpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuOpenMousePressed(evt);
            }
        });

        jMenuBar1.add(jMenuOpen);

        jMenuSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save.gif")));
        jMenuSave.setFocusable(false);
        jMenuSave.setFont(fuente
        );
        jMenuSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuSaveMousePressed(evt);
            }
        });

        jMenuBar1.add(jMenuSave);

        jMenuReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/reload.gif")));
        jMenuReload.setFocusable(false);
        jMenuReload.setFont(fuente
        );
        jMenuReload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuReloadMousePressed(evt);
            }
        });

        jMenuBar1.add(jMenuReload);

        jMenuExpand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/expandir.gif")));
        jMenuExpand.setFocusable(false);
        jMenuExpand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuExpandMousePressed(evt);
            }
        });

        jMenuBar1.add(jMenuExpand);

        jMenuOptions.setText("Options");
        jMenuOptions.setFocusable(false);
        jMenuOptions.setFont(fuente);
        jMenuOptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuOptionsMousePressed(evt);
            }
        });

        jMenuBar1.add(jMenuOptions);

        jMenu4.setText("get");
        jMenu4.setFocusable(false);
        jMenu4.setFont(fuente);
        jMenuItem1.setFont(fuente);
        jMenuItem1.setText("get File()");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });

        jMenu4.add(jMenuItem1);

        jMenuItem2.setFont(fuente
        );
        jMenuItem2.setText("get Image()");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });

        jMenu4.add(jMenuItem2);

        jMenuBar1.add(jMenu4);

        jMenuTimeStep.setText("time step = 1 ");
        jMenuTimeStep.setFocusable(false);
        jMenuTimeStep.setFont(fuente);
        jMenuTimeStep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuTimeStepMousePressed(evt);
            }
        });

        jMenuBar1.add(jMenuTimeStep);

        jMenuOptions1.setText("Calculator");
        jMenuOptions1.setFocusable(false);
        jMenuOptions1.setFont(fuente);
        jMenuOptions1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuOptions1MousePressed(evt);
            }
        });

        jMenuBar1.add(jMenuOptions1);

        jMenuOptions2.setText("Average");
        jMenuOptions2.setFocusable(false);
        jMenuOptions2.setFont(fuente);
        jMenuOptions2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuOptions2MousePressed(evt);
            }
        });

        jMenuBar1.add(jMenuOptions2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenuOptions2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuOptions2MousePressed
        JAverage ave = new JAverage(fuente,fuenteMono);
        ave.path=path;
        ave.setVisible(true);
    }//GEN-LAST:event_jMenuOptions2MousePressed
    
    private void jMenuOptions1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuOptions1MousePressed
        Calc_diff_xyz calDiff = new Calc_diff_xyz(fuente,fuenteMono);
        calDiff.path=path;
    }//GEN-LAST:event_jMenuOptions1MousePressed
    
    private void codeTextMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_codeTextMouseReleased
        if(!codeText.isFocusOwner()){
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    codeText.requestFocus();
                    System.out.println( codeText.hasFocus());
                }
            });
        }
        if(!codeText.isFocusOwner()){
            System.out.println("you can edit this window because you don't have the focus," +
                    "\n please selected the principal window and return to obtain the focus");
        }
    }//GEN-LAST:event_codeTextMouseReleased
    
    
    private void jMenuExpandMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuExpandMousePressed
        if(xyzfile!=null){
            pintar2D_dinamic.opt.ajustarMaximos=true;
            dinamic dinamic=new dinamic();
            dinamic.doCalc=false;
            dinamic.start();
        }
    }//GEN-LAST:event_jMenuExpandMousePressed
    
    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        jMenuTimeStep.setText("time step = "+timeStep_Text.getText());
        jDialogTimeStep.setVisible(false);
    }//GEN-LAST:event_jButton1MousePressed
    
    private void jMenuTimeStepMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuTimeStepMousePressed
        jDialogTimeStep.pack();
        jDialogTimeStep.setVisible(true);
    }//GEN-LAST:event_jMenuTimeStepMousePressed
    
    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        if(jMenuItem1.isEnabled()){
            editor.editor edit = new editor.editor(home+".xeorc"+SEP+"temp"+SEP+"dinamic"+orderTmp+".temp");
            edit.openFile(new File(home+".xeorc"+SEP+"temp"+SEP+"dinamic"+orderTmp+".temp"));
            edit.setTitle(path+SEP+"dinamic.dat");
        }
    }//GEN-LAST:event_jMenuItem1MousePressed
    
    private void jMenuOptionsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuOptionsMousePressed
        if( jMenuOptions.isEnabled())  {
            pintar2D_dinamic.opt.setVisible(true);
            pintar2D_dinamic.opt.ini();
        }
    }//GEN-LAST:event_jMenuOptionsMousePressed
    
    private void jMenuReloadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuReloadMousePressed
        if(xyzfile!=null){
            pintar2D_dinamic.opt.ajustarMaximos=true;
            dinamic dinamic=new dinamic();
            dinamic.doCalc=true;
            dinamic.start();
            this.setTitle(pintar2D_dinamic.inputfile2D.getName());
        }
    }//GEN-LAST:event_jMenuReloadMousePressed
    
    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        if(jMenuItem2.isEnabled())
            new dialogo.show_picture(pintar2D_dinamic.inputfile2D.getAbsolutePath()).plot(pintar2D_dinamic.imageBuffered);
    }//GEN-LAST:event_jMenuItem2MousePressed
    
    private void jMenuSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSaveMousePressed
        if(jMenuSave.isEnabled())
            new dialogo.chooser().savePicture("Save file *.jpg","save",pintar2D_dinamic.inputfile2D.getAbsolutePath(),pintar2D_dinamic.imageBuffered);
    }//GEN-LAST:event_jMenuSaveMousePressed
    
    private void jMenuOpenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuOpenMousePressed
        xyzfile =  new dialogo.chooser().fileChoose("Open file *.xyz","open",path+SEP+".") ;
        if(xyzfile!=null){
            pintar2D_dinamic.inputfile2D=xyzfile;
            pintar2D_dinamic.opt.ajustarMaximos=true;
            dinamic dinamic=new dinamic();
            dinamic.doCalc=true;
            dinamic.start();
            this.setTitle(pintar2D_dinamic.inputfile2D.getName());
        }
    }//GEN-LAST:event_jMenuOpenMousePressed
    
    private void timeStep_TextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeStep_TextKeyPressed
        if(evt.VK_ENTER   == evt.getKeyCode() )
            if(jMenuReload.isEnabled()){
            pintar2D_dinamic.opt.ajustarMaximos=true;
            dinamic dinamic=new dinamic();
            dinamic.start();
            }
    }//GEN-LAST:event_timeStep_TextKeyPressed
    
    private void screen_xyzMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_screen_xyzMouseWheelMoved
        if(evt.getWheelRotation()<0){
            pintar2D_dinamic.mouseIni((int) (evt.getX()-screen_xyz.getWidth()/4),(int) (evt.getY()-screen_xyz.getHeight()/4),true);
            pintar2D_dinamic.mouseFin((int) (evt.getX()+screen_xyz.getWidth()/4),(int) (evt.getY()+screen_xyz.getHeight()/4),false);
        }else{
            pintar2D_dinamic.mouseIni((int) (evt.getX()-screen_xyz.getWidth()),(int) (evt.getY()-screen_xyz.getHeight()),true);
            pintar2D_dinamic.mouseFin((int) (evt.getX()+screen_xyz.getWidth()),(int) (evt.getY()+screen_xyz.getHeight()),false);
        }
        if(screen_xyz.isEnabled()){
            pintar2D_dinamic.lupa2D();
            pintar2D_dinamic.opt.ajustarMaximos=false;
            if(pintar2D_dinamic.opt.isVisible()) pintar2D_dinamic.opt.ini();
            dinamic dinamic=new dinamic();
            dinamic.start();
            pintar2D_dinamic.mouse=false;
        }
    }//GEN-LAST:event_screen_xyzMouseWheelMoved
    
    private void screen_xyzMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_xyzMouseReleased
        if(evt.getButton()==evt.BUTTON1 && screen_xyz.isEnabled()&& pintar2D_dinamic.mouse){
            pintar2D_dinamic.lupa2D();
            pintar2D_dinamic.opt.ajustarMaximos=false;
            if(pintar2D_dinamic.opt.isVisible()) pintar2D_dinamic.opt.ini();
            dinamic dinamic=new dinamic();
            dinamic.start();
            pintar2D_dinamic.mouse=false;
        }
    }//GEN-LAST:event_screen_xyzMouseReleased
    
    private void screen_xyzMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_xyzMousePressed
        if(evt.getButton()==evt.BUTTON1 && screen_xyz.isEnabled())   pintar2D_dinamic.mouseIni(evt.getX(),evt.getY(),true);
        //  if(evt.getButton()==evt.BUTTON3) jPopupMenuDinamic.show( evt.getComponent(), evt.getX(), evt.getY() ) ;
    }//GEN-LAST:event_screen_xyzMousePressed
    
    private void screen_xyzMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_xyzMouseDragged
        if(screen_xyz.isEnabled()&& pintar2D_dinamic.mouse){
            pintar2D_dinamic.mouseFin(evt.getX(),evt.getY(),true);
            screen_xyz.setIcon(new javax.swing.ImageIcon(pintar2D_dinamic.Selec()));
        }
    }//GEN-LAST:event_screen_xyzMouseDragged
    
    
    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JTextArea codeText;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDialog jDialogTimeStep;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuExpand;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu jMenuOpen;
    private javax.swing.JMenu jMenuOptions;
    private javax.swing.JMenu jMenuOptions1;
    private javax.swing.JMenu jMenuOptions2;
    private javax.swing.JMenu jMenuReload;
    private javax.swing.JMenu jMenuSave;
    private javax.swing.JMenu jMenuTimeStep;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JLabel screen_xyz;
    private javax.swing.JTextField timeStep_Text;
    // Fin de declaración de variables//GEN-END:variables
    
    class dinamic extends Thread {
        boolean doCalc=true;
        public void run() {
            if(pintar2D_dinamic.opt.isVisible())
                if(!pintar2D_dinamic.opt.ajustarMaximos)
                    pintar2D_dinamic.opt.load();
            setEnable_xyz(false);
            outfile = new File(home+".xeorc"+SEP+"temp"+SEP+"dinamic"+orderTmp+".temp");
            loadCodigo(codeText.getText());
            if(doCalc){
                timeStep=Double.valueOf(timeStep_Text.getText()).doubleValue();
                calculator_xyz();
            }
            g2d.clear();
            int  N=cadena.numeroCol(outfile);
            pintar2D_dinamic.inputfile2D=outfile;
            g2d.add("1 "+1+" "+"X"  );
            for(int i = 2; i<=N; i++)  g2d.add(i+" "+i+" "+codigo.get(i-2));
            pintar2D_dinamic.g2d=g2d;
            if(pintar2D_dinamic.opt.ajustarMaximos) pintar2D_dinamic.Max_onefile_XNY();
            pintar2D_dinamic.imageBuffered = new java.awt.image.BufferedImage(screen_xyz.getWidth()-dIc,screen_xyz.getHeight()-dIc, java.awt.image.BufferedImage.TYPE_INT_RGB);
            if(pintar2D_dinamic.firstTime) {
                pintar2D_dinamic.opt.indexRight=true;
                pintar2D_dinamic.firstTime=false;
                pintar2D_dinamic.opt.nfy=5;
            }
            pintar2D_dinamic.show_onefile_XNY();
            screen_xyz.setIcon(new javax.swing.ImageIcon(pintar2D_dinamic.imageBuffered));
            pintar2D_dinamic.inputfile2D=xyzfile;
            setEnable_xyz(true);
            if(pintar2D_dinamic.opt.isVisible())
                if(pintar2D_dinamic.opt.ajustarMaximos)
                    pintar2D_dinamic.opt.ini();
            
        }
    }
    void setEnable_xyz(boolean ena){
        screen_xyz.setEnabled(ena);
        jMenuSave.setEnabled(ena);
        //  jButton33.setEnabled(ena);
        jMenuOptions.setEnabled(ena);
        jMenuReload.setEnabled(ena);
        //   fileDinamic.setEnabled(ena);
        jMenuOpen.setEnabled(ena);
    }
    
    public void loadCodigo(String aux){
        codigo.clear();
        for(int i=1;i<=cadena.nLine(aux);i++)
            if(!cadena.readColString(1,cadena.readLine(i,aux)).equals("0.0"))
                codigo.add(cadena.readLine(i,aux));
    }
    boolean media(){
        boolean no=false;
        String linea="";
        for(int i=0;i<codigo.size();i++){ //numero de columnas
            linea=codigo.get(i);
            for(int j=0;j<linea.length()-2;j++)
                if( linea.substring(j,j+2).equals("A[")||linea.substring(j,j+2).equals("D[")||linea.substring(j,j+2).equals("X[")||linea.substring(j,j+2).equals("Y[")||linea.substring(j,j+2).equals("Z[")) no=true;
        }
        return no;
    }
    public void calculator_xyz(){
        java.util.ArrayList<String>  atomsXYZ= new java.util.ArrayList();
        try{
            java.io.FileReader in = new java.io.FileReader(xyzfile) ;
            java.io.BufferedReader input = new java.io.BufferedReader(in) ;
            java.io.FileOutputStream archivo_ant = new java.io.FileOutputStream(outfile);
            java.io.DataOutputStream archivo = new java.io.DataOutputStream(archivo_ant);
            String str="",irmr="";
            int natoms=0,iatoms=0;
            double time=0;
            Media=media();
            natoms=cadena.readColInt(1,input.readLine());
            double R[][] = new double[natoms][3] ;     // WVU
            input.readLine() ; //only with .xyz
            atomsXYZ.add(iatoms,time+"");
            SustCalculator expresion = new SustCalculator();
            while ((str = input.readLine()) != null) {
                iatoms++;
                // Media=true;   // WVU prueba el acelerador
                if(Media){
                    R[iatoms-1][0]=(cadena.readColDouble(2,str))/(time+1)+ R[iatoms-1][0]*time/(time+1);
                    R[iatoms-1][1]=(cadena.readColDouble(3,str))/(time+1)+ R[iatoms-1][1]*time/(time+1);
                    R[iatoms-1][2]=(cadena.readColDouble(4,str))/(time+1)+ R[iatoms-1][2]*time/(time+1) ;
                    irmr=str+" "+R[iatoms-1][0]+" "+R[iatoms-1][1]+" "+R[iatoms-1][2];
                }else irmr=str;
                atomsXYZ.add(iatoms,irmr);
                if(iatoms==natoms){
                    iatoms=0;
                    archivo.writeBytes(time+"  "+expresion.compilar_xyz(atomsXYZ,codigo)+"\n");
                    time+=timeStep;
                    input.readLine();
                    input.readLine();
                    atomsXYZ.clear();
                    atomsXYZ.add(0,time+"");
                }
            }
            input.close();
            archivo.close();
            archivo_ant.close();
        }catch (IOException oe) {System.out.println("hay error/es E10");}
        
    }
}
