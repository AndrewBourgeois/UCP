package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_31_Alert implements UCPOperation {
	private String adc; // Address code for the SMT, maximum length is 16 digits.
	private String pid; // SMT PID value: 0100 = Mobile Station, 0122 = Fax Group 3, 0131 = X.400, 0138 = Menu over PSTN, 0139 = PC appl. via PSTN, 0339 = PC appl. via X.25, 0439 = PC appl. via ISDN, 0539 = PC appl. via TCP/IP, 0639 = PC appl. via abbreviated number
	
	public O_31_Alert(String adc, String pid) {
		this.adc = Null.check(adc,"AdC");
		this.pid = Null.check(pid,"PID");
	}

	public String getAdc() {
		return adc;
	}

	public String getPid() {
		return pid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("O_31_Alert [adc=");
		builder.append(adc);
		builder.append(", pid=");
		builder.append(pid);
		builder.append("]");
		return builder.toString();
	}
}
