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


public class recorrido {
    reader.reader cadena = new reader.reader();
    public String entrada;
    public String salida;
    public int Ntot;
    /* Formato
     x1 y1 z1
     n1
     x2 y2 z2
     n2
     z3 y3 z3
     */
    public recorrido() {
    }
    
    public void hacerRecorrido(){
        Ntot=0;
        double x1=0,x2=0,y1=0,y2=0,z1=0,z2=0;
        x1=cadena.readColDouble(1,cadena.readLine(1,entrada));
        y1=cadena.readColDouble(2,cadena.readLine(1,entrada));
        z1=cadena.readColDouble(3,cadena.readLine(1,entrada));
        salida=cadena.formatFortran(2, 14, 8, x1) + "    " + cadena.formatFortran(2, 14, 8, y1) + "    " + cadena.formatFortran(2, 14, 8, z1) + "\n";
        int n;
        for(int i = 1; i<=cadena.nLine(entrada);i++){ //nLine y readline para no tener en cuenta las filas en blanco
            x1=cadena.readColDouble(1,cadena.readLine(i,entrada));
            y1=cadena.readColDouble(2,cadena.readLine(i,entrada));
            z1=cadena.readColDouble(3,cadena.readLine(i,entrada));
            i++;
            n=cadena.readColInt(1,cadena.readLine(i,entrada));
            Ntot+=n;
            i++;
            x2=cadena.readColDouble(1,cadena.readLine(i,entrada));
            y2=cadena.readColDouble(2,cadena.readLine(i,entrada));
            z2=cadena.readColDouble(3,cadena.readLine(i,entrada));
            i--;
            if(i==2) n--;
            puntos(x1,y1,z1,n,x2,y2,z2);
        }
        //System.out.println(salida);
        //System.out.println(Ntot);
    }
    
    public String formatoKPTS(){
        String aux="";
        aux=Ntot+"\n";
        for(int i = 1; i<=cadena.nLine(salida);i++){
            aux+=cadena.readLine(i,salida)+ "    " + cadena.formatFortran(2, 14, 8, (1.0/Ntot) ) + "\n";
        }
        return aux;
    }
    
    public void puntos(double x1,double y1,double z1, int n, double x2,double y2,double z2){
        double x=0,y=0,z=0;
        for (int i = 1; i <= n; i++) { //no metemos el primero
            x = (x2- x1) * i / n + x1;
            y = (y2- y1) * i / n + y1;
            z = (z2- z1) * i / n + z1;
            salida+=cadena.formatFortran(2, 14, 8, x) + "    " + cadena.formatFortran(2, 14, 8, y) + "    " + cadena.formatFortran(2, 14, 8, z) + "\n";
        }
    }
    
    //--------- lo pasamos por el origen ---------------
    
    public String origen(String s){
        String aux="";
        double x=0.0,y=0.0,z=0.0;
        for(int i = 1; i<=cadena.nLine(s);i++){ //nLine y readline para no tener en cuenta las filas en blanco
            if(i==1){
                aux="0 0 0 "+"\n";
                x=cadena.readColDouble(1,cadena.readLine(i,s));
                y=cadena.readColDouble(2,cadena.readLine(i,s));
                z=cadena.readColDouble(3,cadena.readLine(i,s));
            } else{
                if(i%2==0) aux+=cadena.readLine(i,s)+"\n";
                else{
                    aux+=( cadena.readColDouble(1,cadena.readLine(i,s))-x )+" "+
                            ( cadena.readColDouble(2,cadena.readLine(i,s))-y )+" "+
                            ( cadena.readColDouble(3,cadena.readLine(i,s))-z )+" "+"\n";
                }
            }
        }
        return aux;
    }
    
    //----------- lo multiplicamos por una constante --------------
    
    public String cte(double A, String s){
        String aux="";
        for(int i = 1; i<=cadena.nLine(s);i++){ //nLine y readline para no tener en cuenta las filas en blanco
            if(i%2==0) aux+=cadena.readLine(i,s)+"\n";
            else{
                aux+=( cadena.readColDouble(1,cadena.readLine(i,s))*A )+" "+
                        ( cadena.readColDouble(2,cadena.readLine(i,s))*A )+" "+
                        ( cadena.readColDouble(3,cadena.readLine(i,s))*A )+" "+"\n";
            }
        }
        return aux;
    }
    
    //----------- lo invertimos y los multiplicamos por una constante --------------
    
    public String inv(double A, String s){
        String aux="";
        double mod=0,x=0,y=0,z=0;
        aux=origen(s); //primero lo pasamos por el origen y cargamos la entr
        for(int i = 1; i<=cadena.nLine(s);i++){ //nLine y readline para no tener en cuenta las filas en blanco
            if(i==1){
                aux="0 0 0 "+"\n";
            }else{
                if(i%2==0) aux+=cadena.readLine(i,s)+"\n";
                else{
                    x=cadena.readColDouble(1,cadena.readLine(i,s));
                    y=cadena.readColDouble(2,cadena.readLine(i,s));
                    z=cadena.readColDouble(3,cadena.readLine(i,s));
                    if(x==0)x=0;
                    else x=A/x;
                    if(y==0)y=0;
                    else y=A/y;
                    if(z==0) z=0;
                    else z=A/z;
                    aux+=x+" "+y+" "+z+" \n";
                }
            }
        }
        return aux;
    }
    
    //----------------- meter los el numero de puntos segun la distancia
    public String  generarPuntos(int Ntot, String s){
        String aux="";
        int n_aux,n;
        double d_tot=0,d_i,k;
        double x1=0.0,y1=0.0,z1=0.0,x2=0.0,y2=0.0,z2=0.0;
        for(int i = 1; i<cadena.nLine(s);i++){ //nLine y readline para no tener en cuenta las filas en blanco
            x1=cadena.readColDouble(1,cadena.readLine(i,s));
            y1=cadena.readColDouble(2,cadena.readLine(i,s));
            z1=cadena.readColDouble(3,cadena.readLine(i,s));
            i+=2;
            x2=cadena.readColDouble(1,cadena.readLine(i,s));
            y2=cadena.readColDouble(2,cadena.readLine(i,s));
            z2=cadena.readColDouble(3,cadena.readLine(i,s));
            d_i=Math.pow(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)+Math.pow(z1-z2,2),0.5);
            d_tot+=d_i;
            i--;
        }
        n_aux=0;
        for(int i = 1; i<cadena.nLine(s);i++){ //nLine y readline para no tener en cuenta las filas en blanco
            aux+=cadena.readLine(i,s)+"\n";
            x1=cadena.readColDouble(1,cadena.readLine(i,s));
            y1=cadena.readColDouble(2,cadena.readLine(i,s));
            z1=cadena.readColDouble(3,cadena.readLine(i,s));
            i+=2;
            x2=cadena.readColDouble(1,cadena.readLine(i,s));
            y2=cadena.readColDouble(2,cadena.readLine(i,s));
            z2=cadena.readColDouble(3,cadena.readLine(i,s));
            d_i=Math.pow(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)+Math.pow(z1-z2,2),0.5);
            k=(Ntot*(d_i/d_tot));
            n=(int) k;
            if(i==cadena.nLine(s))n=Ntot-n_aux;
            aux+=n+"\n";
            n_aux+=n;
            i--;
        }
        aux+=cadena.readLine(cadena.nLine(s),s)+"\n";
        return aux;
    }
    
    
    
}
