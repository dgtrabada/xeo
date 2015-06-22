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
import java.util.ArrayList;
import java.awt.Font;


public class Jbegin extends javax.swing.JFrame {
    begin begin = new begin();
    pintar2D.pintar2D pintar2D_begin = new pintar2D.pintar2D();
    ArrayList<String> g2d = new ArrayList();
    String path;
    public Font fuente ;
    //---chanchullo---
    int dIc=5;
    String SEP;
    /*
     pseudopotencial *.pp
     neutral atom file *.na0
     wafefunction file *wfi
     shell potencial file *.nai
     */
    public Jbegin(String p) {
        path=p;
        setXeoFont("/dejavu/DejaVuSans.ttf");
        initComponents();
        SEP = System.getProperty("file.separator");
    }
    
    void iniciar(String homefireball,String HOME){
        begin.HOME=HOME;
    }
    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPopupMenBegin = new javax.swing.JPopupMenu();
        jMenuopen = new javax.swing.JMenuItem();
        jMenuSave = new javax.swing.JMenuItem();
        jMenSee = new javax.swing.JMenuItem();
        jMenuReload = new javax.swing.JMenuItem();
        jMenureloadAdjust = new javax.swing.JMenuItem();
        jMenuOption = new javax.swing.JMenuItem();
        TAB_begin = new javax.swing.JTabbedPane();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        Z = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton39 = new javax.swing.JButton();
        Ltable = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        pathPeriodictable = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        C_s = new javax.swing.JCheckBox();
        C_p = new javax.swing.JCheckBox();
        C_d = new javax.swing.JCheckBox();
        C_f = new javax.swing.JCheckBox();
        jLabel22 = new javax.swing.JLabel();
        n_f = new javax.swing.JTextField();
        n_d = new javax.swing.JTextField();
        n_p = new javax.swing.JTextField();
        n_s = new javax.swing.JTextField();
        n_orbital = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        r_f = new javax.swing.JTextField();
        r_d = new javax.swing.JTextField();
        r_p = new javax.swing.JTextField();
        r_s = new javax.swing.JTextField();
        NExc = new javax.swing.JRadioButton();
        Exc_1 = new javax.swing.JRadioButton();
        n_se = new javax.swing.JTextField();
        Exc_2 = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        n_pe = new javax.swing.JTextField();
        n_de = new javax.swing.JTextField();
        n_fe = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        Exc_3 = new javax.swing.JRadioButton();
        mix_p = new javax.swing.JTextField();
        mix_s = new javax.swing.JTextField();
        mix_d = new javax.swing.JTextField();
        mix_f = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        vo1 = new javax.swing.JTextField();
        vo2 = new javax.swing.JTextField();
        vo3 = new javax.swing.JTextField();
        vo4 = new javax.swing.JTextField();
        r04 = new javax.swing.JTextField();
        r03 = new javax.swing.JTextField();
        r02 = new javax.swing.JTextField();
        r01 = new javax.swing.JTextField();
        voe4 = new javax.swing.JTextField();
        voe3 = new javax.swing.JTextField();
        voe2 = new javax.swing.JTextField();
        voe1 = new javax.swing.JTextField();
        r0e4 = new javax.swing.JTextField();
        r0e3 = new javax.swing.JTextField();
        r0e2 = new javax.swing.JTextField();
        r0e1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        attrPot = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AreabeginScript = new javax.swing.JTextArea();
        reload_begin_script = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        open_begin = new javax.swing.JButton();
        save_begin = new javax.swing.JButton();
        reload_begin = new javax.swing.JButton();
        reload1 = new javax.swing.JButton();
        see0 = new javax.swing.JCheckBox();
        jSplitPane1 = new javax.swing.JSplitPane();
        screen_begin = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        INFO = new javax.swing.JTextArea();
        jButton34 = new javax.swing.JButton();
        reload_begin1 = new javax.swing.JButton();

