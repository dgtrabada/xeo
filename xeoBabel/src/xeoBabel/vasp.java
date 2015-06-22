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

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class vasp {
    ArrayList<atomVasp> bas;
    format cadena = new format();
    double [][] lvs = new double[3][3];
    periodicTable  periodicTable = new  periodicTable();
    String outFile;
    String inputFile;
    String SEP = System.getProperty("file.separator");
    String str="";
    //---------------
    double lvsRes;
    int natoms;
    String TIPOS="";
    int n_tipos;
    String MASAS;
    
    /**
     * Creates a new instance of vasp
     */
    
    public vasp() {
        bas = new ArrayList();
        lvsRes=1;
    }
    //--read----
    
    public class atomVasp{
        String symbol;
        int Z;
        double x,y,z;
        int fixX,fixY,fixZ;
        int posFix;
        public atomVasp(){
            symbol="H";
            Z=1;
            x=y=z=0.0;
            fixX=fixY=fixZ=0;
        }
    }
    
    
    String read(String infile){
        inputFile=outFile=infile;
        String ret="";
        String path=new File(inputFile).getParent();
        if(new File(infile).exists())
            if(new File(inputFile).exists())
                try{
                    bas.clear();
                    BufferedReader in = new BufferedReader(new FileReader(inputFile));
                    str = in.readLine();
                    str = in.readLine();
                    lvsRes=cadena.readColDouble(1,str);
                    for(int i=0;i<3;i++){
                        str = in.readLine();
                        lvs[i][0] =lvsRes*cadena.readColDouble(1,str);
                        lvs[i][1] =lvsRes*cadena.readColDouble(2,str);
                        lvs[i][2] =lvsRes*cadena.readColDouble(3,str);
                    }
                    TIPOS = in.readLine(); //importantisimo ...
                    while ((str = in.readLine()) != null){
                        if(cadena.readColString(1,str).equals("Cartesian")){
                            str = in.readLine();
                            while (cadena.nCol(str)==6){
                                atomVasp atom = new atomVasp();
                                atom.x=cadena.readColDouble(1,str);
                                atom.y=cadena.readColDouble(2,str);
                                atom.z=cadena.readColDouble(3,str);
                                if(cadena.readColString(4,str).equals("F"))atom.fixX=1;
                                if(cadena.readColString(5,str).equals("F"))atom.fixY=1;
                                if(cadena.readColString(6,str).equals("F"))atom.fixZ=1;
                                bas.add(atom);
                                str = in.readLine();
                            }
                            natoms=bas.size();
                        }
                    }
                    in.close();
                }catch (IOException oe) {System.out.println("error read :"+path+SEP+" POSCAR");}
        try{
            MASAS="";
            BufferedReader potcar = new BufferedReader(new FileReader(path+SEP+"POTCAR"));
            while ((str = potcar.readLine()) != null){
                if(cadena.readColString(1,str).equals("POMASS")){
                    MASAS=MASAS+" "+cadena.readColString(3,str).trim();
                    MASAS=MASAS.substring(0,MASAS.length()-1);
                }                
                n_tipos =(int) Double.valueOf(cadena.nCol(TIPOS)).doubleValue();
                int natom=0;
                for(int i = 0; i<n_tipos; i++){
                    for(int j = 0; j<cadena.readColInt(i+1,TIPOS);j++){
                        bas.get(natom).Z=(int) (cadena.readColInt(i+1,MASAS)/2);
                        if(bas.get(natom).Z==0)bas.get(natom).Z=1; // el H no tiene neutron
                        bas.get(natom).symbol=periodicTable.getSymbol(bas.get(natom).Z);
                        natom++;
                    }
                }
            }
            potcar.close();
        }catch (IOException oe) {System.out.println("error read :"+path+SEP+"POTCAR");}
        ret=natoms+"\n\n";
        for(int i=0;i<natoms;i++){
            ret+=bas.get(i).symbol
                    +" "+ bas.get(i).x+" "+bas.get(i).y+" "+bas.get(i).z
                    +" "+bas.get(i).fixX+" "+bas.get(i).fixY+" "+bas.get(i).fixZ
                    +"\n";
        }
        for(int i=0;i<3;i++)
            ret+=lvs[i][0]+" "+lvs[i][1]+" "+lvs[i][2]+"\n";
        
        return ret;
    }
}
