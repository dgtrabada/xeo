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

package editor;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.ArrayList;

public class editor extends javax.swing.JFrame {
    Font currentFont;
    Color currentColor;
    String SEP = System.getProperty("file.separator");
    Vector texto = new Vector();
    public editor(){
        // no hacer nada aqui es importantisimo :)
    }
    
    public editor(String path){
        boolean ERFont=false;
        try{
            java.io.InputStream is = this.getClass().getResourceAsStream("/dejavu/DejaVuSansMono.ttf");
            try{
                currentFont = Font.createFont(Font.TRUETYPE_FONT,is);
                currentFont = currentFont.deriveFont(12.0f);
                is.close();
            }catch(IOException e){ERFont=true;}
        }catch(FontFormatException e){ERFont=true;}
        if(ERFont){
            currentFont = new Font("Monospaced",Font.PLAIN,12);
            System.out.println("ERROR : fuente no encontrada usamos : "+currentFont.toString());
        }
        
        initComponents();
        setTitle(path);
        texto.clear();
        currentColor = Color.black;
        this.setVisible(true);
        
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        open = new javax.swing.JMenu();
        save = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("editor");
        textArea.setColumns(20);
        textArea.setFont(currentFont);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/open.gif")));
        open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                openMousePressed(evt);
            }
        });

        jMenuBar1.add(open);

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/save.gif")));
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                saveMousePressed(evt);
            }
        });

        jMenuBar1.add(save);

        jMenu1.setText("UTF-8");
        jMenu1.setFont(currentFont);
        jMenuItem1.setFont(currentFont);
        jMenuItem1.setText("open");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });

        jMenu1.add(jMenuItem1);

        jMenuItem2.setFont(currentFont);
        jMenuItem2.setText("save");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });

        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        File auxfile = fileChoose("save",new File(getTitle())) ;
        if(auxfile != null) saveFileUTF8(auxfile);
    }//GEN-LAST:event_jMenuItem2MousePressed
    
    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        File auxfile = fileChoose("open", new File(getTitle())) ;
        if(auxfile != null) openFileUTF8(auxfile);
    }//GEN-LAST:event_jMenuItem1MousePressed
    
    private void saveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMousePressed
        File auxfile = fileChoose("save",new File(getTitle())) ;
        if(auxfile != null) saveFile(auxfile);
    }//GEN-LAST:event_saveMousePressed
    
    private void openMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openMousePressed
        File auxfile = fileChoose("open", new File(getTitle())) ;
        if(auxfile != null) openFile(auxfile);
    }//GEN-LAST:event_openMousePressed
    
    
    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu open;
    private javax.swing.JMenu save;
    private javax.swing.JTextArea textArea;
    // Fin de declaración de variables//GEN-END:variables
    
    public File fileChoose(String title, File aux){
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        fc.setName(getTitle());
        fc.setSelectedFile(aux);
        int returnVal=fc.showDialog( editor.this , title);
        File auxfile = null;
        if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION){
            auxfile = fc.getSelectedFile();
        }
        return auxfile;
    }
    
    public void Load(Vector aux){
        textArea.setText("");
        for(int i=0;i<aux.size();i++) {
            textArea.append(aux.elementAt(i) + "\n");
        }
    }
    
    public void Load(ArrayList<String> aux){
        textArea.setText("");
        for(int i=0;i<aux.size();i++) {
            textArea.append(aux.get(i) + "\n");
        }
    }
    
    public void LoadString(String aux){
        textArea.setText("");
        textArea.append(aux);
    }
    
    
    public void LoadStringName(String aux, String name){
        setTitle(name);
        textArea.setText("");
        textArea.append(aux);
    }
    
    
    public void Load(File name){
        setTitle(name.getAbsolutePath());
        textArea.setText("");
        for(int i=0;i<texto.size();i++) {
            textArea.append(texto.elementAt(i) + "\n");
        }
    }
    
    public void Load(){
        textArea.setText("");
        for(int i=0;i<texto.size();i++) {
            textArea.append(texto.elementAt(i) + "\n");
        }
    }
    
    public void openFileUTF8(File auxFile){
        if(auxFile.exists())
            try{
                java.io.FileInputStream is = new  java.io.FileInputStream( auxFile.getAbsolutePath() );
                java.io.BufferedReader br = new  java.io.BufferedReader( new  java.io.InputStreamReader( is, "UTF-8" ) );
                String inputLine=null;
                textArea.setText(inputLine);
                while ((inputLine=br.readLine()) != null) {
                    textArea.append(inputLine + "\n");
                }
                br.close();
                is.close();
            }catch(Exception erx){System.out.println("error in editor : " + auxFile ); }
    }
    
    
    public void saveFileUTF8(File auxFile){
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(auxFile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeUTF(textArea.getText());
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error en editor: saveFile " );}
    }
    
    
    public void openFile(File auxFile){
        if(auxFile.exists())
            try{
                java.io.FileReader inFile = new java.io.FileReader(auxFile);
                java.io.LineNumberReader inLines = new java.io.LineNumberReader(inFile);
                String inputLine="";
                textArea.setText("");
                setTitle(auxFile.getAbsolutePath());
                while ((inputLine=inLines.readLine()) != null) {
                    textArea.append(inputLine + "\n");
                }
                setTitle(auxFile.getAbsolutePath());
            }catch(Exception erx){System.out.println("error in editor : " + auxFile ); }
    }
    
    public void openFile(File auxFile,File title){
        openFile(auxFile);
        setTitle(title.getAbsolutePath());
    }
    
    public void saveFile(File auxFile){
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(auxFile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(textArea.getText());
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error en editor: saveFile " );}
    }
    
    public void openDirecory_tmp(File tempFile, File outputPath){
        java.io.File [] ls = tempFile.listFiles();
        for (int i=0; i<ls.length; i++)
            new editor(outputPath.getAbsolutePath()).openFile(ls[i],new File(outputPath.getAbsolutePath()+SEP+ls[i].getName()));
    }
    
}
