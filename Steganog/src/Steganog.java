import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import edu.neumont.ui.Picture;


public class Steganog
{
	Timer timer;
	double timeout;

	public Picture embedIntoImage(Picture cleanImage, String message) throws IOException
	{
		timeout=System.nanoTime();
		
		PrimeIterator dirtyMessage=new PrimeIterator((int)(2*message.length()*Math.log(message.length())));	//The nth prime is about nlogn, however nlogn does not get all the needed primes so I multiplied the number by 2 to make sure I had all the primes I needed
		
		char[] mes=message.toUpperCase().toCharArray();
		int mesIterator=0;
		
		while(dirtyMessage.hasNext() && mesIterator<mes.length)
		{
			int prime=dirtyMessage.next();
			int y=prime/cleanImage.width();
			int x=prime-(y*cleanImage.width());
			
			Color manipulation=cleanImage.get(x, y);
			
			int combo=mes[mesIterator++]-32;
			
			int red=(manipulation.getRed()&0xFC)+(((combo)&0x30)>>4);
			int green=(manipulation.getGreen()&0xFC)+(((combo)&0xC)>>2);
			int blue=(manipulation.getBlue()&0xFC)+(combo&0x3);
			
			Color change=new Color(red, green, blue);
			
			cleanImage.set(x, y, change);
		}
		
		System.out.println((System.nanoTime()-timeout)/1000000);
		
		
		return cleanImage;
	}
	
	public String retrieveFromImage(Picture imageWithSecretMessage) throws IOException
	{
		timeout=System.nanoTime();
		PrimeIterator cleanUpMessage=new PrimeIterator((int)(imageWithSecretMessage.width()*imageWithSecretMessage.height()));
		String message=new String("");
		
		while(cleanUpMessage.hasNext())
		{
			int prime=cleanUpMessage.next();
			int y=prime/imageWithSecretMessage.width();	//This tells us that the prime is on that y index given
			int x=prime-(y*imageWithSecretMessage.width());	//This tells us which x index on the y index given that the prime is found

			Color manipulation=imageWithSecretMessage.get(x, y);
			int red=manipulation.getRed()&0x3;
			int green=manipulation.getGreen()&0x3;
			int blue=manipulation.getBlue()&0x3;
		
			red=red<<4;
			green=green<<2;
		
			int combo=red+green+blue;
		
			int charRet=combo+32;

			message+=(char)(charRet);
		}
		
		System.out.println((System.nanoTime()-timeout)/1000000);
		
		return message;
	}
}
