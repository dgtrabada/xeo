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


package calculadora;

import java.util.ArrayList;

public class calculadora {
 public ArrayList<String> func;
    public calculadora() {
        func = new ArrayList(); 
        func.add("tanh");
        func.add("tan(");
        func.add("sqrt");
        func.add("sinh");
        func.add("sin(");
        func.add("lg10");
        func.add("log(");
        func.add("flr(");
        func.add("exp(");
        func.add("cosh");
        func.add("cos(");
        func.add("atan");
        func.add("asin");
        func.add("acos");
        func.add("abs(");
        func.add("rnd(");
        func.add("pow(");
    }
    
    public double calcular(String chain){
        double d=0;
        String c="";    
        chain=clean(chain);
        for(int i=0;i<chain.length()-4;i++){
            for(int j=0; j<func.size();j++ ){
                if(chain.substring(i,i+4).equals(func.get(j))){
                    int ini=-1,fin=-1,auxInt=0;
                    for(int k=i;k<chain.length();k++){
                        if(chain.substring(k,k+1).equals("("))auxInt++;
                        if(chain.substring(k,k+1).equals(")"))auxInt--;
                        if(chain.substring(k,k+1).equals("(")&&ini==-1&&fin==-1&&auxInt==1)ini=k;
                        if(chain.substring(k,k+1).equals(")")&&ini!=-1&&fin==-1&&auxInt==0)fin=k;
                    }
                    c=chain.substring(0,i);
                    if(func.get(j).equals("tanh")) c+=Math.atan( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("tan(")) c+=Math.tan( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("sqrt")) c+=Math.sqrt( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("sinh")) c+=Math.sinh( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("sin(")) c+=Math.sin( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("lg10")) c+=Math.log10( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("log(")) c+=Math.log( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("flr(")) c+=Math.floor( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("exp(")) c+=Math.exp( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("cosh")) c+=Math.cosh( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("cos(")) c+=Math.cos( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("atan")) c+=Math.atan( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("asin")) c+=Math.asin( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("acos")) c+=Math.acos( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("abs(")) c+=Math.abs( calcular(chain.substring(ini+1,fin)) ) ;
                    if(func.get(j).equals("rnd(")) c+=Math.random() ;
                    if(func.get(j).equals("pow(")){
                        String aux=chain.substring(ini+1,fin);
                        int coma=0;
                        for(int a=0;a<aux.length();a++) if(aux.substring(a,a+1).equals(",")) coma=a;
                        c+=Math.pow( calcular(aux.substring(0,coma)), Double.valueOf(aux.substring(coma+1,aux.length())).doubleValue() ) ;
                    }
                    c+=chain.substring(fin+1,chain.length());
                    chain=clean(c);
                    i=0;
                }
            }
        }
        d=expresion(chain);
        return d;
    }
    
    String clean(String chain){
        for(int i=0;i<chain.length()-1;i++){
            if(chain.substring(i,i+2).equals("++")||
                    chain.substring(i,i+2).equals("--"))
                chain=chain.substring(0,i)+"+"+chain.substring(i+2,chain.length());
            if(chain.substring(i,i+2).equals("-+")||
                    chain.substring(i,i+2).equals("+-"))
                chain=chain.substring(0,i)+"-"+chain.substring(i+2,chain.length());
            if(chain.substring(i,i+1).equals(" ")) chain=chain.substring(0,i)+chain.substring(i+1,chain.length());
            
        }
        return chain;
    }
    static int maxBraket(String chain){
        int maxP=0,auxInt=0;
        for(int i=0;i<chain.length();i++){
            if(chain.substring(i,i+1).equals("("))auxInt++;
            if(chain.substring(i,i+1).equals(")"))auxInt--;
            maxP=(maxP>=auxInt)?maxP:auxInt;
        }
        return maxP;
    }
    
    double expresion(String chain){
        double d=0;
        int maxP=0,auxInt=0;
        maxP=maxBraket(chain);
        while(maxP!=0){
            int ini=-1,fin=-1;
            boolean exit=false;
            for(int i=0;i<chain.length();i++){
                if(chain.substring(i,i+1).equals("("))auxInt++;
                if(chain.substring(i,i+1).equals(")"))auxInt--;
                if(auxInt==maxP&&ini==-1&&fin==-1)ini=i;
                if(auxInt<maxP&&ini!=-1&&fin==-1)fin=i;
            }
            String c=chain.substring(0,ini)+elemental(chain.substring(ini+1,fin))+chain.substring(fin+1,chain.length());
            chain=clean(c);
            maxP=maxBraket(chain);
        }
        d=elemental(chain);
        return d;
    }
    
    
    double elemental(String chain){
        //not exist ( and ) -> ejemplo: 8+85*4/7-2.E+4
        double d=0;
        ArrayList<String> vec = new ArrayList();        
        String aux="";
        //aux+=chain.substring(0,1);
        for(int i = 0 ; i< chain.length();i++) {
            if(chain.substring(i,i+1).equals("E")||chain.substring(i,i+1).equals("D")){
                aux+="E";
                i++;
                aux+=chain.substring(i,i+1);
                i++;
            }
            if(chain.substring(i,i+1).equals("*")||chain.substring(i,i+1).equals("/")){
                if(!aux.equals("")) {
                    vec.add(aux);
                    vec.add(chain.substring(i,i+1));
                    aux="";
                }else  vec.add(chain.substring(i,i+1));
            }else{
                if((chain.substring(i,i+1).equals("+")||chain.substring(i,i+1).equals("-")&&!aux.equals(""))){
                    vec.add(aux);
                    aux="";
                }
                aux+=chain.substring(i,i+1);
            }
        }
        if(!aux.equals("")) vec.add(aux);
        for(int i= 0 ; i< vec.size(); i++) {
            if(vec.get(i).equals("*")||vec.get(i).equals("/")){
                if(vec.get(i).equals("*"))d=Double.valueOf(vec.get(i-1)).doubleValue()*Double.valueOf(vec.get(i+1)).doubleValue();
                if(vec.get(i).equals("/"))d=Double.valueOf(vec.get(i-1)).doubleValue()/Double.valueOf(vec.get(i+1)).doubleValue();
                vec.remove(++i);
                vec.remove(--i);
                vec.remove(--i);
                vec.add(i,d+"");
            }
        }
        d=0;
        for(int i= 0 ; i< vec.size(); i++) d+=Double.valueOf(vec.get(i)).doubleValue();
        return d;
    }
}
