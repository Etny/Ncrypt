package Main;

import java.nio.charset.Charset;

public class Encrypt {
	
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
		
		KeyMatrix matrix = new KeyMatrix(key1, key2);
				
		while(matrix.hasNext())
			applyKey(data, matrix.getNext());
		
			
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
	
	class KeyMatrix{
		
		private String[] list;
		
		int width, height;
		
		private int current = 0;
		
		public KeyMatrix(String key1, String key2){
			width = key1.length();
			height = key2.length();
			list = new String[width*height];
			
			for(int i=0; i<width; i++){
				for(int j=0; j<height; j++){
					list[(i*height)+j] = key1.toCharArray()[i]+""+key2.toCharArray()[j];
				}
			}
		}
		
		public String getNext(){
			String r = list[(width*height)-1-current];
			current++;		
			return r;
		}
		
		public boolean hasNext(){
			return current < list.length;
		}
	}

}
