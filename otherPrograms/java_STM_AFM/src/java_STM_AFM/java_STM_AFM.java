/*
 
    java_STM_AFM is a free (GPLv3) java program to paint the experimental and theoretical data obtained by STM or AFM.
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


package java_STM_AFM;

import java.io.File;
import java.awt.Font;
import reader.reader;
import editor.editor;

public class java_STM_AFM extends javax.swing.JFrame {
    String path;
    public Font fuente ;
    public stm stm = new stm();
    reader cadena = new reader();
    String SEP = System.getProperty("file.separator");
    File xyzFile;
    
    double [] R_max = new double[3];
    double [] R_min = new double[3];
    
    int fontSize;
    boolean enableSTM;
    boolean enableLoadAtoms;
    
    public java_STM_AFM(String aux) {
        fontSize=11;
        enableSTM=false;
        enableLoadAtoms=false;
        fuente = new Font("SansSerif",Font.PLAIN,11);
        setFont("/dejavu/DejaVuSans.ttf");
        initComponents();
        path=aux+SEP;
        stm.pathIcon=System.getProperty("user.dir")+SEP+"iconos";
        if((!new File(stm.pathIcon).exists())){
            stm.pathIcon=System.getProperty("user.home")+SEP+"iconos";
            if((!new File(stm.pathIcon).exists()))
                stm.pathIcon=System.getProperty("user.dir")+SEP+".xeorc"+SEP+"iconos";
        }
        stm.resolucion=(int) Double.valueOf(resol_Y.getText()).doubleValue();
        Sinter1.setValue(stm.posicion_1);
        Sinter2.setValue(stm.posicion_2);
        color_Fondo.setBackground(stm.colorFondo);
        color_ini.setBackground(stm.colorIni);
        color_inter_1.setBackground(stm.colorInter1);
        color_inter_2.setBackground(stm.colorInter2);
        color_fin.setBackground(stm.colorFin);
        //--atoms---
        Col1.setValue(1);
        Col2.setValue(2);
        Col3.setValue(3);
        dialogo_color();
        
    }
    
    void setFont(String fontDir ){
        boolean ERFont=false;
        try{
            java.io.InputStream is = this.getClass().getResourceAsStream(fontDir);
            try{
                fuente = Font.createFont(Font.TRUETYPE_FONT,is);
                fuente = fuente.deriveFont(fontSize*1.0f);
                is.close();
            }catch(java.io.IOException e){ERFont=true;}
        }catch(java.awt.FontFormatException e){ERFont=true;}
        if(ERFont){
            fuente = new Font("SansSerif",Font.PLAIN,fontSize);
            System.out.println("ERROR : fuente no encontrada usamos : "+fuente.toString());
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jAtoms = new javax.swing.JFrame();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        jDeskt5 = new javax.swing.JDesktopPane();
        Tdespl5 = new javax.swing.JTextField();
        stm_seeVol1 = new javax.swing.JCheckBox();
        Tdespl21 = new javax.swing.JTextField();
        stmXini = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        stmXfin = new javax.swing.JTextField();
        stmYfin = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        stmYini = new javax.swing.JTextField();
        stmZini = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        stmZfin = new javax.swing.JTextField();
        Tdespl22 = new javax.swing.JTextField();
        mol_seeIndex = new javax.swing.JCheckBox();
        jButton62 = new javax.swing.JButton();
        mol_lvs_1 = new javax.swing.JTextField();
        mol_lvs_2 = new javax.swing.JTextField();
        mol_lvs_3 = new javax.swing.JTextField();
        jDeskt6 = new javax.swing.JDesktopPane();
        Tdespl6 = new javax.swing.JTextField();
        TOL1 = new javax.swing.JSlider();
        Tdespl15 = new javax.swing.JTextField();
        stm_grosor = new javax.swing.JTextField();
        D_Ratom = new javax.swing.JTextField();
        Tdespl16 = new javax.swing.JTextField();
        seeBond1 = new javax.swing.JCheckBox();
        Tdespl17 = new javax.swing.JTextField();
        D_alfa = new javax.swing.JSlider();
        verIconosAtomos = new javax.swing.JCheckBox();
        stm_seeLabel = new javax.swing.JCheckBox();
        D_verAtomos = new javax.swing.JCheckBox();
        jButton53 = new javax.swing.JButton();
        Tdespl50 = new javax.swing.JTextField();
        Tdespl51 = new javax.swing.JTextField();
        Tdespl52 = new javax.swing.JTextField();
        Tdespl18 = new javax.swing.JTextField();
        jDesktopPane7 = new javax.swing.JDesktopPane();
        changeLVS2 = new javax.swing.JButton();
        changeLVS3 = new javax.swing.JButton();
        Tdespl48 = new javax.swing.JTextField();
        Tdespl49 = new javax.swing.JTextField();
        Tdespl8 = new javax.swing.JTextField();
        changeLVS4 = new javax.swing.JButton();
        changeLVS5 = new javax.swing.JButton();
        jDeskt4 = new javax.swing.JDesktopPane();
        Tdespl4 = new javax.swing.JTextField();
        changeLVS = new javax.swing.JButton();
        Tdespl45 = new javax.swing.JTextField();
        lvs_11 = new javax.swing.JTextField();
        lvs_21 = new javax.swing.JTextField();
        lvs_31 = new javax.swing.JTextField();
        lvs_12 = new javax.swing.JTextField();
        lvs_22 = new javax.swing.JTextField();
        lvs_32 = new javax.swing.JTextField();
        lvs_13 = new javax.swing.JTextField();
        lvs_23 = new javax.swing.JTextField();
        lvs_33 = new javax.swing.JTextField();
        changeLVS1 = new javax.swing.JButton();
        Tdespl46 = new javax.swing.JTextField();
        Border = new javax.swing.JCheckBox();
        Tdespl47 = new javax.swing.JTextField();
        moreFiles = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        path_project = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProjectTree = new javax.swing.JTree();
        OpenProject = new javax.swing.JButton();
        Tdespl53 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextfiles = new javax.swing.JTextArea();
        OpenProject1 = new javax.swing.JButton();
        TAB_STM = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        screen_stm = new javax.swing.JLabel();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        color_inter_2 = new javax.swing.JButton();
        color_ini = new javax.swing.JButton();
        color_inter_1 = new javax.swing.JButton();
        color_fin = new javax.swing.JButton();
        Sinter1 = new javax.swing.JSlider();
        Clabel = new javax.swing.JLabel();
        Sinter2 = new javax.swing.JSlider();
        jButton49 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        color_Fondo = new javax.swing.JButton();
        Tdespl1 = new javax.swing.JTextField();
        verEjesSTM = new javax.swing.JCheckBox();
        verBarra = new javax.swing.JCheckBox();
        verCamino = new javax.swing.JCheckBox();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        resol_Y = new javax.swing.JTextField();
        jButton48 = new javax.swing.JButton();
        resol_X = new javax.swing.JTextField();
        Tdespl2 = new javax.swing.JTextField();
        jDesktopPane5 = new javax.swing.JDesktopPane();
        Tdespl7 = new javax.swing.JTextField();
        lvs_14 = new javax.swing.JTextField();
        lvs_15 = new javax.swing.JTextField();
        lvs_16 = new javax.swing.JTextField();
        Col1 = new javax.swing.JSpinner();
        Col2 = new javax.swing.JSpinner();
        Col3 = new javax.swing.JSpinner();
        jDesktopPane6 = new javax.swing.JDesktopPane();
        usar_exp = new javax.swing.JCheckBox();
        T = new javax.swing.JTextField();
        lvs_17 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        Me7 = new javax.swing.JMenu();
        me39 = new javax.swing.JMenuItem();
        me35 = new javax.swing.JMenuItem();
        me36 = new javax.swing.JMenuItem();
        me37 = new javax.swing.JMenuItem();
        me38 = new javax.swing.JMenuItem();

        jAtoms.setTitle("see atoms");

        Tdespl5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl5.setEditable(false);
        Tdespl5.setFont(fuente);
        Tdespl5.setText("Repit unit cell");
        Tdespl5.setToolTipText("");
        Tdespl5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl5.setFocusable(false);
        Tdespl5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tdespl5MousePressed(evt);
            }
        });
        jDeskt5.add(Tdespl5);
        Tdespl5.setBounds(0, 0, 180, 18);

        stm_seeVol1.setBackground(new java.awt.Color(255, 255, 255));
        stm_seeVol1.setFont(fuente);
        stm_seeVol1.setToolTipText("");
        stm_seeVol1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        stm_seeVol1.setBorderPainted(true);
        stm_seeVol1.setContentAreaFilled(false);
        stm_seeVol1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stm_seeVol1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        stm_seeVol1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stm_seeVol1MouseClicked(evt);
            }
        });
        jDeskt5.add(stm_seeVol1);
        stm_seeVol1.setBounds(0, 20, 18, 18);

        Tdespl21.setEditable(false);
        Tdespl21.setFont(fuente);
        Tdespl21.setText("atoms inside box");
        Tdespl21.setToolTipText("");
        Tdespl21.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl21.setOpaque(false);
        jDeskt5.add(Tdespl21);
        Tdespl21.setBounds(20, 20, 160, 18);

        stmXini.setFont(fuente);
        stmXini.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        stmXini.setText("0.0");
        stmXini.setToolTipText("");
        stmXini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        stmXini.setOpaque(false);
        jDeskt5.add(stmXini);
        stmXini.setBounds(20, 40, 60, 18);

        jLabel41.setFont(new Font(fuente.getName(),Font.BOLD,fuente.getSize()-2));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("X");
        jLabel41.setToolTipText("");
        jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jLabel41.setFocusable(false);
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDeskt5.add(jLabel41);
        jLabel41.setBounds(82, 40, 36, 18);

        stmXfin.setFont(fuente);
        stmXfin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        stmXfin.setText("10.0");
        stmXfin.setToolTipText("");
        stmXfin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        stmXfin.setOpaque(false);
        jDeskt5.add(stmXfin);
        stmXfin.setBounds(120, 40, 60, 18);

        stmYfin.setFont(fuente);
        stmYfin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        stmYfin.setText("10.0");
        stmYfin.setToolTipText("");
        stmYfin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        stmYfin.setOpaque(false);
        jDeskt5.add(stmYfin);
        stmYfin.setBounds(120, 60, 60, 18);

        jLabel45.setFont(new Font(fuente.getName(),Font.BOLD,fuente.getSize()-2));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Y");
        jLabel45.setToolTipText("");
        jLabel45.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jLabel45.setFocusable(false);
        jLabel45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDeskt5.add(jLabel45);
        jLabel45.setBounds(82, 60, 36, 18);

        stmYini.setFont(fuente);
        stmYini.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        stmYini.setText("0.0");
        stmYini.setToolTipText("");
        stmYini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        stmYini.setOpaque(false);
        jDeskt5.add(stmYini);
        stmYini.setBounds(20, 60, 60, 18);

        stmZini.setFont(fuente);
        stmZini.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        stmZini.setText("0.0");
        stmZini.setToolTipText("");
        stmZini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        stmZini.setOpaque(false);
        jDeskt5.add(stmZini);
        stmZini.setBounds(20, 80, 60, 18);

        jLabel46.setFont(new Font(fuente.getName(),Font.BOLD,fuente.getSize()-2));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Z");
        jLabel46.setToolTipText("");
        jLabel46.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jLabel46.setFocusable(false);
        jLabel46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDeskt5.add(jLabel46);
        jLabel46.setBounds(82, 80, 36, 18);

        stmZfin.setFont(fuente);
        stmZfin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        stmZfin.setText("10.0");
        stmZfin.setToolTipText("");
        stmZfin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        stmZfin.setOpaque(false);
        jDeskt5.add(stmZfin);
        stmZfin.setBounds(120, 80, 60, 18);

        Tdespl22.setEditable(false);
        Tdespl22.setFont(fuente);
        Tdespl22.setText("repit lvs vectors (i,j,k)");
        Tdespl22.setToolTipText("");
        Tdespl22.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl22.setOpaque(false);
        jDeskt5.add(Tdespl22);
        Tdespl22.setBounds(20, 100, 160, 18);

        mol_seeIndex.setFont(fuente);
        mol_seeIndex.setToolTipText("");
        mol_seeIndex.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        mol_seeIndex.setBorderPainted(true);
        mol_seeIndex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mol_seeIndex.setMargin(new java.awt.Insets(0, 0, 0, 0));
        mol_seeIndex.setOpaque(false);
        mol_seeIndex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mol_seeIndexMouseClicked(evt);
            }
        });
        jDeskt5.add(mol_seeIndex);
        mol_seeIndex.setBounds(0, 100, 18, 18);

        jButton62.setFont(fuente);
        jButton62.setText("...");
        jButton62.setToolTipText("");
        jButton62.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton62MousePressed(evt);
            }
        });
        jDeskt5.add(jButton62);
        jButton62.setBounds(20, 120, 18, 18);

        mol_lvs_1.setFont(fuente);
        mol_lvs_1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_lvs_1.setText("1");
        mol_lvs_1.setToolTipText("");
        mol_lvs_1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        mol_lvs_1.setOpaque(false);
        jDeskt5.add(mol_lvs_1);
        mol_lvs_1.setBounds(40, 120, 44, 18);

        mol_lvs_2.setFont(fuente);
        mol_lvs_2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_lvs_2.setText("1");
        mol_lvs_2.setToolTipText("");
        mol_lvs_2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        mol_lvs_2.setOpaque(false);
        jDeskt5.add(mol_lvs_2);
        mol_lvs_2.setBounds(86, 120, 46, 18);

        mol_lvs_3.setFont(fuente);
        mol_lvs_3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_lvs_3.setText("1");
        mol_lvs_3.setToolTipText("");
        mol_lvs_3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        mol_lvs_3.setOpaque(false);
        jDeskt5.add(mol_lvs_3);
        mol_lvs_3.setBounds(134, 120, 46, 18);

        jDesktopPane4.add(jDeskt5);
        jDeskt5.setBounds(2, 382, 180, 140);

        Tdespl6.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl6.setEditable(false);
        Tdespl6.setFont(fuente    );
        Tdespl6.setText("Edit bonds and atoms");
        Tdespl6.setToolTipText("");
        Tdespl6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl6.setFocusable(false);
        Tdespl6.setRequestFocusEnabled(false);
        Tdespl6.setScrollOffset(2);
        Tdespl6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tdespl6MousePressed(evt);
            }
        });
        jDeskt6.add(Tdespl6);
        Tdespl6.setBounds(0, 0, 180, 18);

        TOL1.setBackground(new java.awt.Color(255, 255, 255));
        TOL1.setMaximum(8);
        TOL1.setMinimum(1);
        TOL1.setToolTipText("");
        TOL1.setValue(4);
        TOL1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt6.add(TOL1);
        TOL1.setBounds(42, 20, 138, 18);

        Tdespl15.setEditable(false);
        Tdespl15.setFont(fuente);
        Tdespl15.setText("see bonds");
        Tdespl15.setToolTipText("");
        Tdespl15.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl15.setOpaque(false);
        jDeskt6.add(Tdespl15);
        Tdespl15.setBounds(20, 80, 100, 18);

        stm_grosor.setFont(fuente);
        stm_grosor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        stm_grosor.setText("1");
        stm_grosor.setToolTipText("");
        stm_grosor.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt6.add(stm_grosor);
        stm_grosor.setBounds(122, 80, 58, 18);

        D_Ratom.setFont(fuente);
        D_Ratom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        D_Ratom.setText("6");
        D_Ratom.setToolTipText("");
        D_Ratom.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt6.add(D_Ratom);
        D_Ratom.setBounds(0, 40, 40, 18);

        Tdespl16.setEditable(false);
        Tdespl16.setFont(fuente);
        Tdespl16.setText("radio");
        Tdespl16.setToolTipText("");
        Tdespl16.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl16.setOpaque(false);
        jDeskt6.add(Tdespl16);
        Tdespl16.setBounds(42, 40, 138, 18);

        seeBond1.setBackground(new java.awt.Color(255, 255, 255));
        seeBond1.setFont(fuente
        );
        seeBond1.setSelected(true);
        seeBond1.setToolTipText("");
        seeBond1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeBond1.setBorderPainted(true);
        seeBond1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeBond1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeBond1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDeskt6.add(seeBond1);
        seeBond1.setBounds(0, 80, 18, 18);

        Tdespl17.setEditable(false);
        Tdespl17.setFont(fuente);
        Tdespl17.setText("alfa");
        Tdespl17.setToolTipText("");
        Tdespl17.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl17.setOpaque(false);
        jDeskt6.add(Tdespl17);
        Tdespl17.setBounds(0, 60, 40, 18);

        D_alfa.setBackground(new java.awt.Color(255, 255, 255));
        D_alfa.setMaximum(255);
        D_alfa.setMinimum(1);
        D_alfa.setToolTipText("");
        D_alfa.setValue(255);
        D_alfa.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt6.add(D_alfa);
        D_alfa.setBounds(42, 60, 138, 18);

        verIconosAtomos.setBackground(new java.awt.Color(255, 255, 255));
        verIconosAtomos.setFont(fuente);
        verIconosAtomos.setToolTipText("");
        verIconosAtomos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        verIconosAtomos.setBorderPainted(true);
        verIconosAtomos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verIconosAtomos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        verIconosAtomos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        verIconosAtomos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verIconosAtomosMouseClicked(evt);
            }
        });
        jDeskt6.add(verIconosAtomos);
        verIconosAtomos.setBounds(0, 100, 18, 18);

        stm_seeLabel.setBackground(new java.awt.Color(255, 255, 255));
        stm_seeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        stm_seeLabel.setBorderPainted(true);
        stm_seeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stm_seeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stm_seeLabel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        stm_seeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stm_seeLabelMouseClicked(evt);
            }
        });
        jDeskt6.add(stm_seeLabel);
        stm_seeLabel.setBounds(0, 120, 18, 18);

        D_verAtomos.setBackground(new java.awt.Color(255, 255, 255));
        D_verAtomos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        D_verAtomos.setBorderPainted(true);
        D_verAtomos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D_verAtomos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        D_verAtomos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        D_verAtomos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D_verAtomosMouseClicked(evt);
            }
        });
        jDeskt6.add(D_verAtomos);
        D_verAtomos.setBounds(0, 140, 18, 18);

        jButton53.setText("accept");
        jButton53.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton53.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton53MousePressed(evt);
            }
        });
        jDeskt6.add(jButton53);
        jButton53.setBounds(125, 160, 54, 18);

        Tdespl50.setEditable(false);
        Tdespl50.setFont(fuente);
        Tdespl50.setText("see labels");
        Tdespl50.setToolTipText("");
        Tdespl50.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl50.setOpaque(false);
        jDeskt6.add(Tdespl50);
        Tdespl50.setBounds(20, 120, 160, 18);

        Tdespl51.setEditable(false);
        Tdespl51.setFont(fuente);
        Tdespl51.setText("load icons of the atoms");
        Tdespl51.setToolTipText("");
        Tdespl51.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl51.setOpaque(false);
        jDeskt6.add(Tdespl51);
        Tdespl51.setBounds(20, 100, 160, 18);

        Tdespl52.setEditable(false);
        Tdespl52.setFont(fuente);
        Tdespl52.setText("see atoms");
        Tdespl52.setToolTipText("");
        Tdespl52.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl52.setOpaque(false);
        jDeskt6.add(Tdespl52);
        Tdespl52.setBounds(20, 140, 160, 18);

        Tdespl18.setEditable(false);
        Tdespl18.setFont(fuente);
        Tdespl18.setText("TOL");
        Tdespl18.setToolTipText("");
        Tdespl18.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl18.setOpaque(false);
        jDeskt6.add(Tdespl18);
        Tdespl18.setBounds(0, 20, 40, 18);

        jDesktopPane4.add(jDeskt6);
        jDeskt6.setBounds(2, 62, 180, 180);

        changeLVS2.setFont(fuente);
        changeLVS2.setText("...");
        changeLVS2.setToolTipText("");
        changeLVS2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        changeLVS2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        changeLVS2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changeLVS2MousePressed(evt);
            }
        });
        jDesktopPane7.add(changeLVS2);
        changeLVS2.setBounds(0, 20, 18, 18);

        changeLVS3.setFont(fuente);
        changeLVS3.setText("...");
        changeLVS3.setToolTipText("");
        changeLVS3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        changeLVS3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        changeLVS3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changeLVS3MousePressed(evt);
            }
        });
        jDesktopPane7.add(changeLVS3);
        changeLVS3.setBounds(0, 40, 18, 18);

        Tdespl48.setEditable(false);
        Tdespl48.setFont(fuente);
        Tdespl48.setText("use xyz format");
        Tdespl48.setToolTipText("");
        Tdespl48.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl48.setOpaque(false);
        jDesktopPane7.add(Tdespl48);
        Tdespl48.setBounds(20, 40, 98, 18);

        Tdespl49.setEditable(false);
        Tdespl49.setFont(fuente);
        Tdespl49.setText("use xeo format");
        Tdespl49.setToolTipText("");
        Tdespl49.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl49.setOpaque(false);
        jDesktopPane7.add(Tdespl49);
        Tdespl49.setBounds(20, 20, 98, 18);

        Tdespl8.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl8.setEditable(false);
        Tdespl8.setFont(fuente);
        Tdespl8.setText("Positions of the atoms");
        Tdespl8.setToolTipText("");
        Tdespl8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl8.setFocusable(false);
        jDesktopPane7.add(Tdespl8);
        Tdespl8.setBounds(0, 0, 180, 18);

        changeLVS4.setFont(fuente);
        changeLVS4.setText("example");
        changeLVS4.setToolTipText("");
        changeLVS4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        changeLVS4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        changeLVS4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changeLVS4MousePressed(evt);
            }
        });
        jDesktopPane7.add(changeLVS4);
        changeLVS4.setBounds(120, 20, 60, 18);

        changeLVS5.setFont(fuente);
        changeLVS5.setText("example");
        changeLVS5.setToolTipText("");
        changeLVS5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        changeLVS5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        changeLVS5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changeLVS5MousePressed(evt);
            }
        });
        jDesktopPane7.add(changeLVS5);
        changeLVS5.setBounds(120, 40, 60, 18);

        jDesktopPane4.add(jDesktopPane7);
        jDesktopPane7.setBounds(2, 2, 180, 60);

        Tdespl4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl4.setEditable(false);
        Tdespl4.setFont(fuente);
        Tdespl4.setText("Periodicity");
        Tdespl4.setToolTipText("");
        Tdespl4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tdespl4MousePressed(evt);
            }
        });
        jDeskt4.add(Tdespl4);
        Tdespl4.setBounds(0, 0, 180, 18);

        changeLVS.setFont(fuente);
        changeLVS.setText("...");
        changeLVS.setToolTipText("");
        changeLVS.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        changeLVS.setMargin(new java.awt.Insets(0, 0, 0, 0));
        changeLVS.setOpaque(false);
        changeLVS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changeLVSMousePressed(evt);
            }
        });
        jDeskt4.add(changeLVS);
        changeLVS.setBounds(0, 80, 18, 18);

        Tdespl45.setEditable(false);
        Tdespl45.setFont(fuente);
        Tdespl45.setText("change lattice vectors");
        Tdespl45.setToolTipText("");
        Tdespl45.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl45.setOpaque(false);
        jDeskt4.add(Tdespl45);
        Tdespl45.setBounds(20, 80, 160, 18);

        lvs_11.setFont(fuente);
        lvs_11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_11.setText("99.0");
        lvs_11.setToolTipText("");
        lvs_11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_11);
        lvs_11.setBounds(0, 20, 58, 18);

        lvs_21.setFont(fuente);
        lvs_21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_21.setText("0.0");
        lvs_21.setToolTipText("");
        lvs_21.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_21);
        lvs_21.setBounds(0, 40, 58, 18);

        lvs_31.setFont(fuente);
        lvs_31.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_31.setText("0.0");
        lvs_31.setToolTipText("");
        lvs_31.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_31);
        lvs_31.setBounds(0, 60, 58, 18);

        lvs_12.setFont(fuente);
        lvs_12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_12.setText("0.0");
        lvs_12.setToolTipText("");
        lvs_12.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_12);
        lvs_12.setBounds(60, 20, 59, 18);

        lvs_22.setFont(fuente);
        lvs_22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_22.setText("99.0");
        lvs_22.setToolTipText("");
        lvs_22.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_22);
        lvs_22.setBounds(60, 40, 59, 18);

        lvs_32.setFont(fuente);
        lvs_32.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_32.setText("0.0");
        lvs_32.setToolTipText("");
        lvs_32.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_32);
        lvs_32.setBounds(60, 60, 59, 18);

        lvs_13.setFont(fuente);
        lvs_13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_13.setText("0.0");
        lvs_13.setToolTipText("");
        lvs_13.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_13);
        lvs_13.setBounds(121, 20, 59, 18);

        lvs_23.setFont(fuente);
        lvs_23.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_23.setText("0.0");
        lvs_23.setToolTipText("");
        lvs_23.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_23);
        lvs_23.setBounds(121, 40, 59, 18);

        lvs_33.setFont(fuente);
        lvs_33.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_33.setText("99.0");
        lvs_33.setToolTipText("");
        lvs_33.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_33);
        lvs_33.setBounds(121, 60, 59, 18);

        changeLVS1.setFont(fuente);
        changeLVS1.setText("...");
        changeLVS1.setToolTipText("");
        changeLVS1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        changeLVS1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        changeLVS1.setOpaque(false);
        changeLVS1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changeLVS1MousePressed(evt);
            }
        });
        jDeskt4.add(changeLVS1);
        changeLVS1.setBounds(0, 100, 18, 18);

        Tdespl46.setEditable(false);
        Tdespl46.setFont(fuente);
        Tdespl46.setText("Load lattice vector from file");
        Tdespl46.setToolTipText("");
        Tdespl46.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl46.setOpaque(false);
        jDeskt4.add(Tdespl46);
        Tdespl46.setBounds(20, 100, 160, 18);

        Border.setBackground(new java.awt.Color(255, 255, 255));
        Border.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Border.setBorderPainted(true);
        Border.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Border.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Border.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Border.setOpaque(false);
        jDeskt4.add(Border);
        Border.setBounds(0, 120, 18, 18);

        Tdespl47.setEditable(false);
        Tdespl47.setFont(fuente);
        Tdespl47.setText("use periodicity for borders");
        Tdespl47.setToolTipText("");
        Tdespl47.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl47.setOpaque(false);
        jDeskt4.add(Tdespl47);
        Tdespl47.setBounds(20, 120, 160, 18);

        jDesktopPane4.add(jDeskt4);
        jDeskt4.setBounds(2, 242, 180, 140);

        org.jdesktop.layout.GroupLayout jAtomsLayout = new org.jdesktop.layout.GroupLayout(jAtoms.getContentPane());
        jAtoms.getContentPane().setLayout(jAtomsLayout);
        jAtomsLayout.setHorizontalGroup(
            jAtomsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 183, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        jAtomsLayout.setVerticalGroup(
            jAtomsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 522, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        moreFiles.setTitle("multiple files");
        moreFiles.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        path_project.setFont(fuente);
        path_project.setToolTipText("");
        path_project.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        path_project.setFocusCycleRoot(true);
        path_project.setInheritsPopupMenu(true);
        path_project.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                path_projectKeyPressed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        ProjectTree.setFont(fuente);
        ProjectTree.setToolTipText("");
        ProjectTree.setExpandsSelectedPaths(false);
        ProjectTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProjectTreeMouseClicked(evt);
            }
        });
        ProjectTree.addTreeWillExpandListener(new javax.swing.event.TreeWillExpandListener() {
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
                ProjectTreeTreeWillExpand(evt);
            }
            public void treeWillCollapse(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
            }
        });
        jScrollPane1.setViewportView(ProjectTree);

        OpenProject.setFont(fuente);
        OpenProject.setText("add selected files");
        OpenProject.setToolTipText("");
        OpenProject.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        OpenProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OpenProjectMousePressed(evt);
            }
        });

        Tdespl53.setEditable(false);
        Tdespl53.setFont(fuente);
        Tdespl53.setText("Ctrol + mouse.LEFT (to selectd multiple files)");
        Tdespl53.setToolTipText("");
        Tdespl53.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl53.setOpaque(false);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jTextfiles.setColumns(20);
        jTextfiles.setFont(fuente);
        jTextfiles.setRows(5);
        jTextfiles.setToolTipText("");
        jTextfiles.setBorder(null);
        jScrollPane2.setViewportView(jTextfiles);

        OpenProject1.setFont(fuente);
        OpenProject1.setText("calculate images");
        OpenProject1.setToolTipText("");
        OpenProject1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        OpenProject1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OpenProject1MousePressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, path_project, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, OpenProject, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Tdespl53, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, OpenProject1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(path_project, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(OpenProject, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Tdespl53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 279, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(OpenProject1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout moreFilesLayout = new org.jdesktop.layout.GroupLayout(moreFiles.getContentPane());
        moreFiles.getContentPane().setLayout(moreFilesLayout);
        moreFilesLayout.setHorizontalGroup(
            moreFilesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        moreFilesLayout.setVerticalGroup(
            moreFilesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java_STM_AFM");

        TAB_STM.setBackground(new java.awt.Color(255, 255, 255));
        TAB_STM.setAlignmentX(0.0F);
        TAB_STM.setAlignmentY(0.0F);
        TAB_STM.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        screen_stm.setBackground(new java.awt.Color(255, 255, 255));
        screen_stm.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        screen_stm.setEnabled(false);
        screen_stm.setOpaque(true);
        screen_stm.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                screen_stmMouseDragged(evt);
            }
        });
        screen_stm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                screen_stmMousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(screen_stm);

        jDesktopPane1.setEnabled(false);

        color_inter_2.setBackground(new java.awt.Color(255, 255, 255));
        color_inter_2.setForeground(new java.awt.Color(153, 153, 255));
        color_inter_2.setText("...");
        color_inter_2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        color_inter_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                color_inter_2MousePressed(evt);
            }
        });
        jDesktopPane1.add(color_inter_2);
        color_inter_2.setBounds(142, 22, 18, 18);

        color_ini.setBackground(new java.awt.Color(255, 255, 255));
        color_ini.setForeground(new java.awt.Color(153, 153, 255));
        color_ini.setText("...");
        color_ini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        color_ini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                color_iniMousePressed(evt);
            }
        });
        jDesktopPane1.add(color_ini);
        color_ini.setBounds(0, 22, 18, 18);

        color_inter_1.setBackground(new java.awt.Color(255, 255, 255));
        color_inter_1.setForeground(new java.awt.Color(153, 153, 255));
        color_inter_1.setText("...");
        color_inter_1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        color_inter_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                color_inter_1MousePressed(evt);
            }
        });
        jDesktopPane1.add(color_inter_1);
        color_inter_1.setBounds(20, 22, 18, 18);

        color_fin.setBackground(new java.awt.Color(255, 255, 255));
        color_fin.setForeground(new java.awt.Color(153, 153, 255));
        color_fin.setText("...");
        color_fin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        color_fin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                color_finMousePressed(evt);
            }
        });
        jDesktopPane1.add(color_fin);
        color_fin.setBounds(162, 22, 18, 18);

        Sinter1.setBackground(new java.awt.Color(255, 255, 255));
        Sinter1.setValue(20);
        Sinter1.setAlignmentX(0.0F);
        Sinter1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Sinter1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Sinter1MouseDragged(evt);
            }
        });
        jDesktopPane1.add(Sinter1);
        Sinter1.setBounds(0, 42, 180, 20);

        Clabel.setBackground(new java.awt.Color(255, 255, 255));
        Clabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(Clabel);
        Clabel.setBounds(0, 64, 180, 20);

        Sinter2.setBackground(new java.awt.Color(255, 255, 255));
        Sinter2.setValue(80);
        Sinter2.setAlignmentX(0.0F);
        Sinter2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Sinter2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Sinter2MouseDragged(evt);
            }
        });
        jDesktopPane1.add(Sinter2);
        Sinter2.setBounds(0, 86, 180, 20);

        jButton49.setFont(fuente);
        jButton49.setText("accept");
        jButton49.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton49.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton49MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton49);
        jButton49.setBounds(120, 108, 60, 18);

        jButton60.setFont(fuente);
        jButton60.setText("difumi.");
        jButton60.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton60.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton60MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton60);
        jButton60.setBounds(48, 108, 69, 18);

        color_Fondo.setBackground(new java.awt.Color(255, 255, 255));
        color_Fondo.setForeground(new java.awt.Color(153, 153, 255));
        color_Fondo.setText("...");
        color_Fondo.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        color_Fondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                color_FondoMousePressed(evt);
            }
        });
        jDesktopPane1.add(color_Fondo);
        color_Fondo.setBounds(0, 108, 18, 18);

        Tdespl1.setEditable(false);
        Tdespl1.setFont(fuente);
        Tdespl1.setText("Color");
        Tdespl1.setToolTipText("the step to move the atoms selected");
        Tdespl1.setAutoscrolls(false);
        Tdespl1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl1.setFocusable(false);
        Tdespl1.setRequestFocusEnabled(false);
        jDesktopPane1.add(Tdespl1);
        Tdespl1.setBounds(0, 0, 180, 20);

        verEjesSTM.setBackground(new java.awt.Color(255, 255, 255));
        verEjesSTM.setFont(fuente);
        verEjesSTM.setSelected(true);
        verEjesSTM.setText("see info XY-Axis");
        verEjesSTM.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        verEjesSTM.setBorderPainted(true);
        verEjesSTM.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane1.add(verEjesSTM);
        verEjesSTM.setBounds(0, 128, 180, 18);
        verEjesSTM.getAccessibleContext().setAccessibleName("");

        verBarra.setBackground(new java.awt.Color(255, 255, 255));
        verBarra.setFont(fuente);
        verBarra.setSelected(true);
        verBarra.setText("see info Z-Axi");
        verBarra.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        verBarra.setBorderPainted(true);
        verBarra.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane1.add(verBarra);
        verBarra.setBounds(0, 148, 180, 18);
        verBarra.getAccessibleContext().setAccessibleName("");

        verCamino.setBackground(new java.awt.Color(255, 255, 255));
        verCamino.setFont(fuente);
        verCamino.setText("see Direction");
        verCamino.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        verCamino.setBorderPainted(true);
        verCamino.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane1.add(verCamino);
        verCamino.setBounds(0, 168, 180, 18);
        verCamino.getAccessibleContext().setAccessibleName("");

        jDesktopPane3.add(jDesktopPane1);
        jDesktopPane1.setBounds(2, 2, 180, 186);

        resol_Y.setFont(fuente);
        resol_Y.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resol_Y.setText("250");
        resol_Y.setAlignmentY(0.0F);
        resol_Y.setAutoscrolls(false);
        resol_Y.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane2.add(resol_Y);
        resol_Y.setBounds(91, 20, 89, 18);

        jButton48.setFont(fuente);
        jButton48.setText("accept");
        jButton48.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton48.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton48MousePressed(evt);
            }
        });
        jDesktopPane2.add(jButton48);
        jButton48.setBounds(0, 40, 180, 18);

        resol_X.setEditable(false);
        resol_X.setFont(fuente);
        resol_X.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resol_X.setAlignmentY(0.0F);
        resol_X.setAutoscrolls(false);
        resol_X.setEnabled(false);
        jDesktopPane2.add(resol_X);
        resol_X.setBounds(0, 20, 89, 18);

        Tdespl2.setEditable(false);
        Tdespl2.setFont(fuente);
        Tdespl2.setText("Size (pixels)");
        Tdespl2.setToolTipText("the step to move the atoms selected");
        Tdespl2.setAutoscrolls(false);
        Tdespl2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl2.setFocusable(false);
        Tdespl2.setRequestFocusEnabled(false);
        jDesktopPane2.add(Tdespl2);
        Tdespl2.setBounds(0, 0, 180, 18);

        jDesktopPane3.add(jDesktopPane2);
        jDesktopPane2.setBounds(2, 190, 180, 60);

        Tdespl7.setEditable(false);
        Tdespl7.setFont(fuente);
        Tdespl7.setText("use the columns (input file)");
        Tdespl7.setToolTipText("");
        Tdespl7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tdespl7MousePressed(evt);
            }
        });
        jDesktopPane5.add(Tdespl7);
        Tdespl7.setBounds(0, 0, 180, 18);

        lvs_14.setEditable(false);
        lvs_14.setFont(fuente);
        lvs_14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lvs_14.setText("set X");
        lvs_14.setToolTipText("");
        lvs_14.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane5.add(lvs_14);
        lvs_14.setBounds(0, 20, 58, 18);

        lvs_15.setEditable(false);
        lvs_15.setFont(fuente);
        lvs_15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lvs_15.setText("set Y");
        lvs_15.setToolTipText("");
        lvs_15.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane5.add(lvs_15);
        lvs_15.setBounds(60, 20, 59, 18);

        lvs_16.setEditable(false);
        lvs_16.setFont(fuente);
        lvs_16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lvs_16.setText("set Z");
        lvs_16.setToolTipText("");
        lvs_16.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane5.add(lvs_16);
        lvs_16.setBounds(121, 20, 59, 18);

        Col1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(Col1);
        Col1.setBounds(0, 40, 59, 18);

        Col2.setBackground(new java.awt.Color(255, 255, 255));
        Col2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(Col2);
        Col2.setBounds(61, 40, 59, 18);

        Col3.setBackground(new java.awt.Color(255, 255, 255));
        Col3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(Col3);
        Col3.setBounds(121, 40, 59, 18);

        jDesktopPane3.add(jDesktopPane5);
        jDesktopPane5.setBounds(2, 250, 180, 60);

        usar_exp.setBackground(new java.awt.Color(255, 255, 255));
        usar_exp.setFont(fuente);
        usar_exp.setText("use ln(sum(exp(-(z-zmin)/KT))");
        usar_exp.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        usar_exp.setBorderPainted(true);
        usar_exp.setMargin(new java.awt.Insets(0, 0, 0, 0));
        usar_exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usar_expActionPerformed(evt);
            }
        });
        jDesktopPane6.add(usar_exp);
        usar_exp.setBounds(0, 0, 180, 18);

        T.setFont(fuente);
        T.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        T.setText("300");
        T.setAlignmentY(0.0F);
        T.setAutoscrolls(false);
        T.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        T.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TActionPerformed(evt);
            }
        });
        jDesktopPane6.add(T);
        T.setBounds(80, 20, 100, 18);

        lvs_17.setEditable(false);
        lvs_17.setFont(fuente);
        lvs_17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lvs_17.setText("set T (K)");
        lvs_17.setToolTipText("");
        lvs_17.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane6.add(lvs_17);
        lvs_17.setBounds(0, 20, 80, 18);

        jDesktopPane3.add(jDesktopPane6);
        jDesktopPane6.setBounds(0, 310, 184, 63);

        org.jdesktop.layout.GroupLayout TAB_STMLayout = new org.jdesktop.layout.GroupLayout(TAB_STM);
        TAB_STM.setLayout(TAB_STMLayout);
        TAB_STMLayout.setHorizontalGroup(
            TAB_STMLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, TAB_STMLayout.createSequentialGroup()
                .add(jScrollPane8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jDesktopPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 184, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        TAB_STMLayout.setVerticalGroup(
            TAB_STMLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
            .add(jDesktopPane3)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/open.gif"))); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save.gif"))); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/reload.gif"))); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu3MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Tools");
        jMenu4.setFont(fuente
        );

        jMenuItem1.setFont(fuente
        );
        jMenuItem1.setText("insert atoms");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem2.setFont(fuente
        );
        jMenuItem2.setText("Open more files");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem3.setFont(fuente);
        jMenuItem3.setText("gnuplot");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem3MousePressed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuBar1.add(jMenu4);

        Me7.setText("About/Help");
        Me7.setFont(fuente);

        me39.setBackground(new java.awt.Color(255, 255, 255));
        me39.setFont(fuente);
        me39.setText("help");
        me39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        me39.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        me39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me39MousePressed(evt);
            }
        });
        Me7.add(me39);

        me35.setBackground(new java.awt.Color(255, 255, 255));
        me35.setFont(fuente);
        me35.setText("about");
        me35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        me35.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        me35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me35MousePressed(evt);
            }
        });
        Me7.add(me35);

        me36.setBackground(new java.awt.Color(255, 255, 255));
        me36.setFont(fuente);
        me36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gplv3.gif"))); // NOI18N
        me36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        me36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        me36.setIconTextGap(0);
        me36.setMargin(new java.awt.Insets(0, 0, 0, 0));
        me36.setPreferredSize(new java.awt.Dimension(100, 35));
        me36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me36MousePressed(evt);
            }
        });
        Me7.add(me36);

        me37.setBackground(new java.awt.Color(255, 255, 255));
        me37.setFont(fuente);
        me37.setText("show w");
        me37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        me37.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        me37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me37MousePressed(evt);
            }
        });
        Me7.add(me37);

        me38.setBackground(new java.awt.Color(255, 255, 255));
        me38.setFont(fuente);
        me38.setText("show c");
        me38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me38MousePressed(evt);
            }
        });
        Me7.add(me38);

        jMenuBar1.add(Me7);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TAB_STM, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(TAB_STM, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void me39MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me39MousePressed
        new dialogo.helpURL(getClass().getResource("/help/stm_afm.html")).setVisible(true);
    }//GEN-LAST:event_me39MousePressed
    
    private void me38MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me38MousePressed
        new editor("show c").LoadString(new gpl().gpl_c);
    }//GEN-LAST:event_me38MousePressed
    
    private void me37MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me37MousePressed
        new editor("show w").LoadString(new gpl().gpl_w);
    }//GEN-LAST:event_me37MousePressed
    
    private void me36MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me36MousePressed
        new dialogo.helpURL(getClass().getResource("/gpl-3.0.html")).setVisible(true);
    }//GEN-LAST:event_me36MousePressed
    
    private void me35MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me35MousePressed
        javax.swing.JDialog about = new javax.swing.JDialog() ;
        about.setSize(800,130);
        about.setTitle("about") ;
        javax.swing.JTextArea aboutArea = new javax.swing.JTextArea();
        aboutArea.setEditable(false);
        aboutArea.setText(new gpl().gpl_v3);
        about.add(aboutArea);
        about.setVisible(true);
    }//GEN-LAST:event_me35MousePressed
    
    private void changeLVS5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeLVS5MousePressed
        new dialogo.show_picture("xyz format").plot(new javax.swing.ImageIcon(getClass().getResource("/ico/xyz_e.jpg")));
    }//GEN-LAST:event_changeLVS5MousePressed
    
    private void changeLVS4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeLVS4MousePressed
        new dialogo.show_picture("xeo format").plot(new javax.swing.ImageIcon(getClass().getResource("/ico/xeoFormat_e.jpg")));
    }//GEN-LAST:event_changeLVS4MousePressed
    
    File newFile;
    private void OpenProject1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenProject1MousePressed
        String aux;
        for(int i=1;i<=cadena.getNLine(jTextfiles.getText());i++){
            //   System.out.println(cadena.getLine(i,jTextfiles.getText()));
            aux=cadena.getLine(i,jTextfiles.getText());
            newFile=new File(aux);
            File outFile = new File(aux.substring(0,aux.length()-4)+".jpg") ;
            System.out.println("calculating :"+newFile.getName()+" "+i+"/"+cadena.getNLine(jTextfiles.getText()));
            if(newFile.exists())  {
                enableSTM=true;
                path=newFile.getParent() ;
                stm.inputfile=newFile;
                stm.col1=Col1.getValue().hashCode();
                stm.col2=Col2.getValue().hashCode();
                stm.col3=Col3.getValue().hashCode();
                stm.maximos_dibujo();
                drawSTM(true);
                resol_X.setText(stm.ox+"");
                resol_Y.setText(stm.oy+"");
                try {
                    javax.imageio.ImageIO.write( stm.image_buffered  ,"JPG" ,outFile);
                }catch (java.io.IOException e) {System.out.println("errors save image");}
            }
        }
    }//GEN-LAST:event_OpenProject1MousePressed
    
    private void OpenProjectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenProjectMousePressed
        loadFiles();
    }//GEN-LAST:event_OpenProjectMousePressed
    
    private void ProjectTreeTreeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {//GEN-FIRST:event_ProjectTreeTreeWillExpand
        if(ProjectTree.getSelectionPath() != null)
            if(ProjectTree.getSelectionPath().getPathCount() > 1){
            newFile=new File(path_project.getText()+SEP+ProjectTree.getSelectionPath().getPathComponent(1).toString());
            //  System.out.println(ProjectTree.getlectionPath().getPathComponent(2).toString());
            if(newFile.exists())path_project.setText(newFile.toString());
            path=path_project.getText();
            }
        projectOpen();
    }//GEN-LAST:event_ProjectTreeTreeWillExpand
    
    private void ProjectTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProjectTreeMouseClicked
        if(ProjectTree.getSelectionPath() != null)
            if(ProjectTree.getSelectionPath().getPathCount() > 1)
                if(ProjectTree.getSelectionPath().getPathComponent(1).toString().equals("..")){
            newFile=(new File(path_project.getText()).getParentFile());
            if(newFile.exists())path_project.setText(newFile.toString());
            projectOpen();
                }else{
            newFile=new File(path_project.getText()+SEP+""+ProjectTree.getPathForLocation(evt.getX(),evt.getY()).getPathComponent(1).toString());
            path=newFile.getAbsolutePath();
            OpenProject.setEnabled(!newFile.isDirectory());
                }
        if(evt.getClickCount()==2) loadFiles();
    }//GEN-LAST:event_ProjectTreeMouseClicked
    
    private void path_projectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_path_projectKeyPressed
        if(evt.VK_ENTER == evt.getKeyCode())projectOpen();
    }//GEN-LAST:event_path_projectKeyPressed
    
    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        moreFiles.pack();
        moreFiles.setVisible(true);
        path_project.setText(new File(path)+SEP);
        projectOpen();
    }//GEN-LAST:event_jMenuItem2MousePressed
    
    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        jAtoms.pack();
        jAtoms.setVisible(true);
    }//GEN-LAST:event_jMenuItem1MousePressed
    
    private void mol_seeIndexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mol_seeIndexMouseClicked
        if(mol_seeIndex.isSelected()) stm_seeVol1.setSelected(false);
    }//GEN-LAST:event_mol_seeIndexMouseClicked
    
    private void changeLVS3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeLVS3MousePressed
        xyzFile=null;
        xyzFile =  new dialogo.chooser().fileChoose("Open file lvs","open",path+SEP+".") ;
        load_xyz();
        enableLoadAtoms=true;
    }//GEN-LAST:event_changeLVS3MousePressed
    
    private void changeLVS2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeLVS2MousePressed
        xyzFile=null;
        xyzFile =  new dialogo.chooser().fileChoose("Open file (xeo format)","open",path+SEP+".") ;
        if(xyzFile!=null){
            if(xyzFile.exists())
                try{
                    String aux="";
                    java.io.BufferedReader inlvs = new java.io.BufferedReader(new java.io.FileReader(xyzFile));
                    aux=inlvs.readLine();
                    int natoms=cadena.readColInt(1,aux);
                    for(int i=0;i<=natoms;i++) inlvs.readLine();
                    aux=inlvs.readLine();
                    lvs_11.setText(cadena.readColString(1,aux));
                    lvs_12.setText(cadena.readColString(2,aux));
                    lvs_13.setText(cadena.readColString(3,aux));
                    aux=inlvs.readLine();
                    lvs_21.setText(cadena.readColString(1,aux));
                    lvs_22.setText(cadena.readColString(2,aux));
                    lvs_23.setText(cadena.readColString(3,aux));
                    aux=inlvs.readLine();
                    lvs_31.setText(cadena.readColString(1,aux));
                    lvs_32.setText(cadena.readColString(2,aux));
                    lvs_33.setText(cadena.readColString(3,aux));
                    inlvs.close();
                }catch (java.io.IOException oe) {System.out.println("error read lvs");}
            loadLVS();
        }
        load_xyz();
        drawSTM(false);
        enableLoadAtoms=true;
    }//GEN-LAST:event_changeLVS2MousePressed
    
    private void Tdespl7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tdespl7MousePressed
// TODO add your handling code here:
    }//GEN-LAST:event_Tdespl7MousePressed
    
    private void changeLVS1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeLVS1MousePressed
        newFile=null;
        String aux="";
        newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+"/.") ;
        if(newFile!=null){
            if(newFile.exists())
                try{
                    java.io.BufferedReader inlvs = new java.io.BufferedReader(new java.io.FileReader(newFile));
                    aux=inlvs.readLine();
                    lvs_11.setText(cadena.readColString(1,aux));
                    lvs_12.setText(cadena.readColString(2,aux));
                    lvs_13.setText(cadena.readColString(3,aux));
                    aux=inlvs.readLine();
                    lvs_21.setText(cadena.readColString(1,aux));
                    lvs_22.setText(cadena.readColString(2,aux));
                    lvs_23.setText(cadena.readColString(3,aux));
                    aux=inlvs.readLine();
                    lvs_31.setText(cadena.readColString(1,aux));
                    lvs_32.setText(cadena.readColString(2,aux));
                    lvs_33.setText(cadena.readColString(3,aux));
                    inlvs.close();
                }catch (java.io.IOException oe) {System.out.println("error read lvs");}
            loadLVS();
        }
    }//GEN-LAST:event_changeLVS1MousePressed
    
    private void verIconosAtomosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verIconosAtomosMouseClicked
        drawSTM(false);
    }//GEN-LAST:event_verIconosAtomosMouseClicked
    
    private void stm_seeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stm_seeLabelMouseClicked
        drawSTM(false);
    }//GEN-LAST:event_stm_seeLabelMouseClicked
    
    private void jButton53MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton53MousePressed
        load_xyz();
        drawSTM(false);
    }//GEN-LAST:event_jButton53MousePressed
    int jDesCol;
    private void Tdespl6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tdespl6MousePressed
        if( Tdespl6.isEnabled() )
            jDeskt6.setSize(180,20);
        else
            jDeskt6.setSize(180,4*20);
        Tdespl6.setEnabled( !Tdespl6.isEnabled());
    }//GEN-LAST:event_Tdespl6MousePressed
    
    private void jButton62MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton62MousePressed
        load_xyz();
        drawSTM(false);
    }//GEN-LAST:event_jButton62MousePressed
    
    private void stm_seeVol1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stm_seeVol1MouseClicked
        if(stm_seeVol1.isSelected()) mol_seeIndex.setSelected(false);
    }//GEN-LAST:event_stm_seeVol1MouseClicked
    
    private void Tdespl5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tdespl5MousePressed
        jDesCol=6*20;
        if( Tdespl5.isEnabled() ){
            jDeskt5.setSize(180,20);
            jDesCol=-jDesCol;
        }else
            jDeskt5.setSize(180,jDesCol+20);
        jDeskt6.setLocation(0,jDeskt6.getY()+jDesCol);
        Tdespl5.setEnabled( !Tdespl5.isEnabled());
    }//GEN-LAST:event_Tdespl5MousePressed
    
    private void changeLVSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeLVSMousePressed
        loadLVS();
    }//GEN-LAST:event_changeLVSMousePressed
    
    private void Tdespl4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tdespl4MousePressed
        jDesCol=4*20;
        if( Tdespl4.isEnabled() ){
            jDeskt4.setSize(180,20);
            jDesCol=-jDesCol;
        } else jDeskt4.setSize(180,jDesCol+20);
        jDeskt6.setLocation(0,jDeskt6.getY()+jDesCol);
        jDeskt5.setLocation(0,jDeskt5.getY()+jDesCol);
        Tdespl4.setEnabled( !Tdespl4.isEnabled());
    }//GEN-LAST:event_Tdespl4MousePressed
    
    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        stm.maximos_dibujo();
        drawSTM(true);
        
    }//GEN-LAST:event_jMenu3MousePressed
    
    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        new dialogo.chooser().savePicture("Save file *.jpg","save",stm.inputfile.getAbsolutePath(),stm.image_buffered);
    }//GEN-LAST:event_jMenu2MousePressed
    
    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        newFile = null;
        if(stm.inputfile != null) newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",stm.inputfile.getAbsolutePath()) ;
        else newFile =  new dialogo.chooser().fileChoose("Open file dens.out","open",path+"/.") ;
        if(newFile!=null)  {
            enableSTM=true;
            path=newFile.getParent() ;
            stm.inputfile=newFile;
            stm.col1=Col1.getValue().hashCode();
            stm.col2=Col2.getValue().hashCode();
            stm.col3=Col3.getValue().hashCode();
            stm.maximos_dibujo();
            drawSTM(true);
            resol_X.setText(stm.ox+"");
            resol_Y.setText(stm.oy+"");
        }
    }//GEN-LAST:event_jMenu1MousePressed
    
    private void screen_stmMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_stmMouseDragged
        if(verCamino.isSelected()){
            if(   stm.X_mouse_fin!=evt.getX() ||  stm.Y_mouse_fin!=evt.getY()){
                stm.X_mouse_fin=evt.getX();
                stm.Y_mouse_fin=evt.getY();
                drawSTM(false);
            }
        }
    }//GEN-LAST:event_screen_stmMouseDragged
    
    private void screen_stmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_stmMousePressed
        if(evt.getButton()==evt.BUTTON1) {
            stm.X_mouse_ini=evt.getX();
            stm.Y_mouse_ini=evt.getY();
        }
    }//GEN-LAST:event_screen_stmMousePressed
    
    private void D_verAtomosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D_verAtomosMouseClicked
        drawSTM(false);
    }//GEN-LAST:event_D_verAtomosMouseClicked
    
    private void jButton48MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton48MousePressed
        if(enableSTM){
            stm.resolucion=(int) Double.valueOf(resol_Y.getText()).doubleValue();
            drawSTM(true);
            resol_X.setText(stm.ox+"");
            resol_Y.setText(stm.oy+"");
        }
    }//GEN-LAST:event_jButton48MousePressed
    
    private void jButton60MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton60MousePressed
        if(enableSTM){
            stm.difuminate();
            drawSTM(false);
        }
    }//GEN-LAST:event_jButton60MousePressed
    
    private void jButton49MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton49MousePressed
        stm.posicion_1=Sinter1.getValue();
        stm.posicion_2=Sinter2.getValue();
        drawSTM(false);
    }//GEN-LAST:event_jButton49MousePressed
    
    private void color_finMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_finMousePressed
        color_fin.setBackground(javax.swing.JColorChooser.showDialog( this , "color final", color_fin.getBackground() ));
        dialogo_color();
    }//GEN-LAST:event_color_finMousePressed
    
    private void color_inter_2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_inter_2MousePressed
        color_inter_2.setBackground(javax.swing.JColorChooser.showDialog( this , "color final", color_inter_2.getBackground() ));
        dialogo_color();
    }//GEN-LAST:event_color_inter_2MousePressed
    
    private void color_iniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_iniMousePressed
        color_ini.setBackground(javax.swing.JColorChooser.showDialog( this , "color final", color_ini.getBackground() ));
        dialogo_color();
    }//GEN-LAST:event_color_iniMousePressed
    
    private void color_inter_1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_inter_1MousePressed
        if(enableSTM){
            color_inter_1.setBackground(javax.swing.JColorChooser.showDialog( this , "color final", color_inter_1.getBackground() ));
            dialogo_color();
        }
    }//GEN-LAST:event_color_inter_1MousePressed
    
    private void Sinter1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sinter1MouseDragged
        dialogo_color();
    }//GEN-LAST:event_Sinter1MouseDragged
    
    private void Sinter2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sinter2MouseDragged
        dialogo_color();
    }//GEN-LAST:event_Sinter2MouseDragged
    
    private void color_FondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_FondoMousePressed
        color_Fondo.setBackground(javax.swing.JColorChooser.showDialog( this , "color final", color_inter_2.getBackground() ));
        dialogo_color();
    }//GEN-LAST:event_color_FondoMousePressed

    private void TActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TActionPerformed

    private void usar_expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usar_expActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usar_expActionPerformed

    private void jMenuItem3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MousePressed
       new edt().start();
    }//GEN-LAST:event_jMenuItem3MousePressed
    class edt extends Thread {
         public void run() {
         new editor("splot.dat").LoadString(stm.splot());
         }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Border;
    private javax.swing.JLabel Clabel;
    private javax.swing.JSpinner Col1;
    private javax.swing.JSpinner Col2;
    private javax.swing.JSpinner Col3;
    private javax.swing.JTextField D_Ratom;
    private javax.swing.JSlider D_alfa;
    private javax.swing.JCheckBox D_verAtomos;
    private javax.swing.JMenu Me7;
    private javax.swing.JButton OpenProject;
    private javax.swing.JButton OpenProject1;
    private javax.swing.JTree ProjectTree;
    private javax.swing.JSlider Sinter1;
    private javax.swing.JSlider Sinter2;
    private javax.swing.JTextField T;
    private javax.swing.JPanel TAB_STM;
    private javax.swing.JSlider TOL1;
    private javax.swing.JTextField Tdespl1;
    private javax.swing.JTextField Tdespl15;
    private javax.swing.JTextField Tdespl16;
    private javax.swing.JTextField Tdespl17;
    private javax.swing.JTextField Tdespl18;
    private javax.swing.JTextField Tdespl2;
    private javax.swing.JTextField Tdespl21;
    private javax.swing.JTextField Tdespl22;
    private javax.swing.JTextField Tdespl4;
    private javax.swing.JTextField Tdespl45;
    private javax.swing.JTextField Tdespl46;
    private javax.swing.JTextField Tdespl47;
    private javax.swing.JTextField Tdespl48;
    private javax.swing.JTextField Tdespl49;
    private javax.swing.JTextField Tdespl5;
    private javax.swing.JTextField Tdespl50;
    private javax.swing.JTextField Tdespl51;
    private javax.swing.JTextField Tdespl52;
    private javax.swing.JTextField Tdespl53;
    private javax.swing.JTextField Tdespl6;
    private javax.swing.JTextField Tdespl7;
    private javax.swing.JTextField Tdespl8;
    private javax.swing.JButton changeLVS;
    private javax.swing.JButton changeLVS1;
    private javax.swing.JButton changeLVS2;
    private javax.swing.JButton changeLVS3;
    private javax.swing.JButton changeLVS4;
    private javax.swing.JButton changeLVS5;
    private javax.swing.JButton color_Fondo;
    private javax.swing.JButton color_fin;
    private javax.swing.JButton color_ini;
    private javax.swing.JButton color_inter_1;
    private javax.swing.JButton color_inter_2;
    private javax.swing.JFrame jAtoms;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton62;
    private javax.swing.JDesktopPane jDeskt4;
    private javax.swing.JDesktopPane jDeskt5;
    private javax.swing.JDesktopPane jDeskt6;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JDesktopPane jDesktopPane5;
    private javax.swing.JDesktopPane jDesktopPane6;
    private javax.swing.JDesktopPane jDesktopPane7;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextArea jTextfiles;
    private javax.swing.JTextField lvs_11;
    private javax.swing.JTextField lvs_12;
    private javax.swing.JTextField lvs_13;
    private javax.swing.JTextField lvs_14;
    private javax.swing.JTextField lvs_15;
    private javax.swing.JTextField lvs_16;
    private javax.swing.JTextField lvs_17;
    private javax.swing.JTextField lvs_21;
    private javax.swing.JTextField lvs_22;
    private javax.swing.JTextField lvs_23;
    private javax.swing.JTextField lvs_31;
    private javax.swing.JTextField lvs_32;
    private javax.swing.JTextField lvs_33;
    private javax.swing.JMenuItem me35;
    private javax.swing.JMenuItem me36;
    private javax.swing.JMenuItem me37;
    private javax.swing.JMenuItem me38;
    private javax.swing.JMenuItem me39;
    private javax.swing.JTextField mol_lvs_1;
    private javax.swing.JTextField mol_lvs_2;
    private javax.swing.JTextField mol_lvs_3;
    private javax.swing.JCheckBox mol_seeIndex;
    private javax.swing.JFrame moreFiles;
    private javax.swing.JTextField path_project;
    private javax.swing.JTextField resol_X;
    private javax.swing.JTextField resol_Y;
    private javax.swing.JLabel screen_stm;
    private javax.swing.JCheckBox seeBond1;
    private javax.swing.JTextField stmXfin;
    private javax.swing.JTextField stmXini;
    private javax.swing.JTextField stmYfin;
    private javax.swing.JTextField stmYini;
    private javax.swing.JTextField stmZfin;
    private javax.swing.JTextField stmZini;
    private javax.swing.JTextField stm_grosor;
    private javax.swing.JCheckBox stm_seeLabel;
    private javax.swing.JCheckBox stm_seeVol1;
    private javax.swing.JCheckBox usar_exp;
    private javax.swing.JCheckBox verBarra;
    private javax.swing.JCheckBox verCamino;
    private javax.swing.JCheckBox verEjesSTM;
    private javax.swing.JCheckBox verIconosAtomos;
    // End of variables declaration//GEN-END:variables
    
    //--------STM------------------------
    
    void loadLVS(){
        stm.lvs[0][0] = Double.valueOf(lvs_11.getText()).doubleValue();
        stm.lvs[0][1] = Double.valueOf(lvs_12.getText()).doubleValue();
        stm.lvs[0][2] = Double.valueOf(lvs_12.getText()).doubleValue();
        stm.lvs[1][0] = Double.valueOf(lvs_21.getText()).doubleValue();
        stm.lvs[1][1] = Double.valueOf(lvs_22.getText()).doubleValue();
        stm.lvs[1][2] = Double.valueOf(lvs_23.getText()).doubleValue();
        stm.lvs[2][0] = Double.valueOf(lvs_31.getText()).doubleValue();
        stm.lvs[2][1] = Double.valueOf(lvs_32.getText()).doubleValue();
        stm.lvs[2][2] = Double.valueOf(lvs_33.getText()).doubleValue();
    }
    
    void dialogo_color(){
        java.awt.image.BufferedImage color_buffer;
        java.awt.Graphics color_graphics ;
        color_buffer = new java.awt.image.BufferedImage(Clabel.getWidth() ,Clabel.getHeight() , java.awt.image.BufferedImage.TYPE_INT_RGB);
        color_graphics = color_buffer.createGraphics();
        int azul=0,rojo=0,verde=0;
        stm.colorFondo=color_Fondo.getBackground();
        stm.colorIni=color_ini.getBackground();
        stm.colorInter1=color_inter_1.getBackground();
        stm.colorInter2=color_inter_2.getBackground();
        stm.colorFin = color_fin.getBackground();
        int p_1=(Sinter1.getValue()*Clabel.getWidth())/Sinter1.getMaximum();
        int p_2=(Sinter2.getValue()*Clabel.getWidth())/Sinter2.getMaximum();
        if(p_1 >= p_2){
            int auxpos=p_2;
            p_2=p_1;
            p_1=auxpos;
        }
        for(int C_aux=0;C_aux < Clabel.getWidth(); C_aux+=4){
            if(C_aux<p_1 && p_1 > 0) {
                azul=C_aux*(stm.colorInter1.getBlue()-stm.colorIni.getBlue())/p_1 + stm.colorIni.getBlue();
                rojo=C_aux*(stm.colorInter1.getRed()-stm.colorIni.getRed())/p_1+stm.colorIni.getRed();
                verde=C_aux*(stm.colorInter1.getGreen()-stm.colorIni.getGreen())/p_1+stm.colorIni.getGreen();
            }
            if(C_aux>=p_1 && C_aux<p_2 && p_1!=p_2) {
                azul=(C_aux-p_1)*(stm.colorInter2.getBlue()-stm.colorInter1.getBlue())/(p_2-p_1) + stm.colorInter1.getBlue();
                rojo=(C_aux-p_1)*(stm.colorInter2.getRed()-stm.colorInter1.getRed())/(p_2-p_1)+stm.colorInter1.getRed();
                verde=(C_aux-p_1)*(stm.colorInter2.getGreen()-stm.colorInter1.getGreen())/(p_2-p_1)+stm.colorInter1.getGreen();
            }
            if(C_aux >= p_2  && p_2 < Clabel.getWidth() ){
                azul=(C_aux-p_2)*(stm.colorFin.getBlue()-stm.colorInter2.getBlue())/(Clabel.getWidth()-p_2)+stm.colorInter2.getBlue();
                rojo=(C_aux-p_2)*(stm.colorFin.getRed()-stm.colorInter2.getRed())/(Clabel.getWidth()-p_2)+stm.colorInter2.getRed();
                verde=(C_aux-p_2)*(stm.colorFin.getGreen()-stm.colorInter2.getGreen())/(Clabel.getWidth()-p_2)+stm.colorInter2.getGreen();
            }
            //azul  =  (int) (C[lx][ly]*Math.cos((C[lx][ly])*Math.PI/255/2)) ; //(C[lx][ly]*Math.pow(C[lx][ly],6)/Math.pow(255,6));
            color_graphics.setColor(new java.awt.Color(rojo,verde,azul));
            color_graphics.fillRect(C_aux,0,4, Clabel.getHeight());
        }
        //  Dcolor_panel.getGraphics().drawImage(color_buffer,0,0,this);
        javax.swing.ImageIcon Cicon = new javax.swing.ImageIcon(color_buffer);
        Clabel.setIcon(Cicon);
        //ojo con esto -> deriva del problema de inicializar toda la mierda esta
    }
    
    void drawSTM(boolean calcular){
        if(enableSTM){
            stm.T=(Double.valueOf(T.getText()).doubleValue());
            stm.usar_exp=usar_exp.isSelected();
            stm.alfa=D_alfa.getValue();
            stm.verAtomos=D_verAtomos.isSelected();
            stm.radio = (int) (Double.valueOf(D_Ratom.getText()).doubleValue());
            stm.verIconosAtomos=verIconosAtomos.isSelected();
            stm.seeBond=seeBond1.isSelected();
            stm.seeLabel=stm_seeLabel.isSelected();
            stm.grosor=(int) Double.valueOf(stm_grosor.getText()).doubleValue();
            stm.verIconosAtomos=verIconosAtomos.isSelected();
            screen_stm.setEnabled(true) ;
            if(calcular)  {
                // clock(true);
                stm.calcular_dibujo();
                //  System.out.println(stm.resolucion+" "+stm.oy);
                // clock(false);
                if(!stm_seeVol1.isSelected()){
                    stmXini.setText(cadena.pasarString(stm.x_min-0.1));
                    stmYini.setText(cadena.pasarString(stm.y_min-0.1));
                    stmXfin.setText(cadena.pasarString(stm.x_max+0.1));
                    stmYfin.setText(cadena.pasarString(stm.y_max+0.1));
                }
            }
            stm.borde=Border.isSelected();
            stm.col1=Col1.getValue().hashCode();
            stm.col2=Col2.getValue().hashCode();
            stm.col3=Col3.getValue().hashCode();
            stm.verEjes=verEjesSTM.isSelected();
            stm.verBarra=verBarra.isSelected();
            stm.pintar_dibujo();
            stm.verDireccion=verCamino.isSelected();
            if(stm.verDireccion) stm.pintar_direccion();
            screen_stm.setIcon(stm.icon);
        }
    }
    public double modulo(double Px,double Py, double Pz){return Math.pow(Math.pow(Px,2)+Math.pow(Py,2)+Math.pow(Pz,2),0.5);}
    
    int il,imax,jmax,kmax,Natom,lvs_1,lvs_2,lvs_3;
    double max,x,y,z,z_min,z_max,y_min,y_max,x_min,x_max;
    boolean eq;
    
    public void load_xyz(){
        if(enableSTM){
            if(enableLoadAtoms)
                if(xyzFile.exists())
                    D_verAtomos.setSelected(true);
            loadLVS();
            stm.bas.clear();
            java.util.ArrayList<atomo> aux = new java.util.ArrayList();
            try{
                String str="";
                java.io.BufferedReader inxyz = new java.io.BufferedReader(new java.io.FileReader(xyzFile.getAbsolutePath()));
                int natom=0;
                str = inxyz.readLine();
                natom =cadena.readColInt(1,str);
                inxyz.readLine();
                for(int i=1;i<=natom;i++){
                    str=inxyz.readLine();
                    atomo atom = new atomo();
                    atom.pos=i;
                    atom.Z=stm.table.getZ(cadena.readColString(1,str));
                    atom.symbol=stm.table.getSymbol(atom.Z);
                    atom.x=cadena.readColDouble(2,str);
                    atom.y=cadena.readColDouble(3,str);
                    atom.z=cadena.readColDouble(4,str);
                    aux.add(atom);
                    if(!stm_seeVol1.isSelected()){
                        R_min[2]=(atom.z<R_min[2])?atom.z:R_min[2];
                        R_max[2]=(atom.z>R_max[2])?atom.z:R_max[2];
                    }
                }
                if(!stm_seeVol1.isSelected()){
                    stmZini.setText(cadena.pasarString(R_min[2]-0.1));
                    stmZfin.setText(cadena.pasarString(R_max[2]+0.1));
                }
                //------- he copiado los metodos Adj_Vol(), Adj_ijk():
                if(stm_seeVol1.isSelected()) {
                    R_min[0]=Double.valueOf(stmXini.getText()).doubleValue();
                    R_min[1]=Double.valueOf(stmYini.getText()).doubleValue();
                    R_min[2]=Double.valueOf(stmZini.getText()).doubleValue();
                    R_max[0]=Double.valueOf(stmXfin.getText()).doubleValue();
                    R_max[1]=Double.valueOf(stmYfin.getText()).doubleValue();
                    R_max[2]=Double.valueOf(stmZfin.getText()).doubleValue();
                    
                    //los quitmos luego (muy bueno !!)
                    
                    max= modulo(R_min[0]-R_max[0],R_min[1]-R_max[1],R_min[2]-R_max[2]);
                    imax=(int) (2*max/modulo(stm.lvs[0][0],stm.lvs[1][0],stm.lvs[2][0])) ;
                    jmax=(int) (2*max/modulo(stm.lvs[0][1],stm.lvs[1][1],stm.lvs[2][1])) ;
                    kmax=(int) (2*max/modulo(stm.lvs[0][2],stm.lvs[1][2],stm.lvs[2][2])) ;
                    Natom=aux.size();
                    for(int n=0;n<Natom;n++)
                        for(int i = -imax ; i<= imax ; i++  )
                            for(int j = -jmax ; j<= jmax ; j++  )
                                for(int k = -kmax; k <= kmax ; k++  ){
                        x= aux.get(n).x + ( i*stm.lvs[0][0] + j*stm.lvs[1][0] + k*stm.lvs[2][0]) ;
                        y= aux.get(n).y + ( i*stm.lvs[0][1] + j*stm.lvs[1][1] + k*stm.lvs[2][1]) ;
                        z= aux.get(n).z + ( i*stm.lvs[0][2] + j*stm.lvs[1][2] + k*stm.lvs[2][2]) ;
                        if( x > R_min[0]&& x < R_max[0] && y > R_min[1]&& y < R_max[1] && z > R_min[2]&& z < R_max[2]){
                            eq=false;
                            for(int a=0;a<aux.size();a++) if( aux.get(a).distamcePunto(x,y,z) < 0.01 ) eq=true;
                            if(!eq){
                                atomo atom = new atomo();
                                atom.pos=natom;
                                atom.Z=aux.get(n).Z;
                                atom.symbol=aux.get(n).symbol;
                                atom.x=x;
                                atom.y=y;
                                atom.z=z;
                                aux.add(atom);
                                natom++;
                            }
                        }
                                }
                    for(int n=0;n<aux.size();n++)
                        if( aux.get(n).x < R_min[0]|| aux.get(n).x > R_max[0] ||
                            aux.get(n).x < R_min[1]|| aux.get(n).y > R_max[1] ||
                            aux.get(n).z < R_min[2]|| aux.get(n).z> R_max[2]) {
                        aux.remove(n); n--;
                        }
                    
                }
                if(mol_seeIndex.isSelected()){
                    lvs_1=(int) Double.valueOf(mol_lvs_1.getText()).doubleValue();
                    lvs_2=(int) Double.valueOf(mol_lvs_2.getText()).doubleValue();
                    lvs_3=(int) Double.valueOf(mol_lvs_3.getText()).doubleValue();
                    Natom=aux.size();
                    for(int n=0;n<Natom;n++)
                        for(int i = 0 ; i< lvs_1  ; i++  )
                            for(int j = 0 ; j< lvs_2    ; j++  )
                                for(int k = 0; k < lvs_3 ; k++  ){
                        x= aux.get(n).x + ( i*stm.lvs[0][0] + j*stm.lvs[1][0] + k*stm.lvs[2][0]) ;
                        y= aux.get(n).y + ( i*stm.lvs[0][1] + j*stm.lvs[1][1] + k*stm.lvs[2][1]) ;
                        z= aux.get(n).z + ( i*stm.lvs[0][2] + j*stm.lvs[1][2] + k*stm.lvs[2][2]) ;
                        eq=false;
                        for(int a=0;a<aux.size();a++) if( aux.get(a).distamcePunto(x,y,z) < 0.01 ) eq=true;
                        if(!eq){
                            atomo atom = new atomo();
                            atom.pos=natom;
                            atom.Z=aux.get(n).Z;
                            atom.symbol=aux.get(n).symbol;
                            atom.x=x;
                            atom.y=y;
                            atom.z=z;
                            aux.add(atom);
                            natom++;
                        }
                                }
                }
                
                //----------------------------------------------------------
                //----------------------------------------------------------
                //------ cargamos los enlaces ---------
                int r1,r2;
                double tol=TOL1.getValue();
                for(int i=0;i<aux.size();i++){
                    aux.get(i).ini_enlace();
                    r1 =(int) stm.table.getRadio_100(aux.get(i).Z);
                    for(int j=0;j<aux.size()/*i*/;j++){ //lo hacemos con todos por que este no sabe cual esta mas cerca
                        r2 =(int) stm.table.getRadio_100(aux.get(j).Z);
                        if(IntD_100(aux.get(i),aux.get(j))  < (r1+r2)/2/tol ) {
                            aux.get(i).add_enlace(aux.get(j).pos);  //este esta fijo
                        }
                    }
                }
                //---------------------------
                stm.bas=aux;
                inxyz.close();
            }catch (java.io.IOException oe) {System.out.println("error read basfile");}
        }
    }
    public int IntD_100(atomo atomo1, atomo atomo2) {
        return ((int) (100*Math.pow( Math.pow(atomo1.x-atomo2.x,2) +
                Math.pow(atomo1.y-atomo2.y,2) +
                Math.pow(atomo1.z-atomo2.z,2) ,0.5)));  }
    
    File carpeta;
    int nCarpetas;
    String[] subcarpeta ;
    void projectOpen(){
        carpeta = new File(path_project.getText());
        if(path_project.getText().trim().equals("")) carpeta= new File(path);
        newFile=carpeta.getAbsoluteFile();
        if(newFile.exists())path_project.setText(newFile.toString());
        subcarpeta = carpeta.list();
        javax.swing.tree.DefaultMutableTreeNode root =  new javax.swing.tree.DefaultMutableTreeNode(carpeta.getName());
        root.add(new javax.swing.tree.DefaultMutableTreeNode(".."));
        nCarpetas=0;
        if (subcarpeta != null) {
            for (int i=0; i<subcarpeta.length; i++) {
                File file=new File(carpeta.getAbsolutePath()+SEP+subcarpeta[i]);
                if(!file.getName().substring(0,1).equals(".")){
                    if(file.isDirectory()){
                        javax.swing.tree.DefaultMutableTreeNode rootd = new javax.swing.tree.DefaultMutableTreeNode(subcarpeta[i]);
                        rootd.add(new javax.swing.tree.DefaultMutableTreeNode( file.getName()));
                        root.add(rootd);
                        nCarpetas++;
                    }else{
                        javax.swing.tree.DefaultMutableTreeNode rootd = new javax.swing.tree.DefaultMutableTreeNode(subcarpeta[i]);
                        root.add(new javax.swing.tree.DefaultMutableTreeNode(file.getName()));
                        //root.add(rootd);
                        nCarpetas++;
                    }
                }
                
            }
        }
        ProjectTree.setModel(new javax.swing.tree.DefaultTreeModel(root));
    }
    
    void loadFiles(){
        javax.swing.tree.TreePath [] select;
        int N,j;
        if(ProjectTree.getRowCount()<3) {
            ProjectTree.clearSelection();
            ProjectTree.setSelectionRow(1);
        }
        if(!ProjectTree.isSelectionEmpty()){
            select = ProjectTree.getSelectionPaths();
            N=ProjectTree.getSelectionPaths().length;
            File [] fileAux = new File[N];
            for(int i=N-1;i>=0;i--)
                if(select[i].getPathCount()==2){
                if(ProjectTree.getRowCount()<3) path=path_project.getText();
                else  path=path_project.getText()+SEP+select[i].getPathComponent(1).toString();
                jTextfiles.setText(path+"\n"+jTextfiles.getText());
                }
        }
    }
    
}