import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

import gen.DustParser;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.io.File;

public class Utils {
    public static String checkDataTypeIsDefined(String className){
        return (SymbolTable.root.lookup("import_"+className)==null && SymbolTable.root.lookup("class_"+className)==null) ? "False" : "True";
    }

    public static void detectUndeclaredVariable(TerminalNode id, Stack<SymbolTable> scopes){
        if (deepLookup(String.format("Field_%s",id.getText()),scopes) == null)
            System.err.printf("Error100 : in line [%d:%d], Can not find Variable [%s]\n", id.getSymbol().getLine(), id.getSymbol().getCharPositionInLine() + 1, id.getText());
    }


    public static boolean detectDuplicateDeclaration(String identifier, String fieldType, int line, int column, Stack<SymbolTable> scopes){
        if (scopes.peek().lookup(fieldType + "_" + identifier)!=null) {
            fieldType = fieldType.toLowerCase();
            int errorNo = (fieldType.equals("field")) ? 104 : 102;
            System.err.printf("Error%d : in line [%d:%d], %s [%s] has been defined already\n", errorNo, line, column, fieldType, identifier);

            return true;
        }
        return false;
    }


    public static void detectUndeclaredClass(TerminalNode className){
        if(!Boolean.parseBoolean(checkDataTypeIsDefined(className.getText()))){
            System.err.printf("Error101 : in line [%d:%d], Can not find Class [%s]\n", className.getSymbol().getLine(), className.getSymbol().getCharPositionInLine()+1, className.getText());
        }
    }


    public static void reportDuplicateClassError(String identifier, int line, int column){
        System.err.printf("Error103 : in line [%d:%d], class [%s] has been defined already\n", line, column, identifier);
    }

    public static void detectConstructorError(String className, String constructorName, int line, int column){
        if (!Objects.equals(className, constructorName)){
            System.err.printf("Error105 : in line [%d:%d], constructor name mismatch\n", line, column);
        }
    }

    public static void outOfRange(String identifier, TerminalNode integer, int line, int column, Stack<SymbolTable> scopes){
        HashMap<String, String > dec = deepLookup(String.format("Field_%s",identifier), scopes);
        int arraySize = Integer.parseInt(dec.get("size"));
        int indexValue = Integer.parseInt(integer.getText());

        if (indexValue >= arraySize ) {
            System.err.printf("Error106 : in line [%d:%d], array index out of range\n", line, column);
        }
    }

    public static void checkParameter(DustParser.ExplistContext ctx,String methodName, int line ,Stack<SymbolTable> scopes) {
        HashMap<String,String> methodProperties=deepLookup(String.format("Method_%s",methodName),scopes);
        if(methodProperties == null){
        }
        else{
            if (ctx.exp().size() == Integer.parseInt(methodProperties.get("paramCount"))){
                for(int i = 0; i < ctx.exp().size(); i++){
                    HashMap<String,String> paramProperties=deepLookup(String.format("Field_%s",ctx.exp(i).getText()),scopes);
                    if(!paramProperties.get("type").equals(methodProperties.get(String.format("type%d",i+1)))) {
                        System.err.printf("Error107 : in line [%d], method parameter mismatch\n", line);
                    }
                }
            }
            else{ System.err.printf("Error108 : in line [%d], this method expects %d parameters\n", line, Integer.parseInt(methodProperties.get("paramCount")));
            }
        }
    }

    public static HashMap<String, String>  deepLookup(String query, Stack<SymbolTable> scopes){
        boolean notFound = true;
        SymbolTable symbolTable = scopes.peek();
        HashMap <String, String> dec = new HashMap<>();
        while(notFound && symbolTable!=null){
            dec = symbolTable.lookup(query);
            notFound = (symbolTable.lookup(query)==null);
            symbolTable = symbolTable.parent;
        }
        if(notFound)return null;
        else return dec;
    }
    public static void searchClass(String className, String location,int line,int column) {
        File folder = new File(location);
        File[] files = folder.listFiles();

        boolean found = false;

        for (File file : files) {
            if (file.isFile() && file.getName().equals(className + ".java")) {
                found = true;
            }
        }

        if (!found) {
            System.err.println(String.format("Error109 : in line [%d:%d] Can not find Class %s ",line,column,className));
        }
    }
}
