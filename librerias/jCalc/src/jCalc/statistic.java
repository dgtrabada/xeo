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

package jCalc;

import java.util.Vector;

public class statistic {
    
    public double [][] C = new double [5][5];
    public double [] A = new double [5];
    public double [] b = new double [5];
    
    public double RMS;
    public double x,y;
    public Vector X = new Vector();
    public Vector Y = new Vector();
    reader.reader reader = new reader.reader();
    public String salida;
    
    /** Creates a new instance of statistic */
    public statistic() {
        RMS=0;
    }
    public void Load(java.io.File file){   //cargamos un vector que tiene el archivo que queremos leeer, dos columnas x,y
        String str="";
        X.clear();
        Y.clear();
        if(file.exists()){
            try{
                java.io.BufferedReader read = new java.io.BufferedReader(new java.io.FileReader(file.getAbsolutePath()));
                while ((str = read.readLine()) != null){
                    try{
                        if(!str.trim().equals("")&& reader.nCol(str)>1)
                            x = reader.readColDouble(1,str);
                        y = reader.readColDouble(2,str);
                        X.add(x);
                        Y.add(y);
                    }catch(NumberFormatException ex){System.out.println("error read col in avereage");}
                }
            }catch (java.io.IOException oe) {System.out.println("error read "+file.getAbsolutePath());}
        }
    }
    
//----SUMATORIOS-----
    
    public double Suma(Vector A, int j){
        double suma=0;
        for(int i=0;i<A.size();i++){
            suma+=Math.pow(Double.valueOf(A.get(i).toString()).doubleValue(),j);
        }
        return suma;
    }
    public double Suma(Vector A, int j, Vector B, int k){
        double suma=0;
        for(int i=0;i<A.size();i++){
            suma+=Math.pow(Double.valueOf(A.get(i).toString()).doubleValue(),j)*
                    Math.pow(Double.valueOf(B.get(i).toString()).doubleValue(),k);
        }
        return suma;
    }
    
    
    public double SumaInv(Vector A, int j){
        double suma=0;
        for(int i=0;i<A.size();i++){
            suma+=Math.pow(1/Double.valueOf(A.get(i).toString()).doubleValue(),j);
        }
        return suma;
    }
    public double SumaInv(Vector A, int j, Vector B, int k){
        double suma=0;
        for(int i=0;i<A.size();i++){
            suma+=Math.pow(1/Double.valueOf(A.get(i).toString()).doubleValue(),j)*
                    Math.pow(Double.valueOf(B.get(i).toString()).doubleValue(),k);
        }
        return suma;
    }
    
    public void RectaMinimosCuadrados(){
     /*
     -------- MINIMOS CUADRADOS ----------
     y= a0 + a1*x
      
     Suma{X**0}*a0 + Suma{X**1}*a1 = Suma{Y}  = S0*a0 + S1*a1 = Sy
     Suma{X**1}*a0 + Suma{X**2}*a1 = Suma{XY} = S1*a0 + S2*a1 = Sxy
      */
        
        C[1][1]=Suma(X,0);  C[1][2]=Suma(X,1);    A[1]=Suma(Y,1);
        C[2][1]=C[1][2];    C[2][2]=Suma(X,2);    A[2]=Suma(X,1,Y,1);
        C[1][1]-=C[2][1]*C[1][2]/C[2][2];    A[1]-=A[2]*C[1][2]/C[2][2];   C[1][2]-=C[2][2]*C[1][2]/C[2][2];  //ojo al orden
        C[2][2]-=C[1][2]*C[2][1]/C[1][1];    A[2]-=A[1]*C[2][1]/C[1][1];   C[2][1]-=C[1][1]*C[2][1]/C[1][1];  //ojo al orden
        b[0]=A[1]/C[1][1];
        b[1]=A[2]/C[2][2];
        RMS=0;
        for(int i=0;i<Y.size();i++){
            y=Double.valueOf(Y.get(i).toString()).doubleValue();
            x=Double.valueOf(X.get(i).toString()).doubleValue();
            RMS+=Math.pow(b[1]*x+b[0] - y,2);
        }
        RMS=Math.pow(RMS/Y.size(),0.5);
        salida="y= a0 + a1*x \n";
        salida+="a0 = "+b[0]+"\n";
        salida+="a1 = "+b[1]+"\n";
        salida+="RMS = "+RMS;
    }
    
