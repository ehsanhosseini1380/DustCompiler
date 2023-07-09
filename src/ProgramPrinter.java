import gen.DustListener;
import gen.DustParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.Stack;

public class ProgramPrinter implements DustListener {
    public final Stack<SymbolTable> scopes = new Stack<>();

    @Override
    public void enterProgram(DustParser.ProgramContext ctx) {
        scopes.push(new SymbolTable("program", ctx.start.getLine(), null));
        SymbolTable.root = scopes.peek();
    }

    @Override
    public void exitProgram(DustParser.ProgramContext ctx) {
        scopes.pop();
    }

    @Override
    public void enterImportclass(DustParser.ImportclassContext ctx) {
        String identifier = ctx.CLASSNAME().toString();
        String key = "import_" + identifier;
        if(Boolean.parseBoolean(Utils.checkDataTypeIsDefined(ctx.CLASSNAME().toString()))){
            int line = ctx.start.getLine();
            int column = ctx.CLASSNAME().getSymbol().getCharPositionInLine();
            Utils.reportDuplicateClassError(identifier, line, column);
            key = String.format("%s_%s_%s", identifier, line, column);
        }
        HashMap<String,String> properties=new HashMap<>();
        properties.put("name",ctx.CLASSNAME().toString());
        scopes.peek().insert(key, properties);
    }

    @Override
    public void exitImportclass(DustParser.ImportclassContext ctx) {}

    @Override
    public void enterClassDef(DustParser.ClassDefContext ctx) {
        StringBuilder parents = new StringBuilder();
        if(ctx.CLASSNAME(1) != null){
            for (int i=1;i<ctx.CLASSNAME().size();i++){
                Utils.detectUndeclaredClass(ctx.CLASSNAME(i));
                parents.append(ctx.CLASSNAME(i)).append(",");
            }
            parents.deleteCharAt(parents.length()-1);
        }
        else {
            parents.append("object");
        }

        String identifier = ctx.CLASSNAME(0).toString();
        String key = "class_"+identifier;
        if(Boolean.parseBoolean(Utils.checkDataTypeIsDefined(identifier))){
            int line = ctx.start.getLine();
            int column = ctx.CLASSNAME(0).getSymbol().getCharPositionInLine();
            Utils.reportDuplicateClassError(identifier, line, column);
            key = String.format("%s_%s_%s", identifier, line, column);
        }
        HashMap<String,String> properties=new HashMap<>();
        properties.put("name",identifier);
        properties.put("parents",parents.toString());
        scopes.peek().insert(key, properties);
        SymbolTable newScope = new SymbolTable(identifier, ctx.start.getLine(), scopes.peek());
        scopes.peek().children.add(newScope);
        scopes.push(newScope);
    }

    @Override
    public void exitClassDef(DustParser.ClassDefContext ctx){
        scopes.pop();
    }

    @Override
    public void enterClass_body(DustParser.Class_bodyContext ctx) {}

    @Override
    public void exitClass_body(DustParser.Class_bodyContext ctx) {}

    @Override
    public void enterVarDec(DustParser.VarDecContext ctx) {
        String fieldType;
        String identifier = ctx.ID().toString();
        String dataType;
        if (ctx.CLASSNAME()==null)
            dataType = ctx.TYPE().toString()+", isDefined: True";
        else{
            dataType = "ClassType= "+ctx.CLASSNAME().toString()+", isDefined: "+ Utils.checkDataTypeIsDefined(ctx.CLASSNAME().toString());
            Utils.detectUndeclaredClass(ctx.CLASSNAME());
        }

        switch (ctx.parent.getRuleIndex()) {
            case 3 ->
                    fieldType = "ClassField";
            case 19, 9 ->
                    fieldType = "MethodField";
            default -> {
                return;
            }
        }

        String key = "Field_"+identifier;
        if(!Utils.detectDuplicateDeclaration(identifier, "Field", ctx.start.getLine(), ctx.ID().getSymbol().getCharPositionInLine()+1, scopes)){
//            key = String.format("%s_%d_%d", identifier, ctx.start.getLine(), ctx.ID().getSymbol().getCharPositionInLine()+1);
            HashMap<String,String> properties=new HashMap<>();
            properties.put("fieldType",fieldType);
            properties.put("name",identifier);
            properties.put("type",dataType);
            scopes.peek().insert(key, properties);
        }

    }

