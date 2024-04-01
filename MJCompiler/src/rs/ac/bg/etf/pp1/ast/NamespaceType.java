// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class NamespaceType extends Type {

    private String nameT;
    private String nameType;

    public NamespaceType (String nameT, String nameType) {
        this.nameT=nameT;
        this.nameType=nameType;
    }

    public String getNameT() {
        return nameT;
    }

    public void setNameT(String nameT) {
        this.nameT=nameT;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType=nameType;
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
        buffer.append("NamespaceType(\n");

        buffer.append(" "+tab+nameT);
        buffer.append("\n");

        buffer.append(" "+tab+nameType);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NamespaceType]");
        return buffer.toString();
    }
}
