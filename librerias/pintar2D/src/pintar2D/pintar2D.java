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

import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.awt.Color;
import reader.reader;

public class pintar2D  {
    javax.swing.JLabel label = new javax.swing.JLabel("class pintar2D by dani");
    javax.swing.ImageIcon icon;
    public Options2DPlot opt = new Options2DPlot();
    public File inputfile2D;  //we don't use this file here only to remembered
    double col_max;
    double col_min;
    int ox;
    int oy;
    public BufferedImage imageBuffered;
    java.awt.Graphics imageGraphics;
    
    //------------
    public ArrayList<String> g2d = new ArrayList();
    //----------------
    int X_mouse_ini;
    int X_mouse_fin;
    int Y_mouse_ini;
    int Y_mouse_fin;
    public boolean mouse;
    public boolean firstTime;
    //----------------
    int ancho,anchoC,ac;
    boolean seeLine,seeCircle;
    //----------------
    public pintar2D() {
        col_max=1;col_min=0;
        ox=300;
        oy=200;
        X_mouse_ini=X_mouse_fin=Y_mouse_ini=Y_mouse_fin=0;
        mouse=false;
        firstTime=true;
        ancho=1;
    }
    
//--------------mouse--------
    public void mouseIni(int mx,int my,boolean mo){
        X_mouse_ini=mx;
        Y_mouse_ini=my;
        mouse=mo;
    }
    public void mouseFin(int mx,int my,boolean mo){
        X_mouse_fin=mx;
        Y_mouse_fin=my;
        mouse=mo;
    }
    
//------------ Ventanas --------------------------------------------
    public void plotNY(File fileIN){
        inputfile2D=fileIN;
        imageBuffered = new BufferedImage(ox,oy, BufferedImage.TYPE_INT_RGB);
        try{
            FileOutputStream archivo_ant = new FileOutputStream("x.temp");
            DataOutputStream archivo = new DataOutputStream(archivo_ant);
            String str="";
            int N=opt.cadena.numeroFilas(fileIN);
            for(int j=1;j<=N;j++) archivo.writeBytes(j+"\n");
            archivo.close();
            archivo_ant.close();
        }catch (IOException oe) {System.out.println("hay error/es E10");}
        //----clock---------
        long start = System.currentTimeMillis();// (milisegundos)
        long elapsed = System.currentTimeMillis() - start;
        while(! fileIN.exists() && elapsed<60000) elapsed = System.currentTimeMillis() - start;
        //-----------------
        // ;)
        g2d.clear();
        g2d.add("x.temp"+" "+1+" "+1+" "+"x.temp");
        int NCol=opt.cadena.numeroCol(fileIN);
        for(int k=1;k<=NCol;k++) g2d.add(fileIN.getAbsolutePath()+" "+k+" "+k+" "+"col.temp");
        Max_diff_files_XY();
        ordenarMAX();
        show_diff_files_XNY();
        new dialogo.show_picture(fileIN.getAbsolutePath()).plot(imageBuffered);
        new File("x.temp").delete();
    }
    
    public void plotNY(File [] fileIN){
        inputfile2D=fileIN[0];
        //   for(int i=0;i<fileIN.length;i++) System.out.println(fileIN[i].getAbsolutePath());
        imageBuffered = new BufferedImage(ox,oy, BufferedImage.TYPE_INT_RGB);
        try{
            FileOutputStream archivo_ant = new FileOutputStream("x.temp");
            DataOutputStream archivo = new DataOutputStream(archivo_ant);
            String str="";
            int N=0;
            for(int i=0;i<fileIN.length;i++) N=(N>opt.cadena.numeroFilas(fileIN [i]))?N:opt.cadena.numeroFilas(fileIN [i]);
            for(int j=1;j<=N;j++) archivo.writeBytes(j+"\n");
            archivo.close();
            archivo_ant.close();
        }catch (IOException oe) {System.out.println("hay error/es E10");}
        g2d.clear();
        g2d.add("x.temp"+" "+1+" "+1+" "+"x.temp");
        for(int i=0,color=0;i<fileIN.length;i++,color++)
            for(int k=1;k<=opt.cadena.numeroCol(fileIN[i]);k++)
                g2d.add(fileIN[i].getAbsolutePath()+" "+k+" "+color+" "+"col.temp");
        Max_diff_files_XY();
        ordenarMAX();
        show_diff_files_XNY();
        new dialogo.show_picture(fileIN[0].getAbsolutePath()).plot(imageBuffered);
        new File("x.temp").delete();
    }
    
