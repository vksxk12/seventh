/*
 * see license.txt 
 */
package seventh.network.messages;

import harenet.IOBuffer;

/**
 * @author Tony
 *
 */
public class UserInputMessage extends AbstractNetMessage {
	public int keys;
	public float orientation;
	
	/**
	 * 
	 */
	public UserInputMessage() {
		super(BufferIO.USER_INPUT);
	}
	
	/* (non-Javadoc)
	 * @see seventh.network.messages.AbstractNetMessage#read(java.nio.ByteBuffer)
	 */
	@Override
	public void read(IOBuffer buffer) {	
		super.read(buffer);
		keys = buffer.getInt();
		orientation = buffer.getFloat();
	}
	
	/* (non-Javadoc)
	 * @see seventh.network.messages.AbstractNetMessage#write(java.nio.ByteBuffer)
	 */
	@Override
	public void write(IOBuffer buffer) {	
		super.write(buffer);
		buffer.putInt(keys);
		buffer.putFloat(orientation);
	}
}
