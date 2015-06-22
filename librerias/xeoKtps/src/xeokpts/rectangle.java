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


package xeokpts;
import java.util.ArrayList;

public class rectangle {
    reader.reader cadena = new reader.reader();
    double x, y, z, a, p;
    double x_fin = 0, x_ini = 0, y_fin = 0, y_ini = 0;
    int n1, n2, n3, n4, N_tot;  //!numro de punts para cada camino
    double nx,ny;
    ArrayList<String> salida = new ArrayList();
    public rectangle() {
        a = 4.40;   //    Parametro de red
        n1 = 10;  //    numero de puntos entre Γ --> X
        n2 = 5;   //     numero de puntos entre X --> K
        n3 = 11;  //    numero de puntos entre K --> Γ
        n4 = 5;    //    numero de puntos entre Γ --> Y
//recontrucccion
        
        nx = 2;
        ny = 4;
        
    }
    
    void rectangulo() {
        
        /*
        ! Y ╔════════════════════════*  K
        !   *                       **
        !   *                     *  *
        !   *               *        *
        !   *        *               *
        !   *   *                    *
        !   **************************
        !   Γ                           X
         */
        
        N_tot = n1 + n2 + n3 + n4+1; // por el ultimo punto
        
//!los bordes:
        x_fin = Math.PI / (a * nx / Math.pow(2, 0.5));
        x_ini = 0;
        y_fin = Math.PI / (a * ny / Math.pow(2, 0.5));
        y_ini = 0;
        
        p = 1.0 / N_tot;
        salida.add(N_tot+"");
        for (int i1 = 0; i1 < n1; i1++) {
            x = (x_fin - x_ini) * i1 / n1 + x_ini;
            y = y_ini;
            z = 0.0;
            salida.add("    " + cadena.formatFortran(2, 14, 8, x) + "    " + cadena.formatFortran(2, 14, 8, y) + "    " + cadena.formatFortran(2, 14, 8, z) + "" + cadena.formatFortran(2, 14, 8, p));
        }
        for (int i2 = 0; i2 < n2; i2++) {
            y = (y_fin - y_ini) * i2 / n2 + y_ini;
            x = x_fin;
            z = 0.0;
            salida.add("    " + cadena.formatFortran(2, 14, 8, x) + "    " + cadena.formatFortran(2, 14, 8, y) + "    " + cadena.formatFortran(2, 14, 8, z) + "" + cadena.formatFortran(2, 14, 8, p));
            
        }
        for (int i3 = n3; i3 > 0; i3--) {
            x = (x_fin - x_ini) * i3 / n3 + x_ini;
            y = (y_fin - y_ini) * i3 / n3 + y_ini;
            z = 0.0;
            salida.add("    " + cadena.formatFortran(2, 14, 8, x) + "    " + cadena.formatFortran(2, 14, 8, y) + "    " + cadena.formatFortran(2, 14, 8, z) + "" + cadena.formatFortran(2, 14, 8, p));
        }
        for (int i4 = 0; i4 <= n4; i4++) {
            x = x_ini;
            y = (y_fin - y_ini) * i4 / n4 + y_ini;
            z = 0;
            salida.add("    " + cadena.formatFortran(2, 14, 8, x) + "    " + cadena.formatFortran(2, 14, 8, y) + "    " + cadena.formatFortran(2, 14, 8, z) + "" + cadena.formatFortran(2, 14, 8, p));
        }
    }
    
}
