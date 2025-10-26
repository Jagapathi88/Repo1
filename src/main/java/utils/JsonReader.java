package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	public static List<HashMap<String, String>> getdata(String fileName) throws IOException
	{
	
	File srcFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\utils\\"+fileName+".json");
	String jsonFile = FileUtils.readFileToString(srcFile,StandardCharsets.UTF_8);
	ObjectMapper mapper = new ObjectMapper();
	
	List<HashMap<String, String>> data= mapper.readValue(jsonFile, new TypeReference<List<HashMap<String,String>>>() {
	});
	return data;
	}
}
