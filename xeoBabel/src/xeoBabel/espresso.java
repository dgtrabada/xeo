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

public class espresso {

    int natoms;
    ArrayList<atomQE> bas;
    String secondLine = "";
    String outFile = "";
    periodicTable periodicTable = new periodicTable();
    format cadena = new format();

    /** Creates a new instance of castep */
    public espresso() {
        bas = new ArrayList();
    }

//-----------------------------------------------------------------------------
//-----------------  ESPRESSO---------------------------------------------------
//-----------------------------------------------------------------------------
//------------
// no cambia el numero de atomos... si borras atomos o pones no funciona
//----
    public class atomQE {

        String symbol;
        int Z;
        double x, y, z;
        int fixX, fixY, fixZ;
        int posFix;

        public atomQE() {
            symbol = "H";
            Z = 1;
            x = y = z = 0.0;
            fixX = fixY = fixZ = 0;
        }
    }
    double aux = 1.0;
    String lvs = "";
    String str = "";
    double B = 0.529177; // bohr = 0.529177  angstrom

    String read(String inputfile) {
        outFile = inputfile; //important
        bas.clear();
        lvs = "";
        File cellfile = new File(inputfile);
        if (cellfile.exists()) {
            try {
                BufferedReader inCell = new BufferedReader(new FileReader(cellfile.getAbsolutePath()));
                while ((str = inCell.readLine()) != null) {
                    if (cadena.readColString(1, str).equals("nat")) {
                        natoms = cadena.readColInt(3, cadena.sustituir(",", " ", str));
                    }
                }
            } catch (IOException oe) {
                System.out.println("error read " + inputfile);
            }
            try {
                BufferedReader inCell = new BufferedReader(new FileReader(cellfile.getAbsolutePath()));
                while ((str = inCell.readLine()) != null) {
                    if (cadena.readColString(1, str).equals("ATOMIC_POSITIONS")) {
                        aux = B;
                        if (cadena.contiene(str, "angstrom")) {
                            aux = 1.0;
                        }
                        str = inCell.readLine();
                        for (int i = 0; i < natoms; i++) {
                            //  while (cadena.startInNumber(cadena.readColString(2,str))){ 
                            atomQE atm = new atomQE();
                            atm.symbol = cadena.readColString(1, str);
                            atm.Z = periodicTable.getZ(atm.symbol);
                            atm.x = cadena.readColDouble(2, str) * aux;
                            atm.y = cadena.readColDouble(3, str) * aux;
                            atm.z = cadena.readColDouble(4, str) * aux;
                            if (cadena.nCol(str) > 4) {
                                if (cadena.readColInt(5, str) == 0) {
                                    atm.fixX = 1;
                                }
                                if (cadena.readColInt(6, str) == 0) {
                                    atm.fixY = 1;
                                }
                                if (cadena.readColInt(7, str) == 0) {
                                    atm.fixZ = 1;
                                }
                            }
                            atm.posFix = 0;
                            bas.add(atm);
                            for (int j = 0; j < bas.size(); j++) {
                                if (bas.get(j).Z == atm.Z) {
                                    atm.posFix++;
                                }
                            }

                            str = inCell.readLine();
                        }
                    }

                    if (cadena.readColString(1, str).equals("CELL_PARAMETERS")) {
                        aux = B;
                        if (cadena.contiene(str, "angstrom")) {
                            aux = 1.0;
                        }
                        for (int i = 0; i < 3; i++) {
                            str = inCell.readLine();
                            lvs += cadena.readColDouble(1, str) * aux + " " + cadena.readColDouble(2, str) * aux + " " + cadena.readColDouble(3, str) * aux + "\n";
                        }
                    }
                }
                inCell.close();
            } catch (IOException oe) {
                System.out.println("error read " + inputfile);
            }
        }
        String xeoFormat = "";
        xeoFormat = natoms + "\n" + secondLine + "\n";
        for (int i = 0; i < natoms; i++) {
            xeoFormat += bas.get(i).symbol
                    + " " + bas.get(i).x
                    + " " + bas.get(i).y
                    + " " + bas.get(i).z
                    + " " + bas.get(i).fixX
                    + " " + bas.get(i).fixY
                    + " " + bas.get(i).fixZ
                    + "\n";
        }
        xeoFormat += lvs;
        return xeoFormat;
    }

    void loadFromXeo(String xeo) {
        bas.clear();
        natoms = cadena.readColInt(1, cadena.getLine(1, xeo));
        for (int i = 0; i < natoms; i++) {
            str = cadena.getLine(i + 3, xeo);
            atomQE atm = new atomQE();
            atm.symbol = cadena.readColString(1, str);
            atm.Z = periodicTable.getZ(atm.symbol);
            atm.x = cadena.readColDouble(2, str);
            atm.y = cadena.readColDouble(3, str);
            atm.z = cadena.readColDouble(4, str);
            atm.fixX = cadena.readColInt(5, str);
            atm.fixY = cadena.readColInt(6, str);
            atm.fixZ = cadena.readColInt(7, str);
            bas.add(atm);
        }
        for (int i = (bas.size() - 1); i >= 0; i--) {
            bas.get(i).posFix = 0;
            for (int j = i; j >= 0; j--) {
                if (bas.get(i).Z == bas.get(j).Z) {
                    bas.get(i).posFix++;
                }
            }
        }

    }

