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

public class xeoBabel {
    
    
    public static void main(String[] args) {
        plugin plug = new plugin();
        String version="xeoBabel 1.3";
        String SEP = System.getProperty("file.separator");
        if(args.length!=0){
            String expresion="0+";
            if(args[0].toString().equals("-H")){
                for(int i=0;i<plug.type.size();i++){
                    System.out.println(plug.type.get(i)+" -- "+plug.info.get(i));
                }
                System.exit(0);
            }
            if(args[0].toString().equals("-version")){
                System.out.println(version);
                System.exit(0);
            }
            if(args[0].toString().equals("show")){
                if(args[1].toString().equals("w"))
                    System.out.println(new gpl().gpl_w);
                if(args[1].toString().equals("c"))
                    System.out.println(new gpl().gpl_c);
                System.exit(0);
            }
            
            if(args.length==6)
                plug.write(args[4].toString(),args[5].toString(),plug.read(args[1].toString(),args[2].toString()));
        }else{
            System.out.println("\n"+"java -jar xeoBabel.jar [-i<input-type>] <name> [-o<output-type>] <name>");
            System.out.println("xeoBabel [-i<input-type>] <name> [-o<output-type>] <name>");
            System.out.println("exmaple : java -jar xeoBabel.jar -i xyz example.xyz -o bas example.bas");
            System.out.println("Try  -H option for more information.");
            System.out.println("\n"+"\n"+new gpl().gpl_v3);
            System.out.println("java -jar xeoBabel.jar show w    or    ./xeoBabel show w");
            System.out.println("java -jar xeoBabel.jar show c    or    ./xeoBabel show c");
        }
    }
}

