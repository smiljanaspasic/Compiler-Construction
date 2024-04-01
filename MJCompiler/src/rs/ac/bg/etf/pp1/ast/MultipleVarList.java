// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class MultipleVarList extends VarList {

    private VarList VarList;
    private VarAssign VarAssign;

    public MultipleVarList (VarList VarList, VarAssign VarAssign) {
        this.VarList=VarList;
        if(VarList!=null) VarList.setParent(this);
        this.VarAssign=VarAssign;
        if(VarAssign!=null) VarAssign.setParent(this);
    }

    public VarList getVarList() {
        return VarList;
    }

    public void setVarList(VarList VarList) {
        this.VarList=VarList;
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
        if(VarList!=null) VarList.accept(visitor);
        if(VarAssign!=null) VarAssign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarList!=null) VarList.traverseTopDown(visitor);
        if(VarAssign!=null) VarAssign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarList!=null) VarList.traverseBottomUp(visitor);
        if(VarAssign!=null) VarAssign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleVarList(\n");

        if(VarList!=null)
            buffer.append(VarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarAssign!=null)
            buffer.append(VarAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleVarList]");
        return buffer.toString();
    }
}
