package Main;

public class Decrypt {
	
	
	String key = "Test";
	String input = "e{EGJ2h{EGJ>6X21y.xvz}~E";
	
	final int segLenght = 2;
	
	public String decrypt(String input, String pw){
		return decryptFullString(input, pw);
	}

	private String decryptFullString(String input, String key1){
		StringBuilder output = new StringBuilder();
		String key2 = genSubKey(key1);
		
		
		while(input.length() > 0){
			int endIndex = input.length() >=segLenght?segLenght:input.length();
			String decrypted = decrypt(input.substring(0, endIndex), key1, key2);
			
			output.append(decrypted);
			key2 = decrypted;
			input = input.substring(endIndex);
		}
		
		return output.toString();
	}
	
	private String decrypt(String input, String key1, String key2){
		byte[] data = input.getBytes();
		
		applyKey(data, key2);
		applyKey(data, key1);
				
		return new String(data);
	}
	
	private void applyKey(byte[] data, String key){
		int result = 1;
		
		for(byte b : key.getBytes()){
			result *= b;
		}
		
		String resultData = Math.abs(result)+"";
		
		for(int i = 0; i<resultData.length(); i++){
			int num = Integer.parseInt(resultData.toCharArray()[resultData.length()-i-1]+"");
			int id = (resultData.length()-i-1)%data.length;
			
			if(num==0) continue;
			
			data[id] = (byte) (data[id]-num);
		}
	}
	
	private String genSubKey(String key){
		byte[] data = key.getBytes();
		
		for(int i=0; i<data.length; i++)
			data[i] += data[data.length-1-i];
		
		
		return new String(data);
	}

}
