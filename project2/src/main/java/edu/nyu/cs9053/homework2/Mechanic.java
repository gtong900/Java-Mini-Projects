package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.DiagnosticTroubleCode;

/**
 * User: blangel
 */
public class Mechanic {

    /**
     * @see {@literal https://en.wikipedia.org/wiki/OBD-II_PIDs#Mode_3_(no_PID_required)}
     * @implNote For simplification of this homework, contrary to the Wikipedia article {@code data} is not in the ISO 15765-2 protocol.
     *           It is simply an array of data where the length should be equal to {@code expectedAmount} times 2.
     * @implNote If {code data} is null, empty or not equal to {@code expectedAmount} times 2 then throw
     *           an {@linkplain IllegalArgumentException}; i.e. {@code throw new IllegalArgumentException}
     * @param data to parse
     * @param expectedAmount of {@linkplain DiagnosticTroubleCode} to decode
     * @return the decoded {@linkplain DiagnosticTroubleCode} objects see {@linkplain DiagnosticTroubleCode#construct(String)} to create the object.
     */
    
   public static DiagnosticTroubleCode[] decode(byte[] data, int expectedAmount) {

		if (data == null || data.length != (expectedAmount * 2)) {
		    throw new IllegalArgumentException("Input is null, empty or not match expectedAmount!");
		}
		
		final int dataLength = (expectedAmount * 2) - 1;
		final DiagnosticTroubleCode[] dtcCharsArray = new DiagnosticTroubleCode[expectedAmount];
		
		for (int i = 0; i <= dataLength -1; i += 2) {
		    final String dtcChars = String.format("%8s", Integer.toBinaryString(data[i] & 0xFF)).replace(' ','0')
		                            	+ String.format("%8s", Integer.toBinaryString(data[i + 1] & 0xFF)).replace(' ', '0');
		    dtcCharsArray[i / 2] = new DiagnosticTroubleCode(splitChars(dtcChars));
		}
		
		return dtcCharsArray;
	}

	private static String splitChars(final String dtcChars) {

		final String firstChar = dtcChars.substring(0,2);
		final String secondChar = dtcChars.substring(2,4);
		final String thirdChar = dtcChars.substring(4,8);
		final String fourthChar = dtcChars.substring(8,12);
		final String fifthChar = dtcChars.substring(12);
		
		return switchChars(firstChar, 1) +  switchChars(secondChar, 2) + switchChars(thirdChar, 3)
				+ switchChars(fourthChar, 4) + switchChars(fifthChar, 5);		
	}

	private static String switchChars(final String portionOfChars, final int positionInChars) {

		String switchedChar = null;
		
		if (positionInChars == 1) {

			switch(portionOfChars) {
			
			case "00":
				switchedChar = "P";
				break;
			
			case "01":
				switchedChar = "C";
				break;
			
			case "10":
				switchedChar = "B";
				break;
			
			case "11":
				switchedChar = "U";
				break;

			default:
				break;
				
			}

		} else if (positionInChars == 2) {

		switch(portionOfChars) {
		
		case "00":
			switchedChar = "0";
			break;
		
		case "01":
			switchedChar = "1";
			break;
		
		case "10":
			switchedChar = "2";
			break;
		
		case "11":
			switchedChar = "3";
			break;

		default:
			break;
			
		}

		} else if (positionInChars == 3 || positionInChars == 4 || positionInChars == 5) {

		switch(portionOfChars) {
		
		case "0000":
				switchedChar = "0";
				break;

		case "0001":
				switchedChar = "1";
				break;
		
		case "0010":
				switchedChar = "2";
				break;
		
		case "0011":
				switchedChar = "3";
				break;

		case "0100":
				switchedChar = "4";
				break;
		
		case "0101":
				switchedChar = "5";
				break;
		
		case "0110":
				switchedChar = "6";
				break;
		
		case "0111":
				switchedChar = "7";
				break;

		case "1000":
				switchedChar = "8";
				break;
		
		case "1001":
				switchedChar = "9";
				break;
		
		case "1010":
				switchedChar = "A";
				break;
		
		case "1011":
				switchedChar = "B";
				break;

		case "1100":
				switchedChar = "C";
				break;
		
		case "1101":
				switchedChar = "D";
				break;
		
		case "1110":
				switchedChar = "E";
				break;
		
		case "1111":
				switchedChar = "F";
				break;
			
		default:
				break;
			
		}

		}

		return  switchedChar;
	
	}


}
