package com.justinmobile;

/** 
* @author linduo 
* @version 2006/08/25 
*/ 
public class CRC16{ 
    public int value; 

    public CRC16() 
    { 
     value = 0; 
    } 

    /** update CRC with byte b */ 
    public void update(byte aByte) 
    { 
     int a, b; 

     a = (int) aByte; 
     for (int count = 7; count >=0; count--) { 
         a = a << 1; 
                b = (a >>>8) & 1; 
         if ((value & 0x8000) != 0) { 
      value = ((value << 1) + b) ^ 0x1021; 
         } else { 
      value = (value << 1) + b; 
         } 
     } 
     value = value & 0xffff; 
     return; 
    } 

    /** reset CRC value to 0 */ 
    public void reset() 
    { 
     value = 0; 
    } 
    
    public int getValue() 
    { 
        return value; 
    } 
	public int byteToInt(byte[] b){
		for (int k = 0; k < b.length; k++) {
			this.update(b[k]);
		}
		//System.out.println(Integer.toHexString(this.getValue()));
		//System.out.println(this.getValue());
		return getValue();
	}
    public static void main(String[] args) { 
  CRC16 crc16 = new CRC16(); 
  byte[] b = new byte[]{ 
       //(byte) 0xF0,(byte)0xF0,(byte)0xF0,(byte)0x72 
       (byte) 0x2C,(byte)0x00,(byte)0xFF,(byte)0xFE 
       ,(byte) 0xFE,(byte)0x04,(byte)0x00,(byte)0x00 
       ,(byte) 0x00,(byte)0x00 
     }; 
  for (int k = 0; k < b.length; k++) 
  { 
   crc16.update(b[k]); 
  } 
  System.out.println(Integer.toHexString(crc16.getValue())); 
  System.out.println(crc16.getValue()); 
} 
} 
