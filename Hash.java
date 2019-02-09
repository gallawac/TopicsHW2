//Colin Gallaway and Andrew Chough
import java.util.Random;
import java.lang.System;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class Hash
{
	private String stringHash;
	
	private BigInteger bigIntegerHash;
	
	public Hash(String string){
		stringHash = "";
		try{
		    MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] messageDigest = md.digest(string.getBytes());
			bigIntegerHash = new BigInteger(1, messageDigest);
			stringHash = String.format("%064X", bigIntegerHash);
		}catch (NoSuchAlgorithmException e){
			bigIntegerHash = new BigInteger("0",2);
		}
	}
	
	public Hash(){
		this(String.valueOf((new Random()).nextLong()));
	}
	
	
	public Hash(BigInteger bigInteger){
		this(bigInteger.toString(16));
	}
	
	public Hash(Hash hash){
		this(hash.toString());
	}
	
	@Override
    public String toString(){
		return stringHash;
	}
	
	public BigInteger toBigInteger(){
		return bigIntegerHash;
	}
}