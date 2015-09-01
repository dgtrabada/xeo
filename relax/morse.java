/*
 * morse.java
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

package fireball;
import java.io.*;
/**
 *
 * @author dani
 */
public class morse  extends Thread{
   
    info_bas infBas = new info_bas();
    reader cadena = new reader();
    periodicTable  periodicTable = new  periodicTable();
    int endStep;
    int step;
    //double ETOT,K,etot_old,FTOT;
    FileOutputStream  archivo;
    DataOutputStream out;
    double dt;
    String outFilename;
   
    /** Creates a new instance of morse */
    public morse(info_bas aux) {
        
        infBas=aux;
        outFilename="answer.xyz";
        endStep=100;
        step=0;
        //   ETOT=0;
        //     FTOT=0;
        dt=0.5;
        try{
            archivo= new FileOutputStream(new File(infBas.path+"/"+outFilename));
            out = new DataOutputStream(archivo);
        }catch(Exception erx){;}
        
    }
    
    double V(double d,double dc,double atrac){
        // return 6*Math.pow(1-Math.exp(-1.5*(d-dc)),2)-6;
        double k=2;
        double VD=6;
        return VD*(Math.exp(-2*k*(d-dc))-2*atrac*Math.exp(-k*(d-dc)));
    }
    
    double F(double d,double dc,double A,double atrac){
        double k=2;
        double VD=6;
        return 2*VD*k*(Math.exp(-2*k*(d-dc))-atrac*Math.exp(-k*(d-dc)))*A/d;
    }
    double distancia(double x1,double y1,double z1,double x2,double y2,double z2){
        return Math.pow( Math.pow(x1-x2,2)+ Math.pow(y1-y2,2)+ Math.pow(z1-z2,2)  ,0.5);
    }
    
    void LoadVecinos(){
        infBas.LoadALLVecinosRelax(0,0.5,true);
        infBas.LoadALLVecinosRelax(0.5,1,false);
        infBas.LoadALLVecinosRelax(1,1.5,false);
        infBas.LoadALLVecinosRelax(1.5,2,false);
        infBas.LoadBondsDoble();
        infBas.LoadALLVecinosRelax(2,6,false);
    }
    
    public void run(){
        inicializar();
        for(step=0;step<endStep;step++){
            LoadEnergyForces();
            for(int v1=0;v1<infBas.bas.size();v1++){
                double m=((atom) infBas.bas.elementAt(v1)).getZ()*2; //la masa es apox 2*Z
                if(!((atom) infBas.bas.elementAt(v1)).frag)
                    for(int i=0;i<2;i++){
                    ((atom) infBas.bas.elementAt(v1)).ratom[i][0]= ((atom) infBas.bas.elementAt(v1)).ratom[i][0]+ ((atom) infBas.bas.elementAt(v1)).ratom[i][1]*dt+0.5*((atom) infBas.bas.elementAt(v1)).ratom[i][2]*dt*dt/m;
                    ((atom) infBas.bas.elementAt(v1)).ratom[i][1]= ((atom) infBas.bas.elementAt(v1)).ratom[i][1]+ 0.5*dt*(((atom) infBas.bas.elementAt(v1)).ratom[i][2]/m);
                    }
            //------------
            ((atom) infBas.bas.elementAt(v1)).setx(((atom) infBas.bas.elementAt(v1)).ratom[0][0]);
            ((atom) infBas.bas.elementAt(v1)).sety(((atom) infBas.bas.elementAt(v1)).ratom[1][0]);
            ((atom) infBas.bas.elementAt(v1)).setz(((atom) infBas.bas.elementAt(v1)).ratom[2][0]);
            //------------
            }         
            write_answer();

        }           
    }
    
