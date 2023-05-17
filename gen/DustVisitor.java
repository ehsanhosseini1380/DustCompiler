// Generated from E:/Codes/DustCompiler/grammar\Dust.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DustParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DustVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DustParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(DustParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#importclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportclass(DustParser.ImportclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(DustParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body(DustParser.Class_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(DustParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#arrayDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDec(DustParser.ArrayDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#methodDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDec(DustParser.MethodDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor(DustParser.ConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(DustParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(DustParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#return_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statment(DustParser.Return_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#condition_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_list(DustParser.Condition_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(DustParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#if_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statment(DustParser.If_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#while_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_statment(DustParser.While_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#if_else_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_else_statment(DustParser.If_else_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#print_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_statment(DustParser.Print_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#for_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_statment(DustParser.For_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#method_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_call(DustParser.Method_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(DustParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(DustParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#prefixexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixexp(DustParser.PrefixexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(DustParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#explist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplist(DustParser.ExplistContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_operator(DustParser.Arithmetic_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#relational_operators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_operators(DustParser.Relational_operatorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DustParser#assignment_operators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_operators(DustParser.Assignment_operatorsContext ctx);
}