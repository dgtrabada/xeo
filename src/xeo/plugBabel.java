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

import xeoBabel.plugin;

import java.io.File; 
import xeoBabel.periodicTable;

public class plugBabel {
    plugin plug = new plugin();
    info_bas infBas = new info_bas();
    String path;                // work path (project use at the this moument)
    String xeorc;               // temporal, configuracion, etc ...
    String projectFile;         // file of the project
    String sort;                // type of project
    String typeBabel;
    //---------------------------------
    reader.reader cadena = new reader.reader();
    xeoBabel.periodicTable  periodicTable = new  xeoBabel.periodicTable();
    String SEP = System.getProperty("file.separator");
    String str="";
    boolean readLVS;
    
    public plugBabel() {
        path="."+SEP;
        readLVS = true;
    }
    
    //----------- LEER ARCHIVOS----------------------
    
    void read(){
        read(sort);
    }
    
    void read(String sortRead){
        LoadXeo(plug.read(sortRead,projectFile));
    }
    
    void write(){
        write(sort,projectFile);
    }
    
    void write(String sortWrite, String fileWrite){
        plug.write(sortWrite, fileWrite, getXeoFormat());
    }
    
    public String getSort(String file){
        String sort="unknown";
        String name=new java.io.File(file).getName();
        for(int i = 1; i<plug.type.size(); i++) //i=0 unknown and i=1 xyz
            if(name.length() >= plug.end_InputFile.get(i).length())
                if((name.substring(name.length()-plug.end_InputFile.get(i).length(),name.length())).equals(plug.end_InputFile.get(i)))
                    sort=plug.type.get(i);
        return sort;
    }
    
    String segundaLinea="";
    void LoadXeo(String xeoFormat){
        if(!xeoFormat.substring(0,8).equals("ERROR ::")){
            infBas.bas.clear();
            int natoms=cadena.readColInt(1,cadena.getLine(1,xeoFormat));
            segundaLinea=cadena.getLine(2,xeoFormat);
            for(int n=0;n<natoms;n++){
                str=cadena.getLine(3+n,xeoFormat);
                atom atom = new atom();
                atom.posBas=n;
                atom.posOut=n;
                atom.Z=periodicTable.getZ(cadena.readColString(1,str));
                for(int i=0;i<3;i++){
                    atom.R[i]=cadena.readColDouble(i+2,str);
                    atom.Fix[i]=(cadena.readColInt(5+i,str)==1);
                }
                atom.fix=atom.Fix[0]||atom.Fix[1]||atom.Fix[2];
                atom.symbol=periodicTable.getSymbol(atom.Z);
                infBas.bas.add(atom);
            }
            // for(int i=0;i<3;i++) for(int j=0;j<3;j++)infBas.lvs[i][j] = plugin.fireball.lvs[i][j];
            if(readLVS)
                for(int i=0;i<3;i++){
                str=cadena.getLine(3+natoms+i,xeoFormat);
                infBas.lvs[i][0] = cadena.readColDouble(1,str);
                infBas.lvs[i][1] = cadena.readColDouble(2,str);
                infBas.lvs[i][2] = cadena.readColDouble(3,str);
                }
            if(infBas.seeCell_Vol) infBas.Adj_Vol();
            if(infBas.seeCell_ijk) infBas.Adj_ijk();
            if(infBas.seeBond) infBas.load_enlaces();
        }
    }
    
