
/*
 * toyModel.java
 *
 * Created on 9 de marzo de 2008, 16:49
 * Copyright 2007  by Daniel Gonzalez Trabada
 *
 *  This file is part of fireball-GUI.
 *
 *  fireball-GUI is free software;
 *  you can redistribute it and/or modify it under the terms of the
 *  GNU General Public License as published by the Free Software Foundation;
 *  either version 2 of the License, or (at your option) any later version.
 *
 *   fireball-GUI is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *   or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License along with fireball-GUI;
 *   if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

/**
 *
 * @author dani
 */
package fireball;


public class toyModel extends Thread {
    info_bas infBas = new info_bas();
    reader cadena = new reader();
    periodicTable  periodicTable = new  periodicTable();
    int endStep;
    int step;
    boolean  fixCMS;
    /** Creates a new instance of toyModel */
    
    public toyModel(info_bas aux) {
        infBas=aux;
        endStep=100;
        step=0;
        fixCMS=true;
    }
    public void run() {
        //iniciamos valores
        //   alejar(); //zona no permitida
        //  print();
        // renormalizar enlaces (dobles ...)
        
        infBas.LoadALLVecinosRelax(0,1+20/100,true);
        for(int v1=0;v1<infBas.bas.size();v1++){
            ((atom) infBas.bas.elementAt(v1)).eang=1000;
            ((atom) infBas.bas.elementAt(v1)).epos=1000;
        }
        for(int i=0;i<100;i++) for(int v1=0;v1<infBas.bas.size();v1++) ajustar_giro_atomo(v1);
        
        for(step=0;step<endStep;step++){
            double TOLA=0,TOLP=0;
            double [][] CMS = new double [2][3];
            CMS[0][0]=CMS[0][1]=CMS[0][2]=0;
            CMS[1][0]=CMS[1][1]=CMS[1][2]=0;
            if(fixCMS)
                for(int v1=0;v1<infBas.bas.size();v1++){
                CMS[0][0]+=((atom) infBas.bas.elementAt(v1)).getx()/infBas.bas.size();
                CMS[0][1]+=((atom) infBas.bas.elementAt(v1)).gety()/infBas.bas.size();
                CMS[0][2]+=((atom) infBas.bas.elementAt(v1)).getz()/infBas.bas.size();
                }
            infBas.LoadALLVecinosRelax(0,1+20/100,true);
            for(int v1=0;v1<infBas.bas.size();v1++){
                double aeang=((atom) infBas.bas.elementAt(v1)).eang;
                double aepos=((atom) infBas.bas.elementAt(v1)).epos;
                //LoadRelaxVecinos(v1);
                if(!((atom) infBas.bas.elementAt(v1)).frag) ajustar_xyz_atomo(v1);
                ajustar_giro_atomo(v1);
                TOLA+=Math.abs(((atom) infBas.bas.elementAt(v1)).eang-aeang);
                TOLP+=Math.abs(((atom) infBas.bas.elementAt(v1)).epos-aepos);
            }
            if(fixCMS){
                for(int v1=0;v1<infBas.bas.size();v1++){
                    CMS[1][0]+=((atom) infBas.bas.elementAt(v1)).getx()/infBas.bas.size();
                    CMS[1][1]+=((atom) infBas.bas.elementAt(v1)).gety()/infBas.bas.size();
                    CMS[1][2]+=((atom) infBas.bas.elementAt(v1)).getz()/infBas.bas.size();
                }
                for(int v1=0;v1<infBas.bas.size();v1++){
                    ((atom) infBas.bas.elementAt(v1)).setx(((atom) infBas.bas.elementAt(v1)).getx()+CMS[0][0]-CMS[1][0]);
                    ((atom) infBas.bas.elementAt(v1)).sety(((atom) infBas.bas.elementAt(v1)).gety()+CMS[0][1]-CMS[1][1]);
                    ((atom) infBas.bas.elementAt(v1)).setz(((atom) infBas.bas.elementAt(v1)).getz()+CMS[0][2]-CMS[1][2]);
                }
            }
            System.out.println("step = "+step+"\t"+" TOLP = "+TOLP+"\t"+" TOLA = "+TOLA);
            //  print();
        }
         
        
    }
   
        
    // giros de euler; R3*R1*R3
    double R3X(double a, double x,double y,double z){return x*Math.cos(a)-y*Math.sin(a);}
    double R3Y(double a, double x,double y,double z){return x*Math.sin(a)+y*Math.cos(a);}
    double R3Z(double a, double x,double y,double z){return z;}
    
