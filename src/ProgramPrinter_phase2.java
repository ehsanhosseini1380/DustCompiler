
import gen.DustListener;
import gen.DustParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.Stack;
public class ProgramPrinter_phase2 implements DustListener{
    public final Stack<SymbolTable> scopes = new Stack<>();
    @Override
    public void enterProgram(DustParser.ProgramContext ctx) {
        scopes.push(new SymbolTable("program", ctx.start.getLine(), null));
        scopes.peek().toString();
    }

    @Override
    public void exitProgram(DustParser.ProgramContext ctx) {
        scopes.pop();
        System.out.println("=".repeat(30));
    }

    @Override
    public void enterImportclass(DustParser.ImportclassContext ctx) {

    }

    @Override
    public void exitImportclass(DustParser.ImportclassContext ctx) {

    }

    @Override
    public void enterClassDef(DustParser.ClassDefContext ctx) {
        StringBuilder parents = new StringBuilder();
        parents.append( "Class (name:" + ctx.CLASSNAME(0).getText()+") (parent:");
        if(ctx.CLASSNAME(1) != null){
            for (int i=1;i<ctx.CLASSNAME().size();i++){
                parents.append(ctx.CLASSNAME(i).getText()).append(",");
            }
        }
        else {
            parents.append("object");
        }
        parents.append(")");
        scopes.peek().insert("Class_"+ctx.CLASSNAME(0).getText(), parents.toString());
        scopes.push(new SymbolTable(ctx.CLASSNAME(0).getText(), ctx.start.getLine(),scopes.peek()));
        scopes.peek().toString();

    }

    @Override
    public void exitClassDef(DustParser.ClassDefContext ctx) {
        scopes.pop();
        System.out.println("=".repeat(30));
    }

    @Override
    public void enterClass_body(DustParser.Class_bodyContext ctx) {

    }

    @Override
    public void exitClass_body(DustParser.Class_bodyContext ctx) {

    }

    @Override
    public void enterVarDec(DustParser.VarDecContext ctx) {
        scopes.peek().insert("Field_"+ctx.ID().getText(), "ClassField (name: "+ ctx.ID().getText()+") (type: "+ ctx.getChild(0).getText()+", isDefiend:");
        System.out.println();
    }

    @Override
    public void exitVarDec(DustParser.VarDecContext ctx) {

    }

    @Override
    public void enterArrayDec(DustParser.ArrayDecContext ctx) {

    }

    @Override
    public void exitArrayDec(DustParser.ArrayDecContext ctx) {

    }

    @Override
    public void enterMethodDec(DustParser.MethodDecContext ctx) {
    }

    @Override
    public void exitMethodDec(DustParser.MethodDecContext ctx) {

    }

    @Override
    public void enterConstructor(DustParser.ConstructorContext ctx) {

    }

    @Override
    public void exitConstructor(DustParser.ConstructorContext ctx) {

    }

    @Override
    public void enterParameter(DustParser.ParameterContext ctx) {

    }

    @Override
    public void exitParameter(DustParser.ParameterContext ctx) {

    }

    @Override
    public void enterStatement(DustParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(DustParser.StatementContext ctx) {

    }

    @Override
    public void enterReturn_statment(DustParser.Return_statmentContext ctx) {

    }

    @Override
    public void exitReturn_statment(DustParser.Return_statmentContext ctx) {

    }

    @Override
    public void enterCondition_list(DustParser.Condition_listContext ctx) {

    }

    @Override
    public void exitCondition_list(DustParser.Condition_listContext ctx) {

    }

    @Override
    public void enterCondition(DustParser.ConditionContext ctx) {

    }

    @Override
    public void exitCondition(DustParser.ConditionContext ctx) {

    }

    @Override
    public void enterIf_statment(DustParser.If_statmentContext ctx) {

    }

    @Override
    public void exitIf_statment(DustParser.If_statmentContext ctx) {

    }

    @Override
    public void enterWhile_statment(DustParser.While_statmentContext ctx) {

    }

    @Override
    public void exitWhile_statment(DustParser.While_statmentContext ctx) {

    }

    @Override
    public void enterIf_else_statment(DustParser.If_else_statmentContext ctx) {

    }

    @Override
    public void exitIf_else_statment(DustParser.If_else_statmentContext ctx) {

    }

    @Override
    public void enterPrint_statment(DustParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(DustParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(DustParser.For_statmentContext ctx) {

    }

    @Override
    public void exitFor_statment(DustParser.For_statmentContext ctx) {

    }

    @Override
    public void enterMethod_call(DustParser.Method_callContext ctx) {

    }

    @Override
    public void exitMethod_call(DustParser.Method_callContext ctx) {

    }

    @Override
    public void enterAssignment(DustParser.AssignmentContext ctx) {

    }

    @Override
    public void exitAssignment(DustParser.AssignmentContext ctx) {

    }

    @Override
    public void enterExp(DustParser.ExpContext ctx) {

    }

    @Override
    public void exitExp(DustParser.ExpContext ctx) {

    }

    @Override
    public void enterPrefixexp(DustParser.PrefixexpContext ctx) {

    }

    @Override
    public void exitPrefixexp(DustParser.PrefixexpContext ctx) {

    }

    @Override
    public void enterArgs(DustParser.ArgsContext ctx) {

    }

    @Override
    public void exitArgs(DustParser.ArgsContext ctx) {

    }

    @Override
    public void enterExplist(DustParser.ExplistContext ctx) {

    }

    @Override
    public void exitExplist(DustParser.ExplistContext ctx) {

    }

    @Override
    public void enterArithmetic_operator(DustParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void exitArithmetic_operator(DustParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void enterRelational_operators(DustParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void exitRelational_operators(DustParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void enterAssignment_operators(DustParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void exitAssignment_operators(DustParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
