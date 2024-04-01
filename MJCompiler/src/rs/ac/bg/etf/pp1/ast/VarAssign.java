// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class VarAssign implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String vname;
    private SqrBrackets SqrBrackets;

    public VarAssign (String vname, SqrBrackets SqrBrackets) {
        this.vname=vname;
        this.SqrBrackets=SqrBrackets;
        if(SqrBrackets!=null) SqrBrackets.setParent(this);
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname=vname;
    }

    public SqrBrackets getSqrBrackets() {
        return SqrBrackets;
    }

    public void setSqrBrackets(SqrBrackets SqrBrackets) {
        this.SqrBrackets=SqrBrackets;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SqrBrackets!=null) SqrBrackets.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SqrBrackets!=null) SqrBrackets.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SqrBrackets!=null) SqrBrackets.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarAssign(\n");

        buffer.append(" "+tab+vname);
        buffer.append("\n");

        if(SqrBrackets!=null)
            buffer.append(SqrBrackets.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarAssign]");
        return buffer.toString();
    }
}
