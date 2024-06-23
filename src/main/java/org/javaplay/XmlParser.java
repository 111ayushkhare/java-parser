package org.javaplay;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class XmlParser {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final static String FILE_PATH = "src/main/resources/CD.xml";

    // [START: Method `parseXML` starts here]
    /**
     * This method reads an XML file and parses it with the help of suitable libraries
     *
     */
    protected void parseXML() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(FILE_PATH));

            //document.getDocumentElement().normalize();
            Element rootElement = document.getDocumentElement();

            if (rootElement != null) {
                NodeList cdList = document.getElementsByTagName("CD");

                int cdCount = cdList.getLength();

                if (cdCount != 0) {
                    for (int i = 0; i < cdCount; i++) {
                        displayDetails(document, i);
                    }
                } else {
                    LOGGER.log(Level.INFO, "Zero CDs found !");
                }
            } else {
                LOGGER.log(Level.INFO, "Root opening TAG found to be null, parsing aborted !");
            }

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "Referred file is not found ::- \n" + e);
        } catch(ParserConfigurationException e) {
            LOGGER.log(Level.WARNING, "Error occured in parsing the file ::- \n" + e);
        } catch(IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "File is pointing to null ::- \n" + e);
        } catch(IOException e) {
            LOGGER.log(Level.WARNING, "IO Exception ::- \n" + e);
        } catch(SAXException e) {
            LOGGER.log(Level.WARNING, "SAX Exception ::- \n" + e);
        } catch(NullPointerException e) {
            LOGGER.log(Level.WARNING, "Null Pointer exception ::- \n"+ e);
        } catch(Exception e) {
            LOGGER.log(Level.WARNING, "Some error occurred ::- \n"+ e);
        }
    }
    // [END: Method `parseXML` ends here]


    // [START: Method `displayDetails` starts here]
    /**
     * This method prints parsed XML in readable format
     * 
     * @param document: the XML document that is parsed
     * @param i: iterating parameter
     */
    private void displayDetails(Document document, int i) {
        String cdDetails = "CD number : " + (i + 1) 
            + "\n--------------------------------------------------"
            + String.format("%-15s%s", "\nTITLE", ": " + document.getElementsByTagName("TITLE").item(i).getTextContent())
            + String.format("%-15s%s", "\nARTIST", ": " + document.getElementsByTagName("ARTIST").item(i).getTextContent())
            + String.format("%-15s%s", "\nCOUNTRY", ": " + document.getElementsByTagName("COUNTRY").item(i).getTextContent())
            + String.format("%-15s%s", "\nCOMPANY", ": " + document.getElementsByTagName("COMPANY").item(i).getTextContent())
            + String.format("%-15s%s", "\nPRICE", ": " + document.getElementsByTagName("PRICE").item(i).getTextContent())
            + String.format("%-15s%s", "\nYEAR", ": " + document.getElementsByTagName("YEAR").item(i).getTextContent())
            + "\n--------------------------------------------------";
                    
        LOGGER.log(Level.INFO, cdDetails);
    }
    //[END: Method `displayDetails` ends here]
}
