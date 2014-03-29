package uk.ac.aber.cs39440.experiments;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.InkscapeLoader;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ReadInkscape {

    public static void main(String[] args) throws SlickException,
            ParserConfigurationException, SAXException, IOException {
        String filename = "data/map1.svg";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(filename));

        doc.getDocumentElement().normalize();

        System.out.println(doc.getDocumentElement().getAttribute("width"));
        System.out.println(doc.getDocumentElement().getAttribute("height"));

        Diagram d = InkscapeLoader.load(filename);

        Figure p1start = d.getFigureByID("p1start");
        Figure p2start = d.getFigureByID("p1start");

        System.out.println(p1start.getShape().getCenterX());
        System.out.println(p2start.getShape().getCenterY());
    }

}
