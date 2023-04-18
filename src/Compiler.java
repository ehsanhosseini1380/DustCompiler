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
        CharStream stream = CharStreams.fromFileName("sample/input1.txt");
        DustLexer lexer = new DustLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        DustParser parser = new DustParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        ProgramPrinter listener = new ProgramPrinter();
        walker.walk(listener, tree);
    }
}