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

//**************************************************************
//**************************************************************
//**                                                          **
//**  This is the values (the atomic radi and color) of JMOL  **
//**             http://jmol.sourceforge.net/                 **
//**************************************************************
//**************************************************************

package xeoBabel;

import java.awt.Color;

public class periodicTable {
    
    /** Creates a new instance of periodicTable */
    int [] Z = new int[110] ;
    String [] symbol = new String [110] ;
    String [] name = new String [110] ;
    Color [] color = new Color[110] ;
    int [] radio = new int [110] ;
    int z_max=109;
    public periodicTable(){
        Z[1]=1;    symbol[1]="H";   name[1]="hydrogen";     color[1]=new Color(238,238,238);radio[1]=400;//230; //new Color(0xFFFFFFFF)
        Z[2]=2;    symbol[2]="He";  name[2]="helium";       color[2]=new Color(0xFFD9FFFF);radio[2]=930;
        Z[3]=3;    symbol[3]="Li";  name[3]="lithium";      color[3]=new Color(0xFFCC80FF);radio[3]=680;
        Z[4]=4;    symbol[4]="Be";  name[4]="beryllium";    color[4]=new Color(0xFFC2FF00);radio[4]=350;
        Z[5]=5;    symbol[5]="B";   name[5]="boron";        color[5]=new Color(0xFFFFB5B5);radio[5]=830;
        Z[6]=6;    symbol[6]="C";   name[6]="carbon";       color[6]=new Color(0xFF909090);radio[6]=680;
        Z[7]=7;    symbol[7]="N";   name[7]="nitrogen";     color[7]=new Color(0xFF3050F8);radio[7]=680;
        Z[8]=8;    symbol[8]="O";   name[8]="oxygen";       color[8]=new Color(0xFFFF0D0D);radio[8]=680;
        Z[9]=9;    symbol[9]="F";   name[9]="fluorine";     color[9]=new Color(0xFF90E050);radio[9]=640;
        Z[10]=10;  symbol[10]="Ne"; name[10]="neon";        color[10]=new Color(0xFFB3E3F5);radio[10]=1120;
        Z[11]=11;  symbol[11]="Na"; name[11]="sodium";      color[11]=new Color(0xFFAB5CF2);radio[11]=970;
        Z[12]=12;  symbol[12]="Mg"; name[12]="magnesium";   color[12]=new Color(0xFF8AFF00);radio[12]=1100;
        Z[13]=13;  symbol[13]="Al"; name[13]="aluminum";    color[13]=new Color(0xFFBFA6A6);radio[13]=1350;
        Z[14]=14;  symbol[14]="Si"; name[14]="silicon";     color[14]=new Color(0xFFF0C8A0);radio[14]=1200;
        Z[15]=15;  symbol[15]="P";  name[15]="phosphorus";  color[15]=new Color(0xFFFF8000);radio[15]=750;
        Z[16]=16;  symbol[16]="S";  name[16]="sulfur";      color[16]=new Color(0xFFFFFF30);radio[16]=1020;
        Z[17]=17;  symbol[17]="Cl"; name[17]="chlorine";    color[17]=new Color(0xFF1FF01F);radio[17]=990;
        Z[18]=18;  symbol[18]="Ar"; name[18]="argon";       color[18]=new Color(0xFF80D1E3);radio[18]=1570;
        Z[19]=19;  symbol[19]="K";  name[19]="potassium";   color[19]=new Color(0xFF8F40D4);radio[19]=1330;
        Z[20]=20;  symbol[20]="Ca"; name[20]="calcium";     color[20]=new Color(0xFF3DFF00);radio[20]=990;
        Z[21]=21;  symbol[21]="Sc"; name[21]="scandium";    color[21]=new Color(0xFFE6E6E6);radio[21]=1440;
        Z[22]=22;  symbol[22]="Ti"; name[22]="titanium";    color[22]=new Color(0xFFBFC2C7);radio[22]=1470;
        Z[23]=23;  symbol[23]="V";  name[23]="vanadium";    color[23]=new Color(0xFFA6A6AB);radio[23]=1330;
        Z[24]=24;  symbol[24]="Cr"; name[24]="chromium";    color[24]=new Color(0xFF8A99C7);radio[24]=1350;
        Z[25]=25;  symbol[25]="Mn"; name[25]="manganese";   color[25]=new Color(0xFF9C7AC7);radio[25]=1350;
        Z[26]=26;  symbol[26]="Fe"; name[26]="iron";        color[26]=new Color(0xFFE06633);radio[26]=1340;
        Z[27]=27;  symbol[27]="Co"; name[27]="cobalt";      color[27]=new Color(0xFFF090A0);radio[27]=1330;
        Z[28]=28;  symbol[28]="Ni"; name[28]="nickel";      color[28]=new Color(0xFF50D050);radio[28]=1500;
        Z[29]=29;  symbol[29]="Cu"; name[29]="copper";      color[29]=new Color(0xFFC88033);radio[29]=1520;
        Z[30]=30;  symbol[30]="Zn"; name[30]="zinc";        color[30]=new Color(0xFF7D80B0);radio[30]=1450;
        Z[31]=31;  symbol[31]="Ga"; name[31]="gallium";     color[31]=new Color(0xFFC28F8F);radio[31]=1220;
        Z[32]=32;  symbol[32]="Ge"; name[32]="germanium";   color[32]=new Color(0xFF668F8F);radio[32]=1170;
        Z[33]=33;  symbol[33]="As"; name[33]="arsenic";     color[33]=new Color(0xFFBD80E3);radio[33]=1210;
        Z[34]=34;  symbol[34]="Se"; name[34]="selenium";    color[34]=new Color(0xFFFFA100);radio[34]=1220;
        Z[35]=35;  symbol[35]="Br"; name[35]="bromine";     color[35]=new Color(0xFFA62929);radio[35]=1210;
        Z[36]=36;  symbol[36]="Kr"; name[36]="krypton";     color[36]=new Color(0xFF5CB8D1);radio[36]=1910;
        Z[37]=37;  symbol[37]="Rb"; name[37]="rubidium";    color[37]=new Color(0xFF702EB0);radio[37]=1470;
        Z[38]=38;  symbol[38]="Sr"; name[38]="strontium";   color[38]=new Color(0xFF00FF00);radio[38]=1120;
        Z[39]=39;  symbol[39]="Y";  name[39]="yttrium";     color[39]=new Color(0xFF94FFFF);radio[39]=1780;
        Z[40]=40;  symbol[40]="Zr"; name[40]="zirconium";   color[40]=new Color(0xFF94E0E0);radio[40]=1560;
        Z[41]=41;  symbol[41]="Nb"; name[41]="niobium";     color[41]=new Color(0xFF73C2C9);radio[41]=1480;
        Z[42]=42;  symbol[42]="Mo"; name[42]="molybdenum";  color[42]=new Color(0xFF54B5B5);radio[42]=1470;
        Z[43]=43;  symbol[43]="Tc"; name[43]="technetium";  color[43]=new Color(0xFF3B9E9E);radio[43]=1350;
        Z[44]=44;  symbol[44]="Ru"; name[44]="ruthenium";   color[44]=new Color(0xFF248F8F);radio[44]=1400;
        Z[45]=45;  symbol[45]="Rh"; name[45]="rhodium";     color[45]=new Color(0xFF0A7D8C);radio[45]=1450;
        Z[46]=46;  symbol[46]="Pd"; name[46]="palladium";   color[46]=new Color(0xFF006985);radio[46]=1500;
        Z[47]=47;  symbol[47]="Ag"; name[47]="silver";      color[47]=new Color(0xFFC0C0C0);radio[47]=1590;
        Z[48]=48;  symbol[48]="Cd"; name[48]="cadmium";     color[48]=new Color(0xFFFFD98F);radio[48]=1690;
        Z[49]=49;  symbol[49]="In"; name[49]="indium";      color[49]=new Color(0xFFA67573);radio[49]=1630;
        Z[50]=50;  symbol[50]="Sn"; name[50]="tin";         color[50]=new Color(0xFF668080);radio[50]=1460;
        Z[51]=51;  symbol[51]="Sb"; name[51]="antimony";    color[51]=new Color(0xFF9E63B5);radio[51]=1460;
        Z[52]=52;  symbol[52]="Te"; name[52]="tellurium";   color[52]=new Color(0xFFD47A00);radio[52]=1470;
        Z[53]=53;  symbol[53]="I";  name[53]="iodine";      color[53]=new Color(0xFF940094);radio[53]=1400;
        Z[54]=54;  symbol[54]="Xe"; name[54]="xenon";       color[54]=new Color(0xFF429EB0);radio[54]=1980;
        Z[55]=55;  symbol[55]="Cs"; name[55]="cesium";      color[55]=new Color(0xFF57178F);radio[55]=1670;
        Z[56]=56;  symbol[56]="Ba"; name[56]="barium";      color[56]=new Color(0xFF00C900);radio[56]=1340;
        Z[57]=57;  symbol[57]="La"; name[57]="lanthanum";   color[57]=new Color(0xFF70D4FF);radio[57]=1870;
        Z[58]=58;  symbol[58]="Ce"; name[58]="cerium";      color[58]=new Color(0xFFFFFFC7);radio[58]=1830;
        Z[59]=59;  symbol[59]="Pr"; name[59]="praseodymium";color[59]=new Color(0xFFD9FFC7);radio[59]=1820;
        Z[60]=60;  symbol[60]="Nd"; name[60]="neodymium";   color[60]=new Color(0xFFC7FFC7);radio[60]=1810;
        Z[61]=61;  symbol[61]="Pm"; name[61]="promethium";  color[61]=new Color(0xFFA3FFC7);radio[61]=1800;
        Z[62]=62;  symbol[62]="Sm"; name[62]="samarium";    color[62]=new Color(0xFF8FFFC7);radio[62]=1800;
        Z[63]=63;  symbol[63]="Eu"; name[63]="europium";    color[63]=new Color(0xFF61FFC7);radio[63]=1990;
        Z[64]=64;  symbol[64]="Gd"; name[64]="gadolinium";  color[64]=new Color(0xFF45FFC7);radio[64]=1790;
        Z[65]=65;  symbol[65]="Tb"; name[65]="terbium";     color[65]=new Color(0xFF30FFC7);radio[65]=1760;
        Z[66]=66;  symbol[66]="Dy"; name[66]="dysprosium";  color[66]=new Color(0xFF1FFFC7);radio[66]=1750;
        Z[67]=67;  symbol[67]="Ho"; name[67]="holmium";     color[67]=new Color(0xFF00FF9C);radio[67]=1740;
        Z[68]=68;  symbol[68]="Er"; name[68]="erbium";      color[68]=new Color(0xFF00E675);radio[68]=1730;
        Z[69]=69;  symbol[69]="Tm"; name[69]="thulium";     color[69]=new Color(0xFF00D452);radio[69]=1720;
        Z[70]=70;  symbol[70]="Yb"; name[70]="ytterbium";   color[70]=new Color(0xFF00BF38);radio[70]=1940;
        Z[71]=71;  symbol[71]="Lu"; name[71]="lutetium";    color[71]=new Color(0xFF00AB24);radio[71]=1720;
        Z[72]=72;  symbol[72]="Hf"; name[72]="hafnium";     color[72]=new Color(0xFF4DC2FF);radio[72]=1570;
        Z[73]=73;  symbol[73]="Ta"; name[73]="tantalum";    color[73]=new Color(0xFF4DA6FF);radio[73]=1430;
        Z[74]=74;  symbol[74]="W";  name[74]="tungsten";    color[74]=new Color(0xFF2194D6);radio[74]=1370;
        Z[75]=75;  symbol[75]="Re"; name[75]="rhenium";     color[75]=new Color(0xFF267DAB);radio[75]=1350;
        Z[76]=76;  symbol[76]="Os"; name[76]="osmium";      color[76]=new Color(0xFF266696);radio[76]=1370;
        Z[77]=77;  symbol[77]="Ir"; name[77]="iridium";     color[77]=new Color(0xFF175487);radio[77]=1320;
        Z[78]=78;  symbol[78]="Pt"; name[78]="platinum";    color[78]=new Color(0xFFD0D0E0);radio[78]=1500;
        Z[79]=79;  symbol[79]="Au"; name[79]="gold";        color[79]=new Color(0xFFFFD123);radio[79]=1500;
        Z[80]=80;  symbol[80]="Hg"; name[80]="mercury";     color[80]=new Color(0xFFB8B8D0);radio[80]=1700;
        Z[81]=81;  symbol[81]="Tl"; name[81]="thallium";    color[81]=new Color(0xFFA6544D);radio[81]=1550;
        Z[82]=82;  symbol[82]="Pb"; name[82]="lead";        color[82]=new Color(0xFF575961);radio[82]=1540;
        Z[83]=83;  symbol[83]="Bi"; name[83]="bismuth";     color[83]=new Color(0xFF9E4FB5);radio[83]=1540;
        Z[84]=84;  symbol[84]="Po"; name[84]="polonium";    color[84]=new Color(0xFFAB5C00);radio[84]=1680;
        Z[85]=85;  symbol[85]="At"; name[85]="astatine";    color[85]=new Color(0xFF754F45);radio[85]=1700;
        Z[86]=86;  symbol[86]="Rn"; name[86]="radon";       color[86]=new Color(0xFF428296);radio[86]=2400;
        Z[87]=87;  symbol[87]="Fr"; name[87]="francium";    color[87]=new Color(0xFF420066);radio[87]=2000;
        Z[88]=88;  symbol[88]="Ra"; name[88]="radium";      color[88]=new Color(0xFF007D00);radio[88]=1900;
        Z[89]=89;  symbol[89]="Ac"; name[89]="actinium";    color[89]=new Color(0xFF70ABFA);radio[89]=1880;
        Z[90]=90;  symbol[90]="Th"; name[90]="thorium";     color[90]=new Color(0xFF00BAFF);radio[90]=1790;
        Z[91]=91;  symbol[91]="Pa"; name[91]="protactinium";color[91]=new Color(0xFF00A1FF);radio[91]=1610;
        Z[92]=92;  symbol[92]="U";  name[92]="uranium";     color[92]=new Color(0xFF008FFF);radio[92]=1580;
        Z[93]=93;  symbol[93]="Np"; name[93]="neptunium";   color[93]=new Color(0xFF0080FF);radio[93]=1550;
        Z[94]=94;  symbol[94]="Pu"; name[94]="plutonium";   color[94]=new Color(0xFF006BFF);radio[94]=1530;
        Z[95]=95;  symbol[95]="Am"; name[95]="americium";   color[95]=new Color(0xFF545CF2);radio[95]=1510;
        Z[96]=96;  symbol[96]="Cm"; name[96]="curium";      color[96]=new Color(0xFF785CE3);radio[96]=1500;
        Z[97]=97;  symbol[97]="Bk"; name[97]="berkelium";   color[97]=new Color(0xFF8A4FE3);radio[97]=1500;
        Z[98]=98;  symbol[98]="Cf"; name[98]="californium"; color[98]=new Color(0xFFA136D4);radio[98]=1500;
        Z[99]=99;  symbol[99]="Es"; name[99]="einsteinium"; color[99]=new Color(0xFFB31FD4);radio[99]=1500;
        Z[100]=100;symbol[100]="Fm";name[100]="fermium";    color[100]=new Color(0xFFB31FBA);radio[100]=1500;
        Z[101]=101;symbol[101]="Md";name[101]="mendelevium";color[101]=new Color(0xFFB30DA6);radio[101]=1500;
        Z[102]=102;symbol[102]="No";name[102]="nobelium";   color[102]=new Color(0xFFBD0D87);radio[102]=1500;
        Z[103]=103;symbol[103]="Lr";name[103]="lawrencium"; color[103]=new Color(0xFFC70066);radio[103]=1500;
        Z[104]=104;symbol[104]="Rf";name[104]="rutherfordium";color[104]=new Color(0xFFCC0059);radio[104]=1600;
        Z[105]=105;symbol[105]="Db";name[105]="dubnium";     color[105]=new Color(0xFFD1004F);radio[105]=1600;
        Z[106]=106;symbol[106]="Sg";name[106]="seaborgium";  color[106]=new Color(0xFFD90045);radio[106]=1600;
        Z[107]=107;symbol[107]="Bh";name[107]="bohrium";     color[107]=new Color(0xFFE00038);radio[107]=1600;
        Z[108]=108;symbol[108]="Hs";name[108]="hassium";     color[108]=new Color(0xFFE6002E);radio[108]=1600;
        Z[109]=109;symbol[109]="Mt";name[109]="meitnerium";  color[109]=new Color(0xFFEB0026);radio[109]=1600;
    }
    public String getSymbol(int z) {return (z<1||z>z_max)? "?" : symbol[z];}
    public int getZ(String sy) {
        int aux=0;
        for(int i=1;i<=z_max;i++) if(sy.trim().equals(symbol[i].trim())) aux=i;
        return aux;
    }
    public Color getColor(int z)  {return (z<1||z>z_max)? Color.RED : color[z];}
    public double getRadio_100(int z)  {return (z<1||z>z_max)? 1000000 : radio[z];}
    
