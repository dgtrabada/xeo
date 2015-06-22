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

import java.io.File;
import java.io.IOException;
import pintar2D.pintar2D;
import editor.editor;
import calculadora.calculadora;



public class Main {
    
    public static void main(String args[]) {
        String version = version  ="xeo 2.0";
        boolean visible=true;
        reader.reader cadena = new reader.reader();
        String SEP = System.getProperty("file.separator");
        String here= System.getProperty("user.dir");
        
        if(args.length > 0){  // el argumento 0 es el pwd, luego lo que sea .....
            String expresion="0+";
            
            if(args[0].toString().equals("-help"))  print_help();
            
            if(args[0].toString().equals("-xyz")){
                calc_xyz.JCalc_xyz xyz = new calc_xyz.JCalc_xyz();
                xyz.xyzfile=new File(here+SEP+args[1].toString());
                xyz.outfile=new File(here+SEP+args[2].toString());
                xyz.timeStep=Double.valueOf(args[3].toString()).doubleValue();
                String exp="";
                for(int i=4;i<args.length;i++)
                    if(args[i].toString().equals("N")) exp+="\n";
                    else exp+=args[i];
                xyz.loadCodigo(exp);
                xyz.calculator_xyz();
                System.exit(0);
            }
            
            if(args[0].toString().equals("-aver_xyz")){
                calc_xyz.JAverage xyz = new calc_xyz.JAverage(null,null);
                if(args.length>3){
                    if(args[2].toString().equals("-i"))
                        System.out.println(xyz.Average_xyz(here+SEP+args[1].toString(),(int) Double.valueOf(args[4]).doubleValue(),(int) Double.valueOf(args[5]).doubleValue(),true));
                }else
                    System.out.println(xyz.Average_xyz(here+SEP+args[1].toString(),0,0,false));
                System.exit(0);
            }
            
            
            if(args[0].toString().equals("-version")){
                System.out.println(version);
                System.exit(0);
            }
            
            if(args[0].toString().equals("-Calc")){
                for(int i=1;i<args.length;i++) expresion+=args[i];
                calculadora cal=new calculadora();
                System.out.println(cal.calcular(expresion));
                System.exit(0);
            }
            
            if(args[0].toString().equals("-Aver")){
                jCalc.statistic stt = new jCalc.statistic();
                int col=1;
                if(!args[2].toString().equals("null"))col=(int) Double.valueOf(args[2].toString()).doubleValue();
                stt.MediaArchivo(new File(here+SEP+args[1].toString()),col);
                System.out.println(stt.salida);
                System.exit(0);
            }
            
            if(args[0].toString().equals("-Bulk")){
                bulk bulk = new bulk();
                bulk.nameFile=args[1].toString();
                if(!args[2].toString().equals("null")){
                    if(args[2].toString().equals("bcc"))bulk.bcc=true;
                    if(args[2].toString().equals("fcc"))bulk.fcc=true;
                    if(args[2].toString().equals("zincblende"))bulk.fcc=true;
                    if(args[2].toString().equals("file")){
                        bulk.usefile=true;
                        if(bulk.lvsFile!=null) bulk.lvsFile=new File(args[3].toString()).getAbsolutePath();
                    }
                    
                }else bulk.fcc=true;
                bulk.makeBulk();
                System.out.println(bulk.out);
                System.exit(0);
            }
            
            if(args[0].toString().equals("-editor")){
                if(args.length>1){
                    String file_name=here+SEP+args[1].toString();
                    if(new File(file_name).exists()){
                        if(file_name.substring(file_name.length()-3,file_name.length()).equals("jpg"))
                            new dialogo.show_picture(file_name).plot(file_name);
                        else
                            new editor(file_name).openFile(new File(file_name));
                    }else{
                        System.out.println(file_name+" not found");
                        new editor(SEP).setVisible(true);
                    }
                }else new editor(SEP).setVisible(true);
                
                visible=false;
            }
            
            if(args[0].toString().equals("-plotNY")){
                File [] file_name=new File[args.length-1];
                for(int i=0;i<args.length-1;i++)
                    file_name[i]=new File(here+SEP+args[i+1].toString());
                new pintar2D().plotNY(file_name);
                System.out.println("press Ctrl + c to exit");
                visible=false;
            }
            
            if(args[0].toString().equals("-plotXNY-AjY")){
                pintar2D pint =  new pintar2D();
                pint.inputfile2D=new File(here+SEP+args[4].toString());
                pint.g2d.clear();
                pint.imageBuffered = new java.awt.image.BufferedImage(300,300, java.awt.image.BufferedImage.TYPE_INT_RGB);
                int NCol=cadena.numeroCol(pint.inputfile2D);
                for(int k=1;k<=NCol;k++) pint.g2d.add(k+" "+k+" "+"col.temp");
                // pint.opt.x_min=Double.valueOf(args[2].toString()).doubleValue();
                // pint.opt.x_max=Double.valueOf(args[3].toString()).doubleValue();
                pint.Max_onefile_XNY();
                pint.opt.y_min=Double.valueOf(args[2].toString()).doubleValue();
                pint.opt.y_max=Double.valueOf(args[3].toString()).doubleValue();
                pint.show_onefile_XNY();
                pint.VLine((pint.opt.y_min+pint.opt.y_max)/2);
                if(args[1].toString().equals("false")){
                    try{
                        java.awt.image.RenderedImage o = pint.imageBuffered;
                        javax.imageio.ImageIO.write(o,"jpg",new File("out.jpg"));
                        System.exit(0);
                    } catch (IOException e) { }
                }else{
                    System.out.println("press Ctrl + c to exit");
                    new dialogo.show_picture(args[1].toString()).plot(pint.imageBuffered);
                    
                }
                visible=false;
            }
            
            if(args[0].toString().equals("-plotXNY")){
                pintar2D pint =  new pintar2D();
                pint.inputfile2D=new File(here+SEP+ args[2].toString());
                pint.g2d.clear();
                pint.imageBuffered = new java.awt.image.BufferedImage(300,300, java.awt.image.BufferedImage.TYPE_INT_RGB);
                int NCol=cadena.numeroCol(pint.inputfile2D);
                for(int k=1;k<=NCol;k++) pint.g2d.add(k+" "+k+" "+"col.temp");
                pint.Max_onefile_XNY();
                pint.show_onefile_XNY();
                if(args[1].toString().equals("false")){
                    try{
                        java.awt.image.RenderedImage o = pint.imageBuffered;
                        javax.imageio.ImageIO.write(o,"jpg",new File("out.jpg"));
                        System.exit(0);
                    } catch (IOException e) { }
                }else{
                    System.out.println("press Ctrl + c to exit");
                    new dialogo.show_picture(args[1].toString()).plot(pint.imageBuffered);
                }
                visible=false;
            }
            
            
            if(args[0].toString().equals("show")){
                if(args[1].toString().equals("w"))
                    System.out.println(new gpl().gpl_w);
                if(args[1].toString().equals("c"))
                    System.out.println(new gpl().gpl_c);
                System.exit(0);
            }                        
        }
        
        //---------------------------------------------------------
        //------------------------- start xeo ---------------------
        //---------------------------------------------------------
        //case !exit
        if(visible){
            System.out.println(new gpl().gpl_v3);
            System.out.println("for more information type:"+"\n"+"xeo -help \n"+"java -jar xeo.jar -help");
            xeo xeo = new xeo();
            xeo.Iniciar();
            xeo.setVisible(true);
        }
        //---------------------------------------------------------
        //---------------------------------------------------------
        //---------------------------------------------------------
        
    }
    public static void print_help(){
        System.out.println("xeo -version");
        System.out.println("xeo -Calc 4+8" );
        System.out.println("xeo -Aver file [ col ]" );
        System.out.println("xeo -xyz file.xyz output.dat timeStep d[2][9]*2-1.8 N d[1][2] // use N to separe columns ") ;
        System.out.println("xeo -aver_xyz file.xyz                  (for all)");
        System.out.println("xeo -aver_xyz file.xyz -i initial_step final_step");
        System.out.println("xeo -Bulk [ bcc fcc zincblende or file + file.lvs ]");
        System.out.println("xeo -editor file");
        System.out.println("xeo -plotNY  file1 file2 ...");
        System.out.println("xeo -plotXNY [visible] file");
        System.out.println("xeo -plotXNY-AjY [visible] Yini Yfin file");
        System.out.println("                [visible] = false, true \n");
        System.out.println("\n"+new gpl().gpl_v3+"\n");
        System.out.println("xeo show w");
        System.out.println("xeo show c");
        System.exit(0);
    }
    
    
    
}