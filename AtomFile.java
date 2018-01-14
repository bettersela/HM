//         AtomFile.java
//  Create the binary output file for atoms
//  March 2003   sdb

//package decaf;
import java.io.*;

class AtomFile
{
   FileOutputStream fos;
   DataOutputStream ds;
   String fileName;


AtomFile (String name)
{  fileName = new String (name);
   try 
     {	fos = new FileOutputStream (fileName);
	ds = new DataOutputStream (fos);
     }
   catch (IOException ioe)
     {
	System.err.println ("IO error opening atom file (" +
		fileName + "): " + ioe);
     }
}
   
void close()
{   try
     { ds.close(); }
    catch (IOException ioe)
     {	System.err.println ("IO error closing atom file (" +
		fileName + "): " + ioe);
     }


}
}
