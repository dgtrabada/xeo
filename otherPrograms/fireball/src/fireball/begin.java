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

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class begin {
    String HOME;
    String SEP;
    ArrayList<String>  g2d = new ArrayList();
    File pathPeridictable;
    ArrayList<String>  periodicTable = new ArrayList<String> ();
    reader.reader cadena = new reader.reader();
    int Z;
    String name;
    String Simbol;
    double Mass;
    int NShell;
    double rs,rp,rd,rf;
    double ns,np,nd,nf;
    boolean  C_s,C_p,C_d,C_f;
    double overD;
    
    public begin() {
        overD=0.00001;
        C_s=false;
        C_p=false;
        C_d=false;
        C_f=false;
        rs=0.0;
        rp=0.0;
        rd=0.0;
        rf=0.0;
        ns=0.0;
        np=0.0;
        nd=0.0;
        nf=0.0;
        NShell=0;
        SEP = System.getProperty("file.separator");
        HOME=System.getProperty("user.home")+SEP;
        if(!new File(HOME+SEP+".xeorc"+SEP+"xeo.ini").exists()) HOME=System.getProperty("user.dir")+SEP;
    }
    
    
    void loadPeriodictable(){
        
        if(pathPeridictable.exists()) {
            periodicTable = cadena.readFileArrayList(pathPeridictable);
            for(int i=0;i<periodicTable.size();i++){
                // System.out.println(periodicTable.elementAt(i));
                if(cadena.nCol(periodicTable.get(i))==2){
                    int a = cadena.readColInt(1,periodicTable.get(i));
                    int b = Z;// (int) Double.valueOf(Z.getText()).doubleValue();
                    if( a == Z){
                        // System.out.println(periodicTable.get(i));
                        Z=cadena.readColInt(1,periodicTable.get(i));
                        Simbol=cadena.readColString(2,periodicTable.get(i));
                        i++;name=cadena.readColString(1,periodicTable.get(i));
                        i++;Mass=cadena.readColDouble(1,periodicTable.get(i));
                        i++;
                        rs=cadena.readColDouble(1,periodicTable.get(i));
                        rp=cadena.readColDouble(2,periodicTable.get(i));
                        rd=cadena.readColDouble(3,periodicTable.get(i));
                        rf=cadena.readColDouble(4,periodicTable.get(i));
                        i++;
                        if(cadena.readColInt(1,periodicTable.get(i))!=0) {
                            NShell++;
                            C_s=true;
                        }
                        
                        if(cadena.readColInt(2,periodicTable.get(i))!=0){
                            NShell++;
                            C_p=true;
                        }
                        if(cadena.readColInt(3,periodicTable.get(i))!=0){
                            C_d=true;
                            NShell++;
                        }
                        if(cadena.readColInt(4,periodicTable.get(i))!=0){
                            C_f=true;
                            NShell++;
                        }
                        
                        ns=cadena.readColDouble(1,periodicTable.get(i));
                        np=cadena.readColDouble(2,periodicTable.get(i));
                        nd=cadena.readColDouble(3,periodicTable.get(i));
                        nf=cadena.readColDouble(4,periodicTable.get(i));
                        i=periodicTable.size();
                    }
                }
            }
        }
    }
    
    ArrayList<String> LoadArraList(javax.swing.JTextArea INFO,boolean ajustar_maximos){
        int nS=2;
        g2d.clear();
        g2d.add("..."); //protocolo
        for(int i=1;i<=INFO.getLineCount();i++)
            if(!cadena.readColString(1,cadena.readLine(i,INFO.getText())).equals("0.0"))
                DOcol(cadena.readColString(1,cadena.readLine(i,INFO.getText())),nS++,ajustar_maximos,cadena.readColDouble(2,cadena.readLine(i,INFO.getText())));
        return g2d;
    }
    
    void DOcol(String file, int i,boolean ajustar_maximos,double D){
        //    System.out.println(file);
        int N=0;
        double Rc=0;
        String fileOut=HOME+".xeorc/temp/col"+i;
        if(file.indexOf("wf")!=-1) {
            if(ajustar_maximos)
                try{
                    java.io.FileOutputStream archivo = new java.io.FileOutputStream(new File(fileOut)) ;
                    java.io.DataOutputStream in = new java.io.DataOutputStream(archivo);
                    java.io.FileReader inFile = new java.io.FileReader(file);
                    java.io.LineNumberReader inLines = new java.io.LineNumberReader(inFile);
                    String str="";
                    inLines.readLine();//titulo
                    inLines.readLine();
                    N=(int) Double.valueOf(inLines.readLine()).doubleValue();
                    Rc= cadena.readColDouble(1,inLines.readLine());
                    inLines.readLine(); //L = 0 1 2 3
                    int j=0;
                    double x=0;
                    while ((str=inLines.readLine()) != null) {
                        for(int k = 1; k<=4;k++){
                            if(D<overD) x=j*Rc/N;
                            else x=D-j*Rc/N;
                            j++;
                            in.writeBytes(cadena.noD(x+"")+"\t"+cadena.noD(cadena.readColString(k,str))+"\n");
                            // System.out.println(cadena.noD(y));
                        }
                    }
                    inFile.close();
                    inLines.close();
                    in.close();
                    archivo.close();
                }catch(Exception erx){System.out.println("error read "+file ); }
            g2d.add(fileOut+" 1 "+fileOut+" 2 "+i+" "+new File(file).getName());  //archivo_x colx archivo_y coly color titulo
        }
        if(file.indexOf("na")!=-1) {
            if(ajustar_maximos)
                try{
                    java.io.FileOutputStream archivo = new java.io.FileOutputStream(new File(fileOut)) ;
                    java.io.DataOutputStream in = new java.io.DataOutputStream(archivo);
                    java.io.FileReader inFile = new java.io.FileReader(file);
                    java.io.LineNumberReader inLines = new java.io.LineNumberReader(inFile);
                    String str="";
                    inLines.readLine();//titulo
                    inLines.readLine();
                    Rc= cadena.readColDouble(1,inLines.readLine());
                    inLines.readLine();
                    if(file.indexOf("na0")!=-1) inLines.readLine();
                    String x="";
                    while ((str=inLines.readLine()) != null) in.writeBytes(cadena.noD(cadena.readColString(1,str))+"\t"+cadena.noD(cadena.readColString(2,str))+"\n");
                    inFile.close();
                    inLines.close();
                    in.close();
                    archivo.close();
                }catch(Exception erx){System.out.println("error read "+file ); }
            g2d.add(fileOut+" 1 "+fileOut+" 2 "+i+" "+new File(file).getName());  //archivo_x colx archivo_y coly color titulo
        }
        if(file.indexOf("pp")!=-1) {
            try{
                int iPP=0;
                java.io.FileOutputStream archivo = null;
                java.io.DataOutputStream in = null ;
                java.io.FileReader inFile = new java.io.FileReader(file);
                java.io.LineNumberReader inLines = new java.io.LineNumberReader(inFile);
                String str="",title="";
                inLines.readLine();
                inLines.readLine();
                inLines.readLine();
                inLines.readLine();
                inLines.readLine();
                title= cadena.readColString(3,inLines.readLine())+" ";
                inLines.readLine();//        nuclear charge  14.00
                inLines.readLine();//         total charge   0.00
                inLines.readLine();  // number of core states   3
                inLines.readLine();// number of valence states   2
                inLines.readLine();// exchange-correlation model   3    0.00  LDA CA Perdew/Zunger 1980
                inLines.readLine();// scalar-relativistic mode
                inLines.readLine();// parameters radial mesh   495    1.024700  0.446429E-03
                inLines.readLine();// !!!
                inLines.readLine();// 3   0.00                            ! iexc , exmix
                inLines.readLine();// 2                                   ! nshells
                inLines.readLine();//  0    1                              ! L values
                inLines.readLine();//  4.00000000                          ! Zval
                inLines.readLine();//  2.65767622                          ! alpha
                iPP++; fileOut=HOME+".xeorc/temp/col_pp_"+i+"_"+iPP;
                archivo = new java.io.FileOutputStream(new File(fileOut)) ;
                in = new java.io.DataOutputStream(archivo);
                inLines.readLine();//Rc= Double.valueOf(cadena.readCol(1,inLines.readLine())).doubleValue();//   1.6000000                          ! Rcut_PP
                N =cadena.readColInt(1,inLines.readLine()); //372
                String x="";
                for(int j=1;j<=N;j++){
                    str=inLines.readLine();
                    in.writeBytes(cadena.noD(cadena.readColString(1,str))+"\t"+cadena.noD(cadena.readColString(2,str))+"\n");
                }
                g2d.add(fileOut+" 1 "+fileOut+" 2 "+iPP+" "+title.trim());
                in.close();
                archivo.close();
                boolean write=false;
                int line=0;
                String L="";
                while ((str=inLines.readLine()) != null) {
                    if(str.indexOf("L=")>0){
                        L=cadena.readColString(1,str);
                        write=true;
                        N =cadena.readColInt(3,str);
                        iPP++; fileOut=HOME+".xeorc/temp/col_pp_"+i+"_"+iPP;
                        archivo = new java.io.FileOutputStream(new File(fileOut)) ;
                        in = new java.io.DataOutputStream(archivo);
                        line=0;
                    } else if(write){
                        line++;
                        in.writeBytes(cadena.noD(cadena.readColString(1,str))+"\t"+cadena.noD(cadena.readColString(2,str))+"\n");
                        if(line==N){
                            write = false;
                            in.close();
                            archivo.close();
                            g2d.add(fileOut+" 1 "+fileOut+" 2 "+iPP+" "+title.trim()+"_"+L);  //archivo_x colx archivo_y coly color titulo
                        }
                    }
                }
            }catch(Exception erx){System.out.println("error read "+file ); }
        }
        
    }
    
}
