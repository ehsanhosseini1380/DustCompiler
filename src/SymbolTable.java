import java.util.HashMap;

public class SymbolTable {
    public static SymbolTable root;
    public SymbolTable parent;

    public String name;
    public int scopeNumber;
    public HashMap<String, String> table = new HashMap<>();

    public SymbolTable(String name, int scopeNumber, SymbolTable parent) {
        this.name = name;
        this.scopeNumber = scopeNumber;
        this.parent = parent;
    }

    public void insert(String idefNames, String values){
        table.put(idefNames, values);
        System.out.println((this.printItems()));
    }

    public String lookup(String idefName){
        return table.getOrDefault(idefName, null);
    }

    public String printItems(){
        StringBuilder tableString = new StringBuilder();
        for(var entry: table.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            tableString.append(key + "| " + value).append('\n');
        }

        return tableString.toString();
    }

    @Override
    public String toString() {
        System.out.println( "=".repeat(30) + " " + name + ": " + scopeNumber + ' ' + "=".repeat(30));
        return null;
    }
}