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

public class castep {
    
    int natoms;
    ArrayList<atomCastep> bas;
    String secondLine="";
    String outFile="";
    periodicTable  periodicTable = new  periodicTable();
    format cadena = new format();
    
    /** Creates a new instance of castep */
    public castep() {
        bas = new ArrayList();
    }
    
    
    
//-----------------------------------------------------------------------------
//-----------------  CASTEP ---------------------------------------------------
//-----------------------------------------------------------------------------
    
    public class atomCastep{
        String symbol;
        int Z;
        double x,y,z;
        int fixX,fixY,fixZ;
        int posFix;
        public atomCastep(){
            symbol="H";
            Z=1;
            x=y=z=0.0;
            fixX=fixY=fixZ=0;
        }
    }
    
    String str="";
    String read(String inputfile){
        outFile=inputfile; //important
        bas.clear();
        String lvs="";
        File cellfile=new File(inputfile);
        if(cellfile.exists()){
            try{
                BufferedReader inCell = new BufferedReader(new FileReader(cellfile.getAbsolutePath()));
                while ((str = inCell.readLine()) != null){
                    if(cadena.readColString(2,str).equals("POSITIONS_ABS")){
                        str = inCell.readLine();
                        while (!cadena.readColString(2,str).equals("POSITIONS_ABS")){
                            atomCastep atm = new atomCastep();
                            atm.Z=cadena.readColInt(1,str);
                            atm.symbol=periodicTable.getSymbol(atm.Z);
                            atm.x=cadena.readColDouble(2,str);
                            atm.y=cadena.readColDouble(3,str);
                            atm.z=cadena.readColDouble(4,str);
                            atm.posFix=0;
                            bas.add(atm);
                            for(int j=0;j<bas.size();j++)
                                if(bas.get(j).Z==atm.Z)
                                    atm.posFix++;
                            
                            str = inCell.readLine();
                        }
                    }
                    
                    if(cadena.readColString(2,str).equals("LATTICE_CART")){
                        for(int i=0;i<3;i++){
                            lvs+= inCell.readLine()+"\n";
                        }
                        while (!cadena.readColString(2,inCell.readLine()).equals("LATTICE_CART")){}
                    }
                }
                inCell.close();
            }catch (IOException oe) {System.out.println("error read "+inputfile );}
            
            natoms=bas.size();
            
            try{
                String H="H";
                int pos=1,k=1;
                BufferedReader inCell = new BufferedReader(new FileReader(cellfile.getAbsolutePath()));
                while ((str = inCell.readLine()) != null){
                    if(cadena.readColString(2,str).equals("IONIC_CONSTRAINTS")){
                        str = inCell.readLine();
                        while (!cadena.readColString(2,str).equals("IONIC_CONSTRAINTS")){
                            H= cadena.readColString(2,str);
                            pos=cadena.readColInt(3,str);
                            k=0;
                            for(int i=0;i<bas.size()&&k==0;i++)
                                if(pos==bas.get(i).posFix && H.equals(bas.get(i).symbol))
                                    k = i;
                            if(cadena.readColInt(4,str)==1)  bas.get(k).fixX=1;
                            if(cadena.readColInt(5,str)==1)  bas.get(k).fixY=1;
                            if(cadena.readColInt(6,str)==1)  bas.get(k).fixZ=1;
                            str = inCell.readLine();
                        }
                    }
                }
                inCell.close();
            }catch (IOException oe) {System.out.println("error read "+inputfile );}
            
        }
        String xeoFormat = "";
        xeoFormat=natoms+"\n"+secondLine+"\n";
        for(int i=0;i<natoms;i++)
            xeoFormat+=bas.get(i).symbol
                    +" "+bas.get(i).x
                    +" "+bas.get(i).y
                    +" "+bas.get(i).z
                    +" "+bas.get(i).fixX
                    +" "+bas.get(i).fixY
                    +" "+bas.get(i).fixZ
                    +"\n";
        xeoFormat+=lvs;
        return xeoFormat ;
    }
    
    
    int positionSpecie(String specie, int ibas, String pos){
        int j=0;
        int nsp=0;
        for(int i=1;i<=natoms && ibas!=nsp;i++){
            if(specie.equals(cadena.readColString(1,cadena.getLine(i,pos)))) nsp++;
            if(nsp==ibas)j=i;
        }
        return j;
    }
    
    
    
    
    void loadFromXeo(String xeo){
        bas.clear();
        natoms=cadena.readColInt(1,cadena.getLine(1,xeo));
        for(int i = 0 ; i< natoms; i++){
            str=cadena.getLine(i+3,xeo);
            atomCastep atm = new atomCastep();
            atm.symbol=cadena.readColString(1,str);
            atm.Z=periodicTable.getZ(atm.symbol);
            atm.x=cadena.readColDouble(2,str);
            atm.y=cadena.readColDouble(3,str);
            atm.z=cadena.readColDouble(4,str);
            atm.fixX=cadena.readColInt(5,str);
            atm.fixY=cadena.readColInt(6,str);
            atm.fixZ=cadena.readColInt(7,str);
            bas.add(atm);
        }
        for(int i =(bas.size()-1) ;i>=0;i-- ){
            bas.get(i).posFix=0;
            for(int j =i ;j>=0;j--)
                if(bas.get(i).Z == bas.get(j).Z)
                    bas.get(i).posFix++;
        }
        
    }
    
    public void write(String inputfile,String xeo){
        loadFromXeo(xeo);
        if(new File(outFile).exists())
            write_cell(inputfile,xeo);
        else
            export_xeoBabel(inputfile, xeo);
    }
    
