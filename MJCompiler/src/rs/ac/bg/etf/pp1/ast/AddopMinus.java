// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class AddopMinus extends Addop {

    public AddopMinus () {
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
        buffer.append("AddopMinus(\n");

        buffer.append(tab);
        buffer.append(") [AddopMinus]");
        return buffer.toString();
    }
}