    String getXeoFormat(){
        String ret="";
        ret=infBas.bas.size()+"\n"+segundaLinea+"\n";
        for(int i=0; i<infBas.bas.size() ;i++)
            ret+=cadena.format(4,infBas.bas.get(i).symbol)
            +cadena.formatFortran(2,12,6,infBas.bas.get(i).R[0])
            +cadena.formatFortran(2,12,6,infBas.bas.get(i).R[1])
            +cadena.formatFortran(2,12,6,infBas.bas.get(i).R[2])+"  "
                    +cadena.pasarBoolenaInt(infBas.bas.get(i).Fix[0])+" "
                    +cadena.pasarBoolenaInt(infBas.bas.get(i).Fix[1])+" "
                    +cadena.pasarBoolenaInt(infBas.bas.get(i).Fix[2])+"\n";
        for(int i=0;i<3;i++){
            ret+=cadena.formatFortran(2,12,6,infBas.lvs[i][0])
            +cadena.formatFortran(2,12,6,infBas.lvs[i][1])
            +cadena.formatFortran(2,12,6,infBas.lvs[i][2])+"\n";
        }
        return ret;
    }
    
    
//--leemos projecto de xyz ---
    void loadStepXYZ(int step){
        if(infBas.xyzFile!=null)
            if(infBas.xyzFile.exists()){
            try{
                String str="";
                infBas.bas.clear();
                java.io.BufferedReader inbas = new java.io.BufferedReader(new java.io.FileReader(infBas.xyzFile.getAbsolutePath()));
                str = inbas.readLine();
                //primero
                infBas.nAtomsUnitCell =infBas.cadena.readColInt(1,str);
                int nC=0;
                segundaLinea="";
                while ((str = inbas.readLine()) != null && nC < (step*(infBas.nAtomsUnitCell +2))) nC++;
                segundaLinea+=str+" "; //lo acumulamos aqui, pero luego leer numero de lineas = al numero de pasos
                str = inbas.readLine(); //leemos otra linea
                for(int k=0;k<infBas.nAtomsUnitCell;k++){
                    // while ((str = inbas.readLine()) != null){
                    atom atom = new atom();
                    atom.posBas=k;
                    atom.posOut=k;
                    atom.Z=infBas.periodicTable.getZ(infBas.cadena.readColString(1,str));
                    for(int i=0;i<3;i++)
                        atom.R[i]=infBas.cadena.readColDouble(i+2,str);
                    atom.symbol=infBas.periodicTable.getSymbol(atom.Z);
                    infBas.bas.add(atom);
                    str = inbas.readLine();
                }
                inbas.close();
            }catch (java.io.IOException oe) {System.out.println("error read in class (firstStepXYZ)");}
            if(infBas.seeCell_Vol)infBas.Adj_Vol();
            if(infBas.seeCell_ijk )infBas.Adj_ijk();
            infBas.load_enlaces();
            }
    }
    
    //---------------------------- para el caso del directorio -------------------
    java.io.File aux;
    java.io.File [] fileList;
    String endFile;
    
    public void SORT(){
        sort="unknown";
        fileList = new java.io.File(path).listFiles();
        //ls=System.out.println(fileList[idx].getName());
        for (int idx = 0; idx < fileList.length; idx++)
            for(int i = 2; i<plug.type.size(); i++) //i=0 unknown and i=1 xyz
                if(fileList[idx].getName().length()>= plug.end_InputFile.get(i).length())
                    if((fileList[idx].getName().substring(fileList[idx].getName().length()-plug.end_InputFile.get(i).length(),fileList[idx].getName().length())).equals(plug.end_InputFile.get(i))){
            sort=plug.type.get(i);
            endFile=plug.end_InputFile.get(i);
                    }
        
        if(sort.equals("unknown")) //miramos en este caso si hay algun xyz
            for (int idx = 0; idx < fileList.length; idx++)
                if(fileList[idx].getName().length()>4)
                    if((fileList[idx].getName().substring(fileList[idx].getName().length()-4,fileList[idx].getName().length())).equals(".xyz")){
            sort=plug.type.get(1);
            endFile=plug.end_InputFile.get(1);
                    }
    }
    
    public void INPUT(){
        //--- load input file
        if(endFile!=null){
            fileList = new java.io.File(path).listFiles();
            for (int idx = 0; idx < fileList.length; idx++)
                if(fileList[idx].getName().length()>=endFile.length())
                    if((fileList[idx].getName().substring(fileList[idx].getName().length()-endFile.length(),fileList[idx].getName().length())).equals(endFile))
                        projectFile=fileList[idx].getAbsolutePath();
            
        }
    }
    
    
}

