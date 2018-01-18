/* This file was generated by SableCC (http://www.sablecc.org/). */

package decaf.node;

import decaf.analysis.*;

@SuppressWarnings("nls")
public final class APlusassnExpr extends PExpr
{
    private PPlusassignExpr _plusassignExpr_;

    public APlusassnExpr()
    {
        // Constructor
    }

    public APlusassnExpr(
        @SuppressWarnings("hiding") PPlusassignExpr _plusassignExpr_)
    {
        // Constructor
        setPlusassignExpr(_plusassignExpr_);

    }

    @Override
    public Object clone()
    {
        return new APlusassnExpr(
            cloneNode(this._plusassignExpr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPlusassnExpr(this);
    }

    public PPlusassignExpr getPlusassignExpr()
    {
        return this._plusassignExpr_;
    }

    public void setPlusassignExpr(PPlusassignExpr node)
    {
        if(this._plusassignExpr_ != null)
        {
            this._plusassignExpr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._plusassignExpr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._plusassignExpr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._plusassignExpr_ == child)
        {
            this._plusassignExpr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._plusassignExpr_ == oldChild)
        {
            setPlusassignExpr((PPlusassignExpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}