    public void ParabolaMinimosCuadrados(){
        
        // y=b1 + b2*x +b3*x**2
        
        C[1][1]=Suma(X,0);  C[1][2]=Suma(X,1);   C[1][3]=Suma(X,2);        C[1][4]=0.0;     A[1]=Suma(Y,1);
        C[2][1]=C[1][2];    C[2][2]=C[1][3];     C[2][3]=Suma(X,3);        C[2][4]=0.0;     A[2]=Suma(X,1,Y,1);
        C[3][1]=C[1][3];    C[3][2]=C[2][3];     C[3][3]=Suma(X,4);        C[3][4]=0.0;     A[3]=Suma(X,2,Y,1);
        C[4][1]=0.0;        C[4][2]=0.0;         C[4][3]=0.0;              C[4][4]=1.0;     A[4]=0.0;
        RMS=0;
        resolversistema();
        for(int i=0;i<Y.size();i++){
            y=Double.valueOf(Y.get(i).toString()).doubleValue();
            x=Double.valueOf(X.get(i).toString()).doubleValue();
            RMS+=Math.pow(b[1]+b[2]*x+b[3]*x*x - y,2);
        }
        
        RMS=Math.pow(RMS/Y.size(),0.5);
        salida="b1 + b2*x +b3*x**2 \n";
        salida+="b1 = "+b[1]+"\n";
        salida+="b2 = "+b[2]+"\n";
        salida+="b3 = "+b[3]+"\n";
        salida+="RMS = "+RMS;
    }
    
    
    public void Bich(){
        
        // y=b[1]/x**6+b[2]/x**4+b[3]/x**2+b[4]
        
        C[1][1]=SumaInv(X,12);  C[1][2]=SumaInv(X,10);   C[1][3]=SumaInv(X,8);  C[1][4]=SumaInv(X,6);     A[1]=SumaInv(X,6,Y,1);
        C[2][1]=C[1][2];        C[2][2]=C[1][3];         C[2][3]=C[1][4];       C[2][4]=SumaInv(X,4);     A[2]=SumaInv(X,4,Y,1);
        C[3][1]=C[1][3];        C[3][2]=C[1][4];         C[3][3]=C[2][4];       C[3][4]=SumaInv(X,2);     A[3]=SumaInv(X,2,Y,1);
        C[4][1]=C[1][4];        C[4][2]=C[2][4];         C[4][3]=C[3][4];       C[4][4]=SumaInv(X,0);     A[4]=Suma(Y,1);
        resolversistema();
        RMS=0;
        for(int i=0;i<Y.size();i++){
            y=Double.valueOf(Y.get(i).toString()).doubleValue();
            x=Double.valueOf(X.get(i).toString()).doubleValue();
            RMS+=Math.pow(b[1]/Math.pow(x,6)+b[2]/Math.pow(x,4)+b[3]/Math.pow(x,2)+b[4] - y,2);
        }
        RMS=Math.pow(RMS/Y.size(),0.5);
        salida="    y=b[1]/x**6+b[2]/x**4+b[3]/x**2+b[4] \n";
        salida+="b[4] = "+b[4]+"\n";
        salida+="b[3] = "+b[3]+"\n";
        salida+="b[2] = "+b[2]+"\n";
        salida+="b[1] = "+b[1]+"\n";
        salida+="RMS = "+RMS;
    }
    
