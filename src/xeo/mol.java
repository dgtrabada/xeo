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
import java.awt.Color; 
import java.awt.Font; 
import java.awt.Graphics; 
import java.io.File; 
import xeoBabel.periodicTable;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class mol {
    boolean MOL_enable;
    C3D C3D = new C3D();
    plugBabel babel = new plugBabel();
    reader.reader cadena = new reader.reader();
    xeoBabel.periodicTable  periodicTable = new  xeoBabel.periodicTable();
    //   castep castep = new castep();
    //-----dibujo ----
    BufferedImage imageBuffered;  //;)
    java.awt.Graphics imageGraphics;
    BufferedImage miniEjesBuffered;  //;)
    java.awt.Graphics miniEjesGraphics;
    int miniOx,miniOy;
    Color colorFondo;
    Color colorEjes;
    Color colorArrows;
    java.awt.Font currentFont,currentEjes;
    double [] centro = new double[3];
    double [] c_out = new double[3];  //los fijo desde fuera:
    double [] eje = new double[3];
    double rad;
    boolean verRad;
    boolean verEjes;
    boolean firstTime;
    boolean seeFrag;
    // boolean verCargas;
    boolean seeArrows;
    boolean diffColorArrows;
    Color colorBorde;
    int grosor;
    boolean verPosiciones;
    boolean verdir;
    boolean selec;
    int X_mouse_ini=0;
    int X_mouse_fin=0;
    int Y_mouse_ini=0;
    int Y_mouse_fin=0;
    boolean showMesg;
    String mesg;
    double rescalateArrow;
    //------------------------
    java.io.File xyztFile;
    java.io.File xyzFileTr;
    boolean xyzt,xyzTr;
    String atoms_xyzTr;
    int Nxyzt;
    ArrayList<pixel> superficie;
    ArrayList<pixel> trayectoria;
    int MAX = 100;
    public int [] C = new int[MAX];
    public int [] ancho = new int[MAX];
    public int [] itr = new int[MAX];
    public int [] jtr = new int[MAX];
    public int [] ktr = new int[MAX];
    int trfirst;
    int trlast;
    Color colorIni;
    Color colorInter1;
    Color colorInter2;
    Color colorFin;
    boolean verIcono;
    boolean verEscalaGrises;
    int posicion_1,posicion_2;
    boolean verBordes;
    boolean MOUSE_DRAG;
    int pixel;
    //-----------------------
    // miniejes
    int [][] miniEje = new int[6][2];
    String [] ejeN = {"X","-X","Y","-Y","Z","-Z"};
    boolean [] BoolEje = new boolean[6];
    int nejes;
    
    public mol(){
        MOUSE_DRAG=false;
        MOL_enable=false;
        colorEjes=Color.BLACK;
        colorArrows=Color.BLACK;
        eje[0]=eje[1]=eje[2]=1;
        c_out[0]=c_out[1]=c_out[2]=0;
        verEjes=false;
        firstTime=false;
        grosor=4;
        colorBorde=Color.GRAY;
        verPosiciones=false;
        currentFont = new java.awt.Font("Monospaced",java.awt.Font.BOLD,10);
        currentEjes = new java.awt.Font("Monospaced",java.awt.Font.BOLD,10);
        showMesg=false;
        mesg="";
        rad=1;
        rescalateArrow=1;
        seeArrows=false;
        diffColorArrows=false;
        //---------------------
        xyzt=false;
        xyzTr=false;
        Nxyzt=0;
        colorIni    = new Color(17,9,2);
        colorInter1 = new Color(208,81,12);
        colorInter2 = new Color(249,234,215);
        colorFin    = new Color(250,246,239);
        colorFondo  = new Color(255,255,255);
        posicion_1=60;
        posicion_2=90;
        verIcono=false;
        verEscalaGrises=false;
        verBordes=false;
        superficie = new ArrayList();
        trayectoria = new ArrayList();
        trfirst=0;
        trlast=Integer.MAX_VALUE;
        for(int i = 1; i<MAX;i++) { C[i] = 0; ancho[i]=0; itr[i]=0;jtr[i]=0;ktr[i]=0;}
        ancho[0]=1;C[0]=-13369345;
        ancho[1]=1;C[1]=-16777216;
        ancho[2]=1;C[2]=-39322;
        ancho[3]=1;C[3]=-16737793;
        ancho[4]=1;C[4]=-16737997;
        ancho[5]=1;C[5]=-13312;
        ancho[6]=1;C[6]= -6710887;
        ancho[7]=1;C[7]=-6684673;
        ancho[8]=1;C[8]=-6684775;
        ancho[9]=1;C[9]=-13108;
        ancho[10]=1;C[10]=-3355393;
        ancho[11]=1;C[11]=-3355444;
        //---------------------
        BoolEje[0]=BoolEje[1]=BoolEje[2]=BoolEje[3]=BoolEje[4]=BoolEje[5]=false;
        nejes=1;
    }
    
    //------------------CARGAR ARCHIVOS ------------------------
    //----------------------------------------------------------
    
    void load() {
        babel.read();
        verdir=false;
        selec=false;
        firstTime=true;
    }
    
    void loadLVS(){
        babel.readLVS=false;
        babel.read();
        babel.readLVS=true;
        verdir=false;
        selec=false;
        firstTime=true;
    }
    
    int anguloINI(int X1,int Y1, int X2, int Y2,int X3,int Y3){
        int ax=X1-X2;
        int ay=Y1-Y2;
        int bx=X3-X2; //  10
        int by=Y3-Y2; //   0
        double cos=(ax*bx+ay*by)/Math.pow(ax*ax+ay*ay,0.5)/Math.pow(bx*bx+by*by,0.5);
        int angulo=(int) (Math.acos(cos)*180/Math.PI);
        if(ay>0)angulo=360-angulo;       //recuerda que la pantalla esta inverstida la Y
        return angulo;
    }
    
//------------------------------- PINTAR ---------------------------------------
//------------------------------------------------------------------------------
    //---variables de angulo---
    int atomo_ini,atomo_med,atomo_fin,x_d,y_d,x_old,y_old;
    double x1,y1,x2,y2,z1,z2,N1,N2,xm,ym,zm,Nm,angulo,R;
    //---------------------------
    
    BufferedImage pintar3D(BufferedImage aux){
        imageBuffered=aux;
        //---- dimensiones ----
        C3D.ox=imageBuffered.getWidth();
        C3D.oy=imageBuffered.getHeight();
        imageGraphics = imageBuffered.createGraphics();
        
        //--------fondo -------
        imageGraphics.setColor(colorFondo);  ///setColor(new Color(255,255,255));
        imageGraphics.fillRect(0,0,C3D.ox,C3D.oy);
        //---------------------
        if(firstTime) {
            babel.infBas.ajustar_maximos();
            ajustar_centro();  //when load bas file
        }
        
        // load C3D.pMax
        for(int i=0;i<babel.infBas.bas.size();i++){
            babel.infBas.bas.get(i).dOb=(C3D.c_z(babel.infBas.bas.get(i).R[0]-centro[0],babel.infBas.bas.get(i).R[1]-centro[1],babel.infBas.bas.get(i).R[2]-centro[2]));
            if(i==0)C3D.pMax=babel.infBas.bas.get(i).dOb;
            else if( babel.infBas.bas.get(i).dOb > C3D.pMax ) C3D.pMax = babel.infBas.bas.get(i).dOb;
        }
        
        if(verEjes) ejes();
        
        for(int i=0;i<babel.infBas.bas.size();i++){
            babel.infBas.bas.get(i).X=(C3D.cX(babel.infBas.bas.get(i).R[0]-centro[0],babel.infBas.bas.get(i).R[1]-centro[1],babel.infBas.bas.get(i).R[2]-centro[2]));
            babel.infBas.bas.get(i).Y=(C3D.cY(babel.infBas.bas.get(i).R[0]-centro[0],babel.infBas.bas.get(i).R[1]-centro[1],babel.infBas.bas.get(i).R[2]-centro[2]));
        }
        
        
        ordenarVista();
        
        if(xyzt){
            for(int i=0;i<Nxyzt;i++)
                superficie.get(i).dOb=C3D.c_z(superficie.get(i).R[0]-centro[0],superficie.get(i).R[1]-centro[1],superficie.get(i).R[2]-centro[2]);
            ordenarSuperficie();
        }
        
        
        int radio,ipixel=0;
        for(int i=0;i<babel.infBas.bas.size();i++){
            if(xyzt)
                while(ipixel<Nxyzt-1 && superficie.get(ipixel).dOb<babel.infBas.bas.get(i).dOb) {
                if(verBordes || ( superficie.get(ipixel).color >posicion_1 && superficie.get(ipixel).color <posicion_2)){
                    imageGraphics.setColor(getColorPixel(superficie.get(ipixel).color));
                    putPixel(superficie.get(ipixel).R[0]-centro[0],superficie.get(ipixel).R[1]-centro[1],superficie.get(ipixel).R[2]-centro[2]);
                }
                ipixel++;
                }
            if(babel.infBas.bas.get(i).visible){
                double auxR=C3D.a*rad/1.6;
                if(verRad) auxR*=Math.pow(babel.infBas.bas.get(i).Z,0.5);
                radio=(int) auxR;
                if(babel.infBas.seeBond&&(i<(babel.infBas.bas.size()-1))){
                    int sx1=0,sx2=0,sy1=0,sy2=0;
                    sx1=babel.infBas.bas.get(i).X;
                    sy1=babel.infBas.bas.get(i).Y;
                    for(int j=0;j<babel.infBas.bas.get(i).n_enlaces();j++){
                        int neig=i+1; // solo la mitad sino duplicamos enlaces y factorizamos la busqueda, cojonudo!
                        boolean pintar_enlace=((atom) babel.infBas.bas.get(neig)).posBas==babel.infBas.bas.get(i).get_enlace(j);
                        if(!pintar_enlace){
                            boolean cx=!pintar_enlace;  //lo va ha buscar ...
                            while(cx){
                                if(neig==(babel.infBas.bas.size()-1)) cx=false; //para salir  sela lo que sea
                                else{
                                    neig++;
                                    pintar_enlace=babel.infBas.bas.get(neig).posBas==babel.infBas.bas.get(i).get_enlace(j);
                                    cx=!pintar_enlace;
                                }
                            }
                        }
                        if(pintar_enlace){
                            sx2=babel.infBas.bas.get(neig).X;
                            sy2=babel.infBas.bas.get(neig).Y;
                            Color colorEnlace;
                            colorEnlace=periodicTable.getColor(babel.infBas.bas.get(i).Z);
                            if(verEscalaGrises) colorEnlace=cadena.getGris(colorEnlace);
                            if(verIcono) colorEnlace=colorEjes;
                            imageGraphics.setColor(colorEnlace);
                            for(int g=-grosor+1;g<grosor;g++) {
                                imageGraphics.drawLine(sx1+g,sy1,(sx1+sx2)/2+g,(sy1+sy2)/2);
                                imageGraphics.drawLine(sx1,sy1+g,(sx1+sx2)/2,(sy1+sy2)/2+g);
                            }
                            colorEnlace=periodicTable.getColor(babel.infBas.bas.get(neig).Z);
                            if(verIcono) colorEnlace=colorEjes;
                            if(verEscalaGrises) colorEnlace=cadena.getGris(colorEnlace);
                            imageGraphics.setColor(colorEnlace);
                            for(int g=-grosor+1;g<grosor;g++) {
                                imageGraphics.drawLine(sx2+g,sy2,(sx1+sx2)/2+g,(sy1+sy2)/2);
                                imageGraphics.drawLine(sx2,sy2+g,(sx1+sx2)/2,(sy1+sy2)/2+g);
                            }
                            //     imageGraphics.setColor(colorBorde);
                            //     imageGraphics.drawLine(sx2-grosor+1,sy2-grosor+1,sx1-grosor+1,sy1-grosor+1);
                            //     imageGraphics.drawLine(sx2+grosor,sy2+grosor,sx1+grosor,sy1+grosor);
                        }
                    }
                }
                //esto es para pintar las distancias
                if(i<(babel.infBas.bas.size()-1)){
                    for(int j=0;j<babel.infBas.bas.get(i).n_distancia();j++){
                        int neig=i+1; // solo la mitad sino duplicamos enlaces y factorizamos la busqueda, cojonudo!
                        boolean pintar_distancia=babel.infBas.bas.get(neig).posBas==babel.infBas.bas.get(i).get_distancia(j);
                        if(!pintar_distancia){
                            boolean cx=!pintar_distancia;  //lo va ha buscar ...
                            while(cx){
                                if(neig==(babel.infBas.bas.size()-1)) cx=false; //para salir  sela lo que sea
                                else{
                                    neig++;
                                    pintar_distancia=babel.infBas.bas.get(neig).posBas==babel.infBas.bas.get(i).get_distancia(j);
                                    cx=!pintar_distancia;
                                }
                            }
                        }
                        if(pintar_distancia){
                            imageGraphics.setColor(colorBorde);
                            int atomo_ini=i;
                            int atomo_fin=neig;
                            imageGraphics.drawLine(babel.infBas.bas.get(atomo_ini).X,babel.infBas.bas.get(atomo_ini).Y,babel.infBas.bas.get(atomo_fin).X,babel.infBas.bas.get(atomo_fin).Y);
                            imageGraphics.drawString(cadena.pasarString(
                                    babel.infBas.d2atomos(babel.infBas.bas.get(atomo_ini),babel.infBas.bas.get(atomo_fin))) ,
                                    (babel.infBas.bas.get(atomo_ini).X+babel.infBas.bas.get(atomo_fin).X)/2,
                                    (babel.infBas.bas.get(atomo_ini).Y+babel.infBas.bas.get(atomo_fin).Y)/2-12);
                        }
                    }
                }
                //esto es para pintar los angulos
                int ntot=babel.infBas.bas.get(i).n_angulos();
                for(int j=0;j<ntot;j+=2){
                    int neig1=0;
                    int neig2=0; // ahora lo hacemos con todos porque tomamos como referencia el del el medio
                    boolean pintar_angulo1=babel.infBas.bas.get(neig1).posBas==babel.infBas.bas.get(i).get_angulo(j);
                    boolean pintar_angulo2=babel.infBas.bas.get(neig2).posBas==babel.infBas.bas.get(i).get_angulo(j+1);
                    if((!pintar_angulo1)||(!pintar_angulo2)){
                        boolean cx=(!pintar_angulo1)||(!pintar_angulo2);  //lo va ha buscar ...
                        while(cx){
                            if(neig1==(babel.infBas.bas.size()-1)||neig2==(babel.infBas.bas.size()-1)) cx=false; //para salir  sela lo que sea
                            else{
                                if(!pintar_angulo1) {
                                    neig1++;
                                    pintar_angulo1=babel.infBas.bas.get(neig1).posBas==babel.infBas.bas.get(i).get_angulo(j);
                                }
                                if(!pintar_angulo2) {
                                    neig2++;
                                    pintar_angulo2=babel.infBas.bas.get(neig2).posBas==babel.infBas.bas.get(i).get_angulo(j+1);
                                }
                                cx=(!pintar_angulo1)||(!pintar_angulo2);
                            }
                        }
                    }
                    if(pintar_angulo1&&pintar_angulo2){
                        imageGraphics.setColor(Color.GRAY);
                        atomo_ini=neig1;
                        atomo_med=i;
                        atomo_fin=neig2;
                        angulo = babel.infBas.anguloAtomos( babel.infBas.bas.get(atomo_ini) , babel.infBas.bas.get(atomo_med), babel.infBas.bas.get(atomo_fin) );
                        imageGraphics.drawLine(babel.infBas.bas.get(atomo_ini).X,babel.infBas.bas.get(atomo_ini).Y,(babel.infBas.bas.get(atomo_med)).X,(babel.infBas.bas.get(atomo_med)).Y);
                        imageGraphics.drawLine((babel.infBas.bas.get(atomo_med)).X,(babel.infBas.bas.get(atomo_med)).Y,babel.infBas.bas.get(atomo_fin).X,babel.infBas.bas.get(atomo_fin).Y);
                        imageGraphics.drawString( cadena.pasarString(angulo) +"º",
                                (babel.infBas.bas.get(atomo_ini).X+(babel.infBas.bas.get(atomo_med)).X+babel.infBas.bas.get(atomo_fin).X)/3,
                                (babel.infBas.bas.get(atomo_ini).Y+(babel.infBas.bas.get(atomo_med)).Y+babel.infBas.bas.get(atomo_fin).Y)/3);
                        R=radio/C3D.a;
                        x1=babel.infBas.bas.get(atomo_ini).R[0]-(babel.infBas.bas.get(atomo_med)).R[0];
                        y1=babel.infBas.bas.get(atomo_ini).R[1]-(babel.infBas.bas.get(atomo_med)).R[1];
                        z1=babel.infBas.bas.get(atomo_ini).R[2]-(babel.infBas.bas.get(atomo_med)).R[2];
                        x2=babel.infBas.bas.get(atomo_fin).R[0]-(babel.infBas.bas.get(atomo_med)).R[0];
                        y2=babel.infBas.bas.get(atomo_fin).R[1]-(babel.infBas.bas.get(atomo_med)).R[1];
                        z2=babel.infBas.bas.get(atomo_fin).R[2]-(babel.infBas.bas.get(atomo_med)).R[2];
                        N1=Math.pow(x1*x1+y1*y1+z1*z1,0.5);
                        N2=Math.pow(x2*x2+y2*y2+z2*z2,0.5);
                        x1/=N1; y1/=N1; z1/=N1;
                        x2/=N2; y2/=N2; z2/=N2;
                        xm=(x1+x2)/2;
                        ym=(y1+y2)/2;
                        zm=(z1+z2)/2;
                        xm=(xm+x1)/2; ym=(ym+y1)/2;zm=(zm+z1)/2;
                        Nm=Math.pow(xm*xm+ym*ym+zm*zm,0.5);
                        x_d=C3D.cX( x1*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],y1*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],z1*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        y_d=C3D.cY( x1*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],y1*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],z1*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        x_old=x_d;
                        y_old=y_d;
                        xm/=Nm;ym/=Nm;zm/=Nm;
                        x_d=C3D.cX( xm*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],ym*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],zm*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        y_d=C3D.cY( xm*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],ym*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],zm*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        imageGraphics.drawLine(x_old,y_old,x_d,y_d);
                        x_old=x_d; y_old=y_d;
                        xm=(x1+x2)/2; ym=(y1+y2)/2;zm=(z1+z2)/2;
                        Nm=Math.pow(xm*xm+ym*ym+zm*zm,0.5);
                        xm/=Nm;ym/=Nm;zm/=Nm;
                        x_d=C3D.cX( xm*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],ym*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],zm*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        y_d=C3D.cY( xm*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],ym*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],zm*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        imageGraphics.drawLine(x_old,y_old,x_d,y_d);
                        x_old=x_d; y_old=y_d;
                        xm=(xm+x2)/2; ym=(ym+y2)/2;zm=(zm+z2)/2;Nm=Math.pow(xm*xm+ym*ym+zm*zm,0.5);
                        xm/=Nm;ym/=Nm;zm/=Nm;
                        x_d=C3D.cX( xm*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],ym*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],zm*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        y_d=C3D.cY( xm*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],ym*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],zm*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        imageGraphics.drawLine(x_old,y_old,x_d,y_d);
                        x_old=x_d; y_old=y_d;
                        x_d=C3D.cX( x2*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],y2*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],z2*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        y_d=C3D.cY( x2*R+babel.infBas.bas.get(atomo_med).R[0]-centro[0],y2*R+babel.infBas.bas.get(atomo_med).R[1]-centro[1],z2*R+babel.infBas.bas.get(atomo_med).R[2]-centro[2]);
                        imageGraphics.drawLine(x_old,y_old,x_d,y_d);
                    }
                }
                
                if(verIcono){
                    java.io.File dibujo_atomo = new java.io.File(babel.xeorc+"iconos/"+babel.infBas.bas.get(i).symbol+".gif");
                    // System.out.println(plugBabel.homexeo+"/iconos/"+plugBabel.infBas.bas.get(i).Z+".gif");
                    if(!MOUSE_DRAG){
                        if (dibujo_atomo.exists()){
                            try{
                                
                                javax.imageio.stream.FileImageInputStream archivoImagen = new  javax.imageio.stream.FileImageInputStream(dibujo_atomo);
                                BufferedImage atomoBuffered = javax.imageio.ImageIO.read(archivoImagen);
                                java.awt.Graphics atomoGraphics = atomoBuffered.createGraphics();
                                java.awt.image.ImageObserver ob=null;
                                imageGraphics.drawImage(atomoBuffered,babel.infBas.bas.get(i).X-radio/2,  babel.infBas.bas.get(i).Y-radio/2,radio,radio,ob);
                            }catch(Exception e){e.printStackTrace();}
                        }else{//en el caso que no exista el icono lo dibujamos igual
                            imageGraphics.setColor(periodicTable.getColor( babel.infBas.bas.get(i).Z));
                            if(verEscalaGrises) imageGraphics.setColor(cadena.getGris(periodicTable.getColor( babel.infBas.bas.get(i).Z)));
                            if(seeFrag && babel.infBas.bas.get(i).fix) imageGraphics.setColor(Color.BLACK);
                            imageGraphics.fillOval( babel.infBas.bas.get(i).X-radio/2,  babel.infBas.bas.get(i).Y-radio/2,radio,radio);
                            imageGraphics.setColor(colorBorde);
                            if(verEscalaGrises) colorBorde=cadena.getGris(colorBorde);
                            imageGraphics.drawOval( babel.infBas.bas.get(i).X-radio/2,  babel.infBas.bas.get(i).Y-radio/2,radio,radio);
                        }
                    }
                    
                }else{ //ojo si cambias algo, cambia por duplicado !! dos lineas mas arriba
                    imageGraphics.setColor(periodicTable.getColor( babel.infBas.bas.get(i).Z));
                    if(verEscalaGrises) imageGraphics.setColor(cadena.getGris(periodicTable.getColor( babel.infBas.bas.get(i).Z)));
                    if(seeFrag && babel.infBas.bas.get(i).fix) imageGraphics.setColor(Color.BLACK);
                    imageGraphics.fillOval( babel.infBas.bas.get(i).X-radio/2,  babel.infBas.bas.get(i).Y-radio/2,radio,radio);
                    imageGraphics.setColor(colorBorde);
                    if(verEscalaGrises) colorBorde=cadena.getGris(colorBorde);
                    imageGraphics.drawOval( babel.infBas.bas.get(i).X-radio/2,  babel.infBas.bas.get(i).Y-radio/2,radio,radio);
                }
                if(babel.infBas.bas.get(i).selec) {
                    imageGraphics.setColor(new Color(0,150,255,50));
                    imageGraphics.fillOval( babel.infBas.bas.get(i).X-6*radio/8,  babel.infBas.bas.get(i).Y-6*radio/8,6*radio/4,6*radio/4);
                }
                String out="";
                if(verPosiciones) out+=(babel.infBas.bas.get(i).posOut+1)+" ";
                // if(verCargas) out+=(babel.infBas.bas.get(i).Charge)+" ";
                if(verPosiciones){  //|| verCargas
                    imageGraphics.setColor(colorEjes);
                    imageGraphics.setFont(currentFont);
                    imageGraphics.drawString( out , babel.infBas.bas.get(i).X+radio/2+2 , babel.infBas.bas.get(i).Y-radio/2-2);
                }
                if(seeArrows){
                    for(int a=0;a<babel.infBas.bas.get(i).fx.size();a++ ){
                        if(!diffColorArrows)imageGraphics.setColor(colorArrows);
                        else imageGraphics.setColor(new Color(cadena.getColor(a)));
                        Arrow(babel.infBas.bas.get(i).R[0]-centro[0],babel.infBas.bas.get(i).R[1]-centro[1],babel.infBas.bas.get(i).R[2]-centro[2],
                                babel.infBas.bas.get(i).R[0]+babel.infBas.bas.get(i).fx.get(a)*rescalateArrow-centro[0],
                                babel.infBas.bas.get(i).R[1]+babel.infBas.bas.get(i).fy.get(a)*rescalateArrow-centro[1],
                                babel.infBas.bas.get(i).R[2]+babel.infBas.bas.get(i).fz.get(a)*rescalateArrow-centro[2]);
                    }
                }
            }
        }
        if(xyzt)
            for(int i=ipixel;i<Nxyzt;i++)
                if(verBordes || ( superficie.get(i).color >posicion_1 && superficie.get(i).color <posicion_2)){
            imageGraphics.setColor(getColorPixel(superficie.get(i).color));
            putPixel(superficie.get(i).R[0]-centro[0],superficie.get(i).R[1]-centro[1],superficie.get(i).R[2]-centro[2]);
                }
        
        if(xyzTr==true){
            for(int i=0;i<trayectoria.size();i++){
                imageGraphics.setColor(new Color(trayectoria.get(i).color));
                putPixel(trayectoria.get(i).R[0]-centro[0],trayectoria.get(i).R[1]-centro[1],trayectoria.get(i).R[2]-centro[2]);
            }
        }
        imageGraphics.setColor(Color.GREEN);
        if(verdir)  imageGraphics.drawLine(X_mouse_ini,Y_mouse_ini,X_mouse_fin,Y_mouse_fin);
        if(selec){
            int a=(X_mouse_ini<X_mouse_fin)?X_mouse_ini:X_mouse_fin;
            int b=(Y_mouse_ini<Y_mouse_fin)?Y_mouse_ini:Y_mouse_fin;
            imageGraphics.setColor(new Color(0,150,255,90));
            imageGraphics.drawRect(a,b,(int) Math.abs(X_mouse_ini-X_mouse_fin),(int) Math.abs(Y_mouse_ini-Y_mouse_fin));
            imageGraphics.setColor(new Color(0,150,255,50));
            imageGraphics.fillRect(a,b,(int) Math.abs(X_mouse_ini-X_mouse_fin),(int) Math.abs(Y_mouse_ini-Y_mouse_fin));
        }
        imageGraphics.setColor(Color.BLACK);
        if(showMesg) imageGraphics.drawString(mesg,10,10);
        return imageBuffered;
    }
    
    
    void ajustar_centro(){
        firstTime=false;
        for(int i=0;i<3;i++){
            eje[i]=0;
            centro[i]=(babel.infBas.r_max[i]-babel.infBas.r_min[i])/2+babel.infBas.r_min[i]  +  c_out[i];
            eje[i]=(eje[i] > (babel.infBas.r_max[i]-babel.infBas.r_min[i]))? eje[i] : (babel.infBas.r_max[i]-babel.infBas.r_min[i]);
        }
    }
    
    void String3D(String cadena, double x1, double y1, double z1){
        imageGraphics.drawString(cadena,C3D.cX(x1,y1,z1),C3D.cY(x1,y1,z1));
    }
    
    void Line(double x1, double y1,double z1,double x2, double y2,double z2){
        imageGraphics.drawLine(C3D.cX(x1,y1,z1),C3D.cY(x1,y1,z1),C3D.cX(x2,y2,z2),C3D.cY(x2,y2,z2));
    }
    void putPixel(double x1, double y1,double z1){
        imageGraphics.fillOval( C3D.cX(x1,y1,z1),C3D.cY(x1,y1,z1),pixel,pixel);
        // imageGraphics.drawLine(c,C3D.cX(x1,y1,z1),C3D.cY(x1,y1,z1));
    }
    
    int X0,Y0,X1,Y1,Xm,Ym,ax,ay;
    void Arrow(double x1, double y1,double z1,double x2, double y2,double z2){
        X0=C3D.cX(x1,y1,z1);
        Y0=C3D.cY(x1,y1,z1);
        X1=C3D.cX(x2,y2,z2);
        Y1=C3D.cY(x2,y2,z2);
        Xm=X0+(X1-X0)*7/8;
        Ym=Y0+(Y1-Y0)*7/8;
        ax=X1-Xm;
        ay=Y1-Ym;
        imageGraphics.drawLine(X0,Y0,X1,Y1);
        imageGraphics.drawLine(Xm+ay,Ym-ax,X1,Y1);
        imageGraphics.drawLine(Xm-ay,Ym+ax,X1,Y1);
    }
    
    void ejes(){
        C3D.pMax=(C3D.pMax>C3D.c_z(-eje[0]/2,0,0))?C3D.pMax:C3D.c_z(-eje[0]/2,0,0);
        C3D.pMax=(C3D.pMax>C3D.c_z(eje[0]/2,0,0))?C3D.pMax:C3D.c_z(eje[0]/2,0,0);
        C3D.pMax=(C3D.pMax>C3D.c_z(0,-eje[1]/2,0))?C3D.pMax:C3D.c_z(0,-eje[1]/2,0);
        C3D.pMax=(C3D.pMax>C3D.c_z(0,eje[1]/2,0))?C3D.pMax:C3D.c_z(0,eje[1]/2,0);
        C3D.pMax=(C3D.pMax>C3D.c_z(0,0,-eje[2]/2))?C3D.pMax:C3D.c_z(0,0,-eje[2]/2);
        C3D.pMax=(C3D.pMax>C3D.c_z(0,0,eje[2]/2))?C3D.pMax:C3D.c_z(0,0,eje[2]/2);
        imageGraphics.setFont(currentEjes);
        imageGraphics.setColor(colorEjes);
        Line(-eje[0]/2,0,0,eje[0]/2,0,0);
        Line(0,-eje[1]/2,0,0,eje[1]/2,0);
        Line(0,0,-eje[2]/2,0,0,eje[2]/2);
        String3D("X",eje[0]/2,0,0);
        String3D("Y",0,eje[1]/2,0);
        String3D("Z",0,0,eje[2]/2);
        String3D("-X",-eje[0]/2,0,0);
        String3D("-Y",0,-eje[1]/2,0);
        String3D("-Z",0,0,-eje[2]/2);
    }
    
    void move(double x, double y , double z){
        for(int i=0;i<babel.infBas.bas.size();i++){
            if(babel.infBas.bas.get(i).selec){
                babel.infBas.bas.get(i).R[0]=babel.infBas.bas.get(i).R[0]+x;
                babel.infBas.bas.get(i).R[1]=babel.infBas.bas.get(i).R[1]+y;
                babel.infBas.bas.get(i).R[2]=babel.infBas.bas.get(i).R[2]+z;
            }
        }
    }
    
    void moveAtom(double x){
        double dis=0;
        int imin=0;
        boolean prM=true;
        if(babel.infBas.bas.size()>1)
            for(int i=0;i<babel.infBas.bas.size();i++,prM=true)
                if(babel.infBas.bas.get(i).selec){
            for(int j=0;j<babel.infBas.bas.size();j++)
                if(j!=i){
                if(prM) {
                    dis=babel.infBas.d2atomos(babel.infBas.bas.get(i),babel.infBas.bas.get(j));
                    prM=false;
                }else{
                    if(dis>babel.infBas.d2atomos(babel.infBas.bas.get(i),babel.infBas.bas.get(j))){
                        dis=babel.infBas.d2atomos(babel.infBas.bas.get(i),babel.infBas.bas.get(j));
                        imin=j;
                    }
                }
                }
            for(int h=0;h<3;h++)
                babel.infBas.bas.get(i).R[h]=babel.infBas.bas.get(i).R[h]+
                        (babel.infBas.bas.get(imin).R[h]-babel.infBas.bas.get(i).R[h])/dis*x;
                }
    }
    
    void ordenarVista(){
        //algoritmo de insercion directa
        for(int i=1;i<babel.infBas.bas.size();i++){
            int j=i-1;
            boolean cx=(babel.infBas.bas.get(i).dOb<babel.infBas.bas.get(j).dOb);
            while(cx) {
                if(j==0){
                    cx=(babel.infBas.bas.get(i).dOb<babel.infBas.bas.get(j).dOb);
                    if(cx) babel.infBas.mover_pos(i,0);
                    else babel.infBas.mover_pos(i,1);
                    cx=false;
                }else{
                    j--;
                    cx=(babel.infBas.bas.get(i).dOb<babel.infBas.bas.get(j).dOb);
                    if(!cx) babel.infBas.mover_pos(i,j+1);
                }
            }
        }
    }
    
    void selecAtoms(){
        int c_ini_x=(X_mouse_ini<X_mouse_fin)?X_mouse_ini:X_mouse_fin;
        int c_ini_y=(Y_mouse_ini<Y_mouse_fin)?Y_mouse_ini:Y_mouse_fin;
        int c_fin_x=(X_mouse_ini>X_mouse_fin)?X_mouse_ini:X_mouse_fin;
        int c_fin_y=(Y_mouse_ini>Y_mouse_fin)?Y_mouse_ini:Y_mouse_fin;
        for(int i=0; i<babel.infBas.bas.size();i++)
            if( babel.infBas.bas.get(i).X > c_ini_x &&
                babel.infBas.bas.get(i).X < c_fin_x &&
                babel.infBas.bas.get(i).Y > c_ini_y &&
                babel.infBas.bas.get(i).Y < c_fin_y )
                babel.infBas.bas.get(i).selec=(!babel.infBas.bas.get(i).selec);
    }
    
    //---------MINIEJES------------------
    void MiniLine(double x1, double y1,double z1,double x2, double y2,double z2){
        miniEjesGraphics.drawLine((int)(C3D.c_x(x1,y1,z1)+miniOx/2),(int) (C3D.c_y(x1,y1,z1)+miniOy/2),
                (int) (C3D.c_x(x2,y2,z2)+miniOx/2), (int) (C3D.c_y(x2,y2,z2)+miniOy/2));
    }
    void MiniString3D(String cadena, double x1, double y1, double z1){
        miniEjesGraphics.drawString(cadena,(int)(C3D.c_x(x1,y1,z1)+miniOx/2),(int) (C3D.c_y(x1,y1,z1)+miniOy/2));
    }
    
    BufferedImage pintarMiniEjes(BufferedImage aux){
        miniEjesBuffered=aux;
        //---- dimensiones ----
        miniOx=miniEjesBuffered.getWidth();
        miniOy=miniEjesBuffered.getHeight();
        miniEjesGraphics = miniEjesBuffered.createGraphics();
        
        //--------fondo -------
        miniEjesGraphics.setColor(new Color(255,255,255)); //(new Color(238,238,238)
        miniEjesGraphics.fillRect(0,0,miniOx,miniOy);
        //---------------------
        
        miniEjesGraphics.setFont(currentEjes);
        
        // Cargamos coordenadas
        miniEje[0][0]=(int)(C3D.c_x((miniOx+miniOy)*3/16,0,0)+miniOx/2);
        miniEje[1][0]=(int)(C3D.c_x(-(miniOx+miniOy)*3/16,0,0)+miniOx/2);
        miniEje[2][0]=(int)(C3D.c_x(0,(miniOx+miniOy)*3/16,0)+miniOx/2);
        miniEje[3][0]=(int)(C3D.c_x(0,-(miniOx+miniOy)*3/16,0)+miniOx/2);
        miniEje[4][0]=(int)(C3D.c_x(0,0,(miniOx+miniOy)*3/16)+miniOx/2);
        miniEje[5][0]=(int)(C3D.c_x(0,0,-(miniOx+miniOy)*3/16)+miniOx/2);
        
        miniEje[0][1]=(int)(C3D.c_y((miniOx+miniOy)*3/16,0,0)+miniOy/2);
        miniEje[1][1]=(int)(C3D.c_y(-(miniOx+miniOy)*3/16,0,0)+miniOy/2);
        miniEje[2][1]=(int)(C3D.c_y(0,(miniOx+miniOy)*3/16,0)+miniOy/2);
        miniEje[3][1]=(int)(C3D.c_y(0,-(miniOx+miniOy)*3/16,0)+miniOy/2);
        miniEje[4][1]=(int)(C3D.c_y(0,0,(miniOx+miniOy)*3/16)+miniOy/2);
        miniEje[5][1]=(int)(C3D.c_y(0,0,-(miniOx+miniOy)*3/16)+miniOy/2);
        
        for(int j=0; j<6; j++){
            if(BoolEje[j]) miniEjesGraphics.setColor(Color.RED);
            else miniEjesGraphics.setColor(colorEjes);
            miniEjesGraphics.drawLine(miniOx/2,miniOy/2,miniEje[j][0],miniEje[j][1]);
            miniEjesGraphics.drawString(ejeN[j],miniEje[j][0],miniEje[j][1]);
        }
        
        return miniEjesBuffered;
    }
    
    
    double dC2D(double x1,double y1,double x2,double y2) {
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
    }
    
    void getN(int x,int y){
        double min=Math.pow(10,10);
        int i1=0,i2=0,i3=0;
        BoolEje[0]=BoolEje[1]=BoolEje[2]=BoolEje[3]=BoolEje[4]=BoolEje[5]=false;
        for(int k=0;k<6;k++)
            if(min>dC2D(x,y,miniEje[k][0],miniEje[k][1])){
            min=dC2D(x,y,miniEje[k][0],miniEje[k][1]);
            i1=k;
            }
        BoolEje[i1]=true;
        min=Math.pow(10,10);
        if(nejes==2||nejes==3){
            for(int k=0;k<6;k++)
                if(k!=i1)
                    if(min>dC2D(x,y,miniEje[k][0],miniEje[k][1])){
                min=dC2D(x,y,miniEje[k][0],miniEje[k][1]);
                i2=k;
                    }
            BoolEje[i2]=true;
            min=Math.pow(10,10);
            if(nejes==3){
                for(int k=0;k<6;k++)
                    if(k!=i1&&k!=i2)
                        if(min>dC2D(x,y,miniEje[k][0],miniEje[k][1])){
                    min=dC2D(x,y,miniEje[k][0],miniEje[k][1]);
                    i3=k;
                        }
                BoolEje[i3]=true;
            }
        }
    }
    
    //--------------SUPERFICIE----------------
    void Load_xyzt(){
        double t_min=0,t_max=0;
        if(xyztFile.exists()){
            try{
                String str="";
                java.io.BufferedReader inxyzt = new java.io.BufferedReader(new java.io.FileReader(xyztFile.getAbsolutePath()));
                Nxyzt=0;
                boolean error = false;
                double x=0,y=0,z=0,t=0;
                while ((str = inxyzt.readLine()) != null){
                    try{
                        error=false;
                        x= cadena.readColDouble(1,str);
                        y= cadena.readColDouble(2,str);
                        z= cadena.readColDouble(3,str);
                        if(cadena.nCol(str)>3) t = cadena.readColDouble(4,str);
                        else t = cadena.readColDouble(3,str);
                    } catch(NumberFormatException ex) {error=true;}
                    if(!error){
                        pixel p= new pixel();
                        p.R[0]=x;p.R[1]=y;p.R[2]=z;p.R[3]=t;
                        superficie.add(p);
                        if(Nxyzt==0) t_min=t_max=t;
                        else{
                            t_min=(t_min<t)?t_min:t;
                            t_max=(t_max>t)?t_max:t;
                        }
                        Nxyzt++;
                    }
                }
            }catch (java.io.IOException oe) {System.out.println("error read xyzt");}
            for(int i=0;i<Nxyzt;i++) superficie.get(i).color = (int) ((superficie.get(i).R[3]-t_min)*100/(t_max-t_min));
            //System.out.println(pixel.elementAt(i));
        }
    }
    
    void mover_posSuperficie(int i,int j){
        superficie.add(j,superficie.get(i));
        superficie.remove(i+1);
    }
    
    void ordenarSuperficie(){
        //algoritmo de insercion directa
        for(int i=1;i<superficie.size();i++){
            int j=i-1;
            boolean cx=(superficie.get(i).dOb<superficie.get(j).dOb);
            while(cx) {
                if(j==0){
                    cx=(superficie.get(i).dOb<superficie.get(j).dOb);
                    if(cx) mover_posSuperficie(i,0);
                    else mover_posSuperficie(i,1);
                    cx=false;
                }else{
                    j--;
                    cx=(superficie.get(i).dOb<superficie.get(j).dOb);
                    if(!cx) mover_posSuperficie(i,j+1);
                }
            }
        }
    }
    
    int azul,rojo,verde;
    Color getColorPixel(int C_aux){
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
        return new Color(rojo,verde,azul);
    }
    public class pixel {
        double dOb;
        double R[] = new double[4] ;
        int color; //color = valor entre 0 y 100
        int POS; 
        public pixel(){
        }
    }
    //--------------trayectoria----------------  
    
    void Load_xyzTr(){
        if(xyzFileTr.exists()){
            try{
                trayectoria.clear();
                int [] iatom = new int[cadena.nCol(atoms_xyzTr)+1];
                for(int i=1;i<=cadena.nCol(atoms_xyzTr);i++)
                    iatom[i]=cadena.readColInt(i,atoms_xyzTr);                  
                String str="";
                java.io.BufferedReader inxyzt = new java.io.BufferedReader(new java.io.FileReader(xyzFileTr.getAbsolutePath()));
                int natoms=0;
                String Z=null;
                boolean error = false;
                //  trayectoria.clear();
                double x=0,y=0,z=0;
                int step = 0;
                while ((str = inxyzt.readLine()) != null && step<=trlast ){
                    natoms=cadena.readColInt(1,str);
                    str = inxyzt.readLine(); //segunda linea
                    for(int j=1;j<=natoms;j++){
                        str = inxyzt.readLine();
                        for(int i=1;i<iatom.length;i++)
                            if(iatom[i]==j){
                            if(i==1) step++;
                            if(step>=trfirst && step<=trlast){                                
                               Z = cadena.readColString(1,str);
                               x= cadena.readColDouble(2,str) + ( itr[i]*babel.infBas.lvs[0][0] + jtr[i]*babel.infBas.lvs[1][0] + ktr[i]*babel.infBas.lvs[2][0]) ;
                               y= cadena.readColDouble(3,str) + ( itr[i]*babel.infBas.lvs[0][1] + jtr[i]*babel.infBas.lvs[1][1] + ktr[i]*babel.infBas.lvs[2][1]) ;
                               z= cadena.readColDouble(4,str) + ( itr[i]*babel.infBas.lvs[0][2] + jtr[i]*babel.infBas.lvs[1][2] + ktr[i]*babel.infBas.lvs[2][2]) ;
                               // x = cadena.readColDouble(2,str);
                               // y = cadena.readColDouble(3,str);
                               // z= cadena.readColDouble(4,str);
                               pixel p= new pixel();
                               p.R[0]=x;p.R[1]=y;p.R[2]=z;
                               p.color=cadena.C[i];// babel.periodicTable.getZ(Z);
                               p.POS=iatom[i];
                               trayectoria.add(p);
                               }
                            }
                    }
                }
            }catch (java.io.IOException oe) {System.out.println("error read xyz");}
        }
    }
    
    
}
