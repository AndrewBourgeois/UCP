package be.demmel.protocol.ucp;

public class O_5x_AbstractDataType implements UCPOperation {
	private String adc;// Address code recipient for the SM OadC 16 Num. String Address code originator. 
	private String oadc;// If the OTOA field indicates alphanumeric OAdC: A 22-character string corresponds with a max. 11 character	alphanumeric string.
	private String ac;// Authentication code originator (min 4 char. max 16 char)
	private Character nrq;// Notification Request. 0 = NAdC not used. 1 = NAdC used.
	private String nadc;// Notification Address
	private Character nt;// Notification Type: Buffered message notification (BN),	Delivery Notification (DN),	Non-delivery notification (ND),	0 default value, 1 = DN, 2 = ND, 3 = DN+ND, 4 = BN,	5 = BN+DN, 6 = BN+ND, 7 = all.
	private String npid;// Notification PID value: 0100 = Mobile Station, 0122 = Fax Group 3, 0131 X.400, 0138 = Menu over PSTN, 0139 = PC appl. over PSTN (E.164), 0339 = PC appl. over X.25 (X.121), 0439 = PC appl. over ISDN (E.164), 0539 = PC appl. over TCP/IP
	private Character lrq;// Last Resort Address request: 0 = LRAd not used, 1 = LRAd used
	private String lrad;// Last Resort Address
	private String lpid;// LRAd PID value: 0100 = Mobile Station, 0122 = Fax Group 3, 0131 = X.400, 0138 = Menu over PSTN, 0139 = PC appl. over PSTN, 0339 = PC appl. over X.25 (X121), 0439 = PC appl. over ISDN (E.164), 0539 PC appl. over TCP/IP
	private Character dd;// Deferred Delivery requested: 0 = DDT not used, 1 = DDT used
	private String ddt;// Deferred delivery time in DDMMYYHHmm
	private String vp;// Validity period in DDMMYYHHmm
	private String rpid;// Replace PID. Values according to TP-PID of [3GPP	23.040]: 0000-0255. Special values: 0064 = (Type 0, user not alerted), 0065-0071 = (Replace Type 1-7), 0095 = (Return Call Message), 0125 = (ME Data download), 0126 = (ME De-personalization), 0127 = (SIM Data Download)
	private String scts;// Service Centre Time Stamp in DDMMYYHHmmss. For a Short Message this is the time stamp of the	Short Message itself. For a Notification, this is the time stamp of the corresponding Short Message.
	private Character dst;// Delivery status: 0 = delivered, 1 = buffered (see Rsn), 2 = not delivered (see Rsn)
	private String rsn; // Reason code, value '000'...'255'. Code can be found in an SMSC configuration file witch can be changed by the operator.
	private String dscts;// Delivery time stamp in DDMMYYHHmmss. Indicates the actual time of delivery of the Short Message.
	private Character mt;// Message Type. Associated parameters depend on the value of MT.
	private String nmsg;// Numeric message.
	private String amsg;// Alphanumeric message encoded into IRA characters.
	private String nb;// No. of bits in Transparent Data (TD) message.
	private String tmsg;// TD message encoded into IRA characters.
	private Character mms;// More Messages to Send (to the same SME)
	private Character pr;// Priority Requested
	private Character dc;// Deprecated. Data Coding scheme: 0 = default alphabet, 1 = user defined data ('8 bit')
	private Character mcl;// Message Class: 0 = message class 0, 1 = message class 1, 2 = message class 2, 3 = message class 3
	private Character rpi;// Reply Path: 1 = request, 2 = response
	private String cpg;// (Reserved for Code Page)
	private Character rply;// (Reserved for Reply type)
	private String otoa;// Originator Type Of Address: 1139 = The OadC is set to NPI telephone and TON international. 5039 = The OAdC contains an alphanumeric address (see OAdC and below). Leave OTOA empty for a numeric address in the OAdC.
	private String hplmn;// Home PLMN Address. E.164 number of the originating MSC.
	private String xser;// Extra Services:	With the XSer field, one or more additional services can be specified. These services consist of IRA encoded data constructed in the following common format: TTLLDD…
	// TT: represents two HEX characters defining the type of service. For a description of available services refer to section “Description Of XSer Extra Services ”
	// LL: represents two HEX characters defining the number of octets present in the data field DD. (Note that the number of HEX characters in the	data DD is twice the number of octets)
	// DD…: represents a stream of HEX characters defining the service specific data itself. If more than one additional service is to be specified in one message, this service information is concatenated without any separators, i.e., TT1LL1DD1…DD1TT2LL2DD2..DD2
	// The above construction is designed such that in the future additional service types can be added to the XSer field.
	private String res4;// (Reserved for future use)
	private String res5;// (Reserved for future use)
	
