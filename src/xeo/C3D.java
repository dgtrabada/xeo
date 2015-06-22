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

public class C3D {
    double a;   //ampliacion
    double perspectiva,pMax; //pMax=atomo mas cercano
    double[][] Rot = new double[3][3];//la matriz de Rotacion
    int ox,oy;
    
    /** Creates a new instance of C3D */
    public C3D() {
        a=25;
        rotCero();
        Rot[2][0]=Rot[0][1]=1;Rot[1][2]=-1;
    }
    
    void girar(double dx, double dy){
        double cx=Math.cos(dx),sx=Math.sin(dx),cy=Math.cos(dy),sy=Math.sin(dy);
        //      | cos   0   -sin |       | 1   0    0  |
        // GY = |  0    1     0  |  GX = | 0  cos  sin |
        //      | sin   0    cos |       | 0 -sin  cos |
        // Gxy = GY(dx)GX(dy);
        //  Inv(Gxy)=GX(-dy)GY(-dx)=TR(Gxy)
        //  Gxy*Inv(Gxy)=1
        double [][] Gxy={      // Gxy = GY(dx)GX(dy);
            { cx     ,  0  , -sx    },
            { sy*sx  ,  cy , cx*sy  },
            { cy*sx  , -sy , cy*cx }};
        
        double [][] aux=new double[3][3];
        // Rot_0 = Rot[i][j];  Inv(Rot[i][j])=Rot[j][i]
        
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                aux[i][j]=0;
        // calculamos Rot_1 = Gxy * Rot_0
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                for(int k=0;k<3;k++)
                    aux[i][j]+=Gxy[i][k]*Rot[k][j]; // Rot_1 = Gxy * Rot_0
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                Rot[i][j]=aux[i][j];
    }
    
    double pers(double R, double Obj){
        double dscr=+pMax-Obj;//the screen is in the position of the atom more near of the surface
        double one=Math.exp((perspectiva-1)/0.3);
        double dOb=one*100+dscr;  //perspectiva = { 0 -> 1 }
        return R*(dOb-dscr)/dOb;
    }
    
    int cX(double x,double y,double z) {
        if(perspectiva!=1) return ox/2 + (int) ( pers(c_x(x,y,z),c_z(x,y,z))*a );
        else return ox/2 + (int) ( c_x(x,y,z)*a );
    }
    
    int cY(double x,double y,double z){
        if(perspectiva!=1) return oy/2 + (int) ( pers(c_y(x,y,z),c_z(x,y,z))*a);
        else return oy/2 + (int) ( c_y(x,y,z)*a );
    }
    
    double c_x(double x,double y,double z) {
        return x*Rot[0][0]+y*Rot[0][1]+z*Rot[0][2];}
    
    double c_y(double x,double y,double z) {
        return x*Rot[1][0]+y*Rot[1][1]+z*Rot[1][2];}
    
    double c_z(double x,double y,double z) {
        return x*Rot[2][0]+y*Rot[2][1]+z*Rot[2][2];}
    
    void mas(){ a=a*1.5; }
    
    void menos(){ a=a/1.5;}
    
    void rotCero(){
        for(int i=0;i<3;i++)for(int j=0;j<3;j++) Rot[i][j]=0;}
    
    void Oxy(){  rotCero(); Rot[1][0]=Rot[0][1]=Rot[2][2]=1; }
    
    void Omyx(){ rotCero(); Rot[0][0]=Rot[2][2]=1; Rot[1][1]=-1;}
    
    void Ozy(){  rotCero(); Rot[2][0]=Rot[0][1]=1;Rot[1][2]=-1; }
    
    void Ozx(){  rotCero(); Rot[0][0]=1;Rot[2][1]=-1;Rot[1][2]=-1;}
    
    void Oyx(){  rotCero(); Rot[0][0]=Rot[2][2]=1;Rot[1][1]=-1;}
    
    void Oxmy(){ rotCero(); Rot[0][1]=Rot[1][0]=-1;Rot[2][2]=1;}
    
    void Oxz(){  rotCero(); Rot[0][2]=1;Rot[1][0]=-1;Rot[2][1]=1;}
    
    void Oyz(){  rotCero(); Rot[2][0]=-1;Rot[1][1]=-1;Rot[0][2]=1;}
    
}
