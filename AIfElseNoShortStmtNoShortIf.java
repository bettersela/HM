/* This file was generated by SableCC (http://www.sablecc.org/). */

package decaf.node;

import decaf.analysis.*;

@SuppressWarnings("nls")
public final class AIfElseNoShortStmtNoShortIf extends PStmtNoShortIf
{
    private PIfElseStmtNoShortIf _ifElseStmtNoShortIf_;

    public AIfElseNoShortStmtNoShortIf()
    {
        // Constructor
    }

    public AIfElseNoShortStmtNoShortIf(
        @SuppressWarnings("hiding") PIfElseStmtNoShortIf _ifElseStmtNoShortIf_)
    {
        // Constructor
        setIfElseStmtNoShortIf(_ifElseStmtNoShortIf_);

    }

    @Override
    public Object clone()
    {
        return new AIfElseNoShortStmtNoShortIf(
            cloneNode(this._ifElseStmtNoShortIf_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIfElseNoShortStmtNoShortIf(this);
    }

    public PIfElseStmtNoShortIf getIfElseStmtNoShortIf()
    {
        return this._ifElseStmtNoShortIf_;
    }

    public void setIfElseStmtNoShortIf(PIfElseStmtNoShortIf node)
    {
        if(this._ifElseStmtNoShortIf_ != null)
        {
            this._ifElseStmtNoShortIf_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ifElseStmtNoShortIf_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._ifElseStmtNoShortIf_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._ifElseStmtNoShortIf_ == child)
        {
            this._ifElseStmtNoShortIf_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._ifElseStmtNoShortIf_ == oldChild)
        {
            setIfElseStmtNoShortIf((PIfElseStmtNoShortIf) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}