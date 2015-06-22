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

public class xeo {
    format cadena = new format();
    public xeo() {
        
    }
    
    String read(String infile){
        String ret="",str="";
        if(new File(infile).exists())
            try{
                BufferedReader in = new BufferedReader(new FileReader(infile));
                ret="";
                while ((str = in.readLine()) != null){
                    ret+=str+"\n";
                }
            }catch (IOException oe) {System.out.println("error read xeo :"+infile);}
        return ret;
    }
    
    public void write(String inputfile, String xeoFormat){
        //lo colocamos un poco
        String aux=cadena.getLine(1,xeoFormat)+"\n"+"\n";
        String str="";
        for(int i=3;i<=cadena.getNLine(xeoFormat)-3;i++){
            str=cadena.getLine(i,xeoFormat);
            aux+=cadena.format(4,cadena.readColString(1,str))
            +cadena.formatFortran(2,14,8,cadena.readColDouble(2,str))
            +cadena.formatFortran(2,14,8,cadena.readColDouble(3,str))
            +cadena.formatFortran(2,14,8,cadena.readColDouble(4,str))
            +"   "+cadena.readColInt(5,str)
            +" "+cadena.readColInt(6,str)
            +" "+cadena.readColInt(7,str)+"\n" ;
        }
        for(int i=cadena.getNLine(xeoFormat)-2;i<=cadena.getNLine(xeoFormat);i++){
            str=cadena.getLine(i,xeoFormat);
            aux+=cadena.formatFortran(2,14,8,cadena.readColDouble(1,str))
            +cadena.formatFortran(2,14,8,cadena.readColDouble(2,str))
            +cadena.formatFortran(2,14,8,cadena.readColDouble(3,str))+"\n";
        }
        try{
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(aux);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+inputfile);}
    }
    
}
