import java.util.Stack;
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
    }


//    public static boolean detectDuplicateDeclaration(String identifier, String fieldType, int line, int column,Stack<SymbolTable> scopes){
//        if (scopes.peek().lookup(fieldType + "_" + identifier)!=null) {
//            fieldType = fieldType.toLowerCase();
//            int errorNo = (fieldType.equals("field")) ? 104 : 102;
////            System.out.printf("Error%d : in line [%d:%d] , %s [%s] has been defined already\n", errorNo, line, column, fieldType, identifier);
//
//            return true;
//        }
//        return false;
//    }
}
