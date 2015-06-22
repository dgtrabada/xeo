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

package calc_xyz;
import java.io.File;

public class Calc_diff_xyz extends javax.swing.JFrame {
    reader.reader cadena = new reader.reader();
    Calc_bas cal = new Calc_bas();
    String path;
    String SEP = System.getProperty("file.separator");
    java.awt.Font fuente,fuenteMono;
    public Calc_diff_xyz( java.awt.Font f, java.awt.Font fMono) {
        fuente=f;
        fuenteMono=fMono;
        initComponents();
        this.setVisible(true);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Readme = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        CalcZ = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CalcX = new javax.swing.JTextField();
        CalcY = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        n_interpol = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputText = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(fuenteMono);
        jTextArea1.setRows(5);
        jTextArea1.setText("\n    All the files with the same number of atoms.\n \nInput:  \n    //------------------\n    file0 step1\n    file0 step2\n    //------------------\n\nx[1]-x[2] = diference betwen step 1 and step 2 \n\n    \nGeneral Input \n   //------------------\n    file1 step1\n    file2 step2\n    file3 step3\n    . \n    . \n    .\n    filen step4\n   //------------------\n   x[1]-y[3] = Xpositions of atoms in file1 at step = step1\n                      minus Ypositions of atoms in file3 at step = step1\n   //-----------------\n\nstep = 1 (default)    ");
        jScrollPane2.setViewportView(jTextArea1);

        org.jdesktop.layout.GroupLayout ReadmeLayout = new org.jdesktop.layout.GroupLayout(Readme.getContentPane());
        Readme.getContentPane().setLayout(ReadmeLayout);
        ReadmeLayout.setHorizontalGroup(
            ReadmeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
        );
        ReadmeLayout.setVerticalGroup(
            ReadmeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );

        setTitle("calculator");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        outputText.setColumns(20);
        outputText.setFont(fuenteMono);
        outputText.setRows(5);
        outputText.setBorder(null);
        jScrollPane3.setViewportView(outputText);

        jButton4.setFont(fuente);
        jButton4.setText("save as");
        jButton4.setAutoscrolls(true);
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });

        CalcZ.setFont(fuente);
        CalcZ.setText("z[0]");
        CalcZ.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel5.setFont(fuente);
        jLabel5.setText("Z =");
        jLabel5.setAutoscrolls(true);
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel4.setFont(fuente);
        jLabel4.setText("Y =");
        jLabel4.setAutoscrolls(true);
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel3.setFont(fuente);
        jLabel3.setText("X =");
        jLabel3.setAutoscrolls(true);
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        CalcX.setFont(fuente);
        CalcX.setText("x[0]");
        CalcX.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        CalcY.setFont(fuente);
        CalcY.setText("y[0]");
        CalcY.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jButton2.setFont(fuente);
        jButton2.setText("calulate");
        jButton2.setAutoscrolls(true);
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        n_interpol.setFont(fuente);
        n_interpol.setText("5");
        n_interpol.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jLabel2.setFont(fuente);
        jLabel2.setText("interpolate   file1(step1) to file2(step2) with :");
        jLabel2.setAutoscrolls(true);
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        jButton6.setFont(fuente);
        jButton6.setText("help");
        jButton6.setAutoscrolls(true);
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });

        jLabel1.setFont(fuente);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("example:  file1(step1) - file2(step2)");
        jLabel1.setAutoscrolls(true);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel1.setVerifyInputWhenFocusTarget(false);

        jButton5.setFont(fuente);
        jButton5.setText("...");
        jButton5.setAutoscrolls(true);
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });

        jButton9.setFont(fuente);
        jButton9.setText("...");
        jButton9.setAutoscrolls(true);
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton9MousePressed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));

        inputText.setColumns(20);
        inputText.setFont(fuente);
        inputText.setRows(2);
        inputText.setBorder(null);
        jScrollPane1.setViewportView(inputText);

        jButton1.setFont(fuente);
        jButton1.setText("add file");
        jButton1.setAutoscrolls(true);
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.shadow")));
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 234, Short.MAX_VALUE)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel4)
                            .add(jLabel3)
                            .add(jLabel5))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, CalcZ, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .add(CalcY, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, CalcX, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jButton6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 267, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(n_interpol, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jButton9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton5)
                    .add(jLabel1)
                    .add(jButton6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton9)
                    .add(n_interpol, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(CalcX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(CalcY, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(CalcZ, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton4)
                    .add(jButton2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        new dialogo.helpURL(getClass().getResource("/help/calc_xyz.html")).setVisible(true);
    }//GEN-LAST:event_jButton6MousePressed
    
    private void jButton9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MousePressed
        interpolate((int) Double.valueOf(n_interpol.getText()).doubleValue());
    }//GEN-LAST:event_jButton9MousePressed
    
    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        File auxfile = fileChoose("Save *.bas","save",path) ;
        if(auxfile != null) saveFile(auxfile);
    }//GEN-LAST:event_jButton4MousePressed
    
    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        CalcX.setText("x[0]-x[1]");
        CalcY.setText("y[0]-y[1]");
        CalcZ.setText("z[0]-z[1]");
        calcular();
    }//GEN-LAST:event_jButton5MousePressed
    
    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        calcular();
    }//GEN-LAST:event_jButton2MousePressed
    
    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        File newFile = fileChoose("Open *.bas","open",path+SEP+".") ;
        if(newFile!=null) inputText.setText(inputText.getText()+newFile.getAbsolutePath()+" 1 \n");
    }//GEN-LAST:event_jButton1MousePressed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CalcX;
    private javax.swing.JTextField CalcY;
    private javax.swing.JTextField CalcZ;
    private javax.swing.JFrame Readme;
    private javax.swing.JTextArea inputText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField n_interpol;
    private javax.swing.JTextArea outputText;
    // End of variables declaration//GEN-END:variables
    
    void calcular(){
        outputText.setText(cal.calcular(CalcX.getText(),CalcY.getText(),CalcZ.getText(),inputText.getText()));
    }
    
