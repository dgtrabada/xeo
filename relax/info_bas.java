/*
 * info_bas.java
 *
 *	This file is part of fireball-GUI.
 *
 *  fireball-GUI is free software;
 *  you can redistribute it and/or modify it under the terms of the
 *  GNU General Public License as published by the Free Software Foundation;
 *  either version 2 of the License, or (at your option) any later version.
 *
 *   fireball-GUI is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *   or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License along with fireball-GUI;
 *   if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package fireball;
import java.util.ArrayList;
import java.util.Vector;
import java.io.*;
/**
 *
 * @author dani
 */
public class info_bas {
    ArrayList<atom> bas;
    double [][] lvs = new double[3][3];
    reader cadena = new reader();
    boolean seeBond;
    String path;
    String secondline_xyz;
    periodicTable  periodicTable = new  periodicTable();
    babel babel = new babel();
    int tol;
    double [] r_max = new double[3];
    double [] r_min = new double[3];
    
    int Z_max,Z_min;
    int lvs_1,lvs_2,lvs_3;
    //-----------------------
    boolean seeCell_Vol;
    boolean seeCell_ijk;
    double [] R_max = new double[3];
    double [] R_min = new double[3];
    
    //------------------------------
    
    Vector xyz =  new Vector();
    
    //------------------------------
    
    boolean LoadingXYZ;
    boolean stopLoadXYZ;
    
    //------------------------------
    