    public void write(String inputfile, String xeo) {
        loadFromXeo(xeo);
        if (new File(outFile).exists()) {
            write_QE(inputfile, xeo);
        } else {
            export_xeoBabel(inputfile, xeo);
        }
    }

    public void write_QE(String inputfile, String xeo) {
        String ret = "";
        ret = save_positions(xeo);
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(ret);
            out.close();
            archivo.close();
        } catch (IOException oe) {
            System.out.println("error write " + inputfile);
        }
        ret = savePreview_lvs(xeo);
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(ret);
            out.close();
            archivo.close();
        } catch (IOException oe) {
            System.out.println("error write " + inputfile);
        }
        /*     
        ret=save_fix_atoms(xeo);
        try {
        java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
        java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
        out.writeBytes(ret);
        out.close();
        archivo.close();
        }catch (IOException oe) {System.out.println("error write "+inputfile );}
         */
    }

    String save_positions(String xeo) {
        String ret = "";
        String str = "";
        boolean cont = true;
        try {
            BufferedReader inCell = new BufferedReader(new FileReader(outFile));
            while (cont) {
                str = inCell.readLine();
                if (cadena.readColString(1, str).equals("ATOMIC_POSITIONS") || str == null) {
                    cont = false;
                } else {
                    ret += str + "\n";
                }
            }
            ret += str + "\n";
            aux = B;
            if (cadena.contiene(str, "angstrom")) {
                aux = 1.0;
            }
            for (int i = 0; i < bas.size(); i++) {
                ret += cadena.format(4, bas.get(i).symbol)
                        + cadena.formatFortran(2, 14, 6, bas.get(i).x / aux)
                        + cadena.formatFortran(2, 14, 6, bas.get(i).y / aux)
                        + cadena.formatFortran(2, 14, 6, bas.get(i).z / aux);
                if (bas.get(i).fixX == 1) {
                    ret += " 0";
                } else {
                    ret += " 1";
                }
                if (bas.get(i).fixY == 1) {
                    ret += " 0";
                } else {
                    ret += " 1";
                }
                if (bas.get(i).fixZ == 1) {
                    ret += " 0";
                } else {
                    ret += " 1";
                }
                ret += "\n";
            }
            cont = true;
            for (int i = 0; i < bas.size(); i++) {
                str = inCell.readLine();
            }
            while ((str = inCell.readLine()) != null) {
                ret += str + "\n";
            }
        } catch (IOException oe) {
            System.out.println("error read .cell");
        }
        return ret;
    }

    String savePreview_lvs(String xeo) {
        String ret = "";
        String str = "";
        String s = "";
        try {
            BufferedReader inCell = new BufferedReader(new FileReader(outFile));
            while ((str = inCell.readLine()) != null) {
                ret += str + "\n";
                if (cadena.readColString(1, str).trim().equals("CELL_PARAMETERS")) {
                    aux = B;
                    if (cadena.contiene(str, "angstrom")) {
                        aux = 1.0;
                    }
                    for (int j = bas.size() + 3; j < bas.size() + 6; j++) {
                        s = cadena.getLine(j, xeo);
                        ret += cadena.formatFortran(2, 14, 6,cadena.readColDouble(1, s) / aux) + " " + cadena.formatFortran(2, 14, 6,cadena.readColDouble(2, s) / aux) + " " + cadena.formatFortran(2, 14, 6,cadena.readColDouble(3, s) / aux) + "\n";
                        inCell.readLine();
                    }
                }
            }
        } catch (IOException oe) {
            System.out.println("error read .cell");
        }
        return ret;
    }

    String save_fix_atoms(String xeo) {
        return save_positions(xeo);
    }

    public void export_xeoBabel(String inputfile, String xeo) {
        String ret = "", s = "";
        ret = "ATOMIC_POSITIONS angstrom" + "\n";
        for (int i = 0; i < bas.size(); i++) {
            ret += cadena.format(4, bas.get(i).symbol)
                    + cadena.formatFortran(2, 14, 6, bas.get(i).x)
                    + cadena.formatFortran(2, 14, 6, bas.get(i).y)
                    + cadena.formatFortran(2, 14, 6, bas.get(i).z);
            if (bas.get(i).fixX == 1) {
                ret += " 0";
            } else {
                ret += " 1";
            }
            if (bas.get(i).fixY == 1) {
                ret += " 0";
            } else {
                ret += " 1";
            }
            if (bas.get(i).fixZ == 1) {
                ret += " 0";
            } else {
                ret += " 1";
            }
            ret += "\n";
        }
        ret += "CELL_PARAMETERS angstrom" + "\n";
        for(int j=bas.size()+3;j<bas.size()+6;j++){
            s = cadena.getLine(j, xeo);
            ret += cadena.formatFortran(2, 14, 6,cadena.readColDouble(1, s)) + " " + cadena.formatFortran(2, 14, 6,cadena.readColDouble(2, s)) + " " + cadena.formatFortran(2, 14, 6,cadena.readColDouble(3, s)) + "\n";
        }
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(ret);
            out.close();
            archivo.close();
        } catch (IOException oe) {
            System.out.println("error write " + inputfile);
        }
    }
}


