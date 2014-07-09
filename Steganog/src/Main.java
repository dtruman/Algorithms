import java.io.IOException;
import java.util.Scanner;

import edu.neumont.ui.Picture;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String send="stuffjgkdlskeifjghsldkwieurghfjdksleirutiwoqpfjskfstuffjgkdlskeifjghsldkwieurghfjdksleirutiwoqpfjskfstuffjgkdlskeifjghsldkwieurghfjdksleirutiwoqpfjskfstuffjgkdlskeifjghsldkwieurghfjdksleirutiwoqpfjskfstuffjgkdlskeifjghsldkwieurghfjdksleirutiwoqpfjskfstuffjgkdlskeifjghsldkwieurghfjdksleirutiwoqpfjskf";
		String picFile="C:\\MessedWith.png";
		Steganog imagePlay=new Steganog();
		
		Picture cleanImage = new Picture(picFile);
		imagePlay.embedIntoImage(cleanImage, send);
		cleanImage.save("MessedWith.png");
		System.out.println(imagePlay.retrieveFromImage(cleanImage));
		System.in.read();
	}
}
