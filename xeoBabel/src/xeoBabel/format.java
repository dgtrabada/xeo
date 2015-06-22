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

public class format {
    String aux;
    double auxD;
    int auxI;
    public format(){}
    
    
    String readColString(int i,String Cadena){
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
    
    int nCol(String Cadena){
        int COL=0;
        boolean Bit=false;
        if(Cadena!=null)
            for(int j = 0; j<Cadena.length(); j++ ){
            if(Cadena.substring(j,j+1).equals(" ") || Cadena.substring(j,j+1).equals("\t")) Bit=false;
            else{if(!Bit){COL++;Bit=true;}}
            }
        return COL;
    }
    
    String readColStringSEP(int i,String Cadena, String SEP){
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
    
    boolean startInNumber(String ggg){
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
        if(ggg.substring(0,1).equals(".")) out=true;
        if(ggg.substring(0,1).equals("-")) out=true;
        if(ggg.substring(0,1).equals("+")) out=true;
        return out;
    }
    
    boolean contiene(String ggg, String g){
        boolean out=false;
        for(int i = 0; i<=(ggg.length()-g.length());i++)
            if(ggg.substring(i,i+g.length()).equals(g)) out=true;
        return out;
    }
    
    
    double readColDouble(int i, String Cadena){
        auxD=0.0 ;
        try{
            auxD=Double.valueOf(readColString(i,Cadena)).doubleValue();
        }catch(NumberFormatException ex){System.out.println("error in  readColDouble : reader.java : "+Cadena);}
        return auxD ;
    }
    
    int readColInt(int i, String Cadena){
        auxI=0 ;
        try{
            auxI=(int) Double.valueOf(readColString(i,Cadena)).doubleValue();
        }catch(NumberFormatException ex){System.out.println("error in  readColInt : reader.java : "+Cadena);}
        return auxI ;
    }
    
    long l=0;   // -9233372036854775808 < long < 9223372036854775807
    String d="";
    //  !formato de fotran ->  2x,f12.6  = Ix,fJ.k   float(I,J,K,string(entrada))
    String formatFortran(int I, int J , int K, double x){
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
    
    
    String format(int i,int z){
        aux="";
        String Z=z+"";
        for(int j=0;j<i-Z.length();j++) aux=" "+aux;
        return aux+Z;
    }
    String format(int i,String z){
        aux="";
        for(int j=0;j<i-z.length();j++) aux=" "+aux;
        return aux+z;
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
    
    
    public  String getLine(int i,String Cadena){ //tenemos encuenta todos saltos
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
    int getNLine(String Cadena){
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
    int j=0;
    int subString(String cad, String x1){
        for(int i=1;i<nCol(cad);i++  )
            if(readColString(i,cad).trim().equals(x1.trim()))
                j=i;
        return j; //si no esta devuelve 0
    }
    
    String indexABC(int X){ //pasar a format.java
        String o="";
        if(X==1)o= "a";
        if(X==2)o= "b";
        if(X==3)o= "c";
        if(X==4)o= "d";
        if(X==5)o= "e";
        if(X==6)o= "f";
        if(X==7)o= "g";
        if(X==8)o= "h";
        if(X==9)o= "i";
        if(X==10)o= "j";
        if(X==11)o= "k";
        if(X==12)o= "l";
        if(X==13)o= "m";
        if(X==14)o= "n";
        if(X==15)o= "o";
        if(X==16)o= "p";
        if(X==17)o= "q";
        if(X==18)o= "r";
        if(X==19)o= "s";
        if(X==20)o= "t";
        if(X==21)o= "u";
        if(X==22)o= "v";
        if(X==23)o= "w";
        if(X==24)o= "x";
        if(X==25)o= "y";
        if(X==26)o= "z";
        
        return o;
    }
    
}

