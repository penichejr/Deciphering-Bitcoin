package bitcoin;

import java.security.MessageDigest;
import java.util.Scanner;

public class BruteForce {
	private static StringBuilder string = new StringBuilder("");
	private static int min = 97, max = 123;
	private static long start;


	public static void main(String[] args) {

		System.err.println("1 -> Calculate Hash\n2 -> Identify the original input");

		Scanner scan = new Scanner(System.in);

		int opt=scan.nextInt();

		if(opt==1) {

			System.out.println("Insert Message");

			String msg=scan.next();


			byte[] bytes = msg.getBytes();
			System.out.print("MD5 :");
			imprimirHexa(getDigest("MD5", bytes));
			System.out.print("SHA-1 :");
			imprimirHexa(getDigest("SHA-1", bytes));
		}


		if(opt==2) {


			System.out.println("Insert Hash Value");

			String hashValue=scan.next();


			start = System.currentTimeMillis();
			while(true) {
				string.append((char) min);
				
				//System.out.println(string);
				//TimeUnit.SECONDS.sleep(1);
				

				for(int i = 0; i < string.length() - 1; i++) {
					for(int j = min; j  < max; j++) {
						string.setCharAt(i, (char) j);
						stringGenerator(i + 1, hashValue);
					}
				}
			}
		}
		
		scan.close();
	}



	public static void stringGenerator(int index, String hashValue)  {
		for(int i = min; i < max; i++) {
			string.setCharAt(index, (char) i);
			if(index < string.length() - 1)
				stringGenerator(index + 1, hashValue);
				//System.out.println(string);

			String msg = string.toString();
			byte[] bytes = msg.getBytes();


			if(getHexa(getDigest("MD5", bytes)).equals(hashValue)) {

				System.err.println("Original Input: " + msg);
				System.err.println("Hash Function used: MD5");
				System.err.println("It took: " + convertmillis(System.currentTimeMillis() - start));
				System.exit(0);
			}

			if(getHexa(getDigest("SHA-1", bytes)).equals(hashValue)) {

				System.err.println("Original Input: " + msg);
				System.err.println("Hash Function used: SHA-1");
				System.err.println("It took: " + convertmillis(System.currentTimeMillis() - start));
				System.exit(0);
			}

		}
	}


	public static byte[] getDigest(String algoritm, byte[] buffer) {

		try {
			MessageDigest digest = MessageDigest.getInstance(algoritm);
			digest.update(buffer);
			return digest.digest();

		} catch (Exception e) {
			return null;
		}
	}

	public static void imprimirHexa(byte[] byteArray) {
		String out= "";
		for (int i = 0; i < byteArray.length; i++) {
			if((byteArray[i] & 0xff) <= 0xf) {
				out += "0";
			}		
			out += Integer.toHexString(byteArray[i] & 0xff).toLowerCase();
		}

		System.out.println(out);
	}


	public static String getHexa(byte[] byteArray) {
		String out= "";
		for (int i = 0; i < byteArray.length; i++) {
			if((byteArray[i] & 0xff) <= 0xf) {
				out += "0";
			}		
			out += Integer.toHexString(byteArray[i] & 0xff).toLowerCase();
		}

		return out;
	}



	public static String convertmillis(long input) {
		int days = 0, hours = 0, minutes = 0, seconds = 0, millis = 0;

		int day = 86400000;
		int hour = 3600000;
		int minute = 60000;
		int second = 1000;


		if(input >= day) {
			days = (int) (input / day);
			millis = (int) (input % day);
		} else 
			millis = (int) input;

		if(millis >= hour) {
			hours = millis / hour;
			millis = millis% hour;
		}

		if(millis >= minute) {
			minutes = millis / minute;
			millis = millis % minute;
		}

		if(millis >= second) {
			seconds = millis / second;
			millis = millis % second;
		}

		return (days  + " day(s), " + hours + "h, " + minutes + "min, " + seconds + "s and " + millis + "ms");
	}
}