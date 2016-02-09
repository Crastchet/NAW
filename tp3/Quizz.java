package tp3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Quizz {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		// TODO Auto-generated method stub
		
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 dbf.setCoalescing(false);
		 dbf.setIgnoringElementContentWhitespace(true); 
		 DocumentBuilder db = dbf.newDocumentBuilder(); 
		 Document document = db.parse(new File("src/questionnaire.xml"));
		 
		 XPath xpath = XPathFactory.newInstance().newXPath();
		 
		 XPathExpression exprQuestions = xpath.compile("//question");  
		 //XPathExpression exprPropositions = xpath.compile("//question/reponse");
		 
		 NodeList questions = (NodeList)exprQuestions.evaluate(document, XPathConstants.NODESET);
		 //NodeList propositions = (NodeList)exprPropositions.evaluate(document,  XPathConstants.NODESET);

		 Scanner sc = new Scanner(System.in);
		 int reponse;
		 ArrayList<Integer> scores = new ArrayList<Integer>();
		 
		for (int i=0; i<questions.getLength();i++) {
			
			Element question = (Element) questions.item(i);
			System.out.println("\n" + question.getFirstChild().getFirstChild().getNodeValue());
			
			for(int j=0; j<question.getChildNodes().getLength(); j++) {
				Node elt = question.getChildNodes().item(j);
				if(elt.getNodeName().compareTo("reponse") == 0)
					System.out.println(j + "\t" + elt.getFirstChild().getNodeValue());
			}
			
			do {
				reponse = sc.nextInt();
			} while (reponse < 1 || reponse > 4);
			
			scores.add(Integer.parseInt(question.getChildNodes().item(reponse).getAttributes().getNamedItem("score").getNodeValue()));
		}
		
		int score = 0;
		for(int s : scores)
			score += s;
		System.out.println("Ton score : " + score);

	}

}
