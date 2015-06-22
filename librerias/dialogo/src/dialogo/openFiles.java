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
package dialogo;

import java.io.File;
import java.awt.Font;

public class openFiles extends javax.swing.JFrame {

    Font fuente;
    File[] fileList;
    String SEP = System.getProperty("file.separator");
    String path;
    File newFile;
    boolean parar = true;

    /**
     * Creates new form openFiles
     */
    public openFiles(String aux) {
        fuente = new Font("SansSerif", Font.PLAIN, 11);
        path = aux;
        initComponents();
        this.pack();
        this.setVisible(true);
        path_project.setText(new File(path).getParent());
        projectOpen();
        System.out.println("esperar, hacer un hilo que se pare y otro que funcione");
        //hasta que no hagamos el hilo, no podremos retener el programa aqui
        //hasta que se hayan selecionado los archivos, sino
        // el progrma sigue y los archivos no estan selecionados
        // HACER HILO !!!, hasta entonces esto no funcionara ....
        //no vale con pararlo solo, hay que hacer un hilo que se pare y otro
        //que continue, sino como vamos a selecionar mientras los archivos
    }

    // <editor-fold defaultstate="collapsed" desc=" Cdigo Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        path_project = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProjectTree = new javax.swing.JTree();
        OpenProject = new javax.swing.JButton();
        Tdespl53 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        path_project.setFont(fuente);
        path_project.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        path_project.setFocusCycleRoot(true);
        path_project.setInheritsPopupMenu(true);
        path_project.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                path_projectKeyPressed(evt);
            }
        });

        jScrollPane1.setBorder(null);
        ProjectTree.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
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

        OpenProject.setFont(fuente);
        OpenProject.setText("open selected files");
        OpenProject.setToolTipText("you can press the right botton of the mouse following the left botton to load the project");
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
        Tdespl53.setEnabled(false);
        Tdespl53.setOpaque(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(Tdespl53, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, OpenProject, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, path_project, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(path_project, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(OpenProject, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Tdespl53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 394, Short.MAX_VALUE)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 393, Short.MAX_VALUE)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OpenProjectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenProjectMousePressed
        javax.swing.tree.TreePath[] select;
        int N, j;
        if (ProjectTree.getRowCount() < 3) {
            ProjectTree.clearSelection();
            ProjectTree.setSelectionRow(1);
        }
        if (!ProjectTree.isSelectionEmpty()) {
            select = ProjectTree.getSelectionPaths();
            N = ProjectTree.getSelectionPaths().length;
            File[] fileAux = new File[N];
            for (int i = N - 1; i >= 0; i--) {
                if (select[i].getPathCount() == 2) {
                    if (ProjectTree.getRowCount() < 3) {
                        path = path_project.getText();
                    } else {
                        path = path_project.getText() + SEP + select[i].getPathComponent(1).toString();
                    }
                    fileAux[i] = new File(path);
                }
            }
            fileList = fileAux;
            //      for(int i = 0 ; i<fileList.length ; i++) System.out.println(fileList[i].getName());

        }
    }//GEN-LAST:event_OpenProjectMousePressed

    private void ProjectTreeTreeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {//GEN-FIRST:event_ProjectTreeTreeWillExpand
        if (ProjectTree.getSelectionPath() != null) {
            if (ProjectTree.getSelectionPath().getPathCount() > 1) {
                newFile = new File(path_project.getText() + SEP + ProjectTree.getSelectionPath().getPathComponent(1).toString());
                //  System.out.println(ProjectTree.getlectionPath().getPathComponent(2).toString());
                if (newFile.exists()) {
                    path_project.setText(newFile.toString());
                }
                path = path_project.getText();
            }
        }
        projectOpen();
    }//GEN-LAST:event_ProjectTreeTreeWillExpand

    private void ProjectTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProjectTreeMouseClicked
        if (ProjectTree.getSelectionPath() != null) {
            if (ProjectTree.getSelectionPath().getPathCount() > 1) {
                if (ProjectTree.getSelectionPath().getPathComponent(1).toString().equals("..")) {
                    newFile = (new File(path_project.getText()).getParentFile());
                    if (newFile.exists()) {
                        path_project.setText(newFile.toString());
                    }
                    projectOpen();
                } else {
                    newFile = new File(path_project.getText() + SEP + "" + ProjectTree.getPathForLocation(evt.getX(), evt.getY()).getPathComponent(1).toString());
                    path = newFile.getAbsolutePath();
                    OpenProject.setEnabled(!newFile.isDirectory());
                }
            }
        }
    }//GEN-LAST:event_ProjectTreeMouseClicked

    private void path_projectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_path_projectKeyPressed
//        if(evt.VK_ENTER == evt.getKeyCode())projectOpen();
    }//GEN-LAST:event_path_projectKeyPressed

    // Declaracin de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JButton OpenProject;
    private javax.swing.JTree ProjectTree;
    private javax.swing.JTextField Tdespl53;
    private javax.swing.JDesktopPane jDesktopPane6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField path_project;
    // Fin de declaracin de variables//GEN-END:variables

    File carpeta;
    int nCarpetas;
    String[] subcarpeta;

    void projectOpen() {
        carpeta = new File(path_project.getText());
        if (path_project.getText().trim().equals("")) {
            carpeta = new File(path);
        }
        newFile = carpeta.getAbsoluteFile();
        if (newFile.exists()) {
            path_project.setText(newFile.toString());
        }
        subcarpeta = carpeta.list();
        javax.swing.tree.DefaultMutableTreeNode root = new javax.swing.tree.DefaultMutableTreeNode(carpeta.getName());
        root.add(new javax.swing.tree.DefaultMutableTreeNode(".."));
        nCarpetas = 0;
        if (subcarpeta != null) {
            for (int i = 0; i < subcarpeta.length; i++) {
                File file = new File(carpeta.getAbsolutePath() + SEP + subcarpeta[i]);
                if (!file.getName().substring(0, 1).equals(".")) {
                    if (file.isDirectory()) {
                        javax.swing.tree.DefaultMutableTreeNode rootd = new javax.swing.tree.DefaultMutableTreeNode(subcarpeta[i]);
                        rootd.add(new javax.swing.tree.DefaultMutableTreeNode(file.getName()));
                        root.add(rootd);
                        nCarpetas++;
                    } else {
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

    public File openfile() {
        //aqui no espera como un dialog, tener cuidado
        return fileList[0];
    }

    public File[] openfiles() {
        //aqui no espera como un dialog, tener cuidado
        return fileList;
    }

}
