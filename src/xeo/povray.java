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

import java.io.File; 
import java.io.IOException;
import java.awt.Color;
import java.util.ArrayList;

public class povray {
    public class pixel {
        double dOb;
        double R[] = new double[4] ;
        int color; //color = valor entre 0 y 100
        public pixel(){
        }
    }
    String path;
    int Height;
    int Width;
    info_bas infBas = new info_bas();
    mol pintarMol  = new mol();
    double angle,antialias,rad,radSelected,bonds;
    rotar rotar= new rotar();
    rotar rotar1= new rotar();
    rotar rotar2= new rotar();
    boolean use_radio,use_radio_selected;
    boolean ver_flecha;
    boolean ver_flechaDiffColor;
    boolean ver_enlaces;
    boolean ver_trayectoria;
    ArrayList<pixel> trayectoria;
    reader.reader cadena = new reader.reader() ;
    Color colorfondo;
    Color colorArrow;
    File inifile;
    File povfile;
    File picture;
    C3D C3D = new C3D();
    double a;
    boolean Pause_When_Done;
    boolean display;
    Runtime run;
    Process proc;
    double res;
    float red,green,blue;
    float red1,green1,blue1;
    float red2,green2,blue2;
    float re;
    float radio;
    String Trans, SEP;
    int fr;
    boolean transparente;
    /** Creates a new instance of povray */
    public povray() {
        path="./";
        Height=100;
        Width=100;
        use_radio=false;
        use_radio_selected=false;
        ver_flecha=false;
        ver_flechaDiffColor=false;
        rad=1;
        radSelected=1;
        antialias=0.1;
        a=10;
        angle=5;
        bonds=1;
        ver_enlaces=true;
        ver_trayectoria=false;
        colorfondo=colorfondo.BLACK;
        Pause_When_Done=false;
        display=false;
        run = Runtime.getRuntime();
        res=1;
        trayectoria = new ArrayList();
        SEP = System.getProperty("file.separator");
        Trans="0.6";
        fr=1;
        transparente=false;
    }
    
