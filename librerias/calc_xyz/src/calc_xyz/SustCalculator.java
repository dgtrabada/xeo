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

package calc_xyz;

import java.util.ArrayList;

public class SustCalculator {
    reader.reader cadena = new reader.reader();
    calculadora.calculadora cal = new calculadora.calculadora();
    
    public SustCalculator(){
        
    }
    //----------- case of answer.xyz ---------------------------
    String compilar_xyz(ArrayList<String> atoms,  ArrayList<String> codigo){
        String salida="";
        double D=0.0;
        for(int i=0;i<codigo.size();i++){ //numero de columnas
            salida+=numero(atoms,codigo.get(i))+" ";
        }
        return salida;
    }
    
    double numero(ArrayList<String> atoms,String linea){
        String aux="";
        //primero pasamos todo a numeros  (z[1],z[2],x[1],y[1],d[1][2],d[1][3]........)
        for(int i=0;i<linea.length()-2;i++){
            if( linea.substring(i,i+2).equals("a[") || linea.substring(i,i+2).equals("A[") ){
                int ini=-1,fin=-1;
                for(int j=i;j<linea.length();j++){
                    if(linea.substring(j,j+1).equals("[")&&ini==-1&&fin==-1)ini=j;
                    if(linea.substring(j,j+1).equals("]")&&ini!=-1&&fin==-1)fin=j;
                }
                int at1 =(int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue();
                int j_ini=fin;
                ini=fin=-1;
                for(int j=j_ini;j<linea.length();j++){
                    if(linea.substring(j,j+1).equals("[")&&ini==-1&&fin==-1)ini=j;
                    if(linea.substring(j,j+1).equals("]")&&ini!=-1&&fin==-1)fin=j;
                }
                int at2 =(int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue();
                j_ini=fin;
                ini=fin=-1;
                for(int j=j_ini;j<linea.length();j++){
                    if(linea.substring(j,j+1).equals("[")&&ini==-1&&fin==-1)ini=j;
                    if(linea.substring(j,j+1).equals("]")&&ini!=-1&&fin==-1)fin=j;
                }
                int at3 =(int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue();
                aux="";
                if(linea.substring(i,i+2).equals("a["))
                    aux=linea.substring(0,i)+ anguloAtomos(atoms,at1,at2,at3)+linea.substring(fin+1,linea.length());
                if(linea.substring(i,i+2).equals("A["))
                    aux=linea.substring(0,i)+ anguloAtomosMedia(atoms,at1,at2,at3)+linea.substring(fin+1,linea.length());
                linea=aux;
                i=0;
            }
            if( linea.substring(i,i+2).equals("d[") || linea.substring(i,i+2).equals("D[") ){
                int ini=-1,fin=-1;
                for(int j=i;j<linea.length();j++){
                    if(linea.substring(j,j+1).equals("[")&&ini==-1&&fin==-1)ini=j;
                    if(linea.substring(j,j+1).equals("]")&&ini!=-1&&fin==-1)fin=j;
                }
                int at1 =(int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue();
                int j_ini=fin;
                ini=fin=-1;
                for(int j=j_ini;j<linea.length();j++){
                    if(linea.substring(j,j+1).equals("[")&&ini==-1&&fin==-1)ini=j;
                    if(linea.substring(j,j+1).equals("]")&&ini!=-1&&fin==-1)fin=j;
                }
                int at2 =(int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue();
                aux="";
                if(linea.substring(i,i+2).equals("d["))
                    aux=linea.substring(0,i)+distancia(atoms,at1,at2)+linea.substring(fin+1,linea.length());
                if(linea.substring(i,i+2).equals("D["))
                    aux=linea.substring(0,i)+distanciaMedia(atoms,at1,at2)+linea.substring(fin+1,linea.length());
                linea=aux;
                i=0;
            }
            if(linea.substring(i,i+2).equals("x[")|
                    linea.substring(i,i+2).equals("y[")|
                    linea.substring(i,i+2).equals("z[")|
                    linea.substring(i,i+2).equals("X[")|
                    linea.substring(i,i+2).equals("Y[")|
                    linea.substring(i,i+2).equals("Z[")){
                aux=linea.substring(0,i);
                int ini=-1,fin=-1;
                for(int j=i;j<linea.length();j++){
                    if(linea.substring(j,j+1).equals("[")&&ini==-1&&fin==-1)ini=j;
                    if(linea.substring(j,j+1).equals("]")&&ini!=-1&&fin==-1)fin=j;
                }
                int at1 =(int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue();
                if(linea.substring(i,i+1).equals("x"))
                    aux+=cadena.readColString(2,atoms.get((int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue()));
                if(linea.substring(i,i+1).equals("y"))
                    aux+=cadena.readColString(3,atoms.get((int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue()));
                if(linea.substring(i,i+1).equals("z"))
                    aux+=cadena.readColString(4,atoms.get((int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue()));
                if(linea.substring(i,i+1).equals("X"))
                    aux+=cadena.readColString(5,atoms.get((int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue()));
                if(linea.substring(i,i+1).equals("Y"))
                    aux+=cadena.readColString(6,atoms.get((int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue()));
                if(linea.substring(i,i+1).equals("Z"))
                    aux+=cadena.readColString(7,atoms.get((int) Double.valueOf(linea.substring(ini+1,fin)).doubleValue()));
                aux+=linea.substring(fin+1,linea.length());
                linea=aux;
                i=0;
            }
        }
        return cal.calcular(linea);
    }
    
    
    double distancia(ArrayList<String> atoms,int i,int j){
        return  Math.pow(
                Math.pow(cadena.readColDouble(2,atoms.get(i))-cadena.readColDouble(2,atoms.get(j)),2)+
                Math.pow(cadena.readColDouble(3,atoms.get(i))-cadena.readColDouble(3,atoms.get(j)),2)+
                Math.pow(cadena.readColDouble(4,atoms.get(i))-cadena.readColDouble(4,atoms.get(j)),2)
                ,0.5);
    }
    double distanciaMedia(ArrayList<String> atoms,int i,int j){
        return  Math.pow(
                Math.pow(cadena.readColDouble(5,atoms.get(i))-cadena.readColDouble(5,atoms.get(j)),2)+
                Math.pow(cadena.readColDouble(6,atoms.get(i))-cadena.readColDouble(6,atoms.get(j)),2)+
                Math.pow(cadena.readColDouble(7,atoms.get(i))-cadena.readColDouble(7,atoms.get(j)),2),0.5);
    }
    double anguloAtomos(ArrayList<String> atoms, int at1, int at2, int at3){
        double ax= cadena.readColDouble(2,atoms.get(at1))-cadena.readColDouble(2,atoms.get(at2));
        double ay= cadena.readColDouble(3,atoms.get(at1))-cadena.readColDouble(3,atoms.get(at2));
        double az= cadena.readColDouble(4,atoms.get(at1))-cadena.readColDouble(4,atoms.get(at2));
        double bx= cadena.readColDouble(2,atoms.get(at3))-cadena.readColDouble(2,atoms.get(at2));
        double by= cadena.readColDouble(3,atoms.get(at3))-cadena.readColDouble(3,atoms.get(at2));
        double bz= cadena.readColDouble(4,atoms.get(at3))-cadena.readColDouble(4,atoms.get(at2));
        double cos=(ax*bx+ay*by+az*bz)/Math.pow(ax*ax+ay*ay+az*az,0.5)/Math.pow(bx*bx+by*by+bz*bz,0.5);
        return Math.acos(cos)*180/Math.PI;
    }
    double anguloAtomosMedia(ArrayList<String> atoms, int at1, int at2, int at3){
        double ax= cadena.readColDouble(5,atoms.get(at1))-cadena.readColDouble(5,atoms.get(at2));
        double ay= cadena.readColDouble(6,atoms.get(at1))-cadena.readColDouble(6,atoms.get(at2));
        double az= cadena.readColDouble(7,atoms.get(at1))-cadena.readColDouble(7,atoms.get(at2));
        double bx= cadena.readColDouble(5,atoms.get(at3))-cadena.readColDouble(5,atoms.get(at2));
        double by= cadena.readColDouble(6,atoms.get(at3))-cadena.readColDouble(6,atoms.get(at2));
        double bz= cadena.readColDouble(7,atoms.get(at3))-cadena.readColDouble(7,atoms.get(at2));
        double cos=(ax*bx+ay*by+az*bz)/Math.pow(ax*ax+ay*ay+az*az,0.5)/Math.pow(bx*bx+by*by+bz*bz,0.5);
        return Math.acos(cos)*180/Math.PI;
    }
}

