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

public class fireball {
    File basfile;
    File lvsfile;
    String path;
    periodicTable  periodicTable = new  periodicTable();
    format cadena = new format();
    String SEP = System.getProperty("file.separator");
    String outFile;
    String str;
    String secondLine=" ";
    
    public fireball() {
        path="."+SEP;
        basfile=null;
        lvsfile=null;
        outFile="";
        str="";
    }
    
    
    String read(String inputfile){
        path=new File(inputfile).getParent();
        if(path==null) path=System.getProperty("user.dir");
        String xeoFormat = "";
        String strd="";
        str="";
        String line="";
        int natoms=0;
        File scriptFile=new File(path+SEP+"script.input");
        if(scriptFile.exists())
            try{
                BufferedReader inScript = new BufferedReader(new FileReader(scriptFile.getAbsolutePath()));
                basfile=new File(path+SEP+cadena.readColString(1,inScript.readLine()));
                lvsfile=new File(path+SEP+cadena.readColString(1,inScript.readLine()));
                inScript.close();
                if(basfile.exists()){
                    try{
                        BufferedReader inbas = new BufferedReader(new FileReader(basfile));
                        File fragfile = new File(path+SEP+"FRAGMENTS");
                        natoms=cadena.readColInt(1,inbas.readLine());
                        xeoFormat=natoms+"\n"+"\n";
                        for(int i=0;i<natoms;i++){
                            str = inbas.readLine();
                            line = "";
                            if(!fragfile.exists()){
                                line =  periodicTable.getSymbol(cadena.readColInt(1,str))
                                +" "+cadena.readColDouble(2,str)+" "+cadena.readColDouble(3,str)+" "+cadena.readColDouble(4,str)
                                +" 0 0 0" ;
                            }else{
                                try{
                                    BufferedReader inFrag = new BufferedReader(new FileReader(fragfile));
                                    inFrag.readLine();
                                    inFrag.readLine();
                                    inFrag.readLine();
                                    while ((strd = inFrag.readLine()) != null){
                                        if(cadena.readColInt(1,strd)==(i+1)){
                                            if(cadena.nCol(strd)>1){
                                                line = periodicTable.getSymbol(cadena.readColInt(1,str))
                                                +" "+cadena.readColDouble(2,str)+" "+cadena.readColDouble(3,str)+" "+cadena.readColDouble(4,str)
                                                +" "+cadena.readColInt(2,strd)+" "+cadena.readColInt(3,strd)+" "+cadena.readColInt(4,strd);
                                            }else{ //fireball_estable_2009
                                                line =  periodicTable.getSymbol(cadena.readColInt(1,str))
                                                +" "+cadena.readColDouble(2,str)+" "+cadena.readColDouble(3,str)+" "+cadena.readColDouble(4,str)
                                                +" 1 1 1" ;
                                            }
                                        }
                                    }
                                    inFrag.close();
                                }catch (IOException oe) {System.out.println("error read FRAGMENS");}
                                if(line.equals("")) {
                                    line =  periodicTable.getSymbol(cadena.readColInt(1,str))
                                    +" "+cadena.readColDouble(2,str)+" "+cadena.readColDouble(3,str)+" "+cadena.readColDouble(4,str)
                                    +" 0 0 0" ;
                                }
                            }
                            xeoFormat+=line+"\n";
                        }
                        inbas.close();
                    }catch (IOException oe) {System.out.println("error read bas");}
                }
                if(lvsfile!=null)
                    if(lvsfile.exists())
                        try{
                            BufferedReader inlvs = new BufferedReader(new FileReader(lvsfile));
                            for(int i=0;i<3;i++)
                                xeoFormat+=inlvs.readLine()+"\n";
                            inlvs.close();
                        }catch (IOException oe) {System.out.println("error read lvs");}
                
            }catch (IOException oe) {System.out.println("error read script");}
        return xeoFormat;
    }
    
    
    public void write(String inputfile, String xeoFormat){ //inputfile=script.input
        String fireballFormat="";
        path=new File(inputfile).getParent();
        if(path==null) path= System.getProperty("user.dir");
        if(basfile==null)
            basfile=new File(path+SEP+"input.bas");
        else
            basfile=new File(path+SEP+basfile.getName());
        
        if(lvsfile==null)
            lvsfile=new File(path+SEP+"input.lvs");
        else
            lvsfile=new File(path+SEP+lvsfile.getName());
        int natoms=0;
        try{
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(basfile.getAbsolutePath());
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            natoms=(int) Double.valueOf(cadena.getLine(1,xeoFormat)).doubleValue();
            fireballFormat=natoms+"\n";
            for(int i=3;i<natoms+3;i++){
                str=cadena.getLine(i,xeoFormat);
                fireballFormat+=cadena.format(4,periodicTable.getZ(cadena.readColString(1,str)))
                +cadena.formatFortran(2,12,6,cadena.readColDouble(2,str))
                +cadena.formatFortran(2,12,6,cadena.readColDouble(3,str))
                +cadena.formatFortran(2,12,6,cadena.readColDouble(4,str))+"\n" ;
            }
            out.writeBytes(fireballFormat);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+basfile.getAbsolutePath() );}
        