    /** Creates a new instance of info_bas */
    public info_bas() {
        bas = new ArrayList();
        secondline_xyz="";
        seeBond=false;
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
    
    //----------- LEER ARCHIVOS----------------------
    double modulo(double Px,double Py, double Pz){return Math.pow(Math.pow(Px,2)+Math.pow(Py,2)+Math.pow(Pz,2),0.5);}
    
    void loadValues(){
        babel.load_Values();
        bas=babel.bas;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                lvs[i][j] = babel.lvs[i][j];
        
        
        if(seeCell_Vol) Adj_Vol();
        if(seeCell_ijk) Adj_ijk();
        if(seeBond)load_enlaces();
    }
    
    
    void load_bas(File basfile){
        if(basfile!=null)
            if(basfile.exists()){
            babel.sort="fireball";
            babel.load_bas(basfile);
            bas=babel.bas;
            if(seeCell_Vol) Adj_Vol();
            if(seeCell_ijk) Adj_ijk();
            if(seeBond)load_enlaces();
            }else{//if basfile don't exits
            int il=0;
            atom atom = new atom();
            atom.posBas=il;
            atom.posOut=il;
            atom.Z=1;
            //     atom.n_electron=(1);
            for (int i=0;i<3;i++)atom.R[i]=0.00;
            bas.add(atom);
            }
    }
    void load_lvs(File lvsfile){
        babel.load_lvs(lvsfile);
      for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                lvs[i][j] = babel.lvs[i][j];
    }
    
    void Adj_Vol(){
        int il=bas.size()+1;
        for(int n=0;n<bas.size();n++)
            if( bas.get(n).R[0] < R_min[0]|| bas.get(n).R[0] > R_max[0] ||
                bas.get(n).R[1]< R_min[1]|| bas.get(n).R[1] > R_max[1] ||
                bas.get(n).R[2] < R_min[2]||bas.get(n).R[2] > R_max[2]) {
            bas.remove(n); n--; }
        double x,y,z;
        double max= modulo(R_min[0]-R_max[0],R_min[1]-R_max[1],R_min[2]-R_max[2]);
        int imax=(int) (2*max/modulo(lvs[0][0],lvs[1][0],lvs[2][0])) ;
        int jmax=(int) (2*max/modulo(lvs[0][1],lvs[1][1],lvs[2][1])) ;
        int kmax=(int) (2*max/modulo(lvs[0][2],lvs[1][2],lvs[2][2])) ;
        int Natom=bas.size();
        for(int n=0;n<Natom;n++)
            for(int i = -imax ; i<= imax ; i++  )
                for(int j = -jmax ; j<= jmax ; j++  )
                    for(int k = -kmax; k <= kmax ; k++  ){
            x= bas.get(n).R[0] + ( i*lvs[0][0] + j*lvs[0][1] + k*lvs[0][2]) ;
            y= bas.get(n).R[1] + ( i*lvs[1][0] + j*lvs[1][1] + k*lvs[1][2]) ;
            z= bas.get(n).R[2] + ( i*lvs[2][0] + j*lvs[2][1] + k*lvs[2][2]) ;
            if( x > R_min[0]&& x < R_max[0] && y > R_min[1]&& y < R_max[1] && z > R_min[2]&& z < R_max[2]){
                boolean eq=false;
                for(int a=0;a<bas.size();a++) if( bas.get(a).distamcePunto(x,y,z) < 0.01 ) eq=true;
                if(!eq){
                    atom atom = new atom();
                    atom.posBas=il;
                    atom.posOut=il;
                    atom.Z=bas.get(n).Z;
                    atom.R[0]=x;
                    atom.R[1]=y;
                    atom.R[2]=z;
                    bas.add(atom);
                    il++;
                }
            }
                    }
    }
    
    
    void Adj_ijk(){
        int il=bas.size()+1;
        double x,y,z;
        int Natom=bas.size();
        for(int n=0;n<Natom;n++)
            for(int i = 0 ; i< lvs_1 ; i++  )
                for(int j = 0 ; j< lvs_2 ; j++  )
                    for(int k = 0; k < lvs_3 ; k++  ){
            x= bas.get(n).R[0] + ( i*lvs[0][0] + j*lvs[0][1] + k*lvs[0][2]) ;
            y= bas.get(n).R[1] + ( i*lvs[1][0] + j*lvs[1][1] + k*lvs[1][2]) ;
            z= bas.get(n).R[2] + ( i*lvs[2][0] + j*lvs[2][1] + k*lvs[2][2]) ;
            boolean eq=false;
            for(int a=0;a<bas.size();a++) if( bas.get(n).distamcePunto(x,y,z) < 0.01 ) eq=true;
            if(!eq){
                atom atom = new atom();
                atom.posBas=il;
                atom.posOut=il;
                atom.Z=bas.get(n).Z;
                atom.R[0]=x;
                atom.R[1]=y;
                atom.R[2]=z;
                bas.add(atom);
                il++;
            }
                    }
        
    }
    
    
    void load_xyz(File xyzFile){
        if(xyzFile.exists()){
            xyz.clear();
            secondline_xyz="";
            try{
                String str="";
                bas.clear();
                BufferedReader inxyz = new BufferedReader(new FileReader(xyzFile.getAbsolutePath()));
                int natom=0;
                int paso=0;
                while ((str = inxyz.readLine()) != null && !stopLoadXYZ){
                    LoadingXYZ=true;
                    try{natom =(int) Double.valueOf(cadena.readCol(1,str)).doubleValue(); }catch(NumberFormatException ex){System.out.println("error read numero de atomos bas");}
                    ArrayList<atom> aux;
                    aux= new ArrayList();
                    secondline_xyz+=inxyz.readLine(); //lo acumulamos aqui, pero luego leer numero de lineas = al numero de pasos
                    secondline_xyz+="\n";
                    for(int k=0;k<natom;k++){
                        str = inxyz.readLine();
                        atom atom = new atom();
                        atom.posBas=k;
                        atom.posOut=k;
                        try{atom.Z=periodicTable.getZ(cadena.readCol(1,str).trim()); }catch(NumberFormatException ex){System.out.println("error read Z xyz");}
                        try{atom.R[0]=(Double.valueOf(cadena.readCol(2,str)).doubleValue()); }catch(NumberFormatException ex){System.out.println("error read x xyz");}
                        try{atom.R[1]=(Double.valueOf(cadena.readCol(3,str)).doubleValue());}catch(NumberFormatException ex){System.out.println("error read y xyz");}
                        try{atom.R[2]=(Double.valueOf(cadena.readCol(4,str)).doubleValue());}catch(NumberFormatException ex){System.out.println("error read z xyz");}
                        // atom.n_electron=periodicTable.electrons(atom.Z);
                        // atom.n_hole=periodicTable.hole(atom.R[2]);
                        aux.add(atom);
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
                        }
                        
                    }
                    //------ cargamos los enlaces ---------
                    for(int i=0;i<aux.size();i++){
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
                    paso++; //lo utilizo para los maximos
                    xyz.add(aux);
                }
                inxyz.close();
            }catch (IOException oe) {System.out.println("error read basfile");}
        }
        
        LoadingXYZ=false;
        stopLoadXYZ=false;
    }
    
    //--------------------------------------------------------------------------
    void load_enlaces(){
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
    
    int IntD_100(atom atomo1, atom atomo2) {return ((int) (100*Math.pow( Math.pow(atomo1.R[0]-atomo2.R[0],2) +
            Math.pow(atomo1.R[1]-atomo2.R[1],2) +
            Math.pow(atomo1.R[2]-atomo2.R[2],2) ,0.5)));  }
    
    void load_distancia(int i,int j){ //i,j  -> son posiciones de bas
        boolean put=true;
        int iPos=givePos_fromBas(i);
        int jPos=givePos_fromBas(j);
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
    
    void load_angulo(int ini,int med,int fin ){ //i,j  -> son posiciones de bas
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
    double d2atomos(atom atomo1, atom atomo2) {
        return Math.pow( Math.pow(atomo1.R[0]-atomo2.R[0],2) +
                Math.pow(atomo1.R[1]-atomo2.R[1],2) +
                Math.pow(atomo1.R[2]-atomo2.R[2],2) ,0.5);
    }
    
    double anguloAtomos(atom atomo1, atom atomo2, atom atomo3){
        double ax=atomo1.R[0]-atomo2.R[0];
        double ay=atomo1.R[1]-atomo2.R[1];
        double az=atomo1.R[2]-atomo2.R[2];
        double bx=atomo3.R[0]-atomo2.R[0];
        double by=atomo3.R[1]-atomo2.R[1];
        double bz=atomo3.R[2]-atomo2.R[2];
        double cos=(ax*bx+ay*by+az*bz)/Math.pow(ax*ax+ay*ay+az*az,0.5)/Math.pow(bx*bx+by*by+bz*bz,0.5);
        return Math.acos(cos)*180/Math.PI;
    }
    
    double angulosXYZ2atomos(double x,double y,double z,atom atomo2, atom atomo3){
        double ax=x-atomo2.R[0];
        double ay=y-atomo2.R[1];
        double az=z-atomo2.R[2];
        double bx=atomo3.R[0]-atomo2.R[0];
        double by=atomo3.R[1]-atomo2.R[1];
        double bz=atomo3.R[2]-atomo2.R[2];
        double cos=(ax*bx+ay*by+az*bz)/Math.pow(ax*ax+ay*ay+az*az,0.5)/Math.pow(bx*bx+by*by+bz*bz,0.5);
        return Math.acos(cos)*180/Math.PI;
    }
    
    double getAngulo(double x0,double y0,double z0,double x1,double y1,double z1,double x2,double y2,double z2){
        double ax=x0-x1;
        double ay=y0-y1;
        double az=z0-z1;
        double bx=x2-x1;
        double by=y2-y1;
        double bz=z2-z1;
        double cos=(ax*bx+ay*by+az*bz)/Math.pow(ax*ax+ay*ay+az*az,0.5)/Math.pow(bx*bx+by*by+bz*bz,0.5);
        return Math.acos(cos)*180/Math.PI;
    }
    
    int givePos_fromBas(int i){
        int aux=0;
        for(int j = 0; j< bas.size();j++) if(bas.get(j).posBas == i ) aux= j ;
        return  aux;
    }
    
    void changeCharge(int Z){
        for(int i=0;i<bas.size();i++) if(bas.get(i).selec) {
            bas.get(i).Z=Z;
            //    bas.get(i).n_electron=periodicTable.electrons(Z);
            //    bas.get(i).n_hole=periodicTable.hole(Z);
        }
    }
    
    
    void eliminate(){
        for(int i=0;i<bas.size();i++) if(bas.get(i).selec) {bas.remove(i);i--;}
    }
    
    void copy(){
        for(int i=0;i<bas.size();i++) {
            if(bas.get(i).selec) {
                atom atomo_aux = new atom();
                atomo_aux.equalTo(bas.get(i)) ;
                atomo_aux.selec=false;
                //atomo_aux.n_electron=periodicTable.electrons(atomo_aux.R[2]);
                //atomo_aux.n_hole=periodicTable.hole(atomo_aux.R[2]);
                bas.add(atomo_aux);
            }
        }
    }
    
    void change(int i,int j){
        atom atom_i = bas.get(i);
        atom atom_j = bas.get(j);
        bas.remove(i);
        bas.remove(j-1);   //ojo que antes hemos quitado el i !!!  (i<j)
        bas.add(i,atom_j);
        bas.add(j,atom_i);
    }
    
    void mover_pos(int i,int j){
        bas.add(j,bas.get(i));
        bas.remove(i+1);
    }
    
    void orderY(){
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
    
    void orderX(){
        //primero los ordeno
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).R[0]<bas.get(j).R[0])
                    change(i,j);
        //y luego los marco
        for(int i=0;i<bas.size();i++) bas.get(i).posOut=i;
    }
    
    void order_atomZ(){
        //primero los ordeno
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).R[2]>bas.get(j).R[2])
                    change(i,j);
        //y luego los marco
        for(int i=0;i<bas.size();i++) bas.get(i).posOut=i;
    }
    
    void order_Castep(){
        //estan ordenados por Z atomica, ordenamos en z pero solo los de Z==
        order_atomZ();
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).R[2]==bas.get(j).R[2])
                    if(bas.get(i).R[2]<bas.get(j).R[2])
                        change(i,j);
        //y luego los marco
        for(int i=0;i<bas.size();i++) (bas.get(i)).posOut=i;
    }
    
    void orderZ(){
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
    
    void orderBas(){
        //primero los ordeno
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).posBas>bas.get(j).posBas)
                    change(i,j);
        //y luego los marco
        for(int i=0;i<bas.size();i++) bas.get(i).posOut=i;
    }
    
    void SelectFrag() {
        for(int i=0;i<bas.size();i++)
            if(bas.get(i).frag)
                bas.get(i).selec=true;
    }
    
    Vector FragmentsVector(){
        Vector aux=new Vector(bas.size()+1);
        aux.add("0");
        aux.add("1");
        int nfrag=0;
        for(int i=0;i<bas.size();i++)
            if( bas.get(i).selec )
                nfrag++;
        aux.add(nfrag);
        for(int i=0;i<bas.size();i++)
            if(  bas.get(i).selec )
                aux.add((bas.get(i).posOut+1)+"  1 1 1");
        return aux;
    }
    
    
    Vector outbas(){
        //ordenamos respecto la posicion OUT
        for(int i=0;i<bas.size()-1;i++)
            for(int j=i+1;j<bas.size();j++)
                if(bas.get(i).posOut > bas.get(j).posOut)
                    change(i,j);
        Vector aux=new Vector(bas.size()+1);
        aux.add(bas.size());
        for(int i=0;i<bas.size();i++) {
            aux.add(cadena.format(4,bas.get(i).R[2] )
            +cadena.formatFortran(2,12,6,bas.get(i).R[0])
            +cadena.formatFortran(2,12,6,bas.get(i).R[1])
            +cadena.formatFortran(2,12,6,bas.get(i).R[2])) ;
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
    
    void ajustar_maximos(){
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
    void atomos_selec_frag(){
        for(int i=0; i<bas.size();i++)
            bas.get(i).frag=bas.get(i).selec;
    }
    void rotate(String Gcentro,String GZ,String GX){
        double cx=Double.valueOf(cadena.readCol(1,Gcentro)).doubleValue();
        double cy=Double.valueOf(cadena.readCol(2,Gcentro)).doubleValue();
        double cz=Double.valueOf(cadena.readCol(3,Gcentro)).doubleValue();
        //ajustamos el centro ;
        double Zx=Double.valueOf(cadena.readCol(1,GZ)).doubleValue()-cx;
        double Zy=Double.valueOf(cadena.readCol(2,GZ)).doubleValue()-cy;
        double Zz=Double.valueOf(cadena.readCol(3,GZ)).doubleValue()-cz;
        double Xx=Double.valueOf(cadena.readCol(1,GX)).doubleValue()-cx;
        double Xy=Double.valueOf(cadena.readCol(2,GX)).doubleValue()-cy;
        double Xz=Double.valueOf(cadena.readCol(3,GX)).doubleValue()-cz;
        for(int i=0;i<bas.size();i++ ){
            bas.get(i).R[0]=(bas.get(i).R[0]-cx);
            bas.get(i).R[1]=(bas.get(i).R[1]-cy);
            bas.get(i).R[2]=(bas.get(i).R[2]-cz);
        }
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
        }
    }
    
    //---------------------------------------------------------------------------
    //---------------------- relajamos poscion covalente-------------------------
    Vector esqueleto= new Vector();
    toyModel toy ;
    morse morse;
    boolean toyEnabled=false;
    boolean morseEnabled=false;
    
    void relaxMontecarlo(int end,boolean fixCMS){
    /*    toy=new toyModel(this);
        toyEnabled=true;
        toy.endStep=end;
        toy.fixCMS=fixCMS;
        toy.start();*/
    }
    void relaxMorse(int end,double dt,String out){
/*        morse=new morse(this);
        morseEnabled=true;
        morse.endStep=end;
        morse.dt=dt;
        morse.outFilename=out;
        morse.start();*/
    }
    
     /*
    void LoadALLVecinosRelax(double r_min,double r_max,boolean inicializar){ //ojo vecino != enlaces  (puede haber enlaces dobles)
        // coje vecinos desde r_min*dc hasta r_max*dc
        double x,y,z,dc,xi,yi,zi,d;
        if(inicializar)
        for(int v1=0;v1<bas.size();v1++){
            bas.elementAt(v1).n_enlaces=0;
            bas.elementAt(v1).n_vecinos=0;
            bas.elementAt(v1).vecino="";
        }
        for(int v1=0;v1<bas.size();v1++)
            for(int v2=v1;v2<bas.size();v2++)
                for(int i2 = -2 ; i2<= 2 ; i2++  )
                    for(int j2 = -2 ; j2<= 2 ; j2++  )
                        for(int k2 = -2; k2 <= 2 ; k2++  )
                            if(!(v2==v1&& i2==0 && j2==0 && k2==0)){
            x= ((atom)bas.elementAt(v2)).R[0] + ( i2*lvs[0][1] + j2*lvs[0][2] + k2*lvs[0][3]) ;
            y= ((atom)bas.elementAt(v2)).R[1] + ( i2*lvs[1][1] + j2*lvs[1][2] + k2*lvs[1][3]) ;
            z= ((atom)bas.elementAt(v2)).R[2] + ( i2*lvs[2][1] + j2*lvs[2][2] + k2*lvs[2][3]) ;
            dc=(periodicTable.getRadio_100(((atom)bas.elementAt(v2)).R[2])+periodicTable.getRadio_100(((atom)bas.elementAt(v1)).R[2]))/1020;
            xi=((atom) bas.elementAt(v1)).R[0];
            yi=((atom) bas.elementAt(v1)).R[1];
            zi=((atom) bas.elementAt(v1)).R[2];
            d=distancia(xi,yi,zi,x,y,z);
            int atrak=0;
            if(((atom) bas.elementAt(v1)).enlacesLibres()>0&&((atom) bas.elementAt(v2)).enlacesLibres()>0)atrak=1;
            if( d>r_min*dc && d<=r_max*dc){ // <= (dc+dc*20/100 ) {
                ((atom) bas.elementAt(v1)).vecino+=v2+" "+x+" "+y+" "+z+" "+atrak+" ";
                ((atom) bas.elementAt(v1)).n_vecinos++;
                ((atom) bas.elementAt(v1)).n_enlaces=((atom) bas.elementAt(v1)).n_vecinos;
                ((atom) bas.elementAt(v2)).vecino+=v1+" "+xi+" "+yi+" "+zi+" "+atrak+" ";  //1 significa que se atraen  0 se repelen
                ((atom) bas.elementAt(v2)).n_vecinos++;
                ((atom) bas.elementAt(v2)).n_enlaces=((atom) bas.elementAt(v2)).n_vecinos;
            }
                            }
        LoadBondsDoble();
    }
      
    void LoadBondsDoble(){
        for(int v1=0;v1<bas.size();v1++){
            ((atom) bas.elementAt(v1)).n_enlaces_dobles=0;
            for(int i=1;i<=((atom) bas.elementAt(v1)).n_vecinos;i++)
                if(((atom) bas.elementAt(v1)).enlacesLibres()>0&&((atom) bas.elementAt(((atom) bas.elementAt(v1)).getVecino(i))).enlacesLibres()>0)
                    ((atom) bas.elementAt(v1)).n_enlaces_dobles++;
        }
    }
      */
    double distancia(double x1,double y1,double z1,double x2,double y2,double z2){
        return Math.pow( Math.pow(x1-x2,2)+ Math.pow(y1-y2,2)+ Math.pow(z1-z2,2)  ,0.5);
    }
    
//---------------------------------------------------------------------------
    
}

//------------------------------------------------------------------------------------














