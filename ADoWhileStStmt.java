/* This file was generated by SableCC (http://www.sablecc.org/). */

package decaf.node;

import decaf.analysis.*;

@SuppressWarnings("nls")
public final class ADoWhileStStmt extends PStmt
{
    private PDoWhileStmt _doWhileStmt_;

    public ADoWhileStStmt()
    {
        // Constructor
    }

    public ADoWhileStStmt(
        @SuppressWarnings("hiding") PDoWhileStmt _doWhileStmt_)
    {
        // Constructor
        setDoWhileStmt(_doWhileStmt_);

    }

    @Override
    public Object clone()
    {
        return new ADoWhileStStmt(
            cloneNode(this._doWhileStmt_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADoWhileStStmt(this);
    }

    public PDoWhileStmt getDoWhileStmt()
    {
        return this._doWhileStmt_;
    }

    public void setDoWhileStmt(PDoWhileStmt node)
    {
        if(this._doWhileStmt_ != null)
        {
            this._doWhileStmt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._doWhileStmt_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._doWhileStmt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._doWhileStmt_ == child)
        {
            this._doWhileStmt_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._doWhileStmt_ == oldChild)
        {
            setDoWhileStmt((PDoWhileStmt) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
