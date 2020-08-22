package com.laoh.core.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.CharArrayWriter;

/**
 * @author hyd
 * @date 2020/8/4 9:59
 */
public class JaxbUtil {
    public static String toXml(Object obj) {
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, true);
            CharArrayWriter charArrayWriter = new CharArrayWriter();
            m.marshal(obj, charArrayWriter);
            xml = charArrayWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
