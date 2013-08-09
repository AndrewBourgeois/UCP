package be.demmel.protocol.ucp.util;

public class IRA {
	
	private static final char[][] IRA_TABLE = {
		{'\u0040','\u0394','\u0020','\u0030','\u00a1','\u0050','\u00bf','\u0070'},
	    {'\u00a3','\u005f','\u0021','\u0031','\u0041','\u0051','\u0061','\u0071'},
	    {'\u0024','\u03a6','\u0022','\u0032','\u0042','\u0052','\u0062','\u0072'},
	    {'\u00a5','\u0393','\u0023','\u0033','\u0043','\u0053','\u0063','\u0073'},
	    {'\u00e8','\u039b','\u00a4','\u0034','\u0044','\u0054','\u0064','\u0074'},
	    {'\u00e9','\u03a9','\u0025','\u0035','\u0045','\u0055','\u0065','\u0075'},
	    {'\u00f9','\u03a0','\u0026','\u0036','\u0046','\u0056','\u0066','\u0076'},
	    {'\u00ec','\u03a8',((char) 39),'\u0037','\u0047','\u0057','\u0067','\u0077'},
	    {'\u00f2','\u03a3','\u0028','\u0038','\u0048','\u0058','\u0068','\u0078'},
	    {'\u00e7','\u0398','\u0029','\u0039','\u0049','\u0059','\u0069','\u0079'},
	    {((char) 10),'\u039e','\u002a','\u003a','\u004a','\u005a','\u006a','\u007a'},

	    {'\u00d8','\u00a0','\u002b','\u003b','\u004b','\u00c4','\u006b','\u00e4'},
	    {'\u00f8','\u00c6','\u002c','\u003c','\u004c','\u00d6','\u006c','\u00f6'},
	    {((char) 13),'\u00e6','\u002d','\u003d','\u004d','\u00d1','\u006d','\u00f1'},

	    {'\u00c5','\u00df','\u002e','\u003e','\u004e','\u00dc','\u006e','\u00fc'},
	    {'\u00e5','\u00c9','\u002f','\u003f','\u004f','\u00a7','\u006f','\u00e0'}
	};
	
	// input must already be in decimal format
	public static char iraToUnicode(byte part1, byte part2) {
		return IRA_TABLE[part2][part1];
	}
	
