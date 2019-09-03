package serializableEx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Rectangle {

	private double height;
	private double width;

	public Rectangle(double height, double width) {
		this.height = height;
		this.width = width;
	}

	public double Area() {
		return height * width;
	}

	public double Perimeter() {
		return 2 * (height + width);
	}

	public static void SerializeToFile(Object classObject, String fileName) {
		try {

			// Step 1: Open a file output stream to create a file object on disk.
			// This file object will be used to write the serialized bytes of an object
			FileOutputStream fileStream = new FileOutputStream(fileName);

			// Step 2: Create a ObjectOutputStream, this class takes a files stream.
			// This class is responsible for converting the Object of any type into a byte
			// stream
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

			// Step 3: ObjectOutputStream.writeObject method takes an Object and
			// converts it into a ByteStream. Then it writes the Byte stream into
			// the file using the File stream that we created in step 1.
			objectStream.writeObject(classObject);

			// Step 4: Gracefully close the streams
			objectStream.close();
			fileStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object DeSerializeFromFileToObject(String fileName) {
		try {

			// Step 1: Create a file input stream to read the serialized content
			// of rectangle class from the file
			FileInputStream fileStream = new FileInputStream(new File(fileName));

			// Step 2: Create an object stream from the file stream. So that the content
			// of the file is converted to the Rectangle Object instance
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);

			// Step 3: Read the content of the stream and convert it into object
			Object deserializeObject = objectStream.readObject();

			// Step 4: Close all the resources
			objectStream.close();
			fileStream.close();

			// return the deserialized object
			return deserializeObject;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Rectangle rect = new Rectangle(18, 78);
		SerializeToFile(rect, "rectSerialized");
		
		Rectangle deSerializedRect = (Rectangle) DeSerializeFromFileToObject("rectSerialized");
		System.out.println("Rect area is " + deSerializedRect.Area());
	}
}
