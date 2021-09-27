import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Scanner;
 
import javax.crypto.Cipher;
//import javax.xml.bind.DatatypeConverter;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class RSA {
	private static final String RSA= "RSA";
	private static Scanner sc;
	// Generating public & private keys using RSA algorithm.
    public static KeyPair generateRSAKkeyPair()throws Exception{
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }
    // Encryption function which converts the plainText into a cipherText using private Key.
    public static byte[] do_RSAEncryption(String plainText,PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance(RSA);
 
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
 
        return cipher.doFinal( plainText.getBytes());
    }
    // Decryption function which converts the ciphertext back to the orginal plaintext.
    public static String do_RSADecryption(byte[] cipherText,PublicKey publicKey)throws Exception{
        Cipher cipher= Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        byte[] result= cipher.doFinal(cipherText);
 
        return new String(result);
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
	public static void insertEmployeeEnc(DBCollection collection, byte[] str, int idval) {
		BasicDBObject data = new BasicDBObject();
        data.append("cipher", str);
        data.append("EmpId", idval);
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
		// inserting encrypted and compressed data to EmployeeCompEnc Collection
	 	public static void insertEmployeeCompressEnc(DBCollection collection,String str) {
	 		BasicDBObject data = new BasicDBObject();
	         data.append("compcipher", str);
	         //data.append("EmpId", idval);
	         collection.insert(data);
	 	}
		
		
    public static void main(String args[])throws Exception{
            KeyPair keypair = generateRSAKkeyPair();
            
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
            DB db = mongo.getDB("mongoRSA");
            DBCollection collectionEmp = db.getCollection("EmployeeRSA");
            insertAllDocuments(collectionEmp,alldata);
            
            long startTimeenc = System.currentTimeMillis();
// Applying RSA Encryption Algorithm on every single Employee object and store it in EmployeeEnc collection
            DBCollection collectionEmpenc = db.getCollection("EncEmployeeRSA");
            for(Employee emp:alldata) {
            	String plainText = emp.toString();
            	byte[] cipherText = do_RSAEncryption(plainText,keypair.getPrivate());
            	//String cipherstr = cipherText.toString();
                insertEmployeeEnc(collectionEmpenc,cipherText,emp.getEmployeeId());
            }
            long endTimeenc = System.currentTimeMillis();
            long timeElapsed  = endTimeenc - startTimeenc;
            System.out.println("***********RSA Encryption time "+ timeElapsed + " msec*****************");
            
// Applying RSA Decryption Algorithm on every single Employeeenc document and returned 
            //List<Employee> decrypteddata = new ArrayList<Employee>();
            long startTimedec = System.currentTimeMillis();
    	    BasicDBObject allQuery = new BasicDBObject();
    	    BasicDBObject fields = new BasicDBObject();
    	    fields.put("cipher",1);
    	    fields.put("_id", 0);
    	    //fields.put("EmpId", 0);

    	    DBCursor cursor2 = collectionEmpenc.find(allQuery, fields);
    	    System.out.println("************Decryption of all data**********");
    	    while(cursor2.hasNext()){
    	    //String strenc = cursor2.next().get("cipher").toString();  
    	    	byte[] temp = (byte[]) cursor2.next().get("cipher");
    	    	String decryptedText= do_RSADecryption(temp,keypair.getPublic());
    	    	System.out.println(decryptedText);
    	    }
    	    cursor2.close();
    	    long endTimedec = System.currentTimeMillis();
            long timeElapseddec  = endTimedec - startTimedec;
            System.out.println("**********RSA Decryption time "+ timeElapseddec + " msec****************");
    	    
// Applying Compress and Decompress 
    	 // Applying Compression on Encrypted data and store it in database
            //long startTimecomp = System.currentTimeMillis();
	        DBCollection collectioncompressEmpenc = db.getCollection("EncCompressEmployeeRSA");
	        byte[] compoutput = null;
	        System.out.println("************Compression on Encrypted Data************");
	        for(Employee emp:alldata){
	        	try {
	        		//Applying the compression on encrypted string
	        		String encobj = emp.toString().replace(" ","");
    	        	byte[] encstr = do_RSAEncryption(encobj,keypair.getPrivate());
    	        	String output_enc = encstr.toString();
					compoutput = compressfn(output_enc);
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
            //System.out.println("************RSA Compression time "+ timeElapsedcomp + " msec**************");
	        
    	    
	        
	        
    	    
    	    
    }
    
    
}