    public int min(int z){
        int m=0;
        if(z<=2) m=0; //s2
        if(z>2&&z<=4)m=2; //s2 s2
        if(z>4&&z<=10)m=2; //s2 s2p6
        if(z>10&&z<=12)m=10; //Ne
        if(z>12&&z<=18)m=10;
        if(z>18&&z<=20)m=18;
        if(z>20&&z<=30)m=18;
        if(z>30&&z<=36)m=18;
        if(z>36&&z<=38)m=36;
        if(z>38&&z<=48)m=36;
        if(z>48&&z<=54)m=36;
        if(z>54&&z<=56)m=54;
        if(z>56&&z<=70)m=54;
        if(z>70&&z<=80)m=54;
        if(z>80&&z<=86)m=54;
        if(z>86&&z<=88)m=86;
        if(z>88&&z<=102)m=86;
        if(z>102&&z<=112)m=86;
        if(z>112&&z<=118)m=86;
        return m;
    }
    
    public int max(int z){
        int m=0;
        if(z<=2) m=2; //s2
        if(z>2&&z<=4)m=10; //s2 s2
        if(z>4&&z<=10)m=10; //s2 s2p6
        if(z>10&&z<=12)m=18; //Ne
        if(z>12&&z<=18)m=18;
        if(z>18&&z<=20)m=36;
        if(z>20&&z<=30)m=36;
        if(z>30&&z<=36)m=36;
        if(z>36&&z<=38)m=54;
        if(z>38&&z<=48)m=54;
        if(z>48&&z<=54)m=54;
        if(z>54&&z<=56)m=86;
        if(z>56&&z<=70)m=86;
        if(z>70&&z<=80)m=86;
        if(z>80&&z<=86)m=86;
        if(z>86&&z<=88)m=118;
        if(z>88&&z<=102)m=118;
        if(z>102&&z<=112)m=118;
        if(z>112&&z<=118)m=118;
        return m;
    }
    
    public int electrons(int z) {
        return z-min(z);
    }
    public int hole(int z){
        return max(z)-z;
    }
}
