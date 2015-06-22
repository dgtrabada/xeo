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

package reader;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;
import java.util.ArrayList;

public class reader {
    String aux;
    double auxD;
    int auxI;
    File auxF;
    public int [] C = new int[14];
    public int [] ancho = new int[14];
    public reader(){
        ancho[0]=1;C[0]=-13369345;
        ancho[1]=1;C[1]=-16777216;
        ancho[2]=1;C[2]=-39322;
        ancho[3]=1;C[3]=-16737793;
        ancho[4]=1;C[4]=-16737997;
        ancho[5]=1;C[5]=-13312;
        ancho[6]=1;C[6]= -6710887;
        ancho[7]=1;C[7]=-6684673;
        ancho[8]=1;C[8]=-6684775;
        ancho[9]=1;C[9]=-13108;
        ancho[10]=1;C[10]=-3355393;
        ancho[11]=1;C[11]=-3355444;
        
    }
    
    public  int getColor(int k){
        int ret=0;
        if(k<12) ret=C[k];
        else ret=C[(k-2)%10+2];
        return ret;
    }
    
    public java.awt.Color getGris(java.awt.Color C){
        int o= ((int)((C.getGreen()+C.getBlue()+C.getRed())/3.0));
        return new java.awt.Color(o,o,o);
    }
    
    public String readColString(int i,String Cadena){
        //            1               2               3
        //     |------|******|---------|******|-------|****|
        //Bit  000000011111111000000000111111110000000111111
        aux="0.0";
        int COL=0;
        boolean Bit=false;
        if(Cadena!=null){
            for(int j = 0; j<Cadena.length(); j++ ){
                if(Cadena.substring(j,j+1).equals(" ") || Cadena.substring(j,j+1).equals("\t")) Bit=false;
                else{
                    if(!Bit){
                        COL++;
                        Bit=true;
                        if(COL==i)aux=Cadena.substring(j,j+1);
                    }else if(COL==i)aux+=Cadena.substring(j,j+1);
                    
                }
            }
        }
        if(i>COL)aux="0.0";
        return aux.trim();
    }
    
    public String readColStringSEP(int i,String Cadena, String SEP){
        //            1               2               3
        //     |------|******|---------|******|-------|****|
        //Bit  000000011111111000000000111111110000000111111
        aux="0.0";
        int COL=0;
        boolean Bit=false;
        if(Cadena!=null){
            for(int j = 0; j<Cadena.length(); j++ ){
                if(Cadena.substring(j,j+1).equals(SEP)) Bit=false;
                else{
                    if(!Bit){
                        COL++;
                        Bit=true;
                        if(COL==i)aux=Cadena.substring(j,j+1);
                    }else if(COL==i)aux+=Cadena.substring(j,j+1);
                    
                }
            }
        }
        if(i>COL)aux="0.0";
        return aux.trim();
    }
    
    
    public double readColDouble(int i, String Cadena){
        auxD=0.0 ;
        try{
            auxD=Double.valueOf(readColString(i,Cadena)).doubleValue();
        }catch(NumberFormatException ex){System.out.println("error in  readColDouble : reader.java : "+Cadena);}
        return auxD ;
    }
    
    public int readColInt(int i, String Cadena){
        auxI=0 ;
        try{
            auxI=(int) Double.valueOf(readColString(i,Cadena)).doubleValue();
        }catch(NumberFormatException ex){System.out.println("error in  readColInt : reader.java : "+Cadena);}
        return auxI ;
    }
    
    public File readColFile(int i, String Cadena){
        auxF=null ;
        try{
            auxF=new File(readColString(i,Cadena));
        }catch(NumberFormatException ex){System.out.println("error in  readColFile : reader.java : "+Cadena);}
        return auxF ;
    }
    
