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
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.LineNumberReader;


public class Dos_bands extends javax.swing.JFrame {
    //---externos ------
    String path;
    File outFile;
    //------------------
    xeoBabel.periodicTable  periodicTable = new  xeoBabel.periodicTable();
    suma suma = new suma();
    double Fermi, AFer;
    int nFer;
    //------------------
    reader.reader cadena = new reader.reader();
    pintar2D.pintar2D pintar2D_dos = new pintar2D.pintar2D();
    pintar2D.pintar2D pintar2D_band = new pintar2D.pintar2D();
    java.util.ArrayList<String> g2d = new java.util.ArrayList();
    File newFile;
    int dIc=5;
    String SEP;
    public java.awt.Font fuente ;
    String xeoformat;
    public Dos_bands(String p, String x) {
        setXeoFont("/dejavu/DejaVuSans.ttf");
        xeoformat=x;
        path=p;
        initComponents();
        SEP = System.getProperty("file.separator");
        outFile=new File("./");
        Fermi=10.0;
        AFer=0.1;
        nFer=1;
    }
    
    public class suma {
        reader.reader cadena = new reader.reader();
        /** Creates a new instance of suma */
        public suma() {
        }
        
        void sumaDensidad(javax.swing.JTextArea STextArea, File outFile){
            if(!STextArea.getText().equals("")){
                int nS=0;
                for(int i=1;i<=STextArea.getLineCount();i++){
                    if(!cadena.readColString(1,cadena.readLine(i,STextArea.getText())).equals("0.0")) nS++;
                    // if(nCol(readLine(i,STextArea.getText()))==1) System.out.println(readCol(1,readLine(i,STextArea.getText()))+"...."+nC);
                    //else System.out.println(readCol(1,readLine(i,STextArea.getText()))+".."+readCol(2,readLine(i,STextArea.getText())));
                }
                String str [] = new String[nS];  //suponemos los mismo valores de x
                int Nm [] = new int[nS];
                int N_max=1;
                double CA [] = new double [nS];
                //     System.out.println(C_list.getItemAt(i).toString());
                int iS=0;
                try{
                    BufferedReader [] inFile = new BufferedReader[nS];
                    LineNumberReader [] inLines = new LineNumberReader[nS];
                    for(int i=1;i<=STextArea.getLineCount();i++){
                        if(!cadena.readColString(1,cadena.readLine(i,STextArea.getText())).equals("0.0")){
                            inFile[iS] = new BufferedReader(new  FileReader(cadena.readColString(1,cadena.readLine(i,STextArea.getText()))));
                            if(cadena.nCol(cadena.readLine(i,STextArea.getText()))==1)  CA[iS]=1.0;
                            else  CA[iS]=cadena.readColDouble(2,cadena.readLine(i,STextArea.getText()));
                            inLines[iS]= new LineNumberReader(inFile[iS]);
                            iS++;
                        }
                    }
                    for(int i=0;i<nS;i++) {
                        str[i] = inFile[i].readLine();
                        Nm[i] = cadena.nCol(str[i]);
                        N_max=(N_max<Nm[i])?Nm[i]:N_max;
                    }
                    
                    for(int i=0;i<nS;i++) {
                        inLines[i].close();
                        inFile[i].close();
                    }
                }catch (IOException oe) {System.out.println("hay error/es E1 en la suma");}
                try{
                    java.io.FileOutputStream archivo_ant = new  java.io.FileOutputStream(outFile.getAbsolutePath());
                    java.io.DataOutputStream archivo = new  java.io.DataOutputStream(archivo_ant);
                    //   archivo.writeBytes("Copyright 2006  by Daniel Gonzalez Trabada \njavaSTM is free software you can redistribute it and/or modify it under the terms of the \nGNU General Public License as published by the Free Software Foundation. \n \n");
                    BufferedReader [] inFile = new  BufferedReader[nS];
                    LineNumberReader [] inLines = new  LineNumberReader[nS];
                    iS=0;
                    for(int i=1;i<=STextArea.getLineCount();i++){
                        if(!cadena.readColString(1,cadena.readLine(i,STextArea.getText())).equals("0.0")){
                            inFile[iS] = new  BufferedReader(new FileReader(cadena.readColString(1,cadena.readLine(i,STextArea.getText()))));
                            if(cadena.nCol(cadena.readLine(i,STextArea.getText()))==1)  CA[iS]=1.0;
                            else  CA[iS]=cadena.readColDouble(2,cadena.readLine(i,STextArea.getText()));
                            inLines[iS]= new  LineNumberReader(inFile[iS]);
                            iS++;
                        }
                    }
                    double Cm[][] = new double [N_max+1][nS];
                    double aux[] = new double [N_max+1];
                    String cadenaText="";
                    while ((str[0] = inFile[0].readLine()) != null) {
                        aux[0]=0;
                        cadenaText="";
                        for(int i=1;i<nS;i++){aux[i]=0; str[i]=inLines[i].readLine();}
                        for(int j=0;j<nS;j++ )
                            for(int i=1;i<=Nm[j];i++) {
                            if(j==0) aux[i]=0;
                            Cm[i][j]= cadena.readColDouble(i,str[j]);
                            if(i==1) aux[i]=Cm[i][0]*CA[0];
                            else{
                                if(i<Nm[j]-1) aux[i]=aux[i]+Cm[i][j]*CA[j];
                                if(i==Nm[j]-1) aux[N_max-1]=aux[N_max-1]+Cm[i][j]*CA[j]; //hacemos todas
                                if(i==Nm[j])aux[N_max]=aux[N_max]+Cm[i][j]*CA[j];
                            }
                            }
                        for(int i=1;i<=N_max;i++) cadenaText+="   "+ cadena.pasarString(aux[i]);
                        archivo.writeBytes( cadenaText+"\n");
                        //    System.out.println( Double.valueOf(readCol(1,str[0])).doubleValue()+"\t"+ Double.valueOf(readCol(2,str[0])).doubleValue()+"\t"+aux/auxd);
                        
                    }
                    archivo.close();
                    archivo_ant.close();
                    for(int i=0;i<nS;i++) {
                        inLines[i].close();
                        inFile[i].close();
                    }
                    
                }catch (IOException oe) {System.out.println("hay error/es E1 en la suma");}
            }
        }
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuBand = new javax.swing.JPopupMenu();
        jMenuopen2 = new javax.swing.JMenuItem();
        jMenuSave1 = new javax.swing.JMenuItem();
        jMenSee1 = new javax.swing.JMenuItem();
        jMenuReload1 = new javax.swing.JMenuItem();
        jMenuReload3 = new javax.swing.JMenuItem();
        jMenuOption1 = new javax.swing.JMenuItem();
        jPopupMenuDOS = new javax.swing.JPopupMenu();
        jMenuopen3 = new javax.swing.JMenuItem();
        jMenuSave2 = new javax.swing.JMenuItem();
        jMenSee2 = new javax.swing.JMenuItem();
        jMenuReload2 = new javax.swing.JMenuItem();
        jMenuReload4 = new javax.swing.JMenuItem();
        jMenuOption2 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        TAB_band = new javax.swing.JPanel();
        open_band = new javax.swing.JButton();
        save_band = new javax.swing.JButton();
        reload_band = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Fermi_bands = new javax.swing.JTextField();
        screen_band = new javax.swing.JLabel();
        use_dos_input = new javax.swing.JCheckBox();
        OptionsB = new javax.swing.JButton();
        expand_bands = new javax.swing.JButton();
        TAB_dos = new javax.swing.JPanel();
        screen_dos = new javax.swing.JLabel();
        open_dos = new javax.swing.JButton();
        save_dos = new javax.swing.JButton();
        reload_dos = new javax.swing.JButton();
        Fermi_dosChek = new javax.swing.JCheckBox();
        Fermi_dos = new javax.swing.JTextField();
        OptionsB1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        dosBox1 = new javax.swing.JCheckBox();
        dosBox2 = new javax.swing.JCheckBox();
        dosBox3 = new javax.swing.JCheckBox();
        dosBox4 = new javax.swing.JCheckBox();
        dosBox5 = new javax.swing.JCheckBox();
        dosBox6 = new javax.swing.JCheckBox();
        dosBox7 = new javax.swing.JCheckBox();
        dosBox8 = new javax.swing.JCheckBox();
        dosBox9 = new javax.swing.JCheckBox();
        dosBox10 = new javax.swing.JCheckBox();
        dosBox11 = new javax.swing.JCheckBox();
        dosBox12 = new javax.swing.JCheckBox();
        dosBox13 = new javax.swing.JCheckBox();
        dosBox14 = new javax.swing.JCheckBox();
        dosBox15 = new javax.swing.JCheckBox();
        dosBox16 = new javax.swing.JCheckBox();
        dosBox17 = new javax.swing.JCheckBox();
        dosBox18 = new javax.swing.JCheckBox();
        dosBox19 = new javax.swing.JCheckBox();
        dosBox20 = new javax.swing.JCheckBox();
        expan_dos = new javax.swing.JButton();
        expan_dos1 = new javax.swing.JButton();

