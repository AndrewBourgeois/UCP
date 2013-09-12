package be.demmel.protocol.ucp;

public class O_6x_AbstractDataType implements UCPOperation {
	private String oadc;// Address code originator
	private Character oton;// Originator Type of Number
	private Character onpi;// Originator Numbering Plan Id
	private Character styp;// Subtype of operation
	private String pwd;// Current password encoded into IRA characters
	private String npwd;// New password encoded into IRA characters
	private String vers;// Version number
	private String ladc;// Address for VSMSC list operation
	private Character lton;// Type of Number list address
	private Character lnpi;// Numbering Plan Id list address
	private String opid;// Originator Protocol Identifier
	private String res1;// (reserved for future use)
	
	public O_6x_AbstractDataType(String oadc, Character oton, Character onpi, Character styp, String pwd, String npwd, String vers, String ladc,
			Character lton, Character lnpi, String opid, String res1) {
		this.oadc = oadc;
		this.oton = oton;
		this.onpi = onpi;
		this.styp = styp;
		this.pwd = pwd;
		this.npwd = npwd;
		this.vers = vers;
		this.ladc = ladc;
		this.lton = lton;
		this.lnpi = lnpi;
		this.opid = opid;
		this.res1 = res1;
	}

	public String getOadc() {
		return oadc;
	}

	public Character getOton() {
		return oton;
	}

	public Character getOnpi() {
		return onpi;
	}

	public Character getStyp() {
		return styp;
	}

	public String getPwd() {
		return pwd;
	}

	public String getNpwd() {
		return npwd;
	}

	public String getVers() {
		return vers;
	}

	public String getLadc() {
		return ladc;
	}

	public Character getLton() {
		return lton;
	}

	public Character getLnpi() {
		return lnpi;
	}

	public String getOpid() {
		return opid;
	}

	public String getRes1() {
		return res1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("O_6x_AbstractDataType [oadc=");
		builder.append(oadc);
		builder.append(", oton=");
		builder.append(oton);
		builder.append(", onpi=");
		builder.append(onpi);
		builder.append(", styp=");
		builder.append(styp);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append(", npwd=");
		builder.append(npwd);
		builder.append(", vers=");
		builder.append(vers);
		builder.append(", ladc=");
		builder.append(ladc);
		builder.append(", lton=");
		builder.append(lton);
		builder.append(", lnpi=");
		builder.append(lnpi);
		builder.append(", opid=");
		builder.append(opid);
		builder.append(", res1=");
		builder.append(res1);
		builder.append("]");
		return builder.toString();
	}
}
