// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class RegularType extends Type {

    private String nameT;

    public RegularType (String nameT) {
        this.nameT=nameT;
    }

    public String getNameT() {
        return nameT;
    }

    public void setNameT(String nameT) {
        this.nameT=nameT;
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
        buffer.append("RegularType(\n");

        buffer.append(" "+tab+nameT);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RegularType]");
        return buffer.toString();
    }
}
