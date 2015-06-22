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

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class abinit {
    ArrayList<atomAbinit> bas;
    format cadena = new format();
    double [][] lvs = new double[3][3];
    periodicTable  periodicTable = new  periodicTable();
    String outFile;
    String inputFile;
    //---------------
    double acellx,acelly,acellz;
    int natoms;
    int ndtset;
    int ntypat;
    int nobj;
    String typat;
    String znucl;
    String acell;
    //---------------
    boolean read_cor=false;
    
    public abinit() {
        bas = new ArrayList();
        ndtset=0;
        acellx=acelly=acellz=1;
        nobj=0;
    }
    //---read----
    
    String read(String input){
        
        inputFile = outFile=input;
        String ret="";
        if(new File(inputFile).exists()){
            ndtset=0;
            ndtset=read_1(0,"ndtset");
            //System.out.println("ndtset = "+ndtset);
            for(int k=0;k<=ndtset;k++){
                read_znucl(k);
                //System.out.println("znucl"+k+" = "+znucl);
                read_typat(k);
                read_acell(k);
                //System.out.println("acell"+k+" = "+acellx+" "+acelly+" "+acellz);
                if(bas.size()==0)
                    read_typat(0);
                //System.out.println("typat"+k+" = "+typat);
                read_cor=false;
                //System.out.println("rprim");
                read_rprim(k);
                //System.out.println("read_xcart");
                read_xcart(k,0.5291772108);
                //System.out.println("read_xred");
                read_xred(k);
                //System.out.println("read_xangst");
                read_xangst(k);
                if(!read_cor){
                    read_xcart(0,0.5291772108);
                    read_xred(0);
                    read_xangst(0);
                }
                
                nobj=0;
                nobj=read_1(0,"nobj");
                if(nobj>0) READ_OBJ(0.5291772108);
                
                ret=natoms+"\n\n";
                for(int i=0;i<bas.size();i++){
                    ret+=bas.get(i).symbol
                            +" "+ bas.get(i).x+" "+bas.get(i).y+" "+bas.get(i).z
                            +" "+bas.get(i).fixX+" "+bas.get(i).fixY+" "+bas.get(i).fixZ
                            +"\n";
                }
                
                ret+=lvs[0][0]*acellx+" "+lvs[0][1]*acelly+" "+lvs[0][2]*acellz+"\n";
                ret+=lvs[1][0]*acellx+" "+lvs[1][1]*acelly+" "+lvs[1][2]*acellz+"\n";
                ret+=lvs[2][0]*acellx+" "+lvs[2][1]*acellz+" "+lvs[2][2]*acellz;
                
                //    System.out.println(ret);
                bas.clear();
            }
            ret+="\n";
        } return ret;
    }
    
    
    String str="";
    
    public class atomAbinit{
        String symbol;
        int Z;
        double x,y,z;
        int fixX,fixY,fixZ;
        public atomAbinit(){
            symbol="H";
            Z=1;
            x=y=z=0.0;
            fixX=fixY=fixZ=0;
        }
    }
    
    
    int read_1(int k, String sig){
        int retInt=0;
        if(k!=0) sig+=k+"";
        try{
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            while ((str = in.readLine()) != null){
                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                if(str.contains(sig))
                    retInt=cadena.readColInt(cadena.subString(str,sig)+1,str);
            }
            in.close();
        }catch (IOException oe) {System.out.println("error read "+sig+k);}
        return retInt;
    }
    
    
    String Limpiar(String marca, String s){
        String ret="";
        if(str.contains("#")) s=s.substring(0,s.indexOf("#"));
        s=s.substring(s.indexOf(marca)+marca.length(),s.length());
        int i=1;
        boolean cont=true;
        while(cont){
            if(i>cadena.nCol(s))cont=false;
            if(cont)
                if(cadena.startInNumber(cadena.readColString(i,s)))
                    ret+=cadena.readColString(i,s)+" ";
                else  cont=false;
            i++;
        }
        return ret;
    }
    
    void read_typat(int k){
        try{
            String tyt="typat";
            if(k!=0) tyt+=k+"";
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            while ((str = in.readLine()) != null){
                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                if(str.contains(tyt)) {
                    int i,j,n;
                    natoms=0;
                    bas.clear();
                    typat=readAb(Limpiar(tyt,str));
                    n=cadena.nCol(typat);
                    for(i=1;i<=n;i+=2){;
                    for(j=1;j<=cadena.readColInt(i,typat);j++){
                        natoms++;
                        atomAbinit atom = new atomAbinit();
                        atom.symbol=periodicTable.getSymbol(cadena.readColInt(cadena.readColInt(i+1,typat),znucl));
                        bas.add(atom);
                    }
                    }
                    str = in.readLine();
                    if(str==null) str="salir";
                    while(str.trim().equals("")) str = in.readLine();
                    while(cadena.startInNumber(cadena.readColString(1,str))){
                        if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                        typat=readAb(str);
                        n=cadena.nCol(typat);
                        for(i=1;i<=n;i+=2){
                            for(j=1;j<=cadena.readColInt(i,typat);j++){
                                natoms++;
                                atomAbinit atom = new atomAbinit();
                                atom.symbol=periodicTable.getSymbol(cadena.readColInt(cadena.readColInt(i+1,typat),znucl));
                                bas.add(atom);
                            }
                        }
                        str = in.readLine();
                        if(str==null) str="salir";
                    }
                }
            }
            in.close();
        }catch (IOException oe) {System.out.println("error read typat");}
    }
    
    void read_znucl(int k){
        String zcl="znucl";
        if(k!=0)zcl+=k+"";
        try{
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            while ((str = in.readLine()) != null){
                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                if(str.contains(zcl))
                    znucl=read2Ab(Limpiar(zcl,str));
                
            }
            in.close();
        }catch (IOException oe) {System.out.println("error read znucl"+k);};
    }
    
    
    void read_acell(int k){
        try{
            String acdc="acell";
            if(k!=0)acdc+=k+"";
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            while ((str = in.readLine()) != null){
                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                if(str.contains(acdc+":")||str.contains(acdc+"+")) System.out.println("Da errores con el acell: y el acell+");
                if(str.contains(acdc)){
                    acell=read2Ab(Limpiar(acdc,str));
                    acellx=cadena.readColDouble(1,acell)*0.5291772108;
                    acelly=cadena.readColDouble(2,acell)*0.5291772108;
                    acellz=cadena.readColDouble(3,acell)*0.5291772108;
                    //   System.out.println("acell = "+acellx+" "+acelly+" "+acellz);
                }
            }
            in.close();
        }catch (IOException oe) {System.out.println("error read acell"+k);}
    }
    
    void read_rprim(int k){
        try{
            String rp="rprim";
            if(k!=0)rp+=k+"";
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            while ((str = in.readLine()) != null){
                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                if(str.contains(rp)){
                    if(cadena.nCol(str)==10){
                        lvs[0][0] = cadena.readColDouble(2,str);
                        lvs[1][0] = cadena.readColDouble(3,str);
                        lvs[2][0] = cadena.readColDouble(4,str);
                        lvs[0][1] = cadena.readColDouble(5,str);
                        lvs[1][1] = cadena.readColDouble(6,str);
                        lvs[2][1] = cadena.readColDouble(7,str);
                        lvs[0][2] = cadena.readColDouble(8,str);
                        lvs[1][2] = cadena.readColDouble(9,str);
                        lvs[2][2] = cadena.readColDouble(10,str);
                    }else{
                        lvs[0][0] = cadena.readColDouble(2,str);
                        lvs[1][0] = cadena.readColDouble(3,str);
                        lvs[2][0] = cadena.readColDouble(4,str);
                        str = in.readLine();
                        lvs[0][1] = cadena.readColDouble(1,str);
                        lvs[1][1] = cadena.readColDouble(2,str);
                        lvs[2][1] = cadena.readColDouble(3,str);
                        str = in.readLine();
                        lvs[0][2] = cadena.readColDouble(1,str);
                        lvs[1][2] = cadena.readColDouble(2,str);
                        lvs[2][2] = cadena.readColDouble(3,str);
                    }
                }
            }
            in.close();
        }catch (IOException oe) {System.out.println("error read rprim"+k);}
    }
    
    
    
    void read_xred(int k){
        try{
            String xr="xred";
            if(k!=0) xr+=k+"";
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            int ab=0;
            double x=0,y=0,z=0;
            while ((str = in.readLine()) != null){
                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                if(str.contains(xr)){
                    str=read2Ab(Limpiar(xr,str));
                    int atom_aux=0;
                    for(int h=1;h<=cadena.nCol(str);h+=3){
                        x=cadena.readColDouble(h,str);
                        y=cadena.readColDouble(h+1,str);
                        z=cadena.readColDouble(h+2,str);
                        bas.get(atom_aux).x=x*lvs[0][0]*acellx+y*lvs[0][1]*acelly+z*lvs[0][2]*acellz;
                        bas.get(atom_aux).y=y*lvs[1][0]*acellx+y*lvs[1][1]*acelly+z*lvs[1][2]*acellz;
                        bas.get(atom_aux).z=z*lvs[2][0]*acellx+y*lvs[2][1]*acelly+z*lvs[2][2]*acellz;
                        read_cor=true;
                        atom_aux++;
                    }
                    str = in.readLine();
                    if(str!=null){
                        while(str.trim().equals("")) str = in.readLine();
                        while(cadena.startInNumber(cadena.readColString(1,str))){
                            if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                            str=read2Ab(str);
                            for(int h=1;h<=cadena.nCol(str);h+=3){
                                x=cadena.readColDouble(h,str);
                                y=cadena.readColDouble(h+1,str);
                                z=cadena.readColDouble(h+2,str);
                                bas.get(atom_aux).x=x*lvs[0][0]*acellx+y*lvs[0][1]*acelly+z*lvs[0][2]*acellz;
                                bas.get(atom_aux).y=y*lvs[1][0]*acellx+y*lvs[1][1]*acelly+z*lvs[1][2]*acellz;
                                bas.get(atom_aux).z=z*lvs[2][0]*acellx+y*lvs[2][1]*acelly+z*lvs[2][2]*acellz;
                                atom_aux++;
                            }
                            str = in.readLine();
                            if(str!=null){
                                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                                while(str.trim().equals("")) {
                                    str = in.readLine();
                                    if( str==null)str="salir";
                                }
                                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                            }
                        }
                    }
                }
            }
            in.close();
        }catch (IOException oe) {System.out.println("error read xred"+k);}
    }
    
    void read_xcart(int k,double A){
        String xc="xcart";
        if(A>0.6)xc="xangst";
        if(k!=0)xc+=k+"";
        try{
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            int ab=0;
            while ((str = in.readLine()) != null){
                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                if(str.contains(xc)){
                    str=read2Ab(Limpiar(xc,str));
                    int atom_aux=0;
                    for(int h=1;h<=cadena.nCol(str);h+=3){
                        bas.get(atom_aux).x=cadena.readColDouble(h,str)*A;
                        bas.get(atom_aux).y=cadena.readColDouble(h+1,str)*A;
                        bas.get(atom_aux).z=cadena.readColDouble(h+2,str)*A;
                        read_cor=true;
                        atom_aux++;
                    }
                    str = in.readLine();
                    if(str!=null){
                        while(str.trim().equals("")) str = in.readLine();
                        while(cadena.startInNumber(cadena.readColString(1,str))){
                            if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                            str=read2Ab(str);
                            for(int h=1;h<=cadena.nCol(str);h+=3){
                                bas.get(atom_aux).x=cadena.readColDouble(h,str)*A;
                                bas.get(atom_aux).y=cadena.readColDouble(h+1,str)*A;
                                bas.get(atom_aux).z=cadena.readColDouble(h+2,str)*A;
                                atom_aux++;
                            }
                            str = in.readLine();
                            if(str!=null){
                                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                                while(str.trim().equals("")) {
                                    str = in.readLine();
                                    if( str==null)str="salir";
                                }
                                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                            }
                        }
                    }
                }
            }
            in.close();
        }catch (IOException oe) {System.out.println("error read xcart");}
    }
    
    void read_xangst(int k){
        read_xcart(k,1.0);
    }
    
    
    
    
    
    String aux="";
    int n=0;
    String readAb(String input){
        String ret="";
        if(input.contains("#")) input=input.substring(0,input.indexOf("#"));
        for(int i=1;i<=cadena.nCol(input);i++){
            aux=cadena.readColString(i,input);
            aux=cadena.sustituir("d","0",aux);
            if(!cadena.contiene(aux,"*"))
                ret+="1 "+aux+" ";
            else{
                n=(int) Double.valueOf(aux.substring(0,aux.indexOf("*"))).doubleValue();
                for(int j=1;j<=n;j++)
                    ret+="1 "+aux.substring(aux.indexOf("*")+1,aux.length())+" ";
            }
        }
        return ret; // 3 -> 1 3 ; 2*4 -> 1 4 1 4
    }
    
    String read2Ab(String input){
        String ret="";
        if(input.contains("#")) input=input.substring(0,input.indexOf("#"));
        for(int i=1;i<=cadena.nCol(input);i++){
            aux=cadena.readColString(i,input);
            while(cadena.contiene(aux,"d")) aux=cadena.sustituir("d","0",aux);
            if(!cadena.contiene(aux,"*"))
                ret+=aux+" ";
            else{
                n=(int) Double.valueOf(aux.substring(0,aux.indexOf("*"))).doubleValue();
                for(int j=1;j<=n;j++)
                    ret+=aux.substring(aux.indexOf("*")+1,aux.length())+" ";
            }
        }
        return ret; // 3 -> 3 ; 2*4 -> 4 4
    }
    
    
    void READ_OBJ(double A){
        // System.out.println("read objXat listas de atomos");
        String  [] objXat = new String[nobj+1];
        String  [] objXtr = new String[nobj+1];
        String aux="";
        try{
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            while ((str = in.readLine()) != null){
                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                for(int i=1;i<=nobj;i++){
                    aux="obj"+cadena.indexABC(i)+"at"; //camia a format.java
                    if(str.contains(aux)){
                        objXat[i]=Limpiar(aux,str);
                        //   System.out.println(aux+" = "+objXat[i]);
                    }
                }
            }
        }catch (IOException oe) {System.out.println("error read objXat");}
        
        boolean cont=true;
        //   System.out.println("read objXtr Translaciones");
        try{
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            while ((str = in.readLine()) != null){
                if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                cont=true;
                for(int i=1;i<=nobj;i++){
                    aux="obj"+cadena.indexABC(i)+"tr"; //camia a format.java
                    if(str.contains(aux)){
                        objXtr[i]=Limpiar(aux,str)+"\n";
                        while(cont){
                            str=in.readLine();
                            if(str!=null){
                                while(str.trim().equals("")) {
                                    str = in.readLine();
                                    if( str==null)cont=false;
                                }
                                if(cont){
                                    if(cadena.startInNumber(str.trim())){
                                        if(str.contains("#")) str=str.substring(0,str.indexOf("#"));
                                        objXtr[i]+=str+"\n";
                                    }else cont=false;
                                }
                            }else cont=false;
                        }
                        
                        //     System.out.println(aux+" = "+objXtr[i]);
                    }
                }
            }
        }catch (IOException oe) {System.out.println("error read objXat");}
        
        
        
        for(int i=0;i<bas.size();i++)
            for(int j=1;j<=nobj;j++)
                for(int ia=1;ia<=cadena.nCol(objXat[j]);ia++)
                    if(cadena.readColInt(ia,objXat[j])==(i+1))
                        for(int ntr=1;ntr<=cadena.getNLine(objXtr[j]);ntr++){
            if(ntr==1){
                bas.get(i).x=bas.get(i).x+cadena.readColDouble(1,cadena.getLine(ntr,objXtr[j]))*A;
                bas.get(i).y=bas.get(i).y+cadena.readColDouble(2,cadena.getLine(ntr,objXtr[j]))*A;
                bas.get(i).z=bas.get(i).z+cadena.readColDouble(3,cadena.getLine(ntr,objXtr[j]))*A;
            }else{
                natoms++;
                atomAbinit atom = new atomAbinit();
                atom.symbol= bas.get(i).symbol;
                atom.x= bas.get(i).x+cadena.readColDouble(1,cadena.getLine(ntr,objXtr[j]))*A;
                atom.y= bas.get(i).y+cadena.readColDouble(2,cadena.getLine(ntr,objXtr[j]))*A;
                atom.z= bas.get(i).z+cadena.readColDouble(3,cadena.getLine(ntr,objXtr[j]))*A;
                bas.add(atom);
            }
                        }
        
        //PARA las rotaciones en el eje definido por el vector unitario en objatr y objbtr
        //primero tendremos que hacer una rotación dejando como eje z ese vector unitario,
        //luego rotamos el angulo que nos pida y finalmente lo volvemos a poner como eje z
        //el eje z (esto ultimo no lo tengo muy claro ...)
        
    }
    
    
    
}
