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

package xeo;

import java.util.ArrayList;

public class atom {
    public int Z;            //CHARGE
    public double R[] = new double[6] ;     //coord. in bas file
    public boolean fix;     //if this atoms is in the FRAGMENTS file
    public boolean Fix[] = new boolean[3];
    public int posBas;       //position in the bas file, "FRAGMENTS"   importan FIX !!!!!
    public String Charge;
    public String Charge_spd;
    public String symbol;
    //----screen ooptions---------
    int X,Y;          //coord. in the screen
    boolean selec;    //if this atom is selected in the screen
    ArrayList<Integer> enlace;
    ArrayList<Integer> distancia;
    ArrayList<Integer> angulo;
    ArrayList<Double> fx,fy,fz;
    double dOb;
    int posOut;
    boolean visible;
    
    public atom() {
        fix=Fix[0]=Fix[1]=Fix[2]=false;
        visible=true;
        enlace=new ArrayList();
        distancia = new ArrayList();
        angulo = new ArrayList();
        fx=new ArrayList();
        fy=new ArrayList();
        fz=new ArrayList();
    }
    
    public void equalTo(atom atomo){
        posOut = atomo.posOut;
        posBas = atomo.posBas;
        Z   = atomo.Z;
        symbol=atomo.symbol;
        fx=atomo.fx;
        fy=atomo.fy;
        fz=atomo.fz;
        for(int i=0;i<3;i++){
            R[i] = atomo.R[i];
            Fix[i] = atomo.Fix[i];
        }
        fix  = atomo.fix;
        selec  = atomo.selec;
        dOb=0;
    }
    
    public double distamcePunto(double Px,double Py, double Pz){return Math.pow(Math.pow(Px-R[0],2)+Math.pow(Py-R[1],2)+Math.pow(Pz-R[2],2),0.5);}
    
    //los enlaces estan siempre referidos a la posOut que esta FIX !!!
    // SCREEN
    public void ini_enlace(){ enlace.clear(); }
    public void add_enlace(int i){ enlace.add(i); }
    public int get_enlace(int i){ return enlace.get(i); }
    public int n_enlaces(){ return enlace.size(); }
    
    public void remove_distancia(int j){
        for(int i=0 ; i<distancia.size(); i++)
            if(distancia.get(i)== j)
                distancia.remove(i);
    }
    public void add_distancia(int i){ distancia.add(i); }
    public int get_distancia(int i){ return distancia.get(i); }
    public int n_distancia(){return distancia.size();}
    public void add_angulo(int i){ angulo.add(i); }
    public int get_angulo(int i){ return angulo.get(i) ; }
    public int n_angulos(){ return angulo.size(); }
    public void remove_angulo(int i,int j){
        for(int h=0 ; h<angulo.size(); h+=2)
            if(( angulo.get(h)== i && angulo.get(h+1)== j )
            || (angulo.get(h+1) == i && angulo.get(h) == j )){
            angulo.remove(h+1);
            angulo.remove(h);
            }
    }
    public void setFix(){fix=!(!Fix[0]&&!Fix[1]&&!Fix[2]);}
}
