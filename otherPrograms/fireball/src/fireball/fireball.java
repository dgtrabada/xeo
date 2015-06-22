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

package fireball;


public class fireball {
    
    public int nBotones=3;   //number of buttons
    public String bot [] = new String[nBotones];
    public String infile;
    
    public fireball() {
        bot[0]="begin";
        bot[1]="hopping";
        bot[2]="DOS and bands";
    }
    
    public static void main(String[] args) {
        //new fireball().seeBegin();
    }
    
    public void onPress(String in, String path, String xeoformat){
        if(in.equals( bot[0])) seeBegin(path);
        if(in.equals( bot[1])) seeHopping(path);
        if(in.equals( bot[2])) seefireballOut(path,xeoformat);
    }
    
    public void seeBegin(String path){
        new Jbegin(path).setVisible(true);
    }
    
    public void seefireballOut(String path, String xeoformat){
        new Dos_bands(path,xeoformat).setVisible(true);
    }
    
    public void seeHopping(String path){
        new Jhopping(path).setVisible(true);
    }
    
}
