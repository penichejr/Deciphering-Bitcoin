package bitcoin;

import java.util.HashMap;
import java.util.Map;

public class CompareCharByPosition {
	
	private static int min = 97, max = 123;
	
	static Map<String,String> hashM= new HashMap<>();
	static Map<Integer,String> hashM2= new HashMap<>();
	
	
	static String cipherText="hjlnjzdrnuqrmjupxodnacnzdnjcjungcanvxuunpxmnejurnwcnzdnbnjmernacnzdnujvdnacnwxcardwoxmnbdermjlxwbdvdnacncdexjcxmxnuvdwmxnwyxlxodnnunbyjwcjsxhnulxlxmnuvdwmxnwcjulxhdwcdajzdnjlanmrcxbdjenwcdajvxaraldnamxhereauxlx";
	static String decryptedText="yaceaquielhidalgofuertequeatalextremollegodevalientequeseadviertequelamuertenotriunfodesuvidaconsumuertetuvoatodoelmundoenpocofueelespantajoyelcocodelmundoentalcoyunturaqueacreditosuaventuramorircuerdoyvivrloco";
	
	public static void main(String[] args) {
		
		compareCharsAt(cipherText, decryptedText);
		
		for (int i = min; i < max; i++) {
			char ch = (char) i;
			String str= String.valueOf(ch);
			System.out.println((char) i + "->" + hashM.get(str));
		}
	}
	
	
	public static void compareCharsAt(String a, String b) {
		
		for (int i = 0; i < a.length(); i++) {
			hashM.put(String.valueOf(a.charAt(i)) , String.valueOf(b.charAt(i)));
		}
	}
}