    @Override
    public void exitVarDec(DustParser.VarDecContext ctx) {}

    @Override
    public void enterArrayDec(DustParser.ArrayDecContext ctx) {
        String dataType;
        String identifier = ctx.ID().toString();

        switch (ctx.parent.getRuleIndex()) {
            case 3:
                if (ctx.CLASSNAME()==null)
                    dataType = ctx.TYPE().toString()+", isDefined: True";
                else{
                    dataType = ctx.CLASSNAME().toString()+", isDefined: "+ Utils.checkDataTypeIsDefined(ctx.CLASSNAME().toString());
                    Utils.detectUndeclaredClass(ctx.CLASSNAME());
                }
                break;
            default: return;
        }

        String key = "Field_"+identifier;
        if(!Utils.detectDuplicateDeclaration(identifier, "Field", ctx.start.getLine(), ctx.ID().getSymbol().getCharPositionInLine()+1, scopes)){
//            key = String.format("%s_%d_%d", identifier, ctx.start.getLine(), ctx.ID().getSymbol().getCharPositionInLine()+1);
            HashMap<String,String> properties=new HashMap<>();
            properties.put("name",ctx.ID().toString());
            properties.put("type",dataType);
            properties.put("size",ctx.INTEGER().toString());
            scopes.peek().insert(key, properties);
        }

    }

    @Override
    public void exitArrayDec(DustParser.ArrayDecContext ctx) {}

    @Override
    public void enterMethodDec(DustParser.MethodDecContext ctx) {
        SymbolTable newScope = new SymbolTable(ctx.ID().toString(), ctx.start.getLine(), scopes.peek());

        String returnType = "void";
        String identifier = ctx.ID().toString();
        if(ctx.TYPE() != null)
            returnType = ctx.TYPE().toString();
        else if(ctx.CLASSNAME() != null) {
            returnType = "class type= " + ctx.CLASSNAME().toString();
            Utils.detectUndeclaredClass(ctx.CLASSNAME());
        }

        StringBuilder parameterList = new StringBuilder();
        HashMap<String,String> currentProperties=new HashMap<>();

        if(ctx.parameter().size() != 0){
            int index = 0;
            for (var entry: ctx.parameter(0).varDec()){
                index++;
                String dataType;
                String fullDataType;
                if(entry.CLASSNAME() == null) {
                    fullDataType = entry.TYPE().toString()+", isDefined: True)";
                    currentProperties.put(String.format("type%s",index),entry.TYPE().toString());
                }
                else {
                    dataType = entry.CLASSNAME().toString();
                    fullDataType = String.format("classType= %s, isDefined= %s)", dataType, Utils.checkDataTypeIsDefined(entry.CLASSNAME().toString()));
                    currentProperties.put(String.format("type%s",index),entry.CLASSNAME().toString());
                }
                HashMap<String,String> newScopeProperties=new HashMap<>();
                newScopeProperties.put("name", entry.ID().toString());
                newScopeProperties.put("type",fullDataType);
                newScopeProperties.put("index",String.valueOf(index));
                newScope.insert("Field_"+entry.ID(), newScopeProperties);
                parameterList.append(String.format("[(type: %s, (index: %d)],", fullDataType, index));
            }
            parameterList.deleteCharAt(parameterList.length()-1).append(']');
        }

        String key = "Method_"+identifier;
        if(!Utils.detectDuplicateDeclaration(identifier, "Method", ctx.start.getLine(), ctx.ID().getSymbol().getCharPositionInLine()+1, scopes)){
                currentProperties.put("name", ctx.ID().toString());
                currentProperties.put("return type",returnType);
                currentProperties.put("parameters",parameterList.toString());
                currentProperties.put("paramCount",String.valueOf(parameterList.toString().split("\\[").length-1));
                scopes.peek().insert(key, currentProperties);
        }
        scopes.peek().children.add(newScope);
        scopes.push(newScope);

    }