    public void loadTR(double R0, double R1,double R2 ,int color, double dOb){
    pixel pixeaux;
    pixeaux = new pixel();
    pixeaux.R[0]=R0;
    pixeaux.R[1]=R1;
    pixeaux.R[2]=R2;
    pixeaux.dOb=dOb;
    pixeaux.color=color;
    trayectoria.add(pixeaux);        
    }
    void write_files(){
        fr++;
        inifile = new File(path+SEP+"povray.ini");
        picture = new File(path+SEP+"out"+fr+".png");
        povfile = new File(path+SEP+"povray.pov");
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inifile) ;
            java.io.DataOutputStream in = new java.io.DataOutputStream(archivo);
            in.writeBytes("Input_File_Name=povray.pov"+"\n");
            in.writeBytes("Output_to_File=true"+"\n");
            in.writeBytes("Output_File_Type=N"+"\n");
            in.writeBytes("Output_File_Name=out"+fr+"\n");
            in.writeBytes("Height="+Height+"\n");
            in.writeBytes("Width="+Width+"\n");
            in.writeBytes("Display="+display+"\n");
            in.writeBytes("Pause_When_Done="+Pause_When_Done+"\n");
            in.writeBytes("Antialias=true"+"\n");
            in.writeBytes("Antialias_Threshold="+antialias+"\n");
            in.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error: write povray.ini");}
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(povfile) ;
            java.io.DataOutputStream in = new java.io.DataOutputStream(archivo);
            in.writeBytes("#declare Height="+Height+";\n");
            in.writeBytes("#declare Width="+Width+";\n");
            in.writeBytes("#declare minScreenDimension ="+Height+";\n");
            in.writeBytes("#declare Ratio = Width / Height;"+"\n");
            in.writeBytes("#declare zoom ="+a*100+";\n");
            in.writeBytes("#declare showAtoms = true;"+"\n");
            in.writeBytes("#declare showBonds = true;"+"\n");
            in.writeBytes("#declare showTra = true;"+"\n");
            in.writeBytes("camera{"+"\n");
            in.writeBytes("location < 0, 0, zoom>"+"\n");
            in.writeBytes("right < -Ratio , 0, 0>"+"\n");
            in.writeBytes("angle "+angle+"\n");
            in.writeBytes("look_at < 0, 0, 0 >"+"\n } \n");
            in.writeBytes("background { color rgb<"+colorfondo.getRed()/255.0f+","+colorfondo.getGreen()/255.0f+","+colorfondo.getBlue()/255.0f+"> }"+"\n");
            in.writeBytes("light_source { < 0, 0, zoom>  rgb <1.0,1.0,1.0> }"+"\n");
            in.writeBytes("light_source { < -zoom, zoom, zoom>  rgb <1.0,1.0,1.0> }"+"\n");
            in.writeBytes("#default { finish {"+"\n");
            in.writeBytes("ambient .2 diffuse .6 specular 1 roughness .001 metallic}}"+"\n");
            in.writeBytes("#macro atom(X,Y,Z,RADIUS,R,G,B)"+"\n");
            in.writeBytes("sphere{<X,Y,Z>,RADIUS"+"\n");
            in.writeBytes("pigment{rgb<R,G,B>}}"+"\n");
            in.writeBytes("#end"+"\n");
            in.writeBytes("#macro atomT(X,Y,Z,RADIUS,R,G,B)"+"\n");
            in.writeBytes("sphere{<X,Y,Z>,RADIUS"+"\n");
            in.writeBytes("pigment{color rgb<R,G,B> transmit "+Trans+"}}"+"\n");
            in.writeBytes("#end"+"\n");
            in.writeBytes("#macro bond(X1,Y1,Z1,X2,Y2,Z2,RADIUS,R1,G1,B1,R2,G2,B2)"+"\n");
            in.writeBytes("#local xc = (X1 + X2) / 2;"+"\n");
            in.writeBytes("#local yc = (Y1 + Y2) / 2;"+"\n");
            in.writeBytes("#local zc = (Z1 + Z2) / 2;"+"\n");
            in.writeBytes("cylinder{<X1,Y1,Z1>,<xc,yc,zc>,RADIUS"+"\n");
            in.writeBytes("pigment{rgb<R1,G1,B1>}}"+"\n");
            in.writeBytes("cylinder{<xc,yc,zc>,<X2,Y2,Z2>,RADIUS"+"\n");
            in.writeBytes("pigment{rgb<R2,G2,B2>}}"+"\n");
            in.writeBytes("sphere{<X1,Y1,Z1>,RADIUS"+"\n");
            in.writeBytes("pigment{rgb<R1,G1,B1>}}"+"\n");
            in.writeBytes("sphere{<X2,Y2,Z2>,RADIUS"+"\n");
            in.writeBytes("pigment{rgb<R2,G2,B2>}}"+"\n");
            in.writeBytes("#end"+"\n");
            in.writeBytes("#macro bondT(X1,Y1,Z1,X2,Y2,Z2,RADIUS,R1,G1,B1,R2,G2,B2)"+"\n");
            in.writeBytes("#local xc = (X1 + X2) / 2;"+"\n");
            in.writeBytes("#local yc = (Y1 + Y2) / 2;"+"\n");
            in.writeBytes("#local zc = (Z1 + Z2) / 2;"+"\n");
            in.writeBytes("cylinder{<X1,Y1,Z1>,<xc,yc,zc>,RADIUS"+"\n");
            in.writeBytes("pigment{color rgb<R1,G1,B1> transmit "+Trans+"}}"+"\n");
            in.writeBytes("cylinder{<xc,yc,zc>,<X2,Y2,Z2>,RADIUS"+"\n");
            in.writeBytes("pigment{color rgb<R2,G2,B2> transmit "+Trans+"}}"+"\n");
            in.writeBytes("sphere{<X1,Y1,Z1>,RADIUS"+"\n");
            in.writeBytes("pigment{color rgb<R1,G1,B1> transmit "+Trans+"}}"+"\n");
            in.writeBytes("sphere{<X2,Y2,Z2>,RADIUS"+"\n");
            in.writeBytes("pigment{color rgb<R2,G2,B2> transmit "+Trans+"}}"+"\n");
            in.writeBytes("#end"+"\n");
            in.writeBytes("#macro arrow(X1,Y1,Z1,X2,Y2,Z2,RADIUS,R,G,B)"+"\n");
            in.writeBytes("#local xc =X1 + (X2 - X1)*6/8;"+"\n");
            in.writeBytes("#local yc =Y1 + (Y2 - Y1)*6/8;"+"\n");
            in.writeBytes("#local zc =Z1 + (Z2 - Z1)*6/8;"+"\n");
            in.writeBytes("union{"+"\n");
            in.writeBytes("cylinder{<X1,Y1,Z1>,<xc,yc,zc>,RADIUS"+"\n");
            in.writeBytes("pigment{rgb<R,G,B>}}"+"\n");
            in.writeBytes("cone {"+"\n");
            in.writeBytes("<xc,yc,zc>,2*RADIUS"+"\n");
            in.writeBytes("<X2,Y2,Z2>,0"+"\n");
            in.writeBytes("pigment{rgb<R,G,B>}}"+"\n");
            in.writeBytes("}"+"\n");
            in.writeBytes("#end"+"\n");
            in.writeBytes("#if (showAtoms)"+"\n");
            for(int i=0;i<infBas.bas.size();i++){
                rotar.Rotacion(infBas.bas.get(i).R[0]-(infBas.r_max[0]-infBas.r_min[0])/2-infBas.r_min[0],
                        infBas.bas.get(i).R[1]-(infBas.r_max[1]-infBas.r_min[1])/2-infBas.r_min[1],
                        infBas.bas.get(i).R[2]-(infBas.r_max[2]-infBas.r_min[2])/2-infBas.r_min[2]);
                Color colorAt=infBas.periodicTable.getColor(infBas.bas.get(i).Z);
                red=colorAt.getRed()/255.0f;
                green=colorAt.getGreen()/255.0f;
                blue=colorAt.getBlue()/255.0f;
                //hacemos que el radio del H=0.25 y el resto 0.5, si quieres ver proporciones descomentar la siguiente linea
                radio=1;
                if(use_radio) radio=(float) ((infBas.periodicTable.getRadio_100(infBas.bas.get(i).Z)*1.0f)*6/10000*rad);
                else radio=(infBas.bas.get(i).Z==1)?0.132f:0.231f;     
                if(use_radio_selected)if(infBas.bas.get(i).selec)radio=(float) ((infBas.periodicTable.getRadio_100(infBas.bas.get(i).Z)*1.0f)*6/10000*radSelected);
                
                if(infBas.bas.get(i).selec && transparente)in.writeBytes("atomT("+rotar.X+","+rotar.Y+","+rotar.Z+","+radio+","+red+","+green+","+blue+")\n" );
                else in.writeBytes("atom("+rotar.X+","+rotar.Y+","+rotar.Z+","+radio+","+red+","+green+","+blue+")\n" );
                if(ver_flecha){
                    for(int j=0;j<infBas.bas.get(i).fx.size();j++){
                        if(!ver_flechaDiffColor){
                            red=colorArrow.getRed()/255.0f;
                            green=colorArrow.getGreen()/255.0f;
                            blue=colorArrow.getBlue()/255.0f;
                        }else{
                            red=new Color( cadena.getColor(j)).getRed()/255.0f;
                            green=new Color(cadena.getColor(j)).getGreen()/255.0f;
                            blue=new Color(cadena.getColor(j)).getBlue()/255.0f;
                        }
                        rotar1.Rotacion(
                                infBas.bas.get(i).R[0]+infBas.bas.get(i).fx.get(j)*res-(infBas.r_max[0]-infBas.r_min[0])/2-infBas.r_min[0],
                                infBas.bas.get(i).R[1]+infBas.bas.get(i).fy.get(j)*res-(infBas.r_max[1]-infBas.r_min[1])/2-infBas.r_min[1],
                                infBas.bas.get(i).R[2]+infBas.bas.get(i).fz.get(j)*res-(infBas.r_max[2]-infBas.r_min[2])/2-infBas.r_min[2]);
                        in.writeBytes("arrow("+rotar.X+","+rotar.Y+","+rotar.Z+","+rotar1.X+","+rotar1.Y+","+rotar1.Z+","+0.058f*bonds+","+red+","+green+","+blue+")\n" );
                    }
                }
            }
            in.writeBytes("#end        "+"\n");
            if(ver_enlaces){
                in.writeBytes("#if (showBonds)"+"\n");
                for(int i=0;i<infBas.bas.size();i++){
                    if(i<(infBas.bas.size()-1)){
                        for(int j=0;j<infBas.bas.get(i).n_enlaces();j++){
                            int neig=i+1; // solo la mitad sino duplicamos enlaces y factorizamos la busqueda, cojonudo!
                            boolean pintar_enlace=infBas.bas.get(neig).posBas==infBas.bas.get(i).get_enlace(j);
                            if(!pintar_enlace){
                                boolean cx=!pintar_enlace;  //lo va ha buscar ...
                                while(cx){
                                    if(neig==(infBas.bas.size()-1)) cx=false; //para salir  sela lo que sea
                                    else{
                                        neig++;
                                        pintar_enlace=infBas.bas.get(neig).posBas==infBas.bas.get(i).get_enlace(j);
                                        cx=!pintar_enlace;
                                    }
                                }
                            }
                            if(pintar_enlace){
                                rotar1.Rotacion(infBas.bas.get(i).R[0]-(infBas.r_max[0]-infBas.r_min[0])/2-infBas.r_min[0],
                                        infBas.bas.get(i).R[1]-(infBas.r_max[1]-infBas.r_min[1])/2-infBas.r_min[1],
                                        infBas.bas.get(i).R[2]-(infBas.r_max[2]-infBas.r_min[2])/2-infBas.r_min[2]);
                                Color colorAt1=infBas.periodicTable.getColor(infBas.bas.get(i).Z);
                                red1=colorAt1.getRed()/255.0f;
                                green1=colorAt1.getGreen()/255.0f;
                                blue1=colorAt1.getBlue()/255.0f;
                                //como arriba con los radios
                                //float radio1=(infBas.periodicTable.getRadio_100(infBas.bas.get(i).Z)*1.0f)*6/10000;
                                rotar2.Rotacion(infBas.bas.get(neig).R[0]-(infBas.r_max[0]-infBas.r_min[0])/2-infBas.r_min[0],
                                        infBas.bas.get(neig).R[1]-(infBas.r_max[1]-infBas.r_min[1])/2-infBas.r_min[1],
                                        infBas.bas.get(neig).R[2]-(infBas.r_max[2]-infBas.r_min[2])/2-infBas.r_min[2]);
                                Color colorAt2=infBas.periodicTable.getColor(infBas.bas.get(neig).Z);
                                red2=colorAt2.getRed()/255.0f;
                                green2=colorAt2.getGreen()/255.0f;
                                blue2=colorAt2.getBlue()/255.0f;
                                //como arriba arriba con los radios :)
                                //float radio2=(infBas.periodicTable.getRadio_100(infBas.bas.get(i).Z)*1.0f)*6/10000;
                                //if(use_bonds) re=(float) (bonds*(radio1+radio2)/10);
                                re=(float) (0.058f*bonds);
                                if(infBas.bas.get(i).selec) in.writeBytes("bondT("+rotar1.X+","+rotar1.Y+","+rotar1.Z+
                                        ","+rotar2.X+","+rotar2.Y+","+rotar2.Z+","+re+","
                                        +red1+","+green1+","+blue1+","+red2+","+green2+","+blue2+")\n");
                                else in.writeBytes("bond("+rotar1.X+","+rotar1.Y+","+rotar1.Z+
                                        ","+rotar2.X+","+rotar2.Y+","+rotar2.Z+","+re+","
                                        +red1+","+green1+","+blue1+","+red2+","+green2+","+blue2+")\n");
                            }
                        }
                    }
                }
                in.writeBytes("#end"+"\n");
            }
            if(ver_trayectoria){
                in.writeBytes("#if (showTra)"+"\n");
                for(int i=0;i<trayectoria.size();i++){
                rotar.Rotacion(trayectoria.get(i).R[0]-(infBas.r_max[0]-infBas.r_min[0])/2-infBas.r_min[0],
                        trayectoria.get(i).R[1]-(infBas.r_max[1]-infBas.r_min[1])/2-infBas.r_min[1],
                        trayectoria.get(i).R[2]-(infBas.r_max[2]-infBas.r_min[2])/2-infBas.r_min[2]);
                Color colorAt= new Color(trayectoria.get(i).color); //infBas.periodicTable.getColor(infBas.bas.get(1).Z);  ///OJO
                red=colorAt.getRed()/255.0f;
                green=colorAt.getGreen()/255.0f;
                blue=colorAt.getBlue()/255.0f;
                //hacemos que el radio del H=0.25 y el resto 0.5, si quieres ver proporciones descomentar la siguiente linea
                radio=1/50.0f;
              //  if(use_radio) radio=(float) ((infBas.periodicTable.getRadio_100(infBas.bas.get(i).Z)*1.0f)*6/10000*rad);
              //  else radio=(infBas.bas.get(i).Z==1)?0.132f:0.231f;             
                in.writeBytes("atom("+rotar.X+","+rotar.Y+","+rotar.Z+","+radio+","+red+","+green+","+blue+")\n" );               
                } in.writeBytes("#end"+"\n");
            }
            in.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error: write povray.pov"+"\n");}
    }
    
    void ejecutar_povray(boolean esperar){
        try{
            // String[] command = {"cd "+path, "povray povray.ini", "mkdir hola"   };
            // if(picture.exists()) picture.delete();
            //  Runtime run = Runtime.getRuntime();
            if(new File(path+SEP+"out"+(fr-1)+".png").exists())new File(path+SEP+"out"+(fr-1)+".png").delete();
            proc = run.exec("povray povray.ini",null,new File(path));
            if(esperar) proc.waitFor(); //esperar hasta empezar el siguiente
        } catch(Exception e){System.out.println("error al crear imagen");}
    }
    
    void delete_files(){
        if(inifile.exists()) inifile.delete();
        if(povfile.exists()) povfile.delete();
    }
    
    void show_file(){
        new editor.editor(path+"/.").openFile(inifile);
        new editor.editor(path+"/.").openFile(povfile);
    }
    
    
    class rotar{
        float X,Y,Z;
        void Rotacion(double ax,double ay,double az){
            X=(float) C3D.c_x(ax,ay,az);
            Y=(float) C3D.c_y(-ax,-ay,-az);
            Z=(float) C3D.c_z(ax,ay,az);
        }
    }
    
}
