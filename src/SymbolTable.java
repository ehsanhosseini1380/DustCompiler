import java.util.HashMap;
import java.util.LinkedList;

public class SymbolTable {
    public static SymbolTable root;
    public SymbolTable parent;
    public LinkedList<SymbolTable> children = new LinkedList<>();
    public String name;
    public int scopeNumber;
    public static LinkedList<SymbolTable> instances = new LinkedList<>();

    public HashMap<String, HashMap<String, String>> table = new HashMap<>();
    public int maxKeyLen = 0;
    public int maxValueLen = 0;
    public SymbolTable(String name, int scopeNumber, SymbolTable parent) {
        this.name = name;
        this.scopeNumber = scopeNumber;
        this.parent = parent;
        instances.add(this);

    }
    public String valToString(HashMap<String,String> input){
        StringBuilder s=new StringBuilder();
        for(String key : input.keySet()){
            s.append("(").append(key).append(":").append(input.get(key)).append(")");
        }
        return s.toString();
    }


    public void insert(String idefNames, HashMap<String, String> values){
        table.put(idefNames, values);
        maxKeyLen = Math.max(maxKeyLen, idefNames.length()+1);
        maxValueLen = Math.max(maxValueLen, valToString(values).length()+1);    }

    public HashMap<String, String> lookup(String idefName){
        return table.getOrDefault(idefName, null);
    }

    private String printItems(){
        String tableBorder = '+' + "-".repeat(maxKeyLen+1) + '+' + "-".repeat(maxValueLen+1) + '+' + '\n';
        StringBuilder tableString = new StringBuilder(tableBorder);
        tableString.append("| " + String.format("%-" + maxKeyLen + "s", "KEY") + "| " + String.format("%-" + maxValueLen + "s", "VALUE") + "|").append('\n');
        tableString.append(tableBorder);
        for(var entry: table.entrySet()){
            String key = entry.getKey();
            key = String.format("%-" + maxKeyLen + "s", key);
            String value = String.format("%-" + maxValueLen + "s", valToString(entry.getValue()));
            tableString.append("| " + key + "| " + value + "|").append('\n');
        }
        tableString.append(tableBorder);

        return tableString.toString();
    }

    @Override
    public String toString() {
        return "=".repeat(30) + " " + name + ": " + scopeNumber + ' ' + "=".repeat(30) + '\n' +
                (table.size()>0 ? printItems() : " - empty symbol table\n") + '\n'
                ;
    }
}