        jMenuopen.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuopen.setText("open");
        jMenuopen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuopenMousePressed1(evt);
            }
        });

        jPopupMenBegin.add(jMenuopen);

        jMenuSave.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuSave.setText("save");
        jMenuSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuSaveMousePressed(evt);
            }
        });

        jPopupMenBegin.add(jMenuSave);

        jMenSee.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenSee.setIcon(new javax.swing.ImageIcon("..."));
        jMenSee.setText("preview");
        jMenSee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenSeeMousePressed(evt);
            }
        });

        jPopupMenBegin.add(jMenSee);

        jMenuReload.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuReload.setText("reload");
        jMenuReload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuReloadMousePressed(evt);
            }
        });

        jPopupMenBegin.add(jMenuReload);

        jMenureloadAdjust.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenureloadAdjust.setText("reload rescalate");
        jMenureloadAdjust.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenureloadAdjustMousePressed(evt);
            }
        });

        jPopupMenBegin.add(jMenureloadAdjust);

        jMenuOption.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuOption.setText("options");
        jMenuOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuOptionMousePressed(evt);
            }
        });

        jPopupMenBegin.add(jMenuOption);

        setTitle("Begin");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        TAB_begin.setFont(new java.awt.Font("Monospaced", 0, 12));
        jTabbedPane6.setFont(new java.awt.Font("Monospaced", 0, 12));
        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        Z.setFont(fuente);
        Z.setText("1");
        Z.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel19.setFont(fuente);
        jLabel19.setText("Z =");

        jButton39.setFont(fuente);
        jButton39.setText("load");
        jButton39.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton39.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jButton39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton39MousePressed(evt);
            }
        });

        Ltable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ltable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/table.gif")));
        Ltable.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        info.setText("...");

        jLabel32.setFont(fuente);
        jLabel32.setText("If you want load the defoult values of the periodictable.input,");

        jButton40.setFont(fuente);
        jButton40.setText("insert the path");
        jButton40.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton40.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jButton40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton40MousePressed(evt);
            }
        });

        pathPeriodictable.setFont(fuente);
        pathPeriodictable.setText("begin/periodictable.input");
        pathPeriodictable.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jLabel19)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(Z, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(info, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 290, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel32)
                    .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, Ltable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel11Layout.createSequentialGroup()
                            .add(jButton40)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(pathPeriodictable, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 312, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Z, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel19)
                    .add(info, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel32)
                .add(12, 12, 12)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pathPeriodictable, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Ltable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane6.addTab("  Z  ", jPanel11);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        C_s.setFont(fuente);
        C_s.setText("s-shell");
        C_s.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        C_s.setMargin(new java.awt.Insets(0, 0, 0, 0));
        C_s.setOpaque(false);

        C_p.setFont(fuente);
        C_p.setText("p-shell");
        C_p.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        C_p.setMargin(new java.awt.Insets(0, 0, 0, 0));
        C_p.setOpaque(false);

        C_d.setFont(fuente);
        C_d.setText("d-shell");
        C_d.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        C_d.setMargin(new java.awt.Insets(0, 0, 0, 0));
        C_d.setOpaque(false);

        C_f.setFont(fuente);
        C_f.setText("f-shell");
        C_f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        C_f.setMargin(new java.awt.Insets(0, 0, 0, 0));
        C_f.setOpaque(false);

        jLabel22.setFont(fuente);
        jLabel22.setText("N orbitals");

        n_f.setFont(fuente);
        n_f.setText("0");
        n_f.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        n_d.setFont(fuente);
        n_d.setText("0");
        n_d.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        n_p.setFont(fuente);
        n_p.setText("0");
        n_p.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        n_s.setFont(fuente);
        n_s.setText("0");
        n_s.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        n_orbital.setFont(fuente);
        n_orbital.setText("0");
        n_orbital.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel20.setFont(fuente);
        jLabel20.setText("n elec.");

        jLabel21.setFont(fuente);
        jLabel21.setText("Radio");

        r_f.setFont(fuente);
        r_f.setText("0.0");
        r_f.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r_d.setFont(fuente);
        r_d.setText("0.0");
        r_d.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r_p.setFont(fuente);
        r_p.setText("0.0");
        r_p.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r_s.setFont(fuente);
        r_s.setText("0.0");
        r_s.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        NExc.setFont(fuente);
        NExc.setSelected(true);
        NExc.setText("NO (calculaton the excited state)");
        NExc.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        NExc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        NExc.setOpaque(false);

        Exc_1.setFont(fuente);
        Exc_1.setText("default excited stated");
        Exc_1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Exc_1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Exc_1.setOpaque(false);

        n_se.setFont(fuente);
        n_se.setText("0");
        n_se.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        Exc_2.setFont(fuente);
        Exc_2.setText("DMOL");
        Exc_2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Exc_2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Exc_2.setOpaque(false);

        jLabel23.setFont(fuente);
        jLabel23.setText("n elec.");

        n_pe.setFont(fuente);
        n_pe.setText("0");
        n_pe.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        n_de.setFont(fuente);
        n_de.setText("0");
        n_de.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        n_fe.setFont(fuente);
        n_fe.setText("0");
        n_fe.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel24.setFont(fuente);
        jLabel24.setText("mixing factor ");

        Exc_3.setFont(fuente);
        Exc_3.setText("mix");
        Exc_3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Exc_3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Exc_3.setOpaque(false);

        mix_p.setFont(fuente);
        mix_p.setText("0.0");
        mix_p.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        mix_s.setFont(fuente);
        mix_s.setText("0.0");
        mix_s.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        mix_d.setFont(fuente);
        mix_d.setText("0.0");
        mix_d.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        mix_f.setFont(fuente);
        mix_f.setText("0.0");
        mix_f.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel34.setFont(fuente);
        jLabel34.setText("[0-1]");

        org.jdesktop.layout.GroupLayout jPanel20Layout = new org.jdesktop.layout.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(C_s)
                    .add(C_p)
                    .add(C_d)
                    .add(C_f)
                    .add(jLabel22))
                .add(31, 31, 31)
                .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(n_orbital)
                        .add(n_f)
                        .add(n_d)
                        .add(n_p, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, n_s, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel20)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel21)
                    .add(r_s, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .add(r_p, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .add(r_d, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .add(r_f))
                .add(15, 15, 15)
                .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel23, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(n_fe, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .add(n_de, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .add(n_pe, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .add(n_se, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(12, 12, 12)
                .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel24)
                    .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(mix_f, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(mix_d, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, mix_p, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(mix_s, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel34))
                .addContainerGap(63, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(NExc)
                    .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(Exc_2)
                        .add(Exc_1)
                        .add(Exc_3)))
                .add(45, 45, 45))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel20Layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(NExc)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Exc_1)
                .add(5, 5, 5)
                .add(Exc_2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Exc_3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel20Layout.createSequentialGroup()
                        .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel20)
                            .add(jLabel21)
                            .add(jLabel23)
                            .add(jLabel34))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(C_s)
                            .add(n_s, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(r_s, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(C_p)
                            .add(n_p, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(r_p, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(C_d)
                            .add(n_d, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(r_d, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(C_f)
                            .add(n_f, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(r_f, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel22)
                            .add(n_orbital, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel20Layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, n_se, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, mix_s, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel20Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel20Layout.createSequentialGroup()
                                .add(n_pe, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(n_de, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(n_fe, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel20Layout.createSequentialGroup()
                                .add(mix_p, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(mix_d, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(mix_f, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane6.addTab("configuration", jPanel13);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jRadioButton1.setFont(fuente);
        jRadioButton1.setText("  1   LDA  Wigner");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton1.setOpaque(false);

        jRadioButton12.setFont(fuente);
        jRadioButton12.setText(" 12  B3LYP  mix exact exchange and BLYP");
        jRadioButton12.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton12.setOpaque(false);

        jRadioButton11.setFont(fuente);
        jRadioButton11.setText(" 11  LSDA Vosko/Wilk/Nusair (1980)");
        jRadioButton11.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton11.setOpaque(false);

        jRadioButton10.setFont(fuente);
        jRadioButton10.setText(" 10  GGA  Perdew/Wang (1991) X, Lee/Yang/Parr (1988)");
        jRadioButton10.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton10.setOpaque(false);

        jRadioButton8.setFont(fuente);
        jRadioButton8.setText("  8   LDA  Ceperley/Alder Perdew/Wang (1991)");
        jRadioButton8.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton8.setOpaque(false);

        jRadioButton9.setFont(fuente);
        jRadioButton9.setText("  9   GGA  Becke (1988) X, Lee/Yang/Parr (1988)");
        jRadioButton9.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton9.setOpaque(false);

        jRadioButton7.setFont(fuente);
        jRadioButton7.setText("  7   LDA  Zhao/Parr");
        jRadioButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton7.setOpaque(false);

        jRadioButton6.setFont(fuente);
        jRadioButton6.setText("  6   GGA  Perdew/Burke/Ernzerhof (1996) ");
        jRadioButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton6.setOpaque(false);

        jRadioButton4.setFont(fuente);
        jRadioButton4.setText("  4   GGA  Perdew/Wang (1991)");
        jRadioButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton4.setOpaque(false);

        jRadioButton5.setFont(fuente);
        jRadioButton5.setText("  5   GGA  Becke (1988) X, Perdew (1986)");
        jRadioButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton5.setOpaque(false);

        jRadioButton2.setFont(fuente);
        jRadioButton2.setText("  2   LDA  Hedin/Lundqvist");
        jRadioButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton2.setOpaque(false);

        jRadioButton3.setFont(fuente);
        jRadioButton3.setSelected(true);
        jRadioButton3.setText("  3   LDA  Ceperley/Alder Perdew/Zunger (1980)");
        jRadioButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton3.setOpaque(false);

        org.jdesktop.layout.GroupLayout jPanel21Layout = new org.jdesktop.layout.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel21Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jRadioButton1)
                    .add(jRadioButton12)
                    .add(jRadioButton11)
                    .add(jRadioButton10)
                    .add(jRadioButton8)
                    .add(jRadioButton9)
                    .add(jRadioButton7)
                    .add(jRadioButton6)
                    .add(jRadioButton4)
                    .add(jRadioButton5)
                    .add(jRadioButton2)
                    .add(jRadioButton3))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .add(jRadioButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton9)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton10)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton11)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jRadioButton12)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel16Layout = new org.jdesktop.layout.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel21, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel21, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane6.addTab("functional", jPanel16);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel25.setFont(fuente);
        jLabel25.setText(" 1 'th orbital");

        jLabel26.setFont(fuente);
        jLabel26.setText(" 3 'th orbital");

        jLabel27.setFont(fuente);
        jLabel27.setText(" 2 'th orbital");

        jLabel28.setFont(fuente);
        jLabel28.setText(" 4 'th orbital");

        vo1.setFont(fuente);
        vo1.setText("0.0");
        vo1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        vo2.setFont(fuente);
        vo2.setText("0.0");
        vo2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        vo3.setFont(fuente);
        vo3.setText("0.0");
        vo3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        vo4.setFont(fuente);
        vo4.setText("0.0");
        vo4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r04.setFont(fuente);
        r04.setText("0.0");
        r04.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r03.setFont(fuente);
        r03.setText("0.0");
        r03.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r02.setFont(fuente);
        r02.setText("0.0");
        r02.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r01.setFont(fuente);
        r01.setText("0.0");
        r01.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        voe4.setFont(fuente);
        voe4.setText("0.0");
        voe4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        voe3.setFont(fuente);
        voe3.setText("0.0");
        voe3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        voe2.setFont(fuente);
        voe2.setText("0.0");
        voe2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        voe1.setFont(fuente);
        voe1.setText("0.0");
        voe1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r0e4.setFont(fuente);
        r0e4.setText("0.0");
        r0e4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r0e3.setFont(fuente);
        r0e3.setText("0.0");
        r0e3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r0e2.setFont(fuente);
        r0e2.setText("0.0");
        r0e2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        r0e1.setFont(fuente);
        r0e1.setText("0.0");
        r0e1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel29.setFont(fuente);
        jLabel29.setText(" Vo            r0     Vo (exc.)    r0 (exc.)");

        org.jdesktop.layout.GroupLayout jPanel18Layout = new org.jdesktop.layout.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel25)
                    .add(jLabel27)
                    .add(jLabel26)
                    .add(jLabel28))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel29)
                    .add(jPanel18Layout.createSequentialGroup()
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(vo4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .add(vo1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .add(vo2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .add(vo3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .add(15, 15, 15)
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(r04)
                            .add(r03)
                            .add(r02)
                            .add(r01, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .add(15, 15, 15)
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(voe3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .add(voe2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .add(voe1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .add(voe4))
                        .add(16, 16, 16)
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(r0e1)
                            .add(r0e2)
                            .add(r0e3)
                            .add(r0e4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel29)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel18Layout.createSequentialGroup()
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(r01, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(voe1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(r0e1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(r02, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(voe2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(r0e2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(r03, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(voe3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(r0e3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel28)
                            .add(vo4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(r04, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(voe4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(r0e4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel18Layout.createSequentialGroup()
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel25)
                            .add(vo1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel18Layout.createSequentialGroup()
                                .add(jLabel27)
                                .add(42, 42, 42))
                            .add(jPanel18Layout.createSequentialGroup()
                                .add(vo2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel18Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel26)
                                    .add(vo3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(13, 13, 13)))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        attrPot.setFont(fuente);
        attrPot.setText("Do you want to use additional ");
        attrPot.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        attrPot.setMargin(new java.awt.Insets(0, 0, 0, 0));
        attrPot.setOpaque(false);

        jLabel31.setFont(fuente);
        jLabel31.setText("else  r < r0      V = 0.0");

        jLabel30.setFont(fuente);
        jLabel30.setText("for   r > r0      V = Vo * exp -((rc-r0)/(r-r0))/(rc-r)");

        jLabel35.setFont(fuente);
        jLabel35.setText("attraction potential to optimize basis set?");

        org.jdesktop.layout.GroupLayout jPanel22Layout = new org.jdesktop.layout.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(attrPot)
                    .add(jPanel22Layout.createSequentialGroup()
                        .add(17, 17, 17)
                        .add(jPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel30)
                            .add(jLabel35)
                            .add(jLabel31)))
                    .add(jPanel18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .add(attrPot)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel35)
                .add(5, 5, 5)
                .add(jLabel30)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel31)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel17Layout = new org.jdesktop.layout.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel22, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel22, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane6.addTab("ad.at.pot.", jPanel17);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        AreabeginScript.setColumns(20);
        AreabeginScript.setFont(new java.awt.Font("Monospaced", 0, 12));
        AreabeginScript.setRows(5);
        AreabeginScript.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jScrollPane2.setViewportView(AreabeginScript);

        reload_begin_script.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/reload.gif")));
        reload_begin_script.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        reload_begin_script.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reload_begin_scriptMousePressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel23Layout = new org.jdesktop.layout.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel23Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .add(reload_begin_script, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .add(reload_begin_script, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTabbedPane6.addTab("script", jPanel23);

        TAB_begin.addTab("initial", jTabbedPane6);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        open_begin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/open.gif")));
        open_begin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        open_begin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                open_beginMousePressed(evt);
            }
        });

        save_begin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save.gif")));
        save_begin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        save_begin.setEnabled(false);
        save_begin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                save_beginMousePressed(evt);
            }
        });

        reload_begin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/reload.gif")));
        reload_begin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        reload_begin.setEnabled(false);
        reload_begin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reload_beginMousePressed(evt);
            }
        });

        reload1.setFont(fuente);
        reload1.setText("...");
        reload1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        reload1.setEnabled(false);
        reload1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reload1MousePressed(evt);
            }
        });

        see0.setFont(fuente);
        see0.setSelected(true);
        see0.setText("see 0.0");
        see0.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        see0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        see0.setOpaque(false);

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(220);
        jSplitPane1.setDividerSize(7);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        screen_begin.setBackground(new java.awt.Color(255, 255, 255));
        screen_begin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        screen_begin.setEnabled(false);
        screen_begin.setOpaque(true);
        screen_begin.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        screen_begin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                screen_beginMouseDragged(evt);
            }
        });
        screen_begin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                screen_beginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                screen_beginMouseReleased(evt);
            }
        });

        jSplitPane1.setTopComponent(screen_begin);

        jScrollPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jScrollPane1ComponentResized(evt);
            }
        });

        INFO.setColumns(5);
        INFO.setRows(2);
        INFO.setTabSize(2);
        INFO.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jScrollPane1.setViewportView(INFO);

        jSplitPane1.setRightComponent(jScrollPane1);

        jSplitPane1.getAccessibleContext().setAccessibleParent(n_p);

        jButton34.setFont(fuente);
        jButton34.setText("Opt.");
        jButton34.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton34.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton34MousePressed(evt);
            }
        });

        reload_begin1.setFont(fuente     );
        reload_begin1.setText("help");
        reload_begin1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        reload_begin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reload_begin1MousePressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel19Layout = new org.jdesktop.layout.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .add(jPanel19Layout.createSequentialGroup()
                        .add(open_begin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(save_begin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(reload1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(reload_begin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(see0)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 104, Short.MAX_VALUE)
                        .add(reload_begin1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(open_begin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(save_begin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(reload1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButton34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel19Layout.createSequentialGroup()
                        .add(jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel19Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(see0)
                                .add(reload_begin1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(reload_begin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(1, 1, 1)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );
        TAB_begin.addTab(" represent (*.wf, *.na , *pp)", jPanel19);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TAB_begin, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TAB_begin, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void reload_begin1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reload_begin1MousePressed
        new dialogo.helpURL(getClass().getResource("/help/begin.html")).setVisible(true);
    }//GEN-LAST:event_reload_begin1MousePressed
    
    private void jButton34MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MousePressed
        pintar2D_begin.opt.setVisible(true);
        pintar2D_begin.opt.ini();
    }//GEN-LAST:event_jButton34MousePressed
    
    private void jMenuOptionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuOptionMousePressed
        pintar2D_begin.opt.setVisible(true);
        pintar2D_begin.opt.ini();
    }//GEN-LAST:event_jMenuOptionMousePressed
    
    private void jMenureloadAdjustMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenureloadAdjustMousePressed
        pintarBegin(true);
    }//GEN-LAST:event_jMenureloadAdjustMousePressed
    
    private void jMenuReloadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuReloadMousePressed
        pintarBegin(false);
    }//GEN-LAST:event_jMenuReloadMousePressed
    
    private void jMenSeeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenSeeMousePressed
        if(reload1.isEnabled())
            new dialogo.show_picture(pintar2D_begin.inputfile2D.getAbsolutePath()).plot(pintar2D_begin.imageBuffered);
    }//GEN-LAST:event_jMenSeeMousePressed
    
    private void jMenuSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSaveMousePressed
        new dialogo.chooser().savePicture("Save file *.jpg","save","image.out",pintar2D_begin.imageBuffered);
        
    }//GEN-LAST:event_jMenuSaveMousePressed
    
    private void jMenuopenMousePressed1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuopenMousePressed1
        File newFile=null;
        if(pintar2D_begin.inputfile2D!=null)  newFile =  new dialogo.chooser().fileChoose("begin","open",pintar2D_begin.inputfile2D.getAbsolutePath() );
        else newFile =  new dialogo.chooser().fileChoose("begin","open", new File(new File(pathPeriodictable.getText()).getAbsolutePath()).getParent()+SEP+".");
        if(newFile!=null) INFO.setText(INFO.getText()+"\n"+newFile.getAbsolutePath());
        pintar2D_begin.inputfile2D=newFile;
        pintarBegin(true);
    }//GEN-LAST:event_jMenuopenMousePressed1
    
    private void jScrollPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPane1ComponentResized
        screen_begin.setIcon(null);
    }//GEN-LAST:event_jScrollPane1ComponentResized
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        screen_begin.setIcon(null);
    }//GEN-LAST:event_formComponentResized
    
    private void reload1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reload1MousePressed
        if(reload1.isEnabled())
            new dialogo.show_picture(pintar2D_begin.inputfile2D.getAbsolutePath()).plot(pintar2D_begin.imageBuffered);
    }//GEN-LAST:event_reload1MousePressed
    
    private void reload_beginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reload_beginMousePressed
        pintarBegin(false);
    }//GEN-LAST:event_reload_beginMousePressed
    
    private void save_beginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save_beginMousePressed
        new dialogo.chooser().savePicture("Save file *.jpg","save","image.out",pintar2D_begin.imageBuffered);
    }//GEN-LAST:event_save_beginMousePressed
    
    private void open_beginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_open_beginMousePressed
        File newFile=null;
        if(pintar2D_begin.inputfile2D!=null)  newFile =  new dialogo.chooser().fileChoose("begin","open",pintar2D_begin.inputfile2D.getAbsolutePath() );
        else newFile =  new dialogo.chooser().fileChoose("begin","open", path);
        if(newFile!=null) INFO.setText(INFO.getText()+"\n"+newFile.getAbsolutePath());
        pintar2D_begin.inputfile2D=newFile;
        pintarBegin(true);
    }//GEN-LAST:event_open_beginMousePressed
    
    private void screen_beginMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_beginMouseDragged
        if(screen_begin.isEnabled()&&pintar2D_begin.mouse){
            pintar2D_begin.mouseFin(evt.getX(),evt.getY(),true);
            screen_begin.setIcon(new  javax.swing.ImageIcon(pintar2D_begin.Selec()));
        }
    }//GEN-LAST:event_screen_beginMouseDragged
    
    private void screen_beginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_beginMouseReleased
        if(evt.getButton()==evt.BUTTON1 && screen_begin.isEnabled()){
            pintar2D_begin.lupa2D();
            if(pintar2D_begin.opt.isVisible()) pintar2D_begin.opt.ini();
            pintarBegin(false);
            pintar2D_begin.mouse=false;
        }
    }//GEN-LAST:event_screen_beginMouseReleased
    
    private void screen_beginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_beginMousePressed
        if(evt.getButton()==evt.BUTTON1 && screen_begin.isEnabled()) pintar2D_begin.mouseIni(evt.getX(),evt.getY(),true);
        if(evt.getButton()==evt.BUTTON3) jPopupMenBegin.show( evt.getComponent(), evt.getX(), evt.getY() ) ;
    }//GEN-LAST:event_screen_beginMousePressed
    
    private void reload_begin_scriptMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reload_begin_scriptMousePressed
        makeScript();
    }//GEN-LAST:event_reload_begin_scriptMousePressed
    
    private void jButton40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MousePressed
        File newFile = null;
        newFile =  new dialogo.chooser().fileChoose("Open file peridictable.input","open",begin.HOME) ;
        if(newFile!=null)  pathPeriodictable.setText(newFile.getAbsolutePath());
    }//GEN-LAST:event_jButton40MousePressed
    
    private void jButton39MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton39MousePressed
        begin.Z=(int) Double.valueOf(Z.getText()).doubleValue();
        begin.pathPeridictable=new File(pathPeriodictable.getText());
        begin.loadPeriodictable();
        C_s.setSelected(begin.C_s);
        C_p.setSelected(begin.C_p);
        C_d.setSelected(begin.C_d);
        C_f.setSelected(begin.C_f);
        n_s.setText(begin.ns+"");
        n_p.setText(begin.np+"");
        n_d.setText(begin.nd+"");
        n_f.setText(begin.nf+"");
        r_s.setText(begin.rs+"");
        r_p.setText(begin.rp+"");
        r_d.setText(begin.rd+"");
        r_f.setText(begin.rf+"");
        n_se.setText(begin.ns+"");
        n_pe.setText(begin.np+"");
        n_de.setText(begin.nd+"");
        n_fe.setText(begin.nf+"");
        //--cargamos datos
        info.setText(begin.name+"  ( "+begin.Simbol+" );  M="+begin.Mass);
        n_orbital.setText(begin.NShell+"");
        makeScript();
    }//GEN-LAST:event_jButton39MousePressed
    
    
    
    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JTextArea AreabeginScript;
    private javax.swing.JCheckBox C_d;
    private javax.swing.JCheckBox C_f;
    private javax.swing.JCheckBox C_p;
    private javax.swing.JCheckBox C_s;
    private javax.swing.JRadioButton Exc_1;
    private javax.swing.JRadioButton Exc_2;
    private javax.swing.JRadioButton Exc_3;
    private javax.swing.JTextArea INFO;
    private javax.swing.JLabel Ltable;
    private javax.swing.JRadioButton NExc;
    private javax.swing.JTabbedPane TAB_begin;
    private javax.swing.JTextField Z;
    private javax.swing.JCheckBox attrPot;
    private javax.swing.JLabel info;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JMenuItem jMenSee;
    private javax.swing.JMenuItem jMenuOption;
    private javax.swing.JMenuItem jMenuReload;
    private javax.swing.JMenuItem jMenuSave;
    private javax.swing.JMenuItem jMenuopen;
    private javax.swing.JMenuItem jMenureloadAdjust;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPopupMenu jPopupMenBegin;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTextField mix_d;
    private javax.swing.JTextField mix_f;
    private javax.swing.JTextField mix_p;
    private javax.swing.JTextField mix_s;
    private javax.swing.JTextField n_d;
    private javax.swing.JTextField n_de;
    private javax.swing.JTextField n_f;
    private javax.swing.JTextField n_fe;
    private javax.swing.JTextField n_orbital;
    private javax.swing.JTextField n_p;
    private javax.swing.JTextField n_pe;
    private javax.swing.JTextField n_s;
    private javax.swing.JTextField n_se;
    private javax.swing.JButton open_begin;
    private javax.swing.JTextField pathPeriodictable;
    private javax.swing.JTextField r01;
    private javax.swing.JTextField r02;
    private javax.swing.JTextField r03;
    private javax.swing.JTextField r04;
    private javax.swing.JTextField r0e1;
    private javax.swing.JTextField r0e2;
    private javax.swing.JTextField r0e3;
    private javax.swing.JTextField r0e4;
    private javax.swing.JTextField r_d;
    private javax.swing.JTextField r_f;
    private javax.swing.JTextField r_p;
    private javax.swing.JTextField r_s;
    private javax.swing.JButton reload1;
    private javax.swing.JButton reload_begin;
    private javax.swing.JButton reload_begin1;
    private javax.swing.JButton reload_begin_script;
    private javax.swing.JButton save_begin;
    private javax.swing.JLabel screen_begin;
    private javax.swing.JCheckBox see0;
    private javax.swing.JTextField vo1;
    private javax.swing.JTextField vo2;
    private javax.swing.JTextField vo3;
    private javax.swing.JTextField vo4;
    private javax.swing.JTextField voe1;
    private javax.swing.JTextField voe2;
    private javax.swing.JTextField voe3;
    private javax.swing.JTextField voe4;
    // Fin de declaración de variables//GEN-END:variables
    
    
    void   makeScript(){
        ArrayList<String> scriptBegin = new ArrayList();
        scriptBegin.add("if [[ -f initial.x ]]");
        scriptBegin.add("then echo run");
        scriptBegin.add("else make ;make clean ;");
        scriptBegin.add( "fi ");
        scriptBegin.add( "cat << EOF|./initial.x" );
        scriptBegin.add(Z.getText());
        scriptBegin.add("N");
        if(C_s.isSelected()) scriptBegin.add("s");
        if(C_p.isSelected()) scriptBegin.add("p");
        if(C_d.isSelected()) scriptBegin.add("d");
        if(C_f.isSelected()) scriptBegin.add("f");
        if(!(C_s.isSelected()&&C_p.isSelected()&&C_d.isSelected()&&C_f.isSelected())) scriptBegin.add("c");
        scriptBegin.add("N");
        if(C_s.isSelected()) scriptBegin.add(n_s.getText());
        if(C_p.isSelected()) scriptBegin.add(n_p.getText());
        if(C_d.isSelected()) scriptBegin.add(n_d.getText());
        if(C_f.isSelected()) scriptBegin.add(n_f.getText());
        scriptBegin.add("N");
        if(C_s.isSelected()) scriptBegin.add(r_s.getText());
        if(C_p.isSelected()) scriptBegin.add(r_p.getText());
        if(C_d.isSelected()) scriptBegin.add(r_d.getText());
        if(C_f.isSelected()) scriptBegin.add(r_f.getText());
        if(NExc.isSelected()) scriptBegin.add("N");
        else{
            scriptBegin.add("Y");
            if(Exc_1.isSelected())scriptBegin.add("1");
            if(Exc_2.isSelected())scriptBegin.add("2");
            if(Exc_3.isSelected())scriptBegin.add("3");
            if(Exc_2.isSelected()||Exc_3.isSelected()){
                if(C_s.isSelected()) scriptBegin.add(n_se.getText());
                if(C_p.isSelected()) scriptBegin.add(n_pe.getText());
                if(C_d.isSelected()) scriptBegin.add(n_de.getText());
                if(C_f.isSelected()) scriptBegin.add(n_fe.getText());
            }
            if(Exc_3.isSelected()){
                if(C_s.isSelected()) scriptBegin.add(mix_s.getText());
                if(C_p.isSelected()) scriptBegin.add(mix_p.getText());
                if(C_d.isSelected()) scriptBegin.add(mix_d.getText());
                if(C_f.isSelected()) scriptBegin.add(mix_f.getText());
            }
        }
        scriptBegin.add("N");
        if(jRadioButton1.isSelected())scriptBegin.add("1");
        if(jRadioButton2.isSelected())scriptBegin.add("2");
        if(jRadioButton3.isSelected())scriptBegin.add("3");
        if(jRadioButton4.isSelected())scriptBegin.add("4");
        if(jRadioButton5.isSelected())scriptBegin.add("5");
        if(jRadioButton6.isSelected())scriptBegin.add("6");
        if(jRadioButton7.isSelected())scriptBegin.add("7");
        if(jRadioButton8.isSelected())scriptBegin.add("8");
        if(jRadioButton9.isSelected())scriptBegin.add("9");
        if(jRadioButton10.isSelected())scriptBegin.add("10");
        if(jRadioButton11.isSelected())scriptBegin.add("11");
        if(jRadioButton12.isSelected())scriptBegin.add("12");
        if(!attrPot.isSelected())scriptBegin.add("N");
        else{
            scriptBegin.add("N");
            if(C_s.isSelected()) scriptBegin.add(vo1.getText());
            if(C_s.isSelected()) scriptBegin.add(r01.getText());
            if(C_p.isSelected()) scriptBegin.add(vo2.getText());
            if(C_p.isSelected()) scriptBegin.add(r02.getText());
            if(C_d.isSelected()) scriptBegin.add(vo3.getText());
            if(C_d.isSelected()) scriptBegin.add(r03.getText());
            if(C_f.isSelected()) scriptBegin.add(vo4.getText());
            if(C_f.isSelected()) scriptBegin.add(r04.getText());
            if(!NExc.isSelected()){
                if(C_s.isSelected()) scriptBegin.add(voe1.getText());
                if(C_s.isSelected()) scriptBegin.add(r0e1.getText());
                if(C_p.isSelected()) scriptBegin.add(voe2.getText());
                if(C_p.isSelected()) scriptBegin.add(r0e2.getText());
                if(C_d.isSelected()) scriptBegin.add(voe3.getText());
                if(C_d.isSelected()) scriptBegin.add(r0e3.getText());
                if(C_f.isSelected()) scriptBegin.add(voe4.getText());
                if(C_f.isSelected()) scriptBegin.add(r0e4.getText());
            }
        }
        scriptBegin.add("EOF");
        scriptBegin.add("make && ./begin.x && make clean");
        AreabeginScript.setText("");
        for(int i=0;i<scriptBegin.size();i++){
            AreabeginScript.append(scriptBegin.get(i) + "\n");
        }
    }
    
    void pintarBegin(boolean ajustar_maximos){
        save_begin.setEnabled(true);
        reload1.setEnabled(true);
        reload_begin.setEnabled(true);
        screen_begin.setEnabled(true);
        if(pintar2D_begin.opt.isVisible())
            if(!pintar2D_begin.opt.ajustarMaximos)
                pintar2D_begin.opt.load();
        g2d.clear();
        //for(int i=1;i<=INFO.getLineCount();i++) if(!cadena.readCol(1,cadena.readLine(i,INFO.getText())).equals("0.0")) DOcol(cadena.readCol(1,cadena.readLine(i,INFO.getText())),nS++);
        pintar2D_begin.g2d=begin.LoadArraList(INFO,ajustar_maximos); //  :-)
        if(ajustar_maximos) pintar2D_begin.Max_diff_files_N_XY();
        pintar2D_begin.imageBuffered = new java.awt.image.BufferedImage(screen_begin.getWidth()-dIc,screen_begin.getHeight()-dIc, java.awt.image.BufferedImage.TYPE_INT_RGB);
        if( pintar2D_begin.firstTime){
            pintar2D_begin.firstTime=false;
            pintar2D_begin.opt.indexRight=true; //screen_outBuffered = pintar2D_begin2DS1.show_diff_files_XNY(aux,screen_outBuffered,"no index");
        }
        pintar2D_begin.show_diff_files_N_XY();
        if(see0.isSelected()) pintar2D_begin.VLine(0.0);
        screen_begin.setIcon(new javax.swing.ImageIcon(pintar2D_begin.imageBuffered));
        if(pintar2D_begin.opt.isVisible())
            if(pintar2D_begin.opt.ajustarMaximos)
                pintar2D_begin.opt.ini();
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
