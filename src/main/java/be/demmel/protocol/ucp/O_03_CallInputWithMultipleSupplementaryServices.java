package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_03_CallInputWithMultipleSupplementaryServices implements UCPOperation {
	private String rad;// AdC Address code recipient, maximum length is 16 digits, combined with optional legitimisation code for all calls.
	private String oadc;// Address code originator, maximum length is 16 digits.
	private String ac;// Authentication code originator.
	private String npl;// Number of parameters in the following	GA:s list. Must be “0”.
	private String gas;// List of additional GA:s requested by the calling party. Not present because NPL = 0.
	private Character rp;// Repetition requested. Must be left empty.
	private Character pr; // Priority request 1 or 3. Must be left empty.
	private String lpr;// Legitimisation code for priority requested. Must be left empty.
	private Character ur;// Urgent message indicator request. Must	be left empty.
	private String lur;// Legitimisation code for urgent message. Must be left empty.
	private Character rc;// Reverse charging request. Must be left empty.
	private String lrc;// Legitimisation code for reverse charging.	Must be left empty.
	private Character dd;// Deferred delivery request.
	private String ddt;// Deferred delivery time DDMMYYHHmm.
	private char mt;// Message type. Associated parameters depend on the value of the message type.
	private String nMsg;// will be filled if MR=2. Numeric message, maximum length is 160 digits.
	private String aMsg;// will be filled if MR=3. Alphanumeric message encoded into IRA characters, maximum length is representing 640 characters.
	
	public O_03_CallInputWithMultipleSupplementaryServices(String rad, String oadc, String ac, String npl, String gas, Character rp, Character pr, String lpr, Character ur,
			String lur, Character rc, String lrc, Character dd, String ddt, char mt, String nMsg, String aMsg) {
		this.rad = Null.check(rad,"RAd");
		this.oadc = oadc;
		this.ac = ac;
		this.npl = Null.check(npl,"NPL");
		this.gas = gas;
		this.rp = rp;
		this.pr = pr;
		this.lpr = lpr;
		this.ur = ur;
		this.lur = lur;
		this.rc = rc;
		this.lrc = lrc;
		this.dd = dd;
		this.ddt = ddt;
		this.mt = mt;
		this.nMsg = nMsg;
		this.aMsg = aMsg;
	}

	public String getRad() {
		return rad;
	}

	public String getOadc() {
		return oadc;
	}

	public String getAc() {
		return ac;
	}

	public String getNpl() {
		return npl;
	}

	public String getGas() {
		return gas;
	}

	public char getRp() {
		return rp;
	}

	public char getPr() {
		return pr;
	}

	public String getLpr() {
		return lpr;
	}

	public char getUr() {
		return ur;
	}

	public String getLur() {
		return lur;
	}

	public char getRc() {
		return rc;
	}

	public String getLrc() {
		return lrc;
	}

	public char getDd() {
		return dd;
	}

	public String getDdt() {
		return ddt;
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
