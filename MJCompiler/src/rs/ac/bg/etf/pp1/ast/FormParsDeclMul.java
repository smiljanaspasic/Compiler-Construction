// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class FormParsDeclMul extends FormParsDecl {

    private FormParsDecl FormParsDecl;
    private FormAssign FormAssign;

    public FormParsDeclMul (FormParsDecl FormParsDecl, FormAssign FormAssign) {
        this.FormParsDecl=FormParsDecl;
        if(FormParsDecl!=null) FormParsDecl.setParent(this);
        this.FormAssign=FormAssign;
        if(FormAssign!=null) FormAssign.setParent(this);
    }

    public FormParsDecl getFormParsDecl() {
        return FormParsDecl;
    }

    public void setFormParsDecl(FormParsDecl FormParsDecl) {
        this.FormParsDecl=FormParsDecl;
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
        if(FormParsDecl!=null) FormParsDecl.accept(visitor);
        if(FormAssign!=null) FormAssign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsDecl!=null) FormParsDecl.traverseTopDown(visitor);
        if(FormAssign!=null) FormAssign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsDecl!=null) FormParsDecl.traverseBottomUp(visitor);
        if(FormAssign!=null) FormAssign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParsDeclMul(\n");

        if(FormParsDecl!=null)
            buffer.append(FormParsDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormAssign!=null)
            buffer.append(FormAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParsDeclMul]");
        return buffer.toString();
    }
}
