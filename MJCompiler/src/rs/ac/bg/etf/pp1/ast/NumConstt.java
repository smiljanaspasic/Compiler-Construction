// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class NumConstt extends NumConst {

    private Integer numb;

    public NumConstt (Integer numb) {
        this.numb=numb;
    }

    public Integer getNumb() {
        return numb;
    }

    public void setNumb(Integer numb) {
        this.numb=numb;
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
        buffer.append("NumConstt(\n");

        buffer.append(" "+tab+numb);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumConstt]");
        return buffer.toString();
    }
}