    @Override
    public void exitMethodDec(DustParser.MethodDecContext ctx) {
        scopes.pop();
    }

    @Override
    public void enterConstructor(DustParser.ConstructorContext ctx) {
        SymbolTable newScope = new SymbolTable(ctx.CLASSNAME().toString(), ctx.start.getLine(), scopes.peek());

        String identifier = ctx.CLASSNAME().toString();
        StringBuilder parameterList = new StringBuilder();
        if(ctx.parameter().size() != 0){
            int index = 0;
            parameterList.append("[parameter list: ");
            for (var entry: ctx.parameter(0).varDec()){
                index++;
                String dataType;
                String fullDataType;
                if(entry.CLASSNAME() == null) {
                    dataType = fullDataType = entry.TYPE().toString()+", isDefined: True";
                }
                else {
                    dataType = entry.CLASSNAME().toString();
                    fullDataType = String.format("[classType= %s, isDefined= %s]", dataType, Utils.checkDataTypeIsDefined(entry.CLASSNAME().toString()));
                }
                HashMap<String,String> properties=new HashMap<>();
                properties.put("name", entry.ID().toString());
                properties.put("type",fullDataType);
                properties.put("index",String.valueOf(index));
                newScope.insert("Field_"+entry.ID(), properties);
                parameterList.append(String.format("[type: %s, index: %d],", dataType, index));
            }
            parameterList.deleteCharAt(parameterList.length()-1).append(']');
        }

        String key = "Constructor_"+identifier;
        if(Utils.detectDuplicateDeclaration(identifier, "Constructor", ctx.start.getLine(), ctx.CLASSNAME().getSymbol().getCharPositionInLine()+1, scopes)){
            key = String.format("%s_%d_%d", identifier, ctx.start.getLine(), ctx.CLASSNAME().getSymbol().getCharPositionInLine()+1);
        }
        HashMap<String,String> properties=new HashMap<>();
        properties.put("name", ctx.CLASSNAME().toString());
        properties.put("parameters",parameterList.toString());
        scopes.peek().insert(key, properties);
        scopes.peek().children.add(newScope);
        scopes.push(newScope);
        int line = ctx.start.getLine();
        int column = ctx.CLASSNAME().getSymbol().getCharPositionInLine();
        Utils.detectConstructorError(scopes.peek().parent.name, ctx.CLASSNAME().toString(), line, column);
    }

    @Override
    public void exitConstructor(DustParser.ConstructorContext ctx) {
        scopes.pop();
    }

    @Override
    public void enterParameter(DustParser.ParameterContext ctx) {
    }

    @Override
    public void exitParameter(DustParser.ParameterContext ctx) {}

    @Override
    public void enterStatement(DustParser.StatementContext ctx) {}

    @Override
    public void exitStatement(DustParser.StatementContext ctx) {}

    @Override
    public void enterReturn_statment(DustParser.Return_statmentContext ctx) {}

    @Override
    public void exitReturn_statment(DustParser.Return_statmentContext ctx) {}

    @Override
    public void enterCondition_list(DustParser.Condition_listContext ctx) {}

    @Override
    public void exitCondition_list(DustParser.Condition_listContext ctx) {}

    @Override
    public void enterCondition(DustParser.ConditionContext ctx) {}

    @Override
    public void exitCondition(DustParser.ConditionContext ctx) {}

    @Override
    public void enterIf_statment(DustParser.If_statmentContext ctx) {
        SymbolTable newScope = new SymbolTable("if", ctx.start.getLine(), scopes.peek());
        scopes.peek().children.add(newScope);
        scopes.push(newScope);
    }

    @Override
    public void exitIf_statment(DustParser.If_statmentContext ctx) {
        scopes.pop();
    }

