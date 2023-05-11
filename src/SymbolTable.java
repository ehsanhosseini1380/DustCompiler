import java.util.HashMap;
import java.util.LinkedList;

public class SymbolTable {
    public static SymbolTable root;
    public SymbolTable parent;

    public String name;
    public int scopeNumber;
    public static LinkedList<SymbolTable> instances = new LinkedList<>();

    public HashMap<String, String> table = new HashMap<>();
    public int maxKeyLen = 0;
    public int maxValueLen = 0;
    public SymbolTable(String name, int scopeNumber, SymbolTable parent) {
        this.name = name;
        this.scopeNumber = scopeNumber;
        this.parent = parent;
        instances.add(this);

    }

    public void insert(String idefNames, String values){
        table.put(idefNames, values);
        maxKeyLen = Math.max(maxKeyLen, idefNames.length()+1);
        maxValueLen = Math.max(maxValueLen, values.length()+1);    }

    public String lookup(String idefName){
        return table.getOrDefault(idefName, null);
    }

    private String printItems(){
        String tableBorder = '+' + "-".repeat(maxKeyLen+1) + '+' + "-".repeat(maxValueLen+1) + '+' + '\n';
        StringBuilder tableString = new StringBuilder(tableBorder);
        tableString.append("| " + String.format("%-" + maxKeyLen + "s", "KEY") + "| " + String.format("%-" + maxValueLen + "s", "VALUE") + "|").append('\n');
        tableString.append(tableBorder);
        for(var entry: table.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            key = String.format("%-" + maxKeyLen + "s", key);
            value = String.format("%-" + maxValueLen + "s", value);
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