    double R1X(double a, double x,double y,double z){return x; }
    double R1Y(double a, double x,double y,double z){return y*Math.cos(a)-z*Math.sin(a);}
    double R1Z(double a, double x,double y,double z){return y*Math.sin(a)+z*Math.cos(a);}
            
    
    void mover_atomo(int iatom,double x,double y,double z){
        ((atom)infBas.bas.elementAt(iatom)).setx(x);
        ((atom)infBas.bas.elementAt(iatom)).sety(y);
        ((atom)infBas.bas.elementAt(iatom)).setz(z);
    }
               
    double pot(double a0,double a1, double a2, int v0,double x0,double y0,double z0,int v1,double x1,double y1,double z1){
        double aux=0;
        double uf=0,vf=0;
        int pares[] = new int[2];
        double ang[][] = new double[2][3];
        pares[0]=((atom) infBas.bas.elementAt(v0)).n_pares();
        pares[1]=((atom) infBas.bas.elementAt(v1)).n_pares();
        double [] dc = new double[2];
        dc[0]=periodicTable.getRadio_100(((atom)infBas.bas.elementAt(v0)).getZ())/510/2;
        dc[1]=periodicTable.getRadio_100(((atom)infBas.bas.elementAt(v1)).getZ())/510/2;
        
        
        for(int j=0;j<3;j++)ang[0][j]=((atom) infBas.bas.elementAt(v0)).ang[j];
        for(int j=0;j<3;j++)ang[1][j]=((atom) infBas.bas.elementAt(v1)).ang[j];
        
        double [][][] pos = new double[2][3][4]; //maximo 4 orbitales hibridos
        
        int [] n_orbitales = new int[2];
        
        for(int i=0;i<2;i++){
            n_orbitales[i]=1;
            double [][] v = new double[3][4];  //maximo 4 orbitales hibridos
            if(pares[i]==1 || pares[i]>4){ //s o p
                n_orbitales[i]=1;
                v[0][0]= 1    ; v[1][0]= 0   ; v[2][0]= 0   ;
            }
            if(pares[i]==2){ //sp
                n_orbitales[i]=2;
                v[0][0]= 1    ; v[1][0]= 0   ; v[2][0]= 0   ;
                v[0][1]= -1   ; v[1][1]= 0   ; v[2][1]= 0   ;
            }
            if(pares[i]==3){ //sp2
                n_orbitales[i]=3;
                v[0][0]= 1    ; v[1][0]= 0                   ; v[2][0]= 0   ;
                v[0][1]= -0.5 ; v[1][1]= Math.pow(3,0.5)/2   ; v[2][1]= 0   ;
                v[0][2]= -0.5 ; v[1][2]= -Math.pow(3,0.5)/2  ; v[2][2]= 0   ;
            }
            if(pares[i]==4){ //sp3
                n_orbitales[i]=4;
                v[0][0]=  Math.cos(35.25*Math.PI/180)    ; v[1][0]= -Math.sin(35.25*Math.PI/180)   ; v[2][0]= 0   ;
                v[0][1]= -Math.cos(35.25*Math.PI/180)    ; v[1][1]= -Math.sin(35.25*Math.PI/180)   ; v[2][1]= 0   ;
                v[0][2]=  0                              ; v[1][2]= Math.sin(35.25*Math.PI/180)    ; v[2][2]= Math.cos(35.25*Math.PI/180)   ;
                v[0][3]=  0                              ; v[1][3]= Math.sin(35.25*Math.PI/180)    ; v[2][3]=-Math.cos(35.25*Math.PI/180)   ;
            }
            for(int h=0;h<n_orbitales[i];h++){  //R3*R1*R3
                
                double ex3=R3X(a2,v[0][h],v[1][h],v[2][h]);
                double ey3=R3Y(a2,v[0][h],v[1][h],v[2][h]);
                double ez3=R3Z(a2,v[0][h],v[1][h],v[2][h]);
                
                double ex2=R1X(a1,ex3,ey3,ez3);
                double ey2=R1Y(a1,ex3,ey3,ez3);
                double ez2=R1Z(a1,ex3,ey3,ez3);
                
                pos[i][0][h]=dc[i]*R3X(a0,ex2,ey2,ez2);
                pos[i][1][h]=dc[i]*R3Y(a0,ex2,ey2,ez2);
                pos[i][2][h]=dc[i]*R3Z(a0,ex2,ey2,ez2);
            }
        }
        
        
        //miramos orbitales que estan mas cerca
        double d=0,d_min=0;
        int o1=0,o2=0;
        for(int i=0;i<n_orbitales[0];i++)
            for(int j=0;j<n_orbitales[1];j++){
            d=infBas.distancia(x0+pos[0][0][i],y0+pos[0][1][i],z0+pos[0][2][i],
                    x1+pos[1][0][j],y1+pos[1][1][j],z1+pos[1][2][j]);
            if(i==0&&j==0) d_min=d;
            else if(d<d_min){
                o1=i;
                o2=j;
                d_min=d;
            }
            }
      
        d=infBas.distancia(x0,y0,z0,x1,y1,z1);
        double DC=dc[0]+dc[1];
        aux=6*Math.pow(1-Math.exp(-1.5*(d-DC)),2)-6;
        
        return aux;
    }
    
