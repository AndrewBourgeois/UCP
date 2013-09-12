package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_30_SmsMessageTransfer implements UCPOperation {
	private String adc;// Address code recipient, maximum length is 16 digits.
	private String oadc;// Address code originator, maximum length is 16 digits.
	private String ac;// Authentication code originator.
	private Character nrq;// Notification requested.
	private String nad;// Notification address.
	private String npid;// Notification PID value: 0100 = Mobile Station,0122 Fax Group 3, 0131 = X.400, 0138 = Menu over PSTN, 0139 = PC appl. over PSTN, 0339 = PC appl. over X.25, 0439 = PC appl. over ISDN, 0539 = PC appl. over TCP/IP
	private Character dd; // Deferred delivery request.
	private String ddt;// Deferred delivery time DDMMYYHHmm.
	private String vp;// Validity period DDMMYYHHmm.
	private String amsg;// Alphanumeric message encoded into IRA characters, maximum length representing 640 characters.
	
	public O_30_SmsMessageTransfer(String adc, String oadc, String ac, Character nrq, String nad, String npid, Character dd, String ddt, String vp, String amsg) {
		this.adc = Null.check(adc,"AdC");
		this.oadc = oadc;
		this.ac = ac;
		this.nrq = nrq;
		this.nad = nad;
		this.npid = npid;
		this.dd = dd;
		this.ddt = ddt;
		this.vp = vp;
		this.amsg = amsg;
	}

	public String getAdc() {
		return adc;
	}

	public String getOadc() {
		return oadc;
	}

	public String getAc() {
		return ac;
	}

	public char getNrq() {
		return nrq;
	}

	public String getNad() {
		return nad;
	}

	public String getNpid() {
		return npid;
	}

	public char getDd() {
		return dd;
	}

	public String getDdt() {
		return ddt;
	}

	public String getVp() {
		return vp;
	}

	public String getAmsg() {
		return amsg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("O_30_SmsMessageTransfer [adc=");
		builder.append(adc);
		builder.append(", oadc=");
		builder.append(oadc);
		builder.append(", ac=");
		builder.append(ac);
		builder.append(", nrq=");
		builder.append(nrq);
		builder.append(", nad=");
		builder.append(nad);
		builder.append(", npid=");
		builder.append(npid);
		builder.append(", dd=");
		builder.append(dd);
		builder.append(", ddt=");
		builder.append(ddt);
		builder.append(", vp=");
		builder.append(vp);
		builder.append(", amsg=");
		builder.append(amsg);
		builder.append("]");
		return builder.toString();
	}
}
