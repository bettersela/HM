/* This file was generated by SableCC (http://www.sablecc.org/). */

package decaf.node;

import decaf.analysis.*;

@SuppressWarnings("nls")
public final class ACompoundStmtNoTrailer extends PStmtNoTrailer
{
    private PCompoundStmt _compoundStmt_;

    public ACompoundStmtNoTrailer()
    {
        // Constructor
    }

    public ACompoundStmtNoTrailer(
        @SuppressWarnings("hiding") PCompoundStmt _compoundStmt_)
    {
        // Constructor
        setCompoundStmt(_compoundStmt_);

    }

    @Override
    public Object clone()
    {
        return new ACompoundStmtNoTrailer(
            cloneNode(this._compoundStmt_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACompoundStmtNoTrailer(this);
    }

    public PCompoundStmt getCompoundStmt()
    {
        return this._compoundStmt_;
    }

    public void setCompoundStmt(PCompoundStmt node)
    {
        if(this._compoundStmt_ != null)
        {
            this._compoundStmt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._compoundStmt_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._compoundStmt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._compoundStmt_ == child)
        {
            this._compoundStmt_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._compoundStmt_ == oldChild)
        {
            setCompoundStmt((PCompoundStmt) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