    void  load_esqueleto(){
        infBas.esqueleto.clear();
        for(int v0=0;v0<infBas.bas.size();v0++){
            double dc=periodicTable.getRadio_100(((atom)infBas.bas.elementAt(v0)).getZ());
            dc/=1020;
            double [] ang = new double[3];
            for(int j=0;j<3;j++)ang[j]=((atom) infBas.bas.elementAt(v0)).ang[j];
            
            double [][] pos = new double[3][4];
            int n_orbitales=1;
            double [][] v = new double[3][4];  //maximo 4 orbitales hibridos
            if(((atom) infBas.bas.elementAt(v0)).n_pares()==1 || ((atom) infBas.bas.elementAt(v0)).n_pares()>4){ //s o p
                n_orbitales=1;
                v[0][0]= 1    ; v[1][0]= 0   ; v[2][0]= 0   ;
            }
            if(((atom) infBas.bas.elementAt(v0)).n_pares()==2){ //sp
                n_orbitales=2;
                v[0][0]= 1    ; v[1][0]= 0   ; v[2][0]= 0   ;
                v[0][1]= -1   ; v[1][1]= 0   ; v[2][1]= 0   ;
            }
            if(((atom) infBas.bas.elementAt(v0)).n_pares()==3){ //sp2
                n_orbitales=3;
                v[0][0]= 1    ; v[1][0]= 0                   ; v[2][0]= 0   ;
                v[0][1]= -0.5 ; v[1][1]=  Math.pow(3,0.5)/2  ; v[2][1]= 0   ;
                v[0][2]= -0.5 ; v[1][2]= -Math.pow(3,0.5)/2  ; v[2][2]= 0   ;
            }
            if(((atom) infBas.bas.elementAt(v0)).n_pares()==4){ //sp3
                n_orbitales=4;
                v[0][0]=  Math.cos(35.25*Math.PI/180)    ; v[1][0]= -Math.sin(35.25*Math.PI/180)   ; v[2][0]= 0   ;
                v[0][1]= -Math.cos(35.25*Math.PI/180)    ; v[1][1]= -Math.sin(35.25*Math.PI/180)   ; v[2][1]= 0   ;
                v[0][2]=  0                              ; v[1][2]= Math.sin(35.25*Math.PI/180)    ; v[2][2]= Math.cos(35.25*Math.PI/180)   ;
                v[0][3]=  0                              ; v[1][3]= Math.sin(35.25*Math.PI/180)    ; v[2][3]=-Math.cos(35.25*Math.PI/180)   ;
            }
            for(int h=0;h<n_orbitales;h++){  //R3*R1*R3
                
                double ex3=R3X(ang[2],v[0][h],v[1][h],v[2][h]);
                double ey3=R3Y(ang[2],v[0][h],v[1][h],v[2][h]);
                double ez3=R3Z(ang[2],v[0][h],v[1][h],v[2][h]);
                
                double ex2=R1X(ang[1],ex3,ey3,ez3);
                double ey2=R1Y(ang[1],ex3,ey3,ez3);
                double ez2=R1Z(ang[1],ex3,ey3,ez3);
                
                pos[0][h]=dc*R3X(ang[0],ex2,ey2,ez2);
                pos[1][h]=dc*R3Y(ang[0],ex2,ey2,ez2);
                pos[2][h]=dc*R3Z(ang[0],ex2,ey2,ez2);
            }
            String ss=((atom)infBas.bas.elementAt(v0)).getx()+" "+((atom)infBas.bas.elementAt(v0)).gety()+" "+((atom)infBas.bas.elementAt(v0)).getz()+" ";
            for(int i=0;i<n_orbitales ;i++) ss+=(((atom)infBas.bas.elementAt(v0)).getx()+pos[0][i])+" "+(((atom)infBas.bas.elementAt(v0)).gety()+pos[1][i])+" "+(((atom)infBas.bas.elementAt(v0)).getz()+pos[2][i])+" ";
            infBas.esqueleto.add(ss);
            //  for(int i=0;i<n_orbitales;i++) System.out.println(pos[0][i]+" "+pos[1][i]+" "+pos[2][i]+" "+((atom)infBas.bas.elementAt(v0)).getPosBas());
        }
    }
    