    public void print(){
        System.out.println(salida);
    }
    
    
    public void resolversistema(){
        C[2][2]-=C[3][2]*C[2][3]/C[3][3];   C[2][1]-=C[3][1]*C[2][3]/C[3][3];    A[2]-=A[3]*C[2][3]/C[3][3];   C[2][4]-=C[3][4]*C[2][3]/C[3][3] ;  C[2][3]=0;
        C[1][2]-=C[3][2]*C[1][3]/C[3][3];   C[1][1]-=C[3][1]*C[1][3]/C[3][3];    A[1]-=A[3]*C[1][3]/C[3][3];    C[1][4]-=C[3][4]*C[1][3]/C[3][3];   C[1][3]=0;
        C[1][1]-=C[2][1]*C[1][2]/C[2][2];   C[1][3]-=C[2][3]*C[1][2]/C[2][2];    A[1]-=A[2]*C[1][2]/C[2][2];    C[1][4]-=C[2][4]*C[1][2]/C[2][2] ;  C[1][2]=0;
        C[3][1]-=C[2][1]*C[3][2]/C[2][2];   C[3][3]-=C[2][3]*C[3][2]/C[2][2];    A[3]-=A[2]*C[3][2]/C[2][2];    C[3][4]-=C[2][4]*C[3][2]/C[2][2];   C[3][2]=0;
        C[2][2]-=C[1][2]*C[2][1]/C[1][1];   C[2][3]-=C[1][3]*C[2][1]/C[1][1];    A[2]-=A[1]*C[2][1]/C[1][1];    C[2][4]-=C[1][4]*C[2][1]/C[1][1];   C[2][1]=0 ;
        C[3][2]-=C[1][2]*C[3][1]/C[1][1];   C[3][3]-=C[1][3]*C[3][1]/C[1][1];    A[3]-=A[1]*C[3][1]/C[1][1];    C[3][4]-=C[1][4]*C[3][1]/C[1][1];   C[3][1]=0;
        C[4][4]-=C[3][4]*C[4][3]/C[3][3] ; A[4]-=A[3]*C[4][3]/C[3][3]; C[4][3]=0;
        C[4][4]-=C[2][4]*C[4][2]/C[2][2] ; A[4]-=A[2]*C[4][2]/C[2][2]; C[4][2]=0;
        C[4][4]-=C[1][4]*C[4][1]/C[1][1] ; A[4]-=A[1]*C[4][1]/C[1][1]; C[4][1]=0;
        A[3]-=A[4]*C[3][4]/C[4][4];   C[3][4]=0;
        A[2]-=A[4]*C[2][4]/C[4][4];   C[2][4]=0;
        A[1]-=A[4]*C[1][4]/C[4][4];   C[1][4]=0;
        b[1]=A[1]/C[1][1];
        b[2]=A[2]/C[2][2];
        b[3]=A[3]/C[3][3];
        b[4]=A[4]/C[4][4];
    }
    
    
    public String MediaArchivo(java.io.File file, int col){
        salida="";
        String str="";
        double x=0,x_med=0,sigma=0;
        int count=0;
        if(file.exists()){
            try{
                java.io.BufferedReader read = new java.io.BufferedReader(new java.io.FileReader(file.getAbsolutePath()));
                while ((str = read.readLine()) != null){
                    try{
                        x=reader.readColDouble(col,str);
                        count++;
                        x_med=x_med*(count-1)/count+x/count;
                    }catch(NumberFormatException ex){System.out.println("error read col in avereage");}
                }
                read.close();
                read = new java.io.BufferedReader(new java.io.FileReader(file.getAbsolutePath()));
                while ((str = read.readLine()) != null){
                    try{
                        x=reader.readColDouble(col,str);
                        sigma+=Math.pow(x-x_med,2);
                    }catch(NumberFormatException ex){System.out.println("error read col in avereage");}
                }
                read.close();
            }catch (java.io.IOException oe) {System.out.println("error read "+file.getAbsolutePath());}
        }
        if(count==0) salida="ERROR";
        else {
            sigma=Math.pow(sigma/(count-1),0.5);
            salida="X = "+x_med+"\n";
            salida+="Standard deviation = "+sigma+"\n";
            salida+="---------"+"\n";
            salida+="X = suma(x_i)/N "+"\n";
            salida+="Standard deviation  = raiz(( suma(X-x_i)**2 )/(N-1)) "+"\n";
        }
        return salida;
    }
    
}
