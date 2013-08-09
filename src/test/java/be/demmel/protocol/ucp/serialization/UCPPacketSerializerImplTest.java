package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import be.demmel.protocol.ucp.O_31_Alert;
import be.demmel.protocol.ucp.O_51_SubmitShortMessage;
import be.demmel.protocol.ucp.O_53_DeliveryNotification;
import be.demmel.protocol.ucp.O_56_DeleteMessage;
import be.demmel.protocol.ucp.O_60_SessionManagement;
import be.demmel.protocol.ucp.PositiveResponse;
import be.demmel.protocol.ucp.PositiveResponseWithMvp;
import be.demmel.protocol.ucp.UCPOperationHeader;
import be.demmel.protocol.ucp.UCPOperationType;
import be.demmel.protocol.ucp.UCPPacket;

public class UCPPacketSerializerImplTest {
	private static final UCPPacketSerializerImpl PACKET_SERIALIZER;

	static {
		O_31_AlertSerializer o31Serializer = new O_31_AlertSerializer();
		O_6x_AbstractDataTypeSerializer o6xSerializer = new O_6x_AbstractDataTypeSerializer();
		O_5x_AbstractDataTypeSerializer o5xSerializer = new O_5x_AbstractDataTypeSerializer();
		O_01_CallInputSerializer o01Serializer = new O_01_CallInputSerializer();
		O_02_MultipleAddressCallInputSerializer o02Serializer = new O_02_MultipleAddressCallInputSerializer();
		O_03_CallInputWithMultipleSupplementaryServicesSerializer o03Serializer = new O_03_CallInputWithMultipleSupplementaryServicesSerializer();
		O_30_SmsMessageTransferSerializer o30Serializer = new O_30_SmsMessageTransferSerializer();
		Map<UCPOperationType, UCPOperationSerializer> operationSerializers = new HashMap<>();
		operationSerializers.put(UCPOperationType.ALERT, o31Serializer);
		operationSerializers.put(UCPOperationType.SESSION_MANAGEMENT, o6xSerializer);
		operationSerializers.put(UCPOperationType.LIST_MANAGEMENT, o6xSerializer);
		operationSerializers.put(UCPOperationType.SUBMIT_SHORT_MESSAGE, o5xSerializer);
		operationSerializers.put(UCPOperationType.MODIFY_MESSAGE, o5xSerializer);
		operationSerializers.put(UCPOperationType.INQUIRY_MESSAGE, o5xSerializer);
		operationSerializers.put(UCPOperationType.DELETE_MESSAGE, o5xSerializer);
		operationSerializers.put(UCPOperationType.DELIVER_SHORT_MESSAGE, o5xSerializer);
		operationSerializers.put(UCPOperationType.DELIVER_NOTIFICATION, o5xSerializer);
		operationSerializers.put(UCPOperationType.RESPONSE_INQUIRY_MESSAGE, o5xSerializer);
		operationSerializers.put(UCPOperationType.RESPONSE_DELETE_MESSAGE, o5xSerializer);
		operationSerializers.put(UCPOperationType.CALL_INPUT, o01Serializer);
		operationSerializers.put(UCPOperationType.MULTIPLE_ADDRESS_CALL_INPUT, o02Serializer);
		operationSerializers.put(UCPOperationType.CALL_INPUT_WITH_MULTIPLE_SUPPLEMENTARY_SERVICES, o03Serializer);
		operationSerializers.put(UCPOperationType.SMS_MESSAGE_TRANSFER, o30Serializer);

		PACKET_SERIALIZER = new UCPPacketSerializerImpl(operationSerializers);
	}

	@Test
	public void alertSerializerTest() {
		ByteBuf expectedBytes = toByteBuf("0230302f30303032362f4f2f33312f373130302f303533392f424503");

		UCPOperationHeader header = new UCPOperationHeader(0, UCPOperationHeader.Type.OPERATION, UCPOperationType.ALERT);
		O_31_Alert data = new O_31_Alert("7100", "0539");
		UCPPacket ucpPacket = new UCPPacket(header, data);

		ByteBuf resultingBytes = PACKET_SERIALIZER.serialize(ucpPacket);
		resultingBytes.compareTo(expectedBytes);
	}

	@Test
	public void sessionManagementSerializerTest() {
		ByteBuf expectedBytes = toByteBuf("0230302f30303035352f4f2f36302f353631312f362f352f312f333533363536343334333533333133312f2f303130302f2f2f2f2f2f363303");

		UCPOperationHeader header = new UCPOperationHeader(0, UCPOperationHeader.Type.OPERATION, UCPOperationType.SESSION_MANAGEMENT);
		O_60_SessionManagement data = new O_60_SessionManagement("5611", '6', '5', '1', "56VCCS11", null, "0100", null);
		UCPPacket ucpPacket = new UCPPacket(header, data);

		ByteBuf resultingBytes = PACKET_SERIALIZER.serialize(ucpPacket);
		resultingBytes.compareTo(expectedBytes);
	}

