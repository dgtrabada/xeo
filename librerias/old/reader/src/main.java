

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

import reader.*;

public class main {
    
    public static void main(String[] args) {
        reader.reader cadena = new reader.reader();
        String SEP = System.getProperty("file.separator");
        if(args.length!=0){
            if(args[0].toString().equals("-help")){
                System.out.println("-version");
                System.exit(0);
            }
        }
        if(args[0].toString().equals("-version")){
            System.out.println("reader 0.0");
            System.exit(0);
        }
    }
}
