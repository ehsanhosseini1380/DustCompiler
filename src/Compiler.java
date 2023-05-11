import gen.DustLexer;
import gen.DustParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Compiler {
    public static void main(String[] args) throws IOException {
        CharStream stream = CharStreams.fromFileName("sample/input2.txt");
        DustLexer lexer = new DustLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        DustParser parser = new DustParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
//        phase 1:
//        ProgramPrinter listener = new ProgramPrinter();
//        phase 2:
        ProgramPrinter listener=new ProgramPrinter();
        walker.walk(listener, tree);
        for (var entry: SymbolTable.instances)
            System.out.println(entry);

    }
}