        fireballFormat="";
        
        try{
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(lvsfile.getAbsolutePath());
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            for(int i=natoms+3;i<natoms+6;i++)
                fireballFormat+=cadena.getLine(i,xeoFormat)+"\n";
            out.writeBytes(fireballFormat);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+lvsfile.getAbsolutePath());}
        
        fireballFormat="";
        try{
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(path+SEP+"FRAGMENTS");
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            String fireballfix="";
            int fix_x=0,fix_y=0,fix_z=0,nfrag=0;
            for(int i=3;i<natoms+3;i++){
                str=cadena.getLine(i,xeoFormat);
                fix_x=cadena.readColInt(5,str);
                fix_y=cadena.readColInt(6,str);
                fix_z=cadena.readColInt(7,str);
                if(fix_x==1||fix_y==1||fix_z==1){
                    nfrag++;
                    fireballfix+=i-2+" "+fix_x+" "+fix_y+" "+fix_z+"\n" ;
                }
            }
            fireballFormat+="0"+"\n"+"1"+"\n"+nfrag+"\n"+fireballfix;
            out.writeBytes(fireballFormat);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+path+SEP+"FRAGMENTS");}
        
    }
    
    void load_charges(){
        /*
        File chargefile = new File(path+SEP+"CHARGES");
        if(chargefile.exists()){
            try{
                String str;
                int ifrag=0;
                BufferedReader inchar = new BufferedReader(new FileReader(chargefile.getAbsolutePath()));
                int i =cadena.readColInt(1,inchar.readLine());
                for(int j=0;j<i;j++){
                    str=inchar.readLine();
                    String charge_spd="";
                    double charge=0;
                    for(int k=1;k<=cadena.nCol(str);k++){
                        charge_spd+=cadena.formatFortran(2,2,2,cadena.readColDouble(k,str))+" ";
                        charge+=cadena.readColDouble(k,str);
                    }
                    if(j<bas.size()){
                        bas.get(j).Charge_spd=charge_spd;
                        bas.get(j).Charge=cadena.formatFortran(2,2,2,charge);
                    }
                    //si quitas atomos esto " if(j<bas.size())" hace que no se desborde pero las cargas estaran mal puestas
                }
                inchar.close();
                //si tenemos mas atomos que en CHARGES las ponemos a 0 para evitar los desbordamientos,
                // es el caso contrario a " if(j<bas.size())" y solo sucedera cuando editemos el archivo bas
                for(int j=i;j<bas.size();j++) bas.get(j).Charge="NAN";
            }catch (IOException oe) {System.out.println("error read CHARGES");}
        }
         **/
    }
    
}

