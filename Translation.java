//         Translation.java
// Translation class for decaf, a subset of Java. 
// Output atoms from syntax tree
//   sdb  March 2003
//   sdb  updated May 2007
//  	  to use generic maps instead of hashtables.

//package decaf;
import decaf.analysis.*;
import decaf.node.*;
import java.util.*;
import java.io.*;

class Translation extends DepthFirstAdapter 
{ 

// All stored values are doubles, key=node, value is memory loc or label number
Map  <Node, Integer> hash = new HashMap <Node, Integer> ();	// May 2007

Integer zero = new Integer (0);
Integer one  = new Integer (1);

AtomFile out;

//////////////////////////////////////////////////
// Definition of Program

public void inAProgram (AProgram prog)
//   The class name and main args need to be entered into symbol table
//     to avoid error message.
//   Also, open the atom file for output
{  identifiers.put (prog.getIdentifier().toString(), alloc());	// class name
   identifiers.put (prog.getArg().toString(), alloc());		// main (args)
   out = new AtomFile ("atoms");
}

public void outAProgram (AProgram prog)
//  Write the run-time memory values to a file "constants".
//  Close the binary file of atoms so it can be used for 
//    input by the code generator
{  outConstants();
   out.close(); 
}

//////////////////////////////////////////////////
// Definitions of declaration and identlist

public void inADeclaration (ADeclaration node)
{   install (node.getIdentifier()); }

public void outAIdentlist (AIdentlist node)
{   install (node.getIdentifier()); }

void install (TIdentifier id)
//  Install id into the symbol table
{  Integer loc;
   loc = identifiers.get (id.toString());
   if (loc==null)
	identifiers.put (id.toString(), alloc());
   else 
	System.err.println ("Error: " + id + " has already been declared ");
}


//////////////////////////////////////////////////
// Definition of for_stmt

public void caseAForStmt (AForStmt stmt)
{  Integer lbl1, lbl2, lbl3;
   lbl1 = lalloc();
   lbl2 = lalloc();
   lbl3 = lalloc();
   inAForStmt (stmt);
   if (stmt.getFor() !=null)	stmt.getFor().apply(this);
   if (stmt.getLPar() !=null)	stmt.getLPar().apply(this);
   if (stmt.getAssignExpr()!=null)			// initialize
      	{	stmt.getAssignExpr().apply(this);
		atom ("LBL", lbl1);
	}
   if (stmt.getSemi() != null)	stmt.getSemi().apply(this);
   if (stmt.getBoolExpr() != null)			// test for termination
	{	stmt.getBoolExpr().apply(this);
		atom ("JMP", lbl2);
		atom ("LBL", lbl3);
	}
   if (stmt.getS2() != null)	stmt.getS2().apply(this);
   if (stmt.getA2() != null)
	{	stmt.getA2().apply(this);		// increment
		atom ("JMP", lbl1);
		atom ("LBL", lbl2);
	}
   if (stmt.getRPar() != null)	stmt.getRPar().apply(this);
   if (stmt.getStmt() != null)	
	{	stmt.getStmt().apply(this);
		atom ("JMP", lbl3);
		atom ("LBL", (Integer) hash.get (stmt.getBoolExpr()));
	}
   outAForStmt(stmt);
}
		
public void caseAForStmtNoShortIf (AForStmtNoShortIf stmt)
{  Integer lbl1, lbl2, lbl3;
   lbl1 = lalloc();
   lbl2 = lalloc();
   lbl3 = lalloc();
   inAForStmtNoShortIf (stmt);
   if (stmt.getFor() !=null)	stmt.getFor().apply(this);
   if (stmt.getLPar() !=null)	stmt.getLPar().apply(this);
   if (stmt.getAssignExpr()!=null)			// initialize
      	{	stmt.getAssignExpr().apply(this);
		atom ("LBL", lbl1);
	}
   if (stmt.getSemi() != null)	stmt.getSemi().apply(this);
   if (stmt.getBoolExpr() != null)			// test for termination
	{	stmt.getBoolExpr().apply(this);
		atom ("JMP", lbl2);
		atom ("LBL", lbl3);
	}
   if (stmt.getS2() != null)	stmt.getS2().apply(this);
   if (stmt.getA2() != null)
	{	stmt.getA2().apply(this);		// increment
		atom ("JMP", lbl1);
		atom ("LBL", lbl2);
	}
   if (stmt.getRPar() != null)	stmt.getRPar().apply(this);
   if (stmt.getStmtNoShortIf() != null)	
	{	stmt.getStmtNoShortIf().apply(this);
		atom ("JMP", lbl3);
		atom ("LBL", (Integer) hash.get (stmt.getBoolExpr()));
	}
   outAForStmtNoShortIf (stmt);
}
//////////////////////////////////////////////////
// Definition of until_stmt
public void inAUntilStmt (AUntilStmt stmt)
{ Integer lbl = lalloc();
   hash.put (stmt, lbl);
   atom ("LBL", lbl);
}

public void outAUntilStmt (AUntilStmt stmt)
{    atom ("JMP", (Integer) hash.get(stmt));
   atom ("LBL", (Integer) hash.get (stmt.getBoolExpr()));
}
//////////////////////////////////////////////////
// Definition of do_while_stmt
public void inADoWhileStmt (ADoWhileStmt stmt)
{ Integer lbl = lalloc();
   hash.put (stmt, lbl);
   atom ("LBL", lbl);
}

public void outADoWhileStmt (ADoWhileStmt stmt)
{    atom ("JMP", (Integer) hash.get(stmt));
   atom ("LBL", (Integer) hash.get (stmt.getBoolExpr()));
}
//////////////////////////////////////////////////
// Definition of while_stmt

public void inAWhileStmt (AWhileStmt stmt)
{  Integer lbl = lalloc();
   hash.put (stmt, lbl);
   atom ("LBL", lbl); 
}

public void outAWhileStmt (AWhileStmt stmt)
{  atom ("JMP", (Integer) hash.get(stmt));
   atom ("LBL", (Integer) hash.get (stmt.getBoolExpr()));
}

public void inAWhileStmtNoShortIf (AWhileStmtNoShortIf stmt)
{  Integer lbl = lalloc();
   hash.put (stmt, lbl);
   atom ("LBL", lbl); 
}

public void outAWhileStmtNoShortIf (AWhileStmtNoShortIf stmt)
{  atom ("JMP", (Integer) hash.get(stmt));
   atom ("LBL", (Integer) hash.get (stmt.getBoolExpr()));
}


/////////////////////////////////////////////
// Definition of if_stmt

public void outAIfStmt (AIfStmt stmt)
{  atom ("LBL", (Integer) hash.get (stmt.getBoolExpr())); }	// Target for bool_expr's TST

// override the case of if_else_stmt
public void caseAIfElseStmt (AIfElseStmt node)
{  Integer lbl = lalloc();
   inAIfElseStmt (node);
   if (node.getIf() != null) node.getIf().apply(this);
   if (node.getLPar() != null) node.getLPar().apply(this);
   if (node.getBoolExpr() != null)node.getBoolExpr().apply(this);
   if (node.getRPar() != null) node.getRPar().apply(this);
   if (node.getStmtNoShortIf() != null)
     {	 node.getStmtNoShortIf().apply(this);
   	atom ("JMP", lbl);					// Jump over else part
   	atom ("LBL", (Integer) hash.get (node.getBoolExpr()));
     }
   if (node.getElse() != null) node.getElse().apply(this);
   if  (node.getStmt() != null) node.getStmt().apply(this);
   atom ("LBL", lbl);
   outAIfElseStmt (node);
}

// override the case of if_else_stmt_no_short_if
public void caseAIfElseStmtNoShortIf (AIfElseStmtNoShortIf node)
{  Integer lbl = lalloc();
   inAIfElseStmtNoShortIf (node);
   if (node.getIf() != null) node.getIf().apply(this);
   if (node.getLPar() != null) node.getLPar().apply(this);
   if (node.getBoolExpr() != null)node.getBoolExpr().apply(this);
   if (node.getRPar() != null) node.getRPar().apply(this);
   if (node.getIf1() != null) 
     {	node.getIf1().apply(this);
   	atom ("JMP", lbl);					// Jump over else part
   	atom ("LBL", (Integer) hash.get (node.getBoolExpr()));
     }
   if (node.getElse() != null) node.getElse().apply(this);
   if  (node.getIf2() != null) node.getIf2().apply(this);
   atom ("LBL", lbl);
   outAIfElseStmtNoShortIf (node);
}

///////////////////////////////////////////////////
// Definition of bool_expr

public void outABoolExpr (ABoolExpr node)
{  Integer lbl = lalloc();
   hash.put (node, lbl);
   atom ("TST", (Integer) hash.get(node.getExpr()), 
		(Integer) hash.get(node.getRight()),
		zero, 
		new Integer (7  - getComparisonCode (node.getCompare().toString())),
				// Negation of a comparison code is 7 - code.
		lbl); 
}


////////////////////////////////////////////////
// Definition of expr

public void outAAssnExpr (AAssnExpr node)
// out of alternative {assn} in expr
{ hash.put (node, hash.get (node.getAssignExpr())); }  

public void outARvalExpr (ARvalExpr node)
// out of alternative {rval} in expr
{  hash.put (node, hash.get (node.getRvalue())); }

public void outAMultassnExpr (AMultassnExpr node)
// out of alternative {assn} in expr
{ hash.put (node, hash.get (node.getMultassignExpr())); }  
public void outAPlusassnExpr (APlusassnExpr node)
// out of alternative {assn} in expr
{ hash.put (node, hash.get (node.getPlusassignExpr())); }  

int getComparisonCode (String cmp)
//  Return the integer comparison code for a comparison
{   if (cmp.indexOf ("==")>=0) return  1;
    if (cmp.indexOf ("<")>=0)  return  2;
    if (cmp.indexOf (">")>=0)  return  3;
    if (cmp.indexOf ("<=")>=0) return  4;
    if (cmp.indexOf (">=")>=0) return  5;
    if (cmp.indexOf ("!=")>=0) return  6;
  return  0;			// this should never occur
}

////////////////////////////////////////////////
// Definition of assign_expr

public void outAAssignExpr (AAssignExpr node)
//  Put out the MOV atom
{  Integer assignTo = getIdent (node.getIdentifier());
   atom ("MOV", (Integer) hash.get (node.getExpr()), 
	 zero, 
	 assignTo);
   hash.put (node, assignTo);
} 
////////////////////////////////////////////////
// Definition of multassign_expr

public void outAMultassignExpr (AMultassignExpr node)
//  Put out the MOV atom
{  

 Integer i = alloc();
 Integer assignTo = getIdent (node.getIdentifier());
    hash.put (node, i);
    atom ("MUL",assignTo , (Integer) hash.get(node.getExpr()) , i);

   atom ("MOV", i,  zero,  assignTo);
   hash.put (node, assignTo);
} 
////////////////////////////////////////////////
// Definition of plusassign_expr

public void outAPlusassignExpr (APlusassignExpr node)
//  Put out the MOV atom
{  

 Integer i = alloc();
 Integer assignTo = getIdent (node.getIdentifier());
    hash.put (node, i);
    atom ("ADD",assignTo , (Integer) hash.get(node.getExpr()) , i);

   atom ("MOV", i,  zero,  assignTo);
   hash.put (node, assignTo);
} 

////////////////////////////////////////////////
// Definition of rvalue

public void outAPlusRvalue (APlusRvalue node) 
{// out of alternative {plus} in Rvalue, generate an atom ADD. 
    Integer i = alloc();
    hash.put (node, i);
    atom ("ADD", (Integer)hash.get(node.getRvalue()), 
	         (Integer) hash.get(node.getTerm()) , i);
}

public void outAMinusRvalue(AMinusRvalue node) 
{// out of alternative {minus} in Rvalue, generate an atom SUB. 
    Integer i = alloc();
    hash.put (node, i);
    atom ("SUB", (Integer) hash.get(node.getRvalue()), 
		 (Integer) hash.get(node.getTerm()), i);
}

public void outATermRvalue (ATermRvalue node)
//  Attribute of the rvalue is the same as the term.
{   hash.put (node, hash.get (node.getTerm())); }


////////////////////////////////////////////////
// Definition of term

public void outAMultTerm (AMultTerm node)
{// out of alternative {mult} in Term, generate an atom MUL. 
    Integer i = alloc();
    hash.put (node, i);
    atom ("MUL", (Integer)hash.get(node.getTerm()), 
	         (Integer) hash.get(node.getFactor()) , i);
}

public void outADivTerm(ADivTerm node) 
{// out of alternative {div} in Term, generate an atom DIV. 
    Integer i = alloc();
    hash.put (node, i);
    atom ("DIV", (Integer) hash.get(node.getTerm()), 
		 (Integer) hash.get(node.getFactor()), i);
}

public void outAFacTerm (AFacTerm node)
{ //  Attribute of the term is the same as the factor
	hash.put (node, hash.get(node.getFactor()));
 }


//  May 2007
Map <Double, Integer> nums = new HashMap <Double, Integer> ();
Map <String, Integer > identifiers = new HashMap <String, Integer> ();

final int MAX_MEMORY = 1024;
Double memory [] = new Double [MAX_MEMORY];
int memHigh = 0;
// No, only memory needs to remain for codegen.


// Maintain a hash table of numeric constants, to avoid storing
//   the same number twice.
// Move the number to a run-time memory location.
// That memory location will be the attribute of the Number token.
public void caseTNumber(TNumber num) 
{ Integer loc;
  Double dnum;
  dnum = new Double (num.toString());		// The number as a Double
  loc = (Integer) nums.get (dnum);		// Get its memory location
  if (loc==null)				// Already in table?
     {	loc = alloc();				// No, install in table of nums
	nums.put (dnum, loc);
	memory[loc.intValue()] = dnum;		// Store value in run-time memory
  	if (loc.intValue() > memHigh)		// Retain highest memory loc
	   memHigh = loc.intValue();
     }
  hash.put (num, loc);				// Set attribute to move up tree
}
  


Integer getIdent(TIdentifier id)
// Get the run-time memory location to which this id is bound
{  Integer loc;
   loc = identifiers.get (id.toString());
   if (loc==null)
	System.err.println ("Error: " + id + " has not been declared");
   return loc;
}

////////////////////////////////////////////////
// Definition of factor

public void outAParsFactor (AParsFactor node)
{   hash.put (node, hash.get (node.getExpr())); }

// Unary + doesn't need any atoms to be put out.
public void outAUplusFactor (AUplusFactor node)
{   hash.put (node, hash.get (node.getFactor())); }

// Unary - needs a negation atom (NEG).
public void outAUminusFactor (AUminusFactor node)
{   Integer loc = alloc();	// result of negation
    atom ("NEG", (Integer)hash.get(node.getFactor()), zero, loc);
    hash.put (node, loc);	
}

public void outAIdFactor (AIdFactor node)
{   hash.put (node, getIdent (node.getIdentifier())); }

public void outANumFactor (ANumFactor node)
{   hash.put (node, hash.get (node.getNumber())); }


///////////////////////////////////////////////////////////////////
//  Send the run-time memory constants to a file for use by the code generator.

void outConstants()
{  FileOutputStream fos = null;
   DataOutputStream ds = null;
   int i;
   
   try
    {	fos = new FileOutputStream ("constants");
	ds = new DataOutputStream (fos);   
    }
   catch (IOException ioe)
    {	System.err.println ("IO error opening constants file for output: "
		+ ioe);
    }

   try
    { 	for (i=0; i<=memHigh ; i++)
	    if (memory[i]==null) ds.writeDouble (0.0);		// a variable is bound here
	    else
	    	ds.writeDouble (memory[i].doubleValue());
    }
   catch (IOException ioe)
    {	System.err.println ("IO error writing to constants file: "
		+ ioe);
    }
   try { fos.close(); }
   catch (IOException ioe)
    {	System.err.println ("IO error closing constants file: "
		+ ioe);
    }
}




//////////////////////////////////////////////////////////
// Put out atoms for conversion to machine code.
// These methods display to stdout, and also write to a 
//   binary file of atoms suitable as input to the code generator.

void atom (String atomClass, Integer left, Integer right, Integer result)
{   System.out.println (atomClass +  " T" + left + " T"  + right +  " T" + 
	result);
    Atom atom = new Atom (atomClass, left, right, result);
    atom.write(out);
}

void atom (String atomClass, Integer left, Integer right, Integer result,
	Integer cmp, Integer lbl)
{   System.out.println (atomClass +  " T" + left + " T"  + right +  " T" + 
	result + " C" + cmp + " L" + lbl);
    Atom atom = new Atom (atomClass, left, right, result, cmp, lbl);
    atom.write(out);
}

void atom (String atomClass, Integer lbl)
{   System.out.println (atomClass +  " L" + lbl); 
    Atom atom = new Atom (atomClass, lbl);
    atom.write(out);
}

static int avail = 0;
static int lavail = 0;

Integer alloc()
{ return new Integer (++avail); }

Integer lalloc()
{ return new Integer (++lavail); }


}