    public int endCol(int i,String Cadena){
        //            1               2               3
        //     |------|******|---------|******|-------|****|
        //Bit  000000011111111000000000111111110000000111111
        //                  |                |            |
        //return            15               30           45
        int COL=0,n=0;
        boolean Bit=false;
        if(i==0) n=0;
        else{
            if(Cadena!=null){
                for(int j = 0; j<Cadena.length(); j++ ){
                    if(COL<i)n++;
                    if(Cadena.substring(j,j+1).equals(" ") || Cadena.substring(j,j+1).equals("\t")) Bit=false;
                    else{
                        if(!Bit){
                            COL++;
                            Bit=true;
                            if(COL==i)n++;
                        }else if(COL==i)n++;
                    }
                }
            }
        }
        return n;
    }
    String ret="";
    public String sustituir(String A, String B, String cadena){ //cambia A por B en la cadena; cadenA -> cadenB
        ret=cadena;
        while(ret.contains(A))
            ret=ret.substring(0,ret.indexOf(A))+B+ret.substring(A.length()+ret.indexOf(A),ret.length());
        return ret;
    }
    
    /*
    System.out.println(cadena.sustituir("A","B","cadenA"));
     cadenB
    System.out.println(cadena.sustituir("A","12","cAdenA"));
     c12den12
    System.out.println(cadena.sustituir("de","F","cadenA"));
     caFnA
     */
    
    public String SubtStrig(String A,String B,int col){
        //sustiuimos _._._.A.A.A   por  _._._._.B.B es decir colB en colA
        aux=A.substring(0,endCol(col-1,A));
        for(int i=1;i<(endCol(col,A)-endCol(col-1,A)-B.length());i++) aux+=" ";
        aux+=B;
        aux+=A.substring(endCol(col,A)-1,A.length());
        return aux;
    }
    
    public String readLine(int i,String Cadena){ //no tenemos en cuenta las lineas sin nada
        //            1               2               3
        //     |------|******|---------|******|-------|****|
        //Bit  000000011111111000000000111111110000000111111
        aux="0.0";
        int COL=0;
        boolean Bit=false;
        if(Cadena!=null){
            for(int j = 0; j<Cadena.length(); j++ ){
                if(Cadena.substring(j,j+1).equals("\n")) Bit=false;
                else{
                    if(!Bit){
                        COL++;
                        Bit=true;
                        if(COL==i)aux=Cadena.substring(j,j+1);
                    }else if(COL==i)aux+=Cadena.substring(j,j+1);
                    
                }
            }
        }
        if(i>COL)aux="0.0";
        return aux;
    }
    
    public  int nLine(String Cadena){
        int COL=0;
        boolean Bit=false;
        if(Cadena!=null)
            for(int j = 0; j<Cadena.length(); j++ ){
            if(Cadena.substring(j,j+1).equals("\n")) Bit=false;
            else{if(!Bit){COL++;Bit=true;}}
            }
        return COL;
    }
    
    
    public int nCol(String Cadena){
        int COL=0;
        boolean Bit=false;
        if(Cadena!=null)
            for(int j = 0; j<Cadena.length(); j++ ){
            if(Cadena.substring(j,j+1).equals(" ") || Cadena.substring(j,j+1).equals("\t")) Bit=false;
            else{if(!Bit){COL++;Bit=true;}}
            }
        return COL;
    }
    
