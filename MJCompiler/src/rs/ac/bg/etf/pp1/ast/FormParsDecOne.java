// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class FormParsDecOne extends FormParsDecl {

    private FormAssign FormAssign;

    public FormParsDecOne (FormAssign FormAssign) {
        this.FormAssign=FormAssign;
        if(FormAssign!=null) FormAssign.setParent(this);
    }

    public FormAssign getFormAssign() {
        return FormAssign;
    }

    public void setFormAssign(FormAssign FormAssign) {
        this.FormAssign=FormAssign;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormAssign!=null) FormAssign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormAssign!=null) FormAssign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormAssign!=null) FormAssign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParsDecOne(\n");

        if(FormAssign!=null)
            buffer.append(FormAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParsDecOne]");
        return buffer.toString();
    }
}