	@Test
	public void submitShortMessageSerializerTest() {
		ByteBuf expectedBytes = toByteBuf("0230302f30303134302f4f2f35312f303630333134303535312f313231322f2f312f2f332f2f2f2f2f2f2f2f2f2f2f2f2f332f2f34443635373337333631363736353230373436353733373432303734363537383734323037333734363136453634363137323634323033303331323033303336323033323330333133312f2f2f2f2f2f2f2f2f2f2f2f2f343603");

		UCPOperationHeader header = new UCPOperationHeader(0, UCPOperationHeader.Type.OPERATION, UCPOperationType.SUBMIT_SHORT_MESSAGE);
		O_51_SubmitShortMessage data = new O_51_SubmitShortMessage("0603140551", "1212", null, '1', null, '3', null, null, null, null, null, null, null, null,
				'3', null, "Message test text standard 01 06 2011", null, null, null, null, null, null, null, null);
		UCPPacket ucpPacket = new UCPPacket(header, data);

		ByteBuf resultingBytes = PACKET_SERIALIZER.serialize(ucpPacket);
		resultingBytes.compareTo(expectedBytes);
	}

	@Test
	public void deliveryNotificationSerializerTest() {
		ByteBuf expectedBytes = toByteBuf("0230302f30303139382f4f2f35332f31303731333438373335332f303630333134303535312f2f2f2f2f2f2f2f2f2f2f2f2f3237303731313134353134302f302f3030302f3237303731313134353134352f332f2f344436353733373336313637363532303636364637323230333033303333333333363330333333313334333033353335333132303638363137333230363236353635364532303634363536433639373636353732363536342f2f2f2f2f2f2f2f2f33333630393030343738302f2f2f2f324203");

		UCPOperationHeader header = new UCPOperationHeader(0, UCPOperationHeader.Type.OPERATION, UCPOperationType.DELIVER_NOTIFICATION);
		O_53_DeliveryNotification data = new O_53_DeliveryNotification("10713487353", "0603140551", null, "270711145140", '0', "000", "270711145145", '3',
				null, "Message for 0033603140551 has been delivered", null, null, "33609004780", null);
		UCPPacket ucpPacket = new UCPPacket(header, data);

		ByteBuf resultingBytes = PACKET_SERIALIZER.serialize(ucpPacket);
		resultingBytes.compareTo(expectedBytes);
	}

	@Test
	public void deleteMessageSerializerTest() {
		ByteBuf expectedBytes = toByteBuf("0230312f30303038382f4f2f35362f303630333134303535312f313133332f2f2f2f2f2f2f2f2f2f2f2f2f2f2f2f2f332f2f3332333733303337333133313331333433353331333433302f2f2f2f2f2f2f2f2f2f2f2f2f394603");

		UCPOperationHeader header = new UCPOperationHeader(1, UCPOperationHeader.Type.OPERATION, UCPOperationType.DELETE_MESSAGE);
		O_56_DeleteMessage data = new O_56_DeleteMessage("0603140551", "1133", null, '3', "270711145140", null, null);
		UCPPacket ucpPacket = new UCPPacket(header, data);

		ByteBuf resultingBytes = PACKET_SERIALIZER.serialize(ucpPacket);
		resultingBytes.compareTo(expectedBytes);
	}

	@Test
	public void positiveResponseSerializerTest() {
		ByteBuf expectedBytes = toByteBuf("0230302f30303032332f522f33312f412f303030302f323603");

		UCPOperationHeader header = new UCPOperationHeader(0, UCPOperationHeader.Type.RESULT, UCPOperationType.ALERT);
		PositiveResponse data = new PositiveResponse("0000");
		UCPPacket ucpPacket = new UCPPacket(header, data);

		ByteBuf resultingBytes = PACKET_SERIALIZER.serialize(ucpPacket);
		resultingBytes.compareTo(expectedBytes);
	}

	@Test
	public void positiveResponseWithMvpSerializerTest() {
		ByteBuf expectedBytes = toByteBuf("0230302f30303035362f522f35312f412f323730373131313530362f303033333630333134303535313a3237303731313134353134302f433503");

		UCPOperationHeader header = new UCPOperationHeader(0, UCPOperationHeader.Type.RESULT, UCPOperationType.SUBMIT_SHORT_MESSAGE);
		PositiveResponse data = new PositiveResponseWithMvp("0033603140551:270711145140", "2707111506");
		UCPPacket ucpPacket = new UCPPacket(header, data);

		ByteBuf resultingBytes = PACKET_SERIALIZER.serialize(ucpPacket);
		resultingBytes.compareTo(expectedBytes);
	}

	// Converts the copied "Hex Stream" from Wireshark to a ByteBuf
	private static ByteBuf toByteBuf(String wiresharkCopy) {
		ByteBuf bb = Unpooled.buffer();

		char[] charArray = wiresharkCopy.toCharArray();

		for (int i = 0; i < charArray.length; i += 2) {
			char one = charArray[i];
			char two = charArray[i + 1];
			bb.writeByte(Byte.parseByte("" + one + two, 16));
		}
		return bb;
	}
}
