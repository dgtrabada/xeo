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

import editor.editor;


public class JAverage extends javax.swing.JFrame {
    reader.reader cadena = new reader.reader();
    java.awt.Font fuente,fuenteMono;
    String outFile="";
    String path,SEP;
    public JAverage(java.awt.Font f, java.awt.Font fMono) {
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
        
        initComponents();
        SEP = System.getProperty("file.separator");
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        inputText = new javax.swing.JTextArea();
        Te29 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        AllSteps = new javax.swing.JRadioButton();
        NotAllSteps = new javax.swing.JRadioButton();
        mol_Xini = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        mol_Xfin = new javax.swing.JTextField();
        Bt0 = new javax.swing.JButton();
        Bt1 = new javax.swing.JButton();

        setTitle("Average (xyz)");
        inputText.setColumns(20);
        inputText.setFont(fuente);
        inputText.setRows(2);
        inputText.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        inputText.setBounds(30, 30, 270, 18);
        jDesktopPane1.add(inputText, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Te29.setEditable(false);
        Te29.setFont(fuente);
        Te29.setForeground(java.awt.SystemColor.controlShadow);
        Te29.setText("Load file (*.xyz)");
        Te29.setToolTipText("the step to move the atoms selected");
        Te29.setAutoscrolls(false);
        Te29.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te29.setFocusable(false);
        Te29.setRequestFocusEnabled(false);
        Te29.setBounds(10, 10, 290, 18);
        jDesktopPane1.add(Te29, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton18.setText("...");
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton18MousePressed(evt);
            }
        });

        jButton18.setBounds(10, 30, 18, 18);
        jDesktopPane1.add(jButton18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        AllSteps.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(AllSteps);
        AllSteps.setFont(fuente);
        AllSteps.setSelected(true);
        AllSteps.setText("All steps (*.xyz)");
        AllSteps.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        AllSteps.setBorderPainted(true);
        AllSteps.setMargin(new java.awt.Insets(0, 0, 0, 0));
        AllSteps.setBounds(10, 50, 290, 18);
        jDesktopPane1.add(AllSteps, javax.swing.JLayeredPane.DEFAULT_LAYER);

        NotAllSteps.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(NotAllSteps);
        NotAllSteps.setFont(fuente);
        NotAllSteps.setText("Only the steps:");
        NotAllSteps.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        NotAllSteps.setBorderPainted(true);
        NotAllSteps.setMargin(new java.awt.Insets(0, 0, 0, 0));
        NotAllSteps.setBounds(10, 70, 290, 18);
        jDesktopPane1.add(NotAllSteps, javax.swing.JLayeredPane.DEFAULT_LAYER);

        mol_Xini.setFont(fuente);
        mol_Xini.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_Xini.setText("0");
        mol_Xini.setToolTipText("");
        mol_Xini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        mol_Xini.setBounds(30, 90, 110, 18);
        jDesktopPane1.add(mol_Xini, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel41.setFont(new  java.awt.Font(fuente.getName(), java.awt.Font.BOLD,fuente.getSize()-2));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("to");
        jLabel41.setToolTipText("");
        jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jLabel41.setEnabled(false);
        jLabel41.setFocusable(false);
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel41.setBounds(142, 90, 46, 18);
        jDesktopPane1.add(jLabel41, javax.swing.JLayeredPane.DEFAULT_LAYER);

        mol_Xfin.setFont(fuente);
        mol_Xfin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_Xfin.setText("0");
        mol_Xfin.setToolTipText("");
        mol_Xfin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        mol_Xfin.setBounds(190, 90, 110, 18);
        jDesktopPane1.add(mol_Xfin, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Bt0.setFont(fuente);
        Bt0.setText("Average");
        Bt0.setToolTipText("use more memory/velocity");
        Bt0.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Bt0.setFocusable(false);
        Bt0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bt0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Bt0MousePressed(evt);
            }
        });

