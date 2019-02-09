//Colin Gallaway and Andrew Chough
import java.util.Random;
import java.lang.System;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class BlockHeader
{
	private int blockVersion;
	private Hash prevBlock; //256-bit hash
	private Hash merkleRoot; //256-bit hash
	private long timeStamp; //Unix Time in seconds
	private BigInteger target;
	private BigInteger nonce; //32-bit number
	
	//for use with HW2
	public BlockHeader(BigInteger target){
		this(2, new Hash(), new Hash(), target, BigInteger.ZERO);
	}
	
	public BlockHeader(int blockVersion, Hash prevBlock, Hash merkleRoot,
		BigInteger target, BigInteger nonce){
		this.blockVersion = blockVersion;
		this.prevBlock = prevBlock;
		this.merkleRoot = merkleRoot;
		timeStamp = System.currentTimeMillis()/1000;
		this.target = target;
		this.nonce = nonce;
	}
	
	public int getBlockVersion(){
		return blockVersion;
	}
	
	public Hash getPrevBlock(){
		return prevBlock;
	}
	
	public Hash getMerkleRoot(){
		return merkleRoot;
	}
	
	public long getTimeStamp(){
		return timeStamp;
	}
	
	public BigInteger getTarget(){
		return target;
	}
	
	public BigInteger getNonce(){
		return nonce;
	}
	
	@Override
    public String toString(){
		return("Block Version: " + blockVersion + 
			"\nPrevious Block's Header:" + prevBlock +
			"\nMerkle Root: " + merkleRoot +
			"\nTimeStamp: " + timeStamp +
			"\nTarget: " + target +
			"\nNonce: " + nonce);
	}
}
