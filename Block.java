import java.util.concurrent.TimeUnit;
import java.math.BigInteger;

public class Block
{
	static final String MAX_HASH= "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
	private BlockHeader prevBlockHeader;
	private BlockHeader newBlockHeader;
	
	public Block(String difficulty){
		//create a "previous block with desired mining difficulty"
		BigInteger target = calculateTarget(difficulty);
		//nonce up to 4,294,967,295 (32-bit max value)
		BlockHeader prevBlockHeader = new BlockHeader(target);
		
		BigInteger nonce = BigInteger.ZERO;
		BigInteger maxNonce = new BigInteger("1", 2);
		maxNonce = maxNonce.shiftLeft(32);
		//System.out.println(String.format("%064X",maxNonce));
		while (true){
			if(nonce.compareTo(maxNonce) >= 0){
				System.out.println("Failed to find hash under target value before max nonce reached.");
				break;
			}
			if(solvedPuzzle(nonce, prevBlockHeader, target) == true){	
				System.out.println("Puzzle Solved!");
				break;
			}
			nonce = nonce.add(BigInteger.ONE);
		}
	}
	
	public Block(BlockHeader prevBlockHeader){
		BigInteger target = prevBlockHeader.getTarget();
		BigInteger nonce = BigInteger.ZERO;
		BigInteger maxNonce = new BigInteger("1", 2);
		maxNonce = maxNonce.shiftLeft(32);
		//System.out.println(String.format("%064X",maxNonce));
		while (true){
			if(nonce.compareTo(maxNonce) >= 0){
				System.out.println("Failed to find hash under target value before max nonce reached.");
				break;
			}
			if(solvedPuzzle(nonce, prevBlockHeader, target) == true){	
				System.out.println("Puzzle Solved!");
				break;
			}
			nonce = nonce.add(BigInteger.ONE);
		}
	}
	
	private BigInteger calculateTarget(String difficulty){
		BigInteger max = new BigInteger(MAX_HASH, 16);
		BigInteger diff = new BigInteger(difficulty, 16);
		return max.divide(diff);
	}
	
	private boolean solvedPuzzle(BigInteger nonce, BlockHeader header, BigInteger target){
		String string = String.valueOf(header.getBlockVersion()) + header.getPrevBlock() +
			header.getMerkleRoot() + header.getTimeStamp() + String.format("%064X", target) + String.format("%08X", nonce);
		Hash hash = new Hash(string);
		if(hash.toBigInteger().compareTo(target) == -1){
				newBlockHeader = new BlockHeader(2, hash, newMerkleRoot(), calculateTarget(nextDifficulty()), nonce);
				return true;
			}
		return false;
	}
	
	//for future use
	private Hash newMerkleRoot(){
		//temporary return:
		return new Hash();
	}
	
	//for future use
	private String nextDifficulty(){
		//temporary return
		return (new Hash()).toString();
	}
	//Colin Gallaway and Andrew Chough
	public String getHashValue(){
		return newBlockHeader.getPrevBlock().toString();
	}
	
	//Quick up to 7-hexadecimal diffuculty
	public static void main(String[] args){
        String s = "";
		for(int i = 0; i < 7; i++){
			s = s.concat("F");
			System.out.println("Difficulty: " + s);
			for(int j = 0; j < 5; j++){
				long startTime = System.nanoTime();
				Block b = new Block(s);
				long endTime = System.nanoTime();
				long elapsedTime = (endTime - startTime)/1000000;
				System.out.println("Solution: " + b.getHashValue() + "\nTime Elapsed: " + elapsedTime);
			}
			System.out.println("");
		}
    } 
}