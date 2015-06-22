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

public class Main {
    
    public static void main(String[] args) {
        new java_STM_AFM(System.getProperty("user.dir")).setVisible(true);
    }
    
}
