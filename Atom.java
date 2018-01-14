//      Atom.java
// Define an atom, which is to be put out by the Translator
// March 2003   sdb

//package decaf;
import java.io.*;

class Atom
// Put out atoms to a binary file in the fmt expected by the old code generator
{  

static final int ADD = 1;
static final int SUB = 2;
static final int MUL = 3;
static final int DIV = 4;
static final int JMP = 5;
static final int NEG = 10;
static final int LBL = 11;
static final int TST = 12;
static final int MOV = 13;

int cls;
int left;
int right;
int result;
int cmp;
int lbl;

Atom (String cls, Integer l, Integer r, Integer res)
{   setClass (cls);
    left = l.intValue();
    right = r.intValue();
    result = res.intValue();
    cmp = 0;
    lbl = 0;
}

Atom (String cls, Integer l, Integer r, Integer res, Integer c, Integer lb)
{   setClass (cls);
    left = l.intValue();
    right = r.intValue();
    result = res.intValue();
    cmp = c.intValue();
    lbl = lb.intValue();
}

Atom (String cls,  Integer lb)
{   setClass (cls);
    left = 0;
    right = 0;
    result = 0;
    cmp = 0;
    lbl = lb.intValue();
}

void setClass (String c)
//  set the atom class as an int code
{   if (c.equals("ADD")) cls = ADD;
    else if (c.equals("SUB")) cls = SUB;
    else if (c.equals("MUL")) cls = MUL;
    else if (c.equals("DIV")) cls = DIV;
    else if (c.equals("JMP")) cls = JMP;
    else if (c.equals("NEG")) cls = NEG;
    else if (c.equals("LBL")) cls = LBL;
    else if (c.equals("TST")) cls = TST;
    else if (c.equals("MOV")) cls = MOV;
}

void write (AtomFile out)
 // write a single atom out to the binary file
{
    try 
     {	out.ds.writeInt (cls);
	out.ds.writeInt (left);
	out.ds.writeInt (right);
	out.ds.writeInt (result);
	out.ds.writeInt (cmp);
	out.ds.writeInt (lbl);
     }
    catch (IOException ioe)
     {
    	System.out.println ("IO Error writing to atom file, atom class is "
		+ cls + ", error is " + ioe);
     }
}


}
