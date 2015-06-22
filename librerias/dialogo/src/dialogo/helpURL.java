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

public class helpURL extends javax.swing.JFrame {

    public helpURL(java.net.URL pagina) {
        initComponents();
        edit.addHyperlinkListener(
                new javax.swing.event.HyperlinkListener() {
                    public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent e) {
                        if (e.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {
                            try {
                                edit.setPage(new java.net.URL("file:" + e.getURL().getPath()));
                            } catch (Exception ex) {
                                System.out.println("error loading help");
                            }

                        }
                    }
                }
        );
        setSize(720, 640);
        try {
            edit.setPage(pagina);
        } catch (Exception ex) {
            System.out.println("error loading help URL");
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        edit = new javax.swing.JEditorPane();

        setTitle("help");
        edit.setEditable(false);
        jScrollPane1.setViewportView(edit);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Declaración de variables - no modificar//GEN-BEGIN:variables
    private javax.swing.JEditorPane edit;
    private javax.swing.JScrollPane jScrollPane1;
    // Fin de declaración de variables//GEN-END:variables

}
