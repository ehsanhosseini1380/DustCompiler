import gen.DustLexer;
import gen.DustParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Compiler {
    public static void main(String[] args) throws IOException {
        System.out.println(String.format("%-7s", "ehsan"));


        CharStream stream = CharStreams.fromFileName("sample/inputDoodal.txt");
        DustLexer lexer = new DustLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        DustParser parser = new DustParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
//        phase 1:
//        ProgramPrinter listener = new ProgramPrinter();
//        phase 2:
//        ProgramPrinter_phase2 listener=new ProgramPrinter_phase2();
        //        phase 3, 4:
        ProgramPrinter listener=new ProgramPrinter();
        walker.walk(listener, tree);
        printTreeBFS(SymbolTable.root);

    }

    public static void printTreeBFS(SymbolTable root) {
        if (root == null)
            return;

        Queue<SymbolTable> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            SymbolTable node = queue.poll();
            System.out.println(node);

            for (SymbolTable child : node.children) {
                queue.offer(child);
            }
        }
    }
}