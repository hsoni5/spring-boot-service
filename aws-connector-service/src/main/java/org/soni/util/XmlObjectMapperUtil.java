package org.soni.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
@Log4j2
public class XmlObjectMapperUtil {
    static JacksonXmlModule xmlModule = new JacksonXmlModule();
    static XmlMapper xmlMapper;

    static {
        xmlModule.setDefaultUseWrapper(false);
        xmlMapper = new XmlMapper(xmlModule);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Hide from public usage.
     */
    private XmlObjectMapperUtil() {
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param entity   object to map from
     * @param outClass object to map to
     */
    public static <D, T> D read(final T entity, Class<D> outClass) {
        InputStream stream = new ByteArrayInputStream(entity.toString().getBytes(StandardCharsets.UTF_8));
        try {
            XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(stream);
            return xmlMapper.readValue(xmlStreamReader, outClass);
        } catch (XMLStreamException | IOException exception) {
            log.error("Exception occurred during field mapping {} ", exception.getMessage());
        }
        return null;
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param source object to map from
     * @param source object to map to
     */
    public static <S> String write(final S source) {
        try {
            return xmlMapper.writeValueAsString(source);
        } catch (JsonProcessingException exception) {
            log.error("Exception occurred during field mapping {} ", exception.getMessage());
        }
        return null;
    }

}