        jMenuopen2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuopen2.setText("open");
        jMenuopen2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuopen2MousePressed(evt);
            }
        });
        jPopupMenuBand.add(jMenuopen2);

        jMenuSave1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuSave1.setText("save");
        jMenuSave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuSave1MousePressed(evt);
            }
        });
        jPopupMenuBand.add(jMenuSave1);

        jMenSee1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenSee1.setText("preview");
        jMenSee1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenSee1MousePressed(evt);
            }
        });
        jPopupMenuBand.add(jMenSee1);

        jMenuReload1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuReload1.setText("reload");
        jMenuReload1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuReload1MousePressed(evt);
            }
        });
        jPopupMenuBand.add(jMenuReload1);

        jMenuReload3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuReload3.setText("reload rescalate");
        jMenuReload3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuReload3MousePressed(evt);
            }
        });
        jPopupMenuBand.add(jMenuReload3);

        jMenuOption1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuOption1.setText("options");
        jMenuOption1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuOption1MousePressed(evt);
            }
        });
        jPopupMenuBand.add(jMenuOption1);

        jMenuopen3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuopen3.setText("open");
        jMenuopen3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuopen3MousePressed(evt);
            }
        });
        jPopupMenuDOS.add(jMenuopen3);

        jMenuSave2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuSave2.setText("save");
        jMenuSave2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuSave2MousePressed(evt);
            }
        });
        jPopupMenuDOS.add(jMenuSave2);

        jMenSee2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenSee2.setText("preview");
        jMenSee2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenSee2MousePressed(evt);
            }
        });
        jPopupMenuDOS.add(jMenSee2);

        jMenuReload2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuReload2.setText("reload");
        jMenuReload2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuReload2MousePressed(evt);
            }
        });
        jPopupMenuDOS.add(jMenuReload2);

        jMenuReload4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuReload4.setText("reload rescalate");
        jMenuReload4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuReload4MousePressed(evt);
            }
        });
        jPopupMenuDOS.add(jMenuReload4);

        jMenuOption2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuOption2.setText("options");
        jMenuOption2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuOption2MousePressed(evt);
            }
        });
        jPopupMenuDOS.add(jMenuOption2);

        setTitle("DOS and bands");
        setBackground(new java.awt.Color(255, 255, 255));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        TAB_band.setBackground(new java.awt.Color(255, 255, 255));

        open_band.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/open.gif"))); // NOI18N
        open_band.setAlignmentY(0.0F);
        open_band.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        open_band.setDisabledIcon(null);
        open_band.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                open_bandMousePressed(evt);
            }
        });

        save_band.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save.gif"))); // NOI18N
        save_band.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        save_band.setEnabled(false);
        save_band.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                save_bandMousePressed(evt);
            }
        });

        reload_band.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/reload.gif"))); // NOI18N
        reload_band.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        reload_band.setEnabled(false);
        reload_band.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reload_bandMousePressed(evt);
            }
        });

        jLabel7.setFont(fuente
        );
        jLabel7.setText("Fermi level: ");

        Fermi_bands.setText("0.0");
        Fermi_bands.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        screen_band.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        screen_band.setEnabled(false);
        screen_band.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                screen_bandMouseDragged(evt);
            }
        });
        screen_band.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                screen_bandMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                screen_bandMouseReleased(evt);
            }
        });
        screen_band.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                screen_bandMouseWheelMoved(evt);
            }
        });

        use_dos_input.setFont(fuente
        );
        use_dos_input.setText("use dos.input ( file )");
        use_dos_input.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        use_dos_input.setMargin(new java.awt.Insets(0, 0, 0, 0));
        use_dos_input.setOpaque(false);

        OptionsB.setFont(fuente
        );
        OptionsB.setText("Opt.");
        OptionsB.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        OptionsB.setEnabled(false);
        OptionsB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        OptionsB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OptionsBMousePressed(evt);
            }
        });

        expand_bands.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/expandir.gif"))); // NOI18N
        expand_bands.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        expand_bands.setEnabled(false);
        expand_bands.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                expand_bandsMousePressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout TAB_bandLayout = new org.jdesktop.layout.GroupLayout(TAB_band);
        TAB_band.setLayout(TAB_bandLayout);
        TAB_bandLayout.setHorizontalGroup(
            TAB_bandLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TAB_bandLayout.createSequentialGroup()
                .addContainerGap()
                .add(TAB_bandLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(screen_band, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .add(TAB_bandLayout.createSequentialGroup()
                        .add(open_band, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(save_band, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(OptionsB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(reload_band, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(expand_bands, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(TAB_bandLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(TAB_bandLayout.createSequentialGroup()
                                .add(jLabel7)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(Fermi_bands, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(use_dos_input))))
                .addContainerGap())
        );
        TAB_bandLayout.setVerticalGroup(
            TAB_bandLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TAB_bandLayout.createSequentialGroup()
                .addContainerGap()
                .add(TAB_bandLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(open_band, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(TAB_bandLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(reload_band, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(expand_bands, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(save_band, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(TAB_bandLayout.createSequentialGroup()
                        .add(TAB_bandLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(Fermi_bands, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(use_dos_input, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(OptionsB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(8, 8, 8)
                .add(screen_band, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("bands", TAB_band);

        TAB_dos.setBackground(new java.awt.Color(255, 255, 255));

        screen_dos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        screen_dos.setEnabled(false);
        screen_dos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                screen_dosMouseDragged(evt);
            }
        });
        screen_dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                screen_dosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                screen_dosMouseReleased(evt);
            }
        });
        screen_dos.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                screen_dosMouseWheelMoved(evt);
            }
        });

        open_dos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/open.gif"))); // NOI18N
        open_dos.setAlignmentY(0.0F);
        open_dos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        open_dos.setDisabledIcon(null);
        open_dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                open_dosMousePressed(evt);
            }
        });

        save_dos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save.gif"))); // NOI18N
        save_dos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        save_dos.setEnabled(false);
        save_dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                save_dosMousePressed(evt);
            }
        });

        reload_dos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/reload.gif"))); // NOI18N
        reload_dos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        reload_dos.setEnabled(false);
        reload_dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reload_dosMousePressed(evt);
            }
        });

        Fermi_dosChek.setFont(fuente);
        Fermi_dosChek.setText("dos.input");
        Fermi_dosChek.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Fermi_dosChek.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Fermi_dosChek.setOpaque(false);
        Fermi_dosChek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fermi_dosChekMouseClicked(evt);
            }
        });

        Fermi_dos.setText("0.0");
        Fermi_dos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        OptionsB1.setFont(fuente);
        OptionsB1.setText("Opt.");
        OptionsB1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        OptionsB1.setEnabled(false);
        OptionsB1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        OptionsB1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OptionsB1MousePressed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        dosBox1.setFont(fuente
        );
        dosBox1.setSelected(true);
        dosBox1.setText("---");
        dosBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox1MouseClicked(evt);
            }
        });

        dosBox2.setFont(fuente
        );
        dosBox2.setSelected(true);
        dosBox2.setText("---");
        dosBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox2MouseClicked(evt);
            }
        });

        dosBox3.setFont(fuente
        );
        dosBox3.setSelected(true);
        dosBox3.setText("---");
        dosBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox3MouseClicked(evt);
            }
        });

        dosBox4.setFont(fuente
        );
        dosBox4.setSelected(true);
        dosBox4.setText("---");
        dosBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox4MouseClicked(evt);
            }
        });

        dosBox5.setFont(fuente
        );
        dosBox5.setSelected(true);
        dosBox5.setText("---");
        dosBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox5MouseClicked(evt);
            }
        });

        dosBox6.setFont(fuente
        );
        dosBox6.setSelected(true);
        dosBox6.setText("---");
        dosBox6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox6MouseClicked(evt);
            }
        });

        dosBox7.setFont(fuente
        );
        dosBox7.setSelected(true);
        dosBox7.setText("---");
        dosBox7.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox7MouseClicked(evt);
            }
        });

        dosBox8.setFont(fuente
        );
        dosBox8.setSelected(true);
        dosBox8.setText("---");
        dosBox8.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox8MouseClicked(evt);
            }
        });

        dosBox9.setFont(fuente
        );
        dosBox9.setSelected(true);
        dosBox9.setText("---");
        dosBox9.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox9MouseClicked(evt);
            }
        });

        dosBox10.setFont(fuente
        );
        dosBox10.setSelected(true);
        dosBox10.setText("---");
        dosBox10.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox10MouseClicked(evt);
            }
        });

        dosBox11.setFont(fuente
        );
        dosBox11.setSelected(true);
        dosBox11.setText("---");
        dosBox11.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox11MouseClicked(evt);
            }
        });

        dosBox12.setFont(fuente
        );
        dosBox12.setSelected(true);
        dosBox12.setText("---");
        dosBox12.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox12MouseClicked(evt);
            }
        });

        dosBox13.setFont(fuente
        );
        dosBox13.setSelected(true);
        dosBox13.setText("---");
        dosBox13.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox13MouseClicked(evt);
            }
        });

        dosBox14.setFont(fuente
        );
        dosBox14.setSelected(true);
        dosBox14.setText("---");
        dosBox14.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox14MouseClicked(evt);
            }
        });

        dosBox15.setFont(fuente
        );
        dosBox15.setSelected(true);
        dosBox15.setText("---");
        dosBox15.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox15.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox15MouseClicked(evt);
            }
        });

        dosBox16.setFont(fuente
        );
        dosBox16.setSelected(true);
        dosBox16.setText("---");
        dosBox16.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox16.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox16MouseClicked(evt);
            }
        });

        dosBox17.setFont(fuente
        );
        dosBox17.setSelected(true);
        dosBox17.setText("---");
        dosBox17.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox17.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox17MouseClicked(evt);
            }
        });

        dosBox18.setFont(fuente
        );
        dosBox18.setSelected(true);
        dosBox18.setText("---");
        dosBox18.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox18.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox18MouseClicked(evt);
            }
        });

        dosBox19.setFont(fuente
        );
        dosBox19.setSelected(true);
        dosBox19.setText("---");
        dosBox19.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox19.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox19MouseClicked(evt);
            }
        });

        dosBox20.setFont(fuente
        );
        dosBox20.setSelected(true);
        dosBox20.setText("---");
        dosBox20.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dosBox20.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dosBox20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosBox20MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dosBox2)
                    .add(dosBox3)
                    .add(dosBox4)
                    .add(dosBox5)
                    .add(dosBox1)
                    .add(dosBox6)
                    .add(dosBox7)
                    .add(dosBox8)
                    .add(dosBox9)
                    .add(dosBox10)
                    .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, dosBox15)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, dosBox16)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, dosBox17)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, dosBox18)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, dosBox20)
                        .add(dosBox11))
                    .add(dosBox12)
                    .add(dosBox13)
                    .add(dosBox14)
                    .add(dosBox19))
                .addContainerGap(560, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(dosBox1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox9)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox10)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox11)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox12)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox13)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox14)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox15)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox16)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox17)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox18)
                .add(4, 4, 4)
                .add(dosBox19)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dosBox20)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel13);

        expan_dos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/expandir.gif"))); // NOI18N
        expan_dos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        expan_dos.setEnabled(false);
        expan_dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                expan_dosMousePressed(evt);
            }
        });

        expan_dos1.setFont(fuente);
        expan_dos1.setText("help");
        expan_dos1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        expan_dos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                expan_dos1MousePressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout TAB_dosLayout = new org.jdesktop.layout.GroupLayout(TAB_dos);
        TAB_dos.setLayout(TAB_dosLayout);
        TAB_dosLayout.setHorizontalGroup(
            TAB_dosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TAB_dosLayout.createSequentialGroup()
                .addContainerGap()
                .add(TAB_dosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(TAB_dosLayout.createSequentialGroup()
                        .add(open_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(save_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(OptionsB1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(reload_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(expan_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(TAB_dosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(TAB_dosLayout.createSequentialGroup()
                                .add(Fermi_dosChek)
                                .add(18, 18, 18)
                                .add(expan_dos1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(37, Short.MAX_VALUE))
                            .add(TAB_dosLayout.createSequentialGroup()
                                .add(Fermi_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .add(TAB_dosLayout.createSequentialGroup()
                        .add(TAB_dosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(screen_dos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        TAB_dosLayout.setVerticalGroup(
            TAB_dosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TAB_dosLayout.createSequentialGroup()
                .addContainerGap()
                .add(TAB_dosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(save_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(open_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(OptionsB1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(TAB_dosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(reload_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(expan_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(TAB_dosLayout.createSequentialGroup()
                        .add(TAB_dosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(Fermi_dosChek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(expan_dos1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(Fermi_dos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(screen_dos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6))
        );

        jTabbedPane1.addTab("DOS", TAB_dos);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void expan_dos1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expan_dos1MousePressed
        new dialogo.helpURL(getClass().getResource("/help/begin.html")).setVisible(true);
    }//GEN-LAST:event_expan_dos1MousePressed
    
    private void expand_bandsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expand_bandsMousePressed
        if(pintar2D_band.inputfile2D!=null) {
            pintar2D_band.opt.ajustarMaximos=true;
            band band=new band();
            band.start();
        }
    }//GEN-LAST:event_expand_bandsMousePressed
    
    private void expan_dosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expan_dosMousePressed
        if(pintar2D_dos.inputfile2D!=null){
            pintar2D_band.opt.ajustarMaximos=true;
            DOS(pintar2D_dos.inputfile2D,pintar2D_dos.firstTime);
        }
    }//GEN-LAST:event_expan_dosMousePressed
    
    private void jMenuReload4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuReload4MousePressed
        if(reload_dos.isEnabled()){
            DOS(pintar2D_dos.inputfile2D,true);
        }
    }//GEN-LAST:event_jMenuReload4MousePressed
    
    private void jMenuReload3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuReload3MousePressed
        if(reload_band.isEnabled()){
            pintar2D_band.opt.ajustarMaximos=true;
            band band=new band();
            band.start();
        }
    }//GEN-LAST:event_jMenuReload3MousePressed
    
    private void screen_dosMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_screen_dosMouseWheelMoved
        if(evt.getWheelRotation()<0){
            pintar2D_dos.mouseIni((int) (evt.getX()-screen_dos.getWidth()/4),(int) (evt.getY()-screen_dos.getHeight()/4),true);
            pintar2D_dos.mouseFin((int) (evt.getX()+screen_dos.getWidth()/4),(int) (evt.getY()+screen_dos.getHeight()/4),false);
        }else{
            pintar2D_dos.mouseIni((int) (evt.getX()-screen_dos.getWidth()),(int) (evt.getY()-screen_dos.getHeight()),true);
            pintar2D_dos.mouseFin((int) (evt.getX()+screen_dos.getWidth()),(int) (evt.getY()+screen_dos.getHeight()),false);
        }
        if(screen_dos.isEnabled()){
            pintar2D_dos.lupa2D();
            pintar2D_dos.opt.ajustarMaximos=false;
            if(pintar2D_dos.opt.isVisible()) pintar2D_dos.opt.ini();
            pintar2D_dos.opt.ajustarMaximos=false;
            DOS(pintar2D_dos.inputfile2D,false);
        }
    }//GEN-LAST:event_screen_dosMouseWheelMoved
    
    private void jMenuOption2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuOption2MousePressed
        pintar2D_dos.opt.setVisible(true);
        pintar2D_dos.opt.ini();
    }//GEN-LAST:event_jMenuOption2MousePressed
    
    private void jMenuReload2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuReload2MousePressed
        if(reload_dos.isEnabled()){
            DOS(pintar2D_dos.inputfile2D,false);
        }
    }//GEN-LAST:event_jMenuReload2MousePressed
    
    private void jMenSee2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenSee2MousePressed
        new dialogo.show_picture(pintar2D_dos.inputfile2D.getAbsolutePath()).plot(pintar2D_dos.imageBuffered);
    }//GEN-LAST:event_jMenSee2MousePressed
    
    private void jMenuSave2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSave2MousePressed
        if(save_dos.isEnabled())
            new dialogo.chooser().savePicture("Save file *.jpg","save",pintar2D_dos.inputfile2D.getAbsolutePath(),pintar2D_dos.imageBuffered);
    }//GEN-LAST:event_jMenuSave2MousePressed
    
    private void jMenuopen3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuopen3MousePressed
        newFile = null;
        if(pintar2D_dos.inputfile2D!=null&&new File(path+"/"+pintar2D_dos.inputfile2D.getName()).exists())
            newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+SEP+pintar2D_dos.inputfile2D.getName() ) ;
        else newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+SEP+".") ;
        if(newFile!=null)  DOS(newFile,pintar2D_dos.firstTime);
    }//GEN-LAST:event_jMenuopen3MousePressed
    
    private void screen_bandMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_screen_bandMouseWheelMoved
        if(evt.getWheelRotation()<0){
            pintar2D_band.mouseIni((int) (evt.getX()-screen_band.getWidth()/4),(int) (evt.getY()-screen_band.getHeight()/4),true);
            pintar2D_band.mouseFin((int) (evt.getX()+screen_band.getWidth()/4),(int) (evt.getY()+screen_band.getHeight()/4),false);
        }else{
            pintar2D_band.mouseIni((int) (evt.getX()-screen_band.getWidth()),(int) (evt.getY()-screen_band.getHeight()),true);
            pintar2D_band.mouseFin((int) (evt.getX()+screen_band.getWidth()),(int) (evt.getY()+screen_band.getHeight()),false);
        }
        if(screen_band.isEnabled()){
            pintar2D_band.lupa2D();
            pintar2D_band.opt.ajustarMaximos=false;
            if(pintar2D_band.opt.isVisible()) pintar2D_band.opt.ini();
            pintar2D_band.opt.ajustarMaximos=false;
            band band=new band();
            band.start();
        }
    }//GEN-LAST:event_screen_bandMouseWheelMoved
    
    private void jMenuopen2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuopen2MousePressed
        newFile=null;
        if(new File(path+SEP+"ek.dat").exists()) newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+SEP+"ek.dat") ;
        else newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+SEP+".") ;
        if(newFile!=null) {
            pintar2D_band.inputfile2D=newFile;
            pintar2D_band.opt.ajustarMaximos=true;
            band band=new band();
            band.start();
        }
    }//GEN-LAST:event_jMenuopen2MousePressed
    
    private void jMenuOption1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuOption1MousePressed
        pintar2D_band.opt.setVisible(true);
        pintar2D_band.opt.ini();
    }//GEN-LAST:event_jMenuOption1MousePressed
    
    private void jMenuReload1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuReload1MousePressed
        if(reload_band.isEnabled()){
            pintar2D_band.opt.ajustarMaximos=false;
            band band=new band();
            band.start();
        }
    }//GEN-LAST:event_jMenuReload1MousePressed
    
    private void jMenSee1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenSee1MousePressed
        new dialogo.show_picture(pintar2D_band.inputfile2D.getAbsolutePath()).plot(pintar2D_band.imageBuffered);
    }//GEN-LAST:event_jMenSee1MousePressed
    
    private void jMenuSave1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSave1MousePressed
        new dialogo.chooser().savePicture("Save file *.jpg","save",pintar2D_band.inputfile2D.getAbsolutePath(),pintar2D_band.imageBuffered);
    }//GEN-LAST:event_jMenuSave1MousePressed
    
    private void OptionsB1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsB1MousePressed
        pintar2D_dos.opt.setVisible(true);
        pintar2D_dos.opt.ini();
    }//GEN-LAST:event_OptionsB1MousePressed
    
    private void dosBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox1MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox1MouseClicked
    
    private void dosBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox2MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox2MouseClicked
    
    private void dosBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox3MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox3MouseClicked
    
    private void dosBox4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox4MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox4MouseClicked
    
    private void dosBox5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox5MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox5MouseClicked
    
    private void dosBox6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox6MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox6MouseClicked
    
    private void dosBox7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox7MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox7MouseClicked
    
    private void dosBox8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox8MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox8MouseClicked
    
    private void dosBox9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox9MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox9MouseClicked
    
    private void dosBox10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox10MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox10MouseClicked
    
    private void dosBox11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox11MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox11MouseClicked
    
    private void dosBox12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox12MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox12MouseClicked
    
    private void dosBox13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox13MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox13MouseClicked
    
    private void dosBox14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox14MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox14MouseClicked
    
    private void dosBox15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox15MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox15MouseClicked
    
    private void dosBox16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox16MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox16MouseClicked
    
    private void dosBox17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox17MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox17MouseClicked
    
    private void dosBox18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox18MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox18MouseClicked
    
    private void dosBox19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox19MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox19MouseClicked
    
    private void dosBox20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosBox20MouseClicked
        if(reload_dos.isEnabled())  DOS(pintar2D_dos.inputfile2D,false);
    }//GEN-LAST:event_dosBox20MouseClicked
    
    private void Fermi_dosChekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fermi_dosChekMouseClicked
        if(Fermi_dosChek.isEnabled() && Fermi_dosChek.isSelected()){
            Fermi_dos.setText(cadena.pasarString(getFermi(path)));
            if(pintar2D_dos.inputfile2D!=null) {
                DOS(pintar2D_dos.inputfile2D,true);
            }
        }
    }//GEN-LAST:event_Fermi_dosChekMouseClicked
    
    private void OptionsBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsBMousePressed
        pintar2D_band.opt.setVisible(true);
        pintar2D_band.opt.ini();
    }//GEN-LAST:event_OptionsBMousePressed
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        screen_band.setIcon(null);
        screen_dos.setIcon(null);
    }//GEN-LAST:event_formComponentResized
    
    private void screen_bandMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_bandMouseDragged
        if(screen_band.isEnabled()&&pintar2D_band.mouse){
            pintar2D_band.mouseFin(evt.getX(),evt.getY(),true);
            screen_band.setIcon(new javax.swing.ImageIcon(pintar2D_band.Selec()));
        }
    }//GEN-LAST:event_screen_bandMouseDragged
    
    private void screen_bandMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_bandMouseReleased
        if(evt.getButton()==evt.BUTTON1 && screen_band.isEnabled()&&pintar2D_band.mouse){
            pintar2D_band.lupa2D();
            pintar2D_band.opt.ajustarMaximos=false;
            if(pintar2D_band.opt.isVisible()) pintar2D_band.opt.ini();
            band band=new band();
            band.start();
            pintar2D_band.mouse=false;
        }
    }//GEN-LAST:event_screen_bandMouseReleased
    
    private void screen_bandMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_bandMousePressed
        if(evt.getButton()==evt.BUTTON1 && screen_band.isEnabled())  pintar2D_band.mouseIni(evt.getX(),evt.getY(),true);
        if(evt.getButton()==evt.BUTTON3) jPopupMenuBand.show( evt.getComponent(), evt.getX(), evt.getY() ) ;
    }//GEN-LAST:event_screen_bandMousePressed
    
    private void reload_bandMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reload_bandMousePressed
        if(reload_band.isEnabled()){
            pintar2D_band.opt.ajustarMaximos=false;
            band band=new band();
            band.start();
        }
    }//GEN-LAST:event_reload_bandMousePressed
    
    private void save_bandMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save_bandMousePressed
        if(save_band.isEnabled())   new dialogo.chooser().savePicture("Save file *.jpg","save",pintar2D_band.inputfile2D.getAbsolutePath(),pintar2D_band.imageBuffered);
    }//GEN-LAST:event_save_bandMousePressed
    
    private void open_bandMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_open_bandMousePressed
        newFile=null;
        if(new File(path+SEP+"ek.dat").exists()) newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+SEP+"ek.dat") ;
        else newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+SEP+".") ;
        if(newFile!=null) {
            pintar2D_band.inputfile2D=newFile;
            pintar2D_band.opt.ajustarMaximos=true;
            band band=new band();
            band.start();
        }
    }//GEN-LAST:event_open_bandMousePressed
    
    private void reload_dosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reload_dosMousePressed
        if(reload_dos.isEnabled()){
            DOS(pintar2D_dos.inputfile2D,false);
        }
    }//GEN-LAST:event_reload_dosMousePressed
    
    private void save_dosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save_dosMousePressed
        if(save_dos.isEnabled())
            new dialogo.chooser().savePicture("Save file *.jpg","save",pintar2D_dos.inputfile2D.getAbsolutePath(),pintar2D_dos.imageBuffered);
    }//GEN-LAST:event_save_dosMousePressed
    
    private void open_dosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_open_dosMousePressed
        newFile = null;
        if(pintar2D_dos.inputfile2D!=null&&new File(path+SEP+pintar2D_dos.inputfile2D.getName()).exists())
            newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+SEP+pintar2D_dos.inputfile2D.getName() ) ;
        else newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+SEP+".") ;
        if(newFile!=null)  DOS(newFile,pintar2D_dos.firstTime);
    }//GEN-LAST:event_open_dosMousePressed
    
    private void screen_dosMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_dosMouseDragged
        if(screen_dos.isEnabled()&&pintar2D_dos.mouse){
            pintar2D_dos.mouseFin(evt.getX(),evt.getY(),true);
            screen_dos.setIcon(new javax.swing.ImageIcon(pintar2D_dos.Selec()));
        }
    }//GEN-LAST:event_screen_dosMouseDragged
    
    private void screen_dosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_dosMouseReleased
        if(evt.getButton()==evt.BUTTON1 && screen_dos.isEnabled() &&  pintar2D_dos.mouse){
            pintar2D_dos.lupa2D();
            if(pintar2D_dos.opt.isVisible()) pintar2D_dos.opt.ini();
            DOS(pintar2D_dos.inputfile2D,false);
            pintar2D_dos.mouse=false;
        }
    }//GEN-LAST:event_screen_dosMouseReleased
    
    private void screen_dosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_dosMousePressed
        if(evt.getButton()==evt.BUTTON1 && screen_dos.isEnabled())
            pintar2D_dos.mouseIni(evt.getX(),evt.getY(),true);
        if(evt.getButton()==evt.BUTTON3) jPopupMenuDOS.show( evt.getComponent(), evt.getX(), evt.getY() ) ;
    }//GEN-LAST:event_screen_dosMousePressed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Fermi_bands;
    private javax.swing.JTextField Fermi_dos;
    private javax.swing.JCheckBox Fermi_dosChek;
    private javax.swing.JButton OptionsB;
    private javax.swing.JButton OptionsB1;
    private javax.swing.JPanel TAB_band;
    private javax.swing.JPanel TAB_dos;
    private javax.swing.JCheckBox dosBox1;
    private javax.swing.JCheckBox dosBox10;
    private javax.swing.JCheckBox dosBox11;
    private javax.swing.JCheckBox dosBox12;
    private javax.swing.JCheckBox dosBox13;
    private javax.swing.JCheckBox dosBox14;
    private javax.swing.JCheckBox dosBox15;
    private javax.swing.JCheckBox dosBox16;
    private javax.swing.JCheckBox dosBox17;
    private javax.swing.JCheckBox dosBox18;
    private javax.swing.JCheckBox dosBox19;
    private javax.swing.JCheckBox dosBox2;
    private javax.swing.JCheckBox dosBox20;
    private javax.swing.JCheckBox dosBox3;
    private javax.swing.JCheckBox dosBox4;
    private javax.swing.JCheckBox dosBox5;
    private javax.swing.JCheckBox dosBox6;
    private javax.swing.JCheckBox dosBox7;
    private javax.swing.JCheckBox dosBox8;
    private javax.swing.JCheckBox dosBox9;
    private javax.swing.JButton expan_dos;
    private javax.swing.JButton expan_dos1;
    private javax.swing.JButton expand_bands;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenSee1;
    private javax.swing.JMenuItem jMenSee2;
    private javax.swing.JMenuItem jMenuOption1;
    private javax.swing.JMenuItem jMenuOption2;
    private javax.swing.JMenuItem jMenuReload1;
    private javax.swing.JMenuItem jMenuReload2;
    private javax.swing.JMenuItem jMenuReload3;
    private javax.swing.JMenuItem jMenuReload4;
    private javax.swing.JMenuItem jMenuSave1;
    private javax.swing.JMenuItem jMenuSave2;
    private javax.swing.JMenuItem jMenuopen2;
    private javax.swing.JMenuItem jMenuopen3;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPopupMenu jPopupMenuBand;
    private javax.swing.JPopupMenu jPopupMenuDOS;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton open_band;
    private javax.swing.JButton open_dos;
    private javax.swing.JButton reload_band;
    private javax.swing.JButton reload_dos;
    private javax.swing.JButton save_band;
    private javax.swing.JButton save_dos;
    private javax.swing.JLabel screen_band;
    private javax.swing.JLabel screen_dos;
    private javax.swing.JCheckBox use_dos_input;
    // End of variables declaration//GEN-END:variables
    
    
    
    void allow(){
        if(new File(path+SEP+"dos.input").exists())  {
            use_dos_input.setEnabled(true);
            Fermi_dosChek.setEnabled(true);
        } else{
            use_dos_input.setEnabled(false);
            Fermi_dosChek.setEnabled(false);
        }
    }
    
    double getFermi(String aux) {
        loadDos(aux);
        return Fermi;
    }
    double getFermi_min() {return Fermi-(nFer-1)*AFer/2;}
    double getFermi_max() {return Fermi+(nFer-1)*AFer/2;}
    
    void loadDos(String aux){
        path=aux;
        File dosFile=new File(path+SEP+"dos.input");
        if(dosFile.exists()){
            try{
                BufferedReader indos = new BufferedReader(new FileReader(dosFile.getAbsolutePath()));
                indos.readLine();
                indos.readLine();
                nFer=cadena.readColInt(1,indos.readLine());
                String auxDos=indos.readLine();
                Fermi=cadena.readColDouble(1,auxDos);
                AFer=cadena.readColDouble(2,auxDos);
                Fermi=Fermi+AFer+(nFer-1)*AFer/2;
                // ener_beg,ener_step:  ...  E_inicial+E_fermi-paso      paso
                indos.close();
            }catch (IOException oe) {System.out.println("error read dosfile");}
        }
    }
    
    
    
    
    
    void DOS(File newfile,boolean ajustar_maximos){
        save_dos.setEnabled(true);
        reload_dos.setEnabled(true);
        OptionsB1.setEnabled(true);
        screen_dos.setEnabled(true);
        expan_dos.setEnabled(true);
        if(pintar2D_dos.opt.isVisible())
            if(!pintar2D_dos.opt.ajustarMaximos)
                pintar2D_dos.opt.load();
        g2d.clear();
        //--- ponemos el titulo-----
        pintar2D_dos.setTitle("atom "+newfile.getName().substring(5,newfile.getName().length()-4));
        //--------------------------
        int  i=0, N=cadena.numeroCol(newfile)-1;
        pintar2D_dos.inputfile2D=newfile;
        getOutput();
        //006_450.wf1 006_450.wf2 006_450.ewf1
        String auxDos="--- "; //protocolo de kioto
        if(outFile.exists()){
            String dos=get_WF_outputFile(outFile);       
            //int z=infBas.bas.get((int) Double.valueOf(newfile.getName().substring(5,newfile.getName().length()-4)).doubleValue()-1).Z;
            int z=periodicTable.getZ(cadena.readColString(1,cadena.readLine((int) Double.valueOf(newfile.getName().substring(5,newfile.getName().length()-4)).doubleValue()+1,xeoformat))); //es +1 por que para readLine el \n\n no cuenta
            for(int k=1;k<=cadena.nCol(dos);k++)
                if(((int) Double.valueOf(cadena.readColString(k,dos).substring(0,3).trim()).doubleValue())==z){
                int orbital=(int) Double.valueOf(cadena.readColString(k,dos).substring(cadena.readColString(k,dos).length()-1,cadena.readColString(k,dos).length())).doubleValue();
                boolean excitado=cadena.readColString(k,dos).substring(cadena.readColString(k,dos).length()-4,cadena.readColString(k,dos).length()-3).equals("e");
                if(orbital==1&&!excitado) auxDos+="s ";
                if(orbital==2&&!excitado) auxDos+="py pz px ";
                if(orbital==3&&!excitado) auxDos+="dxy dyx dz^2 dxz dx^2y^2 ";
                if(orbital==1&&excitado) auxDos+="s* ";
                if(orbital==2&&excitado) auxDos+="py* pz* px* ";
                if(orbital==3&&excitado) auxDos+="dxy* dyx* dz^2* dxz* dx^2y^2* ";
                pintar2D_dos.setTitle("atom "+periodicTable.getSymbol(z)+" ("+newfile.getName().substring(5,newfile.getName().length()-4)+")");
                }
        }else for(int k=1;k<N;k++) auxDos+=k+" ";
        
        for(int k=1;k<N;k++) auxDos+=k+" ";
        for(int k=N;k<=20;k++) auxDos+="--- ";
        i++;g2d.add(i+" "+1+" "+"X");
        i++;if(dosBox2.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox2.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox3.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox3.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox4.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox4.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox5.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox5.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox6.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox6.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox7.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox7.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox8.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox8.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox9.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox9.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox10.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox10.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox11.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox11.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox12.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox12.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox13.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox13.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox14.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox14.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox15.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox15.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox16.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox16.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox17.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox17.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox18.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox18.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox19.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox19.setText(cadena.readColString(i,auxDos));}
        i++;if(dosBox20.isSelected()&&i<N&&!cadena.readColString(i,auxDos).trim().equals("---")){g2d.add(i+" "+i+" "+cadena.readColString(i,auxDos));dosBox20.setText(cadena.readColString(i,auxDos));}
        if(dosBox1.isSelected()){ g2d.add(N+" "+N+" "+"Dtot"); dosBox1.setText("Dtot");}
        pintar2D_dos.g2d=g2d;
        if(ajustar_maximos) {
            pintar2D_dos.Max_onefile_XNY();   // aqui ajustamos, es importante
        }
        pintar2D_dos.imageBuffered= new java.awt.image.BufferedImage(screen_dos.getWidth()-dIc,screen_dos.getHeight()-dIc, java.awt.image.BufferedImage.TYPE_INT_RGB);
        if(pintar2D_dos.firstTime){
            pintar2D_dos.opt.indexRight=true;
            pintar2D_dos.opt.showTitle=true;
            pintar2D_dos.firstTime=false;
        }
        pintar2D_dos.show_onefile_XNY();
        pintar2D_dos.HLine(Double.valueOf(Fermi_dos.getText()).doubleValue());
        screen_dos.setIcon(new javax.swing.ImageIcon(pintar2D_dos.imageBuffered));
        if(pintar2D_dos.opt.isVisible())
            if(pintar2D_dos.opt.ajustarMaximos)
                pintar2D_dos.opt.ini();
    }
    
    class band extends Thread {
        public void run() {
            reload_band.setEnabled(false);
            save_band.setEnabled(false);
            OptionsB.setEnabled(false);
            open_band.setEnabled(false);
            screen_band.setEnabled(false);
            expand_bands.setEnabled(false);
            if(pintar2D_band.opt.isVisible())
                if(!pintar2D_band.opt.ajustarMaximos)
                    pintar2D_band.opt.load();
            
            g2d.clear();
            int  N=cadena.numeroCol(pintar2D_band.inputfile2D);
            for(int i = 1; i<=N; i++) g2d.add(i+" "+i+" "+"eig");
            pintar2D_band.g2d=g2d;
            if(pintar2D_band.opt.ajustarMaximos) pintar2D_band.Max_onefile_XNY();   // aqui ajustamos, es importante
            
            if(use_dos_input.isSelected()){
                pintar2D_band.Max_onefile_XNY();
                Fermi_bands.setText(cadena.pasarString(getFermi(path)));
                pintar2D_band.opt.y_min=getFermi_max();
                pintar2D_band.opt.y_max=getFermi_min();
                //    pintar2D_band.ajustarMaximos=false;
            }
            
            pintar2D_band.imageBuffered = new java.awt.image.BufferedImage(screen_band.getWidth()-dIc,screen_band.getHeight()-dIc, java.awt.image.BufferedImage.TYPE_INT_RGB);
            pintar2D_band.show_onefile_XNY();
            pintar2D_band.VLine(Double.valueOf(Fermi_bands.getText()).doubleValue());
            screen_band.setIcon(new javax.swing.ImageIcon(pintar2D_band.imageBuffered));
            
            reload_band.setEnabled(true);
            save_band.setEnabled(true);
            OptionsB.setEnabled(true);
            screen_band.setEnabled(true);
            open_band.setEnabled(true);
            expand_bands.setEnabled(true);
            if(pintar2D_band.opt.isVisible())
                if(pintar2D_band.opt.ajustarMaximos)
                    pintar2D_band.opt.ini();
        }
    }
    
    String get_WF_outputFile(File outputFile){
        String salida="";
        System.out.println(outputFile.getAbsoluteFile());
        //------------
        if(outputFile.exists()){
            try{
                String str="";
                int n_species=0;
                BufferedReader in = new BufferedReader(new FileReader(outputFile.getAbsolutePath()));
                boolean continuar = true;
                str = in.readLine();
                while( str!= null && continuar){
                    str = in.readLine();
                    if(cadena.nCol(str) == 5){
                        if(cadena.readColString(3,str).trim().equals("Radial")&&cadena.readColString(4,str).trim().equals("cutoffs")&&cadena.readColString(5,str).trim().equals("(Pseudopotential)")){
                            in.readLine();   //esta linea ocupacion
                            in.readLine();    //radios de corte
                            salida+=in.readLine();
                            n_species++;
                        }
                    }
                    if(str.trim().equals("The name of the basisfile:")) continuar=false;
                }
            }catch (IOException oe) {System.out.println("error read output file");}
        }
        String aux=salida;
        salida="";
        for(int i = 1;i<=cadena.nCol(aux);i++)  salida+=cadena.readColFile(i,aux).getName()+" ";
        return salida;
    }
    void setXeoFont(String fontDir ){
        boolean ERFont=false;
        try{
            java.io.InputStream is = this.getClass().getResourceAsStream(fontDir);
            try{
                fuente = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,is);
                fuente = fuente.deriveFont(11.0f);
            }catch(IOException e){ERFont=true;}
        }catch(java.awt.FontFormatException e){ERFont=true;}
        if(ERFont){
            fuente = new java.awt.Font("SansSerif", java.awt.Font.PLAIN,11);
            System.out.println("ERROR : fuente no encontrada usamos : "+fuente.toString());
        }
    }
    
    
    void getOutput(){
        File [] fileList = new File(path).listFiles();
        for (int idx = 0; idx < fileList.length; idx++)
            if(!fileList[idx].isDirectory()) {
            try{
                BufferedReader in = new BufferedReader(new FileReader(fileList[idx].getAbsolutePath()));
                String str=in.readLine();
                if(cadena.nCol(str)>=3)
                    if(cadena.readColString(3,str).equals("FIREBALL"))
                        outFile=fileList[idx];
                in.close();
            }catch (IOException oe) {System.out.println("error read basfile");}
            }
        
        
    }
    
    
}