        Bt0.setBounds(190, 120, 110, 17);
        jDesktopPane1.add(Bt0, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Bt1.setFont(fuente);
        Bt1.setText("help");
        Bt1.setToolTipText("use more memory/velocity");
        Bt1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Bt1.setFocusable(false);
        Bt1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Bt1MousePressed(evt);
            }
        });

        Bt1.setBounds(10, 120, 40, 17);
        jDesktopPane1.add(Bt1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void Bt1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bt1MousePressed
        new dialogo.helpURL(getClass().getResource("/help/average.html")).setVisible(true);
    }//GEN-LAST:event_Bt1MousePressed
    
    private void Bt0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bt0MousePressed
        int ini=0,fin=0;
        if(NotAllSteps.isSelected()){
            ini=cadena.readColInt(1,mol_Xini.getText());
            fin=cadena.readColInt(1,mol_Xfin.getText());
            if(ini>fin){
                int h=ini;
                ini=fin;
                fin=h;
            }
        }
        if(NotAllSteps.isSelected())  outFile=path+SEP+"aver_"+ini+"_"+fin+"_"+(new java.io.File(inputText.getText()).getName());
        else outFile=path+SEP+"aver_"+(new java.io.File(inputText.getText()).getName());
        if(new java.io.File(inputText.getText()).exists())
            new editor(outFile).LoadString(Average_xyz(inputText.getText(),ini,fin,NotAllSteps.isSelected()));
    }//GEN-LAST:event_Bt0MousePressed
    
    private void jButton18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MousePressed
        java.io.File newFile = fileChoose("Open *.bas","open",path+SEP+".") ;
        if(newFile!=null) inputText.setText(newFile.getAbsolutePath());
    }//GEN-LAST:event_jButton18MousePressed
    
    
    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JRadioButton AllSteps;
    private javax.swing.JButton Bt0;
    private javax.swing.JButton Bt1;
    private javax.swing.JRadioButton NotAllSteps;
    private javax.swing.JTextField Te29;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextArea inputText;
    private javax.swing.JButton jButton18;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JTextField mol_Xfin;
    private javax.swing.JTextField mol_Xini;
    // Fin de declaración de variables//GEN-END:variables
    
    java.io.File fileChoose(String title,String boton, String file_ini){
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        fc.setName(title);
        if(new java.io.File(file_ini).exists()) fc.setSelectedFile(new java.io.File(file_ini));
        //fc.setSelectedFile(aux);
        int returnVal = fc.showDialog( JAverage.this , boton);
        java.io.File auxfile = null;
        if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION){
            auxfile = fc.getSelectedFile();
        }
        return auxfile;
    }
    
    public String Average_xyz(String infile, int ini,int fin, boolean NOTall){
        String ret="",str="";
        boolean stop_fin=true;
        double [] xm=null,ym=null,zm=null,xa=null,za=null,ya=null;
        String [] Zm=null;
        int n=0,paso=0;
        if(new java.io.File(infile).exists())
            try{
                java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader(infile));
                n=(int) Double.valueOf(in.readLine()).doubleValue();
                xm=new double[n];
                ym=new double[n];
                zm=new double[n];
                xa=new double[n];
                ya=new double[n];
                za=new double[n];
                Zm=new String[n];
            }catch (java.io.IOException oe) {System.out.println("error read xyz :"+infile);}
        try{
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader(infile));
            if(NOTall)
                for(int i=0;i<ini*(n+2);i++)
                    in.readLine();
            double x=0,y=0,z=0;
            stop_fin=false;
            while ((str = in.readLine()) != null && stop_fin==false){
                in.readLine(); //segunda linea
                paso++;
                if(NOTall)
                    if(paso>(fin-ini))
                        stop_fin=true;
                for(int i=0;i<n;i++){
                    str=in.readLine();
                    Zm[i]=cadena.readColString(1,str);
                    x=cadena.readColDouble(2,str);
                    y=cadena.readColDouble(3,str);
                    z=cadena.readColDouble(4,str);
                    xm[i]=xm[i]*(paso-1)/paso+x/paso;
                    ym[i]=ym[i]*(paso-1)/paso+y/paso;
                    zm[i]=zm[i]*(paso-1)/paso+z/paso;
                }
            }
            
        }catch (java.io.IOException oe) {System.out.println("error read xyz :"+infile);}
        try{
            int paso_aux=0;
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader(infile));
            double x=0,y=0,z=0;
            for(int i=0;i<n;i++){
                xa[i]=0;
                ya[i]=0;
                za[i]=0;
            }
            if(NOTall)
                for(int i=0;i<ini*(n+2);i++)
                    in.readLine();
            
            stop_fin=false;
            while ((str = in.readLine()) != null && stop_fin==false){
                in.readLine(); //segunda linea
                paso_aux++;
                if(NOTall)
                    if(paso_aux>(fin-ini))
                        stop_fin=true;
                for(int i=0;i<n;i++){
                    str=in.readLine();
                    x=cadena.readColDouble(2,str);
                    y=cadena.readColDouble(3,str);
                    z=cadena.readColDouble(4,str);
                    xa[i]+=Math.pow(xm[i]-x,2);
                    ya[i]+=Math.pow(ym[i]-y,2);
                    za[i]+=Math.pow(zm[i]-z,2);
                }
            }
            
            ret+=n+"\n";
            ret+="  (Z)    (average_x)   (average_y)   (average_z)    (sigma_x)       (0)           (0)        (-sigma_x)        (0)          (0)           (0)         (sigma_y)        (0)           (0)       -(sigma_y)       (0)           (0)           (0)         (sigma_z)       (0)            (0)       -(sigma_z) "+"\n";
            for(int i=0;i<n;i++){
                ret+=cadena.format(4,Zm[i])
                +cadena.formatFortran(2,12,6,xm[i])
                +cadena.formatFortran(2,12,6,ym[i])
                +cadena.formatFortran(2,12,6,zm[i])
                +cadena.formatFortran(2,12,6,(Math.pow(xa[i]*1.0/paso,0.5)))
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,-(Math.pow(xa[i]*1.0/paso,0.5)))
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,(Math.pow(ya[i]*1.0/paso,0.5)))
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,-(Math.pow(ya[i]*1.0/paso,0.5)))
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,(Math.pow(za[i]*1.0/paso,0.5)))
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,0.0)
                +cadena.formatFortran(2,12,6,-(Math.pow(za[i]*1.0/paso,0.5)))
                +"\n" ;
            }
            
        }catch (java.io.IOException oe) {System.out.println("error read xyz :"+infile);}
        
        return ret;
        
        
    }
}
