package com.nace.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.nace.app.exception.NaceLoadAllException;
import com.nace.app.model.Nace;


public class CSVUtil {
	
  public static String TYPE = "text/csv";  
  
  public static boolean hasCSVFormat(MultipartFile file) {
    return TYPE.equals(file.getContentType()) ? true : false;
  }
  
  
  public static List<Nace> csvToNaces(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
      List<Nace> naces = new ArrayList<Nace>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      for (CSVRecord csvRecord : csvRecords) {
        Nace nace = new Nace(
              Long.parseLong(csvRecord.get(0)),
              Long.parseLong(csvRecord.get("Level")),
              csvRecord.get("Code"),
              csvRecord.get("Parent"),
              csvRecord.get("Description"),
              csvRecord.get("This item includes"),
              csvRecord.get("This item also includes"),
              csvRecord.get("Rulings"),
              csvRecord.get("This item excludes"),
              csvRecord.get("Reference to ISIC Rev. 4")
            );
        naces.add(nace);
      }
      return naces;
    } catch (IOException e) {
      throw new NaceLoadAllException("fail to parse CSV file: " + e.getMessage());
    }
  }
}