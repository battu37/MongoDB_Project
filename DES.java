import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
 
//import com.sun.mail.util.BASE64DecoderStream;
//import com.sun.mail.util.BASE64EncoderStream;
 
public class DES {
    private static Cipher ecipher;
    private static Cipher dcipher;
    private static SecretKey key;
    
    /*
    public static String encrypt(String str) {
	  	  try {
	  	    // encode the string into a sequence of bytes using the named charset
	  	    // storing the result into a new byte array. 
	  	    byte[] utf8 = str.getBytes("UTF8");
	  		byte[] enc = ecipher.doFinal(utf8);
	  		 
	  		// encode to base64
	  		enc = BASE64EncoderStream.encode(enc);
	  		return new String(enc);
	  	  }
	  	  catch (Exception e) {
	  	    e.printStackTrace();
	  	  }
	  	  return null;
      }
   
      public static String decrypt(String str) {
	  	  try {
	  	    // decode with base64 to get bytes
	  		byte[] dec = BASE64DecoderStream.decode(str.getBytes());
	  		byte[] utf8 = dcipher.doFinal(dec);
	  		// create new string based on the specified charset
	  			return new String(utf8, "UTF8");
	  		  }
	  	  catch (Exception e) {
	  		    e.printStackTrace();
	  		  }
	  	  return null;
      }
    */
    // DES Encryption function
    public static String encrypt(String str) {
	    try {
	    	// encode the string into a sequence of bytes using the named charset
	    	// storing the result into a new byte array.
		   	byte[] utf8 = str.getBytes("UTF8");
		    byte[] enc = ecipher.doFinal(utf8);
		    	// encode to base64
		    String encString = new String(Base64.getEncoder().encodeToString(enc));
		    return new String(encString);
	   	}
	    catch (Exception e) {
	    		e.printStackTrace();
	    }
	   	return null;
	}
    //DES Decryption function
    public static String decrypt(String str) {
    	try {
	    	// decode with base64 to get bytes
	    	byte[] dec = Base64.getDecoder().decode(str.getBytes());
	    	byte[] utf8 = dcipher.doFinal(dec);
	    	// create new string based on the specified charset
	    	return new String(utf8, "UTF-8");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static void insertAllDocuments(DBCollection collection, List<Employee> alldata) {
		// TODO Auto-generated method stub
		List<DBObject> list = new ArrayList<DBObject>();
	    // to apply AES we need string format of whole data
	    //String output = alldata.toString();
	    
	    for(Employee emp : alldata) {
	    	BasicDBObject data = new BasicDBObject();
	    	data.append("EmpId", emp.getEmployeeId());
	        data.append("Name",emp.getEmployeeName());
	        data.append("Designation", emp.getEmployeeDesignation());
	        data.append("Salary", emp.getSalary());
	        
	    	list.add(data);
	    }
	    collection.insert(list);
	    //return output;
	}
 // inserting encrypted Employee details to EmployeeEnc collection
 	public static void insertEmployeeEnc(DBCollection collection, String str, int idval) {
 		BasicDBObject data = new BasicDBObject();
         data.append("cipher", str);
         data.append("EmpId", idval);
         collection.insert(data);
 	}
 // inserting encrypted and compressed data to EmployeeCompEnc Collection
 	public static void insertEmployeeCompressEnc(DBCollection collection,String str) {
 		BasicDBObject data = new BasicDBObject();
         data.append("compcipher", str);
         //data.append("EmpId", idval);
         collection.insert(data);
 	}
 	//Compression function using GZIP
 		public static byte[] compressfn(String data) throws IOException {
 			ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
 			GZIPOutputStream gzip = new GZIPOutputStream(bos);
 			gzip.write(data.getBytes());
 			gzip.close();
 			byte[] compressed = bos.toByteArray();
 			bos.close();
 			return compressed;
 		}
 		//Decompress function using GZIP
 		public static String decompressfn(byte[] compressed) throws IOException {
 			ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
 			GZIPInputStream gis = new GZIPInputStream(bis);
 			BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
 			StringBuilder sb = new StringBuilder();
 			String line;
 			while((line = br.readLine()) != null) {
 				sb.append(line);
 			}
 			br.close();
 			gis.close();
 			bis.close();
 			return sb.toString();
 		}
 	
    
    public static void main(String[] args) {
        try {
            // generate secret key using DES algorithm
            key = KeyGenerator.getInstance("DES").generateKey();
 
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key
            ecipher.init(Cipher.ENCRYPT_MODE, key); 
            dcipher.init(Cipher.DECRYPT_MODE, key);
 
            // Data
            Employee e1 = new Employee(1000,"boni","manager",120000);
    		Employee e2 = new Employee(1001,"krishna","ass manager",120000);
    		Employee e3 = new Employee(1002,"battu","manager",23456);
    		Employee e4 = new Employee(1003,"surya","sol architect",120000);
    		Employee e5 = new Employee(1004,"sai","senior manager",120000);
    		Employee e6 = new Employee(1005,"tarun","manager",1230000);
    		Employee e7 = new Employee(1006,"sadik","senior manager",99000);
    		Employee e8 = new Employee(1007,"charan","senior manager",99999);
    		Employee e9 = new Employee(1008,"kenny","sol architect",99000);
    		Employee e10 = new Employee(1009,"satish","senior manager",99000);
    		Employee e11 = new Employee(1010,"sarath","senior manager",99000);
    		Employee e12 = new Employee(1011,"dhruvi","senior manager",99000);
    		Employee e13 = new Employee(1012,"professor","senior manager",99000);
    		Employee e14 = new Employee(1013,"anil verma","senior manager",99000);
    		Employee e15 = new Employee(1014,"yashika","senior manager",99000);
    		Employee e16 = new Employee(1015,"ankit","senior manager",99890);
    		Employee e17 = new Employee(1016,"yash madwanna","manager",99000);
    		Employee e18 = new Employee(1017,"zala dhruvi","sol architect",997870);
    		Employee e19 = new Employee(1018,"sahil","senior engineer",99000);
    		Employee e20 = new Employee(1019,"samyak","R&D engineer",99000);
    		Employee e21 = new Employee(1020,"vamsi","senior sol architect",99000);
    		Employee e22 = new Employee(1021,"mano","senior manager",99000);
    		Employee e23 = new Employee(1022,"raghu","senior engineer",99000);
    		Employee e24 = new Employee(1023,"raghu ch","senior analyst",99000);
    		Employee e25 = new Employee(1024,"ramya","senior sol architect",99000);
    		
    		List<Employee> alldata = new ArrayList<Employee>();
    		alldata.add(e1);alldata.add(e2);alldata.add(e3);alldata.add(e4);alldata.add(e5);alldata.add(e6);
    		alldata.add(e7);alldata.add(e8);alldata.add(e9);alldata.add(e10);alldata.add(e11);alldata.add(e12);
    		alldata.add(e13);alldata.add(e14);alldata.add(e15);alldata.add(e16);alldata.add(e17);alldata.add(e18);
    		alldata.add(e19);alldata.add(e20);alldata.add(e21);alldata.add(e22);alldata.add(e23);alldata.add(e24);
    		alldata.add(e25);
            
    		// Adding this ArrayList to Mongodb database
    		MongoClient mongo = new MongoClient("localhost", 27017); 
    		//Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("mongoDES");
            DBCollection collectionEmp = db.getCollection("EmployeeDES");
            insertAllDocuments(collectionEmp,alldata);
    		
// Applying DES Encryption Algorithm on every single Employee object and store it in EmployeeEnc collection
            long startTimeenc = System.currentTimeMillis();
            DBCollection collectionEmpenc = db.getCollection("EncEmployeeDES");
            for(Employee emp:alldata) {
            	String encobj = emp.toString().replace(" ","");
            	//System.out.println(encobj);
            	String encstr = encrypt(encobj);
            	//System.out.println(encstr);
                insertEmployeeEnc(collectionEmpenc,encstr,emp.getEmployeeId());
            }
            long endTimeenc = System.currentTimeMillis();
            long timeElapsedenc  = endTimeenc - startTimeenc;
            System.out.println("********DES Encryption time "+ timeElapsedenc + " msec**************");
            
// Applying DES Decryption Algorithm on every single Employeeenc document and returned 
            long startTimedec = System.currentTimeMillis();
            List<Employee> decrypteddata = new ArrayList<Employee>();
    	    BasicDBObject allQuery = new BasicDBObject();
    	    BasicDBObject fields = new BasicDBObject();
    	    fields.put("cipher",1);
    	    fields.put("_id", 0);
    	    //fields.put("EmpId", 0);

    	    DBCursor cursor2 = collectionEmpenc.find(allQuery, fields);
    	    System.out.println("************Decryption of all data**********");
    	    while (cursor2.hasNext()) {
    	      //System.out.println(cursor2.next());
    	      String strenc = cursor2.next().get("cipher").toString();
    	      String strdec = decrypt(strenc);
    	      System.out.println(strdec);
    	    }
    	    cursor2.close();
    	    long endTimedec = System.currentTimeMillis();
            long timeElapseddec  = endTimedec - startTimedec;
            System.out.println("***************DES Decryption time "+ timeElapseddec + " msec************");
           
// Applying Compression on Encrypted data and store it in database
            //long startTimecomp = System.currentTimeMillis();
    	        DBCollection collectioncompressEmpenc = db.getCollection("EncCompressEmployeeDES");
    	        byte[] compoutput = null;
    	        System.out.println("************Compression on Encrypted Data************");
    	        for(Employee emp:alldata){
    	        	try {
    	        		//Applying the compression on encrypted string
    	        		String encobj = emp.toString().replace(" ","");
        	        	String encstr = encrypt(encobj);
    					compoutput = compressfn(encstr);
    					String compstr = compoutput.toString();
    					insertEmployeeCompressEnc(collectioncompressEmpenc,compstr);
    					//System.out.println(compoutput);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	        }
    	        //long endTimecomp = System.currentTimeMillis();
                //long timeElapsedcomp  = endTimecomp - startTimecomp;
                //System.out.println("*************DES Compression time "+ timeElapsedcomp + " msec*********");
    	        
//Applying Decompression on encrypted data and shown here
    	        //List<Employee> uncompressedData = new ArrayList<Employee>();
    		   /* BasicDBObject allQuery1 = new BasicDBObject();
    		    BasicDBObject fields1 = new BasicDBObject();
    		    fields1.put("compcipher",1);
    		    fields1.put("_id", 0);
    		    
    		    DBCursor cursor3 = collectioncompressEmpenc.find(allQuery1, fields1);
    		    System.out.println("************deCompression and Decryption of all data**********");
    		    while(cursor3.hasNext()){
    		     // Apply decompress function first and next decryption on cipher text
    			      try {
    			    	byte[] strenc = (byte[]) cursor3.next().get("cipher");
    					String decompressedstr = decompressfn(strenc);
    					String strdec = decrypt(decompressedstr);
    				    System.out.println(strdec);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    		    }
    		    cursor3.close();
    	        */
    	            	        
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
            return;
        }
        catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
            return;
        }
        catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
            return;
        }
        
        
        
        
        
        
    }
 
    
 
}