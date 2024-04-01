// generated with ast extension for cup
// version 0.8
// 16/0/2024 21:47:23


package rs.ac.bg.etf.pp1.ast;

public class StatementDeclListt extends StatementDeclList {

    private StatementDeclList StatementDeclList;
    private Statement Statement;

    public StatementDeclListt (StatementDeclList StatementDeclList, Statement Statement) {
        this.StatementDeclList=StatementDeclList;
        if(StatementDeclList!=null) StatementDeclList.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public StatementDeclList getStatementDeclList() {
        return StatementDeclList;
    }

    public void setStatementDeclList(StatementDeclList StatementDeclList) {
        this.StatementDeclList=StatementDeclList;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementDeclList!=null) StatementDeclList.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementDeclList!=null) StatementDeclList.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementDeclList!=null) StatementDeclList.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementDeclListt(\n");

        if(StatementDeclList!=null)
            buffer.append(StatementDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementDeclListt]");
        return buffer.toString();
    }
}