    public Vector readFile( File inputFile ){
        Vector vector = new Vector();
        if(inputFile.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(inputFile)) ;
                String str="";
                while ((str = in.readLine()) != null) {
                    vector.addElement(str);
                }
                in.close();
            }catch (IOException oe) {System.out.println("error: read " +inputFile.getAbsolutePath());}
        } else System.out.println(inputFile.getAbsolutePath()+" doesn't exist");
        return vector;
    }
    
    public Vector readFileCol(int i, File inputFile ){
        Vector vector = new Vector();
        if(inputFile.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(inputFile.getPath())) ;
                String str="";
                while ((str = in.readLine()) != null) {
                    vector.addElement(readColString(i,str));
                }
                in.close();
            }catch (IOException oe) {System.out.println("error: read " +inputFile.getAbsolutePath().toString());}
        } else System.out.println(inputFile.getAbsolutePath()+" doesn't exist");
        return vector;
    }
    
    
    public void writer( Vector vector , File inputFile ){
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputFile);
            java.io.DataOutputStream in = new java.io.DataOutputStream(archivo);
            for(int i = 0 ; i < vector.size(); i++ ) in.writeBytes(vector.elementAt(i)+"\n");
            in.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error: write " +inputFile.getAbsolutePath());}
    }
    
    
    public void writer( ArrayList vector , File inputFile ){
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(inputFile);
            java.io.DataOutputStream in = new java.io.DataOutputStream(archivo);
            for(int i = 0 ; i < vector.size(); i++ ) in.writeBytes(vector.get(i)+"\n");
            in.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error: write " +inputFile.getAbsolutePath());}
    }
    
    public ArrayList readFileArrayList( File inputFile ){
        ArrayList<String> vector = new ArrayList();
        if(inputFile.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(inputFile)) ;
                String str="";
                while ((str = in.readLine()) != null) {
                    vector.add(str);
                }
                in.close();
            }catch (IOException oe) {System.out.println("error: read " +inputFile.getAbsolutePath());}
        } else System.out.println(inputFile.getAbsolutePath()+" doesn't exist");
        return vector;
    }
    
    public  void writerString( String aux , String inputFile ){
        try {
            java.io.FileOutputStream archivo = new java.io.FileOutputStream(new File(inputFile));
            java.io.DataOutputStream in = new java.io.DataOutputStream(archivo);
            in.writeBytes(aux);
            in.close();
            archivo.close();
        }catch (IOException oe) {System.out.println("error: write " +inputFile);}
    }
    
    
    public int numeroFilas(File inputFile){
        int nC=0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile.getAbsolutePath())) ;
            String str="";
            nC=0;
            while ((str = in.readLine()) != null) nC++;
            in.close();
        }catch (IOException oe) {System.out.println("numeroCol");}
        return nC;
    }
    
    public int numeroCol(File inputFile){
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile.getAbsolutePath())) ;
            aux = in.readLine();
        }catch (IOException oe) {System.out.println("numeroCol");}
        return nCol(aux);
    }
    
    public  String noD(String a){
        if(a.indexOf("D")>0) aux = a.substring(0,a.indexOf("D"))+"E"+a.substring(a.indexOf("D")+1,a.length());
        else aux=a;
        return aux;
    }
    
    public String pasarString(double z){
        String zOut="",zIn=z+"",Int="",Exp="";
        int n=0;
        int ex=-1;
        if(Math.abs(z)<1.0E-300) zOut="0.0";
        else{
            if((z>0.0001&&z<999999)||(z<-0.001&&z>-99999)){
                String h="";   //quitamos los 0000 que sobren
                for(int i=0;(i<zIn.length()&&i<6);i++)
                    h+=zIn.substring(i,i+1);
                int punto=0;
                for(int i=0;i<h.length();i++)
                    if(h.substring(i,i+1).equals("."))
                        punto=i;
                if(punto>0 && h.substring(h.length()-1,h.length()).equals("0")){
                    int corte=h.length();
                    for(int i = h.length();i>punto;i--)
                        if(h.substring(i-2,i).equals("00"))
                            corte=i-2;
                    zOut=h.substring(0,corte);
                }else zOut=h;
            } else {
                for(int i=0;i<zIn.length();i++)if(zIn.substring(i,i+1).equals("E"))ex=i;
                if(ex!=-1){
                    Exp=zIn.substring(ex,zIn.length());
                    Int=zIn.substring(0,ex-1);
                    for(int i=0;i<(6-Exp.length())&&i<Int.length();i++)zOut+=Int.substring(i,i+1);
                    if(zOut.substring(zOut.length()-1,zOut.length()).equals(".")) zOut=zOut.substring(0,zOut.length()-1);
                } else{
                    for(int i=0;i<zIn.length();i++)if(!(zIn.substring(i,i+1).equals("E")||zIn.substring(i,i+1).equals(".")||zIn.substring(i,i+1).equals("-")))Int+=zIn.substring(i,i+1);
                    if(Math.abs(z)<1)for(int i = -2; Math.abs(z)/Math.pow(10,i)<1;i-- ) Exp="E"+i;
                    else for(int i = 3; Math.abs(z)/Math.pow(10,i)>1;i++ ) Exp="E"+i;
                    if(zIn.substring(0,1).equals("-"))zOut+="-";
                    zOut+=Int.substring(0,1);
                    if((6-zOut.length()+Exp.length())>=2 ){
                        zOut+=".";
                        int aux=zOut.length();
                        for(int i=1;i<=(6-aux-Exp.length());i++)zOut+=Int.substring(i,i+1);
                    }
                }
                zOut+=Exp;
            }
            if(zOut.substring(zOut.length()-1,zOut.length()).equals(".")) zOut=zOut.substring(0,zOut.length()-1);
        }
        return zOut;
    }
    
    public double potencia(double input){
        double aux=Math.abs(input);
        int i=0;
        if(aux<1) while(aux*Math.pow(10,i)<1)i++;
        else  while(aux*Math.pow(10,i)>1)i--;
        if(i<0)i++;
        input=Math.signum(input)*Math.pow(10,-i);
        return input;
    }
  /*  2500.0 1000.0
      250.0  100.0
      25.0   10.0
      2.5    1.0
      0.0    0.0
      0.25   0.1
      0.0025 0.0010
   */
    public  double ultimoDecimal(double a){
        a=((int) (a/potencia(a)))*potencia(a);
        return a;
    }
    
    public double ultimos2Decimales(double a){
        double f =ultimoDecimal(a);
        a=ultimoDecimal(a-f);
        a=a+f;
        return a;
    }
    public  double ajustarDecimal(double y,double hy){
        //quiero que 1.345456 0.045 -> 1.340
        //           5421.215 15 -> 5420.0
        double aux=y/potencia(hy);
        if(aux<0) aux--;
        y=(int) (aux);
        return y*potencia(hy);
    }
    
    
    long l=0;   // -9233372036854775808 < long < 9223372036854775807
    String d="";
    //  !formato de fotran ->  2x,f12.6  = Ix,fJ.k   float(I,J,K,string(entrada))
    public  String formatFortran(int I, int J , int K, double x){
        l=(long) x;
        d=((long) (Math.abs(x-l)*Math.pow(10,K)))+"";
        while(d.length()<K)d="0"+d;
        String numero="";
        if(l==0 && x<0.0)numero = "-"+l+"."+d;
        else numero = l+"."+d;
        while(numero.length()<J) numero=" "+numero;
        if(numero.length()>J){
            numero="";
            while(numero.length()<J) numero+="*";
        }
        for(int i=0;i<I;i++) numero=" "+numero;
        return numero;
    }
       /*
    2x,f12.6   |     x
  ------------------------------------
        ************   7.200489933738588E10
        ************   -7.200489933738588E10
        ************   4.851651954097903E8
        ************   -4.851651954097903E8
        ************   3269017.3724721107
        ************   -3269017.3724721107
  22026.465794   22026.465794806718
        ************   -22026.465794806718
    148.413159   148.4131591025766
   -148.413159   -148.4131591025766
      1.000000   1.0
     -1.000000   -1.0
      0.006737   0.006737946999085467
     -0.006737   -0.006737946999085467
      0.000045   4.539992976248485E-5
     -0.000045   -4.539992976248485E-5
      0.000000   3.059023205018258E-7
     -0.000000   -3.059023205018258E-7
      0.000000   2.061153622438558E-9
     -0.000000   -2.061153622438558E-9
        */
    
    /*
    String formatFortran(int I, int J , int K, double x){
        String ret="";
        aux="";
        int SA = (int) (x*Math.pow(10,K));
        aux=SA+"";
        if(Math.abs(x)>=1) ret=aux.substring(0,aux.length()-K)+"."+aux.substring(aux.length()-K,aux.length());
        else {
            if(x<0)ret="-0.";
            else ret="0.";
            for(int g=0;g<K-(Math.abs(SA)+"").toString().length();g++)ret+="0";
            ret+=Math.abs(SA);
        }
        ret=format(I+J,ret);
        //this is for the bas format file
        if(Math.abs(x)>1000) {
            aux=" ";
            for(int i=0;i<I;i++)aux+=" ";
            aux+=x;
            for(int i=0;i<J;i++)aux+=" ";
            ret=(aux).substring(0,J);
        }
        return ret;
    }
     */
    public  String format(int i,double z){
        aux="";
        String Z=pasarString(z);
        for(int j=0;j<i-Z.length();j++) aux=" "+aux;
        return aux+Z;
    }
    public  String format(int i,int z){
        aux="";
        String Z=z+"";
        for(int j=0;j<i-Z.length();j++) aux=" "+aux;
        return aux+Z;
    }
    public  String format(int i,String z){
        aux="";
        for(int j=0;j<i-z.length();j++) aux=" "+aux;
        return aux+z;
    }
    
    public  String ceros(int i, int j){
        aux=j+"";
        int fin=i-aux.length();
        for(int h=0;h<fin;h++) aux=0+""+aux;
        return aux;
    }
    
    public  int pasarBoolenaInt(boolean f){
        if(f) return 1;
        else return 0;
    }
    
    //esta en pruebas, cuidado!, solo usar en grafica, si funciona sustituir  pasarString !!!
    public String digitosGrafica(int dig, double z){
        String zOut="",zIn=z+"",Int="",Exp="";
        int n=0;
        int ex=-1;
        if(Math.abs(z)<1.0E-300) zOut="0.0";
        else{
            if((z>0.0001&&z<999999)||(z<-0.001&&z>-99999)){
                String h="";   //quitamos los 0000 que sobren
                for(int i=0;(i<zIn.length()&&i<dig);i++)
                    h+=zIn.substring(i,i+1);
                int punto=0;
                for(int i=0;i<h.length();i++)
                    if(h.substring(i,i+1).equals("."))
                        punto=i;
                if(punto>0 && h.substring(h.length()-1,h.length()).equals("0")){
                    int corte=h.length();
                    for(int i = h.length();i>punto;i--)
                        if(h.substring(i-2,i).equals("00"))
                            corte=i-2;
                    zOut=h.substring(0,corte);
                }else zOut=h;
            } else {
                for(int i=0;i<zIn.length();i++)if(zIn.substring(i,i+1).equals("E"))ex=i;
                if(ex!=-1){
                    Exp=zIn.substring(ex,zIn.length());
                    Int=zIn.substring(0,ex-1);
                    for(int i=0;i<(dig-Exp.length())&&i<Int.length();i++)zOut+=Int.substring(i,i+1);
                    if(zOut.substring(zOut.length()-1,zOut.length()).equals(".")) zOut=zOut.substring(0,zOut.length()-1);
                } else{
                    for(int i=0;i<zIn.length();i++)if(!(zIn.substring(i,i+1).equals("E")||zIn.substring(i,i+1).equals(".")||zIn.substring(i,i+1).equals("-")))Int+=zIn.substring(i,i+1);
                    if(Math.abs(z)<1)for(int i = -2; Math.abs(z)/Math.pow(10,i)<1;i-- ) Exp="E"+i;
                    else for(int i = 3; Math.abs(z)/Math.pow(10,i)>1;i++ ) Exp="E"+i;
                    if(zIn.substring(0,1).equals("-"))zOut+="-";
                    zOut+=Int.substring(0,1);
                    if((dig-zOut.length()+Exp.length())>=2 ){
                        zOut+=".";
                        int aux=zOut.length();
                        for(int i=1;i<=(dig-aux-Exp.length());i++)zOut+=Int.substring(i,i+1);
                    }
                }
                zOut+=Exp;
            }
            if(zOut.substring(zOut.length()-1,zOut.length()).equals(".")) zOut=zOut.substring(0,zOut.length()-1);
        }
        return zOut;
    }
    
    public boolean startInNumber(String ggg){
        boolean out=false;
        if(ggg.substring(0,1).equals("0")) out=true;
        if(ggg.substring(0,1).equals("1")) out=true;
        if(ggg.substring(0,1).equals("2")) out=true;
        if(ggg.substring(0,1).equals("3")) out=true;
        if(ggg.substring(0,1).equals("4")) out=true;
        if(ggg.substring(0,1).equals("5")) out=true;
        if(ggg.substring(0,1).equals("6")) out=true;
        if(ggg.substring(0,1).equals("7")) out=true;
        if(ggg.substring(0,1).equals("8")) out=true;
        if(ggg.substring(0,1).equals("9")) out=true;
        return out;
    }
    
    public  boolean contiene(String ggg, String g){
        boolean out=false;
        for(int i = 0; i<=(ggg.length()-g.length());i++)
            if(ggg.substring(i,i+g.length()).equals(g)) out=true;
        return out;
    }
    
    
