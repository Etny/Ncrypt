package Main;

public class Encrypt {
	
	String key = "Test";
	String input = "Test123 Test123, Dit is een testbericht.";
	
	final int segLenght = 2;
	
	public String encryptString(String input, String pw){
		return encryptFullString(input, pw);
	}

	private String encryptFullString(String input, String key1){
		StringBuilder output = new StringBuilder();
		String key2 = genSubKey(key1);
		
		
		while(input.length() > 0){
			int endIndex = input.length() >=segLenght?segLenght:input.length();
			String encrypted = encrypt(input.substring(0, endIndex), key1, key2);
			
			output.append(encrypted);
			key2 = input.substring(0, endIndex);
			input = input.substring(endIndex);
		}
		
		return output.toString();
	}
	
	private String encrypt(String input, String key1, String key2){
		byte[] data = input.getBytes();
				
		applyKey(data, key1);
		applyKey(data, key2);
			
		return new String(data);
	}
	
	private void applyKey(byte[] data, String key){
		int result = 1;
		
		for(byte b : key.getBytes()) result *= b;
		
		String resultData = Math.abs(result)+"";
		
		for(int i = 0; i<resultData.length(); i++){
			int num = Integer.parseInt(resultData.toCharArray()[i]+"");
			int id = i%data.length;
			
			if(num==0) continue;
			
			data[id] = (byte) (data[id]+num);
		}

	}
	
	private String genSubKey(String key){
		byte[] data = key.getBytes();
		
		for(int i=0; i<data.length; i++)
			data[i] += data[data.length-1-i];
		
		
		return new String(data);
	}

}
