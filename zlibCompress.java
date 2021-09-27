//import java.io.UnsupportedEncodingException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class zlibCompress{
	
		public static void main(String[] args) throws DataFormatException {
			    String message = "1000-battu-manager-12987.34";
			    System.out.println("Original Message length : " + message.length());
			    byte[] input;
				try {
					input = message.getBytes("UTF-8");
					
					// Compress the bytes
				      byte[] output = new byte[1024];
				      Deflater deflater = new Deflater();
				      deflater.setInput(input);
				      deflater.finish();
				      int compressedDataLength = deflater.deflate(output);
				      deflater.end();
				      System.out.println("Compressed str is "+output);
				      System.out.println("Compressed Message length : " + compressedDataLength);

				      // Decompress the bytes
				      Inflater inflater = new Inflater();
				      inflater.setInput(output, 0, compressedDataLength);
				      byte[] result = new byte[1024];
				      int resultLength = inflater.inflate(result);
				      inflater.end();

				      // Decode the bytes into a String
				      message = new String(result, 0, resultLength, "UTF-8");
				      System.out.println("original str is "+ message);
				      System.out.println("UnCompressed Message length : " + message.length());
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	}























/*
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;


//Java Program to demonstrate
//Object Compression

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPOutputStream;
import java.io.File;

public class zlibCompress {
	public static void main(String args[]){

		// Creating objects of Java Class Bill
		Bill b1 = new Bill("176BU", "Abhishek Gupta");
		Bill b2= new Bill("176DA", "Sushant Singh");
		FileOutputStream f = null;

		// Creates a GZIPOutputStream and initialize it with null
		GZIPOutputStream g = null;

		// Creates an ObjectOutputStream and initialise it with null ObjectOutputStream writes to the
		// defined GZIPOutputStream.
		ObjectOutputStream o = null;

		// Write path of the file in the argument
		File newFile = new File("File.dat");
		try {
			// Pass the File object (newFile) to the FileOutputStream
			f = new FileOutputStream(newFile);
			g = new GZIPOutputStream(f);

			// Now pass the GZIPOutputStream object to the ObjectOutputStream
			o = new ObjectOutputStream(g);

			// Writes the object that are going to be compressed to the ObjectOutputStream using
			// writeObject(objectName)
			o.writeObject(b1);
			o.writeObject(b2);

			// flush() API methods of ObjectOutputStream.
			o.flush();
			System.out.println("Process done..");
			System.out.println("Objects are compressed");
		}
		catch (FileNotFoundException e) {
			//System.out.println(e);
		}
		catch (IOException e) {
			System.out.println(e);
		}
		finally {
			try {
				// Using their close() API methods, closes both the GZIPOutputStream
				// and the ObjectOutputStream
				if (o != null)
					o.close();
				if (g != null)
					g.close();
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}
}

class Bill implements Serializable {
	// Declaring the private variables
	private String billno;
	private String buyerName;

	public Bill(String bill, String buyer){
		this.billno = bill;
		this.buyerName = buyer;
	}
	public String getBill(){
		return billno;
	}
	public void setBill(String billno){
		this.billno = billno;
	}
	public String getBuyerName(){
		return buyerName;
	}
	public void setBuyerName(String buyer){
		this.buyerName = buyer;
	}
}

*/



/*
//import before
//import compressed
//import after       
public class zlibCompress{
	public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream("before.txt");
  
        FileOutputStream fos=new FileOutputStream("compressed.txt");
  
        DeflaterOutputStream dos=new DeflaterOutputStream(fos);
  
        //read data from FileInputStream and write it into DeflaterOutputStream
        int data;
        while ((data=fis.read())!=-1){
            dos.write(data);
        }
        //close the file
        fis.close();
        dos.close();
                
        new decompress();
	}
}
/*

// decompress the output file to newinputfile
                fis = new FileInputStream("compressed.txt");
                fos = new FileOutputStream("after.txt");
                //assign inflaterInputStream to FileInputStream for uncompressing the data
                InflaterInputStream iis = new InflaterInputStream(fis);
                int cdata;
                while((cdata=iis.read())!=-1){
                    fos.write(cdata);
                }
                //close the file
                iis.close();
                fis.close();
		dos.close();
*/