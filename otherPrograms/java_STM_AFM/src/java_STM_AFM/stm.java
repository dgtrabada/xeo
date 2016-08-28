/*
 
    java_STM_AFM is a free (GPLv3) java program to paint the experimental and theoretical data obtained by STM or AFM.
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

package java_STM_AFM;

import reader.reader;
import java.awt.Color;
import java.awt.Font;

public class stm {
    java.io.File inputfile = null;
    reader cadena = new reader();
    periodicTable table = new  periodicTable();
    int ox=1,oy=1;
    int resolucion=100;    //parametro de orden tambien endN
    double x_max,x_min,y_max,y_min,z_max,z_min,x,y,z,ancho;
    double Kb;
    double T;
    boolean usar_exp=false; //else hace el promedio
    int [][] C, Npuntos;
    double [][] Z;
    int [] H;
    boolean [][] up;
    double [][] lvs = new double[3][3];
    java.util.ArrayList<atomo> bas;
    java.awt.image.BufferedImage image_buffered;
    java.awt.Graphics image_graphics;
    Font currentFont,currentEjes;
    Color colorEjes, colorGrafico ;
    javax.swing.ImageIcon icon;
    Color colorIni;
    Color colorInter1;
    Color colorInter2;
    Color colorFin;
    Color colorFondo;
    int posicion_1,posicion_2;
    boolean verDerivada;
    boolean verAtomos;
    boolean verDireccion;
    boolean verIconosAtomos;
    boolean seeBond;
    int alfa;
    String pathIcon;
    int radio;
    boolean verEjes;
    int MxL,MxR;
    int MyU,MyD,L;
    int Aoy,Aox;
    boolean verBarra;
    int col1;
    int col2;
    int col3;
    boolean borde;
    int d_2_average;
    int [][] d_2;  //distancia en pixeles al cuadrado del dibujo
    int X_mouse_ini=0;
    int X_mouse_fin=0;
    int Y_mouse_ini=0;
    int Y_mouse_fin=0;
    int grosor;
    boolean seeLabel;
    
    /** Creates a new instance of stm */
    public stm() {
        Kb =0.000086173 ; // eV/K
        x_max=0;
        x_min=1;
        y_max=0;
        y_min=1;
        z_max=0;
        z_min=1;
        x=0;
        y=0;
        z=0;
        ancho=0;
        colorIni    = new Color(17,9,2);
        colorInter1 = new Color(208,81,12);
        colorInter2 = new Color(249,234,215);
        colorFin    = new Color(250,246,239);
        colorFondo  = new Color(255,255,255);
        posicion_1=60;
        posicion_2=90;
        verDerivada=false;
        verAtomos=false;
        verDireccion=false;
        alfa=100;
        pathIcon="/.";
        radio=10;
        verIconosAtomos=false;
        currentFont = new Font("Monospaced",Font.PLAIN,12);
        currentEjes = new Font("Curier",Font.BOLD,9);
        setFont("/dejavu/DejaVuSans.ttf");
        MxL=0;MxR=0;MyU=0;MyD=0;L=5;
        colorFondo = Color.WHITE;
        colorEjes  = Color.GRAY;
        colorGrafico = Color.RED ;
        Aoy=3;
        Aox=3;
        verBarra=false;
        col1=1;
        col2=2;
        col3=3;
        borde=false;
        d_2_average=0;
        grosor=1;
        bas = new java.util.ArrayList();
    }
    
    void setFont(String fontDir ){
        boolean ERFont=false;
        try{
            java.io.InputStream is = this.getClass().getResourceAsStream(fontDir);
            try{
                currentFont = Font.createFont(Font.TRUETYPE_FONT,is);
                currentFont = currentFont.deriveFont(12.0f);
                is.close();
            }catch(java.io.IOException e){ERFont=true;}
        }catch(java.awt.FontFormatException e){ERFont=true;}
        if(ERFont){
            currentFont = new Font("Monospaced",Font.PLAIN,12);
            System.out.println("ERROR : fuente no encontrada usamos : "+currentFont.toString());
        }
        ERFont=false;
        try{
            java.io.InputStream is = this.getClass().getResourceAsStream(fontDir);
            try{
                currentEjes = Font.createFont(Font.TRUETYPE_FONT,is);
                currentEjes = currentEjes.deriveFont(9.0f);
                is.close();
            }catch(java.io.IOException e){ERFont=true;}
        }catch(java.awt.FontFormatException e){ERFont=true;}
        if(ERFont){
            currentEjes = new Font("Curier",Font.BOLD,9);
            System.out.println("ERROR : fuente no encontrada usamos : "+currentEjes.toString());
        }
    }
    
    void maximos_dibujo(){
        try {
            //error_lectura=false;
            //System.out.println("cargando maximos...");
            //inicializo el tamaño etc. ...
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader(inputfile.getAbsolutePath()));
            String str;
            int nLine=0;
            int maximo= Math.max(col1,Math.max(col2,col3));
            while ((str = in.readLine()) != null) {
                boolean error = false;
                if(cadena.nCol(str)>=maximo){
                    try{
                        error=false;
                        x = cadena.readColDouble(col1,str);
                        y = cadena.readColDouble(col2,str);
                        z = cadena.readColDouble(col3,str);
                    } catch(NumberFormatException ex) {
                        error=true;
                        System.out.println("the progrman doesn used this line: "+str);
                    }
                    if(!error){
                        nLine++;
                        if(nLine==1){
                            x_max=x;x_min=x;y_max=y;y_min=y;z_max=z;z_min=z;
                        } else{
                            if(x_max<x)x_max=x;
                            if(x_min>x)x_min=x;
                            if(y_max<y)y_max=y;
                            if(y_min>y)y_min=y;
                            if(z_max<z)z_max=z;
                            if(z_min>z)z_min=z;
                        }
                    }
                }
            }
            in.close();
        }catch (java.io.IOException oe) {System.out.println("hay error/es E8");}
        double TOL=10E-80;
        if(Math.abs(x_max-x_min)<TOL)x_max=x_min+TOL;
        if(Math.abs(y_max-y_min)<TOL)y_max=y_min+TOL;
        if(Math.abs(z_max-z_min)<TOL)z_max=z_min+TOL;
        //System.out.println(z_max);
    }
    
    void calcular_dibujo(){
        //   error_lectura=false;
        //antiguamente sobreescribiamos los metodos, algo poco aconsejable
        // jPanel1.getGraphics().drawImage(image,0,0,this);
        double x=0,y=0,z=0,aux=0;
        int N,intx,inty,color;
        oy=resolucion;
        ancho=oy*(x_max-x_min)/(y_max-y_min);
        ox=(int)ancho;
        C = new int[ox+1][oy+1];
        up= new boolean[ox+1][oy+1];
        Npuntos = new int[ox+1][oy+1];
        Z = new double[ox+1][oy+1];
        for(int lx=0;lx<ox;lx++)
            for(int ly=0;ly<oy;ly++){
            C[lx][ly]=255;  //inicializamos el color es decir fondo blanco
            //backgroundColor
            up[lx][ly]=false;
            C[lx][ly]=0;
            Z[lx][ly]=0.0;
            }
        
        //cargamos los colores del fichero
        if(inputfile!=null){
            try{
                java.io.BufferedReader inFile = new java.io.BufferedReader(new java.io.FileReader(inputfile));
                java.io.LineNumberReader inLines = new java.io.LineNumberReader(inFile);
                String str;
                int maximo= Math.max(col1,Math.max(col2,col3));
                while ((str = inLines.readLine()) != null) {
                    boolean error = false;
                    if(cadena.nCol(str)>=maximo){
                        try{
                            error=false;
                            x= cadena.readColDouble(col1,str);
                            y= cadena.readColDouble(col2,str);
                            z= cadena.readColDouble(col3,str);
                        } catch(NumberFormatException ex) {error=true;}
                        if(!error){
                            intx=(int)(ox*(x-x_min)/(x_max-x_min));
                            inty=(int)(oy*(y-y_min)/(y_max-y_min));
                            //System.out.println(color);
                            Npuntos[intx][inty]++;
                            if(!usar_exp){
                                Z[intx][inty]=(Z[intx][inty]*(Npuntos[intx][inty]-1)+z)/Npuntos[intx][inty];
                            }else{
                                Z[intx][inty]+=Math.exp(-(z-z_min)/(Kb*T)); 
                            }                             
                            up[intx][inty]=true; //los que salgan aui los damos de alta
                            
                        }
                    }
                }
                inLines.close();
                inFile.close();
                
                if(usar_exp)
                for(int lx=0;lx<=ox;lx++)
                    for(int ly=0;ly<=oy;ly++)
                        if(up[lx][ly])
                            Z[lx][ly]=-Kb*T*Math.log(Z[lx][ly]/Npuntos[lx][ly]);
                
                
                
                int maxp=0;
                //rehacemos maximos en z por si usamos exp o promedio ....
                boolean pvez=false;
                for(int lx=0;lx<=ox;lx++)
                    for(int ly=0;ly<=oy;ly++){
                        if(Npuntos[lx][ly] > maxp) maxp=Npuntos[lx][ly]; 
                        if(!pvez && up[lx][ly]){
                            z_max= Z[lx][ly];
                            z_min= Z[lx][ly];
                            pvez=true;
                        }else{
                            if(up[lx][ly]){
                                if ( Z[lx][ly]  > z_max) z_max=Z[lx][ly];
                                if ( Z[lx][ly]  < z_min) z_min=Z[lx][ly];
                            }
                        }
                    }
                
                H = new int[maxp+1];
                for(int lx=0;lx<=ox;lx++)
                   for(int ly=0;ly<=oy;ly++){
                        if(up[lx][ly]){
                            aux=255*(Z[lx][ly]-z_min)/(z_max-z_min);
                            color=(int) aux;
                            C[lx][ly]=color;  
                            H[Npuntos[lx][ly]]++;
                        }else{
                            H[0]++; 
                       }                       
                    }
               
                System.out.println(z_max+" , "+z_min);
                for(int lx=0;lx<=maxp;lx++)
                    System.out.println(lx+" "+H[lx]);
                
                d_2 = new int[ox+1][oy+1];
                if(borde){
                    for(int lx=0;lx<=ox;lx++)
                        for(int ly=0;ly<=oy;ly++)
                            if(up[lx][ly]){
                        d_2[lx][ly]=0;
                            }else{
                        d_2[lx][ly]=ox*ox+oy*oy;
                        for(int lxx=0;lxx<=ox;lxx++)
                            for(int lyy=0;lyy<=oy;lyy++)
                                if( up[lxx][lyy]) d_2[lx][ly]=( ((lx-lxx)*(lx-lxx)+(ly-lyy)*(ly-lyy)) < d_2[lx][ly] )?((lx-lxx)*(lx-lxx)+(ly-lyy)*(ly-lyy)):d_2[lx][ly] ;
                            }
                    d_2_average=0;
                    for(int lx=0;lx<ox;lx++)
                        for(int ly=0;ly<oy;ly++)
                            d_2_average+=d_2[lx][ly] ;
                    d_2_average/=(ox*oy);
                }
                double c_max,c_min;
                //parametro de orden
                int endN=resolucion;//400;
                for(int newton=1;newton <= endN ;newton ++){
                    //interpolamos con ls caida de calor de newton:
                    for(int lx=1;lx<ox;lx++)
                        for(int ly=1;ly<oy;ly++){
                        
                        //  if(newton>6*endN/10) up[lx][ly]=false;
                        if(!up[lx][ly]) {
                            aux=C[lx+1][ly]+C[lx-1][ly]+C[lx][ly+1]+C[lx][ly-1];
                            aux=aux+C[lx+1][ly+1]+C[lx-1][ly+1]+C[lx+1][ly-1]+C[lx-1][ly-1];
                            aux=aux+C[lx][ly];
                            aux=aux/9;
                            C[lx][ly]=(int) (aux);
                            //C[lx][ly]=(C[lx+1][ly]+C[lx-1][ly]+C[lx][ly+1]+C[lx][ly-1])/4;
                        }
                        }
                    // arreglamos los bordes
                    C[0][0]=(C[0][1]+C[1][0]+C[1][1]+C[0][0])/4;
                    C[0][oy]=(C[0][oy-1]+C[1][oy]+C[1][oy-1]+C[0][oy])/4;
                    C[ox][0]=(C[ox][1]+C[ox-1][0]+C[ox-1][1]+C[ox][0])/4;
                    C[ox][oy]=(C[ox][oy]+C[ox-1][oy]+C[ox][oy-1]+C[ox-1][oy-1])/4;
                    for(int lx=1;lx<ox;lx++) {
                        C[lx][0]=(C[lx][1]+C[lx+1][0]+C[lx-1][0]+C[lx][0])/4;
                        C[lx][oy]=(C[lx][oy-1]+C[lx-1][oy]+C[lx+1][oy]+C[lx][oy])/4;
                    }
                    for(int ly=1;ly<oy;ly++) {
                        C[0][ly]=(C[1][ly]+C[0][ly-1]+C[0][ly+1]+C[0][ly])/4;
                        C[ox][ly]=(C[ox][ly-1]+C[ox][ly+1]+C[ox-1][ly]+C[ox][ly])/4;
                    }
                    if(borde){ // es decir utilizamos los lvs
                        
                        for(int lx=0;lx<ox;lx++)
                            for(int ly=0;ly<=oy;ly+=oy){
                            double xf=lx*(x_max-x_min)/ox+x_min;
                            double yf=ly*(y_max-y_min)/oy+y_min;
                            for(int i=-1;i<=1;i++)
                                for(int j=-1;j<=1;j++)
                                    if(!(i==0&&j==0)) {
                                x=xf+i*lvs[0][0]+j*lvs[0][1];
                                y=yf+i*lvs[1][0]+j*lvs[1][1];
                                if(x>x_min&&x<x_max&&y>y_min&&y<y_max){
                                    C[lx][ly]=C[(int)(ox*(x-x_min)/(x_max-x_min))][(int)(oy*(y-y_min)/(y_max-y_min))];
                                    i=10;j=10;
                                }
                                    }
                            }
                        for(int ly=0;ly<oy;ly++)
                            for(int lx=0;lx<=ox;lx+=ox){
                            double xf=lx*(x_max-x_min)/ox+x_min;
                            double yf=ly*(y_max-y_min)/oy+y_min;
                            for(int i=-1;i<=1;i++)
                                for(int j=-1;j<=1;j++)
                                    if(!(i==0&&j==0)) {
                                x=xf+i*lvs[0][0]+j*lvs[0][1];
                                y=yf+i*lvs[1][0]+j*lvs[1][1];
                                if(x>x_min&&x<x_max&&y>y_min&&y<y_max){
                                    C[lx][ly]=C[(int)(ox*(x-x_min)/(x_max-x_min))][(int)(oy*(y-y_min)/(y_max-y_min))];
                                    i=10;j=10;
                                }
                                    }
                            }
                    }
                    //Normalizamos
                    c_min=255;c_max=0;
                    for(int lx=0;lx<=ox;lx++)
                        for(int ly=0;ly<=oy;ly++){
                        if(C[lx][ly]<c_min)c_min=C[lx][ly];
                        if(C[lx][ly]>c_max)c_max=C[lx][ly];
                        }
                    for(int lx=0;lx<=ox;lx++)
                        for(int ly=0;ly<=oy;ly++){
                        aux= (C[lx][ly]-c_min)*255/(c_max-c_min);
                        C[lx][ly]=(int) aux;
                        }
                } // fin newrton
            }catch (java.io.IOException oe) {System.out.println("hay error/es E9");}
        }
    }
    
    String splot(){
        String aux="";
            for(int lx=0;lx<=ox;lx++){
                for(int ly=0;ly<=oy;ly++){
                        aux+=lx+" "+ly+" "+C[lx][ly]+"\n";
                }aux+="\n";
            }
        return aux;
    }
    void pintar_dibujo(){
        int azul=0,rojo=0,verde=0,C_aux=0;
        //---lo primero ajustar el marco-----//
        MxL=0;MxR=0;MyU=0;MyD=0;
        if(verEjes){
            MxL=40;MxR=10;MyU=10;MyD=20;
        }
        if(verBarra)  MxR=80;
        if(verDireccion) MyD=130;
        
        image_buffered = new java.awt.image.BufferedImage(ox+MxR+MxL,oy+MyD+MyU , java.awt.image.BufferedImage.TYPE_INT_RGB);
        image_graphics = image_buffered.createGraphics();
        //fondo
        image_graphics.setColor(colorFondo);
        //      if(borde) image_graphics.setColor(new Color(0,0,0,0));
        //image_graphics.fillRect(0,0,ox+80+40,oy+40);
        image_graphics.fillRect(0,0,ox+MxR+MxL,oy+MyD+MyU);
        verInfo();
        
        if(posicion_1 >= posicion_2){
            int auxpos=posicion_2;
            posicion_2=posicion_1;
            posicion_1=auxpos;
        }
        //hacemos la derivada :
        if(verDerivada){
            int auxa,auxc ;
            for(int lx=0;lx<=ox;lx++){
                auxa=C[lx][0];
                for(int ly=1;ly<=oy;ly++){
                    auxc=C[lx][ly]-auxa;
                    auxa=C[lx][ly];
                    C[lx][ly]=auxc;
                }
            }
        }
        for(int lx=0;lx<=ox;lx++)
            for(int ly=0;ly<=oy;ly++){
            C_aux=C[lx][ly]*100/255; //normalizamos otra vez-> 100 maximo en Sinter1,2
            if(C_aux<posicion_1 && posicion_1 > 0) {
                azul=C_aux*(colorInter1.getBlue()-colorIni.getBlue())/posicion_1 + colorIni.getBlue();
                rojo=C_aux*(colorInter1.getRed()-colorIni.getRed())/posicion_1+colorIni.getRed();
                verde=C_aux*(colorInter1.getGreen()-colorIni.getGreen())/posicion_1+colorIni.getGreen();
            }
            if(C_aux>=posicion_1 && C_aux<posicion_2 && posicion_1!=posicion_2) {
                azul=(C_aux-posicion_1)*(colorInter2.getBlue()-colorInter1.getBlue())/(posicion_2-posicion_1) + colorInter1.getBlue();
                rojo=(C_aux-posicion_1)*(colorInter2.getRed()-colorInter1.getRed())/(posicion_2-posicion_1)+colorInter1.getRed();
                verde=(C_aux-posicion_1)*(colorInter2.getGreen()-colorInter1.getGreen())/(posicion_2-posicion_1)+colorInter1.getGreen();
            }
            if(C_aux >= posicion_2  && posicion_2 < 100 /*Clabel.getWidth()*/ ){
                azul=(C_aux-posicion_2)*(colorFin.getBlue()-colorInter2.getBlue())/(100-posicion_2)+colorInter2.getBlue();
                rojo=(C_aux-posicion_2)*(colorFin.getRed()-colorInter2.getRed())/(100-posicion_2)+colorInter2.getRed();
                verde=(C_aux-posicion_2)*(colorFin.getGreen()-colorInter2.getGreen())/(100-posicion_2)+colorInter2.getGreen();
            }
            azul=(azul>254)?255:azul;
            azul=(azul<1)?0:azul;
            rojo=(rojo>254)?255:rojo;
            rojo=(rojo<1)?0:rojo;
            verde=(verde>254)?255:verde;
            verde=(verde<1)?0:verde;
            
            image_graphics.setColor(new Color(rojo,verde,azul));
            if(borde)if(d_2[lx][ly]>2*d_2_average)image_graphics.setColor(new Color(0,0,0,0));
            image_graphics.fillRect(lx+MxL,oy-ly+MyU,1,1);
            }
        // g = jPanel1.getGraphics();
        // g.drawImage(image,0,0,this);
        if(verAtomos) pintar_atomos();
        icon = new  javax.swing.ImageIcon(image_buffered);
    }
    
    void verInfo(){
        if(verBarra){
            int max_aux=oy/2-10;
            if(max_aux<0)max_aux=-max_aux;
            int p_1=posicion_1*max_aux/100 ; //(Sinter1.getValue()*max_aux)/Sinter1.getMaximum();
            int p_2=posicion_2*max_aux/100;   //(Sinter2.getValue()*max_aux)/Sinter2.getMaximum();
            int azul=0,rojo=0,verde=0;
            for(int C_aux=0;C_aux < max_aux ; C_aux+=4){
                if(C_aux<p_1 && p_1 > 0) {
                    azul=C_aux*(colorInter1.getBlue()-colorIni.getBlue())/p_1 + colorIni.getBlue();
                    rojo=C_aux*(colorInter1.getRed()-colorIni.getRed())/p_1+colorIni.getRed();
                    verde=C_aux*(colorInter1.getGreen()-colorIni.getGreen())/p_1+colorIni.getGreen();
                }
                if(C_aux>=p_1 && C_aux<p_2 && p_1!=p_2) {
                    azul=(C_aux-p_1)*(colorInter2.getBlue()-colorInter1.getBlue())/(p_2-p_1) + colorInter1.getBlue();
                    rojo=(C_aux-p_1)*(colorInter2.getRed()-colorInter1.getRed())/(p_2-p_1)+colorInter1.getRed();
                    verde=(C_aux-p_1)*(colorInter2.getGreen()-colorInter1.getGreen())/(p_2-p_1)+colorInter1.getGreen();
                }
                if(C_aux >= p_2  && p_2 < oy/2 ){
                    azul=(C_aux-p_2)*(colorFin.getBlue()-colorInter2.getBlue())/(max_aux-p_2)+colorInter2.getBlue();
                    rojo=(C_aux-p_2)*(colorFin.getRed()-colorInter2.getRed())/(max_aux-p_2)+colorInter2.getRed();
                    verde=(C_aux-p_2)*(colorFin.getGreen()-colorInter2.getGreen())/(max_aux-p_2)+colorInter2.getGreen();
                }
                
                image_graphics.setColor(new Color(rojo,verde,azul));
                image_graphics.fillRect(MxL+ox+10,MyU+oy-C_aux-10,25,4);
            }
            image_graphics.setColor(Color.BLACK);
            image_graphics.setFont(new Font("Curier",Font.BOLD,8));
            image_graphics.drawString("C = "+cadena.pasarString((z_max-z_min)/2),MxL+ox+25,10+oy/2-15);
            image_graphics.drawString(" "+cadena.pasarString(z_max),MxL+ox+35,MyU+oy/2+7);
            image_graphics.drawString(" "+cadena.pasarString((z_max+z_min)/2),MxL+ox+35,MyU+3*oy/4);
            image_graphics.drawString(" "+cadena.pasarString(z_min),MxL+ox+35,MyU+oy-7);
        }
        if(verEjes){
            image_graphics.setFont(currentEjes);
            image_graphics.setColor(colorEjes);
            image_graphics.drawLine(MxL,oy+MyU,MxL,MyU);
            image_graphics.drawLine(MxL,oy+MyU,ox+MxL,oy+MyU);
            for(int i=0 ; i <=Aoy ;i++) {
                image_graphics.drawLine(MxL,oy+MyU-(i*oy)/Aoy,MxL-L,oy+MyU-(i*oy)/Aoy);
                image_graphics.drawString(cadena.pasarString(y_min+i*(y_max-y_min)/Aoy),2,oy+MyU-(i*oy)/Aoy+image_graphics.getFont().getSize()/2);
            }
            for(int i=0 ; i <= Aox;i++) {
                image_graphics.drawLine(i*ox/Aox+MxL,oy+MyU+5,i*ox/Aox+MxL,oy+MyU);
                image_graphics.drawString(cadena.pasarString(x_min+i*(x_max-x_min)/Aox),i*ox/Aox+MxL-10,oy-5+(+MyU+20)); //;) (-MyD+20)
            }
        }
    }
    
    
    void difuminate(){
        int endN=resolucion/20;
        for(int newton=1;newton <= endN ;newton ++){
            //interpolamos con ls caida de calor de newton:
            for(int lx=1;lx<ox;lx++)
                for(int ly=1;ly<oy;ly++){
                double aux=C[lx+1][ly]+C[lx-1][ly]+C[lx][ly+1]+C[lx][ly-1];
                aux=aux+C[lx+1][ly+1]+C[lx-1][ly+1]+C[lx+1][ly-1]+C[lx-1][ly-1];
                aux=aux+C[lx][ly];
                aux=aux/9;
                C[lx][ly]=(int) (aux);
                }
            // arreglamos los bordes
            C[0][0]=(C[0][1]+C[1][0]+C[1][1]+C[0][0])/4;
            C[0][oy]=(C[0][oy-1]+C[1][oy]+C[1][oy-1]+C[0][oy])/4;
            C[ox][0]=(C[ox][1]+C[ox-1][0]+C[ox-1][1]+C[ox][0])/4;
            C[ox][oy]=(C[ox][oy]+C[ox-1][oy]+C[ox][oy-1]+C[ox-1][oy-1])/4;
            for(int lx=1;lx<ox;lx++) {
                C[lx][0]=(C[lx][1]+C[lx+1][0]+C[lx-1][0]+C[lx][0])/4;
                C[lx][oy]=(C[lx][oy-1]+C[lx-1][oy]+C[lx+1][oy]+C[lx][oy])/4;
            }
            for(int ly=1;ly<oy;ly++) {
                C[0][ly]=(C[1][ly]+C[0][ly-1]+C[0][ly+1]+C[0][ly])/4;
                C[ox][ly]=(C[ox][ly-1]+C[ox][ly+1]+C[ox-1][ly]+C[ox][ly])/4;
            }
        }
    }
    
    void pintar_direccion( ){
        X_mouse_ini=(X_mouse_ini<MxL)?MxL:X_mouse_ini;
        X_mouse_fin=(X_mouse_fin<MxL)?MxL:X_mouse_fin;
        X_mouse_ini=(X_mouse_ini>MxL+ox)?MxL+ox:X_mouse_ini;
        X_mouse_fin=(X_mouse_fin>MxL+ox)?MxL+ox:X_mouse_fin;
        Y_mouse_ini=(Y_mouse_ini<MyU)?MyU:Y_mouse_ini;
        Y_mouse_fin=(Y_mouse_fin<MyU)?MyU:Y_mouse_fin;
        Y_mouse_ini=(Y_mouse_ini>MyU+oy)?MyU+oy:Y_mouse_ini;
        Y_mouse_fin=(Y_mouse_fin>MyU+oy)?MyU+oy:Y_mouse_fin;
        image_graphics.setColor(Color.blue);
        image_graphics.drawLine(X_mouse_ini,Y_mouse_ini,X_mouse_fin,Y_mouse_fin);
        image_graphics.setFont(currentEjes);
        image_graphics.setColor(colorEjes);
        double xCorte,yCorte,ly;
        int l_old=0;
        for(int l=0;l<=ox;l++){
            xCorte=(X_mouse_fin-X_mouse_ini)*l/ox+X_mouse_ini;
            yCorte=(Y_mouse_fin-Y_mouse_ini)*l/ox+Y_mouse_ini;
            ly=C[(int) xCorte-MxL][(int) (oy-yCorte+MyU)]*(MyD-30)/255;
            ///que gracioso 255 es el maximo :)
            if(l>0) image_graphics.drawLine(MxL+l,(int) (oy+MyU+(MyD-10)-ly) ,MxL+l-1, (int) (oy+MyU+(MyD-10)-l_old) );
            l_old=(int) ly;
        }
        image_graphics.drawLine(MxL,oy+MyU+MyD-10,MxL,oy+MyU+MyD-10-100);
        image_graphics.drawLine(MxL,oy+MyU+MyD-10,MxL+ox,oy+MyU+MyD-10);
        double d=Math.pow(Math.pow((X_mouse_fin-X_mouse_ini)*(x_max-x_min)/ox,2)+Math.pow((Y_mouse_fin-Y_mouse_ini)*(y_max-y_min)/oy,2),0.5);
        if(d>0.01 && (X_mouse_ini>40 && X_mouse_ini<ox+40 && Y_mouse_ini>10 && Y_mouse_ini<oy+10)){
            image_graphics.drawString("distance",MxL+ox+35,15);
            image_graphics.drawString("d = "+cadena.pasarString(d),40+ox+10,10+20) ;
        }
        if(verEjes){
            image_graphics.drawString(cadena.pasarString(z_max)+"",4,oy+MyU+(MyD-100) );
            image_graphics.drawString(cadena.pasarString(z_min)+"",4,oy+MyU+(MyD-10) );
        }else{
            image_graphics.drawString(cadena.pasarString(z_max)+"",MxL,oy+MyU+(MyD-100) );
            image_graphics.drawString(cadena.pasarString(z_min)+"",MxL,oy+MyU+(MyD-10) );
        }
    }
    
    double auxR;
    void pintar_atomos() {
        for(int n=0;n<bas.size();n++){
            x= bas.get(n).x;
            y= bas.get(n).y ;
            z= bas.get(n).z ;
            bas.get(n).X=((int)(ox*(x-x_min)/(x_max-x_min)));
            bas.get(n).Y=((int)(oy*(y-y_min)/(y_max-y_min)));
        }
        for(int n=0;n<bas.size();n++){
            int sx1=bas.get(n).X;
            int sy1=bas.get(n).Y;
            image_graphics.setColor(
                    new Color(table.getColor(bas.get(n).Z).getRed(),table.getColor(bas.get(n).Z).getGreen(),table.getColor(bas.get(n).Z).getBlue(),alfa));
            java.io.File dibujo_atomo = new java.io.File(pathIcon+"/"+bas.get(n).symbol+".gif");
            if (dibujo_atomo.exists()&&verIconosAtomos){
                try{
                    javax.imageio.stream.FileImageInputStream archivoImagen = new  javax.imageio.stream.FileImageInputStream(dibujo_atomo);
                    java.awt.image.BufferedImage atomoBuffered = javax.imageio.ImageIO.read(archivoImagen);
                    java.awt.Graphics atomoGraphics = atomoBuffered.createGraphics();
                    java.awt.image.ImageObserver ob=null;
                    image_graphics.drawImage(atomoBuffered,MxL+sx1-radio/2,MyU +oy - sy1-radio/2,radio,radio,ob);
                }catch(Exception e){e.printStackTrace();}
            } else{
                image_graphics.fillOval(MxL+bas.get(n).X-radio/2,MyU+oy-bas.get(n).Y-radio/2,radio,radio);
            }
            if(seeBond){
                for(int j=0;j<bas.get(n).n_enlaces();j++){
                    int neig=n; // solo la mitad sino duplicamos enlaces y factorizamos la busqueda, cojonudo!
                    boolean pintar_enlace=(bas.get(neig).pos==bas.get(n).get_enlace(j));
                    if(!pintar_enlace){
                        boolean cx=!pintar_enlace;  //lo va ha buscar ...
                        while(cx){
                            if(neig==(bas.size()-1)) cx=false; //para salir  sela lo que sea
                            else{
                                neig++;
                                pintar_enlace=(bas.get(neig).pos==bas.get(n).get_enlace(j));
                                cx=!pintar_enlace;
                            }
                        }
                    }
                    if(pintar_enlace){
                        int sx2=bas.get(neig).X;
                        int sy2=bas.get(neig).Y;
                        image_graphics.setColor(new Color(table.getColor(bas.get(n).Z).getRed(),table.getColor(bas.get(n).Z).getGreen(),table.getColor(bas.get(n).Z).getBlue(),alfa));
                        for(int g=-grosor+1;g<grosor;g++) {
                            image_graphics.drawLine(MxL+sx1+g,MyU+oy-sy1,MxL+(sx1+sx2)/2+g,MyU+oy-(sy1+sy2)/2);
                            image_graphics.drawLine(MxL+sx1,MyU+oy-sy1+g,MxL+(sx1+sx2)/2,MyU+oy-(sy1+sy2)/2+g);
                        }
                        image_graphics.setColor(new Color(table.getColor(bas.get(n).Z).getRed(),table.getColor(bas.get(n).Z).getGreen(),table.getColor(bas.get(n).Z).getBlue(),alfa));
                        for(int g=-grosor+1;g<grosor;g++) {
                            image_graphics.drawLine(MxL+sx2+g,MyU+oy-sy2,MxL+(sx1+sx2)/2+g,MyU+oy-(sy1+sy2)/2);
                            image_graphics.drawLine(MxL+sx2,MyU+oy-sy2+g,MxL+(sx1+sx2)/2,MyU+oy-(sy1+sy2)/2+g);
                        }
                    }
                }
            }
            if(seeLabel){
                image_graphics.setColor(colorFondo);
                image_graphics.drawString((bas.get(n).pos+1) + " " , MxL+bas.get(n).X,MyU+oy-bas.get(n).Y);
            }
        }
        
    }
    
}
