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

import java.awt.Font;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JTextField;

public class Options2DPlot extends javax.swing.JFrame {
    reader.reader cadena = new reader.reader();
    Font fuente = new Font("SansSerif",Font.PLAIN,11);
    public double y_max,y_min;
    public double x_max,x_min;
    public boolean ajustarOptionsX;
    public boolean ajustarOptionsY;
    //marco (40-10,20-10)
    int MxL,MxR;
    int MyU,MyD,L;
    
    boolean indexDown;
    public boolean indexRight;
    public boolean showTitle;
    public String Title;
    //---ejes-----
    public int Aox;
    public int Aoy;
    public int nfx;
    public int nfy;
    public double hx,hy;
    Font currentTitle, currentIndex,currentEjes;
    Color colorFondo, colorEjes, colorGrafico,colorTitulo;
    public boolean ajustarMaximos;
    int [] ancho = new int[12];
    
    public JTextField getTdesk() {
        return Tdesk;
    }
    int [] anchoC = new int[12];
    boolean [] verLine = new boolean[12];
    boolean [] verCircle = new boolean[12];
    public java.util.ArrayList<String> mens = new java.util.ArrayList(); //mens = x y mensaje
    
    /** Creates new form Options2DPlot */
    public Options2DPlot() {
        initComponents();
        ajustarOptionsX=ajustarOptionsY=true;
        y_min=1;y_max=10;x_min=1;x_max=10;
        MxL=40;MxR=10;MyU=10;MyD=20;L=5;
        indexDown=false;
        indexRight=false;
        showTitle=false;
        Aoy=5;
        Aox=5;
        nfx=7;
        nfy=7;
        currentTitle = new Font("Dialog",Font.PLAIN,12);
        currentIndex = new Font("Monospaced",Font.PLAIN,12);
        currentEjes = new Font("Curier",Font.BOLD,9);
        colorFondo = Color.WHITE;
        colorEjes  = Color.GRAY;
        colorGrafico = Color.RED ;
        colorTitulo = Color.BLACK ;
        ajustarMaximos=true;
        loadColor();
        for(int i=0;i<12;i++) {
            ancho[i]=1;
            anchoC[i]=4;
            verLine[i]=true;
            verCircle[i]=false;
        }
    }
    