	public O_5x_AbstractDataType(String adc, String oadc, String ac, Character nrq, String nadc, Character nt, String npid, Character lrq, String lrad, String lpid, Character dd, String ddt,
			String vp, String rpid, String scts, Character dst, String rsn, String dscts, Character mt, String nmsg, String amsg, String nb, String tmsg, Character mms,
			Character pr, Character dc, Character mcl, Character rpi, String cpg, Character rply, String otoa, String hplmn, String xser, String res4, String res5) {
		this.adc = adc;
		this.oadc = oadc;
		this.ac = ac;
		this.nrq = nrq;
		this.nadc = nadc;
		this.nt = nt;
		this.npid = npid;
		this.lrq = lrq;
		this.lrad = lrad;
		this.lpid = lpid;
		this.dd = dd;
		this.ddt = ddt;
		this.vp = vp;
		this.rpid = rpid;
		this.scts = scts;
		this.dst = dst;
		this.rsn = rsn;
		this.dscts = dscts;
		this.mt = mt;
		this.nmsg = nmsg;
		this.amsg = amsg;
		this.nb = nb;
		this.tmsg = tmsg;
		this.mms = mms;
		this.pr = pr;
		this.dc = dc;
		this.mcl = mcl;
		this.rpi = rpi;
		this.cpg = cpg;
		this.rply = rply;
		this.otoa = otoa;
		this.hplmn = hplmn;
		this.xser = xser;
		this.res4 = res4;
		this.res5 = res5;
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

	public Character getNrq() {
		return nrq;
	}

	public String getNadc() {
		return nadc;
	}

	public Character getNt() {
		return nt;
	}

	public String getNpid() {
		return npid;
	}

	public Character getLrq() {
		return lrq;
	}

	public String getLrad() {
		return lrad;
	}

	public String getLpid() {
		return lpid;
	}

	public Character getDd() {
		return dd;
	}

	public String getDdt() {
		return ddt;
	}

	public String getVp() {
		return vp;
	}

	public String getRpid() {
		return rpid;
	}

	public String getScts() {
		return scts;
	}

	public Character getDst() {
		return dst;
	}

	public String getRsn() {
		return rsn;
	}

	public String getDscts() {
		return dscts;
	}

	public Character getMt() {
		return mt;
	}

	public String getNmsg() {
		return nmsg;
	}

	public String getAmsg() {
		return amsg;
	}

	public String getNb() {
		return nb;
	}

	public String getTmsg() {
		return tmsg;
	}

	public Character getMms() {
		return mms;
	}

	public Character getPr() {
		return pr;
	}

	public Character getDc() {
		return dc;
	}

	public Character getMcl() {
		return mcl;
	}

	public Character getRpi() {
		return rpi;
	}

	public String getCpg() {
		return cpg;
	}

	public Character getRply() {
		return rply;
	}

	public String getOtoa() {
		return otoa;
	}

	public String getHplmn() {
		return hplmn;
	}

	public String getXser() {
		return xser;
	}

	public String getRes4() {
		return res4;
	}

	public String getRes5() {
		return res5;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("O_5x_AbstractDataType [adc=");
		builder.append(adc);
		builder.append(", oadc=");
		builder.append(oadc);
		builder.append(", ac=");
		builder.append(ac);
		builder.append(", nrq=");
		builder.append(nrq);
		builder.append(", nadc=");
		builder.append(nadc);
		builder.append(", nt=");
		builder.append(nt);
		builder.append(", npid=");
		builder.append(npid);
		builder.append(", lrq=");
		builder.append(lrq);
		builder.append(", lrad=");
		builder.append(lrad);
		builder.append(", lpid=");
		builder.append(lpid);
		builder.append(", dd=");
		builder.append(dd);
		builder.append(", ddt=");
		builder.append(ddt);
		builder.append(", vp=");
		builder.append(vp);
		builder.append(", rpid=");
		builder.append(rpid);
		builder.append(", scts=");
		builder.append(scts);
		builder.append(", dst=");
		builder.append(dst);
		builder.append(", rsn=");
		builder.append(rsn);
		builder.append(", dscts=");
		builder.append(dscts);
		builder.append(", mt=");
		builder.append(mt);
		builder.append(", nmsg=");
		builder.append(nmsg);
		builder.append(", amsg=");
		builder.append(amsg);
		builder.append(", nb=");
		builder.append(nb);
		builder.append(", tmsg=");
		builder.append(tmsg);
		builder.append(", mms=");
		builder.append(mms);
		builder.append(", pr=");
		builder.append(pr);
		builder.append(", dc=");
		builder.append(dc);
		builder.append(", mcl=");
		builder.append(mcl);
		builder.append(", rpi=");
		builder.append(rpi);
		builder.append(", cpg=");
		builder.append(cpg);
		builder.append(", rply=");
		builder.append(rply);
		builder.append(", otoa=");
		builder.append(otoa);
		builder.append(", hplmn=");
		builder.append(hplmn);
		builder.append(", xser=");
		builder.append(xser);
		builder.append(", res4=");
		builder.append(res4);
		builder.append(", res5=");
		builder.append(res5);
		builder.append("]");
		return builder.toString();
	}
}
