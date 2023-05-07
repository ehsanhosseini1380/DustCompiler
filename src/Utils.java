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
        if(notFound)
            System.out.printf("Error106 : in line [%d:%d] , Can not find Variable [%s]\n", id.getSymbol().getLine(), id.getSymbol().getCharPositionInLine()+1, id.getText());
    }

    public static void detectUndeclaredClass(TerminalNode className){
        if(!Boolean.parseBoolean(checkDataTypeIsDefined(className.getText()))){
            System.out.printf("Error105 : in line [%d:%d] , Can not find Class [%s]\n", className.getSymbol().getLine(), className.getSymbol().getCharPositionInLine()+1, className.getText());
        }
    }
}
