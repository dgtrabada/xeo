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
import java.util.ArrayList;

public class amber {

    format cadena = new format();
    periodicTable periodicTable = new periodicTable();
    ArrayList<String> Z;
    File prmtop;
    String prmtop_up, prmtop_down;
    int N = 0;

    public amber() {
        N = 0;
        Z = new ArrayList();     
        prmtop_up="";
        prmtop_down="";   
    }

    void loadZ(String infile) {
       
        /* File f = null;
        try {
           System.out.println(new File(infile).getCanonicalPath());
            f = new File(new File(infile).getCanonicalPath());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        File f = new File(new File(infile).getParent());
         System.out.println(new File(infile).getParent());
        File[] ficheros = f.listFiles();
        for (int x = 0; x < ficheros.length; x++) {
            if (cadena.contiene(ficheros[x].getName(), ".prmtop")) {
                prmtop = ficheros[x];
            }
        }
        System.out.println("read : " + prmtop.getName());

        //%FLAG ATOMIC_NUMBER
        int col = 0;
        String str = null;
        if (prmtop.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(prmtop));
                int line = 0;
                boolean leerformat = false;
                boolean leerZ = false;
                while ((str = in.readLine()) != null) {
                    line++;
                    if (leerZ = true) {
                        for (int j = 0; j < col; j++) {
                            if (Z.size() < N) {
                                Z.add(cadena.readColString(j + 1, str));
                            }
                        }
                    }

                    if (cadena.contiene(str, "FORMAT") && leerformat) { //%FORMAT(10I8)
                        col = Integer.parseInt(cadena.readColStringSEP(1, cadena.readColStringSEP(2, str, "("), "I"));
                        leerformat = false;
                        leerZ = true;
                    }
                    if (cadena.contiene(str, "FLAG ATOMIC_NUMBER")) {
                        System.out.println("linea = " + line);
                        leerformat = true;
                    }

                }
            } catch (IOException oe) {
                System.out.println("error read xeo :" + prmtop.getName());
            }
        }
        System.out.println("Ncol = " + col);
    }

    String read(String infile) {
        String ret = "", str = "";
        prmtop_up="";
        prmtop_down="";
        if (new File(infile).exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(infile));
                ret = "";
                int i = 0;
                int n = 0;
                while ((str = in.readLine()) != null) {
                    if(i<2) prmtop_up+=str+"\n";
                    System.out.println(n+" "+N+" "+str);
                    if(N!=0 && n==N) prmtop_down+=str+"\n";
                    if (i > 0) {
                        if (i == 1) {
                            N = cadena.readColInt(1, str);
                            ret += str + "\n" + "\n";
                            loadZ(infile);
                        }
                        if (i > 1 && n < N) {
                            n++;
                            if (n <= N) {
                                ret += periodicTable.getSymbol(Integer.parseInt(Z.get(n-1)))+"  " + cadena.formatFortran(2,14,8, cadena.readColDouble(1, str)) + cadena.formatFortran(2,14,8, cadena.readColDouble(2, str)) + cadena.formatFortran(2,14,8, cadena.readColDouble(3, str)) + "\n";
                            }
                            n++;
                            if (n <= N) {
                                ret += periodicTable.getSymbol(Integer.parseInt(Z.get(n-1)))+"  " + cadena.formatFortran(2,14,8, cadena.readColDouble(4, str)) + cadena.formatFortran(2,14,8, cadena.readColDouble(5, str)) + cadena.formatFortran(2,14,8, cadena.readColDouble(6, str)) + "\n";
                            }
                        }
                    }
                    i++;
                }
            } catch (IOException oe) {
                System.out.println("error read xeo :" + infile);
            }
        }
//        System.out.println(ret);

        return ret;
    }

    public void write(String inputfile, String xeoFormat) {
    String amberformat="",str="";
    amberformat+=prmtop_up;
        try{
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            int natoms=(int) Double.valueOf(cadena.getLine(1,xeoFormat)).doubleValue();
            boolean par=false; 
            for(int i=3;i<natoms+3;i++){
                str=cadena.getLine(i,xeoFormat);
                amberformat+=cadena.formatFortran(0,12,7,cadena.readColDouble(2,str))
                +cadena.formatFortran(0,12,7,cadena.readColDouble(3,str))
                +cadena.formatFortran(0,12,7,cadena.readColDouble(4,str));
                if(par) amberformat+="\n" ;
                par=!par;
            }
            if(par)amberformat+="\n";
            amberformat+=prmtop_down;
            out.writeBytes(amberformat);
            System.out.println(amberformat);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+inputfile );}
    }

}
