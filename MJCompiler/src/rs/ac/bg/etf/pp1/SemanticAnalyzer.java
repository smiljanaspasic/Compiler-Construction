package rs.ac.bg.etf.pp1;
import org.apache.log4j.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.util.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;
import java.util.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	int globalVarDeclCount = 0;
	int localVarDeclCount = 0;
	int constDeclCount = 0;
	Obj currentMethod = null;
	int formalParamCount = 0;
	boolean returnFound = false;
	boolean errorDetected = false;
	int nVars;
	String currentNamespaceName = null;
	HashSet<String> namespaces = new HashSet<String>();
	Struct lastVisitedType = null;
	private boolean isArray=false;
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public static final Struct boolType = new Struct(Struct.Bool);


	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	
	public void visit(Program program) {
		Obj mainMeth = Tab.currentScope.findSymbol("main");
		if (mainMeth != null) {
			if (mainMeth.getKind() != Obj.Meth) {
				report_error("Main nije metoda.", program);
			} else {
				if (mainMeth.getType() != Tab.noType) {
					report_error("Main metoda nije tipa void.", program);
				}
				if (mainMeth.getLevel() != 0) {
					report_error("Main metoda ima argumente.", program);
				}
			}
		} else {
			report_error("U programu ne postoji main simbol.", program);
		}

		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	public void visit(NamespaceName namespaceIme) {
		currentNamespaceName = namespaceIme.getNameN();
		namespaces.add(currentNamespaceName);
	}
	
	public void visit(Namespace namespace) {
		currentNamespaceName = "";
	}
	

	public void visit(ProgName progName){
	    	progName.obj = Tab.insert(Obj.Prog, progName.getNameP(), Tab.noType);
	    	Tab.openScope();
	    }
	

	
	public void visit(RegularType regularType) {
		Obj typeNode = Tab.find(regularType.getNameT());
    	if(typeNode == Tab.noObj){
    		report_error("Nije pronadjen tip " + regularType.getNameT() + " u tabeli simbola! ", null);
    		regularType.struct = Tab.noType;
    	
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			lastVisitedType=typeNode.getType();
    			regularType.struct = lastVisitedType;
    			
    		}else{
    			report_error("Greska: Ime " + regularType.getNameT() + " ne predstavlja tip!", regularType);
    			regularType.struct = Tab.noType;
    			
    		}
    	}
    	
    }
	
	
	
	 public void visit(MethodDecl methodDecl) {
		 	
			Tab.chainLocalSymbols(currentMethod);

			Tab.closeScope();

			int nPars = 0;
			for (Obj obj : currentMethod.getLocalSymbols()) {
				if (obj.getFpPos() > 0)
					nPars++;
			}
			currentMethod.setLevel(nPars);

			currentMethod = null;
		}

		public void visit(NonVoidType methodTypeName) {
			if (currentNamespaceName != "") {
				String methName = currentNamespaceName + "::" +  methodTypeName.getTname();
				if (Tab.currentScope.findSymbol(methName) == null) {
					currentMethod = Tab.insert(Obj.Meth, methName, lastVisitedType);
					methodTypeName.obj = currentMethod;
					report_info("Nadjena funkcija " + methName, methodTypeName);
				} else {
					currentMethod = Tab.noObj;
					report_error("Simbol sa imenom " + methName + " vec postoji u trenutnom opsegu",
							methodTypeName);
				}
				Tab.openScope();
			}
			else {
			if (Tab.currentScope.findSymbol(methodTypeName.getTname()) == null) {
				currentMethod = Tab.insert(Obj.Meth, methodTypeName.getTname(), lastVisitedType);
				methodTypeName.obj = currentMethod;
				report_info("Nadjena funkcija " + methodTypeName.getTname(), methodTypeName);
			} else {
				currentMethod = Tab.noObj;
				report_error("Simbol sa imenom " + methodTypeName.getTname() + " vec postoji u trenutnom opsegu",
						methodTypeName);
			}
			Tab.openScope();
			}
		}
		
		public void visit(VoidType voidMethodName) {
			if (currentNamespaceName != "") {
				String methName = currentNamespaceName + "::" +  voidMethodName.getVname();
				if (Tab.currentScope.findSymbol(methName) == null) {
					currentMethod = Tab.insert(Obj.Meth, methName, Tab.noType);
					voidMethodName.obj = currentMethod;
					report_info("Nadjena funkcija " + methName, voidMethodName);
				} else {
					currentMethod = Tab.noObj;
					report_error("Simbol sa imenom " + methName + " vec postoji u trenutnom opsegu",
							voidMethodName);
				}
				Tab.openScope();
			}
			else {
			if (Tab.currentScope.findSymbol(voidMethodName.getVname()) == null) {
				currentMethod = Tab.insert(Obj.Meth, voidMethodName.getVname(), Tab.noType);
				voidMethodName.obj = currentMethod;
				report_info("Nadjena funkcija " + voidMethodName.getVname(), voidMethodName);
			} else {
				currentMethod = Tab.noObj;
				report_error("Simbol sa imenom " + voidMethodName.getVname() + " vec postoji u trenutnom opsegu",
						voidMethodName);
			}
			Tab.openScope();
			}
		}
		
	 public void visit(VarAssign varAssign) {
		 String varName = varAssign.getVname();
			if (currentNamespaceName != "") {
				varName = currentNamespaceName + "::" + varName;
				if (Tab.currentScope.findSymbol(varAssign.getVname()) == null) {
					if (isArray) {
						varAssign.obj = Tab.insert(Obj.Var, varName, new Struct(Struct.Array, lastVisitedType));
						isArray = false;
					if(varAssign.obj.getLevel() == 0) {
					report_info("Nadjena globalna varijabla " + varName, varAssign);
					globalVarDeclCount++; }
					else {
						report_info("Nadjena lokalna varijabla " + varName, varAssign);
						localVarDeclCount++;
					}
					}
				
					else {
						varAssign.obj = Tab.insert(Obj.Var, varName, lastVisitedType);
						if(varAssign.obj.getLevel() == 0) {
							report_info("Nadjena globalna varijabla " + varName, varAssign);
							globalVarDeclCount++; }
							else {
								report_info("Nadjena lokalna varijabla " + varName, varAssign);
								localVarDeclCount++;
							}
						}
					}
					else {
					report_error("Simbol sa imenom " + varName + " vec postoji u trenutnom opsegu", varAssign);
				}
			}
			else {
			if (Tab.currentScope.findSymbol(varAssign.getVname()) == null) {
				if (isArray) {
					varAssign.obj = Tab.insert(Obj.Var, varAssign.getVname(), new Struct(Struct.Array, lastVisitedType));
					isArray = false;
				if(varAssign.obj.getLevel() == 0) {
				report_info("Nadjena globalna varijabla " + varAssign.getVname(), varAssign);
				globalVarDeclCount++; }
				else {
					report_info("Nadjena lokalna varijabla " + varAssign.getVname(), varAssign);
					localVarDeclCount++;
				}
				}
			
				else {
					varAssign.obj = Tab.insert(Obj.Var, varAssign.getVname(), lastVisitedType);
					if(varAssign.obj.getLevel() == 0) {
						report_info("Nadjena globalna varijabla " + varAssign.getVname(), varAssign);
						globalVarDeclCount++; }
						else {
							report_info("Nadjena lokalna varijabla " + varAssign.getVname(), varAssign);
							localVarDeclCount++;
						}
					}
				}
				else {
				report_error("Simbol sa imenom " + varAssign.getVname() + " vec postoji u trenutnom opsegu", varAssign);
			}
			}
		}
	 public void visit(SqrBrackets sqrBrackets) {
		 isArray=true;
	 }
	 public void visit(FormAssign formAssign) {  
		 Struct type=formAssign.getType().struct;
		 if(isArray) 
			 type = new Struct(Struct.Array, type);		 
		 Tab.insert(Obj.Var, formAssign.getFname(),type);
		 report_info("Nadjena lokalna varijabla " + formAssign.getFname(), formAssign);
		 formalParamCount++;
		 isArray=false;
		}
	 
	 public void visit(NumConstDecl numConst) {
		 String constName = numConst.getName();
			if (currentNamespaceName != "") {
				constName = currentNamespaceName + "::" + constName;
				if (Tab.currentScope.findSymbol(numConst.getName()) == null) {
					Tab.insert(Obj.Con, constName, lastVisitedType).setAdr(numConst.getVal());
					constDeclCount++;
					report_info("Nadjena numericka konstanta " + constName, numConst);
				} else {
					report_error("Simbol sa imenom " + constName + " vec postoji u trenutnom opsegu",
							numConst);
				}
				if (lastVisitedType != Tab.intType) {
					report_error("Nekopatibilni tipovi u dodeli konstanti", numConst);
			 	}
			}
			else {
			if (Tab.currentScope.findSymbol(numConst.getName()) == null) {
				Tab.insert(Obj.Con, numConst.getName(), lastVisitedType).setAdr(numConst.getVal());
				constDeclCount++;
				report_info("Nadjena numericka konstanta " + numConst.getName(), numConst);
			} else {
				report_error("Simbol sa imenom " + numConst.getName() + " vec postoji u trenutnom opsegu",
						numConst);
			}
			if (lastVisitedType != Tab.intType) {
				report_error("Nekopatibilni tipovi u dodeli konstanti", numConst);
		 	}
			}
		}
	 
	 public void visit(BoolConstDecl boolDecl) {
		 String constName = boolDecl.getName();
			if (currentNamespaceName != "") {
				constName = currentNamespaceName + "::" + constName;
				if (Tab.currentScope.findSymbol(boolDecl.getName()) == null) {
					Tab.insert(Obj.Con, constName, lastVisitedType).setAdr(boolDecl.getVal() ? 1 : 0);
					constDeclCount++;
					report_info("Nadjena logicka(bool) konstanta " + constName, boolDecl);
				} else {
					report_error("Simbol sa imenom " + constName + " vec postoji u trenutnom opsegu",
							boolDecl);
				}

				if (lastVisitedType != boolType) {
					report_error("Nekopatibilni tipovi u dodeli konstanti", boolDecl);
				}
			}
			else {
			if (Tab.currentScope.findSymbol(boolDecl.getName()) == null) {
				Tab.insert(Obj.Con, boolDecl.getName(), lastVisitedType).setAdr(boolDecl.getVal() ? 1 : 0);
				constDeclCount++;
				report_info("Nadjena logicka(bool) konstanta " + boolDecl.getName(), boolDecl);
			} else {
				report_error("Simbol sa imenom " + boolDecl.getName() + " vec postoji u trenutnom opsegu",
						boolDecl);
			}

			if (lastVisitedType != boolType) {
				report_error("Nekopatibilni tipovi u dodeli konstanti", boolDecl);
			}
			}
		}
	 
	 public void visit(CharConstDecl charDecl) {
		 String constName = charDecl.getName();
			if (currentNamespaceName != "") {
				constName = currentNamespaceName + "::" + constName;
				if (Tab.currentScope.findSymbol(charDecl.getName()) == null) {
					Tab.insert(Obj.Con, constName, lastVisitedType).setAdr(charDecl.getVal());
					constDeclCount++;
					report_info("Nadjena karakterna(char) konstanta " + constName, charDecl);
				} else {
					report_error("Simbol sa imenom " + constName + " vec postoji u trenutnom opsegu",
							charDecl);
				}
				if (lastVisitedType != Tab.charType) {
					report_error("Nekopatibilni tipovi u dodeli konstanti", charDecl);
				}
			}
			else {
			if (Tab.currentScope.findSymbol(charDecl.getName()) == null) {
				Tab.insert(Obj.Con, charDecl.getName(), lastVisitedType).setAdr(charDecl.getVal());
				constDeclCount++;
				report_info("Nadjena karakterna(char) konstanta " + charDecl.getName(), charDecl);
			} else {
				report_error("Simbol sa imenom " + charDecl.getName() + " vec postoji u trenutnom opsegu",
						charDecl);
			}
			if (lastVisitedType != Tab.charType) {
				report_error("Nekopatibilni tipovi u dodeli konstanti", charDecl);
			}
			}
		}
	 
	 public void visit(DesignatorNoExprReg designator){
	    	Obj obj = Tab.find(designator.getName());
	    	if(obj == Tab.noObj){
				report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije deklarisano! ", null);
						
	    	}
	    	designator.obj = obj;
	    	if (designator.obj.getKind() == Obj.Con)
				report_info("Pristup konstanti " + designator.obj.getName(), designator);
			else if (designator.obj.getKind() == Obj.Var)
				report_info("Pristup promenljivoj " + designator.obj.getName(), designator);
	    	
	    	}
	 
	 public void visit(DesignatorNoExprNmSpc designator){
		 if (!namespaces.contains(designator.getNamespaceT())) {
				report_error("Error. " + designator.getNamespaceT() + " is not a namespace", designator);
			}
			String designatorName = designator.getNamespaceT() + "::" + designator.getNameType();
		 	Obj obj = Tab.find(designatorName);
	    	if(obj == Tab.noObj){
				report_error("Greska na liniji " + designator.getLine()+ " : ime "+ designatorName +" nije deklarisano! ", null);
						
	    	}
	    	designator.obj = obj;
	    	if (designator.obj.getKind() == Obj.Con)
				report_info("Pristup konstanti " + designator.obj.getName(), designator);
			else if (designator.obj.getKind() == Obj.Var)
				report_info("Pristup promenljivoj " + designator.obj.getName(), designator);
	    	
	    	
	 }
	 
	 public void visit(DesignatorDecl designator) {
		 Obj obj = Tab.find(designator.getDesignatorArray().getDesignator().obj.getName());
	    	if(obj == Tab.noObj){
				report_error("Greska na liniji " + designator.getLine()+ " : ime "+ designator.getDesignatorArray().getDesignator().obj.getName() +" nije deklarisano! ", null);
						
	    	}
	    	else {
			designator.obj = new Obj(Obj.Elem, "elem", obj.getType().getElemType());
			boolean b = false;
			if (obj.getType().getKind() != Struct.Array) {
				b = true;
				report_error("Pristupa se indeksiranje designator-u koji nije niz.", designator);
			}
			if (designator.getExpr().struct != Tab.intType) {
				b = true;
				report_error("Index niza nije tipa int.", designator);
			}
			if(!b) {
				
				report_info("Detektovano pristup elementu niza ", designator);

			}
	    	}
	 }
	 
	 public void visit(ExprMinus exprMin) {
		 Struct t=exprMin.getTerm().struct;
		 if (!t.equals(Tab.intType)) {
			 	exprMin.struct = Tab.noType;
				report_error("Greska na liniji " + exprMin.getLine() +"tip term nije int! ", null);
				
			} else {			
				exprMin.struct = t;
			}
	 }
	 
	 public void visit(ExprSingleTerm exprSingleTerm) {
		exprSingleTerm.struct=exprSingleTerm.getTerm().struct;
	 }
	 
	 public void visit(ExprAddop exprAddop) {
		 Struct t=exprAddop.getTerm().struct;
		 Struct t1=exprAddop.getExpr().struct;
		 if (t.equals(t1) && t == Tab.intType)
			 exprAddop.struct = t1;
			else {
				exprAddop.struct = Tab.noType;
				report_error("Term i Expr nisu kompatabilni tipovi", exprAddop);
	 }
	 }
	 public void visit(TermSingleFactor termSingleFactor) {
		 termSingleFactor.struct=termSingleFactor.getFactor().struct;
	 }
	 
	 public void visit(TermMulop termMulop) {
		 Struct t=termMulop.getTerm().struct;
		 Struct t1=termMulop.getFactor().struct;
		 if (!t.equals(Tab.intType)  || !t1.equals(Tab.intType)) {
				report_error("Greska na liniji " + termMulop.getLine() +"tip term ili expr nije int! ", null);
				
			}
		 else {	
			 if(!t1.equals(t)) {
				 report_error("Greska na liniji " + termMulop.getLine() +"tip term i expr nisu kompatabilni! ", null);
			 }
			 else {	termMulop.struct = t;
			}
	 }
	 }
	 
	 public void visit(NumFactor numFactor) {
		 numFactor.struct=Tab.intType;
	 }
	 
	 public void visit(CharFactor charFactor) {
		 charFactor.struct=Tab.charType;
	 }
	 
	 public void visit(BoolFactor boolFactor) {
		 boolFactor.struct=boolType;
	 }
	 
	 public void visit(DesignatorFactor designatorFactor) {
		 designatorFactor.struct=designatorFactor.getDesignator().obj.getType();
	 }
	 
	 public void visit(ExprFactor exprFactor) {
		 exprFactor.struct = exprFactor.getExpr().struct;
	 }
	 
	 public void visit(NewFactor newFactor) {
		 newFactor.struct = new Struct(Struct.Array, newFactor.getType().struct);
			if (newFactor.getExpr().struct != Tab.intType) {
				report_error("Dimenzija niza nije tipa int.", newFactor);
			}
	 }
	 
	 public void visit(ExprDesignatorStmt exprDesignStmt) {
		 int k = exprDesignStmt.getDesignator().obj.getKind();
		 Struct t = exprDesignStmt.getExpr().struct;
		 Struct t1 = exprDesignStmt.getDesignator().obj.getType().getElemType();
			if (k != Obj.Var && k != Obj.Elem) {
				report_error("Greska na liniji " + exprDesignStmt.getLine() + " : " +
						"Designator kome se dodeljuje vrednost nije promenljiva ni element niza",
						exprDesignStmt);
				
			}
			
			if (t.getElemType()!=t1) {
				report_error("Tip Expr-a nije kopatibilan pri dodeli sa tipom Designator-a",exprDesignStmt);
			}
		
	 }
	 
	 public void visit(IncDesignatorStmt incDesignStmt) {
		 if(incDesignStmt.getDesignator().obj.getType()!=Tab.intType) {
			 report_error("Greska na liniji " + incDesignStmt.getLine() + " : " + "nekompatibilni tipovi,ne moze se inkrementirati nesto sto nije int ", null);
		 }
	 }
	 
	public void visit(DecDesignatorStmt decDesignStmt) {
			 if(decDesignStmt.getDesignator().obj.getType()!=Tab.intType) {
				 report_error("Greska na liniji " + decDesignStmt.getLine() + " : " + "nekompatibilni tipovi,ne moze se dekrementirati nesto sto nije int ", null);
			 }
	 }
	
	public void visit(ReadStmt readStmt) {
		Struct t=readStmt.getDesignator().obj.getType();
		if(t!=boolType && t!= Tab.intType && t!=Tab.charType) {
			report_error("Greska na liniji " + readStmt.getLine() + " : " + " designator u read statementu nije tipa int,char ili bool",null) ;
			
		}
		int k=readStmt.getDesignator().obj.getKind();
		if(k!=Obj.Var && k!=Obj.Elem && k!=Obj.Fld) {
			report_error("Greska na liniji " + readStmt.getLine() + " : " + " designator u read statementu nije promenljiva,element niza ni polje unutar objekta",null) ;
		}
		
	}
	
	public void visit(PrintStmt printStmt) {
		Struct t=printStmt.getExpr().struct;
		if(t!=boolType && t!=Tab.intType && t!=Tab.charType) {
			report_error("Greska na liniji" + printStmt.getLine() + " : " + " Expr u print statementu nije tipa int,char ili bool ",null);
		}
	}
	
	public void visit(FindStmt findStmt) {
		Struct t=findStmt.getDesignator().obj.getType();
		int k=findStmt.getDesignator().obj.getKind();
		Struct t1=findStmt.getDesignator1().obj.getType().getElemType();
		int k1=findStmt.getDesignator1().obj.getKind();
		Struct t2=findStmt.getExpr().struct;

		if(t!=boolType || k!=Obj.Var ) {
			report_error("Greska na liniji " + findStmt.getLine() + " : " + " designator sa leve strane jednakosti nije promenljiva tipa bool ",null);
		}
		
		else if( t1!=Tab.intType && t1!=Tab.charType && t1!=boolType) {
			report_error("Greska na liniji " + findStmt.getLine() + " : "  + " designator sa desne strane jednakosti mora oznacavati jednodimenzionalni niz ugradjenog tipa ",null);
		}
		
		else if(!t2.assignableTo(t1)) {
			report_error("Greska na liniji " + findStmt.getLine() + " : " + " tip elementa niza je nekompatibilan sa Expr tipom ",null);
			
		}
	}
	
	  public boolean passed(){
	    	return !errorDetected;
	    }


	    
}
