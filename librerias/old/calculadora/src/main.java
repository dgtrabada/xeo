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


public class main {
    
    public static void main(String[] args) {
        calculadora.calculadora calc = new calculadora.calculadora();
        System.out.println("exp(pow(cos(3.1415/5),2)+pow(sin(3.1415/5),2))=");
        System.out.println(calc.calcular("exp(pow(cos(3.1415/5),2)+pow(sin(3.1415/5),2))"));
        
        
        String SEP = System.getProperty("file.separator");
        if(args.length!=0){
            String expresion="0+";
            if(args[0].toString().equals("-help")){
                System.out.println("-version");
                System.exit(0);
            }
        }
        if(args[0].toString().equals("-version")){
            System.out.println("calculadora 0.00");
            System.exit(0);
        }
    }
}