    void ajustar_giro_atomo(int v1){
        double xv,yv,zv,xi,yi,zi;
        double [] ang = new double[3];
        for(int j=0;j<3;j++) ang[j]=((atom) infBas.bas.elementAt(v1)).ang[j];
        xi=((atom) infBas.bas.elementAt(v1)).getx();
        yi=((atom) infBas.bas.elementAt(v1)).gety();
        zi=((atom) infBas.bas.elementAt(v1)).getz();
        double a0=Math.random()*2*Math.PI;
        double a1=Math.random()*2*Math.PI;
        double a2=Math.random()*2*Math.PI;
        double aux=0;
        for(int i=1;i<=((atom) infBas.bas.elementAt(v1)).n_vecinos;i++) {
            int v2=((atom) infBas.bas.elementAt(v1)).getVecino(i);
            //v1 con vecinos v2 con posicion:
            xv=((atom) infBas.bas.elementAt(v1)).getVecinoX(i);
            yv=((atom) infBas.bas.elementAt(v1)).getVecinoY(i);
            zv=((atom) infBas.bas.elementAt(v1)).getVecinoZ(i);
            aux+=pot(a0,a1,a2,v1,xi,yi,zi,v2,xv,yv,zv);
        }
        if( ((atom) infBas.bas.elementAt(v1)).eang>aux){
            ang[0]=a0;
            ang[1]=a1;
            ang[2]=a2;
            ((atom) infBas.bas.elementAt(v1)).eang=aux;
        }
        for(int j=0;j<3;j++)((atom) infBas.bas.elementAt(v1)).ang[j]=ang[j];
    }
    
    void ajustar_xyz_atomo(int v1){
        double xv,yv,zv,xi,yi,zi,x_min=0,y_min=0,z_min=0;
        xi=((atom) infBas.bas.elementAt(v1)).getx();
        yi=((atom) infBas.bas.elementAt(v1)).gety();
        zi=((atom) infBas.bas.elementAt(v1)).getz();
        x_min=xi; y_min=yi; z_min=zi;
        double u=Math.random()*2*Math.PI;
        double v=Math.random()*Math.PI;
        double aux=0;
        double dc=periodicTable.getRadio_100(((atom)infBas.bas.elementAt(v1)).getZ())/510;
        dc/=40 ;
        double xr=xi+dc*Math.sin(u)*Math.cos(v);
        double yr=yi+dc*Math.sin(u)*Math.sin(v);
        double zr=zi+dc*Math.cos(u);
        for(int i=1;i<=((atom) infBas.bas.elementAt(v1)).n_vecinos;i++) {
            int v2=((atom) infBas.bas.elementAt(v1)).getVecino(i);
            //v1 con vecinos v2 con posicion:
            xv=((atom) infBas.bas.elementAt(v1)).getVecinoX(i);
            yv=((atom) infBas.bas.elementAt(v1)).getVecinoY(i);
            zv=((atom) infBas.bas.elementAt(v1)).getVecinoZ(i);
            aux+=pot(((atom) infBas.bas.elementAt(v1)).ang[0],((atom) infBas.bas.elementAt(v1)).ang[1],((atom) infBas.bas.elementAt(v1)).ang[2],
                    v1,xr,yr,zr,v2,xv,yv,zv);
        }
        if( ((atom) infBas.bas.elementAt(v1)).epos>=aux){
            x_min=xr;
            y_min=yr;
            z_min=zr;
            ((atom) infBas.bas.elementAt(v1)).epos=aux;
        }
        mover_atomo(v1,x_min,y_min,z_min);
    }
    
    
    void print(){
        for(int v1=0;v1<infBas.bas.size();v1++) {
            System.out.println("atomo "+(((atom) infBas.bas.elementAt(v1)).posBas+1)+
                    " n_e ="+((atom) infBas.bas.elementAt(v1)).n_electron+" n_hole = "+((atom) infBas.bas.elementAt(v1)).n_hole
                    +" n_vec ="+((atom) infBas.bas.elementAt(v1)).n_vecinos+" n_pares="+((atom) infBas.bas.elementAt(v1)).n_pares()  );
            for(int i=1;i<=((atom) infBas.bas.elementAt(v1)).n_vecinos;i++)
                System.out.println(((atom) infBas.bas.elementAt(v1)).getVecino(i)+" "+((atom) infBas.bas.elementAt(v1)).getVecinoAtrak(i) );
        }
    }
    
//--------------------------------------------------------------------------
//---------------------------------------------------------------------------
   
}