    @Override
    public void enterWhile_statment(DustParser.While_statmentContext ctx) {
        SymbolTable newScope = new SymbolTable("while", ctx.start.getLine(), scopes.peek());
        scopes.peek().children.add(newScope);
        scopes.push(newScope);
    }

    @Override
    public void exitWhile_statment(DustParser.While_statmentContext ctx) {
        scopes.pop();
    }

    @Override
    public void enterIf_else_statment(DustParser.If_else_statmentContext ctx) {
        SymbolTable newScope = new SymbolTable("if-else", ctx.start.getLine(), scopes.peek());
        scopes.peek().children.add(newScope);
        scopes.push(newScope);
    }

    @Override
    public void exitIf_else_statment(DustParser.If_else_statmentContext ctx) {
        scopes.pop();
    }

    @Override
    public void enterPrint_statment(DustParser.Print_statmentContext ctx) {}

    @Override
    public void exitPrint_statment(DustParser.Print_statmentContext ctx) {}

    @Override
    public void enterFor_statment(DustParser.For_statmentContext ctx) {
        for (TerminalNode entry: ctx.ID())
            Utils.detectUndeclaredVariable(entry, scopes);

        SymbolTable newScope = new SymbolTable("for", ctx.start.getLine(), scopes.peek());
        scopes.peek().children.add(newScope);
        scopes.push(newScope);
    }

    @Override
    public void exitFor_statment(DustParser.For_statmentContext ctx) {
        scopes.pop();
    }

    @Override
    public void enterMethod_call(DustParser.Method_callContext ctx) {
    }

    @Override
    public void exitMethod_call(DustParser.Method_callContext ctx) {}

    @Override
    public void enterAssignment(DustParser.AssignmentContext ctx) {}

    @Override
    public void exitAssignment(DustParser.AssignmentContext ctx) {}

    @Override
    public void enterExp(DustParser.ExpContext ctx) {
        if(ctx.ID()!=null)
            Utils.detectUndeclaredVariable(ctx.ID(), scopes);
    }

    @Override
    public void exitExp(DustParser.ExpContext ctx) {}

    @Override
    public void enterPrefixexp(DustParser.PrefixexpContext ctx) {
        if(ctx.ID()!=null)
            Utils.detectUndeclaredVariable(ctx.ID(), scopes);
        if(ctx.INTEGER() != null){
            int line = ctx.start.getLine();
            int column = ctx.INTEGER().getSymbol().getCharPositionInLine();
            Utils.outOfRange(ctx.prefixexp().getText(), ctx.INTEGER(), line, column, scopes);
        }
    }

    @Override
    public void exitPrefixexp(DustParser.PrefixexpContext ctx) {}

    @Override
    public void enterArgs(DustParser.ArgsContext ctx) {}

    @Override
    public void exitArgs(DustParser.ArgsContext ctx) {}

    @Override
    public void enterExplist(DustParser.ExplistContext ctx) {
//        for (int i=0; i < ctx.exp().size(); i++)
//            System.out.println(ctx.exp(i).getText());
        Utils.deepLookup(ctx.getText(), scopes);
    }

    @Override
    public void exitExplist(DustParser.ExplistContext ctx) {}

    @Override
    public void enterArithmetic_operator(DustParser.Arithmetic_operatorContext ctx) {}

    @Override
    public void exitArithmetic_operator(DustParser.Arithmetic_operatorContext ctx) {}

    @Override
    public void enterRelational_operators(DustParser.Relational_operatorsContext ctx) {}

    @Override
    public void exitRelational_operators(DustParser.Relational_operatorsContext ctx) {}

    @Override
    public void enterAssignment_operators(DustParser.Assignment_operatorsContext ctx) {}

    @Override
    public void exitAssignment_operators(DustParser.Assignment_operatorsContext ctx) {}

    @Override
    public void visitTerminal(TerminalNode terminalNode) {}

    @Override
    public void visitErrorNode(ErrorNode errorNode) {}

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {}

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {}
}