    void write_answer(){
        try{
            out.writeBytes(infBas.bas.size()+"\n"+"\n");
            for(int i=0;i<infBas.bas.size();i++){
                out.writeBytes(
                        cadena.format(2,periodicTable.getSymbol(((atom)infBas.bas.elementAt(i)).getZ()))
                        +cadena.formatFortran(2,12,6,((atom) infBas.bas.elementAt(i)).ratom[0][0])
                        +cadena.formatFortran(2,12,6,((atom) infBas.bas.elementAt(i)).ratom[1][0])
                        +cadena.formatFortran(2,12,6,((atom) infBas.bas.elementAt(i)).ratom[2][0])+"\n") ;
            }
        }catch(Exception erx){System.out.println("error in write answer.xyz: "); }
    }
    
    void inicializar(){
        for(int v1=0;v1<infBas.bas.size();v1++){
            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    ((atom) infBas.bas.elementAt(v1)).ratom[i][j]=0;
            ((atom) infBas.bas.elementAt(v1)).ratom[0][0]=((atom) infBas.bas.elementAt(v1)).getx();
            ((atom) infBas.bas.elementAt(v1)).ratom[1][0]=((atom) infBas.bas.elementAt(v1)).gety();
            ((atom) infBas.bas.elementAt(v1)).ratom[2][0]=((atom) infBas.bas.elementAt(v1)).getz();
        }
    }
    