    public void plotXNY(File fileIN){
        g2d.clear();
        inputfile2D=fileIN;
        imageBuffered = new BufferedImage(ox,oy, BufferedImage.TYPE_INT_RGB);
        int NCol=opt.cadena.numeroCol(fileIN);
        for(int k=1;k<=NCol;k++) g2d.add(k+" "+k+" "+"col.temp");
        Max_onefile_XNY();
        show_onefile_XNY();
        new dialogo.show_picture(fileIN.getAbsolutePath()).plot(imageBuffered);
    }
//----------------------------------------------------------
//------------------------GENERALES-------------------------
//----------------------------------------------------------
    public void init(int j /*columna del titulo*/ ){
        ox=imageBuffered.getWidth();
        oy=imageBuffered.getHeight();
        int max_M=0;
        imageGraphics = imageBuffered.createGraphics();
        imageGraphics.setFont(opt.currentIndex);
        imageGraphics.setColor(opt.colorFondo);
        imageGraphics.fillRect(0,0,ox,oy);
        if(opt.indexDown) opt.MyD=opt.currentEjes.getSize()+8+g2d.size()*opt.currentIndex.getSize();  //;) opt.MyD=20
        else opt.MyD=opt.currentEjes.getSize()+8;
        if(opt.indexRight){
            for(int i=1; i<g2d.size();i++ ) max_M=(max_M>opt.cadena.readColString(j,g2d.get(i)).length()*opt.currentIndex.getSize())?max_M:opt.cadena.readColString(j,g2d.get(i)).length()*opt.currentIndex.getSize();
            opt.MxR=10+max_M;  //;) opt.MxR=10
        }else opt.MxR=10;
        if(opt.showTitle) opt.MyU=20;
        else opt.MyU=10;
        opt.MxL=opt.nfy*opt.currentEjes.getSize()-15; //ojo !!
    }
    
    
    public void indexDown(int j){
        imageGraphics.setFont(opt.currentIndex);
        for(int i=1; i < g2d.size();i++ ){
            imageGraphics.setColor(new Color( opt.cadena.getColor(opt.cadena.readColInt(j-1,g2d.get(i)) )));
            imageGraphics.drawLine(opt.MxL,oy-i*imageGraphics.getFont().getSize(),opt.MxL+20,oy-i*imageGraphics.getFont().getSize());
            imageGraphics.drawString(opt.cadena.readColString(j,g2d.get(i)),opt.MxL+30,oy-i*imageGraphics.getFont().getSize());
        }
    }
    
    
    public void indexRight(int j){
        imageGraphics.setFont(opt.currentIndex);//new Font("Curier",Font.BOLD,9));
        for(int i=1; i < g2d.size();i++ ){
            imageGraphics.setColor(new Color(opt.cadena.getColor(opt.cadena.readColInt(j-1,g2d.get(i)))));
            imageGraphics.drawLine(ox-opt.MxR+4,opt.MyU+i*imageGraphics.getFont().getSize(),ox-opt.MxR+16,opt.MyU+i*imageGraphics.getFont().getSize());
            imageGraphics.drawString(opt.cadena.readColString(j,g2d.get(i)),ox-opt.MxR+20,opt.MyU+i*imageGraphics.getFont().getSize()+imageGraphics.getFont().getSize()/2);
        }
    }
    