    public void write_cell(String inputfile,String xeo){
        String ret="";
        ret=save_positions(xeo);
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(ret);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+inputfile );}
        ret=savePreview_lvs(xeo);
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(ret);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+inputfile );}
        ret=save_fix_atoms(xeo);
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(ret);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+inputfile );}
        
    }
    
    
    String save_positions(String xeo){
        String ret ="";
        String str="";
        boolean cont=true;
        try{
            BufferedReader inCell = new BufferedReader(new FileReader(outFile));
            while (cont){
                str = inCell.readLine();
                if(cadena.readColString(2,str).equals("POSITIONS_ABS")) cont=false;
                else ret+=str+"\n";
            }
            ret+=str+"\n";
            for(int i=0;i<bas.size();i++)
                ret+=cadena.format(4,bas.get(i).Z)
                +cadena.formatFortran(2,14,6,bas.get(i).x)
                +cadena.formatFortran(2,14,6,bas.get(i).y)
                +cadena.formatFortran(2,14,6,bas.get(i).z)+"\n" ;
            cont=true;
            while (cont){
                str = inCell.readLine();
                if(cadena.readColString(2,str).equals("POSITIONS_ABS")) cont=false;
            }
            ret+=str+"\n";
            while ((str = inCell.readLine()) != null) ret+=str+"\n";
        }catch (IOException oe) {System.out.println("error read .cell");}
        return ret;
    }
    
    
    String savePreview_lvs(String xeo){
        String ret ="";
        String str="";
        try{
            BufferedReader inCell = new BufferedReader(new FileReader(outFile));
            while ((str = inCell.readLine()) != null){
                ret+=str+"\n";
                if(cadena.readColString(2,str).trim().equals("%block LATTICE_CART"))
                    for(int j=bas.size()+3;j<bas.size()+6;j++){
                    ret+=cadena.getLine(j,xeo)+"\n";
                    System.out.println(inCell.readLine()+" "+j);
                    }
            }
        }catch (IOException oe) {System.out.println("error read .cell");}
        return ret;
    }
    
    
    String save_fix_atoms(String xeo){
        String ret ="";
        String str="";
        try{
            BufferedReader inCell = new BufferedReader(new FileReader(outFile));
            while ((str = inCell.readLine()) != null){
                ret+=str+"\n";
                if(cadena.readColString(2,str).trim().equals("IONIC_CONSTRAINTS")) {
                    int g=0;
                    for(int j=0;j<bas.size();j++){
                        if(bas.get(j).fixX==1){
                            g++;
                            ret+=cadena.format(5,g)+"  "+cadena.format(4,bas.get(j).symbol)+"  "+cadena.format(5,bas.get(j).posFix)+"    1 0 0"+"\n";
                        }
                        if(bas.get(j).fixY==1){
                            g++;
                            ret+=cadena.format(5,g)+"  "+cadena.format(4,bas.get(j).symbol)+"  "+cadena.format(5,bas.get(j).posFix)+"    0 1 0"+"\n";
                        }
                        if(bas.get(j).fixZ==1){
                            g++;
                            ret+=cadena.format(5,g)+"  "+cadena.format(4,bas.get(j).symbol)+"  "+cadena.format(5,bas.get(j).posFix)+"    0 0 1"+"\n";
                        }
                    }
                    str = inCell.readLine();
                    while (!cadena.readColString(2,str).trim().equals("IONIC_CONSTRAINTS")){str = inCell.readLine();}
                    ret+=str+"\n";
                }
            }
        }catch (IOException oe) {System.out.println("error read .cell");}
        return ret;
    }
    
    
    public void export_xeoBabel(String inputfile,String xeo){
        String ret ="";
        ret="%block POSITIONS_ABS"+"\n";
        for(int i=0;i<bas.size();i++)
            ret+=cadena.format(4,bas.get(i).Z)
            +cadena.formatFortran(2,14,6,bas.get(i).x)
            +cadena.formatFortran(2,14,6,bas.get(i).y)
            +cadena.formatFortran(2,14,6,bas.get(i).z)+"\n" ;
        ret+="%endblock POSITIONS_ABS"+"\n"+"\n";
        ret+="%block IONIC_CONSTRAINTS"+"\n";
        int g=0;
        for(int j=0;j<bas.size();j++){
            if(bas.get(j).fixX==1){
                g++;
                ret+=cadena.format(5,g)+"  "+cadena.format(4,bas.get(j).symbol)+"  "+cadena.format(5,bas.get(j).posFix)+"    1 0 0"+"\n";
            }
            if(bas.get(j).fixY==1){
                g++;
                ret+=cadena.format(5,g)+"  "+cadena.format(4,bas.get(j).symbol)+"  "+cadena.format(5,bas.get(j).posFix)+"    0 1 0"+"\n";
            }
            if(bas.get(j).fixZ==1){
                g++;
                ret+=cadena.format(5,g)+"  "+cadena.format(4,bas.get(j).symbol)+"  "+cadena.format(5,bas.get(j).posFix)+"    0 0 1"+"\n";
            }
        }
        ret+="%endblock IONIC_CONSTRAINTS"+"\n"+"\n";
        ret+="%block LATTICE_CART"+"\n";
        for(int j=bas.size()+3;j<bas.size()+6;j++)
            ret+=cadena.getLine(j,xeo)+"\n";
        ret+="%endblock LATTICE_CART";
        
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputfile);
            java.io.DataOutputStream out = new java.io.DataOutputStream(archivo);
            out.writeBytes(ret);
            out.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error write "+inputfile );}
    }
    
}