    void LoadEnergyForces(){
        double x1,y1,z1,x2,y2,z2,dc;
        //    ETOT=0;K=0;FTOT=0;
        for(int v1=0;v1<infBas.bas.size();v1++){
            ((atom) infBas.bas.elementAt(v1)).ETOT[1]=((atom) infBas.bas.elementAt(v1)).ETOT[0];
            ((atom) infBas.bas.elementAt(v1)).ETOT[0]=0;
            ((atom) infBas.bas.elementAt(v1)).K=0;
            ((atom) infBas.bas.elementAt(v1)).FTOT=0;
        }
        
        for(int v1=0;v1<infBas.bas.size();v1++)
            for(int i=0;i<3;i++)
                ((atom) infBas.bas.elementAt(v1)).ratom[i][2]=0;
        
        LoadVecinos();
        for(int v1=0;v1<infBas.bas.size();v1++){
            x1=((atom) infBas.bas.elementAt(v1)).ratom[0][0];
            y1=((atom) infBas.bas.elementAt(v1)).ratom[1][0];
            z1=((atom) infBas.bas.elementAt(v1)).ratom[2][0];
            for(int i=1;i<=((atom) infBas.bas.elementAt(v1)).n_vecinos;i++) {
                int v2=((atom) infBas.bas.elementAt(v1)).getVecino(i);
                dc=(periodicTable.getRadio_100(((atom)infBas.bas.elementAt(v2)).getZ())+periodicTable.getRadio_100(((atom)infBas.bas.elementAt(v1)).getZ()))/2/510;
                x2=((atom) infBas.bas.elementAt(v1)).getVecinoX(i);
                y2=((atom) infBas.bas.elementAt(v1)).getVecinoY(i);
                z2=((atom) infBas.bas.elementAt(v1)).getVecinoZ(i);           
                 double atrac=((atom) infBas.bas.elementAt(v1)).getAtra(i);                
                ((atom) infBas.bas.elementAt(v1)).ETOT[0]+=V(distancia(x1,y1,z1,x2,y2,z2),dc,atrac);
                //ETOT=((atom) infBas.bas.elementAt(v1)).ETOT[0];
                //ETOT+=V(distancia(x1,y1,z1,x2,y2,z2),dc);                
                ((atom) infBas.bas.elementAt(v1)).ratom[0][2]+=F(distancia(x1,y1,z1,x2,y2,z2),dc,x1-x2,atrac);
                ((atom) infBas.bas.elementAt(v1)).ratom[1][2]+=F(distancia(x1,y1,z1,x2,y2,z2),dc,y1-y2,atrac);
                ((atom) infBas.bas.elementAt(v1)).ratom[2][2]+=F(distancia(x1,y1,z1,x2,y2,z2),dc,z1-z2,atrac);
            }
            ((atom) infBas.bas.elementAt(v1)).K=0.5*(((atom) infBas.bas.elementAt(v1)).getZ()*2)*(Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[0][1],2)+Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[1][1],2)+Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[2][1],2));
            ((atom) infBas.bas.elementAt(v1)).FTOT=Math.pow((Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[0][2],2)+Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[1][2],2)+Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[2][2],2)),0.5);
         
       //    if(step>1 &&  ((atom) infBas.bas.elementAt(v1)).ETOT[1] <  ((atom) infBas.bas.elementAt(v1)).ETOT[0])
       //         for(int i=0;i<3;i++)
       //             ((atom) infBas.bas.elementAt(v1)).ratom[i][1]=0;
       //     ((atom) infBas.bas.elementAt(v1)).ETOT[1] =  ((atom) infBas.bas.elementAt(v1)).ETOT[0];
         
           //   System.out.println("step = "+step+" atom "+(((atom) infBas.bas.elementAt(v1)).posBas+1)+" n_vecinos = "+((atom) infBas.bas.elementAt(v1)).n_vecinos+"  ETOT = "+((atom) infBas.bas.elementAt(v1)).ETOT[0]+"  K = "+((atom) infBas.bas.elementAt(v1)).K+" FTOT = "+((atom) infBas.bas.elementAt(v1)).FTOT
         //           +" fx = "+((atom) infBas.bas.elementAt(v1)).ratom[0][2]+" fy = "+((atom) infBas.bas.elementAt(v1)).ratom[1][2]+" fz = "+((atom) infBas.bas.elementAt(v1)).ratom[2][2] );
        }
        double etot=0,etotOld=0,K=0,FTOT=0;
        for(int v1=0;v1<infBas.bas.size();v1++){
            etot+=   ((atom) infBas.bas.elementAt(v1)).ETOT[0] ;
            etotOld+=   ((atom) infBas.bas.elementAt(v1)).ETOT[1] ;
            K+= ((atom) infBas.bas.elementAt(v1)).K;
            FTOT+= ((atom) infBas.bas.elementAt(v1)).FTOT;
        }
        if(step>1 && etotOld < etot){
                for(int v1=0;v1<infBas.bas.size();v1++)
                for(int i=0;i<3;i++)
                    ((atom) infBas.bas.elementAt(v1)).ratom[i][1]=0;
        }
        System.out.println("step = "+step+"  ETOT = "+etot+" K = "+K+" FTOT = "+FTOT);
        
      //  if(step>1 && etot_old < ETOT) {
      //      for(int v1=0;v1<infBas.bas.size();v1++)
      //          for(int i=0;i<3;i++)
     //               ((atom) infBas.bas.elementAt(v1)).ratom[i][1]=0;
           // System.out.println("queching");
     //   }
         
    //    for(int v1=0;v1<infBas.bas.size();v1++) {
    //        K+=0.5*(((atom) infBas.bas.elementAt(v1)).getZ()*2)*(Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[0][1],2)+Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[1][1],2)+Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[2][1],2));
    //        FTOT+=Math.pow((Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[0][2],2)+Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[1][2],2)+Math.pow(((atom) infBas.bas.elementAt(v1)).ratom[2][2],2)),0.5);
   //     }
   //     System.out.println("step = "+step+"  ETOT = "+ETOT+"  K = "+K+" FTOT = "+FTOT);
         
         
    //    etot_old=ETOT;
         
    }
    
    
   /*
           System.out.println("ETOT ="+ETOT);
           for(int v1=0;v1<infBas.bas.size();v1++){
           System.out.println((((atom) infBas.bas.elementAt(v1)).getPosBas()+1)+" "+
           ((atom) infBas.bas.elementAt(v1)).ratom[0][2]+" "+((atom) infBas.bas.elementAt(v1)).ratom[1][2]+" "+((atom) infBas.bas.elementAt(v1)).ratom[2][2]);
           }
      */
}
