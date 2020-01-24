/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cescristorey;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Noelia
 */
public class BaseDatos {
    static int puntosMaximos;

    public static int getPuntosMaximos() {
        return puntosMaximos;
    }
    public static void Escribir(int puntos){
        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = (Document) implementation.createDocument(null, "Puntos", null);
            document.setXmlVersion("1.0"); 
 
            Element raiz = document.createElement("puntosMaximos");
            document.getDocumentElement().appendChild(raiz);                        
            CrearElemento("punto",String.valueOf(puntos), raiz, document); 

            Source source = new DOMSource((Node) document);
            Result result = new StreamResult(new java.io.File("Puntos.xml"));        
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
            System.out.println("Escribo en el fichero");

         }catch(Exception e){ System.err.println("Error: "+e); }
    }
    
     private static void  CrearElemento(String datoEmple, String valor, Element raiz, Document document){
        Element elem = document.createElement(datoEmple); 
        Text text = document.createTextNode(valor); 
        raiz.appendChild(elem); 
        elem.appendChild(text); 		 	
    }
     
    public static void Leer() throws SAXException, IOException{
        XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
	GestionContenido gestor= new GestionContenido();  
	procesadorXML.setContentHandler(gestor);
 	InputSource fileXML = new InputSource("Puntos.xml");	    
        procesadorXML.parse(fileXML);
        System.out.println("Leo de la base de datos");
    }
}    
    
    class GestionContenido extends DefaultHandler {
            private String elemento;

            public GestionContenido() {
               super();
            }
	        
	    public void startDocument() {
               
	        //System.out.println("Comienzo del Documento XML");
	    }	    
	    public void endDocument() {
	        //System.out.println("Final del Documento XML");
	    }	 	    
	    public void startElement(String uri, String nombre,
	              String nombreC, Attributes atts) {
                elemento=nombre;
                if (elemento=="puntosMaximos"){
   
                }
	        //System.out.printf("\t Principio Elemento: %s %n",nombre);	 	        
	    } 	
	    public void endElement(String uri, String nombre, String nombreC) {
                if (nombre=="puntosMaximos"){
                }
	      // System.out.printf("\tFin Elemento: %s %n", nombre);
	    }	
	    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		   String car=new String(ch, inicio, longitud);
               //quitar saltos de línea	
		   car = car.replaceAll("[\t\n]","");
                   if (elemento=="punto"){
                       BaseDatos.puntosMaximos= Integer.parseInt(car);
                   }
		  // System.out.printf ("\t Caracteres: %s %n", car);		
	    }	
}

