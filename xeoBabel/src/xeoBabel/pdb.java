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

public class pdb {

    format cadena = new format();

    public pdb() {

    }

    /*
     PDB Format  
     1         2         3         4         5         6         7         8
     12345678901234567890123456789012345678901234567890123456789012345678901234567890
     ATOM    145  N   VAL A  25      32.433  16.336  57.540  1.00 11.92      A1   N
     ATOM    146  CA  VAL A  25      31.132  16.439  58.160  1.00 11.85      A1   C 
    
     PQR Variant of PDB Format   
     ATOM      1  N   ALA     1      46.457  12.189  21.556  0.1414 1.8240
     ATOM      2  CA  ALA     1      47.614  11.997  22.448  0.0962 1.9080    
     */
    String read(String infile) {
        String ret = "", str = "";
        int n = 0;
        if (new File(infile).exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(infile));
                while ((str = in.readLine()) != null) {
                    if (str.length() > 40) {
                        if (cadena.contiene(str.substring(0, 6), "ATOM") || cadena.contiene(str.substring(0, 6), "HETATM")) {
                            n++;
                            if (str.length() > 77) {
                                ret += str.substring(76, 78) + " " + str.substring(30, 38) + " " + str.substring(38, 46) + " " + str.substring(46, 54) + "\n";
                            } else {
                                ret += str.substring(12, 16).trim().substring(0, 1) + " " + str.substring(30, 38) + " " + str.substring(38, 46) + " " + str.substring(46, 54) + "\n";
                            }
                        }
                    }
                }
            } catch (IOException oe) {
                System.out.println("error read xeo :" + infile);
            }
        }
        ret = n + "\n\n" + ret;
        //  System.out.println(ret);
        return ret;
    }

}
