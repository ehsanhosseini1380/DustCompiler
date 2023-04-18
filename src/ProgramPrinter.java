
import gen.DustListener;
import gen.DustParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
public class ProgramPrinter implements DustListener {

    //instance creation of Printer for indention.
    public Printer indentPrinter = new Printer();

    @Override
    public void enterProgram(DustParser.ProgramContext ctx) {
        System.out.println("program start{");
        indentPrinter.increaseIndentation();
    }

    @Override
    public void exitProgram(DustParser.ProgramContext ctx) {
        indentPrinter.decreaseIndentation();
        System.out.println("}");
    }

    @Override
    public void enterImportclass(DustParser.ImportclassContext ctx) {
        System.out.printf(("import class: "+ ctx.CLASSNAME().getText()).indent(indentPrinter.indentation));
    }

    @Override
    public void exitImportclass(DustParser.ImportclassContext ctx) {
    }

    @Override
    public void enterClassDef(DustParser.ClassDefContext ctx) {
        StringBuilder parents = new StringBuilder();
        parents.append("class ").append(ctx.CLASSNAME(0).getText()).append("/ class parent: ");
        if(ctx.CLASSNAME(1) != null){
            for (int i=1;i<ctx.CLASSNAME().size();i++){
                parents.append(ctx.CLASSNAME(i).getText()).append(",");
            }
        }
        else {
            parents.append("object");
        }
        System.out.printf((parents + "{").indent(indentPrinter.indentation));
        System.out.println();
        indentPrinter.increaseIndentation();
    }

    @Override
    public void exitClassDef(DustParser.ClassDefContext ctx) {
        indentPrinter.decreaseIndentation();
        System.out.printf("}".indent(indentPrinter.indentation));
    }

    @Override
    public void enterClass_body(DustParser.Class_bodyContext ctx) {
    }

    @Override
    public void exitClass_body(DustParser.Class_bodyContext ctx) {
    }

    @Override
    public void enterVarDec(DustParser.VarDecContext ctx) {
        if(ctx.getParent().getRuleIndex() == 3 || (ctx.getParent().getParent().getRuleIndex() == 6 && ctx.getParent().getRuleIndex() == 9))
            System.out.printf(("field: " + ctx.ID().getText() + "/ type= " + ctx.getChild(0).getText()).indent(indentPrinter.indentation));
    }

    @Override
    public void exitVarDec(DustParser.VarDecContext ctx) {

    }

    @Override
    public void enterArrayDec(DustParser.ArrayDecContext ctx) {
        if(ctx.getParent().getRuleIndex() == 3 || (ctx.getParent().getParent().getRuleIndex() == 6 && ctx.getParent().getRuleIndex() == 9))
            System.out.printf(("field: " + ctx.ID().getText() + "/ type= " + ctx.getChild(0).getText()).indent(indentPrinter.indentation));

    }

    @Override
    public void exitArrayDec(DustParser.ArrayDecContext ctx) {

    }

    @Override
    public void enterMethodDec(DustParser.MethodDecContext ctx) {
        StringBuilder methodDec = new StringBuilder();
        methodDec.append("class method: ").append(ctx.ID().getText());
        if(ctx.TYPE() != null) methodDec.append("/ " + "return type: ").append(ctx.TYPE().getText());
        if(ctx.CLASSNAME() != null) methodDec.append("/ " + "return type: ").append(ctx.CLASSNAME().getText());
        System.out.printf((methodDec+"{").indent(indentPrinter.indentation));
        indentPrinter.increaseIndentation();
    }

    @Override
    public void exitMethodDec(DustParser.MethodDecContext ctx) {
        indentPrinter.decreaseIndentation();
        System.out.printf("}".indent(indentPrinter.indentation));

    }

    @Override
    public void enterConstructor(DustParser.ConstructorContext ctx) {
        System.out.printf(("class constructor: " + ctx.CLASSNAME().getText() + "{").indent(indentPrinter.indentation));
        indentPrinter.increaseIndentation();
    }

    @Override
    public void exitConstructor(DustParser.ConstructorContext ctx) {
        indentPrinter.decreaseIndentation();
        System.out.printf("}".indent(indentPrinter.indentation));
    }

    @Override
    public void enterParameter(DustParser.ParameterContext ctx) {
        StringBuilder parameterList = new StringBuilder();
        parameterList.append("parameter list: [ ");
        for (int i = 0; i < ctx.varDec().size(); i++) {
            parameterList.append(ctx.varDec(i).getChild(0).getText()).append(" ").append(ctx.varDec(i).getChild(1).getText());
            if (ctx.varDec().size() > 1) parameterList.append(", ");
        }
        System.out.printf((parameterList+"]").indent(indentPrinter.indentation));
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
        System.out.printf("nested statement{".indent(indentPrinter.indentation));
        indentPrinter.increaseIndentation();
    }

    @Override
    public void exitIf_statment(DustParser.If_statmentContext ctx) {
        indentPrinter.decreaseIndentation();
        System.out.printf("}".indent(indentPrinter.indentation));
    }

    @Override
    public void enterWhile_statment(DustParser.While_statmentContext ctx) {
        System.out.printf("nested statement{".indent(indentPrinter.indentation));
        indentPrinter.increaseIndentation();
    }

    @Override
    public void exitWhile_statment(DustParser.While_statmentContext ctx) {
        indentPrinter.decreaseIndentation();
        System.out.printf("}".indent(indentPrinter.indentation));
    }

    @Override
    public void enterIf_else_statment(DustParser.If_else_statmentContext ctx) {
        System.out.printf("nested statement{".indent(indentPrinter.indentation));
        indentPrinter.increaseIndentation();
    }

    @Override
    public void exitIf_else_statment(DustParser.If_else_statmentContext ctx) {
        indentPrinter.decreaseIndentation();
        System.out.printf("}".indent(indentPrinter.indentation));
    }

    @Override
    public void enterPrint_statment(DustParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(DustParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(DustParser.For_statmentContext ctx) {
        System.out.printf("nested statement{".indent(indentPrinter.indentation));
        indentPrinter.increaseIndentation();
    }

    @Override
    public void exitFor_statment(DustParser.For_statmentContext ctx) {
        indentPrinter.decreaseIndentation();
        System.out.printf("}".indent(indentPrinter.indentation));
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
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }
}
