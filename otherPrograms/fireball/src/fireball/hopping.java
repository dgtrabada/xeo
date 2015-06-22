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

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Vector;


public class hopping {
    reader.reader cadena = new reader.reader() ;
    double overtol=1.0E-10;
    File inputfile;
    int nhop;
    double A,alfa,W,corte;
    Vector vectorHopping = new Vector();
    Vector vectorAjuste = new Vector();
    int X_mouse_ini;
    int X_mouse_fin;
    int Y_mouse_ini;
    int Y_mouse_fin;
    javax.swing.JTextArea textArea;
    //--planos paralelos---
    int NEnergias,NTOT;
    double Eini,Efin;
    double ihop=0,iE=0;
    double hopAjuste_min=0,hopAjuste_max=0,ihopAjuste=0,Fmax=0,Fmin=0;
    boolean logScale;
    boolean CheckFireball;
    boolean CheckFormula;
    boolean CheckMix;
    boolean Selected;
    boolean PintarTODOS;
    int sX;
    int sY;
    javax.swing.ImageIcon icon;
    /** Creates a new instance of hopping */
    public hopping() {
        inputfile=null;
        A=0;
        alfa=0;
        W=0;
        corte=0;
        logScale=false;
        CheckFireball=false;
        CheckFormula=false;
        CheckMix=false;
        Selected=false;
        PintarTODOS=false;
        X_mouse_ini=0;
        X_mouse_fin=0;
        Y_mouse_ini=0;
        Y_mouse_fin=0;
    }
    
    
    void LoadHopping(){
        String str="",lastLine="";
        if (inputfile.exists()) {
            try{
                java.io.BufferedReader inFile = new java.io.BufferedReader(new java.io.FileReader(inputfile.getAbsolutePath()));
                java.io.LineNumberReader inLines = new java.io.LineNumberReader(inFile);
                NEnergias=-4;
                while ((str = inFile.readLine()) != null) {
                    lastLine=str;
                    NEnergias++;
                }
                NTOT=(int) Double.valueOf(cadena.nCol(lastLine)).doubleValue();
                NTOT--;
                NEnergias-=NTOT;
                // System.out.println("NTOT "+NTOT+", Nener = " + NEnergias);
                inFile.close();
                inLines.close();
            }catch (IOException oe) {System.out.println("hay error/es LoadHopping");}
            try{
                Vector vectorAux1 = new Vector();
                Vector vectorAux2 = new Vector();
                java.io.BufferedReader inFile = new java.io.BufferedReader(new java.io.FileReader(inputfile.getAbsolutePath()));
                java.io.LineNumberReader inLines = new java.io.LineNumberReader(inFile);
                String aux=inFile.readLine()+"\n";
                aux+=inFile.readLine()+"\n";
                aux+=inFile.readLine()+"\n";
                aux+=inFile.readLine()+"\n";
                textArea = new   javax.swing.JTextArea(aux,0,0);
                for(int n=1;n<=NTOT;n++)  vectorAux1.addElement(inFile.readLine());
                for(int n=1;n<=NEnergias;n++) vectorAux2.addElement(inFile.readLine());
                inFile.close();
                inLines.close();
                vectorHopping = vectorAux2 ;
                vectorAjuste = vectorAux1 ;
            }catch (IOException oe) {System.out.println("hay error/es E2 en el tip");}
        }
    }
    
    
    void MaximosE(){
        for(int n=0;n<vectorHopping.size();n++){
            iE=cadena.readColDouble(1,vectorHopping.elementAt(n).toString());
            if(n==0) {
                Efin=iE;
                Eini=iE;
            } else{
                Eini=(Eini>iE)?iE:Eini;
                Efin=(Efin<iE)?iE:Efin;
            }
        }
    }
    
    
    void MaximosF(int i){
        for(int n=0;n<vectorHopping.size();n++){
            iE=cadena.readColDouble(1,vectorHopping.elementAt(n).toString());
            ihop=cadena.readColDouble(i+2,vectorHopping.elementAt(n).toString());
            if(PintarTODOS) LoadParametrosAjuste(i); //i.e nhop
            ihopAjuste=A/Math.pow(iE,alfa)*Math.exp(-iE*W);
            if(logScale){
                ihop=(Math.abs(ihop)<overtol)?overtol:ihop;
                ihopAjuste=(Math.abs(ihopAjuste)<overtol)?overtol:ihopAjuste;
                ihop=Math.log(Math.abs(ihop));
                ihopAjuste=Math.log(Math.abs(ihopAjuste));
            }
            if(n==1) {
                if(CheckFireball|CheckMix) {
                    Fmin=ihop;
                    Fmax=ihop;
                }
                if(CheckFormula){
                    Fmin=ihopAjuste;
                    Fmax=ihopAjuste;
                }
                if(CheckFireball&&CheckFormula) {
                    Fmin=(Fmin<ihop)?Fmin:ihop;
                    Fmax=(Fmax>ihop)?Fmax:ihop;
                }
            } else{
                if(CheckFireball|(CheckMix&iE<corte)) {
                    Fmin=(Fmin<ihop)?Fmin:ihop;
                    Fmax=(Fmax>ihop)?Fmax:ihop;
                }
                if(CheckFormula|(CheckMix&iE>corte)){
                    Fmin=(Fmin<ihopAjuste)?Fmin:ihopAjuste;
                    Fmax=(Fmax>ihopAjuste)?Fmax:ihopAjuste;
                }
            }
        }
    }
    