	//TODO: pass the bytes directly, not a string...
	public static String iraToUnicode(String ira) {
		if(ira == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		char[] charArray = ira.toCharArray();
		for(int i = 0; i < charArray.length; i+=2) {
			int part1 = Integer.parseInt("" + charArray[i], 16);
			int part2 = Integer.parseInt("" + charArray[i + 1], 16);
			
			sb.append(IRA_TABLE[part2][part1]);
		}
		return sb.toString();
	}
		
	public static String unicodeToIra(String unicodeValue) {
		if(unicodeValue == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder(unicodeValue.length()*2);
		for(char unicodeChar : unicodeValue.toCharArray()) {
			/* ?????
			0x1B0A 0x000C FORM FEED
			0x1B14 0x005E CIRCUMFLEX ACCENT
			0x1B28 0x007B LEFT CURLY BRACKET
			0x1B29 0x007D RIGHT CURLY BRACKET
			0x1B2F 0x005C REVERSE SOLIDUS
			0x1B3C 0x005B LEFT SQUARE BRACKET
			0x1B3D 0x007E TILDE
			0x1B3E 0x005D RIGHT SQUARE BRACKET
			0x1B40 0x007C VERTICAL LINE
			0x1B65 0x20AC EURO SIGN*/
			switch (unicodeChar) {
			case 0x0040:sb.append("00");break;// COMMERCIAL AT
			case 0x00A3:sb.append("01");break;// POUND SIGN
			case 0x0024:sb.append("02");break;// DOLLAR SIGN
			case 0x00A5:sb.append("03");break;// YEN SIGN
			case 0x00E8:sb.append("04");break;// LATIN SMALL LETTER E WITH GRAVE
			case 0x00E9:sb.append("05");break;// LATIN SMALL LETTER E WITH ACUTE
			case 0x00F9:sb.append("06");break;// LATIN SMALL LETTER U WITH GRAVE
			case 0x00EC:sb.append("07");break;// LATIN SMALL LETTER I WITH GRAVE
			case 0x00F2:sb.append("08");break;// LATIN SMALL LETTER O WITH GRAVE
			case 0x00C7:sb.append("09");break;// LATIN CAPITAL LETTER C WITH CEDILLA
			case 0x000A:sb.append("0A");break;// LINE FEED
			case 0x00D8:sb.append("0B");break;// LATIN CAPITAL LETTER O WITH STROKE
			case 0x00F8:sb.append("0C");break;// LATIN SMALL LETTER O WITH STROKE
			case 0x000D:sb.append("0D");break;// CARRIAGE RETURN
			case 0x00C5:sb.append("0E");break;// LATIN CAPITAL LETTER A WITH RING ABOVE
			case 0x00E5:sb.append("0F");break;// LATIN SMALL LETTER A WITH RING ABOVE
			
			case 0x0394:sb.append("10");break;// GREEK CAPITAL LETTER DELTA
			case 0x005F:sb.append("11");break;// LOW LINE
			case 0x03A6:sb.append("12");break;// GREEK CAPITAL LETTER PHI
			case 0x0393:sb.append("13");break;// GREEK CAPITAL LETTER GAMMA
			case 0x039B:sb.append("14");break;// GREEK CAPITAL LETTER LAMDA
			case 0x03A9:sb.append("15");break;// GREEK CAPITAL LETTER OMEGA
			case 0x03A0:sb.append("16");break;// GREEK CAPITAL LETTER PI
			case 0x03A8:sb.append("17");break;// GREEK CAPITAL LETTER PSI
			case 0x03A3:sb.append("18");break;// GREEK CAPITAL LETTER SIGMA
			case 0x0398:sb.append("19");break;// GREEK CAPITAL LETTER THETA
			case 0x039E:sb.append("1A");break;// GREEK CAPITAL LETTER XI
			case 0x00A0:sb.append("1B");break;// ESCAPE TO EXTENSION TABLE or displayed as non breaking space
			case 0x00C6:sb.append("1C");break;// LATIN CAPITAL LETTER AE
			case 0x00E6:sb.append("1D");break;// LATIN SMALL LETTER AE
			case 0x00DF:sb.append("1E");break;// LATIN SMALL LETTER SHARP S
			case 0x00C9:sb.append("1F");break;// LATIN CAPITAL LETTER E WITH ACUTE
			
			case 0x0020:sb.append("20");break;// SPACE
			case 0x0021:sb.append("21");break;// EXCLAMATION MARK
			case 0x0022:sb.append("22");break;// QUOTATION MARK
			case 0x0023:sb.append("23");break;// NUMBER SIGN
			case 0x00A4:sb.append("24");break;// CURRENCY SIGN
			case 0x0025:sb.append("25");break;// PERCENT SIGN
			case 0x0026:sb.append("26");break;// AMPERSAND
			case 0x0027:sb.append("27");break;// APOSTROPHE
			case 0x0028:sb.append("28");break;// LEFT PARENTHESIS
			case 0x0029:sb.append("29");break;// RIGHT PARENTHESIS
			case 0x002A:sb.append("2A");break;// ASTERISK
			case 0x002B:sb.append("2B");break;// PLUS SIGN
			case 0x002C:sb.append("2C");break;// COMMA
			case 0x002D:sb.append("2D");break;// HYPHEN-MINUS
			case 0x002E:sb.append("2E");break;// FULL STOP
			case 0x002F:sb.append("2F");break;// SOLIDUS
			
			case 0x0030:sb.append("30");break;// DIGIT ZERO
			case 0x0031:sb.append("31");break;// DIGIT ONE
			case 0x0032:sb.append("32");break;// DIGIT TWO
			case 0x0033:sb.append("33");break;// DIGIT THREE
			case 0x0034:sb.append("34");break;// DIGIT FOUR
			case 0x0035:sb.append("35");break;// DIGIT FIVE
			case 0x0036:sb.append("36");break;// DIGIT SIX
			case 0x0037:sb.append("37");break;// DIGIT SEVEN
			case 0x0038:sb.append("38");break;// DIGIT EIGHT
			case 0x0039:sb.append("39");break;// DIGIT NINE
			case 0x003A:sb.append("3A");break;// COLON
			case 0x003B:sb.append("3B");break;// SEMICOLON
			case 0x003C:sb.append("3C");break;// LESS-THAN SIGN
			case 0x003D:sb.append("3D");break;// EQUALS SIGN
			case 0x003E:sb.append("3E");break;// GREATER-THAN SIGN
			case 0x003F:sb.append("3F");break;// QUESTION MARK
			
			case 0x00A1:sb.append("40");break;// INVERTED EXCLAMATION MARK
			case 0x0041:sb.append("41");break;// LATIN CAPITAL LETTER A
			case 0x0042:sb.append("42");break;// LATIN CAPITAL LETTER B
			case 0x0043:sb.append("43");break;// LATIN CAPITAL LETTER C
			case 0x0044:sb.append("44");break;// LATIN CAPITAL LETTER D
			case 0x0045:sb.append("45");break;// LATIN CAPITAL LETTER E
			case 0x0046:sb.append("46");break;// LATIN CAPITAL LETTER F
			case 0x0047:sb.append("47");break;// LATIN CAPITAL LETTER G
			case 0x0048:sb.append("48");break;// LATIN CAPITAL LETTER H
			case 0x0049:sb.append("49");break;// LATIN CAPITAL LETTER I
			case 0x004A:sb.append("4A");break;// LATIN CAPITAL LETTER J
			case 0x004B:sb.append("4B");break;// LATIN CAPITAL LETTER K
			case 0x004C:sb.append("4C");break;// LATIN CAPITAL LETTER L
			case 0x004D:sb.append("4D");break;// LATIN CAPITAL LETTER M
			case 0x004E:sb.append("4E");break;// LATIN CAPITAL LETTER N
			case 0x004F:sb.append("4F");break;// LATIN CAPITAL LETTER O
			
			case 0x0050:sb.append("50");break;// LATIN CAPITAL LETTER P
			case 0x0051:sb.append("51");break;// LATIN CAPITAL LETTER Q
			case 0x0052:sb.append("52");break;// LATIN CAPITAL LETTER R
			case 0x0053:sb.append("53");break;// LATIN CAPITAL LETTER S
			case 0x0054:sb.append("54");break;// LATIN CAPITAL LETTER T
			case 0x0055:sb.append("55");break;// LATIN CAPITAL LETTER U
			case 0x0056:sb.append("56");break;// LATIN CAPITAL LETTER V
			case 0x0057:sb.append("57");break;// LATIN CAPITAL LETTER W
			case 0x0058:sb.append("58");break;// LATIN CAPITAL LETTER X
			case 0x0059:sb.append("59");break;// LATIN CAPITAL LETTER Y
			case 0x005A:sb.append("5A");break;// LATIN CAPITAL LETTER Z
			case 0x00C4:sb.append("5B");break;// LATIN CAPITAL LETTER A WITH DIAERESIS
			case 0x00D6:sb.append("5C");break;// LATIN CAPITAL LETTER O WITH DIAERESIS
			case 0x00D1:sb.append("5D");break;// LATIN CAPITAL LETTER N WITH TILDE
			case 0x00DC:sb.append("5E");break;// CAPITAL LETTER U WITH DIAERESIS
			case 0x00A7:sb.append("5F");break;// SECTION SIGN
			
			case 0x00BF:sb.append("60");break;// INVERTED QUESTION MARK
			case 0x0061:sb.append("61");break;// LATIN SMALL LETTER A
			case 0x0062:sb.append("62");break;// LATIN SMALL LETTER B
			case 0x0063:sb.append("63");break;// LATIN SMALL LETTER C
			case 0x0064:sb.append("64");break;// LATIN SMALL LETTER D
			case 0x0065:sb.append("65");break;// LATIN SMALL LETTER E
			case 0x0066:sb.append("66");break;// LATIN SMALL LETTER F
			case 0x0067:sb.append("67");break;// LATIN SMALL LETTER G
			case 0x0068:sb.append("68");break;// LATIN SMALL LETTER H
			case 0x0069:sb.append("69");break;// LATIN SMALL LETTER I
			case 0x006A:sb.append("6A");break;// LATIN SMALL LETTER J
			case 0x006B:sb.append("6B");break;// LATIN SMALL LETTER K
			case 0x006C:sb.append("6C");break;// LATIN SMALL LETTER L
			case 0x006D:sb.append("6D");break;// LATIN SMALL LETTER M
			case 0x006E:sb.append("6E");break;// LATIN SMALL LETTER N
			case 0x006F:sb.append("6F");break;// LATIN SMALL LETTER O
			
			case 0x0070:sb.append("70");break;// LATIN SMALL LETTER P
			case 0x0071:sb.append("71");break;// LATIN SMALL LETTER Q
			case 0x0072:sb.append("72");break;// LATIN SMALL LETTER R
			case 0x0073:sb.append("73");break;// LATIN SMALL LETTER S
			case 0x0074:sb.append("74");break;// LATIN SMALL LETTER T
			case 0x0075:sb.append("75");break;// LATIN SMALL LETTER U
			case 0x0076:sb.append("76");break;// LATIN SMALL LETTER V
			case 0x0077:sb.append("77");break;// LATIN SMALL LETTER W
			case 0x0078:sb.append("78");break;// LATIN SMALL LETTER X
			case 0x0079:sb.append("79");break;// LATIN SMALL LETTER Y
			case 0x007A:sb.append("7A");break;// LATIN SMALL LETTER Z
			case 0x007B:sb.append("7B");break;// SMALL LETTER A WITH DIAERESIS
			case 0x007C:sb.append("7C");break;// SMALL LETTER O WITH DIAERESIS
			case 0x007D:sb.append("7D");break;// SMALL LETTER N WITH TILDE
			case 0x007E:sb.append("7E");break;// SMALL LETTER U WITH DIAERESIS
			case 0x007F:sb.append("7F");break;// SMALL LETTER A WITH GRAVE
			default:
				throw new IllegalArgumentException("Unsupported character: " +unicodeChar);
			}
		}
		
		return sb.toString();
	}
}