    public void showTitle(){
        imageGraphics.setFont(opt.currentTitle);
        imageGraphics.setColor(opt.colorTitulo);
        imageGraphics.drawString(opt.Title,opt.MxL+(ox-opt.MxL-opt.MxR)/2-opt.Title.length()*opt.currentTitle.getSize()/2 ,opt.MyU/2);
    }
    
    
    public void verInfo(){
        imageGraphics.setFont(opt.currentEjes);
        imageGraphics.setColor(opt.colorFondo);
        imageGraphics.fillRect(0,0,opt.MxL,oy);
        imageGraphics.fillRect(0,0,ox,opt.MyU);
        imageGraphics.fillRect(0,oy-opt.MyD,ox,oy-opt.MyD);
        imageGraphics.fillRect(ox-opt.MxR,0,ox-opt.MxR,oy);
        imageGraphics.setColor(opt.colorEjes);
        imageGraphics.drawLine(opt.MxL,oy-opt.MyD,opt.MxL,opt.MyU);
        imageGraphics.drawLine(opt.MxL,oy-opt.MyD,ox-opt.MxR,oy-opt.MyD);
        int i=0;
        //--- divisiones iguales ----
        while(((i-1)*opt.hy+opt.y_min)<opt.y_max){
            int intY = (int) ((oy-opt.MyU-opt.MyD)*(1-((opt.y_min+i*opt.hy)-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
            imageGraphics.drawLine(opt.MxL,intY,opt.MxL-opt.L,intY);
            imageGraphics.drawString(opt.cadena.digitosGrafica(opt.nfy,opt.y_min+i*opt.hy),2,intY+opt.currentEjes.getSize()/2);
            i++;
        }
        i=0;
        while(((i-1)*opt.hx+opt.x_min)<opt.x_max){
            int intX = (int) ((ox-opt.MxL-opt.MxR)*((i*opt.hx+opt.x_min)-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
            imageGraphics.drawLine(intX,oy-opt.MyD+5,intX,oy-opt.MyD);
            imageGraphics.drawString(opt.cadena.digitosGrafica(opt.nfx,opt.x_min+i*opt.hx),intX-opt.cadena.pasarString(opt.x_min+i*opt.hx).length()*opt.currentEjes.getSize()/4,oy-4+(-opt.MyD+opt.currentEjes.getSize()+8)); //;) (-opt.MyD+20)     //no tengo una explicacion para esto
            i++;
        }
        
        //Pintamos los mensajes que haya,
        
        for(int k=0;k<opt.mens.size();k++){
            int intX = (int) ((ox-opt.MxL-opt.MxR)*(opt.cadena.readColDouble(1,opt.mens.get(k))-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
            int intY = (int) ((oy-opt.MyU-opt.MyD)*(1-(opt.cadena.readColDouble(2,opt.mens.get(k))-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
            imageGraphics.drawString(opt.mens.get(k),intX,intY);
            //    wLine(opt.MxL,oy-opt.MyD,opt.MxL,opt.MyU);
            imageGraphics.drawLine(opt.MxL,oy-opt.MyD,ox-opt.MxR,oy-opt.MyD);
        }
        
        /* divisiones iguales --- quitar en ajustar maximos
          for(int i=0 ; i <=opt.Aoy ;i++) {
            imageGraphics.drawLine(opt.MxL,oy-opt.MyD-(i*(oy-opt.MyD-opt.MyU))/opt.Aoy,opt.MxL-L,oy-opt.MyD-(i*(oy-opt.MyD-opt.MyU))/opt.Aoy);
            imageGraphics.drawString(opt.cadena.pasarString(opt.y_min+i*(opt.y_max-opt.y_min)/opt.Aoy),2,oy-opt.MyD-(i*(oy-opt.MyD-opt.MyU))/opt.Aoy+imageGraphics.getFont().getSize()/2);
        }
        for(int i=0 ; i <= opt.Aox;i++) {
            imageGraphics.drawLine((i*(ox-opt.MxL-opt.MxR)/opt.Aox)+opt.MxL,oy-opt.MyD+5,(i*(ox-opt.MxL-opt.MxR)/opt.Aox)+opt.MxL,oy-opt.MyD);
            imageGraphics.drawString(opt.cadena.pasarString(opt.x_min+i*(opt.x_max-opt.x_min)/opt.Aox),i*(ox-opt.MxL-opt.MxR)/opt.Aox+opt.MxL-10,oy-5+(-opt.MyD+20)); //;) (-opt.MyD+20)
        }
         */
    }
    
    public void HLine(double xL){;
    imageGraphics.setColor(opt.colorEjes);
    imageGraphics.setFont(opt.currentEjes);
    int intX = (int) ((ox-opt.MxL-opt.MxR)*(xL-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
    imageGraphics.drawLine(intX,oy-opt.MyD,intX,opt.MyU);
    }
    
    
    public void VLine(double yl){
        imageGraphics.setFont(opt.currentEjes);
        imageGraphics.setColor(opt.colorEjes);
        int intY = (int) ((oy-opt.MyU-opt.MyD)*(1-(yl-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
        imageGraphics.drawLine(opt.MxL,intY,ox-opt.MxR,intY);
    }
    
    
    public BufferedImage Selec()  {
        BufferedImage auxBf = new BufferedImage(ox,oy, BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics auxGr = auxBf.createGraphics();
        java.awt.image.ImageObserver ob=null;
        auxGr.drawImage(imageBuffered,0,0,ob);
        int a=(X_mouse_ini<X_mouse_fin)?X_mouse_ini:X_mouse_fin;
        int b=(Y_mouse_ini<Y_mouse_fin)?Y_mouse_ini:Y_mouse_fin;
        auxGr.setColor(new Color(0,150,255,90));
        auxGr.drawRect(a,b,(int) Math.abs(X_mouse_ini-X_mouse_fin),(int) Math.abs(Y_mouse_ini-Y_mouse_fin));
        auxGr.setColor(new Color(0,150,255,50));
        auxGr.fillRect(a,b,(int) Math.abs(X_mouse_ini-X_mouse_fin),(int) Math.abs(Y_mouse_ini-Y_mouse_fin));
        return auxBf;
    }
    
    
    public double getInvY(int intY){return -((intY-opt.MyU)*1.0/(oy-opt.MyU-opt.MyD)-1)*(opt.y_max-opt.y_min)+opt.y_min;}
    
    public double getInvX(int intX){return (intX-opt.MxL)*1.0*(opt.x_max-opt.x_min)/(ox-opt.MxL-opt.MxR)+opt.x_min;}
    
    public void lupa2D(){
        if(Math.abs(Y_mouse_ini-Y_mouse_fin)>10 && Math.abs(X_mouse_ini-X_mouse_fin)>10 ){
            double y_min_aux =  getInvY(Y_mouse_ini);// -((Y_mouse_ini-pintar2D.opt.MyU)*1.0/(screen1.getHeight()-pintar2D.opt.MyU-pintar2D.opt.MyD)-1)*(pintar2D.opt.y_max-pintar2D.opt.y_min)+pintar2D.opt.y_min;
            double y_max_aux =  getInvY(Y_mouse_fin);// -((Y_mouse_fin-pintar2D.opt.MyU)*1.0/(screen1.getHeight()-pintar2D.opt.MyU-pintar2D.opt.MyD)-1)*(pintar2D.opt.y_max-pintar2D.opt.y_min)+pintar2D.opt.y_min;
            double x_min_aux =  getInvX(X_mouse_ini);//(X_mouse_ini-pintar2D.opt.MxL)*1.0*(pintar2D.opt.x_max-pintar2D.opt.x_min)/(screen1.getWidth()-pintar2D.opt.MxL-pintar2D.opt.MxR)+pintar2D.opt.x_min;
            double x_max_aux =  getInvX(X_mouse_fin);//(X_mouse_fin-pintar2D.opt.MxL)*1.0*(pintar2D.opt.x_max-pintar2D.opt.x_min)/(screen1.getWidth()-pintar2D.opt.MxL-pintar2D.opt.MxR)+pintar2D.opt.x_min;
            opt.x_min=x_min_aux;opt.x_max=x_max_aux;
            opt.y_min=y_min_aux;opt.y_max=y_max_aux;
        }
    }
    
    
//------------------------------------------------------------------------------
//----------------        MAXIMOS GENERAL    -----------------------------------
//------------------------------------------------------------------------------
    
    // maxCol(inputFile,nCol);   //lo hacemos asi para cuando tengamos mas columnas hacer reiteracion
    // opt.y_min=col_min            --> opt.y_min=(opt.y_min<col_min)?opt.y_min:col_min;  :)
    // opt.y_max=col_max
    public void maxCol(File inputFile,int nCol){
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile.getAbsolutePath())) ;
            String str="";
            double C=0.0;
            str = in.readLine(); //catare la primera fila
            nCol=(nCol<1)?1:nCol;
            C = opt.cadena.readColDouble(nCol,str);
            col_min=C;
            col_max=C;
            while ((str = in.readLine()) != null) {
                C = opt.cadena.readColDouble(nCol,str) ;
                col_min=(C<col_min)?C:col_min;
                col_max=(C>col_max)?C:col_max;
            }
            in.close();
        }catch (IOException oe) {System.out.println("hay error/es maxCol");}
    }
    
    public void ordenarMAX(){
        double aux;
        if(opt.x_max < opt.x_min){
            aux=opt.x_max;
            opt.x_max=opt.x_min;
            opt.x_min=aux;
        }
        if(opt.y_max < opt.y_min){
            aux=opt.y_max;
            opt.y_max=opt.y_min;
            opt.y_min=aux;
        }
        //reescalamos--newInfo  Quitar para divisiones iguales
        
        opt.hy=(opt.y_max-opt.y_min)/opt.Aoy;
        opt.hx=(opt.x_max-opt.x_min)/opt.Aox;
        
    }
    
//------------------------------------------------------------------------------
//---------------------------- MEZCLA DE VARIOS ARCHIVOS -----------------------
//------------------------------------------------------------------------------
    
    public void Max_diff_files_N_XY(){  //diferentes x // cargamos g2d(archivox colx archivoy coly color titulo)
        if(opt.ajustarOptionsX)
            for(int i=1; i<g2d.size();i++ ){
            maxCol(opt.cadena.readColFile(1,g2d.get(i)),opt.cadena.readColInt(2,g2d.get(i)));
            if(i==1){
                opt.x_min=col_min;
                opt.x_max=col_max;
            }else{
                opt.x_min=(opt.x_min<col_min)?opt.x_min:col_min;
                opt.x_max=(opt.x_max>col_max)?opt.x_max:col_max;
            }
            }
        if(opt.ajustarOptionsY)
            for(int i=1; i<g2d.size();i++ ){
            maxCol(opt.cadena.readColFile(3,g2d.get(i)),opt.cadena.readColInt(4,g2d.get(i)));
            if(i==1){
                opt.y_min=col_min;
                opt.y_max=col_max;
            }else{
                opt.y_min=(opt.y_min<col_min)?opt.y_min:col_min;
                opt.y_max=(opt.y_max>col_max)?opt.y_max:col_max;
            }
            }
    }
    
    public void Max_diff_files_XY(){
        if(opt.ajustarOptionsX){
            maxCol(opt.cadena.readColFile(1,g2d.get(0)), opt.cadena.readColInt(2,g2d.get(0)));
            opt.x_min=col_min;
            opt.x_max=col_max;
        }
        if(opt.ajustarOptionsY)
            for(int i=1; i<g2d.size();i++ ){
            maxCol(opt.cadena.readColFile(1,g2d.get(i)),opt.cadena.readColInt(2,g2d.get(i)));
            if(i==1){
                opt.y_min=col_min;
                opt.y_max=col_max;
            }else{
                opt.y_min=(opt.y_min<col_min)?opt.y_min:col_min;
                opt.y_max=(opt.y_max>col_max)?opt.y_max:col_max;
            }
            }
    }
    
    //las columnas pueden ser del mismo archivo lo importate es que leemos columna a columna
    //si son muchas columnas en un archivo lo hace pero es muy ineficinete para eso usar la otra
    public void main_diff_files_XNY(File Xfile, int xCol, File Yfile, int yCol) {
        //-- aqui debera aparecer los Max_XY
        //no los ordenamos !!  ordenarMAX(); ya vienen ordenados
        if(Yfile.toString().equals(Xfile.toString())){
            try {
                BufferedReader in = new BufferedReader(new FileReader(Xfile.getAbsolutePath())) ;
                String str="";
                double y=0,x=0;
                str = in.readLine(); //catare la primera fila
                x= opt.cadena.readColDouble(xCol,str);
                y= opt.cadena.readColDouble(yCol,str);
                int intY_ant = (int) ((oy-opt.MyU-opt.MyD)*(1-(y-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
                int intX_ant = (int) ((ox-opt.MxL-opt.MxR)*(x-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
                while ((str = in.readLine()) != null) {
                    x= opt.cadena.readColDouble(xCol,str);
                    y= opt.cadena.readColDouble(yCol,str);
                    int intY = (int) ((oy-opt.MyU-opt.MyD)*(1-(y-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
                    int intX = (int) ((ox-opt.MxL-opt.MxR)*(x-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
                    if(seeLine)
                        for(ac=0;ac<ancho;ac++)
                            imageGraphics.drawLine(intX_ant,intY_ant+ac,intX,intY+ac);
                    if(seeCircle)
                        imageGraphics.drawOval(intX-anchoC/2,intY-anchoC/2,anchoC,anchoC);
                    intX_ant=intX;
                    intY_ant=intY;
                }
                in.close();
            }catch (IOException oe) {System.out.println("verColStep E4");}
        }else{
            try {
                BufferedReader inX = new BufferedReader(new FileReader(Xfile.getAbsolutePath())) ;
                BufferedReader inY = new BufferedReader(new FileReader(Yfile.getAbsolutePath()));
                String Xstr="";
                String Ystr="";
                double x=0,y=0;
                Xstr = inX.readLine(); //catare la primera fila
                Ystr = inY.readLine(); //me emborrachare de primeras
                x= opt.cadena.readColDouble(xCol,Xstr);
                y= opt.cadena.readColDouble(yCol,Ystr);
                int intY_ant = (int) ((oy-opt.MyU-opt.MyD)*(1-(y-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
                int intX_ant = (int) ((ox-opt.MxL-opt.MxR)*(x-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
                while ((Xstr = inX.readLine()) != null) {
                    Ystr=inY.readLine();
                    x= opt.cadena.readColDouble(xCol,Xstr);
                    y= opt.cadena.readColDouble(yCol,Ystr);
                    int intY = (int) ((oy-opt.MyU-opt.MyD)*(1-(y-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
                    int intX = (int) ((ox-opt.MxL-opt.MxR)*(x-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
                    if(seeLine)
                        for(ac=0;ac<ancho;ac++)
                            imageGraphics.drawLine(intX_ant,intY_ant+ac,intX,intY+ac);
                    if(seeCircle)
                        imageGraphics.drawOval(intX,intY,anchoC,anchoC);
                    intX_ant=intX;
                    intY_ant=intY;
                }
                inX.close();
                inY.close();
            }catch (IOException oe) {System.out.println("verColStep E4");}
        }
        //imageGraphics.drawOval(opt.MxL,opt.MyU,ox-opt.MxR-opt.MxL,oy-opt.MyD-opt.MyU);//(0,0,ox,oy);
        verInfo();  // ;)
    }
    
    //archivo_x colx archivo_y coly color titulo
    public void show_diff_files_N_XY(){
        init(6);
        ordenarMAX();
        for(int i=1; i<g2d.size(); i++) {
            ancho=opt.ancho[opt.cadena.readColInt(2,g2d.get(i))%12];
            anchoC=opt.anchoC[opt.cadena.readColInt(2,g2d.get(i))%12];
            seeLine=opt.verLine[opt.cadena.readColInt(2,g2d.get(i))%12];
            seeCircle=opt.verCircle[opt.cadena.readColInt(2,g2d.get(i))%12];
            imageGraphics.setColor(new Color( opt.cadena.getColor(opt.cadena.readColInt(5,g2d.get(i)))));
            main_diff_files_XNY(
                    opt.cadena.readColFile(1,g2d.get(i)),
                    opt.cadena.readColInt(2,g2d.get(i)),
                    opt.cadena.readColFile(3,g2d.get(i)),
                    opt.cadena.readColInt(4,g2d.get(i)));
        }
        
        if(opt.indexDown) { indexDown(6);}
        if(opt.indexRight){ indexRight(6);}
        if(opt.showTitle) { showTitle(); }
    }
    
    
    //g2d.add(archivoX+" "+int columnaX+" "+int color+" "+"------");
    //g2d.add(archivoY+" "+int columnaY+" "+int color+" "+"titulo");
    public  void show_diff_files_XNY(){
        init(4);
        ordenarMAX();
        //el vector 0 es la X el resto las Y's'  con   g2d.add(newfile.getAbsolutePath()+" "+i+" "+Color.RED.getRGB()+" "+"X");
        for(int i=1; i<g2d.size();i++ ){
            imageGraphics.setColor(new Color(opt.cadena.getColor(opt.cadena.readColInt(2,g2d.get(i)))));
            ancho=opt.ancho[opt.cadena.readColInt(2,g2d.get(i))%12];
            anchoC=opt.anchoC[opt.cadena.readColInt(2,g2d.get(i))%12];
            seeLine=opt.verLine[opt.cadena.readColInt(2,g2d.get(i))%12];
            seeCircle=opt.verCircle[opt.cadena.readColInt(2,g2d.get(i))%12];
            main_diff_files_XNY(
                    opt.cadena.readColFile(1,g2d.get(0)),
                    opt.cadena.readColInt(2,g2d.get(0)),
                    opt.cadena.readColFile(1,g2d.get(i)),
                    opt.cadena.readColInt(2,g2d.get(i)));
        }
        if(opt.indexDown) { indexDown(4);}
        if(opt.indexRight){ indexRight(4);}
        if(opt.showTitle) { showTitle(); }
    }
    
    
    public void setTitle(String auxTitle){
        opt.Title=auxTitle;
    }
    
//------------------------------------------------------------------------------
//---------------------------- UN ARCHIVO XNY-----------------------------------
//------------------------------------------------------------------------------
    public void Max_onefile_XNY(){
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputfile2D.getAbsoluteFile())) ;
            String str="";
            double x_aux=0.0,y_aux=0.0;
            int line=0;
            while ((str = in.readLine()) != null) {
                line++;
                if(opt.ajustarOptionsX){
                    if(line==1)
                        try{
                            opt.x_max=opt.x_min=opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(0)),str);
                        }catch(NumberFormatException ex){System.out.println("maxCol en x ="+opt.x_max);} else {
                        try{
                            x_aux=opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(0)),str);
                        }catch(NumberFormatException ex){System.out.println("maxCol en x = "+x_aux); }
                        opt.x_min=(opt.x_min<x_aux)?opt.x_min:x_aux;
                        opt.x_max=(opt.x_max>x_aux)?opt.x_max:x_aux;
                        }
                }
                if(opt.ajustarOptionsY){
                    for(int col=1;col<g2d.size();col++){
                        if(line==1 && col==1)
                            try{
                                if(opt.ajustarOptionsY)
                                    opt.y_max=opt.y_min=opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(col)),str);
                            }catch(NumberFormatException ex){System.out.println("maxCol en y");} else{
                            try{
                                if(opt.ajustarOptionsY)
                                    y_aux=opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(col)),str);
                            }catch(NumberFormatException ex){System.out.println("maxCol en y");}
                            if(opt.ajustarOptionsY){
                                opt.y_min=(opt.y_min<y_aux)?opt.y_min:y_aux;
                                opt.y_max=(opt.y_max>y_aux)?opt.y_max:y_aux;
                            }
                            }
                    }
                }
            }
            in.close();
        }catch (IOException oe) {System.out.println("hay error/es Max_onefile_XNY");}
    }
    
    public void main_onefile_XNY() {
        //-- aqui deber�a aparecer los Max_XY
        //los ordenamos !!
        ordenarMAX();
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputfile2D)) ;
            String str="";
            double [] DoubleI= new double[g2d.size()+1];
            double [] DoubleI_ant= new double[g2d.size()+1];
            int [] IntI = new int [g2d.size()+1];
            int [] IntI_ant = new int [g2d.size()+1];
            str = in.readLine(); //catare la primera fila
            for(int i=0;i<g2d.size();i++){
                DoubleI[i] = opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(i)),str);
                if(i==0) IntI_ant[i]=(int) ((ox-opt.MxL-opt.MxR)*(DoubleI[i]-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
                else IntI_ant[i]=(int) ((oy-opt.MyU-opt.MyD)*(1-(DoubleI[i]-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
            }
            while ((str = in.readLine()) != null) {
                for(int i=0;i<g2d.size();i++){
                    DoubleI[i] = opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(i)),str);
                    if(i==0) IntI[i]=(int) ((ox-opt.MxL-opt.MxR)*(DoubleI[i]-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
                    else IntI[i]=(int) ((oy-opt.MyU-opt.MyD)*(1-(DoubleI[i]-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
                    if(i>0){
                        imageGraphics.setColor(new Color( opt.cadena.getColor(opt.cadena.readColInt(2,g2d.get(i)))));
                        ancho=opt.ancho[opt.cadena.readColInt(2,g2d.get(i))%12];
                        anchoC=opt.anchoC[opt.cadena.readColInt(2,g2d.get(i))%12];
                        seeLine=opt.verLine[opt.cadena.readColInt(2,g2d.get(i))%12];
                        seeCircle=opt.verCircle[opt.cadena.readColInt(2,g2d.get(i))%12];
                        if(seeLine)
                            for(ac=0;ac<ancho;ac++)
                                imageGraphics.drawLine(IntI_ant[0],IntI_ant[i]+ac,IntI[0],IntI[i]+ac);
                        if(seeCircle)
                            imageGraphics.drawOval(IntI[0]-anchoC/2,IntI[i]-anchoC/2,anchoC,anchoC);
                    }
                }
                for(int i=0;i<g2d.size();i++) IntI_ant[i]=IntI[i];
            }
            in.close();
        }catch (IOException oe) {System.out.println("verColStep E4");}
        verInfo();  // ;)
    }
    
    //g2d.add(int columnaX+" "+int color+" "+"------");
    //g2d.add(int columnaY+" "+int color+" "+"titulo");
    public void show_onefile_XNY(){
        init(3);
        main_onefile_XNY();
        if(opt.indexDown) { indexDown(3);}
        if(opt.indexRight){ indexRight(3);}
        if(opt.showTitle) { showTitle(); }
    }
    
//------------------------------------------------------------------------------
//----------------------------   UN ARCHIVO sin X    ---------------------------
//------------------------------------------------------------------------------
    
    public void Max_onefile_Y(double step){
        if(opt.ajustarOptionsY)
            try {
                BufferedReader in = new BufferedReader(new FileReader(inputfile2D.getAbsoluteFile())) ;
                String str="";
                double x_aux=0.0,y_aux=0.0;
                int line=0;
                while ((str = in.readLine()) != null) {
                    line++;
                    opt.x_min=0;
                    for(int col=1;col<g2d.size();col++){
                        if(line==1 && col==1)
                            opt.y_max=opt.y_min=opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(col)),str);
                        else{
                            y_aux=opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(col)),str);
                            opt.y_min=(opt.y_min<y_aux)?opt.y_min:y_aux;
                            opt.y_max=(opt.y_max>y_aux)?opt.y_max:y_aux;
                        }
                    }
                    opt.x_max=line*step;
                }
                in.close();
            }catch (IOException oe) {System.out.println("hay error/es Max_onefile_Y");}
    }
    
    public void main_onefile_Y(double step) {
        //-- aqui deber�a aparecer los Max_XY
        //los ordenamos !!
        ordenarMAX();
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputfile2D)) ;
            String str="";
            double [] DoubleI= new double[g2d.size()+1];
            double [] DoubleI_ant= new double[g2d.size()+1];
            int [] IntI = new int [g2d.size()+1];
            int [] IntI_ant = new int [g2d.size()+1];
            str = in.readLine(); //catare la primera fila
            int intX=0,intX_ant=0;
            double x=0;
            for(int i=1;i<g2d.size();i++){
                DoubleI[i] = opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(i)),str);
                intX_ant=(int) ((ox-opt.MxL-opt.MxR)*(x-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
                IntI_ant[i]=(int) ((oy-opt.MyU-opt.MyD)*(1-(DoubleI[i]-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
            }
            while ((str = in.readLine()) != null) {
                x+=step;
                intX=(int) ((ox-opt.MxL-opt.MxR)*(x-opt.x_min)/(opt.x_max-opt.x_min)+opt.MxL);
                for(int i=1;i<g2d.size();i++){
                    DoubleI[i] = opt.cadena.readColDouble(opt.cadena.readColInt(1,g2d.get(i)),str);
                    IntI[i]=(int) ((oy-opt.MyU-opt.MyD)*(1-(DoubleI[i]-opt.y_min)/(opt.y_max-opt.y_min))+opt.MyU);
                    imageGraphics.setColor(new Color( opt.cadena.getColor(opt.cadena.readColInt(2,g2d.get(i)))));
                    ancho=opt.ancho[opt.cadena.readColInt(2,g2d.get(i))%12];
                    anchoC=opt.anchoC[opt.cadena.readColInt(2,g2d.get(i))%12];
                    seeLine=opt.verLine[opt.cadena.readColInt(2,g2d.get(i))%12];
                    seeCircle=opt.verCircle[opt.cadena.readColInt(2,g2d.get(i))%12];
                    if(seeLine)
                        for(ac=0;ac<ancho;ac++)
                            imageGraphics.drawLine(intX_ant,IntI_ant[i]+ac,intX,IntI[i]+ac);
                    if(seeCircle)
                        imageGraphics.drawOval(IntI[0]-anchoC/2,IntI[i]-anchoC/2,anchoC,anchoC);
                }
                for(int i=1;i<g2d.size();i++) IntI_ant[i]=IntI[i];
                intX_ant=intX;
            }
            in.close();
        }catch (IOException oe) {System.out.println("verColStep E4");}
        verInfo();  // ;)
    }
    
    //g2d.add(int columnaY+" "+int color+" "+"titulo");
    public void show_onefile_Y(double step){
        init(3);
        main_onefile_Y(step);
        if(opt.indexDown) { indexDown(3);}
        if(opt.indexRight){ indexRight(3);}
        if(opt.showTitle) { showTitle(); }
    }
    
    public void OpenOptions(){
        opt.setVisible(true);
    }
    
}