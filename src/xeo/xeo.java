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

package xeo;

import calc_xyz.JCalc_xyz;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.AWTEvent;
//-----------------------
import fireball.fireball;
import pintar2D.pintar2D;
import reader.reader;
import java_STM_AFM.java_STM_AFM;
import editor.editor;
import calculadora.calculadora;
import jCalc.jCalc;
import java.awt.event.MouseEvent;
import language.language;
import xeokpts.jKpts;

public class xeo extends javax.swing.JFrame {
    //---externos -----
    String home;      // user home
    //------------------
    ArrayList<String> dir = new ArrayList();   // load the projects
    ArrayList<String> ins = new ArrayList();
    ArrayList<String> preLoad = new ArrayList();
    int nInsp ;
    public Font fuente ;
    //--------------------------
    ArrayList<String> babel = new ArrayList();
    boolean babelInstall;
    //--------------------------
    ArrayList<String> botones = new ArrayList();
    reader cadena = new reader();
    pintar2D pintar2D_out = new pintar2D();
    bulk bulk = new bulk();
    povray pov = new povray();
    ArrayList<String> g2d = new ArrayList();
    mol pintarMol  = new mol();
    BufferedImage screen5Buffered ;
    BufferedImage screenMiniejes ;
    int atom1,atom2,atom3,atom4;
    Runtime run;
    Process proc;
    File newFile;
    boolean modeTreeB;
    language lang = new language();
    int fontSize;
    String fontName;
    String write_tmp;
    //----------------------------
    fireball fireball = new fireball();
    //--ordago---
    int dIc=5;
    long tiempo;
    String SEP;
    int jDesCol;
    int MAX_Tabla=400;
    
    public xeo() {
        //--------------------
        SEP = System.getProperty("file.separator");
        home=System.getProperty("user.home")+SEP;
        if(!new File(home+SEP+".xeorc"+SEP+"xeo.ini").exists()) home=System.getProperty("user.dir")+SEP;
        pintarMol.babel.xeorc=home+".xeorc"+SEP;
        write_tmp=pintarMol.babel.xeorc+"write_tmp";
        fontSize=11;
        fontName="default";
        //-------------------
        setXeoFont("/dejavu/DejaVuSans.ttf");
        initComponents();
        readVal();
        //---------------------
        babelInstall=true; //mirar si exite ! en mi ordenador de momento
        loadBabelList();
        JMenu_xeoBabel();
        //---------------------
        JLanguage();
        //--------other programs-----------
        JMenuFireball();
        //-------------------
        jDesCol=0;
        atom1=atom2=atom3=atom4=-1;
        run = Runtime.getRuntime();
        modeTreeB=false;
        Fuente.setText(Fuente.getFont().getFontName()+" "+Fuente.getFont().getSize());
        nInsp = 0 ;
        //-------------------
    }
    
    JMenuItem elemento ;
    private void JMenu_xeoBabel() {
        ActionListener al;
        al = new ActionListener() {
            @Override
            public void actionPerformed(  ActionEvent evt ){
                //System.out.println( ((JMenuItem)evt.getSource()).getText() );
                pintarMol.babel.infBas.orderBas();
                //limpiamos:
                ls= new File(write_tmp).listFiles();
                for (File l : ls) {
                    l.delete();
                }
                if(((JMenuItem)evt.getSource()).getText().equals("xyz"))
                    pintarMol.babel.write("xyz",write_tmp+SEP+"step_"+xyz_step.getText()+".xyz");
                else
                    pintarMol.babel.write(((JMenuItem)evt.getSource()).getText(),write_tmp+SEP+new File(pintarMol.babel.projectFile).getName());
                new editor().openDirecory_tmp(new File( write_tmp) ,new File(pintarMol.babel.path));
            }
        };
        for(int e=1;e<pintarMol.babel.plug.type.size();e++){
            //  cadena.nCol(pintarMol.babel.typesOfProjects());e++){
            // System.out.println( pintarMol.babel.plug.info.get(e));
            elemento = new JMenuItem( pintarMol.babel.plug.type.get(e));
            elemento.addActionListener( al );
            elemento.setBackground(Color.white);
            elemento.setFont(fuente);
            Menu_xeoBabel.add( elemento );
            xeoBabel_types.addItem(pintarMol.babel.plug.type.get(e)); //en projectOpen
        }
        enableEvents( AWTEvent.MOUSE_EVENT_MASK );
    }
//----------------------------------------
    private void JLanguage() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ){
                lang.lastLanguage=lang.Language;
                // if(lang.Language.equals("mandarin")) fuente = new Font("SansSerif",Font.PLAIN,11);
                lang.Language=((JMenuItem)evt.getSource()).getToolTipText();
                loadVal();
            }
        };
        for (String lan : lang.lan) {
            elemento = new JMenuItem(lan);
            elemento.setText(lang.traslate(lan));
            elemento.setToolTipText(lan);
            elemento.addActionListener( al );
            elemento.setBackground(Color.white);
            elemento.setFont(fuente);
            Me_Language.add( elemento );
        }
        enableEvents( AWTEvent.MOUSE_EVENT_MASK );
    }
//----------------------------------------
    private void JMenuFireball() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(  ActionEvent evt ){
                pintarMol.babel.infBas.orderBas();
                fireball.onPress(((JMenuItem)evt.getSource()).getText(),pintarMol.babel.path,pintarMol.babel.getXeoFormat());
            }
        };
        for(int e=0;e< fireball.nBotones;e++){
            elemento = new JMenuItem( fireball.bot[e]);
            elemento.addActionListener(al);
            elemento.setBackground(Color.white);
            elemento.setFont(fuente);
            jMenu_Fireball.add( elemento );
        }
        enableEvents( AWTEvent.MOUSE_EVENT_MASK );
    }
//----------------------------------------
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuProject = new javax.swing.JPopupMenu();
        me11 = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JSeparator();
        me12 = new javax.swing.JMenuItem();
        me13 = new javax.swing.JMenuItem();
        me14 = new javax.swing.JMenuItem();
        me15 = new javax.swing.JMenuItem();
        me16 = new javax.swing.JMenuItem();
        menuInspctor = new javax.swing.JPopupMenu();
        me17 = new javax.swing.JMenuItem();
        me18 = new javax.swing.JMenuItem();
        menuA = new javax.swing.JPopupMenu();
        me34 = new javax.swing.JMenuItem();
        me21 = new javax.swing.JMenuItem();
        me20 = new javax.swing.JMenuItem();
        me23 = new javax.swing.JMenuItem();
        me24 = new javax.swing.JMenuItem();
        me25 = new javax.swing.JMenuItem();
        AZ = new javax.swing.JMenuItem();
        menuB = new javax.swing.JPopupMenu();
        me29 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JSeparator();
        me30 = new javax.swing.JMenuItem();
        Me6 = new javax.swing.JMenu();
        jMenuPlotNY = new javax.swing.JMenuItem();
        jMenuIPlotXNY = new javax.swing.JMenuItem();
        me31 = new javax.swing.JMenuItem();
        jColorChooser1 = new javax.swing.JColorChooser();
        GroupExcited = new javax.swing.ButtonGroup();
        GroupExchange = new javax.swing.ButtonGroup();
        mv_axis = new javax.swing.ButtonGroup();
        projectChooser = new javax.swing.JDialog();
        jDesktopPane6 = new javax.swing.JDesktopPane();
        path_project = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProjectTree = new javax.swing.JTree();
        Te62 = new javax.swing.JButton();
        Te63 = new javax.swing.JLabel();
        Te64 = new javax.swing.JButton();
        note_text = new javax.swing.JTextField();
        xeoBabel = new javax.swing.JRadioButton();
        xeoBabel_types = new javax.swing.JComboBox();
        useBabel = new javax.swing.JRadioButton();
        Babel_types = new javax.swing.JComboBox();
        Te60 = new javax.swing.JButton();
        Frame_Bulk = new javax.swing.JFrame();
        jDesktopPane7 = new javax.swing.JDesktopPane();
        file_Bulk = new javax.swing.JTextField();
        Te65 = new javax.swing.JButton();
        Te66 = new javax.swing.JButton();
        OptBulk = new javax.swing.JCheckBox();
        Te67 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        edit_Bulk = new javax.swing.JTextArea();
        bcc = new javax.swing.JRadioButton();
        zincblende = new javax.swing.JRadioButton();
        fcc = new javax.swing.JRadioButton();
        archivoBullvs = new javax.swing.JRadioButton();
        jButton54 = new javax.swing.JButton();
        Te68 = new javax.swing.JLabel();
        bulkRadiobutton = new javax.swing.ButtonGroup();
        jFrame_POVRAY = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        antialias = new javax.swing.JTextField();
        use_radio = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bonds = new javax.swing.JTextField();
        radio = new javax.swing.JTextField();
        angle = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        seePovray2 = new javax.swing.JButton();
        seePovray16 = new javax.swing.JButton();
        pov_xyz_ini = new javax.swing.JTextField();
        pov_xyz_fin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pov_xyz_step = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel42 = new javax.swing.JPanel();
        seePovray1 = new javax.swing.JButton();
        seePovray4 = new javax.swing.JButton();
        jButtonPovray = new javax.swing.JButton();
        jButtonPovray1 = new javax.swing.JButton();
        pov_auto = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        pov_selected = new javax.swing.JCheckBox();
        pov_transparente = new javax.swing.JCheckBox();
        pov_Trans = new javax.swing.JTextField();
        pov_radio_selec = new javax.swing.JCheckBox();
        pov_radio_selecA = new javax.swing.JTextField();
        jPopupMenuScript = new javax.swing.JPopupMenu();
        me32 = new javax.swing.JMenuItem();
        me33 = new javax.swing.JMenuItem();
        me26 = new javax.swing.JMenuItem();
        me27 = new javax.swing.JMenuItem();
        me28 = new javax.swing.JMenuItem();
        me19 = new javax.swing.JMenuItem();
        script = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jDesktopPane9 = new javax.swing.JDesktopPane();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        screen_out = new javax.swing.JLabel();
        jDesktopPane10 = new javax.swing.JDesktopPane();
        jButton38 = new javax.swing.JButton();
        reload_out = new javax.swing.JButton();
        Te69 = new javax.swing.JLabel();
        parameters = new javax.swing.JTextField();
        outText = new javax.swing.JTextField();
        Te70 = new javax.swing.JLabel();
        jButton39 = new javax.swing.JButton();
        options_XYZ = new javax.swing.JFrame();
        jDesktopPane8 = new javax.swing.JDesktopPane();
        Te33 = new javax.swing.JCheckBox();
        Te34 = new javax.swing.JLabel();
        Te35 = new javax.swing.JLabel();
        v_FRAMES = new javax.swing.JTextField();
        v_instant_FRAME = new javax.swing.JLabel();
        FRAMESLOAD = new javax.swing.JTextField();
        jSliderMemory = new javax.swing.JSlider();
        AllMemory = new javax.swing.JRadioButton();
        NoMemory = new javax.swing.JRadioButton();
        JFont = new javax.swing.JDialog();
        jDesktopPane11 = new javax.swing.JDesktopPane();
        jLabelFont = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextFont = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListFont = new javax.swing.JList();
        jLabel36 = new javax.swing.JLabel();
        jTextStyle = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListStyle = new javax.swing.JList();
        jLabel37 = new javax.swing.JLabel();
        jTextSize = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jListSize = new javax.swing.JList();
        jButton34 = new javax.swing.JButton();
        jTextFont1 = new javax.swing.JTextField();
        jButton63 = new javax.swing.JButton();
        File_ttf = new javax.swing.JTextField();
        jButton40 = new javax.swing.JButton();
        jDCalc = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        Te40 = new javax.swing.JTextField();
        atomo_1 = new javax.swing.JTextField();
        Te42 = new javax.swing.JTextField();
        atomo_2 = new javax.swing.JTextField();
        Te41 = new javax.swing.JTextField();
        atomo_3 = new javax.swing.JTextField();
        Te43 = new javax.swing.JTextField();
        Te44 = new javax.swing.JButton();
        Giro_centro = new javax.swing.JTextField();
        Te45 = new javax.swing.JTextField();
        Giro_Z = new javax.swing.JTextField();
        Te46 = new javax.swing.JTextField();
        Te47 = new javax.swing.JTextField();
        Giro_X = new javax.swing.JTextField();
        Te48 = new javax.swing.JButton();
        Te49 = new javax.swing.JTextField();
        Giro_centro1 = new javax.swing.JTextField();
        Giro_Z1 = new javax.swing.JTextField();
        Giro_X1 = new javax.swing.JTextField();
        Giro_centro2 = new javax.swing.JTextField();
        Giro_Z2 = new javax.swing.JTextField();
        Giro_X2 = new javax.swing.JTextField();
        Te50 = new javax.swing.JTextField();
        atomo_5 = new javax.swing.JTextField();
        Te51 = new javax.swing.JButton();
        Te52 = new javax.swing.JButton();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        CalcX = new javax.swing.JTextField();
        Tdespl33 = new javax.swing.JTextField();
        Tdespl34 = new javax.swing.JTextField();
        Tdespl35 = new javax.swing.JTextField();
        CalcY = new javax.swing.JTextField();
        CalcZ = new javax.swing.JTextField();
        Te54 = new javax.swing.JButton();
        Tdespl37 = new javax.swing.JTextField();
        Te36 = new javax.swing.JTextField();
        Te37 = new javax.swing.JTextField();
        Te38 = new javax.swing.JTextField();
        Te39 = new javax.swing.JTextField();
        resText = new javax.swing.JTextField();
        rot_TextZ = new javax.swing.JTextField();
        rot_TextY = new javax.swing.JTextField();
        rot_TextX = new javax.swing.JTextField();
        Te55 = new javax.swing.JButton();
        Te56 = new javax.swing.JButton();
        Te57 = new javax.swing.JButton();
        Te58 = new javax.swing.JButton();
        Tdespl42 = new javax.swing.JTextField();
        Te59 = new javax.swing.JButton();
        Tdespl43 = new javax.swing.JTextField();
        jbabel = new javax.swing.JFrame();
        jDesktopPane5 = new javax.swing.JDesktopPane();
        inputFileBabel = new javax.swing.JTextField();
        buttonOpenBabel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxBabelRead = new javax.swing.JComboBox();
        jComboBoxBabelWrite = new javax.swing.JComboBox();
        jButton43 = new javax.swing.JButton();
        Tdespl2 = new javax.swing.JTextField();
        jRadioButtonXeo = new javax.swing.JRadioButton();
        jRadioButtonBabel = new javax.swing.JRadioButton();
        Tdespl20 = new javax.swing.JTextField();
        buttonGroupExport = new javax.swing.ButtonGroup();
        buttonGroupBabel = new javax.swing.ButtonGroup();
        Options = new javax.swing.JFrame();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        Te19 = new javax.swing.JTextField();
        Te20 = new javax.swing.JTextField();
        PERS = new javax.swing.JSlider();
        jButton30 = new javax.swing.JButton();
        Fuente = new javax.swing.JLabel();
        verIconosAtomos = new javax.swing.JCheckBox();
        Te22 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        colorFondo = new javax.swing.JPanel();
        Te23 = new javax.swing.JLabel();
        colorEjes = new javax.swing.JPanel();
        verEjes = new javax.swing.JCheckBox();
        jButton20 = new javax.swing.JButton();
        Te24 = new javax.swing.JLabel();
        Te25 = new javax.swing.JTextField();
        jT_cx = new javax.swing.JTextField();
        jT_cy = new javax.swing.JTextField();
        jT_cz = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        colorArrows = new javax.swing.JPanel();
        seeArrows = new javax.swing.JCheckBox();
        seeArrowsColor = new javax.swing.JCheckBox();
        rescalateArrow = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        Te27 = new javax.swing.JLabel();
        Te28 = new javax.swing.JLabel();
        Te32 = new javax.swing.JLabel();
        Te61 = new javax.swing.JLabel();
        escalaGrises = new javax.swing.JCheckBox();
        jDesktopPane13 = new javax.swing.JDesktopPane();
        Te72 = new javax.swing.JTextField();
        jButton59 = new javax.swing.JButton();
        xyzFileTr = new javax.swing.JTextField();
        Te73 = new javax.swing.JTextField();
        xyztAtomsTr = new javax.swing.JTextField();
        jComboBox = new javax.swing.JComboBox();
        jButton47 = new javax.swing.JButton();
        colorLabel = new javax.swing.JPanel();
        ancho = new javax.swing.JSpinner();
        Tdespl65 = new javax.swing.JTextField();
        jlvs_1 = new javax.swing.JSpinner();
        jlvs_2 = new javax.swing.JSpinner();
        jlvs_3 = new javax.swing.JSpinner();
        jtrlasttext = new javax.swing.JTextField();
        jtrfisttext = new javax.swing.JTextField();
        jtrlast = new javax.swing.JTextField();
        jtrfirst = new javax.swing.JTextField();
        Te78 = new javax.swing.JButton();
        xyzTr = new javax.swing.JCheckBox();
        Te77 = new javax.swing.JLabel();
        Te79 = new javax.swing.JButton();
        jDesktopPane12 = new javax.swing.JDesktopPane();
        Te29 = new javax.swing.JTextField();
        xyzt = new javax.swing.JCheckBox();
        Te30 = new javax.swing.JLabel();
        Te31 = new javax.swing.JLabel();
        pixel = new javax.swing.JTextField();
        xyztFile = new javax.swing.JTextField();
        color_inter_2 = new javax.swing.JButton();
        color_fin = new javax.swing.JButton();
        color_ini = new javax.swing.JButton();
        color_inter_1 = new javax.swing.JButton();
        Sinter1 = new javax.swing.JSlider();
        Clabel = new javax.swing.JLabel();
        Sinter2 = new javax.swing.JSlider();
        Te53 = new javax.swing.JButton();
        seeBordes = new javax.swing.JCheckBox();
        Te26 = new javax.swing.JLabel();
        jButton58 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jSplitForm = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        TABXEO = new javax.swing.JPanel();
        screen5 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        xyz_end = new javax.swing.JLabel();
        Bt0 = new javax.swing.JButton();
        Bt1 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabelXYZ = new javax.swing.JLabel();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        openB_xyz = new javax.swing.JButton();
        PlayB = new javax.swing.JButton();
        xyz_menos = new javax.swing.JButton();
        xyz_mas = new javax.swing.JButton();
        xyz_step = new javax.swing.JTextField();
        jScrollDesk = new javax.swing.JScrollPane();
        jPanelDesk = new javax.swing.JPanel();
        jDesktOpt = new javax.swing.JDesktopPane();
        jDeskt0 = new javax.swing.JDesktopPane();
        paste = new javax.swing.JButton();
        Te7 = new javax.swing.JTextField();
        Te6 = new javax.swing.JTextField();
        Te12 = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        Z_all = new javax.swing.JTextField();
        Te8 = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        Te0 = new javax.swing.JTextField();
        savePos = new javax.swing.JButton();
        Te10 = new javax.swing.JTextField();
        Te14 = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        Te9 = new javax.swing.JTextField();
        copy = new javax.swing.JButton();
        seePos = new javax.swing.JCheckBox();
        jCOrdenar = new javax.swing.JComboBox();
        previewPos = new javax.swing.JButton();
        BtOrder = new javax.swing.JButton();
        Te21 = new javax.swing.JTextField();
        jDeskt1 = new javax.swing.JDesktopPane();
        Te1 = new javax.swing.JTextField();
        MiniEjes = new javax.swing.JLabel();
        Tdespl = new javax.swing.JTextField();
        jCAxis = new javax.swing.JComboBox();
        jDeskt3 = new javax.swing.JDesktopPane();
        jCFix = new javax.swing.JComboBox();
        selectFixAtoms = new javax.swing.JButton();
        Te2 = new javax.swing.JTextField();
        CheckFragments = new javax.swing.JCheckBox();
        Te11 = new javax.swing.JTextField();
        jDeskt4 = new javax.swing.JDesktopPane();
        Te3 = new javax.swing.JTextField();
        changeLVS = new javax.swing.JButton();
        Te13 = new javax.swing.JTextField();
        lvs_11 = new javax.swing.JTextField();
        lvs_21 = new javax.swing.JTextField();
        lvs_31 = new javax.swing.JTextField();
        lvs_12 = new javax.swing.JTextField();
        lvs_22 = new javax.swing.JTextField();
        lvs_32 = new javax.swing.JTextField();
        lvs_13 = new javax.swing.JTextField();
        lvs_23 = new javax.swing.JTextField();
        lvs_33 = new javax.swing.JTextField();
        jDeskt5 = new javax.swing.JDesktopPane();
        Te4 = new javax.swing.JTextField();
        mol_seeVol = new javax.swing.JCheckBox();
        Te15 = new javax.swing.JTextField();
        mol_Xini = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        mol_Xfin = new javax.swing.JTextField();
        mol_Yfin = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        mol_Yini = new javax.swing.JTextField();
        mol_Zini = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        mol_Zfin = new javax.swing.JTextField();
        Te16 = new javax.swing.JTextField();
        mol_seeIndex = new javax.swing.JCheckBox();
        jButton62 = new javax.swing.JButton();
        mol_lvs_1 = new javax.swing.JTextField();
        mol_lvs_2 = new javax.swing.JTextField();
        mol_lvs_3 = new javax.swing.JTextField();
        jDeskt6 = new javax.swing.JDesktopPane();
        Te5 = new javax.swing.JTextField();
        TOL = new javax.swing.JSlider();
        Te18 = new javax.swing.JTextField();
        grosor = new javax.swing.JTextField();
        rad = new javax.swing.JTextField();
        Te17 = new javax.swing.JTextField();
        diffRadio = new javax.swing.JCheckBox();
        seeBond = new javax.swing.JCheckBox();
        jDesktopPrpal = new javax.swing.JDesktopPane();
        menos_mol = new javax.swing.JButton();
        yx_mol = new javax.swing.JButton();
        xz_mol = new javax.swing.JButton();
        mas_mol = new javax.swing.JButton();
        xy_mol = new javax.swing.JButton();
        zy_mol = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel66 = new javax.swing.JPanel();
        jScrollPaneTreeA = new javax.swing.JScrollPane();
        TreeA = new javax.swing.JTree();
        jPanel13 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jButtonProy = new javax.swing.JButton();
        jButtonFold = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPaneTreeB = new javax.swing.JScrollPane();
        TreeB = new javax.swing.JTree();
        jSplitPane2 = new javax.swing.JSplitPane();
        Bt2 = new javax.swing.JButton();
        tipo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        menuPpal = new javax.swing.JMenuBar();
        Me0 = new javax.swing.JMenu();
        me0 = new javax.swing.JMenuItem();
        me1 = new javax.swing.JMenuItem();
        me2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        me3 = new javax.swing.JMenuItem();
        me4 = new javax.swing.JMenuItem();
        me5 = new javax.swing.JMenuItem();
        Me1 = new javax.swing.JMenu();
        me_rot = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        me_dinamic = new javax.swing.JMenuItem();
        me_STM = new javax.swing.JMenuItem();
        me_kpoints = new javax.swing.JMenuItem();
        me_Bulk = new javax.swing.JMenuItem();
        me_script = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        me_ediotr = new javax.swing.JMenuItem();
        me_cal = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JSeparator();
        Me5 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu_Fireball = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        Me2 = new javax.swing.JMenu();
        Menu_xeoBabel = new javax.swing.JMenu();
        me_babel = new javax.swing.JMenuItem();
        me_POVRAY = new javax.swing.JMenuItem();
        Me3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        Me_Language = new javax.swing.JMenu();
        me6 = new javax.swing.JMenuItem();
        me7 = new javax.swing.JMenuItem();
        me8 = new javax.swing.JMenuItem();
        Me4 = new javax.swing.JMenu();
        Me7 = new javax.swing.JMenu();
        me35 = new javax.swing.JMenuItem();
        me36 = new javax.swing.JMenuItem();
        me37 = new javax.swing.JMenuItem();
        me38 = new javax.swing.JMenuItem();

        menuProject.setFont(fuente
        );

        me11.setFont(fuente);
        me11.setText("folder/manager");
        me11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me11MousePressed(evt);
            }
        });
        menuProject.add(me11);
        menuProject.add(jSeparator13);

        me12.setFont(fuente);
        me12.setText("add inspector");
        me12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me12MousePressed(evt);
            }
        });
        menuProject.add(me12);

        me13.setFont(fuente);
        me13.setText("close inspector");
        me13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me13MousePressed(evt);
            }
        });
        menuProject.add(me13);

        me14.setFont(fuente);
        me14.setText("rename press (F2 )");
        me14.setEnabled(false);
        me14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me14MousePressed(evt);
            }
        });
        menuProject.add(me14);

        me15.setFont(fuente);
        me15.setText("move up (8)");
        me15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me15MousePressed(evt);
            }
        });
        menuProject.add(me15);

        me16.setFont(fuente);
        me16.setText("move down (2)");
        me16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me16MousePressed(evt);
            }
        });
        menuProject.add(me16);

        menuInspctor.setFont(fuente
        );

        me17.setFont(fuente);
        me17.setText("add inspector");
        me17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me17MousePressed(evt);
            }
        });
        menuInspctor.add(me17);

        me18.setFont(fuente);
        me18.setText("close inspector (Supr)");
        me18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me18MousePressed(evt);
            }
        });
        menuInspctor.add(me18);

        menuA.setFont(fuente
        );

        me34.setFont(fuente);
        me34.setText("info");
        me34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me34MousePressed(evt);
            }
        });
        menuA.add(me34);

        me21.setFont(fuente);
        me21.setText("open project (F4)");
        me21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me21MousePressed(evt);
            }
        });
        menuA.add(me21);

        me20.setFont(fuente);
        me20.setText("load (F5)");
        me20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me20MousePressed(evt);
            }
        });
        menuA.add(me20);

        me23.setFont(fuente);
        me23.setText("close entry (Supr)");
        me23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me23MousePressed(evt);
            }
        });
        menuA.add(me23);

        me24.setFont(fuente);
        me24.setText("move down (2)");
        me24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me24MousePressed(evt);
            }
        });
        menuA.add(me24);

        me25.setFont(fuente);
        me25.setText("move up (8)");
        me25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me25MousePressed(evt);
            }
        });
        menuA.add(me25);

        AZ.setFont(fuente);
        AZ.setText("A-Z");
        AZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AZMousePressed(evt);
            }
        });
        menuA.add(AZ);

        menuB.setFont(fuente
        );

        me29.setFont(fuente);
        me29.setText("foder/manager");
        me29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me29MousePressed(evt);
            }
        });
        menuB.add(me29);
        menuB.add(jSeparator12);

        me30.setFont(fuente);
        me30.setText("Edit(Text)");
        me30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me30MousePressed(evt);
            }
        });
        menuB.add(me30);

        Me6.setText("Graphics");
        Me6.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        jMenuPlotNY.setFont(fuente);
        jMenuPlotNY.setText("plot NY");
        jMenuPlotNY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuPlotNYMousePressed(evt);
            }
        });
        Me6.add(jMenuPlotNY);

        jMenuIPlotXNY.setFont(fuente);
        jMenuIPlotXNY.setText("plot XNY");
        jMenuIPlotXNY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuIPlotXNYMousePressed(evt);
            }
        });
        Me6.add(jMenuIPlotXNY);

        me31.setFont(fuente);
        me31.setText("show picture");
        me31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me31MousePressed(evt);
            }
        });
        Me6.add(me31);

        menuB.add(Me6);

        projectChooser.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        projectChooser.setTitle("Project Chooser");
        projectChooser.setAlwaysOnTop(true);
        projectChooser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        path_project.setFont(fuente);
        path_project.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        path_project.setFocusCycleRoot(true);
        path_project.setInheritsPopupMenu(true);
        path_project.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                path_projectKeyPressed(evt);
            }
        });
        jDesktopPane6.add(path_project);
        path_project.setBounds(2, 2, 400, 18);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        ProjectTree.setFont(fuente);
        ProjectTree.setToolTipText("you can press the right botton of the mouse following the left botton to load the project");
        ProjectTree.setExpandsSelectedPaths(false);
        ProjectTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProjectTreeMouseClicked(evt);
            }
        });
        ProjectTree.addTreeWillExpandListener(new javax.swing.event.TreeWillExpandListener() {
            public void treeWillCollapse(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
            }
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
                ProjectTreeTreeWillExpand(evt);
            }
        });
        jScrollPane1.setViewportView(ProjectTree);

        jDesktopPane6.add(jScrollPane1);
        jScrollPane1.setBounds(2, 22, 400, 256);

        Te62.setFont(fuente);
        Te62.setText("open selected project");
        Te62.setToolTipText("you can press the right botton of the mouse following the left botton to load the project");
        Te62.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te62MousePressed(evt);
            }
        });
        jDesktopPane6.add(Te62);
        Te62.setBounds(200, 320, 200, 18);

        Te63.setFont(fuente);
        Te63.setText("insert the text of the note:");
        Te63.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te63.setOpaque(true);
        jDesktopPane6.add(Te63);
        Te63.setBounds(2, 340, 398, 18);

        Te64.setFont(fuente);
        Te64.setText("insert the note");
        Te64.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te64MousePressed(evt);
            }
        });
        jDesktopPane6.add(Te64);
        Te64.setBounds(270, 360, 130, 18);

        note_text.setFont(fuente);
        note_text.setText("---");
        note_text.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        note_text.setFocusCycleRoot(true);
        note_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                note_textKeyPressed(evt);
            }
        });
        jDesktopPane6.add(note_text);
        note_text.setBounds(2, 360, 266, 18);

        xeoBabel.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupBabel.add(xeoBabel);
        xeoBabel.setFont(fuente);
        xeoBabel.setSelected(true);
        xeoBabel.setText("used xeoBabel");
        xeoBabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        xeoBabel.setBorderPainted(true);
        xeoBabel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane6.add(xeoBabel);
        xeoBabel.setBounds(2, 280, 196, 18);

        xeoBabel_types.setBackground(new java.awt.Color(255, 255, 255));
        xeoBabel_types.setFont(fuente);
        xeoBabel_types.setMaximumRowCount(20);
        jDesktopPane6.add(xeoBabel_types);
        xeoBabel_types.setBounds(200, 280, 200, 18);

        useBabel.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupBabel.add(useBabel);
        useBabel.setFont(fuente);
        useBabel.setText("used Babel");
        useBabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        useBabel.setBorderPainted(true);
        useBabel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane6.add(useBabel);
        useBabel.setBounds(2, 300, 196, 18);

        Babel_types.setBackground(new java.awt.Color(255, 255, 255));
        Babel_types.setFont(fuente);
        Babel_types.setMaximumRowCount(20);
        jDesktopPane6.add(Babel_types);
        Babel_types.setBounds(200, 300, 200, 18);

        Te60.setFont(fuente);
        Te60.setText("open selected directories");
        Te60.setToolTipText("you can press the right botton of the mouse following the left botton to load the project");
        Te60.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te60MousePressed(evt);
            }
        });
        jDesktopPane6.add(Te60);
        Te60.setBounds(2, 320, 196, 18);

        org.jdesktop.layout.GroupLayout projectChooserLayout = new org.jdesktop.layout.GroupLayout(projectChooser.getContentPane());
        projectChooser.getContentPane().setLayout(projectChooserLayout);
        projectChooserLayout.setHorizontalGroup(
            projectChooserLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
        projectChooserLayout.setVerticalGroup(
            projectChooserLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );

        Frame_Bulk.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Frame_Bulk.setTitle("Bulk modulus");
        Frame_Bulk.setResizable(false);

        file_Bulk.setFont(fuente);
        file_Bulk.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane7.add(file_Bulk);
        file_Bulk.setBounds(80, 10, 310, 18);

        Te65.setFont(fuente);
        Te65.setText("open file");
        Te65.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te65.setMargin(new java.awt.Insets(2, 2, 2, 2));
        Te65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te65MousePressed(evt);
            }
        });
        jDesktopPane7.add(Te65);
        Te65.setBounds(10, 10, 58, 18);

        Te66.setFont(fuente);
        Te66.setText("help");
        Te66.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te66.setMargin(new java.awt.Insets(2, 2, 2, 2));
        Te66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te66MousePressed(evt);
            }
        });
        jDesktopPane7.add(Te66);
        Te66.setBounds(400, 10, 33, 18);

        OptBulk.setBackground(new java.awt.Color(255, 255, 255));
        OptBulk.setFont(fuente);
        OptBulk.setSelected(true);
        OptBulk.setText("opt");
        OptBulk.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        OptBulk.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane7.add(OptBulk);
        OptBulk.setBounds(80, 130, 43, 18);

        Te67.setFont(fuente);
        Te67.setText("calculate");
        Te67.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te67MousePressed(evt);
            }
        });
        jDesktopPane7.add(Te67);
        Te67.setBounds(10, 130, 68, 18);

        jScrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        edit_Bulk.setColumns(20);
        edit_Bulk.setFont(fuente);
        edit_Bulk.setRows(5);
        edit_Bulk.setBorder(null);
        jScrollPane7.setViewportView(edit_Bulk);

        jDesktopPane7.add(jScrollPane7);
        jScrollPane7.setBounds(10, 150, 430, 160);

        bcc.setBackground(new java.awt.Color(255, 255, 255));
        bulkRadiobutton.add(bcc);
        bcc.setFont(fuente);
        bcc.setText("bcc");
        bcc.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        bcc.setBorderPainted(true);
        bcc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane7.add(bcc);
        bcc.setBounds(10, 40, 200, 18);

        zincblende.setBackground(new java.awt.Color(255, 255, 255));
        bulkRadiobutton.add(zincblende);
        zincblende.setFont(fuente);
        zincblende.setSelected(true);
        zincblende.setText("zincblende");
        zincblende.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        zincblende.setBorderPainted(true);
        zincblende.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane7.add(zincblende);
        zincblende.setBounds(10, 60, 200, 18);

        fcc.setBackground(new java.awt.Color(255, 255, 255));
        bulkRadiobutton.add(fcc);
        fcc.setFont(fuente);
        fcc.setText("fcc");
        fcc.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        fcc.setBorderPainted(true);
        fcc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane7.add(fcc);
        fcc.setBounds(10, 80, 200, 18);

        archivoBullvs.setBackground(new java.awt.Color(255, 255, 255));
        bulkRadiobutton.add(archivoBullvs);
        archivoBullvs.setFont(fuente);
        archivoBullvs.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        archivoBullvs.setBorderPainted(true);
        archivoBullvs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        archivoBullvs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        archivoBullvs.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane7.add(archivoBullvs);
        archivoBullvs.setBounds(10, 100, 18, 18);

        jButton54.setFont(fuente);
        jButton54.setText("...");
        jButton54.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton54MousePressed(evt);
            }
        });
        jDesktopPane7.add(jButton54);
        jButton54.setBounds(30, 100, 18, 18);

        Te68.setFont(fuente);
        Te68.setText("load lattice vector from file");
        Te68.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane7.add(Te68);
        Te68.setBounds(50, 100, 160, 18);

        org.jdesktop.layout.GroupLayout Frame_BulkLayout = new org.jdesktop.layout.GroupLayout(Frame_Bulk.getContentPane());
        Frame_Bulk.getContentPane().setLayout(Frame_BulkLayout);
        Frame_BulkLayout.setHorizontalGroup(
            Frame_BulkLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
        );
        Frame_BulkLayout.setVerticalGroup(
            Frame_BulkLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
        );

        jFrame_POVRAY.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jFrame_POVRAY.setTitle("POV-Ray");

        jPanel9.setBackground(java.awt.Color.white);
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jPanel40.setBackground(java.awt.Color.white);
        jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel1.setFont(fuente);
        jLabel1.setText("angle (zoom)");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        antialias.setFont(fuente);
        antialias.setText("0.1");
        antialias.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        use_radio.setBackground(new java.awt.Color(255, 255, 255));
        use_radio.setFont(fuente);
        use_radio.setText("use radio");
        use_radio.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        use_radio.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel2.setFont(fuente);
        jLabel2.setText("antialias");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel9.setFont(fuente);
        jLabel9.setText("Bonds");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        bonds.setFont(fuente);
        bonds.setText("1");
        bonds.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        radio.setFont(fuente);
        radio.setText("2");
        radio.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        angle.setFont(fuente);
        angle.setText("1");
        angle.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(fuente);
        jLabel22.setText("POV-RAY");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        org.jdesktop.layout.GroupLayout jPanel40Layout = new org.jdesktop.layout.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel40Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel40Layout.createSequentialGroup()
                        .add(jLabel22)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(angle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel40Layout.createSequentialGroup()
                        .add(0, 150, Short.MAX_VALUE)
                        .add(jPanel40Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel2)
                            .add(use_radio)
                            .add(jLabel9))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel40Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(antialias, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(radio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(bonds, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel40Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(angle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(jLabel22))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel40Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(antialias, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel40Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(radio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(use_radio))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel40Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(bonds, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel41.setBackground(java.awt.Color.white);
        jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        seePovray2.setFont(fuente);
        seePovray2.setText("frames (Povray)");
        seePovray2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        seePovray2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seePovray2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                seePovray2MousePressed(evt);
            }
        });

        seePovray16.setFont(fuente);
        seePovray16.setText("frames (xeo)");
        seePovray16.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        seePovray16.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seePovray16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                seePovray16MousePressed(evt);
            }
        });

        pov_xyz_ini.setFont(fuente);
        pov_xyz_ini.setText("1");
        pov_xyz_ini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        pov_xyz_ini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pov_xyz_iniActionPerformed(evt);
            }
        });

        pov_xyz_fin.setFont(fuente);
        pov_xyz_fin.setText("2147483647");
        pov_xyz_fin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        pov_xyz_fin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pov_xyz_finActionPerformed(evt);
            }
        });

        jLabel3.setFont(fuente);
        jLabel3.setText("initial step");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel4.setFont(fuente);
        jLabel4.setText("final step");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel6.setFont(fuente);
        jLabel6.setText("step by");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        pov_xyz_step.setFont(fuente);
        pov_xyz_step.setText("1");
        pov_xyz_step.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        pov_xyz_step.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pov_xyz_stepActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("obtain a film:\nffDiaporama\nconvert -delay 100  frames/*.png out.gif\n....\n");
        jScrollPane3.setViewportView(jTextArea1);

        org.jdesktop.layout.GroupLayout jPanel41Layout = new org.jdesktop.layout.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel41Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane3)
                    .add(seePovray2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(seePovray16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel41Layout.createSequentialGroup()
                        .add(jPanel41Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel41Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, pov_xyz_ini)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, pov_xyz_fin, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, pov_xyz_step))))
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .add(seePovray2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(7, 7, 7)
                .add(seePovray16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel41Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(pov_xyz_ini, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel41Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pov_xyz_fin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel41Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pov_xyz_step, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel42.setBackground(java.awt.Color.white);
        jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        seePovray1.setFont(fuente);
        seePovray1.setText("see povray");
        seePovray1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        seePovray1.setEnabled(false);
        seePovray1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seePovray1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                seePovray1MousePressed(evt);
            }
        });

        seePovray4.setFont(fuente);
        seePovray4.setText("help");
        seePovray4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        seePovray4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seePovray4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                seePovray4MousePressed(evt);
            }
        });

        jButtonPovray.setFont(fuente);
        jButtonPovray.setText("reload");
        jButtonPovray.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButtonPovray.setEnabled(false);
        jButtonPovray.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonPovray.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonPovrayMousePressed(evt);
            }
        });

        jButtonPovray1.setFont(fuente);
        jButtonPovray1.setText("files");
        jButtonPovray1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButtonPovray1.setEnabled(false);
        jButtonPovray1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonPovray1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonPovray1MousePressed(evt);
            }
        });

        pov_auto.setBackground(new java.awt.Color(255, 255, 255));
        pov_auto.setFont(fuente);
        pov_auto.setText("On mouse released");
        pov_auto.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        pov_auto.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout jPanel42Layout = new org.jdesktop.layout.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel42Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jButtonPovray, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(seePovray1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel42Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel42Layout.createSequentialGroup()
                        .add(jButtonPovray1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(seePovray4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel42Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(pov_auto)))
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel42Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(seePovray1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pov_auto))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel42Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButtonPovray, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButtonPovray1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(seePovray4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.focus")));

        pov_selected.setBackground(new java.awt.Color(255, 255, 255));
        pov_selected.setFont(fuente);
        pov_selected.setText("Only in selected atoms:");
        pov_selected.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        pov_selected.setMargin(new java.awt.Insets(0, 0, 0, 0));

        pov_transparente.setBackground(new java.awt.Color(255, 255, 255));
        pov_transparente.setFont(fuente);
        pov_transparente.setText("Transparent");
        pov_transparente.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        pov_transparente.setMargin(new java.awt.Insets(0, 0, 0, 0));

        pov_Trans.setFont(fuente);
        pov_Trans.setText("0.6");
        pov_Trans.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        pov_radio_selec.setBackground(new java.awt.Color(255, 255, 255));
        pov_radio_selec.setFont(fuente);
        pov_radio_selec.setText("use a radio");
        pov_radio_selec.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        pov_radio_selec.setMargin(new java.awt.Insets(0, 0, 0, 0));

        pov_radio_selecA.setFont(fuente);
        pov_radio_selecA.setText("0");
        pov_radio_selecA.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(pov_selected, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                .add(pov_radio_selec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(pov_radio_selecA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                .add(pov_transparente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(pov_Trans, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(pov_selected, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(pov_transparente)
                    .add(pov_Trans, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(pov_radio_selec)
                    .add(pov_radio_selecA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel40, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel42, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel41, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jFrame_POVRAYLayout = new org.jdesktop.layout.GroupLayout(jFrame_POVRAY.getContentPane());
        jFrame_POVRAY.getContentPane().setLayout(jFrame_POVRAYLayout);
        jFrame_POVRAYLayout.setHorizontalGroup(
            jFrame_POVRAYLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        jFrame_POVRAYLayout.setVerticalGroup(
            jFrame_POVRAYLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPopupMenuScript.setFont(fuente
        );

        me32.setFont(fuente);
        me32.setText("save");
        me32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me32MousePressed(evt);
            }
        });
        jPopupMenuScript.add(me32);

        me33.setFont(fuente);
        me33.setText("preview");
        me33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me33MousePressed(evt);
            }
        });
        jPopupMenuScript.add(me33);

        me26.setFont(fuente);
        me26.setText("reload");
        me26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me26MousePressed(evt);
            }
        });
        jPopupMenuScript.add(me26);

        me27.setFont(fuente);
        me27.setText("options");
        me27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me27MousePressed(evt);
            }
        });
        jPopupMenuScript.add(me27);

        me28.setFont(fuente);
        me28.setText("labels.conf");
        me28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me28MousePressed1(evt);
            }
        });
        jPopupMenuScript.add(me28);

        me19.setFont(fuente);
        me19.setText("help");
        me19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me19MousePressed(evt);
            }
        });
        jPopupMenuScript.add(me19);

        script.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        script.setTitle("script");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jButton10.setFont(fuente);
        jButton10.setText("...");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton10MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton10);
        jButton10.setBounds(0, 0, 18, 18);

        jButton11.setFont(fuente);
        jButton11.setText("...");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton11MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton11);
        jButton11.setBounds(0, 20, 18, 18);

        jButton2.setFont(fuente);
        jButton2.setText("jButton2");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton2.setEnabled(false);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton2);
        jButton2.setBounds(20, 20, 76, 18);

        jButton1.setFont(fuente);
        jButton1.setText("jButton1");
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton1.setEnabled(false);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton1);
        jButton1.setBounds(20, 0, 76, 18);

        jButton12.setFont(fuente);
        jButton12.setText("...");
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton12MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton12);
        jButton12.setBounds(100, 0, 18, 18);

        jButton13.setFont(fuente);
        jButton13.setText("...");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton13MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton13);
        jButton13.setBounds(100, 20, 18, 18);

        jButton4.setFont(fuente);
        jButton4.setText("jButton4");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton4.setEnabled(false);
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton4);
        jButton4.setBounds(120, 20, 76, 18);

        jButton3.setFont(fuente);
        jButton3.setText("jButton3");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton3.setEnabled(false);
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton3);
        jButton3.setBounds(120, 0, 76, 18);

        jButton14.setFont(fuente);
        jButton14.setText("...");
        jButton14.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton14MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton14);
        jButton14.setBounds(200, 0, 18, 18);

        jButton15.setFont(fuente);
        jButton15.setText("...");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton15MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton15);
        jButton15.setBounds(200, 20, 18, 18);

        jButton6.setFont(fuente);
        jButton6.setText("jButton6");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton6.setEnabled(false);
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton6);
        jButton6.setBounds(220, 20, 76, 18);

        jButton5.setFont(fuente);
        jButton5.setText("jButton5");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton5.setEnabled(false);
        jButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton5);
        jButton5.setBounds(220, 0, 76, 18);

        jButton16.setFont(fuente);
        jButton16.setText("...");
        jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton16MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton16);
        jButton16.setBounds(300, 0, 18, 18);

        jButton17.setFont(fuente);
        jButton17.setText("...");
        jButton17.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton17MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton17);
        jButton17.setBounds(300, 20, 18, 18);

        jButton8.setFont(fuente);
        jButton8.setText("jButton8");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton8.setEnabled(false);
        jButton8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton8MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton8);
        jButton8.setBounds(320, 20, 76, 18);

        jButton7.setFont(fuente);
        jButton7.setText("jButton7");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton7.setEnabled(false);
        jButton7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton7MousePressed(evt);
            }
        });
        jDesktopPane9.add(jButton7);
        jButton7.setBounds(320, 0, 76, 18);

        screen_out.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        screen_out.setEnabled(false);
        screen_out.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                screen_outMouseDragged(evt);
            }
        });
        screen_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                screen_outMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                screen_outMouseReleased(evt);
            }
        });
        screen_out.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                screen_outMouseWheelMoved(evt);
            }
        });

        jButton38.setFont(fuente);
        jButton38.setText("Opt.");
        jButton38.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton38.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton38MousePressed(evt);
            }
        });
        jDesktopPane10.add(jButton38);
        jButton38.setBounds(2, 5, 30, 30);

        reload_out.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/reload.gif"))); // NOI18N
        reload_out.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        reload_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reload_outMousePressed(evt);
            }
        });
        jDesktopPane10.add(reload_out);
        reload_out.setBounds(35, 5, 30, 30);

        Te69.setFont(fuente);
        Te69.setText("more prameters $1 $2 ...., : ");
        Te69.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane10.add(Te69);
        Te69.setBounds(70, 0, 178, 18);

        parameters.setText("1");
        parameters.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane10.add(parameters);
        parameters.setBounds(250, 0, 146, 18);

        outText.setFont(fuente);
        outText.setText(" inser here the path of the intput file");
        outText.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane10.add(outText);
        outText.setBounds(166, 20, 210, 18);

        Te70.setFont(fuente);
        Te70.setText("input file: ");
        Te70.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane10.add(Te70);
        Te70.setBounds(70, 20, 94, 18);

        jButton39.setFont(fuente);
        jButton39.setText("...");
        jButton39.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton39MousePressed(evt);
            }
        });
        jDesktopPane10.add(jButton39);
        jButton39.setBounds(378, 20, 18, 18);

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(screen_out, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .add(jDesktopPane9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .add(jDesktopPane10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jDesktopPane10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(screen_out, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jDesktopPane9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout scriptLayout = new org.jdesktop.layout.GroupLayout(script.getContentPane());
        script.getContentPane().setLayout(scriptLayout);
        scriptLayout.setHorizontalGroup(
            scriptLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        scriptLayout.setVerticalGroup(
            scriptLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        options_XYZ.setResizable(false);

        Te33.setBackground(new java.awt.Color(255, 255, 255));
        Te33.setFont(fuente
        );
        Te33.setText("center in each step");
        Te33.setToolTipText("Center each step of the film, default center the bigger step");
        Te33.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te33.setBorderPainted(true);
        Te33.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane8.add(Te33);
        Te33.setBounds(10, 10, 400, 18);

        Te34.setFont(fuente
        );
        Te34.setText("frames per millisecond");
        Te34.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane8.add(Te34);
        Te34.setBounds(10, 30, 140, 18);

        Te35.setFont(fuente
        );
        Te35.setText("Number of steps loading in memory:");
        Te35.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane8.add(Te35);
        Te35.setBounds(10, 50, 248, 18);

        v_FRAMES.setFont(fuente
        );
        v_FRAMES.setForeground(new java.awt.Color(102, 102, 102));
        v_FRAMES.setText("100");
        v_FRAMES.setToolTipText("frames per millisecond");
        v_FRAMES.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane8.add(v_FRAMES);
        v_FRAMES.setBounds(152, 30, 66, 18);

        v_instant_FRAME.setFont(fuente
        );
        v_instant_FRAME.setText("V_instant = 0");
        v_instant_FRAME.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane8.add(v_instant_FRAME);
        v_instant_FRAME.setBounds(220, 30, 190, 18);

        FRAMESLOAD.setFont(fuente
        );
        FRAMESLOAD.setForeground(new java.awt.Color(102, 102, 102));
        FRAMESLOAD.setText("0");
        FRAMESLOAD.setToolTipText("frames per millisecond");
        FRAMESLOAD.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        FRAMESLOAD.setEnabled(false);
        FRAMESLOAD.setFocusable(false);
        jDesktopPane8.add(FRAMESLOAD);
        FRAMESLOAD.setBounds(260, 50, 150, 18);

        jSliderMemory.setBackground(new java.awt.Color(255, 255, 255));
        jSliderMemory.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jSliderMemory.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jSliderMemoryMouseDragged(evt);
            }
        });
        jDesktopPane8.add(jSliderMemory);
        jSliderMemory.setBounds(30, 70, 360, 18);

        AllMemory.setBackground(new java.awt.Color(255, 255, 255));
        AllMemory.setToolTipText("keep all steps in memory");
        AllMemory.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        AllMemory.setBorderPainted(true);
        AllMemory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AllMemory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AllMemory.setMargin(new java.awt.Insets(0, 0, 0, 0));
        AllMemory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AllMemoryMouseClicked(evt);
            }
        });
        jDesktopPane8.add(AllMemory);
        AllMemory.setBounds(392, 70, 18, 18);

        NoMemory.setBackground(new java.awt.Color(255, 255, 255));
        NoMemory.setToolTipText("Not use the memory");
        NoMemory.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        NoMemory.setBorderPainted(true);
        NoMemory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NoMemory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NoMemory.setMargin(new java.awt.Insets(0, 0, 0, 0));
        NoMemory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NoMemoryMouseClicked(evt);
            }
        });
        jDesktopPane8.add(NoMemory);
        NoMemory.setBounds(10, 70, 18, 18);

        org.jdesktop.layout.GroupLayout options_XYZLayout = new org.jdesktop.layout.GroupLayout(options_XYZ.getContentPane());
        options_XYZ.getContentPane().setLayout(options_XYZLayout);
        options_XYZLayout.setHorizontalGroup(
            options_XYZLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 421, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        options_XYZLayout.setVerticalGroup(
            options_XYZLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        JFont.setResizable(false);

        jLabelFont.setEditable(false);
        jLabelFont.setText("xeo (project management for nanostructures)");
        jDesktopPane11.add(jLabelFont);
        jLabelFont.setBounds(10, 210, 410, 70);

        jLabel34.setText("Font:");
        jDesktopPane11.add(jLabel34);
        jLabel34.setBounds(10, 10, 220, 15);

        jTextFont.setEditable(false);
        jTextFont.setText("font");
        jDesktopPane11.add(jTextFont);
        jTextFont.setBounds(10, 30, 219, 20);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jListFont.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jListFont.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListFont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListFontMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(jListFont);

        jDesktopPane11.add(jScrollPane4);
        jScrollPane4.setBounds(10, 60, 219, 140);

        jLabel36.setText("Style:");
        jDesktopPane11.add(jLabel36);
        jLabel36.setBounds(240, 10, 100, 15);

        jTextStyle.setEditable(false);
        jTextStyle.setText("Plain");
        jDesktopPane11.add(jTextStyle);
        jTextStyle.setBounds(240, 30, 100, 20);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jListStyle.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jListStyle.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Plain", "Bold", "Italic", "Bold Italic" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListStyle.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListStyle.setSelectedIndex(0);
        jListStyle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListStyleMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(jListStyle);

        jDesktopPane11.add(jScrollPane5);
        jScrollPane5.setBounds(240, 60, 100, 140);

        jLabel37.setText("Size:");
        jDesktopPane11.add(jLabel37);
        jLabel37.setBounds(350, 10, 65, 15);

        jTextSize.setText("12");
        jTextSize.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane11.add(jTextSize);
        jTextSize.setBounds(350, 30, 70, 20);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jListSize.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jListSize.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "3", "5", "8", "10", "12", "14", "18", "24", "36", "48" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListSize.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListSize.setSelectedIndex(4);
        jListSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListSizeMousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(jListSize);

        jDesktopPane11.add(jScrollPane8);
        jScrollPane8.setBounds(350, 60, 70, 140);

        jButton34.setText("ok");
        jButton34.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton34MousePressed(evt);
            }
        });
        jDesktopPane11.add(jButton34);
        jButton34.setBounds(350, 290, 70, 20);

        jTextFont1.setEditable(false);
        jTextFont1.setText("use a (ttf) file for Fonts");
        jDesktopPane11.add(jTextFont1);
        jTextFont1.setBounds(10, 320, 410, 19);

        jButton63.setFont(fuente);
        jButton63.setText("...");
        jButton63.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton63.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton63MousePressed(evt);
            }
        });
        jDesktopPane11.add(jButton63);
        jButton63.setBounds(10, 350, 18, 18);

        File_ttf.setFont(fuente);
        File_ttf.setToolTipText("the step to move the atoms selected");
        File_ttf.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        File_ttf.setOpaque(false);
        jDesktopPane11.add(File_ttf);
        File_ttf.setBounds(40, 350, 260, 18);

        jButton40.setText("Load from File");
        jButton40.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton40MousePressed(evt);
            }
        });
        jDesktopPane11.add(jButton40);
        jButton40.setBounds(312, 350, 110, 18);

        org.jdesktop.layout.GroupLayout JFontLayout = new org.jdesktop.layout.GroupLayout(JFont.getContentPane());
        JFont.getContentPane().setLayout(JFontLayout);
        JFontLayout.setHorizontalGroup(
            JFontLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
        );
        JFontLayout.setVerticalGroup(
            JFontLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        jDCalc.setTitle("Rotate and recalate the atoms");
        jDCalc.setBackground(new java.awt.Color(255, 255, 255));
        jDCalc.setResizable(false);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        jDesktopPane2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        Te40.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Te40.setEditable(false);
        Te40.setFont(fuente);
        Te40.setText("Rotate using the positions");
        Te40.setToolTipText("");
        Te40.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te40.setFocusable(false);
        jDesktopPane2.add(Te40);
        Te40.setBounds(66, 4, 180, 20);

        atomo_1.setFont(fuente);
        atomo_1.setText("1");
        atomo_1.setToolTipText("");
        atomo_1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(atomo_1);
        atomo_1.setBounds(4, 28, 58, 18);

        Te42.setEditable(false);
        Te42.setFont(fuente);
        Te42.setText("select this atom for (0,0,0)");
        Te42.setToolTipText("");
        Te42.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te42.setOpaque(false);
        jDesktopPane2.add(Te42);
        Te42.setBounds(66, 28, 180, 18);

        atomo_2.setFont(fuente);
        atomo_2.setText("2");
        atomo_2.setToolTipText("");
        atomo_2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(atomo_2);
        atomo_2.setBounds(4, 50, 58, 18);

        Te41.setEditable(false);
        Te41.setFont(fuente);
        Te41.setText("select this atom for Axis-Z");
        Te41.setToolTipText("");
        Te41.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te41.setOpaque(false);
        jDesktopPane2.add(Te41);
        Te41.setBounds(66, 50, 180, 18);

        atomo_3.setFont(fuente);
        atomo_3.setText("3");
        atomo_3.setToolTipText("");
        atomo_3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(atomo_3);
        atomo_3.setBounds(4, 72, 58, 18);

        Te43.setEditable(false);
        Te43.setFont(fuente);
        Te43.setText("select this atom for Axis-X");
        Te43.setToolTipText("");
        Te43.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te43.setOpaque(false);
        jDesktopPane2.add(Te43);
        Te43.setBounds(66, 72, 180, 18);

        Te44.setFont(fuente);
        Te44.setText("Load positions");
        Te44.setToolTipText("");
        Te44.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te44.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te44MousePressed(evt);
            }
        });
        jDesktopPane2.add(Te44);
        Te44.setBounds(250, 94, 126, 18);

        Giro_centro.setFont(fuente);
        Giro_centro.setToolTipText("");
        Giro_centro.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Giro_centro);
        Giro_centro.setBounds(250, 28, 280, 18);

        Te45.setEditable(false);
        Te45.setFont(fuente);
        Te45.setText("(0,0,0) Center");
        Te45.setToolTipText("");
        Te45.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te45.setOpaque(false);
        jDesktopPane2.add(Te45);
        Te45.setBounds(106, 140, 140, 18);

        Giro_Z.setFont(fuente);
        Giro_Z.setToolTipText("");
        Giro_Z.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Giro_Z);
        Giro_Z.setBounds(250, 50, 280, 18);

        Te46.setEditable(false);
        Te46.setFont(fuente);
        Te46.setText("Axis-Z");
        Te46.setToolTipText("");
        Te46.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te46.setOpaque(false);
        jDesktopPane2.add(Te46);
        Te46.setBounds(106, 162, 140, 18);

        Te47.setEditable(false);
        Te47.setFont(fuente);
        Te47.setText("Axis-X");
        Te47.setToolTipText("");
        Te47.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te47.setOpaque(false);
        jDesktopPane2.add(Te47);
        Te47.setBounds(106, 184, 140, 18);

        Giro_X.setFont(fuente);
        Giro_X.setToolTipText("");
        Giro_X.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Giro_X);
        Giro_X.setBounds(250, 72, 280, 18);

        Te48.setFont(fuente);
        Te48.setText("rotate atoms and lvs");
        Te48.setToolTipText("");
        Te48.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te48.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te48MousePressed(evt);
            }
        });
        jDesktopPane2.add(Te48);
        Te48.setBounds(380, 94, 150, 18);

        Te49.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Te49.setFont(fuente);
        Te49.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Te49.setText("atoms ");
        Te49.setToolTipText("");
        Te49.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Te49);
        Te49.setBounds(4, 4, 58, 20);

        Giro_centro1.setFont(fuente);
        Giro_centro1.setText("0 0 0");
        Giro_centro1.setToolTipText("");
        Giro_centro1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Giro_centro1);
        Giro_centro1.setBounds(4, 140, 98, 18);

        Giro_Z1.setFont(fuente);
        Giro_Z1.setText("0 0 1");
        Giro_Z1.setToolTipText("");
        Giro_Z1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Giro_Z1);
        Giro_Z1.setBounds(4, 162, 98, 18);

        Giro_X1.setFont(fuente);
        Giro_X1.setText("1 0 0 ");
        Giro_X1.setToolTipText("");
        Giro_X1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Giro_X1);
        Giro_X1.setBounds(4, 184, 98, 18);

        Giro_centro2.setFont(fuente);
        Giro_centro2.setToolTipText("");
        Giro_centro2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Giro_centro2);
        Giro_centro2.setBounds(250, 140, 280, 18);

        Giro_Z2.setFont(fuente);
        Giro_Z2.setToolTipText("");
        Giro_Z2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Giro_Z2);
        Giro_Z2.setBounds(250, 162, 280, 18);

        Giro_X2.setFont(fuente);
        Giro_X2.setToolTipText("");
        Giro_X2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(Giro_X2);
        Giro_X2.setBounds(250, 184, 280, 18);

        Te50.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Te50.setEditable(false);
        Te50.setFont(fuente);
        Te50.setText("Rotate using directions");
        Te50.setToolTipText("");
        Te50.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te50.setFocusable(false);
        jDesktopPane2.add(Te50);
        Te50.setBounds(106, 116, 140, 20);

        atomo_5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        atomo_5.setFont(fuente);
        atomo_5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        atomo_5.setText("atoms ");
        atomo_5.setToolTipText("");
        atomo_5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane2.add(atomo_5);
        atomo_5.setBounds(4, 116, 98, 20);

        Te51.setFont(fuente);
        Te51.setText("Load directions");
        Te51.setToolTipText("");
        Te51.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te51.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te51MousePressed(evt);
            }
        });
        jDesktopPane2.add(Te51);
        Te51.setBounds(250, 206, 126, 18);

        Te52.setFont(fuente);
        Te52.setText("rotate atoms and lvs");
        Te52.setToolTipText("");
        Te52.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te52.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te52MousePressed(evt);
            }
        });
        jDesktopPane2.add(Te52);
        Te52.setBounds(380, 206, 150, 18);

        jDesktopPane3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        CalcX.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        CalcX.setText("x");
        CalcX.setToolTipText("");
        CalcX.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane3.add(CalcX);
        CalcX.setBounds(48, 4, 484, 18);

        Tdespl33.setEditable(false);
        Tdespl33.setFont(fuente);
        Tdespl33.setText("  X = ");
        Tdespl33.setToolTipText("");
        Tdespl33.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl33.setOpaque(false);
        jDesktopPane3.add(Tdespl33);
        Tdespl33.setBounds(4, 4, 40, 18);

        Tdespl34.setEditable(false);
        Tdespl34.setFont(fuente);
        Tdespl34.setText("  Y = ");
        Tdespl34.setToolTipText("");
        Tdespl34.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl34.setOpaque(false);
        jDesktopPane3.add(Tdespl34);
        Tdespl34.setBounds(4, 26, 40, 18);

        Tdespl35.setEditable(false);
        Tdespl35.setFont(fuente);
        Tdespl35.setText("  Z = ");
        Tdespl35.setToolTipText("");
        Tdespl35.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl35.setOpaque(false);
        jDesktopPane3.add(Tdespl35);
        Tdespl35.setBounds(4, 48, 40, 18);

        CalcY.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        CalcY.setText("y");
        CalcY.setToolTipText("");
        CalcY.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        CalcY.setDragEnabled(true);
        jDesktopPane3.add(CalcY);
        CalcY.setBounds(48, 26, 484, 18);

        CalcZ.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        CalcZ.setText("z");
        CalcZ.setToolTipText("");
        CalcZ.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane3.add(CalcZ);
        CalcZ.setBounds(48, 48, 484, 18);

        Te54.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Te54.setText("accept");
        Te54.setToolTipText("");
        Te54.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te54.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te54MousePressed(evt);
            }
        });
        jDesktopPane3.add(Te54);
        Te54.setBounds(450, 70, 80, 20);

        Tdespl37.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl37.setEditable(false);
        Tdespl37.setFont(fuente);
        Tdespl37.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl37.setText("Examples");
        Tdespl37.setToolTipText("");
        Tdespl37.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl37.setFocusable(false);
        jDesktopPane3.add(Tdespl37);
        Tdespl37.setBounds(186, 182, 316, 20);

        Te36.setEditable(false);
        Te36.setFont(fuente);
        Te36.setText("rescalate atoms positions and lvs");
        Te36.setToolTipText("");
        Te36.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te36.setOpaque(false);
        jDesktopPane3.add(Te36);
        Te36.setBounds(186, 94, 210, 18);

        Te37.setEditable(false);
        Te37.setFont(fuente);
        Te37.setText("rotate atoms ans lvs (Z) with angle");
        Te37.setToolTipText("");
        Te37.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te37.setOpaque(false);
        jDesktopPane3.add(Te37);
        Te37.setBounds(186, 116, 210, 18);

        Te38.setEditable(false);
        Te38.setFont(fuente);
        Te38.setText("rotate atoms ans lvs (Y) with angle");
        Te38.setToolTipText("");
        Te38.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te38.setOpaque(false);
        jDesktopPane3.add(Te38);
        Te38.setBounds(186, 138, 210, 18);

        Te39.setEditable(false);
        Te39.setFont(fuente);
        Te39.setText("rotate atoms ans lvs (X) with angle");
        Te39.setToolTipText("");
        Te39.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te39.setOpaque(false);
        jDesktopPane3.add(Te39);
        Te39.setBounds(186, 160, 210, 18);

        resText.setFont(fuente);
        resText.setText("4.0/2.0");
        resText.setToolTipText("example (4/2 = 2)");
        resText.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane3.add(resText);
        resText.setBounds(400, 94, 58, 18);

        rot_TextZ.setFont(fuente);
        rot_TextZ.setText("90");
        rot_TextZ.setToolTipText("angle (º)");
        rot_TextZ.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane3.add(rot_TextZ);
        rot_TextZ.setBounds(400, 116, 58, 18);

        rot_TextY.setFont(fuente);
        rot_TextY.setText("60");
        rot_TextY.setToolTipText("angle (º)");
        rot_TextY.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane3.add(rot_TextY);
        rot_TextY.setBounds(400, 138, 58, 18);

        rot_TextX.setFont(fuente);
        rot_TextX.setText("45");
        rot_TextX.setToolTipText("angle (º)");
        rot_TextX.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane3.add(rot_TextX);
        rot_TextX.setBounds(400, 160, 58, 18);

        Te55.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Te55.setText("test");
        Te55.setToolTipText("");
        Te55.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te55.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te55MousePressed(evt);
            }
        });
        jDesktopPane3.add(Te55);
        Te55.setBounds(462, 94, 40, 18);

        Te56.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Te56.setText("test");
        Te56.setToolTipText("");
        Te56.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te56.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te56MousePressed(evt);
            }
        });
        jDesktopPane3.add(Te56);
        Te56.setBounds(462, 116, 40, 18);

        Te57.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Te57.setText("test");
        Te57.setToolTipText("");
        Te57.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te57.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te57MousePressed(evt);
            }
        });
        jDesktopPane3.add(Te57);
        Te57.setBounds(462, 138, 40, 18);

        Te58.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Te58.setText("test");
        Te58.setToolTipText("");
        Te58.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te58.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te58MousePressed(evt);
            }
        });
        jDesktopPane3.add(Te58);
        Te58.setBounds(462, 160, 40, 18);

        Tdespl42.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl42.setEditable(false);
        Tdespl42.setFont(fuente);
        Tdespl42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl42.setToolTipText("");
        Tdespl42.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl42.setFocusable(false);
        jDesktopPane3.add(Tdespl42);
        Tdespl42.setBounds(506, 94, 22, 108);

        Te59.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        Te59.setText("help");
        Te59.setToolTipText("");
        Te59.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te59.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te59MousePressed(evt);
            }
        });
        jDesktopPane3.add(Te59);
        Te59.setBounds(4, 184, 80, 18);

        Tdespl43.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Tdespl43.setEditable(false);
        Tdespl43.setFont(fuente);
        Tdespl43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl43.setToolTipText("");
        Tdespl43.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl43.setFocusable(false);
        jDesktopPane3.add(Tdespl43);
        Tdespl43.setBounds(160, 94, 22, 108);

        org.jdesktop.layout.GroupLayout jDCalcLayout = new org.jdesktop.layout.GroupLayout(jDCalc.getContentPane());
        jDCalc.getContentPane().setLayout(jDCalcLayout);
        jDCalcLayout.setHorizontalGroup(
            jDCalcLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jDesktopPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 536, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jDesktopPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 536, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        jDCalcLayout.setVerticalGroup(
            jDCalcLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDCalcLayout.createSequentialGroup()
                .add(jDCalcLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jDesktopPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jDesktopPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        inputFileBabel.setFont(fuente);
        inputFileBabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        inputFileBabel.setEnabled(false);
        jDesktopPane5.add(inputFileBabel);
        inputFileBabel.setBounds(134, 42, 328, 18);

        buttonOpenBabel.setFont(fuente);
        buttonOpenBabel.setText("...");
        buttonOpenBabel.setToolTipText("");
        buttonOpenBabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        buttonOpenBabel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        buttonOpenBabel.setPreferredSize(new java.awt.Dimension(16, 20));
        buttonOpenBabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonOpenBabelMousePressed(evt);
            }
        });
        jDesktopPane5.add(buttonOpenBabel);
        buttonOpenBabel.setBounds(464, 42, 18, 18);

        jLabel5.setFont(fuente);
        jLabel5.setText("<input-type>");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(jLabel5);
        jLabel5.setBounds(134, 62, 176, 18);

        jLabel7.setFont(fuente);
        jLabel7.setText("<output-type>");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(jLabel7);
        jLabel7.setBounds(134, 102, 176, 18);

        jLabel8.setFont(fuente);
        jLabel8.setText("babel [-i<input-type>] <name> [-o<output-type>] <name>");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane5.add(jLabel8);
        jLabel8.setBounds(2, 122, 480, 18);

        jComboBoxBabelRead.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxBabelRead.setFont(fuente);
        jComboBoxBabelRead.setMaximumRowCount(20);
        jDesktopPane5.add(jComboBoxBabelRead);
        jComboBoxBabelRead.setBounds(312, 62, 170, 18);

        jComboBoxBabelWrite.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxBabelWrite.setFont(fuente);
        jComboBoxBabelWrite.setMaximumRowCount(20);
        jDesktopPane5.add(jComboBoxBabelWrite);
        jComboBoxBabelWrite.setBounds(312, 102, 170, 18);

        jButton43.setFont(fuente);
        jButton43.setText("get output file");
        jButton43.setToolTipText("");
        jButton43.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton43.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton43.setPreferredSize(new java.awt.Dimension(16, 20));
        jButton43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton43MousePressed(evt);
            }
        });
        jDesktopPane5.add(jButton43);
        jButton43.setBounds(134, 142, 348, 18);

        Tdespl2.setEditable(false);
        Tdespl2.setFont(fuente);
        Tdespl2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl2.setText("INPUT");
        Tdespl2.setToolTipText("");
        Tdespl2.setAutoscrolls(false);
        Tdespl2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl2.setFocusable(false);
        Tdespl2.setRequestFocusEnabled(false);
        jDesktopPane5.add(Tdespl2);
        Tdespl2.setBounds(2, 2, 480, 18);

        jRadioButtonXeo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupExport.add(jRadioButtonXeo);
        jRadioButtonXeo.setFont(fuente);
        jRadioButtonXeo.setSelected(true);
        jRadioButtonXeo.setText("Load structure (actual project load in xeo)");
        jRadioButtonXeo.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jRadioButtonXeo.setBorderPainted(true);
        jRadioButtonXeo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane5.add(jRadioButtonXeo);
        jRadioButtonXeo.setBounds(2, 22, 480, 18);

        jRadioButtonBabel.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupExport.add(jRadioButtonBabel);
        jRadioButtonBabel.setFont(fuente);
        jRadioButtonBabel.setText("Used input file");
        jRadioButtonBabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jRadioButtonBabel.setBorderPainted(true);
        jRadioButtonBabel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButtonBabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonBabelMouseClicked(evt);
            }
        });
        jDesktopPane5.add(jRadioButtonBabel);
        jRadioButtonBabel.setBounds(2, 42, 130, 18);

        Tdespl20.setEditable(false);
        Tdespl20.setFont(fuente);
        Tdespl20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl20.setText("OUTPUT");
        Tdespl20.setToolTipText("");
        Tdespl20.setAutoscrolls(false);
        Tdespl20.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl20.setFocusable(false);
        Tdespl20.setRequestFocusEnabled(false);
        jDesktopPane5.add(Tdespl20);
        Tdespl20.setBounds(2, 82, 480, 18);

        org.jdesktop.layout.GroupLayout jbabelLayout = new org.jdesktop.layout.GroupLayout(jbabel.getContentPane());
        jbabel.getContentPane().setLayout(jbabelLayout);
        jbabelLayout.setHorizontalGroup(
            jbabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        jbabelLayout.setVerticalGroup(
            jbabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );

        jDesktopPane1.setAlignmentX(0.0F);
        jDesktopPane1.setAlignmentY(0.0F);

        Te19.setEditable(false);
        Te19.setFont(fuente);
        Te19.setText("Options");
        Te19.setToolTipText("the step to move the atoms selected");
        Te19.setAutoscrolls(false);
        Te19.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te19.setFocusable(false);
        Te19.setRequestFocusEnabled(false);
        jDesktopPane1.add(Te19);
        Te19.setBounds(2, 2, 198, 20);

        Te20.setEditable(false);
        Te20.setFont(fuente);
        Te20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Te20.setText("perspective");
        Te20.setToolTipText("the step to move the atoms selected");
        Te20.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te20.setOpaque(false);
        Te20.setScrollOffset(2);
        jDesktopPane1.add(Te20);
        Te20.setBounds(2, 24, 198, 18);

        PERS.setToolTipText("");
        PERS.setValue(100);
        PERS.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        PERS.setEnabled(false);
        PERS.setOpaque(false);
        PERS.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PERSMouseDragged(evt);
            }
        });
        jDesktopPane1.add(PERS);
        PERS.setBounds(2, 44, 198, 20);

        jButton30.setText("...");
        jButton30.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton30MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton30);
        jButton30.setBounds(2, 66, 18, 18);

        Fuente.setFont(fuente);
        Fuente.setText("font");
        Fuente.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(Fuente);
        Fuente.setBounds(22, 66, 178, 18);

        verIconosAtomos.setBackground(new java.awt.Color(255, 255, 255));
        verIconosAtomos.setFont(fuente);
        verIconosAtomos.setToolTipText("use the covalent radio");
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
        jDesktopPane1.add(verIconosAtomos);
        verIconosAtomos.setBounds(2, 86, 18, 18);

        Te22.setFont(fuente);
        Te22.setText("use icons for the atoms");
        Te22.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(Te22);
        Te22.setBounds(22, 86, 178, 18);

        jButton18.setText("...");
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton18MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton18);
        jButton18.setBounds(2, 126, 18, 18);

        colorFondo.setBackground(new java.awt.Color(255, 255, 255));
        colorFondo.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));

        org.jdesktop.layout.GroupLayout colorFondoLayout = new org.jdesktop.layout.GroupLayout(colorFondo);
        colorFondo.setLayout(colorFondoLayout);
        colorFondoLayout.setHorizontalGroup(
            colorFondoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorFondoLayout.setVerticalGroup(
            colorFondoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorFondo);
        colorFondo.setBounds(22, 126, 18, 18);

        Te23.setFont(fuente);
        Te23.setText("background");
        Te23.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(Te23);
        Te23.setBounds(42, 126, 158, 18);

        colorEjes.setBackground(new java.awt.Color(166, 166, 167));
        colorEjes.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));

        org.jdesktop.layout.GroupLayout colorEjesLayout = new org.jdesktop.layout.GroupLayout(colorEjes);
        colorEjes.setLayout(colorEjesLayout);
        colorEjesLayout.setHorizontalGroup(
            colorEjesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorEjesLayout.setVerticalGroup(
            colorEjesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorEjes);
        colorEjes.setBounds(22, 146, 18, 18);

        verEjes.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        verEjes.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        verEjes.setBorderPainted(true);
        verEjes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verEjes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        verEjes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        verEjes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verEjesMouseClicked(evt);
            }
        });
        jDesktopPane1.add(verEjes);
        verEjes.setBounds(42, 146, 18, 18);

        jButton20.setText("...");
        jButton20.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton20MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton20);
        jButton20.setBounds(2, 146, 18, 18);

        Te24.setFont(fuente);
        Te24.setText("axis");
        Te24.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(Te24);
        Te24.setBounds(62, 146, 138, 18);

        Te25.setEditable(false);
        Te25.setFont(fuente);
        Te25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Te25.setText("change center");
        Te25.setToolTipText("the step to move the atoms selected");
        Te25.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te25.setOpaque(false);
        Te25.setScrollOffset(2);
        jDesktopPane1.add(Te25);
        Te25.setBounds(2, 166, 198, 18);

        jT_cx.setFont(fuente);
        jT_cx.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jT_cx.setText("0.0");
        jT_cx.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(jT_cx);
        jT_cx.setBounds(22, 186, 58, 18);

        jT_cy.setFont(fuente);
        jT_cy.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jT_cy.setText("0.0");
        jT_cy.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(jT_cy);
        jT_cy.setBounds(82, 186, 58, 18);

        jT_cz.setFont(fuente);
        jT_cz.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jT_cz.setText("0.0");
        jT_cz.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(jT_cz);
        jT_cz.setBounds(142, 186, 58, 18);

        jButton21.setText("...");
        jButton21.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton21.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton21MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton21);
        jButton21.setBounds(2, 186, 18, 18);

        jButton23.setText("...");
        jButton23.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton23);
        jButton23.setBounds(2, 206, 18, 18);

        colorArrows.setBackground(new java.awt.Color(166, 166, 167));
        colorArrows.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        org.jdesktop.layout.GroupLayout colorArrowsLayout = new org.jdesktop.layout.GroupLayout(colorArrows);
        colorArrows.setLayout(colorArrowsLayout);
        colorArrowsLayout.setHorizontalGroup(
            colorArrowsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorArrowsLayout.setVerticalGroup(
            colorArrowsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane1.add(colorArrows);
        colorArrows.setBounds(22, 206, 18, 18);

        seeArrows.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        seeArrows.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeArrows.setBorderPainted(true);
        seeArrows.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeArrows.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeArrows.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeArrows.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeArrowsMouseClicked(evt);
            }
        });
        jDesktopPane1.add(seeArrows);
        seeArrows.setBounds(42, 206, 18, 18);

        seeArrowsColor.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        seeArrowsColor.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeArrowsColor.setBorderPainted(true);
        seeArrowsColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeArrowsColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeArrowsColor.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane1.add(seeArrowsColor);
        seeArrowsColor.setBounds(2, 226, 18, 18);

        rescalateArrow.setText("1");
        rescalateArrow.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(rescalateArrow);
        rescalateArrow.setBounds(22, 246, 38, 18);

        jButton9.setText("...");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton9MousePressed(evt);
            }
        });
        jDesktopPane1.add(jButton9);
        jButton9.setBounds(2, 246, 18, 18);

        Te27.setFont(fuente);
        Te27.setText("diferents colors for arrows");
        Te27.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(Te27);
        Te27.setBounds(22, 226, 178, 18);

        Te28.setFont(fuente);
        Te28.setText("rescalate arrows");
        Te28.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(Te28);
        Te28.setBounds(62, 246, 138, 18);

        Te32.setFont(fuente);
        Te32.setText("see arrows");
        Te32.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(Te32);
        Te32.setBounds(62, 206, 138, 18);

        Te61.setFont(fuente);
        Te61.setText("grey scale");
        Te61.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane1.add(Te61);
        Te61.setBounds(22, 106, 178, 18);

        escalaGrises.setBackground(new java.awt.Color(255, 255, 255));
        escalaGrises.setFont(fuente);
        escalaGrises.setToolTipText("use the covalent radio");
        escalaGrises.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        escalaGrises.setBorderPainted(true);
        escalaGrises.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        escalaGrises.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        escalaGrises.setMargin(new java.awt.Insets(0, 0, 0, 0));
        escalaGrises.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                escalaGrisesMouseClicked(evt);
            }
        });
        jDesktopPane1.add(escalaGrises);
        escalaGrises.setBounds(2, 106, 18, 18);

        Te72.setEditable(false);
        Te72.setFont(fuente);
        Te72.setText("Load xyz (trajectory)");
        Te72.setToolTipText("the step to move the atoms selected");
        Te72.setAutoscrolls(false);
        Te72.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te72.setFocusable(false);
        Te72.setRequestFocusEnabled(false);
        jDesktopPane13.add(Te72);
        Te72.setBounds(0, 0, 198, 18);

        jButton59.setText("...");
        jButton59.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton59MousePressed(evt);
            }
        });
        jDesktopPane13.add(jButton59);
        jButton59.setBounds(0, 20, 18, 18);

        xyzFileTr.setFont(fuente);
        xyzFileTr.setText("*.xyz file (xyz format)");
        xyzFileTr.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane13.add(xyzFileTr);
        xyzFileTr.setBounds(20, 20, 178, 18);

        Te73.setEditable(false);
        Te73.setFont(fuente);
        Te73.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Te73.setText("show the trajectory of the atoms:");
        Te73.setToolTipText("");
        Te73.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te73.setOpaque(false);
        jDesktopPane13.add(Te73);
        Te73.setBounds(0, 40, 198, 18);

        xyztAtomsTr.setFont(fuente);
        xyztAtomsTr.setText("1 2 3");
        xyztAtomsTr.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane13.add(xyztAtomsTr);
        xyztAtomsTr.setBounds(0, 60, 198, 18);

        jComboBox.setFont(fuente);
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Trayectoria 1", "Trayectoria 2", "Trayectoria 3", "Trayectoria 4", "Trayectoria 5", "Trayectoria 6", "Trayectoria 7", "Trayectoria 8", "Trayectoria 9", "Trayectoria 10" }));
        jComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.focus")));
        jComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboBoxMouseReleased(evt);
            }
        });
        jComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });
        jDesktopPane13.add(jComboBox);
        jComboBox.setBounds(40, 80, 124, 18);

        jButton47.setText("...");
        jButton47.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton47.setFocusable(false);
        jButton47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton47MousePressed(evt);
            }
        });
        jDesktopPane13.add(jButton47);
        jButton47.setBounds(0, 80, 17, 17);

        colorLabel.setBackground(new java.awt.Color(255, 51, 51));
        colorLabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        colorLabel.setFocusable(false);

        org.jdesktop.layout.GroupLayout colorLabelLayout = new org.jdesktop.layout.GroupLayout(colorLabel);
        colorLabel.setLayout(colorLabelLayout);
        colorLabelLayout.setHorizontalGroup(
            colorLabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );
        colorLabelLayout.setVerticalGroup(
            colorLabelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 16, Short.MAX_VALUE)
        );

        jDesktopPane13.add(colorLabel);
        colorLabel.setBounds(20, 80, 0, 0);

        ancho.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ancho.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
        ancho.setToolTipText("");
        ancho.setAlignmentX(0.0F);
        ancho.setAlignmentY(0.0F);
        ancho.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        ancho.setOpaque(false);
        jDesktopPane13.add(ancho);
        ancho.setBounds(166, 80, 32, 18);

        Tdespl65.setEditable(false);
        Tdespl65.setFont(fuente);
        Tdespl65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tdespl65.setText("remapping lvs");
        Tdespl65.setToolTipText("");
        Tdespl65.setAlignmentX(0.0F);
        Tdespl65.setAlignmentY(0.0F);
        Tdespl65.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Tdespl65.setFocusable(false);
        jDesktopPane13.add(Tdespl65);
        Tdespl65.setBounds(0, 100, 96, 17);

        jlvs_1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlvs_1.setAlignmentX(0.0F);
        jlvs_1.setAlignmentY(0.0F);
        jlvs_1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jlvs_1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jlvs_1StateChanged(evt);
            }
        });
        jDesktopPane13.add(jlvs_1);
        jlvs_1.setBounds(98, 100, 32, 18);

        jlvs_2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlvs_2.setAlignmentX(0.0F);
        jlvs_2.setAlignmentY(0.0F);
        jlvs_2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jlvs_2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jlvs_2StateChanged(evt);
            }
        });
        jDesktopPane13.add(jlvs_2);
        jlvs_2.setBounds(132, 100, 32, 18);

        jlvs_3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlvs_3.setAlignmentX(0.0F);
        jlvs_3.setAlignmentY(0.0F);
        jlvs_3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jlvs_3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jlvs_3StateChanged(evt);
            }
        });
        jDesktopPane13.add(jlvs_3);
        jlvs_3.setBounds(166, 100, 32, 18);

        jtrlasttext.setEditable(false);
        jtrlasttext.setFont(fuente);
        jtrlasttext.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtrlasttext.setText("last step");
        jtrlasttext.setToolTipText("");
        jtrlasttext.setAlignmentX(0.0F);
        jtrlasttext.setAlignmentY(0.0F);
        jtrlasttext.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.focus")));
        jtrlasttext.setFocusable(false);
        jtrlasttext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtrlasttextActionPerformed(evt);
            }
        });
        jDesktopPane13.add(jtrlasttext);
        jtrlasttext.setBounds(0, 140, 84, 17);

        jtrfisttext.setEditable(false);
        jtrfisttext.setFont(fuente);
        jtrfisttext.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtrfisttext.setText("first step");
        jtrfisttext.setToolTipText("");
        jtrfisttext.setAlignmentX(0.0F);
        jtrfisttext.setAlignmentY(0.0F);
        jtrfisttext.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.focus")));
        jtrfisttext.setFocusable(false);
        jtrfisttext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtrfisttextActionPerformed(evt);
            }
        });
        jDesktopPane13.add(jtrfisttext);
        jtrfisttext.setBounds(0, 120, 84, 17);

        jtrlast.setFont(fuente);
        jtrlast.setText("2147483647");
        jtrlast.setAlignmentX(0.0F);
        jtrlast.setAlignmentY(0.0F);
        jtrlast.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.focus")));
        jDesktopPane13.add(jtrlast);
        jtrlast.setBounds(86, 140, 112, 17);

        jtrfirst.setFont(fuente);
        jtrfirst.setText("0");
        jtrfirst.setAlignmentX(0.0F);
        jtrfirst.setAlignmentY(0.0F);
        jtrfirst.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.focus")));
        jDesktopPane13.add(jtrfirst);
        jtrfirst.setBounds(86, 120, 112, 17);

        Te78.setFont(fuente);
        Te78.setText("preview");
        Te78.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te78.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te78MousePressed(evt);
            }
        });
        jDesktopPane13.add(Te78);
        Te78.setBounds(134, 160, 64, 18);

        xyzTr.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        xyzTr.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        xyzTr.setBorderPainted(true);
        xyzTr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xyzTr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xyzTr.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xyzTr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xyzTrMouseClicked(evt);
            }
        });
        jDesktopPane13.add(xyzTr);
        xyzTr.setBounds(50, 160, 18, 18);

        Te77.setFont(fuente);
        Te77.setText("visible");
        Te77.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane13.add(Te77);
        Te77.setBounds(70, 160, 62, 18);

        Te79.setFont(fuente);
        Te79.setText("accept");
        Te79.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te79.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te79.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te79MousePressed(evt);
            }
        });
        jDesktopPane13.add(Te79);
        Te79.setBounds(0, 160, 50, 18);

        jDesktopPane1.add(jDesktopPane13);
        jDesktopPane13.setBounds(2, 266, 200, 180);

        Te29.setEditable(false);
        Te29.setFont(fuente);
        Te29.setText("Load file (x,y,z,t) (surface)");
        Te29.setToolTipText("the step to move the atoms selected");
        Te29.setAutoscrolls(false);
        Te29.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te29.setFocusable(false);
        Te29.setRequestFocusEnabled(false);
        jDesktopPane12.add(Te29);
        Te29.setBounds(0, 0, 198, 18);

        xyzt.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        xyzt.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        xyzt.setBorderPainted(true);
        xyzt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xyzt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xyzt.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xyzt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xyztMouseClicked(evt);
            }
        });
        jDesktopPane12.add(xyzt);
        xyzt.setBounds(0, 20, 18, 18);

        Te30.setFont(fuente);
        Te30.setText("visible");
        Te30.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane12.add(Te30);
        Te30.setBounds(20, 20, 58, 18);

        Te31.setFont(fuente);
        Te31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Te31.setText("index :");
        Te31.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane12.add(Te31);
        Te31.setBounds(80, 20, 58, 18);

        pixel.setFont(fuente);
        pixel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pixel.setText("2");
        pixel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane12.add(pixel);
        pixel.setBounds(140, 20, 58, 18);

        xyztFile.setFont(fuente);
        xyztFile.setText("xyzt file (4 columns)");
        xyztFile.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane12.add(xyztFile);
        xyztFile.setBounds(20, 40, 178, 18);

        color_inter_2.setBackground(new java.awt.Color(255, 255, 255));
        color_inter_2.setForeground(new java.awt.Color(153, 153, 255));
        color_inter_2.setText("...");
        color_inter_2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        color_inter_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                color_inter_2MousePressed(evt);
            }
        });
        jDesktopPane12.add(color_inter_2);
        color_inter_2.setBounds(160, 60, 18, 18);

        color_fin.setBackground(new java.awt.Color(255, 255, 255));
        color_fin.setForeground(new java.awt.Color(153, 153, 255));
        color_fin.setText("...");
        color_fin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        color_fin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                color_finMousePressed(evt);
            }
        });
        jDesktopPane12.add(color_fin);
        color_fin.setBounds(180, 60, 18, 18);

        color_ini.setBackground(new java.awt.Color(255, 255, 255));
        color_ini.setForeground(new java.awt.Color(153, 153, 255));
        color_ini.setText("...");
        color_ini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        color_ini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                color_iniMousePressed(evt);
            }
        });
        jDesktopPane12.add(color_ini);
        color_ini.setBounds(0, 60, 18, 18);

        color_inter_1.setBackground(new java.awt.Color(255, 255, 255));
        color_inter_1.setForeground(new java.awt.Color(153, 153, 255));
        color_inter_1.setText("...");
        color_inter_1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        color_inter_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                color_inter_1MousePressed(evt);
            }
        });
        jDesktopPane12.add(color_inter_1);
        color_inter_1.setBounds(20, 60, 18, 18);

        Sinter1.setBackground(new java.awt.Color(255, 255, 255));
        Sinter1.setValue(10);
        Sinter1.setAlignmentX(0.0F);
        Sinter1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Sinter1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Sinter1MouseDragged(evt);
            }
        });
        jDesktopPane12.add(Sinter1);
        Sinter1.setBounds(0, 80, 198, 18);

        Clabel.setBackground(new java.awt.Color(255, 255, 255));
        Clabel.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jDesktopPane12.add(Clabel);
        Clabel.setBounds(0, 100, 198, 18);

        Sinter2.setBackground(new java.awt.Color(255, 255, 255));
        Sinter2.setValue(40);
        Sinter2.setAlignmentX(0.0F);
        Sinter2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Sinter2.setOpaque(false);
        Sinter2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Sinter2MouseDragged(evt);
            }
        });
        jDesktopPane12.add(Sinter2);
        Sinter2.setBounds(0, 120, 198, 18);

        Te53.setFont(fuente);
        Te53.setText("accept");
        Te53.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Te53.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Te53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te53MousePressed(evt);
            }
        });
        jDesktopPane12.add(Te53);
        Te53.setBounds(0, 140, 50, 18);

        seeBordes.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        seeBordes.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        seeBordes.setBorderPainted(true);
        seeBordes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeBordes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeBordes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jDesktopPane12.add(seeBordes);
        seeBordes.setBounds(52, 140, 18, 18);

        Te26.setFont(fuente);
        Te26.setText("don't see borders");
        Te26.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDesktopPane12.add(Te26);
        Te26.setBounds(72, 140, 126, 18);

        jButton58.setText("...");
        jButton58.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton58MousePressed(evt);
            }
        });
        jDesktopPane12.add(jButton58);
        jButton58.setBounds(0, 40, 18, 18);

        org.jdesktop.layout.GroupLayout OptionsLayout = new org.jdesktop.layout.GroupLayout(Options.getContentPane());
        Options.getContentPane().setLayout(OptionsLayout);
        OptionsLayout.setHorizontalGroup(
            OptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 202, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jDesktopPane12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        OptionsLayout.setVerticalGroup(
            OptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(OptionsLayout.createSequentialGroup()
                .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 445, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 1, Short.MAX_VALUE)
                .add(jDesktopPane12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("xeo  (project management for nanostructures)");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jSplitForm.setBorder(null);
        jSplitForm.setDividerLocation(180);
        jSplitForm.setDividerSize(4);
        jSplitForm.setFocusable(false);

        jScrollPane2.setBorder(null);

        TABXEO.setBackground(new java.awt.Color(255, 255, 255));
        TABXEO.setForeground(new java.awt.Color(255, 255, 255));

        screen5.setToolTipText("Load some project or file");
        screen5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        screen5.setRequestFocusEnabled(false);
        screen5.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                screen5MouseWheelMoved(evt);
            }
        });
        screen5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                screen5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                screen5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                screen5MouseReleased(evt);
            }
        });
        screen5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                screen5MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                screen5MouseMoved(evt);
            }
        });
        screen5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                screen5KeyPressed(evt);
            }
        });

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        xyz_end.setFont(fuente);
        xyz_end.setText("final");
        xyz_end.setToolTipText("the final step or at least the last load step");
        xyz_end.setFocusable(false);
        xyz_end.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        Bt0.setFont(fuente);
        Bt0.setText("options");
        Bt0.setToolTipText("use more memory/velocity");
        Bt0.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Bt0.setEnabled(false);
        Bt0.setFocusable(false);
        Bt0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bt0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Bt0MousePressed(evt);
            }
        });

        Bt1.setFont(fuente);
        Bt1.setText("stop");
        Bt1.setToolTipText("if you are loading a file this stop the thread");
        Bt1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Bt1.setEnabled(false);
        Bt1.setFocusable(false);
        Bt1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Bt1MousePressed(evt);
            }
        });

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabelXYZ.setEnabled(false);
        jLabelXYZ.setFocusable(false);
        jLabelXYZ.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabelXYZMouseDragged(evt);
            }
        });
        jLabelXYZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelXYZMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelXYZMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelXYZMouseReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel17Layout = new org.jdesktop.layout.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelXYZ, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabelXYZ, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        openB_xyz.setFont(new Font(fuente.getName(),Font.BOLD,fuente.getSize()));
        openB_xyz.setText("...");
        openB_xyz.setToolTipText("Open (xyz) file");
        openB_xyz.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        openB_xyz.setFocusable(false);
        openB_xyz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                openB_xyzMousePressed(evt);
            }
        });
        jDesktopPane4.add(openB_xyz);
        openB_xyz.setBounds(4, 24, 20, 20);

        PlayB.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        PlayB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/play.gif"))); // NOI18N
        PlayB.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        PlayB.setEnabled(false);
        PlayB.setFocusable(false);
        PlayB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PlayB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        PlayB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PlayBMousePressed(evt);
            }
        });
        jDesktopPane4.add(PlayB);
        PlayB.setBounds(30, 24, 20, 20);

        xyz_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/uno_menos.gif"))); // NOI18N
        xyz_menos.setToolTipText("step back");
        xyz_menos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        xyz_menos.setEnabled(false);
        xyz_menos.setFocusable(false);
        xyz_menos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xyz_menos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xyz_menos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xyz_menosMousePressed(evt);
            }
        });
        jDesktopPane4.add(xyz_menos);
        xyz_menos.setBounds(50, 24, 20, 20);

        xyz_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/uno_mas.gif"))); // NOI18N
        xyz_mas.setToolTipText("step forward");
        xyz_mas.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        xyz_mas.setEnabled(false);
        xyz_mas.setFocusable(false);
        xyz_mas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xyz_mas.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xyz_mas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xyz_masMousePressed(evt);
            }
        });
        jDesktopPane4.add(xyz_mas);
        xyz_mas.setBounds(70, 24, 20, 20);

        xyz_step.setFont(fuente);
        xyz_step.setText("0");
        xyz_step.setToolTipText("press enter to load this step");
        xyz_step.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        xyz_step.setEnabled(false);
        xyz_step.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                xyz_stepKeyPressed(evt);
            }
        });
        jDesktopPane4.add(xyz_step);
        xyz_step.setBounds(4, 0, 86, 18);

        org.jdesktop.layout.GroupLayout jPanel24Layout = new org.jdesktop.layout.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel24Layout.createSequentialGroup()
                .add(jDesktopPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel24Layout.createSequentialGroup()
                        .add(xyz_end, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(Bt1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(Bt0))
                    .add(jPanel17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel24Layout.createSequentialGroup()
                .add(jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel24Layout.createSequentialGroup()
                        .add(13, 13, 13)
                        .add(jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(Bt0, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(Bt1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(xyz_end, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jDesktopPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollDesk.setBorder(null);

        paste.setFont(fuente);
        paste.setText("...");
        paste.setToolTipText("");
        paste.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        paste.setEnabled(false);
        paste.setFocusable(false);
        paste.setMargin(new java.awt.Insets(0, 0, 0, 0));
        paste.setMaximumSize(new java.awt.Dimension(16, 20));
        paste.setMinimumSize(new java.awt.Dimension(16, 20));
        paste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pasteMousePressed(evt);
            }
        });
        jDeskt0.add(paste);
        paste.setBounds(0, 120, 18, 18);

        Te7.setEditable(false);
        Te7.setFont(fuente);
        Te7.setText("paste copied atoms");
        Te7.setToolTipText("");
        Te7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te7.setFocusable(false);
        Te7.setOpaque(false);
        jDeskt0.add(Te7);
        Te7.setBounds(20, 120, 160, 18);

        Te6.setEditable(false);
        Te6.setFont(fuente);
        Te6.setText("copy selected atoms");
        Te6.setToolTipText("");
        Te6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te6.setFocusable(false);
        Te6.setOpaque(false);
        jDeskt0.add(Te6);
        Te6.setBounds(20, 100, 160, 18);

        Te12.setEditable(false);
        Te12.setFont(fuente);
        Te12.setText("duplicate selected atoms");
        Te12.setToolTipText("");
        Te12.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te12.setFocusable(false);
        Te12.setOpaque(false);
        jDeskt0.add(Te12);
        Te12.setBounds(20, 60, 160, 18);

        jButton36.setFont(fuente       );
        jButton36.setText("...");
        jButton36.setToolTipText("");
        jButton36.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton36.setEnabled(false);
        jButton36.setFocusable(false);
        jButton36.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton36MousePressed(evt);
            }
        });
        jDeskt0.add(jButton36);
        jButton36.setBounds(0, 60, 18, 18);

        Z_all.setFont(fuente);
        Z_all.setText("1");
        Z_all.setToolTipText("");
        Z_all.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt0.add(Z_all);
        Z_all.setBounds(20, 140, 58, 18);

        Te8.setEditable(false);
        Te8.setFont(fuente);
        Te8.setText("change Z");
        Te8.setToolTipText("");
        Te8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te8.setFocusable(false);
        Te8.setOpaque(false);
        jDeskt0.add(Te8);
        Te8.setBounds(80, 140, 100, 18);

        jButton37.setFont(fuente);
        jButton37.setText("...");
        jButton37.setToolTipText("");
        jButton37.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton37.setEnabled(false);
        jButton37.setFocusable(false);
        jButton37.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton37MousePressed(evt);
            }
        });
        jDeskt0.add(jButton37);
        jButton37.setBounds(0, 80, 18, 18);

        Te0.setEditable(false);
        Te0.setFont(fuente);
        Te0.setText("Edit atoms");
        Te0.setToolTipText("");
        Te0.setAutoscrolls(false);
        Te0.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te0.setFocusable(false);
        Te0.setRequestFocusEnabled(false);
        Te0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te0MousePressed(evt);
            }
        });
        jDeskt0.add(Te0);
        Te0.setBounds(0, 0, 180, 18);

        savePos.setFont(fuente);
        savePos.setText("...");
        savePos.setToolTipText("");
        savePos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        savePos.setEnabled(false);
        savePos.setFocusable(false);
        savePos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        savePos.setPreferredSize(new java.awt.Dimension(20, 20));
        savePos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                savePosMousePressed(evt);
            }
        });
        jDeskt0.add(savePos);
        savePos.setBounds(0, 20, 18, 18);

        Te10.setEditable(false);
        Te10.setFont(fuente);
        Te10.setText("preview (save as)");
        Te10.setToolTipText("");
        Te10.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te10.setFocusable(false);
        Te10.setOpaque(false);
        jDeskt0.add(Te10);
        Te10.setBounds(20, 40, 160, 18);

        Te14.setEditable(false);
        Te14.setFont(fuente);
        Te14.setText("delete selected atoms");
        Te14.setToolTipText("");
        Te14.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te14.setFocusable(false);
        Te14.setOpaque(false);
        jDeskt0.add(Te14);
        Te14.setBounds(20, 80, 160, 18);

        jButton35.setFont(fuente);
        jButton35.setText("...");
        jButton35.setToolTipText("");
        jButton35.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton35.setEnabled(false);
        jButton35.setFocusable(false);
        jButton35.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton35.setPreferredSize(new java.awt.Dimension(16, 20));
        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton35MousePressed(evt);
            }
        });
        jDeskt0.add(jButton35);
        jButton35.setBounds(0, 140, 18, 18);

        Te9.setEditable(false);
        Te9.setFont(fuente);
        Te9.setText("save");
        Te9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te9.setOpaque(false);
        Te9.setScrollOffset(2);
        jDeskt0.add(Te9);
        Te9.setBounds(20, 20, 160, 18);

        copy.setFont(fuente);
        copy.setText("...");
        copy.setToolTipText("");
        copy.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        copy.setEnabled(false);
        copy.setFocusable(false);
        copy.setMargin(new java.awt.Insets(0, 0, 0, 0));
        copy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                copyMousePressed(evt);
            }
        });
        jDeskt0.add(copy);
        copy.setBounds(0, 100, 18, 18);

        seePos.setBackground(new java.awt.Color(255, 255, 255));
        seePos.setFont(fuente);
        seePos.setToolTipText("");
        seePos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seePos.setBorderPainted(true);
        seePos.setContentAreaFilled(false);
        seePos.setEnabled(false);
        seePos.setFocusable(false);
        seePos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seePos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seePos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seePos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seePosMouseClicked(evt);
            }
        });
        jDeskt0.add(seePos);
        seePos.setBounds(0, 160, 18, 18);

        jCOrdenar.setBackground(new java.awt.Color(255, 255, 255));
        jCOrdenar.setFont(fuente);
        jCOrdenar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "order Z", "order X", "order Y", "order a" }));
        jCOrdenar.setToolTipText("");
        jCOrdenar.setFocusable(false);
        jCOrdenar.setOpaque(false);
        jDeskt0.add(jCOrdenar);
        jCOrdenar.setBounds(20, 180, 160, 18);

        previewPos.setFont(fuente);
        previewPos.setText("...");
        previewPos.setToolTipText("");
        previewPos.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        previewPos.setEnabled(false);
        previewPos.setFocusable(false);
        previewPos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        previewPos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                previewPosMousePressed(evt);
            }
        });
        jDeskt0.add(previewPos);
        previewPos.setBounds(0, 40, 18, 18);

        BtOrder.setFont(fuente);
        BtOrder.setText("...");
        BtOrder.setToolTipText("");
        BtOrder.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        BtOrder.setEnabled(false);
        BtOrder.setFocusable(false);
        BtOrder.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtOrder.setPreferredSize(new java.awt.Dimension(16, 20));
        BtOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BtOrderMousePressed(evt);
            }
        });
        jDeskt0.add(BtOrder);
        BtOrder.setBounds(0, 180, 18, 18);

        Te21.setEditable(false);
        Te21.setFont(fuente);
        Te21.setText("see positions of the atoms");
        Te21.setToolTipText("");
        Te21.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te21.setFocusable(false);
        Te21.setOpaque(false);
        jDeskt0.add(Te21);
        Te21.setBounds(20, 160, 160, 18);

        jDesktOpt.add(jDeskt0);
        jDeskt0.setBounds(0, 2, 180, 200);

        Te1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Te1.setEditable(false);
        Te1.setFont(fuente);
        Te1.setText("Move atoms");
        Te1.setToolTipText("");
        Te1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te1.setFocusable(false);
        Te1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te1MousePressed(evt);
            }
        });
        jDeskt1.add(Te1);
        Te1.setBounds(0, 0, 180, 18);

        MiniEjes.setBackground(new java.awt.Color(255, 255, 255));
        MiniEjes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MiniEjes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/xeo.gif"))); // NOI18N
        MiniEjes.setToolTipText("");
        MiniEjes.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        MiniEjes.setFocusable(false);
        MiniEjes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                MiniEjesMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                MiniEjesMouseMoved(evt);
            }
        });
        MiniEjes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MiniEjesKeyPressed(evt);
            }
        });
        MiniEjes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MiniEjesMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MiniEjesMousePressed(evt);
            }
        });
        MiniEjes.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                MiniEjesMouseWheelMoved(evt);
            }
        });
        jDeskt1.add(MiniEjes);
        MiniEjes.setBounds(0, 20, 180, 180);

        Tdespl.setFont(fuente);
        Tdespl.setText("0.2");
        Tdespl.setToolTipText("");
        Tdespl.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt1.add(Tdespl);
        Tdespl.setBounds(0, 202, 180, 18);

        jCAxis.setBackground(new java.awt.Color(255, 255, 255));
        jCAxis.setFont(fuente);
        jCAxis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 Axis", "2 Axis", "3 Axis", "nearest neighbour" }));
        jCAxis.setToolTipText("");
        jDeskt1.add(jCAxis);
        jCAxis.setBounds(0, 222, 180, 18);

        jDesktOpt.add(jDeskt1);
        jDeskt1.setBounds(0, 202, 180, 240);

        jCFix.setBackground(new java.awt.Color(255, 255, 255));
        jCFix.setFont(fuente);
        jCFix.setMaximumRowCount(20);
        jCFix.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "fix only selected atoms", "add selected atoms to fix", "add (X) selected atoms to fix", "add (Y) selected atoms to fix", "add (Z) selected atoms to fix", "free all", "free selected atoms", "free (X) selected atoms", "free (Y) selected atoms", "free (Z) selected atoms" }));
        jCFix.setToolTipText("");
        jCFix.setPreferredSize(new java.awt.Dimension(406, 24));
        jDeskt3.add(jCFix);
        jCFix.setBounds(20, 40, 160, 18);

        selectFixAtoms.setFont(fuente);
        selectFixAtoms.setText("...");
        selectFixAtoms.setToolTipText("");
        selectFixAtoms.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        selectFixAtoms.setEnabled(false);
        selectFixAtoms.setMargin(new java.awt.Insets(0, 0, 0, 0));
        selectFixAtoms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                selectFixAtomsMousePressed(evt);
            }
        });
        jDeskt3.add(selectFixAtoms);
        selectFixAtoms.setBounds(0, 40, 18, 18);

        Te2.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Te2.setEditable(false);
        Te2.setFont(fuente);
        Te2.setText("Fix atoms");
        Te2.setToolTipText("");
        Te2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te2MousePressed(evt);
            }
        });
        jDeskt3.add(Te2);
        Te2.setBounds(0, 0, 180, 18);

        CheckFragments.setFont(fuente);
        CheckFragments.setToolTipText("");
        CheckFragments.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        CheckFragments.setBorderPainted(true);
        CheckFragments.setEnabled(false);
        CheckFragments.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CheckFragments.setMargin(new java.awt.Insets(0, 0, 0, 0));
        CheckFragments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckFragmentsMouseClicked(evt);
            }
        });
        jDeskt3.add(CheckFragments);
        CheckFragments.setBounds(0, 20, 18, 18);

        Te11.setEditable(false);
        Te11.setFont(fuente);
        Te11.setText("see fix atoms");
        Te11.setToolTipText("");
        Te11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te11.setOpaque(false);
        jDeskt3.add(Te11);
        Te11.setBounds(20, 20, 160, 18);

        jDesktOpt.add(jDeskt3);
        jDeskt3.setBounds(0, 444, 180, 60);

        Te3.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Te3.setEditable(false);
        Te3.setFont(fuente);
        Te3.setText("Periodicity");
        Te3.setToolTipText("");
        Te3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te3MousePressed(evt);
            }
        });
        jDeskt4.add(Te3);
        Te3.setBounds(0, 0, 180, 18);

        changeLVS.setFont(fuente);
        changeLVS.setText("...");
        changeLVS.setToolTipText("");
        changeLVS.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        changeLVS.setMargin(new java.awt.Insets(0, 0, 0, 0));
        changeLVS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changeLVSMousePressed(evt);
            }
        });
        jDeskt4.add(changeLVS);
        changeLVS.setBounds(0, 80, 18, 18);

        Te13.setEditable(false);
        Te13.setFont(fuente);
        Te13.setText("change lattice vectors");
        Te13.setToolTipText("");
        Te13.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te13.setOpaque(false);
        jDeskt4.add(Te13);
        Te13.setBounds(20, 80, 160, 18);

        lvs_11.setFont(fuente);
        lvs_11.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lvs_11.setText("0.0");
        lvs_11.setToolTipText("");
        lvs_11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_11);
        lvs_11.setBounds(0, 20, 58, 18);

        lvs_21.setFont(fuente);
        lvs_21.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lvs_21.setText("0.0");
        lvs_21.setToolTipText("");
        lvs_21.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_21);
        lvs_21.setBounds(0, 40, 58, 18);

        lvs_31.setFont(fuente);
        lvs_31.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lvs_31.setText("0.0");
        lvs_31.setToolTipText("");
        lvs_31.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_31);
        lvs_31.setBounds(0, 60, 58, 18);

        lvs_12.setFont(fuente);
        lvs_12.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lvs_12.setText("0.0");
        lvs_12.setToolTipText("");
        lvs_12.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_12);
        lvs_12.setBounds(60, 20, 59, 18);

        lvs_22.setFont(fuente);
        lvs_22.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lvs_22.setText("0.0");
        lvs_22.setToolTipText("");
        lvs_22.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_22);
        lvs_22.setBounds(60, 40, 59, 18);

        lvs_32.setFont(fuente);
        lvs_32.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lvs_32.setText("0.0");
        lvs_32.setToolTipText("");
        lvs_32.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_32);
        lvs_32.setBounds(60, 60, 59, 18);

        lvs_13.setFont(fuente);
        lvs_13.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lvs_13.setText("0.0");
        lvs_13.setToolTipText("");
        lvs_13.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_13);
        lvs_13.setBounds(121, 20, 59, 18);

        lvs_23.setFont(fuente);
        lvs_23.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lvs_23.setText("0.0");
        lvs_23.setToolTipText("");
        lvs_23.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_23);
        lvs_23.setBounds(121, 40, 59, 18);

        lvs_33.setFont(fuente);
        lvs_33.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lvs_33.setText("0.0");
        lvs_33.setToolTipText("");
        lvs_33.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt4.add(lvs_33);
        lvs_33.setBounds(121, 60, 59, 18);

        jDesktOpt.add(jDeskt4);
        jDeskt4.setBounds(0, 504, 180, 100);

        Te4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Te4.setEditable(false);
        Te4.setFont(fuente);
        Te4.setText("Repeat unit cell");
        Te4.setToolTipText("");
        Te4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te4.setFocusable(false);
        Te4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te4MousePressed(evt);
            }
        });
        jDeskt5.add(Te4);
        Te4.setBounds(0, 0, 180, 18);

        mol_seeVol.setBackground(new java.awt.Color(255, 255, 255));
        mol_seeVol.setFont(fuente);
        mol_seeVol.setToolTipText("");
        mol_seeVol.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        mol_seeVol.setBorderPainted(true);
        mol_seeVol.setContentAreaFilled(false);
        mol_seeVol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mol_seeVol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        mol_seeVol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mol_seeVolMouseClicked(evt);
            }
        });
        jDeskt5.add(mol_seeVol);
        mol_seeVol.setBounds(0, 20, 18, 18);

        Te15.setEditable(false);
        Te15.setFont(fuente);
        Te15.setText("atoms inside box");
        Te15.setToolTipText("");
        Te15.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te15.setOpaque(false);
        jDeskt5.add(Te15);
        Te15.setBounds(20, 20, 160, 18);

        mol_Xini.setFont(fuente);
        mol_Xini.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_Xini.setText("0.0");
        mol_Xini.setToolTipText("");
        mol_Xini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt5.add(mol_Xini);
        mol_Xini.setBounds(20, 40, 60, 18);

        jLabel41.setFont(new Font(fuente.getName(),Font.BOLD,fuente.getSize()-2));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("X");
        jLabel41.setToolTipText("");
        jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jLabel41.setEnabled(false);
        jLabel41.setFocusable(false);
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDeskt5.add(jLabel41);
        jLabel41.setBounds(82, 40, 36, 18);

        mol_Xfin.setFont(fuente);
        mol_Xfin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_Xfin.setText("10.0");
        mol_Xfin.setToolTipText("");
        mol_Xfin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt5.add(mol_Xfin);
        mol_Xfin.setBounds(120, 40, 60, 18);

        mol_Yfin.setFont(fuente);
        mol_Yfin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_Yfin.setText("10.0");
        mol_Yfin.setToolTipText("");
        mol_Yfin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt5.add(mol_Yfin);
        mol_Yfin.setBounds(120, 60, 60, 18);

        jLabel45.setFont(new Font(fuente.getName(),Font.BOLD,fuente.getSize()-2));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Y");
        jLabel45.setToolTipText("");
        jLabel45.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jLabel45.setEnabled(false);
        jLabel45.setFocusable(false);
        jLabel45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDeskt5.add(jLabel45);
        jLabel45.setBounds(82, 60, 36, 18);

        mol_Yini.setFont(fuente);
        mol_Yini.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_Yini.setText("0.0");
        mol_Yini.setToolTipText("");
        mol_Yini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt5.add(mol_Yini);
        mol_Yini.setBounds(20, 60, 60, 18);

        mol_Zini.setFont(fuente);
        mol_Zini.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_Zini.setText("0.0");
        mol_Zini.setToolTipText("");
        mol_Zini.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt5.add(mol_Zini);
        mol_Zini.setBounds(20, 80, 60, 18);

        jLabel46.setFont(new Font(fuente.getName(),Font.BOLD,fuente.getSize()-2));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Z");
        jLabel46.setToolTipText("");
        jLabel46.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jLabel46.setEnabled(false);
        jLabel46.setFocusable(false);
        jLabel46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDeskt5.add(jLabel46);
        jLabel46.setBounds(82, 80, 36, 18);

        mol_Zfin.setFont(fuente);
        mol_Zfin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_Zfin.setText("10.0");
        mol_Zfin.setToolTipText("");
        mol_Zfin.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt5.add(mol_Zfin);
        mol_Zfin.setBounds(120, 80, 60, 18);

        Te16.setEditable(false);
        Te16.setFont(fuente);
        Te16.setText("repeat lvs vectors (i,j,k)");
        Te16.setToolTipText("");
        Te16.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te16.setOpaque(false);
        jDeskt5.add(Te16);
        Te16.setBounds(20, 100, 160, 18);

        mol_seeIndex.setFont(fuente);
        mol_seeIndex.setToolTipText("");
        mol_seeIndex.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        mol_seeIndex.setBorderPainted(true);
        mol_seeIndex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mol_seeIndex.setMargin(new java.awt.Insets(0, 0, 0, 0));
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
        jButton62.setEnabled(false);
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
        jDeskt5.add(mol_lvs_1);
        mol_lvs_1.setBounds(40, 120, 44, 18);

        mol_lvs_2.setFont(fuente);
        mol_lvs_2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_lvs_2.setText("1");
        mol_lvs_2.setToolTipText("");
        mol_lvs_2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt5.add(mol_lvs_2);
        mol_lvs_2.setBounds(86, 120, 46, 18);

        mol_lvs_3.setFont(fuente);
        mol_lvs_3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mol_lvs_3.setText("1");
        mol_lvs_3.setToolTipText("");
        mol_lvs_3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt5.add(mol_lvs_3);
        mol_lvs_3.setBounds(134, 120, 46, 18);

        jDesktOpt.add(jDeskt5);
        jDeskt5.setBounds(0, 604, 180, 140);

        Te5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        Te5.setEditable(false);
        Te5.setFont(fuente    );
        Te5.setText("Edit bonds");
        Te5.setToolTipText("");
        Te5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te5.setFocusable(false);
        Te5.setRequestFocusEnabled(false);
        Te5.setScrollOffset(2);
        Te5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Te5MousePressed(evt);
            }
        });
        jDeskt6.add(Te5);
        Te5.setBounds(0, 0, 180, 18);

        TOL.setBackground(new java.awt.Color(255, 255, 255));
        TOL.setMaximum(8);
        TOL.setMinimum(1);
        TOL.setToolTipText("");
        TOL.setValue(4);
        TOL.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        TOL.setEnabled(false);
        TOL.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                TOLMouseDragged(evt);
            }
        });
        jDeskt6.add(TOL);
        TOL.setBounds(0, 20, 180, 18);

        Te18.setEditable(false);
        Te18.setFont(fuente);
        Te18.setText("see bonds");
        Te18.setToolTipText("");
        Te18.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te18.setOpaque(false);
        jDeskt6.add(Te18);
        Te18.setBounds(80, 40, 100, 18);

        grosor.setFont(fuente);
        grosor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        grosor.setText("2");
        grosor.setToolTipText("");
        grosor.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt6.add(grosor);
        grosor.setBounds(20, 40, 58, 18);

        rad.setFont(fuente);
        rad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        rad.setText("1");
        rad.setToolTipText("");
        rad.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        jDeskt6.add(rad);
        rad.setBounds(20, 60, 58, 18);

        Te17.setEditable(false);
        Te17.setFont(fuente);
        Te17.setText("covalent radio");
        Te17.setToolTipText("");
        Te17.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        Te17.setOpaque(false);
        jDeskt6.add(Te17);
        Te17.setBounds(80, 60, 100, 18);

        diffRadio.setBackground(new java.awt.Color(255, 255, 255));
        diffRadio.setFont(fuente);
        diffRadio.setToolTipText("");
        diffRadio.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        diffRadio.setBorderPainted(true);
        diffRadio.setEnabled(false);
        diffRadio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diffRadio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        diffRadio.setMargin(new java.awt.Insets(0, 0, 0, 0));
        diffRadio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diffRadioMouseClicked(evt);
            }
        });
        jDeskt6.add(diffRadio);
        diffRadio.setBounds(0, 60, 18, 18);

        seeBond.setBackground(new java.awt.Color(255, 255, 255));
        seeBond.setFont(fuente
        );
        seeBond.setSelected(true);
        seeBond.setToolTipText("");
        seeBond.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("info")));
        seeBond.setBorderPainted(true);
        seeBond.setEnabled(false);
        seeBond.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seeBond.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seeBond.setMargin(new java.awt.Insets(0, 0, 0, 0));
        seeBond.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeBondMouseClicked(evt);
            }
        });
        jDeskt6.add(seeBond);
        seeBond.setBounds(0, 40, 18, 18);

        jDesktOpt.add(jDeskt6);
        jDeskt6.setBounds(0, 744, 180, 80);

        org.jdesktop.layout.GroupLayout jPanelDeskLayout = new org.jdesktop.layout.GroupLayout(jPanelDesk);
        jPanelDesk.setLayout(jPanelDeskLayout);
        jPanelDeskLayout.setHorizontalGroup(
            jPanelDeskLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktOpt, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelDeskLayout.setVerticalGroup(
            jPanelDeskLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktOpt, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );

        jScrollDesk.setViewportView(jPanelDesk);

        menos_mol.setFont(fuente);
        menos_mol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/menos.gif"))); // NOI18N
        menos_mol.setToolTipText("zoom");
        menos_mol.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        menos_mol.setEnabled(false);
        menos_mol.setFocusable(false);
        menos_mol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        menos_mol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menos_molMousePressed(evt);
            }
        });
        jDesktopPrpal.add(menos_mol);
        menos_mol.setBounds(0, 34, 30, 30);

        yx_mol.setFont(fuente);
        yx_mol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/yx.gif"))); // NOI18N
        yx_mol.setToolTipText("mousePressedLeft  (yx) Right (x-y) ");
        yx_mol.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        yx_mol.setEnabled(false);
        yx_mol.setFocusable(false);
        yx_mol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        yx_mol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                yx_molMousePressed(evt);
            }
        });
        jDesktopPrpal.add(yx_mol);
        yx_mol.setBounds(34, 34, 30, 30);

        xz_mol.setFont(fuente);
        xz_mol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/xz.gif"))); // NOI18N
        xz_mol.setToolTipText("mousePressedLeft  (xz) Right (yz) ");
        xz_mol.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        xz_mol.setEnabled(false);
        xz_mol.setFocusable(false);
        xz_mol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xz_mol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xz_molMousePressed(evt);
            }
        });
        jDesktopPrpal.add(xz_mol);
        xz_mol.setBounds(68, 34, 30, 30);

        mas_mol.setFont(fuente);
        mas_mol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/mas.gif"))); // NOI18N
        mas_mol.setToolTipText("zoom");
        mas_mol.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        mas_mol.setEnabled(false);
        mas_mol.setFocusable(false);
        mas_mol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        mas_mol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mas_molMousePressed(evt);
            }
        });
        jDesktopPrpal.add(mas_mol);
        mas_mol.setBounds(0, 0, 30, 30);

        xy_mol.setFont(fuente);
        xy_mol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/xy.gif"))); // NOI18N
        xy_mol.setToolTipText("mousePressedLeft  (-xy) Right (yx) ");
        xy_mol.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        xy_mol.setEnabled(false);
        xy_mol.setFocusable(false);
        xy_mol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xy_mol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xy_molMousePressed(evt);
            }
        });
        jDesktopPrpal.add(xy_mol);
        xy_mol.setBounds(34, 0, 30, 30);

        zy_mol.setFont(fuente);
        zy_mol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/zy.gif"))); // NOI18N
        zy_mol.setToolTipText("mousePressedLeft  (zy) Right (zx) ");
        zy_mol.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        zy_mol.setEnabled(false);
        zy_mol.setFocusable(false);
        zy_mol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        zy_mol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                zy_molMousePressed(evt);
            }
        });
        jDesktopPrpal.add(zy_mol);
        zy_mol.setBounds(68, 0, 30, 30);

        org.jdesktop.layout.GroupLayout TABXEOLayout = new org.jdesktop.layout.GroupLayout(TABXEO);
        TABXEO.setLayout(TABXEOLayout);
        TABXEOLayout.setHorizontalGroup(
            TABXEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(TABXEOLayout.createSequentialGroup()
                .add(TABXEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(TABXEOLayout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(screen5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, TABXEOLayout.createSequentialGroup()
                        .add(jPanel24, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jDesktopPrpal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(4, 4, 4)
                .add(jScrollDesk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 202, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        TABXEOLayout.setVerticalGroup(
            TABXEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, TABXEOLayout.createSequentialGroup()
                .add(4, 4, 4)
                .add(screen5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .add(4, 4, 4)
                .add(TABXEOLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jDesktopPrpal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .add(jScrollDesk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(TABXEO);

        jSplitForm.setRightComponent(jScrollPane2);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(240);
        jSplitPane1.setDividerSize(4);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPaneTreeA.setBorder(null);

        TreeA.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        TreeA.setFont(fuente);
        TreeA.setVerifyInputWhenFocusTarget(false);
        TreeA.addTreeWillExpandListener(new javax.swing.event.TreeWillExpandListener() {
            public void treeWillCollapse(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
            }
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
                TreeATreeWillExpand(evt);
            }
        });
        TreeA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TreeAMousePressed(evt);
            }
        });
        TreeA.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                TreeAValueChanged(evt);
            }
        });
        TreeA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TreeAFocusGained(evt);
            }
        });
        TreeA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreeAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TreeAKeyReleased(evt);
            }
        });
        jScrollPaneTreeA.setViewportView(TreeA);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel13ComponentResized(evt);
            }
        });
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel13MousePressed(evt);
            }
        });
        jPanel13.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jPanel13MouseWheelMoved(evt);
            }
        });

        jSplitPane3.setDividerLocation(90);
        jSplitPane3.setDividerSize(4);
        jSplitPane3.setForeground(java.awt.Color.white);

        jButtonProy.setFont(fuente);
        jButtonProy.setText("project");
        jButtonProy.setToolTipText("");
        jButtonProy.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButtonProy.setEnabled(false);
        jButtonProy.setFocusable(false);
        jButtonProy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonProy.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonProy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonProyMousePressed(evt);
            }
        });
        jSplitPane3.setLeftComponent(jButtonProy);

        jButtonFold.setFont(fuente);
        jButtonFold.setText("folder");
        jButtonFold.setToolTipText("");
        jButtonFold.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButtonFold.setFocusable(false);
        jButtonFold.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFold.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonFold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonFoldMousePressed(evt);
            }
        });
        jSplitPane3.setRightComponent(jButtonFold);

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        org.jdesktop.layout.GroupLayout jPanel66Layout = new org.jdesktop.layout.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPaneTreeA, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel66Layout.createSequentialGroup()
                .add(jScrollPaneTreeA, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .add(4, 4, 4)
                .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setTopComponent(jPanel66);

        jScrollPaneTreeB.setBorder(null);

        TreeB.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        TreeB.setFont(fuente);
        TreeB.setAlignmentX(0.0F);
        TreeB.setAlignmentY(0.0F);
        TreeB.setEditable(true);
        TreeB.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                TreeBValueChanged(evt);
            }
        });
        TreeB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TreeBFocusGained(evt);
            }
        });
        TreeB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreeBKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TreeBKeyReleased(evt);
            }
        });
        TreeB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeBMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TreeBMousePressed(evt);
            }
        });
        jScrollPaneTreeB.setViewportView(TreeB);

        jSplitPane2.setBorder(null);
        jSplitPane2.setDividerLocation(60);
        jSplitPane2.setDividerSize(4);

        Bt2.setFont(fuente);
        Bt2.setText("path");
        Bt2.setToolTipText("");
        Bt2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        Bt2.setFocusable(false);
        Bt2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Bt2MousePressed(evt);
            }
        });
        jSplitPane2.setLeftComponent(Bt2);

        tipo.setEditable(false);
        tipo.setFont(fuente
        );
        tipo.setForeground(new java.awt.Color(0, 0, 0));
        tipo.setToolTipText("");
        tipo.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jSplitPane2.setRightComponent(tipo);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 180, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jSplitPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .add(jScrollPaneTreeB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(jScrollPaneTreeB, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .add(4, 4, 4)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(jSplitPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setRightComponent(jPanel3);

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1)
        );

        jSplitForm.setLeftComponent(jPanel6);

        org.jdesktop.layout.GroupLayout jPanel12Layout = new org.jdesktop.layout.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitForm)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitForm)
        );

        menuPpal.setFont(fuente);

        Me0.setBackground(new java.awt.Color(255, 255, 255));
        Me0.setBorder(null);
        Me0.setText("File");
        Me0.setFont(fuente
        );

        me0.setBackground(new java.awt.Color(255, 255, 255));
        me0.setFont(fuente);
        me0.setText("open project (F4)");
        me0.setActionCommand("open project");
        me0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me0MousePressed(evt);
            }
        });
        Me0.add(me0);

        me1.setBackground(new java.awt.Color(255, 255, 255));
        me1.setFont(fuente);
        me1.setText("load project (F5)");
        me1.setActionCommand("load project ");
        me1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me1MousePressed(evt);
            }
        });
        Me0.add(me1);

        me2.setBackground(new java.awt.Color(255, 255, 255));
        me2.setFont(fuente);
        me2.setText("open file (F3)");
        me2.setActionCommand("open file");
        me2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me2MousePressed(evt);
            }
        });
        Me0.add(me2);
        Me0.add(jSeparator1);

        me3.setBackground(new java.awt.Color(255, 255, 255));
        me3.setFont(fuente);
        me3.setText("hide Inspector (F7)");
        me3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me3MousePressed(evt);
            }
        });
        Me0.add(me3);

        me4.setBackground(new java.awt.Color(255, 255, 255));
        me4.setFont(fuente);
        me4.setText("save *.jpg (F8)");
        me4.setActionCommand("save *.jpg");
        me4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me4MousePressed(evt);
            }
        });
        Me0.add(me4);

        me5.setBackground(new java.awt.Color(255, 255, 255));
        me5.setFont(fuente);
        me5.setText("show picture (F9)");
        me5.setActionCommand("show picture");
        me5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me5MousePressed(evt);
            }
        });
        Me0.add(me5);

        menuPpal.add(Me0);

        Me1.setBorder(null);
        Me1.setText("Utilities");
        Me1.setFont(fuente);

        me_rot.setBackground(new java.awt.Color(255, 255, 255));
        me_rot.setFont(fuente);
        me_rot.setText("rotate and rescale (structure)");
        me_rot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_rotMousePressed(evt);
            }
        });
        Me1.add(me_rot);
        Me1.add(jSeparator4);

        me_dinamic.setBackground(new java.awt.Color(255, 255, 255));
        me_dinamic.setFont(fuente);
        me_dinamic.setText("dinamic");
        me_dinamic.setFocusable(true);
        me_dinamic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_dinamicMousePressed(evt);
            }
        });
        Me1.add(me_dinamic);

        me_STM.setBackground(new java.awt.Color(255, 255, 255));
        me_STM.setFont(fuente);
        me_STM.setText("STM");
        me_STM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_STMMousePressed(evt);
            }
        });
        Me1.add(me_STM);

        me_kpoints.setBackground(new java.awt.Color(255, 255, 255));
        me_kpoints.setFont(fuente);
        me_kpoints.setText("kpoints");
        me_kpoints.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_kpointsMousePressed(evt);
            }
        });
        Me1.add(me_kpoints);

        me_Bulk.setBackground(new java.awt.Color(255, 255, 255));
        me_Bulk.setFont(fuente);
        me_Bulk.setText("Bulk modulus");
        me_Bulk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_BulkMousePressed(evt);
            }
        });
        Me1.add(me_Bulk);

        me_script.setBackground(new java.awt.Color(255, 255, 255));
        me_script.setFont(fuente);
        me_script.setText("script");
        me_script.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_scriptjMenuItem2MousePressed(evt);
            }
        });
        Me1.add(me_script);
        Me1.add(jSeparator3);

        me_ediotr.setBackground(new java.awt.Color(255, 255, 255));
        me_ediotr.setFont(fuente);
        me_ediotr.setText("editor");
        me_ediotr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_ediotrMousePressed(evt);
            }
        });
        Me1.add(me_ediotr);

        me_cal.setBackground(new java.awt.Color(255, 255, 255));
        me_cal.setFont(fuente);
        me_cal.setText("calculator");
        me_cal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_calMousePressed(evt);
            }
        });
        Me1.add(me_cal);
        Me1.add(jSeparator14);

        Me5.setBackground(new java.awt.Color(255, 255, 255));
        Me5.setText("Other programs");
        Me5.setFont(fuente);
        Me5.setOpaque(true);

        jMenuItem13.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem13.setFont(fuente);
        jMenuItem13.setText("abinit");
        jMenuItem13.setEnabled(false);
        Me5.add(jMenuItem13);

        jMenuItem11.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem11.setFont(fuente);
        jMenuItem11.setText("castep");
        jMenuItem11.setEnabled(false);
        Me5.add(jMenuItem11);

        jMenu_Fireball.setBackground(new java.awt.Color(255, 255, 255));
        jMenu_Fireball.setText("fireball");
        jMenu_Fireball.setFont(fuente);
        jMenu_Fireball.setOpaque(true);
        Me5.add(jMenu_Fireball);

        jMenuItem12.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem12.setFont(fuente);
        jMenuItem12.setText("vasp");
        jMenuItem12.setEnabled(false);
        Me5.add(jMenuItem12);

        Me1.add(Me5);

        menuPpal.add(Me1);

        Me2.setText("Export");
        Me2.setFont(fuente);

        Menu_xeoBabel.setBackground(new java.awt.Color(255, 255, 255));
        Menu_xeoBabel.setText("xeoBabel");
        Menu_xeoBabel.setFont(fuente
        );
        Menu_xeoBabel.setOpaque(true);
        Me2.add(Menu_xeoBabel);

        me_babel.setBackground(new java.awt.Color(255, 255, 255));
        me_babel.setFont(fuente);
        me_babel.setText("babel");
        me_babel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_babelMousePressed(evt);
            }
        });
        Me2.add(me_babel);

        me_POVRAY.setBackground(new java.awt.Color(255, 255, 255));
        me_POVRAY.setFont(fuente);
        me_POVRAY.setText("POV-Ray");
        me_POVRAY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me_POVRAYjMenuItem1MousePressed(evt);
            }
        });
        Me2.add(me_POVRAY);

        menuPpal.add(Me2);

        Me3.setBorder(null);
        Me3.setText("Options");
        Me3.setFont(fuente);

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(fuente);
        jMenuItem1.setText("visual options");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        Me3.add(jMenuItem1);

        Me_Language.setBackground(new java.awt.Color(255, 255, 255));
        Me_Language.setText("language");
        Me_Language.setFont(fuente);
        Me_Language.setOpaque(true);
        Me3.add(Me_Language);

        me6.setBackground(new java.awt.Color(255, 255, 255));
        me6.setFont(fuente);
        me6.setText("Font");
        me6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me6MousePressed(evt);
            }
        });
        Me3.add(me6);

        me7.setBackground(new java.awt.Color(255, 255, 255));
        me7.setFont(fuente);
        me7.setText("save options");
        me7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me7MousePressed(evt);
            }
        });
        Me3.add(me7);

        me8.setBackground(new java.awt.Color(255, 255, 255));
        me8.setFont(fuente);
        me8.setText("restore intial options");
        me8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me8MousePressed(evt);
            }
        });
        Me3.add(me8);

        menuPpal.add(Me3);

        Me4.setText("help");
        Me4.setFont(fuente);
        Me4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Me4MousePressed(evt);
            }
        });
        menuPpal.add(Me4);

        Me7.setText("about box");
        Me7.setFont(fuente);

        me35.setBackground(new java.awt.Color(255, 255, 255));
        me35.setFont(fuente);
        me35.setText("about");
        me35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me35MousePressed(evt);
            }
        });
        Me7.add(me35);

        me36.setBackground(new java.awt.Color(255, 255, 255));
        me36.setFont(fuente);
        me36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/gplv3.gif"))); // NOI18N
        me36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                me36MousePressed(evt);
            }
        });
        Me7.add(me36);

        me37.setBackground(new java.awt.Color(255, 255, 255));
        me37.setFont(fuente);
        me37.setText("show w");
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

        menuPpal.add(Me7);

        setJMenuBar(menuPpal);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    private void xyzTrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xyzTrMouseClicked
        ver3D_MOL();
    }//GEN-LAST:event_xyzTrMouseClicked
        
    private void jButton59MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton59MousePressed
        newFile =  new dialogo.chooser().fileChoose("Open file x,y,z,y","open",pintarMol.babel.path+SEP+".") ;
        if(newFile!=null) {
        pintarMol.xyzFileTr=newFile;
        xyzTr.setSelected(true);
        pintarMol.xyzTr=xyzTr.isSelected();
        pintarMol.atoms_xyzTr=xyztAtomsTr.getText();
        xyzFileTr.setText(newFile.getAbsolutePath());
        pintarMol.trfirst=Integer.valueOf(jtrfirst.getText());
        pintarMol.trlast=Integer.valueOf(jtrlast.getText());
        pintarMol.C[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=colorLabel.getBackground().getRGB();
        pintarMol.itr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=jlvs_1.getValue().hashCode();
        pintarMol.jtr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=jlvs_2.getValue().hashCode();
        pintarMol.ktr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=jlvs_3.getValue().hashCode();
        pintarMol.ancho[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=ancho.getValue().hashCode(); 
        pintarMol.ancho[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=ancho.getValue().hashCode();
        pintarMol.Load_xyzTr();
        ver3D_MOL();
        }
    }//GEN-LAST:event_jButton59MousePressed
    
    private void Me4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Me4MousePressed
        new dialogo.help(pintarMol.babel.xeorc+"help/index.html").setVisible(true);
    }//GEN-LAST:event_Me4MousePressed
    
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
        about.setSize(500,130);
        about.setTitle("about") ;
        javax.swing.JTextArea aboutArea = new javax.swing.JTextArea();
        aboutArea.setEditable(false);
        aboutArea.setText(new gpl().gpl_v3);
        about.add(aboutArea);
        about.setVisible(true);
    }//GEN-LAST:event_me35MousePressed
    
    private void PERSMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PERSMouseDragged
        if(pintarMol.MOL_enable)     ver3D_MOL();
    }//GEN-LAST:event_PERSMouseDragged
    
    private void jButton30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MousePressed
        JFont.pack();
        JFont.setVisible(true);
        jTextFont.setText(fuente.getFamily());
        jListStyle.setSelectedIndex(fuente.getStyle());
        jTextStyle.setText(jListStyle.getSelectedValue().toString());
        jTextSize.setText(fuente.getSize()+"");
        Options.setVisible(false);
        java.awt.GraphicsEnvironment env = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] familynames = env.getAvailableFontFamilyNames();
        javax.swing.DefaultListModel listaFont= new javax.swing.DefaultListModel();
        for (String familyname : familynames) {
            listaFont.addElement(familyname);
        }
        jListFont.setModel(listaFont);
    }//GEN-LAST:event_jButton30MousePressed
    
    private void verIconosAtomosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verIconosAtomosMouseClicked
        if(pintarMol.MOL_enable){
            pintarMol.MOUSE_DRAG=false;
            ver3D_MOL();
        }
    }//GEN-LAST:event_verIconosAtomosMouseClicked
    
    private void jButton18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MousePressed
        colorFondo.setBackground( JColorChooser.showDialog( this , "background color", colorFondo.getBackground()));
        if(pintarMol.MOL_enable) ver3D_MOL();
    }//GEN-LAST:event_jButton18MousePressed
    
    private void verEjesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verEjesMouseClicked
        if(pintarMol.MOL_enable)     ver3D_MOL();
    }//GEN-LAST:event_verEjesMouseClicked
    
    private void jButton20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MousePressed
        colorEjes.setBackground( JColorChooser.showDialog( this , "axis color", colorEjes.getBackground()));
        if(pintarMol.MOL_enable) ver3D_MOL();
    }//GEN-LAST:event_jButton20MousePressed
    
    private void jButton21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MousePressed
        if(pintarMol.MOL_enable) {
            pintarMol.ajustar_centro();
            ver3D_MOL();
        }
    }//GEN-LAST:event_jButton21MousePressed
    
    private void jButton23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MousePressed
        colorArrows.setBackground( JColorChooser.showDialog( this , "arrow color", colorArrows.getBackground()));
        if(pintarMol.MOL_enable) ver3D_MOL();
    }//GEN-LAST:event_jButton23MousePressed
    
    private void seeArrowsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeArrowsMouseClicked
        if(pintarMol.MOL_enable){
            pintarMol.babel.infBas.bas=(ArrayList)pintarMol.babel.infBas.xyz.get( pintarMol.babel.infBas.iStep-pintarMol.babel.infBas.iniStepMEM);
            ver3D_MOL();
        }
    }//GEN-LAST:event_seeArrowsMouseClicked
    
    private void jButton9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MousePressed
        if(pintarMol.MOL_enable){
            pintarMol.firstTime=true;
            ver3D_MOL();
        }
    }//GEN-LAST:event_jButton9MousePressed
                                    
    private void escalaGrisesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_escalaGrisesMouseClicked
        if(pintarMol.MOL_enable){
            ver3D_MOL();
        }
    }//GEN-LAST:event_escalaGrisesMouseClicked
    
    private void jLabelXYZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelXYZMouseEntered
        if(jLabelXYZ.isEnabled()){
            jLabelXYZ.setToolTipText("load = "+pintarMol.babel.infBas.paso);
            barra_XYZ((pintarMol.babel.infBas.iStep*jLabelXYZ.getWidth())/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
        }
    }//GEN-LAST:event_jLabelXYZMouseEntered
    
    private void xyz_stepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_xyz_stepKeyPressed
        if(KeyEvent.VK_ENTER == evt.getKeyCode() ) {
            pintarMol.babel.infBas.iStep=cadena.readColInt(1,xyz_step.getText());
            barra_XYZ((pintarMol.babel.infBas.iStep*jLabelXYZ.getWidth())/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
            cargarUnPaso();
        }
    }//GEN-LAST:event_xyz_stepKeyPressed
    
    private void me34MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me34MousePressed
        new editor(newFile.getAbsolutePath()).LoadString(
                "path : \n"+pintarMol.babel.path+"\n"+
                "\n"+"sort : "+new File(pintarMol.babel.sort).getName()+"\n"+
                "file : "+new File(pintarMol.babel.projectFile).getName()+"\n"+
                "home : "+home+"\n"+
                "temp : "+pintarMol.babel.xeorc+"write_tmp \n"+
                "open with "+pintarMol.babel.typeBabel+"\n"+"press F2 to rename the file or the project");
        
    }//GEN-LAST:event_me34MousePressed
                        
    private void TreeBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TreeBFocusGained
        if(!jButtonProy.isEnabled())
            if (TreeB.getSelectionPath() != null)
                if(TreeB.getSelectionPath().getPathCount() > 1){
            selectedChildValue = (String) nodeB.getUserObject();
            if(!selectedChildValue.equals(ins.get(TreeB.getLeadSelectionRow()-1))){
                ins.set(TreeB.getLeadSelectionRow()-1,selectedChildValue);
                cadena.writer(ins,new File(pintarMol.babel.xeorc+"inspector"));
                int j=TreeB.getLeadSelectionRow();
                Limpiar_Inspector();
                Inspector();
                TreeB.setSelectionRow(j);
            }
                }
    }//GEN-LAST:event_TreeBFocusGained
    
    javax.swing.tree.DefaultMutableTreeNode nodeB ;
    private void TreeBValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_TreeBValueChanged
        nodeB=(javax.swing.tree.DefaultMutableTreeNode)evt.getPath().getLastPathComponent();
    }//GEN-LAST:event_TreeBValueChanged
    
    
    boolean cambia=false;
    int jcambio=0;
    private void TreeAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TreeAFocusGained
        if (TreeA.getSelectionPath() != null){
            //ajustamos, es decir quitmos lo que hay dentro de las carpetas
            if(TreeA.getSelectionPath().getPathCount() > 1){
                cambia=false;
                selectedChildValue = (String)nodeA.getUserObject();
                aux=dir.get(TreeA.getLeadSelectionRow()-1);
                if(!aux.substring(0,aux.indexOf(" ")).equals("note")){
                    //--------
                    aux_babel=aux.substring(aux.lastIndexOf(" "),aux.length()).trim();
                    aux_file=aux.substring(0,aux.lastIndexOf(" "));
                    aux_sort=aux_file.substring(aux_file.lastIndexOf(" "),aux_file.length()).trim();
                    aux_file=aux_file.substring(0,aux_file.lastIndexOf(" ")).trim();
                    //---------
                    if(aux_sort.equals("directory")){
                        if(!new File(aux_file).getName().equals(selectedChildValue)){
                            newFile=new File(new File(aux_file).getParent()+SEP+selectedChildValue);
                            new File(aux_file).renameTo(newFile);
                            dir.set(TreeA.getLeadSelectionRow()-1,newFile.getAbsolutePath()+" "+aux_sort+" "+aux_babel);
                            cambia=true;
                        }
                    }else{
                        if(!new File(aux_file).getParentFile().getName().equals(selectedChildValue)){
                            newFile=new File(new File(aux_file).getParentFile().getParent()+SEP+selectedChildValue);
                            new File(aux_file).getParentFile().renameTo(newFile);
                            //  System.out.println(newFile.getAbsolutePath()+SEP+new File(aux_file).getName()+" "+aux_sort+" "+aux_babel);
                            dir.set(TreeA.getLeadSelectionRow()-1,newFile.getAbsolutePath()+SEP+new File(aux_file).getName()+" "+aux_sort+" "+aux_babel);
                            cambia=true;
                        }
                    }
                }else{//es una nota
                    if(!aux.substring(aux.indexOf(" "),aux.length()).equals(selectedChildValue)){
                        dir.set(TreeA.getLeadSelectionRow()-1,"note "+selectedChildValue);
                        cambia=true;
                    }
                }
                if(cambia){
                    jcambio=TreeA.getLeadSelectionRow();
                    Inspector();
                    Limpiar_Inspector();
                    Inspector();
                    Limpiar_Inspector();
                    TreeA.setSelectionRow(jcambio);
                }
            }
        }
        TreeA.setEditable(false);
    }//GEN-LAST:event_TreeAFocusGained
    
    
    
    private void TreeAValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_TreeAValueChanged
        nodeA=(javax.swing.tree.DefaultMutableTreeNode)evt.getPath().getLastPathComponent();
    }//GEN-LAST:event_TreeAValueChanged
    
    String aux_file="",aux_sort="",aux_babel="",selectedChildValue="";
    javax.swing.tree.DefaultMutableTreeNode nodeA ;
    private void TreeAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreeAKeyPressed
        KEY_comun(evt);
        if(KeyEvent.VK_F2== evt.getKeyCode()) TreeA.setEditable(true);
        if(KeyEvent.VK_F7==evt.getKeyCode()) {
            if(jSplitForm.getDividerLocation()==1)jSplitForm.setDividerLocation(180);
            else jSplitForm.setDividerLocation(1);
        }
        if(KeyEvent.VK_F1== evt.getKeyCode()) new dialogo.help(pintarMol.babel.xeorc+"help/index.html").setVisible(true);
        if(KeyEvent.VK_ENTER   == evt.getKeyCode() ){
            selectProject();
            loadMOL();
        }
        if(KeyEvent.VK_DELETE  == evt.getKeyCode() ) TreeA_delete();
        if(evt.getKeyCode() == 104) TreeA_up();
        if(evt.getKeyCode() == 98) TreeA_down();
        //   if(KeyEvent.VK_F2 == evt.getKeyCode()) renameOpen();
        if(KeyEvent.VK_RIGHT == evt.getKeyCode()){
            nInsp++;
            if(nInsp>=ins.size())nInsp=0;
            Limpiar_Inspector();
            Inspector();
        }
        if(KeyEvent.VK_LEFT == evt.getKeyCode()){
            nInsp--;
            if(nInsp<0)nInsp=ins.size()-1;
            Limpiar_Inspector();
            Inspector();
        }
    }//GEN-LAST:event_TreeAKeyPressed
    
    private void TreeAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreeAKeyReleased
        if(KeyEvent.VK_UP == evt.getKeyCode() ) {  selectProject(); loadMOL(); }
        if(KeyEvent.VK_DOWN == evt.getKeyCode() ){  selectProject(); loadMOL(); }
    }//GEN-LAST:event_TreeAKeyReleased
    
    private void TreeAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreeAMousePressed
        Limpiar_Inspector();
        if(evt.getButton()==MouseEvent.BUTTON2) loadMOL();
        if(evt.getButton()==MouseEvent.BUTTON3) menuA.show( evt.getComponent(), evt.getX(), evt.getY() ) ;
        if(evt.getButton()==MouseEvent.BUTTON1) selectProject();
        if(ProjectTree.isVisible()) {
            path_project.setText(new File(pintarMol.babel.path).getParent());
            projectOpen();
        }
        allow();
    }//GEN-LAST:event_TreeAMousePressed
    
    private void TreeATreeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {//GEN-FIRST:event_TreeATreeWillExpand
        loadMOL();
    }//GEN-LAST:event_TreeATreeWillExpand
    
    private void jButtonProyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonProyMousePressed
        modeTreeB=!modeTreeB;
        jButtonFold.setEnabled(!jButtonFold.isEnabled());
        jButtonProy.setEnabled(!jButtonProy.isEnabled());
        TreeB.setFocusable(!jButtonProy.isEnabled());
        loadTreeB();
        /*
        nInsp--;
        if(nInsp<0)nInsp=ins.size()-1;
        Limpiar_Inspector();
        Inspector();
         */
    }//GEN-LAST:event_jButtonProyMousePressed
    
    private void jButtonFoldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFoldMousePressed
        modeTreeB=!modeTreeB;
        jButtonFold.setEnabled(!jButtonFold.isEnabled());
        jButtonProy.setEnabled(!jButtonProy.isEnabled());
        TreeB.setFocusable(!jButtonProy.isEnabled());
        loadTreeB();
    }//GEN-LAST:event_jButtonFoldMousePressed
    
    private void jPanel13ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel13ComponentResized
        // jSplitPaneFix.setDividerLocation(22);
    }//GEN-LAST:event_jPanel13ComponentResized
    
    private void jPanel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MousePressed
        if(evt.getButton()==MouseEvent.BUTTON3) menuInspctor.show( evt.getComponent(), evt.getX(), evt.getY() ) ;
    }//GEN-LAST:event_jPanel13MousePressed
    
    private void jPanel13MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jPanel13MouseWheelMoved
        nInsp+=evt.getWheelRotation();
        if(nInsp>=ins.size())nInsp=0;
        if(nInsp<0)nInsp=ins.size()-1;
        Limpiar_Inspector();
        Inspector();
    }//GEN-LAST:event_jPanel13MouseWheelMoved
    
    private void Bt2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bt2MousePressed
        tipo.setText(pintarMol.babel.path);
        Bt2.setToolTipText(pintarMol.babel.path);
    }//GEN-LAST:event_Bt2MousePressed
    
    private void Te60MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te60MousePressed
        insertInTreeADirectory();
    }//GEN-LAST:event_Te60MousePressed
    
    private void jRadioButtonBabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonBabelMouseClicked
        if(jRadioButtonBabel.isSelected()){
            inputFileBabel.setEnabled(true);
            buttonOpenBabel.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButtonBabelMouseClicked
    
    private void me_babelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_babelMousePressed
        jbabel.pack();
        jbabel.setVisible(true);
        int item_xyz=-1;
        for (String babel1 : babel) {
            jComboBoxBabelRead.addItem(cadena.readColString(1, babel1));
            jComboBoxBabelWrite.addItem(cadena.readColString(1, babel1));
            if (cadena.readColString(1, babel1).equals("xyz")) {
                item_xyz= jComboBoxBabelWrite.getItemCount()-1;
            }
        }
        jComboBoxBabelWrite.setSelectedIndex(item_xyz);
        jComboBoxBabelRead.setSelectedIndex(item_xyz);
    }//GEN-LAST:event_me_babelMousePressed
    
    private void jButton43MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton43MousePressed
        if(jRadioButtonXeo.isSelected()){
            new File(pintarMol.babel.xeorc+"temp"+SEP+"babel.xyz").delete();
            new File(pintarMol.babel.path+"babel.out").delete();
            pintarMol.babel.write("xyz",pintarMol.babel.xeorc+"temp"+SEP+"babel.xyz");
            
            try{
                Runtime run = Runtime.getRuntime();
                Process proc = run.exec("babel -i xyz "+pintarMol.babel.xeorc+"temp"+SEP+"babel.xyz -o "+jComboBoxBabelWrite.getSelectedItem()+" "+pintarMol.babel.path+"babel.out");
                proc.waitFor();
            } catch(Exception e){System.out.println("error run babel");}
        }
        if(jRadioButtonBabel.isSelected()){
            if(new File(inputFileBabel.getText()).exists()){
                try{
                    Runtime run = Runtime.getRuntime();
                    Process proc = run.exec("babel -i "+jComboBoxBabelRead.getSelectedItem()+" "+inputFileBabel.getText()+" -o "+jComboBoxBabelWrite.getSelectedItem()+" "+pintarMol.babel.path+"babel.out");
                    proc.waitFor();
                } catch(Exception e){System.out.println("error run babel");}
            }else System.out.println(inputFileBabel.getText()+" no existe");
        }
        new editor(pintarMol.babel.path+"babel.out").openFile(new File(pintarMol.babel.path+"babel.out"));
    }//GEN-LAST:event_jButton43MousePressed
    
    private void buttonOpenBabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonOpenBabelMousePressed
        inputFileBabel.setText(new dialogo.chooser().fileChoose("Open file","open",pintarMol.babel.path+SEP+".").getAbsolutePath());
    }//GEN-LAST:event_buttonOpenBabelMousePressed
    
    private void Te5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te5MousePressed
        if( Te6.isEnabled() )
            jDeskt6.setSize(180,20);
        else
            jDeskt6.setSize(180,4*20);
        Te6.setEnabled( !Te6.isEnabled());
    }//GEN-LAST:event_Te5MousePressed
    
    private void Te4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te4MousePressed
        jDesCol=6*20;
        if( Te5.isEnabled() ){
            jDeskt5.setSize(180,20);
            jDesCol=-jDesCol;
        }else
            jDeskt5.setSize(180,jDesCol+20);
        jDeskt6.setLocation(0,jDeskt6.getY()+jDesCol);
        Te5.setEnabled( !Te5.isEnabled());
    }//GEN-LAST:event_Te4MousePressed
    
    private void Te3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te3MousePressed
        jDesCol=4*20;
        if( Te4.isEnabled() ){
            jDeskt4.setSize(180,20);
            jDesCol=-jDesCol;
        } else jDeskt4.setSize(180,jDesCol+20);
        jDeskt6.setLocation(0,jDeskt6.getY()+jDesCol);
        jDeskt5.setLocation(0,jDeskt5.getY()+jDesCol);
        Te4.setEnabled( !Te4.isEnabled());
    }//GEN-LAST:event_Te3MousePressed
    
    private void Te2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te2MousePressed
        jDesCol=2*20;
        if( Te3.isEnabled() ){
            jDeskt3.setSize(180,20);
            jDesCol=-jDesCol;
        }else jDeskt3.setSize(180,jDesCol+20);
        jDeskt4.setLocation(0,jDeskt4.getY()+jDesCol);
        jDeskt5.setLocation(0,jDeskt5.getY()+jDesCol);
        jDeskt6.setLocation(0,jDeskt6.getY()+jDesCol);
        
        Te3.setEnabled( !Te3.isEnabled());
    }//GEN-LAST:event_Te2MousePressed
    
    private void Te1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te1MousePressed
        jDesCol=222;
        if( Te1.isEnabled() ){
            jDeskt1.setSize(180,20);
            jDesCol=-jDesCol;
        } else jDeskt1.setSize(180,jDesCol+20);
        jDeskt3.setLocation(0,jDeskt3.getY()+jDesCol);
        jDeskt4.setLocation(0,jDeskt4.getY()+jDesCol);
        jDeskt5.setLocation(0,jDeskt5.getY()+jDesCol);
        jDeskt6.setLocation(0,jDeskt6.getY()+jDesCol);
        
        Te1.setEnabled( !Te1.isEnabled());
    }//GEN-LAST:event_Te1MousePressed
    
    private void Te0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te0MousePressed
        jDesCol=9*20;
        if( Te0.isEnabled() ){
            jDeskt0.setSize(180,20);
            jDesCol=-jDesCol;
        } else jDeskt0.setSize(180,jDesCol+20);
        
        jDeskt1.setLocation(0,jDeskt1.getY()+jDesCol);
        jDeskt3.setLocation(0,jDeskt3.getY()+jDesCol);
        jDeskt4.setLocation(0,jDeskt4.getY()+jDesCol);
        jDeskt5.setLocation(0,jDeskt5.getY()+jDesCol);
        jDeskt6.setLocation(0,jDeskt6.getY()+jDesCol);
        
        Te0.setEnabled( !Te0.isEnabled());
    }//GEN-LAST:event_Te0MousePressed
    
    private void me8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me8MousePressed
        defaultVal();
    }//GEN-LAST:event_me8MousePressed
    
    private void me7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me7MousePressed
        saveVal();
    }//GEN-LAST:event_me7MousePressed
    
    private void changeLVSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeLVSMousePressed
        pintarMol.babel.infBas.lvs[0][0] = Double.parseDouble(lvs_11.getText());
        pintarMol.babel.infBas.lvs[0][1] = Double.parseDouble(lvs_12.getText());
        pintarMol.babel.infBas.lvs[0][2] = Double.parseDouble(lvs_12.getText());
        pintarMol.babel.infBas.lvs[1][0] = Double.parseDouble(lvs_21.getText());
        pintarMol.babel.infBas.lvs[1][1] = Double.parseDouble(lvs_22.getText());
        pintarMol.babel.infBas.lvs[1][2] = Double.parseDouble(lvs_23.getText());
        pintarMol.babel.infBas.lvs[2][0] = Double.parseDouble(lvs_31.getText());
        pintarMol.babel.infBas.lvs[2][1] = Double.parseDouble(lvs_32.getText());
        pintarMol.babel.infBas.lvs[2][2] = Double.parseDouble(lvs_33.getText());
    }//GEN-LAST:event_changeLVSMousePressed
    
    private void Te58MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te58MousePressed
        double a=Double.parseDouble(rot_TextX.getText());
        a=a*Math.PI/180;
        CalcX.setText("y*cos("+a+")+z*sin("+a+")" );
        CalcY.setText("-y*sin("+a+")+z*cos("+a+")" );
        CalcZ.setText("x");
    }//GEN-LAST:event_Te58MousePressed
    
    private void Te57MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te57MousePressed
        double a=Double.parseDouble(rot_TextY.getText());
        a=a*Math.PI/180;
        CalcX.setText("x*cos("+a+")+z*sin("+a+")" );
        CalcY.setText("-x*sin("+a+")+z*cos("+a+")" );
        CalcZ.setText("y");
    }//GEN-LAST:event_Te57MousePressed
    
    private void Te56MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te56MousePressed
        double a=Double.parseDouble(rot_TextZ.getText());
        a=a*Math.PI/180;
        CalcX.setText("x*cos("+a+")+y*sin("+a+")" );
        CalcY.setText("-x*sin("+a+")+y*cos("+a+")" );
        CalcZ.setText("z");
    }//GEN-LAST:event_Te56MousePressed
    
    private void Te55MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te55MousePressed
        CalcX.setText("x*"+resText.getText());
        CalcY.setText("y*"+resText.getText());
        CalcZ.setText("z*"+resText.getText());
    }//GEN-LAST:event_Te55MousePressed
    
    private void Te52MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te52MousePressed
        if(pintarMol.MOL_enable){
            if(cadena.nCol(Giro_centro2.getText())==3 &&
                    cadena.nCol(Giro_Z2.getText())==3 &&
                    cadena.nCol(Giro_X2.getText())==3 ){
                pintarMol.babel.infBas.rotate(Giro_centro2.getText(),Giro_Z2.getText(),Giro_X2.getText());
                pintarMol.firstTime=true;
                ver3D_MOL();
            }
        }
    }//GEN-LAST:event_Te52MousePressed
    
    private void Te51MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te51MousePressed
        Giro_centro2.setText( Giro_centro1.getText());
        Giro_Z2.setText( Giro_Z1.getText());
        Giro_X2.setText( Giro_X1.getText());
    }//GEN-LAST:event_Te51MousePressed
    
    private void me6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me6MousePressed
        JFont.pack();
        JFont.setVisible(true);
        jTextFont.setText(fuente.getFamily());
        jListStyle.setSelectedIndex(fuente.getStyle());
        jTextStyle.setText(jListStyle.getSelectedValue().toString());
        jTextSize.setText(fuente.getSize()+"");
        Options.setVisible(false);
        java.awt.GraphicsEnvironment env = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] familynames = env.getAvailableFontFamilyNames();
        javax.swing.DefaultListModel listaFont= new javax.swing.DefaultListModel();
        for (String familyname : familynames) {
            listaFont.addElement(familyname);
        }
        jListFont.setModel(listaFont);
    }//GEN-LAST:event_me6MousePressed
    
    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        Options.pack();
        Options.setLocation((int) (jScrollDesk.getLocationOnScreen().getX()), (int) jSplitForm.getLocationOnScreen().getY());
        Options.setVisible(true);
        Sinter1.setValue(pintarMol.posicion_1);
        Sinter2.setValue(pintarMol.posicion_2);
        color_ini.setBackground(pintarMol.colorIni);
        color_inter_1.setBackground(pintarMol.colorInter1);
        color_inter_2.setBackground(pintarMol.colorInter2);
        color_fin.setBackground(pintarMol.colorFin);
        colorLabel.setBackground(new Color(pintarMol.C[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]));   
        dialogo_color();
    }//GEN-LAST:event_jMenuItem1MousePressed
    
    private void jButton40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MousePressed
        setXeoFont(new File(File_ttf.getText()));
        loadVal();
    }//GEN-LAST:event_jButton40MousePressed
    
    private void jButton63MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton63MousePressed
        newFile =  new dialogo.chooser().fileChoose("Open file","open",pintarMol.babel.path+SEP+".") ;
        File_ttf.setText(newFile.getAbsolutePath());
    }//GEN-LAST:event_jButton63MousePressed
    
    
    
    private void BtOrderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtOrderMousePressed
        if(pintarMol.MOL_enable){
            if(jCOrdenar.getSelectedIndex()==0)pintarMol.babel.infBas.orderZ();
            if(jCOrdenar.getSelectedIndex()==1)pintarMol.babel.infBas.orderX();
            if(jCOrdenar.getSelectedIndex()==2)pintarMol.babel.infBas.orderY();
            if(jCOrdenar.getSelectedIndex()==3)pintarMol.babel.infBas.order_Castep();
            ver3D_MOL();
            if(jCOrdenar.getSelectedIndex()==0)pintarMol.babel.infBas.orderZ();
            if(jCOrdenar.getSelectedIndex()==1)pintarMol.babel.infBas.orderX();
            if(jCOrdenar.getSelectedIndex()==2)pintarMol.babel.infBas.orderY();
            if(jCOrdenar.getSelectedIndex()==3)pintarMol.babel.infBas.order_Castep();
            preview(false);//para que no ordene
            
        }
    }//GEN-LAST:event_BtOrderMousePressed
    
    private void MiniEjesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MiniEjesMouseEntered
        pintarMol.nejes=jCAxis.getSelectedIndex()+1;
    }//GEN-LAST:event_MiniEjesMouseEntered
    
    private void me_kpointsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_kpointsMousePressed
        xeokpts.jKpts jFrameKpts = new  xeokpts.jKpts();
        jFrameKpts.pack();
        jFrameKpts.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
        jFrameKpts.setVisible(true);
        jFrameKpts.setDefaultCloseOperation(jKpts.HIDE_ON_CLOSE);
    }//GEN-LAST:event_me_kpointsMousePressed
    
    private void me_scriptjMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_scriptjMenuItem2MousePressed
        script.pack();
        script.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
        script.setVisible(true);
        if(newFile!=null)outText.setText(newFile.getAbsolutePath());
        allow();
    }//GEN-LAST:event_me_scriptjMenuItem2MousePressed
    
    private void me_calMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_calMousePressed
        new jCalc(true);
    }//GEN-LAST:event_me_calMousePressed
    
    private void me_ediotrMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_ediotrMousePressed
        new editor(pintarMol.babel.path+SEP+".").setVisible(true);
    }//GEN-LAST:event_me_ediotrMousePressed
    
    private void me_BulkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_BulkMousePressed
        Frame_Bulk.pack();
        Frame_Bulk.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
        Frame_Bulk.setVisible(true);
    }//GEN-LAST:event_me_BulkMousePressed
    
    private void me_STMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_STMMousePressed
        java_STM_AFM stm = new java_STM_AFM(new File(pintarMol.babel.path).toString());
        stm.setDefaultCloseOperation(stm.DISPOSE_ON_CLOSE);
        stm.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
        stm.setVisible(true);
    }//GEN-LAST:event_me_STMMousePressed
    
    private void me_POVRAYjMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_POVRAYjMenuItem1MousePressed
        jFrame_POVRAY.pack();
        jFrame_POVRAY.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
        jFrame_POVRAY.setVisible(true);
    }//GEN-LAST:event_me_POVRAYjMenuItem1MousePressed
    
    private void me_rotMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_rotMousePressed
        if(pintarMol.MOL_enable){
            jDCalc.pack();
            jDCalc.setVisible(true);
            // jDCalc.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
            // rescalateCalc.requestFocus();
        }
    }//GEN-LAST:event_me_rotMousePressed
    int dinam=0;
    private void me_dinamicMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me_dinamicMousePressed
        calc_xyz.JCalc_xyz dinamic = new calc_xyz.JCalc_xyz();
        dinamic.iniciar(new File(pintarMol.babel.path).toString(),dinam);
        dinamic.pack();
        dinam++;
        dinamic.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
        dinamic.setVisible(true);
        dinamic.setDefaultCloseOperation(JCalc_xyz.HIDE_ON_CLOSE);
    }//GEN-LAST:event_me_dinamicMousePressed
    
    private void me5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me5MousePressed
        if(pintarMol.MOL_enable)
            new dialogo.show_picture(new File(pintarMol.babel.path+SEP+"picture.jpg").toString()).plot(screen5Buffered);
    }//GEN-LAST:event_me5MousePressed
    
    private void me4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me4MousePressed
        if(me5.isEnabled())
            new dialogo.chooser().savePicture("Save file *.jpg","save", new File(pintarMol.babel.path+SEP+"picture.jpg").toString(),pintarMol.imageBuffered);
    }//GEN-LAST:event_me4MousePressed
    
    private void me3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me3MousePressed
        if(jSplitForm.getDividerLocation()==1)jSplitForm.setDividerLocation(180);
        else jSplitForm.setDividerLocation(1);
    }//GEN-LAST:event_me3MousePressed
    
    private void me2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me2MousePressed
        newFile =  new dialogo.chooser().fileChoose("Open file","open",pintarMol.babel.path+SEP+".") ;
        load_file(newFile);
    }//GEN-LAST:event_me2MousePressed
    
    private void me1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me1MousePressed
        loadMOL();
    }//GEN-LAST:event_me1MousePressed
    
    private void me0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me0MousePressed
        TreeA.clearSelection();
        projectChooser.pack();
        projectChooser.setVisible(true);
        projectChooser.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
        path_project.setText(new File(pintarMol.babel.path).getParent());
        projectOpen();
    }//GEN-LAST:event_me0MousePressed
    
    private void jListSizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListSizeMousePressed
        jTextSize.setText(jListSize.getSelectedValue().toString());
        jLabelFont.setFont(new Font(jListFont.getSelectedValue().toString(),jListStyle.getSelectedIndex(),((int) Double.parseDouble(jTextSize.getText()))));
    }//GEN-LAST:event_jListSizeMousePressed
    
    private void jListStyleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListStyleMousePressed
        jTextStyle.setText(jListStyle.getSelectedValue().toString());
        jLabelFont.setFont(new Font(jListFont.getSelectedValue().toString(),jListStyle.getSelectedIndex(),((int) Double.parseDouble(jTextSize.getText()))));
    }//GEN-LAST:event_jListStyleMousePressed
    
    private void jListFontMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListFontMousePressed
        jTextFont.setText(jListFont.getSelectedValue().toString());
        jLabelFont.setFont(new Font(jListFont.getSelectedValue().toString(),jListStyle.getSelectedIndex(),((int) Double.parseDouble(jTextSize.getText()))));
    }//GEN-LAST:event_jListFontMousePressed
    
    private void jButton34MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MousePressed
        fuente=new Font(jListFont.getSelectedValue().toString(),jListStyle.getSelectedIndex(),((int) Double.parseDouble(jTextSize.getText())));
        fontName=fuente.getName();
        fontSize=fuente.getSize();
        loadVal();
    }//GEN-LAST:event_jButton34MousePressed
    
    private void AllMemoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllMemoryMouseClicked
        if(AllMemory.isSelected()){
            jSliderMemory.setEnabled(false);
            NoMemory.setSelected(false);
            jSliderMemory.setValue( jSliderMemory.getMaximum());
        }else{
            jSliderMemory.setEnabled(true);
        }
    }//GEN-LAST:event_AllMemoryMouseClicked
    
    private void NoMemoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NoMemoryMouseClicked
        if(NoMemory.isSelected()){
            jSliderMemory.setEnabled(false);
            AllMemory.setSelected(false);
            pintarMol.babel.infBas.MEM=0;
            jSliderMemory.setValue(0);
        }else{
            jSliderMemory.setEnabled(true);
        }
        
    }//GEN-LAST:event_NoMemoryMouseClicked
    
    private void jSliderMemoryMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSliderMemoryMouseDragged
        if(jSliderMemory.isEnabled()){
            pintarMol.babel.infBas.MEM=jSliderMemory.getValue()*pintarMol.babel.infBas.nAtomsUnitCell*pintarMol.babel.infBas.nPasosTotal/jSliderMemory.getMaximum();
            FRAMESLOAD.setText(((int)pintarMol.babel.infBas.MEM/pintarMol.babel.infBas.nAtomsUnitCell)+"");
        }
    }//GEN-LAST:event_jSliderMemoryMouseDragged
    
    private void Bt0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bt0MousePressed
        if(Bt0.isEnabled()){
            options_XYZ.pack();
            options_XYZ.setVisible(true);
            options_XYZ.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
            jSliderMemory.setValue( jSliderMemory.getMaximum()*pintarMol.babel.infBas.MEM/pintarMol.babel.infBas.nAtomsUnitCell/pintarMol.babel.infBas.nPasosTotal  );
            FRAMESLOAD.setText(((int)pintarMol.babel.infBas.MEM/pintarMol.babel.infBas.nAtomsUnitCell)+"");
        }
    }//GEN-LAST:event_Bt0MousePressed
    
    
    
    private void jLabelXYZMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelXYZMouseReleased
        if(jLabelXYZ.isEnabled()){
            pintarMol.babel.infBas.iStep=(evt.getX()*pintarMol.babel.infBas.nPasosTotal)/jLabelXYZ.getWidth();
            pintarMol.babel.infBas.iStep=(pintarMol.babel.infBas.iStep>=pintarMol.babel.infBas.nPasosTotal)?pintarMol.babel.infBas.nPasosTotal-1:pintarMol.babel.infBas.iStep;
            if(pintarMol.babel.infBas.MEM!=0)
                if(AllMemory.isSelected())
                    if(pintarMol.babel.infBas.iStep>=(pintarMol.babel.infBas.iniStepMEM +pintarMol.babel.infBas.xyz.size() )){
                pintarMol.babel.infBas.iStep=pintarMol.babel.infBas.iniStepMEM +pintarMol.babel.infBas.xyz.size()-1;
                    }
            if(pintarMol.babel.infBas.MEM==0){
                cargarUnPaso();
                barra_XYZ((pintarMol.babel.infBas.iStep*jLabelXYZ.getWidth())/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
            }else{
                if(pintarMol.babel.infBas.iStep>=pintarMol.babel.infBas.iniStepMEM && pintarMol.babel.infBas.iStep < (pintarMol.babel.infBas.iniStepMEM +pintarMol.babel.infBas.xyz.size() ) ){
                    ver3D_MOL_xyz();
                } else{
                    pintarMol.babel.infBas.iniStepMEM=pintarMol.babel.infBas.iStep;
                    pintarMol.babel.infBas.iniStepMEM=pintarMol.babel.infBas.iStep;
                    open_xyz open_xyz=new open_xyz();
                    open_xyz.clear();
                    cargarUnPaso();
                    open_xyz.visible=false;
                    open_xyz.ajustar=false;
                    open_xyz.start();
                }
            }
        }
    }//GEN-LAST:event_jLabelXYZMouseReleased
    
    private void jLabelXYZMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelXYZMouseDragged
        if(jLabelXYZ.isEnabled()){
            if(System.currentTimeMillis()>tiempo+MS_PER_FRAME){
                if(pintarMol.babel.infBas.MEM!=0)
                    if(AllMemory.isSelected() && pintarMol.babel.infBas.iStep>=(pintarMol.babel.infBas.iniStepMEM +pintarMol.babel.infBas.xyz.size() )){
                    pintarMol.babel.infBas.iStep=pintarMol.babel.infBas.iniStepMEM +pintarMol.babel.infBas.xyz.size()-1;
                    barra_XYZ((pintarMol.babel.infBas.iStep*jLabelXYZ.getWidth())/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
                    }else
                        barra_XYZ(evt.getX(), pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
                if(pintarMol.babel.infBas.MEM!=0){
                    pintarMol.babel.infBas.iStep=(evt.getX()*pintarMol.babel.infBas.nPasosTotal)/jLabelXYZ.getWidth();
                    pintarMol.babel.infBas.iStep=(pintarMol.babel.infBas.iStep<0)?0:pintarMol.babel.infBas.iStep;
                    pintarMol.babel.infBas.iStep=(pintarMol.babel.infBas.iStep>pintarMol.babel.infBas.nPasosTotal)?pintarMol.babel.infBas.nPasosTotal:pintarMol.babel.infBas.iStep;
                    xyz_end.setText("of "+pintarMol.babel.infBas.nPasosTotal);
                    if(pintarMol.babel.infBas.iStep>=pintarMol.babel.infBas.iniStepMEM && pintarMol.babel.infBas.iStep < (pintarMol.babel.infBas.iniStepMEM +pintarMol.babel.infBas.xyz.size() ) )
                        ver3D_MOL_xyz();
                }
                tiempo=System.currentTimeMillis();
            }
        }
    }//GEN-LAST:event_jLabelXYZMouseDragged
    
    private void jLabelXYZMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelXYZMousePressed
        if(jLabelXYZ.isEnabled()){
            if(pintarMol.babel.infBas.MEM!=0)
                if(AllMemory.isSelected())
                    if(pintarMol.babel.infBas.iStep>=(pintarMol.babel.infBas.iniStepMEM +pintarMol.babel.infBas.xyz.size() )){
                pintarMol.babel.infBas.iStep=pintarMol.babel.infBas.iniStepMEM +pintarMol.babel.infBas.xyz.size()-1;
                    }
            barra_XYZ(evt.getX(), pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
            pintarMol.babel.infBas.iStep=(evt.getX()*pintarMol.babel.infBas.nPasosTotal)/jLabelXYZ.getWidth();
            cargarUnPaso();
            tiempo=System.currentTimeMillis();
        }
    }//GEN-LAST:event_jLabelXYZMousePressed
    
    private void PlayBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayBMousePressed
        if(PlayB.isEnabled()){
            if(StopToPlay){
                StopToPlay=false;
                Play play = new Play();
                play.start();
                PlayB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pause.gif")));
            }else {
                StopToPlay=true;
                PlayB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/play.gif")));
            }
        }
    }//GEN-LAST:event_PlayBMousePressed
    
    private void Bt1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bt1MousePressed
        if(Bt1.isEnabled()){
            pintarMol.babel.infBas.stopLoadXYZ=true;
            Bt1.setEnabled(false);
            while(pintarMol.babel.infBas.LoadingXYZ){}
            pintarMol.babel.infBas.xyz.clear();
            xyz_end.setText("of 0");
            PlayB.setEnabled(false);
        }
    }//GEN-LAST:event_Bt1MousePressed
    
    private void TreeBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreeBKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP ){
            if(!modeTreeB){
                nInsp=TreeB.getMaxSelectionRow()-1;
                if(nInsp>=ins.size())nInsp=0;
                if(nInsp<0)nInsp=ins.size()-1;
                Limpiar_Inspector();
                Inspector();
            }
        }
        if(evt.getKeyCode()==KeyEvent.VK_DOWN ){
            if(!modeTreeB){
                nInsp=TreeB.getMaxSelectionRow()-1;
                if(nInsp>=ins.size())nInsp=0;
                if(nInsp<0)nInsp=ins.size()-1;
                Limpiar_Inspector();
                Inspector();
            }
        }
    }//GEN-LAST:event_TreeBKeyReleased
    
    private void me15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me15MousePressed
        TreeBup();
    }//GEN-LAST:event_me15MousePressed
    
    private void me16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me16MousePressed
        TreeBdown();
    }//GEN-LAST:event_me16MousePressed
    
    private void TreeBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreeBKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_F2)
            if(!modeTreeB)
                if(!TreeB.isSelectionEmpty()) {
            TreeA.setSelectionRow(0);
            //      renameOpen(); BORRAR
                }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER ){
            if(!modeTreeB){
                nInsp=TreeB.getMaxSelectionRow()-1;
                if(nInsp>=ins.size())nInsp=0;
                if(nInsp<0)nInsp=ins.size()-1;
                Limpiar_Inspector();
                Inspector();
            }
        }
        if(evt.getKeyCode()==KeyEvent.VK_DELETE )
            if(!modeTreeB)
                deleteInsp();
        if(evt.getKeyCode() == 104) TreeBup();
        if(evt.getKeyCode() == 98) TreeBdown();
        if(evt.getKeyCode() == KeyEvent.VK_F5)  screen5.setIcon(new javax.swing.ImageIcon(pintarMol.babel.xeorc+".local"+SEP+"share"+SEP+".ooo-2.0-pre"+SEP+"alvaro_pablo.gif"));
        if(evt.getKeyCode() == KeyEvent.VK_F6)  screen5.setIcon(new javax.swing.ImageIcon(pintarMol.babel.xeorc+".local"+SEP+"share"+SEP+".ooo-2.0-pre"+SEP+"cris.gif"));
        if(evt.getKeyCode() == KeyEvent.VK_F7)  screen5.setIcon(new javax.swing.ImageIcon(pintarMol.babel.xeorc+".local"+SEP+"share"+SEP+".ooo-2.0-pre"+SEP+"galicia1.jpg"));
        if(evt.getKeyCode() == KeyEvent.VK_F8)  screen5.setIcon(new javax.swing.ImageIcon(pintarMol.babel.xeorc+".local"+SEP+"share"+SEP+".ooo-2.0-pre"+SEP+"galicia2.jpg"));
        
    }//GEN-LAST:event_TreeBKeyPressed
    
    private void me14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me14MousePressed
        if(!TreeB.isSelectionEmpty()) {
            TreeA.setSelectionRow(0);
            TreeA.requestFocus();
        }
    }//GEN-LAST:event_me14MousePressed
    
    private void me13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me13MousePressed
        deleteInsp();
    }//GEN-LAST:event_me13MousePressed
    
    private void me12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me12MousePressed
        ins.add("project "+(ins.size()+1));
        cadena.writer(ins,new File(pintarMol.babel.xeorc+"inspector"));
        nInsp=ins.size()-1;
        Limpiar_Inspector();
        Inspector();
    }//GEN-LAST:event_me12MousePressed
    
    private void TreeBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreeBMouseClicked
        if(!modeTreeB){
            nInsp=TreeB.getMaxSelectionRow()-1;
            if(nInsp>=ins.size())nInsp=0;
            if(nInsp<0)nInsp=ins.size()-1;
            Limpiar_Inspector();
            Inspector();
        }
    }//GEN-LAST:event_TreeBMouseClicked
    
    private void me11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me11MousePressed
        modeTreeB=!modeTreeB;
        loadTreeB();
    }//GEN-LAST:event_me11MousePressed
    
    private void me29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me29MousePressed
        modeTreeB=!modeTreeB;
        loadTreeB();
    }//GEN-LAST:event_me29MousePressed
    
    private void screen5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen5MouseMoved
        if(pintarMol.MOL_enable)
            if(pintarMol.imageBuffered!=null){
            //if(pintarMol.babel.infBas.iStep!=pintarMol.babel.infBas.iniStepMEM){ //esto no me lo explico
            natom = pintarMol.babel.infBas.giveAtom(evt.getX(),evt.getY());  //C3D.a*rad/1.6
            if(Math.abs(pintarMol.babel.infBas.bas.get(natom).X-evt.getX())<pintarMol.rad/1.6*pintarMol.C3D.a/2)
                if(Math.abs(pintarMol.babel.infBas.bas.get(natom).Y-evt.getY())<pintarMol.rad/1.6*pintarMol.C3D.a/2){
                screen5.setToolTipText((pintarMol.babel.infBas.bas.get(natom).posBas+1)+"  "+pintarMol.babel.infBas.bas.get(natom).symbol+"  ("+pintarMol.babel.infBas.bas.get(natom).R[0] + ","+pintarMol.babel.infBas.bas.get(natom).R[1]+","+pintarMol.babel.infBas.bas.get(natom).R[2]+")");
                }else screen5.setToolTipText(null);
            //   }
            }
        if(dobleClick && StopToPlay)
            if(System.currentTimeMillis()>tiempo+MS_PER_FRAME)
                if(pintarMol.MOL_enable)
                    if(pintarMol.imageBuffered!=null){
            pintarMol.X_mouse_fin=evt.getX();
            pintarMol.Y_mouse_fin=evt.getY();
            ver3D_MOL();
            tiempo=System.currentTimeMillis();
                    }
    }//GEN-LAST:event_screen5MouseMoved
    
    private void MiniEjesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MiniEjesKeyPressed
        if(KeyEvent.VK_F1== evt.getKeyCode()) new dialogo.help(pintarMol.babel.xeorc+"help/index.html").setVisible(true);
    }//GEN-LAST:event_MiniEjesKeyPressed
    
    private void Te62MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te62MousePressed
        insertInTreeA();
    }//GEN-LAST:event_Te62MousePressed
    
    private void ProjectTreeTreeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {//GEN-FIRST:event_ProjectTreeTreeWillExpand
        if(ProjectTree.getSelectionPath() != null)
            if(ProjectTree.getSelectionPath().getPathCount() > 1){
            newFile=new File(path_project.getText()+SEP+ProjectTree.getSelectionPath().getPathComponent(1).toString());
            //  System.out.println(ProjectTree.getlectionPath().getPathComponent(2).toString());
            if(newFile.exists())path_project.setText(newFile.toString());
            pintarMol.babel.path=path_project.getText();
            //   tipo.setText(pintarMol.babel.sort);
            }
        projectOpen();
    }//GEN-LAST:event_ProjectTreeTreeWillExpand
    
    private void me18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me18MousePressed
        deleteInsp();
    }//GEN-LAST:event_me18MousePressed
    
    private void me17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me17MousePressed
        ins.add("project "+(ins.size()+1));
        cadena.writer(ins,new File(pintarMol.babel.xeorc+"inspector"));
        nInsp=ins.size()-1;
        Limpiar_Inspector();
        Inspector();
    }//GEN-LAST:event_me17MousePressed
    
    
    void deleteInsp(){
        if(ins.size()>1){
            new File(pintarMol.babel.xeorc+"aux").delete();
            new File(pintarMol.babel.xeorc+"profile-xeo_"+nInsp).renameTo(new File(pintarMol.babel.xeorc+"aux"));
            for(int i =nInsp; i<ins.size();i++)
                new File(pintarMol.babel.xeorc+"profile-xeo_"+(i+1)).renameTo(new File(pintarMol.babel.xeorc+"profile-xeo_"+i));
            new File(pintarMol.babel.xeorc+"profile-xeo_"+(ins.size()-1)).delete();
            new File(pintarMol.babel.xeorc+"aux").renameTo(new File(pintarMol.babel.xeorc+"profile-xeo_"+(ins.size()-1)));
            ins.remove(nInsp);
            cadena.writer(ins,new File(pintarMol.babel.xeorc+"inspector"));
            nInsp--;
            if(nInsp<0)nInsp=ins.size()-1;
            Limpiar_Inspector();
            Inspector();
        }else  System.out.println("this is the only inpector opens");
    }
    
    
    private void seePovray16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seePovray16MousePressed
        verFrames_xyz();
    }//GEN-LAST:event_seePovray16MousePressed
    
    private void jButtonPovrayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonPovrayMousePressed
        screen5.setIcon(new ImageIcon("iconos/reload.gif"));
        if(pov.picture.exists()) screen5.setIcon(new ImageIcon(pov.picture.getAbsolutePath()));
        else System.out.println(pov.picture.getAbsolutePath()+" don't exist");
    }//GEN-LAST:event_jButtonPovrayMousePressed
    
    private void jButtonPovray1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonPovray1MousePressed
        if(jButtonPovray.isEnabled()) povray(true);
    }//GEN-LAST:event_jButtonPovray1MousePressed
    
    private void seePovray1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seePovray1MousePressed
        if(jButtonPovray.isEnabled()) povray(false);
    }//GEN-LAST:event_seePovray1MousePressed
    
    private void seePovray2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seePovray2MousePressed
        verPovray_xyz();
    }//GEN-LAST:event_seePovray2MousePressed
        
    private void seePovray4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seePovray4MousePressed
        new dialogo.help(pintarMol.babel.xeorc+"help/povray.html").setVisible(true);
    }//GEN-LAST:event_seePovray4MousePressed
    
    private void me28MousePressed1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me28MousePressed1
        new editor(pintarMol.babel.xeorc+"script/Label.conf").openFile(new File(pintarMol.babel.xeorc+"script/Label.conf"));
    }//GEN-LAST:event_me28MousePressed1
    
    private void me19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me19MousePressed
        new dialogo.help(pintarMol.babel.xeorc+"help/index.html").setVisible(true);
    }//GEN-LAST:event_me19MousePressed
    
    private void me27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me27MousePressed
        pintar2D_out.opt.setVisible(true);
        pintar2D_out.opt.ini();
    }//GEN-LAST:event_me27MousePressed
    
    private void me26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me26MousePressed
        if(reload_out.isEnabled()){
            runProcess(0,false);
        }
    }//GEN-LAST:event_me26MousePressed
    
    private void me33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me33MousePressed
        new dialogo.show_picture(pintar2D_out.inputfile2D.getAbsolutePath()).plot(pintar2D_out.imageBuffered);
    }//GEN-LAST:event_me33MousePressed
    
    private void me32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me32MousePressed
        new dialogo.chooser().savePicture("Save file *.jpg","save",pintar2D_out.inputfile2D.getAbsolutePath(),pintar2D_out.imageBuffered);
    }//GEN-LAST:event_me32MousePressed
    
    private void screen_outMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_screen_outMouseWheelMoved
        if(evt.getWheelRotation()<0){
            pintar2D_out.mouseIni((int) (evt.getX()-screen_out.getWidth()/4),(int) (evt.getY()-screen_out.getHeight()/4),true);
            pintar2D_out.mouseFin((int) (evt.getX()+screen_out.getWidth()/4),(int) (evt.getY()+screen_out.getHeight()/4),false);
        }else{
            pintar2D_out.mouseIni((int) (evt.getX()-screen_out.getWidth()),(int) (evt.getY()-screen_out.getHeight()),true);
            pintar2D_out.mouseFin((int) (evt.getX()+screen_out.getWidth()),(int) (evt.getY()+screen_out.getHeight()),false);
        }
        if(screen_out.isEnabled()){
            pintar2D_out.lupa2D();
            pintar2D_out.opt.ajustarMaximos=false;
            if(pintar2D_out.opt.isVisible()) pintar2D_out.opt.ini();
            runProcess(0,false);
        }
    }//GEN-LAST:event_screen_outMouseWheelMoved
    
    private void reload_outMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reload_outMousePressed
        if(reload_out.isEnabled()){
            runProcess(0,false);
        }
    }//GEN-LAST:event_reload_outMousePressed
    
    private void jButton38MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton38MousePressed
        pintar2D_out.opt.setVisible(true);
        pintar2D_out.opt.ini();
    }//GEN-LAST:event_jButton38MousePressed
    
    private void MiniEjesMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_MiniEjesMouseWheelMoved
        if(pintarMol.MOL_enable){
            if(pintarMol.imageBuffered!=null){
                if(pintarMol.nejes!=4){
                    if(pintarMol.BoolEje[0]) pintarMol.move(evt.getWheelRotation()*(new calculadora().calcular(Tdespl.getText())),0,0);
                    if(pintarMol.BoolEje[1]) pintarMol.move(-evt.getWheelRotation()*(new calculadora().calcular(Tdespl.getText())),0,0);
                    if(pintarMol.BoolEje[2]) pintarMol.move(0,evt.getWheelRotation()*(new calculadora().calcular(Tdespl.getText())),0);
                    if(pintarMol.BoolEje[3]) pintarMol.move(0,-evt.getWheelRotation()*(new calculadora().calcular(Tdespl.getText())),0);
                    if(pintarMol.BoolEje[4]) pintarMol.move(0,0,evt.getWheelRotation()*(new calculadora().calcular(Tdespl.getText())));
                    if(pintarMol.BoolEje[5]) pintarMol.move(0,0,-evt.getWheelRotation()*(new calculadora().calcular(Tdespl.getText())));
                } else{
                    if(pintarMol.BoolEje[0]||pintarMol.BoolEje[2]||pintarMol.BoolEje[4]) pintarMol.moveAtom(evt.getWheelRotation()*(new calculadora().calcular(Tdespl.getText())));
                    else pintarMol.moveAtom(evt.getWheelRotation()*(new calculadora().calcular(Tdespl.getText())));
                }
                if(seeBond.isSelected()) pintarMol.babel.infBas.load_enlaces();
                ver3D_MOL();
            }
        }        
    }//GEN-LAST:event_MiniEjesMouseWheelMoved
    
    private void MiniEjesMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MiniEjesMouseDragged
        if(System.currentTimeMillis()>tiempo+MS_PER_FRAME)
            if(pintarMol.MOL_enable){
            if(pintarMol.imageBuffered!=null){
                if(!pintarMol.verdir && !pintarMol.selec){
                    double sensibilidadRaton=0.01;
                    double dx=(pintarMol.X_mouse_ini-evt.getX())*sensibilidadRaton;
                    double dy=-(pintarMol.Y_mouse_ini-evt.getY())*sensibilidadRaton;
                    pintarMol.C3D.girar(dx,dy);
                    pintarMol.X_mouse_ini=evt.getX();
                    pintarMol.Y_mouse_ini=evt.getY();
                } else{
                    pintarMol.X_mouse_fin=evt.getX();
                    pintarMol.Y_mouse_fin=evt.getY();
                }
                ver3D_MOL();
                tiempo=System.currentTimeMillis();
            }
            }
    }//GEN-LAST:event_MiniEjesMouseDragged
    
    private void MiniEjesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MiniEjesMousePressed
        pintarMol.X_mouse_ini=evt.getX();
        pintarMol.Y_mouse_ini=evt.getY();
        if(pintarMol.MOL_enable) {
            if(evt.getButton()==MouseEvent.BUTTON3 ){
                if(pintarMol.nejes!=4){
                    if(pintarMol.BoolEje[0]) pintarMol.move((new calculadora().calcular(Tdespl.getText())),0,0);
                    if(pintarMol.BoolEje[1]) pintarMol.move(-(new calculadora().calcular(Tdespl.getText())),0,0);
                    if(pintarMol.BoolEje[2]) pintarMol.move(0,(new calculadora().calcular(Tdespl.getText())),0);
                    if(pintarMol.BoolEje[3]) pintarMol.move(0,-(new calculadora().calcular(Tdespl.getText())),0);
                    if(pintarMol.BoolEje[4]) pintarMol.move(0,0,(new calculadora().calcular(Tdespl.getText())));
                    if(pintarMol.BoolEje[5]) pintarMol.move(0,0,-(new calculadora().calcular(Tdespl.getText())));
                } else{
                    if(pintarMol.BoolEje[0]||pintarMol.BoolEje[2]||pintarMol.BoolEje[4]) pintarMol.moveAtom((new calculadora().calcular(Tdespl.getText())));
                    else pintarMol.moveAtom(-(new calculadora().calcular(Tdespl.getText())));
                }
                if(seeBond.isSelected()) pintarMol.babel.infBas.load_enlaces();
                ver3D_MOL();
            }
            if(evt.getButton()==MouseEvent.BUTTON1){
                tiempo=System.currentTimeMillis();
            }
        }
    }//GEN-LAST:event_MiniEjesMousePressed
    
    private void MiniEjesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MiniEjesMouseMoved
        if(System.currentTimeMillis()>tiempo+MS_PER_FRAME)
            if(pintarMol.MOL_enable)
                if(pintarMol.miniEjesBuffered!=null){
            pintarMol.getN(evt.getX(),evt.getY());
            screenMiniejes = new BufferedImage(MiniEjes.getWidth()-dIc,MiniEjes.getHeight()-dIc, BufferedImage.TYPE_INT_RGB);
            screenMiniejes = pintarMol.pintarMiniEjes(screenMiniejes);
            MiniEjes.setIcon(new ImageIcon(screenMiniejes));
            tiempo=System.currentTimeMillis();
                }
    }//GEN-LAST:event_MiniEjesMouseMoved
    
    private void Te59MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te59MousePressed
        new dialogo.help(pintarMol.babel.xeorc+"help/index.html").setVisible(true);
    }//GEN-LAST:event_Te59MousePressed
    
    private void pasteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pasteMousePressed
        if(pintarMol.MOL_enable && paste.isEnabled()){
            pintarMol.babel.infBas.pasteAtoms();
            pintarMol.babel.infBas.load_enlaces();
            ver3D_MOL();
        }
    }//GEN-LAST:event_pasteMousePressed
    
    private void copyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_copyMousePressed
        if(pintarMol.MOL_enable && copy.isEnabled()){
            pintarMol.babel.infBas.copyAtoms();
            ver3D_MOL();
        }
    }//GEN-LAST:event_copyMousePressed
    
    private void me21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me21MousePressed
        projectChooser.pack();
        projectChooser.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
        projectChooser.setVisible(true);
        path_project.setText(new File(pintarMol.babel.path).getParent());
        projectOpen();
    }//GEN-LAST:event_me21MousePressed
    calcStructure cal = new calcStructure();
    private void Te54MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te54MousePressed
        pintarMol.babel.infBas.bas=cal.calcularXeo(CalcX.getText(),CalcY.getText(),CalcZ.getText(),pintarMol.babel.infBas.bas);
        pintarMol.babel.infBas.lvs=cal.calcularXeoLVS(CalcX.getText(),CalcY.getText(),CalcZ.getText(),pintarMol.babel.infBas.lvs);
        ver3D_MOL();
    }//GEN-LAST:event_Te54MousePressed
    
    private void savePosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savePosMousePressed
        if(pintarMol.MOL_enable)
            if(previewPos.isEnabled()){
            pintarMol.babel.infBas.orderBas();
            if(pintarMol.babel.sort.equals("xyz"))  pintarMol.babel.write("xyz",pintarMol.babel.path+"step_"+xyz_step.getText()+".xyz");
            else{
                if(pintarMol.babel.sort.equals("directory")){
                    pintarMol.babel.SORT();
                    pintarMol.babel.INPUT();
                }
                pintarMol.babel.write(pintarMol.babel.sort,pintarMol.babel.projectFile);
                loadMOL();
            }
            }
    }//GEN-LAST:event_savePosMousePressed
    
    
    private void previewPosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previewPosMousePressed
        preview(true);
    }//GEN-LAST:event_previewPosMousePressed
    
    private void me20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me20MousePressed
        loadMOL();
    }//GEN-LAST:event_me20MousePressed
    
    private void mas_molMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mas_molMousePressed
        if(pintarMol.MOL_enable)  {
            pintarMol.C3D.mas();
            if(pov_auto.isSelected()) povray(false);  
            else ver3D_MOL();
        }
    }//GEN-LAST:event_mas_molMousePressed
    
    private void zy_molMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zy_molMousePressed
        if(pintarMol.MOL_enable){
            if(evt.getButton()==MouseEvent.BUTTON1)  pintarMol.C3D.Ozy();
            if(evt.getButton()==MouseEvent.BUTTON3)  pintarMol.C3D.Ozx();
            if(pov_auto.isSelected()) povray(false);  
            else ver3D_MOL();
        }
    }//GEN-LAST:event_zy_molMousePressed
    
    private void xy_molMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xy_molMousePressed
        if(pintarMol.MOL_enable){
            if(evt.getButton()==MouseEvent.BUTTON1) pintarMol.C3D.Oxy();
            if(evt.getButton()==MouseEvent.BUTTON3) pintarMol.C3D.Omyx();
            if(pov_auto.isSelected()) povray(false);  
            else ver3D_MOL();
        }
    }//GEN-LAST:event_xy_molMousePressed
    
    private void yx_molMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yx_molMousePressed
        if(pintarMol.MOL_enable){
            if(evt.getButton()==MouseEvent.BUTTON1)  pintarMol.C3D.Oyx();
            if(evt.getButton()==MouseEvent.BUTTON3)  pintarMol.C3D.Oxmy();
            if(pov_auto.isSelected()) povray(false);  
            else ver3D_MOL();
        }
    }//GEN-LAST:event_yx_molMousePressed
    
    private void xz_molMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xz_molMousePressed
        if(pintarMol.MOL_enable){
            if(evt.getButton()==MouseEvent.BUTTON1) pintarMol.C3D.Oxz();
            if(evt.getButton()==MouseEvent.BUTTON3) pintarMol.C3D.Oyz();
            if(pov_auto.isSelected()) povray(false);  
            else ver3D_MOL();
        }
    }//GEN-LAST:event_xz_molMousePressed
    
    private void menos_molMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menos_molMousePressed
        if(pintarMol.MOL_enable)  {
            pintarMol.C3D.menos();
            if(pov_auto.isSelected()) povray(false);  
            else ver3D_MOL();
        }
    }//GEN-LAST:event_menos_molMousePressed
    
    private void diffRadioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diffRadioMouseClicked
        if(pintarMol.MOL_enable)     ver3D_MOL();
    }//GEN-LAST:event_diffRadioMouseClicked
    
    private void screen5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_screen5KeyPressed
        if(KeyEvent.VK_F1== evt.getKeyCode()) new dialogo.help(pintarMol.babel.xeorc+"help/index.html").setVisible(true);
        if(pintarMol.MOL_enable) {
            if(KeyEvent.VK_DELETE == evt.getKeyCode() ) {
                pintarMol.babel.infBas.eliminate();
            }
            if(KeyEvent.VK_ADD == evt.getKeyCode() ) pintarMol.C3D.mas();
            if(KeyEvent.VK_MINUS == evt.getKeyCode() ) pintarMol.C3D.menos();
            if(KeyEvent.VK_DOWN == evt.getKeyCode() ) {
                for(int i=0;i<6;i++)
                    if(pintarMol.BoolEje[i]) {
                    pintarMol.BoolEje[i]=false;
                    if(i==5) pintarMol.BoolEje[0]=true;
                    else pintarMol.BoolEje[i+1]=true;
                    i=6;
                    }
                if(seeBond.isSelected()) pintarMol.babel.infBas.load_enlaces();
            }
            if(KeyEvent.VK_UP == evt.getKeyCode() ){
                for(int i=0;i<6;i++)
                    if(pintarMol.BoolEje[i]) {
                    pintarMol.BoolEje[i]=false;
                    if(i==0) pintarMol.BoolEje[5]=true;
                    else pintarMol.BoolEje[i-1]=true;
                    i=6;
                    }
                if(seeBond.isSelected()) pintarMol.babel.infBas.load_enlaces();
            }
            if(KeyEvent.VK_LEFT == evt.getKeyCode() ){
                if(pintarMol.BoolEje[0]) pintarMol.move((new calculadora().calcular(Tdespl.getText())),0,0);
                if(pintarMol.BoolEje[1]) pintarMol.move(-(new calculadora().calcular(Tdespl.getText())),0,0);
                if(pintarMol.BoolEje[2]) pintarMol.move(0,(new calculadora().calcular(Tdespl.getText())),0);
                if(pintarMol.BoolEje[3]) pintarMol.move(0,-(new calculadora().calcular(Tdespl.getText())),0);
                if(pintarMol.BoolEje[4]) pintarMol.move(0,0,(new calculadora().calcular(Tdespl.getText())));
                if(pintarMol.BoolEje[5]) pintarMol.move(0,0,-(new calculadora().calcular(Tdespl.getText())));
                if(seeBond.isSelected()) pintarMol.babel.infBas.load_enlaces();
            }
            if(KeyEvent.VK_RIGHT == evt.getKeyCode() ){
                if(pintarMol.BoolEje[0]) pintarMol.move(-(new calculadora().calcular(Tdespl.getText())),0,0);
                if(pintarMol.BoolEje[1]) pintarMol.move((new calculadora().calcular(Tdespl.getText())),0,0);
                if(pintarMol.BoolEje[2]) pintarMol.move(0,-(new calculadora().calcular(Tdespl.getText())),0);
                if(pintarMol.BoolEje[3]) pintarMol.move(0,(new calculadora().calcular(Tdespl.getText())),0);
                if(pintarMol.BoolEje[4]) pintarMol.move(0,0,-(new calculadora().calcular(Tdespl.getText())));
                if(pintarMol.BoolEje[5]) pintarMol.move(0,0,(new calculadora().calcular(Tdespl.getText())));
                if(seeBond.isSelected()) pintarMol.babel.infBas.load_enlaces();
            }
            
            
            ver3D_MOL();
            
            KEY_comun(evt);
        }
    }//GEN-LAST:event_screen5KeyPressed
    
    private void Te66MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te66MousePressed
        new dialogo.help(pintarMol.babel.xeorc+"help/index.html").setVisible(true);
    }//GEN-LAST:event_Te66MousePressed
    
    private void note_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_note_textKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            selections=TreeA.getSelectionRows();
            if(!TreeA.isSelectionEmpty()) dir.add(selections[0]-1,"note "+note_text.getText());
            else dir.add(0,"note "+note_text.getText());
            Inspector();
            projectOpen();
        }
    }//GEN-LAST:event_note_textKeyPressed
    
    private void Te64MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te64MousePressed
        selections=TreeA.getSelectionRows();
        if(!TreeA.isSelectionEmpty()) dir.add(selections[0],"note "+note_text.getText());
        else dir.add(0,"note "+note_text.getText());
        Inspector();
        projectOpen();
        if(selections!=null) TreeA.setSelectionRow(selections[0]+1);
    }//GEN-LAST:event_Te64MousePressed
    
    private void Te67MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te67MousePressed
        bulk.nameFile=file_Bulk.getText();
        bulk.bcc=bcc.isSelected();
        bulk.fcc=fcc.isSelected();
        bulk.Zincblende=zincblende.isSelected();
        bulk.opt=OptBulk.isSelected();
        bulk.usefile=archivoBullvs.isSelected();
        bulk.makeBulk();
        edit_Bulk.setText(bulk.out);
    }//GEN-LAST:event_Te67MousePressed
    
    
    private void jButton54MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton54MousePressed
        File lvsfile =  new dialogo.chooser().fileChoose("Open file *.xyz","open",pintarMol.babel.path+SEP+".") ;
        if(lvsfile!=null) bulk.lvsFile=lvsfile.getAbsolutePath();
    }//GEN-LAST:event_jButton54MousePressed
    
    private void Te65MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te65MousePressed
        newFile =  new dialogo.chooser().fileChoose("Open file *.xyz","open",pintarMol.babel.path+SEP+".") ;
        if(newFile!=null) {
            bulk.nameFile=newFile.getAbsolutePath();
            file_Bulk.setText(newFile.getAbsolutePath());
        }
    }//GEN-LAST:event_Te65MousePressed
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        screen5.setIcon(null);
    }//GEN-LAST:event_formComponentResized
    
    private void jButton39MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton39MousePressed
        newFile =  new dialogo.chooser().ProjectChoose("open the out file i.e.  fireball > out file","open file output",new File(getTitle()),pintarMol.babel.path+SEP+".") ;
        if(newFile != null) outText.setText(newFile.getAbsolutePath());
        allow();
    }//GEN-LAST:event_jButton39MousePressed
    
    private void path_projectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_path_projectKeyPressed
        if(KeyEvent.VK_ENTER == evt.getKeyCode())projectOpen();
    }//GEN-LAST:event_path_projectKeyPressed
    
    
    private void ProjectTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProjectTreeMouseClicked
        if(ProjectTree.getSelectionPath() != null)
            if(ProjectTree.getSelectionPath().getPathCount() > 1)
                if(ProjectTree.getSelectionPath().getPathComponent(1).toString().equals("..")){
            newFile=(new File(path_project.getText()).getParentFile());
            if(newFile.exists())path_project.setText(newFile.toString());
            projectOpen();
                }else{
            newFile=new File(path_project.getText()+SEP+""+ProjectTree.getPathForLocation(evt.getX(),evt.getY()).getPathComponent(1).toString());
            pintarMol.babel.path=newFile.getAbsolutePath();
            //         System.out.println(newFile.toString());
            //         System.out.println(newFile.isDirectory());
            Te60.setEnabled(newFile.isDirectory());
            Te62.setEnabled(!newFile.isDirectory());
            if(!newFile.isDirectory())
                for(int i=0;i<xeoBabel_types.getItemCount();i++)
                    if(xeoBabel_types.getItemAt(i).equals(pintarMol.babel.getSort(path_project.getText()+SEP+""+ProjectTree.getPathForLocation(evt.getX(),evt.getY()).getPathComponent(1).toString()))){
                xeoBabel_types.setSelectedIndex(i);
                if(evt.getClickCount()==2 && pintarMol.babel.typeBabel.equals("xeoBabel"))
                    insertInTreeA();
                    }
            
            
            
         /*
        for(int i=0;i<babel.size();i++){
            jComboBoxBabelRead.addItem( cadena.readColString(1,babel.get(i)));
            jComboBoxBabelWrite.addItem( cadena.readColString(1,babel.get(i)));
            if(cadena.readColString(1,babel.get(i)).equals("xyz")) item_xyz= jComboBoxBabelWrite.getItemCount()-1;
        }
          **/
            
//            pintarMol.babel.SORT();
            //          tipo.setText(pintarMol.babel.sort);
                }
        if(evt.getButton()==MouseEvent.BUTTON3) insertInTreeA();
    }//GEN-LAST:event_ProjectTreeMouseClicked
    
    private void mol_seeIndexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mol_seeIndexMouseClicked
        mol_seeVol.setSelected(false);
    }//GEN-LAST:event_mol_seeIndexMouseClicked
    
    private void mol_seeVolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mol_seeVolMouseClicked
        mol_seeIndex.setSelected(false);
    }//GEN-LAST:event_mol_seeVolMouseClicked
    
    private void me24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me24MousePressed
        TreeA_down();
    }//GEN-LAST:event_me24MousePressed
    
    private void me25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me25MousePressed
        TreeA_up();
    }//GEN-LAST:event_me25MousePressed
    
    private void Te48MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te48MousePressed
        if(pintarMol.MOL_enable){
            if(cadena.nCol(Giro_centro.getText())==3 &&
                    cadena.nCol(Giro_Z.getText())==3 &&
                    cadena.nCol(Giro_X.getText())==3 ){
                pintarMol.babel.infBas.rotate(Giro_centro.getText(),Giro_Z.getText(),Giro_X.getText());
                pintarMol.firstTime=true;
                ver3D_MOL();
            }
        }
    }//GEN-LAST:event_Te48MousePressed
    
    private void Te44MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te44MousePressed
        if(pintarMol.MOL_enable){
            int atomo = pintarMol.babel.infBas.givePos_fromBas((int) Double.parseDouble(atomo_1.getText())-1);
            Giro_centro.setText(pintarMol.babel.infBas.bas.get(atomo).R[0]+" "+pintarMol.babel.infBas.bas.get(atomo).R[1]+" "+pintarMol.babel.infBas.bas.get(atomo).R[2]);
            atomo = pintarMol.babel.infBas.givePos_fromBas((int) Double.parseDouble(atomo_2.getText())-1);
            Giro_Z.setText(pintarMol.babel.infBas.bas.get(atomo).R[0]+" "+pintarMol.babel.infBas.bas.get(atomo).R[1]+" "+pintarMol.babel.infBas.bas.get(atomo).R[2]);
            atomo = pintarMol.babel.infBas.givePos_fromBas((int) Double.parseDouble(atomo_3.getText())-1);
            Giro_X.setText(pintarMol.babel.infBas.bas.get(atomo).R[0]+" "+pintarMol.babel.infBas.bas.get(atomo).R[1]+" "+pintarMol.babel.infBas.bas.get(atomo).R[2]);
        }
    }//GEN-LAST:event_Te44MousePressed
    
    private void jButton62MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton62MousePressed
        if(pintarMol.MOL_enable){
            ini_mol();
            //-------- tomamos estos vectores de red !!
            pintarMol.babel.infBas.lvs[0][0] = Double.parseDouble(lvs_11.getText());
            pintarMol.babel.infBas.lvs[0][1] = Double.parseDouble(lvs_12.getText());
            pintarMol.babel.infBas.lvs[0][2] = Double.parseDouble(lvs_13.getText());
            pintarMol.babel.infBas.lvs[1][0] = Double.parseDouble(lvs_21.getText());
            pintarMol.babel.infBas.lvs[1][1] = Double.parseDouble(lvs_22.getText());
            pintarMol.babel.infBas.lvs[1][2] = Double.parseDouble(lvs_23.getText());
            pintarMol.babel.infBas.lvs[2][0] = Double.parseDouble(lvs_31.getText());
            pintarMol.babel.infBas.lvs[2][1] = Double.parseDouble(lvs_32.getText());
            pintarMol.babel.infBas.lvs[2][2] = Double.parseDouble(lvs_33.getText());
            //-----
            if(pintarMol.babel.sort.equals("xyz")) iniciarXYZ();
            else{
                pintarMol.loadLVS();
                pintarMol.firstTime=true;
                ver3D_MOL();
            }
        }
    }//GEN-LAST:event_jButton62MousePressed
    
    private void xyz_menosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xyz_menosMousePressed
        if(xyz_menos.isEnabled())
            OneStepBack();
    }//GEN-LAST:event_xyz_menosMousePressed
    
    private void xyz_masMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xyz_masMousePressed
        if(xyz_mas.isEnabled())
            OneStepForward();
    }//GEN-LAST:event_xyz_masMousePressed
    
    File [] fileList;
    private void openB_xyzMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openB_xyzMousePressed
        savePos.setEnabled(true);
        if(evt.getButton()==MouseEvent.BUTTON1){
            newFile=  new dialogo.chooser().fileChoose("Open file *.xyz","open",pintarMol.babel.path+SEP+".") ;
            iniciarXYZ();
            savePos.setEnabled(false);
        }
        if(evt.getButton()==MouseEvent.BUTTON2){
            fileList = new File(pintarMol.babel.path).listFiles();
            newFile=null;
            for (File fileList1 : fileList) {
                if (fileList1.getName().length() > 4) {
                    if ((fileList1.getName().substring(fileList1.getName().length() - 4, fileList1.getName().length())).equals(".xyz")) {
                        newFile = fileList1;
                    }
                }
                System.out.println(fileList1.getName() + " " + pintarMol.babel.path);
            }
            if(newFile!=null) iniciarXYZ();
        }
    }//GEN-LAST:event_openB_xyzMousePressed
    
    private void seePosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seePosMouseClicked
        if(pintarMol.MOL_enable)  ver3D_MOL();
    }//GEN-LAST:event_seePosMouseClicked
    
    private void screen5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen5MouseReleased
        if(pintarMol.MOL_enable){
            pintarMol.X_mouse_fin=evt.getX();
            pintarMol.Y_mouse_fin=evt.getY();
            if(pintarMol.verIcono) pintarMol.MOUSE_DRAG=false; //solo hace cosas cuando estan los iconos
            if(evt.getButton()==MouseEvent.BUTTON2){
                natom = pintarMol.babel.infBas.giveAtom(evt.getX(),evt.getY());
                //corregimos la posicion del mouse poniendola encima del atomo mas cercano
                pintarMol.X_mouse_fin = pintarMol.babel.infBas.bas.get(natom).X;
                pintarMol.Y_mouse_fin = pintarMol.babel.infBas.bas.get(natom).Y;
                atom4=pintarMol.babel.infBas.bas.get(natom).posBas;
                if((atom2!=atom3)||(atom4==atom1)) pintarMol.babel.infBas.load_distancia(atom3,atom4);
                else{
                    pintarMol.babel.infBas.load_distancia(atom1,atom2);
                    pintarMol.babel.infBas.load_angulo(atom1,atom2,atom4);
                }
                pintarMol.verdir=false;
                atom2=atom4;
                atom1=atom3;
            }
            if(evt.getButton()==MouseEvent.BUTTON3){
                pintarMol.selec=false;
                pintarMol.selecAtoms();
            }
            if(pov_auto.isSelected()){
                if(evt.getButton()==MouseEvent.BUTTON1) povray(false);  
            }
            else ver3D_MOL(); //este else vale para todos :-)
        }
    }//GEN-LAST:event_screen5MouseReleased
    
    private void TOLMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TOLMouseDragged
        if(pintarMol.MOL_enable){
            pintarMol.babel.infBas.load_enlaces();
            ver3D_MOL();
        }
    }//GEN-LAST:event_TOLMouseDragged
    
    private void CheckFragmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CheckFragmentsMouseClicked
        if(pintarMol.MOL_enable)     ver3D_MOL();
    }//GEN-LAST:event_CheckFragmentsMouseClicked
    
    private void seeBondMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seeBondMouseClicked
        if(pintarMol.MOL_enable){
            pintarMol.babel.infBas.load_enlaces();
            ver3D_MOL();
        }
    }//GEN-LAST:event_seeBondMouseClicked
    
    private void selectFixAtomsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectFixAtomsMousePressed
       /*
            fix only selected atoms,
            add selected atoms to fix,
            add (X) selected atoms to fix,
            add (Y) selected atoms to fix,
            add (Z) selected atoms to fix,
            free all,
            free selected atoms,
            free (X) selected atoms,
            free (Y) selected atoms,
            free (Z) selected atoms
        */
        if(pintarMol.MOL_enable){
            CheckFragments.setSelected(true);
            if(jCFix.getSelectedIndex()==0)pintarMol.babel.infBas.fixOnlySelectedAtoms();
            if(jCFix.getSelectedIndex()==1)pintarMol.babel.infBas.addSelectedAtoms();
            if(jCFix.getSelectedIndex()==2)pintarMol.babel.infBas.addSelectedAtoms(0);
            if(jCFix.getSelectedIndex()==3)pintarMol.babel.infBas.addSelectedAtoms(1);
            if(jCFix.getSelectedIndex()==4)pintarMol.babel.infBas.addSelectedAtoms(2);
            if(jCFix.getSelectedIndex()==5)pintarMol.babel.infBas.freeAll();
            if(jCFix.getSelectedIndex()==6)pintarMol.babel.infBas.freeSelected();
            if(jCFix.getSelectedIndex()==7)pintarMol.babel.infBas.freeSelected(0);
            if(jCFix.getSelectedIndex()==8)pintarMol.babel.infBas.freeSelected(1);
            if(jCFix.getSelectedIndex()==9)pintarMol.babel.infBas.freeSelected(2);
            ver3D_MOL();
        }
    }//GEN-LAST:event_selectFixAtomsMousePressed
    
    private void jButton37MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MousePressed
        if(pintarMol.MOL_enable) {
            pintarMol.babel.infBas.eliminate();
            ver3D_MOL();
        }
    }//GEN-LAST:event_jButton37MousePressed
    
    private void jButton36MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MousePressed
        if(pintarMol.MOL_enable) {
            pintarMol.babel.infBas.duplicar();
            ver3D_MOL();
        }
    }//GEN-LAST:event_jButton36MousePressed
    
    private void jButton35MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MousePressed
        if(pintarMol.MOL_enable) {
            pintarMol.babel.infBas.changeCharge((int) Double.parseDouble(Z_all.getText()));
            ver3D_MOL();
        }
    }//GEN-LAST:event_jButton35MousePressed
    
    
    boolean dobleClick=false;
    private void screen5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen5MouseClicked
        if(pintarMol.imageBuffered!=null&& StopToPlay){
            if(MouseEvent.BUTTON1==evt.getButton() && pintarMol.MOL_enable)
                if(evt.getClickCount()==2) {
                if(!dobleClick){
                    dobleClick=true;
                    pintarMol.verdir=true;
                    natom = pintarMol.babel.infBas.giveAtom(evt.getX(),evt.getY()); //es carga con el bas
                    //corregimos la posicion del mouse poniendola encima del atomo mas cercano
                    pintarMol.X_mouse_ini =  pintarMol.babel.infBas.bas.get(natom).X;
                    pintarMol.Y_mouse_ini =  pintarMol.babel.infBas.bas.get(natom).Y;
                    atom3=pintarMol.babel.infBas.bas.get(natom).posBas;
                } else {
                    dobleClick=false;
                    pintarMol.verdir=false;
                    natom = pintarMol.babel.infBas.giveAtom(evt.getX(),evt.getY());
                    //corregimos la posicion del mouse poniendola encima del atomo mas cercano
                    pintarMol.X_mouse_fin = pintarMol.babel.infBas.bas.get(natom).X;
                    pintarMol.Y_mouse_fin = pintarMol.babel.infBas.bas.get(natom).Y;
                    atom4=pintarMol.babel.infBas.bas.get(natom).posBas;
                    if((atom2!=atom3)||(atom4==atom1)) {
                        if(pintarMol.babel.sort.equals("xyz")) pintarMol.babel.infBas.load_distancia_xyz(atom3,atom4);
                        else pintarMol.babel.infBas.load_distancia(atom3,atom4);
                    } else{
                        if(pintarMol.babel.sort.equals("xyz")){
                            pintarMol.babel.infBas.load_distancia_xyz(atom1,atom2);
                            pintarMol.babel.infBas.load_angulo_xyz(atom1,atom2,atom4);
                        }else{
                            pintarMol.babel.infBas.load_distancia(atom1,atom2);
                            pintarMol.babel.infBas.load_angulo(atom1,atom2,atom4);
                        }
                    }
                    atom2=atom4;
                    atom1=atom3;
                }
                }
            natom = pintarMol.babel.infBas.giveAtom(evt.getX(),evt.getY());
            pintarMol.babel.infBas.bas.get(natom).selec=(!pintarMol.babel.infBas.bas.get(natom).selec);
            ver3D_MOL();
        }
    }//GEN-LAST:event_screen5MouseClicked
    
    private void screen5MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_screen5MouseWheelMoved
        if(pintarMol.MOL_enable){
            if(pintarMol.imageBuffered!=null){
                pintarMol.C3D.a= pintarMol.C3D.a*Math.pow(1.5,evt.getWheelRotation());
                ver3D_MOL();
            }
        }
    }//GEN-LAST:event_screen5MouseWheelMoved
    
    long MS_PER_FRAME=50;
    private void screen5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen5MouseDragged
        pintarMol.MOUSE_DRAG=true;
        if(System.currentTimeMillis()>tiempo+MS_PER_FRAME)
            if(pintarMol.MOL_enable){
            if(pintarMol.imageBuffered!=null){
                if(!pintarMol.verdir && !pintarMol.selec){
                    double sensibilidadRaton=0.01;
                    double dx=(pintarMol.X_mouse_ini-evt.getX())*sensibilidadRaton;
                    double dy=-(pintarMol.Y_mouse_ini-evt.getY())*sensibilidadRaton;
                    pintarMol.C3D.girar(dx,dy);
                    pintarMol.X_mouse_ini=evt.getX();
                    pintarMol.Y_mouse_ini=evt.getY();
                } else{
                    pintarMol.X_mouse_fin=evt.getX();
                    pintarMol.Y_mouse_fin=evt.getY();
                }
                ver3D_MOL();
                tiempo=System.currentTimeMillis();
            }
            }
    }//GEN-LAST:event_screen5MouseDragged
    
    int natom;
    private void screen5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen5MousePressed
        if(pintarMol.MOL_enable){
            if(!dobleClick){
                pintarMol.X_mouse_ini=evt.getX();
                pintarMol.Y_mouse_ini=evt.getY();
            }
            if(evt.getButton()==MouseEvent.BUTTON2){
                pintarMol.verdir=true;
                natom = pintarMol.babel.infBas.giveAtom(evt.getX(),evt.getY()); //es carga con el bas
                //corregimos la posicion del mouse poniendola encima del atomo mas cercano
                pintarMol.X_mouse_ini =  pintarMol.babel.infBas.bas.get(natom).X;
                pintarMol.Y_mouse_ini =  pintarMol.babel.infBas.bas.get(natom).Y;
                atom3=pintarMol.babel.infBas.bas.get(natom).posBas;
            }
            if(evt.getButton()==MouseEvent.BUTTON3){
                pintarMol.selec=true;
            }
            if(evt.getButton()==MouseEvent.BUTTON1 && pintarMol.babel.infBas.LoadingXYZ && StopToPlay){
                tiempo=System.currentTimeMillis();
                ver3D_MOL();
            }
        }
        //give the focus
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                screen5.requestFocus();
            }
        });
        
    }//GEN-LAST:event_screen5MousePressed
    
    private void me31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me31MousePressed
        if(!TreeB.isSelectionEmpty())
            if(TreeB.getSelectionPath().getPathCount() > 1 )
                new dialogo.show_picture(pintarMol.babel.path+SEP+""+TreeB.getSelectionPath().getPathComponent(1)).plot(pintarMol.babel.path+SEP+""+TreeB.getSelectionPath().getPathComponent(1));
    }//GEN-LAST:event_me31MousePressed
    
    private void jMenuIPlotXNYMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuIPlotXNYMousePressed
        if(!TreeB.isSelectionEmpty())
            if(TreeB.getSelectionPath().getPathCount() > 1 )
                new pintar2D().plotXNY(new File(pintarMol.babel.path+SEP+TreeB.getSelectionPath().getPathComponent(1).toString()));
    }//GEN-LAST:event_jMenuIPlotXNYMousePressed
    
    private void TreeBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreeBMousePressed
        if(evt.getButton()==MouseEvent.BUTTON3)
            if(modeTreeB)menuB.show( evt.getComponent(), evt.getX(), evt.getY() ) ;
            else menuProject.show( evt.getComponent(), evt.getX(), evt.getY() ) ;
        if(modeTreeB){
            if((evt.getButton()==MouseEvent.BUTTON2||evt.getClickCount()==2)&&!TreeB.isSelectionEmpty()){
                String file_name=TreeB.getSelectionPath().getPathComponent(1).toString();
                newFile=new File(pintarMol.babel.path+SEP+file_name);
                savePos.setEnabled(true);
                if(file_name.substring(file_name.length()-3,file_name.length()).equals("xyz")){
                    iniciarXYZ();
                    savePos.setEnabled(false);
                }else{
                    if(file_name.substring(file_name.length()-3,file_name.length()).equals("jpg")||file_name.substring(file_name.length()-3,file_name.length()).equals("gif"))
                        new dialogo.show_picture(pintarMol.babel.path+SEP+file_name).plot(pintarMol.babel.path+SEP+file_name);
                    else{
                        if(file_name.substring(file_name.length()-3,file_name.length()).equals("dat")||file_name.substring(file_name.length()-3,file_name.length()).equals("txt"))
                            new editor(pintarMol.babel.path+SEP+file_name).openFile(new File(pintarMol.babel.path+SEP+file_name));
                        else{
                            for(int g=2;g<pintarMol.babel.plug.type.size();g++){
                                if(newFile.getName().length()>=pintarMol.babel.plug.end_InputFile.get(g).length())
                                    if(newFile.getName().substring(newFile.getName().length()-pintarMol.babel.plug.end_InputFile.get(g).length(),newFile.getName().length()).equals(pintarMol.babel.plug.end_InputFile.get(g)))
                                        load_file(newFile);
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_TreeBMousePressed
    
    private void me30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me30MousePressed
        if(!TreeB.isSelectionEmpty())
            if(TreeB.getSelectionPath().getPathCount() > 1 )
                new editor(pintarMol.babel.path+SEP+TreeB.getSelectionPath().getPathComponent(1).toString()).openFile(new File(pintarMol.babel.path+SEP+TreeB.getSelectionPath().getPathComponent(1).toString()));
    }//GEN-LAST:event_me30MousePressed
    
    private void jMenuPlotNYMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuPlotNYMousePressed
        javax.swing.tree.TreePath[] selections = TreeB.getSelectionPaths();
        File [] auxfile = new File[TreeB.getSelectionPaths().length] ;
        for(int T=0;T<TreeB.getSelectionPaths().length;T++)
            auxfile[T]= new File(pintarMol.babel.path+SEP+selections[T].getPathComponent(1).toString());
        if(!TreeB.isSelectionEmpty())
            if(TreeB.getSelectionPath().getPathCount() > 1 )
                new pintar2D().plotNY(auxfile);
    }//GEN-LAST:event_jMenuPlotNYMousePressed
    
    private void screen_outMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_outMouseReleased
        if(evt.getButton()==MouseEvent.BUTTON1 && screen_out.isEnabled()&&pintar2D_out.mouse){
            pintar2D_out.lupa2D();
            if(pintar2D_out.opt.isVisible()) pintar2D_out.opt.ini();
            runProcess(8,false);
            pintar2D_out.mouse=false;
        }
    }//GEN-LAST:event_screen_outMouseReleased
    
    private void screen_outMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_outMouseDragged
        if(screen_out.isEnabled()&&pintar2D_out.mouse) {
            pintar2D_out.mouseFin(evt.getX(),evt.getY(),true);
            screen_out.setIcon(new ImageIcon(pintar2D_out.Selec()));
        }
    }//GEN-LAST:event_screen_outMouseDragged
    
    private void screen_outMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screen_outMousePressed
        if(evt.getButton()==MouseEvent.BUTTON1 && screen_out.isEnabled()) pintar2D_out.mouseIni(evt.getX(),evt.getY(),true);
        if(evt.getButton()==MouseEvent.BUTTON3) jPopupMenuScript.show( evt.getComponent(), evt.getX(), evt.getY() ) ;
    }//GEN-LAST:event_screen_outMousePressed
    
    private void jButton17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MousePressed
        new editor(pintarMol.babel.xeorc+"script/"+botones.get(7)).openFile(new File(pintarMol.babel.xeorc+"script/"+botones.get(7)));
    }//GEN-LAST:event_jButton17MousePressed
    
    private void jButton16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MousePressed
        new editor(pintarMol.babel.xeorc+"script/"+botones.get(6)).openFile(new File(pintarMol.babel.xeorc+"script/"+botones.get(6)));
    }//GEN-LAST:event_jButton16MousePressed
    
    private void jButton15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MousePressed
        new editor(pintarMol.babel.xeorc+"script/"+botones.get(5)).openFile(new File(pintarMol.babel.xeorc+"script/"+botones.get(5)));
    }//GEN-LAST:event_jButton15MousePressed
    
    private void jButton14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MousePressed
        new editor(pintarMol.babel.xeorc+"script/"+botones.get(4)).openFile(new File(pintarMol.babel.xeorc+"script/"+botones.get(4)));
    }//GEN-LAST:event_jButton14MousePressed
    
    private void jButton13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MousePressed
        new editor(pintarMol.babel.xeorc+"script/"+botones.get(3)).openFile(new File(pintarMol.babel.xeorc+"script/"+botones.get(3)));
    }//GEN-LAST:event_jButton13MousePressed
    
    private void jButton12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MousePressed
        new editor(pintarMol.babel.xeorc+"script/"+botones.get(2)).openFile(new File(pintarMol.babel.xeorc+"script/"+botones.get(2)));
    }//GEN-LAST:event_jButton12MousePressed
    
    private void jButton11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MousePressed
        new editor(pintarMol.babel.xeorc+"script/"+botones.get(1)).openFile(new File(pintarMol.babel.xeorc+"script/"+botones.get(1)));
    }//GEN-LAST:event_jButton11MousePressed
    
    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        if(jButton3.isEnabled())   runProcess(2,true);
    }//GEN-LAST:event_jButton3MousePressed
    
    private void jButton10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MousePressed
        new editor(pintarMol.babel.xeorc+"script/"+botones.get(0)).openFile(new File(pintarMol.babel.xeorc+"script/"+botones.get(0)));
    }//GEN-LAST:event_jButton10MousePressed
    
    private void jButton8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MousePressed
        if(jButton8.isEnabled())  runProcess(7,true);
    }//GEN-LAST:event_jButton8MousePressed
    
    private void jButton7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MousePressed
        if(jButton7.isEnabled())  runProcess(6,true);
    }//GEN-LAST:event_jButton7MousePressed
    
    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        if(jButton6.isEnabled())   runProcess(5,true);
    }//GEN-LAST:event_jButton6MousePressed
    
    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        if(jButton5.isEnabled())   runProcess(4,true);
    }//GEN-LAST:event_jButton5MousePressed
    
    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        if(jButton2.isEnabled())  runProcess(3,true);
    }//GEN-LAST:event_jButton4MousePressed
    
    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        if(jButton2.isEnabled())   runProcess(1,true);
    }//GEN-LAST:event_jButton2MousePressed
    
    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        if(jButton1.isEnabled())  runProcess(0,true);
    }//GEN-LAST:event_jButton1MousePressed
    
    private void me23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_me23MousePressed
        if(!TreeA.isSelectionEmpty()) TreeA_delete();
    }//GEN-LAST:event_me23MousePressed

    private void AZMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AZMousePressed
        TreeA_order();
    }//GEN-LAST:event_AZMousePressed

    private void Te78MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te78MousePressed
        String auxXYZfile="";
        auxXYZfile="";
        //limpiamos
        try {
         ls= new File(write_tmp).listFiles();
            for (int i=0; i<ls.length; i++)
                ls[i].delete();
        int [] iatom = new int[cadena.nCol(pintarMol.atoms_xyzTr)+1];
                for(int i=1;i<=cadena.nCol(pintarMol.atoms_xyzTr);i++)
                    iatom[i]=cadena.readColInt(i,pintarMol.atoms_xyzTr); 
        for(int i = 1; i<iatom.length;i++){
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(write_tmp+SEP+iatom[i]+"_"+jtrfirst.getText()+"_"+jtrlast.getText()+".dat");
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);                      
            for(int j=0;j<pintarMol.trayectoria.size();j++) if(pintarMol.trayectoria.get(j).POS==iatom[i])out.writeBytes(cadena.formatFortran(2,12,6,pintarMol.trayectoria.get(j).R[0])+" "+cadena.formatFortran(2,12,6,pintarMol.trayectoria.get(j).R[1])+" "+cadena.formatFortran(2,12,6,pintarMol.trayectoria.get(j).R[2])+"\n");                
            out.close();
            archivo.close(); 
        }
        new editor().openDirecory_tmp(new File( write_tmp) ,new File(pintarMol.babel.path));
        }catch (IOException oe) {System.out.println("error en trayectoria xyz file " );}
    }//GEN-LAST:event_Te78MousePressed

    private void jtrfisttextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtrfisttextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtrfisttextActionPerformed

    private void jtrlasttextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtrlasttextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtrlasttextActionPerformed

    private void jButton47MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton47MousePressed
        colorLabel.setBackground( JColorChooser.showDialog( this , "axis color", colorLabel.getBackground()));
        pintarMol.C[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=colorLabel.getBackground().getRGB();

    }//GEN-LAST:event_jButton47MousePressed

    private void jComboBoxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxMouseReleased

    
    }//GEN-LAST:event_jComboBoxMouseReleased

    private void jButton58MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton58MousePressed
        newFile =  new dialogo.chooser().fileChoose("Open file x,y,z,y","open",pintarMol.babel.path+SEP+".") ;
        if(newFile!=null) {
            pintarMol.xyztFile=newFile;
            xyzt.setSelected(true);
            pintarMol.xyzt=xyzt.isSelected();
            xyztFile.setText(newFile.getAbsolutePath());
            pintarMol.Load_xyzt();
            ver3D_MOL();
        }
    }//GEN-LAST:event_jButton58MousePressed

    private void Te53MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te53MousePressed
        if(pintarMol.MOL_enable){
            dialogo_color();
            ver3D_MOL();
        }
    }//GEN-LAST:event_Te53MousePressed

    private void Sinter2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sinter2MouseDragged
        dialogo_color();
    }//GEN-LAST:event_Sinter2MouseDragged

    private void Sinter1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sinter1MouseDragged
        dialogo_color();
    }//GEN-LAST:event_Sinter1MouseDragged

    private void color_inter_1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_inter_1MousePressed
        color_inter_1.setBackground(JColorChooser.showDialog( this , "color final", color_inter_1.getBackground() ));
        dialogo_color();
    }//GEN-LAST:event_color_inter_1MousePressed

    private void color_iniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_iniMousePressed
        color_ini.setBackground(JColorChooser.showDialog( this , "color final", color_ini.getBackground() ));
        dialogo_color();
    }//GEN-LAST:event_color_iniMousePressed

    private void color_finMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_finMousePressed
        color_fin.setBackground(JColorChooser.showDialog( this , "color final", color_fin.getBackground() ));
        dialogo_color();
    }//GEN-LAST:event_color_finMousePressed

    private void color_inter_2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_color_inter_2MousePressed
        color_inter_2.setBackground(JColorChooser.showDialog( this , "color final", color_inter_2.getBackground() ));
        dialogo_color();
    }//GEN-LAST:event_color_inter_2MousePressed

    private void xyztMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xyztMouseClicked
        ver3D_MOL();
    }//GEN-LAST:event_xyztMouseClicked

    private void jComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxItemStateChanged
     //System.out.println(cadena.readColString(2,jComboBox.getSelectedItem().toString()));
    colorLabel.setBackground(new Color(pintarMol.C[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]));
    ancho.setValue(pintarMol.ancho[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]);
    jlvs_1.setValue(pintarMol.itr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]);
    jlvs_2.setValue(pintarMol.jtr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]);
    jlvs_3.setValue(pintarMol.ktr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]);
//    Integer.valueOf(jtrlast.getText())
    }//GEN-LAST:event_jComboBoxItemStateChanged

    private void jlvs_1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jlvs_1StateChanged
            pintarMol.itr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=jlvs_1.getValue().hashCode();
    }//GEN-LAST:event_jlvs_1StateChanged

    private void jlvs_2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jlvs_2StateChanged
            pintarMol.jtr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=jlvs_2.getValue().hashCode();
    }//GEN-LAST:event_jlvs_2StateChanged

    private void jlvs_3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jlvs_3StateChanged
            pintarMol.ktr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=jlvs_3.getValue().hashCode();
    }//GEN-LAST:event_jlvs_3StateChanged

    private void Te79MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Te79MousePressed
              newFile =  new File(xyzFileTr.getText());
        if(newFile!=null) {
            pintarMol.xyzFileTr=newFile;
            xyzTr.setSelected(true);
            pintarMol.xyzTr=xyzTr.isSelected();
            pintarMol.atoms_xyzTr=xyztAtomsTr.getText();
            xyzFileTr.setText(newFile.getAbsolutePath());
            pintarMol.trfirst=Integer.valueOf(jtrfirst.getText());
            pintarMol.trlast=Integer.valueOf(jtrlast.getText());
            pintarMol.C[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=colorLabel.getBackground().getRGB();
            pintarMol.itr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=jlvs_1.getValue().hashCode();
            pintarMol.jtr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=jlvs_2.getValue().hashCode();
            pintarMol.ktr[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=jlvs_3.getValue().hashCode();
            pintarMol.ancho[Integer.valueOf(cadena.readColString(2,jComboBox.getSelectedItem().toString()))]=ancho.getValue().hashCode(); 
            pintarMol.Load_xyzTr();
            ver3D_MOL();
        }
    }//GEN-LAST:event_Te79MousePressed

    private void pov_xyz_iniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pov_xyz_iniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pov_xyz_iniActionPerformed

    private void pov_xyz_finActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pov_xyz_finActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pov_xyz_finActionPerformed

    private void pov_xyz_stepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pov_xyz_stepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pov_xyz_stepActionPerformed
        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AZ;
    private javax.swing.JRadioButton AllMemory;
    private javax.swing.JComboBox Babel_types;
    private javax.swing.JButton Bt0;
    private javax.swing.JButton Bt1;
    private javax.swing.JButton Bt2;
    private javax.swing.JButton BtOrder;
    private javax.swing.JTextField CalcX;
    private javax.swing.JTextField CalcY;
    private javax.swing.JTextField CalcZ;
    private javax.swing.JCheckBox CheckFragments;
    private javax.swing.JLabel Clabel;
    private javax.swing.JTextField FRAMESLOAD;
    private javax.swing.JTextField File_ttf;
    private javax.swing.JFrame Frame_Bulk;
    private javax.swing.JLabel Fuente;
    private javax.swing.JTextField Giro_X;
    private javax.swing.JTextField Giro_X1;
    private javax.swing.JTextField Giro_X2;
    private javax.swing.JTextField Giro_Z;
    private javax.swing.JTextField Giro_Z1;
    private javax.swing.JTextField Giro_Z2;
    private javax.swing.JTextField Giro_centro;
    private javax.swing.JTextField Giro_centro1;
    private javax.swing.JTextField Giro_centro2;
    private javax.swing.ButtonGroup GroupExchange;
    private javax.swing.ButtonGroup GroupExcited;
    private javax.swing.JDialog JFont;
    private javax.swing.JMenu Me0;
    private javax.swing.JMenu Me1;
    private javax.swing.JMenu Me2;
    private javax.swing.JMenu Me3;
    private javax.swing.JMenu Me4;
    private javax.swing.JMenu Me5;
    private javax.swing.JMenu Me6;
    private javax.swing.JMenu Me7;
    private javax.swing.JMenu Me_Language;
    private javax.swing.JMenu Menu_xeoBabel;
    private javax.swing.JLabel MiniEjes;
    private javax.swing.JRadioButton NoMemory;
    private javax.swing.JCheckBox OptBulk;
    private javax.swing.JFrame Options;
    private javax.swing.JSlider PERS;
    private javax.swing.JButton PlayB;
    private javax.swing.JTree ProjectTree;
    private javax.swing.JSlider Sinter1;
    private javax.swing.JSlider Sinter2;
    private javax.swing.JPanel TABXEO;
    private javax.swing.JSlider TOL;
    private javax.swing.JTextField Tdespl;
    private javax.swing.JTextField Tdespl2;
    private javax.swing.JTextField Tdespl20;
    private javax.swing.JTextField Tdespl33;
    private javax.swing.JTextField Tdespl34;
    private javax.swing.JTextField Tdespl35;
    private javax.swing.JTextField Tdespl37;
    private javax.swing.JTextField Tdespl42;
    private javax.swing.JTextField Tdespl43;
    private javax.swing.JTextField Tdespl65;
    private javax.swing.JTextField Te0;
    private javax.swing.JTextField Te1;
    private javax.swing.JTextField Te10;
    private javax.swing.JTextField Te11;
    private javax.swing.JTextField Te12;
    private javax.swing.JTextField Te13;
    private javax.swing.JTextField Te14;
    private javax.swing.JTextField Te15;
    private javax.swing.JTextField Te16;
    private javax.swing.JTextField Te17;
    private javax.swing.JTextField Te18;
    private javax.swing.JTextField Te19;
    private javax.swing.JTextField Te2;
    private javax.swing.JTextField Te20;
    private javax.swing.JTextField Te21;
    private javax.swing.JLabel Te22;
    private javax.swing.JLabel Te23;
    private javax.swing.JLabel Te24;
    private javax.swing.JTextField Te25;
    private javax.swing.JLabel Te26;
    private javax.swing.JLabel Te27;
    private javax.swing.JLabel Te28;
    private javax.swing.JTextField Te29;
    private javax.swing.JTextField Te3;
    private javax.swing.JLabel Te30;
    private javax.swing.JLabel Te31;
    private javax.swing.JLabel Te32;
    private javax.swing.JCheckBox Te33;
    private javax.swing.JLabel Te34;
    private javax.swing.JLabel Te35;
    private javax.swing.JTextField Te36;
    private javax.swing.JTextField Te37;
    private javax.swing.JTextField Te38;
    private javax.swing.JTextField Te39;
    private javax.swing.JTextField Te4;
    private javax.swing.JTextField Te40;
    private javax.swing.JTextField Te41;
    private javax.swing.JTextField Te42;
    private javax.swing.JTextField Te43;
    private javax.swing.JButton Te44;
    private javax.swing.JTextField Te45;
    private javax.swing.JTextField Te46;
    private javax.swing.JTextField Te47;
    private javax.swing.JButton Te48;
    private javax.swing.JTextField Te49;
    private javax.swing.JTextField Te5;
    private javax.swing.JTextField Te50;
    private javax.swing.JButton Te51;
    private javax.swing.JButton Te52;
    private javax.swing.JButton Te53;
    private javax.swing.JButton Te54;
    private javax.swing.JButton Te55;
    private javax.swing.JButton Te56;
    private javax.swing.JButton Te57;
    private javax.swing.JButton Te58;
    private javax.swing.JButton Te59;
    private javax.swing.JTextField Te6;
    private javax.swing.JButton Te60;
    private javax.swing.JLabel Te61;
    private javax.swing.JButton Te62;
    private javax.swing.JLabel Te63;
    private javax.swing.JButton Te64;
    private javax.swing.JButton Te65;
    private javax.swing.JButton Te66;
    private javax.swing.JButton Te67;
    private javax.swing.JLabel Te68;
    private javax.swing.JLabel Te69;
    private javax.swing.JTextField Te7;
    private javax.swing.JLabel Te70;
    private javax.swing.JTextField Te72;
    private javax.swing.JTextField Te73;
    private javax.swing.JLabel Te77;
    private javax.swing.JButton Te78;
    private javax.swing.JButton Te79;
    private javax.swing.JTextField Te8;
    private javax.swing.JTextField Te9;
    private javax.swing.JTree TreeA;
    private javax.swing.JTree TreeB;
    private javax.swing.JTextField Z_all;
    private javax.swing.JSpinner ancho;
    private javax.swing.JTextField angle;
    private javax.swing.JTextField antialias;
    private javax.swing.JRadioButton archivoBullvs;
    private javax.swing.JTextField atomo_1;
    private javax.swing.JTextField atomo_2;
    private javax.swing.JTextField atomo_3;
    private javax.swing.JTextField atomo_5;
    private javax.swing.JRadioButton bcc;
    private javax.swing.JTextField bonds;
    private javax.swing.ButtonGroup bulkRadiobutton;
    private javax.swing.ButtonGroup buttonGroupBabel;
    private javax.swing.ButtonGroup buttonGroupExport;
    private javax.swing.JButton buttonOpenBabel;
    private javax.swing.JButton changeLVS;
    private javax.swing.JPanel colorArrows;
    private javax.swing.JPanel colorEjes;
    private javax.swing.JPanel colorFondo;
    private javax.swing.JPanel colorLabel;
    private javax.swing.JButton color_fin;
    private javax.swing.JButton color_ini;
    private javax.swing.JButton color_inter_1;
    private javax.swing.JButton color_inter_2;
    private javax.swing.JButton copy;
    private javax.swing.JCheckBox diffRadio;
    private javax.swing.JTextArea edit_Bulk;
    private javax.swing.JCheckBox escalaGrises;
    private javax.swing.JRadioButton fcc;
    private javax.swing.JTextField file_Bulk;
    private javax.swing.JTextField grosor;
    private javax.swing.JTextField inputFileBabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonFold;
    private javax.swing.JButton jButtonPovray;
    private javax.swing.JButton jButtonPovray1;
    private javax.swing.JButton jButtonProy;
    private javax.swing.JComboBox jCAxis;
    private javax.swing.JComboBox jCFix;
    private javax.swing.JComboBox jCOrdenar;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JComboBox jComboBoxBabelRead;
    private javax.swing.JComboBox jComboBoxBabelWrite;
    private javax.swing.JFrame jDCalc;
    private javax.swing.JDesktopPane jDeskt0;
    private javax.swing.JDesktopPane jDeskt1;
    private javax.swing.JDesktopPane jDeskt3;
    private javax.swing.JDesktopPane jDeskt4;
    private javax.swing.JDesktopPane jDeskt5;
    private javax.swing.JDesktopPane jDeskt6;
    private javax.swing.JDesktopPane jDesktOpt;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane10;
    private javax.swing.JDesktopPane jDesktopPane11;
    private javax.swing.JDesktopPane jDesktopPane12;
    private javax.swing.JDesktopPane jDesktopPane13;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JDesktopPane jDesktopPane5;
    private javax.swing.JDesktopPane jDesktopPane6;
    private javax.swing.JDesktopPane jDesktopPane7;
    private javax.swing.JDesktopPane jDesktopPane8;
    private javax.swing.JDesktopPane jDesktopPane9;
    private javax.swing.JDesktopPane jDesktopPrpal;
    private javax.swing.JFrame jFrame_POVRAY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLabelFont;
    private javax.swing.JLabel jLabelXYZ;
    private javax.swing.JList jListFont;
    private javax.swing.JList jListSize;
    private javax.swing.JList jListStyle;
    private javax.swing.JMenuItem jMenuIPlotXNY;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuPlotNY;
    private javax.swing.JMenu jMenu_Fireball;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelDesk;
    private javax.swing.JPopupMenu jPopupMenuScript;
    private javax.swing.JRadioButton jRadioButtonBabel;
    private javax.swing.JRadioButton jRadioButtonXeo;
    private javax.swing.JScrollPane jScrollDesk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPaneTreeA;
    private javax.swing.JScrollPane jScrollPaneTreeB;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSlider jSliderMemory;
    private javax.swing.JSplitPane jSplitForm;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTextField jT_cx;
    private javax.swing.JTextField jT_cy;
    private javax.swing.JTextField jT_cz;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFont;
    private javax.swing.JTextField jTextFont1;
    private javax.swing.JTextField jTextSize;
    private javax.swing.JTextField jTextStyle;
    private javax.swing.JFrame jbabel;
    private javax.swing.JSpinner jlvs_1;
    private javax.swing.JSpinner jlvs_2;
    private javax.swing.JSpinner jlvs_3;
    private javax.swing.JTextField jtrfirst;
    private javax.swing.JTextField jtrfisttext;
    private javax.swing.JTextField jtrlast;
    private javax.swing.JTextField jtrlasttext;
    private javax.swing.JTextField lvs_11;
    private javax.swing.JTextField lvs_12;
    private javax.swing.JTextField lvs_13;
    private javax.swing.JTextField lvs_21;
    private javax.swing.JTextField lvs_22;
    private javax.swing.JTextField lvs_23;
    private javax.swing.JTextField lvs_31;
    private javax.swing.JTextField lvs_32;
    private javax.swing.JTextField lvs_33;
    private javax.swing.JButton mas_mol;
    private javax.swing.JMenuItem me0;
    private javax.swing.JMenuItem me1;
    private javax.swing.JMenuItem me11;
    private javax.swing.JMenuItem me12;
    private javax.swing.JMenuItem me13;
    private javax.swing.JMenuItem me14;
    private javax.swing.JMenuItem me15;
    private javax.swing.JMenuItem me16;
    private javax.swing.JMenuItem me17;
    private javax.swing.JMenuItem me18;
    private javax.swing.JMenuItem me19;
    private javax.swing.JMenuItem me2;
    private javax.swing.JMenuItem me20;
    private javax.swing.JMenuItem me21;
    private javax.swing.JMenuItem me23;
    private javax.swing.JMenuItem me24;
    private javax.swing.JMenuItem me25;
    private javax.swing.JMenuItem me26;
    private javax.swing.JMenuItem me27;
    private javax.swing.JMenuItem me28;
    private javax.swing.JMenuItem me29;
    private javax.swing.JMenuItem me3;
    private javax.swing.JMenuItem me30;
    private javax.swing.JMenuItem me31;
    private javax.swing.JMenuItem me32;
    private javax.swing.JMenuItem me33;
    private javax.swing.JMenuItem me34;
    private javax.swing.JMenuItem me35;
    private javax.swing.JMenuItem me36;
    private javax.swing.JMenuItem me37;
    private javax.swing.JMenuItem me38;
    private javax.swing.JMenuItem me4;
    private javax.swing.JMenuItem me5;
    private javax.swing.JMenuItem me6;
    private javax.swing.JMenuItem me7;
    private javax.swing.JMenuItem me8;
    private javax.swing.JMenuItem me_Bulk;
    private javax.swing.JMenuItem me_POVRAY;
    private javax.swing.JMenuItem me_STM;
    private javax.swing.JMenuItem me_babel;
    private javax.swing.JMenuItem me_cal;
    private javax.swing.JMenuItem me_dinamic;
    private javax.swing.JMenuItem me_ediotr;
    private javax.swing.JMenuItem me_kpoints;
    private javax.swing.JMenuItem me_rot;
    private javax.swing.JMenuItem me_script;
    private javax.swing.JButton menos_mol;
    private javax.swing.JPopupMenu menuA;
    private javax.swing.JPopupMenu menuB;
    private javax.swing.JPopupMenu menuInspctor;
    private javax.swing.JMenuBar menuPpal;
    private javax.swing.JPopupMenu menuProject;
    private javax.swing.JTextField mol_Xfin;
    private javax.swing.JTextField mol_Xini;
    private javax.swing.JTextField mol_Yfin;
    private javax.swing.JTextField mol_Yini;
    private javax.swing.JTextField mol_Zfin;
    private javax.swing.JTextField mol_Zini;
    private javax.swing.JTextField mol_lvs_1;
    private javax.swing.JTextField mol_lvs_2;
    private javax.swing.JTextField mol_lvs_3;
    private javax.swing.JCheckBox mol_seeIndex;
    private javax.swing.JCheckBox mol_seeVol;
    private javax.swing.ButtonGroup mv_axis;
    private javax.swing.JTextField note_text;
    private javax.swing.JButton openB_xyz;
    private javax.swing.JFrame options_XYZ;
    private javax.swing.JTextField outText;
    private javax.swing.JTextField parameters;
    private javax.swing.JButton paste;
    private javax.swing.JTextField path_project;
    private javax.swing.JTextField pixel;
    private javax.swing.JTextField pov_Trans;
    private javax.swing.JCheckBox pov_auto;
    private javax.swing.JCheckBox pov_radio_selec;
    private javax.swing.JTextField pov_radio_selecA;
    private javax.swing.JCheckBox pov_selected;
    private javax.swing.JCheckBox pov_transparente;
    private javax.swing.JTextField pov_xyz_fin;
    private javax.swing.JTextField pov_xyz_ini;
    private javax.swing.JTextField pov_xyz_step;
    private javax.swing.JButton previewPos;
    private javax.swing.JDialog projectChooser;
    private javax.swing.JTextField rad;
    private javax.swing.JTextField radio;
    private javax.swing.JButton reload_out;
    private javax.swing.JTextField resText;
    private javax.swing.JTextField rescalateArrow;
    private javax.swing.JTextField rot_TextX;
    private javax.swing.JTextField rot_TextY;
    private javax.swing.JTextField rot_TextZ;
    private javax.swing.JButton savePos;
    private javax.swing.JLabel screen5;
    private javax.swing.JLabel screen_out;
    private javax.swing.JFrame script;
    private javax.swing.JCheckBox seeArrows;
    private javax.swing.JCheckBox seeArrowsColor;
    private javax.swing.JCheckBox seeBond;
    private javax.swing.JCheckBox seeBordes;
    private javax.swing.JCheckBox seePos;
    private javax.swing.JButton seePovray1;
    private javax.swing.JButton seePovray16;
    private javax.swing.JButton seePovray2;
    private javax.swing.JButton seePovray4;
    private javax.swing.JButton selectFixAtoms;
    private javax.swing.JTextField tipo;
    private javax.swing.JRadioButton useBabel;
    private javax.swing.JCheckBox use_radio;
    private javax.swing.JTextField v_FRAMES;
    private javax.swing.JLabel v_instant_FRAME;
    private javax.swing.JCheckBox verEjes;
    private javax.swing.JCheckBox verIconosAtomos;
    private javax.swing.JRadioButton xeoBabel;
    private javax.swing.JComboBox xeoBabel_types;
    private javax.swing.JButton xy_mol;
    private javax.swing.JTextField xyzFileTr;
    private javax.swing.JCheckBox xyzTr;
    private javax.swing.JLabel xyz_end;
    private javax.swing.JButton xyz_mas;
    private javax.swing.JButton xyz_menos;
    private javax.swing.JTextField xyz_step;
    private javax.swing.JCheckBox xyzt;
    private javax.swing.JTextField xyztAtomsTr;
    private javax.swing.JTextField xyztFile;
    private javax.swing.JButton xz_mol;
    private javax.swing.JButton yx_mol;
    private javax.swing.JRadioButton zincblende;
    private javax.swing.JButton zy_mol;
    // End of variables declaration//GEN-END:variables
    
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    
    private void setXeoFont(String fontDir ){
        boolean ERFont=false;
        try{
            InputStream is = this.getClass().getResourceAsStream(fontDir);
            try{
                fuente = Font.createFont(Font.TRUETYPE_FONT,is);
                fuente = fuente.deriveFont(fontSize*1.0f);
            }catch(IOException e){ERFont=true;}
        }catch(FontFormatException e){ERFont=true;}
        if(ERFont){
            fuente = new Font("SansSerif",Font.PLAIN,fontSize);
            System.out.println("ERROR : fuente no encontrada usamos : "+fuente.toString());
        }
    }
    
    void setXeoFont(File fontDir ){
        setXeoFont(fontDir.getAbsolutePath());
    }
    
    void loadVal(){
        jLabelFont.setFont(fuente); jLabelFont.setText(lang.traslate(jLabelFont.getText()));
        Te0.setFont(fuente); Te0.setText(lang.traslate(Te0.getText())); Te0.setToolTipText(Te0.getText());
        Te1.setFont(fuente); Te1.setText(lang.traslate(Te1.getText())); Te1.setToolTipText(Te1.getText());
        Te2.setFont(fuente); Te2.setText(lang.traslate(Te2.getText())); Te2.setToolTipText(Te2.getText());
        Te3.setFont(fuente); Te3.setText(lang.traslate(Te3.getText())); Te3.setToolTipText(Te3.getText());
        Te4.setFont(fuente); Te4.setText(lang.traslate(Te4.getText())); Te4.setToolTipText(Te4.getText());
        Te5.setFont(fuente); Te5.setText(lang.traslate(Te5.getText())); Te5.setToolTipText(Te5.getText());
        Te6.setFont(fuente); Te6.setText(lang.traslate(Te6.getText())); Te6.setToolTipText(Te6.getText());
        Te7.setFont(fuente); Te7.setText(lang.traslate(Te7.getText())); Te7.setToolTipText(Te7.getText());
        Te8.setFont(fuente); Te8.setText(lang.traslate(Te8.getText())); Te8.setToolTipText(Te8.getText());
        Te9.setFont(fuente); Te9.setText(lang.traslate(Te9.getText())); Te9.setToolTipText(Te9.getText());
        Te10.setFont(fuente); Te10.setText(lang.traslate(Te10.getText())); Te10.setToolTipText(Te10.getText());
        Te11.setFont(fuente); Te11.setText(lang.traslate(Te11.getText())); Te11.setToolTipText(Te11.getText());
        Te12.setFont(fuente); Te12.setText(lang.traslate(Te12.getText())); Te12.setToolTipText(Te12.getText());
        Te13.setFont(fuente); Te13.setText(lang.traslate(Te13.getText())); Te13.setToolTipText(Te13.getText());
        Te14.setFont(fuente); Te14.setText(lang.traslate(Te14.getText())); Te14.setToolTipText(Te14.getText());
        Te15.setFont(fuente); Te15.setText(lang.traslate(Te15.getText())); Te15.setToolTipText(Te15.getText());
        Te16.setFont(fuente); Te16.setText(lang.traslate(Te16.getText())); Te16.setToolTipText(Te16.getText());
        Te17.setFont(fuente); Te17.setText(lang.traslate(Te17.getText())); Te17.setToolTipText(Te17.getText());
        Te18.setFont(fuente); Te18.setText(lang.traslate(Te18.getText())); Te18.setToolTipText(Te18.getText());
        Te19.setFont(fuente); Te19.setText(lang.traslate(Te19.getText())); Te19.setToolTipText(Te19.getText());
        Te20.setFont(fuente); Te20.setText(lang.traslate(Te20.getText())); Te20.setToolTipText(Te20.getText());
        Te21.setFont(fuente); Te21.setText(lang.traslate(Te21.getText())); Te21.setToolTipText(Te21.getText());
        Te22.setFont(fuente); Te22.setText(lang.traslate(Te22.getText())); Te22.setToolTipText(Te22.getText());
        Te23.setFont(fuente); Te23.setText(lang.traslate(Te23.getText())); Te23.setToolTipText(Te23.getText());
        Te24.setFont(fuente); Te24.setText(lang.traslate(Te24.getText())); Te24.setToolTipText(Te24.getText());
        Te25.setFont(fuente); Te25.setText(lang.traslate(Te25.getText()));
        Te26.setFont(fuente); Te26.setText(lang.traslate(Te26.getText()));
        Te27.setFont(fuente); Te27.setText(lang.traslate(Te27.getText()));
        Te28.setFont(fuente); Te28.setText(lang.traslate(Te28.getText()));
        Te29.setFont(fuente); Te29.setText(lang.traslate(Te29.getText()));
        Te30.setFont(fuente); Te30.setText(lang.traslate(Te30.getText()));
        Te31.setFont(fuente); Te31.setText(lang.traslate(Te31.getText()));
        Te32.setFont(fuente); Te32.setText(lang.traslate(Te32.getText()));
        Te33.setFont(fuente); Te33.setText(lang.traslate(Te33.getText()));
        Te34.setFont(fuente); Te34.setText(lang.traslate(Te34.getText()));
        Te35.setFont(fuente); Te35.setText(lang.traslate(Te35.getText()));
        Te36.setFont(fuente); Te36.setText(lang.traslate(Te36.getText()));
        Te37.setFont(fuente); Te37.setText(lang.traslate(Te37.getText()));
        Te38.setFont(fuente); Te38.setText(lang.traslate(Te38.getText()));
        Te39.setFont(fuente); Te39.setText(lang.traslate(Te39.getText()));
        Te40.setFont(fuente); Te40.setText(lang.traslate(Te40.getText()));
        Te41.setFont(fuente); Te41.setText(lang.traslate(Te41.getText()));
        Te42.setFont(fuente); Te42.setText(lang.traslate(Te42.getText()));
        Te43.setFont(fuente); Te43.setText(lang.traslate(Te43.getText()));
        Te44.setFont(fuente); Te44.setText(lang.traslate(Te44.getText()));
        Te45.setFont(fuente); Te45.setText(lang.traslate(Te45.getText()));
        Te46.setFont(fuente); Te46.setText(lang.traslate(Te46.getText()));
        Te47.setFont(fuente); Te47.setText(lang.traslate(Te47.getText()));
        Te48.setFont(fuente); Te48.setText(lang.traslate(Te48.getText()));
        Te49.setFont(fuente); Te49.setText(lang.traslate(Te49.getText()));
        Te40.setFont(fuente); Te50.setText(lang.traslate(Te50.getText()));
        Te51.setFont(fuente); Te51.setText(lang.traslate(Te51.getText()));
        Te52.setFont(fuente); Te52.setText(lang.traslate(Te52.getText()));
        Te53.setFont(fuente); Te53.setText(lang.traslate(Te53.getText()));
        Te54.setFont(fuente); Te54.setText(lang.traslate(Te54.getText()));
        Te55.setFont(fuente); Te55.setText(lang.traslate(Te55.getText()));
        Te56.setFont(fuente); Te56.setText(lang.traslate(Te56.getText()));
        Te57.setFont(fuente); Te57.setText(lang.traslate(Te57.getText()));
        Te58.setFont(fuente); Te58.setText(lang.traslate(Te58.getText()));
        Te59.setFont(fuente); Te59.setText(lang.traslate(Te59.getText()));
        Te60.setFont(fuente); Te60.setText(lang.traslate(Te60.getText()));
        Te61.setFont(fuente); Te61.setText(lang.traslate(Te61.getText()));
        Te62.setFont(fuente); Te62.setText(lang.traslate(Te62.getText()));
        Te63.setFont(fuente); Te63.setText(lang.traslate(Te63.getText()));
        Te64.setFont(fuente); Te64.setText(lang.traslate(Te64.getText()));
        Te65.setFont(fuente); Te65.setText(lang.traslate(Te65.getText()));
        Te66.setFont(fuente); Te66.setText(lang.traslate(Te66.getText()));
        Te67.setFont(fuente); Te67.setText(lang.traslate(Te67.getText()));
        Te68.setFont(fuente); Te68.setText(lang.traslate(Te68.getText()));
        Te69.setFont(fuente); Te69.setText(lang.traslate(Te69.getText()));
        Te70.setFont(fuente); Te70.setText(lang.traslate(Te70.getText()));
        // Te71.setFont(fuente); Te71.setText(lang.traslate(Te71.getText()));
        // Te72.setFont(fuente); Te72.setText(lang.traslate(Te72.getText()));
        // Te73.setFont(fuente); Te73.setText(lang.traslate(Te73.getText()));
        // Te74.setFont(fuente); Te74.setText(lang.traslate(Te74.getText()));
        // Te75.setFont(fuente); Te75.setText(lang.traslate(Te75.getText()));
        // Te76.setFont(fuente); Te76.setText(lang.traslate(Te76.getText()));
        // Te77.setFont(fuente); Te77.setText(lang.traslate(Te77.getText()));
        // Te78.setFont(fuente); Te78.setText(lang.traslate(Te78.getText()));
        // Te79.setFont(fuente); Te79.setText(lang.traslate(Te79.getText()));
        
        me0.setFont(fuente); me0.setText(lang.traslate(me0.getText()));
        me1.setFont(fuente); me1.setText(lang.traslate(me1.getText()));
        me2.setFont(fuente); me2.setText(lang.traslate(me2.getText()));
        me3.setFont(fuente); me3.setText(lang.traslate(me3.getText()));
        me4.setFont(fuente); me4.setText(lang.traslate(me4.getText()));
        me5.setFont(fuente); me5.setText(lang.traslate(me5.getText()));
        me6.setFont(fuente); me6.setText(lang.traslate(me6.getText()));
        me7.setFont(fuente); me7.setText(lang.traslate(me7.getText()));
        me8.setFont(fuente); me8.setText(lang.traslate(me8.getText()));
        //     me9.setFont(fuente); me9.setText(lang.traslate(me9.getText()));
        //     me10.setFont(fuente); me10.setText(lang.traslate(me10.getText()));
        me11.setFont(fuente); me11.setText(lang.traslate(me11.getText()));
        me12.setFont(fuente); me12.setText(lang.traslate(me12.getText()));
        me13.setFont(fuente); me13.setText(lang.traslate(me13.getText()));
        me14.setFont(fuente); me14.setText(lang.traslate(me14.getText()));
        me15.setFont(fuente); me15.setText(lang.traslate(me15.getText()));
        me16.setFont(fuente); me16.setText(lang.traslate(me16.getText()));
        me17.setFont(fuente); me17.setText(lang.traslate(me17.getText()));
        me18.setFont(fuente); me18.setText(lang.traslate(me18.getText()));
        me19.setFont(fuente); me19.setText(lang.traslate(me19.getText()));
        me20.setFont(fuente); me20.setText(lang.traslate(me20.getText()));
        me21.setFont(fuente); me21.setText(lang.traslate(me21.getText()));
        //me22.setFont(fuente); me22.setText(lang.traslate(me22.getText()));
        me23.setFont(fuente); me23.setText(lang.traslate(me23.getText()));
        me24.setFont(fuente); me24.setText(lang.traslate(me24.getText()));
        me25.setFont(fuente); me25.setText(lang.traslate(me25.getText()));
        me26.setFont(fuente); me26.setText(lang.traslate(me26.getText()));
        me27.setFont(fuente); me27.setText(lang.traslate(me27.getText()));
        me28.setFont(fuente); me28.setText(lang.traslate(me28.getText()));
        me29.setFont(fuente); me29.setText(lang.traslate(me29.getText()));
        me30.setFont(fuente); me30.setText(lang.traslate(me30.getText()));
        me31.setFont(fuente); me31.setText(lang.traslate(me31.getText()));
        me32.setFont(fuente); me32.setText(lang.traslate(me32.getText()));
        me33.setFont(fuente); me33.setText(lang.traslate(me33.getText()));
        /*
         me34.setFont(fuente); me34.setText(lang.traslate(me34.getText()));
        me35.setFont(fuente); me35.setText(lang.traslate(me35.getText()));
        me36.setFont(fuente); me36.setText(lang.traslate(me36.getText()));
        me37.setFont(fuente); me37.setText(lang.traslate(me37.getText()));
        me38.setFont(fuente); me38.setText(lang.traslate(me38.getText()));
        me39.setFont(fuente); me39.setText(lang.traslate(me39.getText()));
        me40.setFont(fuente); me40.setText(lang.traslate(me40.getText()));
        me41.setFont(fuente); me41.setText(lang.traslate(me41.getText()));
        me42.setFont(fuente); me42.setText(lang.traslate(me42.getText()));
        me43.setFont(fuente); me43.setText(lang.traslate(me43.getText()));
        me44.setFont(fuente); me44.setText(lang.traslate(me44.getText()));
        me45.setFont(fuente); me45.setText(lang.traslate(me45.getText()));
        me46.setFont(fuente); me46.setText(lang.traslate(me46.getText()));
        me47.setFont(fuente); me47.setText(lang.traslate(me47.getText()));
         */
        Me0.setFont(fuente); Me0.setText(lang.traslate(Me0.getText()));
        Me1.setFont(fuente); Me1.setText(lang.traslate(Me1.getText()));
        Me2.setFont(fuente); Me2.setText(lang.traslate(Me2.getText()));
        Me3.setFont(fuente); Me3.setText(lang.traslate(Me3.getText()));
        Me4.setFont(fuente); Me4.setText(lang.traslate(Me4.getText()));
        Me5.setFont(fuente); Me5.setText(lang.traslate(Me5.getText()));
        Me6.setFont(fuente); Me6.setText(lang.traslate(Me6.getText()));
        Me7.setFont(fuente); Me7.setText(lang.traslate(Me7.getText()));
        
        Bt0.setFont(fuente); Bt0.setText(lang.traslate(Bt0.getText()));
        Bt1.setFont(fuente); Bt1.setText(lang.traslate(Bt1.getText())); //stop
        Bt2.setFont(fuente); Bt2.setText(lang.traslate(Bt2.getText()));
        jButtonFold.setFont(fuente); jButtonFold.setText(lang.traslate(jButtonFold.getText()));
        jButtonProy.setFont(fuente); jButtonProy.setText(lang.traslate(jButtonProy.getText()));
    }
  /*
    void texto(){
        String sa=Te0.getText()+"\n";
        sa+=Te1.getText()+"\n"+Te2.getText()+"\n"+Te3.getText()+"\n"+Te4.getText()+"\n"+Te5.getText()+"\n"+Te6.getText()+"\n"+Te7.getText()+"\n"+Te8.getText()+"\n"+Te9.getText()+"\n"+Te10.getText()+"\n"+Te11.getText()+"\n"+Te12.getText()+"\n"+Te13.getText()+"\n"+Te14.getText()+"\n"+Te15.getText()+"\n"+Te16.getText()+"\n"+Te17.getText()+"\n"+Te18.getText()+"\n"+Te19.getText()+"\n"+
                Te20.getText()+"\n"+Te21.getText()+"\n"+Te22.getText()+"\n"+Te23.getText()+"\n"+Te24.getText()+"\n"+Te25.getText()+"\n"+Te26.getText()+"\n"+Te27.getText()+"\n"+Te28.getText()+"\n"+Te29.getText()+"\n"+Te30.getText()+"\n"+Te31.getText()+"\n"+Te32.getText()+"\n"+Te33.getText()+"\n"+Te34.getText()+"\n"+Te35.getText()+"\n"+Te36.getText()+"\n"+Te37.getText()+"\n"+Te38.getText()+"\n"+Te39.getText()+
                Te40.getText()+"\n"+Te41.getText()+"\n"+Te42.getText()+"\n"+Te43.getText()+"\n"+Te44.getText()+"\n"+Te45.getText()+"\n"+Te46.getText()+"\n"+Te47.getText()+"\n"+Te48.getText()+"\n"+Te49.getText()+"\n"+Te50.getText()+"\n"+Te51.getText()+"\n"+Te52.getText()+"\n"+Te53.getText()+"\n"+Te54.getText()+"\n"+Te55.getText()+"\n"+Te56.getText()+"\n"+Te57.getText()+"\n"+Te58.getText()+"\n"+Te59.getText()+
                Te60.getText()+"\n"+Te61.getText()+"\n"+Te62.getText()+"\n"+Te63.getText()+"\n"+Te64.getText()+"\n"+Te65.getText()+"\n"+Te66.getText()+"\n"+Te67.getText()+"\n"+Te68.getText()+"\n"+Te69.getText()+"\n"+
                Te70.getText()+"\n";
        sa+=me1.getText()+"\n"+me2.getText()+"\n"+me3.getText()+"\n"+me4.getText()+"\n"+me5.getText()+"\n"+me6.getText()+"\n"+me7.getText()+"\n"+me8.getText()+"\n"+me9.getText()+"\n"+me10.getText()+"\n"+me11.getText()+"\n"+me12.getText()+"\n"+me13.getText()+"\n"+me14.getText()+"\n"+me15.getText()+"\n"+me16.getText()+"\n"+me17.getText()+"\n"+me18.getText()+"\n"+me19.getText()+"\n"+
                me20.getText()+"\n"+me21.getText()+"\n"+me22.getText()+"\n"+me23.getText()+"\n"+me24.getText()+"\n"+me25.getText()+"\n"+me26.getText()+"\n"+me27.getText()+"\n"+me28.getText()+"\n"+me29.getText()+"\n"+me30.getText()+"\n"+me31.getText()+"\n"+me32.getText()+"\n"+me33.getText()+"\n";
        sa+=Me1.getText()+"\n"+Me2.getText()+"\n"+Me3.getText()+"\n"+Me5.getText()+"\n"+Me6.getText()+"\n"+me7.getText()+"\n";
        System.out.println(sa);
    }
   */
    
    void saveVal(){
        preLoad.clear();
        preLoad.add("language = "+lang.Language);
        if(fontName.equals("default"))  preLoad.add("Font = "+fontName);
        else preLoad.add("Font = "+ fuente.getName());
        preLoad.add("sizeFont = "+fontSize);
        preLoad.add("background = "+colorFondo.getBackground().getRGB());
        preLoad.add("backgroundAxis = "+colorEjes.getBackground().getRGB());
        if(verEjes.isSelected()) preLoad.add("seeBackgroundAxis = true");
        else  preLoad.add("seeBackgroundAxis = false");
        if(seeBond.isSelected()) preLoad.add("seeBond = true");
        else  preLoad.add("seeBond = false");
        preLoad.add("HBonds = "+grosor.getText());
        if(diffRadio.isSelected()) preLoad.add("seeCovalent = true");
        else  preLoad.add("seeCovalent = false");
        preLoad.add("rescalBonds = "+ rad.getText());
        preLoad.add("tolBonds = "+ TOL.getValue());
        preLoad.add("perspective = "+PERS.getValue());
        if(verIconosAtomos.isSelected()) preLoad.add("verIcons = true");
        if(escalaGrises.isSelected()) preLoad.add("escalaGrises = true");
        else preLoad.add("verIcons = false");
        cadena.writer(preLoad,new File(pintarMol.babel.xeorc+"xeo.ini"));
        
        readVal();
    }
    
    void defaultVal(){
        lang.Language="english";
        setXeoFont("/dejavu/DejaVuSans.ttf");
        fontSize=11;
        fontName="default";
        colorFondo.setBackground(new Color(-1));
        colorEjes.setBackground(new Color(-6710887));
        verEjes.setSelected(false);
        seeBond.setSelected(true);
        diffRadio.setSelected(false);
        grosor.setText("2");
        rad.setText("1");
        TOL.setValue(4);
        PERS.setValue(100);
        verIconosAtomos.setSelected(false);
        escalaGrises.setSelected(false);
        saveVal();
    }
    
    private void readVal(){
        preLoad.clear();
        preLoad=cadena.readFileArrayList(new File(pintarMol.babel.xeorc+"xeo.ini"));
        if(new File(pintarMol.babel.xeorc+"xeo.ini").exists()){
            for(int i=0;i<preLoad.size();i++){
                if(cadena.readColString(1,preLoad.get(i)).equals("language"))
                    lang.Language=cadena.readColString(3,preLoad.get(i));
                if(cadena.readColString(1,preLoad.get(i)).equals("Font"))
                    fontName=cadena.readColString(3,preLoad.get(i));
                if(cadena.readColString(1,preLoad.get(i)).equals("sizeFont"))
                    fontSize=cadena.readColInt(3,preLoad.get(i));
                if(cadena.readColString(1,preLoad.get(i)).equals("background"))
                    colorFondo.setBackground(new Color(cadena.readColInt(3,preLoad.get(i))));
                if(cadena.readColString(1,preLoad.get(i)).equals("backgroundAxis"))
                    colorEjes.setBackground(new Color(cadena.readColInt(3,preLoad.get(i))));
                if(cadena.readColString(1,preLoad.get(i)).equals("seeBackgroundAxis"))
                    if(cadena.readColString(3,preLoad.get(i)).equals("true")) verEjes.setSelected(true);
                    else verEjes.setSelected(false);
                if(cadena.readColString(1,preLoad.get(i)).equals("seeBond"))
                    if(cadena.readColString(3,preLoad.get(i)).equals("true")) seeBond.setSelected(true);
                    else seeBond.setSelected(false);
                if(cadena.readColString(1,preLoad.get(i)).equals("HBonds"))
                    grosor.setText(cadena.readColString(3,preLoad.get(i)));
                if(cadena.readColString(1,preLoad.get(i)).equals("seeCovalent"))
                    if(cadena.readColString(3,preLoad.get(i)).equals("true")) diffRadio.setSelected(true);
                    else diffRadio.setSelected(false);
                if(cadena.readColString(1,preLoad.get(i)).equals("rescalBonds"))
                    rad.setText(cadena.readColString(3,preLoad.get(i)));
                if(cadena.readColString(1,preLoad.get(i)).equals("tolBonds"))
                    TOL.setValue(cadena.readColInt(3,preLoad.get(i)));
                if(cadena.readColString(1,preLoad.get(i)).equals("perspective"))
                    PERS.setValue(cadena.readColInt(3,preLoad.get(i)));
                if(cadena.readColString(1,preLoad.get(i)).equals("verIcons"))
                    if(cadena.readColString(3,preLoad.get(i)).equals("true")) verIconosAtomos.setSelected(true);
                    else verIconosAtomos.setSelected(false);
                if(cadena.readColString(1,preLoad.get(i)).equals("escalaGrises"))
                    if(cadena.readColString(3,preLoad.get(i)).equals("true")) escalaGrises.setSelected(true);
                    else escalaGrises.setSelected(false);
            }
            if(fontName.equals("default")) {
                setXeoFont("/dejavu/DejaVuSans.ttf");
            }else fuente=new Font(fontName,Font.PLAIN,fontSize);
        }else defaultVal();
        
        loadVal();
    }
    
    void Iniciar(){
        if(new File(SEP+"etc").exists()){
            if(!(new File(pintarMol.babel.xeorc+"profile-xeo_"+nInsp).exists())){
                try{
                    Runtime run = Runtime.getRuntime();
                    Process proc = run.exec("cp -r "+".xeorc "+home);
                    System.out.println("creado: "+"cp -r "+".xeorc "+home);
                    proc.waitFor();
                } catch(Exception e){System.out.println("error al profile");}
            }
        }
        
        Limpiar_Inspector();
        
        //leemos las dos columnas del script/* para los botones
        if(new File(pintarMol.babel.xeorc+"script/Label.conf").exists()) botones = cadena.readFileArrayList( new File(pintarMol.babel.xeorc+"script/Label.conf"));
        else for(int i=0;i<8;i++)botones.add("error");
        jButton1.setText(botones.get(0));
        jButton2.setText(botones.get(1));
        jButton3.setText(botones.get(2));
        jButton4.setText(botones.get(3));
        jButton5.setText(botones.get(4));
        jButton6.setText(botones.get(5));
        jButton7.setText(botones.get(6));
        jButton8.setText(botones.get(7));
        
        Inspector();
    }
    
    
    File carpeta;
    int nCarpetas;
    String[] subcarpeta ;
    void projectOpen(){
        carpeta = new File(path_project.getText());
        if(path_project.getText().trim().equals("")) carpeta= new File(pintarMol.babel.path);
        newFile=carpeta.getAbsoluteFile();
        if(newFile.exists())path_project.setText(newFile.toString());
        subcarpeta = carpeta.list();
        root =  new javax.swing.tree.DefaultMutableTreeNode(carpeta.getName());
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
                        if(xeoBabel.isSelected()) {
                            for(int g=1;g<pintarMol.babel.plug.type.size();g++){ //el uno es desconocido
                                if(file.getName().length()>=pintarMol.babel.plug.end_InputFile.get(g).length()){
                                    if(file.getName().substring(file.getName().length()-pintarMol.babel.plug.end_InputFile.get(g).length(),file.getName().length()).equals(pintarMol.babel.plug.end_InputFile.get(g))){
                                        javax.swing.tree.DefaultMutableTreeNode rootd = new javax.swing.tree.DefaultMutableTreeNode(subcarpeta[i]);
                                        root.add(new javax.swing.tree.DefaultMutableTreeNode(file.getName()));
                                        nCarpetas++;
                                    }
                                }
                            }
                        }else{
                            javax.swing.tree.DefaultMutableTreeNode rootd = new javax.swing.tree.DefaultMutableTreeNode(subcarpeta[i]);
                            root.add(new javax.swing.tree.DefaultMutableTreeNode(file.getName()));;
                            nCarpetas++;
                        }
                    }
                }
            }
            if(pintarMol.babel.sort!=null && pintarMol.babel.projectFile!=null){
                tipo.setText(pintarMol.babel.sort+" ("+new File(pintarMol.babel.projectFile).getName()+")");
                Bt2.setToolTipText(pintarMol.babel.sort+" ("+new File(pintarMol.babel.projectFile).getName()+") "+pintarMol.babel.path);
                if(pintarMol.babel.sort.equals("xyz"))savePos.setEnabled(false);
                else savePos.setEnabled(true);
            }
        }
        ProjectTree.setModel(new javax.swing.tree.DefaultTreeModel(root));
    }
    
    //String auxInspector="";
    // File auxFileInspector=null;
    ArrayList<String> auxList ;
    void Limpiar_Inspector(){
        auxList = new ArrayList();
        ins=cadena.readFileArrayList( new File(pintarMol.babel.xeorc+"inspector"));
        auxList=cadena.readFileArrayList( new File(pintarMol.babel.xeorc+"profile-xeo_"+nInsp));
        int g=0;
        dir.clear();
        for (int i = 0; i< auxList.size(); i++){
            aux=auxList.get(i);
            if(cadena.readColString(1,aux).equals("note")){
                dir.add(g,auxList.get(i));
                g++;
            }else
                if(cadena.nCol(aux)>=3){
                aux_file=aux.substring(0,aux.lastIndexOf(" "));
                aux_file=aux_file.substring(0,aux_file.lastIndexOf(" ")).trim();
                if(new File(aux_file).exists()){
                    dir.add(g,auxList.get(i));
                    g++;
                }
                }
        }
        for(int i=TreeA.getRowCount()-1;i>0;i--)
            if(TreeA.isExpanded(i))
                TreeA.collapseRow(i);
    }
    
    javax.swing.tree.DefaultMutableTreeNode root,child;
    void Inspector(){
        //---------- TreeA -----------------------------------------------------
        root = new javax.swing.tree.DefaultMutableTreeNode(ins.get(nInsp).toString());
        for (int i=0;i<dir.size();i++){
            aux=dir.get(i);
            if(!cadena.readColString(1,aux).equals("note")){  //para note esta bien el cadena.readcolString(1 y para este caso es necesario hacerlo asi, sino peta el substring por longitud :)
                aux_babel=aux.substring(aux.lastIndexOf(" "),aux.length()).trim();
                aux_file=aux.substring(0,aux.lastIndexOf(" ")); //#shift
                aux_sort=aux_file.substring(aux_file.lastIndexOf(" "),aux_file.length()).trim();
                aux_file=aux_file.substring(0,aux_file.lastIndexOf(" ")).trim();
                if(aux_sort.equals("directory"))
                    child=new javax.swing.tree.DefaultMutableTreeNode(new File(aux_file).getName());
                else
                    child=new javax.swing.tree.DefaultMutableTreeNode(new File(aux_file+SEP).getParentFile().getName() );
                child.add(new javax.swing.tree.DefaultMutableTreeNode(""));
                root.add(child);
            } else{
                if(cadena.nCol(dir.get(i))>1)root.add(new javax.swing.tree.DefaultMutableTreeNode(dir.get(i).substring(5))); //quitamos note
                else root.add(new javax.swing.tree.DefaultMutableTreeNode(" ... "));
            }
        }
        TreeA.setModel(new javax.swing.tree.DefaultTreeModel(root));
        //-------------------- TreeB -------------------------------------------
        loadTreeB();
        cadena.writer(dir,new File(pintarMol.babel.xeorc+"profile-xeo_"+nInsp));
        allow();
    }
    
    void allow(){
        if(new File(outText.getText()).exists()){
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            jButton4.setEnabled(true);
            jButton5.setEnabled(true);
            jButton6.setEnabled(true);
            jButton7.setEnabled(true);
            jButton8.setEnabled(true);
        }else{
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jButton4.setEnabled(false);
            jButton5.setEnabled(false);
            jButton6.setEnabled(false);
            jButton7.setEnabled(false);
            jButton8.setEnabled(false);
        }
    }
    
    private void loadBabelList(){
        try{
            Runtime run = Runtime.getRuntime();
            Process proc = run.exec(pintarMol.babel.xeorc+"babel.sh "+pintarMol.babel.xeorc+"temp");
            proc.waitFor();
        } catch(Exception e){System.out.println("error run babel");}
        ArrayList<String> auxList = cadena.readFileArrayList(new File(pintarMol.babel.xeorc+"temp"+SEP+"babel.temp"));
        for(int i=0;i<auxList.size();i++)
            if(cadena.readColString(2,auxList.get(i)).equals("--"))
                babel.add(auxList.get(i));
        for(int i=0;i<babel.size();i++)
            Babel_types.addItem( cadena.readColString(1,babel.get(i)));
    }
//----------------------------------------------------------------------
//----------------------------------------------------------------------
    
    String aux="";
    int[] selections;
    
    void TreeBup(){
        nInsp=TreeB.getMaxSelectionRow()-1;
        if(nInsp>0){
            aux=ins.get(nInsp-1).toString();
            ins.remove(nInsp-1);
            ins.add(nInsp,aux);
            cadena.writer(ins,new File(pintarMol.babel.xeorc+"inspector"));
            new File(pintarMol.babel.xeorc+"profile-xeo_"+(nInsp-1)).renameTo(new File(pintarMol.babel.xeorc+"profile-xeo_aux"));
            new File(pintarMol.babel.xeorc+"profile-xeo_"+(nInsp)).renameTo(new File(pintarMol.babel.xeorc+"profile-xeo_"+(nInsp-1)));
            new File(pintarMol.babel.xeorc+"profile-xeo_aux").renameTo(new File(pintarMol.babel.xeorc+"profile-xeo_"+nInsp));
            nInsp--;
        }
        Limpiar_Inspector();
        Inspector();
    }
    
    void TreeBdown(){
        nInsp=TreeB.getMaxSelectionRow()-1;
        if(nInsp<ins.size()-1){
            aux=ins.get(nInsp).toString();
            ins.remove(nInsp);
            ins.add(nInsp+1,aux);
            cadena.writer(ins,new File(pintarMol.babel.xeorc+"inspector"));
            new File(pintarMol.babel.xeorc+"profile-xeo_"+(nInsp+1)).renameTo(new File(pintarMol.babel.xeorc+"profile-xeo_aux"));
            new File(pintarMol.babel.xeorc+"profile-xeo_"+(nInsp)).renameTo(new File(pintarMol.babel.xeorc+"profile-xeo_"+(nInsp+1)));
            new File(pintarMol.babel.xeorc+"profile-xeo_aux").renameTo(new File(pintarMol.babel.xeorc+"profile-xeo_"+nInsp));
            nInsp++;
        }
        Limpiar_Inspector();
        Inspector();
    }
    
    void TreeA_down(){
        selections = TreeA.getSelectionRows();
        if(!TreeA.isSelectionEmpty())
            if(TreeA.getSelectionPath().getPathCount() > 0 ){
            for(int T=selections.length-1;T>=0;T--)
                if(selections[T]<dir.size()){
                aux=dir.get(selections[T]-1);
                dir.add(selections[T]-1+2,aux);
                dir.remove(selections[T]-1);
                }
            Inspector();
            for(int i=0;i<selections.length;i++) if(selections[i]<(TreeA.getRowCount()-1)) selections[i]++;
            TreeA.setSelectionRows(selections);
            }
    }
    
    void TreeA_up(){
        selections = TreeA.getSelectionRows();
        if(!TreeA.isSelectionEmpty())
            if(TreeA.getSelectionPath().getPathCount() > 1 ){
            for(int T=0 ; T<selections.length;T++)
                if(selections[T]>1){
                aux=dir.get(selections[T]-1);
                dir.remove(selections[T]-1);
                dir.add(selections[T]-2,aux);
                }
            Inspector();
            for(int i=0;i<selections.length;i++) if(selections[i]>1) selections[i]--;
            TreeA.setSelectionRows(selections);
            }
    }
    
    void TreeA_delete(){
        selections = TreeA.getSelectionRows();
        int N=TreeA.getSelectionPaths().length;
        if(!TreeA.isSelectionEmpty())
            if(TreeA.getSelectionPath().getPathCount() > 1 ){
            for(int T=0;T<N;T++){
                dir.remove(selections[T]-1);
                for(int j=T+1;j<TreeA.getSelectionPaths().length;j++ )
                    if(selections[T]<selections[j])selections[j]--;
            }
            Inspector();
            for(int i=0;i<N;i++)
                if(selections[i]<TreeA.getRowCount()&&selections[i]>0)
                    TreeA.setSelectionRow(selections[i]);
            }
    }
    
    void TreeA_order() {
        for (int Ti = 0; Ti < dir.size() - 1; Ti++) 
                for (int Tj = Ti + 1; Tj < dir.size(); Tj++) 
                    if (TreeA.getPathForRow(Ti+1).getLastPathComponent().toString().toUpperCase().compareTo(TreeA.getPathForRow(Tj+1).getLastPathComponent().toString().toUpperCase()) > 0) {
                        aux = dir.get(Tj);
                        dir.remove(Tj);
                        dir.add(Tj, dir.get(Ti));
                        dir.remove(Ti);
                        dir.add(Ti, aux);
                         Inspector();
                    }
        Inspector();

    }
    
    
    
    void insertInTreeADirectory(){
        javax.swing.tree.TreePath [] select;
        int N,j;
        if(ProjectTree.getRowCount()<3) {
            ProjectTree.clearSelection();
            ProjectTree.setSelectionRow(1);
        }
        if(!ProjectTree.isSelectionEmpty()){
            select = ProjectTree.getSelectionPaths();
            N=ProjectTree.getSelectionPaths().length;
            for(int i=N-1;i>=0;i--)
                if(select[i].getPathCount()==2){
                if(ProjectTree.getRowCount()<3) pintarMol.babel.path=path_project.getText();
                else  pintarMol.babel.path=path_project.getText()+SEP+select[i].getPathComponent(1).toString();
                j=(TreeA.isSelectionEmpty())?0:TreeA.getMaxSelectionRow();
                dir.add(j,pintarMol.babel.path+" directory xeoBabel");
                Inspector();
                projectOpen();
                if(j>0)  TreeA.setSelectionRow(j+1);
                }
        }
    }
    
    
    void insertInTreeA(){
        javax.swing.tree.TreePath [] select;
        int j;
        if(ProjectTree.getRowCount()<3) {
            ProjectTree.clearSelection();
            ProjectTree.setSelectionRow(1);
        }
        if(!ProjectTree.isSelectionEmpty()){
            pintarMol.babel.path=path_project.getText();
            j=(TreeA.isSelectionEmpty())?0:TreeA.getMaxSelectionRow();
            if(new File(pintarMol.babel.path+SEP+ProjectTree.getSelectionPath().getPathComponent(1).toString()).isDirectory())
                dir.add(j,pintarMol.babel.path+SEP+ProjectTree.getSelectionPath().getPathComponent(1).toString()+" directory xeoBabel");
            else{
                if(xeoBabel.isSelected())
                    dir.add(j,pintarMol.babel.path+SEP+ProjectTree.getSelectionPath().getPathComponent(1).toString()+" "+xeoBabel_types.getSelectedItem()+" xeoBabel");
                if(useBabel.isSelected())
                    dir.add(j,pintarMol.babel.path+SEP+ProjectTree.getSelectionPath().getPathComponent(1).toString()+" "+Babel_types.getSelectedItem()+" babel");
            }
            Inspector();
            projectOpen();
            if(j>0)  TreeA.setSelectionRow(j+1);
        }
    }
    
    void selectProject(){  //selecionar el projecto, hay que hacerlo antes de abrirlo
        if (TreeA.getSelectionPath() != null){
            aux=dir.get(TreeA.getLeadSelectionRow()-1);
            if(TreeA.getSelectionPath().getPathCount() > 1 && !aux.substring(0,aux.indexOf(" ")).equals("note") ){
                //--AQUI fijamos el directorio de trabajo :)
                // pintarMol.babel.projectFile=cadena.readColString(1,dir.get(TreeA.getLeadSelectionRow()-1));  //no funciona cuando hay espacios en blanco en el path
                //--------
                aux_babel=aux.substring(aux.lastIndexOf(" "),aux.length()).trim();
                aux_file=aux.substring(0,aux.lastIndexOf(" "));
                aux_sort=aux_file.substring(aux_file.lastIndexOf(" "),aux_file.length()).trim();
                aux_file=aux_file.substring(0,aux_file.lastIndexOf(" ")).trim();;
                pintarMol.babel.projectFile=aux_file;
                pintarMol.babel.sort=aux_sort;
                pintarMol.babel.typeBabel=aux_babel;
                //---------
                if(aux_sort.equals("directory"))
                    pintarMol.babel.path=new File(aux_file).getAbsolutePath();
                else
                    pintarMol.babel.path=new File(aux_file).getParent() ;
                
                if(projectChooser.isVisible()) {
                    newFile=new File(pintarMol.babel.path); //dir.get(i));
                    if(newFile.exists())path_project.setText(newFile.toString());
                }
                //----------------------------------------------------------------
                loadTreeB();
            }
        }
    }
    
    void loadTreeB(){
        if(modeTreeB){
            root = new javax.swing.tree.DefaultMutableTreeNode("folder");
            root = new javax.swing.tree.DefaultMutableTreeNode(new File(pintarMol.babel.path).getName());
            File[] fileList = new File(pintarMol.babel.path).listFiles();
            String[] auxFileorder = new String[fileList.length];
            String auxS = "";
            for (int idx = 0; idx < fileList.length; idx++) auxFileorder[idx] = fileList[idx].getName();          
            for (int idx = 0; idx < fileList.length - 1; idx++) 
                for (int idy = idx + 1; idy < fileList.length; idy++) 
                    if (auxFileorder[idx].toUpperCase().compareTo(auxFileorder[idy].toUpperCase()) > 0) {
                        auxS = auxFileorder[idy];
                        auxFileorder[idy] = auxFileorder[idx];
                        auxFileorder[idx] = auxS;
                    }                            
            for (int idx = 0; idx < fileList.length; idx++) root.add(new javax.swing.tree.DefaultMutableTreeNode(auxFileorder[idx]));//fileList[idx].getName()));           
            TreeB.setModel(new javax.swing.tree.DefaultTreeModel(root));           
        }else{
            root  = new javax.swing.tree.DefaultMutableTreeNode("project");
            for (int idx = 0; idx < ins.size(); idx++)
                root.add(new javax.swing.tree.DefaultMutableTreeNode(ins.get(idx)));
            TreeB.setModel(new javax.swing.tree.DefaultTreeModel(root));
            TreeB.setSelectionRow(nInsp+1);
        }
    }
    
    
    void loadMOL() {
        //abrimos el projecto seleccionado , si y solo si esta enable el Open project
        // System.out.println(pintarMol.babel.sort);Te62.isEnabled()&&
        if(!pintarMol.babel.sort.equals("unknown")){
            if(pintarMol.babel.typeBabel.equals("babel")){
                new File(pintarMol.babel.xeorc+"temp"+SEP+"babel.xyz").delete();
                try{
                    Runtime run = Runtime.getRuntime();
                    Process proc = run.exec("babel -i "+pintarMol.babel.sort+" "+pintarMol.babel.projectFile+" -o xyz "+pintarMol.babel.xeorc+"temp"+SEP+"babel.xyz");
                    proc.waitFor();
                } catch(Exception e){System.out.println("error run babel");}
                pintarMol.babel.projectFile=pintarMol.babel.xeorc+"temp"+SEP+"babel.xyz";
                pintarMol.babel.sort="xyz";
            }
            if(pintarMol.babel.sort.equals("directory")){
                pintarMol.babel.SORT();
                pintarMol.babel.INPUT();
            }
            
            if(!pintarMol.babel.sort.equals("unknown")&&!pintarMol.babel.sort.equals("directory")) {
                ini_mol();
                pintarMol.load();
                mol_3d_enable(true);
                ver3D_MOL();
                loadLVS();
                pintarMol.showMesg=false;
            }
        }
    }
    
    
    
    
    void load_file(File newFile){
        if(newFile!=null) {
            boolean open=false;
            savePos.setEnabled(true);
            if(newFile.getName().substring(newFile.getName().length()-4,newFile.getName().length()).equals(".xyz")){
                if(newFile!=null) {
                    pintarMol.babel.sort="xyz";
                    iniciarXYZ();
                    ini_mol();
                    mol_3d_enable(true);
                    open=true;
                    savePos.setEnabled(false);
                }
            }
            if(newFile.getName().substring(newFile.getName().length()-4,newFile.getName().length()).equals(".jpg")||
                    newFile.getName().substring(newFile.getName().length()-4,newFile.getName().length()).equals("jpeg")||
                    newFile.getName().substring(newFile.getName().length()-4,newFile.getName().length()).equals(".gif")||
                    newFile.getName().substring(newFile.getName().length()-3,newFile.getName().length()).equals(".ps")||
                    newFile.getName().substring(newFile.getName().length()-4,newFile.getName().length()).equals(".eps")){
                new dialogo.show_picture(newFile.getName()).plot(newFile);
                open=true;
            }
            if(newFile.getName().substring(newFile.getName().length()-4,newFile.getName().length()).equals(".txt") ||
                    newFile.getName().substring(newFile.getName().length()-4,newFile.getName().length()).equals(".dat") ||
                    newFile.getName().substring(newFile.getName().length()-4,newFile.getName().length()).equals(".out")){
                new editor(newFile.getAbsolutePath()).openFile(newFile);
                open=true;
            }
            if(!open){
                if(!pintarMol.babel.getSort(newFile.getAbsolutePath()).equals("unknown")){
                    pintarMol.babel.projectFile=newFile.getAbsolutePath();
                    pintarMol.babel.sort=pintarMol.babel.getSort(newFile.getAbsolutePath());
                    pintarMol.babel.read();
                    pintarMol.firstTime=true;
                    //ver3D_MOL();
                    //ini_mol();
                    
                    tipo.setText(pintarMol.babel.sort+" ("+new File(pintarMol.babel.projectFile).getName()+")");
                    
                    pintarMol.load();
                    mol_3d_enable(true);
                    ver3D_MOL();
                    loadLVS();
                    
                }else  new editor(newFile.getAbsolutePath()).openFile(newFile);
            }
        }
    }
    
    void KEY_comun(java.awt.event.KeyEvent evt){
        if(KeyEvent.VK_F5== evt.getKeyCode()) loadMOL();
        if(KeyEvent.VK_F3== evt.getKeyCode()) {
            newFile =  new dialogo.chooser().fileChoose("Open file","open",pintarMol.babel.path) ;
            load_file(newFile);
        }
        if(KeyEvent.VK_F4== evt.getKeyCode()){
            projectChooser.pack();
            projectChooser.setVisible(true);
            projectChooser.setLocation((int) (jSplitForm.getLocationOnScreen().getX() + jSplitForm.getDividerLocation()+jSplitForm.getDividerSize()),(int) jSplitForm.getLocationOnScreen().getY());
            newFile=new File(pintarMol.babel.path).getParentFile();
            if(newFile.exists())path_project.setText(newFile.toString());
            projectOpen();
        }
        if(KeyEvent.VK_F8 == evt.getKeyCode() ) {
            if(me4.isEnabled())  new dialogo.chooser().savePicture("Save file *.jpg","save",
                    new File(pintarMol.babel.path+SEP+"picture.jpg").toString(),pintarMol.imageBuffered);
        }
        if(KeyEvent.VK_F9 == evt.getKeyCode() ) {
            if(pintarMol.MOL_enable)
                new dialogo.show_picture(new File(pintarMol.babel.path+SEP+"picture.jpg").toString()).plot(screen5Buffered);
        }
    }
    
    java.io.File [] ls;
    void preview(boolean ordenarBas){
        if(pintarMol.MOL_enable)
            if(previewPos.isEnabled()){
            if(ordenarBas)pintarMol.babel.infBas.orderBas();
            //limpiamos:
            ls= new File(write_tmp).listFiles();
            for (File l : ls) {
                l.delete();
            }
            if(pintarMol.babel.sort.equals("xyz")) {
                pintarMol.babel.write("xyz",write_tmp+SEP+"step_"+xyz_step.getText()+".xyz");
                new editor().openDirecory_tmp(new File( write_tmp) ,new File(pintarMol.babel.path));
            }else{
                if(pintarMol.babel.sort.equals("directory")){
                    pintarMol.babel.SORT();
                    pintarMol.babel.INPUT();
                }
                pintarMol.babel.write(pintarMol.babel.sort,write_tmp+SEP+new File(pintarMol.babel.projectFile).getName());
                new editor().openDirecory_tmp(new File( write_tmp) ,new File(pintarMol.babel.path));
            }
            }
    }
//----------------------------------------------------------------------
//----------------------------------------------------------------------
//----------------------------------------------------------------------
//----------------------------------------------------------------------
    
    void runProcess(int i,boolean ajustar_maximos){
        screen_out.setEnabled(true);
        if(pintar2D_out.firstTime){
            pintar2D_out.firstTime=false;
            pintar2D_out.opt.nfy=9;
        }
        if(pintar2D_out.opt.isVisible())
            if(!pintar2D_out.opt.ajustarMaximos)
                pintar2D_out.opt.load();
        File fileIN = new File(pintarMol.babel.xeorc+"temp/col.temp");
        if(new File(pintarMol.babel.xeorc+"script/"+botones.get(i)).exists()){
            //----clock---------
            long start = System.currentTimeMillis();// (milisegundos)
            long elapsed = System.currentTimeMillis() - start;
            if(ajustar_maximos && new File(SEP+"etc").exists()){
                fileIN.delete();
                try{
                    proc = run.exec("chmod a+x "+pintarMol.babel.xeorc+"script/"+botones.get(i));
                    proc.waitFor(); //esperar hasta empezar el siguiente
                } catch(IOException | InterruptedException e){}
                try{
                    proc = run.exec(pintarMol.babel.xeorc+"script/"+botones.get(i)+" "+outText.getText()+" "+parameters.getText());
                    proc.waitFor();
                } catch(IOException | InterruptedException e){}
                try{
                    proc = run.exec("mv col.temp "+fileIN.getAbsolutePath());
                    proc.waitFor();
                } catch(IOException | InterruptedException e){}
                   /*
                    start = System.currentTimeMillis();// (milisegundos)
                    elapsed = System.currentTimeMillis() - start;
                    while(! fileIN.exists() && elapsed<Long.valueOf(Time.getText())*1000)elapsed = System.currentTimeMillis() - start;
                    */
            }  // ;)
            else{
                if (!(new File(SEP+"etc").exists())){
                    System.out.println("it's posible someone delete your /etc, it's also posible you are working with windows." +
                            "\n"+"the problem: you can't run script on your sistem, please do something like: mkdir /etc or ¿?");
                }
            }
            g2d.clear();
            int NCol=cadena.numeroCol(fileIN);
            pintar2D_out.inputfile2D=fileIN;
            g2d.add("protocolo");
            for(int k=1;k<=NCol;k++) g2d.add(k+" "+(k+1)+" "+botones.get(i));
            pintar2D_out.g2d=g2d;
            if(ajustar_maximos) pintar2D_out.Max_onefile_Y(1.0 /*step*/);
            pintar2D_out.imageBuffered = new BufferedImage(screen_out.getWidth()-dIc,screen_out.getHeight()-dIc, BufferedImage.TYPE_INT_RGB);
            pintar2D_out.show_onefile_Y(1.0);
            screen_out.setIcon(new ImageIcon(pintar2D_out.imageBuffered));
            if(pintar2D_out.opt.isVisible())
                if(pintar2D_out.opt.ajustarMaximos)
                    pintar2D_out.opt.ini();
        }
    }
    
    void ini_mol(){
        //before load the bas file
        //  pintarMol.babel.pathxeo=pathxeo;
        pintarMol.babel.infBas.seeCell_Vol=mol_seeVol.isSelected();
        pintarMol.babel.infBas.seeCell_ijk=mol_seeIndex.isSelected();
        pintarMol.babel.infBas.R_min[0]=Double.parseDouble(mol_Xini.getText());
        pintarMol.babel.infBas.R_min[1]=Double.parseDouble(mol_Yini.getText());
        pintarMol.babel.infBas.R_min[2]=Double.parseDouble(mol_Zini.getText());
        pintarMol.babel.infBas.R_max[0]=Double.parseDouble(mol_Xfin.getText());
        pintarMol.babel.infBas.R_max[1]=Double.parseDouble(mol_Yfin.getText());
        pintarMol.babel.infBas.R_max[2]=Double.parseDouble(mol_Zfin.getText());
        pintarMol.babel.infBas.lvs_1=(int) Double.parseDouble(mol_lvs_1.getText());
        pintarMol.babel.infBas.lvs_2=(int) Double.parseDouble(mol_lvs_2.getText());
        pintarMol.babel.infBas.lvs_3=(int) Double.parseDouble(mol_lvs_3.getText());
        //----
    }
    
    @SuppressWarnings("empty-statement")
    void mol_3d_enable(boolean ena ){
        copy.setEnabled(ena);
        paste.setEnabled(ena);
        savePos.setEnabled(ena);
        previewPos.setEnabled(ena);
        pintarMol.MOL_enable=ena;
        diffRadio.setEnabled(ena);
        PERS.setEnabled(ena);
        me4.setEnabled(ena);
        me0.setEnabled(ena);
        seeBond.setEnabled(ena);
        TOL.setEnabled(ena);
        CheckFragments.setEnabled(ena);
        selectFixAtoms.setEnabled(ena);
        seePos.setEnabled(ena);
        mas_mol.setEnabled(ena);
        xy_mol.setEnabled(ena);
        zy_mol.setEnabled(ena);
        menos_mol.setEnabled(ena);
        yx_mol.setEnabled(ena);
        xz_mol.setEnabled(ena);
        jButton35.setEnabled(ena);
        jButton36.setEnabled(ena);
        jButton37.setEnabled(ena);
        jButton62.setEnabled(ena);
        jButton9.setEnabled(ena);
        jButton21.setEnabled(ena);
        jButtonPovray.setEnabled(ena);
        jButtonPovray1.setEnabled(ena);
        seePovray1.setEnabled(ena);
        BtOrder.setEnabled(ena);
    }
    
    void loadLVS(){
        lvs_11.setText(cadena.formatFortran(0,10,4,pintarMol.babel.infBas.lvs[0][0])+" ");
        lvs_12.setText(cadena.formatFortran(0,10,4,pintarMol.babel.infBas.lvs[0][1])+" ");
        lvs_13.setText(cadena.formatFortran(0,10,4,pintarMol.babel.infBas.lvs[0][2])+" ");
        lvs_21.setText(cadena.formatFortran(0,10,4,pintarMol.babel.infBas.lvs[1][0])+" ");
        lvs_22.setText(cadena.formatFortran(0,10,4,pintarMol.babel.infBas.lvs[1][1])+" ");
        lvs_23.setText(cadena.formatFortran(0,10,4,pintarMol.babel.infBas.lvs[1][2])+" ");
        lvs_31.setText(cadena.formatFortran(0,10,4,pintarMol.babel.infBas.lvs[2][0])+" ");
        lvs_32.setText(cadena.formatFortran(0,10,4,pintarMol.babel.infBas.lvs[2][1])+" ");
        lvs_33.setText(cadena.formatFortran(0,10,4,pintarMol.babel.infBas.lvs[2][2])+" ");
    }
    
    void ver3D_MOL(){
        pintarMol.verIcono=verIconosAtomos.isSelected();
        pintarMol.verEscalaGrises=escalaGrises.isSelected();
        pintarMol.verBordes=!seeBordes.isSelected();
        pintarMol.babel.infBas.seeCell_Vol=mol_seeVol.isSelected();
        pintarMol.babel.infBas.seeCell_ijk=mol_seeIndex.isSelected();
        pintarMol.xyzFileTr=new File(xyztFile.getText());
        pintarMol.xyzTr=xyzTr.isSelected();
        pintarMol.atoms_xyzTr=xyztAtomsTr.getText();
        pintarMol.xyztFile=new File(xyztFile.getText());
        pintarMol.xyzt=xyzt.isSelected();
        pintarMol.pixel=(int)Double.parseDouble(pixel.getText());
        pintarMol.rescalateArrow=Double.parseDouble(rescalateArrow.getText());
        pintarMol.seeArrows=seeArrows.isSelected();
        pintarMol.diffColorArrows=seeArrowsColor.isSelected();
        pintarMol.verRad=diffRadio.isSelected();
        pintarMol.C3D.perspectiva=(PERS.getValue()*1.0)/100;
        pintarMol.rad= Double.parseDouble(rad.getText());
        pintarMol.verPosiciones=seePos.isSelected();
        pintarMol.seeFrag=CheckFragments.isSelected();
        pintarMol.colorEjes=colorEjes.getBackground();
        pintarMol.colorArrows=colorArrows.getBackground();
        pintarMol.colorFondo=colorFondo.getBackground();
        pintarMol.currentEjes=fuente;
        pintarMol.currentFont=fuente;
        pintarMol.verEjes=verEjes.isSelected();
        pintarMol.babel.infBas.seeBond=seeBond.isSelected();
        pintarMol.babel.infBas.tol=TOL.getValue();
        pintarMol.grosor=(int) Double.parseDouble(grosor.getText());
        pintarMol.c_out[0]=Double.parseDouble(jT_cx.getText());
        pintarMol.c_out[1]=Double.parseDouble(jT_cy.getText());
        pintarMol.c_out[2]=Double.parseDouble(jT_cz.getText());
        screen5Buffered = new BufferedImage(screen5.getWidth()-dIc,screen5.getHeight()-dIc, BufferedImage.TYPE_INT_RGB);
        screen5Buffered = pintarMol.pintar3D(screen5Buffered);
        screen5.setIcon(new ImageIcon(screen5Buffered));
        if(!mol_seeVol.isSelected()){
            mol_Xini.setText(cadena.pasarString(pintarMol.babel.infBas.r_min[0]-0.1));
            mol_Yini.setText(cadena.pasarString(pintarMol.babel.infBas.r_min[1]-0.1));
            mol_Zini.setText(cadena.pasarString(pintarMol.babel.infBas.r_min[2]-0.1));
            mol_Xfin.setText(cadena.pasarString(pintarMol.babel.infBas.r_max[0]+0.1));
            mol_Yfin.setText(cadena.pasarString(pintarMol.babel.infBas.r_max[1]+0.1));
            mol_Zfin.setText(cadena.pasarString(pintarMol.babel.infBas.r_max[2]+0.1));
        }
        MiniEjes();
    }
    void MiniEjes(){
        //---------- MiniEjes()
        screenMiniejes = new BufferedImage(MiniEjes.getWidth()-dIc,MiniEjes.getHeight()-dIc, BufferedImage.TYPE_INT_RGB);
        screenMiniejes = pintarMol.pintarMiniEjes(screenMiniejes);
        MiniEjes.setIcon(new ImageIcon(screenMiniejes));
        //--------------
    }
    
    //--------------------------------------------------------------------------
    
    void iniciarXYZ(){
        if(newFile!=null) {
            seeArrows.setSelected(false);
            pintarMol.babel.sort="xyz";
            pintarMol.seeArrows=seeArrows.isSelected();
            savePos.setEnabled(false);        
            pintarMol.babel.sort="xyz";
            //--------- cargamos el primer paso -----
            pintarMol.babel.infBas.xyzFile=newFile;
            tipo.setText(pintarMol.babel.path+" "+pintarMol.babel.sort+" "+pintarMol.babel.infBas.xyzFile);
            //pintarMol.babel.infBas.nPasosTotal=cadena.numeroFilas(newFile)/(pintarMol.babel.infBas.nAtomsUnitCell +2);
            pintarMol.firstTime=true;
            pintarMol.babel.infBas.iStep=0;
            pintarMol.babel.infBas.iniStepMEM=0;
            cargarUnPaso();
            //---------------------------------------
            open_xyz open_xyz=new open_xyz();
            open_xyz.clear();
            open_xyz.visible=false;
            open_xyz.ajustar=true;
            open_xyz.start();
        }
        if(options_XYZ.isVisible()){
            jSliderMemory.setValue( jSliderMemory.getMaximum()*pintarMol.babel.infBas.MEM/pintarMol.babel.infBas.nAtomsUnitCell/pintarMol.babel.infBas.nPasosTotal  );
            FRAMESLOAD.setText(((int)pintarMol.babel.infBas.MEM/pintarMol.babel.infBas.nAtomsUnitCell)+"");
        }
    }
    
    void cargarUnPaso(){
        Bt1.setEnabled(true);
        pintarMol.babel.loadStepXYZ( pintarMol.babel.infBas.iStep);
        Bt1.setEnabled(false);
        pintarMol.mesg=pintarMol.babel.segundaLinea;
        pintarMol.showMesg=true;
        ver3D_MOL();
        pintarMol.showMesg=false;
    }
    
    class open_xyz extends Thread {
        boolean visible=true;
        boolean ajustar=true;
        boolean terminado=true;
        long TThre;
        public void run() {   //start
            TThre=System.currentTimeMillis();
            while(pintarMol.babel.infBas.LoadingXYZ){
                if(System.currentTimeMillis()-TThre>MS_PER_FRAME*2){
                    //System.out.println("esperando a cargar ....");
                    TThre=System.currentTimeMillis();
                }
            }
            terminado=true;
            if(ajustar)terminado=pintarMol.babel.infBas.infoXYZ_file(!Te33.isSelected());
            if(terminado){
                if(NoMemory.isSelected())pintarMol.babel.infBas.MEM=0;
                if(AllMemory.isSelected())pintarMol.babel.infBas.MEM=pintarMol.babel.infBas.nPasosTotal*pintarMol.babel.infBas.nAtomsUnitCell;
                xyz_end.setText("of "+pintarMol.babel.infBas.nPasosTotal);
                ini();
                Bt1.setEnabled(true);
                refrescoBarra_XYZ refresco = new refrescoBarra_XYZ();
                refresco.start();
                pintarMol.babel.infBas.load_xyz();
                if(pintarMol.babel.infBas.nPasosTotal==1)oneStep(false);
                Bt1.setEnabled(false);
                if(visible) ver3D_MOL_xyz();
                barra_XYZ((pintarMol.babel.infBas.iStep*jLabelXYZ.getWidth())/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
            }
        }
        void clear(){
            new cleanXYZ().start();
        }
        
        class cleanXYZ extends Thread{
            @Override
            public void run() {
                if(pintarMol.babel.infBas.LoadingXYZ){
                    TThre=System.currentTimeMillis();
                    pintarMol.babel.infBas.stopLoadXYZ=true;
                    while(pintarMol.babel.infBas.LoadingXYZ){
                        if(System.currentTimeMillis()-TThre>MS_PER_FRAME*1){
                            //System.out.println("parando el asunto");
                            TThre=System.currentTimeMillis();
                        }
                    }
                }
            }
        }
        
        void ini(){
            mol_3d_enable(true);
            oneStep(true);
        }
        void oneStep(boolean h){
            xyz_mas.setEnabled(h);
            xyz_menos.setEnabled(h);
            xyz_step.setEnabled(h);
            Bt0.setEnabled(h);
            PlayB.setEnabled(h);
            jLabelXYZ.setEnabled(h);
        }
    }
    
    class refrescoBarra_XYZ extends Thread{
        @Override
        public void run() {
            long tiempoB=System.currentTimeMillis();
            while(pintarMol.babel.infBas.LoadingXYZ){
                if(System.currentTimeMillis()>tiempoB+MS_PER_FRAME){
                    tiempoB=System.currentTimeMillis();
                    barra_XYZ((pintarMol.babel.infBas.iStep*jLabelXYZ.getWidth())/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
                    xyz_end.setText("of "+pintarMol.babel.infBas.nPasosTotal);
                }
            }
            barra_XYZ((pintarMol.babel.infBas.iStep*jLabelXYZ.getWidth())/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
            xyz_end.setText("of "+pintarMol.babel.infBas.nPasosTotal);
        }
    }
    
    boolean StopToPlay=true;
    class Play extends Thread{
        @Override
        public void run(){
            int pasos=0;
            long tiempoPlay=System.currentTimeMillis();
            v_instant_FRAME.setText("V_aver = 0");
            while(!StopToPlay){
                OneStepForward();
                while(System.currentTimeMillis()<tiempoPlay+(long)Double.parseDouble(v_FRAMES.getText())){}
                if(options_XYZ.isVisible()){
                    v_instant_FRAME.setText("V_aver = "+((System.currentTimeMillis()-tiempoPlay)*pasos/(pasos+1) + ((long) cadena.readColDouble(3,v_instant_FRAME.getText()))/(pasos+1)));
                    pasos++;
                }
                tiempoPlay=System.currentTimeMillis();
                //    System.out.println(tiempoPlay+" "+v_FRAMES.getText());
            }
        }
    }
    BufferedImage screenXYZ;
    Graphics graphicsXYZ;
    int barraStep;
    void barra_XYZ(int iStep,int iniBuff ,int Buff){
        screenXYZ = new BufferedImage(jLabelXYZ.getWidth() ,jLabelXYZ.getHeight() , BufferedImage.TYPE_INT_RGB);
        graphicsXYZ = screenXYZ.createGraphics();
        if(iStep<0)iStep=0;
        if(iStep>jLabelXYZ.getWidth()-1) iStep=jLabelXYZ.getWidth()-1;
        graphicsXYZ.setColor(new Color(238,238,238));
        graphicsXYZ.fillRect(0,0,jLabelXYZ.getWidth(), jLabelXYZ.getHeight());
        graphicsXYZ.setColor(new Color(197,223,255));
        graphicsXYZ.fillRect(iniBuff,0,Buff,jLabelXYZ.getHeight());
        graphicsXYZ.setColor(new Color(107,182,255));
        graphicsXYZ.fillRect(iStep,0,2, jLabelXYZ.getHeight());
        ImageIcon Cicon = new ImageIcon(screenXYZ);
        jLabelXYZ.setIcon(Cicon);
        xyz_step.setText(pintarMol.babel.infBas.iStep+"");
        jLabelXYZ.setToolTipText("load = "+pintarMol.babel.infBas.paso);
    }
    
    
    void OneStepForward(){
        pintarMol.babel.infBas.iStep++;
        pintarMol.babel.infBas.iStep=(pintarMol.babel.infBas.iStep>=pintarMol.babel.infBas.nPasosTotal)?0:pintarMol.babel.infBas.iStep;
        if(pintarMol.babel.infBas.MEM==0){
            cargarUnPaso();
        }else{
            try{
                if(pintarMol.babel.infBas.iStep>=pintarMol.babel.infBas.iniStepMEM && pintarMol.babel.infBas.iStep < (pintarMol.babel.infBas.iniStepMEM + pintarMol.babel.infBas.nStepMEM)){
                    if(pintarMol.babel.infBas.iStep>=pintarMol.babel.infBas.iniStepMEM && pintarMol.babel.infBas.iStep < (pintarMol.babel.infBas.iniStepMEM + pintarMol.babel.infBas.xyz.size()) ){
                        ver3D_MOL_xyz();
                    }else{
                        if(pintarMol.babel.infBas.LoadingXYZ){
                            Long TThre=System.currentTimeMillis();
                            while(pintarMol.babel.infBas.iStep >= (pintarMol.babel.infBas.iniStepMEM + pintarMol.babel.infBas.xyz.size())){
                                if(System.currentTimeMillis()-TThre>MS_PER_FRAME*4){
                                    //System.out.println("esperando a que llege ...");
                                    TThre=System.currentTimeMillis();
                                }
                            }
                        }
                    }
                }else{
                    // System.out.println("loading ...");
                    open_xyz open_xyz=new open_xyz();
                    open_xyz.clear();
                    pintarMol.babel.infBas.iniStepMEM=pintarMol.babel.infBas.iStep;
                    cargarUnPaso();
                    open_xyz.visible=false;
                    open_xyz.ajustar=false;
                    open_xyz.start();
                }
            }catch (ArrayIndexOutOfBoundsException e){
                //   System.out.println("re-loading ...");
                cargarUnPaso();
                System.out.println("ArrayIndexOutOfBoundsException, don't worry about ");
            }
        }
        barra_XYZ((pintarMol.babel.infBas.iStep*jLabelXYZ.getWidth())/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
    }
    
    void OneStepBack(){
        pintarMol.babel.infBas.iStep--;
        pintarMol.babel.infBas.iStep=(pintarMol.babel.infBas.iStep<0)?(pintarMol.babel.infBas.nPasosTotal-1):pintarMol.babel.infBas.iStep;
        if(pintarMol.babel.infBas.MEM==0){
            cargarUnPaso();
        }else{
            if(pintarMol.babel.infBas.iStep>=pintarMol.babel.infBas.iniStepMEM && pintarMol.babel.infBas.iStep < (pintarMol.babel.infBas.iniStepMEM +pintarMol.babel.infBas.xyz.size() ) )
                ver3D_MOL_xyz();
            else{
                pintarMol.babel.infBas.iniStepMEM=pintarMol.babel.infBas.iStep;
                open_xyz open_xyz=new open_xyz();
                open_xyz.clear();
                open_xyz.ajustar=false;
                open_xyz.start();
            }
        }
        barra_XYZ((pintarMol.babel.infBas.iStep*jLabelXYZ.getWidth())/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.iniStepMEM*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal, pintarMol.babel.infBas.xyz.size()*jLabelXYZ.getWidth()/pintarMol.babel.infBas.nPasosTotal);
    }
    
    void ver3D_MOL_xyz(){
        pintarMol.babel.infBas.tol=TOL.getValue();
        pintarMol.babel.infBas.bas=(ArrayList)pintarMol.babel.infBas.xyz.get( pintarMol.babel.infBas.iStep-pintarMol.babel.infBas.iniStepMEM);
        if(!Te33.isSelected()) pintarMol.ajustar_centro();  //when load bas file
        pintarMol.mesg=cadena.readLine( pintarMol.babel.infBas.iStep+1-pintarMol.babel.infBas.iniStepMEM,pintarMol.babel.infBas.secondline_xyz);
        pintarMol.showMesg=true;
        ver3D_MOL();
        pintarMol.showMesg=false;
    }
    
//--------------------------------------------------------------------------
    
    void run_povray(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                pov.C3D.Rot[i][j]=pintarMol.C3D.Rot[i][j];
        pov.transparente=pov_transparente.isSelected();
        pov.Trans=pov_Trans.getText();
        pov.colorArrow=colorArrows.getBackground();
        pov.res=Double.parseDouble(rescalateArrow.getText());
        pov.ver_flecha=seeArrows.isSelected();
        pov.ver_flechaDiffColor=seeArrowsColor.isSelected();
        //  pov.pathxeo=pathxeo;
        pov.path=pintarMol.babel.path;
        pov.infBas=pintarMol.babel.infBas;
        pov.Height=pintarMol.C3D.oy;
        pov.Width=pintarMol.C3D.ox;
        pov.a=200/pintarMol.C3D.a;
        pov.angle=Double.parseDouble(angle.getText());
        pov.antialias=Double.parseDouble(antialias.getText());
        pov.use_radio=use_radio.isSelected();
        pov.rad=Double.parseDouble(radio.getText());
        pov.use_radio_selected=pov_radio_selec.isSelected();
        pov.radSelected=Double.parseDouble(pov_radio_selecA.getText());
        pov.bonds=Double.parseDouble(bonds.getText());
        pov.ver_enlaces=seeBond.isSelected();
        pov.ver_trayectoria=xyzTr.isSelected();
        if(pov.ver_trayectoria){          
            for (mol.pixel trayectoria : pintarMol.trayectoria) {
                pov.loadTR(trayectoria.R[0], trayectoria.R[1], trayectoria.R[2], trayectoria.color, trayectoria.dOb);                            
            }
                                   
        }
        pov.colorfondo=pintarMol.colorFondo;
        pov.write_files();
        pov.ejecutar_povray(true);
        if(pov.picture.exists()) screen5.setIcon(new ImageIcon(pov.picture.getAbsolutePath()));
        else System.out.println("Is povray install?, the "+pov.picture.getAbsolutePath()+" file don't exist but I don't know Why? ");   
    }
    
    void verPovray_xyz(){
        pintarMol.babel.infBas.tol=TOL.getValue();
        new File(pintarMol.babel.path+SEP+"frames").mkdir();
        String cd = (pintarMol.babel.infBas.xyz.size()+"");
        int p = 0;
        for(int step= Integer.parseInt(pov_xyz_ini.getText());step<=Integer.parseInt(pov_xyz_fin.getText());step+=Integer.parseInt(pov_xyz_step.getText())){                    
            pintarMol.babel.loadStepXYZ(step);               
            p =(step - Integer.parseInt(pov_xyz_ini.getText()))*100/(Integer.parseInt(pov_xyz_fin.getText())-Integer.parseInt(pov_xyz_ini.getText()));
            run_povray();
            pov.delete_files();
            pov.picture.renameTo(new File(pintarMol.babel.path+SEP+"frames"+SEP+"out_"+cadena.ceros(cd.length(),step)+".png"));
            System.out.println(p+"%    "+pov.picture+"   "+pintarMol.babel.path+SEP+"frames"+SEP+"out_"+cadena.ceros(cd.length(),step)+".png");
        }
    }
    
    void verFrames_xyz(){
        pintarMol.babel.infBas.tol=TOL.getValue();
        java.awt.image.RenderedImage o;
        new File(pintarMol.babel.path+SEP+"frames").mkdir();
        String cd = (pintarMol.babel.infBas.xyz.size()+"");
              int p = 0;
       for(int step= Integer.parseInt(pov_xyz_ini.getText());step<=Integer.parseInt(pov_xyz_fin.getText());step+=Integer.parseInt(pov_xyz_step.getText())){                     
            pintarMol.babel.loadStepXYZ( step);
            ver3D_MOL();
            try{
                o = pintarMol.imageBuffered;
                ImageIO.write(o,"png",new File(pintarMol.babel.path+SEP+"frames"+SEP+"out_"+cadena.ceros(cd.length(),step)+".png"));
            } catch (IOException e) { }
        }
    }
    
    void povray(boolean showfile){
        run_povray();
        if(showfile) pov.show_file();
        pov.delete_files();
    }

    
    BufferedImage color_buffer;
    Graphics color_graphics ;
    void dialogo_color(){
        color_buffer = new BufferedImage(Clabel.getWidth() ,Clabel.getHeight() , BufferedImage.TYPE_INT_RGB);
        color_graphics = color_buffer.createGraphics();
        pintarMol.colorIni=color_ini.getBackground();
        pintarMol.colorInter1=color_inter_1.getBackground();
        pintarMol.colorInter2=color_inter_2.getBackground();
        pintarMol.colorFin = color_fin.getBackground();
        pintarMol.posicion_1 = Sinter1.getValue(); //(Sinter1.getValue()*Clabel.getWidth())/Sinter1.getMaximum();
        pintarMol.posicion_2 = Sinter2.getValue(); //(Sinter2.getValue()*Clabel.getWidth())/Sinter2.getMaximum();
        if( pintarMol.posicion_1 >=  pintarMol.posicion_2){
            int auxpos= pintarMol.posicion_2;
            pintarMol.posicion_2= pintarMol.posicion_1;
            pintarMol.posicion_1=auxpos;
        }
        for(int C_aux=0;C_aux < Clabel.getWidth(); C_aux+=4){
            color_graphics.setColor(pintarMol.getColorPixel(C_aux*100/Clabel.getWidth()));
            color_graphics.fillRect(C_aux,0,4, Clabel.getHeight());
        }
        ImageIcon Cicon = new ImageIcon(color_buffer);
        Clabel.setIcon(Cicon);
    }
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
}

