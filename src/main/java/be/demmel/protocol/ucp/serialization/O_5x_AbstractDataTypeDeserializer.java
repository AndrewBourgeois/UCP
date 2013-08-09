package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import be.demmel.protocol.ucp.O_5x_AbstractDataType;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_5x_AbstractDataTypeDeserializer implements UCPOperationDeserializer<O_5x_AbstractDataType> {

	@Override
	public O_5x_AbstractDataType deserialize(ByteBuf bb) {
		String adc = Serialization.getString(bb);
		String oadc = Serialization.getString(bb);
		String ac = Serialization.getString(bb);
		Character nrq = Serialization.getCharacter(bb);
		String nadc = Serialization.getString(bb);
		Character nt = Serialization.getCharacter(bb);
		String npid = Serialization.getString(bb);
		Character lrq = Serialization.getCharacter(bb);
		String lrad = Serialization.getString(bb);
		String lpid = Serialization.getString(bb);
		Character dd = Serialization.getCharacter(bb);
		String ddt = Serialization.getString(bb);
		String vp = Serialization.getString(bb);
		String rpid = Serialization.getString(bb);
		String scts = Serialization.getString(bb);
		Character dst = Serialization.getCharacter(bb);
		String rsn = Serialization.getString(bb);
		String dscts = Serialization.getString(bb);
		Character mt = Serialization.getCharacter(bb);
		
		String nmsg = null;
		String amsg = null;
		String tmsg = null;
		String nb = null;

		if (mt != null) {			
			nb = Serialization.getString(bb);
			
			if('2' == mt) {
				nmsg = Serialization.getString(bb);
			} else if('3' == mt) {
				String amsgIra = Serialization.getString(bb);
				amsg = IRA.iraToUnicode(amsgIra);
			} else if('4' == mt) {
				String tmsgIra = Serialization.getString(bb);
				tmsg = IRA.iraToUnicode(tmsgIra);
			} else {
				throw new IllegalArgumentException("Illegal \"mt\" value: "+ mt);
			}
		} else {
			Serialization.checkSeparator(bb, "nb");
			Serialization.checkSeparator(bb, "msg");
		}
		
		Character mms = Serialization.getCharacter(bb);
		Character pr = Serialization.getCharacter(bb);
		Character dc = Serialization.getCharacter(bb);
		Character mcl = Serialization.getCharacter(bb);
		Character rpi = Serialization.getCharacter(bb);
		String cpg = Serialization.getString(bb);
		Character rply = Serialization.getCharacter(bb);
		String otoa = Serialization.getString(bb);
		String hplmn = Serialization.getString(bb);
		String xser = Serialization.getString(bb);
		String res4 = Serialization.getString(bb);
		String res5 = Serialization.getString(bb);
		return new O_5x_AbstractDataType(adc, oadc, ac, nrq, nadc, nt, npid, lrq, lrad, lpid, dd, ddt, vp, rpid, scts, dst, rsn, dscts, mt, nmsg, amsg, nb, tmsg, mms, pr, dc, mcl, rpi, cpg, rply, otoa, hplmn, xser, res4, res5);
	}
}
