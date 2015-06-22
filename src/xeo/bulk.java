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

import jCalc.statistic;

public class bulk {
    double volumen;
    double [][] lvs = new double[4][4];
    String nameFile;
    String lvsFile;
    reader.reader cadena = new reader.reader();
    jCalc.statistic  estadistica = new jCalc.statistic();
    boolean fcc,bcc,usefile,Zincblende;
    String out;
    boolean opt;
    /** Creates a new instance of bulk */
    public bulk() {
        volumen=0;
        nameFile="/.";
        lvsFile="/.";
        for(int i=1;i<=3;i++)
            for(int j=1; j<=3;j++){
            lvs[i][j]=0;
            if(i==j)lvs[i][j]=1;
            }
        bcc=false;
        fcc=true;
        usefile=false;
        opt=true; //por defecto usamos el filtro
    }
    
    void makeBulk() {
        File inputFile=new File(nameFile);
        if(inputFile.exists()) {
            double r_min,e_min;
            String salida="";
            double ddE,dE,B,beta;
            estadistica.Load(inputFile) ;
            if(opt){
                // y=b[1]/x**6+b[2]/x**4+b[3]/x**2+b[4]
                estadistica.Bich();
                salida="("+cadena.formatFortran(0,12,5,estadistica.b[1])+")/x**6 + ("+
                        cadena.formatFortran(0,12,5,estadistica.b[2])+")/x**4 + ("+
                        cadena.formatFortran(0,12,5,estadistica.b[3])+")/x**2 + ("+
                        cadena.formatFortran(0,12,5,estadistica.b[4])+")";
                salida+="\n RMS = "+estadistica.RMS+" % \n";
                //    r_min =Math.pow(6*estadistica.b6,0.5)/Math.pow(-2*estadistica.b4+Math.pow(4*estadistica.b4*estadistica.b4-12*estadistica.b6*estadistica.b2,0.5),0.5);
                
                double xini,xfin,y,x,ymin;
                xini=Double.valueOf(estadistica.X.get(0).toString()).doubleValue();
                xfin=Double.valueOf(estadistica.X.get(estadistica.X.size()-1).toString()).doubleValue();
                ymin=estadistica.b[1]/Math.pow(xini,6)+estadistica.b[2]/Math.pow(xini,4)+estadistica.b[3]/Math.pow(xini,2)+estadistica.b[4];
                r_min=xini;
                
                for(int i=0;i<=2000 ;i++){
                    x=xini+i*(xfin-xini)/2000;
                    y=estadistica.b[1]/Math.pow(x,6)+estadistica.b[2]/Math.pow(x,4)+estadistica.b[3]/Math.pow(x,2)+estadistica.b[4];
                    if(ymin>y) {
                        ymin=y;
                        r_min=x;
                    }
                }
                e_min=estadistica.b[1]/Math.pow(r_min,6)+estadistica.b[2]/Math.pow(r_min,4)+estadistica.b[3]/Math.pow(r_min,2)+estadistica.b[4];
                
                Cristal(r_min);
                dE=-6*estadistica.b[1]/Math.pow(r_min,7)-4*estadistica.b[2]/Math.pow(r_min,5)-2*estadistica.b[3]/Math.pow(r_min,3);
                ddE=6*7*estadistica.b[1]/Math.pow(r_min,8)+4*5*estadistica.b[2]/Math.pow(r_min,6)+6*estadistica.b[3]/Math.pow(r_min,4);
                
            } else {
                // y=b1 + b2*x +b3*x**2
                estadistica.ParabolaMinimosCuadrados();
                salida="("+cadena.formatFortran(0,12,5,estadistica.b[3])+")*x**2 + ("+
                        cadena.formatFortran(0,12,5,estadistica.b[2])+")*x + ("+
                        cadena.formatFortran(0,12,5,estadistica.b[1])+")" ;
                salida+="\n RMS = "+estadistica.RMS+ "% \n";
                r_min = -estadistica.b[2]/2/estadistica.b[3];
                e_min=estadistica.b[1]+estadistica.b[2]*r_min+estadistica.b[3]*r_min*r_min;
                Cristal(r_min);
                dE=2*estadistica.b[3]*r_min+estadistica.b[2];
                ddE=2*estadistica.b[3];
                
            }
            
            salida+="E_min = "+cadena.formatFortran(2,12,5,e_min)+"    eV\n";
            salida+="a_min = "+cadena.formatFortran(2,12,5,r_min)+"    angs \n" ;
            salida+="Volumen = "+cadena.formatFortran(2,12,5,volumen)+" ang**3 \n";
            
            beta=volumen/Math.pow(r_min,3);
            //B0=(1/(9abeta))d²E/da² + 2/(9a²beta)dE/da
            B=(ddE/9/r_min/beta+dE*2/9/r_min/r_min/beta)*160.2177;
            // B=(2/9/r_min/r_min/beta*(2*estadistica.b2*r_min+estadistica.b1)+2*estadistica.b2/9/r_min/beta)*160.2177;
            salida+="Bulk modulus = "+cadena.formatFortran(2,12,5,B)+"  GPa";
            out=salida;
            
        }
        
    }
    
    
    void Cristal(double a){
        if(fcc||Zincblende){
            // v1=lvs[1][...]=1,1,0
            lvs[1][1] = 1*a/2 ; lvs[1][2] = 1*a/2 ; lvs[1][3] = 0     ;
            lvs[2][1] = 1*a/2 ; lvs[2][2] = 0     ; lvs[2][3] = 1*a/2 ;
            lvs[3][1] = 0     ; lvs[3][2] = 1*a/2 ; lvs[3][3] = 1*a/2 ;
        }
        if(bcc){
            // v1=lvs[1][...]=1,1,0
            lvs[1][1] = -1*a/2 ; lvs[1][2] =  1*a/2 ; lvs[1][3] =  1*a/2 ;
            lvs[2][1] =  1*a/2 ; lvs[2][2] = -1*a/2 ; lvs[2][3] =  1*a/2 ;
            lvs[3][1] =  1*a/2 ; lvs[3][2] =  1*a/2 ; lvs[3][3] = -1*a/2 ;
        }
        if(usefile)
            if(new File(lvsFile).exists()){
            try{
                java.io.BufferedReader inlvs = new java.io.BufferedReader(new java.io.FileReader(new File(lvsFile)));
                String str="";
                for(int i=1;i<=3;i++){
                    str = inlvs.readLine();
                    lvs[i][1] = cadena.readColDouble(1,str);
                    lvs[i][2] = cadena.readColDouble(2,str);
                    lvs[i][3] = cadena.readColDouble(3,str);
                }
                inlvs.close();
            }catch (IOException oe) {System.out.println("error read lvs");}
            }
        
        volumen=0;
        volumen=Math.abs(lvs[1][1]*lvs[2][2]*lvs[3][3]+lvs[1][2]*lvs[2][3]*lvs[3][1]+lvs[2][1]*lvs[3][2]*lvs[1][3]
                -lvs[3][1]*lvs[2][2]*lvs[1][3]-lvs[3][2]*lvs[2][3]*lvs[1][1]-lvs[2][1]*lvs[1][2]*lvs[3][3]);
        if(Zincblende) volumen=volumen/2;
    }
    /*
     Esto funciona asi:
     V=a³*beta
     B0=-V*dP/dV = -a/3 * dP/da
     P=dE/dV=-dE/da*1/(3*a²beta)
     E=Aa²+Ba+C    <----------------------------------------------- *
     B0=2/(9a²beta)*(2Aa+B)+1/(9abeta)*2A
     B0=[ 2/(9a²beta)(2Aa+B)+2A/(9a*beta) ]*160.2177 GPa <--------- *
     */
}
