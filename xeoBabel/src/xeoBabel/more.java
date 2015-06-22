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

public class more {

    format cadena = new format();
    periodicTable periodicTable = new periodicTable();
    double[][] lvs = new double[3][3];
    int lvs_1, lvs_2, lvs_3;
    int natoms;
    double aux;
    String str;

    public more() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                lvs[i][j] = 0;
                if (i == j) {
                    lvs[i][j] = 99;
                }
            }
        }
        lvs_1 = 1;
        lvs_2 = 1;
        lvs_3 = 1;
        aux = 1.0;
        str = "";

    }

    public class atom {

        String symbol;
        int Z;
        double x, y, z;
        int fixX, fixY, fixZ;
        ArrayList<atom> neig;

        public double distamcePunto(double Px, double Py, double Pz) {
            return Math.pow(Math.pow(Px - x, 2) + Math.pow(Py - y, 2) + Math.pow(Pz - z, 2), 0.5);
        }

        public double distamceNei(double Px, double Py, double Pz) {
            double d = 0;
            for (int i = 0; i < neig.size(); i++) {
                d += Math.pow(Math.pow(Px - neig.get(i).x, 2) + Math.pow(Py - neig.get(i).y, 2) + Math.pow(Pz - neig.get(i).z, 2), 0.5);
            }
            return d;
        }

        public atom() {
            neig = new ArrayList();
            symbol = "H";
            Z = 1;
            x = y = z = 0.0;
        }

        public void equalTo(atom atomo) {
            Z = atomo.Z;
            symbol = atomo.symbol;
            x = atomo.x;
            y = atomo.y;
            z = atomo.z;
            fixX = atomo.fixX;
            fixY = atomo.fixY;
            fixZ = atomo.fixZ;
        }

        public void myNei(atom atomo) {
            // se mueve hacia atomo
            double R = (periodicTable.getRadio_100(Z) + periodicTable.getRadio_100(atomo.Z) - 4) / 800;
            double d = 0, d_max = 0;
            double xr, yr, zr, x_max = 0, y_max = 0, z_max = 0;

            for (int t = 0; t <= 36; t++) {
                for (int f = 0; f <= 18; f++) {
                    xr = atomo.x + R * Math.cos(t * Math.PI / 18) * Math.sin(f * Math.PI / 18);
                    yr = atomo.y + R * Math.sin(t * Math.PI / 18) * Math.sin(f * Math.PI / 18);
                    zr = atomo.z + R * Math.cos(f * Math.PI / 18);
                    d = atomo.distamceNei(xr, yr, zr);
                    if (d_max < d) {
                        d_max = d;
                        x_max = xr;
                        y_max = yr;
                        z_max = zr;
                    }
                }
            }
            x = x_max;
            y = y_max;
            z = z_max;
        }
    }

    public int HOLE(ArrayList<atom> B) {
        int h = 0;
        for (int i = 0; i < B.size(); i++) {
            h -= (B.get(i).neig.size() - periodicTable.hole(B.get(i).Z));
        }
        return h;
    }

    public int atomHole(ArrayList<atom> B) {
        int h = 0;
        for (int i = 0; i < B.size(); i++) {
            if (-B.get(i).neig.size() + periodicTable.hole(B.get(i).Z) > 0) {
                h++;
            }
        }
        return h;
    }

    public String getXYZ(ArrayList<atom> B) {
        //lo colocamos un poco
        String aux = B.size() + "\n" + HOLE(B) + " " + atomHole(B) + "\n";
        for (int i = 0; i < B.size(); i++) {
            aux += cadena.format(4, B.get(i).symbol) + cadena.formatFortran(2, 14, 8, B.get(i).x) + cadena.formatFortran(2, 14, 8, B.get(i).y) + cadena.formatFortran(2, 14, 8, B.get(i).z)
                    + "   " + B.get(i).fixX
                    + " " + B.get(i).fixY
                    + " " + B.get(i).fixZ
                    //+ "   " + B.get(i).neig.size() + "   " 
                    //+ periodicTable.hole(B.get(i).Z) 
                    + "\n";
        }
        /*
        for (int i = 0; i < 3; i++) {
        aux += cadena.formatFortran(2, 12, 6, lvs[i][0])
        + cadena.formatFortran(2, 12, 6, lvs[i][1])
        + cadena.formatFortran(2, 12, 6, lvs[i][2]) + "\n";
        }
         */
        return aux;
    }

    public String mv(ArrayList<atom> bas) {
        neig(bas);
        String aux = "";
        int inij = 0, inim = 0;
        for (int i = 0; i < atomHole(bas); i++) {
            for (int j = inij; j < bas.size(); j++) {
                if (-bas.get(j).neig.size() + periodicTable.hole(bas.get(j).Z) > 0) {
                    if (bas.get(j).fixX == 0 && bas.get(j).fixY == 0 && bas.get(j).fixZ == 0) {
                        // j=atomo con enlaces sueltos
                        inij = j + 1;
                        atom at = new atom();
                        at.equalTo(bas.get(j));
                        inim = 0;
                        for (int l = 0; l < atomHole(bas); l++) {
                            for (int m = inim; m < bas.size(); m++) {
                                if (-bas.get(m).neig.size() + periodicTable.hole(bas.get(m).Z) > 0) {
                                    if (j != m) { //movemos el aotmo j hacia el atomo m
                                        inim = m + 1;
                                        bas.get(j).myNei(bas.get(m));
                                        aux += getXYZ(bas);
                                        bas.get(j).equalTo(at);
                                        m = bas.size();

                                    }
                                }
                            }
                        }
                        j = bas.size();
                    }
                }
            }
        }

        return aux;
    }

    public String mv(String xyz) {
        String str = "";
        String out = "";
        int natoms = cadena.readColInt(1, cadena.getLine(1, xyz));
        int pasos = (cadena.getNLine(xyz) + 2) / natoms;

        for (int j = 0; j < pasos; j++) {
            ArrayList<atom> bas;
            bas = new ArrayList();        
            for (int i = j*(natoms+2); i < natoms; i++) {
                str = cadena.getLine(i + 3, xyz);
                atom atm = new atom();
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
            out += mv(bas);
        }
        return out;
    }

    public void write(String inputfile, String xeoFormat) {
        ArrayList<atom> bas;
        bas = new ArrayList();
        String str = "";
        //loadFromXeo
        for (int i = 0; i < cadena.readColInt(1, cadena.getLine(1, xeoFormat)); i++) {
            str = cadena.getLine(i + 3, xeoFormat);
            atom atm = new atom();
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
        for (int i = 0; i < 3; i++) {
            str = cadena.getLine(i + 3 + bas.size(), xeoFormat);
            lvs[i][0] = cadena.readColDouble(1, str);
            lvs[i][1] = cadena.readColDouble(2, str);
            lvs[i][2] = cadena.readColDouble(3, str);
        }

        String aux = "";

        aux+= mv(bas);
        for(int g=1; g<atomHole(bas); g++) aux+= mv(aux);

        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(aux);
            out.close();
            archivo.close();
        } catch (IOException oe) {
            System.out.println("error write " + inputfile);
        }
    }

    public void write_neig(ArrayList<atom> bas) {
        for (int i = 0; i < bas.size(); i++) {
            System.out.println(bas.get(i).neig.size() + "\n");
            for (int j = 0; j < bas.get(i).neig.size(); j++) {
                System.out.println(cadena.format(4, bas.get(i).neig.get(j).symbol) + cadena.formatFortran(2, 14, 8, bas.get(i).neig.get(j).x) + cadena.formatFortran(2, 14, 8, bas.get(i).neig.get(j).y) + cadena.formatFortran(2, 14, 8, bas.get(i).neig.get(j).z));
            }
        }
    }

    public int IntD_100(atom atomo1, atom atomo2) {
        return ((int) (100 * Math.pow(Math.pow(atomo1.x - atomo2.x, 2) + Math.pow(atomo1.y - atomo2.y, 2) + Math.pow(atomo1.z - atomo2.z, 2), 0.5)));
    }

    public int IntD_100(atom atomo1, double x, double y, double z) {
        return ((int) (100 * Math.pow(Math.pow(atomo1.x - x, 2) + Math.pow(atomo1.y - y, 2) + Math.pow(atomo1.z - z, 2), 0.5)));
    }

    public void neig(ArrayList<atom> B) {
        int r1, r2, d;
        int tol = 4;
        double x, y, z;
        boolean repe;
        for (int ia = 0; ia < B.size(); ia++) {
            B.get(ia).neig.clear();
        }
        for (int ia = 0; ia < B.size(); ia++) {
            for (int ja = 0; ja < B.size(); ja++) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        for (int k = -1; k < 2; k++) {
                            x = B.get(ja).x + (i * lvs[0][0] + j * lvs[1][0] + k * lvs[2][0]);
                            y = B.get(ja).y + (i * lvs[0][1] + j * lvs[1][1] + k * lvs[2][1]);
                            z = B.get(ja).z + (i * lvs[0][2] + j * lvs[1][2] + k * lvs[2][2]);
                            r1 = (int) periodicTable.getRadio_100(B.get(ia).Z);
                            r2 = (int) periodicTable.getRadio_100(B.get(ja).Z);
                            d = IntD_100(B.get(ia), x, y, z);
                            if (d < (r1 + r2) / 2 / tol && d > 1) {
                                repe = false;
                                for (int ka = 0; ka < B.get(ia).neig.size(); ka++) {
                                    if (IntD_100(B.get(ia).neig.get(ka), x, y, z) < 1) {
                                        repe = true;
                                    }
                                }
                                if (!repe) {
                                    atom atomo = new atom();
                                    atomo.equalTo(B.get(ja));
                                    atomo.x = x;
                                    atomo.y = y;
                                    atomo.z = z;
                                    B.get(ia).neig.add(atomo);
                                }
                            }
                        }
                    }
                }
            }
        }//        write_neig();
    }
}