//--Interpolate, with the 2 first files
    void interpolate(int n){
        n=(n<1)?1:n;
        outputText.setText("  ");
        boolean error = false;
        String file1=cadena.readLine(1,inputText.getText()).trim();
        int step_1=(int) Double.valueOf(file1.substring(file1.lastIndexOf(" "),file1.length())).doubleValue();
        file1=file1.substring(0,file1.lastIndexOf(" ")).trim();
        if(!(new File(file1)).exists()) error=true;
        String file2=cadena.readLine(2,inputText.getText()).trim();
        int step_2=(int) Double.valueOf(file2.substring(file2.lastIndexOf(" "),file2.length())).doubleValue();
        file2=file2.substring(0,file2.lastIndexOf(" ")).trim();
        if(!(new File(file2)).exists()) error=true;
        if(!error){
            try{
                String str_1="",str_2="",ret="";
                int natom=0;
                double xout_1=0,yout_1=0,zout_1=0;
                double xout_2=0,yout_2=0,zout_2=0;
                for(int i=0;i<=n;i++){
                    java.io.BufferedReader inbas_1,inbas_2;
                    inbas_1 = new java.io.BufferedReader(new java.io.FileReader(file1));
                    inbas_2 = new java.io.BufferedReader(new java.io.FileReader(file2));          
                    str_1 = inbas_1.readLine(); //tomamos el numero de atomos de la primera
                    inbas_2.readLine();
                    inbas_1.readLine();
                    inbas_2.readLine();
                    natom =cadena.readColInt(1,str_1);
                    ret+="  "+natom+"\n";
                    ret+="\n";
                    
                    for(int j=0;j<(step_1-1)*(natom+2);j++)
                        inbas_1.readLine();
                    for(int j=0;j<(step_2-1)*(natom+2);j++)
                        inbas_2.readLine();
                    
                    for(int k=0;k<natom;k++){
                        str_1 = inbas_1.readLine();
                        str_2 = inbas_2.readLine();
                        try{
                            xout_1=cadena.readColDouble(2,str_1);
                            yout_1=cadena.readColDouble(3,str_1);
                            zout_1=cadena.readColDouble(4,str_1);
                            xout_2=cadena.readColDouble(2,str_2);
                            yout_2=cadena.readColDouble(3,str_2);
                            zout_2=cadena.readColDouble(4,str_2);
                        }catch(NumberFormatException ex){System.out.println("error read bas");}
                        //----------------------------------------
                        // cadena.format(4,periodicTable.getSymbol(Zout_1))
                        ret+=cadena.format(4,cadena.readColString(1,str_1))
                        + cadena.formatFortran(2,12,6,i*(xout_2-xout_1)/n+xout_1) +
                                cadena.formatFortran(2,12,6,i*(yout_2-yout_1)/n+yout_1)+cadena.formatFortran(2,12,6,i*(zout_2-zout_1)/n+zout_1)+"\n";
                    }      
                    inbas_1.close();
                    inbas_2.close();
                    outputText.setText(ret);
                }
            }catch (java.io.IOException oe) {System.out.println("error read xyz");}
        }
    }
    
    
    void saveFile(File auxFile){
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(auxFile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(outputText.getText());
            out.close();
            archivo.close();
        }catch (java.io.IOException oe) {System.out.println("error en editor: saveFile " );}
    }
    File fileChoose(String title,String boton, String file_ini){
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        fc.setName(title);
        if(new File(file_ini).exists()) fc.setSelectedFile(new File(file_ini));
        //fc.setSelectedFile(aux);
        int returnVal = fc.showDialog( Calc_diff_xyz.this , boton);
        File auxfile = null;
        if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION){
            auxfile = fc.getSelectedFile();
        }
        return auxfile;
    }
    
    
    
//-------------busqueda binaria
    
    double binario(int i,int i_ini, int i_fin,double  x_ini ,double x_fin){
        double dj = (x_ini+x_fin)*0.5; //esto lo cambiaremos pero seguira lo que hace j (busqueda binaria)
        int j=(i_ini+i_fin)/2;   //esto me da la por donde voy , el otro es ciego y carece de criterio
        if(i==i_ini)
            dj=x_ini;
        else{
            if(i==i_fin)
                dj=x_fin;
            else{
                if(i<j)dj=binario(i,i_ini,j,x_ini,dj);
                if(i>j)dj=binario(i,j,i_fin,dj,x_fin);
            }
        }
        return dj;
    }
    //------------- ejemplo de salida ------------------------
//0  0  4  0  41.930148825091486 -> 0.0
//1  0  4  0  41.930148825091486 -> 10.482537206272871
//2  0  4  0  41.930148825091486 -> 20.965074412545743
//3  0  4  0  41.930148825091486 -> 31.447611618818613
//4  0  4  0  41.930148825091486 -> 41.930148825091486
    
}
