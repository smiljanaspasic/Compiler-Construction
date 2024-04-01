// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class ErrorV extends VarList {

    private VarAssign VarAssign;

    public ErrorV (VarAssign VarAssign) {
        this.VarAssign=VarAssign;
        if(VarAssign!=null) VarAssign.setParent(this);
    }

    public VarAssign getVarAssign() {
        return VarAssign;
    }

    public void setVarAssign(VarAssign VarAssign) {
        this.VarAssign=VarAssign;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarAssign!=null) VarAssign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarAssign!=null) VarAssign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarAssign!=null) VarAssign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ErrorV(\n");

        if(VarAssign!=null)
            buffer.append(VarAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ErrorV]");
        return buffer.toString();
    }
}
