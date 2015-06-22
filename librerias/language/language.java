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


package language;

public class language {
    public String Language;
    public String lastLanguage;
    public java.util.ArrayList<String> lan;
    public language() {
        lan=new java.util.ArrayList();
        lan.add("arabic");
        // lan.add("bengali");
        lan.add("catalan");
        lan.add("chinese");
        lan.add("czech");
        lan.add("english");
        lan.add("euskera");
        lan.add("french");
        lan.add("galician");
        lan.add("german");
        lan.add("hindi");
        lan.add("italiano");
        lan.add("japanese");
        lan.add("mandarin");
        lan.add("polish");    
        lan.add("portuguese");
        lan.add("russian");
        lan.add("spanish");
        lastLanguage="english";
        Language="english";
    }
    
    String linea_last ="", linea_out = "";
    // tiene que poner exactamente lo mismo, con las mayusculas y todo !!!
    public String traslate(String ini) { //from english to ...
        String mem=ini;
        String ret=ini;
        try {
            java.io.InputStream out = getClass().getResourceAsStream("/languages/"+Language);
            java.io.InputStream last = getClass().getResourceAsStream("/languages/"+lastLanguage);
            java.io.BufferedReader buffer_out = new java.io.BufferedReader(new java.io.InputStreamReader(out,"UTF-8" ));
            java.io.BufferedReader buffer_last = new java.io.BufferedReader(new java.io.InputStreamReader(last,"UTF-8" ));
            linea_out=buffer_out.readLine();
            linea_last=buffer_last.readLine();
            while (linea_out!= null && linea_last!=null) {
                if(ini.trim().equals(linea_last.trim()))
                    ret=linea_out.trim();
                linea_out=buffer_out.readLine();
                linea_last=buffer_last.readLine();
            }
            last.close();
            out.close();
        } catch (Exception e) {e.printStackTrace();}
        
        return ret;
    }
    
}
