/*
 
    java_STM_AFM is a free (GPLv3) java program to paint the experimental and theoretical data obtained by STM or AFM.
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


package java_STM_AFM;

public class atomo{
    String symbol;
    int Z;
    double x,y,z;
    int X,Y; //screen
    int pos;
    java.util.ArrayList<Integer> enlace;
    
    public atomo(){
        symbol="H";
        Z=1;
        x=y=z=0.0;
        pos=0;
        enlace=new  java.util.ArrayList();
    }
    
    public double distamcePunto(double Px,double Py, double Pz){return Math.pow(Math.pow(Px-x,2)+Math.pow(Py-y,2)+Math.pow(Pz-z,2),0.5);}
    
    public void ini_enlace(){ enlace.clear(); }
    
    public void add_enlace(int i){ enlace.add(i); }
    
    public int n_enlaces(){ return enlace.size(); }
    
    public int get_enlace(int i){ return enlace.get(i); }
}