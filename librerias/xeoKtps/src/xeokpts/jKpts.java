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

package xeokpts;

public class jKpts extends javax.swing.JFrame {
    reader.reader cadena = new reader.reader();
    
    java.awt.Font fuente,fuenteMono;
    recorrido kpts = new recorrido();
    
    double [] K= new double[4];
    double [] u= new double[4];
    double [] lvs_1 = new double[4];
    double [] lvs_2 = new double[4];
    double [] lvs_3 = new double[4];
    double [] Rlvs_1 = new double[4];
    double [] Rlvs_2 = new double[4];
    double [] Rlvs_3 = new double[4];
    int [] q = new int[4];
    
    /** Creates new form jKpts */
    public jKpts() {
        lvs_1[1]=99 ;  lvs_2[1]=0 ;  lvs_3[1]=0;
        lvs_1[2]=0 ;  lvs_2[2]=99 ;  lvs_3[2]=0;
        lvs_1[3]=0 ;  lvs_2[3]=0 ;  lvs_3[3]=99;
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
        
    }
    
    
    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroupLVS = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel3 = new javax.swing.JLabel();
        jDesktopPane5 = new javax.swing.JDesktopPane();
        jLabel41 = new javax.swing.JLabel();
        a_par = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jRadioButtonCubic = new javax.swing.JRadioButton();
        jRadioButtonFCC = new javax.swing.JRadioButton();
        jRadioButtonBCC = new javax.swing.JRadioButton();
        jRadioButtonDIA = new javax.swing.JRadioButton();
        jButton39 = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        jDesktopPane8 = new javax.swing.JDesktopPane();
        jLabel63 = new javax.swing.JLabel();
        jTableLVS = new javax.swing.JTable();
        jTableRLVS = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        jDesktopPane7 = new javax.swing.JDesktopPane();
        jLabel42 = new javax.swing.JLabel();
        kptsX = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        kptsY = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        kptsZ = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        sim = new javax.swing.JCheckBox();
        jButton29 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInput = new javax.swing.JTextArea();
        jDesktopPane6 = new javax.swing.JDesktopPane();
        jLabel61 = new javax.swing.JLabel();
        kpts_N3 = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lvs_a1 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        lvs_b2 = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        lvs_a2 = new javax.swing.JTextField();
        lvs_b1 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        Rlvs_a1 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        Rlvs_b1 = new javax.swing.JTextField();
        Rlvs_b2 = new javax.swing.JTextField();
        Rlvs_a2 = new javax.swing.JTextField();
        kpts_A1 = new javax.swing.JTextField();
        kpts_N1 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        kpts_A = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        kpts_N = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();

        setTitle("Jkpoints");
        setResizable(false);
        jTabbedPane1.setFont(fuente);
        jTabbedPane1.setName("path");
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/kpts.gif")));
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel3.setBounds(10, 180, 680, 253);
        jDesktopPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel41.setFont(fuente);
        jLabel41.setText("a =");
        jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel41.setBounds(10, 10, 30, 18);
        jDesktopPane5.add(jLabel41, javax.swing.JLayeredPane.DEFAULT_LAYER);

