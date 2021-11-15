package com.luxoft.vmosin.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.luxoft.vmosin.eintity.Person;

public class JsonDataStoreImpl implements DataStore<Person>{
	
	private static JsonDataStoreImpl instance;
	
	private JsonDataStoreImpl() {}
	
	public static JsonDataStoreImpl getInstance() {
		if (instance == null) {
			instance = new JsonDataStoreImpl();
		}
		return instance;
	}
	
	@Override
	public void saveData(List<Person> list, String fileName) {
		JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jObj =  new JSONObject();
            jObj.put("name", list.get(i).getName());
            jObj.put("group",  list.get(i).getGroup());
            jObj.put("isDone",  list.get(i).isDone());
            jsonArray.put(jObj);
        }
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonArray.toString());
        } catch(IOException e){
            System.out.println(e);
        }
	}

	@Override
	public String loadData(String fileName, List <Person> list) {
		String inputData = null;
		try(BufferedReader reader = new BufferedReader(new FileReader (fileName))) {
			StringBuilder stringBuilder = new StringBuilder();
			while( ( inputData = reader.readLine() ) != null ) {
	            stringBuilder.append(inputData);
	        }
			inputData = stringBuilder.toString();
		} catch (IOException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		
		list.clear();
		JSONArray jsonArray = new JSONArray(inputData);
		for (int i = 0; i < jsonArray.length(); i++) {
			 JSONObject jObj = jsonArray.getJSONObject(i);
		     Person person = new Person();
		     person.setName(jObj.getString("name"));
		     person.setGroup(jObj.getInt("group"));
		     person.setDone(jObj.getBoolean("isDone"));
		     list.add(person);
		}
		return fileName;
	}
	
	

}
