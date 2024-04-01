// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class NamespaceName implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String nameN;

    public NamespaceName (String nameN) {
        this.nameN=nameN;
    }

    public String getNameN() {
        return nameN;
    }

    public void setNameN(String nameN) {
        this.nameN=nameN;
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
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NamespaceName(\n");

        buffer.append(" "+tab+nameN);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NamespaceName]");
        return buffer.toString();
    }
}