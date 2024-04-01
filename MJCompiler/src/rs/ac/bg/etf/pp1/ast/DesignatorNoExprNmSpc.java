// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class DesignatorNoExprNmSpc extends Designator {

    private String namespaceT;
    private String nameType;

    public DesignatorNoExprNmSpc (String namespaceT, String nameType) {
        this.namespaceT=namespaceT;
        this.nameType=nameType;
    }

    public String getNamespaceT() {
        return namespaceT;
    }

    public void setNamespaceT(String namespaceT) {
        this.namespaceT=namespaceT;
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
        buffer.append("DesignatorNoExprNmSpc(\n");

        buffer.append(" "+tab+namespaceT);
        buffer.append("\n");

        buffer.append(" "+tab+nameType);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorNoExprNmSpc]");
        return buffer.toString();
    }
}