        a_par.setFont(fuente);
        a_par.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        a_par.setText("4.00");
        a_par.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        a_par.setBounds(42, 10, 50, 18);
        jDesktopPane5.add(a_par, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel62.setFont(fuente);
        jLabel62.setText("lattice parameter");
        jLabel62.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel62.setBounds(94, 10, 116, 18);
        jDesktopPane5.add(jLabel62, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jRadioButtonCubic.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupLVS.add(jRadioButtonCubic);
        jRadioButtonCubic.setFont(fuente);
        jRadioButtonCubic.setSelected(true);
        jRadioButtonCubic.setText("cubic unit cell");
        jRadioButtonCubic.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jRadioButtonCubic.setBorderPainted(true);
        jRadioButtonCubic.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButtonCubic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonCubicMouseClicked(evt);
            }
        });

        jRadioButtonCubic.setBounds(10, 40, 200, 18);
        jDesktopPane5.add(jRadioButtonCubic, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jRadioButtonFCC.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupLVS.add(jRadioButtonFCC);
        jRadioButtonFCC.setFont(fuente);
        jRadioButtonFCC.setText("fcc unit cell");
        jRadioButtonFCC.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jRadioButtonFCC.setBorderPainted(true);
        jRadioButtonFCC.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButtonFCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonFCCMouseClicked(evt);
            }
        });

        jRadioButtonFCC.setBounds(10, 60, 200, 18);
        jDesktopPane5.add(jRadioButtonFCC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jRadioButtonBCC.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupLVS.add(jRadioButtonBCC);
        jRadioButtonBCC.setFont(fuente);
        jRadioButtonBCC.setText("bcc unit cell");
        jRadioButtonBCC.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jRadioButtonBCC.setBorderPainted(true);
        jRadioButtonBCC.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButtonBCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonBCCMouseClicked(evt);
            }
        });

        jRadioButtonBCC.setBounds(10, 80, 200, 18);
        jDesktopPane5.add(jRadioButtonBCC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jRadioButtonDIA.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupLVS.add(jRadioButtonDIA);
        jRadioButtonDIA.setFont(fuente);
        jRadioButtonDIA.setText("Zinc blenda unit cell");
        jRadioButtonDIA.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jRadioButtonDIA.setBorderPainted(true);
        jRadioButtonDIA.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButtonDIA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jRadioButtonDIAMousePressed(evt);
            }
        });

        jRadioButtonDIA.setBounds(10, 100, 200, 18);
        jDesktopPane5.add(jRadioButtonDIA, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton39.setFont(fuente);
        jButton39.setText("...");
        jButton39.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton39MousePressed(evt);
            }
        });

        jButton39.setBounds(10, 130, 18, 18);
        jDesktopPane5.add(jButton39, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel69.setFont(fuente);
        jLabel69.setText("load lattice vector from file");
        jLabel69.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel69.setBounds(30, 130, 180, 18);
        jDesktopPane5.add(jLabel69, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane5.setBounds(10, 10, 220, 160);
        jDesktopPane2.add(jDesktopPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Checkbox.select")));
        jLabel63.setFont(fuente);
        jLabel63.setText("lattice vector ");
        jLabel63.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel63.setBounds(10, 10, 200, 18);
        jDesktopPane8.add(jLabel63, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTableLVS.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jTableLVS.setFont(fuente);
        jTableLVS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {new Double(99.0), new Double(0.0), new Double(0.0)},
                {new Double(0.0), new Double(99.0), new Double(0.0)},
                {new Double(0.0), new Double(0.0), new Double(99.0)}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableLVS.setBounds(10, 30, 200, 48);
        jDesktopPane8.add(jTableLVS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTableRLVS.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jTableRLVS.setFont(fuente);
        jTableRLVS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {new Double(99.0), new Double(0.0), new Double(0.0)},
                {new Double(0.0), new Double(99.0), new Double(0.0)},
                {new Double(0.0), new Double(0.0), new Double(99.0)}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableRLVS.setBounds(10, 100, 200, 48);
        jDesktopPane8.add(jTableRLVS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel72.setFont(fuente);
        jLabel72.setText("reciprocal lattice vector ");
        jLabel72.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel72.setBounds(10, 80, 150, 18);
        jDesktopPane8.add(jLabel72, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton40.setFont(fuente);
        jButton40.setText("load");
        jButton40.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton40MousePressed(evt);
            }
        });

        jButton40.setBounds(162, 80, 48, 18);
        jDesktopPane8.add(jButton40, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane8.setBounds(240, 10, 220, 160);
        jDesktopPane2.add(jDesktopPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel42.setFont(fuente);
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText(" up ");
        jLabel42.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel42.setBounds(150, 50, 60, 18);
        jDesktopPane7.add(jLabel42, javax.swing.JLayeredPane.DEFAULT_LAYER);

        kptsX.setFont(fuente);
        kptsX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        kptsX.setText("2");
        kptsX.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        kptsX.setBounds(10, 70, 60, 18);
        jDesktopPane7.add(kptsX, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel43.setFont(fuente);
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("ur");
        jLabel43.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel43.setBounds(80, 50, 60, 18);
        jDesktopPane7.add(jLabel43, javax.swing.JLayeredPane.DEFAULT_LAYER);

        kptsY.setFont(fuente);
        kptsY.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        kptsY.setText("2");
        kptsY.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        kptsY.setBounds(80, 70, 60, 18);
        jDesktopPane7.add(kptsY, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel44.setFont(fuente);
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText(" us ");
        jLabel44.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel44.setBounds(10, 50, 60, 18);
        jDesktopPane7.add(jLabel44, javax.swing.JLayeredPane.DEFAULT_LAYER);

        kptsZ.setFont(fuente);
        kptsZ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        kptsZ.setText("2");
        kptsZ.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        kptsZ.setBounds(150, 70, 60, 18);
        jDesktopPane7.add(kptsZ, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel64.setFont(fuente);
        jLabel64.setText("lattice vector Monkhorst-Pack");
        jLabel64.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel64.setBounds(10, 10, 200, 18);
        jDesktopPane7.add(jLabel64, javax.swing.JLayeredPane.DEFAULT_LAYER);

        sim.setBackground(new java.awt.Color(255, 255, 255));
        sim.setFont(fuente);
        sim.setText(" K = -K  ( use this simetry)");
        sim.setToolTipText("");
        sim.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        sim.setBorderPainted(true);
        sim.setContentAreaFilled(false);
        sim.setFocusable(false);
        sim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sim.setMargin(new java.awt.Insets(0, 0, 0, 0));
        sim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simMouseClicked(evt);
            }
        });

        sim.setBounds(10, 100, 200, 18);
        jDesktopPane7.add(sim, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton29.setFont(fuente);
        jButton29.setText("get File");
        jButton29.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton29MousePressed(evt);
            }
        });

        jButton29.setBounds(10, 130, 200, 18);
        jDesktopPane7.add(jButton29, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane7.setBounds(470, 10, 220, 160);
        jDesktopPane2.add(jDesktopPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("kpoints Monkhorst-Path", jDesktopPane2);

        jTextAreaInput.setColumns(20);
        jTextAreaInput.setFont(fuenteMono);
        jTextAreaInput.setRows(5);
        jTextAreaInput.setText("Format:\n\nx1 y1 z1     (1 position)\nn1              (number of points 1-2 ) \nx2 z2 y2     (2 posicion)\nn2              (number of point 2-3 )  \nx3 y3 z3     (3 position)\n\nload some example.\n");
        jTextAreaInput.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jScrollPane1.setViewportView(jTextAreaInput);

        jScrollPane1.setBounds(10, 10, 430, 350);
        jDesktopPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel61.setFont(fuente);
        jLabel61.setText("points ");
        jLabel61.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel61.setBounds(202, 42, 108, 18);
        jDesktopPane6.add(jLabel61, javax.swing.JLayeredPane.DEFAULT_LAYER);

        kpts_N3.setFont(fuente);
        kpts_N3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        kpts_N3.setText("30");
        kpts_N3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        kpts_N3.setBounds(134, 42, 66, 18);
        jDesktopPane6.add(kpts_N3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton36.setFont(fuente);
        jButton36.setText("Auto adjust with");
        jButton36.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton36MousePressed(evt);
            }
        });

        jButton36.setBounds(2, 42, 130, 18);
        jDesktopPane6.add(jButton36, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton35.setFont(fuente);
        jButton35.setText("plot");
        jButton35.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton35MousePressed(evt);
            }
        });

        jButton35.setBounds(244, 22, 66, 18);
        jDesktopPane6.add(jButton35, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton32.setFont(fuente);
        jButton32.setText("special points ( format )");
        jButton32.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton32MousePressed(evt);
            }
        });

        jButton32.setBounds(2, 22, 240, 18);
        jDesktopPane6.add(jButton32, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel70.setFont(fuente);
        jLabel70.setText("Interpolates :");
        jLabel70.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel70.setBounds(2, 2, 308, 18);
        jDesktopPane6.add(jLabel70, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane6.setBounds(70, 370, 312, 62);
        jDesktopPane1.add(jDesktopPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane2.setFont(fuente);
        jDesktopPane3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane3.setFont(fuente);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/recorrido2d.gif")));
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel1.setBounds(2, 22, 230, 106);
        jDesktopPane3.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel65.setFont(fuente);
        jLabel65.setText("case 2 dim.  ( lattice vectors)");
        jLabel65.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel65.setBounds(2, 130, 230, 18);
        jDesktopPane3.add(jLabel65, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel39.setFont(fuente);
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("vector a =");
        jLabel39.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel39.setBounds(2, 150, 106, 18);
        jDesktopPane3.add(jLabel39, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lvs_a1.setFont(fuente);
        lvs_a1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_a1.setText("6.2254");
        lvs_a1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        lvs_a1.setBounds(110, 150, 60, 18);
        jDesktopPane3.add(lvs_a1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel45.setFont(fuente);
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("vector b =");
        jLabel45.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel45.setBounds(2, 170, 106, 18);
        jDesktopPane3.add(jLabel45, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lvs_b2.setFont(fuente);
        lvs_b2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_b2.setText("12.445");
        lvs_b2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        lvs_b2.setBounds(172, 170, 60, 18);
        jDesktopPane3.add(lvs_b2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton28.setFont(fuente);
        jButton28.setText("LOAD EXAMPLE");
        jButton28.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton28MousePressed(evt);
            }
        });

        jButton28.setBounds(2, 290, 230, 18);
        jDesktopPane3.add(jButton28, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel68.setFont(fuente);
        jLabel68.setText("EXAMPLE ( 2D )");
        jLabel68.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel68.setBounds(2, 2, 230, 18);
        jDesktopPane3.add(jLabel68, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lvs_a2.setFont(fuente);
        lvs_a2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_a2.setText("0.0");
        lvs_a2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        lvs_a2.setBounds(172, 150, 60, 18);
        jDesktopPane3.add(lvs_a2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lvs_b1.setFont(fuente);
        lvs_b1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lvs_b1.setText("0.0");
        lvs_b1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        lvs_b1.setBounds(110, 170, 60, 18);
        jDesktopPane3.add(lvs_b1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel66.setFont(fuente);
        jLabel66.setText("reciprocal lattice vectors");
        jLabel66.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel66.setBounds(2, 190, 230, 18);
        jDesktopPane3.add(jLabel66, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel40.setFont(fuente);
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("  a =");
        jLabel40.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel40.setBounds(2, 210, 78, 18);
        jDesktopPane3.add(jLabel40, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Rlvs_a1.setFont(fuente);
        Rlvs_a1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Rlvs_a1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Rlvs_a1.setEnabled(false);
        Rlvs_a1.setBounds(82, 210, 74, 18);
        jDesktopPane3.add(Rlvs_a1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel47.setFont(fuente);
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText(" b =");
        jLabel47.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel47.setBounds(2, 230, 78, 18);
        jDesktopPane3.add(jLabel47, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Rlvs_b1.setFont(fuente);
        Rlvs_b1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Rlvs_b1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Rlvs_b1.setEnabled(false);
        Rlvs_b1.setBounds(82, 230, 74, 18);
        jDesktopPane3.add(Rlvs_b1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Rlvs_b2.setFont(fuente);
        Rlvs_b2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Rlvs_b2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Rlvs_b2.setEnabled(false);
        Rlvs_b2.setBounds(158, 230, 74, 18);
        jDesktopPane3.add(Rlvs_b2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Rlvs_a2.setFont(fuente);
        Rlvs_a2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Rlvs_a2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Rlvs_a2.setEnabled(false);
        Rlvs_a2.setBounds(158, 210, 74, 18);
        jDesktopPane3.add(Rlvs_a2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        kpts_A1.setFont(fuente);
        kpts_A1.setText("4.42");
        kpts_A1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        kpts_A1.setBounds(172, 250, 60, 18);
        jDesktopPane3.add(kpts_A1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        kpts_N1.setFont(fuente);
        kpts_N1.setText("32");
        kpts_N1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        kpts_N1.setBounds(172, 270, 60, 18);
        jDesktopPane3.add(kpts_N1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel74.setFont(fuente);
        jLabel74.setText("number of points");
        jLabel74.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel74.setBounds(2, 270, 168, 18);
        jDesktopPane3.add(jLabel74, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel67.setFont(fuente);
        jLabel67.setText("lattice parameter");
        jLabel67.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel67.setBounds(2, 250, 168, 18);
        jDesktopPane3.add(jLabel67, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane2.addTab("example 2D", jDesktopPane3);

        jDesktopPane4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.setFont(fuente);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/fcc.jpg")));
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel2.setBounds(2, 22, 230, 206);
        jDesktopPane4.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel46.setFont(fuente);
        jLabel46.setText("a =");
        jLabel46.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel46.setBounds(2, 230, 40, 18);
        jDesktopPane4.add(jLabel46, javax.swing.JLayeredPane.DEFAULT_LAYER);

        kpts_A.setFont(fuente);
        kpts_A.setText("4.42");
        kpts_A.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        kpts_A.setBounds(44, 230, 50, 18);
        jDesktopPane4.add(kpts_A, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel59.setFont(fuente);
        jLabel59.setText("lattice parameter");
        jLabel59.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel59.setBounds(96, 230, 136, 18);
        jDesktopPane4.add(jLabel59, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel71.setFont(fuente);
        jLabel71.setText("number of points");
        jLabel71.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel71.setBounds(96, 250, 136, 18);
        jDesktopPane4.add(jLabel71, javax.swing.JLayeredPane.DEFAULT_LAYER);

        kpts_N.setFont(fuente);
        kpts_N.setText("255");
        kpts_N.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        kpts_N.setBounds(44, 250, 50, 18);
        jDesktopPane4.add(kpts_N, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton31.setFont(fuente);
        jButton31.setText("LOAD EXAMPLE ( fcc )");
        jButton31.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton31MousePressed(evt);
            }
        });

        jButton31.setBounds(2, 290, 230, 18);
        jDesktopPane4.add(jButton31, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel77.setFont(fuente);
        jLabel77.setText("EXAMPLE ( fcc )");
        jLabel77.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel77.setBounds(2, 2, 230, 18);
        jDesktopPane4.add(jLabel77, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel48.setFont(fuente);
        jLabel48.setText("n =");
        jLabel48.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel48.setBounds(2, 250, 40, 18);
        jDesktopPane4.add(jLabel48, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel73.setFont(fuente);
        jLabel73.setText(" path G , X , W , L , G, K");
        jLabel73.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel73.setBounds(2, 270, 230, 18);
        jDesktopPane4.add(jLabel73, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane2.addTab("example 3D", jDesktopPane4);

        jTabbedPane2.setBounds(450, 20, 240, 334);
        jDesktopPane1.add(jTabbedPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("path", jDesktopPane1);

        jTabbedPane1.getAccessibleContext().setAccessibleName("path");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void simMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simMouseClicked
        
    }//GEN-LAST:event_simMouseClicked
    
    private void jRadioButtonDIAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonDIAMousePressed
        loadValues();
    }//GEN-LAST:event_jRadioButtonDIAMousePressed
    
    private void jRadioButtonBCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonBCCMouseClicked
        loadValues();
    }//GEN-LAST:event_jRadioButtonBCCMouseClicked
    
    private void jRadioButtonFCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonFCCMouseClicked
        loadValues();
    }//GEN-LAST:event_jRadioButtonFCCMouseClicked
    
    private void jRadioButtonCubicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonCubicMouseClicked
        loadValues();
    }//GEN-LAST:event_jRadioButtonCubicMouseClicked
    
    private void jButton40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MousePressed
        for(int i=1;i<4;i++){
            lvs_1[i]=Double.valueOf(jTableLVS.getModel().getValueAt(i-1,0).toString()).doubleValue();
            lvs_2[i]=Double.valueOf(jTableLVS.getModel().getValueAt(i-1,1).toString()).doubleValue();
            lvs_3[i]=Double.valueOf(jTableLVS.getModel().getValueAt(i-1,2).toString()).doubleValue();
        }
        
        loadRTabla();
    }//GEN-LAST:event_jButton40MousePressed
    
    private void jButton39MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton39MousePressed
        java.io.File lvsfile =  new dialogo.chooser().fileChoose("Open file *.xyz","open","") ;
        String str="";
        if(lvsfile!=null){
            try{
                java.io.BufferedReader inlvs = new java.io.BufferedReader(new java.io.FileReader(lvsfile));
                for(int i=1;i<4;i++){
                    str=inlvs.readLine()+"\n";
                    lvs_1[i] = cadena.readColDouble(1,str);
                    lvs_2[i] = cadena.readColDouble(2,str);
                    lvs_3[i] = cadena.readColDouble(3,str);
                }
                inlvs.close();
                loadTabla(lvs_1,lvs_2,lvs_3);
            }catch (java.io.IOException oe) {System.out.println("error read lvs");}
        }
    }//GEN-LAST:event_jButton39MousePressed
    
    private void jButton36MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MousePressed
        jTextAreaInput.setText(kpts.generarPuntos(cadena.readColInt(1,kpts_N3.getText()),jTextAreaInput.getText()));
    }//GEN-LAST:event_jButton36MousePressed
    
    private void jButton35MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MousePressed
        kpts.entrada=jTextAreaInput.getText();
        kpts.hacerRecorrido();
        new editor.editor("temp.dat").LoadString(kpts.salida);
    }//GEN-LAST:event_jButton35MousePressed
    
    private void jButton32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MousePressed
        kpts.entrada=jTextAreaInput.getText();
        kpts.hacerRecorrido();
        new editor.editor("temp.kpts").LoadString(kpts.formatoKPTS());
    }//GEN-LAST:event_jButton32MousePressed
    
    private void jButton31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton31MousePressed
        double a=cadena.readColDouble(1,kpts_A.getText());
        String aux="";
        aux="0 0 0 \n"; //G
        aux+="1 \n";
        aux+=(2*Math.PI/a)+" 0 0 \n"; //X
        aux+="1 \n";
        aux+=(2*Math.PI/a)+" "+(Math.PI/a)+"0 0 \n"; //W
        aux+="1 \n";
        aux+=(Math.PI/a)+" "+(Math.PI/a)+" "+(Math.PI/a)+" \n";  //L
        aux+="1 \n";
        aux+="0 0 0 \n"; //G
        aux+="1 \n";
        aux+=(3*Math.PI/a/2)+" "+(3*Math.PI/a/2)+" 0 \n";  //K
        aux=kpts.generarPuntos(cadena.readColInt(1,kpts_N.getText()),aux);
        jTextAreaInput.setText(aux);
        
    }//GEN-LAST:event_jButton31MousePressed
    
    
    private void jButton29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MousePressed
        int i,j,k,n;
        String salida="";
        q[1]=cadena.readColInt(1,kptsX.getText());
        q[2]=cadena.readColInt(1,kptsY.getText());
        q[3]=cadena.readColInt(1,kptsZ.getText());
        for(i=1;i<4;i++)
            q[i]=(q[i]==0)?1:q[i];
        for(i=1;i<4;i++){
            Rlvs_1[i]=Double.valueOf(jTableRLVS.getModel().getValueAt(i-1,0).toString()).doubleValue();
            Rlvs_2[i]=Double.valueOf(jTableRLVS.getModel().getValueAt(i-1,1).toString()).doubleValue();
            Rlvs_3[i]=Double.valueOf(jTableRLVS.getModel().getValueAt(i-1,2).toString()).doubleValue();
        }
        double p=1.0/q[1]/q[2]/q[3];
        salida=q[1]*q[2]*q[3]+"\n";
        for(i=1;i<=q[1];i++){
            u[1]=(2*i-q[1]-1)*1.0/(2*q[1]);
            for(j=1;j<=q[2];j++){
                u[2]=(2*j-q[2]-1)*1.0/(2*q[2]);
                for(k=1;k<=q[3];k++){
                    u[3]=(2*k-q[3]-1)*1.0/(2*q[3]);
                    for(n=1;n<4;n++)
                        K[n]=u[1]*Rlvs_1[n]*1.0+ u[2]*Rlvs_2[n]*1.0+u[3]*Rlvs_3[n]*1.0;
                    //     System.out.println(K[1]+" "+K[2]+" "+K[3]);
                    salida+=cadena.formatFortran(2, 14, 8, K[1]) + "    " + cadena.formatFortran(2, 14, 8, K[2]) + "    " + cadena.formatFortran(2, 14, 8, K[3]) + " " + cadena.formatFortran(2, 14, 8, p)+"\n";
                }
            }
        }
        if(sim.isSelected()){
            //quitar los puntos -K +K
            String aux="",ret="";
            double x=0,y=0,z=0,x1=0,y1=0,z1=0,min_distancia=0,auxj=0;
            for(int a=2;a<=cadena.nLine(salida);a++){
                x=cadena.readColDouble(1,cadena.readLine(a,salida));
                y=cadena.readColDouble(2,cadena.readLine(a,salida));
                z=cadena.readColDouble(3,cadena.readLine(a,salida));
                for(int g=a+1;g<=cadena.nLine(salida);g++){
                    x1=cadena.readColDouble(1,cadena.readLine(g,salida));
                    y1=cadena.readColDouble(2,cadena.readLine(g,salida));
                    z1=cadena.readColDouble(3,cadena.readLine(g,salida));
                    auxj=Math.pow(Math.pow(-x-x1,2)+Math.pow(-y-y1,2)+Math.pow(-z-z1,2),0.5);
                    if(g==a+1)min_distancia=auxj;
                    else min_distancia=(auxj<min_distancia)?auxj:min_distancia;
                }
                if(Math.abs(min_distancia)<0.0001) aux+=cadena.formatFortran(2, 14, 8, x) + "    " + cadena.formatFortran(2, 14, 8, y) + "    " + cadena.formatFortran(2, 14, 8, z)+"\n";
            }
            int nk=cadena.nLine(aux);
            ret=nk+" \n";
            for(int g=1; g<=nk;g++)
                ret+=cadena.getLine(g,aux)+"  "+ cadena.formatFortran(2, 14, 8, 1.0/nk)+"\n";
            new editor.editor("temp.kpts").LoadString(ret);
        }else  new editor.editor("temp.kpts").LoadString(salida);
    }//GEN-LAST:event_jButton29MousePressed
    
    private void jButton28MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton28MousePressed
        lvs_1[1]=cadena.readColDouble(1,lvs_a1.getText());
        lvs_1[2]=cadena.readColDouble(1,lvs_a2.getText());
        lvs_2[1]=cadena.readColDouble(1,lvs_b1.getText());
        lvs_2[2]=cadena.readColDouble(1,lvs_b2.getText());
        
        Rlvs_1[1]=2*Math.PI*lvs_2[2]/(lvs_1[1]*lvs_2[2]-lvs_1[2]*lvs_2[1]);
        Rlvs_1[2]=-2*Math.PI*lvs_2[1]/(lvs_1[1]*lvs_2[2]-lvs_1[2]*lvs_2[1]);
        Rlvs_2[1]=-2*Math.PI*lvs_1[2]/(lvs_1[1]*lvs_2[2]-lvs_1[2]*lvs_2[1]);
        Rlvs_2[2]=2*Math.PI*lvs_1[1]/(lvs_1[1]*lvs_2[2]-lvs_1[2]*lvs_2[1]);
        
        Rlvs_a1.setText(cadena.formatFortran(0, 10, 5,Rlvs_1[1]));
        Rlvs_a2.setText(cadena.formatFortran(0, 10, 5,Rlvs_1[2]));
        Rlvs_b1.setText(cadena.formatFortran(0, 10, 5,Rlvs_2[1]));
        Rlvs_b2.setText(cadena.formatFortran(0, 10, 5,Rlvs_2[2]));
        
        String aux="0 0 0"+"\n";
        aux+="2\n";
        aux+=Rlvs_1[1]/2+" "+ Rlvs_1[2]/2+" 0.0"+"\n";
        aux+="2\n";
        aux+=(Rlvs_1[1]/2+Rlvs_2[1]/2)+" "+(Rlvs_1[2]/2+Rlvs_2[2]/2)+" 0.0"+"\n";
        aux+="2\n";
        aux+="0.0 0.0 0.0"+"\n";
        aux+="2\n";
        aux+=Rlvs_2[1]/2+" "+ Rlvs_2[2]/2+" 0.0"+"\n";
        jTextAreaInput.setText(aux);
        jTextAreaInput.setText(kpts.generarPuntos(cadena.readColInt(1,kpts_N1.getText()),jTextAreaInput.getText()));
    }//GEN-LAST:event_jButton28MousePressed
    
    
    
    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JTextField Rlvs_a1;
    private javax.swing.JTextField Rlvs_a2;
    private javax.swing.JTextField Rlvs_b1;
    private javax.swing.JTextField Rlvs_b2;
    private javax.swing.JTextField a_par;
    private javax.swing.ButtonGroup buttonGroupLVS;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JDesktopPane jDesktopPane5;
    private javax.swing.JDesktopPane jDesktopPane6;
    private javax.swing.JDesktopPane jDesktopPane7;
    private javax.swing.JDesktopPane jDesktopPane8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JRadioButton jRadioButtonBCC;
    private javax.swing.JRadioButton jRadioButtonCubic;
    private javax.swing.JRadioButton jRadioButtonDIA;
    private javax.swing.JRadioButton jRadioButtonFCC;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableLVS;
    private javax.swing.JTable jTableRLVS;
    private javax.swing.JTextArea jTextAreaInput;
    private javax.swing.JTextField kptsX;
    private javax.swing.JTextField kptsY;
    private javax.swing.JTextField kptsZ;
    private javax.swing.JTextField kpts_A;
    private javax.swing.JTextField kpts_A1;
    private javax.swing.JTextField kpts_N;
    private javax.swing.JTextField kpts_N1;
    private javax.swing.JTextField kpts_N3;
    private javax.swing.JTextField lvs_a1;
    private javax.swing.JTextField lvs_a2;
    private javax.swing.JTextField lvs_b1;
    private javax.swing.JTextField lvs_b2;
    private javax.swing.JCheckBox sim;
    // Fin de declaración de variables//GEN-END:variables
    
    void loadTabla(double [] a1, double [] a2,double [] a3 ){
        for(int i=1;i<4;i++){
            jTableLVS.getModel().setValueAt(a1[i],i-1,0);
            jTableLVS.getModel().setValueAt(a2[i],i-1,1);
            jTableLVS.getModel().setValueAt(a3[i],i-1,2);
        }
        loadRTabla();
    }
    
    void loadRTabla(){
        makeRLVS();
        for(int i=1;i<4;i++){
            jTableRLVS.getModel().setValueAt(Rlvs_1[i],i-1,0);
            jTableRLVS.getModel().setValueAt(Rlvs_2[i],i-1,1);
            jTableRLVS.getModel().setValueAt(Rlvs_3[i],i-1,2);
        }
    }
    
    void loadValues(){
        double a=cadena.readColDouble(1,a_par.getText());
        
        lvs_1[1]=99 ;  lvs_2[1]=0 ;  lvs_3[1]=0;
        lvs_1[2]=0 ;  lvs_2[2]=99 ;  lvs_3[2]=0;
        lvs_1[3]=0 ;  lvs_2[3]=0 ;  lvs_3[3]=99;
        
        
        if(jRadioButtonFCC.isSelected()){
            lvs_1[1]=a/2 ;  lvs_2[1]=a/2 ;  lvs_3[1]=0;
            lvs_1[2]=a/2 ;  lvs_2[2]=0 ;  lvs_3[2]=a/2;
            lvs_1[3]=0 ;  lvs_2[3]=a/2 ;  lvs_3[3]=a/2;
        }
        if(jRadioButtonBCC.isSelected()){
            lvs_1[1]=-a/2 ;  lvs_2[1]=a/2 ;  lvs_3[1]=a/2;
            lvs_1[2]=a/2 ;  lvs_2[2]=-a/2 ;  lvs_3[2]=a/2;
            lvs_1[3]=a/2 ;  lvs_2[3]=a/2 ;  lvs_3[3]=-a/2;
        }
        if(jRadioButtonDIA.isSelected()){
            lvs_1[1]=a/4 ;  lvs_2[1]=a/4 ;  lvs_3[1]=0;
            lvs_1[2]=a/4 ;  lvs_2[2]=0 ;  lvs_3[2]=a/4;
            lvs_1[3]=0 ;  lvs_2[3]=a/4 ;  lvs_3[3]=a/4;
        }
        if(jRadioButtonCubic.isSelected()){
            lvs_1[1]=a ;  lvs_2[1]=0 ;  lvs_3[1]=0;
            lvs_1[2]=0 ;  lvs_2[2]=a ;  lvs_3[2]=0;
            lvs_1[3]=0 ;  lvs_2[3]=0 ;  lvs_3[3]=a;
        }
        loadTabla(lvs_1,lvs_2,lvs_3);
    }
    void makeRLVS(){
        Rlvs_1=Pcte(2*Math.PI/producto(lvs_1,PVectorial(lvs_2,lvs_3)),PVectorial(lvs_2,lvs_3));
        Rlvs_2=Pcte(2*Math.PI/producto(lvs_2,PVectorial(lvs_3,lvs_1)),PVectorial(lvs_3,lvs_1));
        Rlvs_3=Pcte(2*Math.PI/producto(lvs_3,PVectorial(lvs_1,lvs_2)),PVectorial(lvs_1,lvs_2));
        // print(Rlvs_1,Rlvs_2,Rlvs_3);
    }
    
    
    
    double [] Pcte(double k, double [] a){
        double  [] ret = new double[4];
        ret[1]=k*a[1];
        ret[2]=k*a[2];
        ret[3]=k*a[3];
        return ret;
    }
    double [] PVectorial(double [] a, double [] b){
         /*
          *     | i  j  k |   |ay az|   |ax az|   |ax ay|
          *ret =| ax ay az|=i*|by bz|-j*|bx bz|+k*|bx by|
          *     | bx by bz|
          */
        double  [] ret = new double[4];
        ret[1]= det(a[2],a[3],b[2],b[3]);
        ret[2]=-det(a[1],a[3],b[1],b[3]);
        ret[3]= det(a[1],a[2],b[1],b[2]);
        return ret;
    }
    
    double producto(double [] a, double [] b){
        return a[1]*b[1]+a[2]*b[2]+a[3]*b[3];
    }
    
    double det(double ax,double ay,double bx, double by){
       /*      | ax ay |
        * ret= | bx by | = ax*by - bx*ay
        */
        return ax*by-bx*ay;
    }
    
    void print(double [] a,double [] b,double [] c){
        System.out.println(a[1]+" "+a[2]+" "+a[3]);
        System.out.println(b[1]+" "+b[2]+" "+b[3]);
        System.out.println(c[1]+" "+c[2]+" "+c[3]);
    }
    
}
