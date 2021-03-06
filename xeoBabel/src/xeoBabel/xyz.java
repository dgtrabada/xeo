/*
 
    xeoBabel is a free (GPLv3) open project to read many languages of chemical data using Java
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

package xeoBabel;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class xyz {
    format cadena = new format();
    public xyz() {
        
    }
    
    String read(String infile){
        String ret="";
        if(new File(infile).exists())
            try{
                BufferedReader in = new BufferedReader(new FileReader(infile));
                int n=(int) Double.valueOf(in.readLine()).doubleValue();
                ret=n+"\n"+in.readLine()+"\n";
                for(int i=0;i<n;i++){
                    ret+=(in.readLine())+" 0 0 0"+"\n";
                }
                ret+="99.0 0.0 0.0 \n";
                ret+="0.0 99.0 0.0 \n";
                ret+="0.0 0.0 99.0 \n";
            }catch (IOException oe) {System.out.println("error read xyz :"+infile);}
        return ret;
    }
    
    public void write(String inputfile, String xeoFormat){
        try{
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            String ret="";
            String str="";
            int natoms=(int) Double.valueOf(cadena.getLine(1,xeoFormat)).doubleValue();
            ret+=natoms+"\n\n";
            for(int i=3;i<natoms+3;i++){
                str=cadena.getLine(i,xeoFormat);
                ret+=cadena.format(4,cadena.readColString(1,str))
                +cadena.formatFortran(2,12,6,cadena.readColDouble(2,str))
                +cadena.formatFortran(2,12,6,cadena.readColDouble(3,str))
                +cadena.formatFortran(2,12,6,cadena.readColDouble(4,str))+"\n" ;
            }
            out.writeBytes(ret);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+inputfile);}
    }
    
}
