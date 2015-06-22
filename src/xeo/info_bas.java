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

import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class info_bas {
    public ArrayList<atom> bas;
    ArrayList<atom> basCopy;
    double [][] lvs = new double[3][3];
    reader.reader cadena = new reader.reader();
    boolean seeBond;
    String path;
    String secondline_xyz;
    xeoBabel.periodicTable  periodicTable = new  xeoBabel.periodicTable();
    int tol;
    double [] r_max = new double[3];
    double [] r_min = new double[3];
    int Z_max,Z_min;
    int lvs_1,lvs_2,lvs_3;
    File xyzFile=null;
    //-----------------------
    boolean seeCell_Vol;
    boolean seeCell_ijk;
    double [] R_max = new double[3];
    double [] R_min = new double[3];
    //-----------------------
    
    int nAtomsUnitCell;
    int nPasosTotal;
    int iStep;
    int iniStepMEM;
    //int nLoadStepMEM=xyz.size();
    int nStepMEM;
    int MEM=(int) Math.pow(2,12); //npasos*nAtomosCeldaUnidad
    
    //------------------------------
    
    Vector xyz =  new Vector();
    
    //------------------------------
    
    boolean LoadingXYZ;
    boolean stopLoadXYZ;
    
    //------------------------------
    
    int il,imax,jmax,kmax,Natom;
    double x,y,z,max;
    boolean eq;
    
    //------------------------------
    
    /** Creates a new instance of info_bas */
    public info_bas() {
        bas = new ArrayList();
        basCopy = new ArrayList();
        secondline_xyz="";
        seeBond=true;
        path="./";
        tol=4;
        seeCell_Vol=false;
        seeCell_ijk=false;
        R_max[0]=R_max[1]=R_max[2]=10;
        R_min[0]=R_min[1]=R_min[2]=0;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
            lvs[i][j]=0;
            if(i==j)lvs[i][j]=99;
            }
        lvs_1=1;lvs_2=1;lvs_3=1;
        LoadingXYZ=false;
        stopLoadXYZ=false;
    }
    
    public double modulo(double Px,double Py, double Pz){return Math.pow(Math.pow(Px,2)+Math.pow(Py,2)+Math.pow(Pz,2),0.5);}
    
    
    public void Adj_Vol(){
        il=bas.size()+1;
        max= modulo(R_min[0]-R_max[0],R_min[1]-R_max[1],R_min[2]-R_max[2]);
        imax=(int) (2*max/modulo(lvs[0][0],lvs[1][0],lvs[2][0])) ;
        jmax=(int) (2*max/modulo(lvs[0][1],lvs[1][1],lvs[2][1])) ;
        kmax=(int) (2*max/modulo(lvs[0][2],lvs[1][2],lvs[2][2])) ;
        Natom=bas.size();
        for(int n=0;n<Natom;n++)
            for(int i = -imax ; i<= imax ; i++  )
                for(int j = -jmax ; j<= jmax ; j++  )
                    for(int k = -kmax; k <= kmax ; k++  ){
            x= bas.get(n).R[0] + ( i*lvs[0][0] + j*lvs[1][0] + k*lvs[2][0]) ;
            y= bas.get(n).R[1] + ( i*lvs[0][1] + j*lvs[1][1] + k*lvs[2][1]) ;
            z= bas.get(n).R[2] + ( i*lvs[0][2] + j*lvs[1][2] + k*lvs[2][2]) ;
            if( x > R_min[0]&& x < R_max[0] && y > R_min[1]&& y < R_max[1] && z > R_min[2]&& z < R_max[2]){
                eq=false;
                for(int a=0;a<bas.size();a++) if( bas.get(a).distamcePunto(x,y,z) < 0.01 ) eq=true;
                if(!eq){
                    atom atom = new atom();
                    atom.equalTo(bas.get(n));
                    atom.posBas=il;
                    atom.posOut=il;
                    atom.R[0]=x;
                    atom.R[1]=y;
                    atom.R[2]=z;
                    bas.add(atom);
                    il++;
                }
            }
                    }
        for(int n=0;n<bas.size();n++)
            if( bas.get(n).R[0] < R_min[0]|| bas.get(n).R[0] > R_max[0] ||
                bas.get(n).R[1] < R_min[1]|| bas.get(n).R[1] > R_max[1] ||
                bas.get(n).R[2] < R_min[2]||bas.get(n).R[2] > R_max[2]) {
            bas.remove(n); n--; }
    }
    
    public void Adj_ijk(){
        il=bas.size()+1;
        Natom=bas.size();
        for(int n=0;n<Natom;n++)
            for(int i = 0 ; i< lvs_1  ; i++  )
                for(int j = 0 ; j< lvs_2    ; j++  )
                    for(int k = 0; k < lvs_3 ; k++  ){
            x= bas.get(n).R[0] + ( i*lvs[0][0] + j*lvs[1][0] + k*lvs[2][0]) ;
            y= bas.get(n).R[1] + ( i*lvs[0][1] + j*lvs[1][1] + k*lvs[2][1]) ;
            z= bas.get(n).R[2] + ( i*lvs[0][2] + j*lvs[1][2] + k*lvs[2][2]) ;
            eq=false;
            for(int a=0;a<bas.size();a++) if( bas.get(a).distamcePunto(x,y,z) < 0.01 ) eq=true;
            if(!eq){
                atom atom = new atom();
                atom.equalTo(bas.get(n));
                atom.posBas=il;
                atom.posOut=il;
                atom.R[0]=x;
                atom.R[1]=y;
                atom.R[2]=z;
                bas.add(atom);
                il++;
            }
                    }
    }
    
    
    public boolean infoXYZ_file(boolean do_maximos){
        LoadingXYZ=true;
        // System.out.println("ajustando maximos ...");
        boolean terminado=true;
        if(xyzFile.exists()){
            try{
                java.io.BufferedReader inxyz = new java.io.BufferedReader(new java.io.FileReader(xyzFile.getAbsolutePath()));
                String str="";
                int paso=0;
                double [] R = new double[3];
                while ((str = inxyz.readLine()) != null && !stopLoadXYZ){
                    inxyz.readLine();
                    if(do_maximos) inxyz.mark(nAtomsUnitCell*200);
                    for(int k=0;k<nAtomsUnitCell;k++){
                        str = inxyz.readLine();
                        if(paso==0)
                            if(do_maximos){
                            for(int i=0;i<3;i++)
                                R[i]=cadena.readColDouble(i+2,str);
                            if(paso==0&&k==0){ //al final solo tomamos en cuenta el primer y ultimo paso, dejo lo de paso=0 pero ya no hace falta
                                for(int i=0;i<3;i++)
                                    r_max[i]=r_min[i]=R[i];
                            }else{
                                for(int i=0;i<3;i++){
                                    r_max[i]=(r_max[i]>R[i])?r_max[i]:R[i];
                                    r_min[i]=(r_min[i]<R[i])?r_min[i]:R[i];
                                }
                            }
                            }
                    }paso++;
                }
                if(do_maximos){
                    inxyz.reset();
                    for(int k=0;k<nAtomsUnitCell;k++){
                        str = inxyz.readLine();
                        for(int i=0;i<3;i++){
                            R[i]=cadena.readColDouble(i+2,str);
                            r_max[i]=(r_max[i]>R[i])?r_max[i]:R[i];
                            r_min[i]=(r_min[i]<R[i])?r_min[i]:R[i];
                        }
                    }
                }
                inxyz.close();
                // nAtomsUnitCell load in babel
                nPasosTotal=paso;
                if(MEM<(nAtomsUnitCell*paso)*5/100) MEM=0;
            }catch (IOException oe) {System.out.println("error read xyz or stopLoad="+stopLoadXYZ);}
        }
        if(!LoadingXYZ) terminado=false;
        LoadingXYZ=false;
        return terminado;
    }
    
    
    
    
    ArrayList<atom> aux ;
    int paso=0; //estos son los pasos que hemos cargado !!
    public void load_xyz(){
        if(xyzFile.exists()&&nAtomsUnitCell!=0){
            xyz.clear();
            nStepMEM=1+MEM/nAtomsUnitCell;
            secondline_xyz="";
            try{
                String str="";
                LoadingXYZ=true;
                java.io.BufferedReader inxyz = new java.io.BufferedReader(new java.io.FileReader(xyzFile.getAbsolutePath()));
                int natom=0;
                paso=0;
                int nC=0,ncol=0;
                while ((str = inxyz.readLine()) != null && nC < (iniStepMEM*(nAtomsUnitCell +2)) && !stopLoadXYZ) nC++;
                double ax=0,ay=0,az=0;
                natom =cadena.readColInt(1,str);
                while ((str = inxyz.readLine()) != null && !stopLoadXYZ && paso<nStepMEM){
                    if(paso!=0) {
                        natom =cadena.readColInt(1,str);
                        str=inxyz.readLine();
                    }
                    secondline_xyz+=str+" "; //lo acumulamos aqui, pero luego leer numero de lineas = al numero de pasos
                    secondline_xyz+="\n";
                    //aux.clear();
                    aux=new ArrayList();
                    for(int k=0;k<natom;k++){
                        if(!stopLoadXYZ){
                            str = inxyz.readLine();
                            atom atom = new atom();
                            atom.posBas=k;
                            atom.posOut=k;
                            atom.Z=periodicTable.getZ(cadena.readColString(1,str));
                            atom.symbol=periodicTable.getSymbol(atom.Z);
                            for(int i=0;i<3;i++)
                                atom.R[i]=cadena.readColDouble(i+2,str);
                            ncol=cadena.nCol(str);
                            if(ncol>6){
                                for(int w=1;w<(ncol-1)/3;w++){
                                    ax=cadena.readColDouble(w*3+2,str);
                                    ay=cadena.readColDouble(w*3+3,str);
                                    az=cadena.readColDouble(w*3+4,str);
                                    atom.fx.add(ax);
                                    atom.fy.add(ay);
                                    atom.fz.add(az);
                                }
                            }
                            aux.add(atom);
                            //  if(stopLoadXYZ) k=natom;
                        /*
                        if(paso==0&&k==0){
                            for(int i=0;i<3;i++)
                                r_max[i]=r_min[i]=atom.R[i];
                            Z_max=Z_min=atom.Z;
                        }else{
                            for(int i=0;i<3;i++){
                                r_max[i]=(r_max[i]>atom.R[i])?r_max[i]:atom.R[i];
                                r_min[i]=(r_min[i]<atom.R[i])?r_min[i]:atom.R[i];
                            }
                            Z_max=(Z_max>atom.Z)?Z_max:atom.Z;
                            Z_min=(Z_min<atom.Z)?Z_min:atom.Z;
                        } //ahora en info
                         */
                        }
                    }
                    //------- he copiado los metodos Adj_Vol(), Adj_ijk():
                    if(seeCell_Vol) {
                        for(int n=0;n<aux.size();n++)
                            if(!stopLoadXYZ)
                                if( aux.get(n).R[0] < R_min[0]|| aux.get(n).R[0] > R_max[0] ||
                                aux.get(n).R[1] < R_min[1]|| aux.get(n).R[1] > R_max[1] ||
                                aux.get(n).R[2] < R_min[2]|| aux.get(n).R[2] > R_max[2]) {
                            aux.remove(n); n--; }
                        max= modulo(R_min[0]-R_max[0],R_min[1]-R_max[1],R_min[2]-R_max[2]);
                        imax=(int) (2*max/modulo(lvs[0][0],lvs[1][0],lvs[2][0])) ;
                        jmax=(int) (2*max/modulo(lvs[0][1],lvs[1][1],lvs[2][1])) ;
                        kmax=(int) (2*max/modulo(lvs[0][2],lvs[1][2],lvs[2][2])) ;
                        Natom=aux.size();
                        for(int n=0;n<Natom;n++)
                            for(int i = -imax ; i<= imax ; i++  )
                                for(int j = -jmax ; j<= jmax ; j++  )
                                    for(int k = -kmax; k <= kmax ; k++  )
                                        if(!stopLoadXYZ){
                            x= aux.get(n).R[0] + ( i*lvs[0][0] + j*lvs[1][0] + k*lvs[2][0]) ;
                            y= aux.get(n).R[1] + ( i*lvs[0][1] + j*lvs[1][1] + k*lvs[2][1]) ;
                            z= aux.get(n).R[2] + ( i*lvs[0][2] + j*lvs[1][2] + k*lvs[2][2]) ;
                            if( x > R_min[0]&& x < R_max[0] && y > R_min[1]&& y < R_max[1] && z > R_min[2]&& z < R_max[2]){
                                eq=false;
                                for(int a=0;a<aux.size();a++) if( aux.get(a).distamcePunto(x,y,z) < 0.01 ) eq=true;
                                if(!eq){
                                    atom atom = new atom();
                                    atom.posBas=natom;
                                    atom.posOut=natom;
                                    atom.Z=aux.get(n).Z;
                                    atom.R[0]=x;
                                    atom.R[1]=y;
                                    atom.R[2]=z;
                                    aux.add(atom);
                                    natom++;
                                    for(int iq=0;iq<3;iq++){
                                        r_max[iq]=(r_max[iq]>atom.R[iq])?r_max[iq]:atom.R[iq];
                                        r_min[iq]=(r_min[iq]<atom.R[iq])?r_min[iq]:atom.R[iq];
                                    }
                                    Z_max=(Z_max>atom.Z)?Z_max:atom.Z;
                                    Z_min=(Z_min<atom.Z)?Z_min:atom.Z;
                                }
                            }
                                        }
                        
                    }
                    
                    if(seeCell_ijk){
                        Natom=aux.size();
                        for(int n=0;n<Natom;n++)
                            for(int i = 0 ; i< lvs_1  ; i++  )
                                for(int j = 0 ; j< lvs_2    ; j++  )
                                    for(int k = 0; k < lvs_3 ; k++  )
                                        if(!stopLoadXYZ){
                            x= aux.get(n).R[0] + ( i*lvs[0][0] + j*lvs[1][0] + k*lvs[2][0]) ;
                            y= aux.get(n).R[1] + ( i*lvs[0][1] + j*lvs[1][1] + k*lvs[2][1]) ;
                            z= aux.get(n).R[2] + ( i*lvs[0][2] + j*lvs[1][2] + k*lvs[2][2]) ;
                            eq=false;
                            for(int a=0;a<aux.size();a++) if( aux.get(a).distamcePunto(x,y,z) < 0.01 ) eq=true;
                            if(!eq){
                                atom atom = new atom();
                                atom.posBas=natom;
                                atom.posOut=natom;
                                atom.Z=aux.get(n).Z;
                                atom.R[0]=x;
                                atom.R[1]=y;
                                atom.R[2]=z;
                                aux.add(atom);
                                natom++;
                                for(int iq=0;iq<3;iq++){
                                    r_max[iq]=(r_max[iq]>atom.R[iq])?r_max[iq]:atom.R[iq];
                                    r_min[iq]=(r_min[iq]<atom.R[iq])?r_min[iq]:atom.R[iq];
                                }
                                Z_max=(Z_max>atom.Z)?Z_max:atom.Z;
                                Z_min=(Z_min<atom.Z)?Z_min:atom.Z;
                            }
                                        }
                    }
                    //----------------------------------------------------------
                    //----------------------------------------------------------
                    //------ cargamos los enlaces ---------
                    for(int i=0;i<aux.size();i++)
                        if(!stopLoadXYZ){
                        aux.get(i).ini_enlace();
                        int r1 =(int) periodicTable.getRadio_100(aux.get(i).Z);
                        for(int j=0;j<aux.size()/*i*/;j++){ //lo hacemos con todos por que este no sabe cual esta mas cerca
                            int r2 =(int) periodicTable.getRadio_100(aux.get(j).Z);
                            if(IntD_100(aux.get(i),aux.get(j))  < (r1+r2)/2/tol ) {
                                aux.get(i).add_enlace(aux.get(j).posBas);  //este esta fijo
                            }
                        }
                        }
                    //---------------------------
                    paso++; //lo utilizo para la memoria
                    if(!stopLoadXYZ) xyz.add(aux);
                }
                inxyz.close();
            }catch (IOException oe) {System.out.println("error read xyz or stopLoad="+stopLoadXYZ);}
        }
        if(stopLoadXYZ) {
            nAtomsUnitCell=(bas.size()>0)?bas.size()-1:0;
            xyz.clear(); //lo hemos parado
        }
        LoadingXYZ=false;
        stopLoadXYZ=false;
    }
    
    //--------------------------------------------------------------------------
    public void load_enlaces(){
        for(int i=0;i<bas.size();i++){
            bas.get(i).ini_enlace();
            int r1 =(int) periodicTable.getRadio_100(bas.get(i).Z);
            for(int j=0;j<bas.size()/*i*/;j++){ //lo hacemos con todos por que este no sabe cual esta mas cerca
                int r2 =(int) periodicTable.getRadio_100(bas.get(j).Z);
                if(IntD_100(bas.get(i),bas.get(j))  < (r1+r2)/2/tol ) {
                    bas.get(i).add_enlace(bas.get(j).posBas);  //este esta fijo
                }
            }
        }
    }
    
    
    public int IntD_100(atom atomo1, atom atomo2) {return ((int) (100*Math.pow( Math.pow(atomo1.R[0]-atomo2.R[0],2) +
            Math.pow(atomo1.R[1]-atomo2.R[1],2) +
            Math.pow(atomo1.R[2]-atomo2.R[2],2) ,0.5)));  }
    
    
    public boolean put;
    public int iPos,jPos,medPos;
    public void load_distancia_xyz(int i,int j){ //i,j  -> son posiciones de bas
        for(int s=0;s<xyz.size();s++){
            aux= new ArrayList();
            aux=(ArrayList) xyz.get(s);
            put=true;
            for(int h=0;h<aux.get(iPos).n_distancia();h++)
                if(aux.get(iPos).get_distancia(h)==j)
                    put=false;
            for(int ja = 0; ja< aux.size();ja++) if(aux.get(ja).posBas == i ) iPos= ja ;
            for(int ja = 0; ja< aux.size();ja++) if(aux.get(ja).posBas == j ) jPos= ja ;
            
            if(put){
                aux.get(iPos).add_distancia(j);
                aux.get(jPos).add_distancia(i);
                //lo hacemos en los dos, pero luego solo pinto la mitad como en los enlaces :)
            } else{
                aux.get(iPos).remove_distancia(j);
                aux.get(jPos).remove_distancia(i);
            }
            xyz.setElementAt(aux,s);
        }
    }
    
    public void load_angulo_xyz(int ini,int med,int fin ){ //i,j  -> son posiciones de bas
        for(int s=0;s<xyz.size();s++){
            aux= new ArrayList();
            aux=(ArrayList) xyz.get(s);
            for(int ja = 0; ja< aux.size();ja++) if(aux.get(ja).posBas == med ) medPos= ja;
            put=true;
            for(int h=0;h<aux.get(medPos).n_angulos();h+=2){
                if ( (aux.get(medPos).get_angulo(h)==ini && aux.get(medPos).get_angulo(h+1)==fin) ||
                        (aux.get(medPos).get_angulo(h+1)==ini && aux.get(medPos).get_angulo(h)==fin))
                    put = false;
            }
            if(put){
                aux.get(medPos).add_angulo(ini);
                aux.get(medPos).add_angulo(fin);
            }else aux.get(medPos).remove_angulo(ini,fin);
            xyz.setElementAt(aux,s);
        }
    }
    
    public void load_distancia(int i,int j){ //i,j  -> son posiciones de bas
        put=true;
        iPos=givePos_fromBas(i);
        jPos=givePos_fromBas(j);
        for(int h=0;h<bas.get(iPos).n_distancia();h++)
            if(bas.get(iPos).get_distancia(h)==j)
                put=false;
        
        if(put){
            bas.get(iPos).add_distancia(j);
            bas.get(jPos).add_distancia(i);
            //lo hacemos en los dos, pero luego solo pinto la mitad como en los enlaces :)
        } else{
            bas.get(iPos).remove_distancia(j);
            bas.get(jPos).remove_distancia(i);
        }
    }
    
    
    public void load_angulo(int ini,int med,int fin ){ //i,j  -> son posiciones de bas
        int medPos=givePos_fromBas(med);
        boolean put=true;
        for(int h=0;h<bas.get(medPos).n_angulos();h+=2){
            if ( (bas.get(medPos).get_angulo(h)==ini && bas.get(medPos).get_angulo(h+1)==fin) ||
                    (bas.get(medPos).get_angulo(h+1)==ini && bas.get(medPos).get_angulo(h)==fin))
                put = false;
        }
        if(put){
            bas.get(medPos).add_angulo(ini);
            bas.get(medPos).add_angulo(fin);
        }else bas.get(medPos).remove_angulo(ini,fin);
    }
    
    //------------atomos --------
    public double d2atomos(atom atomo1, atom atomo2) {
        return Math.pow( Math.pow(atomo1.R[0]-atomo2.R[0],2) +
                Math.pow(atomo1.R[1]-atomo2.R[1],2) +
                Math.pow(atomo1.R[2]-atomo2.R[2],2) ,0.5);
    }
    
    public double anguloAtomos(atom atomo1, atom atomo2, atom atomo3){
        double ax=atomo1.R[0]-atomo2.R[0];
        double ay=atomo1.R[1]-atomo2.R[1];
        double az=atomo1.R[2]-atomo2.R[2];
        double bx=atomo3.R[0]-atomo2.R[0];
        double by=atomo3.R[1]-atomo2.R[1];
        double bz=atomo3.R[2]-atomo2.R[2];
        double cos=(ax*bx+ay*by+az*bz)/Math.pow(ax*ax+ay*ay+az*az,0.5)/Math.pow(bx*bx+by*by+bz*bz,0.5);
        return Math.acos(cos)*180/Math.PI;
    }
    
    public double angulosXYZ2atomos(double x,double y,double z,atom atomo2, atom atomo3){
        double ax=x-atomo2.R[0];
        double ay=y-atomo2.R[1];
        double az=z-atomo2.R[2];
        double bx=atomo3.R[0]-atomo2.R[0];
        double by=atomo3.R[1]-atomo2.R[1];
        double bz=atomo3.R[2]-atomo2.R[2];
        double cos=(ax*bx+ay*by+az*bz)/Math.pow(ax*ax+ay*ay+az*az,0.5)/Math.pow(bx*bx+by*by+bz*bz,0.5);
        return Math.acos(cos)*180/Math.PI;
    }
    
    public double getAngulo(double x0,double y0,double z0,double x1,double y1,double z1,double x2,double y2,double z2){
        double ax=x0-x1;
        double ay=y0-y1;
        double az=z0-z1;
        double bx=x2-x1;
        double by=y2-y1;
        double bz=z2-z1;
        double cos=(ax*bx+ay*by+az*bz)/Math.pow(ax*ax+ay*ay+az*az,0.5)/Math.pow(bx*bx+by*by+bz*bz,0.5);
        return Math.acos(cos)*180/Math.PI;
    }
    
    public int givePos_fromBas(int i){
        int aux=0;
        for(int j = 0; j< bas.size();j++) if(bas.get(j).posBas == i ) aux= j ;
        return  aux;
    }
    
    public void changeCharge(int Z){
        for(int i=0;i<bas.size();i++)
            if(bas.get(i).selec){
                bas.get(i).Z=Z;
                bas.get(i).symbol=periodicTable.getSymbol(Z);
            }
    }
    
    public void eliminate(){
        for(int i=0;i<bas.size();i++) if(bas.get(i).selec) {bas.remove(i);i--;}
    }
    
    public void duplicar(){
        int j=1;
        for(int i=0;i<bas.size();i++) {
            if(bas.get(i).selec) {
                atom atomo_aux = new atom();
                atomo_aux.equalTo(bas.get(i)) ;
                atomo_aux.selec=false;
                atomo_aux.posBas=bas.size()+j;
                bas.add(atomo_aux);
                j++;
            }
        }
    }
    
    public void change(int i,int j){
        atom atom_i = bas.get(i);
        atom atom_j = bas.get(j);
        bas.remove(i);
        bas.remove(j-1);   //ojo que antes hemos quitado el i !!!  (i<j)
        bas.add(i,atom_j);
        bas.add(j,atom_i);
    }
    
    public void mover_pos(int i,int j){
        bas.add(j,bas.get(i));
        bas.remove(i+1);
    }
    
    public void orderY(){
        //primero los ordeno con el algoritmo de la burbuja mejorada
        for(int i=bas.size()-1;i>0;i--){
            boolean mejorada=true;
            for(int j=0;j<i;j++)
                if(bas.get(j).R[1]<bas.get(j+1).R[1]){
                change(j,j+1);
                mejorada=false;
                }
            if(mejorada) break;
        }
        //y luego los marco
        for(int i=0;i<bas.size();i++) bas.get(i).posOut=i;
    }
    
    public void orderX(){
        //primero los ordeno
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).R[0]<bas.get(j).R[0])
                    change(i,j);
        //y luego los marco
        for(int i=0;i<bas.size();i++) bas.get(i).posOut=i;
    }
    
    public void order_atomZ(){
        //primero los ordeno
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).Z>bas.get(j).Z)
                    change(i,j);
        //y luego los marco
        for(int i=0;i<bas.size();i++) bas.get(i).posOut=i;
    }
    
    public void order_Castep(){
        //estan ordenados por Z atomica, ordenamos en z pero solo los de Z==
        order_atomZ();
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).Z==bas.get(j).Z)
                    if(bas.get(i).R[2]<bas.get(j).R[2])
                        change(i,j);
        //y luego los marco
        for(int i=0;i<bas.size();i++) (bas.get(i)).posOut=i;
    }
    
    public void orderZ(){
        //primero los ordeno una forma mas eficaz que la X e Y algoritmo de insercion directa
        // se podria mejorar si le ponemos busqueda binaria ~ O(log n)
        for(int i=1;i<bas.size();i++){
            int j=i-1;
            boolean cx=(bas.get(i).R[2]>bas.get(j).R[2]);
            while(cx) {
                if(j==0){
                    cx=(bas.get(i).R[2]>bas.get(j).R[2]);
                    if(cx) mover_pos(i,0);
                    else mover_pos(i,1);
                    cx=false;
                }else{
                    j--;
                    cx=(bas.get(i).R[2]> bas.get(j).R[2]);
                    if(!cx) mover_pos(i,j+1);
                }
            }
        }
        //y luego los marco
        for(int i=0;i<bas.size();i++) bas.get(i).posOut=i;
    }
    
    public void orderBas(){ //posicion en que la ha leido
        //primero los ordeno
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).posBas>bas.get(j).posBas)
                    change(i,j);
        //y luego los marco
        for(int i=0;i<bas.size();i++) bas.get(i).posOut=i;
    }
    
    public void SelectFrag() {
        for(int i=0;i<bas.size();i++)
            if(bas.get(i).fix)
                bas.get(i).selec=true;
    }
    
    public void fixOnlySelectedAtoms(){
        for(int i=0;i<bas.size();i++)
            if(bas.get(i).selec)
                bas.get(i).fix=bas.get(i).Fix[0]=bas.get(i).Fix[1]=bas.get(i).Fix[2]=true;
            else
                bas.get(i).fix=bas.get(i).Fix[0]=bas.get(i).Fix[1]=bas.get(i).Fix[2]=false;
    }
    
    public void addSelectedAtoms(){
        for(int i=0;i<bas.size();i++)
            if(bas.get(i).selec)
                bas.get(i).fix=bas.get(i).Fix[0]=bas.get(i).Fix[1]=bas.get(i).Fix[2]=true;
    }
    public void addSelectedAtoms(int axi){
        for(int i=0;i<bas.size();i++)
            if(bas.get(i).selec)
                bas.get(i).fix=bas.get(i).Fix[axi]=true;
    }
    public void freeAll(){
        for(int i=0;i<bas.size();i++)
            bas.get(i).fix=bas.get(i).Fix[0]=bas.get(i).Fix[1]=bas.get(i).Fix[2]=false;
    }
    public void freeSelected(){
        for(int i=0;i<bas.size();i++)
            if(bas.get(i).selec)
                bas.get(i).fix=bas.get(i).Fix[0]=bas.get(i).Fix[1]=bas.get(i).Fix[2]=false;
    }
    public void freeSelected(int axi){
        for(int i=0;i<bas.size();i++)
            if(bas.get(i).selec){
            bas.get(i).Fix[axi]=false;
            bas.get(i).fix=(bas.get(i).Fix[0]||bas.get(i).Fix[1]||bas.get(i).Fix[2]);
            }
    }
    
    public Vector outXYZ(){
        //ordenamos respecto la posicion OUT
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).posOut > bas.get(j).posOut)
                    change(i,j);
        Vector aux=new Vector(bas.size()+1);
        aux.add(bas.size());
        aux.add("");
        String cad="";
        for(int i=0;i<bas.size();i++){
            cad=cadena.format(4,bas.get(i).symbol);
            cad+=cadena.formatFortran(2,14,6,bas.get(i).R[0]);
            cad+=cadena.formatFortran(2,14,6,bas.get(i).R[1]);
            cad+=cadena.formatFortran(2,14,6,bas.get(i).R[2]);
            for(int j=0;j<bas.get(i).fx.size();j++) {
                cad+=cadena.formatFortran(2,14,6,bas.get(i).fx.get(j));
                cad+=cadena.formatFortran(2,14,6,bas.get(i).fy.get(j));
                cad+=cadena.formatFortran(2,14,6,bas.get(i).fz.get(j));
            }
            aux.add(cad);
        }
        return aux;
    }
    
    int giveAtom(int X, int Y){
        int atomo=0;
        double R = 10*Math.pow( bas.get(0).X -  X,2)+Math.pow(bas.get(0).Y-  Y,2);
        for(int i=0;i<bas.size();i++)
            if(Math.pow(bas.get(i).X- X,2)+Math.pow(bas.get(i).Y- Y,2)<R){
            R=Math.pow(bas.get(i).X-X,2)+Math.pow(bas.get(i).Y-Y,2);
            //atomo mas cercano a la posicion X Y
            atomo=i;
            }
        return atomo;
    }
    
    public void ajustar_maximos(){
        if(bas.size()>0){
            for(int i=0;i<3;i++)  r_max[i]=r_min[i]=bas.get(0).R[i];
            Z_max=Z_min=bas.get(0).Z;
            for(int i=1;i<bas.size();i++){
                for(int j=0;j<3;j++){
                    r_max[j]=(r_max[j]>bas.get(i).R[j])?r_max[j]:bas.get(i).R[j];
                    r_min[j]=(r_min[j]<bas.get(i).R[j])?r_min[j]:bas.get(i).R[j];
                }
                Z_max=(Z_max>bas.get(i).Z)?Z_max:bas.get(i).Z;
                Z_min=(Z_min<bas.get(i).Z)?Z_min:bas.get(i).Z;
            }
        }
    }
    public void atomos_selec_frag(){
        for(int i=0; i<bas.size();i++)
            bas.get(i).fix=bas.get(i).selec;
    }
    
    
    public void copyAtoms(){
        basCopy.clear();
        for(int i=0; i<bas.size();i++)
            if(bas.get(i).selec)
                basCopy.add(bas.get(i));
    }
    
    public void pasteAtoms(){
        int j=1;
        for(int i=0; i<basCopy.size();i++){
            basCopy.get(i).posBas=bas.size()+j;
            bas.add(basCopy.get(i));
            j++;
        }
    }
    
    public void rotate(String Gcentro,String GZ,String GX){
        double cx=cadena.readColDouble(1,Gcentro);
        double cy=cadena.readColDouble(2,Gcentro);
        double cz=cadena.readColDouble(3,Gcentro);
        //ajustamos el centro ;
        double Zx=cadena.readColDouble(1,GZ)-cx;
        double Zy=cadena.readColDouble(2,GZ)-cy;
        double Zz=cadena.readColDouble(3,GZ)-cz;
        double Xx=cadena.readColDouble(1,GX)-cx;
        double Xy=cadena.readColDouble(2,GX)-cy;
        double Xz=cadena.readColDouble(3,GX)-cz;
        for(int i=0;i<bas.size();i++ ){
            bas.get(i).R[0]=(bas.get(i).R[0]-cx);
            bas.get(i).R[1]=(bas.get(i).R[1]-cy);
            bas.get(i).R[2]=(bas.get(i).R[2]-cz);
        }
        // for the lvs not -cs -cy -cz just rotate
        
        double xn,yn,zn,cos,sin,tol=Math.pow(10,-8);
        // centrado en 0,0,0
        if(Math.abs(Zx)>tol){
            // rotamos al rededor del eje y hasta que Zx=0+
            sin=-Math.pow(Zx*Zx/(Zx*Zx+Zz*Zz),0.5);
            cos=Math.pow(1-sin*sin,0.5);
            if(Math.abs(Zx*cos + Zz*sin) > tol) sin=-sin;
            xn =  Zx*cos + Zz*sin ;
            yn =  Zy;
            zn = -Zx*sin + Zz*cos ;
            Zx = xn ; Zy=yn ; Zz=zn; //rotamos las dos dir
            xn =  Xx*cos + Xz*sin ;
            yn =  Xy;
            zn = -Xx*sin + Xz*cos ;
            Xx=xn ; Xy=yn ; Xz=zn;
            for(int i=0;i<bas.size();i++ ){
                xn= bas.get(i).R[0]*cos+bas.get(i).R[2]*sin;
                yn= bas.get(i).R[1];
                zn=-bas.get(i).R[0]*sin+bas.get(i).R[2]*cos;
                bas.get(i).R[0]=xn;
                bas.get(i).R[1]=yn;
                bas.get(i).R[2]=zn;
            }
            for(int j=0;j<3;j++){
                xn= lvs[j][0]*cos+lvs[j][2]*sin;
                yn= lvs[j][1];
                zn=-lvs[j][0]*sin+lvs[j][2]*cos;
                lvs[j][0]=xn;
                lvs[j][1]=yn;
                lvs[j][2]=zn;
            }
            
        }
        if(Math.abs(Zy)>tol){
            // rotamos al rededor del eje x hasta que Zy=0
            sin=Math.pow(Zy*Zy/(Zy*Zy+Zz*Zz),0.5);
            cos=Math.pow(1-sin*sin,0.5);
            if(Math.abs(  Zy*cos + Zz*sin ) > tol) sin = -sin;
            xn =  Zx;
            yn =  Zy*cos + Zz*sin ;
            zn = -Zy*sin + Zz*cos ;
            Zx = xn ; Zy=yn ; Zz=zn;
            xn =  Xx;
            yn =  Xy*cos + Xz*sin ;
            zn = -Xy*sin + Xz*cos ;
            Xx = xn ; Xy=yn ; Xz=zn;
            for(int i=0;i<bas.size();i++ ){
                xn= bas.get(i).R[0];
                yn= bas.get(i).R[1]*cos+bas.get(i).R[2]*sin;
                zn=-bas.get(i).R[1]*sin+bas.get(i).R[2]*cos;
                bas.get(i).R[0]=xn;
                bas.get(i).R[1]=yn;
                bas.get(i).R[2]=zn;
            }
            
            for(int j=0;j<3;j++){
                xn= lvs[j][0];
                yn= lvs[j][1]*cos+lvs[j][2]*sin;
                zn=-lvs[j][1]*sin+lvs[j][2]*cos;
                lvs[j][0]=xn;
                lvs[j][1]=yn;
                lvs[j][2]=zn;
            }
            
        }
        if(Math.abs(Xy)>tol){
            // rotamos al rededor del eje z hasta que Xy=0
            sin=Math.pow(Xy*Xy/(Xx*Xx+Xy*Xy),0.5);
            cos=Math.pow(1-sin*sin,0.5);
            if(Math.abs(  -Xx*sin + Xy*cos ) > tol) sin = -sin;
            zn =  Zz;
            xn =  Zx*cos + Zy*sin ;
            yn = -Zx*sin + Zy*cos ;
            Zx = xn ; Zy=yn ; Zz=zn;
            //   System.out.println(xn+" "+yn+" "+zn);
            zn =  Xz;
            xn =  Xx*cos + Xy*sin ;
            yn = -Xx*sin + Xy*cos ;
            // System.out.println(xn+" "+yn+" "+zn);
            Xx = xn ; Xy=yn ; Xz=zn;
            for(int i=0;i<bas.size();i++ ){
                zn= bas.get(i).R[2];
                xn= bas.get(i).R[0]*cos+bas.get(i).R[1]*sin;
                yn=-bas.get(i).R[0]*sin+bas.get(i).R[1]*cos;
                bas.get(i).R[0]=xn;
                bas.get(i).R[1]=yn;
                bas.get(i).R[2]=zn;
            }
            for(int j=0;j<3;j++){
                zn= lvs[j][2];
                xn= lvs[j][0]*cos+lvs[j][1]*sin;
                yn=-lvs[j][0]*sin+lvs[j][1]*cos;
                lvs[j][0]=xn;
                lvs[j][1]=yn;
                lvs[j][2]=zn;
            }
            
        }
    }
    
    public double distancia(double x1,double y1,double z1,double x2,double y2,double z2){
        return Math.pow( Math.pow(x1-x2,2)+ Math.pow(y1-y2,2)+ Math.pow(z1-z2,2)  ,0.5);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BDD8881C-B73D-4076-DC3E-C9CE0C202603]
    // </editor-fold> 
    public class Unnamed {

    }
}

//------------------------------------------------------------------------------------














