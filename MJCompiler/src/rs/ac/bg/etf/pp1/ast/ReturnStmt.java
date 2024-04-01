// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class ReturnStmt extends Statement {

    private ExprDetails ExprDetails;

    public ReturnStmt (ExprDetails ExprDetails) {
        this.ExprDetails=ExprDetails;
        if(ExprDetails!=null) ExprDetails.setParent(this);
    }

    public ExprDetails getExprDetails() {
        return ExprDetails;
    }

    public void setExprDetails(ExprDetails ExprDetails) {
        this.ExprDetails=ExprDetails;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprDetails!=null) ExprDetails.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprDetails!=null) ExprDetails.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprDetails!=null) ExprDetails.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnStmt(\n");

        if(ExprDetails!=null)
            buffer.append(ExprDetails.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnStmt]");
        return buffer.toString();
    }
}
