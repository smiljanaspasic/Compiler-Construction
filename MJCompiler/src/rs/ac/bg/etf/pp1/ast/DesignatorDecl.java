// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class DesignatorDecl extends Designator {

    private DesignatorArray DesignatorArray;
    private Expr Expr;

    public DesignatorDecl (DesignatorArray DesignatorArray, Expr Expr) {
        this.DesignatorArray=DesignatorArray;
        if(DesignatorArray!=null) DesignatorArray.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorArray getDesignatorArray() {
        return DesignatorArray;
    }

    public void setDesignatorArray(DesignatorArray DesignatorArray) {
        this.DesignatorArray=DesignatorArray;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArray!=null) DesignatorArray.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArray!=null) DesignatorArray.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArray!=null) DesignatorArray.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorDecl(\n");

        if(DesignatorArray!=null)
            buffer.append(DesignatorArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorDecl]");
        return buffer.toString();
    }
}
