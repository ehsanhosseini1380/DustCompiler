package gen;// Generated from E:/Codes/DustCompiler/grammar\Dust.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DustParser}.
 */
public interface DustListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DustParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DustParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DustParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#importclass}.
	 * @param ctx the parse tree
	 */
	void enterImportclass(DustParser.ImportclassContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#importclass}.
	 * @param ctx the parse tree
	 */
	void exitImportclass(DustParser.ImportclassContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(DustParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(DustParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body(DustParser.Class_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body(DustParser.Class_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(DustParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(DustParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#arrayDec}.
	 * @param ctx the parse tree
	 */
	void enterArrayDec(DustParser.ArrayDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#arrayDec}.
	 * @param ctx the parse tree
	 */
	void exitArrayDec(DustParser.ArrayDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#methodDec}.
	 * @param ctx the parse tree
	 */
	void enterMethodDec(DustParser.MethodDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#methodDec}.
	 * @param ctx the parse tree
	 */
	void exitMethodDec(DustParser.MethodDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#constructor}.
	 * @param ctx the parse tree
	 */
	void enterConstructor(DustParser.ConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#constructor}.
	 * @param ctx the parse tree
	 */
	void exitConstructor(DustParser.ConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(DustParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(DustParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(DustParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(DustParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#return_statment}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statment(DustParser.Return_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#return_statment}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statment(DustParser.Return_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void enterCondition_list(DustParser.Condition_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void exitCondition_list(DustParser.Condition_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(DustParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(DustParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#if_statment}.
	 * @param ctx the parse tree
	 */
	void enterIf_statment(DustParser.If_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#if_statment}.
	 * @param ctx the parse tree
	 */
	void exitIf_statment(DustParser.If_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#while_statment}.
	 * @param ctx the parse tree
	 */
	void enterWhile_statment(DustParser.While_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#while_statment}.
	 * @param ctx the parse tree
	 */
	void exitWhile_statment(DustParser.While_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#if_else_statment}.
	 * @param ctx the parse tree
	 */
	void enterIf_else_statment(DustParser.If_else_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#if_else_statment}.
	 * @param ctx the parse tree
	 */
	void exitIf_else_statment(DustParser.If_else_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#print_statment}.
	 * @param ctx the parse tree
	 */
	void enterPrint_statment(DustParser.Print_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#print_statment}.
	 * @param ctx the parse tree
	 */
	void exitPrint_statment(DustParser.Print_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#for_statment}.
	 * @param ctx the parse tree
	 */
	void enterFor_statment(DustParser.For_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#for_statment}.
	 * @param ctx the parse tree
	 */
	void exitFor_statment(DustParser.For_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterMethod_call(DustParser.Method_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitMethod_call(DustParser.Method_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(DustParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(DustParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(DustParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(DustParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void enterPrefixexp(DustParser.PrefixexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void exitPrefixexp(DustParser.PrefixexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(DustParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(DustParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#explist}.
	 * @param ctx the parse tree
	 */
	void enterExplist(DustParser.ExplistContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#explist}.
	 * @param ctx the parse tree
	 */
	void exitExplist(DustParser.ExplistContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_operator(DustParser.Arithmetic_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_operator(DustParser.Arithmetic_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#relational_operators}.
	 * @param ctx the parse tree
	 */
	void enterRelational_operators(DustParser.Relational_operatorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#relational_operators}.
	 * @param ctx the parse tree
	 */
	void exitRelational_operators(DustParser.Relational_operatorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DustParser#assignment_operators}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_operators(DustParser.Assignment_operatorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DustParser#assignment_operators}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_operators(DustParser.Assignment_operatorsContext ctx);
}