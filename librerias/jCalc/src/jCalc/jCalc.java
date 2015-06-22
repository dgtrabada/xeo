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

package jCalc;

import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.awt.AWTEvent;

public class jCalc extends javax.swing.JFrame {
    calculadora.calculadora calc = new calculadora.calculadora();
    JMenuItem elemento ;
    int linea=0;
    ArrayList<String> panel = new ArrayList();
    String path;
    java.awt.Font fuente ;
    //exp(pow(cos(3.1415/5),2)+pow(sin(3.1415/5),2))
    public jCalc(boolean visible) {
        path=".";
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
        initComponents();
        menu();
        this.setSize(175,215);
        this.setVisible(visible);
    }
    
    void menu(){
        java.awt.event.ActionListener al = new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent evt ){
                input.setText(input.getText()+((JMenuItem)evt.getSource()).getText());
            }
        };
        for(int e=0;e< calc.func.size();e++){
            elemento = new JMenuItem( calc.func.get(e));
            elemento.addActionListener(al);
            elemento.setFont(fuente);
            jMenu.add( elemento );
        }
        enableEvents( AWTEvent.MOUSE_EVENT_MASK );
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        input = new javax.swing.JTextField();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel6 = new javax.swing.JLabel();
        jTextAverg = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jTextAvergInt = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();

        setTitle("calculator");
        setResizable(false);
        jButton4.setFont(fuente);
        jButton4.setText("7");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });

        jButton4.setBounds(10, 40, 25, 25);
        jDesktopPane1.add(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton9.setFont(fuente);
        jButton9.setText("4");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton9MousePressed(evt);
            }
        });

        jButton9.setBounds(10, 70, 25, 25);
        jDesktopPane1.add(jButton9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton10.setFont(fuente);
        jButton10.setText("1");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton10MousePressed(evt);
            }
        });

        jButton10.setBounds(10, 100, 25, 25);
        jDesktopPane1.add(jButton10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton13.setFont(fuente);
        jButton13.setText("0");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton13MousePressed(evt);
            }
        });

        jButton13.setBounds(10, 130, 56, 25);
        jDesktopPane1.add(jButton13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton14.setFont(fuente);
        jButton14.setText(".");
        jButton14.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton14MousePressed(evt);
            }
        });

        jButton14.setBounds(70, 130, 25, 25);
        jDesktopPane1.add(jButton14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setFont(fuente);
        jButton1.setText("=");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton1.setBounds(100, 130, 54, 25);
        jDesktopPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton20.setFont(fuente);
        jButton20.setText(")");
        jButton20.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton20.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton20MousePressed(evt);
            }
        });

        jButton20.setBounds(130, 100, 25, 25);
        jDesktopPane1.add(jButton20, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton17.setFont(fuente);
        jButton17.setText("(");
        jButton17.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton17.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton17MousePressed(evt);
            }
        });

        jButton17.setBounds(100, 100, 25, 25);
        jDesktopPane1.add(jButton17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton11.setFont(fuente);
        jButton11.setText("3");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton11MousePressed(evt);
            }
        });

        jButton11.setBounds(70, 100, 25, 25);
        jDesktopPane1.add(jButton11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton12.setFont(fuente);
        jButton12.setText("2");
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton12MousePressed(evt);
            }
        });

        jButton12.setBounds(40, 100, 25, 25);
        jDesktopPane1.add(jButton12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton8.setFont(fuente);
        jButton8.setText("5");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton8MousePressed(evt);
            }
        });

        jButton8.setBounds(40, 70, 25, 25);
        jDesktopPane1.add(jButton8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton7.setFont(fuente);
        jButton7.setText("6");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton7MousePressed(evt);
            }
        });

        jButton7.setBounds(70, 70, 25, 25);
        jDesktopPane1.add(jButton7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton16.setFont(fuente);
        jButton16.setText("x");
        jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton16.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton16MousePressed(evt);
            }
        });

        jButton16.setBounds(100, 70, 25, 25);
        jDesktopPane1.add(jButton16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton19.setFont(fuente);
        jButton19.setText("/");
        jButton19.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton19.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton19MousePressed(evt);
            }
        });

        jButton19.setBounds(130, 70, 25, 25);
        jDesktopPane1.add(jButton19, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton18.setFont(fuente);
        jButton18.setText("-");
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton18.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton18MousePressed(evt);
            }
        });

        jButton18.setBounds(130, 40, 25, 25);
        jDesktopPane1.add(jButton18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton15.setFont(fuente);
        jButton15.setText("+");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton15.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton15MousePressed(evt);
            }
        });

        jButton15.setBounds(100, 40, 25, 25);
        jDesktopPane1.add(jButton15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton6.setFont(fuente);
        jButton6.setText("9");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });

        jButton6.setBounds(70, 40, 25, 25);
        jDesktopPane1.add(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton5.setFont(fuente);
        jButton5.setText("8");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });

        jButton5.setBounds(40, 40, 25, 25);
        jDesktopPane1.add(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        input.setFont(fuente);
        input.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        input.setToolTipText("key : Enter(=) ; up(preview) ; Down(clean)");
        input.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputKeyPressed(evt);
            }
        });

        input.setBounds(10, 10, 144, 20);
        jDesktopPane1.add(input, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane1.setBounds(0, 0, 161, 161);
        jDesktopPane3.add(jDesktopPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel6.setFont(fuente);
        jLabel6.setText("File:");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("ComboBox.buttonShadow")));
        jLabel6.setBounds(10, 10, 30, 18);
        jDesktopPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextAverg.setFont(fuente);
        jTextAverg.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("ComboBox.buttonShadow")));
        jTextAverg.setBounds(42, 10, 208, 18);
        jDesktopPane2.add(jTextAverg, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton24.setFont(fuente);
        jButton24.setText("...");
        jButton24.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("ComboBox.buttonShadow")));
        jButton24.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton24MousePressed(evt);
            }
        });

        jButton24.setBounds(252, 10, 18, 18);
        jDesktopPane2.add(jButton24, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton27.setFont(fuente);
        jButton27.setText("calculate");
        jButton27.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("ComboBox.buttonShadow")));
        jButton27.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton27MousePressed(evt);
            }
        });

        jButton27.setBounds(210, 30, 60, 18);
        jDesktopPane2.add(jButton27, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextAvergInt.setFont(fuente);
        jTextAvergInt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextAvergInt.setText("1");
        jTextAvergInt.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("ComboBox.buttonShadow")));
        jTextAvergInt.setBounds(112, 30, 40, 18);
        jDesktopPane2.add(jTextAvergInt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel32.setFont(fuente);
        jLabel32.setText("Use the colum");
        jLabel32.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("ComboBox.buttonShadow")));
        jLabel32.setBounds(10, 30, 100, 18);
        jDesktopPane2.add(jLabel32, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(fuente);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("ComboBox.buttonShadow")));
        jTextArea1.setBounds(10, 50, 260, 90);
        jDesktopPane2.add(jTextArea1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane2.setBounds(170, 10, 280, 150);
        jDesktopPane3.add(jDesktopPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu.setText("funtion");
        jMenu.setFont(fuente);
        jMenuBar1.add(jMenu);

        jMenu1.setText("Average ( file )");
        jMenu1.setFont(fuente);
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 460, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        if(this.getWidth()==175) this.setSize(465,215);
        else this.setSize(175,215);
    }//GEN-LAST:event_jMenu1MousePressed
    
    private void jButton27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MousePressed
        statistic stt = new statistic();
        stt.MediaArchivo(new java.io.File(jTextAverg.getText()),((int) Double.valueOf(jTextAvergInt.getText()).doubleValue()));
        jTextArea1.setText(stt.salida);
    }//GEN-LAST:event_jButton27MousePressed
    
    private void jButton24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton24MousePressed
        java.io.File newFile=  new dialogo.chooser().fileChoose("Open file ","open",path) ;
        jTextAverg.setText(newFile.getAbsolutePath());
        path=newFile.getParent();
    }//GEN-LAST:event_jButton24MousePressed
    
    private void jButton20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MousePressed
        input.setText(input.getText()+")");
    }//GEN-LAST:event_jButton20MousePressed
    
    private void jButton19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MousePressed
        input.setText(input.getText()+"/");
    }//GEN-LAST:event_jButton19MousePressed
    
    private void jButton18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MousePressed
        input.setText(input.getText()+"-");
    }//GEN-LAST:event_jButton18MousePressed
    
    private void jButton17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MousePressed
        input.setText(input.getText()+"(");
    }//GEN-LAST:event_jButton17MousePressed
    
    private void jButton16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MousePressed
        input.setText(input.getText()+"*");
    }//GEN-LAST:event_jButton16MousePressed
    
    private void jButton15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MousePressed
        input.setText(input.getText()+"+");
    }//GEN-LAST:event_jButton15MousePressed
    
    private void jButton14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MousePressed
        input.setText(input.getText()+".");
    }//GEN-LAST:event_jButton14MousePressed
    
    private void jButton13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MousePressed
        input.setText(input.getText()+0);
    }//GEN-LAST:event_jButton13MousePressed
    
    private void jButton12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MousePressed
        input.setText(input.getText()+2);
    }//GEN-LAST:event_jButton12MousePressed
    
    private void jButton11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MousePressed
        input.setText(input.getText()+3);
    }//GEN-LAST:event_jButton11MousePressed
    
    private void jButton10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MousePressed
        input.setText(input.getText()+3);
    }//GEN-LAST:event_jButton10MousePressed
    
    private void jButton9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MousePressed
        input.setText(input.getText()+4);
    }//GEN-LAST:event_jButton9MousePressed
    
    private void jButton8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MousePressed
        input.setText(input.getText()+5);
    }//GEN-LAST:event_jButton8MousePressed
    
    private void jButton7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MousePressed
        input.setText(input.getText()+6);
    }//GEN-LAST:event_jButton7MousePressed
    
    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        input.setText(input.getText()+9);
    }//GEN-LAST:event_jButton6MousePressed
    
    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        input.setText(input.getText()+8);
    }//GEN-LAST:event_jButton5MousePressed
    
    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        input.setText(input.getText()+7);
    }//GEN-LAST:event_jButton4MousePressed
    
    private void inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputKeyPressed
        if(evt.VK_ENTER == evt.getKeyCode() ){
            panel.add(input.getText());
            input.setText(calc.calcular(input.getText())+"");
            panel.add(input.getText());
            linea=panel.size()-1;
        }
        if(evt.VK_UP == evt.getKeyCode()){
            if(linea>0) {
                linea--;
                input.setText(panel.get(linea));
            }
        }
        if(evt.VK_DOWN == evt.getKeyCode()){
            if(linea<panel.size()-1) {
                linea++;
                input.setText(panel.get(linea));
            }else {
                linea=panel.size();
                input.setText("");
            }
        }
    }//GEN-LAST:event_inputKeyPressed
    
    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        panel.add(input.getText());
        input.setText(calc.calcular(input.getText())+"");
        panel.add(input.getText());
        linea=panel.size()-1;
    }//GEN-LAST:event_jButton1MousePressed
    
    
    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JTextField input;
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
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextAverg;
    private javax.swing.JTextField jTextAvergInt;
    // Fin de declaración de variables//GEN-END:variables
    
}
