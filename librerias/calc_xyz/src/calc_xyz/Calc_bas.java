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

public class Calc_bas {
    reader.reader cadena = new reader.reader();
    calculadora.calculadora cal=new calculadora.calculadora();
    
    public Calc_bas() {
    }
    
    String calcular(String Xbas, String Ybas, String Zbas, String inputfiles){
        int nfiles=cadena.nLine(inputfiles);
        String output="";
        String str="";
        boolean error = false;
        for(int i = 0; i< nfiles; i++){            
            str=cadena.readLine(i+1,inputfiles).trim();
            str=str.substring(0,str.lastIndexOf(" ")).trim();
            if(!(new java.io.File(str)).exists())
                error=true;
        }
        if(!error){
            try{
                java.io.BufferedReader [] inbas = new  java.io.BufferedReader[nfiles];
                int [] step = new int[nfiles];
                for(int i = 0; i< nfiles; i++){
                    str=cadena.readLine(i+1,inputfiles).trim();
                    if(cadena.nCol(str)>1){
                        step[i]=(int) Double.valueOf(str.substring(str.lastIndexOf(" "),str.length()).trim()).doubleValue();
                        str=str.substring(0,str.lastIndexOf(" ")).trim();
                        inbas[i] = new java.io.BufferedReader(new java.io.FileReader(str));
                    }
                }
                int natom=0;
                str = inbas[0].readLine(); //tomamos el numero de atomos de la primera
                for(int i = 1; i< nfiles; i++)  inbas[i].readLine();  //todas igual
                for(int i = 0; i< nfiles; i++)  inbas[i].readLine(); //la siguiente linea vacia (xyz)
                natom =cadena.readColInt(1,str);
                for(int i = 0; i< nfiles; i++)
                    for(int j=0;j<(step[i]-1)*(natom+2);j++)
                        inbas[i].readLine();
                String [] Z = new String[nfiles] ;
                double [] x = new double[nfiles];
                double [] y = new double[nfiles];
                double [] z = new double[nfiles];
                String Zout;
                double xout,yout,zout;
                output="  "+natom+"\n";
                for(int k=0;k<natom;k++){
                    for(int i = 0; i< nfiles; i++){
                        str = inbas[i].readLine();
                        Z[i]=cadena.readColString(1,str);
                        x[i]=cadena.readColDouble(2,str);
                        y[i]=cadena.readColDouble(3,str);
                        z[i]=cadena.readColDouble(4,str);
                    }
                    Zout=Z[0]; //este no cambia, tomamos de referencia el primer archivo, suponemos que es igual en todos
                    xout=x[0];
                    yout=y[0];
                    zout=z[0];
                    //--------- metemos la calculadora -------
                    for(int q=1;q<=3;q++){
                        if(q==1)str="0+"+Xbas;
                        if(q==2)str="0+"+Ybas;
                        if(q==3)str="0+"+Zbas; //siento lo de 0+  pero ¿?
                        String aux="";
                        int ini=0,fin=0;
                        for(int i = 0; i< str.length()-1; i++){
                            if(str.substring(i,i+2).equals("x[")||str.substring(i,i+2).equals("y[")||str.substring(i,i+2).equals("z[")){
                                ini=i;
                                fin=ini;
                                while(!str.substring(fin,fin+1).equals("]")) fin++;
                                int h=(int) Double.valueOf(str.substring(ini+2,fin)).doubleValue();
                                h=(h<nfiles)?h:0;
                                if(str.substring(i,i+2).equals("x[")) aux=str.substring(0,ini)+x[h]+str.substring(++fin,str.length());
                                if(str.substring(i,i+2).equals("y[")) aux=str.substring(0,ini)+y[h]+str.substring(++fin,str.length());
                                if(str.substring(i,i+2).equals("z[")) aux=str.substring(0,ini)+z[h]+str.substring(++fin,str.length());
                                str=aux;
                            }
                        }
                        if(q==1)xout=cal.calcular(str);
                        if(q==2)yout=cal.calcular(str);
                        if(q==3)zout=cal.calcular(str);
                    }
                    //----------------------------------------
                    output+="\n"+cadena.format(4,Zout) + cadena.formatFortran(2,12,6,xout) +
                            cadena.formatFortran(2,12,6,yout)+cadena.formatFortran(2,12,6,zout);
                }
                
                for(int i = 0; i< nfiles; i++) inbas[i].close();
            }catch (java.io.IOException oe) {System.out.println("error read basfile");}
        }
        return output;
    }
}
