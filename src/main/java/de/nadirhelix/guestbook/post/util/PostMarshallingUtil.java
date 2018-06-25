package de.nadirhelix.guestbook.post.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

public class PostMarshallingUtil {

	public static <T> void marshal(Class<T> clazz, T storedPosts, File file) throws JAXBException {
		Marshaller jaxbMarshaller = PostMarshallingUtil.getMarshaller(clazz);
		jaxbMarshaller.marshal(storedPosts, file);
	}

	private static <T> Marshaller getMarshaller(Class<T> clazz)
			throws JAXBException, PropertyException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		return jaxbMarshaller;
	}

	@SuppressWarnings("unchecked")
	public static <T> T unmarshall(Class<T> clazz, File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (T) jaxbUnmarshaller.unmarshal(file);
	}

}
