// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class FormAssign implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private Type Type;
    private String fname;
    private SqrBrackets SqrBrackets;

    public FormAssign (Type Type, String fname, SqrBrackets SqrBrackets) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.fname=fname;
        this.SqrBrackets=SqrBrackets;
        if(SqrBrackets!=null) SqrBrackets.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname=fname;
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
        if(Type!=null) Type.accept(visitor);
        if(SqrBrackets!=null) SqrBrackets.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SqrBrackets!=null) SqrBrackets.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SqrBrackets!=null) SqrBrackets.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormAssign(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+fname);
        buffer.append("\n");

        if(SqrBrackets!=null)
            buffer.append(SqrBrackets.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormAssign]");
        return buffer.toString();
    }
}
