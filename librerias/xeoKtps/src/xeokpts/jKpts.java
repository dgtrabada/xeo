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
    //2D CASE
    double a1x,a1y,a2x,a2y,b1x,b1y,b2x,b2y;
    double x1,x2,y1,y2,Xx,Xy,Kx,Ky,Lx,Ly; //2d x1=Rlvs_1[1]
    
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
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        jDesktopPane9 = new javax.swing.JDesktopPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        JTa1x = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        JTa2y = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jTa1y = new javax.swing.JTextField();
        JTa2x = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        JTb1x = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        JTb2x = new javax.swing.JTextField();
        JTb2y = new javax.swing.JTextField();
        JTb1y = new javax.swing.JTextField();
        kpts_N2 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jButton33 = new javax.swing.JButton();
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
        jTabbedPane1.setName("path"); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/kpts.gif"))); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane2.add(jLabel3);
        jLabel3.setBounds(10, 180, 680, 253);

        jDesktopPane5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel41.setFont(fuente);
        jLabel41.setText("a =");
        jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(jLabel41);
        jLabel41.setBounds(10, 10, 30, 18);

        a_par.setFont(fuente);
        a_par.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        a_par.setText("4.00");
        a_par.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(a_par);
        a_par.setBounds(42, 10, 50, 18);

        jLabel62.setFont(fuente);
        jLabel62.setText("lattice parameter");
        jLabel62.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(jLabel62);
        jLabel62.setBounds(94, 10, 116, 18);

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
        jDesktopPane5.add(jRadioButtonCubic);
        jRadioButtonCubic.setBounds(10, 40, 200, 18);

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
        jDesktopPane5.add(jRadioButtonFCC);
        jRadioButtonFCC.setBounds(10, 60, 200, 18);

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
        jDesktopPane5.add(jRadioButtonBCC);
        jRadioButtonBCC.setBounds(10, 80, 200, 18);

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
        jDesktopPane5.add(jRadioButtonDIA);
        jRadioButtonDIA.setBounds(10, 100, 200, 18);

        jButton39.setFont(fuente);
        jButton39.setText("...");
        jButton39.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton39MousePressed(evt);
            }
        });
        jDesktopPane5.add(jButton39);
        jButton39.setBounds(10, 130, 18, 18);

        jLabel69.setFont(fuente);
        jLabel69.setText("load lattice vector from file");
        jLabel69.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(jLabel69);
        jLabel69.setBounds(30, 130, 180, 18);

        jDesktopPane2.add(jDesktopPane5);
        jDesktopPane5.setBounds(10, 10, 220, 160);

        jDesktopPane8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Checkbox.select")));

        jLabel63.setFont(fuente);
        jLabel63.setText("lattice vector ");
        jLabel63.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane8.add(jLabel63);
        jLabel63.setBounds(10, 10, 200, 18);

        jTableLVS.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jTableLVS.setFont(fuente);
        jTableLVS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Double(99.0),  new Double(0.0),  new Double(0.0)},
                { new Double(0.0),  new Double(99.0),  new Double(0.0)},
                { new Double(0.0),  new Double(0.0),  new Double(99.0)}
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
        jDesktopPane8.add(jTableLVS);
        jTableLVS.setBounds(10, 30, 200, 48);

        jTableRLVS.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jTableRLVS.setFont(fuente);
        jTableRLVS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Double(99.0),  new Double(0.0),  new Double(0.0)},
                { new Double(0.0),  new Double(99.0),  new Double(0.0)},
                { new Double(0.0),  new Double(0.0),  new Double(99.0)}
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
        jDesktopPane8.add(jTableRLVS);
        jTableRLVS.setBounds(10, 100, 200, 48);

        jLabel72.setFont(fuente);
        jLabel72.setText("reciprocal lattice vector ");
        jLabel72.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane8.add(jLabel72);
        jLabel72.setBounds(10, 80, 150, 18);

        jButton40.setFont(fuente);
        jButton40.setText("load");
        jButton40.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton40MousePressed(evt);
            }
        });
        jDesktopPane8.add(jButton40);
        jButton40.setBounds(162, 80, 48, 18);

        jDesktopPane2.add(jDesktopPane8);
        jDesktopPane8.setBounds(240, 10, 220, 160);

        jDesktopPane7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel42.setFont(fuente);
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText(" up ");
        jLabel42.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane7.add(jLabel42);
        jLabel42.setBounds(150, 50, 60, 18);

        kptsX.setFont(fuente);
        kptsX.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        kptsX.setText("2");
        kptsX.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane7.add(kptsX);
        kptsX.setBounds(10, 70, 60, 18);

        jLabel43.setFont(fuente);
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("ur");
        jLabel43.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane7.add(jLabel43);
        jLabel43.setBounds(80, 50, 60, 18);

        kptsY.setFont(fuente);
        kptsY.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        kptsY.setText("2");
        kptsY.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane7.add(kptsY);
        kptsY.setBounds(80, 70, 60, 18);

        jLabel44.setFont(fuente);
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText(" us ");
        jLabel44.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane7.add(jLabel44);
        jLabel44.setBounds(10, 50, 60, 18);

        kptsZ.setFont(fuente);
        kptsZ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        kptsZ.setText("2");
        kptsZ.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane7.add(kptsZ);
        kptsZ.setBounds(150, 70, 60, 18);

        jLabel64.setFont(fuente);
        jLabel64.setText("lattice vector Monkhorst-Pack");
        jLabel64.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane7.add(jLabel64);
        jLabel64.setBounds(10, 10, 200, 18);

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
        jDesktopPane7.add(sim);
        sim.setBounds(10, 100, 200, 18);

        jButton29.setFont(fuente);
        jButton29.setText("get File");
        jButton29.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton29MousePressed(evt);
            }
        });
        jDesktopPane7.add(jButton29);
        jButton29.setBounds(10, 130, 200, 18);

        jDesktopPane2.add(jDesktopPane7);
        jDesktopPane7.setBounds(470, 10, 220, 160);

        jTabbedPane1.addTab("kpoints Monkhorst-Path", jDesktopPane2);

        jTextAreaInput.setColumns(20);
        jTextAreaInput.setFont(fuenteMono);
        jTextAreaInput.setRows(5);
        jTextAreaInput.setText("Format:\n\nx1 y1 z1     (1 position)\nn1              (number of points 1-2 ) \nx2 z2 y2     (2 posicion)\nn2              (number of point 2-3 )  \nx3 y3 z3     (3 position)\n\nload some example.\n");
        jTextAreaInput.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jScrollPane1.setViewportView(jTextAreaInput);

        jDesktopPane1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 430, 350);

        jDesktopPane6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel61.setFont(fuente);
        jLabel61.setText("points ");
        jLabel61.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane6.add(jLabel61);
        jLabel61.setBounds(202, 42, 108, 18);

        kpts_N3.setFont(fuente);
        kpts_N3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        kpts_N3.setText("30");
        kpts_N3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane6.add(kpts_N3);
        kpts_N3.setBounds(134, 42, 66, 18);

        jButton36.setFont(fuente);
        jButton36.setText("Auto adjust with");
        jButton36.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton36MousePressed(evt);
            }
        });
        jDesktopPane6.add(jButton36);
        jButton36.setBounds(2, 42, 130, 18);

        jButton35.setFont(fuente);
        jButton35.setText("plot");
        jButton35.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton35MousePressed(evt);
            }
        });
        jDesktopPane6.add(jButton35);
        jButton35.setBounds(244, 22, 66, 18);

        jButton32.setFont(fuente);
        jButton32.setText("special points ( format )");
        jButton32.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton32MousePressed(evt);
            }
        });
        jDesktopPane6.add(jButton32);
        jButton32.setBounds(2, 22, 240, 18);

        jLabel70.setFont(fuente);
        jLabel70.setText("Interpolates :");
        jLabel70.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane6.add(jLabel70);
        jLabel70.setBounds(2, 2, 308, 18);

        jDesktopPane1.add(jDesktopPane6);
        jDesktopPane6.setBounds(70, 370, 312, 62);

        jTabbedPane2.setFont(fuente);

        jDesktopPane9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.setFont(fuente);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/recorrido2d.gif"))); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jLabel4);
        jLabel4.setBounds(2, 22, 230, 106);

        jLabel75.setFont(fuente);
        jLabel75.setText("case 2 dim.  ( lattice vectors)");
        jLabel75.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jLabel75);
        jLabel75.setBounds(2, 130, 230, 18);

        jLabel49.setFont(fuente);
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("vector a1 =");
        jLabel49.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jLabel49);
        jLabel49.setBounds(2, 150, 106, 18);

        JTa1x.setFont(fuente);
        JTa1x.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTa1x.setText("6.7481");
        JTa1x.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        JTa1x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTa1xActionPerformed(evt);
            }
        });
        jDesktopPane9.add(JTa1x);
        JTa1x.setBounds(110, 150, 60, 18);

        jLabel50.setFont(fuente);
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("vector a2 =");
        jLabel50.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jLabel50);
        jLabel50.setBounds(2, 170, 106, 18);

        JTa2y.setFont(fuente);
        JTa2y.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTa2y.setText("11.6879");
        JTa2y.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(JTa2y);
        JTa2y.setBounds(172, 170, 60, 18);

        jButton30.setFont(fuente);
        jButton30.setText("Load G-M-K");
        jButton30.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton30MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton30);
        jButton30.setBounds(0, 270, 230, 18);

        jLabel76.setFont(fuente);
        jLabel76.setText("EXAMPLE ( 2D )");
        jLabel76.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jLabel76);
        jLabel76.setBounds(2, 2, 230, 18);

        jTa1y.setFont(fuente);
        jTa1y.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTa1y.setText("11.6879");
        jTa1y.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jTa1y);
        jTa1y.setBounds(172, 150, 60, 18);

        JTa2x.setFont(fuente);
        JTa2x.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTa2x.setText("-6.7481");
        JTa2x.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(JTa2x);
        JTa2x.setBounds(110, 170, 60, 18);

        jLabel78.setFont(fuente);
        jLabel78.setText("reciprocal lattice vectors");
        jLabel78.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jLabel78);
        jLabel78.setBounds(2, 190, 230, 18);

        jLabel51.setFont(fuente);
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("  b1 =");
        jLabel51.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jLabel51);
        jLabel51.setBounds(2, 210, 78, 18);

        JTb1x.setFont(fuente);
        JTb1x.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTb1x.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        JTb1x.setEnabled(false);
        jDesktopPane9.add(JTb1x);
        JTb1x.setBounds(82, 210, 74, 18);

        jLabel52.setFont(fuente);
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText(" b2 =");
        jLabel52.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jLabel52);
        jLabel52.setBounds(2, 230, 78, 18);

        JTb2x.setFont(fuente);
        JTb2x.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTb2x.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        JTb2x.setEnabled(false);
        jDesktopPane9.add(JTb2x);
        JTb2x.setBounds(82, 230, 74, 18);

        JTb2y.setFont(fuente);
        JTb2y.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTb2y.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        JTb2y.setEnabled(false);
        jDesktopPane9.add(JTb2y);
        JTb2y.setBounds(158, 230, 74, 18);

        JTb1y.setFont(fuente);
        JTb1y.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTb1y.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        JTb1y.setEnabled(false);
        jDesktopPane9.add(JTb1y);
        JTb1y.setBounds(158, 210, 74, 18);

        kpts_N2.setFont(fuente);
        kpts_N2.setText("32");
        kpts_N2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(kpts_N2);
        kpts_N2.setBounds(170, 250, 60, 18);

        jLabel79.setFont(fuente);
        jLabel79.setText("number of points");
        jLabel79.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane9.add(jLabel79);
        jLabel79.setBounds(0, 250, 168, 18);

        jButton33.setFont(fuente);
        jButton33.setText("Wigner-Seitz");
        jButton33.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton33MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton33);
        jButton33.setBounds(2, 290, 230, 18);

        jTabbedPane2.addTab("2D", jDesktopPane9);

        jDesktopPane4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.setFont(fuente);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/fcc.jpg"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.add(jLabel2);
        jLabel2.setBounds(2, 22, 230, 206);

        jLabel46.setFont(fuente);
        jLabel46.setText("a =");
        jLabel46.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.add(jLabel46);
        jLabel46.setBounds(2, 230, 40, 18);

        kpts_A.setFont(fuente);
        kpts_A.setText("4.42");
        kpts_A.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.add(kpts_A);
        kpts_A.setBounds(44, 230, 50, 18);

        jLabel59.setFont(fuente);
        jLabel59.setText("lattice parameter");
        jLabel59.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.add(jLabel59);
        jLabel59.setBounds(96, 230, 136, 18);

        jLabel71.setFont(fuente);
        jLabel71.setText("number of points");
        jLabel71.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.add(jLabel71);
        jLabel71.setBounds(96, 250, 136, 18);

        kpts_N.setFont(fuente);
        kpts_N.setText("255");
        kpts_N.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.add(kpts_N);
        kpts_N.setBounds(44, 250, 50, 18);

        jButton31.setFont(fuente);
        jButton31.setText("LOAD EXAMPLE ( fcc )");
        jButton31.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton31MousePressed(evt);
            }
        });
        jDesktopPane4.add(jButton31);
        jButton31.setBounds(2, 290, 230, 18);

        jLabel77.setFont(fuente);
        jLabel77.setText("EXAMPLE ( fcc )");
        jLabel77.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.add(jLabel77);
        jLabel77.setBounds(2, 2, 230, 18);

        jLabel48.setFont(fuente);
        jLabel48.setText("n =");
        jLabel48.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.add(jLabel48);
        jLabel48.setBounds(2, 250, 40, 18);

        jLabel73.setFont(fuente);
        jLabel73.setText(" path G , X , W , L , G, K");
        jLabel73.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane4.add(jLabel73);
        jLabel73.setBounds(2, 270, 230, 18);

        jTabbedPane2.addTab("3D", jDesktopPane4);

        jDesktopPane1.add(jTabbedPane2);
        jTabbedPane2.setBounds(450, 20, 240, 334);
        jTabbedPane2.getAccessibleContext().setAccessibleName("example");

        jTabbedPane1.addTab("path", jDesktopPane1);

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

        jTabbedPane1.getAccessibleContext().setAccessibleName("path");

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

    private void jButton30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MousePressed
        a1x=cadena.readColDouble(1,JTa1x.getText());
        a1y=cadena.readColDouble(1,jTa1y.getText());
        a2y=cadena.readColDouble(1,JTa2y.getText());
        a2x=cadena.readColDouble(1,JTa2x.getText());

        b1x=2*Math.PI*a2y/(a1x*a2y-a1y*a2x);
        b2x=-2*Math.PI*a1y/(a1x*a2y-a1y*a2x);
        b1y=-2*Math.PI*a2x/(a1x*a2y-a1y*a2x);
        b2y=2*Math.PI*a1x/(a1x*a2y-a1y*a2x);

        // bi⋅aj=2πδij
            
        JTb1x.setText(cadena.formatFortran(0, 10, 5,b1x));
        JTb1y.setText(cadena.formatFortran(0, 10, 5,b1y));
        JTb2x.setText(cadena.formatFortran(0, 10, 5,b2x));
        JTb2y.setText(cadena.formatFortran(0, 10, 5,b2y));


        
        x1=b1x;
        y1=b1y;
        x2=b2x;
        y2=b2y;
        Xx=(-(y1+y2)*(Math.pow(x1,2)-y1*y2)+y1*Math.pow(x1+x2,2))/(2*(y1*x2-x1*y2));
        Xy=-x1/y1*Xx+y1/2+Math.pow(x1,2)/2/y1;
        Kx=(-(y1+y2)*(Math.pow(x2,2)-y1*y2)+y2*Math.pow(x1+x2,2))/(2*(-y1*x2+x1*y2));
        Ky=-x2/y2*Kx+y2/2+Math.pow(x2,2)/2/y2;
        Lx=(y2*y2*y1+y1*x2*x2+y1*y1*y2+y2*x1*x1)/2/(x1*y2-x2*y1);
        Ly=-x2/y2*Lx-y2/2-x2*x2/2/y2;
        String aux="0 0 0"+"\n";
        aux+="2\n";
        aux+=b1x/2+" "+ b1y/2+" 0.0"+"\n"; //M
        aux+="2\n";
        aux+=Xx+" "+ Xy+" 0.00"+"\n"; //K
        aux+="2\n";        
        aux+=(Kx+Xx)/2+" "+ (Ky+Xy)/2+" 0.0"+"\n"; //J
        aux+="2\n";
        aux+=Kx+" "+ Ky+" 0.000"+"\n";
        aux+="2\n";
        aux+="0.0 0.0 0.0"+"\n";
        aux+="2\n";
        aux+=b2x/2+" "+ b2y/2+" 0.0"+"\n";
        jTextAreaInput.setText(aux);
        jTextAreaInput.setText(kpts.generarPuntos(cadena.readColInt(1,kpts_N2.getText()),jTextAreaInput.getText()));
    }//GEN-LAST:event_jButton30MousePressed

    private void JTa1xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTa1xActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTa1xActionPerformed

    private void jButton33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton33MousePressed
        new editor.editor("V_red.dat").LoadString(b1x+" "+b1y+"\n"+"0.00000    0.00000"+"\n"+b2x+" "+b2y );
        new editor.editor("Wigner-Seitz.dat").LoadString(
                b1x/2+" "+b1y/2+"\n"+
                Xx+" "+Xy+"\n"+ 
                (Xx+Kx)/2+" "+(Xy+Ky)/2+"\n"+        
                Kx+" "+Ky+"\n"+   
                b2x/2+" "+b2y/2+"\n"+
                -Lx+" "+-Ly+"\n"+
                -b1x/2+" "+-b1y/2+"\n"+
                -Xx+" "+-Xy+"\n"+
                -Kx+" "+-Ky+"\n"+
                Lx+" "+Ly+"\n"+
                b1x/2+" "+b1y/2+"\n"
                        );
    }//GEN-LAST:event_jButton33MousePressed
        
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTa1x;
    private javax.swing.JTextField JTa2x;
    private javax.swing.JTextField JTa2y;
    private javax.swing.JTextField JTb1x;
    private javax.swing.JTextField JTb1y;
    private javax.swing.JTextField JTb2x;
    private javax.swing.JTextField JTb2y;
    private javax.swing.JTextField a_par;
    private javax.swing.ButtonGroup buttonGroupLVS;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JDesktopPane jDesktopPane5;
    private javax.swing.JDesktopPane jDesktopPane6;
    private javax.swing.JDesktopPane jDesktopPane7;
    private javax.swing.JDesktopPane jDesktopPane8;
    private javax.swing.JDesktopPane jDesktopPane9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JRadioButton jRadioButtonBCC;
    private javax.swing.JRadioButton jRadioButtonCubic;
    private javax.swing.JRadioButton jRadioButtonDIA;
    private javax.swing.JRadioButton jRadioButtonFCC;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTa1y;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableLVS;
    private javax.swing.JTable jTableRLVS;
    private javax.swing.JTextArea jTextAreaInput;
    private javax.swing.JTextField kptsX;
    private javax.swing.JTextField kptsY;
    private javax.swing.JTextField kptsZ;
    private javax.swing.JTextField kpts_A;
    private javax.swing.JTextField kpts_N;
    private javax.swing.JTextField kpts_N2;
    private javax.swing.JTextField kpts_N3;
    private javax.swing.JCheckBox sim;
    // End of variables declaration//GEN-END:variables
    
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
