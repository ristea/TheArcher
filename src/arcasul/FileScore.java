package arcasul;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Lydya0103
 */
public class FileScore {
    private static final String FILE_NAME = "Ierarhie.poperties";
    private Map<String, Integer> ScoreTable = new HashMap<>();
    private Properties props = new Properties();
    private boolean BestScore;
    
    public FileScore() 
    {
        BestScore = false;
        citesteFisier();
        adaugaInHashMap();
    }
    
    private void citesteFisier() {
        FileInputStream in;
        try {
            in = new FileInputStream(FILE_NAME);
            props.load(in);
            in.close();
        } catch (FileNotFoundException ex) {
            File file = new File(FILE_NAME);
            try {
                file.createNewFile();
            } catch (IOException ex1) {
            }
        } catch (IOException ex) {
        }
    }
    
    public void adaugaScor(String PlayerName, Integer Score) {
        if(isBestScore(Score) == true)
        {
            BestScore = true;
            ScoreTable.put(PlayerName, Score);
            ScoreTable = sortByValues(ScoreTable);
        }
        else
        {
            ScoreTable.put(PlayerName, Score);
            ScoreTable = sortByValues(ScoreTable);
        }
    }
    
    private void adaugaInHashMap() {
        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key);
            ScoreTable.put(key, Integer.valueOf(value));
        }
        ScoreTable = sortByValues(ScoreTable);
    }
    
    private <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
            List<Map.Entry<K,V>> entries = new LinkedList<>(map.entrySet());

            Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {                
                @Override
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            
            Map<K,V> sortedMap = new LinkedHashMap<>(); 
            for(Map.Entry<K,V> entry: entries){
                sortedMap.put(entry.getKey(), entry.getValue());
            }
            return sortedMap;
    }
    
    public void salveazaTabelaScor() {
        Map<String, String> Table = new HashMap<>();
        for (Map.Entry pair : ScoreTable.entrySet()) {
            Table.put((String) pair.getKey(), pair.getValue().toString());            
        }
        props.clear();        
        props.putAll(Table);
        
        OutputStream out;
        try {
            out = new FileOutputStream( new File(FILE_NAME) );
            props.store(out,"Key = PLAYER_NAME" + "\n" + "Value = SCORE");
            out.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
    public boolean getBestScore() {
        return BestScore;
    }
    
    public void setBestScore(boolean _BestScore) {
        BestScore = _BestScore;
    }
    
    private boolean isBestScore(int CurrentScore) {
        if(ScoreTable.isEmpty() == true)
            return true;
        String FirstPlayer = (String) ScoreTable.keySet().toArray()[0];
        Integer myValue = ScoreTable.get(FirstPlayer);
        if(CurrentScore > myValue)
            return true;
        else
            return false;
    }
    
    public String getScoreTable() {
        String temp = new String();
        for (Map.Entry pair : ScoreTable.entrySet()) {
            temp += (String) pair.getKey() + " - " + pair.getValue().toString() + "\n";
        }
        return temp;
    }
}