/*
 *
 * la diferencia de los getLine getNline con los read line es que cuentan la linea
 * por cada separador, para los readLine \n+\n+a+\n  es una linea no dos
 */
    
    
    public String getLine(int i,String Cadena){ //tenemos encuenta todos saltos
        String ret="";
        int count=1;
        boolean salir=true;
        if(Cadena!=null){
            if(i==1){
                for(int j = 0; j<Cadena.length() && salir; j++ ){
                    if(Cadena.substring(j,j+1).equals("\n")) salir=false;
                    if(salir)ret+=Cadena.substring(j,j+1);
                }
            } else{
                for(int j = 0; j<Cadena.length() && salir; j++ ){
                    if(Cadena.substring(j,j+1).equals("\n")) count++;
                    if(count==i)
                        for(int k = j+1; k<Cadena.length() && salir; k++ ){
                        if(Cadena.substring(k,k+1).equals("\n")) salir=false;
                        if(salir)ret+=Cadena.substring(k,k+1);
                        }
                }
            }
        }
        return ret;
    }
    public  int getNLine(String Cadena){
        int n=0;
        for(int j = 0; j<Cadena.length(); j++ ) if(Cadena.substring(j,j+1).equals("\n")) n++;
        return n;
    }
    /*
     *
getLine     ||   readline
\n12\n\nesto \nes \n \n\n un\ntest\n
 ||   12
12    ||   esto
    ||   es
esto     ||
es     ||    un
     ||   test
    ||   0.0
 un    ||   0.0
test    ||   0.0
    ||   0.0
    ||   0.0
    ||   0.0
    ||   0.0
12\n\nesto \nes \n \n\n un\ntest\n
12    ||   12
    ||   esto
esto     ||   es
es     ||
     ||    un
    ||   test
 un    ||   0.0
test    ||   0.0
    ||   0.0
    ||   0.0
    ||   0.0
    ||   0.0
    ||   0.0
 \n\n\n12\n\nesto \nes \n \n\n un\ntest\
    ||   12
    ||   esto
    ||   es
12    ||
    ||    un
esto     ||   test
es     ||   0.0
     ||   0.0
    ||   0.0
 un    ||   0.0
test    ||   0.0
    ||   0.0
    ||   0.0
    ||   0.0
    ||   0.0
     *
     */
}

