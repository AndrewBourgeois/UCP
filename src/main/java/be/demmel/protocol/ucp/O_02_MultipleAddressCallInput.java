package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_02_MultipleAddressCallInput implements UCPOperation {
	private String npl;// Number of parameters in the following	RAd:s list
	private String rads;// List of parameters: Each parameter consists of AdC Address code recipient; maximum length is 16 digits with optional	legitimisation code for all calls.
	private String oadc;// Address code originator, maximum length is 16 digits.
	private String ac;// Authentication code originator.
	private char mt;// Message type. Associated parameters depend on the value of the message type.
	private String nMsg;// will be filled if MR=2. Numeric message, maximum length is 160 digits.
	private String aMsg;// will be filled if MR=3. Alphanumeric message encoded into IRA characters, maximum length is representing 640 characters.
	
	public O_02_MultipleAddressCallInput(String npl, String rads, String oadc, String ac, char mt, String nMsg, String aMsg) {
		this.npl = Null.check(npl,"NPL");
		this.rads = Null.check(rads,"RAd:s");
		this.oadc = oadc;
		this.ac = ac;
		this.mt = mt;
		this.nMsg = nMsg;
		this.aMsg = aMsg;
	}
	
	public String getNpl() {
		return npl;
	}
	public String getRads() {
		return rads;
	}
	public String getOadc() {
		return oadc;
	}
	public String getAc() {
		return ac;
	}
	public char getMt() {
		return mt;
	}
	public String getnMsg() {
		return nMsg;
	}
	public String getaMsg() {
		return aMsg;
	}
}
