package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class RuleVisitor extends VisitorAdaptor{

	int constDeclCount = 0;
	int varDeclCount = 0;
	
	Logger log = Logger.getLogger(getClass());

	public void visit(VarAssign vardecl){
		varDeclCount++;
	}
	
   public void visit(ConstAssign constdecl) {
		constDeclCount++;
	}

}
