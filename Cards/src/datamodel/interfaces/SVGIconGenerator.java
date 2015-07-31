/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.batik.transcoder.TranscoderException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import views.svg.SVGIcon;


public class SVGIconGenerator implements ICardIconGenerator {

    @Override
    public Icon generateCardIcon(ICard aCard, int width, int height){
        Icon result = null;
        if(null != aCard){
            if(aCard.isShowingFace()){
                //Generate Icon to show the playing card
                StringBuilder path = new StringBuilder("/views/svg/cards/");
                path.append(aCard.getValue().getCardValueString());
                path.append("_of_");
                path.append(aCard.getSuite().getSuiteString());
                path.append(".svg");
                try (InputStream in = getClass().getResourceAsStream(path.toString())) {
                    InputSource inSource = new InputSource(in);
                    Document doc = this.loadXMLFromFile(inSource);
                    SVGIcon backOfCard = new SVGIcon(doc, width, height);
                    result = backOfCard;
                } catch (ParserConfigurationException | SAXException | IOException | TranscoderException ex) {
                    Logger.getLogger(SVGIconGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                //Generate Icon to show the back of a playing card
                try (InputStream in = getClass().getResourceAsStream("/views/svg/cards/back_of_card.svg")) {
                    InputSource inSource = new InputSource(in);
                    Document doc = this.loadXMLFromFile(inSource);
                    SVGIcon backOfCard = new SVGIcon(doc, width, height);
                    result = backOfCard;
                } catch (ParserConfigurationException | SAXException | IOException | TranscoderException ex) {
                    Logger.getLogger(SVGIconGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
    
    private Document loadXMLFromFile(InputSource inSource) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(inSource);
    }
}
