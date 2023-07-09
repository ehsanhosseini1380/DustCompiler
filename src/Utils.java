import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.Stack;

import gen.DustParser;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Utils {
    public static String checkDataTypeIsDefined(String className){
        return (SymbolTable.root.lookup("import_"+className)==null && SymbolTable.root.lookup("class_"+className)==null) ? "False" : "True";
    }

    public static void detectUndeclaredVariable(TerminalNode id, Stack<SymbolTable> scopes){
        boolean notFound = true;
        SymbolTable symbolTable = scopes.peek();
        while(notFound && symbolTable!=null){
            notFound = (symbolTable.lookup("Field_"+id.getText())==null);
            symbolTable = symbolTable.parent;
        }
        if(notFound)
            System.out.printf("Error106 : in line [%d:%d] , Can not find Variable [%s]\n", id.getSymbol().getLine(), id.getSymbol().getCharPositionInLine()+1, id.getText());
    }


    public static boolean detectDuplicateDeclaration(String identifier, String fieldType, int line, int column, Stack<SymbolTable> scopes){
        if (scopes.peek().lookup(fieldType + "_" + identifier)!=null) {
            fieldType = fieldType.toLowerCase();
            int errorNo = (fieldType.equals("field")) ? 104 : 102;
            System.out.printf("Error%d : in line [%d:%d] , %s [%s] has been defined already\n", errorNo, line, column, fieldType, identifier);

            return true;
        }
        return false;
    }


    public static void detectUndeclaredClass(TerminalNode className){
        if(!Boolean.parseBoolean(checkDataTypeIsDefined(className.getText()))){
            System.out.printf("Error105 : in line [%d:%d] , Can not find Class [%s]\n", className.getSymbol().getLine(), className.getSymbol().getCharPositionInLine()+1, className.getText());
        }
    }


    public static void reportDuplicateClassError(String identifier, int line, int column){
        System.out.printf("Error100 : in line [%d:%d] , class [%s] has been defined already\n", line, column, identifier);
    }

    public static void detectConstructorError(String className, String constructorName, int line, int column){
        if (!Objects.equals(className, constructorName)){
            System.out.printf("Error101 : in line [%d:%d] , constructor name missmatch\n", line, column);
        }
    }

    public static void outOfRange(String identifier, TerminalNode integer, int line, int column, Stack<SymbolTable> scopes){
        HashMap <String, String> dec = new HashMap<>();
        SymbolTable symbolTable = scopes.peek();
        boolean notFound = true;
        while(notFound){
            dec = symbolTable.lookup("Field_"+identifier);
            notFound = (dec == null);
            symbolTable = symbolTable.parent;
        }

        int arraySize = Integer.parseInt(dec.get("size"));
        int indexValue = Integer.parseInt(integer.getText());

        if (indexValue >= arraySize ) {
            System.out.printf("Error107: in line [%d:%d], array index out of range\n", line, column);
        }
    }

    public static void checkParameter(DustParser.Method_callContext parameter, Stack<SymbolTable> scopes){
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="+scopes.peek().table.keySet());
    }
}