    void loadColor(){
        colorGLabel1.setBackground(new Color(cadena.C[1]));
        colorGLabel2.setBackground(new Color(cadena.C[2]));
        colorGLabel3.setBackground(new Color(cadena.C[3]));
        colorGLabel4.setBackground(new Color(cadena.C[4]));
        colorGLabel5.setBackground(new Color(cadena.C[5]));
        colorGLabel6.setBackground(new Color(cadena.C[6]));
        colorGLabel7.setBackground(new Color(cadena.C[7]));
        colorGLabel8.setBackground(new Color(cadena.C[8]));
        colorGLabel9.setBackground(new Color(cadena.C[9]));
        colorGLabel10.setBackground(new Color(cadena.C[10]));
        colorGLabel11.setBackground(new Color(cadena.C[11]));
        ancho1.setValue(1);
        ancho2.setValue(1);
        ancho3.setValue(1);
        ancho4.setValue(1);
        ancho5.setValue(1);
        ancho6.setValue(1);
        ancho7.setValue(1);
        ancho8.setValue(1);
        ancho9.setValue(1);
        ancho10.setValue(1);
        ancho11.setValue(1);
        anchoC1.setValue(4);
        anchoC2.setValue(4);
        anchoC3.setValue(4);
        anchoC4.setValue(4);
        anchoC5.setValue(4);
        anchoC6.setValue(4);
        anchoC7.setValue(4);
        anchoC8.setValue(4);
        anchoC9.setValue(4);
        anchoC10.setValue(4);
        anchoC11.setValue(4);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser = new javax.swing.JColorChooser();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jButton26 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        Tdespl51 = new javax.swing.JTextField();
        Tdespl52 = new javax.swing.JTextField();
        colorGLabel1 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        colorGLabel2 = new javax.swing.JPanel();
        Tdespl53 = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        colorGLabel3 = new javax.swing.JPanel();
        Tdespl54 = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        colorGLabel4 = new javax.swing.JPanel();
        Tdespl55 = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        colorGLabel5 = new javax.swing.JPanel();
        Tdespl56 = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        colorGLabel6 = new javax.swing.JPanel();
        Tdespl57 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        colorGLabel7 = new javax.swing.JPanel();
        Tdespl58 = new javax.swing.JTextField();
        jButton32 = new javax.swing.JButton();
        colorGLabel8 = new javax.swing.JPanel();
        Tdespl59 = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        colorGLabel9 = new javax.swing.JPanel();
        Tdespl60 = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        colorGLabel10 = new javax.swing.JPanel();
        Tdespl61 = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        colorGLabel11 = new javax.swing.JPanel();
        Tdespl62 = new javax.swing.JTextField();
        Tdespl64 = new javax.swing.JTextField();
        ancho1 = new javax.swing.JSpinner();
        ancho2 = new javax.swing.JSpinner();
        ancho3 = new javax.swing.JSpinner();
        ancho4 = new javax.swing.JSpinner();
        ancho5 = new javax.swing.JSpinner();
        ancho6 = new javax.swing.JSpinner();
        ancho7 = new javax.swing.JSpinner();
        ancho8 = new javax.swing.JSpinner();
        ancho9 = new javax.swing.JSpinner();
        ancho10 = new javax.swing.JSpinner();
        ancho11 = new javax.swing.JSpinner();
        Tdespl65 = new javax.swing.JTextField();
        Tdespl66 = new javax.swing.JTextField();
        seeL1 = new javax.swing.JCheckBox();
        seeL2 = new javax.swing.JCheckBox();
        seeL3 = new javax.swing.JCheckBox();
        seeL4 = new javax.swing.JCheckBox();
        seeL5 = new javax.swing.JCheckBox();
        seeL6 = new javax.swing.JCheckBox();
        seeL7 = new javax.swing.JCheckBox();
        seeL8 = new javax.swing.JCheckBox();
        seeL9 = new javax.swing.JCheckBox();
        seeL10 = new javax.swing.JCheckBox();
        seeL11 = new javax.swing.JCheckBox();
        seeC1 = new javax.swing.JCheckBox();
        seeC2 = new javax.swing.JCheckBox();
        seeC3 = new javax.swing.JCheckBox();
        seeC4 = new javax.swing.JCheckBox();
        seeC5 = new javax.swing.JCheckBox();
        seeC6 = new javax.swing.JCheckBox();
        seeC7 = new javax.swing.JCheckBox();
        seeC8 = new javax.swing.JCheckBox();
        seeC9 = new javax.swing.JCheckBox();
        seeC10 = new javax.swing.JCheckBox();
        seeC11 = new javax.swing.JCheckBox();
        Tdespl67 = new javax.swing.JTextField();
        anchoC1 = new javax.swing.JSpinner();
        anchoC2 = new javax.swing.JSpinner();
        anchoC3 = new javax.swing.JSpinner();
        anchoC4 = new javax.swing.JSpinner();
        anchoC5 = new javax.swing.JSpinner();
        anchoC6 = new javax.swing.JSpinner();
        anchoC7 = new javax.swing.JSpinner();
        anchoC8 = new javax.swing.JSpinner();
        anchoC9 = new javax.swing.JSpinner();
        anchoC10 = new javax.swing.JSpinner();
        anchoC11 = new javax.swing.JSpinner();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        Tdespl36 = new javax.swing.JTextField();
        Tdespl33 = new javax.swing.JTextField();
        Tdespl34 = new javax.swing.JTextField();
        Tdespl35 = new javax.swing.JTextField();
        Tdespl44 = new javax.swing.JTextField();
        nDigX = new javax.swing.JTextField();
        nDX = new javax.swing.JTextField();
        Xmax = new javax.swing.JTextField();
        Xmin = new javax.swing.JTextField();
        adjustX = new javax.swing.JCheckBox();
        Tdespl42 = new javax.swing.JTextField();
        Tdespl39 = new javax.swing.JTextField();
        Tdespl40 = new javax.swing.JTextField();
        Ymin = new javax.swing.JTextField();
        adjustY = new javax.swing.JCheckBox();
        Ymax = new javax.swing.JTextField();
        Tdespl43 = new javax.swing.JTextField();
        Tdespl45 = new javax.swing.JTextField();
        nDY = new javax.swing.JTextField();
        nDigY = new javax.swing.JTextField();
        Tdesk = new javax.swing.JTextField();
        Tdespl38 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        colorEjesLabel = new javax.swing.JPanel();
        fontEjes = new javax.swing.JTextField();
        fonTamEjes = new javax.swing.JSpinner();
        jButton18 = new javax.swing.JButton();
        colorFondoLabel = new javax.swing.JPanel();
        Tdespl37 = new javax.swing.JTextField();
        Tdespl47 = new javax.swing.JTextField();
        seeIndexRight = new javax.swing.JCheckBox();
        seeIndexDown = new javax.swing.JCheckBox();
        fontLeyenda = new javax.swing.JTextField();
        fonTamIndex = new javax.swing.JSpinner();
        Tdespl46 = new javax.swing.JTextField();
        fonTamTitulo = new javax.swing.JSpinner();
        Titulo = new javax.swing.JTextField();
        fontTitulo = new javax.swing.JTextField();
        colorTituloLabel = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        seeTitle = new javax.swing.JCheckBox();
        testString = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();

        setTitle("2D Options");
        setResizable(false);

        jDesktopPane3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jButton26.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton26.setText("Accept");
        jButton26.setToolTipText("");
        jButton26.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton26.setFocusable(false);
        jButton26.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton26MousePressed(evt);
            }
        });
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jDesktopPane3.add(jButton26);
        jButton26.setBounds(320, 480, 60, 18);

        Tdespl51.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl51.setEditable(false);
        Tdespl51.setFont(fuente);
        Tdespl51.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl51.setText("Color");
        Tdespl51.setToolTipText("");
        Tdespl51.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl51.setFocusable(false);
        jDesktopPane1.add(Tdespl51);
        Tdespl51.setBounds(0, 0, 128, 18);

        Tdespl52.setEditable(false);
        Tdespl52.setFont(fuente);
        Tdespl52.setText("Color 0");
        Tdespl52.setToolTipText("");
        Tdespl52.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl52.setOpaque(false);
        jDesktopPane1.add(Tdespl52);
        Tdespl52.setBounds(40, 20, 88, 18);

        colorGLabel1.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel1.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel1Layout = new org.jdesktop.layout.GroupLayout(colorGLabel1);
        colorGLabel1.setLayout(colorGLabel1Layout);
        colorGLabel1Layout.setHorizontalGroup(
            colorGLabel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel1Layout.setVerticalGroup(
            colorGLabel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel1);
        colorGLabel1.setBounds(20, 20, 18, 18);

        jButton24.setText("...");
        jButton24.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton24.setFocusable(false);
        jButton24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton24MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton24);
        jButton24.setBounds(0, 20, 18, 18);

        jButton25.setText("...");
        jButton25.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton25.setFocusable(false);
        jButton25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton25MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton25);
        jButton25.setBounds(0, 40, 18, 18);

        colorGLabel2.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel2.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel2Layout = new org.jdesktop.layout.GroupLayout(colorGLabel2);
        colorGLabel2.setLayout(colorGLabel2Layout);
        colorGLabel2Layout.setHorizontalGroup(
            colorGLabel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel2Layout.setVerticalGroup(
            colorGLabel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel2);
        colorGLabel2.setBounds(20, 40, 18, 18);

        Tdespl53.setEditable(false);
        Tdespl53.setFont(fuente);
        Tdespl53.setText("Color 1");
        Tdespl53.setToolTipText("");
        Tdespl53.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl53.setOpaque(false);
        jDesktopPane1.add(Tdespl53);
        Tdespl53.setBounds(40, 40, 88, 18);

        jButton27.setText("...");
        jButton27.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton27.setFocusable(false);
        jButton27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton27MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton27);
        jButton27.setBounds(0, 60, 18, 18);

        colorGLabel3.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel3.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel3Layout = new org.jdesktop.layout.GroupLayout(colorGLabel3);
        colorGLabel3.setLayout(colorGLabel3Layout);
        colorGLabel3Layout.setHorizontalGroup(
            colorGLabel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel3Layout.setVerticalGroup(
            colorGLabel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel3);
        colorGLabel3.setBounds(20, 60, 18, 18);

        Tdespl54.setEditable(false);
        Tdespl54.setFont(fuente);
        Tdespl54.setText("Color 2");
        Tdespl54.setToolTipText("");
        Tdespl54.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl54.setOpaque(false);
        jDesktopPane1.add(Tdespl54);
        Tdespl54.setBounds(40, 60, 88, 18);

        jButton28.setText("...");
        jButton28.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton28.setFocusable(false);
        jButton28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton28MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton28);
        jButton28.setBounds(0, 80, 18, 18);

        colorGLabel4.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel4.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel4Layout = new org.jdesktop.layout.GroupLayout(colorGLabel4);
        colorGLabel4.setLayout(colorGLabel4Layout);
        colorGLabel4Layout.setHorizontalGroup(
            colorGLabel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel4Layout.setVerticalGroup(
            colorGLabel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel4);
        colorGLabel4.setBounds(20, 80, 18, 18);

        Tdespl55.setEditable(false);
        Tdespl55.setFont(fuente);
        Tdespl55.setText("Color 3");
        Tdespl55.setToolTipText("");
        Tdespl55.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl55.setOpaque(false);
        jDesktopPane1.add(Tdespl55);
        Tdespl55.setBounds(40, 80, 88, 18);

        jButton29.setText("...");
        jButton29.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton29.setFocusable(false);
        jButton29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton29MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton29);
        jButton29.setBounds(0, 100, 18, 18);

        colorGLabel5.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel5.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel5Layout = new org.jdesktop.layout.GroupLayout(colorGLabel5);
        colorGLabel5.setLayout(colorGLabel5Layout);
        colorGLabel5Layout.setHorizontalGroup(
            colorGLabel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel5Layout.setVerticalGroup(
            colorGLabel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel5);
        colorGLabel5.setBounds(20, 100, 18, 18);

        Tdespl56.setEditable(false);
        Tdespl56.setFont(fuente);
        Tdespl56.setText("Color 4");
        Tdespl56.setToolTipText("");
        Tdespl56.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl56.setOpaque(false);
        jDesktopPane1.add(Tdespl56);
        Tdespl56.setBounds(40, 100, 88, 18);

        jButton30.setText("...");
        jButton30.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton30.setFocusable(false);
        jButton30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton30MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton30);
        jButton30.setBounds(0, 120, 18, 18);

        colorGLabel6.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel6.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel6Layout = new org.jdesktop.layout.GroupLayout(colorGLabel6);
        colorGLabel6.setLayout(colorGLabel6Layout);
        colorGLabel6Layout.setHorizontalGroup(
            colorGLabel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel6Layout.setVerticalGroup(
            colorGLabel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel6);
        colorGLabel6.setBounds(20, 120, 18, 18);

        Tdespl57.setEditable(false);
        Tdespl57.setFont(fuente);
        Tdespl57.setText("Color 5");
        Tdespl57.setToolTipText("");
        Tdespl57.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl57.setOpaque(false);
        jDesktopPane1.add(Tdespl57);
        Tdespl57.setBounds(40, 120, 88, 18);

        jButton31.setText("...");
        jButton31.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton31.setFocusable(false);
        jButton31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton31MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton31);
        jButton31.setBounds(0, 140, 18, 18);

        colorGLabel7.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel7.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel7Layout = new org.jdesktop.layout.GroupLayout(colorGLabel7);
        colorGLabel7.setLayout(colorGLabel7Layout);
        colorGLabel7Layout.setHorizontalGroup(
            colorGLabel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel7Layout.setVerticalGroup(
            colorGLabel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel7);
        colorGLabel7.setBounds(20, 140, 18, 18);

        Tdespl58.setEditable(false);
        Tdespl58.setFont(fuente);
        Tdespl58.setText("Color 6");
        Tdespl58.setToolTipText("");
        Tdespl58.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl58.setOpaque(false);
        jDesktopPane1.add(Tdespl58);
        Tdespl58.setBounds(40, 140, 88, 18);

        jButton32.setText("...");
        jButton32.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton32.setFocusable(false);
        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton32MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton32);
        jButton32.setBounds(0, 160, 18, 18);

        colorGLabel8.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel8.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel8Layout = new org.jdesktop.layout.GroupLayout(colorGLabel8);
        colorGLabel8.setLayout(colorGLabel8Layout);
        colorGLabel8Layout.setHorizontalGroup(
            colorGLabel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel8Layout.setVerticalGroup(
            colorGLabel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel8);
        colorGLabel8.setBounds(20, 160, 18, 18);

        Tdespl59.setEditable(false);
        Tdespl59.setFont(fuente);
        Tdespl59.setText("Color 7");
        Tdespl59.setToolTipText("");
        Tdespl59.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl59.setOpaque(false);
        jDesktopPane1.add(Tdespl59);
        Tdespl59.setBounds(40, 160, 88, 18);

        jButton33.setText("...");
        jButton33.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton33.setFocusable(false);
        jButton33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton33MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton33);
        jButton33.setBounds(0, 180, 18, 18);

        colorGLabel9.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel9.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel9Layout = new org.jdesktop.layout.GroupLayout(colorGLabel9);
        colorGLabel9.setLayout(colorGLabel9Layout);
        colorGLabel9Layout.setHorizontalGroup(
            colorGLabel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel9Layout.setVerticalGroup(
            colorGLabel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel9);
        colorGLabel9.setBounds(20, 180, 18, 18);

        Tdespl60.setEditable(false);
        Tdespl60.setFont(fuente);
        Tdespl60.setText("Color 8");
        Tdespl60.setToolTipText("");
        Tdespl60.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl60.setOpaque(false);
        jDesktopPane1.add(Tdespl60);
        Tdespl60.setBounds(40, 180, 88, 18);

        jButton34.setText("...");
        jButton34.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton34.setFocusable(false);
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton34MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton34);
        jButton34.setBounds(0, 200, 18, 18);

        colorGLabel10.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel10.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel10Layout = new org.jdesktop.layout.GroupLayout(colorGLabel10);
        colorGLabel10.setLayout(colorGLabel10Layout);
        colorGLabel10Layout.setHorizontalGroup(
            colorGLabel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel10Layout.setVerticalGroup(
            colorGLabel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel10);
        colorGLabel10.setBounds(20, 200, 18, 18);

        Tdespl61.setEditable(false);
        Tdespl61.setFont(fuente);
        Tdespl61.setText("Color 9");
        Tdespl61.setToolTipText("");
        Tdespl61.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl61.setOpaque(false);
        jDesktopPane1.add(Tdespl61);
        Tdespl61.setBounds(40, 200, 88, 18);

        jButton35.setText("...");
        jButton35.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton35.setFocusable(false);
        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton35MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton35);
        jButton35.setBounds(0, 220, 18, 18);

        colorGLabel11.setBackground(new java.awt.Color(255, 51, 51));
        colorGLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorGLabel11.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorGLabel11Layout = new org.jdesktop.layout.GroupLayout(colorGLabel11);
        colorGLabel11.setLayout(colorGLabel11Layout);
        colorGLabel11Layout.setHorizontalGroup(
            colorGLabel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorGLabel11Layout.setVerticalGroup(
            colorGLabel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorGLabel11);
        colorGLabel11.setBounds(20, 220, 18, 18);

        Tdespl62.setEditable(false);
        Tdespl62.setFont(fuente);
        Tdespl62.setText("Color 10");
        Tdespl62.setToolTipText("");
        Tdespl62.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl62.setOpaque(false);
        jDesktopPane1.add(Tdespl62);
        Tdespl62.setBounds(40, 220, 88, 18);

        Tdespl64.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl64.setEditable(false);
        Tdespl64.setFont(fuente);
        Tdespl64.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl64.setText("Width");
        Tdespl64.setToolTipText("");
        Tdespl64.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl64.setFocusable(false);
        jDesktopPane1.add(Tdespl64);
        Tdespl64.setBounds(172, 0, 40, 18);

        ancho1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho1);
        ancho1.setBounds(172, 20, 40, 18);

        ancho2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho2);
        ancho2.setBounds(172, 40, 40, 18);

        ancho3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho3);
        ancho3.setBounds(172, 60, 40, 18);

        ancho4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho4);
        ancho4.setBounds(172, 80, 40, 18);

        ancho5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho5);
        ancho5.setBounds(172, 100, 40, 18);

        ancho6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho6);
        ancho6.setBounds(172, 120, 40, 18);

        ancho7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho7);
        ancho7.setBounds(172, 140, 40, 18);

        ancho8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho8);
        ancho8.setBounds(172, 160, 40, 18);

        ancho9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho9);
        ancho9.setBounds(172, 180, 40, 18);

        ancho10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho10.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho10);
        ancho10.setBounds(172, 200, 40, 18);

        ancho11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(ancho11);
        ancho11.setBounds(172, 220, 40, 18);

        Tdespl65.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl65.setEditable(false);
        Tdespl65.setFont(fuente);
        Tdespl65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl65.setText("Line");
        Tdespl65.setToolTipText("");
        Tdespl65.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl65.setFocusable(false);
        jDesktopPane1.add(Tdespl65);
        Tdespl65.setBounds(130, 0, 40, 18);

        Tdespl66.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl66.setEditable(false);
        Tdespl66.setFont(fuente);
        Tdespl66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl66.setText("Circle");
        Tdespl66.setToolTipText("");
        Tdespl66.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl66.setFocusable(false);
        jDesktopPane1.add(Tdespl66);
        Tdespl66.setBounds(214, 0, 40, 18);

        seeL1.setBackground(new java.awt.Color(255, 255, 255));
        seeL1.setFont(fuente);
        seeL1.setSelected(true);
        seeL1.setToolTipText("");
        seeL1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL1.setBorderPainted(true);
        seeL1.setContentAreaFilled(false);
        seeL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL1MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL1);
        seeL1.setBounds(130, 20, 40, 18);

        seeL2.setBackground(new java.awt.Color(255, 255, 255));
        seeL2.setFont(fuente);
        seeL2.setSelected(true);
        seeL2.setToolTipText("");
        seeL2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL2.setBorderPainted(true);
        seeL2.setContentAreaFilled(false);
        seeL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL2MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL2);
        seeL2.setBounds(130, 40, 40, 18);

        seeL3.setBackground(new java.awt.Color(255, 255, 255));
        seeL3.setFont(fuente);
        seeL3.setSelected(true);
        seeL3.setToolTipText("");
        seeL3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL3.setBorderPainted(true);
        seeL3.setContentAreaFilled(false);
        seeL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL3MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL3);
        seeL3.setBounds(130, 60, 40, 18);

        seeL4.setBackground(new java.awt.Color(255, 255, 255));
        seeL4.setFont(fuente);
        seeL4.setSelected(true);
        seeL4.setToolTipText("");
        seeL4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL4.setBorderPainted(true);
        seeL4.setContentAreaFilled(false);
        seeL4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL4MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL4);
        seeL4.setBounds(130, 80, 40, 18);

        seeL5.setBackground(new java.awt.Color(255, 255, 255));
        seeL5.setFont(fuente);
        seeL5.setSelected(true);
        seeL5.setToolTipText("");
        seeL5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL5.setBorderPainted(true);
        seeL5.setContentAreaFilled(false);
        seeL5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeL5ActionPerformed(evt);
            }
        });
        seeL5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL5MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL5);
        seeL5.setBounds(130, 100, 40, 18);

        seeL6.setBackground(new java.awt.Color(255, 255, 255));
        seeL6.setFont(fuente);
        seeL6.setSelected(true);
        seeL6.setToolTipText("");
        seeL6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL6.setBorderPainted(true);
        seeL6.setContentAreaFilled(false);
        seeL6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL6MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL6);
        seeL6.setBounds(130, 120, 40, 18);

        seeL7.setBackground(new java.awt.Color(255, 255, 255));
        seeL7.setFont(fuente);
        seeL7.setSelected(true);
        seeL7.setToolTipText("");
        seeL7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL7.setBorderPainted(true);
        seeL7.setContentAreaFilled(false);
        seeL7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL7MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL7);
        seeL7.setBounds(130, 140, 40, 18);

        seeL8.setBackground(new java.awt.Color(255, 255, 255));
        seeL8.setFont(fuente);
        seeL8.setSelected(true);
        seeL8.setToolTipText("");
        seeL8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL8.setBorderPainted(true);
        seeL8.setContentAreaFilled(false);
        seeL8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL8MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL8);
        seeL8.setBounds(130, 160, 40, 18);

        seeL9.setBackground(new java.awt.Color(255, 255, 255));
        seeL9.setFont(fuente);
        seeL9.setSelected(true);
        seeL9.setToolTipText("");
        seeL9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL9.setBorderPainted(true);
        seeL9.setContentAreaFilled(false);
        seeL9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL9MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL9);
        seeL9.setBounds(130, 180, 40, 18);

        seeL10.setBackground(new java.awt.Color(255, 255, 255));
        seeL10.setFont(fuente);
        seeL10.setSelected(true);
        seeL10.setToolTipText("");
        seeL10.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL10.setBorderPainted(true);
        seeL10.setContentAreaFilled(false);
        seeL10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL10MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL10);
        seeL10.setBounds(130, 200, 40, 18);

        seeL11.setBackground(new java.awt.Color(255, 255, 255));
        seeL11.setFont(fuente);
        seeL11.setSelected(true);
        seeL11.setToolTipText("");
        seeL11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeL11.setBorderPainted(true);
        seeL11.setContentAreaFilled(false);
        seeL11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeL11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeL11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeL11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeL11MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeL11);
        seeL11.setBounds(130, 220, 40, 18);

        seeC1.setBackground(new java.awt.Color(255, 255, 255));
        seeC1.setFont(fuente);
        seeC1.setToolTipText("");
        seeC1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC1.setBorderPainted(true);
        seeC1.setContentAreaFilled(false);
        seeC1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC1MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC1);
        seeC1.setBounds(214, 20, 40, 18);

        seeC2.setBackground(new java.awt.Color(255, 255, 255));
        seeC2.setFont(fuente);
        seeC2.setToolTipText("");
        seeC2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC2.setBorderPainted(true);
        seeC2.setContentAreaFilled(false);
        seeC2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC2MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC2);
        seeC2.setBounds(214, 40, 40, 18);

        seeC3.setBackground(new java.awt.Color(255, 255, 255));
        seeC3.setFont(fuente);
        seeC3.setToolTipText("");
        seeC3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC3.setBorderPainted(true);
        seeC3.setContentAreaFilled(false);
        seeC3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC3MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC3);
        seeC3.setBounds(214, 60, 40, 18);

        seeC4.setBackground(new java.awt.Color(255, 255, 255));
        seeC4.setFont(fuente);
        seeC4.setToolTipText("");
        seeC4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC4.setBorderPainted(true);
        seeC4.setContentAreaFilled(false);
        seeC4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC4MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC4);
        seeC4.setBounds(214, 80, 40, 18);

        seeC5.setBackground(new java.awt.Color(255, 255, 255));
        seeC5.setFont(fuente);
        seeC5.setToolTipText("");
        seeC5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC5.setBorderPainted(true);
        seeC5.setContentAreaFilled(false);
        seeC5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeC5ActionPerformed(evt);
            }
        });
        seeC5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC5MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC5);
        seeC5.setBounds(214, 100, 40, 18);

        seeC6.setBackground(new java.awt.Color(255, 255, 255));
        seeC6.setFont(fuente);
        seeC6.setToolTipText("");
        seeC6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC6.setBorderPainted(true);
        seeC6.setContentAreaFilled(false);
        seeC6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC6MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC6);
        seeC6.setBounds(214, 120, 40, 18);

        seeC7.setBackground(new java.awt.Color(255, 255, 255));
        seeC7.setFont(fuente);
        seeC7.setToolTipText("");
        seeC7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC7.setBorderPainted(true);
        seeC7.setContentAreaFilled(false);
        seeC7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC7MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC7);
        seeC7.setBounds(214, 140, 40, 18);

        seeC8.setBackground(new java.awt.Color(255, 255, 255));
        seeC8.setFont(fuente);
        seeC8.setToolTipText("");
        seeC8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC8.setBorderPainted(true);
        seeC8.setContentAreaFilled(false);
        seeC8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC8MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC8);
        seeC8.setBounds(214, 160, 40, 18);

        seeC9.setBackground(new java.awt.Color(255, 255, 255));
        seeC9.setFont(fuente);
        seeC9.setToolTipText("");
        seeC9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC9.setBorderPainted(true);
        seeC9.setContentAreaFilled(false);
        seeC9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC9MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC9);
        seeC9.setBounds(214, 180, 40, 18);

        seeC10.setBackground(new java.awt.Color(255, 255, 255));
        seeC10.setFont(fuente);
        seeC10.setToolTipText("");
        seeC10.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC10.setBorderPainted(true);
        seeC10.setContentAreaFilled(false);
        seeC10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC10MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC10);
        seeC10.setBounds(214, 200, 40, 18);

        seeC11.setBackground(new java.awt.Color(255, 255, 255));
        seeC11.setFont(fuente);
        seeC11.setToolTipText("");
        seeC11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeC11.setBorderPainted(true);
        seeC11.setContentAreaFilled(false);
        seeC11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeC11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeC11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeC11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeC11MouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeC11);
        seeC11.setBounds(214, 220, 40, 18);

        Tdespl67.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl67.setEditable(false);
        Tdespl67.setFont(fuente);
        Tdespl67.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl67.setText("Width");
        Tdespl67.setToolTipText("");
        Tdespl67.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl67.setFocusable(false);
        jDesktopPane1.add(Tdespl67);
        Tdespl67.setBounds(256, 0, 40, 18);

        anchoC1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC1);
        anchoC1.setBounds(256, 20, 40, 18);

        anchoC2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC2);
        anchoC2.setBounds(256, 40, 40, 18);

        anchoC3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC3);
        anchoC3.setBounds(256, 60, 40, 18);

        anchoC4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC4);
        anchoC4.setBounds(256, 80, 40, 18);

        anchoC5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC5);
        anchoC5.setBounds(256, 100, 40, 18);

        anchoC6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC6);
        anchoC6.setBounds(256, 120, 40, 18);

        anchoC7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC7);
        anchoC7.setBounds(256, 140, 40, 18);

        anchoC8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC8);
        anchoC8.setBounds(256, 160, 40, 18);

        anchoC9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC9);
        anchoC9.setBounds(256, 180, 40, 18);

        anchoC10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC10.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC10);
        anchoC10.setBounds(256, 200, 40, 18);

        anchoC11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        anchoC11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane1.add(anchoC11);
        anchoC11.setBounds(256, 220, 40, 18);

        jDesktopPane3.add(jDesktopPane1);
        jDesktopPane1.setBounds(2, 260, 300, 240);

        Tdespl36.setEditable(false);
        Tdespl36.setFont(fuente);
        Tdespl36.setText("  Auto adjust (X)");
        Tdespl36.setToolTipText("");
        Tdespl36.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl36.setOpaque(false);
        jDesktopPane2.add(Tdespl36);
        Tdespl36.setBounds(0, 0, 120, 18);

        Tdespl33.setEditable(false);
        Tdespl33.setFont(fuente);
        Tdespl33.setText("  Xmin ");
        Tdespl33.setToolTipText("");
        Tdespl33.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl33.setOpaque(false);
        jDesktopPane2.add(Tdespl33);
        Tdespl33.setBounds(0, 20, 120, 18);

        Tdespl34.setEditable(false);
        Tdespl34.setFont(fuente);
        Tdespl34.setText("  Xmax ");
        Tdespl34.setToolTipText("");
        Tdespl34.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl34.setOpaque(false);
        jDesktopPane2.add(Tdespl34);
        Tdespl34.setBounds(0, 40, 120, 18);

        Tdespl35.setEditable(false);
        Tdespl35.setFont(fuente);
        Tdespl35.setText("  n. divisions (X)");
        Tdespl35.setToolTipText("");
        Tdespl35.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl35.setOpaque(false);
        jDesktopPane2.add(Tdespl35);
        Tdespl35.setBounds(0, 60, 120, 18);

        Tdespl44.setEditable(false);
        Tdespl44.setFont(fuente);
        Tdespl44.setText("  n. digist (X)");
        Tdespl44.setToolTipText("");
        Tdespl44.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl44.setOpaque(false);
        jDesktopPane2.add(Tdespl44);
        Tdespl44.setBounds(0, 80, 120, 18);

        nDigX.setFont(fuente);
        nDigX.setToolTipText("");
        nDigX.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(nDigX);
        nDigX.setBounds(122, 80, 58, 18);

        nDX.setFont(fuente);
        nDX.setToolTipText("");
        nDX.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(nDX);
        nDX.setBounds(122, 60, 58, 18);

        Xmax.setEditable(false);
        Xmax.setFont(fuente);
        Xmax.setToolTipText("");
        Xmax.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Xmax.setEnabled(false);
        jDesktopPane2.add(Xmax);
        Xmax.setBounds(122, 40, 58, 18);

        Xmin.setEditable(false);
        Xmin.setFont(fuente);
        Xmin.setToolTipText("");
        Xmin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Xmin.setEnabled(false);
        jDesktopPane2.add(Xmin);
        Xmin.setBounds(122, 20, 58, 18);

        adjustX.setBackground(new java.awt.Color(255, 255, 255));
        adjustX.setFont(fuente);
        adjustX.setSelected(true);
        adjustX.setToolTipText("");
        adjustX.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        adjustX.setBorderPainted(true);
        adjustX.setContentAreaFilled(false);
        adjustX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adjustX.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adjustX.setMargin(new java.awt.Insets(0, 0, 0, 0));
        adjustX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adjustXMouseClicked(evt);
            }
        });
        jDesktopPane2.add(adjustX);
        adjustX.setBounds(122, 0, 58, 18);

        Tdespl42.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl42.setEditable(false);
        Tdespl42.setFont(fuente);
        Tdespl42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl42.setToolTipText("");
        Tdespl42.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl42.setFocusable(false);
        jDesktopPane2.add(Tdespl42);
        Tdespl42.setBounds(182, 0, 22, 98);

        Tdespl39.setEditable(false);
        Tdespl39.setFont(fuente);
        Tdespl39.setText("  Auto adjust (Y)");
        Tdespl39.setToolTipText("");
        Tdespl39.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl39.setOpaque(false);
        jDesktopPane2.add(Tdespl39);
        Tdespl39.setBounds(206, 0, 120, 18);

        Tdespl40.setEditable(false);
        Tdespl40.setFont(fuente);
        Tdespl40.setText("  Ymin");
        Tdespl40.setToolTipText("");
        Tdespl40.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl40.setOpaque(false);
        jDesktopPane2.add(Tdespl40);
        Tdespl40.setBounds(206, 20, 120, 18);

        Ymin.setEditable(false);
        Ymin.setFont(fuente);
        Ymin.setToolTipText("");
        Ymin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Ymin.setEnabled(false);
        jDesktopPane2.add(Ymin);
        Ymin.setBounds(328, 20, 58, 18);

        adjustY.setBackground(new java.awt.Color(255, 255, 255));
        adjustY.setFont(fuente);
        adjustY.setSelected(true);
        adjustY.setToolTipText("");
        adjustY.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        adjustY.setBorderPainted(true);
        adjustY.setContentAreaFilled(false);
        adjustY.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adjustY.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adjustY.setMargin(new java.awt.Insets(0, 0, 0, 0));
        adjustY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adjustYMouseClicked(evt);
            }
        });
        jDesktopPane2.add(adjustY);
        adjustY.setBounds(328, 0, 58, 18);

        Ymax.setEditable(false);
        Ymax.setFont(fuente);
        Ymax.setToolTipText("");
        Ymax.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Ymax.setEnabled(false);
        jDesktopPane2.add(Ymax);
        Ymax.setBounds(328, 40, 58, 18);

        Tdespl43.setEditable(false);
        Tdespl43.setFont(fuente);
        Tdespl43.setText("  Ymax ");
        Tdespl43.setToolTipText("");
        Tdespl43.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl43.setOpaque(false);
        jDesktopPane2.add(Tdespl43);
        Tdespl43.setBounds(206, 40, 120, 18);

        Tdespl45.setEditable(false);
        Tdespl45.setFont(fuente);
        Tdespl45.setText("  n. divisions (Y)");
        Tdespl45.setToolTipText("");
        Tdespl45.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl45.setOpaque(false);
        jDesktopPane2.add(Tdespl45);
        Tdespl45.setBounds(206, 60, 120, 18);

        nDY.setFont(fuente);
        nDY.setToolTipText("");
        nDY.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(nDY);
        nDY.setBounds(328, 60, 58, 18);

        nDigY.setFont(fuente);
        nDigY.setToolTipText("");
        nDigY.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(nDigY);
        nDigY.setBounds(328, 80, 58, 18);

        Tdesk.setEditable(false);
        Tdesk.setFont(fuente);
        Tdesk.setText("  n. digist (Y)");
        Tdesk.setToolTipText("");
        Tdesk.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdesk.setOpaque(false);
        jDesktopPane2.add(Tdesk);
        Tdesk.setBounds(206, 80, 120, 18);

        Tdespl38.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl38.setEditable(false);
        Tdespl38.setFont(fuente);
        Tdespl38.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl38.setText("Axis");
        Tdespl38.setToolTipText("");
        Tdespl38.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl38.setFocusable(false);
        jDesktopPane2.add(Tdespl38);
        Tdespl38.setBounds(0, 100, 386, 18);

        jButton20.setFont(fuente
        );
        jButton20.setText("...");
        jButton20.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton20.setFocusable(false);
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton20MousePressed(evt);
            }
        });
        jDesktopPane2.add(jButton20);
        jButton20.setBounds(0, 120, 18, 18);

        colorEjesLabel.setBackground(java.awt.Color.gray);
        colorEjesLabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorEjesLabel.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorEjesLabelLayout = new org.jdesktop.layout.GroupLayout(colorEjesLabel);
        colorEjesLabel.setLayout(colorEjesLabelLayout);
        colorEjesLabelLayout.setHorizontalGroup(
            colorEjesLabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorEjesLabelLayout.setVerticalGroup(
            colorEjesLabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane2.add(colorEjesLabel);
        colorEjesLabel.setBounds(20, 120, 18, 18);

        fontEjes.setText("tipo");
        fontEjes.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane2.add(fontEjes);
        fontEjes.setBounds(40, 120, 286, 18);

        fonTamEjes.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        fonTamEjes.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane2.add(fonTamEjes);
        fonTamEjes.setBounds(328, 120, 58, 18);

        jButton18.setText("...");
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton18.setFocusable(false);
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton18MousePressed(evt);
            }
        });
        jDesktopPane2.add(jButton18);
        jButton18.setBounds(0, 140, 18, 18);
        jButton18.getAccessibleContext().setAccessibleName("");

        colorFondoLabel.setBackground(new java.awt.Color(255, 255, 255));
        colorFondoLabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorFondoLabel.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorFondoLabelLayout = new org.jdesktop.layout.GroupLayout(colorFondoLabel);
        colorFondoLabel.setLayout(colorFondoLabelLayout);
        colorFondoLabelLayout.setHorizontalGroup(
            colorFondoLabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorFondoLabelLayout.setVerticalGroup(
            colorFondoLabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane2.add(colorFondoLabel);
        colorFondoLabel.setBounds(20, 140, 18, 18);
        colorFondoLabel.getAccessibleContext().setAccessibleName("");

        Tdespl37.setEditable(false);
        Tdespl37.setFont(fuente);
        Tdespl37.setText("background");
        Tdespl37.setToolTipText("");
        Tdespl37.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl37.setOpaque(false);
        jDesktopPane2.add(Tdespl37);
        Tdespl37.setBounds(40, 140, 346, 18);
        Tdespl37.getAccessibleContext().setAccessibleName("");

        Tdespl47.setEditable(false);
        Tdespl47.setFont(fuente);
        Tdespl47.setText("Leyenda");
        Tdespl47.setToolTipText("");
        Tdespl47.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl47.setOpaque(false);
        jDesktopPane2.add(Tdespl47);
        Tdespl47.setBounds(0, 160, 60, 18);

        seeIndexRight.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        seeIndexRight.setText("Right");
        seeIndexRight.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        seeIndexRight.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeIndexRight.setOpaque(false);
        jDesktopPane2.add(seeIndexRight);
        seeIndexRight.setBounds(60, 160, 50, 18);

        seeIndexDown.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        seeIndexDown.setText("Down");
        seeIndexDown.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        seeIndexDown.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeIndexDown.setOpaque(false);
        jDesktopPane2.add(seeIndexDown);
        seeIndexDown.setBounds(110, 160, 60, 18);

        fontLeyenda.setFont(fuente);
        fontLeyenda.setToolTipText("");
        fontLeyenda.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        fontLeyenda.setOpaque(false);
        jDesktopPane2.add(fontLeyenda);
        fontLeyenda.setBounds(172, 160, 154, 18);

        fonTamIndex.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        fonTamIndex.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane2.add(fonTamIndex);
        fonTamIndex.setBounds(328, 160, 58, 18);

        Tdespl46.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl46.setEditable(false);
        Tdespl46.setFont(fuente);
        Tdespl46.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl46.setText("Title");
        Tdespl46.setToolTipText("");
        Tdespl46.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl46.setFocusable(false);
        jDesktopPane2.add(Tdespl46);
        Tdespl46.setBounds(0, 180, 386, 18);

        fonTamTitulo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        fonTamTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane2.add(fonTamTitulo);
        fonTamTitulo.setBounds(328, 200, 58, 18);

        Titulo.setFont(fuente);
        Titulo.setText("Title");
        Titulo.setToolTipText("");
        Titulo.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Titulo.setOpaque(false);
        jDesktopPane2.add(Titulo);
        Titulo.setBounds(0, 220, 386, 18);

        fontTitulo.setFont(fuente);
        fontTitulo.setToolTipText("");
        fontTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        fontTitulo.setOpaque(false);
        jDesktopPane2.add(fontTitulo);
        fontTitulo.setBounds(60, 200, 266, 18);

        colorTituloLabel.setBackground(new java.awt.Color(51, 51, 51));
        colorTituloLabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorTituloLabel.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorTituloLabelLayout = new org.jdesktop.layout.GroupLayout(colorTituloLabel);
        colorTituloLabel.setLayout(colorTituloLabelLayout);
        colorTituloLabelLayout.setHorizontalGroup(
            colorTituloLabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorTituloLabelLayout.setVerticalGroup(
            colorTituloLabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane2.add(colorTituloLabel);
        colorTituloLabel.setBounds(40, 200, 18, 18);

        jButton22.setText("...");
        jButton22.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton22.setFocusable(false);
        jButton22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton22MousePressed(evt);
            }
        });
        jDesktopPane2.add(jButton22);
        jButton22.setBounds(20, 200, 18, 18);

        seeTitle.setBackground(new java.awt.Color(255, 255, 255));
        seeTitle.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        seeTitle.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        seeTitle.setBorderPainted(true);
        seeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeTitle.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane2.add(seeTitle);
        seeTitle.setBounds(0, 200, 18, 18);

        testString.setFont(fuente);
        testString.setText("1 2 insert this in x=1,y=2 ");
        testString.setToolTipText("");
        testString.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        testString.setOpaque(false);
        jDesktopPane2.add(testString);
        testString.setBounds(0, 240, 274, 18);

        jButton37.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton37.setText("insert String");
        jButton37.setToolTipText("");
        jButton37.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton37.setFocusable(false);
        jButton37.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton37MousePressed(evt);
            }
        });
        jDesktopPane2.add(jButton37);
        jButton37.setBounds(276, 240, 110, 18);

        jDesktopPane3.add(jDesktopPane2);
        jDesktopPane2.setBounds(2, 0, 388, 260);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton37MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MousePressed
        mens.add(testString.getText());
    }//GEN-LAST:event_jButton37MousePressed
    
    private void seeC11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC11MouseClicked
        load();
    }//GEN-LAST:event_seeC11MouseClicked
    
    private void seeC10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC10MouseClicked
        load();
    }//GEN-LAST:event_seeC10MouseClicked
    
    private void seeC9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC9MouseClicked
        load();
    }//GEN-LAST:event_seeC9MouseClicked
    
    private void seeC8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC8MouseClicked
        load();
    }//GEN-LAST:event_seeC8MouseClicked
    
    private void seeC7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC7MouseClicked
        load();
    }//GEN-LAST:event_seeC7MouseClicked
    
    private void seeC6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC6MouseClicked
        load();
    }//GEN-LAST:event_seeC6MouseClicked
    
    private void seeC5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC5MouseClicked
        load();
    }//GEN-LAST:event_seeC5MouseClicked
    
    private void seeC5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeC5ActionPerformed
        load();
    }//GEN-LAST:event_seeC5ActionPerformed
    
    private void seeC4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC4MouseClicked
        load();
    }//GEN-LAST:event_seeC4MouseClicked
    
    private void seeC3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC3MouseClicked
        load();
    }//GEN-LAST:event_seeC3MouseClicked
    
    private void seeC2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC2MouseClicked
        load();
    }//GEN-LAST:event_seeC2MouseClicked
    
    private void seeC1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeC1MouseClicked
        load();
    }//GEN-LAST:event_seeC1MouseClicked
    
    private void seeL5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeL5ActionPerformed
        load();
    }//GEN-LAST:event_seeL5ActionPerformed
    
    private void seeL11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL11MouseClicked
        load();
    }//GEN-LAST:event_seeL11MouseClicked
    
    private void seeL10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL10MouseClicked
        load();
    }//GEN-LAST:event_seeL10MouseClicked
    
    private void seeL9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL9MouseClicked
        load();
    }//GEN-LAST:event_seeL9MouseClicked
    
    private void seeL8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL8MouseClicked
        load();
    }//GEN-LAST:event_seeL8MouseClicked
    
    private void seeL7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL7MouseClicked
        load();
    }//GEN-LAST:event_seeL7MouseClicked
    
    private void seeL6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL6MouseClicked
        load();
    }//GEN-LAST:event_seeL6MouseClicked
    
    private void seeL5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL5MouseClicked
        load();
    }//GEN-LAST:event_seeL5MouseClicked
    
    private void seeL4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL4MouseClicked
        load();
    }//GEN-LAST:event_seeL4MouseClicked
    
    private void seeL3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL3MouseClicked
        load();
    }//GEN-LAST:event_seeL3MouseClicked
    
    private void seeL2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL2MouseClicked
        load();
    }//GEN-LAST:event_seeL2MouseClicked
    
    private void seeL1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeL1MouseClicked
        load();
    }//GEN-LAST:event_seeL1MouseClicked
    
    private void jButton35MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MousePressed
        colorGLabel11.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel11.getBackground()));
        cadena.C[11]=colorGLabel11.getBackground().getRGB();
    }//GEN-LAST:event_jButton35MousePressed
    
    private void jButton34MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MousePressed
        colorGLabel10.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel10.getBackground()));
        cadena.C[10]=colorGLabel10.getBackground().getRGB();
    }//GEN-LAST:event_jButton34MousePressed
    
    private void jButton33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton33MousePressed
        colorGLabel9.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel9.getBackground()));
        cadena.C[9]=colorGLabel9.getBackground().getRGB();
    }//GEN-LAST:event_jButton33MousePressed
    
    private void jButton32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MousePressed
        colorGLabel8.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel8.getBackground()));
        cadena.C[8]=colorGLabel8.getBackground().getRGB();
    }//GEN-LAST:event_jButton32MousePressed
    
    private void jButton31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton31MousePressed
        colorGLabel7.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel7.getBackground()));
        cadena.C[7]=colorGLabel7.getBackground().getRGB();
    }//GEN-LAST:event_jButton31MousePressed
    
    private void jButton30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MousePressed
        colorGLabel6.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel6.getBackground()));
        cadena.C[6]=colorGLabel6.getBackground().getRGB();
    }//GEN-LAST:event_jButton30MousePressed
    
    private void jButton29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MousePressed
        colorGLabel5.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel5.getBackground()));
        cadena.C[5]=colorGLabel5.getBackground().getRGB();
    }//GEN-LAST:event_jButton29MousePressed
    
    private void jButton28MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton28MousePressed
        colorGLabel4.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel4.getBackground()));
        cadena.C[4]=colorGLabel4.getBackground().getRGB();
    }//GEN-LAST:event_jButton28MousePressed
    
    private void jButton27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MousePressed
        colorGLabel3.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel3.getBackground()));
        cadena.C[3]=colorGLabel3.getBackground().getRGB();
    }//GEN-LAST:event_jButton27MousePressed
    
    private void jButton25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton25MousePressed
        colorGLabel2.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel2.getBackground()));
        cadena.C[2]=colorGLabel2.getBackground().getRGB();
    }//GEN-LAST:event_jButton25MousePressed
    
    private void jButton24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton24MousePressed
        colorGLabel1.setBackground( JColorChooser.showDialog( this , "axis color", colorGLabel1.getBackground()));
        cadena.C[1]=colorGLabel1.getBackground().getRGB();
    }//GEN-LAST:event_jButton24MousePressed
    int v=0;
    private void adjustYMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adjustYMouseClicked
        Editar();
        load();
    }//GEN-LAST:event_adjustYMouseClicked
    
    private void adjustXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adjustXMouseClicked
        Editar();
        load();
    }//GEN-LAST:event_adjustXMouseClicked
    
    private void jButton26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MousePressed
        load();
    }//GEN-LAST:event_jButton26MousePressed
    
    
    double auxMax;
    private void jButton22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MousePressed
        colorTituloLabel.setBackground( JColorChooser.showDialog( this , "axis color", colorTituloLabel.getBackground()));
        colorTitulo=colorTituloLabel.getBackground();
    }//GEN-LAST:event_jButton22MousePressed
    
    private void jButton20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MousePressed
        colorEjesLabel.setBackground( JColorChooser.showDialog( this , "axis color", colorEjesLabel.getBackground()));
        colorEjes=colorEjesLabel.getBackground();
        // System.out.println(colorEjes.getRGB());
    }//GEN-LAST:event_jButton20MousePressed
    
    private void jButton18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MousePressed
        colorFondoLabel.setBackground( JColorChooser.showDialog( this , "background color", colorFondoLabel.getBackground()));
        colorFondo=colorFondoLabel.getBackground();
    }//GEN-LAST:event_jButton18MousePressed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Tdesk;
    private javax.swing.JTextField Tdespl33;
    private javax.swing.JTextField Tdespl34;
    private javax.swing.JTextField Tdespl35;
    private javax.swing.JTextField Tdespl36;
    private javax.swing.JTextField Tdespl37;
    private javax.swing.JTextField Tdespl38;
    private javax.swing.JTextField Tdespl39;
    private javax.swing.JTextField Tdespl40;
    private javax.swing.JTextField Tdespl42;
    private javax.swing.JTextField Tdespl43;
    private javax.swing.JTextField Tdespl44;
    private javax.swing.JTextField Tdespl45;
    private javax.swing.JTextField Tdespl46;
    private javax.swing.JTextField Tdespl47;
    private javax.swing.JTextField Tdespl51;
    private javax.swing.JTextField Tdespl52;
    private javax.swing.JTextField Tdespl53;
    private javax.swing.JTextField Tdespl54;
    private javax.swing.JTextField Tdespl55;
    private javax.swing.JTextField Tdespl56;
    private javax.swing.JTextField Tdespl57;
    private javax.swing.JTextField Tdespl58;
    private javax.swing.JTextField Tdespl59;
    private javax.swing.JTextField Tdespl60;
    private javax.swing.JTextField Tdespl61;
    private javax.swing.JTextField Tdespl62;
    private javax.swing.JTextField Tdespl64;
    private javax.swing.JTextField Tdespl65;
    private javax.swing.JTextField Tdespl66;
    private javax.swing.JTextField Tdespl67;
    private javax.swing.JTextField Titulo;
    private javax.swing.JTextField Xmax;
    private javax.swing.JTextField Xmin;
    private javax.swing.JTextField Ymax;
    private javax.swing.JTextField Ymin;
    private javax.swing.JCheckBox adjustX;
    private javax.swing.JCheckBox adjustY;
    private javax.swing.JSpinner ancho1;
    private javax.swing.JSpinner ancho10;
    private javax.swing.JSpinner ancho11;
    private javax.swing.JSpinner ancho2;
    private javax.swing.JSpinner ancho3;
    private javax.swing.JSpinner ancho4;
    private javax.swing.JSpinner ancho5;
    private javax.swing.JSpinner ancho6;
    private javax.swing.JSpinner ancho7;
    private javax.swing.JSpinner ancho8;
    private javax.swing.JSpinner ancho9;
    private javax.swing.JSpinner anchoC1;
    private javax.swing.JSpinner anchoC10;
    private javax.swing.JSpinner anchoC11;
    private javax.swing.JSpinner anchoC2;
    private javax.swing.JSpinner anchoC3;
    private javax.swing.JSpinner anchoC4;
    private javax.swing.JSpinner anchoC5;
    private javax.swing.JSpinner anchoC6;
    private javax.swing.JSpinner anchoC7;
    private javax.swing.JSpinner anchoC8;
    private javax.swing.JSpinner anchoC9;
    private javax.swing.JPanel colorEjesLabel;
    private javax.swing.JPanel colorFondoLabel;
    private javax.swing.JPanel colorGLabel1;
    private javax.swing.JPanel colorGLabel10;
    private javax.swing.JPanel colorGLabel11;
    private javax.swing.JPanel colorGLabel2;
    private javax.swing.JPanel colorGLabel3;
    private javax.swing.JPanel colorGLabel4;
    private javax.swing.JPanel colorGLabel5;
    private javax.swing.JPanel colorGLabel6;
    private javax.swing.JPanel colorGLabel7;
    private javax.swing.JPanel colorGLabel8;
    private javax.swing.JPanel colorGLabel9;
    private javax.swing.JPanel colorTituloLabel;
    private javax.swing.JSpinner fonTamEjes;
    private javax.swing.JSpinner fonTamIndex;
    private javax.swing.JSpinner fonTamTitulo;
    private javax.swing.JTextField fontEjes;
    private javax.swing.JTextField fontLeyenda;
    private javax.swing.JTextField fontTitulo;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton37;
    private javax.swing.JColorChooser jColorChooser;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JTextField nDX;
    private javax.swing.JTextField nDY;
    private javax.swing.JTextField nDigX;
    private javax.swing.JTextField nDigY;
    private javax.swing.JCheckBox seeC1;
    private javax.swing.JCheckBox seeC10;
    private javax.swing.JCheckBox seeC11;
    private javax.swing.JCheckBox seeC2;
    private javax.swing.JCheckBox seeC3;
    private javax.swing.JCheckBox seeC4;
    private javax.swing.JCheckBox seeC5;
    private javax.swing.JCheckBox seeC6;
    private javax.swing.JCheckBox seeC7;
    private javax.swing.JCheckBox seeC8;
    private javax.swing.JCheckBox seeC9;
    private javax.swing.JCheckBox seeIndexDown;
    private javax.swing.JCheckBox seeIndexRight;
    private javax.swing.JCheckBox seeL1;
    private javax.swing.JCheckBox seeL10;
    private javax.swing.JCheckBox seeL11;
    private javax.swing.JCheckBox seeL2;
    private javax.swing.JCheckBox seeL3;
    private javax.swing.JCheckBox seeL4;
    private javax.swing.JCheckBox seeL5;
    private javax.swing.JCheckBox seeL6;
    private javax.swing.JCheckBox seeL7;
    private javax.swing.JCheckBox seeL8;
    private javax.swing.JCheckBox seeL9;
    private javax.swing.JCheckBox seeTitle;
    private javax.swing.JTextField testString;
    // End of variables declaration//GEN-END:variables
    
    
    public void ini(){
        colorFondoLabel.setBackground(colorFondo);
        colorEjesLabel.setBackground(colorEjes);
        Xmax.setText(x_max+"");
        Xmin.setText(x_min+"");
        Ymax.setText(y_max+"");
        Ymin.setText(y_min+"");
        fontEjes.setText(currentEjes.getFontName());
        fonTamEjes.setValue(currentEjes.getSize());
        fontTitulo.setText(currentTitle.getFontName());
        fonTamTitulo.setValue(currentTitle.getSize());
        fonTamIndex.setValue(currentIndex.getSize());
        fontLeyenda.setText(currentIndex.getFontName());
        seeIndexDown.setSelected(indexDown);
        seeIndexRight.setSelected(indexRight);
        seeTitle.setSelected(showTitle);
        Titulo.setText(Title);
        nDX.setText(Aox+"");
        nDY.setText(Aoy+"");
        nDigX.setText(nfx+"");
        nDigY.setText(nfy+"");
    }
    
    void Editar(){
        ajustarOptionsX=adjustX.isSelected();
        ajustarOptionsY=adjustY.isSelected();
        Xmax.setEditable(!ajustarOptionsX);
        Xmin.setEditable(!ajustarOptionsX);
        Xmax.setEnabled(!ajustarOptionsX);
        Xmin.setEnabled(!ajustarOptionsX);
        Ymax.setEditable(!ajustarOptionsY);
        Ymax.setEnabled(!ajustarOptionsY);
        Ymin.setEnabled(!ajustarOptionsY);
        Ymin.setEditable(!ajustarOptionsY);
    }
    
    
    public void load(){
        //el color no hace fasta
        ajustarOptionsX=adjustX.isSelected();
        if(!ajustarOptionsX){
            x_max=Double.valueOf(Xmax.getText()).doubleValue();
            x_min=Double.valueOf(Xmin.getText()).doubleValue();
        }
        Aox=(int) Double.valueOf(nDX.getText()).doubleValue();
        nfx=(int) Double.valueOf(nDigX.getText()).doubleValue();
        
        ajustarOptionsY=adjustY.isSelected();
        if(!ajustarOptionsY){
            y_max=Double.valueOf(Ymax.getText()).doubleValue();
            y_min=Double.valueOf(Ymin.getText()).doubleValue();
        }
        Aoy=(int) Double.valueOf(nDY.getText()).doubleValue();
        nfy=(int) Double.valueOf(nDigY.getText()).doubleValue();
        
        currentEjes = new Font(fontEjes.getText(),Font.PLAIN,fonTamEjes.getValue().hashCode());
        currentTitle = new Font(fontTitulo.getText(),Font.PLAIN,fonTamTitulo.getValue().hashCode());
        currentIndex = new Font(fontLeyenda.getText(),Font.PLAIN,fonTamIndex.getValue().hashCode());
        indexDown=seeIndexDown.isSelected();
        indexRight=seeIndexRight.isSelected();
        showTitle=seeTitle.isSelected();
        ancho[1] = ancho1.getValue().hashCode();
        ancho[2] = ancho2.getValue().hashCode();
        ancho[3] = ancho3.getValue().hashCode();
        ancho[4] = ancho4.getValue().hashCode();
        ancho[5] = ancho5.getValue().hashCode();
        ancho[6] = ancho6.getValue().hashCode();
        ancho[7] = ancho7.getValue().hashCode();
        ancho[8] = ancho8.getValue().hashCode();
        ancho[9] = ancho9.getValue().hashCode();
        ancho[10] = ancho10.getValue().hashCode();
        ancho[11] = ancho11.getValue().hashCode();
        
        anchoC[1] = anchoC1.getValue().hashCode();
        anchoC[2] = anchoC2.getValue().hashCode();
        anchoC[3] = anchoC3.getValue().hashCode();
        anchoC[4] = anchoC4.getValue().hashCode();
        anchoC[5] = anchoC5.getValue().hashCode();
        anchoC[6] = anchoC6.getValue().hashCode();
        anchoC[7] = anchoC7.getValue().hashCode();
        anchoC[8] = anchoC8.getValue().hashCode();
        anchoC[9] = anchoC9.getValue().hashCode();
        anchoC[10] = anchoC10.getValue().hashCode();
        anchoC[11] = anchoC11.getValue().hashCode();
        
        verLine[1] =seeL1.isSelected();
        verLine[2] =seeL2.isSelected();
        verLine[3] =seeL3.isSelected();
        verLine[4] =seeL4.isSelected();
        verLine[5] =seeL5.isSelected();
        verLine[6] =seeL6.isSelected();
        verLine[7] =seeL7.isSelected();
        verLine[8] =seeL8.isSelected();
        verLine[9] =seeL9.isSelected();
        verLine[10] =seeL10.isSelected();
        verLine[11] =seeL11.isSelected();
        
        verCircle[1] =seeC1.isSelected();
        verCircle[2] =seeC2.isSelected();
        verCircle[3] =seeC3.isSelected();
        verCircle[4] =seeC4.isSelected();
        verCircle[5] =seeC5.isSelected();
        verCircle[6] =seeC6.isSelected();
        verCircle[7] =seeC7.isSelected();
        verCircle[8] =seeC8.isSelected();
        verCircle[9] =seeC9.isSelected();
        verCircle[10] =seeC10.isSelected();
        verCircle[11] =seeC11.isSelected();
        
        
        if(Title!=null)Title=Titulo.getText();
        else {
            Title="Title";
            Titulo.setText(Title);
        }
    }
    
}
