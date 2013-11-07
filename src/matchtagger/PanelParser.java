/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matchtagger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Eric
 */
public class PanelParser {

    /**
     *
     * @author ericr
     */
    static final String NAME = "name";
    static final String HEIGHT = "height";
    static final String WIDTH = "width";
    static final String INDEX = "index";
    static final String PUSHER = "pusher";
    static final String LABEL = "label";
    static final String VALUE = "value";
    static final String VISIBLE = "visible";
    static final String CATEGORY = "category";
    static final String COLOR = "color";
    static final String POS_X = "x";
    static final String POS_Y = "y";

    @SuppressWarnings({"unchecked", "null"})
    public PanelConfig readConfig(String configFile) {
        List<Pusher> pushers = new ArrayList<>();
        String name = null;
        int height = 0;
        int width = 0;

        try {
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // Read the XML document
            Pusher pusher = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a pusher element we create a new pusher

                    if (event.asStartElement().getName().getLocalPart().equals(NAME)) {
                        event = eventReader.nextEvent();
                        name = (event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(HEIGHT)) {
                        event = eventReader.nextEvent();
                        height = Integer.parseInt(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(WIDTH)) {
                        event = eventReader.nextEvent();
                        width = Integer.parseInt(event.asCharacters().getData());
                        continue;
                    }
                    if (startElement.getName().getLocalPart().equals(PUSHER)) {
                        pusher = new Pusher();
                        // We read the attributes from this tag and add the date
                        // attribute to our object
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            // INDEx -> name ???
                            int index = 0;
                            if (attribute.getName().toString().equals(INDEX)) {
                                try {
                                    index = Integer.parseInt(attribute.getValue());
                                } catch (NumberFormatException e) {
                                    System.err.println("Error");
                                }
                                pusher.setIndex(index);
                            }
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(LABEL)) {
                            event = eventReader.nextEvent();
                            if (event.isCharacters()) {
                                pusher.setLabel(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(VALUE)) {
                        event = eventReader.nextEvent();
                        pusher.setValue(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(CATEGORY)) {
                        event = eventReader.nextEvent();
                        pusher.setCategory(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(VISIBLE)) {
                        event = eventReader.nextEvent();
                        pusher.setVisible(event.asCharacters().getData().equals("1"));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(COLOR)) {
                        event = eventReader.nextEvent();
                        if (event.isCharacters()) {
                            pusher.setColor(event.asCharacters().getData());
                        }
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(POS_X)) {
                        event = eventReader.nextEvent();
                        if (event.isCharacters()) {
                            pusher.setX(Integer.parseInt(event.asCharacters().getData()));
                        }
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(POS_Y)) {
                        event = eventReader.nextEvent();
                        if (event.isCharacters()) {
                            pusher.setY(Integer.parseInt(event.asCharacters().getData()));
                        }
                        continue;
                    }
                }
                // If we reach the end of an pusher element we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(PUSHER)) {
                        pushers.add(pusher);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        PanelConfig tp = new PanelConfig(name, width, height);
        tp.setPushers(pushers);
        return tp;
    }
}