    void ajustarMaximos(){
        MaximosE();
        if(PintarTODOS){
            double max=0,min=0;
            for(int n=0;n<NTOT;n++) {
                MaximosF(n);
                if(n==0){
                    max=Fmax;
                    min=Fmin;
                } else{
                    max=(max>Fmax)?max:Fmax;
                    min=(min<Fmin)?min:Fmin;
                }
            }
            Fmax=max;
            Fmin=min;
        }else MaximosF(nhop);
    }
    
    void LoadParametrosAjuste(int i){
        String aux=vectorAjuste.elementAt(i).toString();
        A =  cadena.readColDouble(4,aux);
        alfa =  cadena.readColDouble(5,aux);
        W = cadena.readColDouble(6,aux);
        corte = cadena.readColDouble(9,aux);
    }
    
    
    void Planos_paralelos(){
        int Xint,Yint,XintOld=0,YintOld=0,YintMix=0,YintMixOLd=0;
        BufferedImage salidaBuffer;
        salidaBuffer = new BufferedImage(sX,sY, BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics salidaGraphics = salidaBuffer.createGraphics();
        //--- Fondo estandar en Blanco  ----
        salidaGraphics.setColor(Color.WHITE);
        salidaGraphics.fillRect(0,0,sX ,sY);
        //--- x (40,20) y(10,20)  marco estandar ---
        sX=sX-60;
        sY=sY-30;
        //----------------------------------------------*********
        
        for(int i=2;i<(NTOT+2);i++){  //int i=nhop+2;
            for(int n=0;n<vectorHopping.size();n++){
                if(PintarTODOS|i==(nhop+2)){
                    iE=cadena.readColDouble(1,vectorHopping.elementAt(n).toString());
                    ihop=cadena.readColDouble(i,vectorHopping.elementAt(n).toString());
                    if(PintarTODOS) LoadParametrosAjuste(i-2); //i.e nhop
                    ihopAjuste=A/Math.pow(iE,alfa)*Math.exp(-iE*W);
                    if(logScale){
                        ihop=(Math.abs(ihop)<overtol)?overtol:ihop;
                        ihopAjuste=(Math.abs(ihopAjuste)<overtol)?overtol:ihopAjuste;
                        ihop=Math.log(Math.abs(ihop));
                        ihopAjuste=Math.log(Math.abs(ihopAjuste));
                    }
                    
                    Xint = (int) (40+sX*(iE-Eini)/(Efin-Eini));
                    Yint = (int) (sY+10-sY*(ihop-Fmin)/(Fmax-Fmin));
                    int Fy2= (int) (sY+10-sY*(ihopAjuste-Fmin)/(Fmax-Fmin));
                    if(iE<corte) YintMix=Yint;
                    else YintMix=Fy2;
                    salidaGraphics.setColor(Color.BLUE);
                    if(CheckFireball) salidaGraphics.fillRect(Xint,Yint,2,2);
                    if(n>1&&CheckFireball) salidaGraphics.drawLine(Xint,Yint,XintOld,YintOld);
                    salidaGraphics.setColor(Color.GREEN);
                    if(n>1&&CheckMix) salidaGraphics.drawLine(Xint,YintMix,XintOld,YintMixOLd);
                    salidaGraphics.setColor(Color.RED);
                    if(CheckFormula)salidaGraphics.fillRect(Xint,Fy2,2,2);
                    XintOld=Xint;
                    YintMixOLd=YintMix;
                    YintOld=Yint;
                }
            }
        }
        
        salidaGraphics.setColor(Color.GREEN);
        int xchop = (int) (40+sX*(corte-Eini)/(Efin-Eini));
        int ychop =0;
        if(logScale) ychop = (int) (sY+10-sY*(Math.log(Math.abs(A/Math.pow(corte,alfa)*Math.exp(-corte*W)))-Fmin)/(Fmax-Fmin));
        else ychop = (int) (sY+10-sY*(A/Math.pow(corte,alfa)*Math.exp(-corte*W)-Fmin)/(Fmax-Fmin));
        salidaGraphics.drawOval(xchop-2,ychop-2,4,4);
        
        //chan-yaki
        salidaGraphics.setColor(Color.WHITE);
        salidaGraphics.fillRect(0,0,40 ,sY+30);
        salidaGraphics.fillRect(40+sX,0,40+sX+20 ,sY+30);
        salidaGraphics.fillRect(0,0,sX+60,10);
        salidaGraphics.fillRect(0,sY+10,sX+60,sY+30);
        
        //---- pintamos ejes y salida con un icono ----
        
        salidaGraphics.setFont(new java.awt.Font("Curier",java.awt.Font.BOLD,9));
        salidaGraphics.setColor(Color.BLACK);
        
        salidaGraphics.drawLine(40,sY+15,40,10);
        salidaGraphics.drawString(cadena.pasarString(Fmin),2,sY+10);
        salidaGraphics.drawLine(40,sY+15-sY/4,35,sY+15-sY/4);
        salidaGraphics.drawString(cadena.pasarString(Fmin+1*(Fmax-Fmin)/4),2,sY+15-sY/4);
        salidaGraphics.drawLine(40,sY+15-2*sY/4,35,sY+15-2*sY/4);
        salidaGraphics.drawString(cadena.pasarString(Fmin + 2*(Fmax-Fmin)/4),2,sY+15-2*sY/4);
        salidaGraphics.drawLine(40,sY+15-3*sY/4,35,sY+15-3*sY/4);
        salidaGraphics.drawString(cadena.pasarString(Fmin + 3*(Fmax-Fmin)/4),2,sY+15-3*sY/4);
        salidaGraphics.drawLine(40,sY+15-sY,35,sY+15-sY);
        salidaGraphics.drawString(cadena.pasarString(Fmax),2,sY+15-sY);
        
        salidaGraphics.drawLine(35,sY+10,sX+40,sY+10);
        salidaGraphics.drawString(cadena.pasarString(Eini),30,sY+25);
        salidaGraphics.drawLine(sX/4+40,sY+15,sX/4+40,sY+10);
        salidaGraphics.drawString(cadena.pasarString(Eini+(Efin-Eini)/4),sX/4+30,sY+25);
        salidaGraphics.drawLine(sX/2+40,sY+15,sX/2+40,sY+10);
        salidaGraphics.drawString(cadena.pasarString(Eini+(Efin-Eini)/2),sX/2+30,sY+25);
        salidaGraphics.drawLine(3*sX/4+40,sY+15,3*sX/4+40,sY+10);
        salidaGraphics.drawString(cadena.pasarString(Eini+3*(Efin-Eini)/4),3*sX/4+30,sY+25);
        salidaGraphics.drawLine(sX+40,sY+15,sX+40,sY+10);
        salidaGraphics.drawString(cadena.pasarString(Efin),sX+30,sY+25);
        
        if(Selected){
            int a=(X_mouse_ini<X_mouse_fin)?X_mouse_ini:X_mouse_fin;
            int b=(Y_mouse_ini<Y_mouse_fin)?Y_mouse_ini:Y_mouse_fin;
            salidaGraphics.setColor(new Color(0,150,255,90));
            salidaGraphics.drawRect(a,b,(int) Math.abs(X_mouse_ini-X_mouse_fin),(int) Math.abs(Y_mouse_ini-Y_mouse_fin));
            salidaGraphics.setColor(new Color(0,150,255,50));
            salidaGraphics.fillRect(a,b,(int) Math.abs(X_mouse_ini-X_mouse_fin),(int) Math.abs(Y_mouse_ini-Y_mouse_fin));
        }
        
        icon = new  javax.swing.ImageIcon(salidaBuffer);
        //---------------------------------------------
    }
    
    void LoadA(int fin){
        iE=cadena.readColDouble(1,vectorHopping.elementAt(fin).toString());
        ihop=cadena.readColDouble(nhop+2,vectorHopping.elementAt(fin).toString());
        A=ihop*Math.pow(iE,alfa)*Math.exp(iE*W);
        corte=iE;
    }
    
    void lupa2D(){
        //Xint = (int) (40+sX*(iE-Eini)/(Efin-Eini));
        // Yint = (int) (sY+10-sY*(ihop-Fmin)/(Fmax-Fmin));
        double x_min_aux =  (X_mouse_ini-40)*1.0*(Efin-Eini)/sX+Eini;
        double x_max_aux =  (X_mouse_fin-40)*1.0*(Efin-Eini)/sX+Eini;
        double y_min_aux = -(Y_mouse_ini-10-sY)*1.0*(Fmax-Fmin)/sY+Fmin;
        double y_max_aux =  -(Y_mouse_fin-10-sY)*1.0*(Fmax-Fmin)/sY+Fmin;
        Eini=(x_min_aux<x_max_aux)?x_min_aux:x_max_aux;
        Efin=(x_min_aux>x_max_aux)?x_min_aux:x_max_aux;
        Fmin=(y_min_aux<y_max_aux)?y_min_aux:y_max_aux;
        Fmax=(y_min_aux>y_max_aux)?y_min_aux:y_max_aux;
    }
    
    void SAVE(String AS,String alfaS, String WS, String cutS ){
        for(int n=0;n<vectorAjuste.size();n++) {
            if(n!=nhop) textArea.append(vectorAjuste.elementAt(n).toString() + "\n");
            else{
                String aux="";
                aux=cadena.SubtStrig(vectorAjuste.elementAt(n).toString(),AS,4);
                aux=cadena.SubtStrig(aux,alfaS,5);
                aux=cadena.SubtStrig(aux,WS,6);
                aux=cadena.SubtStrig(aux,cutS,9);
                textArea.append(aux+"\n");
            }
        }
        for(int n=0;n<vectorHopping.size();n++)  textArea.append(vectorHopping.elementAt(n).toString() + "\n");
        try{
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile.getAbsoluteFile());
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(textArea.getText());
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error en editor: saveFile " );}
    }
    
}
