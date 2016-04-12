package io.github.phantamanta44.spaceres.energy;

public class ResonancePacket {
	
	public final DeviceAddress source, target;
	public final PacketType type;
	public final int data;
	
	private boolean valid = true;
	
	public ResonancePacket(DeviceAddress src, DeviceAddress target, PacketType type, int data) {
		this.source = src;
		this.target = target;
		this.type = type;
		this.data = data;
	}
	
	public boolean receive(IAddressable receiver) {
		if (receiver.getAddress().equals(target))
			return false;
		if (valid) {
			valid = false;
			return true;
		}
		return false;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public enum PacketType {
		
		IMPORT_REQ, IMPORT_ACK,
		DIST_STACK, DIST_EQUAL, DIST_ACK,
		ACCUM_REQ, ACCUM_ACK,
		EXPORT_REQ, EXPORT_ACK
		
	}
	
}
