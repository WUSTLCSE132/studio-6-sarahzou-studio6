package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"

	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	


	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
				SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE);

		debug = false; // Default is to NOT be in debug mode
	}

	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte data){
		try {
			port.writeByte(data);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception!");
		}
		if (debug){
			System.out.println("<0x" + data + ">");
		}


	}

	// TODO: Add available() method
	public boolean available() throws SerialPortException{
		if (port.getInputBufferBytesCount() > 0){
			return true;
		}
		return false;
	}

	// TODO: Add readByte() method	
	public byte readByte() throws SerialPortException{
		return port.readBytes(1)[0];
	}

	// TODO: Add a main() method
	public static void main(String[] args){
		while(true){
			try{
				SerialComm sc = new SerialComm("COM3");
				if (sc.available()){
					char c = (char) sc.readByte();
					System.out.print(c);
				}
			}
			catch(SerialPortException e){
				
			}
		}
		



	}
}
