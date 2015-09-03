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

public class plugin {
    
    //--------------------------------------------------
    public java.util.ArrayList<String> type;
    public java.util.ArrayList<String> end_InputFile;
    public java.util.ArrayList<String> info;
    //--------------------------------------------------
 /*
 exmaple of xeo format CH4                           |   explain
 ------------------------------------------------------------------------------------------
 5                                                   |   numer of atoms
                                                     |   what ever you write here apear in the screen, for nothing write a line (\n)
 C      0.730025      0.000000     -0.058417  1 1 1  |   C    0.73   0.00  -0.05   1 1 1
 H      1.916415     -0.000004      0.031591  0 0 0  |   C                  ->  simbol of the element, you can write C
 H      1.916415     -0.000004      0.031591  0 0 0  |   0.73   0.00  -0.05 ->  position of the atoms X,Y,Z
 H      1.916415     -0.000004      0.031591  0 0 0  |   1 1 1              ->  fix atom in the direction x,y,z.  (0 1 0 only in the y direction)
 H      0.408052      0.560827     -1.057131  0 0 0  |   x1 y1 z1           -> if you check see arrows, take (X+x1, Y+y1 ,Z+z1) , (X+x2, Y+y2 ,Z+z2) , ....
 99.00000      0.000000     0.0000000                |   lvs[0][0]  lvs[0][1]  lvs[0][2]
 0.000000      99.00000     0.0000000                |   lvs[1][0]  lvs[1][1]  lvs[1][2]
 0.000000      0.000000     99.000000                |   lvs[2][0]  lvs[2][1]  lvs[2][2]
 ---------------------------------------------------------------------------------------------
 all input files must be pass this format as a String.
  */
    
    
    abinit      abinit        = new abinit();
    espresso    espresso      = new espresso();
    bas         bas           = new bas();
    castep      castep        = new castep();
    fireball    fireball      = new fireball();
    fireball_TG fireball_TG   = new fireball_TG();
    vasp        vasp          = new vasp();
    xeo         xeo           = new xeo();
//    more        more          = new more();
    xyz         xyz           = new xyz();
    
    
    String SEP,ret;
    
    public plugin() {
        SEP = System.getProperty("file.separator");
        ret="";
        type = new  java.util.ArrayList();
        end_InputFile= new java.util.ArrayList();
        info= new java.util.ArrayList();
        
        //-----------------------------------------------------------------------------------------------
        //-------------------    PROJECTS   -------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------
        //---- unknown and xyz have to be the 1º and 2º, the others do not import the order---------------
        //--------- unknown ----------
        type.add("unknown");
        end_InputFile.add("");
        info.add("unknown");
        
        //--------- Abinit ------------
        type.add("abinit");
        end_InputFile.add(".in");
        info.add("read (on process)"); //http://www.abinit.org/

        //--------- quantum-espresso ------------
        type.add("espresso");
        end_InputFile.add(".in");
        info.add("read and write (on process)"); //http://www.quantum-espresso.org/

        //--------- Castep ------------
        type.add("castep");
        end_InputFile.add(".cell");
        info.add("read and write (POSITIONS_ABS,LATTICE_CART)"); //http://en.wikipedia.org/wiki/CASTEP
        
        //--------- Fireball ------------
        type.add("fireball");
        end_InputFile.add("script.input");
        info.add("read and write"); //http://www.fireball-dft.org/web/fireballHome; http://www.efireball.cz/
        
        //--------- Fireball_TG ----------
        type.add("fireball_TG");
        end_InputFile.add("fireball.in");
        info.add("read and write"); //http://www.fireball-dft.org/web/fireballHome; http://www.efireball.cz/
        
        //--------- bas ----------
        type.add("bas");
        end_InputFile.add(".bas");
        info.add("read and write"); //http://www.efireball.cz/
        
        //--------- Vasp ----------
        type.add("vasp");
        end_InputFile.add("POSCAR");
        info.add("read (on process)"); //http://cms.mpi.univie.ac.at/vasp/
        
        //--------- xeo ----------
        type.add("xeo");
        end_InputFile.add(".xeo");
        info.add("read and write"); //https://sourceforge.net/projects/xeo/
        
        //--------- more ----------
//        type.add("more");
//        end_InputFile.add(".xyz");
//        info.add("write"); 

        //--------- xyz ---------------
        type.add("xyz");
        end_InputFile.add(".xyz");
        info.add("read and write (preview)");// http://en.wikipedia.org/wiki/XYZ_file_format"
        //-----------------------------
        
        //---------------------------------
        
        
        
    }
    
    public String read(String sort, String inputFile){
        //  ret="ERROR :: There are some errors in the file : "+inputFile+" or loadValues it is not implemented in "+sort+"\n";
        
        if(sort.equals("abinit"))       ret =      abinit.read(inputFile);
        if(sort.equals("espresso"))       ret =      espresso.read(inputFile);
        if(sort.equals("castep"))       ret =      castep.read(inputFile);
        if(sort.equals("fireball"))     ret =    fireball.read(inputFile);
        if(sort.equals("fireball_TG"))  ret = fireball_TG.read(inputFile);
        if(sort.equals("vasp"))         ret =        vasp.read(inputFile);
        if(sort.equals("bas"))          ret =         bas.read(inputFile);
        if(sort.equals("xeo"))          ret =         xeo.read(inputFile);
        if(sort.equals("xyz"))          ret =         xyz.read(inputFile);
        
        if(ret.length()>8)
            if(ret.substring(0,8).equals("ERROR ::"))
                System.out.println(ret);
        return ret;
    }
    
    public void write(String sort,String outputFile, String xeoFormat){
        
        if(sort.equals("castep"))            castep.write(outputFile,xeoFormat);
        if(sort.equals("espresso"))            espresso.write(outputFile,xeoFormat);
        if(sort.equals("fireball"))        fireball.write(outputFile,xeoFormat);
        if(sort.equals("fireball_TG"))  fireball_TG.write(outputFile,xeoFormat);
        if(sort.equals("bas"))                  bas.write(outputFile,xeoFormat);
        if(sort.equals("xeo"))                  xeo.write(outputFile,xeoFormat);
//        if(sort.equals("more"))                more.write(outputFile,xeoFormat);
        if(sort.equals("xyz"))                  xyz.write(outputFile,xeoFormat);
        
    }
    
    
}
