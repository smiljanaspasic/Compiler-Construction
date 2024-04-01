package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.SemanticAnalyzer;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

private int mainPc;
private Obj lastVisitedMethod;
	public CodeGenerator() {
		
	}
	
	public int getMainPc() {
		return mainPc;
	}
	
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(NonVoidType methodTypeName) {
		methodTypeName.obj.setAdr(Code.pc);
		lastVisitedMethod = methodTypeName.obj;
		// Generisi ulaz
		Code.put(Code.enter);
		Code.put(methodTypeName.obj.getLevel());
		Code.put(methodTypeName.obj.getLocalSymbols().size());
		
	}
	
	public void visit(VoidType voidMethodName) { 
		voidMethodName.obj.setAdr(Code.pc);
		if ("main".equalsIgnoreCase(voidMethodName.getVname())) {
			mainPc = Code.pc;
		}
		lastVisitedMethod = voidMethodName.obj;
		// Generisi ulazz
		Code.put(Code.enter);
		Code.put(voidMethodName.obj.getLevel());
		Code.put(voidMethodName.obj.getLocalSymbols().size());
		
	}
	 
	 public void visit(ExprMinus exprMin) { 
		 Code.put(Code.neg);
	 }
	 
	 public void visit(ExprAddop exprAddop) { 
		 if(exprAddop.getAddop() instanceof AddopMinus) {
			 Code.put(Code.sub);
		 }
		 else { Code.put(Code.add); }
	 }
	 
	 public void visit(TermMulop termMulop) { 
		 if (termMulop.getMulop() instanceof MulopDiv)
				Code.put(Code.div);
			else if (termMulop.getMulop() instanceof MulopMul)
				Code.put(Code.mul);
			else
				Code.put(Code.rem);
	 }
	 
	 public void visit(NumFactor numFactor) {
		 
		 Code.loadConst(numFactor.getValue()); }
	 
	 public void visit(CharFactor charFactor) {
		 Code.loadConst(charFactor.getValue());
	 }
	 
	 public void visit(BoolFactor boolFactor) {
		 Code.loadConst(boolFactor.getValue() ? 1 : 0);
	 }
	 
	 public void visit(DesignatorFactor designatorFactor) {
		 Code.load(designatorFactor.getDesignator().obj);
	 }
	 
	 public void visit(NewFactor newFactor) {
		 Code.put(Code.newarray);
			if (newFactor.getType().struct == Tab.charType) {
				Code.put(0);
			} else {
				Code.put(1);
			}
	 }
	 
	 public void visit(ExprDesignatorStmt exprDesignStmt) { 
		 Code.store(exprDesignStmt.getDesignator().obj);
	 }
	 
	 public void visit(IncDesignatorStmt incDesignStmt) { 
		 Obj obj = incDesignStmt.getDesignator().obj;
			if (obj.getKind() == Obj.Elem) {
				Code.put(Code.dup2);
			}
			Code.load(obj);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(obj);
	 }
	 
	 public void visit(DecDesignatorStmt decDesignStmt) {
		 Obj obj = decDesignStmt.getDesignator().obj;
			if (obj.getKind() == Obj.Elem) {
				Code.put(Code.dup2);
			}
			Code.load(obj);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(obj);
	 }

	 public void visit(ReadStmt readStmt) { 
		 Obj obj = readStmt.getDesignator().obj;
			if (obj.getType() == Tab.charType)
				Code.put(Code.bread);
			else
				Code.put(Code.read);
			Code.store(obj);
	 }
	 
	 public void visit(PrintStmt printStmt) { 
		 
			if (printStmt.getExpr().struct == Tab.charType)
				Code.put(Code.bprint);
			else {
				Code.put(Code.print);
			}
	 }
	 
	 public void visit(NumConstt numConst) {
		 Code.loadConst(numConst.getNumb());
	 }
	 
	 public void visit(NoNumConstt nonumConst) {
		 Code.loadConst(1);
	 }
	 
	 public void visit(DesignatorArray designatorArray) {
		 Code.load(designatorArray.getDesignator().obj);
			
	 }
	
	 public void visit(FindStmt findStmt) {
		 Obj obj = findStmt.getDesignator().obj;
		 Obj arrayObj = findStmt.getDesignator1().obj;
		 Code.put(Code.enter);
			Code.put(1);
			Code.put(2);
			
			// condition
			int whileStart = Code.pc;
			Code.put(Code.load_1);
			Code.load(arrayObj);
			Code.put(Code.arraylength);
			Code.putFalseJump(Code.lt, 0);
			int whileJumpAdr = Code.pc - 2;
			
			// body
			Code.load(arrayObj);
			Code.put(Code.load_1);
			if (arrayObj.getType().getElemType() == Tab.charType)
				Code.put(Code.baload);
			 else 
		        	Code.put(Code.aload);
				Code.put(Code.load_n);
				Code.putFalseJump(Code.eq, 0);
				int ifElseJumpAdr = Code.pc - 2;
				
				// then
				Code.loadConst(1);
				Code.putJump(0);
				int ifThenJumpAdr = Code.pc - 2;
				
				// if end
				Code.fixup(ifElseJumpAdr);
				
				// next
				Code.put(Code.load_1);
				Code.loadConst(1);
				Code.put(Code.add);
				Code.put(Code.store_1);
				
				// pocetak while
				Code.putJump(whileStart);
				
				Code.fixup(whileJumpAdr);
				Code.loadConst(0);
				
				Code.fixup(ifThenJumpAdr);
				
				Code.put(Code.exit);
				Code.store(obj);
	 }
	
	 
}
