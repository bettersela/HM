//       Compiler.java
//  main method which invokes the parser and reads from stdin
//  March 2003   sdb

//package decaf;
import decaf.parser.*;
import decaf.lexer.*;
import decaf.node.*;
import java.io.*;

public class Compiler 
{ 

public static void main(String[] arguments) 
{ try 
  { System.out.println();

    // Create a Parser instance. 
    Parser p = new Parser
	( new Lexer
	  ( new PushbackReader
	    ( new InputStreamReader(System.in), 1024)));

    // Parse the input. 
    Start tree = p.parse();

    // Apply the translation. 
    tree.apply(new Translation()); 

    System.out.println();
  } 
  catch(Exception e) 
    { System.out.println(e.getMessage()); } 
} 

}

