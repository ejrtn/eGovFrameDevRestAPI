package egovframework.ecall.main.common;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JsonOrXmlAction {
	
	public Source createXml(List<Map<String, Object>> resultList,String url) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			document.setXmlStandalone(true);  // standalone="no" 제거
			
			Element root = document.createElement("Root");
			document.appendChild(root);
			
			
			Element dataset = document.createElement("Dataset");
			dataset.setAttribute("id", url);
			root.appendChild(dataset);
			
			
			if(resultList.size()>0) {
				
				Element columnInfo = document.createElement("ColumnInfo");
				dataset.appendChild(columnInfo);
				
				for( String key: resultList.get(0).keySet()) {
					Element column = document.createElement("Column");
					column.setAttribute("id", key);
					if(resultList.get(0).get(key).getClass().getName().contains("String")) {
						column.setAttribute("type", "string");
						column.setAttribute("size", "255");
					}else if(resultList.get(0).get(key).getClass().getName().contains("Long")
							|| resultList.get(0).get(key).getClass().getName().contains("int")
							|| resultList.get(0).get(key).getClass().getName().contains("Integer")){
						column.setAttribute("type", "int");
					}else {
						column.setAttribute("type", "datetime");
					}
					
					columnInfo.appendChild(column);
				}
				
				Element rows = document.createElement("Rows");
				dataset.appendChild(rows);
				
				
				
				for(Map<String, Object> line : resultList) {
					// 태그 생성
					
					Element row = document.createElement("Row");
					rows.appendChild(row);
					for( String key: line.keySet()) {
						
						Element col = document.createElement("Col");
						
						col.setAttribute("id", key);
						col.setTextContent(line.get(key).toString());
						row.appendChild(col);
					}
					dataset.appendChild(rows);
				}
			}
			return new DOMSource(document);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String, Object> xmlToMap(String data){
		try {
			if(!data.equals("")) {
				
				Document doc = stringToXml(data);
		        	
		        Map<String, Object> result = new HashMap<>();
		        for(int i=0;i<doc.getElementsByTagName("Parameter").getLength();i++) {
		        	String column = String.valueOf(doc.getElementsByTagName("Parameter").item(i).getAttributes().getNamedItem("id").getTextContent());
		        	String value = doc.getElementsByTagName("Parameter").item(i).getTextContent();
		        	result.put(column, value);
		        }
		        return result;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return null;
	}
	
	
	public Document stringToXml(String data) {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setNamespaceAware(true);
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(new InputSource(new StringReader(data)));
		    return document;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
	
	public Map<String,Object> stringToMap(String data){
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> returnMap = mapper.readValue(data, Map.class);
	        return returnMap;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
}
